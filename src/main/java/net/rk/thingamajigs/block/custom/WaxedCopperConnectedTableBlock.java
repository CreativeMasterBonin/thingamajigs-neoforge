package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.block.TBlocks;

import java.util.List;

public class WaxedCopperConnectedTableBlock extends ConnectedTableBlock{
    public WaxedCopperConnectedTableBlock(Properties p) {
        super(p);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player pl, BlockHitResult bhr) {
        InteractionHand h = pl.getUsedItemHand();

        if(pl.getItemInHand(pl.getUsedItemHand()).is(Items.AIR)){
            return InteractionResult.PASS;
        }

        if(pl.getItemInHand(h).is(ItemTags.AXES)){
            toBeStripped(bs,lvl,bp);
            lvl.playSound(null,bp, SoundEvents.AXE_WAX_OFF, SoundSource.PLAYERS,1.0F,1.0F);
            ParticleUtils.spawnParticlesOnBlockFaces(lvl, bp, ParticleTypes.WAX_OFF, UniformInt.of(3, 5));
            pl.swing(h);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    public void toBeStripped(BlockState bs, Level l, BlockPos p){
        BlockState normal = TBlocks.COPPER_TABLE.get().defaultBlockState();
        BlockState ok = TBlocks.EXPOSED_COPPER_TABLE.get().defaultBlockState();
        BlockState bad = TBlocks.WEATHERED_COPPER_TABLE.get().defaultBlockState();
        BlockState awful = TBlocks.OXIDIZED_COPPER_TABLE.get().defaultBlockState();

        awful.setValue(NORTH,bs.getValue(NORTH));
        awful.setValue(SOUTH,bs.getValue(SOUTH));
        awful.setValue(EAST,bs.getValue(EAST));
        awful.setValue(WEST,bs.getValue(WEST));

        bad.setValue(NORTH,bs.getValue(NORTH));
        bad.setValue(SOUTH,bs.getValue(SOUTH));
        bad.setValue(EAST,bs.getValue(EAST));
        bad.setValue(WEST,bs.getValue(WEST));

        ok.setValue(NORTH,bs.getValue(NORTH));
        ok.setValue(SOUTH,bs.getValue(SOUTH));
        ok.setValue(EAST,bs.getValue(EAST));
        ok.setValue(WEST,bs.getValue(WEST));

        normal.setValue(NORTH,bs.getValue(NORTH));
        normal.setValue(SOUTH,bs.getValue(SOUTH));
        normal.setValue(EAST,bs.getValue(EAST));
        normal.setValue(WEST,bs.getValue(WEST));

        // states to choose from
        if(bs.is(TBlocks.WAXED_OXIDIZED_COPPER_TABLE.get())){
            l.setBlock(p,awful.setValue(WATERLOGGED,bs.getValue(WATERLOGGED)),3);
        }
        else if(bs.is(TBlocks.WAXED_WEATHERED_COPPER_TABLE.get())){
            l.setBlock(p,bad.setValue(WATERLOGGED,bs.getValue(WATERLOGGED)),3);
        }
        else if(bs.is(TBlocks.WAXED_EXPOSED_COPPER_TABLE.get())){
            l.setBlock(p,ok.setValue(WATERLOGGED,bs.getValue(WATERLOGGED)),3);
        }
        else if(bs.is(TBlocks.WAXED_COPPER_TABLE.get())){
            l.setBlock(p,normal.setValue(WATERLOGGED,bs.getValue(WATERLOGGED)),3);
        }
    }

    @Override
    public BlockState updateShape(BlockState bs, Direction d, BlockState bs2, LevelAccessor la, BlockPos bp, BlockPos bp2){
        List<Block> coppertables = List.of(
                TBlocks.WAXED_COPPER_TABLE.get(),
                TBlocks.WAXED_EXPOSED_COPPER_TABLE.get(),
                TBlocks.WAXED_WEATHERED_COPPER_TABLE.get(),
                TBlocks.WAXED_OXIDIZED_COPPER_TABLE.get(),
                TBlocks.COPPER_TABLE.get(),
                TBlocks.EXPOSED_COPPER_TABLE.get(),
                TBlocks.WEATHERED_COPPER_TABLE.get(),
                TBlocks.OXIDIZED_COPPER_TABLE.get()
        );

        boolean n = la.getBlockState(bp.north()).getBlock() ==
                this ||
                la.getBlockState(bp.north()).getBlock() == coppertables.get(0) ||
                la.getBlockState(bp.north()).getBlock() == coppertables.get(1) ||
                la.getBlockState(bp.north()).getBlock() == coppertables.get(2) ||
                la.getBlockState(bp.north()).getBlock() == coppertables.get(3) ||
                la.getBlockState(bp.north()).getBlock() == coppertables.get(4) ||
                la.getBlockState(bp.north()).getBlock() == coppertables.get(5) ||
                la.getBlockState(bp.north()).getBlock() == coppertables.get(6) ||
                la.getBlockState(bp.north()).getBlock() == coppertables.get(7);
        boolean s = la.getBlockState(bp.south()).getBlock() ==
                this ||
                la.getBlockState(bp.south()).getBlock() == coppertables.get(0) ||
                la.getBlockState(bp.south()).getBlock() == coppertables.get(1) ||
                la.getBlockState(bp.south()).getBlock() == coppertables.get(2) ||
                la.getBlockState(bp.south()).getBlock() == coppertables.get(3) ||
                la.getBlockState(bp.south()).getBlock() == coppertables.get(4) ||
                la.getBlockState(bp.south()).getBlock() == coppertables.get(5) ||
                la.getBlockState(bp.south()).getBlock() == coppertables.get(6) ||
                la.getBlockState(bp.south()).getBlock() == coppertables.get(7);
        boolean e = la.getBlockState(bp.east()).getBlock() ==
                this ||
                la.getBlockState(bp.east()).getBlock() == coppertables.get(0) ||
                la.getBlockState(bp.east()).getBlock() == coppertables.get(1) ||
                la.getBlockState(bp.east()).getBlock() == coppertables.get(2) ||
                la.getBlockState(bp.east()).getBlock() == coppertables.get(3) ||
                la.getBlockState(bp.east()).getBlock() == coppertables.get(4) ||
                la.getBlockState(bp.east()).getBlock() == coppertables.get(5) ||
                la.getBlockState(bp.east()).getBlock() == coppertables.get(6) ||
                la.getBlockState(bp.east()).getBlock() == coppertables.get(7);
        boolean w = la.getBlockState(bp.west()).getBlock() ==
                this ||
                la.getBlockState(bp.west()).getBlock() == coppertables.get(0) ||
                la.getBlockState(bp.west()).getBlock() == coppertables.get(1) ||
                la.getBlockState(bp.west()).getBlock() == coppertables.get(2) ||
                la.getBlockState(bp.west()).getBlock() == coppertables.get(3) ||
                la.getBlockState(bp.west()).getBlock() == coppertables.get(4) ||
                la.getBlockState(bp.west()).getBlock() == coppertables.get(5) ||
                la.getBlockState(bp.west()).getBlock() == coppertables.get(6) ||
                la.getBlockState(bp.west()).getBlock() == coppertables.get(7);

        return bs.setValue(NORTH,n).setValue(SOUTH,s).setValue(EAST,e).setValue(WEST,w);
    }
}
