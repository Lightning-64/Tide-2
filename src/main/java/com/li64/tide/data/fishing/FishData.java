package com.li64.tide.data.fishing;

import com.google.common.collect.ImmutableList;
import com.li64.tide.Tide;
import com.li64.tide.TideConfig;
import com.li64.tide.compat.seasons.Season;
import com.li64.tide.data.commands.TestType;
import com.li64.tide.data.item.TideItemData;
import com.li64.tide.datagen.fabric.providers.SimpleDataOutput;
import com.li64.tide.data.TideData;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.types.*;
import com.li64.tide.data.fishing.mediums.FishingMedium;
import com.li64.tide.data.fishing.modifiers.FishingModifier;
import com.li64.tide.data.fishing.modifiers.types.TemperatureModifier;
import com.li64.tide.data.fishing.selector.FishingEntry;
import com.li64.tide.data.journal.FishRarity;
import com.li64.tide.data.journal.JournalGroup;
import com.li64.tide.util.TideUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;

/*? if <1.21 {*/
/*import com.li64.tide.data.ValidatableDataEntry;
import java.util.function.Function;
*///?}

import java.util.*;
import java.util.function.Consumer;

public record FishData(/*? if >=1.21 {*/ Holder<Item> fish,
                       /*?} else*//*ResourceKey<Item> fishKey,*/
                       Optional<Holder<Item>> bucket,
                       List<FishingCondition> conditions,
                       List<FishingModifier> modifiers,
                       double weight, double quality,
                       float strength, float speed,
                       boolean showInJournal,
                       ProfileData profile,
                       MinigameBehavior behavior,
                       Optional<SizeData> size,
                       Optional<DisplayData> display,
                       Optional<Holder<Item>> parent) implements FishingEntry/*? if <1.21 {*//*, ValidatableDataEntry*//*?}*/ {
    public static LootTable VANILLA_FISH_TABLE;

    private static Map<Item, FishData> FISH_BY_ITEM;
    private static Map<Item, FishData> FISH_BY_BUCKET;
    private static Map<Item, List<FishData>> MERGER_MAP;

    public static final Codec<FishData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            /*? if >=1.21 {*/BuiltInRegistries.ITEM.holderByNameCodec().fieldOf("fish").forGetter(FishData::fish),
            /*?} else*//*ResourceKey.codec(Registries.ITEM).fieldOf("fish").forGetter(FishData::fishKey),*/
            BuiltInRegistries.ITEM.holderByNameCodec().optionalFieldOf("bucket").forGetter(FishData::bucket),
            FishingCondition.CODEC.listOf().optionalFieldOf("conditions", List.of()).forGetter(FishData::conditions),
            FishingModifier.CODEC.listOf().optionalFieldOf("modifiers", List.of()).forGetter(FishData::modifiers),
            Codec.DOUBLE.optionalFieldOf("selection_weight", 0.0).forGetter(FishData::weight),
            Codec.DOUBLE.optionalFieldOf("selection_quality", 0.0).forGetter(FishData::quality),
            Codec.FLOAT.optionalFieldOf("strength", 0.3f).forGetter(FishData::strength),
            Codec.FLOAT.optionalFieldOf("speed", 1.0f).forGetter(FishData::speed),
            Codec.BOOL.optionalFieldOf("show_in_journal", true).forGetter(FishData::showInJournal),
            ProfileData.CODEC.optionalFieldOf("journal_profile", new ProfileData()).forGetter(FishData::profile),
            MinigameBehavior.CODEC.optionalFieldOf("behavior", MinigameBehavior.SINE).forGetter(FishData::behavior),
            SizeData.CODEC.optionalFieldOf("size").forGetter(FishData::size),
            DisplayData.CODEC.optionalFieldOf("display_data").forGetter(FishData::display),
            BuiltInRegistries.ITEM.holderByNameCodec().optionalFieldOf("parent").forGetter(FishData::parent)
    ).apply(instance, FishData::new));

    public static void buildMaps() {
        FISH_BY_ITEM = new HashMap<>();
        FISH_BY_BUCKET = new HashMap<>();
        MERGER_MAP = new HashMap<>();

        TideData.FISH.get().values().forEach(FishData::register);
        if (!TideData.FISH.hasGenerated()) {
            TideData.FISH.acceptGenerated(createGeneratedFishData())
                    .values().forEach(FishData::register);
        }

        MERGER_MAP.forEach((parent, mergers) -> {
            Optional<FishData> parentData = get(parent);
            if (parentData.isEmpty()) return;
            mergers.forEach(data -> {
                FISH_BY_ITEM.put(data.fish().value(), parentData.get());
                data.bucket().ifPresent(bucket -> FISH_BY_BUCKET.put(bucket.value(), parentData.get()));
            });
        });
    }

    private static void register(FishData data) {
        if (data.parent().isPresent()) {
            Item parent = data.parent().get().value();
            if (MERGER_MAP.containsKey(parent)) MERGER_MAP.get(parent).add(data);
            else {
                ArrayList<FishData> mergers = new ArrayList<>();
                mergers.add(data);
                MERGER_MAP.put(parent, mergers);
            }
            return;
        }
        FISH_BY_ITEM.put(data.fish().value(), data);
        data.bucket().ifPresent(bucket -> FISH_BY_BUCKET.put(bucket.value(), data));
    }

    public static Optional<FishData> get(ItemStack fish) {
        return get(fish.getItem());
    }

    public static Optional<FishData> get(Entity entity) {
        return get(entity.getType());
    }

    public static Optional<FishData> get(EntityType<?> entityType) {
        ResourceLocation key = BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
        return BuiltInRegistries.ITEM.getOptional(key).flatMap(FishData::get);
    }

    public static Optional<FishData> get(Item fish) {
        if (FISH_BY_ITEM == null) {
            Tide.LOG.error("Tried to access data for fish \"{}\" before fish data was loaded", fish);
            return Optional.empty();
        }
        return Optional.ofNullable(FISH_BY_ITEM.get(fish));
    }

    public static Optional<FishData> fromBucket(ItemStack bucket) {
        if (FISH_BY_BUCKET == null) {
            Tide.LOG.error("Tried to access data for bucket \"{}\" before fish data was loaded", bucket);
            return Optional.empty();
        }
        return Optional.ofNullable(FISH_BY_BUCKET.get(bucket.getItem()));
    }

    public static Optional<FishData> getExact(ItemStack fish) {
        return getExact(fish.getItem());
    }

    public static Optional<FishData> getExact(Item fish) {
        var dataOp = get(fish);
        if (dataOp.isEmpty() || dataOp.get().fish().value() == fish) return dataOp;
        Tide.LOG.info("additional checks required");
        List<FishData> mergers = MERGER_MAP.get(dataOp.get().fish().value());
        return mergers.stream().filter(d -> d.fish().value() == fish).findFirst();
    }

    public static FishData getOrThrow(ItemStack fish) {
        return FishData.get(fish).orElseThrow();
    }

    public static ItemStack randomFish() {
        return new ItemStack(TideData.FISH.get().values().get(new Random()
                .nextInt(TideData.FISH.get().count())).fish());
    }

    //? if <1.21 {
    /*public Holder<Item> fish() {
        return BuiltInRegistries.ITEM.getHolder(fishKey)
                .orElseThrow(() -> new IllegalStateException("could not find item " + fishKey));
    }

    @Override
    public boolean isValid() {
        return BuiltInRegistries.ITEM.getHolder(fishKey).isPresent();
    }

    @Override
    public String invalidReason() {
        return "fish item '" + fishKey.location() + "' was not found";
    }
    *///?}

    public boolean hasJournalEntry() {
        return this.isOriginal() && this.showInJournal();
    }

    public boolean isOriginal() {
        return this.parent().isEmpty();
    }

    @Override
    public double weight(FishingContext context) {
        return this.modifyWeight(weight, quality, modifiers, context);
    }

    @Override
    public boolean shouldKeep(FishingContext context) {
        return conditions().stream().allMatch(condition -> condition.test(context));
    }

    @Override
    public CatchResult getResult(FishingContext context) {
        ItemStack stack = new ItemStack(fish().value());
        if (Tide.CONFIG.items.fishItemSizes == TideConfig.Items.SizeMode.ALWAYS && size().isPresent())
            TideItemData.FISH_LENGTH.set(stack, this.getRandomLength(context.rng()));
        if (Tide.CONFIG.items.bucketableFishItems == TideConfig.Items.BucketableMode.WHEN_LIVING && bucket().isPresent())
            TideItemData.CATCH_TIMESTAMP.set(stack, context.level().getDayTime());
        return createResult(stack);
    }

    public double getRandomLength(RandomSource rng) {
        SizeData sizeData = this.size().orElse(null);
        if (sizeData == null) return 0.0;
        return sizeData.sample(rng, 0.0);
    }

    public double getAverageLength() {
        SizeData sizeData = this.size().orElse(null);
        if (sizeData == null) return 0.0;
        return (sizeData.typicalHighCm() + sizeData.typicalLowCm()) / 2.0;
    }

    @Override
    public boolean matchesTestType(TestType type) {
        return type == TestType.FISH;
    }

    @Override
    public MutableComponent getTestKey() {
        return Component.translatable(fish().value().getDescriptionId());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Holder<Item> fish;
        private Holder<Item> bucket;
        private Holder<Item> parent;

        private final ProfileData.Builder profile = ProfileData.builder();
        private final List<FishingCondition> conditions = new ArrayList<>();
        private final List<FishingModifier> modifiers = new ArrayList<>();

        private SizeData size = null;
        private DisplayData display = null;

        private double weight = 0.0;
        private double quality = 0.0;

        private float strength = 0.3f;
        private float speed = 1.0f;
        private MinigameBehavior behavior = MinigameBehavior.SINE;

        private Builder() {}

        public Builder fish(Item fish) {
            this.fish = BuiltInRegistries.ITEM.wrapAsHolder(fish);
            ResourceLocation key = BuiltInRegistries.ITEM.getKey(fish);
            return this.journalDescription("journal.description." + key.getNamespace() + "." + key.getPath());
        }

        public Builder fish(String namespace, String path) {
            ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Tide.resource(namespace, path));
            this.fish = Holder.Reference.createStandAlone(BuiltInRegistries.ITEM.holderOwner(), key);
            return this.journalDescription("journal.description." + namespace + "." + path);
        }

        public Builder bucket(Item bucket) {
            this.bucket = BuiltInRegistries.ITEM.wrapAsHolder(bucket);
            return this;
        }

        public Builder either(FishingCondition a, FishingCondition b) {
            return condition(new EitherCondition(a, b));
        }

        public Builder medium(FishingMedium medium) {
            return condition(new FishingMediumCondition(medium));
        }

        public Builder inStructure(ResourceKey<Structure> structure) {
            return condition(StructuresCondition.only(structure));
        }

        public Builder foundIn(List<TagKey<Biome>> foundIn) {
            return condition(BiomeWhitelistCondition.fromTagList(foundIn));
        }

        public Builder foundIn(TagKey<Biome> foundIn) {
            return condition(BiomeWhitelistCondition.fromTagList(List.of(foundIn)));
        }

        public Builder dimensions(List<ResourceKey<Level>> dimensions) {
            return condition(new DimensionsCondition(dimensions));
        }

        public Builder overworld() {
            return dimensions(List.of(Level.OVERWORLD));
        }

        public Builder nether() {
            return dimensions(List.of(Level.NETHER));
        }

        public Builder end() {
            return dimensions(List.of(Level.END));
        }

        public Builder water() {
            return medium(FishingMedium.WATER);
        }

        public Builder surface() {
            return above(40);
        }

        public Builder above(int y) {
            return condition(new AboveCondition(y));
        }

        public Builder below(int y) {
            return condition(new BelowCondition(y));
        }

        public Builder elevationRange(int minY, int maxY) {
            return condition(new DepthRangeCondition(minY, maxY));
        }

        public Builder freshwater() {
            return condition(new FreshwaterCondition());
        }

        public Builder saltwater() {
            return condition(new SaltwaterCondition());
        }

        public Builder temperature(float preferred, float tolerance) {
            return modifier(new TemperatureModifier(preferred, tolerance));
        }

        public Builder weather(WeatherType... weather) {
            return condition(WeatherCondition.anyOf(weather));
        }

        public Builder timeOfDay(TimeRange... ranges) {
            return condition(new TimeOfDayCondition(Arrays.stream(ranges).toList()));
        }

        public Builder seasons(Season... seasons) {
            return condition(SeasonsCondition.fromSeasons(seasons));
        }

        public Builder condition(FishingCondition condition) {
            this.conditions.add(condition);
            return this;
        }

        public Builder conditions(List<FishingCondition> conditions) {
            this.conditions.addAll(conditions);
            return this;
        }

        public Builder modifier(FishingModifier modifier) {
            this.modifiers.add(modifier);
            return this;
        }

        public Builder modifiers(List<FishingModifier> modifiers) {
            this.modifiers.addAll(modifiers);
            return this;
        }

        public Builder selectionWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder selectionQuality(double quality) {
            this.quality = quality;
            return this;
        }

        public Builder strength(float strength) {
            this.strength = strength;
            return this;
        }

        public Builder speed(float speed) {
            this.speed = speed;
            return this;
        }

        public Builder size(double lowCm, double highCm, double recordCm) {
            this.size = new SizeData(lowCm, highCm, recordCm);
            return this;
        }

        public Builder behavior(MinigameBehavior behavior) {
            this.behavior = behavior;
            return this;
        }

        public Builder displayData(Consumer<DisplayData.Builder> modifiers) {
            DisplayData.Builder builder = DisplayData.builder();
            if (this.fish instanceof Holder.Reference<Item>) {
                ResourceLocation id = TideUtils.holderId(this.fish);
                builder.entityType(ResourceKey.create(Registries.ENTITY_TYPE, id));
            }
            else builder.entityType(BuiltInRegistries.ENTITY_TYPE.get(TideUtils.holderId(this.fish)));
            modifiers.accept(builder);
            this.display = builder.build();
            return this;
        }

        public Builder journalDescription(String description) {
            this.profile.description(description);
            return this;
        }

        public Builder journalLocation(String location) {
            this.profile.location(location);
            return this;
        }

        public Builder journalAltSprite(int size) {
            ResourceLocation location = this.fish.unwrap().map(ResourceKey::location, BuiltInRegistries.ITEM::getKey);
            this.profile.altSprite(location.withPath(path -> "textures/item/" + path + "_full.png"), size);
            return this;
        }

        public Builder journalRarity(FishRarity rarity) {
            this.profile.rarity(rarity);
            return this;
        }

        public Builder journalGroup(JournalGroup group) {
            this.profile.group(group);
            return this;
        }

        public Builder parent(Item parent) {
            this.parent = BuiltInRegistries.ITEM.wrapAsHolder(parent);
            return this;
        }

        public void build(ResourceLocation path, SimpleDataOutput<FishData> output) {
            output.accept(path, build());
        }

        public void build(String group, SimpleDataOutput<FishData> output) {
            ResourceLocation key = TideUtils.holderId(this.fish);
            output.accept(Tide.resource(key.getNamespace().replace('-', '_'),
                    group + "/" + key.getPath()), build());
        }

        public void build(SimpleDataOutput<FishData> output) {
            build(profile.getGroup().getSerializedName(), output);
        }

        public FishData build() {
            if (fish == null) throw new IllegalStateException("Fish item must be provided");
            return new FishData(
                    /*? if >=1.21 {*/fish,
                    //?} else {
                    /*fish.unwrap().map(Function.identity(), item ->
                            BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow()),
                    *///?}
                    Optional.ofNullable(bucket),
                    ImmutableList.copyOf(conditions),
                    ImmutableList.copyOf(modifiers),
                    weight, quality,
                    strength, speed,
                    true,
                    profile.build(), behavior,
                    Optional.ofNullable(size),
                    Optional.ofNullable(display),
                    Optional.ofNullable(parent)
            );
        }
    }

    public static Map<ResourceLocation, FishData> createGeneratedFishData() {
        Map<ResourceLocation, FishData> entries = new HashMap<>();
        if (VANILLA_FISH_TABLE == null || VANILLA_FISH_TABLE == LootTable.EMPTY) {
            Tide.LOG.error("Failed to find vanilla fishing loot table during post-process! Some compat functionality may not be present.");
            return entries;
        }
        for (LootPool pool : VANILLA_FISH_TABLE.pools) {
            for (LootPoolEntryContainer container : pool.entries) {
                if (!(container instanceof LootItem lootItem)) continue;
                Item item = lootItem.item/*? if >=1.21 {*/.value()/*?}*/;
                ResourceLocation key = BuiltInRegistries.ITEM.getKey(item);
                if (Tide.CONFIG.general.autoFishDataBlacklist.contains(key.toString()) || get(item).isPresent()) continue;
                Tide.LOG.info("Found unknown fish \"{}\" in vanilla fishing loot table, auto-generating fish data", item);
                FishData data = builder().fish(item)
                        .strength(0.5f)
                        .speed(0.9f)
                        .selectionWeight(30)
                        .overworld()
                        .water()
                        .surface()
                        .journalGroup(JournalGroup.MISC)
                        .build();
                entries.put(key.withPrefix("generated/"), data);
            }
        }
        if (!entries.isEmpty()) Tide.LOG.info("Auto-generated {} fish data entries", entries.size());
        return entries;
    }
}
