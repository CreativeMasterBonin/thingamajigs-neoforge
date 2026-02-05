package net.rk.thingamajigs.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.rk.thingamajigs.xtras.TConfig;

import java.util.List;

public class ThingamajigItem extends Item {
    public ThingamajigItem(Properties pProperties) {
        super(pProperties);
    }

    public boolean isFoil(ItemStack pStack) {
        return true;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        try{
            return TConfig.maxThingamajigsStackSize.get();
        }
        catch(Exception e){
            return 64;
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.thingamajigs.thingamajig"));
    }
}
