package com.li64.tide.mixin;

import com.li64.tide.Tide;
import com.li64.tide.config.TideServerConfig;
import com.li64.tide.data.FishLengthHolder;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.item.TideItemData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//? if >=1.21 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
//?}

import java.util.Optional;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Inject(at = @At(value = "RETURN"), method = "overrideStackedOnOther", cancellable = true)
    public void overrideStackedOnOther(ItemStack stack, Slot slot, ClickAction action, Player player, CallbackInfoReturnable<Boolean> cir) {
        if (Tide.SERVER_CONFIG.items.bucketableFishItems != TideServerConfig.Items.BucketableMode.NEVER
                && stack.getItem() instanceof BucketItem bucket && !(bucket instanceof MobBucketItem)) {
            if (tide$tryFillBucket(slot, slot.getItem(), stack, bucket, player)) cir.setReturnValue(true);
        }
    }

    @Inject(at = @At(value = "RETURN"), method = "overrideOtherStackedOnMe", cancellable = true)
    public void overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access, CallbackInfoReturnable<Boolean> cir) {
        if (Tide.SERVER_CONFIG.items.bucketableFishItems != TideServerConfig.Items.BucketableMode.NEVER
                && stack.getItem() instanceof BucketItem bucket && !(bucket instanceof MobBucketItem)) {
            if (tide$tryFillBucket(slot, other, other, bucket, player)) cir.setReturnValue(true);
        }
    }

    @Unique
    public boolean tide$tryFillBucket(Slot slot, ItemStack fish, ItemStack held, BucketItem bucket, Player player) {
        Optional<FishData> dataOp = FishData.get(fish);
        if (dataOp.isEmpty()) return false;

        FishData data = dataOp.get();
        if (data.bucket().isEmpty()
                || (Tide.SERVER_CONFIG.items.bucketableFishItems == TideServerConfig.Items.BucketableMode.WHEN_LIVING
                        && !TideItemData.IS_BUCKETABLE.getOrDefault(fish, false))
                || !(data.bucket().get().value() instanceof BucketItem fishBucketItem))
            return false;

        //? if !forge {
        Fluid fluid = fishBucketItem.content;
        if (!bucket.content.isSame(fluid)) return false;
        //?} else {
        /*Fluid fluid = fishBucketItem.getFluid();
        Tide.LOG.info("fluid 1: {}, fluid 2: {}", bucket.getFluid(), fluid);
        if (!bucket.getFluid().isSame(fluid)) {
            Tide.LOG.info("check failed :(");
            return false;
        }
        *///?}

        ItemStack newStack = new ItemStack(data.bucket().get());
        if (TideItemData.FISH_LENGTH.isPresent(fish)) {
            double length = TideItemData.FISH_LENGTH.getOrDefault(fish, 0.0);
            /*? if >=1.21 {*/CustomData.update(DataComponents.BUCKET_ENTITY_DATA, newStack, tag -> {
                if (TideItemData.FISH_LENGTH.isPresent(fish)) tag.putDouble(FishLengthHolder.tide$LENGTH_KEY, length);
            });
            /*?} else*/
            /*newStack.getOrCreateTag().putDouble(FishLengthHolder.tide$LENGTH_KEY, length);*/
            
        }

        fish.shrink(1);
        if (held == fish) slot.setByPlayer(newStack);
        else {
            if (fish.getCount() > 0) player.containerMenu.setCarried(newStack);
            else {
                held.shrink(1);
                slot.set(newStack);
            }
        }
        player.playSound(SoundEvents.BUCKET_FILL_FISH, 1.0f, 1.0f);
        return true;
    }
}