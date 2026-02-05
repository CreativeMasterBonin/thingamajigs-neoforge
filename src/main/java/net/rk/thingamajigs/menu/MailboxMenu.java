package net.rk.thingamajigs.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class MailboxMenu extends AbstractContainerMenu{
    public static final int CONTAINER_SIZE = 5; // used for calcs
    private final Container mailbox;

    public MailboxMenu(int in2, Inventory inv2) {
        this(in2, inv2, new SimpleContainer(CONTAINER_SIZE));
    }

    public MailboxMenu(int in2, Inventory inv2, FriendlyByteBuf fbb) {
        this(in2, inv2, new SimpleContainer(CONTAINER_SIZE));
    }

    public MailboxMenu(int in1, Inventory inv1, Container cont) {
        super(TMenu.MAILBOX_MENU.get(), in1);

        this.mailbox = cont;

        checkContainerSize(cont, CONTAINER_SIZE);

        cont.startOpen(inv1.player);

        for(int j = 0; j < 5; ++j) {
            this.addSlot(new Slot(cont, j, 44 + j * 18, 20));
        }

        // Inventory slots (required)
        for(int l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inv1, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
            }
        }

        // Hotbar slots (required)
        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(inv1, i1, 8 + i1 * 18, 109));
        }

    }

    // not used currently
    private static BlockEntity getBe(){
        return null;
    }

    @Override
    public boolean stillValid(Player ply3) {
        return this.mailbox.stillValid(ply3);
    }

    @Override
    public ItemStack quickMoveStack(Player plyr1, int in500) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(in500);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (in500 < this.mailbox.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.mailbox.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.mailbox.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public void removed(Player ply) {
        super.removed(ply);
        this.mailbox.stopOpen(ply);
    }
}
