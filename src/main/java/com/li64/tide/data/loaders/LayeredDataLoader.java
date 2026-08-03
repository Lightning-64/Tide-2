package com.li64.tide.data.loaders;

import com.li64.tide.Tide;
import com.li64.tide.data.SendableDataMap;
import com.mojang.serialization.Codec;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.util.*;

public abstract class LayeredDataLoader<T> extends AbstractDataLoader<T, SendableDataMap<T>> {
    public LayeredDataLoader(String directory) {
        super(directory);
        this.data = new SendableDataMap<>(Map.of(), this.getEntryCodec());
    }

    public static <T> LayeredDataLoader<T> of(String directory, Codec<T> codec) {
        return new LayeredDataLoader<>(directory) {
            @Override protected Codec<T> getEntryCodec() { return codec; }
        };
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        this.setData(SendableDataMap.decode(buf, getEntryCodec()));
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        this.data.encode(buf);
    }

    @Override
    protected SendableDataMap<T> prepareData(ResourceManager manager, FileToIdConverter lister) {
        Map<ResourceLocation, List<SourcedValue<T>>> collected = new HashMap<>();

        lister.listMatchingResourceStacks(manager).forEach((path, resources) -> {
            ResourceLocation key = lister.fileToId(path);
            resources.forEach(resource -> {
                try (Reader reader = resource.openAsReader()) {
                    parseOrLog(path, reader, value -> collected
                            .computeIfAbsent(key, k -> new ArrayList<>())
                            .add(new SourcedValue<>(resource.sourcePackId(), value)));
                }
                catch (Exception e) { Tide.LOG.error("Failed to read '{}'", path, e); }
            });
        });

        return new SendableDataMap<>(mergeDuplicates(collected), getEntryCodec());
    }

    @Override
    protected int getEntryCount(SendableDataMap<T> data) {
        return data.count();
    }

    private Map<ResourceLocation, T> mergeDuplicates(Map<ResourceLocation, List<SourcedValue<T>>> input) {
        Map<ResourceLocation, T> output = new TreeMap<>();
        input.forEach((key, list) -> output.put(key, list.stream()
                .sorted(Comparator.comparingInt(SourcedValue::loadOrder))
                .map(SourcedValue::value)
                .reduce((a, b) -> b).orElseThrow()));
        return output;
    }

    protected record SourcedValue<T>(String sourcePackId, T value) {
        public int loadOrder() {
            if (sourcePackId.equals("tide")) return -2;
            if (sourcePackId.equals("minecraft")) return -1;
            return 0;
        }

        @Override
        public @NotNull String toString() {
            return "SourcedValue{" +
                    "sourcePackId='" + sourcePackId + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}