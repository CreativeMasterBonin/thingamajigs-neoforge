package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.blockentity.custom.PlateBE;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings("deprecated")
public class Plate extends BaseEntityBlock{
    public static final MapCodec<Plate> PLATE_MAP_CODEC = simpleCodec(Plate::new);

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return PLATE_MAP_CODEC;
    }
    public static final BooleanProperty FULL = BooleanProperty.create("full");

    public Plate(Properties p) {
        super(p.strength(1F,5F)
                .sound(SoundType.DECORATED_POT).noOcclusion().mapColor(MapColor.TERRACOTTA_WHITE));
        this.registerDefaultState(this.defaultBlockState().setValue(FULL, false));
    }

    // old updated code
    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult bhr) {
        Object object = level.getBlockEntity(blockPos);
        InteractionHand ih = player.getUsedItemHand();

        if (!(object instanceof PlateBE)) {
            return InteractionResult.PASS;
        }
        try{
            PlateBE plateBE = (PlateBE) object;
            object = player.getItemInHand(ih);
            ItemStack itemStack = plateBE.getStackedItem(); // get our container's itemstack, only ONE type and ONE item total allowed!

            // not perfect, but when right-clicked will update
            // this doesn't update with the server and client sync packet that the BE uses (it is just a blockstate)
            // this is here to ensure players who don't know what's going on to know that the plate is full and needs to be emptied...
            // ...in order to put a new item in (break and replace)
            if(itemStack.getCount() >= 1){
                level.setBlock(blockPos,blockState.setValue(FULL,true),3);
            }
            else if(itemStack.getCount() < 1){
                level.setBlock(blockPos,blockState.setValue(FULL,false),3);
            }


            if(itemStack.getCount() >= 1){
                return InteractionResult.PASS;
            }
            else{
                boolean flag1 = ItemStack.isSameItemSameComponents(itemStack, (ItemStack)object);
                boolean isSameItem = ItemStack.isSameItem(itemStack, (ItemStack)object);

                if (!((ItemStack)object).isEmpty() && (itemStack.isEmpty() || (isSameItem && flag1) && itemStack.getCount() < 1)) {
                    ItemStack itemStack2 = player.isCreative() ? ((ItemStack)object).copyWithCount(1) : ((ItemStack)object).split(1);

                    player.awardStat(Stats.ITEM_USED.get(((ItemStack)object).getItem()));

                    if (plateBE.isEmpty()) {
                        plateBE.setStackedItem(itemStack2); // add new item inside block (accesses BE save data)
                    }
                    else {
                        itemStack.grow(1); // increase same item amt (accesses BE save data)
                    }
                    level.playSound(null, blockPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0f, 0.7f + 0.5f);
                    level.updateNeighbourForOutputSignal(blockPos, this);
                }
                else {
                    level.playSound(null, blockPos, SoundEvents.ITEM_FRAME_ROTATE_ITEM, SoundSource.BLOCKS, 1.0f, 0.5f);
                    return InteractionResult.PASS;
                }
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos); // tell the game we just changed a block for the usage thingy
                return InteractionResult.SUCCESS;
            }
        }
        catch (Exception e){
            Logger.getAnonymousLogger().warning("Thingamajigs encountered an exception with the Plate Block and Block Entity.");
            return InteractionResult.FAIL;
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder builder) {
        BlockEntity blockEntity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof PlateBE) {
            PlateBE plateBE = (PlateBE) blockEntity;
        }
        return super.getDrops(blockState, builder);
    }

    @Override
    public void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (!blockState.is(blockState2.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (blockentity instanceof PlateBE) {
                Containers.dropContents(level, blockPos, (PlateBE)blockentity);
                level.updateNeighbourForOutputSignal(blockPos, this);
            }
            super.onRemove(blockState, level, blockPos, blockState2, bl);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level lvl, BlockPos bp, BlockState bs, Player p) {
        return super.playerWillDestroy(lvl, bp, bs, p);
    }

    @Override
    public RenderShape getRenderShape(BlockState bs) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return Block.box(2, 0, 2, 14, 2, 14);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return new PlateBE(bp, bs);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FULL);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        return this.defaultBlockState().setValue(FULL, false);
    }
}
