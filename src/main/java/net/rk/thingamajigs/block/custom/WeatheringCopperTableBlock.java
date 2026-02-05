package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.xtras.TWeatheringCopperOther;

import java.util.List;

public class WeatheringCopperTableBlock extends ConnectedTableBlock implements TWeatheringCopperOther{
    private final TWeatheringCopperOther.State ruststate;

    public WeatheringCopperTableBlock(TWeatheringCopperOther.State rs, BlockBehaviour.Properties p) {
        super(p.mapColor(MapColor.COLOR_ORANGE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 6.0F)
                .sound(SoundType.COPPER));
        this.ruststate = rs;
    }

    @Override
    public void randomTick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        this.changeOverTime(bs,sl,bp,rs);
    }

    public boolean isRandomlyTicking(BlockState bsr1) {
        return TWeatheringCopperOther.getNext(bsr1.getBlock()).isPresent();
    }

    public TWeatheringCopperOther.State getAge() {
        return this.ruststate;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level level, BlockPos pos, Player player, BlockHitResult bhr) {
        BlockState normal = TBlocks.COPPER_TABLE.get().defaultBlockState();
        BlockState ok = TBlocks.EXPOSED_COPPER_TABLE.get().defaultBlockState();
        BlockState bad = TBlocks.WEATHERED_COPPER_TABLE.get().defaultBlockState();
        BlockState current = bs;
        boolean successful = false;

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

        current.setValue(NORTH,bs.getValue(NORTH));
        current.setValue(SOUTH,bs.getValue(SOUTH));
        current.setValue(EAST,bs.getValue(EAST));
        current.setValue(WEST,bs.getValue(WEST));

        InteractionHand hand = player.getUsedItemHand();

        if(player.getItemInHand(player.getUsedItemHand()).is(Items.AIR)){
            return InteractionResult.PASS;
        }

        if(player.getItemInHand(hand).is(ItemTags.AXES)){
            if(level.getBlockState(pos).is(TBlocks.OXIDIZED_COPPER_TABLE.get())){
                level.setBlock(pos,bad.setValue(WATERLOGGED,current.getValue(WATERLOGGED)),3);

                if(player.getMainHandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }
                else if(player.getOffhandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.OFFHAND);
                }

                successful = true;
            }
            else if(level.getBlockState(pos).is(TBlocks.WEATHERED_COPPER_TABLE.get())){
                level.setBlock(pos,ok.setValue(WATERLOGGED,current.getValue(WATERLOGGED)),3);

                if(player.getMainHandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.MAINHAND);
                }
                else if(player.getOffhandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.OFFHAND);
                }

                successful = true;
            }
            else if(level.getBlockState(pos).is(TBlocks.EXPOSED_COPPER_TABLE.get())){
                level.setBlock(pos,normal.setValue(WATERLOGGED,current.getValue(WATERLOGGED)),3);

                if(player.getMainHandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.MAINHAND);
                }
                else if(player.getOffhandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.OFFHAND);
                }

                successful = true;
            }
            if(successful){
                level.playSound(null,pos, SoundEvents.AXE_SCRAPE, SoundSource.PLAYERS,1.0F,1.0F);
                ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.SCRAPE, UniformInt.of(3, 5));
                player.swing(hand);
            }
            return InteractionResult.CONSUME;
        }
        else if(player.getItemInHand(hand).is(Items.HONEYCOMB)){
            if(!player.isCreative()){
                player.getItemInHand(hand).shrink(1);
            }
            applyWaxItemToBlock(bs,level,pos); // apply the wax to the block, converting it to a waxed block variant
            // level renderer
            level.playLocalSound(pos,SoundEvents.HONEYCOMB_WAX_ON,SoundSource.PLAYERS,1.0F,1.0F,false);
            ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.WAX_ON, UniformInt.of(3, 5));
            // end
            player.swing(hand);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    public void applyWaxItemToBlock(BlockState bs, Level lvl, BlockPos bp){
        BlockState norm = bs;

        BlockState w_normal = TBlocks.WAXED_COPPER_TABLE.get().defaultBlockState();
        BlockState w_ok = TBlocks.WAXED_EXPOSED_COPPER_TABLE.get().defaultBlockState();
        BlockState w_bad = TBlocks.WAXED_WEATHERED_COPPER_TABLE.get().defaultBlockState();
        BlockState w_awful = TBlocks.WAXED_OXIDIZED_COPPER_TABLE.get().defaultBlockState();

        w_awful.setValue(NORTH,bs.getValue(NORTH));
        w_awful.setValue(SOUTH,bs.getValue(SOUTH));
        w_awful.setValue(EAST,bs.getValue(EAST));
        w_awful.setValue(WEST,bs.getValue(WEST));

        w_bad.setValue(NORTH,bs.getValue(NORTH));
        w_bad.setValue(SOUTH,bs.getValue(SOUTH));
        w_bad.setValue(EAST,bs.getValue(EAST));
        w_bad.setValue(WEST,bs.getValue(WEST));

        w_ok.setValue(NORTH,bs.getValue(NORTH));
        w_ok.setValue(SOUTH,bs.getValue(SOUTH));
        w_ok.setValue(EAST,bs.getValue(EAST));
        w_ok.setValue(WEST,bs.getValue(WEST));

        w_normal.setValue(NORTH,bs.getValue(NORTH));
        w_normal.setValue(SOUTH,bs.getValue(SOUTH));
        w_normal.setValue(EAST,bs.getValue(EAST));
        w_normal.setValue(WEST,bs.getValue(WEST));

        if(norm.is(TBlocks.OXIDIZED_COPPER_TABLE.get())){
            lvl.setBlock(bp,w_awful.setValue(WATERLOGGED,bs.getValue(WATERLOGGED)),3);
        }
        else if(norm.is(TBlocks.WEATHERED_COPPER_TABLE.get())){
            lvl.setBlock(bp,w_bad.setValue(WATERLOGGED,bs.getValue(WATERLOGGED)),3);
        }
        else if(norm.is(TBlocks.EXPOSED_COPPER_TABLE.get())){
            lvl.setBlock(bp,w_ok.setValue(WATERLOGGED,bs.getValue(WATERLOGGED)),3);
        }
        else if(norm.is(TBlocks.COPPER_TABLE.get())){
            lvl.setBlock(bp,w_normal.setValue(WATERLOGGED,bs.getValue(WATERLOGGED)),3);
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
