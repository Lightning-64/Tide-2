package com.li64.tide.registries;

import com.li64.tide.Tide;
import com.li64.tide.registries.entities.misc.StarArrow;
import com.li64.tide.registries.entities.misc.LootCrateEntity;
import com.li64.tide.registries.entities.misc.fishing.HookAccessor;
import com.li64.tide.registries.entities.misc.fishing.TideFishingHook;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.Item;

//? if >=1.21 {
import net.minecraft.world.item.component.CustomData;
import net.minecraft.core.component.DataComponents;
//?}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TideEntityTypes {
    public static final HashMap<String, EntityType<?>> ENTITY_TYPES = new HashMap<>();
    public static final List<EntityType<?>> FISH_ENTITIES = new ArrayList<>();

    public static final EntityType<TideFishingHook> FISHING_BOBBER = register(
            "fishing_bobber", EntityType.Builder.<TideFishingHook>of(TideFishingHook::new, MobCategory.MISC)
                    .noSave().noSummon().sized(0.25f, 0.25f)
                    .clientTrackingRange(4).updateInterval(5));

    public static final EntityType<HookAccessor> HOOK_ACCESSOR = register(
            "fishing_bobber_accessor", EntityType.Builder.<HookAccessor>of(HookAccessor::new, MobCategory.MISC)
                    .noSave().noSummon().sized(0.25f, 0.25f)
                    .clientTrackingRange(4).updateInterval(5));

    public static final EntityType<StarArrow> STAR_ARROW = register(
            "star_arrow", EntityType.Builder.<StarArrow>of(StarArrow::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(4).updateInterval(20));

    public static final EntityType<FallingBlockEntity> LOOT_CRATE = register(
            "loot_crate", EntityType.Builder.of(LootCrateEntity::new, MobCategory.MISC)
                    .sized(0.98f, 0.98f).clientTrackingRange(10)
                    .updateInterval(20));

    public static <T extends Entity> EntityType<T> register(String key, EntityType.Builder<T> builder) {
        EntityType<T> type = builder.build(key);
        ENTITY_TYPES.put(key, type);
        return register(key, type);
    }

    public static <T extends Entity> EntityType<T> register(String key, EntityType<T> type) {
        return Tide.PLATFORM.register(BuiltInRegistries.ENTITY_TYPE, Tide.resource(key), type);
    }

    public static void init() {
        TideFish.ENTITY_DATA.forEach(TideEntityTypes::buildFishEntity);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Mob> void buildFishEntity(String key, TideFish.FishEntityData<T> data) {
        // construct entity type
        EntityType<T> entityType = (EntityType<T>) register(key, ENTITY_TYPES
                .computeIfAbsent(key, k -> data.createEntityBuilder().build(k)));
        if (Tide.PLATFORM.isDevEnvironment()) FISH_ENTITIES.add(entityType);

        // build and register attributes
        TideEntityAttributes.registerAttributes(entityType, data.attributesBuilder().build());
    }
}