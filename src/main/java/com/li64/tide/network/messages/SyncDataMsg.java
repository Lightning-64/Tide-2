package com.li64.tide.network.messages;

import com.google.gson.Gson;
import com.li64.tide.Tide;
import com.li64.tide.config.TideConfig;
import com.li64.tide.config.TideServerConfig;
import com.li64.tide.data.TideData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public record SyncDataMsg() implements TidePacketPayload {
    public static final ResourceLocation ID = Tide.resource("sync_data");
    @Override public ResourceLocation id() { return ID; }

    private static final Gson GSON = new Gson();

    public SyncDataMsg(FriendlyByteBuf buf) {
        this();
        TideServerConfig.readFromPacket(buf, GSON);
        TideData.readFromPacket(buf);
    }

    public static void encode(SyncDataMsg message, FriendlyByteBuf buf) {
        TideServerConfig.writeToPacket(buf, GSON);
        TideData.writeToPacket(buf);
    }

    public static void handle(SyncDataMsg message, Player player) {}
}