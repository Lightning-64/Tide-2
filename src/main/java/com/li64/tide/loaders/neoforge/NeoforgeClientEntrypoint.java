//? if neoforge {
/*package com.li64.tide.loaders.neoforge;

import com.li64.tide.Tide;
import com.li64.tide.client.FishDisplayRenderer;
import com.li64.tide.client.TideCoreShaders;
import com.li64.tide.client.TideItemModelProperties;
import com.li64.tide.client.VoidParticleSpawner;
import com.li64.tide.client.gui.TideMenuTypes;
import com.li64.tide.client.gui.screens.AnglingTableScreen;
import com.li64.tide.config.TideConfig;
import com.li64.tide.data.DoubleJumper;
import com.li64.tide.data.rods.ClientFishingRodTooltip;
import com.li64.tide.data.rods.FishingRodTooltip;
import com.li64.tide.events.TideClientEventHandler;
import com.li64.tide.registries.TideBlockEntities;
import com.li64.tide.registries.TideEntityModels;
import com.li64.tide.registries.TideItems;
import com.li64.tide.registries.TideParticleTypes;
import com.li64.tide.registries.particles.MagicChain;
import com.li64.tide.registries.particles.VoidRipple;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = Tide.MOD_ID, value = Dist.CLIENT)
public class NeoforgeClientEntrypoint {
    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        NeoforgeEntrypoint.CONTAINER.registerExtensionPoint(
                IConfigScreenFactory.class,
                (mc, screen) -> AutoConfig.getConfigScreen(TideConfig.class, screen).get()
        );

        event.enqueueWork(TideItemModelProperties::registerAll);
    }

    @SubscribeEvent
    public static void registerShaders(final RegisterShadersEvent event) {
        try {
            event.registerShader(new ShaderInstance(event.getResourceProvider(), Tide.resource("full_white"),
                    DefaultVertexFormat.POSITION_TEX_COLOR), instance -> TideCoreShaders.FULL_WHITE = instance);
            event.registerShader(new ShaderInstance(event.getResourceProvider(), Tide.resource("full_white_item"),
                    DefaultVertexFormat.NEW_ENTITY), instance -> TideCoreShaders.FULL_WHITE_ITEM = instance);
        } catch (Exception e) {
            Tide.LOG.error("Failed to load Tide shaders! {}", e.toString());
        }
    }

    @SubscribeEvent
    public static void registerParticleProviders(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(TideParticleTypes.VOID_RIPPLE_LARGE, VoidRipple.LargeProvider::new);
        event.registerSpriteSet(TideParticleTypes.VOID_RIPPLE_SMALL, VoidRipple.SmallProvider::new);
        event.registerSpriteSet(TideParticleTypes.MAGIC_CHAIN, MagicChain.Provider::new);
    }

    @SubscribeEvent
    public static void registerEntityModels(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        if (TideEntityModels.LAYER_DEFS.isEmpty()) TideEntityModels.init();
        TideEntityModels.LAYER_DEFS.forEach(event::registerLayerDefinition);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        if (TideEntityModels.RENDERERS.isEmpty()) TideEntityModels.init();
        TideEntityModels.RENDERERS.forEach(reg -> registerEntityRenderer(reg, event));
        event.registerBlockEntityRenderer(TideBlockEntities.FISH_DISPLAY, FishDisplayRenderer::new);
    }

    public static <T extends Entity> void registerEntityRenderer(TideEntityModels.RendererRegistration<T> reg, EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(reg.entityType(), reg.renderer());
    }

    @SubscribeEvent
    public static void registerMenuScreens(final RegisterMenuScreensEvent event) {
        event.register(TideMenuTypes.ANGLING_TABLE, AnglingTableScreen::new);
    }

    @SubscribeEvent
    public static void registerClientTooltipComponents(final RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(FishingRodTooltip.class, tooltip -> new ClientFishingRodTooltip(tooltip.slots(), tooltip.contents()));
    }

    @SubscribeEvent
    public static void itemTooltipEvent(final ItemTooltipEvent event) {
        TideClientEventHandler.onTooltipRender(event.getItemStack(), event.getToolTip());
    }

    @SubscribeEvent
    public static void endClientTick(final ClientTickEvent.Post event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;
        if (!Minecraft.getInstance().isPaused() && player.getRandom().nextFloat() > 0.5f) {
            VoidParticleSpawner.spawnParticles(Minecraft.getInstance().player, 1);
        }
        if (player.getItemBySlot(EquipmentSlot.FEET).is(TideItems.DRAGONFIN_BOOTS)
                && player instanceof DoubleJumper jumper) {
            if (!jumper.tide$hasDoubleJump() && player.onGround()) jumper.tide$restoreDoubleJump();
            if (player.input.jumping) jumper.tide$doDoubleJump();
            jumper.tide$setWasJumpPressed(player.input.jumping);
        }
    }
}
*///?}