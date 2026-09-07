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

public class Generator extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(7.5, 3, -3, 8.5, 6, 9),
            Block.box(-1, 2, -3, 17, 3, -2),
            Block.box(0, 0, -5, 3, 5, 0),
            Block.box(13, 0, -5, 16, 5, 0),
            Block.box(0, 15, -5, 16, 16, 19),
            Block.box(0, 5, 15, 16, 15, 19),
            Block.box(0, 6, -3, 16, 8, 15),
            Block.box(0, 0, 15, 2, 5, 16),
            Block.box(14, 0, 15, 16, 5, 16),
            Block.box(0, 0, 16, 16, 1, 19),
            Block.box(0, 8, -3, 4, 15, 2),
            Block.box(12, 8, -3, 16, 15, 2),
            Block.box(4, 8, -5, 12, 14, 3),
            Block.box(5, 8, 4, 11, 15, 5),
            Block.box(5, 8, 6, 11, 15, 7),
            Block.box(5, 8, 8, 11, 15, 9),
            Block.box(5, 8, 10, 11, 15, 11),
            Block.box(5, 8, 12, 11, 15, 15),
            Block.box(6, 9, 3, 10, 13, 12),
            Block.box(0, 9, 5, 1, 15, 11),
            Block.box(15, 9, 5, 16, 15, 11),
            Block.box(2, 8, 19, 6, 13, 20),
            Block.box(7, 8, 19, 11, 13, 20),
            Block.box(12, 12, 19, 13, 13, 20),
            Block.box(12, 10, 19, 13, 11, 20),
            Block.box(12, 8, 19, 13, 9, 20)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(7, 3, 7.5, 19, 6, 8.5),
            Block.box(18, 2, -1, 19, 3, 17),
            Block.box(16, 0, 0, 21, 5, 3),
            Block.box(16, 0, 13, 21, 5, 16),
            Block.box(-3, 15, 0, 21, 16, 16),
            Block.box(-3, 5, 0, 1, 15, 16),
            Block.box(1, 6, 0, 19, 8, 16),
            Block.box(0, 0, 0, 1, 5, 2),
            Block.box(0, 0, 14, 1, 5, 16),
            Block.box(-3, 0, 0, 0, 1, 16),
            Block.box(14, 8, 0, 19, 15, 4),
            Block.box(14, 8, 12, 19, 15, 16),
            Block.box(13, 8, 4, 21, 14, 12),
            Block.box(11, 8, 5, 12, 15, 11),
            Block.box(9, 8, 5, 10, 15, 11),
            Block.box(7, 8, 5, 8, 15, 11),
            Block.box(5, 8, 5, 6, 15, 11),
            Block.box(1, 8, 5, 4, 15, 11),
            Block.box(4, 9, 6, 13, 13, 10),
            Block.box(5, 9, 0, 11, 15, 1),
            Block.box(5, 9, 15, 11, 15, 16),
            Block.box(-4, 8, 2, -3, 13, 6),
            Block.box(-4, 8, 7, -3, 13, 11),
            Block.box(-4, 12, 12, -3, 13, 13),
            Block.box(-4, 10, 12, -3, 11, 13),
            Block.box(-4, 8, 12, -3, 9, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(7.5, 3, 7, 8.5, 6, 19),
            Block.box(-1, 2, 18, 17, 3, 19),
            Block.box(13, 0, 16, 16, 5, 21),
            Block.box(0, 0, 16, 3, 5, 21),
            Block.box(0, 15, -3, 16, 16, 21),
            Block.box(0, 5, -3, 16, 15, 1),
            Block.box(0, 6, 1, 16, 8, 19),
            Block.box(14, 0, 0, 16, 5, 1),
            Block.box(0, 0, 0, 2, 5, 1),
            Block.box(0, 0, -3, 16, 1, 0),
            Block.box(12, 8, 14, 16, 15, 19),
            Block.box(0, 8, 14, 4, 15, 19),
            Block.box(4, 8, 13, 12, 14, 21),
            Block.box(5, 8, 11, 11, 15, 12),
            Block.box(5, 8, 9, 11, 15, 10),
            Block.box(5, 8, 7, 11, 15, 8),
            Block.box(5, 8, 5, 11, 15, 6),
            Block.box(5, 8, 1, 11, 15, 4),
            Block.box(6, 9, 4, 10, 13, 13),
            Block.box(15, 9, 5, 16, 15, 11),
            Block.box(0, 9, 5, 1, 15, 11),
            Block.box(10, 8, -4, 14, 13, -3),
            Block.box(5, 8, -4, 9, 13, -3),
            Block.box(3, 12, -4, 4, 13, -3),
            Block.box(3, 10, -4, 4, 11, -3),
            Block.box(3, 8, -4, 4, 9, -3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(-3, 3, 7.5, 9, 6, 8.5),
            Block.box(-3, 2, -1, -2, 3, 17),
            Block.box(-5, 0, 13, 0, 5, 16),
            Block.box(-5, 0, 0, 0, 5, 3),
            Block.box(-5, 15, 0, 19, 16, 16),
            Block.box(15, 5, 0, 19, 15, 16),
            Block.box(-3, 6, 0, 15, 8, 16),
            Block.box(15, 0, 14, 16, 5, 16),
            Block.box(15, 0, 0, 16, 5, 2),
            Block.box(16, 0, 0, 19, 1, 16),
            Block.box(-3, 8, 12, 2, 15, 16),
            Block.box(-3, 8, 0, 2, 15, 4),
            Block.box(-5, 8, 4, 3, 14, 12),
            Block.box(4, 8, 5, 5, 15, 11),
            Block.box(6, 8, 5, 7, 15, 11),
            Block.box(8, 8, 5, 9, 15, 11),
            Block.box(10, 8, 5, 11, 15, 11),
            Block.box(12, 8, 5, 15, 15, 11),
            Block.box(3, 9, 6, 12, 13, 10),
            Block.box(5, 9, 15, 11, 15, 16),
            Block.box(5, 9, 0, 11, 15, 1),
            Block.box(19, 8, 10, 20, 13, 14),
            Block.box(19, 8, 5, 20, 13, 9),
            Block.box(19, 12, 3, 20, 13, 4),
            Block.box(19, 10, 3, 20, 11, 4),
            Block.box(19, 8, 3, 20, 9, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public Generator(Properties properties) {
        super(properties.sound(SoundType.LANTERN).strength(1.1f,5.25f));
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
