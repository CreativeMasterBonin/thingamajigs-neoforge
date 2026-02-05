package net.rk.thingamajigs.network;

import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.network.payload.DJLaserLightUpdatePacket;
import net.rk.thingamajigs.network.record.DJLaserLightUpdatePayload;

public class Handler{
    public static void register(final RegisterPayloadHandlersEvent event){
        final PayloadRegistrar regex_reg = event.registrar(Thingamajigs.MODID);
        regex_reg.playToServer(DJLaserLightUpdatePayload.TYPE,DJLaserLightUpdatePayload.STREAM_CODEC,DJLaserLightUpdatePacket.get()::handle);
    }
}
