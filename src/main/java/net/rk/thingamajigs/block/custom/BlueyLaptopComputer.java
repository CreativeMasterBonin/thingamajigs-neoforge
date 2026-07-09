package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.xtras.TCalcStuff;

public class BlueyLaptopComputer extends ThingamajigsDecorativeBlock {
    public static final IntegerProperty VERSION = IntegerProperty.create("version",0,2);

    public BlueyLaptopComputer(Properties p){
        super(p);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VERSION);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide()){
            player.playSound(SoundEvents.CAKE_ADD_CANDLE,1f, TCalcStuff.nextFloatBetweenInclusive(0.98f,1.05f));
            return InteractionResult.SUCCESS;
        }
        else {
            level.setBlock(pos,state.cycle(VERSION),3);
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }
    }
}
