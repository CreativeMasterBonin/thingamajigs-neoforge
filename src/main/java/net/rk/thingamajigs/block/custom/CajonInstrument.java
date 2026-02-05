package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class CajonInstrument extends ThingamajigsDecorativeBlock{
    public CajonInstrument(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> lc, TooltipFlag flag) {
        lc.add(Component.translatable("block.thingamajigs.cajon.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if(lvl.isClientSide){
            return InteractionResult.SUCCESS;
        }
        else{
            boolean hitASide = false;
            if(ply.getItemInHand(ply.getUsedItemHand()).isEmpty()){
                if(bs.getValue(FACING) == Direction.SOUTH){
                    if(bhr.getDirection() == Direction.SOUTH){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_HAT.value(), SoundSource.BLOCKS,1.0f,0.75f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_SNARE.value(), SoundSource.BLOCKS,1.0f,0.5f);
                        hitASide = true;
                    }
                    else if(bhr.getDirection() == Direction.WEST){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_BASEDRUM.value(), SoundSource.BLOCKS,1.0f,0.5f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_COW_BELL.value(), SoundSource.BLOCKS,0.75f,0.1f);
                        hitASide = true;
                    }
                }
                else if(bs.getValue(FACING) == Direction.NORTH){
                    if(bhr.getDirection() == Direction.NORTH){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_HAT.value(), SoundSource.BLOCKS,1.0f,0.75f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_SNARE.value(), SoundSource.BLOCKS,1.0f,0.5f);
                        hitASide = true;
                    }
                    else if(bhr.getDirection() == Direction.EAST){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_BASEDRUM.value(), SoundSource.BLOCKS,1.0f,0.5f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_COW_BELL.value(), SoundSource.BLOCKS,0.75f,0.1f);
                        hitASide = true;
                    }
                }
                else if(bs.getValue(FACING) == Direction.WEST){
                    if(bhr.getDirection() == Direction.WEST){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_HAT.value(), SoundSource.BLOCKS,1.0f,0.75f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_SNARE.value(), SoundSource.BLOCKS,1.0f,0.5f);
                        hitASide = true;
                    }
                    else if(bhr.getDirection() == Direction.NORTH){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_BASEDRUM.value(), SoundSource.BLOCKS,1.0f,0.5f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_COW_BELL.value(), SoundSource.BLOCKS,0.75f,0.1f);
                        hitASide = true;
                    }
                }
                else if(bs.getValue(FACING) == Direction.EAST){
                    if(bhr.getDirection() == Direction.EAST){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_HAT.value(), SoundSource.BLOCKS,1.0f,0.75f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_SNARE.value(), SoundSource.BLOCKS,1.0f,0.5f);
                        hitASide = true;
                    }
                    else if(bhr.getDirection() == Direction.SOUTH){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_BASEDRUM.value(), SoundSource.BLOCKS,1.0f,0.5f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_COW_BELL.value(), SoundSource.BLOCKS,0.75f,0.1f);
                        hitASide = true;
                    }
                }
            }
            if(hitASide){
                ply.swing(ply.getUsedItemHand());
                return InteractionResult.CONSUME;
            }
            return InteractionResult.PASS;
        }
    }
}
