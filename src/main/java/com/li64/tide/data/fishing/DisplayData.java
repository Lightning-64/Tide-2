package com.li64.tide.data.fishing;

import com.li64.tide.registries.blocks.FishDisplayShape;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

import java.util.Optional;

public record DisplayData(ResourceKey<EntityType<?>> entityKey, Optional<CompoundTag> nbt, FishDisplayShape shape, float x, float y, float z, float roll, float pitch, float yaw) {
    public static final Codec<DisplayData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceKey.codec(Registries.ENTITY_TYPE).fieldOf("entity").forGetter(DisplayData::entityKey),
            CompoundTag.CODEC.optionalFieldOf("nbt").forGetter(DisplayData::nbt),
            FishDisplayShape.CODEC.optionalFieldOf("shape", FishDisplayShape.SHAPE_1x1).forGetter(DisplayData::shape),
            Codec.FLOAT.optionalFieldOf("x", 0f).forGetter(DisplayData::x),
            Codec.FLOAT.optionalFieldOf("y", 0f).forGetter(DisplayData::y),
            Codec.FLOAT.optionalFieldOf("z", 0f).forGetter(DisplayData::z),
            Codec.FLOAT.optionalFieldOf("roll", 0f).forGetter(DisplayData::roll),
            Codec.FLOAT.optionalFieldOf("pitch", 0f).forGetter(DisplayData::pitch),
            Codec.FLOAT.optionalFieldOf("yaw", 0f).forGetter(DisplayData::yaw)
    ).apply(instance, DisplayData::new));

    public Holder<EntityType<?>> entityHolder() {
        return BuiltInRegistries.ENTITY_TYPE.getHolder(entityKey).orElseThrow();
    }

    public EntityType<?> entityType() {
        return BuiltInRegistries.ENTITY_TYPE.get(entityKey);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ResourceKey<EntityType<?>> entityType;
        private CompoundTag nbt;
        private FishDisplayShape shape = FishDisplayShape.SHAPE_1x1;
        private float x, y, z;
        private float roll, pitch, yaw;

        private Builder() {}

        public Builder entityType(EntityType<?> entityType) {
            return this.entityType(BuiltInRegistries.ENTITY_TYPE.getResourceKey(entityType).orElseThrow());
        }

        public Builder entityType(ResourceLocation id) {
            this.entityType = ResourceKey.create(Registries.ENTITY_TYPE, id);
            return this;
        }

        public Builder entityType(ResourceKey<EntityType<?>> key) {
            this.entityType = key;
            return this;
        }

        public Builder nbt(CompoundTag nbt) {
            this.nbt = nbt;
            return this;
        }

        public Builder offsets(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        public Builder rotation(float roll, float pitch, float yaw) {
            this.roll = roll;
            this.pitch = pitch;
            this.yaw = yaw;
            return this;
        }

        public Builder shape(FishDisplayShape shape) {
            this.shape = shape;
            return this;
        }

        public DisplayData build() {
            return new DisplayData(this.entityType, Optional.ofNullable(nbt), this.shape, this.x, this.y, this.z, this.roll, this.pitch, this.yaw);
        }
    }
}
