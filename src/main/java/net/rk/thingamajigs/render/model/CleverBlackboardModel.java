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
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.blockentity.custom.CleverBlackboardBE;

public class CleverBlackboardModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID, "textures/entity/clever_blackboard.png"), "main");
    private final ModelPart main;

    public CleverBlackboardModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.main = root.getChild("board");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition board = partdefinition.addOrReplaceChild("board", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -32.5F, -4.0F, 54.0F, 32.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 44).addBox(11.0F, -32.5F, -8.0F, 32.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(26, 50).addBox(37.0F, -30.5F, -6.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 50).addBox(30.0F, -30.5F, -6.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(14.0F, -30.5F, -7.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 35).addBox(0.0F, -0.5F, -9.0F, 54.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(27.0F, 32.5F, -4.0F, 0.0F, 3.1416F, 0.0F));


        return LayerDefinition.create(meshdefinition, 128, 64);
    }

    public void setupAnim(CleverBlackboardBE be){
        //main.zRot = 0;
        //main.xRot = 3.14159265f;
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vc, int packedLight, int packedOverlay, int color) {
        main.render(poseStack,vc,packedLight,packedOverlay);
    }
}
