//? if neoforge {
/*package com.li64.tide.network.messages;

import com.li64.tide.Tide;
import com.li64.tide.client.TideClientHelper;
import com.wdiscute.starcatcher.fish.FishProperties;
import com.wdiscute.starcatcher.modifiers.Modifier;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public record StarcatcherStartMinigameMsg(FishProperties properties, List<Modifier> modifiers) implements TidePacketPayload {
    public static final ResourceLocation ID = Tide.resource("start_starcatcher_minigame");
    @Override public ResourceLocation id() { return ID; }

    public StarcatcherStartMinigameMsg(RegistryFriendlyByteBuf buf) {
        this(
            FishProperties.STREAM_CODEC.decode(buf),
            ByteBufCodecs.fromCodec(Modifier.CODEC.listOf()).decode(buf)
        );
    }

    public static void encode(StarcatcherStartMinigameMsg message, RegistryFriendlyByteBuf buf) {
        FishProperties.STREAM_CODEC.encode(buf, message.properties);
        ByteBufCodecs.fromCodec(Modifier.CODEC.listOf()).encode(buf, message.modifiers);
    }

    public static void handle(StarcatcherStartMinigameMsg message, Player player) {
        TideClientHelper.startStarcatcherMinigame(message, player);
    }
}
*///?} else if forge {

//?}
