package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;

public class WeightScale extends ThingamajigsDecorativeBlock{
    public WeightScale(Properties p) {
        super(p.sound(SoundType.LANTERN).noOcclusion().instrument(NoteBlockInstrument.BASS));
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(player.isSecondaryUseActive()){
            if(!level.isClientSide()){
                int totalWeightOfItems = 0;
                for(int weightSlotIndex = 0; weightSlotIndex < player.getInventory().getContainerSize() - 1; weightSlotIndex++){
                    if(player.getInventory().getItem(weightSlotIndex) instanceof ItemStack){
                        if(player.getInventory().getItem(weightSlotIndex) != ItemStack.EMPTY){
                            totalWeightOfItems += player.getInventory().getItem(weightSlotIndex).getCount();
                        }
                    }
                }
                player.displayClientMessage(Component.translatable(
                        "block.thingamajigs.weight_scale.weight_is",totalWeightOfItems),true);
                return InteractionResult.CONSUME;
            }
            else{
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
