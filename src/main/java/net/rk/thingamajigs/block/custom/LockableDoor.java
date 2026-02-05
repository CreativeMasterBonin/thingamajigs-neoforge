package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.item.TItems;

import java.util.List;

public class LockableDoor extends DoorBlock{
    // still trying to find a use for this
    public static final BlockSetType LOCKABLE = new BlockSetType(
                    "lockable",
                    false,
                    false,
                    false,
                    BlockSetType.PressurePlateSensitivity.MOBS,
                    SoundType.NETHERITE_BLOCK,
                    SoundEvents.IRON_DOOR_CLOSE,
                    SoundEvents.IRON_DOOR_OPEN,
                    SoundEvents.IRON_TRAPDOOR_CLOSE,
                    SoundEvents.IRON_TRAPDOOR_OPEN,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
                    SoundEvents.STONE_BUTTON_CLICK_OFF,
                    SoundEvents.STONE_BUTTON_CLICK_ON
            );

    public static BooleanProperty LOCKED = BooleanProperty.create("locked");

    public LockableDoor(Properties p) {
        super(BlockSetType.IRON,p.requiresCorrectToolForDrops().strength(75.0F,100F).noOcclusion().sound(SoundType.NETHERITE_BLOCK));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, false).setValue(HINGE, DoorHingeSide.LEFT).setValue(POWERED, false).setValue(HALF, DoubleBlockHalf.LOWER).setValue(LOCKED,false));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack is, BlockState bs, Level lvl, BlockPos bp, Player ply, InteractionHand ih, BlockHitResult bhr) {
        ItemStack playerItem = ply.getItemInHand(ih);
        BlockState bs2 = lvl.getBlockState(bp.above());
        boolean isLower = bs.getValue(HALF) == DoubleBlockHalf.LOWER;


        if(isLower){
            if(playerItem.is(TItems.KEY.asItem())){
                bs = bs.cycle(LOCKED);
                lvl.setBlock(bp,bs,10);

                if(bs2.is(this)){
                    bs2 = bs2.cycle(LOCKED);
                    lvl.setBlock(bp.above(),bs2,10);
                }
                lvl.playSound(ply,bp,SoundEvents.ARMOR_EQUIP_NETHERITE.value(),SoundSource.BLOCKS,1f,1f);
                ply.swing(ih);
                return ItemInteractionResult.CONSUME;
            }
            else{
                if(!bs.getValue(LOCKED)){
                    bs = bs.cycle(OPEN);
                    lvl.setBlock(bp, bs, 10);
                    if(bs.getValue(OPEN)){
                        lvl.playSound(ply,bp,SoundEvents.COPPER_DOOR_CLOSE,SoundSource.BLOCKS,1f,1f);
                    }
                    else{
                        lvl.playSound(ply,bp,SoundEvents.COPPER_DOOR_OPEN,SoundSource.BLOCKS,1f,1f);
                    }
                    return ItemInteractionResult.sidedSuccess(lvl.isClientSide);
                }
                else{
                    lvl.playSound(null,bp,SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS,0.75F,1.0F);
                    ply.displayClientMessage(Component.translatable("block.thingamajigs.lockable_door.locked"),true);
                    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                }
            }
        }
        else{
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
    }

    // FORCE the block (in a survival or like mode) to NOT be breakable (unless a block from below is broken) when locked
    @Override
    protected float getDestroyProgress(BlockState bs, Player ply, BlockGetter bg, BlockPos bp) {
        float f = -1.0f;
        if (!bs.getValue(LOCKED) && f == -1.0f) {
            return super.getDestroyProgress(bs,ply,bg,bp);
        }
        else if(bs.getValue(LOCKED) && f == -1.0f) {
            return 0.0f;
        }
        return super.getDestroyProgress(bs,ply,bg,bp);
    }

    @Override
    public float defaultDestroyTime() {
        return super.defaultDestroyTime();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult p_52774_) {
        if (bs.getValue(LOCKED)) {
            lvl.playSound(null,bp,SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS,0.75F,1.0F);
            ply.displayClientMessage(Component.translatable("block.thingamajigs.lockable_door.locked"),true);
            return InteractionResult.PASS;
        }
        else {
            bs = bs.cycle(OPEN);
            lvl.setBlock(bp,bs,10);
            if(bs.getValue(OPEN)){
                lvl.playSound(null,bp,SoundEvents.COPPER_DOOR_CLOSE,SoundSource.BLOCKS,1f,1f);
                lvl.gameEvent(ply, this.isOpen(bs) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, bp);
                return InteractionResult.sidedSuccess(lvl.isClientSide);
            }
            else{
                lvl.playSound(null,bp,SoundEvents.COPPER_DOOR_OPEN,SoundSource.BLOCKS,1f,1f);
                lvl.gameEvent(ply, this.isOpen(bs) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, bp);
                return InteractionResult.sidedSuccess(lvl.isClientSide);
            }
        }
    }

    @Override
    protected void neighborChanged(BlockState bs, Level lvl, BlockPos bp, Block blk, BlockPos bp2, boolean p_52781_) {
        boolean flag = lvl.hasNeighborSignal(bp) || lvl.hasNeighborSignal(bp.relative(bs.getValue(HALF) == DoubleBlockHalf.LOWER ? Direction.UP : Direction.DOWN));
        if(bs.getValue(LOCKED) && flag){
            lvl.playSound(null,bp,SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS,0.75F,1.0F);
            return;
        }
        if (!this.defaultBlockState().is(blk) && flag != bs.getValue(POWERED)) {
            if (flag != bs.getValue(OPEN)) {
                lvl.gameEvent(null, flag ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, bp);
            }
            lvl.setBlock(bp,bs.setValue(POWERED,flag).setValue(OPEN,flag),2);
        }
    }

    // carry-over fix for DD mod from user kitteh6660 - 2024
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        super.createBlockStateDefinition(builder);
        builder.add(LOCKED);
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(Component.translatable("block.thingamajigs.lockable_door.desc"));
    }
}
