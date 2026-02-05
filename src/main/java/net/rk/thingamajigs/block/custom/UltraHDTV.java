package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.xtras.TSoundEvent;

import java.util.List;

@SuppressWarnings("deprecated")
public class UltraHDTV extends ThingamajigsDecorativeBlock{
    public UltraHDTV(Properties properties) {
        super(properties.strength(0.75F,1.25F).sound(SoundType.LANTERN).noOcclusion().noCollission());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState p_60503_, Level lvl, BlockPos bp, Player p, BlockHitResult p_60508_) {
        if(p.getItemInHand(p.getUsedItemHand()).is(Items.AIR)){
            p.displayClientMessage(Component.translatable("block.uhd_tv.message"),true);
            lvl.playSound(p,bp,TSoundEvent.POOP.get(),SoundSource.BLOCKS,1f,1f);
            return InteractionResult.CONSUME;
        }
        else{
            return InteractionResult.PASS;
        }
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
        list.add(Component.translatable("block.uhd_tv.desc"));
    }
}
