package com.li64.tide.client;

import com.li64.tide.Tide;
import com.li64.tide.client.gui.TideToasts;
import com.li64.tide.client.gui.screens.FishyNoteScreen;
import com.li64.tide.client.gui.screens.journal.FishingJournal;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

//? if neoforge {
/*import com.li64.tide.network.messages.StarcatcherStartMinigameMsg;
import com.li64.tide.compat.starcatcher.StarcatcherCompat;
import net.minecraft.world.entity.player.Player;
*///?}

public class TideClientHelper {
    public static void openJournalScreen() {
        openJournalScreen(null);
    }

    public static void openJournalScreen(Item turnTo) {
        if (Minecraft.getInstance().player == null) return;
        FishingJournal screen = new FishingJournal(Minecraft.getInstance().player);
        Minecraft.getInstance().setScreen(screen);
    }

    public static void showToast(Component title, Component description, ItemStack display) {
        if (Tide.CONFIG.journal.showToasts) TideToasts.display(new TideToasts.NewPageToast(title, description, display));
    }

    public static void openNoteScreen(ItemStack stack) {
        Minecraft.getInstance().setScreen(new FishyNoteScreen(stack));
    }

    //? if neoforge {
    /*public static void startStarcatcherMinigame(StarcatcherStartMinigameMsg message, Player player) {
        if (Tide.PLATFORM.isModLoaded("starcatcher")) TideStarcatcherMinigameScreen.open(message, player);
    }
    *///?}
}
