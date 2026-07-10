//? if neoforge {
/*package com.li64.tide.compat.starcatcher;

import com.li64.tide.Tide;
import com.li64.tide.config.TideConfig;
import com.li64.tide.data.rods.CustomRodManager;
import com.li64.tide.network.messages.StarcatcherStartMinigameMsg;
import com.li64.tide.registries.TideItems;
import com.li64.tide.registries.entities.misc.fishing.HookAccessor;
import com.li64.tide.registries.entities.misc.fishing.TideFishingHook;
import com.wdiscute.starcatcher.data.FishCaughtCounter;
import com.wdiscute.starcatcher.fish.FishApi;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.modifiers.Modifier;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.AddBasicSweetSpotModifier;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.AdjustBaseHandleSpeedModifier;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.AdjustMovingModifier;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.AdjustVanishingRate;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.SteadyBobberModifier;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.StoneHookModifier;
import com.wdiscute.starcatcher.fish.Difficulty;
import com.wdiscute.starcatcher.tournament.TournamentHandler;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StarcatcherCompat {
    private static final Set<ResourceLocation> WARNED_MISSING_FISH = new HashSet<>();

    public static boolean start(ServerPlayer player, HookAccessor hook, ItemStack rod, List<ItemStack> hookedItems) {
        if (hookedItems.isEmpty()) return false;

        // try get minigame properties for current fish
        Optional<FishProperties> optional = getFishProperties(player.level(), hookedItems.get(0));

        // unwrap it or use a fallback if it doesn't exist
        FishProperties properties = optional.orElseGet(() ->
                FishProperties.empty().withFish(hookedItems.get(0)));

        TideFishingHook tideHook = HookAccessor.getHook(player);
        if (tideHook != null) tideHook.setMinigameStartTime(System.currentTimeMillis());

        // start the minigame
        Tide.NETWORK.sendToPlayer(new StarcatcherStartMinigameMsg(properties, getMinigameModifiers(rod)), player);
        return true;
    }

    public static void completeCatch(ServerPlayer player, TideFishingHook hook, boolean perfectCatch) {
        List<ItemStack> hookedItems = hook.getHookedItems();
        if (hookedItems == null || hookedItems.isEmpty()) return;
        ItemStack caught = hookedItems.get(0);

        Optional<FishProperties> optional = getFishProperties(player.level(), caught);
        if (optional.isEmpty()) {
            ResourceLocation unknownFish = BuiltInRegistries.ITEM.getKey(caught.getItem());
            if (WARNED_MISSING_FISH.add(unknownFish)) {
                Tide.LOG.warn("No starcatcher fish properties found for {}", unknownFish);
            }
            return;
        }
        FishProperties properties = optional.get();

        RandomSource random = player.level().getRandom();
        float percentile = random.nextFloat() * 100f;
        boolean golden = random.nextFloat() < properties.sizeWeight().goldenChance()
                && FishCaughtCounter.canCatchGolden(properties, player);
        ResourceLocation fishId = BuiltInRegistries.ITEM.getKey(caught.getItem());

        long startTime = hook.getMinigameStartTime();
        int ticks = startTime > 0 ? (int) ((System.currentTimeMillis() - startTime) / 50L) : 0;

        FishCaughtCounter.awardFishCaughtCounter(properties, fishId, player, ticks, percentile,
                perfectCatch, true, golden, false);
        TournamentHandler.addScore(player, properties, perfectCatch, percentile);

        if (Tide.CONFIG.minigame.fishDataSource == TideConfig.Minigame.FishDataSource.STARCATCHER) {
            ItemStack scItem = FishApi.makeItemStack(hook.getRod().copy(), properties, percentile, golden, player, perfectCatch);
            scItem.setCount(caught.getCount());
            hook.replacePrimaryCatch(scItem);
        }
    }

    private static Optional<FishProperties> getFishProperties(Level level, ItemStack fish) {
        return Optional.ofNullable(FishApi.getFP(level, BuiltInRegistries.ITEM.getKey(fish.getItem())));
    }

    private static List<Modifier> getMinigameModifiers(ItemStack rod) {
        ItemStack line = CustomRodManager.getLine(rod);
        List<Modifier> modifiers = new ArrayList<>();

        if (line.is(TideItems.IRON_LINE)) {
            modifiers.add(new SteadyBobberModifier(""));
        }
        if (line.is(TideItems.COPPER_LINE)) {
            modifiers.add(new AdjustMovingModifier(0.5f, ""));
            modifiers.add(new AdjustVanishingRate(1 / 3f, ""));
            modifiers.add(new AdjustBaseHandleSpeedModifier(0.75f, ""));
        }
        if (line.is(TideItems.GOLDEN_LINE)) {
            modifiers.add(new AdjustVanishingRate(1 / 3f, ""));
            modifiers.add(new AdjustMovingModifier(0.5f, ""));
            modifiers.add(new StoneHookModifier(""));
        }
        if (line.is(TideItems.DIAMOND_LINE)) {
            modifiers.add(new AdjustVanishingRate(1 / 3f, ""));
            modifiers.add(new SteadyBobberModifier(""));
            modifiers.add(new AddBasicSweetSpotModifier(Difficulty.SweetSpot.NORMAL, ""));
            modifiers.add(new StoneHookModifier(""));
        }

        return modifiers;
    }
}
*///?} else if forge {

//?}
