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

public class ShowerHead extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(6, 6, 15, 10, 10, 16),
            Block.box(7, 8, 13, 9, 10, 15),
            Block.box(7, 10, 11, 9, 12, 13),
            Block.box(7, 12, 9, 9, 14, 11),
            Block.box(6, 13, 6, 10, 14, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 6, 6, 1, 10, 10),
            Block.box(1, 8, 7, 3, 10, 9),
            Block.box(3, 10, 7, 5, 12, 9),
            Block.box(5, 12, 7, 7, 14, 9),
            Block.box(6, 13, 6, 10, 14, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(6, 6, 0, 10, 10, 1),
            Block.box(7, 8, 1, 9, 10, 3),
            Block.box(7, 10, 3, 9, 12, 5),
            Block.box(7, 12, 5, 9, 14, 7),
            Block.box(6, 13, 6, 10, 14, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(15, 6, 6, 16, 10, 10),
            Block.box(13, 8, 7, 15, 10, 9),
            Block.box(11, 10, 7, 13, 12, 9),
            Block.box(9, 12, 7, 11, 14, 9),
            Block.box(6, 13, 6, 10, 14, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ShowerHead(Properties p) {
        super(p.sound(SoundType.LANTERN));
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
