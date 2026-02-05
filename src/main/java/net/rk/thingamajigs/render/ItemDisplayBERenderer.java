package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.AABB;
import net.rk.thingamajigs.blockentity.custom.ItemDisplayBE;
import org.joml.Quaternionf;

import java.util.logging.Logger;

public class ItemDisplayBERenderer implements BlockEntityRenderer<ItemDisplayBE>{
    public ItemDisplayBERenderer(BlockEntityRendererProvider.Context berpContext) {}

    // thx quat and others on neodisc for spinning help
    // updated jun302024
    @Override
    public void render(ItemDisplayBE itemdbe, float ptick, PoseStack ps1, MultiBufferSource buf1, int pl, int po) {
        try{
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

            ps1.pushPose();
            ps1.mulPose(Axis.XP.rotationDegrees(0));
            ps1.scale(0.57f, 0.57f, 0.57f);
            ps1.translate(0.7f, 0.7f, 0.7f);

            ps1.mulPose(new Quaternionf().rotationY(itemdbe.rot * (float) (Math.PI / 270.0)));

            if(!itemdbe.hidePose){
                if(!itemdbe.item.isEmpty()){
                    itemRenderer.renderStatic(itemdbe.item, ItemDisplayContext.FIXED, getLightLevel(itemdbe.getLevel(),itemdbe.getBlockPos()),
                            OverlayTexture.NO_OVERLAY, ps1, buf1, itemdbe.getLevel(), 1);
                }
            }
            ps1.popPose();
        }
        catch (Exception e){
            Logger.getAnonymousLogger().warning("Thingamajigs' ItemDisplayRenderer encountered an exception! ERR: " + e.getMessage());
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLight);
    }

    @Override
    public boolean shouldRenderOffScreen(ItemDisplayBE idbe) {
        return false;
    }

    @Override
    public int getViewDistance() {
        return 24;
    }

    @Override
    public AABB getRenderBoundingBox(ItemDisplayBE be) {
        BlockPos bp = be.getBlockPos();
        return new AABB(bp.getX(),bp.getY() - getViewDistance(),bp.getZ(),bp.getX(),bp.getY() + getViewDistance(),bp.getZ());
    }
}
