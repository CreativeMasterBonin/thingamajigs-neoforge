package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class BlueyBox extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_S = Stream.of(
            Block.box(-8, 0, 0, 8, 32, 16),
            Block.box(8, 0, 0, 24, 16, 16),
            Block.box(8, 30, 0, 24, 32, 16),
            Block.box(8, 16, 14, 24, 30, 16),
            Block.box(23, 16, 0, 24, 30, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_S = Stream.of(
            Block.box(8, 0, 0, 24, 32, 16),
            Block.box(-8, 0, 0, 8, 16, 16),
            Block.box(-8, 30, 0, 8, 32, 16),
            Block.box(-8, 16, 0, 8, 30, 2),
            Block.box(-8, 16, 2, -7, 30, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_S = Stream.of(
            Block.box(0, 0, -8, 16, 32, 8),
            Block.box(0, 0, 8, 16, 16, 24),
            Block.box(0, 30, 8, 16, 32, 24),
            Block.box(0, 16, 8, 2, 30, 24),
            Block.box(2, 16, 23, 16, 30, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_S = Stream.of(
            Block.box(0, 0, 8, 16, 32, 24),
            Block.box(0, 0, -8, 16, 16, 8),
            Block.box(0, 30, -8, 16, 32, 8),
            Block.box(14, 16, -8, 16, 30, 8),
            Block.box(0, 16, -8, 14, 30, -7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BlueyBox(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_S;
            case SOUTH: return SOUTH_S;
            case EAST: return EAST_S;
            case WEST: return WEST_S;
            default: return Shapes.block();
        }
    }
}
