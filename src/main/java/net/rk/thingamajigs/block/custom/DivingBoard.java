package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class DivingBoard extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 4, 9),
            Block.box(3, 4, 0, 13, 6, 32),
            Block.box(2, 5, 0, 3, 11, 2),
            Block.box(13, 5, 0, 14, 11, 2),
            Block.box(13, 5, 9, 14, 11, 11),
            Block.box(2, 5, 9, 3, 11, 11),
            Block.box(2, 10, 2, 3, 12, 9),
            Block.box(13, 10, 2, 14, 12, 9),
            Block.box(2, 0, -2, 3, 7, 0),
            Block.box(13, 0, -2, 14, 7, 0),
            Block.box(3, 0, -2, 13, 1, 0),
            Block.box(3, 4, -2, 13, 5, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 4, 9),
            Block.box(3, 4, -16, 13, 6, 16),
            Block.box(13, 5, 14, 14, 11, 16),
            Block.box(2, 5, 14, 3, 11, 16),
            Block.box(2, 5, 5, 3, 11, 7),
            Block.box(13, 5, 5, 14, 11, 7),
            Block.box(13, 10, 7, 14, 12, 14),
            Block.box(2, 10, 7, 3, 12, 14),
            Block.box(13, 0, 16, 14, 7, 18),
            Block.box(2, 0, 16, 3, 7, 18),
            Block.box(3, 0, 16, 13, 1, 18),
            Block.box(3, 4, 16, 13, 5, 18)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 4, 9),
            Block.box(0, 4, 3, 32, 6, 13),
            Block.box(0, 5, 13, 2, 11, 14),
            Block.box(0, 5, 2, 2, 11, 3),
            Block.box(9, 5, 2, 11, 11, 3),
            Block.box(9, 5, 13, 11, 11, 14),
            Block.box(2, 10, 13, 9, 12, 14),
            Block.box(2, 10, 2, 9, 12, 3),
            Block.box(-2, 0, 13, 0, 7, 14),
            Block.box(-2, 0, 2, 0, 7, 3),
            Block.box(-2, 0, 3, 0, 1, 13),
            Block.box(-2, 4, 3, 0, 5, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 4, 9),
            Block.box(-16, 4, 3, 16, 6, 13),
            Block.box(14, 5, 2, 16, 11, 3),
            Block.box(14, 5, 13, 16, 11, 14),
            Block.box(5, 5, 13, 7, 11, 14),
            Block.box(5, 5, 2, 7, 11, 3),
            Block.box(7, 10, 2, 14, 12, 3),
            Block.box(7, 10, 13, 14, 12, 14),
            Block.box(16, 0, 2, 18, 7, 3),
            Block.box(16, 0, 13, 18, 7, 14),
            Block.box(16, 0, 3, 18, 1, 13),
            Block.box(16, 4, 3, 18, 5, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public DivingBoard(Properties properties) {
        super(properties.strength(1F,2F).sound(SoundType.SCAFFOLDING));
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
                return WEST;
            case WEST:
                return EAST;
            default:
                return Shapes.block();
        }
    }
}
