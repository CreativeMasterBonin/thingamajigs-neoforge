package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import net.rk.thingamajigs.block.TBlocks;
import org.jetbrains.annotations.Nullable;

public class TFlammableRPBlock extends RotatedPillarBlock{
    public TFlammableRPBlock(Properties properties) {
        super(properties.ignitedByLava());
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem){
            if(state.is(TBlocks.RUBBER_LOG.get())){
                return TBlocks.STRIPPED_RUBBER_LOG.get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }

            if(state.is(TBlocks.RUBBER_WOOD.get())){
                return TBlocks.STRIPPED_RUBBER_WOOD.get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}
