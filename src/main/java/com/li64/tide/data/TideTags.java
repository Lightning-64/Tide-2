package com.li64.tide.data;

import com.li64.tide.Tide;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class TideTags {
    public static class Items {
        public static final TagKey<Item> VANILLA_FISH = TagKey.create(Registries.ITEM, Tide.resource("vanilla_fish"));
        public static final TagKey<Item> BIOME_FISH = TagKey.create(Registries.ITEM, Tide.resource("biome_fish"));
        public static final TagKey<Item> LEGENDARY_FISH = TagKey.create(Registries.ITEM, Tide.resource("legendary_fish"));
        public static final TagKey<Item> COOKED_FISH = TagKey.create(Registries.ITEM, Tide.resource("cooked_fish"));
        public static final TagKey<Item> COOKABLE_FISH = TagKey.create(Registries.ITEM, Tide.resource("cookable_fish"));
        public static final TagKey<Item> FISH = TagKey.create(Registries.ITEM, Tide.resource("fish"));

        public static final TagKey<Item> TWILIGHT_ANGLER_EATABLE = TagKey.create(Registries.ITEM, Tide.resource("twilight_angler_eatable"));
        public static final TagKey<Item> BAIT_PLANTS = TagKey.create(Registries.ITEM, Tide.resource("bait_plants"));
        public static final TagKey<Item> CRATES = TagKey.create(Registries.ITEM, Tide.resource("crates"));

        public static final TagKey<Item> FISHING_RODS = TagKey.create(Registries.ITEM, Tide.resource("fishing_rods"));
        public static final TagKey<Item> BOBBERS = TagKey.create(Registries.ITEM, Tide.resource("bobbers"));
        public static final TagKey<Item> HOOKS = TagKey.create(Registries.ITEM, Tide.resource("hooks"));
        public static final TagKey<Item> LINES = TagKey.create(Registries.ITEM, Tide.resource("lines"));
        public static final TagKey<Item> LAVA_BUCKETS = TagKey.create(Registries.ITEM, Tide.resource("lava_buckets"));
    }

    public static class Blocks {
        public static final TagKey<Block> DESERT_WELL_LOOT = TagKey.create(Registries.BLOCK, Tide.resource("desert_well_loot"));
        public static final TagKey<Block> CHASM_EEL_CAN_EAT = TagKey.create(Registries.BLOCK, Tide.resource("chasm_eel_can_eat"));
    }

    public static class Fluids {
        public static final TagKey<Fluid> CAN_FISH_IN = TagKey.create(Registries.FLUID, Tide.resource("can_fish_in"));
        public static final TagKey<Fluid> WATER_FISHING = TagKey.create(Registries.FLUID, Tide.resource("types/water_fishing"));
        public static final TagKey<Fluid> LAVA_FISHING = TagKey.create(Registries.FLUID, Tide.resource("types/lava_fishing"));
    }
    public static class Entities {
        public static final TagKey<EntityType<?>> FRESHWATER_FISH = TagKey.create(
                Registries.ENTITY_TYPE, Tide.resource("freshwater_fish"));
        public static final TagKey<EntityType<?>> SALTWATER_FISH = TagKey.create(
                Registries.ENTITY_TYPE, Tide.resource("saltwater_fish"));
    }

    public static class Biomes {
        public static final TagKey<Biome> WATER_BIOMES = TagKey.create(
                Registries.BIOME, Tide.resource("water_biomes"));

        public static final TagKey<Biome> HAS_FISHING_BOAT = TagKey.create(
                Registries.BIOME, Tide.resource("has_structure/fishing_boat"));
        public static final TagKey<Biome> HAS_END_OASIS = TagKey.create(
                Registries.BIOME, Tide.resource("has_end_oasis"));

        public static final TagKey<Biome> CAN_CATCH_STARFISH = TagKey.create(
                Registries.BIOME, Tide.resource("can_catch_starfish"));

        public static final TagKey<Biome> IS_SALTWATER =
                TagKey.create(Registries.BIOME, Tide.resource("is_saltwater"));
        public static final TagKey<Biome> IS_COLD =
                TagKey.create(Registries.BIOME, Tide.resource("is_cold"));
        public static final TagKey<Biome> IS_WARM =
                TagKey.create(Registries.BIOME, Tide.resource("is_warm"));

        public static final TagKey<Biome> HAS_COASTAL_FISH = biomeFishTag("has_coastal_fish");
        public static final TagKey<Biome> HAS_DESERT_FISH = biomeFishTag("has_desert_fish");
        public static final TagKey<Biome> HAS_CHERRY_GROVE_FISH = biomeFishTag("has_cherry_grove_fish");
        public static final TagKey<Biome> HAS_FROZEN_FISH = biomeFishTag("has_frozen_fish");
        public static final TagKey<Biome> HAS_SWAMP_FISH = biomeFishTag("has_swamp_fish");
        public static final TagKey<Biome> HAS_JUNGLE_FISH = biomeFishTag("has_jungle_fish");
        public static final TagKey<Biome> HAS_MUSHROOM_FISH = biomeFishTag("has_mushroom_fish");
        public static final TagKey<Biome> HAS_DEEP_DARK_FISH = biomeFishTag("has_deep_dark_fish");
        public static final TagKey<Biome> HAS_DRIPSTONE_FISH = biomeFishTag("has_dripstone_fish");

        public static final TagKey<Biome> THE_VOID = biomeFishTag("the_void");

        public static TagKey<Biome> biomeFishTag(String path) {
            return TagKey.create(Registries.BIOME, Tide.resource(path));
        }
    }

    public static class Cookables {
        private static final Map<Item, TagKey<Item>> CACHE = new HashMap<>();

        public static TagKey<Item> getCookableTag(Item cookedItem) {
            return CACHE.computeIfAbsent(cookedItem, item -> TagKey.create(
                    Registries.ITEM,
                    Tide.resource(BuiltInRegistries.ITEM.getKey(item)
                            .withPrefix("cookable/")
                            .getPath())
            ));
        }
    }

    public static class Convention {
        public static final TagKey<Item> FISHING_ROD_TOOLS = create("tools/fishing_rod", Registries.ITEM);
        public static final TagKey<Item> BOW_TOOLS = create("tools/bow", Registries.ITEM);

        public static final TagKey<Item> LAVA_BUCKETS = create("buckets/lava", Registries.ITEM);

        public static final TagKey<Item> COPPER_INGOTS = create("ingots/copper", Registries.ITEM);
        public static final TagKey<Item> IRON_INGOTS = create("ingots/iron", Registries.ITEM);
        public static final TagKey<Item> GOLD_INGOTS = create("ingots/gold", Registries.ITEM);
        public static final TagKey<Item> NETHERITE_INGOTS = create("ingots/netherite", Registries.ITEM);

        public static final TagKey<Item> LAPIS_GEMS = create("gems/lapis", Registries.ITEM);
        public static final TagKey<Item> DIAMOND_GEMS = create("gems/diamond", Registries.ITEM);
        public static final TagKey<Item> AMETHYST_GEMS = create("gems/amethyst", Registries.ITEM);
        public static final TagKey<Item> PRISMARINE_GEMS = create("gems/prismarine", Registries.ITEM);

        public static final TagKey<Item> IRON_NUGGETS = create("nuggets/iron", Registries.ITEM);

        public static final TagKey<Item> REDSTONE_DUSTS = create("dusts/redstone", Registries.ITEM);

        public static final TagKey<Item> COBBLESTONES = create("cobblestones", Registries.ITEM);
        public static final TagKey<Item> OBSIDIANS = create("obsidians", Registries.ITEM);
        public static final TagKey<Item> COLORLESS_GLASS_BLOCKS = create("glass_blocks/colorless", Registries.ITEM);

        public static final TagKey<Item> SLIME_BALLS = create("slime_balls", Registries.ITEM);
        public static final TagKey<Item> SEEDS = create("seeds", Registries.ITEM);
        public static final TagKey<Item> LEATHERS = create("leathers", Registries.ITEM);
        public static final TagKey<Item> STRINGS = create("strings", Registries.ITEM);
        public static final TagKey<Item> MUSHROOMS = create("mushrooms", Registries.ITEM);
        public static final TagKey<Item> CROPS = create("crops", Registries.ITEM);

        public static final TagKey<Item> RAW_FISH_FOODS = create("foods/raw_fish", Registries.ITEM);
        public static final TagKey<Item> COOKED_FISH_FOODS = create("foods/cooked_fish", Registries.ITEM);

        public static final TagKey<Biome> IS_OCEAN = create("is_ocean", Registries.BIOME);
        public static final TagKey<Biome> IS_BEACH = create("is_beach", Registries.BIOME);
        public static final TagKey<Biome> IS_DESERT = create("is_desert", Registries.BIOME);
        public static final TagKey<Biome> IS_BADLANDS = create("is_badlands", Registries.BIOME);
        public static final TagKey<Biome> IS_ICY = create("is_icy", Registries.BIOME);
        public static final TagKey<Biome> IS_SNOWY = create("is_snowy", Registries.BIOME);
        public static final TagKey<Biome> IS_JUNGLE = create("is_jungle", Registries.BIOME);
        public static final TagKey<Biome> IS_SWAMP = create("is_swamp", Registries.BIOME);
        public static final TagKey<Biome> IS_MUSHROOM = create("is_mushroom", Registries.BIOME);

        public static <T> TagKey<T> create(String id, ResourceKey<Registry<T>> registryKey) {
            return TagKey.create(registryKey, Tide.resource("c", id));
        }
    }
}
