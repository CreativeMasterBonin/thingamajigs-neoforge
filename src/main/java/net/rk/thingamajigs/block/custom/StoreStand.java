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

public class StoreStand extends DoubleTallDecorationBlock{
    public static final VoxelShape NORTHSOUTH = Stream.of(
            Block.box(8, 11, 1, 16, 13, 15),
            Block.box(0, 0, 0, 2, 2, 2),
            Block.box(0, 0, 14, 2, 2, 16),
            Block.box(14, 0, 0, 16, 2, 2),
            Block.box(14, 0, 14, 16, 2, 16),
            Block.box(0, 2, 0, 16, 3, 16),
            Block.box(7, 3, 7, 9, 32, 9),
            Block.box(0, 25, 1, 8, 27, 15),
            Block.box(0, 18, 1, 8, 20, 15),
            Block.box(0, 11, 1, 8, 13, 15),
            Block.box(8, 25, 1, 16, 27, 15),
            Block.box(8, 18, 1, 16, 20, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST = Stream.of(
            Block.box(1, 11, 8, 15, 13, 16),
            Block.box(14, 0, 0, 16, 2, 2),
            Block.box(0, 0, 0, 2, 2, 2),
            Block.box(14, 0, 14, 16, 2, 16),
            Block.box(0, 0, 14, 2, 2, 16),
            Block.box(0, 2, 0, 16, 3, 16),
            Block.box(7, 3, 7, 9, 32, 9),
            Block.box(1, 25, 0, 15, 27, 8),
            Block.box(1, 18, 0, 15, 20, 8),
            Block.box(1, 11, 0, 15, 13, 8),
            Block.box(1, 25, 8, 15, 27, 16),
            Block.box(1, 18, 8, 15, 20, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH,SOUTH -> {return NORTHSOUTH;}
            case EAST,WEST -> {return NORTHSOUTH;}
            default -> {return BLOCK_SHAPE;}
        }
    }

    public StoreStand(Properties properties) {
        super(properties);
    }
}
