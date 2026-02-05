package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class InstrumentDecorativeBlock extends ThingamajigsDecorativeBlock{
    public final SoundEvent INSTRUMENT_SOUND;
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

    public InstrumentDecorativeBlock(Properties properties){
        super(properties.noOcclusion());
        INSTRUMENT_SOUND = SoundEvents.NOTE_BLOCK_BIT.value();
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    public InstrumentDecorativeBlock(Properties properties, SoundEvent soundEvent){
        super(properties.noOcclusion());
        INSTRUMENT_SOUND = soundEvent;
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIT);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if(lvl.isClientSide){
            return InteractionResult.SUCCESS;
        }
        else{
            if(ply.getItemInHand(ply.getUsedItemHand()).isEmpty()){
                lvl.playSound(null,bp,INSTRUMENT_SOUND, SoundSource.BLOCKS,1.0f,1.0f);
                ply.swing(ply.getUsedItemHand());
                return InteractionResult.CONSUME;
            }
            return InteractionResult.PASS;
        }
    }

    @Override
    public void neighborChanged(BlockState bs, Level lvl, BlockPos bp1, Block blk1, BlockPos bp2, boolean boo1) {
        if (!lvl.isClientSide) {
            boolean flag = bs.getValue(LIT);
            if (flag != lvl.hasNeighborSignal(bp1)) {
                if (flag) {
                    lvl.scheduleTick(bp1, this, 4);
                }
                else {
                    lvl.setBlock(bp1, bs.cycle(LIT), 2);
                }
                if(!bs.getValue(LIT)){
                    lvl.playSound(null,bp1,INSTRUMENT_SOUND, SoundSource.BLOCKS,1.0f,1.0f);
                }
            }

        }
    }

    public void tick(BlockState bs, ServerLevel slvl, BlockPos bp, RandomSource rs) {
        if (bs.getValue(LIT) && !slvl.hasNeighborSignal(bp)) {
            slvl.setBlock(bp, bs.cycle(LIT), 2);
        }
    }
}
