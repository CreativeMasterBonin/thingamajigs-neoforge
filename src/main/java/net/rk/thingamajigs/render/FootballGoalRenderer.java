package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.custom.FootballGoal;
import net.rk.thingamajigs.blockentity.custom.FootballGoalBE;
import net.rk.thingamajigs.render.model.FootballGoalModel;
import org.joml.Quaternionf;

public class FootballGoalRenderer implements BlockEntityRenderer<FootballGoalBE> {
    public FootballGoalModel model;

    public FootballGoalRenderer(BlockEntityRendererProvider.Context ctx){
        this.model = new FootballGoalModel(ctx.bakeLayer(FootballGoalModel.LAYER_LOCATION));
    }

    @Override
    public void render(FootballGoalBE rotatableBlockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        poseStack.pushPose();
        poseStack.scale(1f,1f,1f);
        poseStack.translate(0.5,-1.5,0.5);
        // set the direction separate from the abstract method then pass that value in
        Direction dir = rotatableBlockEntity.getBlockState().getValue(FootballGoal.FACING);
        this.rotate(dir,rotatableBlockEntity,poseStack);
        VertexConsumer vc = multiBufferSource.getBuffer(RenderType.entityCutout(FootballGoalModel.LAYER_LOCATION.getModel()));
        this.model.setupAnim(rotatableBlockEntity);
        this.model.renderToBuffer(poseStack, vc, i,i1);
        poseStack.popPose();
    }

    public void rotate(Direction direction, FootballGoalBE rotatableBlockEntity, PoseStack poseStack){
        if(rotatableBlockEntity.custom){
            poseStack.mulPose(new Quaternionf().rotateY(rotatableBlockEntity.yAngle));
        }
        else{
            if(direction == Direction.NORTH){
                poseStack.mulPose(new Quaternionf().rotateY(0.0f));
            }
            else if (direction == Direction.SOUTH) {
                poseStack.mulPose(new Quaternionf().rotateY(3.15000000f));
            }
            else if (direction == Direction.EAST) {
                poseStack.mulPose(new Quaternionf().rotateY(-1.57000000f));
            }
            else if (direction == Direction.WEST){
                poseStack.mulPose(new Quaternionf().rotateY(1.57000000f));
            }
            else if(direction == Direction.UP){
                poseStack.mulPose(new Quaternionf().rotateX(-3.15000000f));
            }
            else{
                poseStack.mulPose(new Quaternionf().rotateX(3.15000000f));
            }
        }
    }

    @Override
    public boolean shouldRenderOffScreen(FootballGoalBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 72;
    }

    @Override
    public boolean shouldRender(FootballGoalBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }
}
