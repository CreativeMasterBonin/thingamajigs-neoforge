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

public class GasHeater extends DoubleTallDecorationBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 0, 0, 16, 28, 16),
            Block.box(6, 28, 6, 10, 32, 16),
            Block.box(5, 28, 7, 6, 29, 15),
            Block.box(10, 28, 7, 11, 29, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, 0, 16, 28, 16),
            Block.box(0, 28, 6, 10, 32, 10),
            Block.box(1, 28, 5, 9, 29, 6),
            Block.box(1, 28, 10, 9, 29, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 0, 0, 16, 28, 16),
            Block.box(6, 28, 0, 10, 32, 10),
            Block.box(10, 28, 1, 11, 29, 9),
            Block.box(5, 28, 1, 6, 29, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 0, 16, 28, 16),
            Block.box(6, 28, 6, 16, 32, 10),
            Block.box(7, 28, 10, 15, 29, 11),
            Block.box(7, 28, 5, 15, 29, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return DoubleTallDecorationBlock.BLOCK_SHAPE;}
        }
    }
    public GasHeater(Properties properties) {
        super(properties);
    }
}
