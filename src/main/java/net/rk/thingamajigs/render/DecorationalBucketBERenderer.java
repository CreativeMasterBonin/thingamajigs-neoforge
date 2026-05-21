package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.rk.thingamajigs.blockentity.custom.DecorationalBucketBE;

public class DecorationalBucketBERenderer implements BlockEntityRenderer<DecorationalBucketBE> {
    public DecorationalBucketBERenderer(BlockEntityRendererProvider.Context ctx){
    }
    @Override
    public void render(DecorationalBucketBE decorationalBucketBE, float v, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int i1) {
        poseStack.pushPose();
        poseStack.translate(0,0,0);
        poseStack.popPose();

        if(!decorationalBucketBE.fakeTank.getFluid().isEmpty()){
            if(decorationalBucketBE.fakeTank.getFluid().getFluid() != Fluids.EMPTY){

                ResourceLocation texture = IClientFluidTypeExtensions.of(decorationalBucketBE.fakeTank.getFluid().getFluid()).getStillTexture();
                ResourceLocation textureWithPng;

                int color = IClientFluidTypeExtensions.of(decorationalBucketBE.fakeTank.getFluid().getFluid()).getTintColor();
                if(texture == null){
                    texture = MissingTextureAtlasSprite.getLocation();
                    textureWithPng = texture;
                }
                else{
                    textureWithPng = ResourceLocation.fromNamespaceAndPath(texture.getNamespace(),"textures/" + texture.getPath() + ".png");
                }

                boolean isGlowingOrLava = decorationalBucketBE.fakeTank.getFluid().getFluidType().getLightLevel() >= 1 || decorationalBucketBE.fakeTank.getFluid().getFluid() == Fluids.LAVA;

                // if a glowing liquid, use an emissive rendertype and light value
                if(isGlowingOrLava){
                    renderPart(decorationalBucketBE,
                            poseStack,
                            buffer.getBuffer(RenderType.beaconBeam(textureWithPng,false)),
                            color,
                            16777215);
                }
                else{
                    // try to get the biome water color and grab the tint
                    int color2 = color;
                    try{
                        color2 = decorationalBucketBE.getLevel().getBiome(decorationalBucketBE.getBlockPos()).value().getWaterColor();
                    }
                    catch (Exception e){

                    }

                    // check if this is water, then tint it according to the color
                    if(decorationalBucketBE.fakeTank.getFluid().getFluid() == Fluids.WATER){
                        renderPart(decorationalBucketBE,
                                poseStack,
                                buffer.getBuffer(RenderType.entitySolid(textureWithPng)),
                                color2,
                                16777215);
                    }
                    else{
                        renderPart(decorationalBucketBE,
                                poseStack,
                                buffer.getBuffer(RenderType.entitySolid(textureWithPng)),
                                color,
                                decorationalBucketBE.fakeTank.getFluid().getFluidType().getLightLevel() >= 1 ? 16777215 : packedLight);
                    }
                }
            }
        }
    }

    public static void renderPart(DecorationalBucketBE bucketBE, PoseStack poseStack,VertexConsumer consumer, int color, int packedLight){
        poseStack.pushPose();
        poseStack.rotateAround(Axis.ZP.rotationDegrees(90),0.5f,0.5f,0.5f);
        poseStack.translate(0.5,0.25,0.25);
        poseStack.scale(0.5f,0.5f,0.5f);

        float x1 = 0.0f;
        float x2 = 0.0f;
        float x3 = 0.0f;
        float x4 = 0.0f;
        float z1 = 1.0f;
        float z2 = 1.0f;
        float z3 = 0.0f;
        float z4 = 0.0f;
        float minU = 0.0f;
        float minV = 0.0f;
        float maxU = 0.5f;
        float maxV = 0.02f;

        // white is 16777215
        if(color < 0 || color > 16777215){
            color = 16777215;
        }

        int minY = 0;
        int maxY = 1;

        renderQuad(poseStack.last(),consumer,minY, maxY, x1, z1, x2, z2, minU, maxU, minV, maxV,color, packedLight);
        renderQuad(poseStack.last(), consumer,minY, maxY, x4, z4, x3, z3, minU, maxU, minV, maxV,color, packedLight);
        renderQuad(poseStack.last(), consumer,minY, maxY, x2, z2, x4, z4, minU, maxU, minV, maxV,color, packedLight);
        renderQuad(poseStack.last(), consumer,minY, maxY, x3, z3, x1, z1, minU, maxU, minV, maxV,color, packedLight);
        poseStack.popPose();
    }

    public static void renderQuad(PoseStack.Pose pose, VertexConsumer consumer, int minY, int maxY, float minX, float minZ, float maxX, float maxZ, float minU, float maxU, float minV, float maxV, int customColor, int packedLight) {
        addVertex(pose, consumer, customColor, maxY, minX, minZ, maxU, minV,packedLight);
        addVertex(pose, consumer, customColor, minY, minX, minZ, maxU, maxV,packedLight);
        addVertex(pose, consumer, customColor, minY, maxX, maxZ, minU, maxV,packedLight);
        addVertex(pose, consumer, customColor, maxY, maxX, maxZ, minU, minV,packedLight);
    }

    public static void addVertex(PoseStack.Pose pose, VertexConsumer consumer, int color, int y, float x, float z, float u, float v, int packedLight) {
        consumer.addVertex(pose, x, (float)y, z).setColor(color).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(16777215).setNormal(pose, 0.0F, 1.0F, 0.0F);
    }

    @Override
    public boolean shouldRenderOffScreen(DecorationalBucketBE be) {
        return false;
    }

    @Override
    public int getViewDistance() {
        return 64;
    }

    @Override
    public boolean shouldRender(DecorationalBucketBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 2.0, 2.0)
                .closerThan(vec3.multiply(2.0, 2.0, 2.0), (double)this.getViewDistance());
    }

    @Override
    public AABB getRenderBoundingBox(DecorationalBucketBE blockEntity) {
        return new AABB(blockEntity.getBlockPos().getX() - 1, blockEntity.getBlockPos().getY() - 1, blockEntity.getBlockPos().getZ() - 1,
                blockEntity.getBlockPos().getX() + 1, blockEntity.getBlockPos().getY() + 1, blockEntity.getBlockPos().getZ() + 1);
    }
}
