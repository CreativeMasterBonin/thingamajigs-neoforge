package net.rk.thingamajigs.blockentity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.blockentity.custom.*;

import java.util.function.Supplier;

public class TBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            BuiltInRegistries.BLOCK_ENTITY_TYPE, Thingamajigs.MODID);

    public static final Supplier<BlockEntityType<DJLaserLightBE>> DJ_LASER_LIGHT_BE = BLOCK_ENTITIES.register(
            "laser_light_be",() ->
                    BlockEntityType.Builder.of(DJLaserLightBE::new,TBlocks.DJ_LASER_LIGHT.get())
                    .build(null));

    public static final Supplier<BlockEntityType<FridgeBE>> FRIDGE_BE = BLOCK_ENTITIES.register(
            "fridge_be",() -> BlockEntityType.Builder.of(FridgeBE::new,TBlocks.FRIDGE.get())
                    .build(null));

    public static final Supplier<BlockEntityType<PlateBE>> PLATE_BE = BLOCK_ENTITIES.register(
            "plate_be",() -> BlockEntityType.Builder.of(PlateBE::new,TBlocks.PLATE.get())
                    .build(null));

    public static final Supplier<BlockEntityType<MailboxBE>> MAILBOX_BLOCK_ENTITY =
            BLOCK_ENTITIES.register(
                    "mailbox", () -> BlockEntityType.Builder.of(
                            MailboxBE::new,TBlocks.MAILBOX.get()
                    ).build(null));

    public static final Supplier<BlockEntityType<ItemDisplayBE>> ITEM_DISPLAY_BE = BLOCK_ENTITIES.register(
            "item_display_be",() -> BlockEntityType.Builder.of(ItemDisplayBE::new,TBlocks.ITEM_DISPLAY_BLOCK.get())
                    .build(null));

    public static final Supplier<BlockEntityType<CurvedMonitorBE>> CURVED_MONITOR_BE = BLOCK_ENTITIES.register(
            "curved_monitor_be",() -> BlockEntityType.Builder.of(CurvedMonitorBE::new,TBlocks.CURVED_MONITOR.get())
                    .build(null));

    public static final Supplier<BlockEntityType<CleverBlackboardBE>> CLEVER_BLACKBOARD_BE = BLOCK_ENTITIES.register(
            "clever_blackboard_be",() -> BlockEntityType.Builder.of(CleverBlackboardBE::new,TBlocks.CLEVER_BLACKBOARD.get())
                    .build(null));

    public static final Supplier<BlockEntityType<UmbrellaBE>> UMBRELLA_BE = BLOCK_ENTITIES.register(
            "umbrella_be",() -> BlockEntityType.Builder.of(UmbrellaBE::new,TBlocks.UMBRELLA.get())
                    .build(null));

    public static final Supplier<BlockEntityType<TheaterProjectorBE>> THEATER_PROJECTOR_BE = BLOCK_ENTITIES.register(
            "theater_projector_be",() -> BlockEntityType.Builder.of(TheaterProjectorBE::new,TBlocks.THEATER_PROJECTOR.get())
                    .build(null));

    public static final Supplier<BlockEntityType<StorageDecorationBE>> STORAGE_DECORATION_BE = BLOCK_ENTITIES.register(
            "storage_decoration_be",() -> BlockEntityType.Builder.of(StorageDecorationBE::new,
                            TBlocks.TRIPLE_SHELF.get(),TBlocks.TOY_BOX.get()
                    ).build(null));

    public static final Supplier<BlockEntityType<AnimatedIceRinkBE>> ANIMATED_ICE_RINK = BLOCK_ENTITIES.register(
            "animated_ice_rink_be",() -> BlockEntityType.Builder.of(AnimatedIceRinkBE::new,TBlocks.ANIMATED_ICE_RINK.get())
                    .build(null)
    );

    public static final Supplier<BlockEntityType<AnimatedDeerBE>> ANIMATED_DEER_BE = BLOCK_ENTITIES.register(
            "animated_deer_be",() -> BlockEntityType.Builder.of(AnimatedDeerBE::new,TBlocks.ANIMATED_DEER.get())
            .build(null)
    );

    public static final Supplier<BlockEntityType<FootballGoalBE>> FOOTBALL_GOAL = BLOCK_ENTITIES.register(
            "football_goal",() -> BlockEntityType.Builder.of(FootballGoalBE::new,TBlocks.FOOTBALL_GOAL.get())
                    .build(null)
    );

    // general purpose block entities (add blocks here so they are supported when needed)
    public static final Supplier<BlockEntityType<OpenableContainer>> OPENABLE_CONTAINER = BLOCK_ENTITIES.register(
            "openable_container_be",() -> BlockEntityType.Builder.of(OpenableContainer::new,
                            TBlocks.FOOD_COOLER.get(),TBlocks.CARDBOARD_BOX.get()
                    )
                    .build(null));

    public static final Supplier<BlockEntityType<CarWashBrushBE>> CAR_WASH_BRUSH_BE = BLOCK_ENTITIES.register(
            "car_wash_brush_be",() -> BlockEntityType.Builder.of(CarWashBrushBE::new,
                            TBlocks.CAR_WASH_BLUE_BRUSH.get(),
                            TBlocks.CAR_WASH_YELLOW_BRUSH.get(),
                            TBlocks.CAR_WASH_RED_BRUSH.get(),
                            TBlocks.CAR_WASH_MIXED_BRUSH.get()
                    )
                    .build(null)
    );

    public static final Supplier<BlockEntityType<MitterCurtainBE>> MITTER_CURTAIN = BLOCK_ENTITIES.register(
            "mitter_curtain",() -> BlockEntityType.Builder.of(MitterCurtainBE::new,
                            TBlocks.CAR_WASH_MITTER_CURTAIN.get())
                    .build(null)
    );
    public static final Supplier<BlockEntityType<CarWashTireScrubberBE>> CAR_WASH_TIRE_SCRUBBER_BE = BLOCK_ENTITIES.register(
            "car_wash_tire_scrubber",() -> BlockEntityType.Builder.of(CarWashTireScrubberBE::new,
                            TBlocks.CAR_WASH_TIRE_SCRUBBER.get())
                    .build(null)
    );

    public static final Supplier<BlockEntityType<UltraHDTVBE>> ULTRA_HD_TV = BLOCK_ENTITIES.register(
            "ultra_hd_tv_be",() -> BlockEntityType.Builder.of(UltraHDTVBE::new,
                            TBlocks.ULTRA_HD_TV.get())
                    .build(null)
    );

    public static final Supplier<BlockEntityType<DecorationalBucketBE>> DECORATIONAL_BUCKET_BE = BLOCK_ENTITIES.register(
            "decorational_bucket_be",() -> BlockEntityType.Builder.of(DecorationalBucketBE::new,
                            TBlocks.DECORATIONAL_BUCKET.get())
                    .build(null)
    );

    public static final Supplier<BlockEntityType<FancyStorageDecorationBE>> FANCY_STORAGE_DECORATION_BE = BLOCK_ENTITIES.register(
            "fancy_storage_decoration_be",() -> BlockEntityType.Builder.of(FancyStorageDecorationBE::new,
                TBlocks.WHITE_CUBE_SHELF.get()
            ).build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
