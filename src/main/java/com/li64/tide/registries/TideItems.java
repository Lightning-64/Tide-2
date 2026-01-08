package com.li64.tide.registries;

import com.li64.tide.Tide;
import com.li64.tide.TideConfig;
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

    public static final Item STONE_FISHING_ROD = register("stone_fishing_rod", properties -> new TideFishingRodItem(48, properties));//, new Item.Properties().repairable(Items.STRING));
    public static final Item IRON_FISHING_ROD = register("iron_fishing_rod", properties -> new TideFishingRodItem(64, properties));//, new Item.Properties().repairable(Items.STRING));
    public static final Item GOLDEN_FISHING_ROD = register("golden_fishing_rod", properties -> new TideFishingRodItem(36, properties));//, new Item.Properties().repairable(Items.STRING));
    public static final Item CRYSTAL_FISHING_ROD = register("crystal_fishing_rod", properties -> new TideFishingRodItem(80, properties));//, new Item.Properties().repairable(Items.STRING));
    public static final Item DIAMOND_FISHING_ROD = register("diamond_fishing_rod", properties -> new TideFishingRodItem(128, properties));//, new Item.Properties().repairable(Items.STRING));
    public static final Item NETHERITE_FISHING_ROD = register("netherite_fishing_rod", properties -> new TideFishingRodItem(512, properties));//, new Item.Properties().repairable(Items.STRING));

    public static final Item BAIT = register("bait", Item::new);
    public static final Item LUCKY_BAIT = register("lucky_bait", Item::new);
    public static final Item MAGNETIC_BAIT = register("magnetic_bait", Item::new);

    public static final Item RED_FISHING_BOBBER = register("red_fishing_bobber", FishingBobberItem::new);
    public static final Item ORANGE_FISHING_BOBBER = register("orange_fishing_bobber", FishingBobberItem::new);
    public static final Item YELLOW_FISHING_BOBBER = register("yellow_fishing_bobber", FishingBobberItem::new);
    public static final Item LIME_FISHING_BOBBER = register("lime_fishing_bobber", FishingBobberItem::new);
    public static final Item GREEN_FISHING_BOBBER = register("green_fishing_bobber", FishingBobberItem::new);
    public static final Item CYAN_FISHING_BOBBER = register("cyan_fishing_bobber", FishingBobberItem::new);
    public static final Item LIGHT_BLUE_FISHING_BOBBER = register("light_blue_fishing_bobber", FishingBobberItem::new);
    public static final Item BLUE_FISHING_BOBBER = register("blue_fishing_bobber", FishingBobberItem::new);
    public static final Item PURPLE_FISHING_BOBBER = register("purple_fishing_bobber", FishingBobberItem::new);
    public static final Item MAGENTA_FISHING_BOBBER = register("magenta_fishing_bobber", FishingBobberItem::new);
    public static final Item PINK_FISHING_BOBBER = register("pink_fishing_bobber", FishingBobberItem::new);
    public static final Item WHITE_FISHING_BOBBER = register("white_fishing_bobber", FishingBobberItem::new);
    public static final Item LIGHT_GRAY_FISHING_BOBBER = register("light_gray_fishing_bobber", FishingBobberItem::new);
    public static final Item GRAY_FISHING_BOBBER = register("gray_fishing_bobber", FishingBobberItem::new);
    public static final Item BLACK_FISHING_BOBBER = register("black_fishing_bobber", FishingBobberItem::new);
    public static final Item BROWN_FISHING_BOBBER = register("brown_fishing_bobber", FishingBobberItem::new);
    public static final Item APPLE_FISHING_BOBBER = register("apple_fishing_bobber", FishingBobberItem::new, new Item.Properties().food(Foods.APPLE));
    public static final Item GOLDEN_APPLE_FISHING_BOBBER = register("golden_apple_fishing_bobber", FishingBobberItem::new, new Item.Properties().food(Foods.GOLDEN_APPLE));
    public static final Item ENCHANTED_GOLDEN_APPLE_FISHING_BOBBER = register("enchanted_golden_apple_fishing_bobber", EnchantedFishingBobberItem::new, new Item.Properties()
            /*? if >=1.21*/.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
            .food(Foods.ENCHANTED_GOLDEN_APPLE));
    public static final Item IRON_FISHING_BOBBER = register("iron_fishing_bobber", FishingBobberItem::new);
    public static final Item GOLDEN_FISHING_BOBBER = register("golden_fishing_bobber", FishingBobberItem::new);
    public static final Item DIAMOND_FISHING_BOBBER = register("diamond_fishing_bobber", FishingBobberItem::new);
    public static final Item NETHERITE_FISHING_BOBBER = register("netherite_fishing_bobber", FishingBobberItem::new);
    public static final Item AMETHYST_FISHING_BOBBER = register("amethyst_fishing_bobber", FishingBobberItem::new);
    public static final Item ECHO_FISHING_BOBBER = register("echo_fishing_bobber", FishingBobberItem::new);
    public static final Item CHORUS_FISHING_BOBBER = register("chorus_fishing_bobber", FishingBobberItem::new);
    public static final Item FEATHER_FISHING_BOBBER = register("feather_fishing_bobber", FishingBobberItem::new);
    public static final Item LICHEN_FISHING_BOBBER = register("lichen_fishing_bobber", FishingBobberItem::new);
    public static final Item NAUTILUS_FISHING_BOBBER = register("nautilus_fishing_bobber", FishingBobberItem::new);
    public static final Item PEARL_FISHING_BOBBER = register("pearl_fishing_bobber", FishingBobberItem::new);
    public static final Item HEART_FISHING_BOBBER = register("heart_fishing_bobber", FishingBobberItem::new);
    public static final Item GRASSY_FISHING_BOBBER = register("grassy_fishing_bobber", FishingBobberItem::new);
    public static final Item DUCK_FISHING_BOBBER = register("duck_fishing_bobber", FishingBobberItem::new);

    public static final Item FISHING_HOOK = register("fishing_hook", FishingHookItem::new);
    public static final Item IRON_FISHING_HOOK = register("iron_fishing_hook", properties -> new FishingHookItem(properties, "item.tide.iron_hook.desc"));
    public static final Item LAVAPROOF_FISHING_HOOK = register("lavaproof_fishing_hook", properties -> new FishingHookItem(properties, "item.tide.lavaproof_hook.desc"));
    public static final Item VOID_FISHING_HOOK = register("void_fishing_hook", properties -> new FishingHookItem(properties, "item.tide.void_hook.desc"));

    public static final Item FISHING_LINE = register("fishing_line", FishingLineItem::new);
    public static final Item BRAIDED_LINE = register("braided_line", properties -> new FishingLineItem(properties, "item.tide.braided_line.desc"));
    public static final Item REINFORCED_LINE = register("reinforced_line", properties -> new FishingLineItem(properties, "item.tide.reinforced_line.desc"));
    public static final Item GOLDEN_LINE = register("golden_line", properties -> new FishingLineItem(properties, "item.tide.golden_line.desc"));

    public static final Item FISHING_JOURNAL = register("fishing_journal", FishingJournalItem::new);
    public static final Item FISHY_NOTE = register("fishy_note", FishyNoteItem::new, new Item.Properties().stacksTo(1));
    public static final Item FISH_SATCHEL = register("fish_satchel", FishSatchelItem::new, new Item.Properties()
            /*? if >=1.21*/.component(DataComponents.BUNDLE_CONTENTS, new BundleContents(List.of()))
            .stacksTo(1));

    public static final Item THERMOMETER = register("thermometer", ThermometerItem::new);
    public static final Item POCKET_WATCH = register("pocket_watch", PocketWatchItem::new);

    public static final Item ENCHANTED_POCKET_WATCH = register("enchanted_pocket_watch", EnchantedPocketWatchItem::new, new Item.Properties()
            /*? if >=1.21*/.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
            .rarity(Rarity.EPIC));
    public static final Item STARLIGHT_BOW = register("starlight_bow", StarlightBowItem::new,
            new Item.Properties().rarity(Rarity.EPIC).durability(750));
    public static final Item MIDAS_FISHING_ROD = register("midas_fishing_rod", properties -> new TideFishingRodItem(256, properties));
    public static final Item DRAGONFIN_BOOTS = register("dragonfin_boots", DragonfinBootsItem::new, new Item.Properties()
            /*? if >=1.21*/.durability(ArmorItem.Type.BOOTS.getDurability(37))
            .rarity(Rarity.EPIC));

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

                    BAIT, LUCKY_BAIT, MAGNETIC_BAIT,

                    THERMOMETER, POCKET_WATCH,

                    ENCHANTED_POCKET_WATCH, STARLIGHT_BOW, MIDAS_FISHING_ROD, DRAGONFIN_BOOTS,

                    ANGLING_TABLE, FISH_DISPLAY,
                    WOODEN_CRATE, OBSIDIAN_CRATE, PURPUR_CRATE
            );

            TideFish.ORDERED.forEach(item -> {
                ItemStack stack = new ItemStack(item);
                if (Tide.CONFIG.items.fishItemSizes == TideConfig.Items.SizeMode.ALWAYS) FishData.get(stack)
                        .ifPresent(data -> TideItemData.FISH_LENGTH.set(stack, data.getAverageLength()));
                DISPLAY_ITEMS.add(stack);

                ResourceLocation itemKey = BuiltInRegistries.ITEM.getKey(item);

                var bucketItem = BuiltInRegistries.ITEM.getOptional(itemKey.withSuffix("_bucket"));
                bucketItem.ifPresent(value -> DISPLAY_ITEMS.add(new ItemStack(value)));

                var spawnEggItem = BuiltInRegistries.ITEM.getOptional(itemKey.withSuffix("_spawn_egg"));
                spawnEggItem.ifPresent(value -> DISPLAY_ITEMS.add(new ItemStack(value)));
            });

            addDisplayItems(
                    COOKED_FISH, GRILLED_TUNA, FISH_SLICE, COOKED_FISH_SLICE,

                    FISH_BONE, OBSIDIAN_FRAGMENT,

                    JELLY_TORCH,

                    FISHING_HOOK, IRON_FISHING_HOOK, LAVAPROOF_FISHING_HOOK, VOID_FISHING_HOOK,

                    FISHING_LINE, BRAIDED_LINE, REINFORCED_LINE, GOLDEN_LINE,

                    RED_FISHING_BOBBER, ORANGE_FISHING_BOBBER,
                    YELLOW_FISHING_BOBBER, LIME_FISHING_BOBBER,
                    GREEN_FISHING_BOBBER, CYAN_FISHING_BOBBER,
                    LIGHT_BLUE_FISHING_BOBBER, BLUE_FISHING_BOBBER,
                    PURPLE_FISHING_BOBBER, MAGENTA_FISHING_BOBBER,
                    PINK_FISHING_BOBBER, WHITE_FISHING_BOBBER,
                    LIGHT_GRAY_FISHING_BOBBER, GRAY_FISHING_BOBBER,
                    BLACK_FISHING_BOBBER, BROWN_FISHING_BOBBER,
                    APPLE_FISHING_BOBBER, GOLDEN_APPLE_FISHING_BOBBER,
                    ENCHANTED_GOLDEN_APPLE_FISHING_BOBBER, IRON_FISHING_BOBBER,
                    GOLDEN_FISHING_BOBBER, DIAMOND_FISHING_BOBBER,
                    NETHERITE_FISHING_BOBBER, AMETHYST_FISHING_BOBBER,
                    ECHO_FISHING_BOBBER, CHORUS_FISHING_BOBBER,
                    FEATHER_FISHING_BOBBER, LICHEN_FISHING_BOBBER,
                    NAUTILUS_FISHING_BOBBER, PEARL_FISHING_BOBBER,
                    HEART_FISHING_BOBBER, GRASSY_FISHING_BOBBER,
                    DUCK_FISHING_BOBBER
            );
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
