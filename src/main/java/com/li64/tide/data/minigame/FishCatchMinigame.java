package com.li64.tide.data.minigame;

import com.li64.tide.Tide;
import com.li64.tide.compat.CompatHelper;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.fishing.mediums.FishingMedium;
import com.li64.tide.network.messages.MinigameClientMsg;
import com.li64.tide.registries.TideItems;
import com.li64.tide.registries.entities.misc.fishing.HookAccessor;
import com.li64.tide.registries.entities.misc.fishing.TideFishingHook;
import com.li64.tide.util.TideUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class FishCatchMinigame {
    private static final int SERVER_DELAY_MILLIS = 200;
    private static final ArrayList<FishCatchMinigame> ACTIVE_MINIGAMES = new ArrayList<>();
    private static final HashMap<ServerPlayer, Long> ACTIVE_DELAYS = new HashMap<>();

    private final TideFishingHook hook;
    private final ServerPlayer player;

    public static FishCatchMinigame getInstance(Player player) {
        for (FishCatchMinigame minigame : ACTIVE_MINIGAMES) {
            if (minigame.getPlayer() == player) return minigame;
        }
        return null;
    }

    public static FishCatchMinigame create(Player player) {
        FishCatchMinigame existingInstance = getInstance(player);
        if (existingInstance != null) return existingInstance;

        FishCatchMinigame minigame = new FishCatchMinigame((ServerPlayer) player);
        ACTIVE_MINIGAMES.add(minigame);
        return minigame;
    }

    public static boolean minigameActive(Player player) {
        for (FishCatchMinigame minigame : ACTIVE_MINIGAMES) {
            if (minigame.getPlayer() == player) return true;
        }
        return false;
    }

    protected FishCatchMinigame(ServerPlayer player) {
        this.player = player;
        this.hook = Objects.requireNonNull(HookAccessor.getHook(player));

        hook.setMinigameActive(true);
        Optional<FishData> data = TideUtils.getStrongest(hook.getHookedItems());
        int behavior = data.map(d -> d.behavior().ordinal()).orElse(0);
        float fishStrength = data.map(FishData::strength).orElse(0.2f); // miss area percentage
        float fishSpeed = data.map(FishData::speed).orElse(0.5f); // movements per second

        if (hook.getLine().is(TideItems.COPPER_LINE)) fishSpeed *= 0.9f;
        if (hook.getLine().is(TideItems.IRON_LINE)) fishStrength *= 0.86f;
        if (hook.getLine().is(TideItems.GOLDEN_LINE)) fishSpeed *= 0.95f;
        if (hook.getLine().is(TideItems.DIAMOND_LINE)) fishStrength *= 0.75f;

        float area = Mth.clamp(1 - fishStrength, 0.05f, 1.0f);
        float speed = Math.max(fishSpeed / 20f * Tide.SERVER_CONFIG.minigame.minigameDifficultyMultiplier, 0.05f);

        // Start client minigame gui
        int type = 0;
        if (hook.getCurrentMedium() == FishingMedium.LAVA) type = 1;
        if (hook.getCurrentMedium() == FishingMedium.VOID) type = 2;
        Tide.NETWORK.sendToPlayer(new MinigameClientMsg((byte) 0, (byte) type, (byte) behavior, area, speed), player);
    }

    public static boolean delayActive(ServerPlayer player) {
        if (!ACTIVE_DELAYS.containsKey(player)) return false;
        if (System.currentTimeMillis() > ACTIVE_DELAYS.get(player)) {
            ACTIVE_DELAYS.remove(player);
            return false;
        } else return true;
    }

    private ServerPlayer getPlayer() {
        return player;
    }

    public void onFinish() {
        if (cancelIfNecessary()) return;
        hook.setMinigameActive(false);
        Tide.NETWORK.sendToPlayer(new MinigameClientMsg(2), player);
        ACTIVE_MINIGAMES.remove(this);
        ACTIVE_DELAYS.put(player, System.currentTimeMillis() + SERVER_DELAY_MILLIS);
    }

    public void onTimeout() {
        if (cancelIfNecessary()) return;
        onFinish();
    }

    public void onFail() {
        if (cancelIfNecessary()) return;
        if (Tide.SERVER_CONFIG.minigame.doFailSound) hook.level().playSound(
                null, hook.getPlayerOwner().blockPosition(),
                SoundEvents.SHEEP_SHEAR, SoundSource.AMBIENT,
                0.9f, 1.0f);
        hook.invalidateCatch();
        hook.retrieve();
        onFinish();
    }

    public void onWin(boolean perfectCatch) {
        if (cancelIfNecessary()) return;
        if (Tide.SERVER_CONFIG.minigame.doSuccessSound) hook.level().playSound(
                null, hook.getPlayerOwner().blockPosition(),
                SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.AMBIENT,
                0.15f, 1.0f);
        if (CompatHelper.useStarcatcherMinigame())
            CompatHelper.starcatcherCompleteCatch(player, hook, perfectCatch);
        hook.retrieve(perfectCatch);
        onFinish();
    }

    public boolean cancelIfNecessary() {
        if (hook == null || player == null) {
            Tide.NETWORK.sendToPlayer(new MinigameClientMsg(2), player);
            ACTIVE_MINIGAMES.remove(this);
            return true;
        } else return false;
    }

    public void handleClientEvent(byte event) {
        cancelIfNecessary();
        switch (event) {
            case 0 -> onTimeout();
            case 1 -> onFail();
            case 2 -> onWin(false);
            case 3 -> onWin(true);
        }
    }
}
