package net.rk.thingamajigs.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.rk.thingamajigs.block.custom.CurvedMonitor;
import net.rk.thingamajigs.blockentity.custom.CurvedMonitorBE;

public class CurvedMonitorModel extends Model{
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/curved_monitor.png"), "main");
    private final ModelPart screen;
    private final ModelPart keys;

    public CurvedMonitorModel(ModelPart root){
        super(RenderType::entityTranslucent);
        this.screen = root.getChild("screen");
        this.keys = screen.getChild("keyboard");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition screen = partdefinition.addOrReplaceChild("screen", CubeListBuilder.create().texOffs(23, 0).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(58, 19).addBox(-1.0F, -12.5F, 0.0F, 2.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(23, 19).addBox(-8.0F, -14.5F, -1.0F, 16.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.5F, 2.0F));

        PartDefinition cube_r1 = screen.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -6.0F, -0.5F, 10.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -8.5F, -0.5F, 0.0F, -0.3054F, 0.0F));

        PartDefinition cube_r2 = screen.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -6.0F, -0.5F, 10.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -8.5F, -0.5F, 0.0F, 0.3054F, 0.0F));

        PartDefinition keyboard = screen.addOrReplaceChild("keyboard", CubeListBuilder.create().texOffs(23, 11).addBox(-8.0F, -0.5F, -10.0F, 12.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(23, 4).addBox(5.0F, -0.5F, -10.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public void setupAnim(CurvedMonitorBE cmbe){
        if(cmbe.customAngle){
            screen.yRot = cmbe.yAngle;
        }
        else{
            if(cmbe.getBlockState().getValue(CurvedMonitor.FACING) == Direction.NORTH){
                screen.yRot = 3.15000000f;
            }
            else if (cmbe.getBlockState().getValue(CurvedMonitor.FACING) == Direction.SOUTH) {
                screen.yRot = 0.000000f;
            }
            else if (cmbe.getBlockState().getValue(CurvedMonitor.FACING) == Direction.EAST) {
                screen.yRot = 1.57000000f;
            }
            else if (cmbe.getBlockState().getValue(CurvedMonitor.FACING) == Direction.WEST){
                screen.yRot = -1.57000000f;
            }
        }
        screen.zRot = 0;
        screen.xRot = 3.14555111f;
        keys.visible = !cmbe.hideKeyboard;
    }

    @Override
    public void renderToBuffer(PoseStack ps, VertexConsumer vc, int i1, int i2, int i3) {
        this.screen.render(ps,vc,i1,i2);
    }
}
