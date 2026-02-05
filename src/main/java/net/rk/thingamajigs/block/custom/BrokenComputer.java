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
public class BrokenComputer extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_S = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(3, 5, 7, 13, 6, 14),
            Block.box(5, 6, 8, 11, 7, 11),
            Block.box(7, 7, 11, 9, 8, 13),
            Block.box(0, 8, 2, 16, 24, 14),
            Block.box(1, 9, 14, 15, 23, 16),
            Block.box(1, 4, 1, 15, 5, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_S = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(3, 5, 2, 13, 6, 9),
            Block.box(5, 6, 5, 11, 7, 8),
            Block.box(7, 7, 3, 9, 8, 5),
            Block.box(0, 8, 2, 16, 24, 14),
            Block.box(1, 9, 0, 15, 23, 2),
            Block.box(1, 4, 1, 15, 5, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_S = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(2, 5, 3, 9, 6, 13),
            Block.box(5, 6, 5, 8, 7, 11),
            Block.box(3, 7, 7, 5, 8, 9),
            Block.box(2, 8, 0, 14, 24, 16),
            Block.box(0, 9, 1, 2, 23, 15),
            Block.box(1, 4, 1, 15, 5, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_S = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(7, 5, 3, 14, 6, 13),
            Block.box(8, 6, 5, 11, 7, 11),
            Block.box(11, 7, 7, 13, 8, 9),
            Block.box(2, 8, 0, 14, 24, 16),
            Block.box(14, 9, 1, 16, 23, 15),
            Block.box(1, 4, 1, 15, 5, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BrokenComputer(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_S;
            case SOUTH: return SOUTH_S;
            case EAST: return EAST_S;
            case WEST: return WEST_S;
            default: return Shapes.block();
        }
    }
}
