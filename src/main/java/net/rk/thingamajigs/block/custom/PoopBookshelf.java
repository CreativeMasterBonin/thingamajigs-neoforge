package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MapColor;
import net.rk.thingamajigs.xtras.TSoundEvent;

@SuppressWarnings("deprecated")
public class PoopBookshelf extends Block{
    public PoopBookshelf(Properties p) {
        super(p.sound(SoundType.WOOD).strength(1.5F).mapColor(MapColor.COLOR_BROWN));
    }

    @Override
    public void onPlace(BlockState state, Level lvl, BlockPos bp, BlockState bs, boolean pControl) {
        super.onPlace(state,lvl,bp,bs,pControl);
        if(!lvl.isClientSide()){
            lvl.playSound(null,bp,TSoundEvent.POOP.get(),SoundSource.BLOCKS,1F,1F);
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        level.playSound(null,pos,TSoundEvent.POOP_BREAK.get(),SoundSource.BLOCKS,1F, 1F);
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}
