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
public class SmallRoadBarrier extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 0, 7, 1, 1, 10),
            Block.box(15, 0, 7, 16, 1, 10),
            Block.box(15, 1, 8, 16, 21, 9),
            Block.box(0, 1, 8, 1, 21, 9),
            Block.box(-2, 14, 7, 18, 18, 8),
            Block.box(-2, 4, 7, 18, 8, 8),
            Block.box(-2, 16, 8, 18, 17, 9),
            Block.box(-2, 5, 8, 18, 6, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(15, 0, 6, 16, 1, 9),
            Block.box(0, 0, 6, 1, 1, 9),
            Block.box(0, 1, 7, 1, 21, 8),
            Block.box(15, 1, 7, 16, 21, 8),
            Block.box(-2, 14, 8, 18, 18, 9),
            Block.box(-2, 4, 8, 18, 8, 9),
            Block.box(-2, 16, 7, 18, 17, 8),
            Block.box(-2, 5, 7, 18, 6, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(6, 0, 0, 9, 1, 1),
            Block.box(6, 0, 15, 9, 1, 16),
            Block.box(7, 1, 15, 8, 21, 16),
            Block.box(7, 1, 0, 8, 21, 1),
            Block.box(8, 14, -2, 9, 18, 18),
            Block.box(8, 4, -2, 9, 8, 18),
            Block.box(7, 16, -2, 8, 17, 18),
            Block.box(7, 5, -2, 8, 6, 18)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(7, 0, 15, 10, 1, 16),
            Block.box(7, 0, 0, 10, 1, 1),
            Block.box(8, 1, 0, 9, 21, 1),
            Block.box(8, 1, 15, 9, 21, 16),
            Block.box(7, 14, -2, 8, 18, 18),
            Block.box(7, 4, -2, 8, 8, 18),
            Block.box(8, 16, -2, 9, 17, 18),
            Block.box(8, 5, -2, 9, 6, 18)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public SmallRoadBarrier(Properties properties) {
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
