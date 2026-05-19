package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.rk.thingamajigs.TClient;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.blockentity.custom.MitterCurtainBE;

import java.util.Objects;

public class MitterCurtainBERenderer implements BlockEntityRenderer<MitterCurtainBE> {
    private final Minecraft mc;
    private BlockRenderDispatcher dispatcher;
    private ModelBlockRenderer blockRenderer;
    private ModelManager manager;

    public MitterCurtainBERenderer(BlockEntityRendererProvider.Context ctx){
        mc = Objects.requireNonNull(Minecraft.getInstance());
        dispatcher = Objects.requireNonNull(mc.getBlockRenderer());
        blockRenderer = mc.getBlockRenderer().getModelRenderer();
        manager = Objects.requireNonNull(dispatcher.getBlockModelShaper().getModelManager());
    }

    private ModelResourceLocation mitterCurtain =
            new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,
                    TClient.carWashCustomModelBaseLocation + "mitter_curtain"),ModelResourceLocation.STANDALONE_VARIANT);

    @Override
    public void render(MitterCurtainBE mitterCurtainBE, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        makeMitterCurtain(mitterCurtainBE,0.2f,-2f,-0.25f,partialTick,poseStack,buffer,packedLight,-2.0f,25.0f,0.0f);
        makeMitterCurtain(mitterCurtainBE,-0.3f,-2f,0.33f,partialTick,poseStack,buffer,packedLight,-1.0f,27.0f,10.0f);
        makeMitterCurtain(mitterCurtainBE,0.3f,-2f,0.33f,partialTick,poseStack,buffer,packedLight,-1.0f,27.0f,10.0f);
        makeMitterCurtain(mitterCurtainBE,-0.3f,-2f,0,partialTick,poseStack,buffer,packedLight,-1.0f,25.0f,12.0f);
        makeMitterCurtain(mitterCurtainBE,0.3f,-2f,0,partialTick,poseStack,buffer,packedLight,-1.0f,25.0f,12.0f);
        makeMitterCurtain(mitterCurtainBE,-0.2f,-2f,-0.4f,partialTick,poseStack,buffer,packedLight,-3.0f,25.0f,25.0f);
        makeMitterCurtain(mitterCurtainBE,0.0f,-2f,0.4f,partialTick,poseStack,buffer,packedLight,-3.0f,25.0f,25.0f);
    }

    public void makeMitterCurtain(MitterCurtainBE mitter,float xOffset,float yOffset,float zOffset, float partialTick,PoseStack poseStack,MultiBufferSource buffer,int packedLight,float min, float max, float timingOffset){
        poseStack.pushPose();
        poseStack.translate(xOffset,yOffset,zOffset);
        float angle = min + Mth.sin(Util.getMillis() / (297.0f + timingOffset)) * max;
        if(mitter.getBlockState().getValue(BlockStateProperties.LIT)){
            if(mitter.horizontal){
                poseStack.rotateAround(Axis.XP.rotationDegrees(angle),
                        0.5f,2.0f,0.5f);
            }
            else{
                poseStack.rotateAround(Axis.ZP.rotationDegrees(angle),
                        0.5f,2.0f,0.5f);
            }
        }

        poseStack.rotateAround(Axis.YP.rotationDegrees(mitter.yAngle),0.5f,0.5f,0.5f);

        this.blockRenderer.renderModel(poseStack.last(), buffer.getBuffer(Sheets.solidBlockSheet()), null,
                manager.getModel(mitterCurtain), 1.0f, 1.0f, 1.0f,
                packedLight,
                OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.SOLID);
        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(MitterCurtainBE be) {
        return true;
    }

    @Override
    public int getViewDistance() {
        return 72;
    }

    @Override
    public boolean shouldRender(MitterCurtainBE be, Vec3 vec3) {
        return Vec3.atCenterOf(be.getBlockPos()).multiply(2.0, 3.0, 2.0)
                .closerThan(vec3.multiply(2.0, 3.0, 2.0), (double)this.getViewDistance());
    }

    @Override
    public AABB getRenderBoundingBox(MitterCurtainBE blockEntity) {
        return new AABB(blockEntity.getBlockPos().getX() - 2, blockEntity.getBlockPos().getY() - 4, blockEntity.getBlockPos().getZ() - 2,
                blockEntity.getBlockPos().getX() + 2, blockEntity.getBlockPos().getY() + 4, blockEntity.getBlockPos().getZ() + 2);
    }
}
