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
import net.rk.thingamajigs.blockentity.custom.AnimatedIceRinkBE;

public class AnimatedIceRinkModel extends Model {
    public static final ModelLayerLocation ICE_RINK_ALL =
            new ModelLayerLocation(ResourceLocation.parse("thingamajigs:textures/entity/animated_snow_rink.png"), "main");
    public final ModelPart main;
    private final ModelPart rinkbase;
    public final ModelPart movingrink;
    public final ModelPart person1;
    public final ModelPart person2;
    public final ModelPart person3;
    public final ModelPart person4;
    public final ModelPart wheel;
    public final ModelPart movingwheel;

    public AnimatedIceRinkModel(ModelPart root) {
        super(RenderType::entityCutout);
        this.main = root.getChild("main");
        this.rinkbase = this.main.getChild("rinkbase");
        this.movingrink = this.main.getChild("movingrink");
        this.person1 = this.movingrink.getChild("person1");
        this.person2 = this.movingrink.getChild("person2");
        this.person3 = this.movingrink.getChild("person3");
        this.person4 = this.movingrink.getChild("person4");
        this.wheel = this.main.getChild("wheel");
        this.movingwheel = this.wheel.getChild("movingwheel");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 51).addBox(-6.0F, -0.01F, -6.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25).addBox(4.0F, -3.2F, 4.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(49, 0).addBox(-7.0F, -6.0F, 2.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(54, 9).addBox(-2.0F, -6.0F, 7.01F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(40, 29).addBox(6.01F, -5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 33).addBox(-7.01F, -5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(41, 37).addBox(0.0F, -5.0F, -7.01F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 46).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));

        PartDefinition cube_r1 = main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(49, 9).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.5F, 7.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r2 = main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(59, 9).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.5F, 7.0F, 0.0F, 0.5672F, 0.0F));

        PartDefinition cube_r3 = main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(49, 12).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.5F, 7.0F, 0.0F, 0.9599F, 0.0F));

        PartDefinition cube_r4 = main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(54, 12).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.5F, 7.0F, 0.0F, -0.9599F, 0.0F));

        PartDefinition cube_r5 = main.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(54, 3).addBox(-1.0F, -1.5F, 0.01F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -4.5F, 2.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r6 = main.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(49, 3).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -4.5F, 2.0F, 0.0F, -0.9599F, 0.0F));

        PartDefinition cube_r7 = main.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(59, 0).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -4.5F, 2.0F, 0.0F, 0.9599F, 0.0F));

        PartDefinition cube_r8 = main.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(54, 0).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -4.5F, 2.0F, 0.0F, 0.5672F, 0.0F));

        PartDefinition rinkbase = main.addOrReplaceChild("rinkbase", CubeListBuilder.create().texOffs(49, 21).addBox(-2.8995F, -3.1F, -7.0F, 5.799F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 59).addBox(-2.8995F, -3.1F, 6.0F, 5.799F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(13, 54).addBox(6.0F, -3.1F, -2.8995F, 1.0F, 4.0F, 5.799F, new CubeDeformation(0.0F))
                .texOffs(16, 54).addBox(-7.0F, -3.1F, -2.8995F, 1.0F, 4.0F, 5.799F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition northeast_r1 = rinkbase.addOrReplaceChild("northeast_r1", CubeListBuilder.create().texOffs(20, 54).addBox(-0.5F, -2.0F, -2.8995F, 1.0F, 4.0F, 5.799F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5962F, -1.0F, -4.5962F, 0.0F, -0.7854F, 0.0F));

        PartDefinition southwest_r1 = rinkbase.addOrReplaceChild("southwest_r1", CubeListBuilder.create().texOffs(22, 54).addBox(-0.5F, -2.0F, -2.8995F, 1.0F, 4.0F, 5.799F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5962F, -1.0F, 4.5962F, 0.0F, -0.7854F, 0.0F));

        PartDefinition southeast_r1 = rinkbase.addOrReplaceChild("southeast_r1", CubeListBuilder.create().texOffs(13, 59).addBox(-2.8995F, -2.0F, -0.5F, 5.799F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5962F, -1.0F, 4.5962F, 0.0F, -0.7854F, 0.0F));

        PartDefinition northwest_r1 = rinkbase.addOrReplaceChild("northwest_r1", CubeListBuilder.create().texOffs(49, 16).addBox(-2.8995F, 5.0F, -18.3137F, 5.799F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -8.0F, 8.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition movingrink = main.addOrReplaceChild("movingrink", CubeListBuilder.create().texOffs(0, 38).addBox(-2.4853F, -0.41F, -6.0F, 4.9706F, 1.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-6.0F, -0.41F, -2.4853F, 12.0F, 1.0F, 4.9706F, new CubeDeformation(0.0F))
                .texOffs(17, 33).addBox(-1.75F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition horsewest_r1 = movingrink.addOrReplaceChild("horsewest_r1", CubeListBuilder.create().texOffs(23, 33).addBox(0.15F, -1.0F, -0.5F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, 0.5F, 0.0F, 3.1416F, 0.0F));

        PartDefinition horsesouth_r1 = movingrink.addOrReplaceChild("horsesouth_r1", CubeListBuilder.create().texOffs(23, 30).addBox(0.0F, -1.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 1.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition horsenorth_r1 = movingrink.addOrReplaceChild("horsenorth_r1", CubeListBuilder.create().texOffs(17, 30).addBox(-0.25F, -1.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -1.5F, 0.0F, -1.5708F, 0.0F));

        PartDefinition caro_r1 = movingrink.addOrReplaceChild("caro_r1", CubeListBuilder.create().texOffs(34, 39).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-6.0F, 1.08F, -2.4853F, 12.0F, 1.0F, 4.9706F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-2.4853F, 1.07F, -6.0F, 4.9706F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition person1 = movingrink.addOrReplaceChild("person1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r9 = person1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 0).addBox(0.15F, -11.0F, -0.5F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -1.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

        PartDefinition person2 = movingrink.addOrReplaceChild("person2", CubeListBuilder.create(), PartPose.offset(-3.0F, 0.0F, -5.0F));

        PartDefinition cube_r10 = person2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(24, 0).addBox(0.15F, -11.0F, -0.5F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -1.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

        PartDefinition person3 = movingrink.addOrReplaceChild("person3", CubeListBuilder.create(), PartPose.offsetAndRotation(4.1235F, -1.0F, 0.1094F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r11 = person3.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(24, -12).addBox(0.15F, -11.0F, -0.5F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1061F, 0.0F, -0.1061F, 0.0F, -0.7854F, 0.0F));

        PartDefinition person4 = movingrink.addOrReplaceChild("person4", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.8765F, -1.0F, 0.1094F, 0.0F, 3.1416F, 0.0F));

        PartDefinition cube_r12 = person4.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, -12).addBox(0.15F, -11.0F, -0.5F, 0.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1061F, 0.0F, -0.1061F, 0.0F, -0.7854F, 0.0F));

        PartDefinition wheel = main.addOrReplaceChild("wheel", CubeListBuilder.create().texOffs(30, 25).addBox(-0.5F, -4.0F, -1.51F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 25).addBox(-0.5F, -4.0F, 0.51F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, -2.7F, 5.5F));

        PartDefinition movingwheel = wheel.addOrReplaceChild("movingwheel", CubeListBuilder.create().texOffs(17, 25).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(49, 26).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void setupAnim(AnimatedIceRinkBE iceRinkBE){
        this.person1.xScale = 0.002f;
        this.person2.xScale = 0.002f;
        this.person3.xScale = 0.002f;
        this.person4.xScale = 0.002f;
        // z and y are TOO big at first, so scale them to the intended size
        this.person1.zScale = 0.2f;
        this.person2.zScale = 0.2f;
        this.person3.zScale = 0.2f;
        this.person4.zScale = 0.2f;
        this.person1.yScale = 0.2f;
        this.person2.yScale = 0.2f;
        this.person3.yScale = 0.2f;
        this.person4.yScale = 0.2f;
        // PI is a good angle for the x axis, as it aligns with block edges
        this.main.xRot = Mth.PI;
        // angles are used here
        this.movingrink.yRot = iceRinkBE.rinkAngle * -1.0f;
        this.movingwheel.zRot = iceRinkBE.ferrisAngle * -1.0f;
    }

    public void noAnim(){
        this.person1.xScale = 0.002f;
        this.person2.xScale = 0.002f;
        this.person3.xScale = 0.002f;
        this.person4.xScale = 0.002f;
        this.person1.zScale = 0.2f;
        this.person2.zScale = 0.2f;
        this.person3.zScale = 0.2f;
        this.person4.zScale = 0.2f;
        this.person1.yScale = 0.2f;
        this.person2.yScale = 0.2f;
        this.person3.yScale = 0.2f;
        this.person4.yScale = 0.2f;
        this.main.xRot = Mth.PI;
        this.movingrink.yRot = 0;
        this.movingwheel.zRot = 0;
    }

    @Override
    public void renderToBuffer(PoseStack pose, VertexConsumer vc, int i1, int i2, int i3) {
        this.main.render(pose,vc,i1,i2);
    }
}
