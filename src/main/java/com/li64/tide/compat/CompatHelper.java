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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/*? if neoforge || forge*/ /*import com.li64.tide.compat.stardewfishing.StardewFishingCompat;*/

public class CompatHelper {
    public static boolean isHybridAquaticLoaded() {
        return Tide.PLATFORM.isModLoaded("hybrid-aquatic") || Tide.PLATFORM.isModLoaded("hybrid_aquatic");
    }

    public static Entity hybridAquaticPullEntity(ItemEntity itemEntity, Player player, TideFishingHook hook) {
        return HybridAquaticCompat.convertEntity(itemEntity, player, hook);
    }

    public static void hybridAquaticApplyVariant(Entity entity, ItemStack stack) {
        HybridAquaticCompat.applyVariant(entity, stack);
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

    public static Entity fishingRealConvertItemStack(ItemStack stack, Player player, Vec3 pos) {
        return FishingRealCompat.convertItemStack(stack, player, pos);
    }
}
