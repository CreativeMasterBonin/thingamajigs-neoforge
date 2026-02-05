package net.rk.thingamajigs.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.rk.thingamajigs.Thingamajigs;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;

public class DJLaserLightToggledButton extends AbstractWidget {
    protected boolean isStateTriggered;

    public static final WidgetSprites BUTTON_SPRITES = new WidgetSprites(
            ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,"container/laser_light/pressed_button"),
            ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,"container/laser_light/normal_button"),
            ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,"container/laser_light/pressed_selected_button"),
            ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,"container/laser_light/selected_button")
    );

    public Component lolcatz = CommonComponents.OPTION_ON;
    public Component singleBitText = Component.literal("?");

    public Component tooltipText = Component.literal("nothing");
    public Component translatedtext = Component.empty();

    public DJLaserLightToggledButton(int x, int y, boolean initalState, String str, Component addon) {
        super(x, y, 16, 16, Component.empty());
        this.isStateTriggered = initalState;
        Component temp = Component.translatable(str);
        this.setTooltip(Tooltip.create(Component.literal(temp.getString() + addon.getString())));
        this.setTooltipDelay(Duration.ZERO);
        this.translatedtext = temp;
    }

    public void setStateTriggered(boolean pTriggered) {
        this.isStateTriggered = pTriggered;
    }

    public boolean isStateTriggered() {
        return this.isStateTriggered;
    }

    @Override
    public void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {
        this.defaultButtonNarrationText(pNarrationElementOutput);
    }

    @Nullable
    @Override
    public Tooltip getTooltip() {
        Tooltip tt;
        if(isStateTriggered){
            tooltipText = Component.literal(translatedtext.getString() + CommonComponents.OPTION_ON.getString());
        }
        else{
            tooltipText = Component.literal(translatedtext.getString() + CommonComponents.OPTION_OFF.getString());
        }
        tt = Tooltip.create(tooltipText);
        return tt;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraph, int pMouseX, int pMouseY, float pPartialTick) {
        RenderSystem.disableDepthTest();
        ResourceLocation en = BUTTON_SPRITES.enabled();
        ResourceLocation enFoc = BUTTON_SPRITES.enabledFocused();
        ResourceLocation dis = BUTTON_SPRITES.disabled();
        ResourceLocation disFoc = BUTTON_SPRITES.disabledFocused();

        Minecraft minecraft = Minecraft.getInstance();

        ResourceLocation finale;

        if(isStateTriggered()){
            if(isHoveredOrFocused()){
                finale = enFoc;
            }
            else{
                finale = en;
            }
        }
        else{
            if(isHoveredOrFocused()){
                finale = disFoc;
            }
            else{
                finale = dis;
            }
        }
        guiGraph.blitSprite(finale, this.getX(), this.getY(), this.width, this.height);

        RenderSystem.enableDepthTest();
    }
}
