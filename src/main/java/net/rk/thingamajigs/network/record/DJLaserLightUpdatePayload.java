package net.rk.thingamajigs.network.record;

import com.mojang.datafixers.util.Function14;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.rk.thingamajigs.Thingamajigs;

import java.util.function.Function;

public record DJLaserLightUpdatePayload(BlockPos bp,
                                        int height, String colorstr,
                                        float laserSize, int laserCount,
                                        float angleOffset, float vAngleOffset,
                                        int lightMode,float hOsc,float vOsc,
                                        float hMulti, float vMulti,
                                        String lightModeData, String laserTextureLoc) implements CustomPacketPayload {
    public static final Type<DJLaserLightUpdatePayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,"dj_light_update"));

    public static final StreamCodec<FriendlyByteBuf, DJLaserLightUpdatePayload> STREAM_CODEC = composite(
            BlockPos.STREAM_CODEC, DJLaserLightUpdatePayload::bp,
            ByteBufCodecs.INT, DJLaserLightUpdatePayload::height,
            ByteBufCodecs.STRING_UTF8,DJLaserLightUpdatePayload::colorstr,
            ByteBufCodecs.FLOAT,DJLaserLightUpdatePayload::laserSize,
            ByteBufCodecs.INT,DJLaserLightUpdatePayload::laserCount,
            ByteBufCodecs.FLOAT,DJLaserLightUpdatePayload::angleOffset,
            ByteBufCodecs.FLOAT,DJLaserLightUpdatePayload::vAngleOffset,
            ByteBufCodecs.INT,DJLaserLightUpdatePayload::lightMode,
            ByteBufCodecs.FLOAT,DJLaserLightUpdatePayload::hOsc,
            ByteBufCodecs.FLOAT,DJLaserLightUpdatePayload::vOsc,
            ByteBufCodecs.FLOAT,DJLaserLightUpdatePayload::hMulti,
            ByteBufCodecs.FLOAT,DJLaserLightUpdatePayload::vMulti,
            ByteBufCodecs.STRING_UTF8,DJLaserLightUpdatePayload::lightModeData,
            ByteBufCodecs.STRING_UTF8,DJLaserLightUpdatePayload::laserTextureLoc,
            DJLaserLightUpdatePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14> StreamCodec<B, C> composite(
            final StreamCodec<? super B, T1> kedc1,
            final Function<C, T1> getter1,
            final StreamCodec<? super B, T2> kedc2,
            final Function<C, T2> getter2,
            final StreamCodec<? super B, T3> kedc3,
            final Function<C, T3> getter3,
            final StreamCodec<? super B, T4> kedc4,
            final Function<C, T4> getter4,
            final StreamCodec<? super B, T5> kedc5,
            final Function<C, T5> getter5,
            final StreamCodec<? super B, T6> kedc6,
            final Function<C, T6> getter6,
            final StreamCodec<? super B, T7> kedc7,
            final Function<C, T7> getter7,
            final StreamCodec<? super B, T8> kedc8,
            final Function<C, T8> getter8,
            final StreamCodec<? super B, T9> kedc9,
            final Function<C, T9> getter9,
            final StreamCodec<? super B, T10> kedc10,
            final Function<C, T10> getter10,
            final StreamCodec<? super B, T11> kedc11,
            final Function<C, T11> getter11,
            final StreamCodec<? super B, T12> kedc12,
            final Function<C, T12> getter12,
            final StreamCodec<? super B, T13> kedc13,
            final Function<C, T13> getter13,
            final StreamCodec<? super B, T14> kedc14,
            final Function<C, T14> getter14,
            final Function14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, C> fnc14) {
        return new StreamCodec<B, C>() {
            @Override
            public C decode(B p_330310_) {
                T1 t1 = kedc1.decode(p_330310_);
                T2 t2 = kedc2.decode(p_330310_);
                T3 t3 = kedc3.decode(p_330310_);
                T4 t4 = kedc4.decode(p_330310_);
                T5 t5 = kedc5.decode(p_330310_);
                T6 t6 = kedc6.decode(p_330310_);
                T7 t7 = kedc7.decode(p_330310_);
                T8 t8 = kedc8.decode(p_330310_);
                T9 t9 = kedc9.decode(p_330310_);
                T10 t10 = kedc10.decode(p_330310_);
                T11 t11 = kedc11.decode(p_330310_);
                T12 t12 = kedc12.decode(p_330310_);
                T13 t13 = kedc13.decode(p_330310_);
                T14 t14 = kedc14.decode(p_330310_);
                return fnc14.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14);
            }

            @Override
            public void encode(B p_332052_, C p_331912_) {
                kedc1.encode(p_332052_, getter1.apply(p_331912_));
                kedc2.encode(p_332052_, getter2.apply(p_331912_));
                kedc3.encode(p_332052_, getter3.apply(p_331912_));
                kedc4.encode(p_332052_, getter4.apply(p_331912_));
                kedc5.encode(p_332052_, getter5.apply(p_331912_));
                kedc6.encode(p_332052_, getter6.apply(p_331912_));
                kedc7.encode(p_332052_, getter7.apply(p_331912_));
                kedc8.encode(p_332052_, getter8.apply(p_331912_));
                kedc9.encode(p_332052_, getter9.apply(p_331912_));
                kedc10.encode(p_332052_, getter10.apply(p_331912_));
                kedc11.encode(p_332052_, getter11.apply(p_331912_));
                kedc12.encode(p_332052_, getter12.apply(p_331912_));
                kedc13.encode(p_332052_, getter13.apply(p_331912_));
                kedc14.encode(p_332052_, getter14.apply(p_331912_));
            }
        };
    }
}
