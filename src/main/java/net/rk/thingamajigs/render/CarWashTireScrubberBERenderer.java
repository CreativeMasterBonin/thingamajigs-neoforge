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
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.rk.thingamajigs.TClient;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.custom.UltraHDTV;
import net.rk.thingamajigs.blockentity.custom.CarWashTireScrubberBE;

import java.util.Objects;

public class CarWashTireScrubberBERenderer implements BlockEntityRenderer<CarWashTireScrubberBE> {
    private final Minecraft mc;
    private BlockRenderDispatcher dispatcher;
    private ModelBlockRenderer blockRenderer;
    private ModelManager manager;

    public CarWashTireScrubberBERenderer(BlockEntityRendererProvider.Context ctx){
        mc = Objects.requireNonNull(Minecraft.getInstance());
        dispatcher = Objects.requireNonNull(mc.getBlockRenderer());
        blockRenderer = mc.getBlockRenderer().getModelRenderer();
        manager = Objects.requireNonNull(dispatcher.getBlockModelShaper().getModelManager());
    }

    private static ModelResourceLocation tireScrubberBase = new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                                                                      TClient.carWashCustomModelBaseLocation + "tire_scrubber_base"),ModelResourceLocation.STANDALONE_VARIANT);
    private static ModelResourceLocation tireScrubberBlade = new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                                                 TClient.carWashCustomModelBaseLocation + "tire_scrubber_blade"),ModelResourceLocation.STANDALONE_VARIANT);

    @Override
    public void render(CarWashTireScrubberBE carWashTireScrubberBE, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();

        if(carWashTireScrubberBE.customRotation){
            poseStack.rotateAround(Axis.YP.rotationDegrees(carWashTireScrubberBE.yAngle),0.5f,0.5f,0.5f);
        }
        else{
            if(carWashTireScrubberBE.getBlockState().getValue(UltraHDTV.FACING) == Direction.NORTH){
                poseStack.rotateAround(Axis.YP.rotationDegrees(0),0.5f,0.5f,0.5f);
            }
            else if(carWashTireScrubberBE.getBlockState().getValue(UltraHDTV.FACING) == Direction.SOUTH){
                poseStack.rotateAround(Axis.YP.rotationDegrees(180),0.5f,0.5f,0.5f);
            }
            else if(carWashTireScrubberBE.getBlockState().getValue(UltraHDTV.FACING) == Direction.EAST){
                poseStack.rotateAround(Axis.YP.rotationDegrees(270),0.5f,0.5f,0.5f);
            }
            else if(carWashTireScrubberBE.getBlockState().getValue(UltraHDTV.FACING) == Direction.WEST){
                poseStack.rotateAround(Axis.YP.rotationDegrees(90),0.5f,0.5f,0.5f);
            }
        }

        poseStack.translate(0,0,0);
        this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()), null,
                manager.getModel(tireScrubberBase), 1.0f, 1.0f, 1.0f,
                packedLight,
                OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        poseStack.popPose();

        for(int tire = 0; tire < 13; tire++){
            renderTireScrubberBlade(carWashTireScrubberBE,partialTick,poseStack,buffer,packedLight,
                    (float)tire * -0.15f + 0.9f,0,0,tire + 0.1f);
        }
    }

    public void renderTireScrubberBlade(CarWashTireScrubberBE tireScrubber,float partialTick,PoseStack poseStack,MultiBufferSource buffer,int packedLight,float offsetX,float offsetY,float offsetZ,float timingOffset){
        poseStack.pushPose();
        //poseStack.rotateAround(Axis.YP.rotationDegrees(tireScrubber.yAngle), 0.5f,0.5f,0.5f);

        if(tireScrubber.customRotation){
            poseStack.rotateAround(Axis.YP.rotationDegrees(tireScrubber.yAngle),0.5f,0.5f,0.5f);
        }
        else{
            if(tireScrubber.getBlockState().getValue(UltraHDTV.FACING) == Direction.NORTH){
                poseStack.rotateAround(Axis.YP.rotationDegrees(0),0.5f,0.5f,0.5f);
            }
            else if(tireScrubber.getBlockState().getValue(UltraHDTV.FACING) == Direction.SOUTH){
                poseStack.rotateAround(Axis.YP.rotationDegrees(180),0.5f,0.5f,0.5f);
            }
            else if(tireScrubber.getBlockState().getValue(UltraHDTV.FACING) == Direction.EAST){
                poseStack.rotateAround(Axis.YP.rotationDegrees(270),0.5f,0.5f,0.5f);
            }
            else if(tireScrubber.getBlockState().getValue(UltraHDTV.FACING) == Direction.WEST){
                poseStack.rotateAround(Axis.YP.rotationDegrees(90),0.5f,0.5f,0.5f);
            }
        }

        poseStack.translate(offsetX,offsetY,offsetZ);
        poseStack.rotateAround(Axis.XP.rotationDegrees(
                        tireScrubber.rotation + Util.getMillis() / 7.0f + timingOffset),
                0.5f,0.38f,0.5f);

        this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()), null,
                manager.getModel(tireScrubberBlade), 1.0f, 1.0f, 1.0f,
                packedLight,
                OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 84;
    }

    @Override
    public boolean shouldRenderOffScreen(CarWashTireScrubberBE blockEntity) {
        return true;
    }

    @Override
    public boolean shouldRender(CarWashTireScrubberBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }

    @Override
    public AABB getRenderBoundingBox(CarWashTireScrubberBE blockEntity) {
        return new AABB(blockEntity.getBlockPos().getX() - 2, blockEntity.getBlockPos().getY() - 2, blockEntity.getBlockPos().getZ() - 2,
                blockEntity.getBlockPos().getX() + 2, blockEntity.getBlockPos().getY() + 2, blockEntity.getBlockPos().getZ() + 2);
    }
}
