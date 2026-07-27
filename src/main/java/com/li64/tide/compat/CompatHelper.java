package com.li64.tide.compat;

import com.li64.tide.Tide;
import com.li64.tide.compat.fishingreal.FishingRealCompat;
import com.li64.tide.compat.hybridaquatic.HybridAquaticCompat;
import com.li64.tide.registries.entities.misc.fishing.HookAccessor;
import com.li64.tide.registries.entities.misc.fishing.TideFishingHook;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

//? if fabric {
import com.li64.tide.compat.accessories.TrinketsCompat;
//?} else {
/*import com.li64.tide.compat.stardewfishing.StardewFishingCompat;
import com.li64.tide.compat.accessories.CuriosCompat;
/^? if neoforge^//^import com.li64.tide.compat.starcatcher.StarcatcherCompat;^/
*///?}

public class CompatHelper {
    // -- hybrid aquatic --

    public static boolean isHybridAquaticLoaded() {
        return Tide.PLATFORM.isModLoaded("hybrid_aquatic");
    }

    public static Entity hybridAquaticPullEntity(ItemEntity itemEntity, Player player, TideFishingHook hook) {
        return HybridAquaticCompat.convertEntity(itemEntity, player, hook);
    }

    public static void hybridAquaticApplyVariant(Entity entity, ItemStack stack) {
        HybridAquaticCompat.applyVariant(entity, stack);
    }

    // -- starcatcher --

    public static boolean useStarcatcherMinigame() {
        return Tide.PLATFORM.isModLoaded("starcatcher") && Tide.SERVER_CONFIG.minigame.useThirdPartyMinigames;
    }

    public static boolean starcatcherStartMinigame(ServerPlayer player, HookAccessor hook, ItemStack rod, List<ItemStack> hookedItems) {
        /*? if neoforge {*/ /*return StarcatcherCompat.start(player, hook, rod, hookedItems);
        *//*?} else*/ return false;
    }

    public static void starcatcherCompleteCatch(ServerPlayer player, TideFishingHook hook, boolean perfectCatch) {
        /*? if neoforge {*/ /*StarcatcherCompat.completeCatch(player, hook, perfectCatch);
        *//*?}*/
    }

    // -- stardew fishing --

    public static boolean useStardewMinigame() {
        return Tide.PLATFORM.isModLoaded("stardew_fishing") && Tide.SERVER_CONFIG.minigame.useThirdPartyMinigames;
    }

    public static boolean stardewFishingStartMinigame(ServerPlayer player, HookAccessor hook, ItemStack rod, List<ItemStack> hookedItems) {
        /*? if neoforge || forge {*/ /*return StardewFishingCompat.start(player, hook, rod, hookedItems);
        *//*?} else*/ return false;
    }

    public static List<ItemStack> stardewFishingGetRewards(HookAccessor hook) {
        /*? if neoforge || forge {*/ /*return StardewFishingCompat.getRewards(hook);
        *//*?} else*/ return List.of();
    }

    public static double stardewFishingBiteTimeMultiplier() {
        /*? if neoforge || forge {*/ /*return StardewFishingCompat.getBiteTimeMultiplier();
        *//*?} else*/ return 1.0;
    }

    // -- fishing real --

    public static Entity fishingRealConvertItemStack(ItemStack stack, Player player, Vec3 pos) {
        return FishingRealCompat.convertItemStack(stack, player, pos);
    }

    // -- curios/trinkets --

    public static void addInformationItemsFromAccessories(Player player, ArrayList<Item> items) {
        /*? if fabric {*/if (Tide.PLATFORM.isModLoaded("trinkets")) TrinketsCompat.addInformationalItems(player, items);
        /*?} else*//*if (Tide.PLATFORM.isModLoaded("curios")) CuriosCompat.addInformationalItems(player, items);*/
    }
}
