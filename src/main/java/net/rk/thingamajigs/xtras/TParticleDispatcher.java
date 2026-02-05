package net.rk.thingamajigs.xtras;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.xtras.particletypes.ChimneySmokeParticleType;

@EventBusSubscriber(modid = Thingamajigs.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class TParticleDispatcher {
    @SubscribeEvent
    public static void registerProviders(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(TParticles.CHIMNEY_SMOKE.get(), ChimneySmokeParticleType.ChimneySmokeParticleFactory::new);
    }
}
