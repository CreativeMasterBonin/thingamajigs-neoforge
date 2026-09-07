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

public class GateArmModel extends Model {
    public static final ModelLayerLocation GATE_ARM = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/gate_arm.png"),"main");
    public ModelPart gateArm;
    public GateArmModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.gateArm = root.getChild("gate_arm");
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition gate_arm = partdefinition.addOrReplaceChild("gate_arm", CubeListBuilder.create().texOffs(0, 0).addBox(-63.0F, -2.0F, -1.0F, 64.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 132, 4);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, int i2) {
        this.gateArm.render(poseStack,vertexConsumer,i,i1);
    }
}
