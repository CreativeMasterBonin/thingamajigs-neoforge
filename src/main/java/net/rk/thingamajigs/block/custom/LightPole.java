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

public class LightPole extends Pole{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(7, 15, -1, 9, 16, 7),
            Block.box(6, 13, -6, 10, 15, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(9, 15, 7, 17, 16, 9),
            Block.box(16, 13, 6, 22, 15, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(7, 15, 9, 9, 16, 17),
            Block.box(6, 13, 16, 10, 15, 22)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(-1, 15, 7, 7, 16, 9),
            Block.box(-6, 13, 6, 0, 15, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

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
    public LightPole(Properties properties) {
        super(properties);
    }
}
