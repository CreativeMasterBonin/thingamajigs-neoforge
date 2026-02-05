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
public class SantaStatue extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_STATUE_SHAPE = Stream.of(
            Block.box(4, 2, 6, 8, 12, 10),
            Block.box(8, 2, 6, 12, 12, 10),
            Block.box(4, 12, 4, 12, 14, 12),
            Block.box(4, 14, 4, 12, 22, 12),
            Block.box(4, 22, 4, 12, 29, 12),
            Block.box(7.5, 25, 3, 8.5, 26, 4),
            Block.box(4, 29, 4, 12, 30, 12),
            Block.box(5, 30, 5, 11, 31, 11),
            Block.box(5, 31, 6, 11, 32, 12),
            Block.box(5, 30, 11, 11, 31, 13),
            Block.box(5, 29, 12, 11, 30, 14),
            Block.box(5, 27, 13, 11, 29, 15),
            Block.box(5, 25, 14, 11, 27, 16),
            Block.box(6, 23, 14, 10, 25, 16),
            Block.box(7, 21, 14, 9, 23, 16),
            Block.box(7, 19, 14, 9, 21, 16),
            Block.box(4, 0, 6, 8, 2, 10),
            Block.box(8, 0, 6, 12, 2, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_STATUE_SHAPE = Stream.of(
            Block.box(8, 2, 6, 12, 12, 10),
            Block.box(4, 2, 6, 8, 12, 10),
            Block.box(4, 12, 4, 12, 14, 12),
            Block.box(4, 14, 4, 12, 22, 12),
            Block.box(4, 22, 4, 12, 29, 12),
            Block.box(7.5, 25, 12, 8.5, 26, 13),
            Block.box(4, 29, 4, 12, 30, 12),
            Block.box(5, 30, 5, 11, 31, 11),
            Block.box(5, 31, 4, 11, 32, 10),
            Block.box(5, 30, 3, 11, 31, 5),
            Block.box(5, 29, 2, 11, 30, 4),
            Block.box(5, 27, 1, 11, 29, 3),
            Block.box(5, 25, 0, 11, 27, 2),
            Block.box(6, 23, 0, 10, 25, 2),
            Block.box(7, 21, 0, 9, 23, 2),
            Block.box(7, 19, 0, 9, 21, 2),
            Block.box(8, 0, 6, 12, 2, 10),
            Block.box(4, 0, 6, 8, 2, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_STATUE_SHAPE = Stream.of(
            Block.box(6, 2, 4, 10, 12, 8),
            Block.box(6, 2, 8, 10, 12, 12),
            Block.box(4, 12, 4, 12, 14, 12),
            Block.box(4, 14, 4, 12, 22, 12),
            Block.box(4, 22, 4, 12, 29, 12),
            Block.box(12, 25, 7.5, 13, 26, 8.5),
            Block.box(4, 29, 4, 12, 30, 12),
            Block.box(5, 30, 5, 11, 31, 11),
            Block.box(4, 31, 5, 10, 32, 11),
            Block.box(3, 30, 5, 5, 31, 11),
            Block.box(2, 29, 5, 4, 30, 11),
            Block.box(1, 27, 5, 3, 29, 11),
            Block.box(0, 25, 5, 2, 27, 11),
            Block.box(0, 23, 6, 2, 25, 10),
            Block.box(0, 21, 7, 2, 23, 9),
            Block.box(0, 19, 7, 2, 21, 9),
            Block.box(6, 0, 4, 10, 2, 8),
            Block.box(6, 0, 8, 10, 2, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_STATUE_SHAPE = Stream.of(
            Block.box(6, 2, 8, 10, 12, 12),
            Block.box(6, 2, 4, 10, 12, 8),
            Block.box(4, 12, 4, 12, 14, 12),
            Block.box(4, 14, 4, 12, 22, 12),
            Block.box(4, 22, 4, 12, 29, 12),
            Block.box(3, 25, 7.5, 4, 26, 8.5),
            Block.box(4, 29, 4, 12, 30, 12),
            Block.box(5, 30, 5, 11, 31, 11),
            Block.box(6, 31, 5, 12, 32, 11),
            Block.box(11, 30, 5, 13, 31, 11),
            Block.box(12, 29, 5, 14, 30, 11),
            Block.box(13, 27, 5, 15, 29, 11),
            Block.box(14, 25, 5, 16, 27, 11),
            Block.box(14, 23, 6, 16, 25, 10),
            Block.box(14, 21, 7, 16, 23, 9),
            Block.box(14, 19, 7, 16, 21, 9),
            Block.box(6, 0, 8, 10, 2, 12),
            Block.box(6, 0, 4, 10, 2, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public SantaStatue(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_STATUE_SHAPE;
            case SOUTH: return SOUTH_STATUE_SHAPE;
            case EAST: return EAST_STATUE_SHAPE;
            case WEST: return WEST_STATUE_SHAPE;
            default: return Shapes.block();
        }
    }
}
