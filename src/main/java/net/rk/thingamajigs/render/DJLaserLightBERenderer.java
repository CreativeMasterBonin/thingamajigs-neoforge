package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.blockentity.custom.DJLaserLightBE;
import net.rk.thingamajigs.xtras.TColors;

import java.util.logging.Logger;

@SuppressWarnings("deprecated,unused")
public class DJLaserLightBERenderer implements BlockEntityRenderer<DJLaserLightBE> {
    @Override
    public void render(DJLaserLightBE be, float parttick, PoseStack ps, MultiBufferSource mbs, int light, int overlay) {
        long time = be.getLevel().getGameTime();
        if(!be.hidePose){
            ps.pushPose();
            ps.translate(0.5, 0.5, 0.5);

            float osc = be.hOsc;
            float vosc = be.vOsc;
            float horz_multi = be.hMulti;
            float vert_multi = be.vMulti;

            float ftick = (float)be.ticks;

            // test code that was rejected as it didn't allow for multiple lasers
            /*
            ps.mulPose(Axis.YP.rotationDegrees((Mth.sin(osc * (ftick / 360)) * horz_multi) - be.angle + be.angleOffset));

            ps.mulPose(Axis.XP.rotationDegrees((Mth.sin(vosc * (ftick / 360)) * vert_multi) - be.verticalAngle + be.vAngleOffset));

            ps.scale(1.0f,(be.height + 0.5f),1.0f);
            */

            if(be.lightMode == 1){
                if(parttick / 16 <= 2){
                    be.color = be.getLevel().getRandom().nextInt(TColors.getWhite());
                }
            }

            try {
                String str = be.laserTextureLoc;
                CUSTOM_TEXTURE = ResourceLocation.parse(str);
            }
            catch(Exception e){
                CUSTOM_TEXTURE = TEXTURE;
            }

            if(be.laserCount != 1){
                be.laserCount = 1;
            }

            try{
                if(be.laserCount <= 1){
                    render(ps,mbs,parttick,time,0,1,be.color,
                            be.getBlockPos(),be.laserSize,be.angle,be.verticalAngle,
                            osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick
                    );
                }
                else{
                    // experimental, slightly worked
                    /*
                    for(int num = 1; num < be.laserCount; num++){
                        if(num >= 7){
                            break;
                        }
                        HolderObjectLaser hol = new HolderObjectLaser(num,ps,mbs,parttick,time,be,osc,vosc,horz_multi,vert_multi,ftick);
                        if(num == be.laserCount){
                            break;
                        }
                    }
                    */
                    switch(be.laserCount){
                        case 2:
                            render(ps,mbs,parttick,time,0,1,be.color,
                                be.getBlockPos(),be.laserSize,be.angle - 5,be.verticalAngle,
                                osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle + 5,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            break;
                        case 3:
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 5,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 5,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            break;
                        case 4:
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 10,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 5,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle + 5,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 10,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            break;
                        case 5:
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 10,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 5,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle + 5,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 10,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            break;
                        case 6:
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 15,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 10,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 5,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle + 5,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle + 10,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle + 15,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            break;
                        case 7:
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 15,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 10,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle - 5,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle + 5,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle + 10,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            render(ps,mbs,parttick,time,0,1,be.color,
                                    be.getBlockPos(),be.laserSize,be.angle + 15,be.verticalAngle,
                                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            break;
                        default:
                            render(ps,mbs,parttick,time,0,1,be.color,
                                be.getBlockPos(),be.laserSize,be.angle,be.verticalAngle,
                                osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick);
                            break;
                    }
                }
            }
            catch (Exception e){
                Logger.getAnonymousLogger().warning("DJ Laser Light BE Renderer caught an exception! ERR: " + e.getMessage());
            }
            ps.popPose();
        }
    }

    public static class HolderObjectLaser{
        public HolderObjectLaser(int num, PoseStack ps,MultiBufferSource mbs,float parttick, long time, DJLaserLightBE be,
        float osc,float vosc,float horz_multi, float vert_multi, float ftick){
            render(ps,mbs,parttick,time,0,1,be.color,
                    be.getBlockPos(),be.laserSize,be.angle + (num),be.verticalAngle,
                    osc,vosc,horz_multi,vert_multi,be.angleOffset,be.vAngleOffset,be.height,ftick
            );
        }
    }

    private static void render(PoseStack ps1,
                               MultiBufferSource mbs1, float ptick, long p_112190_,
                               int p_112191_, int p_112192_, int color, BlockPos bp,
                               float size,
                               float hAngle, float vAngle,
                               float osc, float vosc,
                               float hMulti, float vMulti,
                               float hOff, float vOff,
                               int height, float ftick
    ){
        ps1.mulPose(Axis.YP.rotationDegrees((Mth.sin(osc * (ftick / 360)) * hMulti) - hAngle + hOff));

        ps1.mulPose(Axis.XP.rotationDegrees((Mth.sin(vosc * (ftick / 360)) * vMulti) - vAngle + vOff));

        ps1.scale(size,(height + 0.5f),size);

        extendedRenderLaser(ps1, mbs1, TEXTURE, ptick, 1.0F, p_112190_, p_112191_, p_112192_, color, 0.2F, 0.25F,bp);
    }

    public DJLaserLightBERenderer(BlockEntityRendererProvider.Context berpContext){}
    private static final ResourceLocation TEXTURE = ResourceLocation.parse("minecraft:textures/block/white_concrete.png");
    public static ResourceLocation CUSTOM_TEXTURE = ResourceLocation.parse("minecraft:textures/block/white_concrete.png");


    @Override
    public int getViewDistance() {
        return 256;
    }

    @Override
    public boolean shouldRender(DJLaserLightBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(1.0, 0.0, 1.0)
                .closerThan(vec3.multiply(1.0, 0.0, 1.0), (double)this.getViewDistance());
    }

    @Override
    public boolean shouldRenderOffScreen(DJLaserLightBE be) {
        return true;
    }

    @Override
    public AABB getRenderBoundingBox(DJLaserLightBE be) {
        BlockPos bp = be.getBlockPos();
        return new AABB(bp.getX() - (float)be.height,bp.getY() - getViewDistance(),bp.getZ() - (float)be.height,bp.getX() + (float)be.height,bp.getY() + getViewDistance(),bp.getZ() + (float)be.height);
    }

    // b
    private static void ver(PoseStack.Pose ps, VertexConsumer vc, int i1, int i2, float f1, float f2, float f3, float f4){
        vc.addVertex(ps, f1, (float)i2, f2)
                .setColor(i1)
                .setUv(f3, f4)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(15728880)
                .setNormal(ps, 0.0F, 1.0F, 0.0F);
    }

    private static void qua(
            PoseStack.Pose pose,
            VertexConsumer vc1,
            int i1,
            int i2,
            int i3,
            float f1,
            float f2,
            float f3,
            float f4,
            float f5,
            float f6,
            float f7,
            float f8) {
        ver(pose, vc1, i1, i3, f1, f2, f6, f7);
        ver(pose, vc1, i1, i2, f1, f2, f6, f8);
        ver(pose, vc1, i1, i2, f3, f4, f5, f8);
        ver(pose, vc1, i1, i3, f3, f4, f5, f7);
    }

    private static void rp(
            PoseStack ps1,
            VertexConsumer vc1,
            int i1,
            int i2,
            int i3,
            float p1,
            float p2,
            float p3,
            float p4,
            float u1,
            float u2,
            float u3,
            float u4,
            float t1,
            float tu1,
            float tup1,
            float tuv1
    ){
        PoseStack.Pose ps = ps1.last();
        qua(ps, vc1, i1, i2, i3, p1, p2, p3, p4, t1, tu1, tup1, tuv1);
        qua(ps, vc1, i1, i2, i3, u3, u4, u1, u2, t1, tu1, tup1, tuv1);
        qua(ps, vc1, i1, i2, i3, p3, p4, u3, u4, t1, tu1, tup1, tuv1);
        qua(ps, vc1, i1, i2, i3, u1, u2, p1, p2, t1, tu1, tup1, tuv1);
    }

    public static void extendedRenderLaser(
            PoseStack ps,
            MultiBufferSource mbs,
            ResourceLocation rs,
            float ack1,
            float tfc1,
            long lng1,
            int i1,
            int i2,
            int color,
            float ftuvp1,
            float lf1,
            BlockPos bp){
        int i = i1 + i2;
        ps.pushPose();


        float f = (float)Math.floorMod(lng1, 40) + ack1;
        float f1 = i2 < 0 ? f : -f;
        float f2 = Mth.frac(f1 * 0.2F - (float)Mth.floor(f1 * 0.1F));


        ps.pushPose();
        float f6 = -ftuvp1;
        float f9 = -ftuvp1;
        float f12 = -1.0F + f2;
        float f13 = (float) i2 * tfc1 * (0.5F / ftuvp1) + f12;

        VertexConsumer vc = mbs.getBuffer(RenderType.beaconBeam(CUSTOM_TEXTURE,false));
        rp(ps, vc, color,
                i1, i, 0.0F, ftuvp1, ftuvp1, 0.0F, f6, 0.0F, 0.0F, f9, 0.0F, 1.0F, f13, f12
        );
        ps.popPose();
        ps.popPose();
    }
}
