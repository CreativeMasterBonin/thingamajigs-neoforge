package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.custom.FancyStorageDecoration;
import net.rk.thingamajigs.blockentity.custom.FancyStorageDecorationBE;

public class FancyStorageDecorationBERenderer implements BlockEntityRenderer<FancyStorageDecorationBE>{
    private static ItemRenderer itemRenderer;

    public FancyStorageDecorationBERenderer(BlockEntityRendererProvider.Context ctx){
        itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    @Override
    public void render(FancyStorageDecorationBE be, float v, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if(be.getBlockState().getBlock() instanceof FancyStorageDecoration deco){
            renderStandalone(0,0,-0.1,be,poseStack,buffer,packedLight);
            renderStandalone(1,0,-0.1,be,poseStack,buffer,packedLight);
            renderStandalone(2,0,-0.1,be,poseStack,buffer,packedLight);
            renderStandalone(3,0,-0.1,be,poseStack,buffer,packedLight);
            renderStandalone(4,0,-0.1,be,poseStack,buffer,packedLight);
            renderStandalone(5,0,-0.1,be,poseStack,buffer,packedLight);
            renderStandalone(6,0,-0.1,be,poseStack,buffer,packedLight);
            renderStandalone(7,0,-0.1,be,poseStack,buffer,packedLight);
            renderStandalone(8,0,-0.1,be,poseStack,buffer,packedLight);

            renderStandalone(9,-0.7,-0.08,be,poseStack,buffer,packedLight);
            renderStandalone(10,-0.7,-0.08,be,poseStack,buffer,packedLight);
            renderStandalone(11,-0.7,-0.08,be,poseStack,buffer,packedLight);
            renderStandalone(12,-0.7,-0.08,be,poseStack,buffer,packedLight);
            renderStandalone(13,-0.7,-0.08,be,poseStack,buffer,packedLight);
            renderStandalone(14,-0.7,-0.08,be,poseStack,buffer,packedLight);
            renderStandalone(15,-0.7,-0.08,be,poseStack,buffer,packedLight);
            renderStandalone(16,-0.7,-0.08,be,poseStack,buffer,packedLight);
            renderStandalone(17,-0.7,-0.08,be,poseStack,buffer,packedLight);

            renderStandalone(18,-1.4,-0.06,be,poseStack,buffer,packedLight);
            renderStandalone(19,-1.4,-0.06,be,poseStack,buffer,packedLight);
            renderStandalone(20,-1.4,-0.06,be,poseStack,buffer,packedLight);
            renderStandalone(21,-1.4,-0.06,be,poseStack,buffer,packedLight);
            renderStandalone(22,-1.4,-0.06,be,poseStack,buffer,packedLight);
            renderStandalone(23,-1.4,-0.06,be,poseStack,buffer,packedLight);
            renderStandalone(24,-1.4,-0.06,be,poseStack,buffer,packedLight);
            renderStandalone(25,-1.4,-0.06,be,poseStack,buffer,packedLight);
            renderStandalone(26,-1.4,-0.06,be,poseStack,buffer,packedLight);

            renderStandalone(27,-2.15,-0.045,be,poseStack,buffer,packedLight);
            renderStandalone(28,-2.15,-0.045,be,poseStack,buffer,packedLight);
            renderStandalone(29,-2.15,-0.045,be,poseStack,buffer,packedLight);
            renderStandalone(30,-2.15,-0.045,be,poseStack,buffer,packedLight);
            renderStandalone(31,-2.15,-0.045,be,poseStack,buffer,packedLight);
            renderStandalone(32,-2.15,-0.045,be,poseStack,buffer,packedLight);
            renderStandalone(33,-2.15,-0.045,be,poseStack,buffer,packedLight);
            renderStandalone(34,-2.15,-0.045,be,poseStack,buffer,packedLight);
            renderStandalone(35,-2.15,-0.045,be,poseStack,buffer,packedLight);

            renderStandalone(36,-2.85,-0.037,be,poseStack,buffer,packedLight);
            renderStandalone(37,-2.85,-0.037,be,poseStack,buffer,packedLight);
            renderStandalone(38,-2.85,-0.037,be,poseStack,buffer,packedLight);
            renderStandalone(39,-2.85,-0.037,be,poseStack,buffer,packedLight);
            renderStandalone(40,-2.85,-0.037,be,poseStack,buffer,packedLight);
            renderStandalone(41,-2.85,-0.037,be,poseStack,buffer,packedLight);
            renderStandalone(42,-2.85,-0.037,be,poseStack,buffer,packedLight);
            renderStandalone(43,-2.85,-0.037,be,poseStack,buffer,packedLight);
            renderStandalone(44,-2.85,-0.037,be,poseStack,buffer,packedLight);

            renderStandalone(45,-3.6,-0.031,be,poseStack,buffer,packedLight);
            renderStandalone(46,-3.6,-0.031,be,poseStack,buffer,packedLight);
            renderStandalone(47,-3.6,-0.031,be,poseStack,buffer,packedLight);
            renderStandalone(48,-3.6,-0.031,be,poseStack,buffer,packedLight);
            renderStandalone(49,-3.6,-0.031,be,poseStack,buffer,packedLight);
            renderStandalone(50,-3.6,-0.031,be,poseStack,buffer,packedLight);
            renderStandalone(51,-3.6,-0.031,be,poseStack,buffer,packedLight);
            renderStandalone(52,-3.6,-0.031,be,poseStack,buffer,packedLight);
            renderStandalone(53,-3.6,-0.031,be,poseStack,buffer,packedLight);

        }
    }

    public void renderStandalone(int index, double xoff, double zoff, FancyStorageDecorationBE be, PoseStack poseStack, MultiBufferSource buffer, int packedLight){
        poseStack.pushPose();
        switch(be.getBlockState().getValue(FancyStorageDecoration.FACING)){
            case NORTH -> {
                poseStack.rotateAround(Axis.YP.rotationDegrees(180),0.5f,0.5f,0.5f);
                break;
            }
            case SOUTH -> {
                poseStack.rotateAround(Axis.YP.rotationDegrees(0),0.5f,0.5f,0.5f);
                break;
            }
            case EAST -> {
                poseStack.rotateAround(Axis.YP.rotationDegrees(90),0.5f,0.5f,0.5f);
                break;
            }
            case WEST -> {
                poseStack.rotateAround(Axis.YP.rotationDegrees(270),0.5f,0.5f,0.5f);
                break;
            }
            default -> {

            }
        }

        poseStack.translate(xoff,0,zoff);

        poseStack.translate(0.15,0.02,0.15);
        poseStack.scale(0.2f,0.2f,0.2f);
        double evenSpacingBackToFront = 0.4;
        double heightFromFloor = 0.7;
        double evenSpacingLeftToRight = 0.2 + (index / 12D);

        poseStack.translate(evenSpacingBackToFront * index,heightFromFloor,evenSpacingLeftToRight);
        renderItem(be.getItem(index),be,poseStack,buffer,packedLight);
        poseStack.popPose();
    }

    public void renderRow(int offset, FancyStorageDecorationBE be,PoseStack poseStack, MultiBufferSource buffer, int packedLight){
        poseStack.pushPose();

        switch(be.getBlockState().getValue(FancyStorageDecoration.FACING)){
            case NORTH -> {
                poseStack.rotateAround(Axis.YP.rotationDegrees(180),0.5f,0.5f,0.5f);
                break;
            }
            case SOUTH -> {
                poseStack.rotateAround(Axis.YP.rotationDegrees(0),0.5f,0.5f,0.5f);
                break;
            }
            case EAST -> {
                poseStack.rotateAround(Axis.YP.rotationDegrees(90),0.5f,0.5f,0.5f);
                break;
            }
            case WEST -> {
                poseStack.rotateAround(Axis.YP.rotationDegrees(270),0.5f,0.5f,0.5f);
                break;
            }
            default -> {

            }
        }

        poseStack.translate(0.2,0.15,0.02 + (offset / (double)be.getContainerSize()));
        poseStack.scale(0.18f,0.2f,0.2f);
        poseStack.translate(-0.1,0,0.22);
        double offsetX = 0.44;
        double offsetZ = 0.05;
        if(offset <= be.getContainerSize() - 1){
            offset -= 1;
            renderItem(be.getItem(offset),be,poseStack,buffer,packedLight);
        }
        poseStack.translate(offsetX,0,offsetZ);
        if( offset + 1 <= be.getContainerSize() - 1){
            offset -= 1;
            renderItem(be.getItem(1 + offset),be,poseStack,buffer,packedLight);
        }
        poseStack.translate(offsetX,0,offsetZ);
        if(offset + 2 <= be.getContainerSize() - 1 ){
            offset -= 1;
            renderItem(be.getItem(2 + offset),be,poseStack,buffer,packedLight);
        }
        poseStack.translate(offsetX,0,offsetZ);
        if(offset + 3 <= be.getContainerSize() - 1){
            offset -= 1;
            renderItem(be.getItem(3 + offset),be,poseStack,buffer,packedLight);
        }
        poseStack.translate(offsetX,0,offsetZ);
        if(offset + 4 <= be.getContainerSize() - 1){
            offset -= 1;
            renderItem(be.getItem(4 + offset),be,poseStack,buffer,packedLight);
        }
        poseStack.translate(offsetX,0,offsetZ);
        if(offset + 5 <= be.getContainerSize() - 1){
            offset -= 1;
            renderItem(be.getItem(5 + offset),be,poseStack,buffer,packedLight);
        }
        poseStack.translate(offsetX,0,offsetZ);
        if (offset + 6 <= be.getContainerSize() - 1){
            offset -= 1;
            renderItem(be.getItem(6 + offset),be,poseStack,buffer,packedLight);
        }
        poseStack.translate(offsetX,0,offsetZ);
        if (offset + 7 <= be.getContainerSize() - 1){
            offset -= 1;
            renderItem(be.getItem(7 + offset),be,poseStack,buffer,packedLight);
        }
        poseStack.translate(offsetX,0,offsetZ);
        if (offset + 8 <= be.getContainerSize() - 1){
            offset -= 1;
            renderItem(be.getItem(8 + offset),be,poseStack,buffer,packedLight);
        }
        poseStack.popPose();
    }

    public void renderItem(ItemStack item, FancyStorageDecorationBE be, PoseStack poseStack, MultiBufferSource buffer, int packedLight){
        itemRenderer.renderStatic(item, ItemDisplayContext.FIXED,
                packedLight,OverlayTexture.NO_OVERLAY,
                poseStack,buffer,be.getLevel(),1);
    }

    @Override
    public int getViewDistance() {
        return 32;
    }

    @Override
    public boolean shouldRenderOffScreen(FancyStorageDecorationBE blockEntity) {
        return false;
    }

    @Override
    public boolean shouldRender(FancyStorageDecorationBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }

    @Override
    public AABB getRenderBoundingBox(FancyStorageDecorationBE blockEntity) {
        return new AABB(blockEntity.getBlockPos().getX() - 2, blockEntity.getBlockPos().getY() - 1, blockEntity.getBlockPos().getZ() - 2,
                blockEntity.getBlockPos().getX() + 2, blockEntity.getBlockPos().getY() + 1, blockEntity.getBlockPos().getZ() + 2);
    }
}
