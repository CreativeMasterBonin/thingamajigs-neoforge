package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.item.TItems;
import net.rk.thingamajigs.xtras.TConfig;

import java.util.List;

public class ChangeMachine extends ThingamajigsDecorativeBlock{
    public ChangeMachine(Properties p) {
        super(p.sound(SoundType.METAL).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.1f,10f));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("block.thingamajigs.change_machine.desc")
                .withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(Component.translatable("generic.thingamajigs.coin_values.desc")
                .withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack is, BlockState bs, Level lvl, BlockPos bp, Player p, InteractionHand ih, BlockHitResult bhr) {
        if(lvl.isClientSide()){
            if(!TConfig.moneyExchangeEnabled.get()){
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if(bs.getValue(WATERLOGGED)){
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }

            ItemStack itsm = p.getItemInHand(ih);
            boolean gem = itsm.is(Items.EMERALD);
            boolean money = itsm.is(TItems.MONEY.asItem());
            boolean coin = itsm.is(TItems.COIN.asItem());

            if(money){
                p.playSound(SoundEvents.TRIAL_SPAWNER_HIT,1.0f,0.7f);
                return ItemInteractionResult.SUCCESS;
            }

            if(gem){
                p.playSound(SoundEvents.ITEM_PICKUP,1.0f,0.5f);
                return ItemInteractionResult.SUCCESS;
            }

            if(coin){
                if(itsm.getCount() >= 4){
                    p.playSound(SoundEvents.TRIAL_SPAWNER_FALL,1.0f,0.9f);
                    return ItemInteractionResult.SUCCESS;
                }
                else{
                    p.playSound(SoundEvents.VAULT_INSERT_ITEM_FAIL,1.0f,0.7f);
                }
            }
        }
        else{
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
            boolean coin = itsm.is(TItems.COIN.asItem());
            boolean didMakeTransaction = false;

            if(money){
                itsm.shrink(1);
                if(itsm.isEmpty()) {
                    p.setItemInHand(ih, new ItemStack(TItems.COIN.asItem(),4));
                }
                else if(!p.getInventory().add(new ItemStack(TItems.COIN.asItem(),4))) {
                    ItemEntity entity = new ItemEntity(lvl,bp.getX(),bp.getY(),bp.getZ(),new ItemStack(TItems.COIN.asItem(),4),0D,0.1D,0D);
                    lvl.addFreshEntity(entity);
                }

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
                    ItemStack stack = new ItemStack(TItems.MONEY.asItem());
                    stack.setCount(4);
                    p.setItemInHand(ih,stack);
                }
                else if(!p.getInventory().add(new ItemStack(TItems.MONEY.asItem()))) {
                    ItemStack moneyStack = new ItemStack(TItems.MONEY.asItem());
                    moneyStack.setCount(4);
                    ItemEntity entity = new ItemEntity(lvl,bp.getX(),bp.getY(),bp.getZ(),moneyStack,0D,0.1D,0D);
                    lvl.addFreshEntity(entity);
                }
                didMakeTransaction = true;
                return ItemInteractionResult.SUCCESS;
            }

            if(coin && itsm.getCount() >= 4){
                itsm.shrink(4);
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
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
