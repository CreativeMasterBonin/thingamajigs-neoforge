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
public class DoorBlockade extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(5, 7, 14, 11, 21, 16),
            Block.box(11, 21, 14, 16, 31, 16),
            Block.box(0, 21, 14, 5, 31, 16),
            Block.box(0, 0, 14, 5, 10, 16),
            Block.box(11, 0, 14, 16, 10, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 7, 5, 2, 21, 11),
            Block.box(0, 21, 11, 2, 31, 16),
            Block.box(0, 21, 0, 2, 31, 5),
            Block.box(0, 0, 0, 2, 10, 5),
            Block.box(0, 0, 11, 2, 10, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(5, 7, 0, 11, 21, 2),
            Block.box(0, 21, 0, 5, 31, 2),
            Block.box(11, 21, 0, 16, 31, 2),
            Block.box(11, 0, 0, 16, 10, 2),
            Block.box(0, 0, 0, 5, 10, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(14, 7, 5, 16, 21, 11),
            Block.box(14, 21, 0, 16, 31, 5),
            Block.box(14, 21, 11, 16, 31, 16),
            Block.box(14, 0, 11, 16, 10, 16),
            Block.box(14, 0, 0, 16, 10, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    // window blockade shapes
    public static final VoxelShape WINDOW_N = Block.box(0, 0, 14, 16, 12, 16);
    public static final VoxelShape WINDOW_E = Block.box(0, 0, 0, 2, 12, 16);
    public static final VoxelShape WINDOW_S = Block.box(0, 0, 0, 16, 12, 2);
    public static final VoxelShape WINDOW_W = Block.box(14, 0, 0, 16, 12, 16);


    public DoorBlockade(Properties properties) {
        super(properties.strength(2f,15f).sound(SoundType.WOOD));
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
                return Shapes.block();
        }
    }
}
