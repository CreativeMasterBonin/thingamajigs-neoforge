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

public class SteamCleaner extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(3, 2, 2, 13, 10, 14),
            Block.box(1, 0, 10, 3, 5, 15),
            Block.box(13, 0, 10, 15, 5, 15),
            Block.box(0, 2, 12, 16, 3, 13),
            Block.box(6, 4, 1, 10, 8, 2),
            Block.box(5, 0, -2, 11, 7, 2),
            Block.box(5, 10, 4, 7, 11, 6),
            Block.box(8, 10, 4, 10, 11, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(2, 2, 3, 14, 10, 13),
            Block.box(1, 0, 1, 6, 5, 3),
            Block.box(1, 0, 13, 6, 5, 15),
            Block.box(3, 2, 0, 4, 3, 16),
            Block.box(14, 4, 6, 15, 8, 10),
            Block.box(14, 0, 5, 18, 7, 11),
            Block.box(10, 10, 5, 12, 11, 7),
            Block.box(10, 10, 8, 12, 11, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(3, 2, 2, 13, 10, 14),
            Block.box(13, 0, 1, 15, 5, 6),
            Block.box(1, 0, 1, 3, 5, 6),
            Block.box(0, 2, 3, 16, 3, 4),
            Block.box(6, 4, 14, 10, 8, 15),
            Block.box(5, 0, 14, 11, 7, 18),
            Block.box(9, 10, 10, 11, 11, 12),
            Block.box(6, 10, 10, 8, 11, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(2, 2, 3, 14, 10, 13),
            Block.box(10, 0, 13, 15, 5, 15),
            Block.box(10, 0, 1, 15, 5, 3),
            Block.box(12, 2, 0, 13, 3, 16),
            Block.box(1, 4, 6, 2, 8, 10),
            Block.box(-2, 0, 5, 2, 7, 11),
            Block.box(4, 10, 9, 6, 11, 11),
            Block.box(4, 10, 6, 6, 11, 8)
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
    public SteamCleaner(Properties p) {
        super(p.sound(SoundType.LANTERN).strength(1F));
    }
}
