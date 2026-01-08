//? if fabric {
package com.li64.tide.datagen.fabric.providers.assets;

import com.li64.tide.registries.TideBlocks;
import com.li64.tide.registries.TideEntityTypes;
import com.li64.tide.registries.TideFish;
import com.li64.tide.registries.TideItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class TideLanguageProvider extends FabricLanguageProvider {
    @SuppressWarnings("unused")
    public TideLanguageProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output/*? if >=1.21 {*/, registries/*?}*/);
    }

    @Override
    public void generateTranslations(/*? if >=1.21 {*/HolderLookup.Provider provider, /*?}*/TranslationBuilder builder) {
        // Config

        builder.add("text.autoconfig.tide.title", "Tide Config");
        builder.add("text.autoconfig.tide.category.general", "General");
        builder.add("text.autoconfig.tide.category.items", "Items");
        builder.add("text.autoconfig.tide.category.journal", "Journal");
        builder.add("text.autoconfig.tide.category.minigame", "Minigame");
        builder.add("text.autoconfig.tide.category.worldgen", "World Generation");

        builder.add("text.autoconfig.tide.option.general.overrideVanillaRod", "Override Vanilla Fishing Rod");
        builder.add("text.autoconfig.tide.option.general.overrideVanillaRod.@Tooltip", "When enabled, the vanilla fishing rod will use Tide's fishing loot system");
        builder.add("text.autoconfig.tide.option.general.holdToCast", "Hold to Cast");
        builder.add("text.autoconfig.tide.option.general.holdToCast.@Tooltip", "When enabled, you can hold right-click to cast the bobber farther");
        builder.add("text.autoconfig.tide.option.general.rodDurabilityMultiplier", "Rod Durability Multiplier");
        builder.add("text.autoconfig.tide.option.general.rodDurabilityMultiplier.@Tooltip", "Multiplier applied to the durability of fishing rods");
        builder.add("text.autoconfig.tide.option.general.defaultLineColor", "Use Vanilla Line Color");
        builder.add("text.autoconfig.tide.option.general.defaultLineColor.@Tooltip", "Enable this to use the default black fishing line color");
        builder.add("text.autoconfig.tide.option.general.ambientVoidParticles", "Ambient Void Particles");
        builder.add("text.autoconfig.tide.option.general.ambientVoidParticles.@Tooltip", "Enables the ripple effects on the fishable void surface");
        builder.add("text.autoconfig.tide.option.general.fishableVoidHeights", "Fishable Void Heights");
        builder.add("text.autoconfig.tide.option.general.fishableVoidHeights.@Tooltip", "A list that defines the fishable void heights for each dimension");
        builder.add("text.autoconfig.tide.option.VoidHeightEntry", "Entry");
        builder.add("text.autoconfig.tide.option.VoidHeightEntry.dimension", "Dimension");
        builder.add("text.autoconfig.tide.option.VoidHeightEntry.type", "Type");
        builder.add("text.autoconfig.tide.option.VoidHeightEntry.height", "Height");
        builder.add("text.autoconfig.tide.option.VoidHeightEntry.Type.relative_to_bottom", "Relative to Bottom");
        builder.add("text.autoconfig.tide.option.VoidHeightEntry.Type.relative_to_top", "Relative to Top");
        builder.add("text.autoconfig.tide.option.VoidHeightEntry.Type.absolute", "Absolute Y");
        builder.add("text.autoconfig.tide.option.general.crateWeight", "Crate Selection Weight");
        builder.add("text.autoconfig.tide.option.general.crateWeight.@Tooltip", "The chance of a crate being selected from the fishing loot table (not a percent, see wiki for more info)");
        builder.add("text.autoconfig.tide.option.general.crateQuality", "Crate Luck Scaling");
        builder.add("text.autoconfig.tide.option.general.crateQuality.@Tooltip", "The amount that the crate chance is scaled with higher fishing luck (see wiki for more info)");
        builder.add("text.autoconfig.tide.option.general.logDataErrors", "Log Data Loading Errors");
        builder.add("text.autoconfig.tide.option.general.logDataErrors.@Tooltip", "For datapackers: enable to show Tide data loading errors in the logs");

        builder.add("text.autoconfig.tide.option.items.fishItemSizes.@PrefixText",
                "Note: If Fish Item Sizes is set to \"Always\" or Bucketable Fish Items is set to \"While Living\", extra data will be added to fish item stacks that make them unstackable." +
                        "\nTo make fish stackable, simply change both settings to something other than their defaults.");
        builder.add("text.autoconfig.tide.option.items.fishItemSizes", "Fish Item Sizes");
        builder.add("text.autoconfig.tide.option.items.fishItemSizes.@Tooltip", "Controls when/where fish should be assigned a length");
        builder.add("text.autoconfig.tide.option.items.fishItemSizes.always", "Always");
        builder.add("text.autoconfig.tide.option.items.fishItemSizes.in_journal", "Only In Journal");
        builder.add("text.autoconfig.tide.option.items.fishItemSizes.never", "Never");
        builder.add("text.autoconfig.tide.option.items.bucketableFishItems", "Bucketable Fish Items");
        builder.add("text.autoconfig.tide.option.items.bucketableFishItems.@Tooltip", "Controls when fish items should be bucketable via menus");
        builder.add("text.autoconfig.tide.option.items.bucketableFishItems.always", "Always");
        builder.add("text.autoconfig.tide.option.items.bucketableFishItems.when_living", "Only When Living");
        builder.add("text.autoconfig.tide.option.items.bucketableFishItems.never", "Never");
        builder.add("text.autoconfig.tide.option.items.fishItemLifespan.@PrefixText", "The following only applies if Bucketable Fish Items is set to \"Only When Living\"");
        builder.add("text.autoconfig.tide.option.items.fishItemLifespan", "Fish Item Lifespan");
        builder.add("text.autoconfig.tide.option.items.fishItemLifespan.@Tooltip", "The duration, in seconds, of how long a fish item is bucketable after being caught");

        builder.add("text.autoconfig.tide.option.journal.giveJournal", "Give Journal");
        builder.add("text.autoconfig.tide.option.journal.giveJournal.@Tooltip", "Give players a fishing journal upon joining a world for the first time");
        builder.add("text.autoconfig.tide.option.journal.showToasts", "Show Toasts");
        builder.add("text.autoconfig.tide.option.journal.showToasts.@Tooltip", "Shows a notification (toast) that appears when a new fish is discovered");
        builder.add("text.autoconfig.tide.option.journal.showUnread", "Highlight Unread Profiles");
        builder.add("text.autoconfig.tide.option.journal.showUnread.@Tooltip", "Highlight fish icons in yellow until you click on them for the first time");
        builder.add("text.autoconfig.tide.option.journal.useAmPm", "Use AM/PM");
        builder.add("text.autoconfig.tide.option.journal.useAmPm.@Tooltip", "Disable to use 24-hour time in the journal and pocket watches (ex. 21:00 vs 9:00 PM)");
        builder.add("text.autoconfig.tide.option.journal.useFahrenheit", "Use Fahrenheit");
        builder.add("text.autoconfig.tide.option.journal.useFahrenheit.@Tooltip", "Enable to use Fahrenheit instead of Celsius in the journal and thermometers (ex. 0°C vs 32°F)");
        builder.add("text.autoconfig.tide.option.journal.useRealDate", "Use Real Date");
        builder.add("text.autoconfig.tide.option.journal.useRealDate.@Tooltip", "If enabled, the real date is used in the fishing journal as opposed to the local world day");

        builder.add("text.autoconfig.tide.option.minigame.doMinigame", "Do Minigame");
        builder.add("text.autoconfig.tide.option.minigame.doMinigame.@Tooltip", "Enables the fishing minigame");
        builder.add("text.autoconfig.tide.option.minigame.doFeedback", "Do Feedback");
        builder.add("text.autoconfig.tide.option.minigame.doFeedback.@Tooltip", "Enables scoring messages after a minigame (ex. Perfect Catch!)");
        builder.add("text.autoconfig.tide.option.minigame.doSuccessSound", "Do Success Sound");
        builder.add("text.autoconfig.tide.option.minigame.doSuccessSound.@Tooltip", "Enables the sound played when you win the minigame");
        builder.add("text.autoconfig.tide.option.minigame.doFailSound", "Do Fail Sound");
        builder.add("text.autoconfig.tide.option.minigame.doFailSound.@Tooltip", "Enables the sound played when you lose the minigame");
        builder.add("text.autoconfig.tide.option.minigame.minigameDifficulty", "Minigame Difficulty");
        builder.add("text.autoconfig.tide.option.minigame.minigameDifficulty.@Tooltip", "A multiplier that increases or decreases the speed of the minigame");

        // Items

        builder.add(TideItems.STONE_FISHING_ROD, "Stone Fishing Rod");
        builder.add(TideItems.IRON_FISHING_ROD, "Iron Fishing Rod");
        builder.add(TideItems.GOLDEN_FISHING_ROD, "Golden Fishing Rod");
        builder.add(TideItems.CRYSTAL_FISHING_ROD, "Crystal Fishing Rod");
        builder.add(TideItems.DIAMOND_FISHING_ROD, "Diamond Fishing Rod");
        builder.add(TideItems.NETHERITE_FISHING_ROD, "Netherite Fishing Rod");

        builder.add(TideItems.WHITE_FISHING_BOBBER, "White Fishing Bobber");
        builder.add(TideItems.ORANGE_FISHING_BOBBER, "Orange Fishing Bobber");
        builder.add(TideItems.MAGENTA_FISHING_BOBBER, "Magenta Fishing Bobber");
        builder.add(TideItems.LIGHT_BLUE_FISHING_BOBBER, "Light Blue Fishing Bobber");
        builder.add(TideItems.YELLOW_FISHING_BOBBER, "Yellow Fishing Bobber");
        builder.add(TideItems.LIME_FISHING_BOBBER, "Lime Fishing Bobber");
        builder.add(TideItems.PINK_FISHING_BOBBER, "Pink Fishing Bobber");
        builder.add(TideItems.GRAY_FISHING_BOBBER, "Gray Fishing Bobber");
        builder.add(TideItems.LIGHT_GRAY_FISHING_BOBBER, "Light Gray Fishing Bobber");
        builder.add(TideItems.CYAN_FISHING_BOBBER, "Cyan Fishing Bobber");
        builder.add(TideItems.PURPLE_FISHING_BOBBER, "Purple Fishing Bobber");
        builder.add(TideItems.BLUE_FISHING_BOBBER, "Blue Fishing Bobber");
        builder.add(TideItems.BROWN_FISHING_BOBBER, "Brown Fishing Bobber");
        builder.add(TideItems.GREEN_FISHING_BOBBER, "Green Fishing Bobber");
        builder.add(TideItems.RED_FISHING_BOBBER, "Red Fishing Bobber");
        builder.add(TideItems.BLACK_FISHING_BOBBER, "Black Fishing Bobber");

        builder.add(TideItems.APPLE_FISHING_BOBBER, "Apple Fishing Bobber");
        builder.add(TideItems.GOLDEN_APPLE_FISHING_BOBBER, "Golden Apple Fishing Bobber");
        builder.add(TideItems.ENCHANTED_GOLDEN_APPLE_FISHING_BOBBER, "Enchanted Golden Apple Fishing Bobber");

        builder.add(TideItems.IRON_FISHING_BOBBER, "Iron Fishing Bobber");
        builder.add(TideItems.GOLDEN_FISHING_BOBBER, "Golden Fishing Bobber");
        builder.add(TideItems.DIAMOND_FISHING_BOBBER, "Diamond Fishing Bobber");
        builder.add(TideItems.NETHERITE_FISHING_BOBBER, "Netherite Fishing Bobber");
        builder.add(TideItems.AMETHYST_FISHING_BOBBER, "Amethyst Fishing Bobber");
        builder.add(TideItems.ECHO_FISHING_BOBBER, "Echo Fishing Bobber");
        builder.add(TideItems.CHORUS_FISHING_BOBBER, "Chorus Fishing Bobber");
        builder.add(TideItems.FEATHER_FISHING_BOBBER, "Feather Fishing Bobber");
        builder.add(TideItems.LICHEN_FISHING_BOBBER, "Lichen Fishing Bobber");
        builder.add(TideItems.NAUTILUS_FISHING_BOBBER, "Nautilus Fishing Bobber");
        builder.add(TideItems.PEARL_FISHING_BOBBER, "Pearl Fishing Bobber");
        builder.add(TideItems.HEART_FISHING_BOBBER, "Heart Fishing Bobber");
        builder.add(TideItems.GRASSY_FISHING_BOBBER, "Grassy Fishing Bobber");
        builder.add(TideItems.DUCK_FISHING_BOBBER, "Duck Fishing Bobber");

        builder.add(TideItems.FISHING_HOOK, "Fishing Hook");
        builder.add(TideItems.IRON_FISHING_HOOK, "Iron Fishing Hook");
        builder.add(TideItems.LAVAPROOF_FISHING_HOOK, "Lavaproof Fishing Hook");
        builder.add(TideItems.VOID_FISHING_HOOK, "Hook of the Depths");

        builder.add(TideItems.FISHING_LINE, "Fishing Line");
        builder.add(TideItems.BRAIDED_LINE, "Braided Fishing Line");
        builder.add(TideItems.REINFORCED_LINE, "Reinforced Fishing Line");
        builder.add(TideItems.GOLDEN_LINE, "Golden Fishing Line");

        builder.add(TideItems.FISHING_JOURNAL, "Fishing Journal");
        builder.add(TideItems.FISH_SATCHEL, "Fish Satchel");
        builder.add(TideItems.FISHY_NOTE, "Fishy Note");

        builder.add(TideItems.THERMOMETER, "Thermometer");
        builder.add(TideItems.POCKET_WATCH, "Pocket Watch");

        builder.add(TideItems.ENCHANTED_POCKET_WATCH, "Enchanted Pocket Watch");
        builder.add(TideItems.STARLIGHT_BOW, "Starlight Bow");
        builder.add(TideItems.MIDAS_FISHING_ROD, "Midas Fishing Rod");
        builder.add(TideItems.DRAGONFIN_BOOTS, "Dragonfin Boots");

        builder.add(TideItems.FISH_BONE, "Fish Bone");
        builder.add(TideItems.OBSIDIAN_FRAGMENT, "Obsidian Fragment");

        builder.add(TideItems.BAIT, "Bait");
        builder.add(TideItems.LUCKY_BAIT, "Lucky Bait");
        builder.add(TideItems.MAGNETIC_BAIT, "Magnetic Bait");

        fishTranslation(TideFish.CARP, "Carp", builder);
        fishTranslation(TideFish.RAINBOW_TROUT, "Rainbow Trout", builder);
        fishTranslation(TideFish.BROOK_TROUT, "Brook Trout", builder);
        fishTranslation(TideFish.LARGEMOUTH_BASS, "Largemouth Bass", builder);
        fishTranslation(TideFish.SMALLMOUTH_BASS, "Smallmouth Bass", builder);
        fishTranslation(TideFish.YELLOW_PERCH, "Yellow Perch", builder);
        fishTranslation(TideFish.PIKE, "Pike", builder);
        fishTranslation(TideFish.CATFISH, "Catfish", builder);
        fishTranslation(TideFish.BLUEGILL, "Bluegill", builder);
        fishTranslation(TideFish.GUPPY, "Guppy", builder);
        fishTranslation(TideFish.TUNA, "Tuna", builder);
        fishTranslation(TideFish.OCEAN_PERCH, "Ocean Perch", builder);
        fishTranslation(TideFish.ANGELFISH, "Angelfish", builder);
        fishTranslation(TideFish.ANCHOVY, "Anchovy", builder);
        fishTranslation(TideFish.SAILFISH, "Sailfish", builder);
        fishTranslation(TideFish.MACKEREL, "Mackerel", builder);
        fishTranslation(TideFish.WALLEYE, "Walleye", builder);
        fishTranslation(TideFish.RED_SNAPPER, "Red Snapper", builder);
        fishTranslation(TideFish.FLOUNDER, "Flounder", builder);
        fishTranslation(TideFish.SNOOK, "Snook", builder);
        fishTranslation(TideFish.WHITE_CRAPPIE, "White Crappie", builder);
        fishTranslation(TideFish.BLACK_CRAPPIE, "Black Crappie", builder);
        fishTranslation(TideFish.MOONEYE, "Mooneye", builder);
        fishTranslation(TideFish.ARAPAIMA, "Arapaima", builder);
        fishTranslation(TideFish.SAND_TIGER_SHARK, "Sand Tiger Shark", builder);
        fishTranslation(TideFish.STURGEON, "Sturgeon", builder);
        fishTranslation(TideFish.FROSTBITE_FLOUNDER, "Frostbite Flounder", builder);
        fishTranslation(TideFish.BLOSSOM_BASS, "Blossom Bass", builder);
        fishTranslation(TideFish.MIRAGE_CATFISH, "Mirage Catfish", builder);
        fishTranslation(TideFish.ECHO_SNAPPER, "Echo Snapper", builder);
        fishTranslation(TideFish.DRIPSTONE_DARTER, "Dripstone Darter", builder);
        fishTranslation(TideFish.SLIMY_SALMON, "Slimy Salmon", builder);
        fishTranslation(TideFish.SPORE_STALKER, "Spore Stalker", builder);
        fishTranslation(TideFish.AQUATHORN, "Aquathorn", builder);
        /*? if >=1.21*/fishTranslation(TideFish.WINDBASS, "Windbass", builder);
        fishTranslation(TideFish.SWORDFISH, "Swordfish", builder);
        fishTranslation(TideFish.MANTA_RAY, "Manta Ray", builder);
        fishTranslation(TideFish.MAHI_MAHI, "Mahi-Mahi", builder);
        fishTranslation(TideFish.GREAT_WHITE_SHARK, "Great White Shark", builder);
        fishTranslation(TideFish.BULL_SHARK, "Bull Shark", builder);
        fishTranslation(TideFish.NEPTUNE_KOI, "Neptune Koi", builder);
        fishTranslation(TideFish.PLUTO_SNAIL, "Pluto Snail", builder);
        fishTranslation(TideFish.SUN_EMBLEM, "Sun Emblem", builder);
        fishTranslation(TideFish.MARSTILUS, "Marstilus", builder);
        fishTranslation(TideFish.SATURN_CUTTLEFISH, "Saturn Cuttlefish", builder);
        fishTranslation(TideFish.URANIAS_PISCES, "Urania's Pisces", builder);

        fishTranslation(TideFish.CAVE_EEL, "Cave Eel", builder);
        fishTranslation(TideFish.CRYSTAL_SHRIMP, "Crystal Shrimp", builder);
        fishTranslation(TideFish.IRON_TETRA, "Iron Tetra", builder);
        fishTranslation(TideFish.GLOWFISH, "Glowfish", builder);
        fishTranslation(TideFish.ANGLERFISH, "Angler Fish", builder);
        fishTranslation(TideFish.CAVE_CRAWLER, "Cave Crawler", builder);
        fishTranslation(TideFish.GILDED_MINNOW, "Gilded Minnow", builder);
        fishTranslation(TideFish.ABYSS_ANGLER, "Abyss Angler", builder);
        fishTranslation(TideFish.CRYSTALLINE_CARP, "Crystalline Carp", builder);
        fishTranslation(TideFish.LAPIS_LANTERNFISH, "Lapis Lanternfish", builder);
        fishTranslation(TideFish.LUMINESCENT_JELLYFISH, "Luminescent Jellyfish", builder);
        fishTranslation(TideFish.SHADOW_SNAPPER, "Shadow Snapper", builder);
        fishTranslation(TideFish.DEEP_GROUPER, "Deep Grouper", builder);
        fishTranslation(TideFish.BEDROCK_TETRA, "Bedrock Tetra", builder);
        fishTranslation(TideFish.CHASM_EEL, "Chasm Eel", builder);
        fishTranslation(TideFish.DEVILS_HOLE_PUPFISH, "Devils Hole Pupfish", builder);

        fishTranslation(TideFish.EMBER_KOI, "Ember Koi", builder);
        fishTranslation(TideFish.INFERNO_GUPPY, "Inferno Guppy", builder);
        fishTranslation(TideFish.OBSIDIAN_PIKE, "Obsidian Pike", builder);
        fishTranslation(TideFish.VOLCANO_TUNA, "Volcano Tuna", builder);

        fishTranslation(TideFish.BLAZING_SWORDFISH, "Blazing Swordfish", builder);
        fishTranslation(TideFish.MAGMA_MACKEREL, "Magma Mackerel", builder);
        fishTranslation(TideFish.WARPED_GUPPY, "Warped Guppy", builder);
        fishTranslation(TideFish.CRIMSON_FANGJAW, "Crimson Fangjaw", builder);
        fishTranslation(TideFish.SOULSCALE, "Soulscale", builder);
        fishTranslation(TideFish.WITHERFIN, "Witherfin", builder);
        fishTranslation(TideFish.ASH_PERCH, "Ash Perch", builder);

        fishTranslation(TideFish.PALE_CLUBFISH, "Pale Clubfish", builder);
        fishTranslation(TideFish.ENDERFIN, "Enderfin", builder);
        fishTranslation(TideFish.AMBER_ROCKFISH, "Amber Rockfish", builder);
        fishTranslation(TideFish.ENDER_GLIDER, "Ender Glider", builder);
        fishTranslation(TideFish.ENDERGAZER, "Endergazer", builder);
        fishTranslation(TideFish.VIOLET_CARP, "Violet Carp", builder);
        fishTranslation(TideFish.CHORUS_COD, "Chorus Cod", builder);
        fishTranslation(TideFish.RED_40, "Red 40", builder);
        fishTranslation(TideFish.DUTCHMAN_SOCK, "Flying Dutchman's Sock", builder);
        fishTranslation(TideFish.ELYTROUT, "Elytrout", builder);
        fishTranslation(TideFish.MANTYVERN, "Mantyvern", builder);
        fishTranslation(TideFish.SNATCHER_SQUID, "Snatcher Squid", builder);
        fishTranslation(TideFish.VOIDSEEKER, "Voidseeker", builder);
        fishTranslation(TideFish.DRAGON_FISH, "Dragon Fish", builder);

        fishTranslation(TideFish.INCANDESCENT_LARVA, "Incandescent Larva", builder);
        fishTranslation(TideFish.BEDROCK_BUG, "Bedrock Bug", builder);
        fishTranslation(TideFish.PENTAPUS, "Pentapus", builder);
        fishTranslation(TideFish.SLEEPY_CARP, "Sleepy Carp", builder);
        fishTranslation(TideFish.BLUE_NEONFISH, "Blue Neonfish", builder);
        fishTranslation(TideFish.JUDGMENT_FISH, "Judgment Fish", builder);
        fishTranslation(TideFish.NEPHROSILU, "Nephrosilu", builder);
        fishTranslation(TideFish.VENGEANCE, "Vengeance", builder);
        fishTranslation(TideFish.DEEP_BLUE, "Deep Blue", builder);
        fishTranslation(TideFish.DARKNESS_EATER, "Darkness Eater", builder);
        fishTranslation(TideFish.SHADOW_SHARK, "Shadow Shark", builder);
        fishTranslation(TideFish.ALPHA_FISH, "Alpha Fish", builder);

        fishTranslation(TideFish.MIDAS_FISH, "Midas Fish", builder);
        fishTranslation(TideFish.SHOOTING_STARFISH, "Shooting Starfish", builder);
        fishTranslation(TideFish.COELACANTH, "Coelacanth", builder);

        builder.add(TideItems.COOKED_FISH, "Cooked Fish");
        builder.add(TideItems.SMALL_COOKED_FISH, "Small Cooked Fish");
        builder.add(TideItems.LARGE_COOKED_FISH, "Large Cooked Fish");
        builder.add(TideItems.GRILLED_TUNA, "Grilled Tuna");

        builder.add(TideItems.FISH_SLICE, "Fish Slice");
        builder.add(TideItems.COOKED_FISH_SLICE, "Cooked Fish Slice");

        // Blocks

        builder.add(TideBlocks.WOODEN_CRATE, "Wooden Crate");
        builder.add(TideBlocks.OBSIDIAN_CRATE, "Obsidian Crate");
        builder.add(TideBlocks.PURPUR_CRATE, "Purpur Crate");

        builder.add(TideBlocks.ANGLING_TABLE, "Angling Table");
        builder.add(TideBlocks.FISH_DISPLAY, "Fish Display");

        builder.add(TideBlocks.JELLY_TORCH, "Jelly Torch");

        // Entities

        builder.add(TideEntityTypes.FISHING_BOBBER, "Fishing Bobber");
        builder.add(TideEntityTypes.STAR_ARROW, "Shooting Star");
        builder.add(TideEntityTypes.LOOT_CRATE, "Loot Crate");

        // Hover text / tooltips

        builder.add("text.tide.rod_tooltip.bait_desc", "Bait slots (right click to fill)");
        builder.add("text.tide.rod_tooltip.accessories_prefix", "Accessories:");
        builder.add("text.tide.rod_tooltip.crystal_bonus", "Plays sound when a fish bites");
        builder.add("text.tide.rod_tooltip.gold_bonus", "+1 luck");
        builder.add("text.tide.rod_tooltip.diamond_bonus", "Extra XP");
        builder.add("text.tide.rod_tooltip.netherite_bonus", "Can fish in lava");
        builder.add("text.tide.rod_tooltip.midas_bonus", "Catch additional gold items");

        builder.add("text.tide.bait_tooltip.unknown_effects", "Can be used as bait");
        builder.add("text.tide.bait_tooltip.prefix", "When used as bait:");
        builder.add("text.tide.bait_tooltip.speed", "%s fishing speed");
        builder.add("text.tide.bait_tooltip.lucky", "%s fishing luck");
        builder.add("text.tide.bait_tooltip.crate", "%s crate chance");

        builder.add("text.tide.accessory_tooltip.prefix", "When applied:");

        builder.add("text.tide.fish.alive", "Bucketable");

        builder.add("item.tide.fish_satchel.desc_0", "Right-click while held to open");
        builder.add("item.tide.fish_satchel.desc_1", "Collects fish automatically while open");
        builder.add("item.tide.jelly_torch.desc", "Can be placed underwater");
        builder.add("item.tide.chasm_eel.desc", "Use on a bedrock block to eat it");
        builder.add("item.tide.thermometer.desc", "Reads biome temperature");
        builder.add("item.tide.pocket_watch.desc", "Reads precise time");
        builder.add("item.tide.enchanted_pocket_watch.desc", "Use on a mob to freeze the mob in time");
        builder.add("item.tide.voidseeker.desc", "Sends you to your respawn point when eaten");
        builder.add("item.tide.starlight_bow.desc_0", "Transforms arrows into shooting stars");
        builder.add("item.tide.starlight_bow.desc_1", "60% chance not to consume ammo");
        builder.add("item.tide.dragonfin_boots.desc_0", "Grants a single midair jump");
        builder.add("item.tide.dragonfin_boots.desc_1", "The jump is restored upon touching the ground");

        builder.add("item.tide.braided_line.desc", "Increases rod charge speed");
        builder.add("item.tide.reinforced_line.desc", "Increases rod durability");
        builder.add("item.tide.golden_line.desc", "Increases fishing luck");

        builder.add("item.tide.iron_hook.desc", "-15% catch difficulty");
        builder.add("item.tide.lavaproof_hook.desc", "Allows fishing in lava");
        builder.add("item.tide.void_hook.desc", "Allows fishing in the void");

        // Advancements

        builder.add("advancements.tide.root.title", "Tide");
        builder.add("advancements.tide.root.description", "Gotta catch 'em all");

        builder.add("advancements.tide.get_bait.title", "Oldest Trick in the Book");
        builder.add("advancements.tide.get_bait.description", "Obtain fish bait");

        builder.add("advancements.tide.pull_crate.title", "That's No Fish!");
        builder.add("advancements.tide.pull_crate.description", "Reel in a crate while fishing");

        builder.add("advancements.tide.fish_in_lava.title", "Hot Rod");
        builder.add("advancements.tide.fish_in_lava.description", "Fish in lava");

        builder.add("advancements.tide.fish_in_void.title", "Into the A-fish");
        builder.add("advancements.tide.fish_in_void.description", "Fish in the void");

        builder.add("advancements.tide.catch_legendary.title", "One in a Million");
        builder.add("advancements.tide.catch_legendary.description", "Catch a legendary fish");

        builder.add("advancements.tide.release_pupfish.title", "Preservation Expert");
        builder.add("advancements.tide.release_pupfish.description", "Release a Devils Hole Pupfish back into the wild");

        builder.add("advancements.tide.freeze_coelacanth.title", "Taste of its Own Medicine");
        builder.add("advancements.tide.freeze_coelacanth.description", "Freeze a Coelacanth with an Enchanted Pocket Watch");

        builder.add("advancements.tide.catch_midas_with_midas_rod.title", "Full Circle");
        builder.add("advancements.tide.catch_midas_with_midas_rod.description", "Catch a Midas Fish with a Midas Fishing Rod");

        builder.add("advancements.tide.all_fishing_rods.title", "Hooked on Variety");
        builder.add("advancements.tide.all_fishing_rods.description", "Obtain every type of fishing rod");

        builder.add("advancements.tide.journal_halfway_completed.title", "Aquatic Archivist");
        builder.add("advancements.tide.journal_halfway_completed.description", "Complete half of the fishing journal");

        builder.add("advancements.tide.journal_completed.title", "A Reel Completionist");
        builder.add("advancements.tide.journal_completed.description", "Complete the fishing journal by catching every fish");

        // Fishing Journal

        builder.add("journal.remove_from_names", "Raw ");

        builder.add("journal.description.missing", "No info");
        builder.add("journal.description.minecraft.salmon", "This strong and resilient fish is a familiar face for all anglers (and a tasty snack).");
        builder.add("journal.description.tide.rainbow_trout", "With their vibrant colors, these fish are common sights in cool rivers and streams.");
        builder.add("journal.description.tide.brook_trout", "Smaller and more secretive than other trout, the brook trout favors shaded streams and mossy pools.");
        builder.add("journal.description.tide.largemouth_bass", "A bold and aggressive predator. This lake-dwelling fish is a widely-accepted favorite.");
        builder.add("journal.description.tide.smallmouth_bass", "A lookalike to their large-mouthed cousins, these fish prefer slightly cooler and calmer waters.");
        builder.add("journal.description.tide.white_crappie", "This light-colored fish favors slower waters and is a common recreational catch.");
        builder.add("journal.description.tide.black_crappie", "Dark and patterned, the black crappie thrives in clear lakes and puts up a fight.");
        builder.add("journal.description.tide.yellow_perch", "This bright yellow-striped fish is as eye-catching as it is abundant.");
        builder.add("journal.description.tide.pike", "Long, lean, and carnivorous, these fish rule small lakes and ambush prey with their speed.");
        builder.add("journal.description.tide.carp", "This bottom-feeder thrives in murky waters and often stirs up a mess in its search for food.");
        builder.add("journal.description.tide.catfish", "This fish has whiskered jaws and a taste for the night. Rumors say it fears the presence of wolves.");
        builder.add("journal.description.tide.guppy", "This fish is tiny, colorful, and endlessly active. It thrives in warm and tropical waters.");
        builder.add("journal.description.tide.bluegill", "A classic catch in calm ponds and lakes of any region. They're always hungry for some bait.");
        builder.add("journal.description.tide.walleye", "Lurking in the chilly depths, this sharp-eyed fish is prized by many anglers.");
        builder.add("journal.description.tide.blossom_bass", "A bright and colorful catch among the cherry blossoms. They radiate with a calming power.");
        builder.add("journal.description.tide.frostbite_flounder", "A flounder that seems to be frozen in place. They lurk on chilly seafloors.");
        builder.add("journal.description.tide.mirage_catfish", "Found under extreme heat, this catfish has confused travelers for many years.");
        builder.add("journal.description.tide.slimy_salmon", "A bouncy shell of a fish made out of slime. Its covering allows it to withstand almost anything.");
        builder.add("journal.description.tide.spore_stalker", "This fungal fiend flocks to nearby mushrooms. It's possible that it may have been a fish at some point.");
        builder.add("journal.description.tide.arapaima", "This giant creature glides through warm jungle rivers with strength and speed.");
        builder.add("journal.description.tide.sand_tiger_shark", "With jagged teeth and a menacing appearance, they search the desert for prey.");
        builder.add("journal.description.tide.sturgeon", "A bottom-feeding fish with tough scales and a long snout. They cruise the depths of cold rivers and lakes.");
        builder.add("journal.description.tide.mooneye", "Few have spotted this fish in the wild. They thrive in cool waters yet remain hidden from sight.");
        builder.add("journal.description.tide.bull_shark", "A powerful swimmer with a short temper, notably able to travel upriver to freshwater environments.");

        builder.add("journal.description.minecraft.cod", "A staple of colder seas. These fish are known for their calm presence under the waves.");
        builder.add("journal.description.minecraft.tropical_fish", "One of the most diverse and vibrant fish in the sea, with over 3,000 recorded varieties.");
        builder.add("journal.description.minecraft.pufferfish", "Their survival tactics are quite simple. If anyone gets close, blow up.");
        builder.add("journal.description.tide.tuna", "Built for speed and endurance, this open-ocean predator can travel vast distances.");
        builder.add("journal.description.tide.ocean_perch", "This perch is often found schooling near rocky seafloors and cold ocean depths.");
        builder.add("journal.description.tide.mackerel", "Fast-moving, tightly schooling, and a common sight in coastal waters and open seas.");
        builder.add("journal.description.tide.snook", "This fish is a common sight in coastal waters, being able to tolerate many different habitats.");
        builder.add("journal.description.tide.angelfish", "Brightly colored and graceful, often found elegantly gliding through coral reefs.");
        builder.add("journal.description.tide.red_snapper", "Known for its vibrant red hue, it inhabits rocky bottoms in coastal waters.");
        builder.add("journal.description.tide.flounder", "These uniquely-shaped fish rest flat against sandy or muddy sea floors, staying hidden from predators.");
        builder.add("journal.description.tide.anchovy", "These tiny fish move in large schools that swirl through oceans, creating dynamic patterns.");
        builder.add("journal.description.tide.mahi_mahi", "These long fish have dazzling colors and a blunt, rounded head.");
        builder.add("journal.description.tide.sailfish", "Known for its impressive dorsal fin, it speeds through open oceans hunting fish with quick strikes.");
        builder.add("journal.description.tide.swordfish", "A powerful predator with a long, flat bill used to slash prey. It cruises deep waters in search of food.");
        builder.add("journal.description.tide.manta_ray", "Its wide fins carry it gracefully through open water near the surface of warm seas.");
        builder.add("journal.description.tide.aquathorn", "A much less aggressive (and smaller) ancestor of the guardians of undersea monuments.");
        builder.add("journal.description.tide.neptune_koi", "This fish has beautiful glowing rings around its body. Rumors say that it protects waters from evil spirits.");
        builder.add("journal.description.tide.pluto_snail", "Crawling at the bottom of the ocean, this colorful snail is much less sluggish than its relatives.");
        builder.add("journal.description.tide.sun_emblem", "A luminous jellyfish with a spiky shell to protect itself from harm.");
        builder.add("journal.description.tide.saturn_cuttlefish", "This pale-colored cuttlefish has a distinctive ring orbiting its head.");
        builder.add("journal.description.tide.marstilus", "This dusty nautilus-like creature's qualities are unlike anything found in the overworld.");
        builder.add("journal.description.tide.uranias_pisces", "Many anglers believe that this fish is from heaven itself. It's said to bring good fortune to whomever catches it.");
        builder.add("journal.description.tide.great_white_shark", "A feared and respected predator, patrolling coastal waters with keen senses and bursts of speed.");
        builder.add("journal.description.tide.shooting_starfish", "Some anglers talk about fish that fall from the sky. Looks like they were onto something.");
        builder.add("journal.description.tide.coelacanth", "A living fossil, thought to be extinct for millions of years. They lurk in dark caves to avoid light.");

        builder.add("journal.description.tide.cave_eel", "These little cave noodles can fit between thin gaps in rocky terrain to search for food.");
        builder.add("journal.description.tide.crystal_shrimp", "The origins of this specimen are unknown. It sparkles and shines as it drifts through aquifers.");
        builder.add("journal.description.tide.iron_tetra", "This fish lurks around ore veins building up a metallic frame, keeping it safe from most predators.");
        builder.add("journal.description.tide.glowfish", "A bioluminescent fish that lives peacefully in the depths. They're said to be distant relatives of squids.");
        builder.add("journal.description.tide.anglerfish", "The bright lure on this fish's head attracts food, allowing them to conserve energy while they hunt.");
        builder.add("journal.description.tide.cave_crawler", "This unique quadruped is quick on its feet and can scale any surface.");
        builder.add("journal.description.tide.gilded_minnow", "This fish is said to have been the victim of a much more powerful creature, turning its scales golden.");
        builder.add("journal.description.tide.abyss_angler", "Its lure shines especially bright, allowing it to thrive even deeper than its cousins.");
        builder.add("journal.description.tide.crystalline_carp", "The makeup of the crystals surrounding this mysterious fish's scales is unknown.");
        builder.add("journal.description.tide.lapis_lanternfish", "This tiny lapis lazuli fish possesses shocking amounts of strength and speed.");
        builder.add("journal.description.tide.dripstone_darter", "Is it a stalagmite, or a stalactite? Either way, its pointy nose is useful for hunting.");
        builder.add("journal.description.tide.luminescent_jellyfish", "This peaceful jellyfish's bright light helps guide other fish living in the depths.");
        builder.add("journal.description.tide.shadow_snapper", "A stealthy predator of the deep that strikes swiftly with its razor-sharp teeth.");
        builder.add("journal.description.tide.deep_grouper", "Its pale, dark reddish-black scales give this fish an otherworldly look.");
        builder.add("journal.description.tide.bedrock_tetra", "This tiny fish is concealed beneath a solid layer of bedrock, rendering it impervious to predators.");
        builder.add("journal.description.tide.chasm_eel", "This gulper eel is incredibly resilient. It feasts on pieces of bedrock lying in the deepest depths.");
        builder.add("journal.description.tide.echo_snapper", "Some sculk surrounding a lifeless fish bone. Even so, it feels like it's listening for something.");
        builder.add("journal.description.tide.windbass", "This fish possesses incredible speed, powered by its ability to create wind currents beneath the water.");
        builder.add("journal.description.tide.devils_hole_pupfish", "Tiny and iridescent, it hides in one of the most isolated and extreme aquatic habitats.");
        builder.add("journal.description.tide.midas_fish", "A fish blessed with the ability to turn things into gold. Unfortunately for the fish, it can no longer eat its prey.");

        builder.add("journal.description.tide.magma_mackerel", "With heat resistant scales and powerful fins, this tiny fish navigates lava pools with ease.");
        builder.add("journal.description.tide.ember_koi", "These fish are at the bottom of the food chain, but can use the lava itself to create more of them.");
        builder.add("journal.description.tide.ash_perch", "This fish lurks in the depths of lava lakes, traveling quickly and blending seamlessly with its fiery surroundings.");
        builder.add("journal.description.tide.obsidian_pike", "Researchers can't figure out how this pike can even move its fins with its rock-hard composition.");
        builder.add("journal.description.tide.volcano_tuna", "This fish is able to live for up to 600 years before becoming enveloped in obsidian and sinking to the floor.");
        builder.add("journal.description.tide.inferno_guppy", "This tiny fish absorbs large amounts of energy from lava, completely eliminating the need for food.");
        builder.add("journal.description.tide.warped_guppy", "Protected by heat-resistant hyphae, this fungal fellow travels through lava oceans far beyond its home biome.");
        builder.add("journal.description.tide.crimson_fangjaw", "This creature evolved to be a powerful hunter in its hostile home. Its diet seems to consist of...pork?");
        builder.add("journal.description.tide.soulscale", "This fish is woven out of strands of soul soil possessed by a ghostly spirit.");
        builder.add("journal.description.tide.witherfin", "Once a thriving creature, it now holds a deadly infection that is passed to those who touch it.");
        builder.add("journal.description.tide.blazing_swordfish", "This swordfish has shimmering scales, a razor-sharp nose, and can be used as a deadly weapon.");

        builder.add("journal.description.tide.incandescent_larva", "Its transparent skin helps it glow with its own body heat. A tasty snack for many fish.");
        builder.add("journal.description.tide.bedrock_bug", "Its teeth can penetrate bedrock, allowing them to build nests in the depths of the overworld.");
        builder.add("journal.description.tide.sleepy_carp", "A lazy, passive fish that spends most of its time sitting in one spot.");
        builder.add("journal.description.tide.blue_neonfish", "This fish has a set of dark, jagged teeth that bite through even the toughest scales.");
        builder.add("journal.description.tide.judgment_fish", "Its big eyes allow it to see predators clearly and make a move before they get close.");
        builder.add("journal.description.tide.deep_blue", "This fish's tough skin and versatile diet allow it to survive much longer than other fish.");
        builder.add("journal.description.tide.nephrosilu", "This deformed abomination is an apex predator, with razor-sharp fins and a bright purple eye.");
        builder.add("journal.description.tide.vengeance", "Is it a ghost? Is it a fish? Regardless, it's highly territorial and aggressive towards anything.");
        builder.add("journal.description.tide.pentapus", "This slimy, translucent purple creature has five legs. Is its center body part an eye or a brain?");
        builder.add("journal.description.tide.darkness_eater", "Legend says that this massive catfish can swallow five iron golems with one bite.");
        builder.add("journal.description.tide.shadow_shark", "This spooky predator possesses absurdly sharp teeth but relatively weak skin.");
        builder.add("journal.description.tide.alpha_fish", "Driven to near extinction by other fish, this familiar-looking species wanders the depths of the overworld.");

        builder.add("journal.description.tide.pale_clubfish", "These oddly shaped fish are surprisingly common and are a tasty snack for predators and anglers.");
        builder.add("journal.description.tide.amber_rockfish", "This fish is literally made out of rock, with bright orange moss covering its body.");
        builder.add("journal.description.tide.enderfin", "This fish's metallic skin is covered in a deep blue hue, giving it a pearl-like appearance.");
        builder.add("journal.description.tide.chorus_cod", "This fish seems to have formed a strange relationship with a chorus flower, providing it with free food.");
        builder.add("journal.description.tide.ender_glider", "A winged alien fish, able to traverse the end much more quickly than the rest.");
        builder.add("journal.description.tide.endergazer", "This fish has a large eye that makes up its head. In addition to providing sight, the eye also serves as a brain.");
        builder.add("journal.description.tide.violet_carp", "Many have debated where this carp gets its unique colors from. Still, no one knows.");
        builder.add("journal.description.tide.red_40", "This eyeless insect is surprisingly quick, moving through either water or the void with ease.");
        builder.add("journal.description.tide.dutchman_sock", "This species of bacteria grows so large that it's often mistaken for a fish (or a sock).");
        builder.add("journal.description.tide.elytrout", "An elegant bug-like creature that resembles the same elytra found in ancient end cities.");
        builder.add("journal.description.tide.mantyvern", "This surprisingly docile creature creates signals with its antennae for communication.");
        builder.add("journal.description.tide.snatcher_squid", "This squid routinely travels to the pressurized death zone of the void, dragging its prey with it.");
        builder.add("journal.description.tide.voidseeker", "Not much is known about the voidseeker, except that they hop between dimensions to avoid getting caught.");
        builder.add("journal.description.tide.dragon_fish", "Legend speaks of a player who submerged a dragon egg in water for several years, eventually creating this.");

        // Profile info

        builder.add("journal.rarity.title", "Rarity");
        builder.add("journal.rarity.common", "Common");
        builder.add("journal.rarity.uncommon", "Uncommon");
        builder.add("journal.rarity.rare", "Rare");
        builder.add("journal.rarity.very_rare", "Very Rare");
        builder.add("journal.rarity.legendary", "Legendary");

        builder.add("journal.info.location.title", "Location");
        builder.add("journal.info.location.freshwater", "Lakes and Rivers");
        builder.add("journal.info.location.saltwater", "Oceans");
        builder.add("journal.info.location.saltwater_warm", "Warm Oceans");
        builder.add("journal.info.location.coast", "Coast");
        builder.add("journal.info.location.underground", "Underground");
        builder.add("journal.info.location.deep_ocean", "Deep Oceans");
        builder.add("journal.info.location.river", "Rivers");
        builder.add("journal.info.location.jungle", "Jungle");
        builder.add("journal.info.location.swamp", "Swamp");
        builder.add("journal.info.location.desert", "Desert");
        builder.add("journal.info.location.frozen", "Frozen Biomes");
        builder.add("journal.info.location.cherry_grove", "Cherry Grove");
        builder.add("journal.info.location.mushroom_island", "Mushroom Island");
        builder.add("journal.info.location.dripstone_caves", "Dripstone Caves");
        builder.add("journal.info.location.deep_dark", "Deep Dark");
        builder.add("journal.info.location.trial_chambers", "Trial Chambers");
        builder.add("journal.info.location.ocean_monument", "Ocean Monument");
        builder.add("journal.info.location.monuments_and_outposts", "Monuments and Outposts");
        builder.add("journal.info.location.shipwrecks", "Shipwrecks");
        builder.add("journal.info.location.thunderstorms", "Thunderstorms");
        builder.add("journal.info.location.void", "The Void");
        builder.add("journal.info.location.lava", "Lava");

        builder.add("journal.info.climate.title", "Climate");
        builder.add("journal.info.time.title", "Time of Day");
        builder.add("journal.info.depth.title", "Depth");
        builder.add("journal.info.luck.title", "Luck Requirement");
        builder.add("journal.info.moon_phase.title", "Moon Phases");
        builder.add("journal.info.moon_phase.0", "Full Moon");
        builder.add("journal.info.moon_phase.1", "Waning Gibbous");
        builder.add("journal.info.moon_phase.2", "Third Quarter");
        builder.add("journal.info.moon_phase.3", "Waning Crescent");
        builder.add("journal.info.moon_phase.4", "New Moon");
        builder.add("journal.info.moon_phase.5", "Waxing Crescent");
        builder.add("journal.info.moon_phase.6", "First Quarter");
        builder.add("journal.info.moon_phase.7", "Waxing Gibbous");
        builder.add("journal.info.seasons.title", "Seasons");
        builder.add("journal.info.seasons.spring", "Spring");
        builder.add("journal.info.seasons.summer", "Summer");
        builder.add("journal.info.seasons.fall", "Fall");
        builder.add("journal.info.seasons.winter", "Winter");
        builder.add("journal.info.dimensions.title", "Dimensions");
        builder.add("journal.info.dimensions.overworld", "Overworld");
        builder.add("journal.info.dimensions.the_nether", "The Nether");
        builder.add("journal.info.dimensions.the_end", "The End");
        builder.add("journal.info.weather.title", "Weather");
        builder.add("journal.info.weather.clear", "Clear");
        builder.add("journal.info.weather.rain", "Rain");
        builder.add("journal.info.weather.storm", "Thunderstorm");

        builder.add("journal.info.stats.total", "Total caught: %s");
        builder.add("journal.info.stats.first", "First catch: %s");
        builder.add("journal.info.stats.day", "Day %s");
        builder.add("journal.info.stats.largest", "Largest catch: %s");
        builder.add("journal.info.stats.smallest", "Smallest catch: %s");

        // Accessories

        builder.add("accessory.item.tide.red_fishing_bobber", "Red Bobber");
        builder.add("accessory.item.tide.orange_fishing_bobber", "Orange Bobber");
        builder.add("accessory.item.tide.yellow_fishing_bobber", "Yellow Bobber");
        builder.add("accessory.item.tide.lime_fishing_bobber", "Lime Bobber");
        builder.add("accessory.item.tide.green_fishing_bobber", "Green Bobber");
        builder.add("accessory.item.tide.light_blue_fishing_bobber", "Light Blue Bobber");
        builder.add("accessory.item.tide.cyan_fishing_bobber", "Cyan Bobber");
        builder.add("accessory.item.tide.blue_fishing_bobber", "Blue Bobber");
        builder.add("accessory.item.tide.purple_fishing_bobber", "Purple Bobber");
        builder.add("accessory.item.tide.magenta_fishing_bobber", "Magenta Bobber");
        builder.add("accessory.item.tide.pink_fishing_bobber", "Pink Bobber");
        builder.add("accessory.item.tide.white_fishing_bobber", "White Bobber");
        builder.add("accessory.item.tide.light_gray_fishing_bobber", "Light Gray Bobber");
        builder.add("accessory.item.tide.gray_fishing_bobber", "Gray Bobber");
        builder.add("accessory.item.tide.black_fishing_bobber", "Black Bobber");
        builder.add("accessory.item.tide.brown_fishing_bobber", "Brown Bobber");
        builder.add("accessory.item.tide.apple_fishing_bobber", "Apple Bobber");
        builder.add("accessory.item.tide.golden_apple_fishing_bobber", "Golden Apple Bobber");
        builder.add("accessory.item.tide.enchanted_golden_apple_fishing_bobber", "Enchanted Apple Bobber");
        builder.add("accessory.item.tide.iron_fishing_bobber", "Iron Bobber");
        builder.add("accessory.item.tide.golden_fishing_bobber", "Golden Bobber");
        builder.add("accessory.item.tide.diamond_fishing_bobber", "Diamond Bobber");
        builder.add("accessory.item.tide.netherite_fishing_bobber", "Netherite Bobber");
        builder.add("accessory.item.tide.amethyst_fishing_bobber", "Amethyst Bobber");
        builder.add("accessory.item.tide.echo_fishing_bobber", "Echo Bobber");
        builder.add("accessory.item.tide.chorus_fishing_bobber", "Chorus Bobber");
        builder.add("accessory.item.tide.feather_fishing_bobber", "Feather Bobber");
        builder.add("accessory.item.tide.lichen_fishing_bobber", "Lichen Bobber");
        builder.add("accessory.item.tide.nautilus_fishing_bobber", "Nautilus Bobber");
        builder.add("accessory.item.tide.pearl_fishing_bobber", "Pearl Bobber");
        builder.add("accessory.item.tide.heart_fishing_bobber", "Heart Bobber");
        builder.add("accessory.item.tide.grassy_fishing_bobber", "Grassy Bobber");
        builder.add("accessory.item.tide.duck_fishing_bobber", "Duck Bobber");

        builder.add("accessory.item.tide.fishing_hook", "Normal Hook");
        builder.add("accessory.item.tide.iron_fishing_hook", "Iron Hook");
        builder.add("accessory.item.tide.lavaproof_fishing_hook", "Lavaproof Hook");
        builder.add("accessory.item.tide.void_fishing_hook", "Hook of the Depths");

        builder.add("accessory.item.tide.fishing_line", "Normal Line");
        builder.add("accessory.item.tide.braided_line", "Braided Line");
        builder.add("accessory.item.tide.reinforced_line", "Reinforced Line");
        builder.add("accessory.item.tide.golden_line", "Golden Line");

        builder.add("accessory.item.hybrid-aquatic.barbed_hook", "Barbed Hook");
        builder.add("accessory.item.hybrid-aquatic.glowing_hook", "Glowing Hook");
        builder.add("accessory.item.hybrid-aquatic.magnetic_hook", "Magnetic Hook");
        builder.add("accessory.item.hybrid-aquatic.creepermagnet_hook", "CreeperMagnet Hook");
        builder.add("accessory.item.hybrid-aquatic.ominous_hook", "Ominous Hook");

        // Command responses

        builder.add("commands.journal.lock", "Locked all fish for %s");
        builder.add("commands.journal.unlock", "Unlocked fish \"%s\" for %s");
        builder.add("commands.journal.unlock.fail", "\"%s\" is not in the fishing journal");
        builder.add("commands.journal.unlock.already_unlocked", "Fish \"%s\" is already unlocked");
        builder.add("commands.journal.unlock_all", "Unlocked all fish for %s");
        builder.add("commands.fishing.no_hook", "You must cast a fishing rod to use this command");
        builder.add("commands.fishing.nothing_found", "No fishing results were found for this context");
        builder.add("commands.fishing.results", "Fishing Results:");
        builder.add("commands.fishing.entries.unspecified", "Unspecified Entry");
        builder.add("commands.fishing.entries.fish_selector", "Fish Selector");
        builder.add("commands.fishing.entries.crate_selector", "Crate Selector");
        builder.add("commands.fishing.entries.loot_table", "Loot Table");
        builder.add("commands.fishing.entries.crate", "Crate");
        builder.add("commands.fishing.entries.crate.unknown", "Unknown Crate");
        builder.add("commands.fishing.entries.fish", "Fish");

        // Minigame

        builder.add("minigame.perfect", "Perfect Catch!");
        builder.add("minigame.catch", "Good");
        builder.add("minigame.miss", "Miss");

        // Container titles

        builder.add("container.tide.loot_crate", "Crate");
        builder.add("container.tide.angling_table", "Upgrade Rod");

        // Sounds

        builder.add("subtitles.tide.journal_open", "Fishing Journal opens");
        builder.add("subtitles.tide.journal_close", "Fishing Journal closes");
        builder.add("subtitles.tide.page_flip", "Page rustles");
        builder.add("subtitles.item.fishing_rod.reel", "Fishing Rod reels");

        // Toasts

        builder.add("newpage.toast.title", "New Journal Page");
        builder.add("newfish.toast.title", "New Fish Discovered");

        // Misc

        builder.add("itemGroup.tide.main", "Tide");
    }

    private void fishTranslation(Item item, String translation, TranslationBuilder builder) {
        String key = BuiltInRegistries.ITEM.getKey(item).getPath();
        builder.add(item, translation);
        builder.add("entity.tide." + key, translation);
        builder.add("item.tide." + key + "_bucket", translation + " Bucket");
        builder.add("item.tide." + key + "_spawn_egg", translation + " Spawn Egg");
    }
}
//?}