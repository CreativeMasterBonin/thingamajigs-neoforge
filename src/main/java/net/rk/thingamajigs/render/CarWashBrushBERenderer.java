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
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.rk.thingamajigs.TClient;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.block.custom.ShortCarWashBrush;
import net.rk.thingamajigs.blockentity.custom.CarWashBrushBE;

import java.util.Objects;

public class CarWashBrushBERenderer implements BlockEntityRenderer<CarWashBrushBE> {
    private final Minecraft mc;
    private BlockRenderDispatcher dispatcher;
    private ModelBlockRenderer blockRenderer;
    private ModelManager manager;


    private ModelResourceLocation brushBase =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "spinning_brush_base"),ModelResourceLocation.STANDALONE_VARIANT);
    private ModelResourceLocation shortBrushBase =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "spinning_short_brush_base"),ModelResourceLocation.STANDALONE_VARIANT);

    private ModelResourceLocation mixedBrushBlade =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "spinning_mixed_brush_blade"),ModelResourceLocation.STANDALONE_VARIANT);

    private ModelResourceLocation blueBrushLongBlade =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
            TClient.carWashCustomModelBaseLocation + "spinning_brush_long_blade"),ModelResourceLocation.STANDALONE_VARIANT);
    private ModelResourceLocation blueBrushMedBlade =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "spinning_brush_medium_blade"),ModelResourceLocation.STANDALONE_VARIANT);
    private ModelResourceLocation blueBrushShortBlade =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "spinning_brush_short_blade"),ModelResourceLocation.STANDALONE_VARIANT);

    private ModelResourceLocation redBrushLongBlade =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "spinning_red_brush_long_blade"),ModelResourceLocation.STANDALONE_VARIANT);
    private ModelResourceLocation redBrushMedBlade =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "spinning_red_brush_medium_blade"),ModelResourceLocation.STANDALONE_VARIANT);
    private ModelResourceLocation redBrushShortBlade =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "spinning_red_brush_short_blade"),ModelResourceLocation.STANDALONE_VARIANT);

    private ModelResourceLocation yellowBrushLongBlade =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "spinning_yellow_brush_long_blade"),ModelResourceLocation.STANDALONE_VARIANT);
    private ModelResourceLocation yellowBrushMedBlade =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "spinning_yellow_brush_medium_blade"),ModelResourceLocation.STANDALONE_VARIANT);
    private ModelResourceLocation yellowBrushShortBlade =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "spinning_yellow_brush_short_blade"),ModelResourceLocation.STANDALONE_VARIANT);

    public CarWashBrushBERenderer(BlockEntityRendererProvider.Context ctx){
        mc = Objects.requireNonNull(Minecraft.getInstance());
        dispatcher = Objects.requireNonNull(mc.getBlockRenderer());
        blockRenderer = mc.getBlockRenderer().getModelRenderer();
        manager = Objects.requireNonNull(dispatcher.getBlockModelShaper().getModelManager());
    }

    @Override
    public void render(CarWashBrushBE carWashBrushBE, float v, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        // render all sections
        if(carWashBrushBE.getBlockState().getBlock() instanceof ShortCarWashBrush){
            poseStack.pushPose();
            poseStack.translate(0,0,0);
            this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()), null,
                    manager.getModel(shortBrushBase), 1.0f, 1.0f, 1.0f,
                    packedLight,
                    OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
            poseStack.popPose();

            for(int tinySection = 0; tinySection < 14; tinySection++){
                renderOneShortCarBrushBladeForShortBrushVariant(carWashBrushBE,v,tinySection * 33.0f,poseStack,buffer,packedLight);
            }
        }
        else{
            poseStack.pushPose();
            poseStack.translate(0,0,0);
            this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()), null,
                    manager.getModel(brushBase), 1.0f, 1.0f, 1.0f,
                    packedLight,
                    OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
            poseStack.popPose();
            for(int bigSection = 0; bigSection < 14; bigSection++){
                renderOneCarBrushBlade(carWashBrushBE,v,bigSection * 33.0f,poseStack,buffer,packedLight);
            }
            for(int medSection = 0; medSection < 14; medSection++){
                renderOneMedCarBrushBlade(carWashBrushBE,v,medSection * -33.0f,poseStack,buffer,packedLight);
            }
            for(int tinySection = 0; tinySection < 14; tinySection++){
                renderOneShortCarBrushBlade(carWashBrushBE,v,tinySection * 33.0f,poseStack,buffer,packedLight);
            }
        }
    }

    public void renderOneCarBrushBlade(CarWashBrushBE carWashBrushBE, float partialTick, float angle, PoseStack poseStack,MultiBufferSource buffer,int packedLight){
        poseStack.pushPose();
        float t = angle + carWashBrushBE.yAngle + 1.0f + Util.getMillis() / 7.0f;

        poseStack.rotateAround(Axis.YP.rotationDegrees(t),0.5f,0.5f,0.5f);
        poseStack.rotateAround(Axis.XP.rotationDegrees(carWashBrushBE.extensionAngle),0.5f,2.0f,0.5f);

        if (carWashBrushBE.getBlockState().getBlock().equals(TBlocks.CAR_WASH_BLUE_BRUSH.get())) {
            this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),null,
                    manager.getModel(blueBrushLongBlade), 1.0f, 1.0f, 1.0f,
                    packedLight,
                    OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        }
        else if(carWashBrushBE.getBlockState().getBlock().equals(TBlocks.CAR_WASH_RED_BRUSH.get())){
            this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),null,
                    manager.getModel(redBrushLongBlade), 1.0f, 1.0f, 1.0f,
                    packedLight,
                    OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        }
        else{
            this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),null,
                    manager.getModel(yellowBrushLongBlade), 1.0f, 1.0f, 1.0f,
                    packedLight,
                    OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        }
        poseStack.popPose();
    }

    public void renderOneMedCarBrushBlade(CarWashBrushBE carWashBrushBE, float partialTick, float angle, PoseStack poseStack,MultiBufferSource buffer,int packedLight){
        poseStack.pushPose();
        poseStack.translate(0,-0.55,0);
        float t = angle + carWashBrushBE.yAngle + 1.0f + Util.getMillis() / 6.0f;
        poseStack.rotateAround(Axis.YP.rotationDegrees(t),0.5f,0.5f,0.5f);
        poseStack.rotateAround(Axis.XP.rotationDegrees(carWashBrushBE.extensionAngle),0.5f,2.0f,0.5f);

        if (carWashBrushBE.getBlockState().getBlock().equals(TBlocks.CAR_WASH_BLUE_BRUSH.get())) {
            this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),null,
                    manager.getModel(blueBrushMedBlade), 1.0f, 1.0f, 1.0f,
                    packedLight,
                    OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        }
        else if(carWashBrushBE.getBlockState().getBlock().equals(TBlocks.CAR_WASH_RED_BRUSH.get())){
            this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),null,
                    manager.getModel(redBrushMedBlade), 1.0f, 1.0f, 1.0f,
                    packedLight,
                    OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        }
        else{
            this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),null,
                    manager.getModel(yellowBrushMedBlade), 1.0f, 1.0f, 1.0f,
                    packedLight,
                    OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        }

        poseStack.popPose();
    }

    public void renderOneShortCarBrushBlade(CarWashBrushBE carWashBrushBE, float partialTick, float angle, PoseStack poseStack,MultiBufferSource buffer,int packedLight){
        poseStack.pushPose();
        poseStack.translate(0,-1.15,0);

        float t = angle + carWashBrushBE.yAngle + 1.0f + Util.getMillis() / 5.0f;
        poseStack.rotateAround(Axis.YP.rotationDegrees(t),0.5f,0.5f,0.5f);
        poseStack.rotateAround(Axis.XP.rotationDegrees(carWashBrushBE.extensionAngle),0.5f,2.0f,0.5f);

        if (carWashBrushBE.getBlockState().getBlock().equals(TBlocks.CAR_WASH_BLUE_BRUSH.get())) {
            this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),null,
                    manager.getModel(blueBrushShortBlade), 1.0f, 1.0f, 1.0f,
                    packedLight,
                    OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        }
        else if(carWashBrushBE.getBlockState().getBlock().equals(TBlocks.CAR_WASH_RED_BRUSH.get())){
            this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),null,
                    manager.getModel(redBrushShortBlade), 1.0f, 1.0f, 1.0f,
                    packedLight,
                    OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        }
        else{
            this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),null,
                    manager.getModel(yellowBrushShortBlade), 1.0f, 1.0f, 1.0f,
                    packedLight,
                    OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        }

        poseStack.popPose();
    }

    public void renderOneShortCarBrushBladeForShortBrushVariant(CarWashBrushBE carWashBrushBE, float partialTick, float angle, PoseStack poseStack,MultiBufferSource buffer,int packedLight){
        poseStack.pushPose();
        poseStack.translate(0, Mth.clamp(1.0 + (carWashBrushBE.extensionAngle / 45),0.0,1.45),0);
        poseStack.rotateAround(Axis.ZP.rotationDegrees(180),0.5f,0.5f,0.5f);

        float t = angle + carWashBrushBE.yAngle + 1.0f + Util.getMillis() / 5.0f;
        poseStack.rotateAround(Axis.YP.rotationDegrees(t),0.5f,0.5f,0.5f);
        poseStack.rotateAround(Axis.XP.rotationDegrees(carWashBrushBE.extensionAngle),0.5f,2.0f,0.5f);

        this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()),null,
                manager.getModel(mixedBrushBlade), 1.0f, 1.0f, 1.0f,
                packedLight,
                OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);

        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 84;
    }

    @Override
    public boolean shouldRenderOffScreen(CarWashBrushBE blockEntity) {
        return true;
    }

    @Override
    public boolean shouldRender(CarWashBrushBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }

    @Override
    public AABB getRenderBoundingBox(CarWashBrushBE blockEntity) {
        return new AABB(blockEntity.getBlockPos().getX() - 2, blockEntity.getBlockPos().getY() - 2, blockEntity.getBlockPos().getZ() - 2,
                blockEntity.getBlockPos().getX() + 2, blockEntity.getBlockPos().getY() + 2, blockEntity.getBlockPos().getZ() + 2);
    }
}
