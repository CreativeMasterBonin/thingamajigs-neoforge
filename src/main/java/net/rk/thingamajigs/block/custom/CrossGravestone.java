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
public class CrossGravestone extends ThingamajigsDecorativeBlock{
    private static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 0, 4, 16, 2, 12),
            Block.box(6, 2, 6, 10, 26, 10),
            Block.box(0, 17, 5.8, 16, 21, 8.8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(0, 0, 4, 16, 2, 12),
            Block.box(6, 2, 6, 10, 26, 10),
            Block.box(0, 17, 7.199999999999999, 16, 21, 10.2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(4, 0, 0, 12, 2, 16),
            Block.box(6, 2, 6, 10, 26, 10),
            Block.box(7.199999999999999, 17, 0, 10.2, 21, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(4, 0, 0, 12, 2, 16),
            Block.box(6, 2, 6, 10, 26, 10),
            Block.box(5.800000000000001, 17, 0, 8.8, 21, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CrossGravestone(Properties properties) {
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
