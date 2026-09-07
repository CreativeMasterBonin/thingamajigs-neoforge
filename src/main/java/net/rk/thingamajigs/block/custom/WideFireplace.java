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

public class WideFireplace extends DoubleTallDecorationBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(-16, 0, 0, -8, 16, 16),
            Block.box(24, 0, 0, 32, 16, 16),
            Block.box(16, 16, 0, 32, 32, 16),
            Block.box(-16, 16, 0, 0, 32, 16),
            Block.box(0, 16, 0, 16, 32, 16),
            Block.box(-8, 0, 12, 8, 16, 16),
            Block.box(8, 0, 12, 24, 16, 16),
            Block.box(-10, 0, 0, 9, 7, 0),
            Block.box(9, 0, 0, 26, 7, 0),
            Block.box(-7, 0, 8, 23, 3, 11),
            Block.box(0, 0, 4, 16, 3, 7),
            Block.box(-12, 31, -1, -11, 32, 0),
            Block.box(0, 31, -1, 1, 32, 0),
            Block.box(12, 31, -1, 13, 32, 0),
            Block.box(24, 31, -1, 25, 32, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, -16, 16, 16, -8),
            Block.box(0, 0, 24, 16, 16, 32),
            Block.box(0, 16, 16, 16, 32, 32),
            Block.box(0, 16, -16, 16, 32, 0),
            Block.box(0, 16, 0, 16, 32, 16),
            Block.box(0, 0, -8, 4, 16, 8),
            Block.box(0, 0, 8, 4, 16, 24),
            Block.box(16, 0, -10, 16, 7, 9),
            Block.box(16, 0, 9, 16, 7, 26),
            Block.box(5, 0, -7, 8, 3, 23),
            Block.box(9, 0, 0, 12, 3, 16),
            Block.box(16, 31, -12, 17, 32, -11),
            Block.box(16, 31, 0, 17, 32, 1),
            Block.box(16, 31, 12, 17, 32, 13),
            Block.box(16, 31, 24, 17, 32, 25)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(24, 0, 0, 32, 16, 16),
            Block.box(-16, 0, 0, -8, 16, 16),
            Block.box(-16, 16, 0, 0, 32, 16),
            Block.box(16, 16, 0, 32, 32, 16),
            Block.box(0, 16, 0, 16, 32, 16),
            Block.box(8, 0, 0, 24, 16, 4),
            Block.box(-8, 0, 0, 8, 16, 4),
            Block.box(7, 0, 16, 26, 7, 16),
            Block.box(-10, 0, 16, 7, 7, 16),
            Block.box(-7, 0, 5, 23, 3, 8),
            Block.box(0, 0, 9, 16, 3, 12),
            Block.box(27, 31, 16, 28, 32, 17),
            Block.box(15, 31, 16, 16, 32, 17),
            Block.box(3, 31, 16, 4, 32, 17),
            Block.box(-9, 31, 16, -8, 32, 17)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 24, 16, 16, 32),
            Block.box(0, 0, -16, 16, 16, -8),
            Block.box(0, 16, -16, 16, 32, 0),
            Block.box(0, 16, 16, 16, 32, 32),
            Block.box(0, 16, 0, 16, 32, 16),
            Block.box(12, 0, 8, 16, 16, 24),
            Block.box(12, 0, -8, 16, 16, 8),
            Block.box(0, 0, 7, 0, 7, 26),
            Block.box(0, 0, -10, 0, 7, 7),
            Block.box(8, 0, -7, 11, 3, 23),
            Block.box(4, 0, 0, 7, 3, 16),
            Block.box(-1, 31, 27, 0, 32, 28),
            Block.box(-1, 31, 15, 0, 32, 16),
            Block.box(-1, 31, 3, 0, 32, 4),
            Block.box(-1, 31, -9, 0, 32, -8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public WideFireplace(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return BLOCK_SHAPE;}
        }
    }
}
