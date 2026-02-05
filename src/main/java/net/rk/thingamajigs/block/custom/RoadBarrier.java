package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class RoadBarrier extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(-3, 0, 7, -2, 1, 10),
            Block.box(18, 0, 7, 19, 1, 10),
            Block.box(18, 1, 8, 19, 21, 9),
            Block.box(-3, 1, 8, -2, 21, 9),
            Block.box(-8, 15, 7, 24, 19, 8),
            Block.box(-8, 9, 7, 24, 13, 8),
            Block.box(-8, 3, 7, 24, 7, 8),
            Block.box(-2, 11, 8, 18, 12, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(18, 0, 6, 19, 1, 9),
            Block.box(-3, 0, 6, -2, 1, 9),
            Block.box(-3, 1, 7, -2, 21, 8),
            Block.box(18, 1, 7, 19, 21, 8),
            Block.box(-8, 15, 8, 24, 19, 9),
            Block.box(-8, 9, 8, 24, 13, 9),
            Block.box(-8, 3, 8, 24, 7, 9),
            Block.box(-2, 11, 7, 18, 12, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(6, 0, -3, 9, 1, -2),
            Block.box(6, 0, 18, 9, 1, 19),
            Block.box(7, 1, 18, 8, 21, 19),
            Block.box(7, 1, -3, 8, 21, -2),
            Block.box(8, 15, -8, 9, 19, 24),
            Block.box(8, 9, -8, 9, 13, 24),
            Block.box(8, 3, -8, 9, 7, 24),
            Block.box(7, 11, -2, 8, 12, 18)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(7, 0, 18, 10, 1, 19),
            Block.box(7, 0, -3, 10, 1, -2),
            Block.box(8, 1, -3, 9, 21, -2),
            Block.box(8, 1, 18, 9, 21, 19),
            Block.box(7, 15, -8, 8, 19, 24),
            Block.box(7, 9, -8, 8, 13, 24),
            Block.box(7, 3, -8, 8, 7, 24),
            Block.box(8, 11, -2, 9, 12, 18)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public RoadBarrier(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_SHAPE;
            case SOUTH: return SOUTH_SHAPE;
            case EAST: return EAST_SHAPE;
            case WEST: return WEST_SHAPE;
            default: return Shapes.block();
        }
    }
}
