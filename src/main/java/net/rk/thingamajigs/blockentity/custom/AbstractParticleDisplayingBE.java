package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractParticleDisplayingBE extends BlockEntity {
    public int ticks = 0;

    public AbstractParticleDisplayingBE(BlockEntityType<?> blockEntityType, BlockPos pPos, BlockState pBlockState) {
        super(blockEntityType, pPos, pBlockState);
    }

    public abstract ParticleType<?> getParticleType();
    public abstract void applyParticleEffect(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState);

    public static void clientTick(Level clvl, BlockPos cbp, BlockState cbs, AbstractParticleDisplayingBE cbe){
        cbe.ticks++;
        if(cbe.ticks > 32767){
            cbe.ticks = 0;
        }
    }
    public static void serverTick(Level slvl, BlockPos sbp, BlockState sbs, AbstractParticleDisplayingBE sbe){
        sbe.ticks++;
        if(sbe.ticks > 32767){
            sbe.ticks = 0;
        }
    }
}
