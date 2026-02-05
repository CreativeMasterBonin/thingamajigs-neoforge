package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Calendar;
import java.util.Date;

public class OldPC extends ThingamajigsDecorativeBlock{
    public OldPC(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    public boolean getDateJoke(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.MONTH) == Calendar.APRIL && calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack is, BlockState bs, Level lvl, BlockPos bp, Player p, InteractionHand h, BlockHitResult bhr) {
        ItemStack itemstack = p.getItemInHand(h);
        boolean changed = false;

        // MUST BEE SERVER-SIDE ONLY to work! at least for blocks
        if (!lvl.isClientSide) {
            if(p.getAbilities().mayBuild){
                if(itemstack.getItem() == Items.DANGER_POTTERY_SHERD){
                    BlockState selfisstate = bs;
                    lvl.updateNeighborsAt(bp,this);
                    changed = true;
                    if(getDateJoke()){
                        p.displayClientMessage(Component.translatable("block.thingamajigs.old_pc.use_ok"),true);
                        lvl.setBlock(bp, Blocks.AIR.defaultBlockState(), 2);
                        PrimedTnt primedtnt = new PrimedTnt(lvl,bp.getX(),bp.getY(),bp.getZ(),p);
                        primedtnt.setBlockState(selfisstate);
                        int i = primedtnt.getFuse();
                        primedtnt.setFuse((short)(lvl.random.nextInt(i / 4) + i / 8));
                        lvl.addFreshEntity(primedtnt);
                    }
                    else{
                        p.displayClientMessage(Component.translatable("block.thingamajigs.old_pc.use"), true);
                    }
                }
                else{
                    if(p.getItemInHand(h) == ItemStack.EMPTY){
                        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                    }
                }
                // update THIS block, no matter what happens
                if(changed){
                    lvl.playSound(null,bp,SoundEvents.ITEM_FRAME_ADD_ITEM,SoundSource.BLOCKS,1.0F,1.0F);
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
