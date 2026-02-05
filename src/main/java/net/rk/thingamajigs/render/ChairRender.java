package net.rk.thingamajigs.render;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.entity.custom.Chair;

public class ChairRender extends EntityRenderer<Chair> {
    public ChairRender(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(Chair c) {
        return ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,"block/placeholder.png");
    }

    @Override
    protected boolean shouldShowName(Chair c){return false;}

    @Override
    public boolean shouldRender(Chair c, Frustum fr, double d1, double d2, double d3){return false;}
}
