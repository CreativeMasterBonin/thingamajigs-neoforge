package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.rk.thingamajigs.TClient;
import net.rk.thingamajigs.block.custom.CeilingFan;
import net.rk.thingamajigs.blockentity.custom.CeilingFanBE;

public class CeilingFanBERenderer implements BlockEntityRenderer<CeilingFanBE> {
    public CeilingFanBERenderer(BlockEntityRendererProvider.Context ctx){

    }

    @Override
    public void render(CeilingFanBE ceilingFanBE, float v, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0,0,0);
        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(poseStack.last(),
                bufferSource.getBuffer(Sheets.solidBlockSheet()),null,
                Minecraft.getInstance().getModelManager().getModel(TClient.ceilingFanConnector),
                1.0f,1.0f,1.0f,
                packedLight,
                OverlayTexture.NO_OVERLAY,ModelData.EMPTY,RenderType.SOLID);
        poseStack.popPose();

        renderFanBlade(0,ceilingFanBE,poseStack,bufferSource,packedLight);
        renderFanBlade(90,ceilingFanBE,poseStack,bufferSource,packedLight);
        renderFanBlade(180,ceilingFanBE,poseStack,bufferSource,packedLight);
        renderFanBlade(270,ceilingFanBE,poseStack,bufferSource,packedLight);
    }

    public void renderFanBlade(float angle, CeilingFanBE fan,PoseStack poseStack,MultiBufferSource buffer, int packedLight){
        poseStack.pushPose();
        float t = angle + fan.yAngle + Util.getMillis() / 2.0f;
        if(fan.getBlockState().getValue(CeilingFan.TOGGLED)){
            poseStack.rotateAround(Axis.YP.rotationDegrees(fan.reversed ? -t : t),0.5f,0.5f,0.5f);
        }
        else{
            poseStack.rotateAround(Axis.YP.rotationDegrees(angle),0.5f,0.5f,0.5f);
        }

        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(poseStack.last(),
                buffer.getBuffer(Sheets.cutoutBlockSheet()),null,
                Minecraft.getInstance().getModelManager().getModel(TClient.ceilingFanBlade),
                1.0f,1.0f,1.0f,
                packedLight,
                OverlayTexture.NO_OVERLAY,ModelData.EMPTY,RenderType.CUTOUT);

        poseStack.popPose();
    }

    @Override
    public int getViewDistance() {
        return 78;
    }

    @Override
    public boolean shouldRenderOffScreen(CeilingFanBE blockEntity) {
        return true;
    }

    @Override
    public boolean shouldRender(CeilingFanBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }

    @Override
    public AABB getRenderBoundingBox(CeilingFanBE blockEntity) {
        return new AABB(blockEntity.getBlockPos().getX() - 2, blockEntity.getBlockPos().getY() - 2, blockEntity.getBlockPos().getZ() - 2,
                blockEntity.getBlockPos().getX() + 2, blockEntity.getBlockPos().getY() + 2, blockEntity.getBlockPos().getZ() + 2);
    }
}
