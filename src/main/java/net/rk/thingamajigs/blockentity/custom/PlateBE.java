package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.blockentity.TBlockEntity;

public class PlateBE extends BlockEntity implements ContainerSingleItem.BlockContainerSingleItem{
    private ItemStack singularItemStack = ItemStack.EMPTY;
    public static final String SINGULAR_ITEMSTACK = "item";

    public PlateBE(BlockPos pos, BlockState bs){
        super(TBlockEntity.PLATE_BE.get(),pos,bs);
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public ItemStack getRenderStack() {
        ItemStack newIS = new ItemStack(Items.BAKED_POTATO); // potato
        if(!singularItemStack.isEmpty()) {
            return singularItemStack;
        }
        return ItemStack.EMPTY;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider prov) {
        return this.saveWithoutMetadata(prov);
    }

    public ItemStack getStackedItem(){
        return singularItemStack;
    }

    public void setStackedItem(ItemStack itemStack){
        singularItemStack = itemStack;
        // itemhandler doesn't handle the correct slots, so we must manually tell the server we request a 'block updated' thingy
        if(this.level != null){
            if(!this.level.isClientSide()){
                this.level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(),3);
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider prov) {
        super.saveAdditional(compoundTag, prov);
        if (!this.singularItemStack.isEmpty()) {
            compoundTag.put(SINGULAR_ITEMSTACK, this.singularItemStack.save(prov));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        this.singularItemStack = compoundTag.contains(SINGULAR_ITEMSTACK, 10) ? ItemStack.parseOptional(provider,compoundTag.getCompound(SINGULAR_ITEMSTACK)) : ItemStack.EMPTY;

    }

    @Override
    public ItemStack getTheItem() {
        return singularItemStack;
    }

    @Override
    public ItemStack splitTheItem(int pAmount) {
        return singularItemStack;
    }

    @Override
    public void setTheItem(ItemStack pItem) {
        singularItemStack = pItem;
    }

    @Override
    public ItemStack getItem(int slotId) {
        return singularItemStack;
    }

    @Override
    public ItemStack removeItem(int slotId, int p_18943_) {
        ItemStack is = new ItemStack(singularItemStack.getItem());
        if(is.getCount() == 0){
            return ItemStack.EMPTY;
        }
        is.shrink(1);
        return is;
    }

    @Override
    public void setItem(int slotId, ItemStack isNew) {
        singularItemStack = isNew;
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this.level.getBlockEntity(this.getBlockPos());
    }

    @Override
    public boolean stillValid(Player p) {
        BlockState bs = p.level().getBlockState(this.getBlockPos());
        if(bs != TBlocks.PLATE.get().defaultBlockState()){
            return false;
        }
        else if(bs == TBlocks.PLATE.get().defaultBlockState()){
            return true;
        }
        return false;
    }
}
