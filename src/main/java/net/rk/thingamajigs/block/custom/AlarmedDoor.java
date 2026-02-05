package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.xtras.TSoundEvent;

public class AlarmedDoor extends DoorBlock{
    public BlockSetType type = BlockSetType.IRON;

    public AlarmedDoor(Properties p) {
        super(BlockSetType.IRON,p.requiresCorrectToolForDrops().strength(1.0F,75F).noOcclusion().sound(SoundType.METAL));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(HINGE, DoorHingeSide.LEFT).setValue(POWERED, false).setValue(HALF, DoubleBlockHalf.LOWER));
    }

    // we want the door to play an alarm sound every (certain amount of) ticks that are called
    // this is a ticking loop! Do not place a billion of these doors!
    @Override
    public void tick(BlockState bs, ServerLevel slvl, BlockPos bp, RandomSource rs) {
        if(!slvl.isClientSide()){
            if(bs.getValue(OPEN)){
                slvl.playSound(null,bp,TSoundEvent.BEEP.get(), SoundSource.BLOCKS,1.0F,1.0F);
                slvl.scheduleTick(bp,bs.getBlock(),45, TickPriority.LOW);
            }
        }
    }

    @Override
    public void onPlace(BlockState bs, Level lvl, BlockPos bp, BlockState bsOri, boolean bo1) {
        if(!lvl.isClientSide()){
            lvl.scheduleTick(bp,bs.getBlock(),45,TickPriority.LOW);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if (!this.type.canOpenByHand()) {
            return InteractionResult.PASS;
        } else {
            bs = bs.cycle(OPEN);
            lvl.setBlock(bp, bs, 10);
            lvl.gameEvent(ply, this.isOpen(bs) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, bp);
            return InteractionResult.sidedSuccess(lvl.isClientSide);
        }
    }

    @Override
    public void neighborChanged(BlockState p_52776_, Level p_52777_, BlockPos p_52778_, Block p_52779_, BlockPos p_52780_, boolean p_52781_) {
        boolean flag = p_52777_.hasNeighborSignal(p_52778_) || p_52777_.hasNeighborSignal(p_52778_.relative(p_52776_.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        if (!this.defaultBlockState().is(p_52779_) && flag != p_52776_.getValue(POWERED)) {
            if (flag != p_52776_.getValue(OPEN)) {
                p_52777_.gameEvent((Entity)null, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, p_52778_);
            }
            //
            if(p_52776_.getValue(OPEN)){
                p_52777_.playSound(null,p_52778_, SoundEvents.IRON_DOOR_CLOSE,SoundSource.BLOCKS,1.0F,1.0F);
            }
            else{
                p_52777_.playSound(null,p_52778_,SoundEvents.IRON_DOOR_OPEN,SoundSource.BLOCKS,1.0F,1.0F);
            }
            p_52777_.setBlock(p_52778_, p_52776_.setValue(POWERED, flag).setValue(OPEN, flag), 2);
        }

    }
}
