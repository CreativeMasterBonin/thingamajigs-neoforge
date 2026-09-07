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

public class ServerRack extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(2, 1, 2, 14, 15, 12),
            Block.box(1, 0, 3, 15, 1, 13),
            Block.box(5, 0, 2, 15, 1, 3),
            Block.box(1, 0, 2, 5, 1, 3),
            Block.box(1, 15, 2, 15, 16, 13),
            Block.box(1, 1, 12, 15, 15, 13),
            Block.box(1, 1, 2, 2, 15, 12),
            Block.box(14, 1, 2, 15, 15, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(4, 1, 2, 14, 15, 14),
            Block.box(3, 0, 1, 13, 1, 15),
            Block.box(13, 0, 5, 14, 1, 15),
            Block.box(13, 0, 1, 14, 1, 5),
            Block.box(3, 15, 1, 14, 16, 15),
            Block.box(3, 1, 1, 4, 15, 15),
            Block.box(4, 1, 1, 14, 15, 2),
            Block.box(4, 1, 14, 14, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(2, 1, 4, 14, 15, 14),
            Block.box(1, 0, 3, 15, 1, 13),
            Block.box(1, 0, 13, 11, 1, 14),
            Block.box(11, 0, 13, 15, 1, 14),
            Block.box(1, 15, 3, 15, 16, 14),
            Block.box(1, 1, 3, 15, 15, 4),
            Block.box(14, 1, 4, 15, 15, 14),
            Block.box(1, 1, 4, 2, 15, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(2, 1, 2, 12, 15, 14),
            Block.box(3, 0, 1, 13, 1, 15),
            Block.box(2, 0, 1, 3, 1, 11),
            Block.box(2, 0, 11, 3, 1, 15),
            Block.box(2, 15, 1, 13, 16, 15),
            Block.box(12, 1, 1, 13, 15, 15),
            Block.box(2, 1, 14, 12, 15, 15),
            Block.box(2, 1, 1, 12, 15, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ServerRack(Properties p) {
        super(p.sound(SoundType.METAL));
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
