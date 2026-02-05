package net.rk.thingamajigs.block.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.material.MapColor;

import java.util.List;

public class DarkCrystalBlock extends AncientRelicCrystalBlock{
    public DarkCrystalBlock(Properties p) {
        super(p.mapColor(MapColor.COLOR_PURPLE).strength(47F,2200F));
    }
    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
        list.add(Component.translatable("block.thingamajigs.dark_crystal_block.desc"));
    }
}
