package com.li64.tide.data.loaders;

import com.google.gson.JsonParser;
import com.li64.tide.Tide;
import com.li64.tide.data.ValidatableDataEntry;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.util.function.Consumer;

public abstract class AbstractDataLoader<T, R> extends SimplePreparableReloadListener<R> {
    protected final String directory;
    protected R data;

    public AbstractDataLoader(String directory) {
        this.directory = directory;
    }

    public abstract void decode(FriendlyByteBuf buf);
    public abstract void encode(FriendlyByteBuf buf);

    protected abstract Codec<T> getEntryCodec();
    protected abstract int getEntryCount(R data);

    protected abstract R prepareData(ResourceManager manager, FileToIdConverter lister);

    @Override
    protected @NotNull R prepare(@NotNull ResourceManager manager, @NotNull ProfilerFiller profiler) {
        FileToIdConverter lister = FileToIdConverter.json(this.directory);
        return prepareData(manager, lister);
    }

    @Override
    protected void apply(@NotNull R data, @NotNull ResourceManager manager, @NotNull ProfilerFiller profiler) {
        this.setData(data);
        Tide.LOG.info("Loaded {} entries from '{}'", getEntryCount(data), getDirectory());
    }

    protected void parseOrLog(ResourceLocation key, Reader reader, Consumer<T> onSuccess) {
        var json = JsonParser.parseReader(reader);
        getEntryCodec().parse(JsonOps.INSTANCE, json)
                .resultOrPartial(error -> {
                    if (Tide.SERVER_CONFIG.general.logDataErrors)
                        Tide.LOG.error("Skipping invalid data entry '{}' due to parsing error: {}", key, error);
                })
                .map(data -> {
                    if (data instanceof ValidatableDataEntry entry && !entry.isValid()) {
                        if (Tide.SERVER_CONFIG.general.logDataErrors)
                            Tide.LOG.error("Skipping invalid data entry '{}': {}", key, entry.invalidReason());
                        return null;
                    }
                    return data;
                })
                .ifPresent(onSuccess);
    }

    public ResourceLocation getDirectory() {
        return Tide.resource(directory);
    }

    public R get() {
        return this.data;
    }

    public void setData(R data) {
        this.data = data;
        this.onDataUpdated();
    }

    public void onDataUpdated() {}
}
