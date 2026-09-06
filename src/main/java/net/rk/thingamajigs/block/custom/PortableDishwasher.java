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
public class PortableDishwasher extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(1, 0, 0, 15, 16, 1),
            Block.box(1, 0, 1, 14, 16, 15),
            Block.box(14, 0, 1, 15, 16, 15),
            Block.box(2, 0, 14, 14, 16, 15),
            Block.box(2, 0, 1, 14, 1, 14),
            Block.box(2, 15, 1, 14, 16, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(15, 0, 1, 16, 16, 15),
            Block.box(1, 0, 1, 15, 16, 14),
            Block.box(1, 0, 14, 15, 16, 15),
            Block.box(1, 0, 2, 2, 16, 14),
            Block.box(2, 0, 2, 15, 1, 14),
            Block.box(2, 15, 2, 15, 16, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(1, 0, 15, 15, 16, 16),
            Block.box(2, 0, 1, 15, 16, 15),
            Block.box(1, 0, 1, 2, 16, 15),
            Block.box(2, 0, 1, 14, 16, 2),
            Block.box(2, 0, 2, 14, 1, 15),
            Block.box(2, 15, 2, 14, 16, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 1, 1, 16, 15),
            Block.box(1, 0, 2, 15, 16, 15),
            Block.box(1, 0, 1, 15, 16, 2),
            Block.box(14, 0, 2, 15, 16, 14),
            Block.box(1, 0, 2, 14, 1, 14),
            Block.box(1, 15, 2, 14, 16, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public PortableDishwasher(Properties properties) {
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
