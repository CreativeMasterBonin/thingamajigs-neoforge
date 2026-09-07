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

public class OldPCMonitor extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(3, 4, 4, 13, 5, 12),
            Block.box(5, 5, 6, 11, 8, 10),
            Block.box(0, 8, 0, 16, 24, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(4, 4, 3, 12, 5, 13),
            Block.box(6, 5, 5, 10, 8, 11),
            Block.box(0, 8, 0, 16, 24, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(3, 4, 4, 13, 5, 12),
            Block.box(5, 5, 6, 11, 8, 10),
            Block.box(0, 8, 0, 16, 24, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(4, 4, 3, 12, 5, 13),
            Block.box(6, 5, 5, 10, 8, 11),
            Block.box(0, 8, 0, 16, 24, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
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

    public OldPCMonitor(Properties p) {
        super(p.sound(SoundType.METAL));
    }
}
