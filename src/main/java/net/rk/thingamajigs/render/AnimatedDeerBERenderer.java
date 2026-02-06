package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.custom.AnimatedDeer;
import net.rk.thingamajigs.blockentity.custom.AnimatedDeerBE;
import net.rk.thingamajigs.render.model.AnimatedDeerModel;
import org.joml.Quaternionf;

public class AnimatedDeerBERenderer implements BlockEntityRenderer<AnimatedDeerBE> {
    public AnimatedDeerModel model;
    public AnimatedDeerModel modelB;

    public AnimatedDeerBERenderer(BlockEntityRendererProvider.Context ctx){
        this.model = new AnimatedDeerModel(ctx.bakeLayer(AnimatedDeerModel.LAYER_LOCATION));
        this.modelB = new AnimatedDeerModel(ctx.bakeLayer(AnimatedDeerModel.LAYER_LOCATION));
    }

    @Override
    public void render(AnimatedDeerBE blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.scale(1f,1f,1f);
        poseStack.translate(0.5,0.0,0.5);
        VertexConsumer vc = bufferSource.getBuffer(RenderType.entityCutout(
                ResourceLocation.parse("thingamajigs:textures/entity/animated_deer.png")));
        if(!blockEntity.getBlockState().getValue(AnimatedDeer.ENABLED)){
            vc = bufferSource.getBuffer(RenderType.entityCutout(
                    ResourceLocation.parse("thingamajigs:textures/entity/animated_deer_off.png")));
        }

        if(blockEntity.custom){
            poseStack.mulPose(new Quaternionf().rotateY(blockEntity.yAngle));
        }
        else{
            if(blockEntity.getBlockState().getValue(AnimatedDeer.FACING) == Direction.NORTH){
                poseStack.mulPose(new Quaternionf().rotateY(0.0f));
            }
            else if (blockEntity.getBlockState().getValue(AnimatedDeer.FACING) == Direction.SOUTH) {
                poseStack.mulPose(new Quaternionf().rotateY(3.15000000f));
            }
            else if (blockEntity.getBlockState().getValue(AnimatedDeer.FACING) == Direction.EAST) {
                poseStack.mulPose(new Quaternionf().rotateY(-1.57000000f));
            }
            else if (blockEntity.getBlockState().getValue(AnimatedDeer.FACING) == Direction.WEST){
                poseStack.mulPose(new Quaternionf().rotateY(1.57000000f));
            }
        }

        if(blockEntity.alternateMovement){
            this.modelB.neck.xRot = 0.5f;
            this.modelB.head.xRot = -0.275f;
            this.modelB.neck.yRot = blockEntity.headAngle;
            this.modelB.motor.zRot = 1.57f;

            this.modelB.antlers.visible = blockEntity.showAntlers;
            this.modelB.setupAnimAlt(blockEntity);
            this.modelB.main.render(poseStack,vc,packedLight,packedOverlay);
        }
        else{
            this.model.antlers.visible = blockEntity.showAntlers;
            this.model.setupAnim(blockEntity);
            this.model.main.render(poseStack,vc,packedLight,packedOverlay);
        }
        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 70;
    }

    @Override
    public boolean shouldRender(AnimatedDeerBE blockEntity, Vec3 cameraPos) {
        return Vec3.atCenterOf(blockEntity.getBlockPos()).multiply(2, 2, 2)
                .closerThan(cameraPos.multiply(2, 2, 2), (double)this.getViewDistance());
    }

    @Override
    public AABB getRenderBoundingBox(AnimatedDeerBE blockEntity) {
        return new AABB(blockEntity.getBlockPos().getX() - 2, blockEntity.getBlockPos().getY() - 1, blockEntity.getBlockPos().getZ() - 2,
                blockEntity.getBlockPos().getX() + 2, blockEntity.getBlockPos().getY() + 1, blockEntity.getBlockPos().getZ() + 2);
    }
}
