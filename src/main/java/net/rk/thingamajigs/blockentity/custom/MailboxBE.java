package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.blockentity.TBlockEntity;
import net.rk.thingamajigs.menu.MailboxMenu;

public class MailboxBE extends RandomizableContainerBlockEntity{
    private NonNullList<ItemStack> items = NonNullList.withSize(5, ItemStack.EMPTY);

    public MailboxBE(BlockPos pos, BlockState state) {
        super(TBlockEntity.MAILBOX_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider prov) {
        super.loadAdditional(compoundTag,prov);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(compoundTag)) {
            ContainerHelper.loadAllItems(compoundTag,this.items,prov);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag cpt, HolderLookup.Provider prov) {
        super.saveAdditional(cpt,prov);
        if (!this.trySaveLootTable(cpt)) {
            ContainerHelper.saveAllItems(cpt,this.items,prov);
        }
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("blockEntities.mailbox.name");
    }

    @Override
    protected AbstractContainerMenu createMenu(int num1, Inventory inv1) {
        return new MailboxMenu(num1,inv1,this);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> p_59625_) {
        this.items = p_59625_;
    }

    @Override
    public int getContainerSize() {
        return 5;
    }
}
