package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class BabyCarriage extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(15, 0, 0, 16, 3, 3),
            Block.box(15, 0, 13, 16, 3, 16),
            Block.box(0, 0, 13, 1, 3, 16),
            Block.box(0, 0, 0, 1, 3, 3),
            Block.box(1, 1, 1, 15, 2, 2),
            Block.box(1, 1, 14, 15, 2, 15),
            Block.box(0, 4, -2, 16, 6, 16),
            Block.box(0.019999999999999574, 6, 15, 16.02, 20, 18),
            Block.box(0, 18, 2, 16, 20, 18),
            Block.box(14, 17, 18, 16, 25, 27),
            Block.box(0, 17, 18, 2, 25, 27),
            Block.box(0.009999999999999787, 25, 27, 16.01, 27, 29),
            Block.box(-2, 5, 3, 0, 19, 17),
            Block.box(16, 5, 3, 18, 19, 17),
            Block.box(0, 6, -4, 16, 9, -2),
            Block.box(-2, 6, -2, 0, 8, 3),
            Block.box(16, 6, -2, 18, 8, 3),
            Block.box(7, 1, 0, 9, 4, 3),
            Block.box(7, 1, 13, 9, 4, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(13, 0, 15, 16, 3, 16),
            Block.box(0, 0, 15, 3, 3, 16),
            Block.box(0, 0, 0, 3, 3, 1),
            Block.box(13, 0, 0, 16, 3, 1),
            Block.box(14, 1, 1, 15, 2, 15),
            Block.box(1, 1, 1, 2, 2, 15),
            Block.box(0, 4, 0, 18, 6, 16),
            Block.box(-2, 6, 0.019999999999999574, 1, 20, 16.02),
            Block.box(-2, 18, 0, 14, 20, 16),
            Block.box(-11, 17, 14, -2, 25, 16),
            Block.box(-11, 17, 0, -2, 25, 2),
            Block.box(-13, 25, 0.009999999999999787, -11, 27, 16.01),
            Block.box(-1, 5, -2, 13, 19, 0),
            Block.box(-1, 5, 16, 13, 19, 18),
            Block.box(18, 6, 0, 20, 9, 16),
            Block.box(13, 6, -2, 18, 8, 0),
            Block.box(13, 6, 16, 18, 8, 18),
            Block.box(13, 1, 7, 16, 4, 9),
            Block.box(0, 1, 7, 3, 4, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 0, 13, 1, 3, 16),
            Block.box(0, 0, 0, 1, 3, 3),
            Block.box(15, 0, 0, 16, 3, 3),
            Block.box(15, 0, 13, 16, 3, 16),
            Block.box(1, 1, 14, 15, 2, 15),
            Block.box(1, 1, 1, 15, 2, 2),
            Block.box(0, 4, 0, 16, 6, 18),
            Block.box(-0.019999999999999574, 6, -2, 15.98, 20, 1),
            Block.box(0, 18, -2, 16, 20, 14),
            Block.box(0, 17, -11, 2, 25, -2),
            Block.box(14, 17, -11, 16, 25, -2),
            Block.box(-0.010000000000001563, 25, -13, 15.99, 27, -11),
            Block.box(16, 5, -1, 18, 19, 13),
            Block.box(-2, 5, -1, 0, 19, 13),
            Block.box(0, 6, 18, 16, 9, 20),
            Block.box(16, 6, 13, 18, 8, 18),
            Block.box(-2, 6, 13, 0, 8, 18),
            Block.box(7, 1, 13, 9, 4, 16),
            Block.box(7, 1, 0, 9, 4, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 0, 3, 3, 1),
            Block.box(13, 0, 0, 16, 3, 1),
            Block.box(13, 0, 15, 16, 3, 16),
            Block.box(0, 0, 15, 3, 3, 16),
            Block.box(1, 1, 1, 2, 2, 15),
            Block.box(14, 1, 1, 15, 2, 15),
            Block.box(-2, 4, 0, 16, 6, 16),
            Block.box(15, 6, -0.019999999999999574, 18, 20, 15.98),
            Block.box(2, 18, 0, 18, 20, 16),
            Block.box(18, 17, 0, 27, 25, 2),
            Block.box(18, 17, 14, 27, 25, 16),
            Block.box(27, 25, -0.010000000000001563, 29, 27, 15.99),
            Block.box(3, 5, 16, 17, 19, 18),
            Block.box(3, 5, -2, 17, 19, 0),
            Block.box(-4, 6, 0, -2, 9, 16),
            Block.box(-2, 6, 16, 3, 8, 18),
            Block.box(-2, 6, -2, 3, 8, 0),
            Block.box(0, 1, 7, 3, 4, 9),
            Block.box(13, 1, 7, 16, 4, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public BabyCarriage(Properties properties) {
        super(properties.sound(SoundType.CALCITE));
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
