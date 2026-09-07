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

@SuppressWarnings("deprecated")
public class OfficePhone extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(5.5, 0.92388, 9.61732, 9.5, 2.67388, 13.61732),
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(5.5, 1.02, 3, 12.5, 1.02, 9),
            Block.box(9.7, 1, 10, 13.7, 3, 14),
            Block.box(3, 1, 3.5, 5, 2, 5.5),
            Block.box(3, 1, 11.5, 5, 2, 13.5),
            Block.box(3, 2, 4.5, 5, 3, 12.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(2.3826800000000006, 0.92388, 5.5, 6.382680000000001, 2.67388, 9.5),
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(7, 1.02, 5.5, 13, 1.02, 12.5),
            Block.box(2, 1, 9.7, 6, 3, 13.7),
            Block.box(10.5, 1, 3, 12.5, 2, 5),
            Block.box(2.5, 1, 3, 4.5, 2, 5),
            Block.box(3.5, 2, 3, 11.5, 3, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(6.5, 0.92388, 2.3826800000000006, 10.5, 2.67388, 6.382680000000001),
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(3.5, 1.02, 7, 10.5, 1.02, 13),
            Block.box(2.3000000000000007, 1, 2, 6.300000000000001, 3, 6),
            Block.box(11, 1, 10.5, 13, 2, 12.5),
            Block.box(11, 1, 2.5, 13, 2, 4.5),
            Block.box(11, 2, 3.5, 13, 3, 11.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(9.61732, 0.92388, 6.5, 13.61732, 2.67388, 10.5),
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(3, 1.02, 3.5, 9, 1.02, 10.5),
            Block.box(10, 1, 2.3000000000000007, 14, 3, 6.300000000000001),
            Block.box(3.5, 1, 11, 5.5, 2, 13),
            Block.box(11.5, 1, 11, 13.5, 2, 13),
            Block.box(4.5, 2, 11, 12.5, 3, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_GENERAL = Stream.of(
            Block.box(1, 0, 0, 15, 2, 16),
            Block.box(5.949999999999999, 2, 10, 9.95, 2.5, 13),
            Block.box(8.5, 2, 7.5, 10.5, 2.25, 9),
            Block.box(5.5, 2, 7.5, 7.5, 2.25, 9),
            Block.box(2.5, 2, 7.5, 4.5, 2.25, 9),
            Block.box(2.5, 2, 5, 4.5, 2.25, 6.5),
            Block.box(8.5, 2, 5, 10.5, 2.25, 6.5),
            Block.box(5.5, 2, 5, 7.5, 2.25, 6.5),
            Block.box(8.5, 2, 2.5, 10.5, 2.25, 4),
            Block.box(5.5, 2, 2.5, 7.5, 2.25, 4),
            Block.box(2.5, 2, 2.5, 4.5, 2.25, 4),
            Block.box(5.5, 2.26, 5, 7.5, 2.26, 5.65),
            Block.box(11, 2, 1, 14, 3, 3),
            Block.box(11, 2, 13, 14, 3, 15),
            Block.box(11, 3, 2, 14, 4, 14),
            Block.box(2.5, 2, 12.5, 4.5, 2.25, 14),
            Block.box(2.5, 2, 10.5, 4.5, 2.25, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_GENERAL = Stream.of(
            Block.box(0, 0, 1, 16, 2, 15),
            Block.box(3, 2, 5.949999999999999, 6, 2.5, 9.95),
            Block.box(7, 2, 8.5, 8.5, 2.25, 10.5),
            Block.box(7, 2, 5.5, 8.5, 2.25, 7.5),
            Block.box(7, 2, 2.5, 8.5, 2.25, 4.5),
            Block.box(9.5, 2, 2.5, 11, 2.25, 4.5),
            Block.box(9.5, 2, 8.5, 11, 2.25, 10.5),
            Block.box(9.5, 2, 5.5, 11, 2.25, 7.5),
            Block.box(12, 2, 8.5, 13.5, 2.25, 10.5),
            Block.box(12, 2, 5.5, 13.5, 2.25, 7.5),
            Block.box(12, 2, 2.5, 13.5, 2.25, 4.5),
            Block.box(10.35, 2.26, 5.5, 11, 2.26, 7.5),
            Block.box(13, 2, 11, 15, 3, 14),
            Block.box(1, 2, 11, 3, 3, 14),
            Block.box(2, 3, 11, 14, 4, 14),
            Block.box(2, 2, 2.5, 3.5, 2.25, 4.5),
            Block.box(4, 2, 2.5, 5.5, 2.25, 4.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_GENERAL = Stream.of(
            Block.box(1, 0, 0, 15, 2, 16),
            Block.box(6.050000000000001, 2, 3, 10.05, 2.5, 6),
            Block.box(5.5, 2, 7, 7.5, 2.25, 8.5),
            Block.box(8.5, 2, 7, 10.5, 2.25, 8.5),
            Block.box(11.5, 2, 7, 13.5, 2.25, 8.5),
            Block.box(11.5, 2, 9.5, 13.5, 2.25, 11),
            Block.box(5.5, 2, 9.5, 7.5, 2.25, 11),
            Block.box(8.5, 2, 9.5, 10.5, 2.25, 11),
            Block.box(5.5, 2, 12, 7.5, 2.25, 13.5),
            Block.box(8.5, 2, 12, 10.5, 2.25, 13.5),
            Block.box(11.5, 2, 12, 13.5, 2.25, 13.5),
            Block.box(8.5, 2.26, 10.35, 10.5, 2.26, 11),
            Block.box(2, 2, 13, 5, 3, 15),
            Block.box(2, 2, 1, 5, 3, 3),
            Block.box(2, 3, 2, 5, 4, 14),
            Block.box(11.5, 2, 2, 13.5, 2.25, 3.5),
            Block.box(11.5, 2, 4, 13.5, 2.25, 5.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_GENERAL = Stream.of(
            Block.box(0, 0, 1, 16, 2, 15),
            Block.box(10, 2, 6.050000000000001, 13, 2.5, 10.05),
            Block.box(7.5, 2, 5.5, 9, 2.25, 7.5),
            Block.box(7.5, 2, 8.5, 9, 2.25, 10.5),
            Block.box(7.5, 2, 11.5, 9, 2.25, 13.5),
            Block.box(5, 2, 11.5, 6.5, 2.25, 13.5),
            Block.box(5, 2, 5.5, 6.5, 2.25, 7.5),
            Block.box(5, 2, 8.5, 6.5, 2.25, 10.5),
            Block.box(2.5, 2, 5.5, 4, 2.25, 7.5),
            Block.box(2.5, 2, 8.5, 4, 2.25, 10.5),
            Block.box(2.5, 2, 11.5, 4, 2.25, 13.5),
            Block.box(5, 2.26, 8.5, 5.65, 2.26, 10.5),
            Block.box(1, 2, 2, 3, 3, 5),
            Block.box(13, 2, 2, 15, 3, 5),
            Block.box(2, 3, 2, 14, 4, 5),
            Block.box(12.5, 2, 11.5, 14, 2.25, 13.5),
            Block.box(10.5, 2, 11.5, 12, 2.25, 13.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();



    public OfficePhone(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(state.is(TBlocks.GENERAL_DIGITAL_PHONE.get())){
            switch (state.getValue(FACING)){
                case NORTH->{return NORTH_GENERAL;}
                case SOUTH->{return SOUTH_GENERAL;}
                case EAST->{return EAST_GENERAL;}
                case WEST->{return WEST_GENERAL;}
                default -> {return Shapes.block();}
            }
        }
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
