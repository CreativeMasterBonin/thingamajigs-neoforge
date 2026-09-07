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

public class LightedDeer extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(4, 8, 1, 12, 15, 15),
            Block.box(5, 19, -5, 6, 20, -3),
            Block.box(10, 19, -5, 11, 20, -3),
            Block.box(7, 17, -11, 9, 19, -10),
            Block.box(7, 10, 2, 9, 12, 14),
            Block.box(6, 16, -10, 10, 20, -2),
            Block.box(5, 9, 2, 11, 11, 4),
            Block.box(5, 9, 12, 11, 11, 14),
            Block.box(6, 9, 5, 10, 13, 11),
            Block.box(1, 0, 2, 4, 9, 4),
            Block.box(12, 0, 2, 15, 9, 4),
            Block.box(1, 0, 12, 4, 9, 14),
            Block.box(12, 0, 12, 15, 9, 14),
            Block.box(6, 10, -2, 10, 18, 1),
            Block.box(6, 12, -4, 10, 16, -2),
            Block.box(6, 9, 15, 10, 14, 18)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(1, 8, 4, 15, 15, 12),
            Block.box(19, 19, 5, 21, 20, 6),
            Block.box(19, 19, 10, 21, 20, 11),
            Block.box(26, 17, 7, 27, 19, 9),
            Block.box(2, 10, 7, 14, 12, 9),
            Block.box(18, 16, 6, 26, 20, 10),
            Block.box(12, 9, 5, 14, 11, 11),
            Block.box(2, 9, 5, 4, 11, 11),
            Block.box(5, 9, 6, 11, 13, 10),
            Block.box(12, 0, 1, 14, 9, 4),
            Block.box(12, 0, 12, 14, 9, 15),
            Block.box(2, 0, 1, 4, 9, 4),
            Block.box(2, 0, 12, 4, 9, 15),
            Block.box(15, 10, 6, 18, 18, 10),
            Block.box(18, 12, 6, 20, 16, 10),
            Block.box(-2, 9, 6, 1, 14, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(4, 8, 1, 12, 15, 15),
            Block.box(10, 19, 19, 11, 20, 21),
            Block.box(5, 19, 19, 6, 20, 21),
            Block.box(7, 17, 26, 9, 19, 27),
            Block.box(7, 10, 2, 9, 12, 14),
            Block.box(6, 16, 18, 10, 20, 26),
            Block.box(5, 9, 12, 11, 11, 14),
            Block.box(5, 9, 2, 11, 11, 4),
            Block.box(6, 9, 5, 10, 13, 11),
            Block.box(12, 0, 12, 15, 9, 14),
            Block.box(1, 0, 12, 4, 9, 14),
            Block.box(12, 0, 2, 15, 9, 4),
            Block.box(1, 0, 2, 4, 9, 4),
            Block.box(6, 10, 15, 10, 18, 18),
            Block.box(6, 12, 18, 10, 16, 20),
            Block.box(6, 9, -2, 10, 14, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(1, 8, 4, 15, 15, 12),
            Block.box(-5, 19, 10, -3, 20, 11),
            Block.box(-5, 19, 5, -3, 20, 6),
            Block.box(-11, 17, 7, -10, 19, 9),
            Block.box(2, 10, 7, 14, 12, 9),
            Block.box(-10, 16, 6, -2, 20, 10),
            Block.box(2, 9, 5, 4, 11, 11),
            Block.box(12, 9, 5, 14, 11, 11),
            Block.box(5, 9, 6, 11, 13, 10),
            Block.box(2, 0, 12, 4, 9, 15),
            Block.box(2, 0, 1, 4, 9, 4),
            Block.box(12, 0, 12, 14, 9, 15),
            Block.box(12, 0, 1, 14, 9, 4),
            Block.box(-2, 10, 6, 1, 18, 10),
            Block.box(-4, 12, 6, -2, 16, 10),
            Block.box(15, 9, 6, 18, 14, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public LightedDeer(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
