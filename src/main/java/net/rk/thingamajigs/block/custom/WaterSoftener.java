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

public class WaterSoftener extends DoubleTallDecorationBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(3, 0, 3, 13, 18, 13),
            Block.box(3, 18, 2, 13, 24, 9),
            Block.box(11.5, 22, 1.5, 12.5, 23, 2.5),
            Block.box(11.5, 20, 1.5, 12.5, 21, 2.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(3, 0, 3, 13, 18, 13),
            Block.box(7, 18, 3, 14, 24, 13),
            Block.box(13.5, 22, 11.5, 14.5, 23, 12.5),
            Block.box(13.5, 20, 11.5, 14.5, 21, 12.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(3, 0, 3, 13, 18, 13),
            Block.box(3, 18, 7, 13, 24, 14),
            Block.box(3.5, 22, 13.5, 4.5, 23, 14.5),
            Block.box(3.5, 20, 13.5, 4.5, 21, 14.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(3, 0, 3, 13, 18, 13),
            Block.box(2, 18, 3, 9, 24, 13),
            Block.box(1.5, 22, 3.5, 2.5, 23, 4.5),
            Block.box(1.5, 20, 3.5, 2.5, 21, 4.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public WaterSoftener(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
