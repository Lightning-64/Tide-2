package com.li64.tide.registries.items;

import com.li64.tide.Tide;
import com.li64.tide.data.TideTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ChasmEelItem extends AbstractTooltipItem {
    public ChasmEelItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() == null || !Tide.SERVER_CONFIG.items.enableBedrockBreakingItems) return super.useOn(context);
        ItemStack eelStack = context.getItemInHand();
        Level level = context.getPlayer().level();
        BlockState usedOn = level.getBlockState(context.getClickedPos());
        if (!usedOn.is(TideTags.Blocks.CHASM_EEL_CAN_EAT)) return super.useOn(context);
        if (!context.getPlayer().isCreative()) eelStack.shrink(1);
        if (level.isClientSide()) return InteractionResult.SUCCESS;
        level.destroyBlock(context.getClickedPos(), false, context.getPlayer());
        level.playSound(null, context.getClickedPos(), SoundEvents.WITHER_BREAK_BLOCK, SoundSource.MASTER);
        level.playSound(null, context.getClickedPos(), SoundEvents.PLAYER_BURP, SoundSource.MASTER);
        return InteractionResult.CONSUME;
    }

    @Override
    public void addTooltip(ItemStack stack, Consumer<Component> tooltip) {
        if (!Tide.SERVER_CONFIG.items.enableBedrockBreakingItems) return;
        Style gray = Component.empty().getStyle().withColor(ChatFormatting.GRAY);
        tooltip.accept(Component.translatable("item.tide.chasm_eel.desc").setStyle(gray));
    }
}
