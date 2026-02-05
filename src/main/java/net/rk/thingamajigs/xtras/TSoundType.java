package net.rk.thingamajigs.xtras;

import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.util.DeferredSoundType;

public class TSoundType{
    public static final DeferredSoundType POOP = new DeferredSoundType(1.0F,1.0F,
            TSoundEvent.POOP_BREAK,TSoundEvent.POOP_STEP,
            TSoundEvent.POOP,TSoundEvent.POOP_HIT,TSoundEvent.POOP);

    public static final DeferredSoundType CALENDAR = new DeferredSoundType(1.0f,1.0f,
            () -> SoundEvents.ITEM_FRAME_BREAK,() -> SoundEvents.BAMBOO_WOOD_STEP,
            () -> SoundEvents.ITEM_FRAME_PLACE,() -> SoundEvents.BAMBOO_WOOD_HIT,() -> SoundEvents.ITEM_FRAME_BREAK);

    public static final DeferredSoundType METALLIC_INSTRUMENT = new DeferredSoundType(1.0f,1.0f,
            () -> SoundEvents.METAL_BREAK,() -> SoundEvents.METAL_STEP,
            () -> SoundEvents.METAL_PLACE,TSoundEvent.METALLIC_HIT,TSoundEvent.METALLIC_HIT);

    public static final DeferredSoundType NATURAL_INSTRUMENT = new DeferredSoundType(1.0f,1.0f,
            () -> SoundEvents.PAINTING_BREAK,() -> SoundEvents.BOAT_PADDLE_LAND,
            () -> SoundEvents.PAINTING_PLACE,TSoundEvent.NATURAL_HIT,TSoundEvent.NATURAL_HIT);

    public static final DeferredSoundType PAPER_INSTRUMENT = new DeferredSoundType(1.0f,1.0f,
            () -> SoundEvents.PAINTING_BREAK,() -> SoundEvents.BOAT_PADDLE_LAND,
            () -> SoundEvents.PAINTING_PLACE,TSoundEvent.PAPER_HIT,TSoundEvent.PAPER_HIT);

    public static final DeferredSoundType RATTLE_INSTRUMENT = new DeferredSoundType(1.0f,1.0f,
            () -> SoundEvents.BAMBOO_WOOD_HANGING_SIGN_BREAK,() -> SoundEvents.BOAT_PADDLE_LAND,
            () -> SoundEvents.PINK_PETALS_PLACE,TSoundEvent.RATTLE_HIT,TSoundEvent.RATTLE_HIT);
}
