package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
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
            poseStack.pushPose();
            poseStack.rotateAround(Axis.YP.rotationDegrees(0),0.5f,0.5f,0.5f);

            poseStack.translate(0.15,0,0.15);
            poseStack.scale(0.5f,0.5f,0.5f);

            try{
                for(int itemIndex = 0; itemIndex < be.getContainerSize() - 1; itemIndex++){
                    if(itemIndex % 8 == 0){
                        poseStack.translate(0.02 * itemIndex,0.02,1.0);
                        renderItem(itemIndex,be,poseStack,buffer,packedLight);
                    }
                }
            }
            catch (Exception e){

            }
            poseStack.popPose();
        }
    }

    public void renderItem(int itemIndex, FancyStorageDecorationBE be, PoseStack poseStack, MultiBufferSource buffer, int packedLight){
        itemRenderer.renderStatic(be.getItem(itemIndex), ItemDisplayContext.FIXED,
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
