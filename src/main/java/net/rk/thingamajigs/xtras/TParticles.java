package net.rk.thingamajigs.xtras;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.thingamajigs.Thingamajigs;

import java.util.function.Supplier;

public class TParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, Thingamajigs.MODID);

    public static final Supplier<SimpleParticleType> CHIMNEY_SMOKE =
            PARTICLE_TYPES.register("chimney_smoke", () -> new SimpleParticleType(false));

    public static void register(IEventBus bus){
        PARTICLE_TYPES.register(bus);
    }
}
