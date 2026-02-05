package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NoParticlesTorchBlock extends TorchBlock {
    protected static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
    protected final SimpleParticleType flameParticle;

    public NoParticlesTorchBlock(SimpleParticleType spt, BlockBehaviour.Properties p) {
        super(spt,p);
        this.flameParticle = spt;
    }

    @Override
    public void animateTick(BlockState bs,Level lvl,BlockPos bp,RandomSource rs){}
}
