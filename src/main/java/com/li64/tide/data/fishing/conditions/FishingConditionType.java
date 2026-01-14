package com.li64.tide.data.fishing.conditions;

import com.li64.tide.Tide;
import com.li64.tide.data.fishing.conditions.types.*;
import com.li64.tide.registries.TideRegistries;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public interface FishingConditionType<T extends FishingCondition> {
    FishingConditionType<EitherCondition> EITHER = FishingConditionType.register("either", EitherCondition.CODEC);
    FishingConditionType<NotCondition> NOT = FishingConditionType.register("not", NotCondition.CODEC);
    FishingConditionType<FreshwaterCondition> FRESHWATER = FishingConditionType.register("freshwater", FreshwaterCondition.CODEC);
    FishingConditionType<SaltwaterCondition> SALTWATER = FishingConditionType.register("saltwater", SaltwaterCondition.CODEC);
    FishingConditionType<DimensionsCondition> DIMENSION = FishingConditionType.register("dimension", DimensionsCondition.CODEC);
    FishingConditionType<BiomeWhitelistCondition> BIOME_WHITELIST = FishingConditionType.register("found_in", BiomeWhitelistCondition.CODEC);
    FishingConditionType<TimeOfDayCondition> TIME_OF_DAY = FishingConditionType.register("time_of_day", TimeOfDayCondition.CODEC);
    FishingConditionType<FishingMediumCondition> MEDIUM = FishingConditionType.register("fluid", FishingMediumCondition.CODEC);
    FishingConditionType<AboveCondition> ABOVE = FishingConditionType.register("above", AboveCondition.CODEC);
    FishingConditionType<BelowCondition> BELOW = FishingConditionType.register("below", BelowCondition.CODEC);
    FishingConditionType<DepthRangeCondition> DEPTH_RANGE = FishingConditionType.register("depth_range", DepthRangeCondition.CODEC);
    FishingConditionType<StructuresCondition> STRUCTURES = FishingConditionType.register("found_in_structures", StructuresCondition.CODEC);
    FishingConditionType<LuckCondition> LUCK = FishingConditionType.register("luck", LuckCondition.CODEC);
    FishingConditionType<MoonPhaseCondition> MOON_PHASE = FishingConditionType.register("moon_phase", MoonPhaseCondition.CODEC);
    FishingConditionType<WeatherCondition> WEATHER = FishingConditionType.register("weather", WeatherCondition.CODEC);
    FishingConditionType<OpenWaterCondition> OPEN_WATER = FishingConditionType.register("open_water", OpenWaterCondition.CODEC);
    FishingConditionType<BlockNearbyCondition> BLOCK_NEARBY = FishingConditionType.register("block_nearby", BlockNearbyCondition.CODEC);
    FishingConditionType<SeasonsCondition> SEASONS = FishingConditionType.register("seasons", SeasonsCondition.CODEC);
    FishingConditionType<EnchantmentsCondition> HAS_ENCHANTMENTS = FishingConditionType.register("has_enchantments", EnchantmentsCondition.CODEC);

    static void register() {
        Tide.LOG.info("Registering fishing conditions");
    }

    MapCodec<T> codec();

    static <T extends FishingCondition> FishingConditionType<T> register(String name, MapCodec<T> codec) {
        return register(Tide.resource(name), codec);
    }

    static <T extends FishingCondition> FishingConditionType<T> register(ResourceLocation name, MapCodec<T> codec) {
        /*? if !forge {*/return Registry.register(TideRegistries.FISHING_CONDITIONS, name, () -> codec);
         //?} else {
        /*FishingConditionType<T> type = () -> codec;
        TideRegistries.FISHING_CONDITIONS.register(name, type);
        return type;
        *///?}
    }
}