package net.rk.thingamajigs.block.custom;

import net.minecraft.world.level.block.TransparentBlock;

public class ScreenBlock extends TransparentBlock {
    public ScreenBlock(Properties p) {
        super(p.noOcclusion());
    }
}
