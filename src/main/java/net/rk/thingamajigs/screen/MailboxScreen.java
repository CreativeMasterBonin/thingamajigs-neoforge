package net.rk.thingamajigs.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.rk.thingamajigs.menu.MailboxMenu;

public class MailboxScreen extends AbstractContainerScreen<MailboxMenu>{
    private static final ResourceLocation MAILBOX_TEXTURE = ResourceLocation.parse("thingamajigs:textures/gui/mailbox.png");

    public MailboxScreen(MailboxMenu mm, Inventory inv1, Component comp) {
        super(mm, inv1, comp);
        this.imageHeight = 133;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i1, int i2, float f1) {
        super.render(guiGraphics, i1, i2, f1);
        this.renderTooltip(guiGraphics, i1, i2);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics2, float f2, int i3, int i4) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        guiGraphics2.blit(MAILBOX_TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
