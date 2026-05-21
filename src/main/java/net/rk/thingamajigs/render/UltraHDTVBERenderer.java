package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.rk.thingamajigs.TClient;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.custom.UltraHDTV;
import net.rk.thingamajigs.blockentity.custom.UltraHDTVBE;

import java.util.Objects;

public class UltraHDTVBERenderer implements BlockEntityRenderer<UltraHDTVBE> {
    private final Minecraft mc;
    private BlockRenderDispatcher dispatcher;
    private ModelBlockRenderer blockRenderer;
    private ModelManager manager;

    public UltraHDTVBERenderer(BlockEntityRendererProvider.Context ctx){
        mc = Objects.requireNonNull(Minecraft.getInstance());
        dispatcher = Objects.requireNonNull(mc.getBlockRenderer());
        blockRenderer = mc.getBlockRenderer().getModelRenderer();
        manager = Objects.requireNonNull(dispatcher.getBlockModelShaper().getModelManager());
    }

    @Override
    public void render(UltraHDTVBE uHDTV, float v, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0,0,0);
        if(uHDTV.customRotation){
            poseStack.rotateAround(Axis.YP.rotationDegrees(uHDTV.yAngle),0.5f,0.5f,0.5f);
        }
        else{
            if(uHDTV.getBlockState().getValue(UltraHDTV.FACING) == Direction.NORTH){
                poseStack.rotateAround(Axis.YP.rotationDegrees(0),0.5f,0.5f,0.5f);
            }
            else if(uHDTV.getBlockState().getValue(UltraHDTV.FACING) == Direction.SOUTH){
                poseStack.rotateAround(Axis.YP.rotationDegrees(180),0.5f,0.5f,0.5f);
            }
            else if(uHDTV.getBlockState().getValue(UltraHDTV.FACING) == Direction.EAST){
                poseStack.rotateAround(Axis.YP.rotationDegrees(270),0.5f,0.5f,0.5f);
            }
            else if(uHDTV.getBlockState().getValue(UltraHDTV.FACING) == Direction.WEST){
                poseStack.rotateAround(Axis.YP.rotationDegrees(90),0.5f,0.5f,0.5f);
            }
        }

        this.blockRenderer.renderModel(poseStack.last(),buffer.getBuffer(Sheets.solidBlockSheet()),
                null, manager.getModel(TClient.uhdTVLocation),1.0f,1.0f,1.0f,packedLight, OverlayTexture.NO_OVERLAY,
                ModelData.EMPTY, RenderType.SOLID);

        poseStack.popPose();

        float offsetX = 0.0f;
        float offsetY = 0.0f;

        switch(uHDTV.currentChannel){
            case 1: {
                renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.endPortal()),offsetX,offsetY,false);
                break;
            }
            case 2: {
                renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                        ResourceLocation.withDefaultNamespace("textures/block/jigsaw_top.png"),false)),offsetX,offsetY,true);
                break;
            }
            case 3: {
                if(uHDTV.randomColor % 2 == 0){
                    renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                            ResourceLocation.withDefaultNamespace("textures/block/kelp_plant.png"),false)),-0.95f,-0.95f,false);
                }
                else if(uHDTV.randomColor % 17 == 0){
                    renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                            ResourceLocation.withDefaultNamespace("textures/block/kelp.png"),false)),-0.95f,-0.95f,false);
                }
                break;
            }
            case 4: {
                renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                        ResourceLocation.withDefaultNamespace("textures/block/lava_flow.png"),false)),offsetX,-0.95f,false);
                break;
            }
            case 5: {
                renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                        ResourceLocation.withDefaultNamespace("textures/block/nether_portal.png"),false)),offsetX,0.95f,false);
                break;
            }
            case 6: {
                if(uHDTV.randomColor % 2 == 0){
                    renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                            ResourceLocation.withDefaultNamespace("textures/block/purpur_pillar_top.png"),false)),offsetX,offsetY,true);
                }
                else if(uHDTV.randomColor % 3 == 0){
                    renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                            ResourceLocation.withDefaultNamespace("textures/block/purpur_pillar.png"),false)),offsetX,offsetY,true);
                }
                else{
                    renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                            ResourceLocation.withDefaultNamespace("textures/block/purpur_block.png"),false)),offsetX,offsetY,true);
                }
                break;
            }
            case 7: {
                if(uHDTV.randomColor <= 5200){
                    renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                            ResourceLocation.withDefaultNamespace("textures/block/detector_rail.png"),false)),offsetX,offsetY,true);
                }
                else if(uHDTV.randomColor >= 72000 && uHDTV.randomColor <= 853200){
                    renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                            ResourceLocation.withDefaultNamespace("textures/block/powered_rail.png"),false)),offsetX,offsetY,true);
                }
                else if(uHDTV.randomColor >= 910000 && uHDTV.randomColor <= 1200000){
                    renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                            ResourceLocation.withDefaultNamespace("textures/block/activator_rail.png"),false)),offsetX,offsetY,true);
                }
                else{
                    renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                            ResourceLocation.withDefaultNamespace("textures/block/rail.png"),false)),offsetX,offsetY,true);
                }
                break;
            }
            case 8: {
                renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                        ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                                "textures/block/tv/random_ani.png"),false)),offsetX,-0.95f,true);
                break;
            }
            case 9: {
                renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                        ResourceLocation.withDefaultNamespace("textures/block/repeating_command_block_front.png"),false)),offsetX,-0.99f,false);
                break;
            }
            case 10: {
                renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                        ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,"textures/block/blueman.png"),false)),offsetX,-0.95f,false);
                break;
            }
            default: {
                renderPart(uHDTV,poseStack,buffer.getBuffer(RenderType.beaconBeam(
                        ResourceLocation.withDefaultNamespace("textures/block/black_concrete.png"),false)),offsetX,offsetY,false);
                break;
            }
        }
    }

    public static void renderPart(UltraHDTVBE tv,PoseStack poseStack, VertexConsumer consumer, float offsetX,float offsetY,boolean randomColors) {
        poseStack.pushPose();
        if(tv.customRotation){
            poseStack.rotateAround(Axis.YP.rotationDegrees(tv.yAngle - 90.0f),0.5f,0.5f,0.5f);
        }
        else{
            if(tv.getBlockState().getValue(UltraHDTV.FACING) == Direction.NORTH){
                poseStack.rotateAround(Axis.YP.rotationDegrees(270),0.5f,0.5f,0.5f);
            }
            else if(tv.getBlockState().getValue(UltraHDTV.FACING) == Direction.SOUTH){
                poseStack.rotateAround(Axis.YP.rotationDegrees(90),0.5f,0.5f,0.5f);
            }
            else if(tv.getBlockState().getValue(UltraHDTV.FACING) == Direction.EAST){
                poseStack.rotateAround(Axis.YP.rotationDegrees(180),0.5f,0.5f,0.5f);
            }
            else if(tv.getBlockState().getValue(UltraHDTV.FACING) == Direction.WEST){
                poseStack.rotateAround(Axis.YP.rotationDegrees(0),0.5f,0.5f,0.5f);
            }
        }

        poseStack.translate(0.41,0.11,-1.15);
        poseStack.scale(1.0f,2.02f,3.3f);

        float x1 = 0.0f;
        float x2 = 0.0f;
        float x3 = 0.0f;
        float x4 = 0.0f;
        float z1 = 1.0f;
        float z2 = 1.0f;
        float z3 = 0.0f;
        float z4 = 0.0f;
        float minU = 0.0f;
        float minV = 0.0f;
        float maxU = 1.0f + offsetX;
        float maxV = 1.0f + offsetY;

        float minFractionalYMovement = Mth.frac(Util.getMillis() / 92000.0f - (float)Mth.floor(0.4f));
        float fractionalYMovement = Mth.frac(Util.getMillis() / 92000.0f - (float)Mth.floor(0.5f)) + 0.02f;
        float minFractionalYMovementSlower = Mth.frac(Util.getMillis() / 9000.0f - (float)Mth.floor(Mth.cos(0.4f + Util.getMillis() / (1000.0f + 1.1f))));
        float fractionalYMovementSlower = Mth.frac(Util.getMillis() / 11000.0f - (float)Mth.floor(Mth.cos(0.5f + Util.getMillis() / (1200.0f + 1.2f)))) + 0.02f;

        if(offsetY == -0.95f){
            minU = 0.0f;
            maxU = 0.5f;
            if(offsetX == -0.95f){
                minU = minFractionalYMovementSlower - Mth.sin(Util.getMillis() / 41230.0f);
                maxU = fractionalYMovementSlower;
            }
            else{
                minV = minFractionalYMovement;
                maxV = fractionalYMovement;
            }
        }
        else if(offsetY == 0.95f){
            minU = -1.0f;
            maxU = 0.0f;
            minV = -0.99f + Util.getMillis() / 100000.0f + 16.0f;
            maxV = -1.0f + Util.getMillis() / 100000.0f + 16.0f;
        }
        else if(offsetY == -0.99f){
            minU = -1.0f;
            maxU = 0.0f;
            minV = -0.99f + Util.getMillis() / 100000.0f + 16.0f;
            maxV = -1.0f + Util.getMillis() / 100000.0f + 16.0f;
        }

        int minY = 0;
        int maxY = 1;

        if(!randomColors){
            renderQuad(poseStack.last(), consumer,minY, maxY, x1, z1, x2, z2, minU, maxU, minV, maxV);
            renderQuad(poseStack.last(), consumer,minY, maxY, x4, z4, x3, z3, minU, maxU, minV, maxV);
            renderQuad(poseStack.last(), consumer,minY, maxY, x2, z2, x4, z4, minU, maxU, minV, maxV);
            renderQuad(poseStack.last(), consumer,minY, maxY, x3, z3, x1, z1, minU, maxU, minV, maxV);
        }
        else{
            if(tv.getLevel() != null){
                int color = tv.randomColor;
                renderQuadRandomColor(poseStack.last(), consumer,minY, maxY, x1, z1, x2, z2, minU, maxU, minV, maxV,color);
                renderQuadRandomColor(poseStack.last(), consumer,minY, maxY, x4, z4, x3, z3, minU, maxU, minV, maxV,color);
                renderQuadRandomColor(poseStack.last(), consumer,minY, maxY, x2, z2, x4, z4, minU, maxU, minV, maxV,color);
                renderQuadRandomColor(poseStack.last(), consumer,minY, maxY, x3, z3, x1, z1, minU, maxU, minV, maxV,color);
            }
        }
        poseStack.popPose();
    }

    public static void renderQuadRandomColor(PoseStack.Pose pose, VertexConsumer consumer, int minY, int maxY, float minX, float minZ, float maxX, float maxZ, float minU, float maxU, float minV, float maxV, int color) {
        addVertex(pose, consumer, color, maxY, minX, minZ, maxU, minV);
        addVertex(pose, consumer, color, minY, minX, minZ, maxU, maxV);
        addVertex(pose, consumer, color, minY, maxX, maxZ, minU, maxV);
        addVertex(pose, consumer, color, maxY, maxX, maxZ, minU, minV);
    }

    public static void renderQuad(PoseStack.Pose pose, VertexConsumer consumer, int minY, int maxY, float minX, float minZ, float maxX, float maxZ, float minU, float maxU, float minV, float maxV) {
        addVertex(pose, consumer, 16777215, maxY, minX, minZ, maxU, minV);
        addVertex(pose, consumer, 16777215, minY, minX, minZ, maxU, maxV);
        addVertex(pose, consumer, 16777215, minY, maxX, maxZ, minU, maxV);
        addVertex(pose, consumer, 16777215, maxY, maxX, maxZ, minU, minV);
    }

    public static void addVertex(PoseStack.Pose pose, VertexConsumer consumer, int color, int y, float x, float z, float u, float v) {
        consumer.addVertex(pose, x, (float)y, z).setColor(color).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(16777215).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }

    @Override
    public boolean shouldRenderOffScreen(UltraHDTVBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 87;
    }

    @Override
    public boolean shouldRender(UltraHDTVBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 3.0, 2.0)
                .closerThan(vec3.multiply(2.0, 3.0, 2.0), (double)this.getViewDistance());
    }

    @Override
    public AABB getRenderBoundingBox(UltraHDTVBE blockEntity) {
        return new AABB(blockEntity.getBlockPos().getX() - 2, blockEntity.getBlockPos().getY() - 2, blockEntity.getBlockPos().getZ() - 2,
                blockEntity.getBlockPos().getX() + 2, blockEntity.getBlockPos().getY() + 2, blockEntity.getBlockPos().getZ() + 2);
    }
}
