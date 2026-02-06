package net.rk.thingamajigs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.blockentity.TBlockEntity;
import net.rk.thingamajigs.entity.TEntity;
import net.rk.thingamajigs.item.TItems;
import net.rk.thingamajigs.menu.TMenu;
import net.rk.thingamajigs.render.*;
import net.rk.thingamajigs.render.model.*;
import net.rk.thingamajigs.screen.DJLaserLightScreen;
import net.rk.thingamajigs.screen.MailboxScreen;
import net.rk.thingamajigs.xtras.TColors;
import net.rk.thingamajigs.xtras.TParticles;
import net.rk.thingamajigs.xtras.particletypes.BallPitParticleType;
import net.rk.thingamajigs.xtras.particletypes.ChimneySmokeParticleType;
import net.rk.thingamajigs.xtras.particletypes.IcyAirParticleType;
import net.rk.thingamajigs.xtras.particletypes.SoapParticleType;

import java.util.logging.Logger;

@Mod(value=Thingamajigs.MODID,dist = Dist.CLIENT)
public class TClient{
    public TClient(IEventBus eventBus, ModContainer container){
        eventBus.addListener(this::setupClientExtensions);
        eventBus.addListener(this::registerModelLayers);
        eventBus.addListener(this::registerParticleFactories);
        eventBus.addListener(this::setupClient);
        eventBus.addListener(this::setupMenuTypes);
        eventBus.addListener(this::setupBlockColors);
        eventBus.addListener(this::setupItemColors);
        eventBus.addListener(this::addCreative);
    }

    public void setupItemColors(RegisterColorHandlersEvent.Item event){
        event.register((itemStack,i) -> {
                    BlockState bs = ((BlockItem)itemStack.getItem()).getBlock().defaultBlockState();
                    return Minecraft.getInstance().getBlockColors().getColor(bs,null,null,i);
                },
                TBlocks.FLOWERING_LILY_PAD.get(),
                TBlocks.TRIPLE_LILY_PAD.get(),
                TBlocks.COLORED_GLASS.get(),
                TItems.FLOWERING_LILY_PAD_ITEM.get(),
                TItems.TRIPLE_LILY_PAD_ITEM.get(),
                TBlocks.RUBBER_LEAVES.get(),
                TBlocks.ROUND_BUSH.get(),
                TBlocks.WISPY_WEED.get(),
                TBlocks.BULBLET.get()
        );
        // glow blocks
        event.register((itemStack,i) -> TColors.getLightGray(),
                TBlocks.LIGHT_GRAY_GLOW_BLOCK.asItem()
        );
        event.register((itemStack,i) -> TColors.getGray(),
                TBlocks.GRAY_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getBlack(),
                TBlocks.BLACK_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getBrown(),
                TBlocks.BROWN_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getColorFromList(1),
                TBlocks.RED_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getColorFromList(2),
                TBlocks.ORANGE_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getColorFromList(3),
                TBlocks.YELLOW_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getColorFromList(4),
                TBlocks.LIME_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getColorFromList(5),
                TBlocks.GREEN_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getColorFromList(6),
                TBlocks.CYAN_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getColorFromList(7),
                TBlocks.LIGHT_BLUE_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getColorFromList(8),
                TBlocks.BLUE_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getColorFromList(9),
                TBlocks.PURPLE_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getColorFromList(10),
                TBlocks.MAGENTA_GLOW_BLOCK.get().asItem()
        );
        event.register((itemStack,i) -> TColors.getColorFromList(11),
                TBlocks.PINK_GLOW_BLOCK.get().asItem()
        );
    }

    public void setupBlockColors(RegisterColorHandlersEvent.Block event){
        event.register((blockState,tintGetter,blockPos,i) ->
                        tintGetter != null && blockPos != null ?
                                BiomeColors.getAverageFoliageColor(tintGetter,blockPos) : FoliageColor.getDefaultColor(),
                TBlocks.FLOWERING_LILY_PAD.get(),
                TBlocks.TRIPLE_LILY_PAD.get(),
                TBlocks.RUBBER_LEAVES.get()
        );

        event.register((blockstate, tintget,bpos,i) ->
                tintget != null && bpos != null ?
                BiomeColors.getAverageGrassColor(tintget,bpos) : FoliageColor.getEvergreenColor(),
                TBlocks.ROUND_BUSH.get(),
                TBlocks.WISPY_WEED.get(),
                TBlocks.BULBLET.get()
        );

        event.register((blockState,tintGetter,blockPos,i) ->
                        tintGetter != null && blockPos != null ?
                                customGetColor(tintGetter,blockPos,BiomeColors.WATER_COLOR_RESOLVER) : FoliageColor.getDefaultColor(),
                TBlocks.COLORED_GLASS.get()
        );
    }

    public static int customGetColor(BlockAndTintGetter btgetter, BlockPos bp, ColorResolver cr) {
        return btgetter.getBlockTint(bp,cr);
    }

    public void registerParticleFactories(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(TParticles.CHIMNEY_SMOKE.get(),ChimneySmokeParticleType.ChimneySmokeParticleFactory::new);
        event.registerSpriteSet(TParticles.ICY_AIR.get(),IcyAirParticleType.IcyAirParticleFactory::new);
        event.registerSpriteSet(TParticles.PLASTIC_BALL.get(),BallPitParticleType.BallPitParticleFactory::new);
        event.registerSpriteSet(TParticles.SOAP.get(),SoapParticleType.SoapParticleFactory::new);
    }

    public void setupMenuTypes(RegisterMenuScreensEvent event){
        event.register(TMenu.MAILBOX_MENU.get(),MailboxScreen::new);
        event.register(TMenu.DJ_BE_MENU.get(),DJLaserLightScreen::new);
    }

    public void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(CurvedMonitorModel.LAYER_LOCATION,CurvedMonitorModel::createBodyLayer);
        event.registerLayerDefinition(CleverBlackboardModel.LAYER_LOCATION,CleverBlackboardModel::createBodyLayer);
        event.registerLayerDefinition(UmbrellaModel.LAYER_LOCATION,UmbrellaModel::createBodyLayer);
        event.registerLayerDefinition(TheaterProjectorModel.LAYER_LOCATION,TheaterProjectorModel::createBodyLayer);
        event.registerLayerDefinition(AnimatedIceRinkModel.ICE_RINK_ALL,AnimatedIceRinkModel::createBodyLayer);
        event.registerLayerDefinition(AnimatedDeerModel.LAYER_LOCATION,AnimatedDeerModel::createBodyLayer);
        event.registerLayerDefinition(FootballGoalModel.LAYER_LOCATION,FootballGoalModel::createBodyLayer);
    }

    public void setupClient(final FMLClientSetupEvent event){
        event.enqueueWork(this::setupRenderTypes);
        try{
            EntityRenderers.register(TEntity.CHAIR.get(),ChairRender::new);
            EntityRenderers.register(TEntity.TOILET.get(),ToiletRenderer::new);
            EntityRenderers.register(TEntity.STOOL.get(),StoolRenderer::new);
        }
        catch (Exception e){
            Logger.getAnonymousLogger().warning(e.getMessage());
        }

        try{
            BlockEntityRenderers.register(TBlockEntity.DJ_LASER_LIGHT_BE.get(),DJLaserLightBERenderer::new);
            BlockEntityRenderers.register(TBlockEntity.PLATE_BE.get(),PlateBERenderer::new);
            BlockEntityRenderers.register(TBlockEntity.ITEM_DISPLAY_BE.get(),ItemDisplayBERenderer::new);
            BlockEntityRenderers.register(TBlockEntity.CURVED_MONITOR_BE.get(),CurvedMonitorBERenderer::new);
            // 1.7.7
            BlockEntityRenderers.register(TBlockEntity.CLEVER_BLACKBOARD_BE.get(),CleverBlackboardBERenderer::new);
            BlockEntityRenderers.register(TBlockEntity.UMBRELLA_BE.get(),UmbrellaBERenderer::new);
            BlockEntityRenderers.register(TBlockEntity.THEATER_PROJECTOR_BE.get(),TheaterProjectorBERenderer::new);
            // 1.8.0-1.8.4
            BlockEntityRenderers.register(TBlockEntity.ANIMATED_ICE_RINK.get(),AnimatedIceRinkRenderer::new);
            BlockEntityRenderers.register(TBlockEntity.ANIMATED_DEER_BE.get(),AnimatedDeerBERenderer::new);
            BlockEntityRenderers.register(TBlockEntity.FOOTBALL_GOAL.get(),FootballGoalRenderer::new);
        }
        catch (Exception e){
            Logger.getAnonymousLogger().warning(e.getMessage());
        }
    }

    public void setupRenderTypes(){
        ItemBlockRenderTypes.setRenderLayer(TBlocks.ROUND_BUSH.get(),RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TBlocks.BULBLET.get(),RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(TBlocks.WISPY_WEED.get(),RenderType.cutout());
    }

    public void setupClientExtensions(RegisterClientExtensionsEvent event){
        event.registerItem(new IClientItemExtensions(){
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new TBEWLR(Minecraft.getInstance().getBlockEntityRenderDispatcher(),Minecraft.getInstance().getEntityModels());
            }
        },TItems.ANIMATED_ICE_RINK.asItem(),TItems.ANIMATED_DEER.asItem(),TItems.FOOTBALL_GOAL.asItem(),
                TItems.CLEVER_BLACKBOARD.asItem(),TItems.CURVED_MONITOR.asItem(),TItems.THEATER_PROJECTOR.asItem(),
                TItems.UMBRELLA.asItem());
    }

    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.OP_BLOCKS){
            event.accept(TItems.WATER_SOURCE);
            event.accept(TItems.NP_PLACEABLE);
            event.accept(TItems.EP_PLACEABLE);
            event.accept(TItems.EG_PLACEABLE);
            event.accept(TItems.VOID_AIR_PLACEABLE);
            event.accept(TItems.ILLUSIONER_SPAWN_EGG);
            event.accept(TItems.GIANT_SPAWN_EGG);
            // experimental things
            event.accept(TBlocks.FAKE_FLUID_PUMP.get().asItem());
        }
        if(event.getTabKey() == Thingamajigs.MAIN_CTAB.getKey()){
            event.accept(TItems.THINGAMAJIG);
            // globs (used in secondary recipes)
            event.accept(TItems.THINGAMAJIG_GLOB);
            event.accept(TItems.SIGN_GLOB);
            event.accept(TItems.CIRCLE_SIGN_GLOB);
            event.accept(TItems.SQUARE_SIGN_GLOB);
            event.accept(TItems.TRIANGLE_SIGN_GLOB);
            event.accept(TItems.MISC_SIGN_GLOB);
            event.accept(TItems.DOOR_GLOB);
            // components (used in tertiary recipes and beyond)
            event.accept(TItems.BASE_COMPONENT);
            event.accept(TItems.INFRASTRUCTURE_COMPONENT);
            event.accept(TItems.FACTORY_COMPONENT);
            event.accept(TItems.TECHNOLOGY_COMPONENT);
            event.accept(TItems.SPORTS_COMPONENT);
            event.accept(TItems.FURNITURE_COMPONENT);
            event.accept(TItems.MISC_COMPONENT);
            // subcategory components (used in recipes and beyond)
            event.accept(TItems.MINI_COMPONENT);
            event.accept(TItems.CAR_WASH_COMPONENT);
            event.accept(TItems.TRAFFIC_SIGNAL_COMPONENT);
            event.accept(TItems.RAILROAD_COMPONENT);
            event.accept(TItems.COMPUTER_COMPONENT);
            event.accept(TItems.GAME_CONSOLE_COMPONENT);
            event.accept(TItems.CHRISTMAS_COMPONENT);
            event.accept(TItems.SAFETY_COMPONENT);
            event.accept(TItems.ARCADE_COMPONENT);
            event.accept(TItems.PHONE_COMPONENT);
            event.accept(TItems.HOME_COMPONENT);
            event.accept(TItems.APPLIANCE_COMPONENT);
            event.accept(TItems.SCIENCE_COMPONENT);
            event.accept(TItems.HEALTH_COMPONENT);
            event.accept(TItems.TOY_COMPONENT);
            event.accept(TItems.MUSIC_COMPONENT);
            // items continued
            event.accept(TItems.KEY);
            // money system items
            event.accept(TItems.COIN);
            event.accept(TItems.MONEY);
            //event.accept(TItems.DEBIT_CARD);
            // refinement and other recipe items
            // rubber tree stuff
            event.accept(TBlocks.RUBBER_SAPLING.get().asItem());
            event.accept(TBlocks.RUBBER_LEAVES.get().asItem());
            event.accept(TBlocks.RUBBER_LOG.get().asItem());
            event.accept(TBlocks.RUBBER_WOOD.get().asItem());
            event.accept(TBlocks.STRIPPED_RUBBER_LOG.get().asItem());
            event.accept(TBlocks.STRIPPED_RUBBER_WOOD.get().asItem());
            event.accept(TBlocks.RUBBER_PLANKS.get().asItem());
            event.accept(TBlocks.RUBBER_WOOD_DOOR.get().asItem());
            event.accept(TBlocks.RUBBER_WOOD_STAIRS.get().asItem());
            event.accept(TBlocks.RUBBER_WOOD_SLAB.get().asItem());
            event.accept(TBlocks.RUBBER_WOOD_FENCE.get().asItem());
            event.accept(TBlocks.RUBBER_WOOD_FENCE_GATE.get().asItem());
            event.accept(TBlocks.RUBBER_WOOD_TRAPDOOR.get().asItem());
            event.accept(TBlocks.RUBBER_WOOD_PRESSURE_PLATE.get().asItem());
            event.accept(TBlocks.RUBBER_WOOD_BUTTON.get().asItem());
            // continued other items
            event.accept(TItems.TREE_RESIN);
            event.accept(TItems.RUBBER);
            // food
            event.accept(TItems.GLOB_SANDWICH);
            // plants and flowers
            event.accept(TBlocks.BRAMBLE.get().asItem());
            event.accept(TBlocks.ROUND_BUSH.asItem());
            event.accept(TBlocks.BULBLET.asItem());
            event.accept(TBlocks.WISPY_WEED.asItem());
            event.accept(TBlocks.BULBY_FLOWER.get().asItem());
            event.accept(TBlocks.DROOPY_FLOWER.get().asItem());

            event.accept(TItems.FLOWERING_LILY_PAD_ITEM);
            event.accept(TItems.TRIPLE_LILY_PAD_ITEM);
            // interactive items (torch placers and other block items)
            event.accept(TItems.CLEAR_BULB_ITEM);
            event.accept(TItems.FULL_BULB_ITEM);
            event.accept(TItems.CLEAR_LANTERN_ITEM);
            event.accept(TItems.FULL_LANTERN_ITEM);
            event.accept(TItems.PAPER_LANTERN_ITEM);
            event.accept(TItems.RED_LANTERN_ITEM);
            // lanterns
            event.accept(TBlocks.SCULK_LANTERN.get().asItem());
            // start full blocks
            // Mini-City Blocks
            event.accept(TBlocks.MINI_ROAD.get().asItem());
            event.accept(TBlocks.MINI_RAIL.get().asItem());
            event.accept(TBlocks.TINY_CROSSING.get().asItem());
            event.accept(TBlocks.MINI_RED_BUILDING.get().asItem());
            event.accept(TBlocks.MINI_YELLOW_BUILDING.get().asItem());
            event.accept(TBlocks.MINI_TALL_YELLOW_BUILDING.get().asItem());
            event.accept(TBlocks.MINI_GREEN_BUILDING.get().asItem());
            event.accept(TBlocks.MINI_BLUE_BUILDING.get().asItem());
            // Doors
            event.accept(TBlocks.WHITE_WOOD_DOOR.get().asItem());
            event.accept(TBlocks.SCREEN_DOOR.get().asItem());
            event.accept(TBlocks.FESTIVE_DOOR.get().asItem());
            event.accept(TBlocks.SNOWMAN_DOOR.get().asItem());
            event.accept(TBlocks.STONE_DOOR.get().asItem());
            event.accept(TBlocks.BUBBLE_DOOR.get().asItem());
            event.accept(TBlocks.METALLIC_DOOR.get().asItem());
            event.accept(TBlocks.ALARMED_DOOR.get().asItem());
            event.accept(TBlocks.LOCKABLE_DOOR.get().asItem());
            // chains
            event.accept(TBlocks.SCULK_CHAIN.get().asItem());
            // Misc. Begin Blocks
            event.accept(TBlocks.DECORATIVE_PORTAL.get().asItem());
            event.accept(TBlocks.NOT_QUITE.get().asItem());
            // Techno Blocks
            event.accept(TBlocks.TECHNO_CORE.get().asItem());
            event.accept(TBlocks.TECHNO_PILLAR.get().asItem());
            event.accept(TBlocks.CHISELED_TECHNO_BLOCK.get().asItem());
            event.accept(TBlocks.NEON_BLOCK.get().asItem());
            event.accept(TBlocks.ALT_NEON_BLOCK.get().asItem());
            event.accept(TBlocks.SWIRLY_TECHNO_BLOCK.get().asItem());
            // other tech blocks
            event.accept(TBlocks.CIRCUITS.get().asItem());
            event.accept(TBlocks.SCRAP_PANELS.get().asItem());
            // stripes and such
            event.accept(TBlocks.RED_CAUTION.get().asItem());
            event.accept(TBlocks.ORANGE_CAUTION.get().asItem());
            event.accept(TBlocks.YELLOW_CAUTION.get().asItem());
            event.accept(TBlocks.GREEN_CAUTION.get().asItem());
            event.accept(TBlocks.LIGHT_BLUE_CAUTION.get().asItem());
            event.accept(TBlocks.ALT_ORANGE_CAUTION.get().asItem());
            event.accept(TBlocks.SCROLLING_YELLOW_CAUTION.get().asItem());
            // Factory Blocks
            event.accept(TBlocks.METAL_SCAFFOLDING.get().asItem());
            event.accept(TBlocks.METAL_VENTS.get().asItem());
            event.accept(TBlocks.OUTLET_BLOCK.get().asItem());
            event.accept(TBlocks.GEARS_BLOCK.get().asItem());
            event.accept(TBlocks.MOVING_GEARS_BLOCK.get().asItem());
            event.accept(TBlocks.FAN_BLOCK_ULTRASONIC.get().asItem());
            event.accept(TBlocks.FAN_BLOCK_FAST.get().asItem());
            event.accept(TBlocks.FAN_BLOCK.get().asItem());
            event.accept(TBlocks.FAN_BLOCK_SPARK.get().asItem());
            event.accept(TBlocks.FAN_BLOCK_OFF.get().asItem());
            event.accept(TBlocks.TRANSPARENT_FAST_FAN_BLOCK.get().asItem());
            event.accept(TBlocks.TRANSPARENT_FAN_BLOCK.get().asItem());
            event.accept(TBlocks.TRANSPARENT_OFF_FAN_BLOCK.get().asItem());
            event.accept(TBlocks.GRATE.get().asItem());
            // paper walls
            event.accept(TBlocks.PAPER_WALL_BLOCK.get().asItem());
            event.accept(TBlocks.PAPER_FLOWER_WALL_BLOCK.get().asItem());
            // misc other glass blocks
            event.accept(TBlocks.SCREEN.get().asItem());
            event.accept(TBlocks.COLORED_GLASS.get().asItem());
            event.accept(TBlocks.REINFORCED_GLASS.get().asItem());
            // Fancy Decoration Blocks
            event.accept(TBlocks.MYSTERIOUS_ONE_WOOL.get().asItem());
            event.accept(TBlocks.FIREOUS_GLAZED_TERRACOTTA.get().asItem());
            event.accept(TBlocks.DARK_FIREOUS_GLAZED_TERRACOTTA.get().asItem());
            event.accept(TBlocks.CRYSTAL_BLOCK.get().asItem());
            event.accept(TBlocks.DARK_CRYSTAL_BLOCK.get().asItem());
            // Laboratory Blocks
            event.accept(TBlocks.GRAY_SCREEN.get().asItem());
            event.accept(TBlocks.BLUE_SCREEN.get().asItem());
            // Useful Blocks
            event.accept(TBlocks.NETHER_CHISELED_BOOKSHELF.get().asItem());
            // Road Blocks & Items
            event.accept(TItems.PAINT_BRUSH);
            // Poles and Generic Road Side Things
            event.accept(TBlocks.LIGHT_POLE.get().asItem());
            event.accept(TBlocks.HOLDER_POLE.get().asItem());
            event.accept(TBlocks.STRAIGHT_POLE.get().asItem());
            event.accept(TBlocks.STRAIGHT_HORIZONTAL_POLE.get().asItem());
            event.accept(TBlocks.L_POLE.get().asItem());
            event.accept(TBlocks.L_ONLY_POLE.get().asItem());
            event.accept(TBlocks.VERTICAL_AXIS_POLE.get().asItem());
            event.accept(TBlocks.AXIS_POLE.get().asItem());
            event.accept(TBlocks.PLUS_POLE.get().asItem());
            event.accept(TBlocks.THREE_WAY_POLE.get().asItem());
            event.accept(TBlocks.TL_CONNECTOR.get().asItem());
            event.accept(TBlocks.T_POLE.get().asItem());
            event.accept(TBlocks.T_POLE_B.get().asItem());
            event.accept(TBlocks.T_POLE_C.get().asItem());
            event.accept(TBlocks.VERTICAL_T_POLE.get().asItem());
            event.accept(TBlocks.TRI_POLE.get().asItem());
            event.accept(TBlocks.TRI_POLE_B.get().asItem());
            event.accept(TBlocks.T_HORZ_ONLY_POLE.get().asItem());
            event.accept(TBlocks.ALL_WAY_POLE.get().asItem());
            event.accept(TBlocks.VERTICAL_POLE_REDSTONE.get().asItem());
            // railroad crossing stuff
            event.accept(TBlocks.RAILROAD_CROSSING_BLOCKER.get().asItem());
            // other traffic control things
            event.accept(TBlocks.STOP_GATE.get().asItem());
            event.accept(TBlocks.ARROW_BOARD.get().asItem());
            // traffic control
            event.accept(TBlocks.TRAFFIC_CONTROL_BOX.get().asItem());
            // Traffic Signals
            event.accept(TBlocks.CROSSWALK_BUTTON.get().asItem());
            event.accept(TBlocks.PED_FLASHERS.get().asItem());
            event.accept(TBlocks.HAWK_SIGNAL.get().asItem());
            event.accept(TBlocks.PED_SIGNAL_WORDED.get().asItem());
            event.accept(TBlocks.PED_SIGNAL_SYMBOLS.get().asItem());
            event.accept(TBlocks.PED_SIGNAL_MAN_1.get().asItem());
            event.accept(TBlocks.TRAFFIC_SIGNAL_NORMAL_1.get().asItem());
            event.accept(TBlocks.TRAFFIC_SIGNAL_NORMAL_2.get().asItem());
            event.accept(TBlocks.TRAFFIC_SIGNAL_NORMAL_3.get().asItem());
            event.accept(TBlocks.TRAFFIC_SIGNAL_NORMAL_4.get().asItem());
            event.accept(TBlocks.TRAFFIC_SIGNAL_DOGHOUSE_1.get().asItem());
            event.accept(TBlocks.TRAFFIC_SIGNAL_DOGHOUSE_2.get().asItem());
            event.accept(TBlocks.TRAFFIC_SIGNAL_SYMBOL_1.get().asItem());
            event.accept(TBlocks.TRAFFIC_SIGNAL_YELLOW_FLASH.get().asItem());
            event.accept(TBlocks.TRAFFIC_SIGNAL_RED_FLASH.get().asItem());
            event.accept(TBlocks.HORIZONTAL_TRAFFIC_SIGNAL_1.get().asItem());
            event.accept(TBlocks.HORIZONTAL_TRAFFIC_SIGNAL_3.get().asItem());
            event.accept(TBlocks.HORIZONTAL_TRAFFIC_SIGNAL_2.get().asItem());
            event.accept(TBlocks.YELLOW_BEACON.get().asItem());
            event.accept(TBlocks.RED_BEACON.get().asItem());
            event.accept(TBlocks.ARROW_BEACON.get().asItem());
            event.accept(TBlocks.TRAFFIC_SIGNAL_ALLWAY_STOP_BEACON.get().asItem());
            // Road Construction Blocks
            event.accept(TBlocks.ROAD_PANEL.get().asItem());
            event.accept(TBlocks.ROAD_BARRIER_SMALL.get().asItem());
            event.accept(TBlocks.ROAD_BARRIER_SMALL_LIGHTED.get().asItem());
            event.accept(TBlocks.ROAD_BARRIER_LIGHTED.get().asItem());
            event.accept(TBlocks.ROAD_BARRIER_CLOSED.get().asItem());
            event.accept(TBlocks.ROAD_BARRIER_THRU_CLOSED.get().asItem());
            event.accept(TBlocks.ROAD_BARRIER_BRIDGE_CLOSED.get().asItem());
            event.accept(TBlocks.ROAD_BARRIER_BRIDGE_THRU_CLOSED.get().asItem());
            event.accept(TBlocks.BIG_ROAD_CONE.get().asItem());
            event.accept(TBlocks.ROAD_BARREL.get().asItem());
            event.accept(TBlocks.ROAD_CHANNELIZER.get().asItem());
            // General Purpose Construction
            event.accept(TBlocks.CONCRETE.get().asItem());
            event.accept(TBlocks.CONCRETE_STAIRS.get().asItem());
            event.accept(TBlocks.CONCRETE_SLAB.get().asItem());
            event.accept(TBlocks.CONCRETE_BRICKS.get().asItem());
            event.accept(TBlocks.CONCRETE_BRICKS_STAIRS.get().asItem());
            event.accept(TBlocks.CONCRETE_BRICKS_SLAB.get().asItem());
            event.accept(TBlocks.COBBLED_CONCRETE.get().asItem());
            event.accept(TBlocks.COBBLED_CONCRETE_STAIRS.get().asItem());
            event.accept(TBlocks.COBBLED_CONCRETE_SLAB.get().asItem());
            event.accept(TBlocks.STEEL.get().asItem());
            // General Purpose Barriers
            event.accept(TBlocks.STEEL_HOARDING.get().asItem());
            event.accept(TBlocks.CONCRETE_BARRIER.get().asItem());
            event.accept(TBlocks.REBAR_CONCRETE_BARRIER.get().asItem());
            event.accept(TBlocks.REINFORCED_CONCRETE_BARRIER.get().asItem());
            event.accept(TBlocks.BRIDGE_BARRIER.get().asItem());
            event.accept(TBlocks.LADDER_RAILING.get().asItem());
            event.accept(TBlocks.CURVED_RAILING.get().asItem());
            // abandonment and other construction things
            event.accept(TBlocks.I_BEAM.get().asItem());
            event.accept(TBlocks.CINDER_BLOCK.get().asItem());
            event.accept(TBlocks.CINDER_BLOCK_SLAB.get().asItem());
            event.accept(TBlocks.CINDER_BLOCK_SMALL.get().asItem());
            event.accept(TBlocks.DOOR_BLOCKADE.get().asItem());
            event.accept(TBlocks.WINDOW_BLOCKADE.get().asItem());
            // Road Covers (sewer or otherwise, service panels, etc.)
            event.accept(TBlocks.ROAD_COVER.get().asItem());
            event.accept(TBlocks.ALT_ROAD_COVER.get().asItem());
            event.accept(TBlocks.ROAD_PANEL_COVER.get().asItem());
            event.accept(TBlocks.ALT_ROAD_PANEL_COVER.get().asItem());
            // car wash
            event.accept(TBlocks.CAR_WASH_SIGNAGE.get().asItem());
            event.accept(TBlocks.CAR_WASH_SIGNAL.get().asItem());
            event.accept(TBlocks.CAR_WASH_TIRE_SCRUBBER.get().asItem());
            event.accept(TBlocks.CAR_WASH_MIXED_BRUSH.get().asItem());
            event.accept(TBlocks.CAR_WASH_DRIPPER.get().asItem());
            event.accept(TBlocks.CAR_WASH_SPRAYER.get().asItem());
            event.accept(TBlocks.CAR_WASH_TRIFOAMER.get().asItem());
            event.accept(TBlocks.CAR_WASH_SOAPER.get().asItem());
            event.accept(TBlocks.CAR_WASH_WAXER.get().asItem());
            event.accept(TBlocks.CAR_WASH_BLUE_BRUSH.get().asItem());
            event.accept(TBlocks.CAR_WASH_YELLOW_BRUSH.get().asItem());
            event.accept(TBlocks.CAR_WASH_RED_BRUSH.get().asItem());
            event.accept(TBlocks.CAR_WASH_MITTER_CURTAIN.get().asItem());
            event.accept(TBlocks.CAR_WASH_DRYER.get().asItem());
            // telephone & cell service towers
            event.accept(TBlocks.PHONE_SWITCHER.get().asItem());
            event.accept(TBlocks.CELL_MULTI_ANGLED_TRANSMITTER.get().asItem());
            event.accept(TBlocks.CELL_MULTI_TRANSMITTER.get().asItem());
            event.accept(TBlocks.CELL_TRANSMITTER.get().asItem());
            event.accept(TBlocks.CELL_MICROWAVE_TRANSMITTER.get().asItem());
            event.accept(TBlocks.OLD_MICROWAVE_TRANSMITTER.get().asItem());
            // dark stone
            event.accept(TBlocks.DARKENED_STONE.get().asItem());
            event.accept(TBlocks.GRADIENT_DARKENED_STONE.get().asItem());
            event.accept(TBlocks.DARK_DARKENED_STONE.get().asItem());
            event.accept(TBlocks.DARKENED_STONE_BRICKS.get().asItem());
            event.accept(TBlocks.PANEL_DARKENED_STONE_BRICKS.get().asItem());
            event.accept(TBlocks.CHISELED_PANEL_DARKENED_STONE_BRICKS.get().asItem());
            // other
            event.accept(TBlocks.REFINED_THINGAMAJIG_BLOCK.get().asItem());
            event.accept(TBlocks.MYSTERIOUS_PILLAR.get().asItem());
            // runic stone
            event.accept(TBlocks.RUNICSTONE_BLOCK.get().asItem());
            event.accept(TBlocks.RUNICSTONE_BRICKS.get().asItem());
            event.accept(TBlocks.SUNSTONE_BLOCK.get().asItem());
            event.accept(TBlocks.MOONSTONE_BLOCK.get().asItem());
            event.accept(TBlocks.EXPOSED_RUNICSTONE_BLOCK.get().asItem());
            // Tiling and Flooring Blocks
            event.accept(TBlocks.CRYSTALINE_STONE.get().asItem());
            event.accept(TBlocks.INDENTED_STONE.get().asItem());
            event.accept(TBlocks.PANEL_STONE.get().asItem());
            event.accept(TBlocks.PANEL_STONE_BRICKS.get().asItem());
            event.accept(TBlocks.MOSSY_PANEL_STONE_BRICKS.get().asItem());
            event.accept(TBlocks.CRACKED_PANEL_STONE_BRICKS.get().asItem());
            event.accept(TBlocks.CHISELED_PANEL_STONE_BRICKS.get().asItem());
            // connected texture blocks
            event.accept(TBlocks.STONE_PILLAR.get().asItem());
            event.accept(TBlocks.STONE_BRICK_PILLAR.get().asItem());
            event.accept(TBlocks.CHISELED_STONE_BRICK_PILLAR.get().asItem());
            //
            event.accept(TBlocks.BRICK_SIDEWALK.get().asItem());
            event.accept(TBlocks.BRICK_SIDEWALK_HB.get().asItem());
            event.accept(TBlocks.FRENCH_BRICK.get().asItem());
            event.accept(TBlocks.ALT_FRENCH_BRICK.get().asItem());
            event.accept(TBlocks.BASIC_BATHROOM_TILE.get().asItem());
            event.accept(TBlocks.STORE_FLOORING.get().asItem());
            event.accept(TBlocks.BOWLING_FLOORING.get().asItem());
            // lanes
            event.accept(TBlocks.OAK_LANE.get().asItem());
            event.accept(TBlocks.SPRUCE_LANE.get().asItem());
            event.accept(TBlocks.BIRCH_LANE.get().asItem());
            event.accept(TBlocks.JUNGLE_LANE.get().asItem());
            event.accept(TBlocks.ACACIA_LANE.get().asItem());
            event.accept(TBlocks.DARK_OAK_LANE.get().asItem());
            event.accept(TBlocks.MANGROVE_LANE.get().asItem());
            event.accept(TBlocks.CHERRY_LANE.get().asItem());
            event.accept(TBlocks.CRIMSON_LANE.get().asItem());
            event.accept(TBlocks.WARPED_LANE.get().asItem());
            event.accept(TBlocks.RUBBER_LANE.get().asItem());
            // wools
            event.accept(TBlocks.OLD_TEAL_WOOL.get().asItem());
            event.accept(TBlocks.LOVE_SEAT_WOOL.get().asItem());
            event.accept(TBlocks.CHECKBOARD_WOOL.get().asItem());
            // Pathways and Carpets
            event.accept(TBlocks.GRAY_PATHWAY.get().asItem());
            event.accept(TBlocks.BROWN_PATHWAY.get().asItem());
            event.accept(TBlocks.RED_PATHWAY.get().asItem());
            // Bookshelves
            event.accept(TBlocks.BLANK_BOOKSHELF.get().asItem());
            event.accept(TBlocks.ABANDONED_BOOKSHELF.get().asItem());
            event.accept(TBlocks.BONE_BOOKSHELF.get().asItem());
            event.accept(TBlocks.BRICK_BOOKSHELF.get().asItem());
            event.accept(TBlocks.GLOWSTONE_BOOKSHELF.get().asItem());
            event.accept(TBlocks.EXPERIENCE_BOOKSHELF.get().asItem());
            event.accept(TBlocks.HISTORIAN_BOOKSHELF.get().asItem());
            event.accept(TBlocks.EXPLORER_BOOKSHELF.get().asItem());
            event.accept(TBlocks.POTION_BOOKSHELF.get().asItem());
            event.accept(TBlocks.EXPENSIVE_BOOKSHELF.get().asItem());
            event.accept(TBlocks.SCARY_BOOKSHELF.get().asItem());
            event.accept(TBlocks.RED_TOME_BOOKSHELF.get().asItem());
            event.accept(TBlocks.YELLOW_TOME_BOOKSHELF.get().asItem());
            event.accept(TBlocks.GREEN_TOME_BOOKSHELF.get().asItem());
            event.accept(TBlocks.BLUE_TOME_BOOKSHELF.get().asItem());
            event.accept(TBlocks.POOPSHELF.get().asItem());
            event.accept(TBlocks.ANCIENT_BOOKSHELF.get().asItem());
            // Sports & Games
            // outdoor and workout
            event.accept(TBlocks.GOAL.get().asItem());
            event.accept(TItems.FOOTBALL_GOAL.get().asItem());
            event.accept(TBlocks.PUNCHING_BAG.get().asItem());
            event.accept(TBlocks.BASKETBALL_HOOP.get().asItem());
            // mini golf
            event.accept(TBlocks.MINIGOLF_GRASS_BLOCK.get().asItem());
            event.accept(TBlocks.MINIGOLF_HOLE.get().asItem());
            event.accept(TBlocks.MINIGOLF_FLAG.get().asItem());
            // bowling pins
            event.accept(TBlocks.BOWLING_PIN.get().asItem());
            event.accept(TBlocks.RED_BOWLING_PIN.get().asItem());
            event.accept(TBlocks.GOLD_BOWLING_PIN.get().asItem());
            event.accept(TBlocks.DIAMOND_BOWLING_PIN.get().asItem());
            // bowling balls
            event.accept(TBlocks.BROWN_BOWLING_BALL.get().asItem());
            event.accept(TBlocks.YELLOW_BOWLING_BALL.get().asItem());
            event.accept(TBlocks.LIME_BOWLING_BALL.get().asItem());
            event.accept(TBlocks.GREEN_BOWLING_BALL.get().asItem());
            event.accept(TBlocks.LIGHT_BLUE_BOWLING_BALL.get().asItem());
            event.accept(TBlocks.BLUE_BOWLING_BALL.get().asItem());
            event.accept(TBlocks.PURPLE_BOWLING_BALL.get().asItem());
            event.accept(TBlocks.PINK_BOWLING_BALL.get().asItem());
            // bowling technical
            event.accept(TBlocks.BOWLING_ALLEY_OILER.get().asItem());
            event.accept(TBlocks.BOWLING_BALL_RETRIEVER.get().asItem());
            event.accept(TBlocks.BOWLING_GAME_CONTROLLER.get().asItem());
            event.accept(TBlocks.PIN_SETTER.get().asItem());
            // arcade machines
            event.accept(TBlocks.ARCADE_MACHINE.get().asItem());
            event.accept(TBlocks.ARCADE_MACHINE_OPENABLE.get().asItem());
            event.accept(TBlocks.BASKETBALL_MACHINE.get().asItem());
            event.accept(TBlocks.PINBALL_MACHINE.get().asItem());
            event.accept(TBlocks.LIGHTUP_MACHINE.get().asItem());
            event.accept(TBlocks.FOOSBALL_TABLE.get().asItem());
            event.accept(TBlocks.CLAW_MACHINE.get().asItem());
            event.accept(TBlocks.GUMBALL_MACHINE.get().asItem());
            event.accept(TBlocks.HAMMER_MACHINE.get().asItem());
            event.accept(TBlocks.WACK_MACHINE.get().asItem());
            event.accept(TBlocks.AIR_HOCKEY_TABLE.get().asItem());
            // Water Park
            event.accept(TBlocks.WATER_SLIDE.get().asItem());
            event.accept(TBlocks.DIVING_BOARD.get().asItem());
            // Commercial Use
            event.accept(TBlocks.MALE_BATHROOM_SIGN.get().asItem());
            event.accept(TBlocks.FEMALE_BATHROOM_SIGN.get().asItem());
            event.accept(TBlocks.BOTH_BATHROOM_SIGN.get().asItem());
            event.accept(TBlocks.BARBER_POLE.get().asItem());
            event.accept(TBlocks.BARBER_HAIR_DRYER.get().asItem());
            event.accept(TBlocks.CHANGE_MACHINE.get().asItem());
            event.accept(TBlocks.INSET_ATM.get().asItem());
            event.accept(TBlocks.ATM.get().asItem());
            event.accept(TBlocks.BLUEYBOX.get().asItem());
            event.accept(TBlocks.BAR_STOOL.get().asItem());
            event.accept(TBlocks.BARREL_KEG.get().asItem());
            event.accept(TBlocks.REFRESHMENT_MACHINE.get().asItem());
            event.accept(TBlocks.RED_SODA_MACHINE.get().asItem());
            event.accept(TBlocks.BLUE_SODA_MACHINE.get().asItem());
            event.accept(TBlocks.RED_VENDING_MACHINE.get().asItem());
            event.accept(TBlocks.BLUE_VENDING_MACHINE.get().asItem());
            event.accept(TBlocks.OPEN_SIGN.get().asItem());
            event.accept(TBlocks.OPEN_SIGN_ALT.get().asItem());
            event.accept(TBlocks.OPEN_SIGN_ALT_TWO.get().asItem());
            event.accept(TBlocks.OFFICE_PHONE.get().asItem());
            event.accept(TBlocks.FAX_MACHINE.get().asItem());
            event.accept(TBlocks.WATER_FOUNTAIN.get().asItem());
            event.accept(TBlocks.ICECREAM_MACHINE.get().asItem());
            event.accept(TBlocks.FRIER.get().asItem());
            event.accept(TBlocks.ICECREAM_DISPLAY.get().asItem());
            event.accept(TBlocks.COMMERCIAL_UTENCIL_DISPENSER.get().asItem());
            event.accept(TBlocks.COMMERCIAL_CONDIMENT_DISPENSER.get().asItem());
            event.accept(TBlocks.COMMERCIAL_JUICE_DISPENSER.get().asItem());
            event.accept(TBlocks.COMMERCIAL_LIQUID_DISPENSER.get().asItem());
            event.accept(TBlocks.CLOTHES_RACK.get().asItem());
            event.accept(TBlocks.CASH_REGISTER.get().asItem());
            event.accept(TBlocks.CARD_READER.get().asItem());
            event.accept(TBlocks.SUPERMARKET_CONVEYOR.get().asItem());
            event.accept(TBlocks.STORE_NUMBER_SIGN.get().asItem());
            event.accept(TBlocks.AISLE_SIGN.get().asItem());
            event.accept(TBlocks.STORE_STAND.get().asItem());
            event.accept(TBlocks.STORE_SHELF.get().asItem());
            event.accept(TBlocks.STORE_FREEZER.get().asItem());
            event.accept(TBlocks.CONVENIENCE_SHELF.get().asItem());
            event.accept(TBlocks.SHOPPING_CART_MOVER.get().asItem());
            event.accept(TBlocks.SHOPPING_CART.get().asItem());
            event.accept(TBlocks.SHOPPING_BASKET_PILE.get().asItem());
            event.accept(TBlocks.SHOPPING_BASKET.get().asItem());
            event.accept(TBlocks.COMMERCIAL_WASHER.get().asItem());
            event.accept(TBlocks.COMMERCIAL_DRYER.get().asItem());
            event.accept(TBlocks.PARKING_METER.get().asItem());
            event.accept(TBlocks.PAYPHONE.get().asItem());
            event.accept(TBlocks.PAYPHONE_SEETHROUGH.get().asItem());
            event.accept(TBlocks.CAMPING_FUEL_CAN.get().asItem());
            event.accept(TBlocks.GAS_PUMP.get().asItem());
            event.accept(TBlocks.NEWSPAPER_DISPENSER.get().asItem());
            event.accept(TBlocks.RESTAURANT_TRASH_CAN.get().asItem());
            event.accept(TBlocks.DUMPSTER.get().asItem());
            event.accept(TBlocks.TRASH_BAG.get().asItem());
            // theater stuff
            event.accept(TItems.THEATER_PROJECTOR.get().asItem());
            event.accept(TBlocks.THEATER_SEAT.get().asItem());
            event.accept(TBlocks.THEATER_SEAT_CONTINUOUS.get().asItem());
            event.accept(TBlocks.POPCORN_MACHINE.get().asItem());
            event.accept(TBlocks.COTTON_CANDY_MAKER.get().asItem());
            event.accept(TBlocks.HOTDOG_ROTATOR.get().asItem());
            event.accept(TBlocks.SLUSHY_MACHINE.get().asItem());
            event.accept(TBlocks.TICKET_TELLER_WINDOW.get().asItem());
            event.accept(TBlocks.VELVET_ROPE_FENCE.get().asItem());
            event.accept(TBlocks.CARNIVAL_AWNING.get().asItem());
            event.accept(TBlocks.PORTA_POTTY.get().asItem());
            event.accept(TBlocks.CATWALK_CENTER.get().asItem());
            event.accept(TBlocks.CATWALK.get().asItem());
            event.accept(TBlocks.CONVEYOR_BELT.get().asItem());
            event.accept(TBlocks.ESCALATOR.get().asItem());
            event.accept(TBlocks.ESCALATOR_DOWN.get().asItem());
            event.accept(TBlocks.TEACHING_BOARD.get().asItem());
            event.accept(TItems.CLEVER_BLACKBOARD.get().asItem());
            event.accept(TBlocks.SCHOOL_DESK.get().asItem());
            event.accept(TBlocks.LOCKER.get().asItem());
            event.accept(TBlocks.LIBRARY_STOOL.get().asItem());
            // Electronics
            event.accept(TBlocks.CLASSIC_TV.get().asItem());
            event.accept(TBlocks.TV.get().asItem());
            event.accept(TBlocks.WALL_TV.get().asItem());
            event.accept(TBlocks.BIG_TV.get().asItem());
            event.accept(TBlocks.ULTRA_HD_TV.get().asItem());
            event.accept(TBlocks.OLD_PC_MONITOR.get().asItem());
            event.accept(TBlocks.MODERN_PC_MONITOR.get().asItem());
            event.accept(TItems.CURVED_MONITOR.get().asItem());
            event.accept(TBlocks.COMPUTER_CONTROLS.get().asItem());
            event.accept(TBlocks.PC_CONTROLS.get().asItem());
            event.accept(TBlocks.RGB_PC_CONTROLS.get().asItem());
            event.accept(TBlocks.DVD_PLAYER.get().asItem());
            event.accept(TBlocks.VHS_PLAYER.get().asItem());
            event.accept(TBlocks.CORNER_COMPUTER.get().asItem());
            event.accept(TBlocks.CORNER_COMPUTER_WM.get().asItem());
            event.accept(TBlocks.OLD_PC.get().asItem());
            event.accept(TBlocks.BROKEN_COMPUTER.get().asItem());
            event.accept(TBlocks.OLD_FLAT_COMPUTER.get().asItem());
            event.accept(TBlocks.BLUEY_DESKTOP_COMPUTER.get().asItem());
            event.accept(TBlocks.GAMING_PC.get().asItem());
            event.accept(TBlocks.BLUEYTOSH_LAPTOP_OLD.get().asItem());
            event.accept(TBlocks.BLUEYDOWS_LAPTOP.get().asItem());
            event.accept(TBlocks.BLUEYTOSH_LAPTOP.get().asItem());
            event.accept(TBlocks.BLUEYTOSH_STUDIO.get().asItem());
            event.accept(TBlocks.GRAY_GAME_CONSOLE.get().asItem());
            event.accept(TBlocks.BLACK_GAME_CONSOLE.get().asItem());
            event.accept(TBlocks.BOXY_CONSOLE.get().asItem());
            event.accept(TBlocks.ORANGE_BOXY_CONSOLE.get().asItem());
            event.accept(TBlocks.TALL_BOXY_CONSOLE.get().asItem());
            event.accept(TBlocks.BLUEYCUBE_CONSOLE.get().asItem());
            event.accept(TBlocks.BLUEMAN_CONSOLE.get().asItem());
            event.accept(TBlocks.BLUEYSNAP_BASE.get().asItem());
            event.accept(TBlocks.BLUEYSNAP_CONSOLE.get().asItem());
            event.accept(TBlocks.ANALOG_CLOCK.get().asItem());
            event.accept(TBlocks.CLOCK_RADIO.get().asItem());
            event.accept(TBlocks.PRINTER.get().asItem());
            event.accept(TBlocks.PROJECTOR.get().asItem());
            event.accept(TBlocks.VIDEO_CAMERA.get().asItem());
            event.accept(TBlocks.PROFESSIONAL_TV_CAMERA.get().asItem());
            event.accept(TBlocks.STUDIO_CAMERA.get().asItem());
            event.accept(TBlocks.WHITE_TELEPHONE.get().asItem());
            event.accept(TBlocks.BLACK_TELEPHONE.get().asItem());
            event.accept(TBlocks.GENERAL_DIGITAL_PHONE.get().asItem());
            event.accept(TBlocks.FEATURED_CORDLESS_PHONE.get().asItem());
            event.accept(TBlocks.MOBILE_PHONE.get().asItem());
            event.accept(TBlocks.SMARTPHONE.get().asItem());
            event.accept(TBlocks.GRAPHICS_CARD.get().asItem());
            event.accept(TBlocks.HARD_DRIVE.get().asItem());
            event.accept(TBlocks.INTERNET_MODEM.get().asItem());
            event.accept(TBlocks.INTERNET_ROUTER.get().asItem());
            event.accept(TBlocks.NEWER_INTERNET_ROUTER.get().asItem());
            event.accept(TBlocks.WIFI_ROUTER.get().asItem());
            // Audio Instruments
            event.accept(TBlocks.STRING_BASS.get().asItem());
            event.accept(TBlocks.BONGOS.get().asItem());
            event.accept(TBlocks.CONGAS.get().asItem());
            event.accept(TBlocks.CAJON.get().asItem());
            event.accept(TBlocks.BASS_DRUM.get().asItem());
            event.accept(TBlocks.SNARE_DRUM.get().asItem());
            event.accept(TBlocks.FLOOR_TOM.get().asItem());
            event.accept(TBlocks.RACK_TOM.get().asItem());
            event.accept(TBlocks.HI_HAT.get().asItem());
            event.accept(TBlocks.CYMBAL_CRASH.get().asItem());
            // Audio stuff
            event.accept(TBlocks.VOICE_MICROPHONE.get().asItem());
            event.accept(TBlocks.SPEAKER.get().asItem());
            event.accept(TBlocks.AUDIO_MIXER.get().asItem());
            event.accept(TBlocks.AUDIO_CONTROLLER.get().asItem());
            // dj stuff
            event.accept(TBlocks.DISCO_BALL.get().asItem());
            event.accept(TBlocks.GOBO_LIGHT.get().asItem());
            event.accept(TBlocks.DJ_LASER_LIGHT.get().asItem());
            event.accept(TBlocks.TURNTABLE.get().asItem());
            // Utilities
            event.accept(TBlocks.AC_DUCT.get().asItem());
            event.accept(TBlocks.AC_DUCT_CORNER.get().asItem());
            event.accept(TBlocks.AC_DUCT_ALLWAY.get().asItem());
            event.accept(TBlocks.AIRDUCT_VENT.get().asItem());
            event.accept(TBlocks.PTAC_AC.get().asItem());
            event.accept(TBlocks.AIR_CONDITIONER.get().asItem());
            event.accept(TBlocks.AC_THERMOSTAT.get().asItem());
            event.accept(TBlocks.GAS_HEATER.get().asItem());
            event.accept(TBlocks.WATER_SOFTENER.get().asItem());
            event.accept(TBlocks.SALT_TANK.get().asItem());
            event.accept(TBlocks.SOLAR_PANEL.get().asItem());
            event.accept(TBlocks.SERVER_RACK.get().asItem());
            // power outlets and switches or buttons
            event.accept(TBlocks.HOME_BREAKER.get().asItem());
            event.accept(TBlocks.UNGROUNDED_US_OUTLET.get().asItem());
            event.accept(TBlocks.US_OUTLET.get().asItem());
            event.accept(TBlocks.ALT_US_OUTLET.get().asItem());
            event.accept(TBlocks.T_US_OUTLET.get().asItem());
            event.accept(TBlocks.INTERNET_JACK_OUTLET.get().asItem());
            event.accept(TBlocks.USB_OUTLET.get().asItem());
            event.accept(TBlocks.UK_OUTLET.get().asItem());
            event.accept(TBlocks.GERMAN_OUTLET.get().asItem());
            event.accept(TBlocks.AUSTRALIAN_OUTLET.get().asItem());
            event.accept(TBlocks.BUTTON_SWITCH.get().asItem());
            event.accept(TBlocks.ROCKER_SWITCH.get().asItem());
            // door bells
            event.accept(TBlocks.DOOR_BELL.get().asItem());
            event.accept(TBlocks.METALLIC_DOOR_BELL.get().asItem());
            event.accept(TBlocks.PLUCK_DOOR_BELL.get().asItem());
            event.accept(TBlocks.OLD_DOOR_BELL.get().asItem());
            // fire prevention & protection
            event.accept(TBlocks.FIRE_EXTINGUISHER.get().asItem());
            event.accept(TBlocks.FIRE_DETECTOR.get().asItem());
            event.accept(TBlocks.BEEP_FIRE_ALARM.get().asItem());
            event.accept(TBlocks.HORN_FIRE_ALARM.get().asItem());
            event.accept(TBlocks.LOUD_FIRE_ALARM.get().asItem());
            event.accept(TBlocks.FIRE_ESCAPE_LADDER.get().asItem());
            // security
            event.accept(TBlocks.FILM_SECURITY_CAMERA.get().asItem());
            event.accept(TBlocks.ROBOT_SECURITY_CAMERA.get().asItem());
            event.accept(TBlocks.BOX_SECURITY_CAMERA.get().asItem());
            event.accept(TBlocks.SECURE_SECURITY_CAMERA.get().asItem());
            event.accept(TBlocks.DOME_SECURITY_CAMERA.get().asItem());
            event.accept(TBlocks.CHAINLINK_FENCE.get().asItem());
            event.accept(TBlocks.SECURITY_METAL_DETECTOR.get().asItem());
            // water management
            event.accept(TBlocks.CULVERT.get().asItem());
            event.accept(TBlocks.DIRT_CULVERT.get().asItem());
            event.accept(TBlocks.SAND_CULVERT.get().asItem());
            event.accept(TBlocks.SANDSTONE_CULVERT.get().asItem());
            event.accept(TBlocks.STONE_CULVERT.get().asItem());
            event.accept(TBlocks.TERRACOTTA_CULVERT.get().asItem());
            event.accept(TBlocks.BRICK_CULVERT.get().asItem());
            event.accept(TBlocks.STONE_BRICK_CULVERT.get().asItem());
            // hydrants
            event.accept(TBlocks.RED_FIRE_HYDRANT.get().asItem());
            event.accept(TBlocks.YELLOW_FIRE_HYDRANT.get().asItem());
            event.accept(TBlocks.SILVER_FIRE_HYDRANT.get().asItem());
            // hazard signs
            event.accept(TBlocks.BIO_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.BLAST_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.CRYO_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.HARDHAT_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.DEATH_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.FIRE_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.GENERAL_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.NOENTRY_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.FALLING_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.POISON_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.RADIATION_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.SHOCK_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.WORKERS_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.RADIOWAVES_HAZARD_SIGN.get().asItem());
            event.accept(TBlocks.OXYGEN_HAZARD_SIGN.get().asItem());
            // Home Appliances & Other
            // misc. home things
            event.accept(TBlocks.BUTTER_CHURNER.get().asItem());
            event.accept(TBlocks.GARDEN_GNOME.get().asItem());
            event.accept(TBlocks.GARDEN_HOSE.get().asItem());
            event.accept(TBlocks.PICNIC_TABLE.get().asItem());
            event.accept(TBlocks.AQUARIUM.get().asItem());
            event.accept(TBlocks.DOG_HOUSE.get().asItem());
            event.accept(TBlocks.CAT_TREE.get().asItem());
            event.accept(TBlocks.LITTER_BOX.get().asItem());
            event.accept(TBlocks.HOTTUB.get().asItem());
            event.accept(TBlocks.MAILBOX.get().asItem());
            event.accept(TBlocks.GREY_MAILBOX.get().asItem());
            event.accept(TBlocks.BLACK_MAILBOX.get().asItem());
            event.accept(TBlocks.LAWN_MOWER.get().asItem());
            event.accept(TBlocks.GENERATOR.get().asItem());
            event.accept(TBlocks.CEILING_FAN.get().asItem());
            event.accept(TBlocks.WHITE_FAN.get().asItem());
            event.accept(TBlocks.GRAY_FAN.get().asItem());
            event.accept(TBlocks.BLACK_FAN.get().asItem());
            event.accept(TBlocks.LAVA_LAMP.get().asItem());
            event.accept(TBlocks.STANDING_VACUUM.get().asItem());
            event.accept(TBlocks.SHOP_VACUUM.get().asItem());
            event.accept(TBlocks.STEAM_CLEANER.get().asItem());
            event.accept(TBlocks.CALENDAR.get().asItem());
            event.accept(TBlocks.CRIB.get().asItem());
            event.accept(TBlocks.BABY_CARRIAGE.get().asItem());
            event.accept(TBlocks.GAS_CAN.get().asItem());
            event.accept(TBlocks.SATELLITE_DISH.get().asItem());
            event.accept(TBlocks.ANTENNA.get().asItem());
            event.accept(TBlocks.OLD_WOODEN_PHONE.get().asItem());
            event.accept(TBlocks.WHEELBARROW.get().asItem());
            event.accept(TBlocks.TRASH_CAN.get().asItem());
            event.accept(TBlocks.RECYCLE_BIN.get().asItem());
            event.accept(TBlocks.LAUNDRY_BASKET.get().asItem());
            event.accept(TBlocks.LAUNDRY_PILE.get().asItem());
            event.accept(TBlocks.THERMOMETER.get().asItem());
            event.accept(TBlocks.AIR_PURIFIER.get().asItem());
            event.accept(TBlocks.SPACE_HEATER.get().asItem());
            event.accept(TBlocks.VHS_COLLECTION.get().asItem());
            event.accept(TBlocks.DVD_COLLECTION.get().asItem());
            event.accept(TBlocks.TALL_LAMP.get().asItem());
            event.accept(TBlocks.DESK_LAMP.get().asItem());
            // eating utensils
            event.accept(TBlocks.PLATE.get().asItem());
            event.accept(TBlocks.CUP.get().asItem());
            event.accept(TBlocks.COOKIE_JAR.get().asItem());
            event.accept(TBlocks.EATING_UTENCILS.get().asItem());
            // furniture
            // tables
            event.accept(TBlocks.STONE_TABLE.get().asItem());
            event.accept(TBlocks.QUARTZ_TABLE.get().asItem());
            // copper tables
            event.accept(TBlocks.COPPER_TABLE.get().asItem());
            event.accept(TBlocks.EXPOSED_COPPER_TABLE.get().asItem());
            event.accept(TBlocks.WEATHERED_COPPER_TABLE.get().asItem());
            event.accept(TBlocks.OXIDIZED_COPPER_TABLE.get().asItem());
            event.accept(TBlocks.WAXED_COPPER_TABLE.get().asItem());
            event.accept(TBlocks.WAXED_EXPOSED_COPPER_TABLE.get().asItem());
            event.accept(TBlocks.WAXED_WEATHERED_COPPER_TABLE.get().asItem());
            event.accept(TBlocks.WAXED_OXIDIZED_COPPER_TABLE.get().asItem());
            event.accept(TBlocks.IRON_TABLE.get().asItem());
            event.accept(TBlocks.GOLD_TABLE.get().asItem());
            event.accept(TBlocks.DIAMOND_TABLE.get().asItem());
            event.accept(TBlocks.NETHER_BRICK_TABLE.get().asItem());
            event.accept(TBlocks.PRISMARINE_TABLE.get().asItem());
            event.accept(TBlocks.PURPUR_TABLE.get().asItem());
            event.accept(TBlocks.SCULK_TABLE.get().asItem());
            event.accept(TBlocks.TRIPLE_SHELF.get().asItem());
            // chairs
            event.accept(TBlocks.BARBER_CHAIR.get().asItem());
            event.accept(TBlocks.STONE_CHAIR.get().asItem());
            event.accept(TBlocks.QUARTZ_CHAIR.get().asItem());
            event.accept(TBlocks.COPPER_CHAIR.get().asItem());
            event.accept(TBlocks.EXPOSED_COPPER_CHAIR.get().asItem());
            event.accept(TBlocks.WEATHERED_COPPER_CHAIR.get().asItem());
            event.accept(TBlocks.OXIDIZED_COPPER_CHAIR.get().asItem());
            event.accept(TBlocks.WAXED_COPPER_CHAIR.get().asItem());
            event.accept(TBlocks.WAXED_EXPOSED_COPPER_CHAIR.get().asItem());
            event.accept(TBlocks.WAXED_WEATHERED_COPPER_CHAIR.get().asItem());
            event.accept(TBlocks.WAXED_OXIDIZED_COPPER_CHAIR.get().asItem());
            event.accept(TBlocks.IRON_CHAIR.get().asItem());
            event.accept(TBlocks.GOLD_CHAIR.get().asItem());
            event.accept(TBlocks.DIAMOND_CHAIR.get().asItem());
            event.accept(TBlocks.NETHER_BRICK_CHAIR.get().asItem());
            event.accept(TBlocks.PRISMARINE_CHAIR.get().asItem());
            event.accept(TBlocks.PURPUR_CHAIR.get().asItem());
            event.accept(TBlocks.SCULK_CHAIR.get().asItem());
            event.accept(TBlocks.POOP_CHAIR.get().asItem());
            // couches and seats
            event.accept(TBlocks.LOVE_SEAT.get().asItem());
            event.accept(TBlocks.LOVE_COUCH.get().asItem());
            event.accept(TBlocks.MYSTERIOUS_ONE_COUCH.get().asItem());
            // appliances
            event.accept(TBlocks.WATER_DISPENSER.get().asItem());
            event.accept(TBlocks.DRYER.get().asItem());
            event.accept(TBlocks.WASHER.get().asItem());
            event.accept(TBlocks.DISHWASHER_WALL.get().asItem());
            event.accept(TBlocks.PORTABLE_DISH_WASHER.get().asItem());
            event.accept(TBlocks.HUMIDIFIER.get().asItem());
            event.accept(TBlocks.DEHUMIDIFIER.get().asItem());
            event.accept(TBlocks.TOOL_STATION.get().asItem());
            event.accept(TBlocks.FREEZER.get().asItem());
            event.accept(TBlocks.FRIDGE.get().asItem());
            event.accept(TBlocks.MINI_FRIDGE.get().asItem());
            event.accept(TBlocks.STOVE_HOOD.get().asItem());
            event.accept(TBlocks.STOVE.get().asItem());
            event.accept(TBlocks.SEWING_MACHINE.get().asItem());
            event.accept(TBlocks.IRONING_TABLE.get().asItem());
            // Kitchen Stuff
            event.accept(TBlocks.KITCHEN_SINK.get().asItem());
            event.accept(TBlocks.MICROWAVE.get().asItem());
            event.accept(TBlocks.TOASTER_OVEN.get().asItem());
            event.accept(TBlocks.TOASTER.get().asItem());
            event.accept(TBlocks.OVEN.get().asItem());
            event.accept(TBlocks.PAPER_TOWEL.get().asItem());
            event.accept(TBlocks.FOOD_PROCESSOR.get().asItem());
            event.accept(TBlocks.BLENDER.get().asItem());
            event.accept(TBlocks.STAND_MIXER.get().asItem());
            event.accept(TBlocks.JUICER.get().asItem());
            event.accept(TBlocks.RICE_COOKER.get().asItem());
            event.accept(TBlocks.SLOW_COOKER.get().asItem());
            event.accept(TBlocks.INSTANT_POT.get().asItem());
            event.accept(TBlocks.AIR_FRYER.get().asItem());
            event.accept(TBlocks.BREAD_MACHINE.get().asItem());
            event.accept(TBlocks.WAFFLE_IRON.get().asItem());
            event.accept(TBlocks.PANINI_MAKER.get().asItem());
            event.accept(TBlocks.ICE_CREAM_MAKER.get().asItem());
            event.accept(TBlocks.YOGURT_MAKER.get().asItem());
            event.accept(TBlocks.COFFEE_GRINDER.get().asItem());
            event.accept(TBlocks.FRENCH_PRESS.get().asItem());
            event.accept(TBlocks.COFFEE_MACHINE.get().asItem());
            event.accept(TBlocks.FOOD_DEHYDRATOR.get().asItem());
            event.accept(TBlocks.SMOKER_GRILL.get().asItem());
            // Bathroom Stuff
            event.accept(TBlocks.MIRROR.get().asItem());
            event.accept(TBlocks.SMALL_SINK.get().asItem());
            event.accept(TBlocks.FANCY_SINK.get().asItem());
            event.accept(TBlocks.SHOWER_HANDLES.get().asItem());
            event.accept(TBlocks.SHOWER_HEAD.get().asItem());
            event.accept(TBlocks.BATHTUB_NOZZLE.get().asItem());
            event.accept(TBlocks.DENTAL_CUP.get().asItem());
            event.accept(TBlocks.TOILET.get().asItem());
            event.accept(TBlocks.TOILET_PAPER.get().asItem());
            event.accept(TBlocks.TISSUE_BOX.get().asItem());
            // Toys & Kids Stuff
            event.accept(TBlocks.TOY_BOX.get().asItem());
            event.accept(TBlocks.WOOD_DUCK.get().asItem());
            event.accept(TBlocks.WOOD_CAR.get().asItem());
            event.accept(TBlocks.TEDDY_BEAR.get().asItem());
            event.accept(TBlocks.MRPUPPY.get().asItem());
            event.accept(TBlocks.REINDEER_PLUSHY.get().asItem());
            event.accept(TBlocks.SNOWMAN_PLUSHY.get().asItem());
            event.accept(TBlocks.STEVE_PLUSHY.get().asItem());
            event.accept(TBlocks.ZOMBIE_PLUSHIE.get().asItem());
            event.accept(TBlocks.CREEPER_PLUSHY.get().asItem());
            // Hospital and Health
            event.accept(TBlocks.HOSPITAL_COVER.get().asItem());
            event.accept(TBlocks.HOSPITAL_BED.get().asItem());
            event.accept(TBlocks.HOSPITAL_COMPUTER.get().asItem());
            event.accept(TBlocks.HEART_MONITOR.get().asItem());
            event.accept(TBlocks.IV.get().asItem());
            event.accept(TBlocks.OPERATION_TABLE.get().asItem());
            event.accept(TBlocks.OPERATION_TOOLS.get().asItem());
            // Science
            event.accept(TBlocks.MICROSCOPE.get().asItem());
            event.accept(TBlocks.CHEMICAL_TUBE.get().asItem());
            event.accept(TBlocks.BEAKER.get().asItem());
            event.accept(TBlocks.FLASK.get().asItem());
            // Packed & Bulk Items
            event.accept(TBlocks.WATER_BOTTLE_PACK.get().asItem());
            event.accept(TBlocks.BULK_PRODUCT.get().asItem());
            // Graveyards & Death
            event.accept(TBlocks.COFFIN.get().asItem());
            event.accept(TBlocks.CROSS_GRAVESTONE.get().asItem());
            event.accept(TBlocks.STANDARD_GRAVESTONE.get().asItem());
            event.accept(TBlocks.PLACARD_GRAVESTONE.get().asItem());
            // Other
            event.accept(TItems.UMBRELLA.get().asItem());
            event.accept(TBlocks.CHIMNEY.get().asItem());
            event.accept(TItems.ANIMATED_ICE_RINK.get().asItem());
            event.accept(TBlocks.SNOW_MACHINE.get().asItem());
            event.accept(TBlocks.BALL_PIT.get().asItem());
            // Seasonal (Christmas)
            event.accept(TBlocks.CHRISTMAS_FIREPLACE.get().asItem());
            event.accept(TBlocks.SLEIGH.get().asItem());
            event.accept(TBlocks.GINGERBREAD_HOUSE.get().asItem());
            event.accept(TBlocks.NUTCRACKER.get().asItem());
            event.accept(TBlocks.NORTH_POLE.get().asItem());
            event.accept(TBlocks.SANTA_STATUE.get().asItem());
            event.accept(TBlocks.SANTA_INFLATABLE.get().asItem());
            event.accept(TBlocks.SNOWMAN.get().asItem());
            event.accept(TBlocks.SNOWMAN_BLUEMAN_STATUE.get().asItem());
            event.accept(TBlocks.CHRISTMAS_WREATH.get().asItem());
            event.accept(TBlocks.SMALL_CHRISTMAS_TREE.get().asItem());
            event.accept(TBlocks.CHRISTMAS_TREE.get().asItem());
            event.accept(TBlocks.LIGHTED_CHRISTMAS_TREE.get().asItem());
            event.accept(TBlocks.PRESENT_PILE.get().asItem());
            event.accept(TBlocks.LIGHTED_DEER.get().asItem());
            event.accept(TItems.ANIMATED_DEER.get().asItem());
            event.accept(TBlocks.CHRISTMAS_LIGHTS.get().asItem());
            event.accept(TBlocks.CHRISTMAS_LIGHTS_ALT.get().asItem());
            event.accept(TBlocks.AMBER_STRING_LIGHTS.get().asItem());
            event.accept(TBlocks.BLUE_STRING_LIGHTS.get().asItem());
            // Food Related
            event.accept(TBlocks.FOOD_COOLER.get().asItem());
            event.accept(TBlocks.WINE_BOTTLE.get().asItem());
            event.accept(TBlocks.ROAST_TURKEY.get().asItem());
            event.accept(TBlocks.PIZZA_BOX.get().asItem());
            event.accept(TBlocks.TENTH_ANNIVERSARY_CAKE.get().asItem());
            event.accept(TBlocks.CHEESE_BLOCK.get().asItem());
            event.accept(TBlocks.GLOWING_CHEESE_BLOCK.get().asItem());
            // Misc. Junk
            event.accept(TBlocks.POWDER_KEG.get().asItem());
            event.accept(TBlocks.BIOHAZARD_BIN.get().asItem());
            event.accept(TBlocks.RADIOACTIVE_BARREL.get().asItem());
            event.accept(TBlocks.FIREWORKS_DISPLAY.get().asItem());
            event.accept(TBlocks.ITEM_DISPLAY_BLOCK.get().asItem());

            event.accept(TBlocks.BLUEMAN_STATUE.get().asItem());
            event.accept(TBlocks.SPECIAL_STATUE.get().asItem());
            event.accept(TBlocks.PARTICULAR_STATUE.get().asItem());
            //event.accept(TBlocks.FURIOUS_STATUE.get().asItem());
            event.accept(TBlocks.CATCHING_STATUE.get().asItem());
            //event.accept(TBlocks.SORROW_STATUE.get().asItem());
            event.accept(TBlocks.BONDING_STATUE.get().asItem());
            event.accept(TItems.STRANGE_STATUE.get());
            event.accept(TBlocks.DUCK_STATUE.get().asItem());
            event.accept(TBlocks.VALIANT_STATUE.get().asItem());
            event.accept(TBlocks.TRI_CANDLE_HOLDER_BLOCK.get().asItem());
            event.accept(TBlocks.REINDEER_WALL_HEAD.get().asItem());
            event.accept(TBlocks.WARDEN_TROPHY.get().asItem());
            event.accept(TBlocks.POOP.get().asItem());
            event.accept(TBlocks.FULL_POOP_BLOCK.get().asItem());
            // neon-ish blocks
            event.accept(TBlocks.GLOW_BLOCK.get());
            event.accept(TBlocks.LIGHT_GRAY_GLOW_BLOCK.get());
            event.accept(TBlocks.GRAY_GLOW_BLOCK.get());
            event.accept(TBlocks.BLACK_GLOW_BLOCK.get());
            event.accept(TBlocks.BROWN_GLOW_BLOCK.get());
            event.accept(TBlocks.RED_GLOW_BLOCK.get());
            event.accept(TBlocks.ORANGE_GLOW_BLOCK.get());
            event.accept(TBlocks.YELLOW_GLOW_BLOCK.get());
            event.accept(TBlocks.LIME_GLOW_BLOCK.get());
            event.accept(TBlocks.GREEN_GLOW_BLOCK.get());
            event.accept(TBlocks.CYAN_GLOW_BLOCK.get());
            event.accept(TBlocks.LIGHT_BLUE_GLOW_BLOCK.get());
            event.accept(TBlocks.BLUE_GLOW_BLOCK.get());
            event.accept(TBlocks.PURPLE_GLOW_BLOCK.get());
            event.accept(TBlocks.MAGENTA_GLOW_BLOCK.get());
            event.accept(TBlocks.PINK_GLOW_BLOCK.get());
        }
    }
}
