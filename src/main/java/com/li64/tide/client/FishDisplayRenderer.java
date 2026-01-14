package com.li64.tide.client;

import com.li64.tide.compat.CompatHelper;
import com.li64.tide.data.fishing.DisplayData;
import com.li64.tide.registries.blocks.FishDisplayBlock;
import com.li64.tide.registries.blocks.entities.FishDisplayBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public record FishDisplayRenderer(EntityRenderDispatcher entityRenderer) implements BlockEntityRenderer<FishDisplayBlockEntity> {
    public FishDisplayRenderer(BlockEntityRendererProvider.Context entityRenderer) {
        this(entityRenderer.getEntityRenderer());
    }

    @Override
    public void render(@NotNull FishDisplayBlockEntity display, float partialTick, @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (display.isEmpty()) return;
        DisplayData displayData = display.getDisplayData();
        poseStack.pushPose();

        Direction dir = display.getBlockState().getValue(FishDisplayBlock.FACING);
        poseStack.translate(0.5f, 0.5f, 0.5f); // center rotation
        poseStack.mulPose(Axis.YP.rotationDegrees(-dir.toYRot() + 90)); // rotate based on facing direction
        poseStack.translate(0.5f, 0.0f, 0.0f); // move towards display
        poseStack.pushPose();

        // apply translations
        poseStack.translate(-displayData.z(), displayData.y(), displayData.x());
        // apply rotations
        poseStack.mulPose(Axis.ZP.rotationDegrees(displayData.roll() + 90f));
        poseStack.mulPose(Axis.XP.rotationDegrees(displayData.pitch()));
        poseStack.mulPose(Axis.YP.rotationDegrees(displayData.yaw()));

        EntityType<?> entityType = displayData.entityType();
        if (entityType == null) {
            poseStack.popPose();
            poseStack.popPose();
            return;
        }
        Entity entity = display.getRenderedEntity();
        if (entity == null || entity.getType() != entityType) {
            entity = entityType.create(Minecraft.getInstance().level);
            if (entity != null) {
                if (displayData.nbt().isPresent()) entity.load(displayData.nbt().get());
                if (CompatHelper.isHybridAquaticLoaded()) CompatHelper.hybridAquaticApplyVariant(entity, display.getDisplayStack());
                entity.xRotO = entity.getXRot();
                entity.yRotO = entity.getYRot();
            }
            display.setRenderedEntity(entity);
        }

        this.entityRenderer.render(entity,
                0, 0, 0,
                0, 0,
                poseStack, buffer, packedLight
        );

        poseStack.popPose();
        poseStack.popPose();
    }
}