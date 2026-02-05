package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class DogHouse extends ThingamajigsDecorativeBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    //doghouse shapes
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(14, 1, 0, 16, 17, 14),
            Block.box(0, 1, 14, 16, 17, 16),
            Block.box(0, 17, 0, 16, 18, 16),
            Block.box(7, 22, -7.5, 9, 23, 17.5),
            Block.box(7, 18, 0, 9, 22, 1),
            Block.box(7, 18, 15, 9, 22, 16),
            Block.box(7, 18, 3, 9, 22, 4),
            Block.box(7, 18, 7, 9, 22, 9),
            Block.box(7, 18, 12, 9, 22, 13),
            Block.box(0, 1, 0, 2, 17, 14),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(0, 1, 2, 2, 17, 16),
            Block.box(0, 1, 0, 16, 17, 2),
            Block.box(0, 17, 0, 16, 18, 16),
            Block.box(7, 22, -1.5, 9, 23, 23.5),
            Block.box(7, 18, 15, 9, 22, 16),
            Block.box(7, 18, 0, 9, 22, 1),
            Block.box(7, 18, 12, 9, 22, 13),
            Block.box(7, 18, 7, 9, 22, 9),
            Block.box(7, 18, 3, 9, 22, 4),
            Block.box(14, 1, 2, 16, 17, 16),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(2, 1, 14, 16, 17, 16),
            Block.box(0, 1, 0, 2, 17, 16),
            Block.box(0, 17, 0, 16, 18, 16),
            Block.box(-1.5, 22, 7, 23.5, 23, 9),
            Block.box(15, 18, 7, 16, 22, 9),
            Block.box(0, 18, 7, 1, 22, 9),
            Block.box(12, 18, 7, 13, 22, 9),
            Block.box(7, 18, 7, 9, 22, 9),
            Block.box(3, 18, 7, 4, 22, 9),
            Block.box(2, 1, 0, 16, 17, 2),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(0, 1, 0, 14, 17, 2),
            Block.box(14, 1, 0, 16, 17, 16),
            Block.box(0, 17, 0, 16, 18, 16),
            Block.box(-7.5, 22, 7, 17.5, 23, 9),
            Block.box(0, 18, 7, 1, 22, 9),
            Block.box(15, 18, 7, 16, 22, 9),
            Block.box(3, 18, 7, 4, 22, 9),
            Block.box(7, 18, 7, 9, 22, 9),
            Block.box(12, 18, 7, 13, 22, 9),
            Block.box(0, 1, 14, 14, 17, 16),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public DogHouse(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_SHAPE;
            case SOUTH: return SOUTH_SHAPE;
            case EAST: return EAST_SHAPE;
            case WEST: return WEST_SHAPE;
            default: return Shapes.block();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
