package net.rk.thingamajigs.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.rk.thingamajigs.blockentity.custom.TheaterProjectorBE;

public class TheaterProjectorModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.parse("thingamajigs:textures/entity/theater_projector.png"), "main");
    public final ModelPart main;
    public final ModelPart projectorTop;

    public TheaterProjectorModel(ModelPart root){
        super(RenderType::entitySolid);
        this.main = root.getChild("base");
        this.projectorTop = main.getChild("projector_top");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 6.0F, -16.0F, 16.0F, 16.0F, 32.0F, new CubeDeformation(0.0F))
                .texOffs(64, 0).addBox(-7.9F, 3.0F, 1.1F, 16.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 8.0F));

        PartDefinition screen_r1 = base.addOrReplaceChild("screen_r1", CubeListBuilder.create().texOffs(0, 24).addBox(-4.0F, -2.5F, -1.0F, 8.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 17.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition holderscreen_r1 = base.addOrReplaceChild("holderscreen_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.0F, 14.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition projector_top = base.addOrReplaceChild("projector_top", CubeListBuilder.create().texOffs(0, 48).addBox(-7.99F, -12.0F, -21.0F, 16.0F, 12.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(52, 48).addBox(-3.0F, -9.0F, -33.0F, 6.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 5.0F));

        PartDefinition connector1 = projector_top.addOrReplaceChild("connector1", CubeListBuilder.create().texOffs(64, 6).addBox(-7.0F, -6.0F, 3.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = connector1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 58).addBox(-2.1F, 4.0F, 6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -9.0F, 2.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r2 = connector1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 0).addBox(-2.1F, -3.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -9.0F, 2.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition connector2 = projector_top.addOrReplaceChild("connector2", CubeListBuilder.create().texOffs(0, 48).addBox(-7.0F, -6.0F, 3.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = connector2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 12).addBox(-2.1F, 4.0F, 6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -9.0F, 2.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r4 = connector2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 12).addBox(-2.1F, -3.0F, -3.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -9.0F, 2.0F, 0.7854F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack ps, VertexConsumer vc, int p1, int p2, int color) {
        this.main.render(ps,vc,p1,p2);
    }

    public void setupAnim(TheaterProjectorBE be){
        this.main.xRot = Mth.PI;
    }
}
