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
public class StoreShelfBlock extends ThingamajigsDecorativeBlock {
    public static final VoxelShape NORTH_SHELF = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 7, 0, 16, 8, 14),
            Block.box(0, 1, 14, 16, 31, 16),
            Block.box(0, 15, 0, 16, 16, 14),
            Block.box(0, 23, 0, 16, 24, 14),
            Block.box(0, 31, 0, 16, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHELF = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 7, 2, 16, 8, 16),
            Block.box(0, 1, 0, 16, 31, 2),
            Block.box(0, 15, 2, 16, 16, 16),
            Block.box(0, 23, 2, 16, 24, 16),
            Block.box(0, 31, 0, 16, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHELF = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(2, 7, 0, 16, 8, 16),
            Block.box(0, 1, 0, 2, 31, 16),
            Block.box(2, 15, 0, 16, 16, 16),
            Block.box(2, 23, 0, 16, 24, 16),
            Block.box(0, 31, 0, 16, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHELF = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 7, 0, 14, 8, 16),
            Block.box(14, 1, 0, 16, 31, 16),
            Block.box(0, 15, 0, 14, 16, 16),
            Block.box(0, 23, 0, 14, 24, 16),
            Block.box(0, 31, 0, 16, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public StoreShelfBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_SHELF;
            case SOUTH: return SOUTH_SHELF;
            case EAST: return EAST_SHELF;
            case WEST: return WEST_SHELF;
            default: return Shapes.block();
        }
    }
}
