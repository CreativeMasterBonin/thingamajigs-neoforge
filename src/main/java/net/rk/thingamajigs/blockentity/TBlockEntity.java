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


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
