package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.custom.AnimatedIceRink;
import net.rk.thingamajigs.blockentity.custom.AnimatedIceRinkBE;
import net.rk.thingamajigs.render.model.AnimatedIceRinkModel;
import org.joml.Quaternionf;

public class AnimatedIceRinkRenderer implements BlockEntityRenderer<AnimatedIceRinkBE> {
    public AnimatedIceRinkModel model;
    public static final ResourceLocation animatedIceRinkAll = ResourceLocation.parse("thingamajigs:textures/entity/animated_snow_rink.png");

    public AnimatedIceRinkRenderer(BlockEntityRendererProvider.Context ctx){
        this.model = new AnimatedIceRinkModel(ctx.bakeLayer(AnimatedIceRinkModel.ICE_RINK_ALL));
    }

    @Override
    public void render(AnimatedIceRinkBE iceRinkBE, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.scale(1f,1f,1f);
        poseStack.translate(0.5,-1.35,0.5);
        if(iceRinkBE.custom){
            poseStack.mulPose(new Quaternionf().rotateY(iceRinkBE.yAngle));
        }
        else{
            if(iceRinkBE.getBlockState().getValue(AnimatedIceRink.FACING) == Direction.NORTH){
                poseStack.mulPose(new Quaternionf().rotateY(3.15000000f));
            }
            else if (iceRinkBE.getBlockState().getValue(AnimatedIceRink.FACING) == Direction.SOUTH) {
                poseStack.mulPose(new Quaternionf().rotateY(0.0f));
            }
            else if (iceRinkBE.getBlockState().getValue(AnimatedIceRink.FACING) == Direction.EAST) {
                poseStack.mulPose(new Quaternionf().rotateY(1.57000000f));
            }
            else if (iceRinkBE.getBlockState().getValue(AnimatedIceRink.FACING) == Direction.WEST){
                poseStack.mulPose(new Quaternionf().rotateY(-1.57000000f));
            }
        }

        float gearAngle = partialTick + Util.getMillis() / 37.0f;

        // show the animation if on
        if(iceRinkBE.getBlockState().hasProperty(AnimatedIceRink.TOGGLED)){
            if(iceRinkBE.getBlockState().getValue(AnimatedIceRink.TOGGLED)){
                this.model.setupAnim(iceRinkBE,gearAngle / 132.0f,gearAngle / 72.0f);
            }
            else{
                this.model.noAnim();
            }
        }
        this.model.main.render(poseStack,bufferSource.getBuffer(
                RenderType.entityCutout(animatedIceRinkAll)),
                packedLight,packedOverlay);
        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 30;
    }

    @Override
    public boolean shouldRender(AnimatedIceRinkBE blockEntity, Vec3 cameraPos) {
        return Vec3.atCenterOf(blockEntity.getBlockPos()).multiply(2, 1, 2)
                .closerThan(cameraPos.multiply(2, 1, 2), (double)this.getViewDistance());
    }

}
