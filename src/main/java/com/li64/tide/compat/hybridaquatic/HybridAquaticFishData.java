//? if fabric {
package com.li64.tide.compat.hybridaquatic;

import com.li64.tide.Tide;
import com.li64.tide.data.TideTags;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.fishing.MinigameBehavior;
import com.li64.tide.data.journal.FishRarity;
import com.li64.tide.data.journal.JournalGroup;
import com.li64.tide.datagen.fabric.providers.SimpleDataOutput;
import com.li64.tide.registries.TideFish;
import com.li64.tide.registries.blocks.FishDisplayShape;

import java.util.List;

public class HybridAquaticFishData {
    public static final String MOD_ID = "hybrid-aquatic";

    public static void generate(SimpleDataOutput<FishData> output) {
        FishData.builder().fish(MOD_ID, "anglerfish")
                .parent(TideFish.ANGLERFISH)
                .displayData(display -> display
                        .offsets(0.06f, -0.2f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "barreleye")
                .size(10, 20, 35)
                .strength(0.4f)
                .speed(1.3f)
                .selectionWeight(12)
                .overworld().water().below(15)
                .journalLocation("journal.info.location.underground")
                .journalGroup(JournalGroup.UNDERGROUND)
                .journalRarity(FishRarity.RARE)
                .displayData(display -> display
                        .offsets(0.04f, -0.1f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "betta")
                .size(6, 12, 30)
                .strength(0.35f)
                .speed(0.8f)
                .selectionWeight(20)
                .overworld().water().surface()
                .foundIn(TideTags.Biomes.HAS_SWAMP_FISH)
                .journalLocation("journal.info.location.swamp")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.04f, -0.1f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "blowfish")
                .size(12, 22, 60)
                .strength(0.45f)
                .speed(1.25f)
                .selectionWeight(25)
                .temperature(0.8f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.1f, -0.2f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "blue_spotted_stingray")
                .size(55, 75, 130)
                .strength(0.65f)
                .speed(1.1f)
                .selectionWeight(12)
                .temperature(0.55f, 0.4f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.RARE)
                .displayData(display -> display
                        .entityType(Tide.resource(MOD_ID, "stingray"))
                        .rotation(0f, 0f, 90f))
                .build(output);

        FishData.builder().fish(MOD_ID, "boxfish")
                .size(40, 50, 90)
                .strength(0.45f)
                .speed(1.25f)
                .selectionWeight(25)
                .temperature(0.8f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0f, -0.2f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "carp")
                .parent(TideFish.CARP)
                .displayData(display -> display
                        .offsets(0.05f, -0.15f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "coelacanth")
                .parent(TideFish.COELACANTH)
                .displayData(display -> display
                        .offsets(0.14f, -0.25f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "clownfish")
                .size(18, 30, 50)
                .strength(0.45f)
                .speed(1.25f)
                .selectionWeight(25)
                .temperature(0.8f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.1f, -0.15f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "damselfish")
                .size(10, 23, 40)
                .strength(0.45f)
                .speed(1.25f)
                .selectionWeight(25)
                .temperature(0.8f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.07f, -0.18f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "danio")
                .size(5, 8, 16)
                .strength(0.4f)
                .speed(1.0f)
                .selectionWeight(19)
                .overworld().water().surface()
                .foundIn(List.of(
                        TideTags.Biomes.HAS_JUNGLE_FISH,
                        TideTags.Biomes.HAS_SWAMP_FISH
                ))
                .journalLocation("journal.info.location.jungle")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0f, -0.15f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "discus")
                .size(15, 20, 40)
                .strength(0.4f)
                .speed(0.9f)
                .selectionWeight(18)
                .overworld().water().surface()
                .foundIn(List.of(
                        TideTags.Biomes.HAS_JUNGLE_FISH,
                        TideTags.Biomes.HAS_SWAMP_FISH
                ))
                .journalLocation("journal.info.location.jungle")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.04f, -0.18f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "dragonfish")
                .size(15, 30, 60)
                .strength(0.5f)
                .speed(1.15f)
                .selectionWeight(12)
                .overworld().water().below(15)
                .journalLocation("journal.info.location.underground")
                .journalGroup(JournalGroup.UNDERGROUND)
                .journalRarity(FishRarity.RARE)
                .displayData(display -> display
                        .shape(FishDisplayShape.SHAPE_2x1)
                        .offsets(0.3f, -0.1f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "flashlight_fish")
                .size(10, 23, 40)
                .strength(0.45f)
                .speed(1.25f)
                .selectionWeight(25)
                .temperature(0.8f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.02f, -0.1f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "flying_fish")
                .size(17, 30, 40)
                .strength(0.3f)
                .speed(1.25f)
                .behavior(MinigameBehavior.PLATEAU)
                .selectionWeight(30)
                .temperature(0.55f, 0.45f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .displayData(display -> display
                        .offsets(0.02f, -0.14f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "golden_dorado")
                .size(50, 80, 140)
                .strength(0.7f)
                .speed(1.1f)
                .selectionWeight(13)
                .temperature(0.6f, 0.45f)
                .overworld().water().surface().freshwater()
                .journalLocation("journal.info.location.freshwater")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.RARE)
                .displayData(display -> display
                        .offsets(0.06f, -0.2f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "goldfish")
                .size(5, 8, 20)
                .strength(0.3f)
                .speed(1.5f)
                .selectionWeight(40)
                .overworld().water().surface()
                .foundIn(TideTags.Biomes.HAS_CHERRY_GROVE_FISH)
                .journalLocation("journal.info.location.cherry_grove")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0f, -0.25f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "gourami")
                .size(15, 30, 75)
                .strength(0.4f)
                .speed(0.9f)
                .selectionWeight(18)
                .overworld().water().surface()
                .foundIn(List.of(
                        TideTags.Biomes.HAS_JUNGLE_FISH,
                        TideTags.Biomes.HAS_SWAMP_FISH
                ))
                .journalLocation("journal.info.location.jungle")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0f, -0.15f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "herring")
                .size(20, 35, 60)
                .strength(0.5f)
                .speed(1.0f)
                .selectionWeight(40)
                .temperature(0f, 0.5f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .displayData(display -> display
                        .offsets(0.02f, -0.1f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "john_dory")
                .size(10, 20, 40)
                .strength(0.4f)
                .speed(1.0f)
                .selectionWeight(23)
                .overworld().water().below(40)
                .journalLocation("journal.info.location.underground")
                .journalGroup(JournalGroup.UNDERGROUND)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .shape(FishDisplayShape.SHAPE_2x1)
                        .offsets(0.1f, -0.25f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "lionfish")
                .size(10, 23, 40)
                .strength(0.45f)
                .speed(1.25f)
                .selectionWeight(25)
                .temperature(0.8f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.1f, -0.2f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "mackerel")
                .parent(TideFish.MACKEREL)
                .displayData(display -> display
                        .offsets(0.02f, -0.1f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "mahi")
                .parent(TideFish.MAHI_MAHI)
                .displayData(display -> display
                        .shape(FishDisplayShape.SHAPE_2x1)
                        .offsets(0.14f, -0.35f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "moray_eel")
                .size(110, 170, 300)
                .strength(0.6f)
                .speed(1.2f)
                .selectionWeight(14)
                .temperature(0.5f, 0.45f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.RARE)
                .displayData(display -> display
                        .offsets(0.24f, -0.2f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "needlefish")
                .size(30, 90, 200)
                .strength(0.4f)
                .speed(1.25f)
                .selectionWeight(24)
                .temperature(0.7f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0f, -0.15f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "neon_tetra")
                .size(4, 7, 15)
                .strength(0.4f)
                .speed(1.0f)
                .selectionWeight(19)
                .overworld().water().surface()
                .foundIn(List.of(
                        TideTags.Biomes.HAS_JUNGLE_FISH,
                        TideTags.Biomes.HAS_SWAMP_FISH
                ))
                .journalLocation("journal.info.location.jungle")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .entityType(Tide.resource(MOD_ID, "tetra"))
                        .offsets(0f, -0.1f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "oarfish")
                .size(250, 500, 850)
                .strength(0.8f)
                .speed(1.2f)
                .selectionWeight(6)
                .overworld().water().below(10)
                .journalLocation("journal.info.location.underground")
                .journalGroup(JournalGroup.UNDERGROUND)
                .journalRarity(FishRarity.VERY_RARE)
                .displayData(display -> display
                        .shape(FishDisplayShape.SHAPE_3x1)
                        .offsets(0f, -0.3f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "opah")
                .size(75, 90, 135)
                .strength(0.5f)
                .speed(0.8f)
                .selectionWeight(20)
                .temperature(0.35f, 0.5f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.04f, -0.48f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "oscar")
                .size(25, 35, 70)
                .strength(0.4f)
                .speed(0.9f)
                .selectionWeight(18)
                .overworld().water().surface()
                .foundIn(List.of(
                        TideTags.Biomes.HAS_JUNGLE_FISH,
                        TideTags.Biomes.HAS_SWAMP_FISH
                ))
                .journalLocation("journal.info.location.jungle")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.04f, -0.18f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "parrotfish")
                .size(30, 50, 90)
                .strength(0.4f)
                .speed(1.0f)
                .selectionWeight(30)
                .temperature(0.6f, 0.5f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .displayData(display -> display
                        .offsets(0f, -0.2f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "pearlfish")
                .size(40, 60, 100)
                .strength(0.55f)
                .speed(1.6f)
                .selectionWeight(2.5)
                .selectionQuality(0.5)
                .temperature(0f, 0.6f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.VERY_RARE)
                .displayData(display -> display
                        .offsets(0.02f, -0.08f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "piranha")
                .size(30, 45, 65)
                .strength(0.5f)
                .speed(1.0f)
                .selectionWeight(30)
                .overworld().water().surface().freshwater()
                .journalLocation("journal.info.location.freshwater")
                .journalGroup(JournalGroup.FRESHWATER)
                .displayData(display -> display
                        .offsets(0.02f, -0.15f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "ratfish")
                .size(30, 65, 110)
                .strength(0.5f)
                .speed(1.0f)
                .selectionWeight(20)
                .overworld().water().below(40)
                .journalLocation("journal.info.location.underground")
                .journalGroup(JournalGroup.UNDERGROUND)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.13f, -0.2f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "rockfish")
                .size(35, 55, 120)
                .strength(0.55f)
                .speed(1.25f)
                .selectionWeight(26)
                .temperature(0.5f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.05f, -0.18f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "sea_bass")
                .size(50, 80, 150)
                .strength(0.5f)
                .speed(1.0f)
                .selectionWeight(45)
                .temperature(0.2f, 0.8f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .displayData(display -> display
                        .offsets(0.1f, -0.18f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "seahorse")
                .size(15, 25, 45)
                .strength(0.4f)
                .speed(1.2f)
                .selectionWeight(25)
                .temperature(0.8f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.03f, -0.24f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "sheepshead_wrasse")
                .size(60, 75, 120)
                .strength(0.4f)
                .speed(1.0f)
                .selectionWeight(28)
                .temperature(0f, 0.5f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .displayData(display -> display
                        .entityType(Tide.resource(MOD_ID, "wrasse"))
                        .shape(FishDisplayShape.SHAPE_2x1)
                        .offsets(0.05f, -0.2f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "snailfish")
                .size(40, 60, 90)
                .strength(0.5f)
                .speed(1.15f)
                .selectionWeight(30)
                .overworld().water().below(40)
                .journalLocation("journal.info.location.underground")
                .journalGroup(JournalGroup.UNDERGROUND)
                .displayData(display -> display
                        .offsets(0.1f, -0.12f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "spotted_eagle_ray")
                .size(160, 200, 300)
                .strength(0.65f)
                .speed(1.1f)
                .selectionWeight(12)
                .temperature(0.55f, 0.4f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.RARE)
                .displayData(display -> display
                        .entityType(Tide.resource(MOD_ID, "stingray"))
                        .offsets(0f, 0.1f, 0f)
                        .rotation(0f, 0f, 90f))
                .build(output);

        FishData.builder().fish(MOD_ID, "squirrelfish")
                .size(12, 25, 45)
                .strength(0.45f)
                .speed(1.25f)
                .selectionWeight(25)
                .temperature(0.8f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0f, -0.18f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "stonefish")
                .size(30, 40, 90)
                .strength(0.55f)
                .speed(1.25f)
                .selectionWeight(20)
                .temperature(0.8f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.07f, -0.18f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "sunfish")
                .size(75, 90, 135)
                .strength(0.85f)
                .speed(0.65f)
                .selectionWeight(10)
                .temperature(0.35f, 0.5f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.RARE)
                .displayData(display -> display
                        .shape(FishDisplayShape.SHAPE_3x1)
                        .offsets(0.35f, -1.0f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "surgeonfish")
                .size(20, 35, 55)
                .strength(0.45f)
                .speed(1.25f)
                .selectionWeight(25)
                .temperature(0.8f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.02f, -0.15f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "tiger_barb")
                .size(8, 12, 20)
                .strength(0.4f)
                .speed(0.9f)
                .selectionWeight(18)
                .overworld().water().surface()
                .foundIn(List.of(
                        TideTags.Biomes.HAS_JUNGLE_FISH,
                        TideTags.Biomes.HAS_SWAMP_FISH
                ))
                .journalLocation("journal.info.location.jungle")
                .journalGroup(JournalGroup.FRESHWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0.1f, -0.18f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "triggerfish")
                .size(25, 45, 100)
                .strength(0.45f)
                .speed(1.25f)
                .selectionWeight(25)
                .temperature(0.8f, 0.7f)
                .overworld().water().surface().saltwater()
                .journalLocation("journal.info.location.saltwater")
                .journalGroup(JournalGroup.SALTWATER)
                .journalRarity(FishRarity.UNCOMMON)
                .displayData(display -> display
                        .offsets(0f, -0.35f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);

        FishData.builder().fish(MOD_ID, "tuna")
                .parent(TideFish.TUNA)
                .displayData(display -> display
                        .shape(FishDisplayShape.SHAPE_3x1)
                        .offsets(0.14f, -0.3f, 0.08f)
                        .rotation(-90f, 0f, 0f))
                .build(output);
    }
}
//?}