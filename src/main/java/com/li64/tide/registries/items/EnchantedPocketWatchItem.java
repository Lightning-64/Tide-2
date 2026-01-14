package com.li64.tide.registries.items;

import com.li64.tide.Tide;
import com.li64.tide.data.FreezableMob;
import com.li64.tide.data.TideCriteriaTriggers;
import com.li64.tide.data.TideTags;
import com.li64.tide.registries.TideParticleTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class EnchantedPocketWatchItem extends PocketWatchItem {
    public EnchantedPocketWatchItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!(target instanceof Mob mob) || !(target instanceof FreezableMob freezable)
                || player.getCooldowns().isOnCooldown(this) || target.getType().is(TideTags.Entities.IGNORES_POCKET_WATCH)
                || Tide.CONFIG.items.pocketWatchBlacklist.contains(BuiltInRegistries.ENTITY_TYPE.getKey(target.getType()).toString())) {
            return super.interactLivingEntity(stack, player, target, hand);
        }
        if (!(player.level() instanceof ServerLevel level)) return InteractionResult.SUCCESS;
        if (freezable.tide$isFrozen()) {
            level.playSound(null, mob.blockPosition(), SoundEvents.CHAIN_BREAK, SoundSource.PLAYERS, 1.0f, 1.0f);
            freezable.tide$setFrozen(false);
            player.getCooldowns().addCooldown(this, 20);
        }
        else {
            if (BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType()).getPath().equals("coelacanth")) TideCriteriaTriggers.FROZE_COELACANTH.trigger((ServerPlayer) player);
            level.playSound(null, mob.blockPosition(), SoundEvents.CHAIN_PLACE, SoundSource.PLAYERS, 0.8f, 1.0f);
            level.playSound(null, mob.blockPosition(), SoundEvents.ELDER_GUARDIAN_CURSE, SoundSource.PLAYERS, 0.8f, 0.9f);
            level.sendParticles(TideParticleTypes.MAGIC_CHAIN, mob.getX(), mob.getY() + mob.getBbHeight() / 2, mob.getZ(), 8, 0, 0, 0, 0);
            freezable.tide$setFrozen(true);
            player.getCooldowns().addCooldown(this, 200);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void addTooltip(ItemStack stack, Consumer<Component> tooltip) {
        Style gray = Component.empty().getStyle().withColor(ChatFormatting.GRAY);
        Style gold = Component.empty().getStyle().withColor(ChatFormatting.GOLD);
        tooltip.accept(Component.translatable("item.tide.pocket_watch.desc").setStyle(gray));
        tooltip.accept(Component.translatable("item.tide.enchanted_pocket_watch.desc").setStyle(gold));
    }

    //? if <1.21 {
    /*@Override
    public boolean isFoil(ItemStack itemStack) {
        return true;
    }
    *///?}
}
