package net.rk.thingamajigs.block.custom;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

@SuppressWarnings("deprecated")
public class CustomWaterlilyBlock extends WaterlilyBlock{
    public CustomWaterlilyBlock(Properties p) {
        super(p.pushReaction(PushReaction.DESTROY)
                .mapColor(MapColor.PLANT)
                .instabreak()
                .sound(SoundType.LILY_PAD)
                .noOcclusion());
    }
}
