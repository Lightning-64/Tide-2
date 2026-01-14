package com.li64.tide.registries;

import com.li64.tide.Tide;
import com.li64.tide.compat.seasons.Season;
import com.li64.tide.data.TideTags;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.fishing.MinigameBehavior;
import com.li64.tide.data.fishing.conditions.types.*;
import com.li64.tide.data.fishing.mediums.FishingMedium;
import com.li64.tide.data.journal.FishRarity;
import com.li64.tide.data.journal.JournalGroup;
import com.li64.tide.registries.blocks.FishDisplayShape;
import com.li64.tide.registries.entities.fish.*;
import com.li64.tide.registries.entities.fish.lava.TideSchoolingLavaFish;
import com.li64.tide.registries.items.*;
import com.li64.tide.util.MoonPhases;
import com.li64.tide.util.TimeConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.material.Fluids;

//? if >=1.21 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
//?}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings("unused")
public class TideFish {
    public static final ArrayList<Item> ORDERED = new ArrayList<>();
    public static final List<ResourceKey<Item>> FISH_KEYS = new ArrayList<>();
    public static final Map<Item, List<ResourceKey<Item>>> COOKABLE_FISH_MAP = new HashMap<>();
    public static final Map<Item, Consumer<FishData.Builder>> DATA_BUILDERS = new HashMap<>();
    public static final Map<String, FishEntityData<? extends Mob>> ENTITY_DATA = new HashMap<>();
    public static final ArrayList<Item> SPAWNING_ITEMS = new ArrayList<>();

    // Freshwater fish

    public static final Item RAINBOW_TROUT = new Builder("rainbow_trout")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(50.0, 75.0, 110.0)
                    .strength(0.3f)
                    .speed(0.75f)
                    .selectionWeight(36)
                    .temperature(-0.5f, 0.65f)
                    .seasons(Season.SPRING, Season.SUMMER, Season.WINTER)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .displayData(display -> display.offsets(-0.05f, -0.08f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.6f, 0.4f).clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();
    
    public static final Item BROOK_TROUT = new Builder("brook_trout")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(30.0, 50.0, 80.0)
                    .strength(0.35f)
                    .speed(0.8f)
                    .selectionWeight(28)
                    .temperature(-0.1f, 0.7f)
                    .seasons(Season.SPRING, Season.SUMMER, Season.WINTER)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .displayData(display -> display.offsets(-0.05f, -0.08f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.6f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();
    
    public static final Item LARGEMOUTH_BASS = new Builder("largemouth_bass")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(35.0, 65.0, 100.0)
                    .strength(0.4f)
                    .speed(0.85f)
                    .selectionWeight(32)
                    .temperature(0.03f, 0.82f)
                    .seasons(Season.SPRING, Season.SUMMER, Season.FALL)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .displayData(display -> display.offsets(-0.05f, -0.1f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.65f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();
    
    public static final Item SMALLMOUTH_BASS = new Builder("smallmouth_bass")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(35.0, 52.0, 70.0)
                    .strength(0.4f)
                    .speed(0.9f)
                    .selectionWeight(24)
                    .temperature(-0.25f, 0.78f)
                    .seasons(Season.SPRING, Season.SUMMER, Season.FALL)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .displayData(display -> display.offsets(-0.05f, -0.1f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.65f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();
    
    public static final Item WHITE_CRAPPIE = new Builder("white_crappie")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(20.0, 30.0, 50.0)
                    .strength(0.35f)
                    .speed(0.85f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(25)
                    .temperature(0.05f, 0.4f)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .displayData(display -> display.offsets(-0.07f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.4f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 2.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();
    
    public static final Item BLACK_CRAPPIE = new Builder("black_crappie")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(25.0, 35.0, 50.0)
                    .strength(0.38f)
                    .speed(0.85f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(25)
                    .temperature(-0.12f, 0.4f)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .displayData(display -> display.offsets(-0.07f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.4f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 2.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();
    
    public static final Item YELLOW_PERCH = new Builder("yellow_perch")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(15.0, 30.0, 45.0)
                    .strength(0.35f)
                    .speed(0.95f)
                    .selectionWeight(36)
                    .temperature(0.28f, 0.8f)
                    .timeOfDay(
                            new TimeRange(TimeConstants.DAY, 15000),
                            new TimeRange(21000, TimeConstants.DAY_ALT)
                    )
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.4f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 2.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();
    
    public static final Item CARP = new Builder("carp")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 80.0, 120.0)
                    .strength(0.4f)
                    .speed(0.6f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(24)
                    .temperature(0.15f, 0.7f)
                    .seasons(Season.SPRING, Season.SUMMER, Season.FALL)
                    .timeOfDay(
                            new TimeRange(TimeConstants.DAY, TimeConstants.SUNSET),
                            new TimeRange(TimeConstants.MIDNIGHT, TimeConstants.DAY_ALT)
                    )
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.65f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item PIKE = new Builder("pike")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(60.0, 80.0, 140.0)
                    .strength(0.45f)
                    .speed(0.9f)
                    .selectionWeight(22)
                    .temperature(-0.3f, 0.5f)
                    .seasons(Season.SPRING, Season.FALL, Season.WINTER)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .displayData(display -> display.offsets(-0.05f, -0.13f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.75f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();
    
    public static final Item GUPPY = new Builder("guppy")
            .food(TideFoods.TINY_RAW_FISH)
            .cookedItem(TideItems.SMALL_COOKED_FISH)
            .fishData(builder -> builder
                    .size(4.0, 7.0, 9.0)
                    .strength(0.2f)
                    .speed(1.1f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(27)
                    .temperature(1.0f, 0.8f)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.1f, 0.06f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.3f, 0.15f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 1.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();

    public static final Item BLUEGILL = new Builder("bluegill")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(15.0, 30.0, 40.0)
                    .strength(0.35f)
                    .speed(0.7f)
                    .selectionWeight(14)
                    .temperature(0f, 1.6f)
                    .seasons(Season.SPRING, Season.SUMMER, Season.FALL)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .displayData(display -> display.offsets(-0.05f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.42f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item CATFISH = new Builder("catfish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 75.0, 155.0)
                    .strength(0.4f)
                    .speed(1.0f)
                    .selectionWeight(18)
                    .temperature(0.4f, 0.5f)
                    .seasons(Season.SPRING, Season.SUMMER, Season.FALL)
                    .timeOfDay(new TimeRange(TimeConstants.SUNSET, TimeConstants.DAY_ALT))
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.85f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();
    
    public static final Item WALLEYE = new Builder("walleye")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(35.0, 75.0, 105.0)
                    .strength(0.45f)
                    .speed(1.1f)
                    .selectionWeight(18)
                    .temperature(-1.0f, 1.3f)
                    .seasons(Season.SPRING, Season.FALL, Season.WINTER)
                    .timeOfDay(
                            new TimeRange(TimeConstants.DAY, 4000),
                            new TimeRange(9000, TimeConstants.DAY_ALT)
                    )
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.6f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();
    
    public static final Item ARAPAIMA = new Builder("arapaima")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(240.0, 340.0, 460.0)
                    .strength(0.6f)
                    .speed(1.7f)
                    .selectionWeight(9)
                    .foundIn(List.of(TideTags.Biomes.HAS_JUNGLE_FISH, TideTags.Biomes.HAS_SWAMP_FISH))
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.jungle")
                    .journalAltSprite(48)
                    .journalGroup(JournalGroup.FRESHWATER)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(-0.08f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(1.8f, 0.6f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 20.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.15f)
            ))
            .build();

    public static final Item MIRAGE_CATFISH = new Builder("mirage_catfish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 75.0, 155.0)
                    .strength(0.45f)
                    .speed(1.4f)
                    .selectionWeight(8)
                    .selectionQuality(0.5)
                    .timeOfDay(new TimeRange(TimeConstants.SUNSET, TimeConstants.DAY_ALT))
                    .foundIn(TideTags.Biomes.HAS_DESERT_FISH)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.desert")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.85f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();
    
    public static final Item SAND_TIGER_SHARK = new Builder("sand_tiger_shark")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(215.0, 275.0, 350.0)
                    .strength(0.7f)
                    .speed(1.65f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(7)
                    .temperature(0.35f, 0.2f)
                    .timeOfDay(
                            new TimeRange(TimeConstants.DAY, 3000),
                            new TimeRange(9000, TimeConstants.DAY_ALT)
                    )
                    .foundIn(TideTags.Biomes.HAS_DESERT_FISH)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.desert")
                    .journalAltSprite(32)
                    .journalGroup(JournalGroup.FRESHWATER)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(-0.05f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(1.8f, 0.6f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 25.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item SLIMY_SALMON = new Builder("slimy_salmon")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(55.0, 95.0, 145.0)
                    .strength(0.5f)
                    .speed(1.4f)
                    .selectionWeight(7)
                    .selectionQuality(0.5)
                    .foundIn(TideTags.Biomes.HAS_SWAMP_FISH)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.swamp")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(0f, -0.1f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.9f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item FROSTBITE_FLOUNDER = new Builder("frostbite_flounder")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 55.0, 75.0)
                    .strength(0.55f)
                    .speed(0.9f)
                    .selectionWeight(8)
                    .selectionQuality(0.5)
                    .foundIn(TideTags.Biomes.HAS_FROZEN_FISH)
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.frozen")
                    .journalRarity(FishRarity.RARE)
                    .journalGroup(JournalGroup.FRESHWATER)
                    .displayData(display -> display.offsets(0.05f, 0f, 0.02f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.65f, 0.2f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.8f)
            ))
            .build();
    
    public static final Item STURGEON = new Builder("sturgeon")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(140.0, 250.0, 365.0)
                    .strength(0.6f)
                    .speed(1.6f)
                    .selectionWeight(7)
                    .temperature(-0.65f, 0.45f)
                    .seasons(Season.FALL, Season.WINTER)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalAltSprite(32)
                    .journalGroup(JournalGroup.FRESHWATER)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(-0.05f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(1.6f, 0.5f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 18.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();
    
    public static final Item BLOSSOM_BASS = new Builder("blossom_bass")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(35.0, 65.0, 100.0)
                    .strength(0.5f)
                    .speed(1.3f)
                    .selectionWeight(10)
                    .selectionQuality(0.5)
                    .foundIn(TideTags.Biomes.HAS_CHERRY_GROVE_FISH)
                    .seasons(Season.SPRING, Season.SUMMER)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.cherry_grove")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.05f, -0.1f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.65f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();
    
    public static final Item SPORE_STALKER = new Builder("spore_stalker")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(35.0, 55.0, 90.0)
                    .strength(0.5f)
                    .speed(1.3f)
                    .selectionWeight(10)
                    .selectionQuality(0.5)
                    .foundIn(TideTags.Biomes.HAS_MUSHROOM_FISH)
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.mushroom_island")
                    .journalRarity(FishRarity.RARE)
                    .journalGroup(JournalGroup.SALTWATER)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.63f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item MOONEYE = new Builder("mooneye")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(23.0, 35.0, 50.0)
                    .strength(0.7f)
                    .speed(1.8f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(4)
                    .selectionQuality(0.5)
                    .temperature(-0.35f, 0.2f)
                    .timeOfDay(
                            new TimeRange(TimeConstants.DAY, 1000),
                            new TimeRange(TimeConstants.SUNSET, TimeConstants.DAY_ALT)
                    )
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.freshwater")
                    .journalGroup(JournalGroup.FRESHWATER)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display.offsets(-0.07f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.5f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 2.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();
    
    public static final Item BULL_SHARK = new Builder("bull_shark")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(210.0, 265.0, 335.0)
                    .strength(0.8f)
                    .speed(1.7f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(3)
                    .selectionQuality(0.5)
                    .temperature(0.35f, 0.2f)
                    .timeOfDay(
                            new TimeRange(TimeConstants.DAY, 2000),
                            new TimeRange(11000, TimeConstants.DAY_ALT)
                    )
                    .foundIn(BiomeTags.IS_RIVER)
                    .freshwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.river")
                    .journalAltSprite(48)
                    .journalGroup(JournalGroup.FRESHWATER)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(-0.05f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(1.8f, 0.6f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 25.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    // Saltwater fish
    
    public static final Item OCEAN_PERCH = new Builder("ocean_perch")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 50.0, 67.0)
                    .strength(0.4f)
                    .speed(1.1f)
                    .selectionWeight(50)
                    .temperature(-0.4f, 0.75f)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalGroup(JournalGroup.SALTWATER)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.6f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item RED_SNAPPER = new Builder("red_snapper")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(30.0, 60.0, 115.0)
                    .strength(0.4f)
                    .speed(1.0f)
                    .selectionWeight(50)
                    .temperature(-0.3f, 0.8f)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalGroup(JournalGroup.SALTWATER)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            ).entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.65f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item FLOUNDER = new Builder("flounder")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 55.0, 75.0)
                    .strength(0.45f)
                    .speed(0.8f)
                    .selectionWeight(35)
                    .temperature(-0.25f, 0.5f)
                    .seasons(Season.SUMMER, Season.FALL, Season.WINTER)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalGroup(JournalGroup.SALTWATER)
                    .displayData(display -> display.offsets(0.05f, 0f, 0.02f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.65f, 0.2f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.8f)
            ))
            .build();
    
    public static final Item ANCHOVY = new Builder("anchovy")
            .food(TideFoods.TINY_RAW_FISH)
            .cookedItem(TideItems.SMALL_COOKED_FISH)
            .fishData(builder -> builder
                    .size(6.0, 12.0, 20.0)
                    .strength(0.3f)
                    .speed(1.5f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(35)
                    .temperature(-0.15f, 0.5f)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalGroup(JournalGroup.SALTWATER)
                    .displayData(display -> display.offsets(-0.11f, 0.06f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.25f, 0.1f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 1.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.5f)
            ))
            .build();

    public static final Item TUNA = new Builder("tuna")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.GRILLED_TUNA)
            .fishData(builder -> builder
                    .size(90.0, 150.0, 300.0)
                    .strength(0.55f)
                    .speed(1.2f)
                    .selectionWeight(50)
                    .temperature(-0.1f, 0.7f)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalGroup(JournalGroup.SALTWATER)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_2x1)
                            .offsets(-0.05f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(1.1f, 0.65f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 10.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.3f)
            ))
            .build();

    public static final Item MACKEREL = new Builder("mackerel")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(30.0, 45.0, 65.0)
                    .strength(0.5f)
                    .speed(1.0f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(55)
                    .temperature(0.1f, 0.5f)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalGroup(JournalGroup.SALTWATER)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.4f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 2.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();

    public static final Item SNOOK = new Builder("snook")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(38.0, 68.0, 125.0)
                    .strength(0.45f)
                    .speed(1.1f)
                    .selectionWeight(42)
                    .temperature(0.12f, 0.45f)
                    .foundIn(TideTags.Biomes.HAS_COASTAL_FISH)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.coast")
                    .journalGroup(JournalGroup.SALTWATER)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.65f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item ANGELFISH = new Builder("angelfish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(10.0, 13.0, 20.0)
                    .strength(0.45f)
                    .speed(1.25f)
                    .selectionWeight(25)
                    .temperature(1.0f, 0.8f)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, -0.1f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.28f, 0.32f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 2.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();

    public static final Item MAHI_MAHI = new Builder("mahi_mahi")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(85.0, 130.0, 200.0)
                    .strength(0.7f)
                    .speed(1.4f)
                    .selectionWeight(9)
                    .temperature(0.4f, 0.4f)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalAltSprite(48)
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_2x1)
                            .offsets(-0.05f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(1.0f, 0.6f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 10.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.2f)
            ))
            .build();

    public static final Item SAILFISH = new Builder("sailfish")
            .item(properties -> new SwordfishItem(Tiers.IRON, 2, -3.0f, properties))
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(180.0, 270.0, 380.0)
                    .strength(0.8f)
                    .speed(1.4f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(8)
                    .temperature(0.7f, 0.5f)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalAltSprite(48)
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(-0.06f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(1.25f, 0.65f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 10.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.3f)
            ))
            .build();

    public static final Item SWORDFISH = new Builder("swordfish")
            .item(properties -> new SwordfishItem(Tiers.IRON, 3, -2.8f, properties))
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(180.0, 290.0, 455.0)
                    .strength(0.8f)
                    .speed(1.4f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(8)
                    .temperature(0.6f, 0.5f)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalAltSprite(48)
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(-0.06f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(1.3f, 0.6f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 12.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.32f)
            ))
            .build();
    
    public static final Item MANTA_RAY = new Builder("manta_ray")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(400.0, 650.0, 900.0)
                    .strength(0.76f)
                    .speed(1.3f)
                    .selectionWeight(8)
                    .temperature(0.43f, 0.4f)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalAltSprite(48)
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(0f, 0f, 0.02f)
                            .rotation(0f, 0f, 90f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(1.5f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 18.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item AQUATHORN = new Builder("aquathorn")
            .fishData(builder -> builder
                    .size(32.0, 45.0, 70.0)
                    .strength(0.82f)
                    .speed(1.5f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(20)
                    .condition(StructuresCondition.inStructures(List.of(
                            BuiltinStructures.OCEAN_MONUMENT,
                            ResourceKey.create(Registries.STRUCTURE, Tide.resource(
                                    "betteroceanmonuments", "ocean_monument"))
                    )))
                    .inStructure(BuiltinStructures.OCEAN_MONUMENT)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.ocean_monument")
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display.offsets(-0.07f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.45f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();

    public static final Item NEPTUNE_KOI = new Builder("neptune_koi")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 50.0, 80.0)
                    .strength(0.75f)
                    .speed(1.3f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(5.1)
                    .selectionQuality(0.5)
                    .condition(MoonPhaseCondition.anyOf(MoonPhases.NEW_MOON))
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display.offsets(-0.07f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.45f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.5f)
            ))
            .build();

    public static final Item PLUTO_SNAIL = new Builder("pluto_snail")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(10.0, 20.0, 35.0)
                    .strength(0.87f)
                    .speed(0.8f)
                    .selectionWeight(5)
                    .selectionQuality(0.5)
                    .condition(MoonPhaseCondition.anyOf(
                            MoonPhases.WANING_CRESCENT,
                            MoonPhases.WAXING_CRESCENT))
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display
                            .offsets(-0.02f, -0.2f, 0.02f)
                            .rotation(-90f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    Snail::new,
                    builder -> builder
                            .sized(0.45f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 2.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.4f)
            ))
            .build();

    public static final Item SUN_EMBLEM = new Builder("sun_emblem")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(50.0, 60.0, 85.0)
                    .strength(0.83f)
                    .speed(1.3f)
                    .selectionWeight(4.8)
                    .selectionQuality(0.5)
                    .timeOfDay(new TimeRange(TimeConstants.SUNRISE, TimeConstants.DAY_ALT),
                            new TimeRange(TimeConstants.DAY, TimeConstants.SUNSET))
                    .condition(MoonPhaseCondition.anyOf(
                            MoonPhases.FULL_MOON))
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display.offsets(-0.1f, -0.2f, -0.05f))
            )
            .entityData(FishEntityData.of(
                    Jellyfish::new,
                    builder -> builder
                            .sized(0.4f, 0.55f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.6f)
            ))
            .build();

    public static final Item SATURN_CUTTLEFISH = new Builder("saturn_cuttlefish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(90.0, 120.0, 180.0)
                    .strength(0.82f)
                    .speed(1.2f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(4.5)
                    .selectionQuality(0.5)
                    .condition(MoonPhaseCondition.anyOf(
                            MoonPhases.FIRST_QUARTER))
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalAltSprite(32)
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0.02f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(0.8f, 0.25f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 6.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.8f)
            ))
            .build();

    public static final Item MARSTILUS = new Builder("marstilus")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(100.0, 124.0, 185.0)
                    .strength(0.82f)
                    .speed(1.4f)
                    .selectionWeight(4.3)
                    .selectionQuality(0.5)
                    .condition(MoonPhaseCondition.anyOf(
                            MoonPhases.WANING_GIBBOUS,
                            MoonPhases.WAXING_GIBBOUS))
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalAltSprite(32)
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display.offsets(-0.05f, -0.1f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(0.8f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 8.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.8f)
            ))
            .build();

    public static final Item URANIAS_PISCES = new Builder("uranias_pisces")
            .food(TideFoods.BIG_RAW_FISH.withEffects(List.of(new TideFoods.FoodEffect(
                    new MobEffectInstance(MobEffects.LUCK, 12000), 1.0f))))
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(180.0, 220.0, 350.0)
                    .strength(0.8f)
                    .speed(1.7f)
                    .selectionWeight(4)
                    .selectionQuality(0.5)
                    .condition(MoonPhaseCondition.anyOf(
                            MoonPhases.THIRD_QUARTER))
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalAltSprite(32)
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_2x1)
                            .offsets(-0.06f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(1.1f, 0.6f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 12.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.25f)
            ))
            .build();
    
    public static final Item GREAT_WHITE_SHARK = new Builder("great_white_shark")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(370.0, 490.0, 610.0)
                    .strength(0.85f)
                    .speed(1.7f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(3)
                    .selectionQuality(0.5)
                    .temperature(-0.18f, 0.2f)
                    .timeOfDay(
                            new TimeRange(22000, TimeConstants.DAY_ALT),
                            new TimeRange(TimeConstants.DAY, 3000),
                            new TimeRange(11000, 17000)
                    )
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.saltwater")
                    .journalAltSprite(48)
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(-0.06f, -0.25f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(2.1f, 0.75f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 32.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.15f)
            ))
            .build();

    public static final Item SHOOTING_STARFISH = new Builder("shooting_starfish")
            .properties(p -> p.rarity(Rarity.RARE))
            .fishData(builder -> builder
                    .size(70.0, 85.0, 120.0)
                    .strength(0.88f)
                    .speed(2.2f)
                    .selectionWeight(1.5)
                    .selectionQuality(0.5)
                    .condition(MoonPhaseCondition.anyOf(MoonPhases.FULL_MOON))
                    .timeOfDay(new TimeRange(TimeConstants.NIGHT, TimeConstants.SUNRISE))
                    .foundIn(BiomeTags.IS_DEEP_OCEAN)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.deep_ocean")
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.LEGENDARY)
                    .displayData(display -> display
                            .offsets(0f, 0f, 0.02f)
                            .rotation(0f, 0f, 90f))
            )
            .entityData(FishEntityData.of(
                    ShootingStarfish::new,
                    builder -> builder
                            .sized(0.65f, 0.15f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 10.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.0f)
            ))
            .build();

    public static final Item COELACANTH = new Builder("coelacanth")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .properties(p -> p.rarity(Rarity.RARE))
            .fishData(builder -> builder
                    .size(140.0, 190.0, 270.0)
                    .strength(0.92f)
                    .speed(1.6f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(1)
                    .selectionQuality(0.5)
                    .temperature(0.3f, 0.2f)
                    .timeOfDay(new TimeRange(TimeConstants.NIGHT, TimeConstants.SUNRISE))
                    .weather(WeatherType.RAIN, WeatherType.STORM)
                    .foundIn(BiomeTags.IS_DEEP_OCEAN)
                    .saltwater()
                    .overworld()
                    .water()
                    .surface()
                    .journalLocation("journal.info.location.deep_ocean")
                    .journalAltSprite(48)
                    .journalGroup(JournalGroup.SALTWATER)
                    .journalRarity(FishRarity.LEGENDARY)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_2x1)
                            .offsets(-0.12f, -0.22f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingFish::new,
                    builder -> builder
                            .sized(1.1f, 0.7f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 16.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.3f)
            ))
            .build();

    // Underground fish

    public static final Item CAVE_EEL = new Builder("cave_eel")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(90.0, 115.0, 150.0)
                    .strength(0.65f)
                    .speed(1.0f)
                    .selectionWeight(50)
                    .elevationRange(-45, 40)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_2x1)
                            .offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.95f, 0.25f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item CAVE_CRAWLER = new Builder("cave_crawler")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(45.0, 60.0, 100.0)
                    .strength(0.75f)
                    .speed(0.8f)
                    .behavior(MinigameBehavior.LINEAR)
                    .selectionWeight(50)
                    .elevationRange(-30, 40)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .displayData(display -> display.offsets(0f, 0f, 0.02f))
            )
            .entityData(FishEntityData.of(
                    AmphibiousFish::new,
                    builder -> builder
                            .sized(0.65f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item DEEP_GROUPER = new Builder("deep_grouper")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(55.0, 75.0, 115.0)
                    .strength(0.5f)
                    .speed(1.2f)
                    .selectionWeight(50)
                    .below(30)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .displayData(display -> display.offsets(-0.05f, -0.1f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.6f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item SHADOW_SNAPPER = new Builder("shadow_snapper")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(35.0, 65.0, 120.0)
                    .strength(0.55f)
                    .speed(1.1f)
                    .selectionWeight(45)
                    .below(10)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .displayData(display -> display.offsets(-0.05f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.6f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();

    public static final Item GLOWFISH = new Builder("glowfish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(55.0, 75.0, 110.0)
                    .strength(0.5f)
                    .speed(1.1f)
                    .selectionWeight(30)
                    .elevationRange(-50, 40)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.6f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item ANGLERFISH = new Builder("anglerfish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(20.0, 35.0, 75.0)
                    .strength(0.75f)
                    .speed(0.8f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(30)
                    .elevationRange(-20, 40)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.1f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.5f, 0.25f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.8f)
            ))
            .build();

    public static final Item ABYSS_ANGLER = new Builder("abyss_angler")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(20.0, 35.0, 75.0)
                    .strength(0.78f)
                    .speed(0.8f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(30)
                    .below(-20)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.07f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.5f, 0.25f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.7f)
            ))
            .build();

    public static final Item IRON_TETRA = new Builder("iron_tetra")
            .food(TideFoods.TINY_RAW_FISH)
            .cookedItem(TideItems.SMALL_COOKED_FISH)
            .fishData(builder -> builder
                    .size(2.8, 4.5, 8.0)
                    .strength(0.65f)
                    .speed(1.0f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(20)
                    .below(50)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, 0.03f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.3f, 0.25f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 6.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.6f)
            ))
            .build();

    public static final Item LAPIS_LANTERNFISH = new Builder("lapis_lanternfish")
            .food(TideFoods.TINY_RAW_FISH)
            .cookedItem(TideItems.SMALL_COOKED_FISH)
            .fishData(builder -> builder
                    .size(5.0, 15.0, 40.0)
                    .strength(0.6f)
                    .speed(1.0f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(18)
                    .below(20)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, 0.02f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.3f, 0.15f)
                               .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 2.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.6f)
            ))
            .build();

    public static final Item CRYSTAL_SHRIMP = new Builder("crystal_shrimp")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(4.0, 8.0, 15.0)
                    .strength(0.62f)
                    .speed(1.08f)
                    .selectionWeight(17)
                    .below(35)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.4f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 2.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item DRIPSTONE_DARTER = new Builder("dripstone_darter")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(35.0, 55.0, 95.0)
                    .strength(0.7f)
                    .speed(1.3f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(18)
                    .foundIn(TideTags.Biomes.HAS_DRIPSTONE_FISH)
                    .below(40)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.dripstone_caves")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.07f, -0.02f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.5f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.5f)
            ))
            .build();

    public static final Item LUMINESCENT_JELLYFISH = new Builder("luminescent_jellyfish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 50.0, 80.0)
                    .strength(0.68f)
                    .speed(0.9f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(15)
                    .below(5)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.1f, -0.2f, -0.05f))
            )
            .entityData(FishEntityData.of(
                    Jellyfish::new,
                    builder -> builder
                            .sized(0.4f, 0.55f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.6f)
            ))
            .build();

    public static final Item CRYSTALLINE_CARP = new Builder("crystalline_carp")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 80.0, 120.0)
                    .strength(0.7f)
                    .speed(0.9f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(15)
                    .below(10)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.06f, -0.06f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.5f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 7.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.2f)
            ))
            .build();

    public static final Item GILDED_MINNOW = new Builder("gilded_minnow")
            .food(TideFoods.TINY_RAW_FISH)
            .cookedItem(TideItems.SMALL_COOKED_FISH)
            .fishData(builder -> builder
                    .size(8.0, 15.0, 28.0)
                    .strength(0.7f)
                    .speed(1.1f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(12)
                    .below(30)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.1f, 0.06f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.27f, 0.12f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.5f)
            ))
            .build();

    public static final Item BEDROCK_TETRA = new Builder("bedrock_tetra")
            .food(TideFoods.TINY_RAW_FISH)
            .cookedItem(TideItems.SMALL_COOKED_FISH)
            .fishData(builder -> builder
                    .size(2.8, 4.5, 8.0)
                    .strength(0.75f)
                    .speed(1.2f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(12)
                    .below(-20)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.05f, 0.03f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.3f, 0.25f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 10.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.6f)
            ))
            .build();

    public static final Item CHASM_EEL = new Builder("chasm_eel")
            .item(ChasmEelItem::new)
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(120, 180, 300)
                    .strength(0.85f)
                    .speed(1.5f)
                    .behavior(MinigameBehavior.LINEAR)
                    .selectionWeight(6)
                    .below(-20)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalAltSprite(32)
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_2x1)
                            .offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.9f, 0.25f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 8.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item ECHO_SNAPPER = new Builder("echo_snapper")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(65.0, 85.0, 140.0)
                    .strength(0.8f)
                    .speed(0.8f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(16)
                    .either(
                        BiomeWhitelistCondition.fromTag(TideTags.Biomes.HAS_DEEP_DARK_FISH),
                        StructuresCondition.only(BuiltinStructures.ANCIENT_CITY)
                    )
                    .below(30)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.deep_dark")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display.offsets(0.05f, 0f, 0.02f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.65f, 0.125f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.75f)
            ))
            .build();

    //? if >=1.21 {
    public static final Item WINDBASS = new Builder("windbass")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(32.0, 60.0, 95.0)
                    .strength(0.72f)
                    .speed(1.5f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(20)
                    .inStructure(BuiltinStructures.TRIAL_CHAMBERS)
                    .below(30)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.trial_chambers")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display.offsets(-0.05f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    Windbass::new,
                    builder -> builder
                            .sized(0.5f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 2.0f)
            ))
            .build();
    //?}
    
    public static final Item DEVILS_HOLE_PUPFISH = new Builder("devils_hole_pupfish")
            .food(TideFoods.TINY_RAW_FISH)
            .cookedItem(TideItems.SMALL_COOKED_FISH)
            .properties(p -> p.rarity(Rarity.RARE))
            .fishData(builder -> builder
                    .size(1.4, 2.6, 4.3)
                    .strength(0.8f)
                    .speed(2.1f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(1.5)
                    .selectionQuality(0.5)
                    .elevationRange(20, 30)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.LEGENDARY)
                    .displayData(display -> display.offsets(-0.12f, 0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.25f, 0.12f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 1.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.6f)
            ))
            .build();

    public static final Item MIDAS_FISH = new Builder("midas_fish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .properties(p -> p.rarity(Rarity.RARE))
            .fishData(builder -> builder
                    .size(50.0, 80.0, 130.0)
                    .strength(0.9f)
                    .speed(1.8f)
                    .selectionWeight(7)
                    .condition(LuckCondition.minimum(7))
                    .below(41)
                    .overworld()
                    .water()
                    .journalLocation("journal.info.location.underground")
                    .journalGroup(JournalGroup.UNDERGROUND)
                    .journalRarity(FishRarity.LEGENDARY)
                    .displayData(display -> display.offsets(-0.05f, -0.07f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideFishEntity::new,
                    builder -> builder
                            .sized(0.7f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 20.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    // Void fish

    public static final Item INCANDESCENT_LARVA = new Builder("incandescent_larva")
            .food(TideFoods.TINY_RAW_FISH)
            .cookedItem(TideItems.SMALL_COOKED_FISH)
            .fishData(builder -> builder
                    .size(2.0, 4.0, 10.0)
                    .strength(0.65f)
                    .speed(1.55f)
                    .selectionWeight(45)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .displayData(display -> display.offsets(0f, 0f, 0.02f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.35f, 0.12f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 1.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item BEDROCK_BUG = new Builder("bedrock_bug")
            .food(TideFoods.TINY_RAW_FISH)
            .cookedItem(TideItems.SMALL_COOKED_FISH)
            .fishData(builder -> builder
                    .size(3.0, 4.5, 10.0)
                    .strength(0.75f)
                    .speed(1.4f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(44)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .displayData(display -> display.offsets(0f, 0f, 0.02f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.35f, 0.13f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();

    public static final Item SLEEPY_CARP = new Builder("sleepy_carp")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 80.0, 120.0)
                    .strength(0.78f)
                    .speed(0.65f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(40)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.COMMON)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.65f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 6.0)
                            .add(Attributes.MOVEMENT_SPEED, 0.6f)
            ))
            .build();

    public static final Item BLUE_NEONFISH = new Builder("blue_neonfish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(20.0, 35.0, 75.0)
                    .strength(0.75f)
                    .speed(1.5f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(35)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.1f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.5f, 0.25f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item JUDGMENT_FISH = new Builder("judgment_fish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 80.0, 140.0)
                    .strength(0.73f)
                    .speed(1.6f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(33)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.4f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.25f)
            ))
            .build();

    public static final Item DEEP_BLUE = new Builder("deep_blue")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(38.0, 65.0, 100.0)
                    .strength(0.76f)
                    .speed(1.55f)
                    .selectionWeight(32)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, -0.03f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.6f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 8.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();

    public static final Item NEPHROSILU = new Builder("nephrosilu")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(55.0, 90.0, 160.0)
                    .strength(0.8f)
                    .speed(1.65f)
                    .selectionWeight(20)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(0f, 0f, 0.08f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.5f,0.2f)
                               .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 7.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.15f)
            ))
            .build();

    public static final Item VENGEANCE = new Builder("vengeance")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(50.0, 80.0, 130.0)
                    .strength(0.8f)
                    .speed(1.68f)
                    .selectionWeight(18)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.05f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.6f, 0.45f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 8.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();

    public static final Item PENTAPUS = new Builder("pentapus")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(30.0, 90.0, 200.0)
                    .strength(0.8f)
                    .speed(1.65f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(16)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.1f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    Pentapus::new,
                    builder -> builder
                            .sized(0.45f, 0.5f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item DARKNESS_EATER = new Builder("darkness_eater")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(170.0, 230.0, 400.0)
                    .strength(0.86f)
                    .speed(1.7f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(5)
                    .selectionQuality(0.5)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalAltSprite(32)
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(-0.05f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingVoidFish::new,
                    builder -> builder
                            .sized(1.7f, 0.65f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 25.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item SHADOW_SHARK = new Builder("shadow_shark")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(250.0, 305.0, 375.0)
                    .strength(0.86f)
                    .speed(1.75f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(4)
                    .selectionQuality(0.5)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalAltSprite(48)
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(-0.05f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingVoidFish::new,
                    builder -> builder
                            .sized(1.8f, 0.6f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 28.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();

    public static final Item ALPHA_FISH = new Builder("alpha_fish")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .properties(p -> p.rarity(Rarity.RARE))
            .fishData(builder -> builder
                    .size(40.0, 60.0, 100.0)
                    .strength(0.9f)
                    .speed(2.1f)
                    .selectionWeight(2)
                    .selectionQuality(0.5)
                    .overworld()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.LEGENDARY)
                    .displayData(display -> display.offsets(0.03f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.5f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.25f)
            ))
            .build();

    // Lava fish

    public static final Item MAGMA_MACKEREL = new Builder("magma_mackerel")
            .food(TideFoods.FIERY_FISH)
            .fireproof()
            .fishData(builder -> builder
                    .size(30.0, 45.0, 65.0)
                    .strength(0.65f)
                    .speed(1.0f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(50)
                    .medium(FishingMedium.LAVA)
                    .journalLocation("journal.info.location.lava")
                    .journalGroup(JournalGroup.LAVA)
                    .journalRarity(FishRarity.COMMON)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideSchoolingLavaFish::new,
                    builder -> builder
                            .sized(0.4f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f),
                    (type, properties) -> new MobBucketItem(
                            type, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties)
            ))
            .build();

    public static final Item EMBER_KOI = new Builder("ember_koi")
            .food(TideFoods.FIERY_FISH)
            .fireproof()
            .fishData(builder -> builder
                    .size(40.0, 50.0, 80.0)
                    .strength(0.6f)
                    .speed(0.75f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(45)
                    .medium(FishingMedium.LAVA)
                    .journalLocation("journal.info.location.lava")
                    .journalGroup(JournalGroup.LAVA)
                    .journalRarity(FishRarity.COMMON)
                    .displayData(display -> display.offsets(-0.07f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideSchoolingLavaFish::new,
                    builder -> builder
                            .sized(0.4f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f),
                    (type, properties) -> new MobBucketItem(
                            type, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties)
            ))
            .build();

    public static final Item ASH_PERCH = new Builder("ash_perch")
            .food(TideFoods.FIERY_FISH)
            .fireproof()
            .fishData(builder -> builder
                    .size(15.0, 30.0, 45.0)
                    .strength(0.55f)
                    .speed(1.2f)
                    .selectionWeight(42)
                    .medium(FishingMedium.LAVA)
                    .journalLocation("journal.info.location.lava")
                    .journalGroup(JournalGroup.LAVA)
                    .journalRarity(FishRarity.COMMON)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideSchoolingLavaFish::new,
                    builder -> builder
                            .sized(0.4f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f),
                    (type, properties) -> new MobBucketItem(
                            type, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties)
            ))
            .build();

    public static final Item OBSIDIAN_PIKE = new Builder("obsidian_pike")
            .food(TideFoods.FIERY_FISH)
            .fireproof()
            .fishData(builder -> builder
                    .size(60.0, 80.0, 140.0)
                    .strength(0.65f)
                    .speed(1.25f)
                    .selectionWeight(30)
                    .medium(FishingMedium.LAVA)
                    .journalLocation("journal.info.location.lava")
                    .journalGroup(JournalGroup.LAVA)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, -0.13f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideSchoolingLavaFish::new,
                    builder -> builder
                            .sized(0.75f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 14.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f),
                    (type, properties) -> new MobBucketItem(
                            type, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties)
            ))
            .build();

    public static final Item VOLCANO_TUNA = new Builder("volcano_tuna")
            .food(TideFoods.FIERY_FISH)
            .fireproof()
            .fishData(builder -> builder
                    .size(50.0, 75.0, 110.0)
                    .strength(0.7f)
                    .speed(1.3f)
                    .selectionWeight(28)
                    .medium(FishingMedium.LAVA)
                    .journalLocation("journal.info.location.lava")
                    .journalGroup(JournalGroup.LAVA)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideSchoolingLavaFish::new,
                    builder -> builder
                            .sized(0.6f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f),
                    (type, properties) -> new MobBucketItem(
                            type, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties)
            ))
            .build();
    
    public static final Item INFERNO_GUPPY = new Builder("inferno_guppy")
            .food(TideFoods.TINY_FIERY_FISH)
            .fireproof()
            .fishData(builder -> builder
                    .size(4.0, 7.0, 9.0)
                    .strength(0.4f)
                    .speed(1.25f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(16)
                    .medium(FishingMedium.LAVA)
                    .journalLocation("journal.info.location.lava")
                    .journalGroup(JournalGroup.LAVA)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.1f, 0.06f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideSchoolingLavaFish::new,
                    builder -> builder
                            .sized(0.3f, 0.15f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 1.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f),
                    (type, properties) -> new MobBucketItem(
                            type, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties)
            ))
            .build();

    public static final Item WARPED_GUPPY = new Builder("warped_guppy")
            .food(TideFoods.TINY_FIERY_FISH)
            .fireproof()
            .fishData(builder -> builder
                    .size(4.0, 7.0, 9.0)
                    .strength(0.5f)
                    .speed(1.15f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(24)
                    .nether()
                    .medium(FishingMedium.LAVA)
                    .journalLocation("journal.info.location.lava")
                    .journalGroup(JournalGroup.LAVA)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.1f, 0.06f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideSchoolingLavaFish::new,
                    builder -> builder
                            .sized(0.3f, 0.15f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 1.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f),
                    (type, properties) -> new MobBucketItem(
                            type, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties)
            ))
            .build();

    public static final Item CRIMSON_FANGJAW = new Builder("crimson_fangjaw")
            .food(TideFoods.FIERY_FISH)
            .fireproof()
            .fishData(builder -> builder
                    .size(35.0, 65.0, 100.0)
                    .strength(0.68f)
                    .speed(0.9f)
                    .selectionWeight(24)
                    .nether()
                    .medium(FishingMedium.LAVA)
                    .journalLocation("journal.info.location.lava")
                    .journalGroup(JournalGroup.LAVA)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.05f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideSchoolingLavaFish::new,
                    builder -> builder
                            .sized(0.6f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f),
                    (type, properties) -> new MobBucketItem(
                            type, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties)
            ))
            .build();

    public static final Item SOULSCALE = new Builder("soulscale")
            .food(TideFoods.FIERY_FISH)
            .fireproof()
            .fishData(builder -> builder
                    .size(35.0, 60.0, 95.0)
                    .strength(0.6f)
                    .speed(1.15f)
                    .selectionWeight(22)
                    .nether()
                    .medium(FishingMedium.LAVA)
                    .journalLocation("journal.info.location.lava")
                    .journalGroup(JournalGroup.LAVA)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.05f, -0.1f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideSchoolingLavaFish::new,
                    builder -> builder
                            .sized(0.65f, 0.4f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f),
                    (type, properties) -> new MobBucketItem(
                            type, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties)
            ))
            .build();

    public static final Item WITHERFIN = new Builder("witherfin")
            .food(TideFoods.RAW_FISH.withEffects(List.of(new TideFoods.FoodEffect(
                    new MobEffectInstance(MobEffects.WITHER, 140, 3), 1.0f))))
            .fireproof()
            .fishData(builder -> builder
                    .size(25.0, 40.0, 90.0)
                    .strength(0.7f)
                    .speed(1.15f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(17)
                    .nether()
                    .medium(FishingMedium.LAVA)
                    .journalLocation("journal.info.location.lava")
                    .journalGroup(JournalGroup.LAVA)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(0f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    Witherfin::new,
                    builder -> builder
                            .sized(0.5f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 6.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f),
                    (type, properties) -> new MobBucketItem(
                            type, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties)
            ))
            .build();

    public static final Item BLAZING_SWORDFISH = new Builder("blazing_swordfish")
            .item(properties -> new BlazingSwordfishItem(Tiers.IRON, 4, -2.8f, properties))
            .food(TideFoods.BIG_FIERY_FISH)
            .fireproof()
            .fishData(builder -> builder
                    .size(180.0, 290.0, 455.0)
                    .strength(0.85f)
                    .speed(1.48f)
                    .selectionWeight(5)
                    .selectionQuality(0.5)
                    .nether()
                    .medium(FishingMedium.LAVA)
                    .journalLocation("journal.info.location.lava")
                    .journalAltSprite(48)
                    .journalGroup(JournalGroup.LAVA)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(-0.06f, -0.15f, 0f))
            )
            .entityData(FishEntityData.of(
                    BlazingSwordfish::new,
                    builder -> builder
                            .sized(1.3f, 0.6f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 15.0f)
                            .add(Attributes.MOVEMENT_SPEED, 1.3f),
                    (type, properties) -> new MobBucketItem(
                            type, Fluids.LAVA, SoundEvents.BUCKET_EMPTY_LAVA, properties)
            ))
            .build();

    // End fish

    public static final Item PALE_CLUBFISH = new Builder("pale_clubfish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(40.0, 65.0, 135.0)
                    .strength(0.6f)
                    .speed(1.1f)
                    .selectionWeight(55)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.COMMON)
                    .displayData(display -> display.offsets(-0.05f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.55f, 0.35f)
                               .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 4.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item AMBER_ROCKFISH = new Builder("amber_rockfish")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(35.0, 60.0, 95.0)
                    .strength(0.6f)
                    .speed(1.2f)
                    .selectionWeight(55)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.COMMON)
                    .displayData(display -> display.offsets(-0.05f, -0.08f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.45f, 0.45f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item ENDERFIN = new Builder("enderfin")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(35.0, 55.0, 90.0)
                    .strength(0.6f)
                    .speed(1.25f)
                    .behavior(MinigameBehavior.LINEAR_WRAP)
                    .selectionWeight(50)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.COMMON)
                    .displayData(display -> display.offsets(-0.07f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.4f, 0.38f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();

    public static final Item CHORUS_COD = new Builder("chorus_cod")
            .item(ChorusFruitItem::new)
            .food(TideFoods.RAW_FISH.withAlwaysEdible())
            .fishData(builder -> builder
                    .size(55.0, 75.0, 110.0)
                    .strength(0.72f)
                    .speed(1.15f)
                    .behavior(MinigameBehavior.LINEAR_WRAP)
                    .selectionWeight(38)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, -0.05f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.6f, 0.38f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.1f)
            ))
            .build();

    public static final Item ENDER_GLIDER = new Builder("ender_glider")
            .food(TideFoods.RAW_FISH.withEffects(List.of(new TideFoods.FoodEffect(
                    new MobEffectInstance(MobEffects.SLOW_FALLING, 4000), 1.0f))))
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(48.0, 70.0, 110.0)
                    .strength(0.55f)
                    .speed(1.25f)
                    .behavior(MinigameBehavior.PLATEAU)
                    .selectionWeight(35)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(0f, 0f, -0.05f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.6f, 0.25f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.4f)
            ))
            .build();

    public static final Item ENDERGAZER = new Builder("endergazer")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(25.0, 35.0, 55.0)
                    .strength(0.68f)
                    .speed(1.1f)
                    .selectionWeight(35)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.08f, -0.03f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.4f, 0.25f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.2f)
            ))
            .build();

    public static final Item VIOLET_CARP = new Builder("violet_carp")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(60.0, 85.0, 125.0)
                    .strength(0.7f)
                    .speed(1.15f)
                    .selectionWeight(32)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.UNCOMMON)
                    .displayData(display -> display.offsets(-0.05f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.65f, 0.35f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 5.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item RED_40 = new Builder("red_40")
            .food(TideFoods.TINY_RAW_FISH)
            .cookedItem(TideItems.SMALL_COOKED_FISH)
            .fishData(builder -> builder
                    .size(12.0, 25.0, 45.0)
                    .strength(0.75f)
                    .speed(1.25f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(20)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.1f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.26f, 0.22f)
                               .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 2.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.25f)
            ))
            .build();

    public static final Item DUTCHMAN_SOCK = new Builder("dutchman_sock")
            .food(TideFoods.TINY_RAW_FISH)
            .cookedItem(TideItems.SMALL_COOKED_FISH)
            .fishData(builder -> builder
                    .size(4.0, 7.0, 15.0)
                    .strength(0.65f)
                    .speed(1.4f)
                    .behavior(MinigameBehavior.JITTER)
                    .selectionWeight(18)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(-0.08f, 0.02f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.3f, 0.125f)
                               .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 1.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.3f)
            ))
            .build();

    public static final Item ELYTROUT = new Builder("elytrout")
            .food(TideFoods.RAW_FISH)
            .cookedItem(TideItems.COOKED_FISH)
            .fishData(builder -> builder
                    .size(35.0, 60.0, 100.0)
                    .strength(0.65f)
                    .speed(1.45f)
                    .selectionWeight(14)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.RARE)
                    .displayData(display -> display.offsets(0f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.5f, 0.125f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 3.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.15f)
            ))
            .build();

    public static final Item MANTYVERN = new Builder("mantyvern")
            .food(TideFoods.BIG_RAW_FISH.withEffects(List.of(new TideFoods.FoodEffect(
                    new MobEffectInstance(MobEffects.SLOW_FALLING, 6000), 1.0f))))
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(280.0, 360.0, 500.0)
                    .strength(0.78f)
                    .speed(1.55f)
                    .selectionWeight(6)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalAltSprite(32)
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(0.1f, 0f, -0.05f)
                            .rotation(0f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingVoidFish::new,
                    builder -> builder
                            .sized(1.8f, 0.5f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 20.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.4f)
            ))
            .build();

    public static final Item SNATCHER_SQUID = new Builder("snatcher_squid")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .fishData(builder -> builder
                    .size(110.0, 170.0, 260.0)
                    .strength(0.8f)
                    .speed(1.5f)
                    .behavior(MinigameBehavior.DARTS)
                    .selectionWeight(6)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalAltSprite(32)
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.VERY_RARE)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_2x1)
                            .offsets(0.1f, 0f, -0.05f)
                            .rotation(0f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingVoidFish::new,
                    builder -> builder
                            .sized(1.0f, 0.2f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 16.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.0f)
            ))
            .build();

    public static final Item VOIDSEEKER = new Builder("voidseeker")
            .item(VoidseekerItem::new)
            .food(new TideFoods.FoodDefinition(6, 0.7f, false, true, List.of()))
            .properties(p -> p.rarity(Rarity.RARE))
            .fishData(builder -> builder
                    .size(40.0, 65.0, 120.0)
                    .strength(0.9f)
                    .speed(1.85f)
                    .behavior(MinigameBehavior.LINEAR_WRAP)
                    .selectionWeight(3)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.LEGENDARY)
                    .displayData(display -> display.offsets(-0.13f, 0f, 0f))
            )
            .entityData(FishEntityData.of(
                    TideVoidFish::new,
                    builder -> builder
                            .sized(0.7f, 0.3f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 8.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.2f)
            ))
            .build();

    public static final Item DRAGON_FISH = new Builder("dragon_fish")
            .food(TideFoods.BIG_RAW_FISH)
            .cookedItem(TideItems.LARGE_COOKED_FISH)
            .properties(p -> p.rarity(Rarity.RARE))
            .fishData(builder -> builder
                    .size(550.0, 750.0, 1250.0)
                    .strength(0.92f)
                    .speed(1.9f)
                    .selectionWeight(1)
                    .selectionQuality(0.5)
                    .end()
                    .medium(FishingMedium.VOID)
                    .journalLocation("journal.info.location.void")
                    .journalAltSprite(48)
                    .journalGroup(JournalGroup.VOID)
                    .journalRarity(FishRarity.LEGENDARY)
                    .displayData(display -> display
                            .shape(FishDisplayShape.SHAPE_3x1)
                            .offsets(0f, -0.35f, 0f))
            )
            .entityData(FishEntityData.of(
                    SmoothSwimmingVoidFish::new,
                    builder -> builder
                            .sized(4f, 1f)
                            .clientTrackingRange(4),
                    Mob.createMobAttributes()
                            .add(Attributes.MAX_HEALTH, 60.0)
                            .add(Attributes.MOVEMENT_SPEED, 1.25f)
            ))
            .build();

    public static void init() {}

    public static ArrayList<Item> getOrderedFishItems() {
        return ORDERED;
    }

    private static class Builder {
        private final String id;
        private final Item.Properties properties = new Item.Properties();
        private Function<Item.Properties, Item> factory = Item::new;
        private Consumer<FishData.Builder> data;
        private Item cookedItem;
        private FishEntityData<?> entityData;

        public Builder(String id) {
            this.id = id;
        }

        public Builder item(Function<Item.Properties, Item> factory) {
            this.factory = factory;
            return this;
        }

        public Builder properties(Consumer<Item.Properties> properties) {
            properties.accept(this.properties);
            return this;
        }

        public Builder fishData(Consumer<FishData.Builder> data) {
            this.data = data;
            return this;
        }

        public Builder food(TideFoods.FoodDefinition def) {
            TideFoods.apply(properties, def);
            return this;
        }

        public Builder fireproof() {
            this.properties.fireResistant();
            return this;
        }

        public Builder cookedItem(Item cookedItem) {
            this.cookedItem = cookedItem;
            return this;
        }

        public <T extends Mob> Builder entityData(FishEntityData<T> entityData) {
            this.entityData = entityData;
            return this;
        }

        public Item build() {
            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Tide.resource(id));
            Item item = factory.apply(properties);

            // Saving all of this datagen utility data may take up large portions
            // of memory, so only add these when running in a dev environment
            if (Tide.PLATFORM.isDevEnvironment()) {
                FISH_KEYS.add(key);
                if (cookedItem != null) {
                    if (COOKABLE_FISH_MAP.containsKey(cookedItem))
                        COOKABLE_FISH_MAP.get(cookedItem).add(key);
                    else {
                        ArrayList<ResourceKey<Item>> cookables = new ArrayList<>();
                        cookables.add(key);
                        COOKABLE_FISH_MAP.put(cookedItem, cookables);
                    }
                }
                if (data != null) DATA_BUILDERS.put(item, data);
            }

            if (entityData != null) {
                String path = key.location().getPath();
                ENTITY_DATA.put(path, entityData);
                createSpawningItems(path, entityData);
            }

            ORDERED.add(item);
            return TideItems.register(key, item);
        }

        @SuppressWarnings("unchecked")
        private <T extends Mob> void createSpawningItems(String key, FishEntityData<T> entityData) {
            EntityType<T> type = (EntityType<T>) TideEntityTypes.ENTITY_TYPES
                    .computeIfAbsent(key, k -> entityData.createEntityBuilder().build(k));

            Item egg = TideItems.register(key + "_spawn_egg", properties ->
                    new SpawnEggItem(type, 0xffffff, 0x000000, properties));
            Item bucket = TideItems.register(key + "_bucket", properties -> entityData.bucketBuilder().apply(type, properties),
                    new Item.Properties().stacksTo(1)/*? if >=1.21 {*/.component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)/*?}*/);

            if (Tide.PLATFORM.isDevEnvironment()) {
                SPAWNING_ITEMS.add(egg);
                SPAWNING_ITEMS.add(bucket);
            }
        }
    }

    public record FishEntityData<T extends Mob>(EntityType.EntityFactory<T> factory,
                                                Consumer<EntityType.Builder<T>> entityBuilderConsumer,
                                                AttributeSupplier.Builder attributesBuilder,
                                                BiFunction<EntityType<T>, Item.Properties, Item> bucketBuilder) {

        public static <T extends Mob> FishEntityData<T> of(EntityType.EntityFactory<T> factory,
                                                           Consumer<EntityType.Builder<T>> entityBuilderConsumer,
                                                           AttributeSupplier.Builder attributesBuilder) {
            return FishEntityData.of(factory, entityBuilderConsumer, attributesBuilder, (type, properties) ->
                    new MobBucketItem(type, Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, properties));
        }

        public static <T extends Mob> FishEntityData<T> of(EntityType.EntityFactory<T> factory,
                                                           Consumer<EntityType.Builder<T>> entityBuilderConsumer,
                                                           AttributeSupplier.Builder attributesBuilder,
                                                           BiFunction<EntityType<T>, Item.Properties, Item> bucketBuilder) {
            return new FishEntityData<>(factory, entityBuilderConsumer, attributesBuilder, bucketBuilder);
        }

        public EntityType.Builder<T> createEntityBuilder() {
            EntityType.Builder<T> builder = EntityType.Builder.of(factory, MobCategory.WATER_AMBIENT);
            entityBuilderConsumer.accept(builder);
            return builder;
        }
    }
}
