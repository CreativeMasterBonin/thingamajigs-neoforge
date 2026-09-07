package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.block.custom.StopGate;
import net.rk.thingamajigs.blockentity.custom.StopGateBE;
import net.rk.thingamajigs.render.model.GateArmModel;

public class StopGateBERenderer implements BlockEntityRenderer<StopGateBE> {
    public static final float BOUNDING_BOX_RANGE = 4.575f;
    public GateArmModel gateArmModel;
    public static final ResourceLocation GATE_ARM_ALL = ResourceLocation.parse("thingamajigs:textures/entity/gate_arm.png");

    public StopGateBERenderer(BlockEntityRendererProvider.Context ctx){
        gateArmModel = new GateArmModel(ctx.bakeLayer(GateArmModel.GATE_ARM));
    }

    @Override
    public AABB getRenderBoundingBox(StopGateBE gate) {
        BlockPos bp = gate.getBlockPos();
        return new AABB(
                bp.getX() - BOUNDING_BOX_RANGE,
                bp.getY() - BOUNDING_BOX_RANGE,
                bp.getZ() - BOUNDING_BOX_RANGE,
                bp.getX() + BOUNDING_BOX_RANGE,
                bp.getY() + BOUNDING_BOX_RANGE,
                bp.getZ() + BOUNDING_BOX_RANGE);
    }

    @Override
    public void render(StopGateBE stopGateBE, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        // rotate arm up-down degrees
        float negAngle = stopGateBE.gateAngle * -1.0f;
        float posAngle = stopGateBE.gateAngle;

        // invert offset by 90 degrees based on settings
        if(stopGateBE.inverse){
            negAngle = (stopGateBE.gateAngle + 90.0f) * -1.0f;
            posAngle = stopGateBE.gateAngle + 90.0f;
        }

        // make sure direction state is on the block
        if(stopGateBE.getBlockState().hasProperty(StopGate.FACING)){
            // rotate the pose based on direction
            switch(stopGateBE.getBlockState().getValue(StopGate.FACING)){
                case NORTH->{
                    poseStack.rotateAround(Axis.ZP.rotationDegrees(negAngle),
                            stopGateBE.northXRot,stopGateBE.northYRot,stopGateBE.northZRot);
                    poseStack.rotateAround(Axis.YP.rotationDegrees(0),0.5f,0.5f,0.5f);
                }
                case SOUTH->{
                    poseStack.rotateAround(Axis.ZP.rotationDegrees(posAngle),stopGateBE.southXRot,stopGateBE.southYRot,stopGateBE.southZRot);
                    poseStack.rotateAround(Axis.YP.rotationDegrees(180),0.5f,0.5f,0.5f);
                }
                case EAST->{
                    poseStack.rotateAround(Axis.XP.rotationDegrees(posAngle),stopGateBE.eastXRot,stopGateBE.eastYRot,stopGateBE.eastZRot);
                    poseStack.rotateAround(Axis.YP.rotationDegrees(-90),0.5f,0.5f,0.5f);
                }
                case WEST->{
                    poseStack.rotateAround(Axis.XP.rotationDegrees(negAngle),stopGateBE.westXRot,stopGateBE.westYRot,stopGateBE.westZRot);
                    poseStack.rotateAround(Axis.YP.rotationDegrees(90),0.5f,0.5f,0.5f);
                }
            }
        }
        // move arm to position
        poseStack.translate(stopGateBE.offsetX,stopGateBE.offsetY,stopGateBE.offsetZ);
        // render the arm model
        gateArmModel.gateArm.render(poseStack,buffer.getBuffer(RenderType.entitySolid(
                GATE_ARM_ALL)),packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 64;
    }

    @Override
    public boolean shouldRenderOffScreen(StopGateBE blockEntity) {
        return true;
    }
}
