package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("deprecated")
public class RotatingToggledBlock extends Block{
    public static final BooleanProperty OPENED = BlockStateProperties.OPEN;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public RotatingToggledBlock(Properties p) {
        super(p.noOcclusion().strength(1F));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(OPENED, false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player pl, BlockHitResult bhr) {
        if (pl.isShiftKeyDown()){
            if (lvl.isClientSide) {
                bs.cycle(OPENED);
                return InteractionResult.SUCCESS;
            }
            else{
                BlockState blockstate = this.open(bs,lvl,bp);
                return InteractionResult.CONSUME;
            }
        }
        else{
            return InteractionResult.CONSUME;
        }
    }

    public BlockState open(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.cycle(OPENED);
        pLevel.setBlock(pPos, pState, 3);
        pLevel.updateNeighborsAt(pPos, this);
        return pState;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, OPENED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(OPENED, false).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
