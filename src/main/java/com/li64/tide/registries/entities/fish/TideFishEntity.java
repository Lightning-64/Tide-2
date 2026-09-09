package com.li64.tide.registries.entities.fish;

import com.li64.tide.compat.seasons.SeasonsCompat;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.data.fishing.FishingContext;
import com.li64.tide.data.fishing.conditions.FishingConditionType;
import com.li64.tide.data.fishing.mediums.FishingMedium;
import com.li64.tide.data.fishing.mediums.VoidMedium;
import com.li64.tide.util.TideUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class TideFishEntity extends AbstractSchoolingFish {
    private final Item bucketItem;

    public TideFishEntity(EntityType<? extends AbstractSchoolingFish> entityType, Level level) {
        super(entityType, level);
        ResourceLocation key = BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
        this.bucketItem = BuiltInRegistries.ITEM.getOptional(key.withSuffix("_bucket")).orElseThrow();
    }

    @Override
    protected @NotNull SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(@NotNull DamageSource source) {
        return SoundEvents.COD_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    @Override
    public @NotNull ItemStack getBucketItemStack() {
        return bucketItem.getDefaultInstance();
    }

    @Override
    public @NotNull AbstractSchoolingFish startFollowing(AbstractSchoolingFish leader) {
        if (leader.getType() == this.getType()) return super.startFollowing(leader);
        return this;
    }

    @SuppressWarnings("deprecation")
    public static <T extends Mob> boolean checkWaterFishSpawnRules(EntityType<T> entityType, ServerLevelAccessor level,
                                                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        if (pos.getY() > level.getSeaLevel()
                || !level.getFluidState(pos.below()).is(FluidTags.WATER)
                || !level.getBlockState(pos.above()).is(Blocks.WATER)) return false;

        return checkTideFishSpawnRules(entityType, level, pos, random, FishingMedium.WATER);
    }

    @SuppressWarnings("deprecation")
    public static <T extends Mob> boolean checkLavaFishSpawnRules(EntityType<T> entityType, ServerLevelAccessor level,
                                                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        if (pos.getY() > level.getSeaLevel()
                || !level.getFluidState(pos.below()).is(FluidTags.LAVA)
                || !level.getBlockState(pos.above()).is(Blocks.LAVA)) return false;

        return checkTideFishSpawnRules(entityType, level, pos, random, FishingMedium.LAVA);
    }

    public static <T extends Mob> boolean checkVoidFishSpawnRules(EntityType<T> entityType, ServerLevelAccessor level,
                                                                   MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        if (pos.getY() > VoidMedium.VOID.getVoidSurface(level.getLevel())
                || !level.getBlockState(pos).isAir()) return false;

        return checkTideFishSpawnRules(entityType, level, pos, random, FishingMedium.VOID);
    }

    public static <T extends Mob> boolean checkTideFishSpawnRules(EntityType<T> entityType, ServerLevelAccessor level,
                                                                  BlockPos pos, RandomSource random, FishingMedium medium) {
        FishData data = FishData.get(entityType).orElseThrow();

        Holder<Biome> biome = level.getBiome(pos);
        Holder<Biome> nearestBiome = data.conditions().stream().anyMatch(c ->
                c.containsType(FishingConditionType.BIOME_WHITELIST))
                ? TideUtils.findClosestNonWaterBiome(level.getLevel(), pos, 12, 3).orElse(biome) : biome;

        FishingContext context = new FishingContext(
                level.getLevel(), null, null, random, pos.getCenter(), pos, 0,
                medium.id().getPath(), biome, nearestBiome, level.getLevel().dimension(),
                TideUtils.getTemperatureAt(pos, level.getLevel()),
                level.getMoonPhase(), SeasonsCompat.getSeason(level.getLevel())
        );

        boolean conditionsMatch = data.shouldKeep(context);
        boolean nonzeroWeight = (data.weight(context) / data.weight()) > 0.1;
//        String info = "Failed, no conditions or modifiers matched";
//        if (conditionsMatch && nonzeroWeight) info = "Success!";
//        if (!conditionsMatch && nonzeroWeight) info = "Failed, conditions didn't match";
//        if (conditionsMatch && !nonzeroWeight) info = "Failed, modifiers didn't match";
//        Tide.LOG.info("Trying to spawn tide fish '{}' at {}. {}", entityType, pos, info);
        return conditionsMatch && nonzeroWeight;
    }
}