package com.li64.tide.config;

import com.li64.tide.Tide;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = Tide.MOD_ID)
public class TideConfig extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category(Tide.MOD_ID + "_client")
    @ConfigEntry.Gui.TransitiveObject
    TideClientConfig client = new TideClientConfig();

    public TideClientConfig client() {
        return this.client;
    }

    @ConfigEntry.Category(Tide.MOD_ID + "_server")
    @ConfigEntry.Gui.TransitiveObject
    TideServerConfig server = new TideServerConfig();

    public TideServerConfig server() {
        return this.server;
    }
}