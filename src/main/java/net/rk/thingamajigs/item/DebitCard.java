package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.custom.ATMBlock;
import net.rk.thingamajigs.xtras.TCalcStuff;
import net.rk.thingamajigs.xtras.TConfig;

import java.util.List;

public class DebitCard extends Item {
    public static final DataComponentType<Integer> MONEY_COMPONENT = Thingamajigs.MONEY.get();

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack debitCardInstance = new ItemStack(this);
        debitCardInstance.set(MONEY_COMPONENT,0);

        return debitCardInstance;
    }

    public DebitCard(Properties properties) {
        super(properties.stacksTo(1).setNoRepair().fireResistant().rarity(Rarity.EPIC));
    }

    public int getMoney(ItemStack stack){
        return stack.getComponents().has(MONEY_COMPONENT) ? stack.get(MONEY_COMPONENT).intValue() : 0;
    }

    public boolean setMoneyClient(Player player, ItemStack stack, int moneyChangeAmount){
        if(stack.has(Thingamajigs.MONEY.get())){
            if(getMoney(stack) + moneyChangeAmount >= 0){
                player.playSound(SoundEvents.ITEM_PICKUP,0.5f,TCalcStuff.nextFloatBetweenInclusive(0.95f,1.1f));
            }
            else if(getMoney(stack) + moneyChangeAmount < 0){
                player.playSound(SoundEvents.VILLAGER_NO,0.5f,TCalcStuff.nextFloatBetweenInclusive(0.95f,1.1f));
            }
            return getMoney(stack) + moneyChangeAmount >= 0;
        }
        player.playSound(SoundEvents.NETHERITE_BLOCK_HIT,0.5f,1.0f);
        return false;
    }

    public boolean setMoney(Player player, ItemStack stack, int moneyChangeAmount){
        if(stack.has(MONEY_COMPONENT)){
            if(getMoney(stack) + moneyChangeAmount < 0){
                player.displayClientMessage(Component.translatable("item.thingamajigs.debit_card.insufficient_funds",moneyChangeAmount,getMoney(stack)).withStyle(ChatFormatting.RED),true);
                return false;
            }
            else if(getMoney(stack) + moneyChangeAmount > getMaxMoney()){
                player.displayClientMessage(Component.translatable("item.thingamajigs.debit_card.exceeding_wealth",moneyChangeAmount,getMoney(stack)).withStyle(ChatFormatting.GOLD),true);
                return false;
            }
            int newMoney = Mth.clamp(getMoney(stack) + moneyChangeAmount,0,getMaxMoney());
            stack.set(MONEY_COMPONENT,newMoney);
            return true;
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(stack.has(MONEY_COMPONENT)){
            if(getMoney(stack) <= 0){
                tooltipComponents.add(Component.translatable("item.thingamajigs.debit_card.stored_money.title",getMoney(stack))
                        .withStyle(ChatFormatting.RED));
            }
            else{
                tooltipComponents.add(Component.translatable("item.thingamajigs.debit_card.stored_money.title",getMoney(stack))
                        .withStyle(ChatFormatting.GREEN));
            }
        }
        else{
            tooltipComponents.add(Component.translatable("item.thingamajigs.debit_card.desc.inactive")
                    .withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if(player.getOffhandItem().isEmpty()){
            if(level.isClientSide()){
                if(!player.getItemInHand(usedHand).has(Thingamajigs.MONEY)){
                    player.playSound(SoundEvents.VILLAGER_CELEBRATE,0.5f,TCalcStuff.nextFloatBetweenInclusive(0.97f,1.0f));
                    return InteractionResultHolder.success(player.getItemInHand(usedHand));
                }
                else{
                    player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP,0.5f,TCalcStuff.nextFloatBetweenInclusive(0.95f,1.1f));
                }
            }
            else{
                if(!player.getItemInHand(usedHand).has(Thingamajigs.MONEY)){
                    player.getItemInHand(usedHand).set(Thingamajigs.MONEY,0);
                    player.displayClientMessage(Component.translatable("message.thingamajigs.debit_card.activated"),true);
                    return InteractionResultHolder.success(player.getItemInHand(usedHand));
                }
                else{
                    player.displayClientMessage(Component.translatable("message.thingamajigs.debit_card.balance",getMoney(player.getItemInHand(usedHand))),true);
                    return InteractionResultHolder.consume(player.getItemInHand(usedHand));
                }
            }
        }
        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level =  context.getLevel();
        BlockState lookingAtState = level.getBlockState(context.getClickedPos());
        ItemStack itemInHand = context.getItemInHand();
        Player player = context.getPlayer();
        ItemStack offhandStack = player.getOffhandItem();
        InteractionHand hand = player.getUsedItemHand();

        if(!itemInHand.has(MONEY_COMPONENT)){
            return InteractionResult.PASS;
        }

        if(level.isClientSide()){
            if(itemInHand.getItem() instanceof DebitCard){
                if(lookingAtState.getBlock() instanceof ATMBlock){
                    if(offhandStack.is(TItems.MONEY) && hand == InteractionHand.MAIN_HAND) {
                        boolean moneyWasChangedClient = setMoneyClient(player,itemInHand,1);
                        if(moneyWasChangedClient){
                            return InteractionResult.SUCCESS;
                        }
                    }
                    else if(offhandStack.isEmpty() && hand == InteractionHand.MAIN_HAND){
                        boolean moneyWasChangedClient = setMoneyClient(player,itemInHand,-1);
                        if(moneyWasChangedClient){
                            return InteractionResult.SUCCESS;
                        }
                        else{
                            player.playSound(SoundEvents.VILLAGER_NO,0.5f,TCalcStuff.nextFloatBetweenInclusive(0.95f,1.0f));
                            return InteractionResult.CONSUME;
                        }
                    }
                }
            }
        }
        else{
            if(itemInHand.getItem() instanceof DebitCard){
                int previousBalance = itemInHand.get(Thingamajigs.MONEY).intValue();
                if(lookingAtState.getBlock() instanceof ATMBlock){
                    if(offhandStack.is(TItems.MONEY) && hand == InteractionHand.MAIN_HAND){
                        boolean moneyWasChanged = setMoney(player,itemInHand,1);
                        offhandStack.shrink(1);
                        if(moneyWasChanged){
                            player.displayClientMessage(
                                    Component.translatable("message.thingamajigs.debit_card.changed_balance",
                                            previousBalance,itemInHand.get(MONEY_COMPONENT).intValue()),true);
                            return InteractionResult.SUCCESS;
                        }
                    }
                    else if(offhandStack.isEmpty() && hand == InteractionHand.MAIN_HAND){
                        boolean moneyWasChanged = setMoney(player,itemInHand,-1);
                        if(moneyWasChanged){
                            ItemEntity inWorldItem = new ItemEntity(level,player.getX(),player.getY(),player.getZ(),new ItemStack(TItems.MONEY.asItem()),0D,0.1D,0D);
                            level.addFreshEntity(inWorldItem);
                            player.displayClientMessage(
                                    Component.translatable("message.thingamajigs.debit_card.changed_balance",
                                            previousBalance,itemInHand.get(MONEY_COMPONENT).intValue()),true);
                            return InteractionResult.SUCCESS;
                        }
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    public int getMaxMoney(){
        if(TConfig.maxMoneyOnDebitCards.getAsInt() > Integer.MAX_VALUE){ // just in case
            return Integer.MAX_VALUE;
        }
        else if(TConfig.maxMoneyOnDebitCards.getAsInt() < 100){ // hard limit as to allow players to at least buy something valuable
            return 100;
        }
        return TConfig.maxMoneyOnDebitCards.getAsInt();
    }

    @Override
    public void onCraftedPostProcess(ItemStack stack, Level level) {
        stack.set(Thingamajigs.MONEY,0);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return stack.getComponents().has(Thingamajigs.MONEY.get());
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        if(stack.has(MONEY_COMPONENT)){
            return 13;
        }
        return 0;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if(stack.has(MONEY_COMPONENT)){
            float convertedFloatMoney = (float)stack.get(Thingamajigs.MONEY).intValue();
            float newVal = TCalcStuff.convertFloatRangeToOther(convertedFloatMoney,0f,(float)getMaxMoney(),0.0f,1.0f);
            return FastColor.ARGB32.colorFromFloat(1.0f,0.5f,newVal,newVal);
        }
        return super.getBarColor(stack);
    }
}
