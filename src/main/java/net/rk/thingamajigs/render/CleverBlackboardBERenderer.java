package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.custom.CleverBlackboard;
import net.rk.thingamajigs.blockentity.custom.CleverBlackboardBE;
import net.rk.thingamajigs.render.model.CleverBlackboardModel;
import net.rk.thingamajigs.xtras.TColors;
import org.joml.Quaternionf;

public class CleverBlackboardBERenderer implements BlockEntityRenderer<CleverBlackboardBE> {
    private CleverBlackboardModel model;

    public CleverBlackboardBERenderer(BlockEntityRendererProvider.Context berp){
        model = new CleverBlackboardModel(berp.bakeLayer(CleverBlackboardModel.LAYER_LOCATION));
    }

    @Override
    public boolean shouldRenderOffScreen(CleverBlackboardBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 64;
    }

    @Override
    public boolean shouldRender(CleverBlackboardBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }

    @Override
    public void render(CleverBlackboardBE be, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        poseStack.pushPose();
        poseStack.scale(1.0f,1.0f,1.0f);

        if(be.custom){
            poseStack.mulPose(new Quaternionf().rotateY(be.yAngle));
            poseStack.translate(be.x_xtra, 0.0,be.z_xtra);
        }
        else{
            if(be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.NORTH){
                poseStack.mulPose(new Quaternionf().rotateY(3.15000000f));
                poseStack.translate(-0.5,0.0,-0.8);
            }
            else if (be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.SOUTH) {
                poseStack.mulPose(new Quaternionf().rotateY(0.0f));
                poseStack.translate(0.5,0.0,0.2);
            }
            else if (be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.EAST) {
                poseStack.mulPose(new Quaternionf().rotateY(1.57000000f));
                poseStack.translate(-0.5,0.0,0.2);
            }
            else if (be.getBlockState().getValue(CleverBlackboard.FACING) == Direction.WEST){
                poseStack.mulPose(new Quaternionf().rotateY(-1.57000000f));
                poseStack.translate(0.5,0.0,-0.8);
            }
        }

        this.model.setupAnim(be);
        this.model.renderToBuffer(poseStack,multiBufferSource.getBuffer(RenderType.entityCutout(CleverBlackboardModel.LAYER_LOCATION.getModel())),
                i,i1, TColors.getWhite());
        poseStack.popPose();
    }

    @Override
    public AABB getRenderBoundingBox(CleverBlackboardBE blockEntity) {
        return new AABB(blockEntity.getBlockPos().getX() - 4, blockEntity.getBlockPos().getY() - 2, blockEntity.getBlockPos().getZ() - 4,
                blockEntity.getBlockPos().getX() + 4, blockEntity.getBlockPos().getY() + 2, blockEntity.getBlockPos().getZ() + 4);
    }
}
