package com.li64.tide.data.loaders;

import com.google.common.collect.ImmutableList;
import com.li64.tide.Tide;
import com.mojang.serialization.Codec;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public abstract class FlatDataLoader<T> extends AbstractDataLoader<T, List<T>> {
    public FlatDataLoader(String directory) {
        super(directory);
        this.data = List.of();
    }

    public static <T> FlatDataLoader<T> of(String directory, Codec<T> codec) {
        return new FlatDataLoader<>(directory) {
            @Override protected Codec<T> getEntryCodec() { return codec; }
        };
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        ArrayList<T> tempValues = new ArrayList<>();
        int size = buf.readInt();
        for (int i = 0; i < size; i++) {
            tempValues.add(buf.readJsonWithCodec(getEntryCodec()));
        }
        this.setData(ImmutableList.copyOf(tempValues));
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(data.size());
        data.forEach(entry -> buf.writeJsonWithCodec(getEntryCodec(), entry));
    }

    @Override
    protected List<T> prepareData(ResourceManager manager, FileToIdConverter lister) {
        List<T> output = new ArrayList<>();
        lister.listMatchingResources(manager).forEach((path, resource) -> {
            try (Reader reader = resource.openAsReader()) {
                parseOrLog(path, reader, output::add);
            } catch (Exception e) {
                Tide.LOG.error("Failed to read '{}'", path, e);
            }
        });
        return output;
    }

    @Override
    protected int getEntryCount(List<T> data) {
        return data.size();
    }
}