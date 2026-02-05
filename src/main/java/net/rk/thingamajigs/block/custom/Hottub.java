package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class Hottub extends ThingamajigsDecorativeBlock{
    private static final VoxelShape ALL_SHAPE = Stream.of(
            Block.box(-8, 0, -8, 24, 1, 24),
            Block.box(-3, 1, -6, 19, 17, -5),
            Block.box(-3, 1, 21, 19, 17, 22),
            Block.box(21, 1, -3, 22, 17, 19),
            Block.box(-6, 1, -3, -5, 17, 19),
            Block.box(-5, 1, -5, -3, 17, -3),
            Block.box(19, 1, -5, 21, 17, -3),
            Block.box(19, 1, 19, 21, 17, 21),
            Block.box(-5, 1, 19, -3, 17, 21)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Hottub(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL_SHAPE;
    }
}
