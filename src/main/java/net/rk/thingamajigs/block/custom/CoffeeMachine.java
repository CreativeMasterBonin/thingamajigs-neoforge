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

public class CoffeeMachine extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(2, 0, 0, 14, 1, 16),
            Block.box(2, 1, 8, 11, 14, 16),
            Block.box(11, 1, 8, 14, 14, 16),
            Block.box(3, 14, 1, 13, 16, 10),
            Block.box(4, 14, 11, 8, 15, 15),
            Block.box(6, 11, 3, 7, 14, 5),
            Block.box(8, 11, 3, 9, 14, 5),
            Block.box(10, 11, 3, 11, 14, 5),
            Block.box(2, 3, 7, 4, 11, 8),
            Block.box(2.5, 5, 6, 3.5, 6, 7),
            Block.box(2.5, 6.5, 6, 3.5, 7.5, 7),
            Block.box(2.5, 8, 6, 3.5, 9, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, 2, 16, 1, 14),
            Block.box(0, 1, 2, 8, 14, 11),
            Block.box(0, 1, 11, 8, 14, 14),
            Block.box(6, 14, 3, 15, 16, 13),
            Block.box(1, 14, 4, 5, 15, 8),
            Block.box(11, 11, 6, 13, 14, 7),
            Block.box(11, 11, 8, 13, 14, 9),
            Block.box(11, 11, 10, 13, 14, 11),
            Block.box(8, 3, 2, 9, 11, 4),
            Block.box(9, 5, 2.5, 10, 6, 3.5),
            Block.box(9, 6.5, 2.5, 10, 7.5, 3.5),
            Block.box(9, 8, 2.5, 10, 9, 3.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(2, 0, 0, 14, 1, 16),
            Block.box(5, 1, 0, 14, 14, 8),
            Block.box(2, 1, 0, 5, 14, 8),
            Block.box(3, 14, 6, 13, 16, 15),
            Block.box(8, 14, 1, 12, 15, 5),
            Block.box(9, 11, 11, 10, 14, 13),
            Block.box(7, 11, 11, 8, 14, 13),
            Block.box(5, 11, 11, 6, 14, 13),
            Block.box(12, 3, 8, 14, 11, 9),
            Block.box(12.5, 5, 9, 13.5, 6, 10),
            Block.box(12.5, 6.5, 9, 13.5, 7.5, 10),
            Block.box(12.5, 8, 9, 13.5, 9, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 2, 16, 1, 14),
            Block.box(8, 1, 5, 16, 14, 14),
            Block.box(8, 1, 2, 16, 14, 5),
            Block.box(1, 14, 3, 10, 16, 13),
            Block.box(11, 14, 8, 15, 15, 12),
            Block.box(3, 11, 9, 5, 14, 10),
            Block.box(3, 11, 7, 5, 14, 8),
            Block.box(3, 11, 5, 5, 14, 6),
            Block.box(7, 3, 12, 8, 11, 14),
            Block.box(6, 5, 12.5, 7, 6, 13.5),
            Block.box(6, 6.5, 12.5, 7, 7.5, 13.5),
            Block.box(6, 8, 12.5, 7, 9, 13.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public CoffeeMachine(Properties properties) {
        super(properties.sound(SoundType.LANTERN));
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
