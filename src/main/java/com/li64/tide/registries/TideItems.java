package com.li64.tide.registries;

import com.li64.tide.Tide;
import com.li64.tide.config.TideServerConfig;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.item.TideItemData;
import com.li64.tide.registries.blocks.JellyTorchBlockItem;
import com.li64.tide.registries.items.*;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;

import java.util.*;
import java.util.function.Function;

//? if >=1.21 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.BundleContents;
//?}

public class TideItems {
    public static final HashMap<ResourceKey<Item>, Item> ITEMS = new HashMap<>();
    private static final ArrayList<ItemStack> DISPLAY_ITEMS = new ArrayList<>();

    public static final Item STONE_FISHING_ROD = register("stone_fishing_rod", properties -> new TideFishingRodItem(1, 48, properties));
    public static final Item IRON_FISHING_ROD = register("iron_fishing_rod", properties -> new TideFishingRodItem(1, 64, properties));
    public static final Item GOLDEN_FISHING_ROD = register("golden_fishing_rod", properties -> new TideFishingRodItem(2, 36, properties));
    public static final Item CRYSTAL_FISHING_ROD = register("crystal_fishing_rod", properties -> new TideFishingRodItem(2, 80, properties));
    public static final Item DIAMOND_FISHING_ROD = register("diamond_fishing_rod", properties -> new TideFishingRodItem(2, 128, properties));
    public static final Item NETHERITE_FISHING_ROD = register("netherite_fishing_rod", properties -> new TideFishingRodItem(2, 512, properties), new Item.Properties().fireResistant());
    public static final Item ECHO_FISHING_ROD = register("echo_fishing_rod", properties -> new TideFishingRodItem(3, 256, properties));
    public static final Item PRISMARINE_FISHING_ROD = register("prismarine_fishing_rod", properties -> new TideFishingRodItem(3, 256, properties));
    public static final Item SUNFLOWER_FISHING_ROD = register("sunflower_fishing_rod", properties -> new TideFishingRodItem(6, 256, properties));
    public static final Item VILLAGE_FISHING_ROD = register("village_fishing_rod", properties -> new TideFishingRodItem(4, 256, properties));
    public static final Item BLAZING_FISHING_ROD = register("blazing_fishing_rod", properties -> new TideFishingRodItem(4, 256, properties));
    public static final Item HONEYCOMB_FISHING_ROD = register("honeycomb_fishing_rod", properties -> new TideFishingRodItem(5, 256, properties));
    public static final Item MIDAS_FISHING_ROD = register("midas_fishing_rod", properties -> new TideFishingRodItem(3, 256, properties));

    public static final Item BAIT = register("bait", Item::new);
    public static final Item LUCKY_BAIT = register("lucky_bait", Item::new);
    public static final Item MAGNETIC_BAIT = register("magnetic_bait", Item::new);
    public static final Item INCANDESCENT_BAIT = register("incandescent_bait", Item::new);
    public static final Item ABYSS_BAIT = register("abyss_bait", Item::new);

    public static final Item RED_BOBBER = register("red_bobber", FishingBobberItem::new);
    public static final Item ORANGE_BOBBER = register("orange_bobber", FishingBobberItem::new);
    public static final Item YELLOW_BOBBER = register("yellow_bobber", FishingBobberItem::new);
    public static final Item LIME_BOBBER = register("lime_bobber", FishingBobberItem::new);
    public static final Item GREEN_BOBBER = register("green_bobber", FishingBobberItem::new);
    public static final Item CYAN_BOBBER = register("cyan_bobber", FishingBobberItem::new);
    public static final Item LIGHT_BLUE_BOBBER = register("light_blue_bobber", FishingBobberItem::new);
    public static final Item BLUE_BOBBER = register("blue_bobber", FishingBobberItem::new);
    public static final Item PURPLE_BOBBER = register("purple_bobber", FishingBobberItem::new);
    public static final Item MAGENTA_BOBBER = register("magenta_bobber", FishingBobberItem::new);
    public static final Item PINK_BOBBER = register("pink_bobber", FishingBobberItem::new);
    public static final Item WHITE_BOBBER = register("white_bobber", FishingBobberItem::new);
    public static final Item LIGHT_GRAY_BOBBER = register("light_gray_bobber", FishingBobberItem::new);
    public static final Item GRAY_BOBBER = register("gray_bobber", FishingBobberItem::new);
    public static final Item BLACK_BOBBER = register("black_bobber", FishingBobberItem::new);
    public static final Item BROWN_BOBBER = register("brown_bobber", FishingBobberItem::new);
    public static final Item APPLE_BOBBER = register("apple_bobber", FishingBobberItem::new, new Item.Properties().food(Foods.APPLE));
    public static final Item GOLDEN_APPLE_BOBBER = register("golden_apple_bobber", FishingBobberItem::new, new Item.Properties().food(Foods.GOLDEN_APPLE));
    public static final Item ENCHANTED_GOLDEN_APPLE_BOBBER = register("enchanted_golden_apple_bobber", EnchantedFishingBobberItem::new, new Item.Properties()
            /*? if >=1.21*/.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
            .food(Foods.ENCHANTED_GOLDEN_APPLE));
    public static final Item IRON_BOBBER = register("iron_bobber", FishingBobberItem::new);
    public static final Item GOLDEN_BOBBER = register("golden_bobber", FishingBobberItem::new);
    public static final Item DIAMOND_BOBBER = register("diamond_bobber", FishingBobberItem::new);
    public static final Item NETHERITE_BOBBER = register("netherite_bobber", FishingBobberItem::new, new Item.Properties().fireResistant());
    public static final Item AMETHYST_BOBBER = register("amethyst_bobber", FishingBobberItem::new);
    public static final Item ECHO_BOBBER = register("echo_bobber", FishingBobberItem::new);
    public static final Item CHORUS_BOBBER = register("chorus_bobber", FishingBobberItem::new);
    public static final Item FEATHER_BOBBER = register("feather_bobber", FishingBobberItem::new);
    public static final Item LICHEN_BOBBER = register("lichen_bobber", FishingBobberItem::new);
    public static final Item NAUTILUS_BOBBER = register("nautilus_bobber", FishingBobberItem::new);
    public static final Item PEARL_BOBBER = register("pearl_bobber", FishingBobberItem::new);
    public static final Item HEART_BOBBER = register("heart_bobber", FishingBobberItem::new);
    public static final Item GRASSY_BOBBER = register("grassy_bobber", FishingBobberItem::new);
    public static final Item DUCK_BOBBER = register("duck_bobber", FishingBobberItem::new);

    public static final Item FISHING_HOOK = register("fishing_hook", FishingHookItem::new);
    public static final Item FIERY_HOOK = register("fiery_hook", properties -> new FishingHookItem(properties, "item.tide.fiery_hook.desc"));
    public static final Item PERMAFROST_HOOK = register("permafrost_hook", properties -> new FishingHookItem(properties, "item.tide.permafrost_hook.desc"));
    public static final Item TWILIGHT_HOOK = register("twilight_hook", properties -> new FishingHookItem(properties, "item.tide.twilight_hook.desc"));
    public static final Item LAVAPROOF_HOOK = register("lavaproof_hook", properties -> new FishingHookItem(properties, "item.tide.lavaproof_hook.desc"));
    public static final Item VOID_HOOK = register("void_hook", properties -> new FishingHookItem(properties, "item.tide.void_hook.desc"));

    public static final Item FISHING_LINE = register("fishing_line", FishingLineItem::new);
    public static final Item COPPER_LINE = register("copper_line", properties -> new FishingLineItem(properties, "item.tide.copper_line.desc"));
    public static final Item IRON_LINE = register("iron_line", properties -> new FishingLineItem(properties, "item.tide.iron_line.desc"));
    public static final Item GOLDEN_LINE = register("golden_line", GoldenLineItem::new);
    public static final Item DIAMOND_LINE = register("diamond_line", properties -> new FishingLineItem(properties, "item.tide.diamond_line.desc"));

    public static final Item FISHING_JOURNAL = register("fishing_journal", FishingJournalItem::new);
    public static final Item FISHY_NOTE = register("fishy_note", FishyNoteItem::new, new Item.Properties().stacksTo(1));
    public static final Item FISH_SATCHEL = register("fish_satchel", FishSatchelItem::new, new Item.Properties()
            /*? if >=1.21*/.component(DataComponents.BUNDLE_CONTENTS, new BundleContents(List.of()))
            .stacksTo(1));

    public static final Item POCKET_WATCH = register("pocket_watch", PocketWatchItem::new);
    public static final Item LUNAR_CALENDAR = register("lunar_calendar", LunarCalendarItem::new);
    public static final Item CLIMATE_GAUGE = register("climate_gauge", ClimateGaugeItem::new);
    public static final Item DEPTH_METER = register("depth_meter", DepthMeterItem::new);
    public static final Item WEATHER_RADIO = register("weather_radio", WeatherRadioItem::new);
    public static final Item FISH_FINDER = register("fish_finder", FishFinderItem::new, new Item.Properties().rarity(Rarity.RARE));

    public static final Item ENCHANTED_POCKET_WATCH = register("enchanted_pocket_watch", EnchantedPocketWatchItem::new, new Item.Properties()
            /*? if >=1.21*/.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
            .rarity(Rarity.EPIC));
    public static final Item STARLIGHT_BOW = register("starlight_bow", StarlightBowItem::new,
            new Item.Properties().rarity(Rarity.EPIC).durability(750));
    public static final Item DRAGONFIN_BOOTS = register("dragonfin_boots", DragonfinBootsItem::new, new Item.Properties()
            /*? if >=1.21*/.durability(ArmorItem.Type.BOOTS.getDurability(37))
            .rarity(Rarity.EPIC).fireResistant());

    public static final Item ANGLING_TABLE = register("angling_table", properties -> new BlockItem(TideBlocks.ANGLING_TABLE, properties));
    public static final Item FISH_DISPLAY = register("fish_display", properties -> new BlockItem(TideBlocks.FISH_DISPLAY, properties));

    public static final Item WOODEN_CRATE = register("wooden_crate", properties -> new BlockItem(TideBlocks.WOODEN_CRATE, properties));
    public static final Item OBSIDIAN_CRATE = register("obsidian_crate", properties -> new BlockItem(TideBlocks.OBSIDIAN_CRATE, properties));
    public static final Item PURPUR_CRATE = register("purpur_crate", properties -> new BlockItem(TideBlocks.PURPUR_CRATE, properties));

    public static final Item FISH_BONE = register("fish_bone", Item::new, new Item.Properties());
    public static final Item OBSIDIAN_FRAGMENT = register("obsidian_fragment", Item::new, new Item.Properties().fireResistant());

    public static final Item JELLY_TORCH = register("jelly_torch",  properties -> new JellyTorchBlockItem(TideBlocks.JELLY_TORCH, TideBlocks.JELLY_WALL_TORCH, Direction.DOWN, properties), new Item.Properties());

    public static final Item COOKED_FISH = register("cooked_fish", Item::new, new Item.Properties().food(TideFoods.COOKED_FISH.toProperties()));
    public static final Item SMALL_COOKED_FISH = register("small_cooked_fish", Item::new, new Item.Properties().food(TideFoods.TINY_COOKED_FISH.toProperties()));
    public static final Item LARGE_COOKED_FISH = register("large_cooked_fish", Item::new, new Item.Properties().food(TideFoods.BIG_COOKED_FISH.toProperties()));
    public static final Item GRILLED_TUNA = register("grilled_tuna", Item::new, new Item.Properties().food(TideFoods.GRILLED_TUNA.toProperties()));

    public static final Item FISH_SLICE = register("fish_slice", Item::new, new Item.Properties().food(TideFoods.FISH_SLICE.toProperties()));
    public static final Item COOKED_FISH_SLICE = register("cooked_fish_slice", Item::new, new Item.Properties().food(TideFoods.COOKED_FISH_SLICE.toProperties()));

    public static Item register(String name, Function<Item.Properties, Item> factory) {
        return register(name, factory, new Item.Properties());
    }

    public static Item register(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Tide.resource(name));
        Item item = factory.apply(properties);
        return register(key, item);
    }

    static Item register(ResourceKey<Item> key, Item item) {
        ITEMS.put(key, item);
        if (item instanceof BlockItem blockItem) blockItem.registerBlocks(Item.BY_BLOCK, item);
        return Tide.PLATFORM.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void init() {
        TideFish.init();
    }

    public static ArrayList<ItemStack> getCreativeModeItemList() {
        if (DISPLAY_ITEMS.isEmpty()) {
            addDisplayItems(
                    FISHING_JOURNAL, FISHY_NOTE, FISH_SATCHEL,

                    Items.FISHING_ROD,
                    STONE_FISHING_ROD, IRON_FISHING_ROD, GOLDEN_FISHING_ROD,
                    CRYSTAL_FISHING_ROD, DIAMOND_FISHING_ROD, NETHERITE_FISHING_ROD,
                    ECHO_FISHING_ROD, PRISMARINE_FISHING_ROD, SUNFLOWER_FISHING_ROD,
                    VILLAGE_FISHING_ROD, BLAZING_FISHING_ROD, HONEYCOMB_FISHING_ROD,
                    MIDAS_FISHING_ROD,

                    BAIT, LUCKY_BAIT, MAGNETIC_BAIT, INCANDESCENT_BAIT, ABYSS_BAIT,

                    POCKET_WATCH, LUNAR_CALENDAR, CLIMATE_GAUGE,
                    DEPTH_METER, WEATHER_RADIO, FISH_FINDER,

                    ENCHANTED_POCKET_WATCH, STARLIGHT_BOW, DRAGONFIN_BOOTS,

                    ANGLING_TABLE, FISH_DISPLAY,
                    WOODEN_CRATE, OBSIDIAN_CRATE, PURPUR_CRATE,

                    COOKED_FISH, GRILLED_TUNA, FISH_SLICE, COOKED_FISH_SLICE,

                    FISH_BONE, OBSIDIAN_FRAGMENT,

                    JELLY_TORCH,

                    FISHING_HOOK, FIERY_HOOK, PERMAFROST_HOOK,
                    TWILIGHT_HOOK, LAVAPROOF_HOOK, VOID_HOOK,

                    FISHING_LINE, COPPER_LINE, IRON_LINE, GOLDEN_LINE, DIAMOND_LINE,

                    RED_BOBBER, ORANGE_BOBBER,
                    YELLOW_BOBBER, LIME_BOBBER,
                    GREEN_BOBBER, CYAN_BOBBER,
                    LIGHT_BLUE_BOBBER, BLUE_BOBBER,
                    PURPLE_BOBBER, MAGENTA_BOBBER,
                    PINK_BOBBER, WHITE_BOBBER,
                    LIGHT_GRAY_BOBBER, GRAY_BOBBER,
                    BLACK_BOBBER, BROWN_BOBBER,
                    APPLE_BOBBER, GOLDEN_APPLE_BOBBER,
                    ENCHANTED_GOLDEN_APPLE_BOBBER, IRON_BOBBER,
                    GOLDEN_BOBBER, DIAMOND_BOBBER,
                    NETHERITE_BOBBER, AMETHYST_BOBBER,
                    ECHO_BOBBER, CHORUS_BOBBER,
                    FEATHER_BOBBER, LICHEN_BOBBER,
                    NAUTILUS_BOBBER, PEARL_BOBBER,
                    HEART_BOBBER, GRASSY_BOBBER,
                    DUCK_BOBBER
            );

            TideFish.ORDERED.forEach(item -> {
                ItemStack stack = new ItemStack(item);
                if (Tide.SERVER_CONFIG.items.fishItemSizes == TideServerConfig.Items.SizeMode.ALWAYS && FishData.get(stack).map(FishData::size).isPresent())
                    FishData.get(stack).ifPresent(data -> TideItemData.FISH_LENGTH.set(stack, data.getAverageLength()));
                DISPLAY_ITEMS.add(stack);

                ResourceLocation itemKey = BuiltInRegistries.ITEM.getKey(item);

                var bucketItem = BuiltInRegistries.ITEM.getOptional(itemKey.withSuffix("_bucket"));
                bucketItem.ifPresent(value -> DISPLAY_ITEMS.add(new ItemStack(value)));

                var spawnEggItem = BuiltInRegistries.ITEM.getOptional(itemKey.withSuffix("_spawn_egg"));
                spawnEggItem.ifPresent(value -> DISPLAY_ITEMS.add(new ItemStack(value)));
            });
        }
        return DISPLAY_ITEMS;
    }

    private static void addDisplayItems(Item... items) {
        DISPLAY_ITEMS.addAll(Arrays.stream(items).map(ItemStack::new).toList());
    }

    public static CreativeModeTab.Builder getCreativeTab(CreativeModeTab.Builder builder) {
        return builder.title(Component.translatable("itemGroup.tide.main"))
                .icon(() -> new ItemStack(TideFish.ANGELFISH))
                .displayItems(TideItems::getAllDisplayItems);
    }

    public static void getAllDisplayItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        TideItems.getCreativeModeItemList().forEach(output::accept);
    }
}
