package net.rk.thingamajigs.block.custom;

import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.datagen.TTag;
import net.rk.thingamajigs.item.TItems;
import net.rk.thingamajigs.xtras.TCalcStuff;

import java.util.List;
import java.util.Optional;

public class SodaVendingMachine extends DoubleTallDecorationBlock implements VendingAble{
    public SodaVendingMachine(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide()){
            ItemStack itemInHand = player.getItemInHand(player.getUsedItemHand());
            if(itemInHand.is(TItems.MONEY)){
                player.playSound(SoundEvents.VAULT_INSERT_ITEM,1.0f, TCalcStuff.nextFloatBetweenInclusive(0.97f,1.2f));
                return InteractionResult.SUCCESS;
            }
            else if(itemInHand.is(TItems.DEBIT_CARD)){
                if(itemInHand.has(Thingamajigs.MONEY)){
                    if(itemInHand.get(Thingamajigs.MONEY).intValue() - 2 >= 0){
                        player.playSound(SoundEvents.VAULT_INSERT_ITEM,1.0f, TCalcStuff.nextFloatBetweenInclusive(0.97f,1.2f));
                        return InteractionResult.SUCCESS;
                    }
                    else{
                        player.playSound(SoundEvents.VAULT_INSERT_ITEM_FAIL,1.0f,0.75f);
                    }
                }
                else{
                    player.playSound(SoundEvents.VAULT_INSERT_ITEM_FAIL,1.0f,0.97f);
                }
            }
        }
        else{
            return vendItem(player.getItemInHand(player.getUsedItemHand()),player,level) ? InteractionResult.SUCCESS : InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean vendItem(ItemStack paymentStack, Player player, Level level){
        if(paymentStack.is(TItems.MONEY) && paymentStack.getCount() >= 4){
            paymentStack.shrink(4);
            ItemStack productToBeDispensed = randomlyCreateVendingItemStack(level.getRandom());
            ItemEntity inWorldItem = new ItemEntity(level,
                    player.getX(),player.getY(),player.getZ(),
                    productToBeDispensed,0D,0.1D,0D);
            level.addFreshEntity(inWorldItem);

            player.displayClientMessage(Component.translatable("message.thingamajigs.vending_machine.success")
                    .withStyle(ChatFormatting.ITALIC),true);
            return true;
        }
        else if(paymentStack.is(TItems.DEBIT_CARD)){
            if(!paymentStack.has(Thingamajigs.MONEY)){
                player.displayClientMessage(Component.translatable("message.thingamajigs.vending_machine.failed.card_not_ready")
                        .withStyle(ChatFormatting.ITALIC),true);
                return false;
            }
            else{
                if(paymentStack.get(Thingamajigs.MONEY).intValue() - 2 >= 0){
                    paymentStack.set(Thingamajigs.MONEY.get(),paymentStack.get(Thingamajigs.MONEY).intValue() - 2);
                    ItemStack productToBeDispensed = randomlyCreateVendingItemStack(level.getRandom());
                    ItemEntity inWorldItem = new ItemEntity(level,
                            player.getX(),player.getY(),player.getZ(),
                            productToBeDispensed,0D,0.1D,0D);
                    level.addFreshEntity(inWorldItem);
                    player.displayClientMessage(Component.translatable("message.thingamajigs.vending_machine.success")
                            .withStyle(ChatFormatting.ITALIC),true);
                }
                else{
                    player.displayClientMessage(Component.translatable("message.thingamajigs.vending_machine.failed.need_money")
                            .withStyle(ChatFormatting.ITALIC),true);
                    return false;
                }
                return true;
            }
        }
        else{
            player.displayClientMessage(Component.translatable("message.thingamajigs.vending_machine.failed.unacceptable_payment_method")
                    .withStyle(ChatFormatting.ITALIC),true);
        }
        return false;
    }

    @Override
    public ItemStack randomlyCreateVendingItemStack(RandomSource randomSource) {
        ItemStack potion = new ItemStack(Items.POTION); // make a new potion item stack
        potion.setCount(1); // default count of '1'
        potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.name")); // default name of 'Soda'
        potion.set(DataComponents.POTION_CONTENTS,PotionContents.EMPTY); // default potion contents of 'empty'

        // set the ticks, amplifier, and color that the potion will have
        int timeInTicks = TCalcStuff.nextIntBetweenInclusive(192,512);
        int amplifierAmount = TCalcStuff.nextIntBetweenInclusive(0,3);
        int randomColor = TCalcStuff.nextIntBetweenInclusive(1,16777214);

        // 2026: got help here from the 'evil Curle', credit where credit is due; fixed logic to be less janky
        Holder<MobEffect> randomMobEffectHolder = null; // initialize the holder
        // while the effect is still disallowed, get a new effect
        do{
            randomMobEffectHolder = BuiltInRegistries.MOB_EFFECT.getRandom(randomSource).get();
        }
        while (randomMobEffectHolder.is(TTag.DISALLOWED_IN_VENDING_MACHINES));
        // make a list of mob effects (even though it is only a singular effect)
        List<MobEffectInstance> randomEffectInstance = List.of(new MobEffectInstance(
                randomMobEffectHolder,timeInTicks,amplifierAmount,false,true));

        // give the item a unique name based on effect
        if(randomMobEffectHolder.is(MobEffects.CONFUSION)){
            potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.disgusting.name"));
        }
        else if(randomMobEffectHolder.is(MobEffects.BLINDNESS)){
            potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.blinding.name"));
        }
        else if(randomMobEffectHolder.is(MobEffects.HARM)){
            potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.unhealthy.name"));
        }
        else if(randomMobEffectHolder.is(MobEffects.HEAL)){
            potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.healthy.name"));
        }
        else if(randomMobEffectHolder.is(MobEffects.DARKNESS)){
            potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.darkening.name"));
        }
        else if(randomMobEffectHolder.is(MobEffects.DIG_SLOWDOWN)){
            potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.obnoxious.name"));
        }
        else if(randomMobEffectHolder.is(MobEffects.HUNGER)){
            potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.atrocious.name"));
        }
        else if(randomMobEffectHolder.is(MobEffects.JUMP)){
            potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.silly.name"));
        }
        else if(randomMobEffectHolder.is(MobEffects.SLOW_FALLING)){
            potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.strange.name"));
        }
        else{ // for effects that are not hardcoded with names
            if(randomMobEffectHolder.value().isBeneficial()){
                if(randomSource.nextBoolean()){
                    potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.helpful.name"));
                }
                else{
                    potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.name"));
                }
            }
            else{
                if(randomSource.nextBoolean()){
                    potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.horrible.name"));
                }
                else{
                    potion.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.soda.name"));
                }
            }
        }

        // set the potion effects inside the potion item
        potion.set(DataComponents.POTION_CONTENTS,new PotionContents(
                Optional.of(Potions.WATER),
                Optional.of(randomColor),
                randomEffectInstance
        ));

        return potion;
    }
}
