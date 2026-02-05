package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.blockentity.TBlockEntity;

public class FridgeBE extends RandomizableContainerBlockEntity{
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);

    public FridgeBE(BlockPos bp, BlockState bs) {
        super(TBlockEntity.FRIDGE_BE.get(), bp, bs);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nnlis) {
        this.items = nnlis;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("blockEntities.fridge.name");
    }

    @Override
    protected AbstractContainerMenu createMenu(int cmi1, Inventory i) {
        return ChestMenu.threeRows(cmi1, i, this);
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected void saveAdditional(CompoundTag cts, HolderLookup.Provider p_324280_) {
        super.saveAdditional(cts, p_324280_);
        if (!this.trySaveLootTable(cts)) {
            ContainerHelper.saveAllItems(cts,this.items,p_324280_);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag ctl, HolderLookup.Provider provider) {
        super.loadAdditional(ctl, provider);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(ctl)) {
            ContainerHelper.loadAllItems(ctl,this.items,provider);
        }
    }
}
