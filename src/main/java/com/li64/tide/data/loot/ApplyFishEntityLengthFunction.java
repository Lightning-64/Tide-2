package com.li64.tide.data.loot;

import com.li64.tide.Tide;
import com.li64.tide.config.TideServerConfig;
import com.li64.tide.data.FishLengthHolder;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.item.TideItemData;
import com.li64.tide.registries.TideLootFunctions;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ApplyFishEntityLengthFunction implements LootItemFunction {
    public static final MapCodec<ApplyFishEntityLengthFunction> CODEC = MapCodec.unit(ApplyFishEntityLengthFunction::new);

    public ApplyFishEntityLengthFunction() {}

    public @NotNull LootItemFunctionType/*? if >=1.21 {*/<ApplyFishEntityLengthFunction>/*?}*/ getType() {
        return TideLootFunctions.APPLY_FISH_ENTITY_LENGTH;
    }

    @Override
    public @NotNull Set<LootContextParam<?>> getReferencedContextParams() {
        return Set.of(LootContextParams.THIS_ENTITY);
    }

    @Override
    public ItemStack apply(ItemStack stack, LootContext context) {
        if (Tide.SERVER_CONFIG.items.fishItemSizes != TideServerConfig.Items.SizeMode.ALWAYS
                || FishData.get(stack).map(FishData::size).isEmpty()) return stack;
        Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);
        if (entity == null) return stack;
        if (entity instanceof FishLengthHolder lengthHolder) {
            TideItemData.FISH_LENGTH.set(stack, lengthHolder.tide$getLength());
        }
        return stack;
    }
}
