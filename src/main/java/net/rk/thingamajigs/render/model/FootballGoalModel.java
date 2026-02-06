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
import net.rk.thingamajigs.blockentity.custom.FootballGoalBE;

public class FootballGoalModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/football_goal.png"), "main");
    public final ModelPart main;

    public FootballGoalModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.main = root.getChild("main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 6).addBox(-2.0F, -48.0F, -2.0F, 4.0F, 48.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-32.0F, -50.0F, -2.0F, 64.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(16, 6).addBox(30.0F, -98.0F, -2.0F, 2.0F, 48.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 6).addBox(-32.0F, -98.0F, -2.0F, 2.0F, 48.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 142, 64);
    }

    public void setupAnim(FootballGoalBE be){
        this.main.yRot = be.yAngle;
        this.main.zRot = 0;
        this.main.xRot = Mth.PI;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, int i3) {
        this.main.render(poseStack,vertexConsumer,i,i1);
    }
}
