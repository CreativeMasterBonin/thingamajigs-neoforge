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
import net.rk.thingamajigs.block.TBlocks;

import java.util.stream.Stream;

public class MinigolfFlag extends DoubleTallDecorationBlock{
    public MinigolfFlag(Properties properties) {
        super(properties.sound(SoundType.CALCITE).noCollission());
    }

    public static final VoxelShape NORTH = Stream.of(
            Block.box(6, 0, 6, 10, 1, 10),
            Block.box(7.5, 1, 7.5, 8.5, 25, 8.5),
            Block.box(7.5, 19.37, 0.5, 8.5, 24.37, 7.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(6, 0, 6, 10, 1, 10),
            Block.box(7.5, 1, 7.5, 8.5, 25, 8.5),
            Block.box(8.5, 19.37, 7.5, 15.5, 24.37, 8.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(6, 0, 6, 10, 1, 10),
            Block.box(7.5, 1, 7.5, 8.5, 25, 8.5),
            Block.box(7.5, 19.37, 8.5, 8.5, 24.37, 15.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(6, 0, 6, 10, 1, 10),
            Block.box(7.5, 1, 7.5, 8.5, 25, 8.5),
            Block.box(0.5, 19.37, 7.5, 7.5, 24.37, 8.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return DoubleTallDecorationBlock.BLOCK_SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch(state.getValue(FACING)){
            case NORTH -> {
                return NORTH;
            }
            case SOUTH ->  {
                return SOUTH;
            }
            case EAST -> {
                return EAST;
            }
            case WEST -> {
                return WEST;
            }
            default -> {
                return Shapes.block();
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter lvl, BlockPos pos, CollisionContext ctx) {
        if(ctx.isHoldingItem(TBlocks.MINIGOLF_FLAG.asItem())){
            return DoubleTallDecorationBlock.BLOCK_SHAPE;
        }
        else{
            switch(state.getValue(FACING)){
                case NORTH -> {
                    return NORTH;
                }
                case SOUTH ->  {
                    return SOUTH;
                }
                case EAST -> {
                    return EAST;
                }
                case WEST -> {
                    return WEST;
                }
                default -> {
                    return Shapes.block();
                }
            }
        }
    }
}
