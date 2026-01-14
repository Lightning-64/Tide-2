package com.li64.tide.data.fishing.conditions.types;

import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

//? if >=1.21 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.enchantment.ItemEnchantments;
//?}

import java.util.List;

public class EnchantmentsCondition extends FishingCondition {
    public static final MapCodec<EnchantmentsCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceKey.codec(Registries.ENCHANTMENT).listOf().optionalFieldOf("enchantments",List.of()).forGetter(EnchantmentsCondition::getEnchantments)
    ).apply(instance, EnchantmentsCondition::new));

    private final List<ResourceKey<Enchantment>> enchantments;

    public EnchantmentsCondition(List<ResourceKey<Enchantment>> enchantments) {
        this.enchantments = enchantments;
    }

    public List<ResourceKey<Enchantment>> getEnchantments() {
        return enchantments;
    }

    @Override
    public FishingConditionType<?> type() {
        return FishingConditionType.HAS_ENCHANTMENTS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean test(FishingContext context) {
        if (context.hook() == null || context.hook().rod() == null || context.hook().rod().isEmpty()) return false;
        //? if >=1.21 {
        ItemEnchantments enchantments = context.hook().rod().getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        return enchantments.keySet().stream().anyMatch(e -> this.enchantments.stream().anyMatch(e::is));
        //?} else {
        /*return EnchantmentHelper.getEnchantments(context.hook().rod()).keySet().stream().anyMatch(e ->
                this.enchantments.stream().anyMatch(e2 -> e2.location().equals(BuiltInRegistries.ENCHANTMENT.getKey(e))));
        *///?}
    }
}
