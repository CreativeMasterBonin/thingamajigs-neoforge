package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class CarWashSignal extends CustomRedstoneSignalControlledBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    // car wash go stop signal shapes
    public static final VoxelShape NORTHSOUTH_SHAPE = Shapes.join(Block.box(6, 0, 6, 10, 2, 10), Block.box(3, 2, 4, 13, 14, 12), BooleanOp.OR);
    public static final VoxelShape EASTWEST_SHAPE = Shapes.join(Block.box(6, 0, 6, 10, 2, 10), Block.box(4, 2, 3, 12, 14, 13), BooleanOp.OR);

    public CarWashSignal(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
            case SOUTH:
                return NORTHSOUTH_SHAPE;
            case EAST:
            case WEST:
                return EASTWEST_SHAPE;
            default: return Shapes.block();
        }
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos())).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
