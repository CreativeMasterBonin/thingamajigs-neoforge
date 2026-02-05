package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

@SuppressWarnings("deprecated")
public class AncientRelicCrystalBlock extends Block{
    public AncientRelicCrystalBlock(Properties p) {
        super(p.strength(45F,2000F)
                .requiresCorrectToolForDrops()
                .mapColor(MapColor.COLOR_LIGHT_BLUE)
                .sound(SoundType.AMETHYST)
                .instrument(NoteBlockInstrument.CHIME));
    }

    @Override
    public boolean isPortalFrame(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
        list.add(Component.translatable("block.thingamajigs.ancient_relic_crystal_block.desc"));
    }

    @Override
    public void onProjectileHit(Level lvl, BlockState bs, BlockHitResult bhr, Projectile prjt) {
        if (!lvl.isClientSide) {
            BlockPos blockpos = bhr.getBlockPos();
            lvl.playSound(null, blockpos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 1.0F, 0.5F + lvl.random.nextFloat() * 1.2F);
            lvl.playSound(null, blockpos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0F, 0.5F + lvl.random.nextFloat() * 1.2F);
        }
    }
}
