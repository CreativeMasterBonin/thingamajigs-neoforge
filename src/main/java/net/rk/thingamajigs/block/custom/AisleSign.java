package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

@SuppressWarnings("deprecated")
public class AisleSign extends ThingamajigsDecorativeBlock{
    public static final IntegerProperty NUMBER = IntegerProperty.create("number",0,10);

    public AisleSign(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(NUMBER,0).setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player p, BlockHitResult bhr) {
        if(p.isShiftKeyDown()){
            if(!lvl.isClientSide()){
                lvl.setBlock(bp,bs.cycle(NUMBER),2);
                lvl.playSound(null,bp, SoundEvents.ITEM_FRAME_ROTATE_ITEM, SoundSource.BLOCKS,1F,1F);
                return InteractionResult.SUCCESS;
            }
        }
        else{
            return InteractionResult.PASS;
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(NUMBER);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(NUMBER,0).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
