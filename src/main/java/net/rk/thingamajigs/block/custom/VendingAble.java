package net.rk.thingamajigs.block.custom;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.item.TItems;

public interface VendingAble{
    /**
     * A method that takes in a payment and returns whether the process succeeded in getting product. All logic must be made by the programmer.
     * @param paymentStack The payment method in order to receive product
     * @param player The player that may be checked for payment items or status
     * @param level The level currently inside of
     * @return Whether the VendingAble gave an item
     */
    boolean vendItem(ItemStack paymentStack, Player player, Level level);

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
}
