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

public class RotatingSecurityCamera extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, -3, 0, 16, 13, 16),
            Block.box(6, 13, 6, 10, 15, 10),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(-1, -4, -1, 2, -1, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, -3, 0, 16, 13, 16),
            Block.box(6, 13, 6, 10, 15, 10),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(14, -4, -1, 17, -1, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, -3, 0, 16, 13, 16),
            Block.box(6, 13, 6, 10, 15, 10),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(14, -4, 14, 17, -1, 17)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, -3, 0, 16, 13, 16),
            Block.box(6, 13, 6, 10, 15, 10),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(-1, -4, 14, 2, -1, 17)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public RotatingSecurityCamera(Properties p) {
        super(p.sound(SoundType.LANTERN));
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
