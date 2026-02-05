package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

@SuppressWarnings("deprecated")
public class WardenTrophy extends ThingamajigsDecorativeBlock{
    public WardenTrophy(Properties properties) {
        super(properties.sound(SoundType.SCULK_SHRIEKER).strength(1.0F,50F).noCollission().noOcclusion());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState p_60503_, Level level, BlockPos pos, Player player, BlockHitResult p_60508_) {
        if(player.isShiftKeyDown()){
            level.playSound(null,pos, SoundEvents.WARDEN_ANGRY, SoundSource.BLOCKS,1.0F,1.0F);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack is, Item.TooltipContext tc, List<Component> list, TooltipFlag tf) {
        super.appendHoverText(is,tc,list,tf);
        list.add(Component.translatable("tooltip.thingamajigs.warden_trophy"));
    }
}
