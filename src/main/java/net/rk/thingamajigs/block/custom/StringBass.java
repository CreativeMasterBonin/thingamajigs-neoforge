package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class StringBass extends DoubleTallDecorationBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(6, 18, 11, 10, 27, 14),
            Block.box(7, 23, 14, 9, 30, 16),
            Block.box(6, 2, -0.3000000000000007, 10, 4, 0.6999999999999993),
            Block.box(1, 0, 9.25, 2, 20, 10.25),
            Block.box(7, 0, 9, 9, 11, 10),
            Block.box(4, 26, 13, 6, 27, 14),
            Block.box(4, 24, 12, 6, 25, 13),
            Block.box(10, 24, 12, 12, 25, 13),
            Block.box(10, 26, 13, 12, 27, 14),
            Block.box(2, 0, 1, 14, 6, 7),
            Block.box(4, 6, 3, 12, 11, 9),
            Block.box(2, 11, 5, 14, 18, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(2, 18, 6, 5, 27, 10),
            Block.box(0, 23, 7, 2, 30, 9),
            Block.box(15.3, 2, 6, 16.3, 4, 10),
            Block.box(5.75, 0, 1, 6.75, 20, 2),
            Block.box(6, 0, 7, 7, 11, 9),
            Block.box(2, 26, 4, 3, 27, 6),
            Block.box(3, 24, 4, 4, 25, 6),
            Block.box(3, 24, 10, 4, 25, 12),
            Block.box(2, 26, 10, 3, 27, 12),
            Block.box(9, 0, 2, 15, 6, 14),
            Block.box(7, 6, 4, 13, 11, 12),
            Block.box(5, 11, 2, 11, 18, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(6, 18, 2, 10, 27, 5),
            Block.box(7, 23, 0, 9, 30, 2),
            Block.box(6, 2, 15.3, 10, 4, 16.3),
            Block.box(14, 0, 5.75, 15, 20, 6.75),
            Block.box(7, 0, 6, 9, 11, 7),
            Block.box(10, 26, 2, 12, 27, 3),
            Block.box(10, 24, 3, 12, 25, 4),
            Block.box(4, 24, 3, 6, 25, 4),
            Block.box(4, 26, 2, 6, 27, 3),
            Block.box(2, 0, 9, 14, 6, 15),
            Block.box(4, 6, 7, 12, 11, 13),
            Block.box(2, 11, 5, 14, 18, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(11, 18, 6, 14, 27, 10),
            Block.box(14, 23, 7, 16, 30, 9),
            Block.box(-0.3000000000000007, 2, 6, 0.6999999999999993, 4, 10),
            Block.box(9.25, 0, 14, 10.25, 20, 15),
            Block.box(9, 0, 7, 10, 11, 9),
            Block.box(13, 26, 10, 14, 27, 12),
            Block.box(12, 24, 10, 13, 25, 12),
            Block.box(12, 24, 4, 13, 25, 6),
            Block.box(13, 26, 4, 14, 27, 6),
            Block.box(1, 0, 2, 7, 6, 14),
            Block.box(3, 6, 4, 9, 11, 12),
            Block.box(5, 11, 2, 11, 18, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public StringBass(Properties properties) {
        super(properties.mapColor(MapColor.TERRACOTTA_BROWN).sound(SoundType.BAMBOO_WOOD).pushReaction(PushReaction.BLOCK));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default -> {return BLOCK_SHAPE;}
        }
    }
}
