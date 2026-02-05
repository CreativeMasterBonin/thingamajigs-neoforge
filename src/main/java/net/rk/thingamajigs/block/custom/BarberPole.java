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

public class BarberPole extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(5, 0, 10, 11, 1, 16),
            Block.box(5, 15, 10, 11, 16, 16),
            Block.box(6, 1, 15, 10, 15, 16),
            Block.box(6, 1, 11, 10, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, 5, 6, 1, 11),
            Block.box(0, 15, 5, 6, 16, 11),
            Block.box(0, 1, 6, 1, 15, 10),
            Block.box(1, 1, 6, 5, 15, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(5, 0, 0, 11, 1, 6),
            Block.box(5, 15, 0, 11, 16, 6),
            Block.box(6, 1, 0, 10, 15, 1),
            Block.box(6, 1, 1, 10, 15, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(10, 0, 5, 16, 1, 11),
            Block.box(10, 15, 5, 16, 16, 11),
            Block.box(15, 1, 6, 16, 15, 10),
            Block.box(11, 1, 6, 15, 15, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BarberPole(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        switch(bs.getValue(FACING)){
            case NORTH -> {
                return NORTH;
            }
            case SOUTH -> {
                return SOUTH;
            }
            case EAST -> {
                return EAST;
            }
            case WEST -> {
                return WEST;
            }
            default -> {
                return Shapes.block();
            }
        }
    }
}
