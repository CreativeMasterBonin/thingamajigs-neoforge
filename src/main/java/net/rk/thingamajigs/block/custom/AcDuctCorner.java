package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
public class AcDuctCorner extends AcDuctBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    // shapes
    public static final VoxelShape NORTH_S = Stream.of(
            Block.box(12, 6, 0, 16, 16, 16),
            Block.box(4, 6, 0, 12, 7, 16),
            Block.box(4, 15, 0, 12, 16, 16),
            Block.box(0, 6, 4, 12, 7, 12),
            Block.box(0, 15, 4, 12, 16, 12),
            Block.box(0, 6, 0, 12, 16, 4),
            Block.box(0, 6, 12, 4, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_S = Stream.of(
            Block.box(0, 6, 0, 4, 16, 16),
            Block.box(4, 6, 0, 12, 7, 16),
            Block.box(4, 15, 0, 12, 16, 16),
            Block.box(4, 6, 4, 16, 7, 12),
            Block.box(4, 15, 4, 16, 16, 12),
            Block.box(4, 6, 12, 16, 16, 16),
            Block.box(12, 6, 0, 16, 16, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_S = Stream.of(
            Block.box(0, 6, 12, 16, 16, 16),
            Block.box(0, 6, 4, 16, 7, 12),
            Block.box(0, 15, 4, 16, 16, 12),
            Block.box(4, 6, 0, 12, 7, 12),
            Block.box(4, 15, 0, 12, 16, 12),
            Block.box(12, 6, 0, 16, 16, 12),
            Block.box(0, 6, 0, 4, 16, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_S = Stream.of(
            Block.box(0, 6, 0, 16, 16, 4),
            Block.box(0, 6, 4, 16, 7, 12),
            Block.box(0, 15, 4, 16, 16, 12),
            Block.box(4, 6, 4, 12, 7, 16),
            Block.box(4, 15, 4, 12, 16, 16),
            Block.box(0, 6, 4, 4, 16, 16),
            Block.box(12, 6, 12, 16, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public AcDuctCorner(BlockBehaviour.Properties properties) {
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
            default: return COMMON_SHAPE;
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
