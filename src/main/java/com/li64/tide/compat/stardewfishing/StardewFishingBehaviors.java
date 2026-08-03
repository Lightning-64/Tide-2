//? if fabric {
package com.li64.tide.compat.stardewfishing;

import com.li64.tide.Tide;
import com.li64.tide.data.fishing.FishData;
import com.li64.tide.registries.TideFish;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class StardewFishingBehaviors implements DataProvider {
    private final ResourceLocation LOCATION = Tide.resource("stardew_fishing", "fish_behaviors");
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final PackOutput.PathProvider pathProvider;

    public StardewFishingBehaviors(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this.pathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "");
        this.registries = registries;
    }

    @Override
    public final @NotNull CompletableFuture<?> run(CachedOutput output) {
        return this.registries.thenCompose((provider) -> this.run(output, provider));
    }

    private void generate(HashMap<ResourceLocation, MockFishBehavior> output) {//, ArrayList<ResourceLocation> fish) {
        TideFish.DATA_BUILDERS.forEach((item, constructor) -> {
            FishData.Builder builder = FishData.builder();
            constructor.accept(builder.fish(item));
            FishData data = builder.build();
            output.put(BuiltInRegistries.ITEM.getKey(item), MockFishBehavior.fromFishData(data));
//            var op = data.conditions().stream().filter(condition -> condition instanceof FishingMediumCondition).findFirst();
//            if (op.isPresent() && Objects.equals(((FishingMediumCondition) op.get()).getMediumId(), "void")) {
//                fish.add(BuiltInRegistries.ITEM.getKey(item));
//            }
        });
    }

    @SuppressWarnings("unused")
    public CompletableFuture<?> run(final CachedOutput output, final HolderLookup.Provider registries) {
        HashMap<ResourceLocation, MockFishBehavior> behaviors = new HashMap<>();
        //ArrayList<ResourceLocation> fish = new ArrayList<>();
        this.generate(behaviors);//, fish);
        //Collections.sort(fish);
        MockFishBehaviorList list = new MockFishBehaviorList(false, behaviors, Optional.empty());//, fish);
        //? if >=1.21 {
        return DataProvider.saveStable(output, registries, MockFishBehaviorList.CODEC, list, pathProvider.json(LOCATION));
        //?} else {
        /*return DataProvider.saveStable(output, MockFishBehaviorList.CODEC.encodeStart(JsonOps.INSTANCE, list)
                .resultOrPartial(Tide.LOG::error).orElseThrow(), pathProvider.json(LOCATION));
        *///?}
    }

    private record MockFishBehaviorList(boolean replace, Map<ResourceLocation, MockFishBehavior> behaviors, Optional<MockFishBehavior> defaultBehavior) {//, List<ResourceLocation> fish) {
        public static final Codec<MockFishBehaviorList> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                Codec.BOOL.optionalFieldOf("replace", false).forGetter(MockFishBehaviorList::replace),
                Codec.unboundedMap(ResourceLocation.CODEC, MockFishBehavior.CODEC).fieldOf("behaviors").forGetter(MockFishBehaviorList::behaviors),
                MockFishBehavior.CODEC.optionalFieldOf("defaultBehavior").forGetter(MockFishBehaviorList::defaultBehavior)
                //ResourceLocation.CODEC.listOf().optionalFieldOf("fish", List.of()).forGetter(MockFishBehaviorList::fish)
        ).apply(inst, MockFishBehaviorList::new));
    }

    private record MockFishBehavior(int idleTime, float topSpeed, float upAcceleration, float downAcceleration, int avgDistance, int moveVariation) {
        public static final Codec<MockFishBehavior> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                Codec.INT.fieldOf("idle_time").forGetter(MockFishBehavior::idleTime),
                Codec.FLOAT.fieldOf("top_speed").forGetter(MockFishBehavior::topSpeed),
                Codec.FLOAT.fieldOf("up_acceleration").forGetter(MockFishBehavior::upAcceleration),
                Codec.FLOAT.fieldOf("down_acceleration").forGetter(MockFishBehavior::downAcceleration),
                Codec.INT.fieldOf("avg_distance").forGetter(MockFishBehavior::avgDistance),
                Codec.INT.fieldOf("move_variation").forGetter(MockFishBehavior::moveVariation)
        ).apply(inst, MockFishBehavior::new));

        public static MockFishBehavior fromFishData(FishData data) {
            int idleTime = Math.max(Math.round(20 - (5f * data.speed())), 1);
            float topSpeed = 2.5f * data.speed();
            float upAcceleration = 0.05f + (0.2f * data.speed());
            float downAcceleration = 0.05f + (0.2f * data.speed());
            int avgDistance = Math.max(Math.round(50f * data.strength()), 2);
            int moveVariation = Math.round((float)Math.exp(4.5f * data.strength() - 1f));
            return new MockFishBehavior(idleTime, topSpeed, upAcceleration, downAcceleration, avgDistance, moveVariation);
        }
    }

    @Override
    public @NotNull String getName() {
        return "Stardew Fishing Behaviors";
    }
}
//?}