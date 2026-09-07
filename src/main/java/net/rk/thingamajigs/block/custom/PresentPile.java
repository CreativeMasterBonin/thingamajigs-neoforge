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

public class PresentPile extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(9, 0, 2, 14, 5, 7),
            Block.box(2, 0, 2, 7, 5, 7),
            Block.box(1, 0, 9, 6, 5, 14),
            Block.box(8, 0, 9, 13, 5, 14),
            Block.box(5, 5, 6, 10, 10, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(9, 0, 9, 14, 5, 14),
            Block.box(9, 0, 2, 14, 5, 7),
            Block.box(2, 0, 1, 7, 5, 6),
            Block.box(2, 0, 8, 7, 5, 13),
            Block.box(5, 5, 5, 10, 10, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(2, 0, 9, 7, 5, 14),
            Block.box(9, 0, 9, 14, 5, 14),
            Block.box(10, 0, 2, 15, 5, 7),
            Block.box(3, 0, 2, 8, 5, 7),
            Block.box(6, 5, 5, 11, 10, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(2, 0, 2, 7, 5, 7),
            Block.box(2, 0, 9, 7, 5, 14),
            Block.box(9, 0, 10, 14, 5, 15),
            Block.box(9, 0, 3, 14, 5, 8),
            Block.box(6, 5, 6, 11, 10, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH ->{return NORTH;}
            case SOUTH ->{return SOUTH;}
            case EAST ->{return EAST;}
            case WEST ->{return WEST;}
            default -> {return Shapes.block();}
        }
    }
    public PresentPile(Properties p) {
        super(p.noCollission().sound(SoundType.WOOL).strength(1.0F,2.0F));
    }
}
