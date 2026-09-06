package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class ConvenienceShelf extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 16, 0, 16, 17, 16),
            Block.box(0, 8, 0, 16, 9, 16),
            Block.box(0, 1, 15, 16, 16, 16),
            Block.box(1, 1, 2, 5, 7, 5),
            Block.box(1, 1, 6, 5, 7, 9),
            Block.box(1, 1, 10, 5, 7, 13),
            Block.box(6, 1, 1, 8, 6, 3),
            Block.box(9, 1, 1, 11, 6, 3),
            Block.box(9, 1, 4, 11, 6, 6),
            Block.box(6, 1, 4, 8, 6, 6),
            Block.box(9, 1, 7, 11, 6, 9),
            Block.box(6, 1, 7, 8, 6, 9),
            Block.box(9, 1, 10, 11, 6, 12),
            Block.box(6, 1, 10, 8, 6, 12),
            Block.box(12, 1, 1, 15, 7, 2),
            Block.box(12, 1, 3, 15, 7, 4),
            Block.box(12, 1, 5, 15, 7, 6),
            Block.box(12, 1, 7, 15, 7, 8),
            Block.box(12, 1, 9, 15, 7, 10),
            Block.box(12, 1, 11, 15, 7, 12),
            Block.box(6, 9, 1, 11, 13, 3),
            Block.box(6, 9, 4, 11, 13, 6),
            Block.box(6, 9, 7, 11, 13, 9),
            Block.box(6, 9, 10, 11, 13, 12),
            Block.box(13, 9, 2, 15, 12, 7),
            Block.box(13, 9, 8, 15, 12, 13),
            Block.box(13, 12, 8, 15, 15, 13),
            Block.box(13, 12, 2, 15, 15, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 16, 0, 16, 17, 16),
            Block.box(0, 8, 0, 16, 9, 16),
            Block.box(0, 1, 0, 1, 16, 16),
            Block.box(11, 1, 1, 14, 7, 5),
            Block.box(7, 1, 1, 10, 7, 5),
            Block.box(3, 1, 1, 6, 7, 5),
            Block.box(13, 1, 6, 15, 6, 8),
            Block.box(13, 1, 9, 15, 6, 11),
            Block.box(10, 1, 9, 12, 6, 11),
            Block.box(10, 1, 6, 12, 6, 8),
            Block.box(7, 1, 9, 9, 6, 11),
            Block.box(7, 1, 6, 9, 6, 8),
            Block.box(4, 1, 9, 6, 6, 11),
            Block.box(4, 1, 6, 6, 6, 8),
            Block.box(14, 1, 12, 15, 7, 15),
            Block.box(12, 1, 12, 13, 7, 15),
            Block.box(10, 1, 12, 11, 7, 15),
            Block.box(8, 1, 12, 9, 7, 15),
            Block.box(6, 1, 12, 7, 7, 15),
            Block.box(4, 1, 12, 5, 7, 15),
            Block.box(13, 9, 6, 15, 13, 11),
            Block.box(10, 9, 6, 12, 13, 11),
            Block.box(7, 9, 6, 9, 13, 11),
            Block.box(4, 9, 6, 6, 13, 11),
            Block.box(9, 9, 13, 14, 12, 15),
            Block.box(3, 9, 13, 8, 12, 15),
            Block.box(3, 12, 13, 8, 15, 15),
            Block.box(9, 12, 13, 14, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 16, 0, 16, 17, 16),
            Block.box(0, 8, 0, 16, 9, 16),
            Block.box(0, 1, 0, 16, 16, 1),
            Block.box(11, 1, 11, 15, 7, 14),
            Block.box(11, 1, 7, 15, 7, 10),
            Block.box(11, 1, 3, 15, 7, 6),
            Block.box(8, 1, 13, 10, 6, 15),
            Block.box(5, 1, 13, 7, 6, 15),
            Block.box(5, 1, 10, 7, 6, 12),
            Block.box(8, 1, 10, 10, 6, 12),
            Block.box(5, 1, 7, 7, 6, 9),
            Block.box(8, 1, 7, 10, 6, 9),
            Block.box(5, 1, 4, 7, 6, 6),
            Block.box(8, 1, 4, 10, 6, 6),
            Block.box(1, 1, 14, 4, 7, 15),
            Block.box(1, 1, 12, 4, 7, 13),
            Block.box(1, 1, 10, 4, 7, 11),
            Block.box(1, 1, 8, 4, 7, 9),
            Block.box(1, 1, 6, 4, 7, 7),
            Block.box(1, 1, 4, 4, 7, 5),
            Block.box(5, 9, 13, 10, 13, 15),
            Block.box(5, 9, 10, 10, 13, 12),
            Block.box(5, 9, 7, 10, 13, 9),
            Block.box(5, 9, 4, 10, 13, 6),
            Block.box(1, 9, 9, 3, 12, 14),
            Block.box(1, 9, 3, 3, 12, 8),
            Block.box(1, 12, 3, 3, 15, 8),
            Block.box(1, 12, 9, 3, 15, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 16, 0, 16, 17, 16),
            Block.box(0, 8, 0, 16, 9, 16),
            Block.box(15, 1, 0, 16, 16, 16),
            Block.box(2, 1, 11, 5, 7, 15),
            Block.box(6, 1, 11, 9, 7, 15),
            Block.box(10, 1, 11, 13, 7, 15),
            Block.box(1, 1, 8, 3, 6, 10),
            Block.box(1, 1, 5, 3, 6, 7),
            Block.box(4, 1, 5, 6, 6, 7),
            Block.box(4, 1, 8, 6, 6, 10),
            Block.box(7, 1, 5, 9, 6, 7),
            Block.box(7, 1, 8, 9, 6, 10),
            Block.box(10, 1, 5, 12, 6, 7),
            Block.box(10, 1, 8, 12, 6, 10),
            Block.box(1, 1, 1, 2, 7, 4),
            Block.box(3, 1, 1, 4, 7, 4),
            Block.box(5, 1, 1, 6, 7, 4),
            Block.box(7, 1, 1, 8, 7, 4),
            Block.box(9, 1, 1, 10, 7, 4),
            Block.box(11, 1, 1, 12, 7, 4),
            Block.box(1, 9, 5, 3, 13, 10),
            Block.box(4, 9, 5, 6, 13, 10),
            Block.box(7, 9, 5, 9, 13, 10),
            Block.box(10, 9, 5, 12, 13, 10),
            Block.box(2, 9, 1, 7, 12, 3),
            Block.box(8, 9, 1, 13, 12, 3),
            Block.box(8, 12, 1, 13, 15, 3),
            Block.box(2, 12, 1, 7, 15, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ConvenienceShelf(Properties p) {
        super(p);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
