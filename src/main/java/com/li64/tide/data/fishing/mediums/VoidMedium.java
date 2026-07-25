package com.li64.tide.data.fishing.mediums;

import com.li64.tide.Tide;
import com.li64.tide.config.TideServerConfig;
import com.li64.tide.registries.TideParticleTypes;
import com.li64.tide.registries.entities.misc.fishing.TideFishingHook;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;

public class VoidMedium implements FishingMedium {
    private final Map<ResourceKey<Level>, TideServerConfig.General.VoidHeightEntry> CACHE = new HashMap<>();
    private final TideServerConfig.General.VoidHeightEntry DEFAULT_ENTRY = new TideServerConfig.General.VoidHeightEntry();
    private final ResourceLocation id = Tide.resource("void");

    public VoidMedium() {
        Tide.SERVER_CONFIG.general.fishableVoidHeights.forEach(entry -> {
            ResourceKey<Level> dimension = ResourceKey.create(Registries.DIMENSION, ResourceLocation.tryParse(entry.dimension));
            CACHE.put(dimension, entry);
        });
    }

    @Override
    public ResourceLocation id() {
        return id;
    }

    public boolean isInVoid(BlockPos pos, Level level) {
        return isInVoid(pos.getCenter(), level);
    }

    public boolean isInVoid(Vec3 pos, Level level) {
        return pos.y() <= getVoidSurface(level);
    }

    public int getVoidSurface(Level level) {
        TideServerConfig.General.VoidHeightEntry entry = CACHE.getOrDefault(level.dimension(), DEFAULT_ENTRY);
        if (entry.type == TideServerConfig.General.VoidHeightEntry.Type.RELATIVE_TO_BOTTOM) return level.getMinBuildHeight() + entry.height;
        if (entry.type == TideServerConfig.General.VoidHeightEntry.Type.RELATIVE_TO_TOP) return level.getMaxBuildHeight() + entry.height;
        return entry.height;
    }

    @Override
    public boolean isAt(BlockPos pos, ServerLevel level) {
        return pos.getY() <= getVoidSurface(level);
    }

    @Override
    public boolean canFishIn(TideFishingHook hook) {
        return getHeight(hook) > 0.0f && hook.canFishInVoid();
    }

    @Override
    public float getHeight(TideFishingHook hook) {
        return Math.min((float) (getVoidSurface(hook.level()) - hook.getY()), 1f);
    }

    @Override
    public double biteTimeMultiplier() {
        return 1.8;
    }

    @Override
    public void drawAmbientSplash(TideFishingHook hook, float angleRad, float distance, double x, double y, double z) {
        if (!(hook.level() instanceof ServerLevel level)) return;
        level.sendParticles(TideParticleTypes.VOID_RIPPLE_SMALL, x, y, z, 0,
                0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public void drawFishTrail(TideFishingHook hook, float angleRad, float sin, float cos, double fishX, double fishY, double fishZ) {
        if (!(hook.level() instanceof ServerLevel level)) return;

        RandomSource random = hook.getRandom();

        if (random.nextFloat() < 0.35f) level.sendParticles(TideParticleTypes.VOID_RIPPLE_SMALL,
                fishX, fishY - 0.5, fishZ, 1, 0.0, 0.0, 0.0, 0.0);

        float scaledSin = sin * 0.04f;
        float scaledCos = cos * 0.04f;
        level.sendParticles(ParticleTypes.ENCHANTED_HIT, fishX, fishY - 0.1, fishZ, 0, scaledCos, 0.0, -scaledSin, 1.0);
    }

    @Override
    public void onFishBite(TideFishingHook hook) {
        if (!(hook.level() instanceof ServerLevel level)) return;

        RandomSource random = hook.getRandom();
        hook.playSound(SoundEvents.FISHING_BOBBER_SPLASH, 0.25f, 1.0f + (random.nextFloat() - random.nextFloat()) * 0.4f);

        double y = hook.getY() + 0.5;
        level.sendParticles(TideParticleTypes.VOID_RIPPLE_LARGE, hook.getX(), y - 0.5, hook.getZ(), 0, 0.0, 0.0, 0.0, 0.2);
    }
}
