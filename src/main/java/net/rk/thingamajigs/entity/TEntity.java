package net.rk.thingamajigs.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.entity.custom.Chair;
import net.rk.thingamajigs.entity.custom.Stool;
import net.rk.thingamajigs.entity.custom.Toilet;

import java.util.function.Supplier;

public class TEntity{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Thingamajigs.MODID);

    public static final DeferredHolder<EntityType<?>,EntityType<Chair>> CHAIR = register("chair",
            () -> EntityType.Builder.<Chair>of(Chair::new,MobCategory.MISC)
                    .sized(0.00001F, 0.00001F)
                    .setUpdateInterval(20)
                    .clientTrackingRange(16)
                    .fireImmune());

    public static final DeferredHolder<EntityType<?>,EntityType<Toilet>> TOILET = register("toilet",
            () -> EntityType.Builder.<Toilet>of(Toilet::new,MobCategory.MISC)
                    .sized(0.00001F, 0.00001F)
                    .setUpdateInterval(20)
                    .clientTrackingRange(16)
                    .fireImmune());

    public static final DeferredHolder<EntityType<?>,EntityType<Stool>> STOOL = register("stool",
            () -> EntityType.Builder.<Stool>of(Stool::new,MobCategory.MISC)
                    .sized(0.00001F, 0.00001F)
                    .setUpdateInterval(20)
                    .clientTrackingRange(16)
                    .fireImmune());

    public static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builder) {
        return ENTITIES.register(name, () -> builder.get().build(name));
    }

    public static void register(IEventBus eventBus){ENTITIES.register(eventBus);}
}
