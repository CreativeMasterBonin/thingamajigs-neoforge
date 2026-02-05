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
public class HorizontalTrafficSignal extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 7, 7, 16, 9, 9),
            Block.box(1, 6, 5, 3, 11, 7),
            Block.box(13, 6, 5, 15, 11, 7),
            Block.box(0, 6, 0, 16, 11, 5),
            Block.box(1, 10.5, -2, 5, 11.5, 0),
            Block.box(6, 10.5, -2, 10, 11.5, 0),
            Block.box(11, 10.5, -2, 15, 11.5, 0),
            Block.box(0, 8.5, -2, 1, 10.5, 0),
            Block.box(5, 8.5, -2, 6, 10.5, 0),
            Block.box(10, 8.5, -2, 11, 10.5, 0),
            Block.box(15, 8.5, -2, 16, 10.5, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(0, 7, 7, 16, 9, 9),
            Block.box(13, 6, 9, 15, 11, 11),
            Block.box(1, 6, 9, 3, 11, 11),
            Block.box(0, 6, 11, 16, 11, 16),
            Block.box(11, 10.5, 16, 15, 11.5, 18),
            Block.box(6, 10.5, 16, 10, 11.5, 18),
            Block.box(1, 10.5, 16, 5, 11.5, 18),
            Block.box(15, 8.5, 16, 16, 10.5, 18),
            Block.box(10, 8.5, 16, 11, 10.5, 18),
            Block.box(5, 8.5, 16, 6, 10.5, 18),
            Block.box(0, 8.5, 16, 1, 10.5, 18)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(7, 7, 0, 9, 9, 16),
            Block.box(9, 6, 1, 11, 11, 3),
            Block.box(9, 6, 13, 11, 11, 15),
            Block.box(11, 6, 0, 16, 11, 16),
            Block.box(16, 10.5, 1, 18, 11.5, 5),
            Block.box(16, 10.5, 6, 18, 11.5, 10),
            Block.box(16, 10.5, 11, 18, 11.5, 15),
            Block.box(16, 8.5, 0, 18, 10.5, 1),
            Block.box(16, 8.5, 5, 18, 10.5, 6),
            Block.box(16, 8.5, 10, 18, 10.5, 11),
            Block.box(16, 8.5, 15, 18, 10.5, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(7, 7, 0, 9, 9, 16),
            Block.box(5, 6, 13, 7, 11, 15),
            Block.box(5, 6, 1, 7, 11, 3),
            Block.box(0, 6, 0, 5, 11, 16),
            Block.box(-2, 10.5, 11, 0, 11.5, 15),
            Block.box(-2, 10.5, 6, 0, 11.5, 10),
            Block.box(-2, 10.5, 1, 0, 11.5, 5),
            Block.box(-2, 8.5, 15, 0, 10.5, 16),
            Block.box(-2, 8.5, 10, 0, 10.5, 11),
            Block.box(-2, 8.5, 5, 0, 10.5, 6),
            Block.box(-2, 8.5, 0, 0, 10.5, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public HorizontalTrafficSignal(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = bs.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
        }
        return NORTH_SHAPE;
    }
}
