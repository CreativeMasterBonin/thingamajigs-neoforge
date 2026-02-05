package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.item.TItems;
import net.rk.thingamajigs.xtras.TConfig;

public class ChangeMachine extends ThingamajigsDecorativeBlock{
    public ChangeMachine(Properties p) {
        super(p.sound(SoundType.METAL).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.1f,10f));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack is, BlockState bs, Level lvl, BlockPos bp, Player p, InteractionHand ih, BlockHitResult bhr) {
        if(!TConfig.moneyExchangeEnabled.get()){
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        // can't use a machine underwater
        if(bs.getValue(WATERLOGGED)){
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        ItemStack itsm = p.getItemInHand(ih);
        boolean gem = itsm.is(Items.EMERALD);
        boolean money = itsm.is(TItems.MONEY.asItem());
        boolean didMakeTransaction = false;

        if(money){
            itsm.shrink(1);
            if(itsm.isEmpty()) {
                p.setItemInHand(ih, new ItemStack(TItems.COIN.asItem(),4));
            }
            else if(!p.getInventory().add(new ItemStack(TItems.COIN.asItem(),4))) {
                p.drop(new ItemStack(TItems.COIN.asItem(),4), false);
            }

            lvl.playSound(p,p.getX(),p.getY(),p.getZ(),
                    SoundEvents.TRIAL_SPAWNER_HIT, SoundSource.BLOCKS,
                    1.0F, 0.5F);

            didMakeTransaction = true;
            return ItemInteractionResult.SUCCESS;
        }

        if(gem){
            itsm.shrink(1);
            lvl.playSound(p,p.getX(),p.getY(),p.getZ(),
                    SoundEvents.ITEM_PICKUP,SoundSource.BLOCKS,
                    1.0F, 0.5F);
            // if we have money add more to the stack
            if(itsm.isEmpty()) {
                p.setItemInHand(ih, new ItemStack(TItems.MONEY.asItem()));
            }
            else if(!p.getInventory().add(new ItemStack(TItems.MONEY.asItem()))) {
                p.drop(new ItemStack(TItems.MONEY.asItem()), false);
            }
            didMakeTransaction = true;
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
