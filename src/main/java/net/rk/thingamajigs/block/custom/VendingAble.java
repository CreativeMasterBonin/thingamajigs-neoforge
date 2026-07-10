package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.item.TItems;

public interface VendingAble{
    /**
     * A method that takes in a payment and returns whether the process succeeded in getting product. All logic must be made by the programmer.
     * @param paymentStack The payment method in order to receive product
     * @param player The player that may be checked for payment items or status
     * @param level The level currently inside of
     * @return Whether the VendingAble gave an item
     */
    public default boolean vendItem(ItemStack paymentStack, Player player, Level level){
        if(paymentStack.is(TItems.MONEY) && paymentStack.getCount() >= this.purchaseMoneyAmount(paymentStack)){
            paymentStack.shrink(this.purchaseMoneyAmount(paymentStack));
            ItemStack productToBeDispensed = randomlyCreateVendingItemStack(level.getRandom());
            ItemEntity inWorldItem = new ItemEntity(level,
                    player.getX(),player.getY(),player.getZ(),
                    productToBeDispensed,this.getProductDeltaMovement().x,this.getProductDeltaMovement().y,this.getProductDeltaMovement().z);
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
                if(paymentStack.get(Thingamajigs.MONEY).intValue() - this.purchaseMoneyAmount(paymentStack) >= 0){
                    paymentStack.set(Thingamajigs.MONEY.get(),paymentStack.get(Thingamajigs.MONEY).intValue() - this.purchaseMoneyAmount(paymentStack));
                    ItemStack productToBeDispensed = randomlyCreateVendingItemStack(level.getRandom());
                    ItemEntity inWorldItem = new ItemEntity(level,
                            player.getX(),player.getY(),player.getZ(),
                            productToBeDispensed,this.getProductDeltaMovement().x,this.getProductDeltaMovement().y,this.getProductDeltaMovement().z);
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

    /**
     * Randomly create an ItemStack based on a RandomSource. All logic must be made by the programmer.
     * @return The ItemStack to use for dispensing
     */
    ItemStack randomlyCreateVendingItemStack(RandomSource randomSource);

    public default void dispenseItem(ItemStack itemStack, Vec3 pos, Vec3 deltaMovement, Level level){
        if(!level.isClientSide()){
            ItemEntity product = new ItemEntity(level,pos.x,pos.y,pos.z,itemStack,deltaMovement.x,deltaMovement.y,deltaMovement.z);
            level.addFreshEntity(product);
        }
    }

    public default int purchaseMoneyAmount(ItemStack itemStack){
        return 1;
    }

    public default Vec3 getProductDeltaMovement(){
        return new Vec3(0D,0.1D,0D);
    }
}
