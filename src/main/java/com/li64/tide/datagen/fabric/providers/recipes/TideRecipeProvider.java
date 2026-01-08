//? if fabric {
package com.li64.tide.datagen.fabric.providers.recipes;

import com.li64.tide.data.TideTags;
import com.li64.tide.registries.TideFish;
import com.li64.tide.registries.TideItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.registries.Registries;
import org.jetbrains.annotations.NotNull;

/*? if >=1.21 {*/import com.li64.tide.data.FishyNoteRecipe;
//?} else {
/*import com.li64.tide.data.TideRecipeSerializers;
import java.util.function.Consumer;
*///?}

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class TideRecipeProvider extends FabricRecipeProvider {
    static final List<Item> BOBBERS = List.of(
            TideItems.WHITE_FISHING_BOBBER,
            TideItems.ORANGE_FISHING_BOBBER,
            TideItems.MAGENTA_FISHING_BOBBER,
            TideItems.LIGHT_BLUE_FISHING_BOBBER,
            TideItems.YELLOW_FISHING_BOBBER,
            TideItems.LIME_FISHING_BOBBER,
            TideItems.PINK_FISHING_BOBBER,
            TideItems.GRAY_FISHING_BOBBER,
            TideItems.LIGHT_GRAY_FISHING_BOBBER,
            TideItems.CYAN_FISHING_BOBBER,
            TideItems.PURPLE_FISHING_BOBBER,
            TideItems.BLUE_FISHING_BOBBER,
            TideItems.BROWN_FISHING_BOBBER,
            TideItems.GREEN_FISHING_BOBBER,
            TideItems.RED_FISHING_BOBBER,
            TideItems.BLACK_FISHING_BOBBER
    );

    @SuppressWarnings("unused")
    public TideRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output/*? if >=1.21 {*/, registryLookup/*?}*/);
    }

    @Override
    /*? if >=1.21 {*/public void buildRecipes(RecipeOutput output) {
     /*?} else*//*public void buildRecipes(Consumer<FinishedRecipe> output) {*/
        // -- Shapeless --
        shapeless(RecipeCategory.BUILDING_BLOCKS, Items.OBSIDIAN, 1)
                .requires(TideItems.OBSIDIAN_FRAGMENT)
                .requires(TideItems.OBSIDIAN_FRAGMENT)
                .requires(TideItems.OBSIDIAN_FRAGMENT)
                .requires(TideItems.OBSIDIAN_FRAGMENT)
                .unlockedBy("has_obsidian_fragment", has(TideItems.OBSIDIAN_FRAGMENT))
                .save(output, "obsidian_from_fragments");

        shapeless(RecipeCategory.MISC, TideItems.FISHING_JOURNAL, 1)
                .requires(TideTags.Items.FISH)
                .requires(Items.BOOK)
                .unlockedBy("has_book", has(Items.BOOK))
                .unlockedBy("has_fish", has(TideTags.Items.FISH))
                .save(output);

        shapeless(RecipeCategory.MISC, TideItems.BAIT, 4)
                .requires(TideTags.Items.BAIT_PLANTS)
                .requires(Items.ROTTEN_FLESH)
                .requires(TideTags.Convention.SEEDS)
                .requires(Items.BONE_MEAL)
                .unlockedBy("has_bait_plants", has(TideTags.Items.BAIT_PLANTS))
                .unlockedBy("has_rotten_flesh", has(Items.ROTTEN_FLESH))
                .unlockedBy("has_seeds", has(TideTags.Convention.SEEDS))
                .unlockedBy("has_bone_meal", has(Items.BONE_MEAL))
                .save(output);

        shapeless(RecipeCategory.TOOLS, TideItems.POCKET_WATCH, 1)
                .requires(Items.CLOCK)
                .requires(Items.CHAIN)
                .unlockedBy("has_clock", has(Items.CLOCK))
                .unlockedBy("has_chain", has(Items.CHAIN))
                .save(output);

        shapeless(RecipeCategory.TOOLS, TideItems.ENCHANTED_POCKET_WATCH, 1)
                .requires(TideItems.POCKET_WATCH)
                .requires(TideFish.COELACANTH)
                .unlockedBy("has_coelacanth", has(TideFish.COELACANTH))
                .save(output);

        shapeless(RecipeCategory.COMBAT, TideItems.STARLIGHT_BOW, 1)
                .requires(Items.BOW)
                .requires(TideFish.SHOOTING_STARFISH)
                .unlockedBy("has_shooting_starfish", has(TideFish.SHOOTING_STARFISH))
                .save(output);

        shapeless(RecipeCategory.MISC, Items.AMETHYST_SHARD, 4)
                .requires(TideFish.CRYSTAL_SHRIMP)
                .unlockedBy("has_crystal_shrimp", has(TideFish.CRYSTAL_SHRIMP))
                .save(output, "tide:amethyst_from_shrimp");

        shapeless(RecipeCategory.MISC, Items.ENDER_PEARL, 1)
                .requires(TideFish.ENDERFIN)
                .unlockedBy("has_enderfin", has(TideFish.ENDERFIN))
                .save(output, "tide:pearl_from_enderfin");

        shapeless(RecipeCategory.MISC, Items.ENDER_EYE, 1)
                .requires(TideFish.ENDERGAZER)
                .unlockedBy("has_endergazer", has(TideFish.ENDERGAZER))
                .save(output, "tide:eye_from_endergazer");

        shapeless(RecipeCategory.MISC, Items.BONE_MEAL, 2)
                .requires(TideItems.FISH_BONE)
                .unlockedBy("has_fish_bone", has(TideItems.FISH_BONE))
                .save(output, "tide:bone_meal_from_fish_bone");

        shapeless(RecipeCategory.MISC, Items.GLOW_INK_SAC, 2)
                .requires(TideFish.GLOWFISH)
                .unlockedBy("has", has(TideFish.GLOWFISH))
                .save(output, "tide:glow_ink_from_fish");

        shapeless(RecipeCategory.MISC, Items.RAW_GOLD, 2)
                .requires(TideFish.GILDED_MINNOW)
                .unlockedBy("has_gilded_minnow", has(TideFish.GILDED_MINNOW))
                .save(output, "tide:gold_from_minnow");

        shapeless(RecipeCategory.MISC, Items.RAW_IRON, 2)
                .requires(TideFish.IRON_TETRA)
                .unlockedBy("has_iron_tetra", has(TideFish.IRON_TETRA))
                .save(output, "tide:iron_from_tetra");

        shapeless(RecipeCategory.MISC, Items.LAPIS_LAZULI, 2)
                .requires(TideFish.LAPIS_LANTERNFISH)
                .unlockedBy("has_lapis_lanternfish", has(TideFish.LAPIS_LANTERNFISH))
                .save(output, "tide:lapis_from_lanternfish");

        shapeless(RecipeCategory.MISC, Items.PHANTOM_MEMBRANE, 1)
                .requires(TideFish.ELYTROUT)
                .unlockedBy("has_elytrout", has(TideFish.ELYTROUT))
                .save(output, "tide:membrane_from_elytrout");

        shapeless(RecipeCategory.MISC, TideItems.JELLY_TORCH, 8)
                .requires(TideFish.LUMINESCENT_JELLYFISH)
                .unlockedBy("has_luminescent_jellyfish", has(TideFish.LUMINESCENT_JELLYFISH))
                .save(output, "tide:jelly_torch_from_jellyfish");

        shapeless(RecipeCategory.MISC, TideItems.JELLY_TORCH, 8)
                .requires(TideFish.SUN_EMBLEM)
                .unlockedBy("has_sun_emblem", has(TideFish.SUN_EMBLEM))
                .save(output, "tide:jelly_torch_from_sun_emblem");

        createColoredBobberRecipes(output);
        createSimpleBobberRecipe(output, TideItems.APPLE_FISHING_BOBBER, Items.APPLE);
        createSimpleBobberRecipe(output, TideItems.GOLDEN_APPLE_FISHING_BOBBER, Items.GOLDEN_APPLE);
        createSimpleBobberRecipe(output, TideItems.ENCHANTED_GOLDEN_APPLE_FISHING_BOBBER, Items.ENCHANTED_GOLDEN_APPLE);
        createSimpleBobberRecipe(output, TideItems.IRON_FISHING_BOBBER, Items.IRON_INGOT);
        createSimpleBobberRecipe(output, TideItems.GOLDEN_FISHING_BOBBER, Items.GOLD_INGOT);
        createSimpleBobberRecipe(output, TideItems.DIAMOND_FISHING_BOBBER, Items.DIAMOND);
        createSimpleBobberRecipe(output, TideItems.NETHERITE_FISHING_BOBBER, Items.NETHERITE_INGOT);
        createSimpleBobberRecipe(output, TideItems.AMETHYST_FISHING_BOBBER, Items.AMETHYST_SHARD);
        createSimpleBobberRecipe(output, TideItems.ECHO_FISHING_BOBBER, Items.ECHO_SHARD);
        createSimpleBobberRecipe(output, TideItems.CHORUS_FISHING_BOBBER, Items.CHORUS_FRUIT);
        createSimpleBobberRecipe(output, TideItems.FEATHER_FISHING_BOBBER, Items.FEATHER);
        createSimpleBobberRecipe(output, TideItems.LICHEN_FISHING_BOBBER, Items.GLOW_LICHEN);
        createSimpleBobberRecipe(output, TideItems.NAUTILUS_FISHING_BOBBER, Items.NAUTILUS_SHELL);
        createSimpleBobberRecipe(output, TideItems.PEARL_FISHING_BOBBER, Items.ENDER_PEARL);
        createSimpleBobberRecipe(output, TideItems.HEART_FISHING_BOBBER, Items.HEART_OF_THE_SEA);
        createSimpleBobberRecipe(output, TideItems.GRASSY_FISHING_BOBBER, Items.GRASS_BLOCK);
        createSimpleBobberRecipe(output, TideItems.DUCK_FISHING_BOBBER, Items.CHICKEN);

        // -- Shaped --

        shaped(RecipeCategory.TOOLS, TideItems.FISH_SATCHEL)
                .pattern(" L ")
                .pattern("L#L")
                .pattern(" L ")
                .define('L', TideTags.Convention.LEATHERS)
                .define('#', TideTags.Items.FISH)
                .unlockedBy("has_leather", has(TideTags.Convention.LEATHERS))
                .unlockedBy("has_fish", has(TideTags.Items.FISH))
                .save(output);

        shaped(RecipeCategory.DECORATIONS, TideItems.FISH_DISPLAY)
                .pattern("SSS")
                .pattern("S#S")
                .pattern("SSS")
                .define('S', Items.STICK)
                .define('#', ItemTags.LOGS)
                .unlockedBy("has_log", has(ItemTags.LOGS))
                .save(output);

        shaped(RecipeCategory.DECORATIONS, TideItems.ANGLING_TABLE)
                .pattern("SS")
                .pattern("CC")
                .pattern("##")
                .define('S', TideTags.Convention.STRINGS)
                .define('C', TideTags.Convention.COPPER_INGOTS)
                .define('#', ItemTags.PLANKS)
                .unlockedBy("has_string", has(TideTags.Convention.STRINGS))
                .unlockedBy("has_copper_ingot", has(TideTags.Convention.COPPER_INGOTS))
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(output);

        shaped(RecipeCategory.MISC, TideItems.WOODEN_CRATE)
                .pattern("SSS")
                .pattern("S S")
                .pattern("SSS")
                .define('S', ItemTags.WOODEN_SLABS)
                .unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
                .save(output);

        shaped(RecipeCategory.TOOLS, TideItems.THERMOMETER, 1)
                .pattern(" # ")
                .pattern("#I#")
                .pattern("#R#")
                .define('#', TideTags.Convention.COLORLESS_GLASS_BLOCKS)
                .define('I', TideTags.Convention.IRON_NUGGETS)
                .define('R', TideTags.Convention.REDSTONE_DUSTS)
                .unlockedBy("has_redstone", has(TideTags.Convention.REDSTONE_DUSTS))
                .save(output);

        shaped(RecipeCategory.MISC, TideItems.LUCKY_BAIT, 16)
                .pattern("###")
                .pattern("#R#")
                .pattern("###")
                .define('#', TideItems.BAIT)
                .define('R', Items.RABBIT_FOOT)
                .unlockedBy("has_rabbit_foot", has(Items.RABBIT_FOOT))
                .save(output);

        shaped(RecipeCategory.MISC, TideItems.MAGNETIC_BAIT, 4)
                .pattern("L#R")
                .pattern("I#I")
                .pattern(" P ")
                .define('L', TideTags.Convention.LAPIS_GEMS)
                .define('R', TideTags.Convention.REDSTONE_DUSTS)
                .define('P', TideTags.Convention.PRISMARINE_GEMS)
                .define('I', Items.IRON_NUGGET)
                .define('#', TideItems.BAIT)
                .unlockedBy("has_lapis", has(TideTags.Convention.LAPIS_GEMS))
                .unlockedBy("has_redstone", has(TideTags.Convention.REDSTONE_DUSTS))
                .unlockedBy("has_prismarine", has(TideTags.Convention.PRISMARINE_GEMS))
                .save(output);

        shaped(RecipeCategory.TOOLS, TideItems.STONE_FISHING_ROD)
                .pattern("  #")
                .pattern(" #S")
                .pattern("# S")
                .define('S', TideTags.Convention.STRINGS)
                .define('#', TideTags.Convention.COBBLESTONES)
                .unlockedBy("has_string", has(TideTags.Convention.STRINGS))
                .unlockedBy("has_cobble", has(TideTags.Convention.COBBLESTONES))
                .save(output);

        shaped(RecipeCategory.TOOLS, TideItems.FISHING_LINE)
                .pattern("S")
                .pattern("S")
                .pattern("S")
                .define('S', TideTags.Convention.STRINGS)
                .unlockedBy("has_string", has(TideTags.Convention.STRINGS))
                .save(output);

        shaped(RecipeCategory.TOOLS, TideItems.BRAIDED_LINE)
                .pattern("#")
                .pattern("L")
                .pattern("#")
                .define('L', TideItems.FISHING_LINE)
                .define('#', TideTags.Convention.SLIME_BALLS)
                .unlockedBy("has_fishing_line", has(TideItems.FISHING_LINE))
                .unlockedBy("has_slimeball", has(TideTags.Convention.SLIME_BALLS))
                .save(output);

        shaped(RecipeCategory.TOOLS, TideItems.REINFORCED_LINE)
                .pattern("#")
                .pattern("L")
                .pattern("#")
                .define('L', TideItems.FISHING_LINE)
                .define('#', TideTags.Convention.IRON_NUGGETS)
                .unlockedBy("has_fishing_line", has(TideItems.FISHING_LINE))
                .unlockedBy("has_iron_nuggets", has(TideTags.Convention.IRON_NUGGETS))
                .save(output);

        shaped(RecipeCategory.TOOLS, TideItems.GOLDEN_LINE)
                .pattern("#")
                .pattern("L")
                .pattern("#")
                .define('L', TideItems.FISHING_LINE)
                .define('#', TideTags.Convention.GOLD_INGOTS)
                .unlockedBy("has_fishing_line", has(TideItems.FISHING_LINE))
                .save(output);

        shaped(RecipeCategory.TOOLS, TideItems.FISHING_HOOK)
                .pattern("#")
                .pattern("C")
                .pattern("#")
                .define('C', TideTags.Convention.COBBLESTONES)
                .define('#', Items.IRON_NUGGET)
                .unlockedBy("has_cobble", has(TideTags.Convention.COBBLESTONES))
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .save(output);

        shaped(RecipeCategory.TOOLS, TideItems.IRON_FISHING_HOOK)
                .pattern("  #")
                .pattern("I I")
                .pattern(" I ")
                .define('I', TideTags.Convention.IRON_INGOTS)
                .define('#', TideTags.Convention.IRON_NUGGETS)
                .unlockedBy("has_iron_ingot", has(TideTags.Convention.IRON_INGOTS))
                .unlockedBy("has_iron_nugget", has(TideTags.Convention.IRON_NUGGETS))
                .save(output);

        shaped(RecipeCategory.TOOLS, TideItems.LAVAPROOF_FISHING_HOOK)
                .pattern("  #")
                .pattern("O O")
                .pattern(" O ")
                .define('O', TideTags.Convention.OBSIDIANS)
                .define('#', TideTags.Convention.GOLD_INGOTS)
                .unlockedBy("has_obsidian", has(TideTags.Convention.OBSIDIANS))
                .unlockedBy("has_copper", has(TideTags.Convention.GOLD_INGOTS))
                .save(output);

        shaped(RecipeCategory.TOOLS, TideItems.VOID_FISHING_HOOK)
                .pattern("  #")
                .pattern("#D#")
                .pattern(" # ")
                .define('D', Items.DRAGON_BREATH)
                .define('#', Blocks.CRYING_OBSIDIAN)
                .unlockedBy("has_dragon_breath", has(Items.DRAGON_BREATH))
                .save(output);

        // -- Smithing --

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TideTags.Convention.STRINGS),
                        Ingredient.of(Items.FISHING_ROD, TideItems.STONE_FISHING_ROD),
                        Ingredient.of(TideTags.Convention.IRON_INGOTS),
                        RecipeCategory.TOOLS,
                        TideItems.IRON_FISHING_ROD)
                .unlocks("has_string", has(TideTags.Convention.STRINGS))
                .unlocks("has_stone_fishing_rod", has(TideItems.STONE_FISHING_ROD))
                .unlocks("has_iron_ingot", has(TideTags.Convention.IRON_INGOTS))
                .save(output, "tide:iron_rod_smithing");

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TideTags.Convention.STRINGS),
                        Ingredient.of(Items.FISHING_ROD, TideItems.STONE_FISHING_ROD,
                                TideItems.IRON_FISHING_ROD),
                        Ingredient.of(TideTags.Convention.GOLD_INGOTS),
                        RecipeCategory.TOOLS,
                        TideItems.GOLDEN_FISHING_ROD)
                .unlocks("has_string", has(TideTags.Convention.STRINGS))
                .unlocks("has_stone_fishing_rod", has(TideItems.STONE_FISHING_ROD))
                .unlocks("has_gold", has(TideTags.Convention.GOLD_INGOTS))
                .save(output, "tide:gold_rod_smithing");

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TideTags.Convention.STRINGS),
                        Ingredient.of(Items.FISHING_ROD, TideItems.STONE_FISHING_ROD,
                                TideItems.IRON_FISHING_ROD, TideItems.GOLDEN_FISHING_ROD),
                        Ingredient.of(TideTags.Convention.AMETHYST_GEMS),
                        RecipeCategory.TOOLS,
                        TideItems.CRYSTAL_FISHING_ROD)
                .unlocks("has_string", has(TideTags.Convention.STRINGS))
                .unlocks("has_iron_fishing_rod", has(TideItems.IRON_FISHING_ROD))
                .unlocks("has_amethyst", has(TideTags.Convention.AMETHYST_GEMS))
                .save(output, "tide:crystal_rod_smithing");

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TideTags.Convention.STRINGS),
                        Ingredient.of(Items.FISHING_ROD, TideItems.STONE_FISHING_ROD,
                                TideItems.IRON_FISHING_ROD, TideItems.GOLDEN_FISHING_ROD,
                                TideItems.CRYSTAL_FISHING_ROD),
                        Ingredient.of(TideTags.Convention.DIAMOND_GEMS),
                        RecipeCategory.TOOLS,
                        TideItems.DIAMOND_FISHING_ROD)
                .unlocks("has_string", has(TideTags.Convention.STRINGS))
                .unlocks("has_iron_fishing_rod", has(TideItems.IRON_FISHING_ROD))
                .unlocks("has_diamond", has(TideTags.Convention.DIAMOND_GEMS))
                .save(output, "tide:diamond_rod_smithing");

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(TideItems.DIAMOND_FISHING_ROD),
                        Ingredient.of(TideTags.Convention.NETHERITE_INGOTS),
                        RecipeCategory.TOOLS,
                        TideItems.NETHERITE_FISHING_ROD)
                .unlocks("has_netherite_upgrade_template", has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .unlocks("has_diamond_fishing_rod", has(TideItems.DIAMOND_FISHING_ROD))
                .unlocks("has_netherite", has(TideTags.Convention.NETHERITE_INGOTS))
                .save(output, "tide:netherite_rod_smithing");

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(TideTags.Convention.STRINGS),
                        Ingredient.of(TideItems.GOLDEN_FISHING_ROD),
                        Ingredient.of(TideFish.MIDAS_FISH),
                        RecipeCategory.TOOLS,
                        TideItems.MIDAS_FISHING_ROD)
                .unlocks("has_midas_fish", has(TideFish.MIDAS_FISH))
                .save(output, "tide:midas_rod_smithing");

        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.DRAGON_BREATH),
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(TideFish.DRAGON_FISH),
                        RecipeCategory.TOOLS,
                        TideItems.DRAGONFIN_BOOTS)
                .unlocks("has_dragon_breath", has(Items.DRAGON_BREATH))
                .unlocks("has_netherite_boots", has(Items.NETHERITE_BOOTS))
                .unlocks("has_dragon_fish", has(TideFish.DRAGON_FISH))
                .save(output, "tide:dragonfin_boots_smithing");

        // -- Cooking --

        TideFish.COOKABLE_FISH_MAP.forEach((cookedItem, rawItems) -> {
            String name = BuiltInRegistries.ITEM.getKey(cookedItem).getPath();
            TagKey<Item> cookables = TideTags.Cookables.getCookableTag(cookedItem);
            String cookablesName = cookables.location().getPath();

            SimpleCookingRecipeBuilder.generic(Ingredient.of(cookables),
                            RecipeCategory.FOOD, cookedItem, 0.35f, 600,
                            RecipeSerializer.CAMPFIRE_COOKING_RECIPE/*? if >=1.21 {*/, CampfireCookingRecipe::new/*?}*/)
                    .unlockedBy("has_" + cookablesName, has(cookables))
                    .save(output, "tide:" + name + "_campfire_cooking");

            SimpleCookingRecipeBuilder.generic(Ingredient.of(cookables),
                            RecipeCategory.FOOD, cookedItem, 0.1f, 200,
                            RecipeSerializer.SMELTING_RECIPE/*? if >=1.21 {*/, SmeltingRecipe::new/*?}*/)
                    .unlockedBy("has_" + cookablesName, has(cookables))
                    .save(output, "tide:" + name + "_smelting");

            SimpleCookingRecipeBuilder.generic(Ingredient.of(cookables),
                            RecipeCategory.FOOD, cookedItem, 0.1f, 100,
                            RecipeSerializer.SMOKING_RECIPE/*? if >=1.21 {*/, SmokingRecipe::new/*?}*/)
                    .unlockedBy("has_" + cookablesName, has(cookables))
                    .save(output, "tide:" + name + "_smoking");
        });

        SimpleCookingRecipeBuilder.generic(Ingredient.of(TideItems.FISH_SLICE),
                        RecipeCategory.FOOD, TideItems.COOKED_FISH_SLICE, 0.35f, 600,
                        RecipeSerializer.CAMPFIRE_COOKING_RECIPE/*? if >=1.21 {*/, CampfireCookingRecipe::new/*?}*/)
                .unlockedBy("has_fish_slice", has(TideItems.FISH_SLICE))
                .save(output, "tide:cooked_fish_slice_campfire_cooking");

        SimpleCookingRecipeBuilder.generic(Ingredient.of(TideItems.FISH_SLICE),
                        RecipeCategory.FOOD, TideItems.COOKED_FISH_SLICE, 0.35f, 200,
                        RecipeSerializer.SMELTING_RECIPE/*? if >=1.21 {*/, SmeltingRecipe::new/*?}*/)
                .unlockedBy("has_fish_slice", has(TideItems.FISH_SLICE))
                .save(output, "tide:cooked_fish_slice_smelting");

        SimpleCookingRecipeBuilder.generic(Ingredient.of(TideItems.FISH_SLICE),
                        RecipeCategory.FOOD, TideItems.COOKED_FISH_SLICE, 0.35f, 100,
                        RecipeSerializer.SMOKING_RECIPE/*? if >=1.21 {*/, SmokingRecipe::new/*?}*/)
                .unlockedBy("has_fish_slice", has(TideItems.FISH_SLICE))
                .save(output, "tide:cooked_fish_slice_smoking");

        // -- Special --
        /*? if >=1.21 {*/SpecialRecipeBuilder.special(FishyNoteRecipe::new).save(output, "fishy_note_from_fish");
        /*?} else*//*SpecialRecipeBuilder.special(TideRecipeSerializers.FISHY_NOTE_RECIPE).save(output, "fishy_note_from_fish");*/
    }

    @Override
    public @NotNull String getName() {
        return "Recipes";
    }

    private void createSimpleBobberRecipe(/*? if >=1.21 {*/RecipeOutput/*?} else {*//*Consumer<FinishedRecipe>*//*?}*/ output,
                                          Item bobber, Item addition) {
        String identifier = BuiltInRegistries.ITEM.getKey(bobber).getPath()
                .replace("_fishing_bobber", "");
        String additionPath = BuiltInRegistries.ITEM.getKey(addition).getPath();
        shapeless(RecipeCategory.MISC, bobber)
                .requires(TideTags.Convention.SLIME_BALLS)
                .requires(ItemTags.PLANKS)
                .requires(addition)
                .unlockedBy("has_slimeball", has(TideTags.Convention.SLIME_BALLS))
                .unlockedBy("has_" + additionPath, has(addition))
                .save(output, "tide:bobbers/" + identifier);
    }

    private void createColoredBobberRecipes(/*? if >=1.21 {*/RecipeOutput/*?} else {*//*Consumer<FinishedRecipe>*//*?}*/ output) {
        BOBBERS.forEach(bobber -> {
            String dyeId = BuiltInRegistries.ITEM.getKey(bobber).getPath().replace("_fishing_bobber", "");
            TagKey<Item> dyeTag = TideTags.Convention.create("dyes/" + dyeId, Registries.ITEM);

            shapeless(RecipeCategory.MISC, bobber)
                    .requires(TideTags.Convention.SLIME_BALLS)
                    .requires(ItemTags.PLANKS)
                    .requires(dyeTag)
                    .unlockedBy("has_slimeball", has(TideTags.Convention.SLIME_BALLS))
                    .unlockedBy("has_" + dyeId + "_dye", has(dyeTag))
                    .save(output, "tide:bobbers/" + dyeId);
        });
    }
}
//?}