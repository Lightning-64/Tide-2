package com.li64.tide.mixin;

import com.li64.tide.registries.TideItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ProjectileWeaponItem.class)
public abstract class ProjectileWeaponItemMixin {
    //? if >=1.21 {
    @Inject(at = @At("HEAD"), method = "draw", cancellable = true)
    private static void draw(ItemStack weapon, ItemStack ammo, LivingEntity shooter, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (weapon.is(TideItems.STARLIGHT_BOW) && shooter.getRandom().nextFloat() < 0.6f) cir.setReturnValue(List.of(Items.ARROW.getDefaultInstance()));
    }
    //?}
}
