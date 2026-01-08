package com.li64.tide.data.fishing;

import com.google.common.collect.ImmutableList;
import com.li64.tide.Tide;
import com.li64.tide.data.ValidatableDataEntry;
import com.li64.tide.data.commands.TestType;
import com.li64.tide.data.loot.LootTableRef;
import com.li64.tide.datagen.fabric.providers.SimpleDataOutput;
import com.li64.tide.data.fishing.conditions.FishingCondition;
import com.li64.tide.data.fishing.conditions.types.*;
import com.li64.tide.data.fishing.mediums.FishingMedium;
import com.li64.tide.data.fishing.modifiers.FishingModifier;
import com.li64.tide.data.fishing.selector.FishingEntry;
import com.li64.tide.util.TideUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.storage.loot.LootTable;

/*? if >= 1.21*/import net.minecraft.core.registries.Registries;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record CrateData(BlockStateProvider blockProvider,
                        /*? if >= 1.21 {*/Optional<ResourceKey<LootTable>> lootTable,
                        /*?} else*//*Optional<ResourceLocation> lootTable,*/
                        Optional<String> associatedMod,
                        List<FishingCondition> conditions,
                        List<FishingModifier> modifiers,
                        double weight, double quality) implements FishingEntry, ValidatableDataEntry {

    public static final Codec<CrateData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("block").forGetter(CrateData::blockProvider),
            /*? if >= 1.21 {*/ResourceKey.codec(Registries.LOOT_TABLE).optionalFieldOf("loot_table").forGetter(CrateData::lootTable),
            /*?} else*//*ResourceLocation.CODEC.optionalFieldOf("loot_table").forGetter(CrateData::lootTable),*/
            Codec.STRING.optionalFieldOf("associated_mod").forGetter(CrateData::associatedMod),
            FishingCondition.CODEC.listOf().optionalFieldOf("conditions", List.of()).forGetter(CrateData::conditions),
            FishingModifier.CODEC.listOf().optionalFieldOf("modifiers", List.of()).forGetter(CrateData::modifiers),
            Codec.DOUBLE.optionalFieldOf("weight", 0.0).forGetter(CrateData::weight),
            Codec.DOUBLE.optionalFieldOf("quality", 0.0).forGetter(CrateData::quality)
    ).apply(instance, CrateData::new));

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
        MinecraftServer server = context.level().getServer();
        if (lootTable.isEmpty()) return createResult(List.of());
        LootTable table = TideUtils.getLootTable(lootTable.get(), server);
        return createResult(table.getRandomItems(context.createFishingLootParams()));
    }

    @Override
    public boolean matchesTestType(TestType type) {
        return type == TestType.CRATES;
    }

    @Override
    public MutableComponent getTestKey() {
        if (lootTable.isEmpty()) return Component.translatable("commands.fishing.entries.crate.unknown");
        ResourceLocation tableLocation = lootTable.get()/*? if >=1.21 {*/.location()/*?}*/;
        return Component.translatable("commands.fishing.entries.crate").append(" \"")
                .append(Component.literal(tableLocation.toString())).append("\"");
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean isValid() {
        return this.associatedMod().map(Tide.PLATFORM::isModLoaded).orElse(true) || this.associatedMod()
                .map(id -> Tide.PLATFORM.isModLoaded(id.replace("-", "_"))).orElse(true);
    }

    @Override
    public String invalidReason() {
        return "mod '" + this.associatedMod().orElse("undefined") + "' is not loaded";
    }

    public static class Builder {
        private BlockStateProvider block;
        /*? if >= 1.21 {*/private ResourceKey<LootTable> lootKey;
         /*?} else*//*private ResourceLocation lootKey;*/

        private final List<FishingCondition> conditions = new ArrayList<>();
        private final List<FishingModifier> modifiers = new ArrayList<>();

        private double weight = 1.0;
        private double quality = 0.0;

        private Builder() {}

        public Builder block(Block block) {
            this.block = BlockStateProvider.simple(block);
            return this;
        }

        public Builder block(BlockStateProvider block) {
            this.block = block;
            return this;
        }

        public Builder lootTable(LootTableRef lootKey) {
            return this.lootTable(lootKey.get());
        }

        //? if >=1.21 {
        public Builder lootTable(ResourceKey<LootTable> lootKey) {
            this.lootKey = lootKey;
            return this;
        }
        //?} else {
        /*public Builder lootTable(ResourceKey<LootTable> lootKey) {
            return this.lootTable(lootKey.location());
        }

        public Builder lootTable(ResourceLocation lootKey) {
            this.lootKey = lootKey;
            return this;
        }
        *///?}

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

        public Builder saltwater() {
            return condition(new SaltwaterCondition());
        }

        public Builder freshwater() {
            return condition(new FreshwaterCondition());
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

        public Builder lava() {
            return medium(FishingMedium.LAVA);
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

        public Builder openWater(boolean inOpenWater) {
            return condition(new OpenWaterCondition(inOpenWater));
        }

        public Builder dimensions(List<ResourceKey<Level>> dimensions) {
            return condition(new DimensionsCondition(dimensions));
        }

        public Builder medium(FishingMedium medium) {
            return condition(new FishingMediumCondition(medium));
        }

        public Builder selectionWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public Builder selectionQuality(double quality) {
            this.quality = quality;
            return this;
        }

        public void build(SimpleDataOutput<CrateData> output) {
            this.build(this.lootKey/*? if >=1.21 {*/.location()/*?}*/, output);
        }

        public void build(String path, SimpleDataOutput<CrateData> output) {
            this.build(Tide.resource(path), output);
        }

        public void build(ResourceLocation path, SimpleDataOutput<CrateData> output) {
            output.accept(path, build());
        }

        public CrateData build() {
            if (block == null) throw new IllegalStateException("Crate block provider must be provided");
            return new CrateData(block, Optional.ofNullable(lootKey),
                    Optional.empty(),
                    ImmutableList.copyOf(conditions),
                    ImmutableList.copyOf(modifiers),
                    weight, quality
            );
        }
    }
}
