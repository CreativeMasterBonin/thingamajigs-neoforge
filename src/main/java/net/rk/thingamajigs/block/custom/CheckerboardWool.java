package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

@SuppressWarnings("deprecated")
public class CheckerboardWool extends Block{
    public CheckerboardWool(Properties p) {
        super(p.strength(0.8F).sound(SoundType.WOOL));
    }

    @Override
    public float getJumpFactor() {
        return 1.2F;
    }

    @Override
    public float getSpeedFactor() {
        return 1.1F;
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(Component.translatable("block.checkerboard_wool.desc"));
    }

    // we want the checkerboard wool when stepped on to give entities (who can) obtain helpful effects for fast sneaking (without enchanted boots)
    @Override
    public void stepOn(Level lvl, BlockPos bp, BlockState bs, Entity ent1) {
        if(ent1 instanceof LivingEntity){
            if(((LivingEntity) ent1).isAffectedByPotions() && !ent1.isSteppingCarefully()){
                ((LivingEntity) ent1).addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SPEED,
                        10,
                        2,
                        true,
                        false,
                        false));
            }
        }
    }
}
