package net.rk.thingamajigs.block.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;

public class SodaVendingMachine extends DoubleTallDecorationBlock implements VendingAble{
    public SodaVendingMachine(Properties properties) {
        super(properties);
    }

    @Override
    public boolean vendItem(ItemStack paymentStack, Player player, Level level){
        return false;
    }

    @Override
    public ItemStack randomlyCreateVendingItemStack(RandomSource randomSource) {
        ItemStack potion = new ItemStack(Items.POTION);
        potion.setCount(1);

        potion.set(DataComponents.POTION_CONTENTS,new PotionContents(Potions.AWKWARD));

        return potion;
    }
}
