package net.rk.thingamajigs.render;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.entity.custom.Toilet;

public class ToiletRenderer extends EntityRenderer<Toilet>{
    public ToiletRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(Toilet c) {
        return ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,"block/placeholder.png");
    }

    @Override
    protected boolean shouldShowName(Toilet c){return false;}

    @Override
    public boolean shouldRender(Toilet c, Frustum fr, double d1, double d2, double d3){return false;}
}
