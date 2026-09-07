package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

import java.util.stream.Stream;

public class UsableComputer extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 0, 0, 20, 2, 6),
            Block.box(-4, 0, 9, 20, 3, 16),
            Block.box(-4, 3, 6, 20, 15, 16),
            Block.box(-4, 0, 6, -2, 2, 9),
            Block.box(18, 0, 6, 20, 2, 9),
            Block.box(-4, 0, 0, 0, 2, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(10, 0, 0, 16, 2, 20),
            Block.box(0, 0, -4, 7, 3, 20),
            Block.box(0, 3, -4, 10, 15, 20),
            Block.box(7, 0, -4, 10, 2, -2),
            Block.box(7, 0, 18, 10, 2, 20),
            Block.box(11, 0, -4, 16, 2, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(-4, 0, 10, 16, 2, 16),
            Block.box(-4, 0, 0, 20, 3, 7),
            Block.box(-4, 3, 0, 20, 15, 10),
            Block.box(18, 0, 7, 20, 2, 10),
            Block.box(-4, 0, 7, -2, 2, 10),
            Block.box(16, 0, 11, 20, 2, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, -4, 6, 2, 16),
            Block.box(9, 0, -4, 16, 3, 20),
            Block.box(6, 3, -4, 16, 15, 20),
            Block.box(6, 0, 18, 9, 2, 20),
            Block.box(6, 0, -4, 9, 2, -2),
            Block.box(0, 0, 16, 5, 2, 20)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public UsableComputer(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(state.is(TBlocks.OLD_LEGENDARY_COMPUTER.get())){
            switch (state.getValue(FACING)){
                case NORTH->{return NORTH;}
                case SOUTH->{return SOUTH;}
                case EAST->{return EAST;}
                case WEST->{return WEST;}
                default -> {return Shapes.block();}
            }
        }
        return Shapes.block();
    }
}
