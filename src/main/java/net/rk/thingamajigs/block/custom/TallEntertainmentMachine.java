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
import net.rk.thingamajigs.block.TBlocks;

import java.util.stream.Stream;

public class TallEntertainmentMachine extends DoubleTallDecorationBlock{
    public static final VoxelShape NORTH_HAMMER_MACHINE= Stream.of(
            Block.box(0, 0, 15, 16, 32, 16),
            Block.box(0, 4, 0, 16, 5, 15),
            Block.box(0, 0, 0, 1, 4, 1),
            Block.box(15, 0, 0, 16, 4, 1),
            Block.box(1, 2, 0, 4, 4, 1),
            Block.box(4, 5, 4, 12, 6, 12),
            Block.box(5, 6, 5, 11, 7, 11),
            Block.box(1, 7, 0, 2, 8, 10),
            Block.box(0, 5, 10, 3, 10, 12),
            Block.box(4, 8, 14, 12, 9, 15),
            Block.box(4, 11, 14, 12, 12, 15),
            Block.box(4, 14, 14, 12, 15, 15),
            Block.box(4, 17, 14, 12, 18, 15),
            Block.box(4, 20, 14, 12, 21, 15),
            Block.box(4, 23, 14, 12, 24, 15),
            Block.box(4, 26, 14, 12, 27, 15),
            Block.box(3, 29, 13, 13, 31, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_HAMMER_MACHINE=Stream.of(
            Block.box(0, 0, 0, 1, 32, 16),
            Block.box(1, 4, 0, 16, 5, 16),
            Block.box(15, 0, 0, 16, 4, 1),
            Block.box(15, 0, 15, 16, 4, 16),
            Block.box(15, 2, 1, 16, 4, 4),
            Block.box(4, 5, 4, 12, 6, 12),
            Block.box(5, 6, 5, 11, 7, 11),
            Block.box(6, 7, 1, 16, 8, 2),
            Block.box(4, 5, 0, 6, 10, 3),
            Block.box(1, 8, 4, 2, 9, 12),
            Block.box(1, 11, 4, 2, 12, 12),
            Block.box(1, 14, 4, 2, 15, 12),
            Block.box(1, 17, 4, 2, 18, 12),
            Block.box(1, 20, 4, 2, 21, 12),
            Block.box(1, 23, 4, 2, 24, 12),
            Block.box(1, 26, 4, 2, 27, 12),
            Block.box(1, 29, 3, 3, 31, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_HAMMER_MACHINE=Stream.of(
            Block.box(0, 0, 0, 16, 32, 1),
            Block.box(0, 4, 1, 16, 5, 16),
            Block.box(15, 0, 15, 16, 4, 16),
            Block.box(0, 0, 15, 1, 4, 16),
            Block.box(12, 2, 15, 15, 4, 16),
            Block.box(4, 5, 4, 12, 6, 12),
            Block.box(5, 6, 5, 11, 7, 11),
            Block.box(14, 7, 6, 15, 8, 16),
            Block.box(13, 5, 4, 16, 10, 6),
            Block.box(4, 8, 1, 12, 9, 2),
            Block.box(4, 11, 1, 12, 12, 2),
            Block.box(4, 14, 1, 12, 15, 2),
            Block.box(4, 17, 1, 12, 18, 2),
            Block.box(4, 20, 1, 12, 21, 2),
            Block.box(4, 23, 1, 12, 24, 2),
            Block.box(4, 26, 1, 12, 27, 2),
            Block.box(3, 29, 1, 13, 31, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_HAMMER_MACHINE=Stream.of(
            Block.box(15, 0, 0, 16, 32, 16),
            Block.box(0, 4, 0, 15, 5, 16),
            Block.box(0, 0, 15, 1, 4, 16),
            Block.box(0, 0, 0, 1, 4, 1),
            Block.box(0, 2, 12, 1, 4, 15),
            Block.box(4, 5, 4, 12, 6, 12),
            Block.box(5, 6, 5, 11, 7, 11),
            Block.box(0, 7, 14, 10, 8, 15),
            Block.box(10, 5, 13, 12, 10, 16),
            Block.box(14, 8, 4, 15, 9, 12),
            Block.box(14, 11, 4, 15, 12, 12),
            Block.box(14, 14, 4, 15, 15, 12),
            Block.box(14, 17, 4, 15, 18, 12),
            Block.box(14, 20, 4, 15, 21, 12),
            Block.box(14, 23, 4, 15, 24, 12),
            Block.box(14, 26, 4, 15, 27, 12),
            Block.box(13, 29, 3, 15, 31, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTH_WACK_MACHINE=Stream.of(
            Block.box(0, 0, 0, 1, 8, 1),
            Block.box(15, 0, 0, 16, 8, 1),
            Block.box(15, 0, 15, 16, 8, 16),
            Block.box(0, 0, 15, 1, 8, 16),
            Block.box(0, 8, 0, 16, 9, 16),
            Block.box(0, 9, 15, 15, 13, 16),
            Block.box(15, 9, 1, 16, 13, 16),
            Block.box(1, 9, 0, 16, 13, 1),
            Block.box(0, 9, 0, 1, 13, 15),
            Block.box(1, 12, 1, 15, 13, 3),
            Block.box(1, 12, 7, 15, 13, 9),
            Block.box(1, 12, 13, 15, 13, 15),
            Block.box(0, 13, 14, 2, 32, 16),
            Block.box(14, 13, 14, 16, 32, 16),
            Block.box(2, 30, 14, 14, 32, 16),
            Block.box(1, 9, -1, 5, 12, 0),
            Block.box(10, 13, 0.5, 12, 18, 2.5),
            Block.box(3, 15, 1, 10, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_WACK_MACHINE=Stream.of(
            Block.box(15, 0, 0, 16, 8, 1),
            Block.box(15, 0, 15, 16, 8, 16),
            Block.box(0, 0, 15, 1, 8, 16),
            Block.box(0, 0, 0, 1, 8, 1),
            Block.box(0, 8, 0, 16, 9, 16),
            Block.box(0, 9, 0, 1, 13, 15),
            Block.box(0, 9, 15, 15, 13, 16),
            Block.box(15, 9, 1, 16, 13, 16),
            Block.box(1, 9, 0, 16, 13, 1),
            Block.box(13, 12, 1, 15, 13, 15),
            Block.box(7, 12, 1, 9, 13, 15),
            Block.box(1, 12, 1, 3, 13, 15),
            Block.box(0, 13, 0, 2, 32, 2),
            Block.box(0, 13, 14, 2, 32, 16),
            Block.box(0, 30, 2, 2, 32, 14),
            Block.box(16, 9, 1, 17, 12, 5),
            Block.box(13.5, 13, 10, 15.5, 18, 12),
            Block.box(14, 15, 3, 15, 16, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_WACK_MACHINE=Stream.of(
            Block.box(15, 0, 15, 16, 8, 16),
            Block.box(0, 0, 15, 1, 8, 16),
            Block.box(0, 0, 0, 1, 8, 1),
            Block.box(15, 0, 0, 16, 8, 1),
            Block.box(0, 8, 0, 16, 9, 16),
            Block.box(1, 9, 0, 16, 13, 1),
            Block.box(0, 9, 0, 1, 13, 15),
            Block.box(0, 9, 15, 15, 13, 16),
            Block.box(15, 9, 1, 16, 13, 16),
            Block.box(1, 12, 13, 15, 13, 15),
            Block.box(1, 12, 7, 15, 13, 9),
            Block.box(1, 12, 1, 15, 13, 3),
            Block.box(14, 13, 0, 16, 32, 2),
            Block.box(0, 13, 0, 2, 32, 2),
            Block.box(2, 30, 0, 14, 32, 2),
            Block.box(11, 9, 16, 15, 12, 17),
            Block.box(4, 13, 13.5, 6, 18, 15.5),
            Block.box(6, 15, 14, 13, 16, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_WACK_MACHINE=Stream.of(
            Block.box(0, 0, 15, 1, 8, 16),
            Block.box(0, 0, 0, 1, 8, 1),
            Block.box(15, 0, 0, 16, 8, 1),
            Block.box(15, 0, 15, 16, 8, 16),
            Block.box(0, 8, 0, 16, 9, 16),
            Block.box(15, 9, 1, 16, 13, 16),
            Block.box(1, 9, 0, 16, 13, 1),
            Block.box(0, 9, 0, 1, 13, 15),
            Block.box(0, 9, 15, 15, 13, 16),
            Block.box(1, 12, 1, 3, 13, 15),
            Block.box(7, 12, 1, 9, 13, 15),
            Block.box(13, 12, 1, 15, 13, 15),
            Block.box(14, 13, 14, 16, 32, 16),
            Block.box(14, 13, 0, 16, 32, 2),
            Block.box(14, 30, 2, 16, 32, 14),
            Block.box(-1, 9, 11, 0, 12, 15),
            Block.box(0.5, 13, 4, 2.5, 18, 6),
            Block.box(1, 15, 6, 2, 16, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTH_GUMBALL_MACHINE=Stream.of(
            Block.box(2, 19, 2, 14, 31, 14),
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(5, 3, 5, 11, 14, 11),
            Block.box(5, 14, 5, 11, 19, 11),
            Block.box(4, 5, 1, 12, 15, 5),
            Block.box(4, 11, -1, 5, 12, 1),
            Block.box(3, 11, -2, 6, 12, -1),
            Block.box(4, 13, -1, 12, 14, 1),
            Block.box(6, 6, 0.95, 10, 12.5, 0.95)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_GUMBALL_MACHINE=Stream.of(
            Block.box(2, 19, 2, 14, 31, 14),
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(5, 3, 5, 11, 14, 11),
            Block.box(5, 14, 5, 11, 19, 11),
            Block.box(11, 5, 4, 15, 15, 12),
            Block.box(15, 11, 4, 17, 12, 5),
            Block.box(17, 11, 3, 18, 12, 6),
            Block.box(15, 13, 4, 17, 14, 12),
            Block.box(15.05, 6, 6, 15.05, 12.5, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_GUMBALL_MACHINE=Stream.of(
            Block.box(2, 19, 2, 14, 31, 14),
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(5, 3, 5, 11, 14, 11),
            Block.box(5, 14, 5, 11, 19, 11),
            Block.box(4, 5, 11, 12, 15, 15),
            Block.box(11, 11, 15, 12, 12, 17),
            Block.box(10, 11, 17, 13, 12, 18),
            Block.box(4, 13, 15, 12, 14, 17),
            Block.box(6, 6, 15.05, 10, 12.5, 15.05)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_GUMBALL_MACHINE= Stream.of(
            Block.box(2, 19, 2, 14, 31, 14),
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(5, 3, 5, 11, 14, 11),
            Block.box(5, 14, 5, 11, 19, 11),
            Block.box(1, 5, 4, 5, 15, 12),
            Block.box(-1, 11, 11, 1, 12, 12),
            Block.box(-2, 11, 10, -1, 12, 13),
            Block.box(-1, 13, 4, 1, 14, 12),
            Block.box(0.9499999999999993, 6, 6, 0.9499999999999993, 12.5, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public TallEntertainmentMachine(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        Direction direction = state.getValue(FACING);
        if(state.is(TBlocks.HAMMER_MACHINE.get())){
            switch (direction){
                case NORTH->{return NORTH_HAMMER_MACHINE;}
                case SOUTH->{return SOUTH_HAMMER_MACHINE;}
                case EAST->{return EAST_HAMMER_MACHINE;}
                case WEST->{return WEST_HAMMER_MACHINE;}
            }
        } else if (state.is(TBlocks.WACK_MACHINE.get())) {
            switch (direction){
                case NORTH->{return NORTH_WACK_MACHINE;}
                case SOUTH->{return SOUTH_WACK_MACHINE;}
                case EAST->{return EAST_WACK_MACHINE;}
                case WEST->{return WEST_WACK_MACHINE;}
            }
        }
        else if(state.is(TBlocks.GUMBALL_MACHINE.get())){
            switch (direction){
                case NORTH->{return NORTH_GUMBALL_MACHINE;}
                case SOUTH->{return SOUTH_GUMBALL_MACHINE;}
                case EAST -> {return EAST_GUMBALL_MACHINE;}
                case WEST -> {return WEST_GUMBALL_MACHINE;}
            }
        }
        return BLOCK_SHAPE;
    }
}
