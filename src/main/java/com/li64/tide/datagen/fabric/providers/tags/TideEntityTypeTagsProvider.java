//? if fabric {
package com.li64.tide.datagen.fabric.providers.tags;

import com.li64.tide.registries.TideEntityTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class TideEntityTypeTagsProvider extends FabricTagProvider<EntityType<?>> {
    public TideEntityTypeTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Registries.ENTITY_TYPE, registries);
    }

    @Override
    public @NotNull String getName() {
        return "Entity Type Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(EntityTypeTags.ARROWS).add(TideEntityTypes.STAR_ARROW);

        //? if >=1.21 {
        var aquatic = getOrCreateTagBuilder(EntityTypeTags.AQUATIC);
        TideEntityTypes.FISH_ENTITIES.forEach(aquatic::add);
        //?}
    }
}
//?}