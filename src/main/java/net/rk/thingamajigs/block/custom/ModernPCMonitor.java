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

public class ModernPCMonitor extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(5, 0, 7, 11, 1, 9),
            Block.box(-3, 2, 5, 19, 16, 6),
            Block.box(7, 1, 6, 9, 3, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(7, 0, 5, 9, 1, 11),
            Block.box(10, 2, -3, 11, 16, 19),
            Block.box(8, 1, 7, 10, 3, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(5, 0, 7, 11, 1, 9),
            Block.box(-3, 2, 10, 19, 16, 11),
            Block.box(7, 1, 8, 9, 3, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(7, 0, 5, 9, 1, 11),
            Block.box(5, 2, -3, 6, 16, 19),
            Block.box(6, 1, 7, 8, 3, 9)
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

    public ModernPCMonitor(Properties p) {
        super(p.sound(SoundType.METAL));
    }
}
