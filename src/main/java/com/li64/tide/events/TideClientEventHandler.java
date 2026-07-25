package com.li64.tide.events;

import com.li64.tide.Tide;
import com.li64.tide.config.TideServerConfig;
import com.li64.tide.data.TideTags;
import com.li64.tide.data.item.TideItemData;
import com.li64.tide.registries.items.TideFishingRodItem;
import com.li64.tide.util.BaitUtils;
import com.li64.tide.util.TideUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

//? if >=1.21 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
//?}

import java.util.List;

public class TideClientEventHandler {
    public static void onTooltipRender(ItemStack stack, List<Component> lines) {
        if (BaitUtils.isBait(stack)) lines.addAll(BaitUtils.getDescriptionLines(stack));

        if (stack.is(TideTags.Items.FISHING_RODS)) lines.addAll(TideFishingRodItem.getDescriptionLines(stack));

        if (Minecraft.getInstance().level != null
                && TideItemData.IS_BUCKETABLE.getOrDefault(stack, false)
                && Tide.SERVER_CONFIG.items.bucketableFishItems == TideServerConfig.Items.BucketableMode.WHEN_LIVING)
            lines.add(Component.translatable("text.tide.fish.alive").withStyle(ChatFormatting.GRAY));

        if (TideItemData.FISH_LENGTH.isPresent(stack)) {
            lines.add(TideUtils.getFormattedLength(stack).withStyle(ChatFormatting.BLUE));
        }
        //? if >=1.21 {
        else if (stack.has(DataComponents.BUCKET_ENTITY_DATA)) {
            CustomData data = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
            if (data.contains("Length")) {
                double length = data.copyTag().getDouble("Length");
                lines.add(TideUtils.getFormattedLength(length).withStyle(ChatFormatting.BLUE));
            }
        }
        //?}
    }
}
