package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.blockentity.custom.CurvedMonitorBE;
import net.rk.thingamajigs.render.model.CurvedMonitorModel;

public class CurvedMonitorBERenderer implements BlockEntityRenderer<CurvedMonitorBE>{
    private CurvedMonitorModel model;

    public CurvedMonitorBERenderer(BlockEntityRendererProvider.Context berp){
        model = new CurvedMonitorModel(berp.bakeLayer(CurvedMonitorModel.LAYER_LOCATION));
    }

    @Override
    public boolean shouldRenderOffScreen(CurvedMonitorBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 64;
    }

    @Override
    public boolean shouldRender(CurvedMonitorBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }

    @Override
    public void render(CurvedMonitorBE be, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        poseStack.pushPose();
        poseStack.scale(1.0f,1.0f,1.0f);
        poseStack.translate(0.3800f,-1.4350f,0.3800f);

        this.model.setupAnim(be);
        this.model.renderToBuffer(poseStack,
                multiBufferSource.getBuffer(RenderType.entityTranslucent(CurvedMonitorModel.LAYER_LOCATION.getModel())),
                i,i1);
        poseStack.popPose();
    }

    @Override
    public AABB getRenderBoundingBox(CurvedMonitorBE blockEntity) {
        return new AABB(blockEntity.getBlockPos().getX() - 4, blockEntity.getBlockPos().getY() - 2, blockEntity.getBlockPos().getZ() - 4,
                blockEntity.getBlockPos().getX() + 4, blockEntity.getBlockPos().getY() + 2, blockEntity.getBlockPos().getZ() + 4);
    }
}
