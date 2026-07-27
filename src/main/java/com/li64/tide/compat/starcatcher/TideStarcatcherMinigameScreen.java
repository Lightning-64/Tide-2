//? if neoforge {
/*package com.li64.tide.compat.starcatcher;

import com.li64.tide.Tide;
import com.li64.tide.network.messages.MinigameServerMsg;
import com.li64.tide.network.messages.StarcatcherStartMinigameMsg;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import com.wdiscute.starcatcher.modifiers.minigamemodifiers.AbstractMinigameModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class TideStarcatcherMinigameScreen extends FishingMinigameScreen {
    public TideStarcatcherMinigameScreen(FishProperties properties, List<AbstractMinigameModifier> modifiers) {
        super(properties, ItemStack.EMPTY, modifiers, Starcatcher.TACKLE_SKIN_REGISTRY.get(Starcatcher.rl("rod")));
    }

    public static void open(StarcatcherStartMinigameMsg message, Player player) {
        List<AbstractMinigameModifier> modifiers = message.modifiers().stream()
                .filter(AbstractMinigameModifier.class::isInstance)
                .map(AbstractMinigameModifier.class::cast)
                .toList();
        Minecraft.getInstance().setScreen(new TideStarcatcherMinigameScreen(message.properties(), modifiers));
    }

    @Override
    public void onClose() {
        boolean wonMinigame = this.progressSmooth > this.hp;
        super.onClose();
        if (wonMinigame) Tide.NETWORK.sendToServer(new MinigameServerMsg((byte) (this.perfectCatch ? 3 : 2)));
        else Tide.NETWORK.sendToServer(new MinigameServerMsg((byte) 1));
    }
}
*///?}
