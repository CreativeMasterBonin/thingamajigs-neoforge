package net.rk.thingamajigs.block.custom;

import net.minecraft.world.level.block.SoundType;

public class DiscoBall extends ThingamajigsDecorativeBlock{
    public DiscoBall(Properties p) {
        super(p.strength(1f,2f).sound(SoundType.GLASS).noOcclusion());
    }
}
