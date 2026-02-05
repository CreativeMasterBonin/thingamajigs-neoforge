package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.xtras.TSoundEvent;

public class Dehumidifier extends ThingamajigsDecorativeBlock{
    public Dehumidifier(Properties properties) {
        super(properties.sound(SoundType.DEEPSLATE_TILES).strength(1F));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rnd) {
        if (!state.getValue(WATERLOGGED)) {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ() + 0.5D;
            float pitch = rnd.nextFloat() * 0.5F;
            if(pitch > 1.0F){
                pitch = 1.0F;
            }
            else if(pitch < 0.0F){
                pitch = 0.75F;
            }
            //
            if (rnd.nextInt(420) == 0) {
                level.playLocalSound(d0, d1, d2, TSoundEvent.AIR.get(), SoundSource.BLOCKS, 1.0F, pitch, false);
            }
            else if(rnd.nextInt(720) == 0){
                level.playLocalSound(d0, d1, d2, TSoundEvent.WATER_NOISE.get(), SoundSource.BLOCKS, 1.0F, pitch, false);
            }

            Direction direction = state.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d4 = rnd.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
            double d6 = rnd.nextDouble() * 9.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
            level.addParticle(ParticleTypes.END_ROD, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }
}
