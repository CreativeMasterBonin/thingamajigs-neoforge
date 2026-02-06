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
import net.rk.thingamajigs.blockentity.custom.AnimatedDeerBE;

public class AnimatedDeerModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/animated_deer.png"), "main");
    public final ModelPart main;
    private final ModelPart body;
    public final ModelPart neck;
    public final ModelPart head;
    public final ModelPart motor;
    public final ModelPart rod;
    public final ModelPart gear;
    public final ModelPart antlers;

    public AnimatedDeerModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.main = root.getChild("main");
        this.body = this.main.getChild("body");
        this.neck = this.body.getChild("neck");
        this.head = this.neck.getChild("head");
        this.antlers = this.head.getChild("antlers");
        this.motor = this.body.getChild("motor");
        this.rod = this.motor.getChild("rod");
        this.gear = this.motor.getChild("gear");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(1.0F, 13.0F, 0.0F));

        PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(15, 54).addBox(-2.0F, 2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(11, 60).addBox(-2.0F, 2.0F, 2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -3.0F, -8.0F, 8.0F, 7.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(2.0F, -13.0F, -8.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(48, 0).addBox(2.0F, -13.0F, 6.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-4.0F, -13.0F, 6.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 0).addBox(-4.0F, -13.0F, -8.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(11, 53).addBox(-2.0F, -5.0F, 8.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(9, 26).addBox(-2.0F, -2.0F, -7.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 49).addBox(-2.0F, -3.0F, -8.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-2.0F, -3.0F, -7.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(11, 40).addBox(-2.5F, -3.5F, -6.5F, 5.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 55).addBox(-1.5F, -2.5F, -9.5341F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 61).addBox(-1.0F, -2.0F, -10.5341F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 27).addBox(-3.0F, -3.2588F, -4.5341F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.5F, 0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(48, 12).addBox(-1.5F, -2.2588F, 0.4659F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 4.0F, -2.0F, 0.0F, 0.0F, -0.5236F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 12).addBox(-1.5F, -2.2588F, 0.4659F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 4.0F, -2.0F, 0.0F, 0.0F, 0.5236F));

        PartDefinition antlers = head.addOrReplaceChild("antlers", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, 0.0F, 0.0F, 7.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(41, 38).addBox(-7.0F, 0.0F, 0.0F, 7.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 2.0F, 0.0F));

        PartDefinition motor = body.addOrReplaceChild("motor", CubeListBuilder.create().texOffs(24, 49).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rod = motor.addOrReplaceChild("rod", CubeListBuilder.create().texOffs(28, 52).addBox(-0.5F, -0.5F, -9.5F, 1.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 0.5F, 2.5F));

        PartDefinition gear = motor.addOrReplaceChild("gear", CubeListBuilder.create().texOffs(54, 58).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.5F, 2.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void setupAnim(AnimatedDeerBE blockEntity){
        this.main.xRot = 0.0f;

        this.neck.xRot = blockEntity.headAngle;
        this.head.xRot = 0.231f;
        this.neck.yRot = 0.0f;
        this.motor.zRot = 0.0f;

        this.gear.xRot = blockEntity.gearAngle;
        this.rod.xRot = blockEntity.headAngle / 4.3571f * -1.0f;
    }

    public void setupAnimAlt(AnimatedDeerBE blockEntity){
        this.main.xRot = 0.0f;
        this.gear.xRot = blockEntity.gearAngle;
        this.rod.xRot = blockEntity.headAngle / 4.3571f * -1.0f;
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vc, int i1, int i2, int i3) {
        this.main.render(poseStack,vc,i1,i2);
    }
}
