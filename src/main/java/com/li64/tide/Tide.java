package com.li64.tide;

import com.li64.tide.config.TideClientConfig;
import com.li64.tide.config.TideConfig;
import com.li64.tide.config.TideServerConfig;
import com.li64.tide.data.TideFishingManager;
import com.li64.tide.loaders.LoaderPlatform;
import com.li64.tide.loaders.NetworkPlatform;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*? if fabric*/ import com.li64.tide.loaders.fabric.*;
/*? if neoforge*/ /*import com.li64.tide.loaders.neoforge.*;*/
/*? if forge*/ /*import com.li64.tide.loaders.forge.*;*/

// looks like you've stumbled upon the code for
// .___________. __   _______   _______
// |           ||  | |       \ |   ____|
// `---|  |----`|  | |  .--.  ||  |__
//     |  |     |  | |  |  |  ||   __|
//     |  |     |  | |  '--'  ||  |____
//     |__|     |__| |_______/ |_______|  (v2)
// everyone's favorite fishing mod! (probably)
// released in 2023 by Lightning64 (v2 in 2026)
public class Tide {
    public static final String MOD_ID = "tide";
    public static final String MOD_NAME = "Tide";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final LoaderPlatform PLATFORM;
    public static final NetworkPlatform NETWORK;
    static {
        //? if fabric {
        PLATFORM = new FabricPlatform();
        NETWORK = /*? if >=1.21 {*/new FabricNetworkPlatform();
                  /*?} else*/ /*new FabricLegacyNetworkPlatform();*/
        //?}
        //? if neoforge {
        /*PLATFORM = new NeoforgePlatform();
        NETWORK = new NeoforgeNetworkPlatform();
        *///?}
        //? if forge {
        /*PLATFORM = new ForgePlatform();
        NETWORK = new ForgeNetworkPlatform();
        *///?}
    }

    public static TideConfig CONFIG;
    public static TideClientConfig CLIENT_CONFIG;
    public static TideServerConfig SERVER_CONFIG;

    public static TideFishingManager FISHING_MANAGER;

    public static ResourceLocation resource(String path) {
        return resource(MOD_ID, path);
    }

    public static ResourceLocation resource(String namespace, String path) {
        /*? if >=1.21 {*/ return ResourceLocation.fromNamespaceAndPath(namespace, path);
        /*?} else*/ /*return new ResourceLocation(namespace, path);*/
    }

    public static void initialize() {
        Tide.setupConfigs();
        LOG.info("Initialized Tide on {} {}", PLATFORM.getPlatformName(), PLATFORM.getMCVersion());
    }

    public static void setupConfigs() {
        if (CONFIG == null) {
            ConfigHolder<TideConfig> holder = AutoConfig.register(TideConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new));
            holder.registerLoadListener(Tide::updateConfig);
            holder.registerSaveListener(Tide::updateConfig);

            CONFIG = holder.getConfig();

            updateConfig(holder, CONFIG);
        }
    }

    public static InteractionResult updateConfig(ConfigHolder<TideConfig> holder, TideConfig config) {
        CLIENT_CONFIG = config.client();
        SERVER_CONFIG = config.server();
        return InteractionResult.SUCCESS;
    }
}