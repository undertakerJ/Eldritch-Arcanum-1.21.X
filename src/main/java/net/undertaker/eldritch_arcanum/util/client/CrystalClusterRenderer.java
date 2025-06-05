package net.undertaker.eldritch_arcanum.util.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.blocks.custom.CrystalClusterBlock;
import net.undertaker.eldritch_arcanum.blocks.entity.CrystalClusterEntity;
import net.undertaker.eldritch_arcanum.blocks.entity.model.CrystalClusterModel;
import org.joml.Quaternionf;

public class CrystalClusterRenderer implements BlockEntityRenderer<CrystalClusterEntity> {
    private final CrystalClusterModel model;

    public CrystalClusterRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new CrystalClusterModel(context.bakeLayer(CrystalClusterModel.LAYER_LOCATION));
    }
    @Override
    public void render(CrystalClusterEntity crystalClusterEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        if (Minecraft.getInstance().player == null) return;
        Minecraft mc = Minecraft.getInstance();
        ResourceLocation clusterTexture =
                ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, "textures/block/crystal_cluster.png");
        ResourceLocation clusterOverlayTexture =
                ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, "textures/block/crystal_cluster_overlay.png");

        Direction facing = crystalClusterEntity.getBlockState().getValue(CrystalClusterBlock.FACING);
        poseStack.pushPose();
        poseStack.translate(0.5, 1.5, 0.5);
        poseStack.scale(-1.0F, -1.0F, 1.0F);

        VertexConsumer vertexConsumer =
                mc.renderBuffers().bufferSource().getBuffer(RenderType.entityCutout(clusterTexture));
        int packedLight = 15728880;
        model.renderToBuffer(
                poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);

        mc.renderBuffers().bufferSource().endBatch();
        VertexConsumer eyesConsumer =
                mc.renderBuffers().bufferSource().getBuffer(RenderType.entityTranslucent(clusterOverlayTexture));
        int color = crystalClusterEntity.getColor();
        model.renderToBuffer(
                poseStack, eyesConsumer, packedLight, OverlayTexture.NO_OVERLAY, color);
        mc.renderBuffers().bufferSource().endBatch();

        poseStack.popPose();
    }
}
