package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.rk.thingamajigs.xtras.TCalcStuff;
import net.rk.thingamajigs.xtras.TParticles;

import java.util.List;

public class CarWashSprayer extends RedstoneLampBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public CarWashSprayer(Properties p) {
        super(p.strength(1F,20F).sound(SoundType.METAL).noOcclusion().noCollission().randomTicks());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    public static final List<SoundEvent> waterSounds = List.of(
            SoundEvents.BOAT_PADDLE_WATER,SoundEvents.POINTED_DRIPSTONE_DRIP_WATER,SoundEvents.GENERIC_SPLASH,
            SoundEvents.PLAYER_SPLASH_HIGH_SPEED,SoundEvents.PLAYER_SPLASH,SoundEvents.DOLPHIN_SPLASH
    );

    @Override
    public void animateTick(BlockState pState, Level level, BlockPos pos, RandomSource pRandom) {
        if(pState.getValue(LIT)){
            double d0 = (double)pos.getX() + 0.63D - level.getRandom().nextDouble() * 0.25D;
            double d1 = (double)pos.getY() + 0.01D;
            double d2 = (double)pos.getZ() + 0.63D - level.getRandom().nextDouble() * 0.25D;
            level.addParticle(TParticles.WATER_SPRAY.get(), d0, d1, d2,
                    0D, 0D, 0D);
            if(pRandom.nextIntBetweenInclusive(0,10) <= 7){
                level.playSound(null,pos,
                        waterSounds.get(pRandom.nextIntBetweenInclusive(0,waterSounds.size() - 1)),
                        SoundSource.BLOCKS,
                        1.0f,
                        TCalcStuff.nextFloatBetweenInclusive(0.95f,1.12f));
            }
        }
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos())).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
