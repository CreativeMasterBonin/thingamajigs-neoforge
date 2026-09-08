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

public class GardenHose extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(7, 8, 8, 9, 10, 16),
            Block.box(4, 5, 13, 12, 13, 15),
            Block.box(4, 5, 10.75, 12, 13, 12.75),
            Block.box(4, 5, 8.5, 7, 13, 10.5),
            Block.box(7, 10, 8.5, 12, 13, 10.5),
            Block.box(10, 4, 8.5, 12, 10, 10.5),
            Block.box(10.25, 1, 8.75, 11.75, 4, 10.25),
            Block.box(10, 3, 8.5, 12, 4, 10.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 8, 7, 8, 10, 9),
            Block.box(1, 5, 4, 3, 13, 12),
            Block.box(3.25, 5, 4, 5.25, 13, 12),
            Block.box(5.5, 5, 4, 7.5, 13, 7),
            Block.box(5.5, 10, 7, 7.5, 13, 12),
            Block.box(5.5, 4, 10, 7.5, 10, 12),
            Block.box(5.75, 1, 10.25, 7.25, 4, 11.75),
            Block.box(5.5, 3, 10, 7.5, 4, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(7, 8, 0, 9, 10, 8),
            Block.box(4, 5, 1, 12, 13, 3),
            Block.box(4, 5, 3.25, 12, 13, 5.25),
            Block.box(9, 5, 5.5, 12, 13, 7.5),
            Block.box(4, 10, 5.5, 9, 13, 7.5),
            Block.box(4, 4, 5.5, 6, 10, 7.5),
            Block.box(4.25, 1, 5.75, 5.75, 4, 7.25),
            Block.box(4, 3, 5.5, 6, 4, 7.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(8, 8, 7, 16, 10, 9),
            Block.box(13, 5, 4, 15, 13, 12),
            Block.box(10.75, 5, 4, 12.75, 13, 12),
            Block.box(8.5, 5, 9, 10.5, 13, 12),
            Block.box(8.5, 10, 4, 10.5, 13, 9),
            Block.box(8.5, 4, 4, 10.5, 10, 6),
            Block.box(8.75, 1, 4.25, 10.25, 4, 5.75),
            Block.box(8.5, 3, 4, 10.5, 4, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public GardenHose(Properties p) {
        super(p.sound(SoundType.CALCITE).noCollission());
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
