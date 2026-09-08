package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.blockentity.custom.DiscoBallBE;

import java.util.Objects;

public class DiscoBallBERenderer implements BlockEntityRenderer<DiscoBallBE> {
    private final Minecraft mc;
    private final BlockRenderDispatcher dispatcher;
    private final ModelBlockRenderer blockRenderer;
    private final ModelManager manager;
    private final BlockModelShaper blockModelShaper;

    public DiscoBallBERenderer(BlockEntityRendererProvider.Context ctx){
        mc = Objects.requireNonNull(Minecraft.getInstance());
        dispatcher = mc.getBlockRenderer();
        blockRenderer = dispatcher.getModelRenderer();
        manager = dispatcher.getBlockModelShaper().getModelManager();
        blockModelShaper = dispatcher.getBlockModelShaper();
    }

    @Override
    public void render(DiscoBallBE discoBallBE, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        BakedModel model;
        model = blockModelShaper.getBlockModel(TBlocks.DISCO_BALL.get().defaultBlockState());
        double ticks = ((partialTick + Util.getMillis()) * discoBallBE.speed) / 32.0D;

        if(discoBallBE.spinning){
            poseStack.rotateAround(Axis.YP.rotationDegrees((float)ticks),0.5f,0.5f,0.5f);
        }
        blockRenderer.renderModel(poseStack.last(),buffer.getBuffer(Sheets.solidBlockSheet()),
                null,
                model,
                1.0f, 1.0f, 1.0f,
                packedLight, packedOverlay, ModelData.EMPTY, RenderType.solid());
        poseStack.popPose();
    }
}
