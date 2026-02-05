package net.rk.thingamajigs.block.custom;

import jdk.jfr.Description;
import jdk.jfr.Name;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.xtras.TSoundEvent;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecated")
public class ToggledStateBlock extends ThingamajigsDecorativeBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static BooleanProperty TOGGLED = BlockStateProperties.ENABLED;

    public ToggledStateBlock(Properties p) {
        super(p.strength(1F));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TOGGLED, false).setValue(WATERLOGGED, false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if (ply.isShiftKeyDown()){
            if (lvl.isClientSide) {
                BlockState blockstate1 = bs.cycle(TOGGLED);
                return InteractionResult.SUCCESS;
            }
            else {
                BlockState blockstate = this.pull(bs,lvl,bp);
                playSound(bs,lvl,bp);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    public BlockState pull(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.cycle(TOGGLED);
        pLevel.setBlock(pPos, pState, 3);
        this.updateNeighbours(pState, pLevel, pPos);
        return pState;
    }

    private void updateNeighbours(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.updateNeighborsAt(pPos, this);
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

    public SoundEvent onSound = SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_ON;
    public SoundEvent offSound = SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF;

    /**
     * Custom sounds are played by the toggled state block when specified.
     * Otherwise, the default sounds are always played when this called.
     * See @net.rk.thingamajigs.block.custom.ToggledStateBlock.onSound and @net.rk.thingamajigs.block.custom.ToggledStateBlock.offSound
     */
    public void playSound(BlockState bs, Level lvl, BlockPos bp){
        if(bs.getValue(TOGGLED)){
            lvl.playSound(null,bp,offSound, SoundSource.BLOCKS,1f,1f);
        }
        else{
            lvl.playSound(null,bp,onSound, SoundSource.BLOCKS,1f,1f);
        }
    }
}
