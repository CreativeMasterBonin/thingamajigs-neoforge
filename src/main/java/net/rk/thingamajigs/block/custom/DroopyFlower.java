package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.rk.thingamajigs.xtras.TSoundEvent;

import java.util.List;

public class DroopyFlower extends FlowerBlock{
    public DroopyFlower(Holder<MobEffect> effect,Properties p){
        super(makeEffectList(effect, 2.0f), p);
    }

    @Override
    protected void entityInside(BlockState bs, Level lvl, BlockPos bp, Entity ent) {
        if (!lvl.isClientSide) {
            if(lvl.getDifficulty() != Difficulty.PEACEFUL){
                if(ent instanceof LivingEntity le) {
                    le.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 275,0,false,false));
                }
            }
        }
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if(!player.isCreative()){
            if(level.getDifficulty() != Difficulty.PEACEFUL){
                level.playSound(null,pos, TSoundEvent.POOP.get(), SoundSource.BLOCKS,2F, 0.95F);
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 275, 0, false ,false));
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public void appendHoverText(ItemStack is, Item.TooltipContext itc, List<Component> list, TooltipFlag tf) {
        list.add(Component.translatable("block.droopy_flower.desc"));
    }
}
