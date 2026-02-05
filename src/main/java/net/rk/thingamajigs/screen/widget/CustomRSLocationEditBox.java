package net.rk.thingamajigs.screen.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;

public class CustomRSLocationEditBox extends EditBox{
    public CustomRSLocationEditBox(Font font, int posX, int posY, int width, int height, Component message, String txt) {
        super(font,posX,posY,width,height,message);
        this.setMaxLength(6);
    }

    @Override
    public void setTextColor(int decColor) {
        super.setTextColor(12578815);
    }

    @Override
    protected MutableComponent createNarrationMessage() {
        return Component.translatable("gui.narrate.editBox" + " " + this.getMessage());
    }

    @Override
    public void setResponder(Consumer<String> p_94152_) {
        super.setResponder(this::onEdited);
    }

    // overwrite this with your own code as there is no way to pass arguments to this ran method otherwise
    public void onEdited(String str){}

    @Override
    public void setCanLoseFocus(boolean loseFocus) {
        super.setCanLoseFocus(true);
    }
}
