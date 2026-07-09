package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.rk.thingamajigs.TClient;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.blockentity.custom.TubeManDeco;
import net.rk.thingamajigs.blockentity.custom.TubeManDecoBE;

import java.util.Objects;

public class TubeManDecoBERenderer implements BlockEntityRenderer<TubeManDecoBE> {
    private final Minecraft mc;
    private BlockRenderDispatcher dispatcher;
    private ModelBlockRenderer blockRenderer;
    private ModelManager manager;

    public TubeManDecoBERenderer(BlockEntityRendererProvider.Context ctx){
        mc = Objects.requireNonNull(Minecraft.getInstance());
        dispatcher = Objects.requireNonNull(mc.getBlockRenderer());
        blockRenderer = mc.getBlockRenderer().getModelRenderer();
        manager = Objects.requireNonNull(dispatcher.getBlockModelShaper().getModelManager());
    }

    @Override
    public void render(TubeManDecoBE tubeManDecoBE, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        poseStack.rotateAround(Axis.YP.rotationDegrees(tubeManDecoBE.yAngle),0.5f,0.5f,0.5f);
        poseStack.translate(0,0,0);

        ModelResourceLocation compressedModel = ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                        TClient.tubeManCustomModelBaseLocation + tubeManDecoBE.color.getName() + "_tube_man_base_compressed"));

        ModelResourceLocation bodyModel = ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                        TClient.tubeManCustomModelBaseLocation + tubeManDecoBE.color.getName() + "_tube_man_body_section"));

        ModelResourceLocation headModel = ModelResourceLocation.standalone(
                ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                        TClient.tubeManCustomModelBaseLocation + tubeManDecoBE.color.getName() + "_tube_man_head_section"));

        if(tubeManDecoBE.getBlockState().getValue(TubeManDeco.TOGGLED)){
            this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                    manager.getModel(TClient.tubeManBase),
                    1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);

            poseStack.translate(0,0.25,0);

            poseStack.rotateAround(Axis.ZP.rotationDegrees((-4.0f - Mth.sin(Util.getMillis() / 320f) + 4.0f) * (3.0f * tubeManDecoBE.getRandomOffset() - 1.0f)),0.5f,-0.2f,0.5f);
            poseStack.rotateAround(Axis.XP.rotationDegrees((-4.0f - Mth.sin(Util.getMillis() / 625f) + 4.0f) * (2.0f * tubeManDecoBE.getRandomOffset() - 2.0f)),0.5f,-0.2f,0.5f);
            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(TClient.tubeManBodySection),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(bodyModel),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }

            float clampedCosMid = (-3.0f - Mth.cos(Util.getMillis() / 495f) + 3.0f) * (3.0f + tubeManDecoBE.getRandomOffset() * 2.1f);

            poseStack.translate(0,1,0);
            poseStack.rotateAround(Axis.ZP.rotationDegrees((-2.0f - Mth.sin(Util.getMillis() / 475f) + 2.0f) * (4.0f + tubeManDecoBE.getRandomOffset() * 3.0f)),0.5f,0,0.5f);
            poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.clamp(clampedCosMid,-15.0f,15.0f)),0.5f,0,0.5f);
            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(TClient.tubeManBodySection),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(bodyModel),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }

            poseStack.translate(0,1,0);
            poseStack.rotateAround(Axis.ZP.rotationDegrees((-4.0f - Mth.sin(Util.getMillis() / 210f)) + 4.0f),0.5f,0,0.5f);
            poseStack.rotateAround(Axis.XP.rotationDegrees((-5.0f - Mth.sin(Util.getMillis() / 202f) + 5.0f) * 3.0f),0.5f,0,0.5f);

            float clampedCosHeadSelectionZ = (-tubeManDecoBE.getRandomOffset() * 32.0f)
                    - Mth.cos(Util.getMillis() / 100f)
                    + (tubeManDecoBE.getRandomOffset() * 32.0f);

            float clampedCosHeadSelectionX = (-tubeManDecoBE.getRandomOffset() * 32.0f)
                    - Mth.cos(Util.getMillis() / 100f)
                    + (tubeManDecoBE.getRandomOffset() * 32.0f);

            poseStack.rotateAround(Axis.ZP.rotationDegrees(Mth.clamp(clampedCosHeadSelectionZ,-15.0f,15.0f)),
                    0.5f,0.5f,0.5f);
            poseStack.rotateAround(Axis.XP.rotationDegrees(Mth.clamp(clampedCosHeadSelectionX,-15.0f,15.0f)),
                    0.5f,0.5f,0.5f);

            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(TClient.tubeManHeadSection),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(headModel),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }

            poseStack.translate(1,-0.5,0.25);
            poseStack.rotateAround(Axis.ZP.rotationDegrees(90),0.5f,0.5f,0.5f);
            poseStack.scale(0.5f,1.2f,0.5f);
            poseStack.rotateAround(Axis.XP.rotationDegrees((-13.0f - Mth.sin(Util.getMillis() / 300f) + 13.0f) * (9.0f + tubeManDecoBE.getRandomOffset())),0.5f,0.5f,0.5f);
            poseStack.rotateAround(Axis.ZP.rotationDegrees((-13.0f - Mth.sin(Util.getMillis() / 370f) + 13.0f) * (5.0f - tubeManDecoBE.getRandomOffset())),0.5f,0.5f,0.5f);
            poseStack.rotateAround(Axis.YP.rotationDegrees((-5.0f - Mth.sin(Util.getMillis() / 190f) + 5.0f) * (4.0f + tubeManDecoBE.getRandomOffset())),0.5f,0.5f,0.5f);
            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(TClient.tubeManBodySection),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(bodyModel),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }

            poseStack.translate(0,1.5,0);
            poseStack.rotateAround(Axis.YP.rotationDegrees((-5.0f - Mth.sin(Util.getMillis() / 170f) + 5.0f) * 8.0f),0.5f,0.5f,0.5f);
            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(TClient.tubeManBodySection),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(bodyModel),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }
        }
        else{
            if(tubeManDecoBE.color == DyeColor.BLUE){
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(TClient.tubeManBaseCompressed),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }
            else{
                this.blockRenderer.renderModel(poseStack.last(),multiBufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                        manager.getModel(compressedModel),
                        1.0f,1.0f,1.0f,packedLight,packedOverlay, ModelData.EMPTY, RenderType.SOLID);
            }
        }

        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(TubeManDecoBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 89;
    }

    @Override
    public boolean shouldRender(TubeManDecoBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 3.0, 2.0)
                .closerThan(vec3.multiply(2.0, 3.0, 2.0), (double)this.getViewDistance());
    }

    @Override
    public AABB getRenderBoundingBox(TubeManDecoBE blockEntity) {
        return new AABB(blockEntity.getBlockPos().getX() - 5.5, blockEntity.getBlockPos().getY() - 2, blockEntity.getBlockPos().getZ() - 5.5,
                blockEntity.getBlockPos().getX() + 5.5, blockEntity.getBlockPos().getY() + 5, blockEntity.getBlockPos().getZ() + 5.5);
    }
}
