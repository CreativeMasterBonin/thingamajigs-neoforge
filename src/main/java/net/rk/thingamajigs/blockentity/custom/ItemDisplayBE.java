package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.ticks.ContainerSingleItem;
import net.rk.thingamajigs.blockentity.TBlockEntity;

import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("deprecated,unused")
public class ItemDisplayBE extends BlockEntity implements RandomizableContainer, ContainerSingleItem.BlockContainerSingleItem{
    public static final String ITEM = "item";
    public ItemStack item = ItemStack.EMPTY;
    public int ticks;
    public float rot;
    public boolean hidePose = false;
    protected ResourceKey<LootTable> loottable;
    protected long seed;

    public ItemDisplayBE(BlockPos bp, BlockState bs) {
        super(TBlockEntity.ITEM_DISPLAY_BE.get(), bp, bs);
    }

    @Override
    protected void saveAdditional(CompoundTag ct, HolderLookup.Provider prov) {
        super.saveAdditional(ct,prov);
        if (!this.trySaveLootTable(ct) && !this.item.isEmpty()) {
            ct.put("item", this.item.save(prov));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag ct, HolderLookup.Provider prov) {
        super.loadAdditional(ct,prov);
        if (!this.tryLoadLootTable(ct)) {
            if (ct.contains("item", 10)) {
                this.item = ItemStack.parse(prov,ct.getCompound("item")).orElse(ItemStack.EMPTY);
            } else {
                this.item = ItemStack.EMPTY;
            }
        }
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider hlprov) {
        return this.saveCustomOnly(hlprov);
    }

    public void setFromItem(ItemStack is) {
        this.applyComponentsFromItemStack(is);
    }


    @Nullable
    @Override
    public ResourceKey<LootTable> getLootTable() {
        return this.loottable;
    }

    @Override
    public void setLootTable(@Nullable ResourceKey<LootTable> key) {
        this.loottable = key;
    }

    @Override
    public long getLootTableSeed() {
        return this.seed;
    }

    @Override
    public void setLootTableSeed(long lsed) {
        this.seed = lsed;
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder dcmbu) {
        super.collectImplicitComponents(dcmbu);
        dcmbu.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(List.of(this.item)));
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput dci) {
        super.applyImplicitComponents(dci);
        this.item = dci.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyOne();
    }

    @Override
    public void removeComponentsFromTag(CompoundTag ct) {
        super.removeComponentsFromTag(ct);
        ct.remove("item");
    }

    @Override
    public ItemStack getTheItem() {
        this.unpackLootTable(null);
        return this.item;
    }

    @Override
    public ItemStack splitTheItem(int id) {
        this.unpackLootTable(null);
        ItemStack itemstack = this.item.split(id);
        if (this.item.isEmpty()) {
            this.item = ItemStack.EMPTY;
        }
        return itemstack;
    }

    @Override
    public void setTheItem(ItemStack is) {
        this.unpackLootTable(null);
        this.item = is;
        this.getLevel().sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(),3);
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    public static void clientTick(Level lvl, BlockPos bp, BlockState bs, ItemDisplayBE be){
        ++be.ticks;
        if(!be.getLevel().hasNearbyAlivePlayer((double)bp.getX() + 0.5, (double)bp.getY() + 0.5, (double)bp.getZ() + 0.5, (double)12)){
            be.hidePose = true;
        }
        else{
            ++be.rot;
            be.hidePose = false;
        }
    }

    public static void serverTick(Level slvl, BlockPos sbp, BlockState sbs, ItemDisplayBE sbe){
        ++sbe.ticks;
    }
}
