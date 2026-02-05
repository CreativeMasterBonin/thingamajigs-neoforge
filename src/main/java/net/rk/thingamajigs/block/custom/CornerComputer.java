package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class CornerComputer extends ThingamajigsDecorativeBlock{
    public static final VoxelShape SHAPE = Block.box(
            0.0D,0.0D,0.0D,16.0D,5.0D,16.0D);

    public CornerComputer(Properties properties) {
        super(properties.sound(SoundType.LANTERN).strength(0.85F));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return SHAPE;
    }
}
