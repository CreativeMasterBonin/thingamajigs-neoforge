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
public class StandingVacuum extends ThingamajigsDecorativeBlock{
    private static final VoxelShape NORTH = Stream.of(
            Block.box(2, 0, 4, 14, 2, 8),
            Block.box(4, 4, 6, 12, 20, 10),
            Block.box(4, 2, 7, 12, 4, 9),
            Block.box(5, 20, 10, 11, 22, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SOUTH = Stream.of(
            Block.box(2, 0, 9, 14, 2, 13),
            Block.box(4, 4, 7, 12, 20, 11),
            Block.box(4, 2, 8, 12, 4, 10),
            Block.box(5, 20, 5, 11, 22, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape EAST = Stream.of(
            Block.box(8.5, 0, 2.5, 12.5, 2, 14.5),
            Block.box(6.5, 4, 4.5, 10.5, 20, 12.5),
            Block.box(7.5, 2, 4.5, 9.5, 4, 12.5),
            Block.box(4.5, 20, 5.5, 6.5, 22, 11.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WEST = Stream.of(
            Block.box(3.5, 0, 2.5, 7.5, 2, 14.5),
            Block.box(5.5, 4, 4.5, 9.5, 20, 12.5),
            Block.box(6.5, 2, 4.5, 8.5, 4, 12.5),
            Block.box(9.5, 20, 5.5, 11.5, 22, 11.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public StandingVacuum(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = bs.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case EAST:
                return EAST;
            case WEST:
                return WEST;
            default:
                return DoubleTallDecorationBlock.BLOCK_SHAPE;
        }
    }
}
