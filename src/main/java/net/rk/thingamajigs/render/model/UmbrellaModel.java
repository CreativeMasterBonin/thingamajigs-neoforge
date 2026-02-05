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
import net.rk.thingamajigs.blockentity.custom.UmbrellaBE;

public class UmbrellaModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/umbrella.png"), "main");

    public static final ModelLayerLocation ORANGE = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/orange_umbrella.png"), "main");
    public static final ModelLayerLocation MAGENTA = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/magenta_umbrella.png"), "main");
    public static final ModelLayerLocation LIGHT_BLUE = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/light_blue_umbrella.png"), "main");
    public static final ModelLayerLocation YELLOW = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/yellow_umbrella.png"), "main");
    public static final ModelLayerLocation LIME = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/lime_umbrella.png"), "main");
    public static final ModelLayerLocation PINK = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/pink_umbrella.png"), "main");
    public static final ModelLayerLocation GRAY = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/gray_umbrella.png"), "main");
    public static final ModelLayerLocation LIGHT_GRAY = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/light_gray_umbrella.png"), "main");
    public static final ModelLayerLocation CYAN = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/cyan_umbrella.png"), "main");
    public static final ModelLayerLocation PURPLE = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/purple_umbrella.png"), "main");
    public static final ModelLayerLocation BLUE = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/blue_umbrella.png"), "main");
    public static final ModelLayerLocation BROWN = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/brown_umbrella.png"), "main");
    public static final ModelLayerLocation GREEN = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/green_umbrella.png"), "main");
    public static final ModelLayerLocation RED = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/red_umbrella.png"), "main");
    public static final ModelLayerLocation BLACK = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/black_umbrella.png"), "main");

    private final ModelPart main;
    private final ModelPart pole;
    private final ModelPart top;

    public UmbrellaModel(ModelPart root){
        super(RenderType::entityCutout);
        this.main = root.getChild("main");
        this.pole = main.getChild("pole");
        this.top = pole.getChild("top");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition pole = main.addOrReplaceChild("pole", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -27.0F, -1.0F, 2.0F, 27.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition top = pole.addOrReplaceChild("top", CubeListBuilder.create().texOffs(0, 0).addBox(-16.0F, -6.4F, -16.06F, 32.0F, 2.0F, 32.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-1.0F, -6.9F, -1.06F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -26.1F, 0.06F));

        PartDefinition cube_r1 = top.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(40, 31).addBox(-3.9445F, -1.0F, -38.1755F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 31).addBox(-3.9445F, -1.0F, -0.6755F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.7445F, -5.5F, 13.1155F, 0.0F, 0.7854F, 0.0F));

        PartDefinition cube_r2 = top.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(40, 31).addBox(-2.8F, -1.1F, 36.44F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 31).addBox(-2.8F, -1.1F, -1.06F, 7.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.5F, -5.4F, -13.5F, 0.0F, -0.7854F, 0.0F));

        PartDefinition cube_r3 = top.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(8, 0).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -2.4F, -0.56F, 0.0F, 0.0F, -0.3927F));

        PartDefinition cube_r4 = top.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(8, 6).addBox(-1.0F, -3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -2.4F, 1.44F, -0.3927F, 0.0F, 0.0F));

        PartDefinition cube_r5 = top.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(12, 0).addBox(-0.5F, -3.0F, -0.7F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -2.4F, 0.44F, 0.0F, 0.0F, 0.3927F));

        PartDefinition cube_r6 = top.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(12, 6).addBox(0.0F, -3.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -2.4F, -1.56F, 0.3927F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 34);
    }

    @Override
    public void renderToBuffer(PoseStack ps, VertexConsumer vc, int i1, int i2, int color) {
        this.main.render(ps,vc,i1,i2);
    }

    public void setupAnim(UmbrellaBE be){
        this.pole.xScale = 1.35f;
        this.pole.yScale = 1.35f;
        this.pole.zScale = 1.35f;
        if(be.custom){
            this.pole.xRot = be.base_x_rot;
            this.main.xRot = Mth.PI;
            this.pole.zRot = be.base_z_rot;
            this.top.xRot = be.pole_x_rot;
            this.top.yRot = be.pole_y_rot;
            this.top.zRot = be.pole_z_rot;
        }
        else{
            this.pole.xRot = 0.17f;
            this.main.xRot = Mth.PI;
            this.pole.zRot = 0.02f;
            this.top.xRot = 0.21f;
            this.top.yRot = 0;
            this.top.zRot = 0;
        }
    }
}
