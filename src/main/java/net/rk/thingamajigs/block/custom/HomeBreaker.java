package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class HomeBreaker extends ToggledStateBlock{
    public static final VoxelShape NORTH_S = Stream.of(
            Block.box(1, 1, 11, 15, 15, 12),
            Block.box(1, 0, 15, 15, 15, 16),
            Block.box(1, 0, 12, 2, 15, 15),
            Block.box(14, 0, 12, 15, 15, 15),
            Block.box(1, 15, 11, 15, 16, 16),
            Block.box(2, 0, 11, 14, 1, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_S = Stream.of(
            Block.box(1, 1, 4, 15, 15, 5),
            Block.box(1, 0, 0, 15, 15, 1),
            Block.box(14, 0, 1, 15, 15, 4),
            Block.box(1, 0, 1, 2, 15, 4),
            Block.box(1, 15, 0, 15, 16, 5),
            Block.box(2, 0, 1, 14, 1, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_S = Stream.of(
            Block.box(4, 1, 1, 5, 15, 15),
            Block.box(0, 0, 1, 1, 15, 15),
            Block.box(1, 0, 1, 4, 15, 2),
            Block.box(1, 0, 14, 4, 15, 15),
            Block.box(0, 15, 1, 5, 16, 15),
            Block.box(1, 0, 2, 5, 1, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_S = Stream.of(
            Block.box(11, 1, 1, 12, 15, 15),
            Block.box(15, 0, 1, 16, 15, 15),
            Block.box(12, 0, 14, 15, 15, 15),
            Block.box(12, 0, 1, 15, 15, 2),
            Block.box(11, 15, 1, 16, 16, 15),
            Block.box(11, 0, 2, 15, 1, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public HomeBreaker(Properties p) {
        super(p);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TOGGLED, false).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        Direction direction = blockState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_S;
            case SOUTH: return SOUTH_S;
            case EAST: return EAST_S;
            case WEST: return WEST_S;
            default: return Shapes.block();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,TOGGLED,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(TOGGLED,false).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
