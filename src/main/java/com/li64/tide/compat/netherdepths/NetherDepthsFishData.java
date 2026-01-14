package com.li64.tide.compat.netherdepths;

import com.li64.tide.Tide;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.fishing.MinigameBehavior;
import com.li64.tide.data.fishing.mediums.FishingMedium;
import com.li64.tide.data.journal.FishRarity;
import com.li64.tide.data.journal.JournalGroup;
import com.li64.tide.datagen.fabric.providers.SimpleDataOutput;
import com.li64.tide.registries.blocks.FishDisplayShape;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class NetherDepthsFishData {
    public static final String MOD_ID = "netherdepthsupgrade";

    private static final TagKey<Biome> IS_LAVA_PUFFERFISH_BIOME = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "is_lava_pufferfish_biome"));
    private static final TagKey<Biome> IS_WITHER_BONEFISH_BIOME = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "is_wither_bonefish_biome"));
    private static final TagKey<Biome> IS_GLOWDINE_BIOME = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "is_glowdine_biome"));
    private static final TagKey<Biome> IS_MAGMACUBEFISH_BIOME = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "is_magmacubefish_biome"));
    private static final TagKey<Biome> IS_SOULSUCKER_BIOME = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "is_soulsucker_biome"));
    private static final TagKey<Biome> IS_OBSIDIANFISH_BIOME = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "is_obsidianfish_biome"));
    private static final TagKey<Biome> IS_FORTRESS_GROUPER_BIOME = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "is_fortress_grouper_biome"));
    private static final TagKey<Biome> IS_EYEBALL_FISH_BIOME = TagKey.create(Registries.BIOME, Tide.resource(MOD_ID, "is_eyeball_fish_biome"));

    public static void generate(SimpleDataOutput<FishData> output) {
        FishData.builder().fish(MOD_ID, "searing_cod")
                .size(65, 115, 200)
                .strength(0.7f).speed(1.0f)
                .selectionWeight(50)
                .nether().medium(FishingMedium.LAVA)
                .journalLocation("journal.info.location.lava")
                .journalGroup(JournalGroup.LAVA)
                .journalRarity(FishRarity.COMMON)
                .displayData(display -> display
                        .offsets(0.1f, 0f, 0.05f)
                        .rotation(0f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "bonefish")
                .size(80, 125, 210)
                .strength(0.7f).speed(0.85f)
                .behavior(MinigameBehavior.PLATEAU)
                .selectionWeight(45)
                .nether().medium(FishingMedium.LAVA)
                .journalLocation("journal.info.location.lava")
                .journalGroup(JournalGroup.LAVA)
                .journalRarity(FishRarity.COMMON)
                .displayData(display -> display
                        .offsets(0.1f, 0f, 0.03f)
                        .rotation(45f, 5.0f, -5.0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "lava_pufferfish")
                .size(12.0, 22.0, 75.0)
                .strength(0.6f).speed(1.25f)
                .behavior(MinigameBehavior.JITTER)
                .selectionWeight(45)
                .nether().medium(FishingMedium.LAVA)
                .foundIn(IS_LAVA_PUFFERFISH_BIOME)
                .journalLocation("journal.info.location.lava")
                .journalGroup(JournalGroup.LAVA)
                .displayData(display -> display
                        .offsets(0.0f, 0.0f, -0.05f)
                        .rotation(0f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "obsidianfish")
                .size(60, 90, 150)
                .strength(0.65f).speed(1.25f)
                .selectionWeight(30)
                .nether().medium(FishingMedium.LAVA)
                .foundIn(IS_OBSIDIANFISH_BIOME)
                .journalLocation("journal.info.location.lava")
                .journalGroup(JournalGroup.LAVA)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .shape(FishDisplayShape.SHAPE_2x1)
                        .offsets(0.15f, -0.15f, 0.05f)
                        .rotation(0f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "magmacubefish")
                .size(80, 120, 160)
                .strength(0.65f).speed(1.2f)
                .behavior(MinigameBehavior.DARTS)
                .selectionWeight(30)
                .nether().medium(FishingMedium.LAVA)
                .foundIn(IS_MAGMACUBEFISH_BIOME)
                .journalLocation("journal.info.location.lava")
                .journalGroup(JournalGroup.LAVA)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0f, 0f, 0.02f)
                        .rotation(85.0f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "glowdine")
                .size(75, 100, 160)
                .strength(0.65f).speed(1.25f)
                .selectionWeight(30)
                .nether().medium(FishingMedium.LAVA)
                .foundIn(IS_GLOWDINE_BIOME)
                .journalLocation("journal.info.location.lava")
                .journalGroup(JournalGroup.LAVA)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(-0.05f, -0.1f, 0.05f)
                        .rotation(0f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "soulsucker")
                .size(110, 170, 260)
                .strength(0.6f).speed(1.15f)
                .selectionWeight(22)
                .nether().medium(FishingMedium.LAVA)
                .foundIn(IS_SOULSUCKER_BIOME)
                .journalLocation("journal.info.location.lava")
                .journalGroup(JournalGroup.LAVA)
                .journalRarity(FishRarity.RARE)
                .displayData(display -> display
                        .shape(FishDisplayShape.SHAPE_3x1)
                        .offsets(0.75f, 0.1f, 0.05f)
                        .rotation(90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "wither_bonefish")
                .size(80, 125, 210)
                .strength(0.7f).speed(1.15f)
                .behavior(MinigameBehavior.PLATEAU)
                .selectionWeight(17)
                .nether().medium(FishingMedium.LAVA)
                .foundIn(IS_WITHER_BONEFISH_BIOME)
                .journalLocation("journal.info.location.lava")
                .journalGroup(JournalGroup.LAVA)
                .journalRarity(FishRarity.RARE)
                .displayData(display -> display
                        .shape(FishDisplayShape.SHAPE_2x1)
                        .offsets(0.08f, -0.1f, 0.275f)
                        .rotation(-45.0f, -5.0f, -5.0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "blazefish")
                .size(80, 125, 210)
                .strength(0.75f).speed(1.25f)
                .behavior(MinigameBehavior.PLATEAU)
                .selectionWeight(7)
                .nether().medium(FishingMedium.LAVA)
                .journalLocation("journal.info.location.lava")
                .journalGroup(JournalGroup.LAVA)
                .journalRarity(FishRarity.VERY_RARE)
                .displayData(display -> display
                        .offsets(0f, -0.7f, 0.15f)
                        .rotation(0f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "fortress_grouper")
                .size(450, 550, 800)
                .strength(0.8f).speed(1.3f)
                .selectionWeight(7)
                .nether().medium(FishingMedium.LAVA)
                .foundIn(IS_FORTRESS_GROUPER_BIOME)
                .journalLocation("journal.info.location.lava")
                .journalGroup(JournalGroup.LAVA)
                .journalRarity(FishRarity.VERY_RARE)
                .displayData(display -> display
                        .entityType(Tide.resource(MOD_ID, "fortressgrouper"))
                        .shape(FishDisplayShape.SHAPE_3x1)
                        .offsets(0.3f, 0.2f, 0.2f)
                        .rotation(90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "eyeball_fish")
                .size(140, 200, 360)
                .strength(0.75f).speed(1.25f)
                .behavior(MinigameBehavior.DARTS)
                .selectionWeight(7)
                .nether().medium(FishingMedium.LAVA)
                .foundIn(IS_EYEBALL_FISH_BIOME)
                .journalLocation("journal.info.location.lava")
                .journalGroup(JournalGroup.LAVA)
                .journalRarity(FishRarity.VERY_RARE)
                .displayData(display -> display
                        .shape(FishDisplayShape.SHAPE_3x1)
                        .offsets(1f, 0.125f, 0f)
                        .rotation(90f, 0f, 0f))
                .build(output);
    }
}
