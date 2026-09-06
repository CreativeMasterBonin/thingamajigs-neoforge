package net.rk.thingamajigs.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class Mode {
    public final String name;

    public Mode(String name){
        this.name = name;
    }

    public abstract void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player);
}
