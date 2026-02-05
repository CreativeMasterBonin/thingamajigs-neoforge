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
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.rk.thingamajigs.blockentity.custom.PlateBE;

import java.util.logging.Logger;

public class PlateBERenderer implements BlockEntityRenderer<PlateBE>{
    public PlateBERenderer(BlockEntityRendererProvider.Context berpContext) {}

    @Override
    public void render(PlateBE pbent, float ptick, PoseStack ps1, MultiBufferSource buf1, int pl, int po) {
        try{
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            ItemStack itemStack = pbent.getRenderStack();

            // we want blocks to not be rotated sideways, heh
            // x = left-right, y = up-down, z = height for items not based on a block
            // Each item/blockitem in game is centered on the plate as good as possible
            if(itemStack.getItem() instanceof BlockItem){
                ps1.pushPose();
                ps1.mulPose(Axis.XP.rotationDegrees(0));
                ps1.scale(0.55f, 0.55f, 0.55f);
                ps1.translate(0.9f, 0.315f, 0.9f);
                itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(pbent.getLevel(), pbent.getBlockPos()),
                        OverlayTexture.NO_OVERLAY, ps1, buf1, pbent.getLevel(), 1);
                ps1.popPose();
            }
            else{
                ps1.pushPose();
                ps1.mulPose(Axis.XP.rotationDegrees(270));
                ps1.translate(0.5f, -0.5f, 0.118f);
                ps1.scale(0.35f, 0.35f, 0.35f);
                itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(pbent.getLevel(), pbent.getBlockPos()),
                        OverlayTexture.NO_OVERLAY, ps1, buf1, pbent.getLevel(), 1);
                ps1.popPose();
            }
        }
        catch (Exception e){
            Logger.getAnonymousLogger().warning("Thingamajigs couldn't render the Plate BE item!");
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLight);
    }
}
