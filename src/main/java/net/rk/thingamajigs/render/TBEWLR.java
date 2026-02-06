package net.rk.thingamajigs.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.blockentity.custom.*;
import net.rk.thingamajigs.item.TItems;
import net.rk.thingamajigs.render.model.*;

public class TBEWLR extends BlockEntityWithoutLevelRenderer {
    //
    private FootballGoalBE footballGoalBE = new FootballGoalBE(
            new BlockPos(0,0,0),
            TBlocks.FOOTBALL_GOAL.get().defaultBlockState());

    private CleverBlackboardBE cleverBlackboard = new CleverBlackboardBE(
            new BlockPos(0,0,0),
            TBlocks.CLEVER_BLACKBOARD.get().defaultBlockState()
    );
    private AnimatedIceRinkBE iceRinkBE = new AnimatedIceRinkBE(
            new BlockPos(0,0,0),
            TBlocks.ANIMATED_ICE_RINK.get().defaultBlockState()
    );
    private UmbrellaBE umbrellaBE = new UmbrellaBE(
            new BlockPos(0,0,0),
            TBlocks.UMBRELLA.get().defaultBlockState()
    );
    private CurvedMonitorBE curvedMonitorBE = new CurvedMonitorBE(
            new BlockPos(0,0,0),
            TBlocks.CURVED_MONITOR.get().defaultBlockState()
    );
    private TheaterProjectorBE theaterProjectorBE = new TheaterProjectorBE(
            new BlockPos(0,0,0),
            TBlocks.THEATER_PROJECTOR.get().defaultBlockState()
    );
    private AnimatedDeerBE animatedDeerBE = new AnimatedDeerBE(
            new BlockPos(0,0,0),
            TBlocks.ANIMATED_DEER.get().defaultBlockState()
    );

    // model instances
    private FootballGoalModel footballGoalModel;
    private CleverBlackboardModel cleverBlackboardModel;
    private AnimatedIceRinkModel iceRinkModel;
    private UmbrellaModel umbrellaModel;
    private CurvedMonitorModel curvedMonitorModel;
    private TheaterProjectorModel theaterProjectorModel;
    private AnimatedDeerModel animatedDeerModel;


    private EntityModelSet set = Minecraft.getInstance().getEntityModels();

    // standalone model layer locations
    public static final ModelLayerLocation FOOTBALL_GOAL_LOC = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/football_goal.png"), "main");
    public static final ModelLayerLocation CLEVERBLACKBOARD_LOC = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/clever_blackboard.png"), "main");
    public static final ModelLayerLocation ICE_RINK_LOC =
            new ModelLayerLocation(ResourceLocation.parse("thingamajigs:textures/entity/animated_snow_rink.png"), "main");
    public static final ModelLayerLocation UMBRELLA_LOC =
            new ModelLayerLocation(ResourceLocation.parse("thingamajigs:textures/entity/umbrella.png"), "main");
    public static final ModelLayerLocation CURVED_MONITOR_LOC = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/curved_monitor.png"), "main");
    public static final ModelLayerLocation THEATER_PROJECTOR_LOC = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/theater_projector.png"), "main");
    public static final ModelLayerLocation ANIMATED_DEER_LOC = new ModelLayerLocation(
            ResourceLocation.parse("thingamajigs:textures/entity/animated_deer.png"), "main");



    public TBEWLR(BlockEntityRenderDispatcher dispatcher, EntityModelSet set){
        super(dispatcher,set);
        this.footballGoalModel = new FootballGoalModel(this.set.bakeLayer(FOOTBALL_GOAL_LOC));
        this.cleverBlackboardModel = new CleverBlackboardModel(this.set.bakeLayer(CLEVERBLACKBOARD_LOC));
        this.iceRinkModel = new AnimatedIceRinkModel(this.set.bakeLayer(ICE_RINK_LOC));
        this.umbrellaModel = new UmbrellaModel(this.set.bakeLayer(UMBRELLA_LOC));
        this.curvedMonitorModel = new CurvedMonitorModel(this.set.bakeLayer(CURVED_MONITOR_LOC));
        this.theaterProjectorModel = new TheaterProjectorModel(this.set.bakeLayer(THEATER_PROJECTOR_LOC));
        this.animatedDeerModel = new AnimatedDeerModel(this.set.bakeLayer(ANIMATED_DEER_LOC));
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        this.footballGoalModel = new FootballGoalModel(this.set.bakeLayer(FOOTBALL_GOAL_LOC));
        this.cleverBlackboardModel = new CleverBlackboardModel(this.set.bakeLayer(CLEVERBLACKBOARD_LOC));
        this.iceRinkModel = new AnimatedIceRinkModel(this.set.bakeLayer(ICE_RINK_LOC));
        this.umbrellaModel = new UmbrellaModel(this.set.bakeLayer(UMBRELLA_LOC));
        this.curvedMonitorModel = new CurvedMonitorModel(this.set.bakeLayer(CURVED_MONITOR_LOC));
        this.theaterProjectorModel = new TheaterProjectorModel(this.set.bakeLayer(THEATER_PROJECTOR_LOC));
        this.animatedDeerModel = new AnimatedDeerModel(this.set.bakeLayer(ANIMATED_DEER_LOC));
    }

    // item form context rendering
    @Override
    public void renderByItem(ItemStack itemStack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource mbs, int i, int i1){
        if(itemStack.is(TItems.ANIMATED_ICE_RINK.get())){
            poseStack.pushPose();
            poseStack.scale(1f,1f,1f);
            poseStack.translate(0.5,-1.35,0.5);
            VertexConsumer vc = mbs.getBuffer(RenderType.entityCutout(ICE_RINK_LOC.getModel()));
            this.iceRinkModel.noAnim();
            this.iceRinkModel.main.render(poseStack,vc,i,i1);
            poseStack.popPose();
        }
        else if(itemStack.is(TItems.ANIMATED_DEER.get())){
            poseStack.pushPose();
            VertexConsumer vc = mbs.getBuffer(RenderType.entityCutout(ANIMATED_DEER_LOC.getModel()));
            this.animatedDeerModel.setupAnim(animatedDeerBE);
            this.animatedDeerModel.main.render(poseStack,vc,
                    i,i1);
            poseStack.popPose();
        }
        if(itemStack.is(TItems.FOOTBALL_GOAL.get())){
            poseStack.pushPose();
            poseStack.scale(0.25f,0.25f,0.25f);
            poseStack.translate(0.0,0.0,0.0);
            VertexConsumer vc = mbs.getBuffer(RenderType.entityCutout(FOOTBALL_GOAL_LOC.getModel()));
            this.footballGoalModel.setupAnim(footballGoalBE);
            this.footballGoalModel.renderToBuffer(poseStack, vc, i,i1);
            poseStack.popPose();
        }
        else if(itemStack.is(TItems.CLEVER_BLACKBOARD.get())){
            poseStack.pushPose();
            poseStack.scale(0.25f,0.25f,0.25f);
            poseStack.translate(0.0,0.0,0.0);
            VertexConsumer vc = mbs.getBuffer(RenderType.entityCutout(CLEVERBLACKBOARD_LOC.getModel()));
            this.cleverBlackboardModel.setupAnim(cleverBlackboard);
            this.cleverBlackboardModel.renderToBuffer(poseStack,
                    vc, i,i1);
            poseStack.popPose();
        }
        else if(itemStack.is(TItems.UMBRELLA.get())){
            poseStack.pushPose();
            poseStack.scale(1.0f,1.0f,1.0f);
            VertexConsumer vc = mbs.getBuffer(RenderType.entityCutout(UMBRELLA_LOC.getModel()));
            this.umbrellaModel.setupAnim(umbrellaBE);
            this.umbrellaModel.main.render(poseStack,vc,
                    i,i1);
            poseStack.popPose();
        }
        else if(itemStack.is(TItems.CURVED_MONITOR.get())){
            poseStack.pushPose();
            VertexConsumer vc = mbs.getBuffer(RenderType.entityCutout(CURVED_MONITOR_LOC.getModel()));
            this.curvedMonitorModel.setupAnim(curvedMonitorBE);
            this.curvedMonitorModel.screen.render(poseStack,vc,
                    i,i1);
            poseStack.popPose();
        }
        else if(itemStack.is(TItems.THEATER_PROJECTOR.get())){
            poseStack.pushPose();
            VertexConsumer vc = mbs.getBuffer(RenderType.entityCutout(THEATER_PROJECTOR_LOC.getModel()));
            this.theaterProjectorModel.setupAnim(theaterProjectorBE);
            this.theaterProjectorModel.main.render(poseStack,vc,
                    i,i1);
            poseStack.popPose();
        }
        else{
            super.renderByItem(itemStack,context,poseStack,mbs,i,i1);
        }
    }
}
