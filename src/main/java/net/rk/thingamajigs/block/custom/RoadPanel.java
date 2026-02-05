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
public class RoadPanel extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 1, 7, 9, 21, 9),
            Block.box(6, 6, 6, 10, 20, 7),
            Block.box(6, 18, 7, 10, 19, 8),
            Block.box(6, 7, 7, 10, 8, 8),
            Block.box(6, 21, 7, 10, 22, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 1, 7, 9, 21, 9),
            Block.box(6, 6, 9, 10, 20, 10),
            Block.box(6, 18, 8, 10, 19, 9),
            Block.box(6, 7, 8, 10, 8, 9),
            Block.box(6, 21, 7, 10, 22, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 1, 7, 9, 21, 9),
            Block.box(9, 6, 6, 10, 20, 10),
            Block.box(8, 18, 6, 9, 19, 10),
            Block.box(8, 7, 6, 9, 8, 10),
            Block.box(7, 21, 6, 9, 22, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 1, 7, 9, 21, 9),
            Block.box(6, 6, 6, 7, 20, 10),
            Block.box(7, 18, 6, 8, 19, 10),
            Block.box(7, 7, 6, 8, 8, 10),
            Block.box(7, 21, 6, 9, 22, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public RoadPanel(Properties properties) {
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
