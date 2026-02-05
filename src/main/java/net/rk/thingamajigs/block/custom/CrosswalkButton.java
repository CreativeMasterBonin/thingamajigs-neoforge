package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class CrosswalkButton extends Block{
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape NORTH_S = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(4, 6, 6.9, 12, 14, 6.9),
            Block.box(6, 2, 6, 10, 6, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SOUTH_S = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(4, 6, 9.1, 12, 14, 9.1),
            Block.box(6, 2, 9, 10, 6, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape EAST_S = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(9.1, 6, 4, 9.1, 14, 12),
            Block.box(9, 2, 6, 10, 6, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WEST_S = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(6.9, 6, 4, 6.9, 14, 12),
            Block.box(6, 2, 6, 7, 6, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CrosswalkButton(Properties p_49795_) {
        super(p_49795_.strength(1.5F,3F));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false));
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

    private int getPressDuration() {
        return 75;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player p, BlockHitResult bhr) {
        if (bs.getValue(POWERED)) {
            return InteractionResult.CONSUME;
        }
        else {
            this.press(bs,lvl,bp);
            lvl.gameEvent(p,GameEvent.BLOCK_ACTIVATE,bp);
            return InteractionResult.sidedSuccess(lvl.isClientSide);
        }
    }

    @Override
    public int getSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pBlockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pIsMoving && !pState.is(pNewState.getBlock())) {
            if (pState.getValue(POWERED)) {
                this.updateNeighbours(pState, pLevel, pPos);
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    @Override
    public int getDirectSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        return pBlockState.getValue(POWERED) ? 15 : 0;
    }

    @Override
    public boolean isSignalSource(BlockState pState) {
        return true;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandomSource) {
        if (pState.getValue(POWERED)) {
            pLevel.setBlock(pPos, pState.setValue(POWERED, false), 3);
            this.updateNeighbours(pState, pLevel, pPos);
            pLevel.gameEvent(null,GameEvent.BLOCK_DEACTIVATE, pPos);
        }
    }

    public void press(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos, pState.setValue(POWERED, true), 3);
        this.updateNeighbours(pState, pLevel, pPos);
        pLevel.playLocalSound(pPos.getX(),pPos.getY(),pPos.getZ(), SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundSource.BLOCKS,100,1.0F,false);
        pLevel.scheduleTick(pPos, this, this.getPressDuration());
    }

    private void updateNeighbours(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.updateNeighborsAt(pPos, this);
        pLevel.updateNeighborsAt(pPos.north(), this);
        pLevel.updateNeighborsAt(pPos.south(), this);
        pLevel.updateNeighborsAt(pPos.east(), this);
        pLevel.updateNeighborsAt(pPos.west(), this);
        pLevel.updateNeighborsAt(pPos.above(), this);
        pLevel.updateNeighborsAt(pPos.below(), this);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(POWERED,false);
    }
}
