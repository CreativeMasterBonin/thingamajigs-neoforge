package net.rk.thingamajigs.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.blockentity.custom.DJLaserLightBE;
import net.rk.thingamajigs.menu.DJLaserLightMenu;
import net.rk.thingamajigs.network.record.DJLaserLightUpdatePayload;
import net.rk.thingamajigs.screen.widget.CustomRSLocationEditBox;
import net.rk.thingamajigs.screen.widget.RevertedButton;
import net.rk.thingamajigs.xtras.TColors;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.logging.Logger;

public class DJLaserLightScreen extends AbstractContainerScreen<DJLaserLightMenu> {
    private final static HashMap<String, Object> guistate = DJLaserLightMenu.guistate;
    private final Level world;
    private final int x, y, z;
    private final Player entity;

    private DJLaserLightBE djllbe;

    private static final ResourceLocation BG_TEXTURE = ResourceLocation.parse("thingamajigs:textures/gui/laser_light_menu.png");

    // buttons
    public Button decreaseHeightButton;
    public Button increaseHeightButton;

    public Button closeThisScreenButton;

    public Button whiteButton;
    public EditBox colorHexBox;
    public Button updateHexColorButton;
    public Button incHorzOscButton;
    public Button decHorzOscButton;
    public Button incVertOscButton;
    public Button decVertOscButton;
    public Button incHMulti;
    public Button decHMulti;
    public Button incVMulti;
    public Button decVMulti;
    public Button incSize;
    public Button decSize;
    public Button incAngleOffset;
    public Button decAngleOffset;
    public Button incVAngleOffset;
    public Button decVAngleOffset;
    public Button incCount;
    public Button decCount;

    public Button resetColorCode;


    public DJLaserLightScreen(DJLaserLightMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 320;
        this.imageHeight = 240;

        this.minecraft = Minecraft.getInstance();
        this.font = this.minecraft.font;

        int widthx = (this.width - this.imageWidth) / 2;
        int heighty = (this.height - this.imageHeight) / 2;

        this.djllbe = (DJLaserLightBE)world.getBlockEntity(new BlockPos(x,y,z)); // access the BE at the current pos that the screen is opened at
    }

    @Override
    protected void init() {
        super.init();
        setupExtras();
        addRenderableWidget(decreaseHeightButton);
        addRenderableWidget(increaseHeightButton);

        addRenderableWidget(closeThisScreenButton);
        addRenderableWidget(whiteButton);
        addRenderableWidget(colorHexBox);
        addRenderableWidget(updateHexColorButton);

        addRenderableWidget(incHorzOscButton);
        addRenderableWidget(decHorzOscButton);

        addRenderableWidget(incVertOscButton);
        addRenderableWidget(decVertOscButton);

        addRenderableWidget(incHMulti);
        addRenderableWidget(decHMulti);

        addRenderableWidget(incVMulti);
        addRenderableWidget(decVMulti);

        addRenderableWidget(incSize);
        addRenderableWidget(decSize);

        addRenderableWidget(incAngleOffset);
        addRenderableWidget(decAngleOffset);

        addRenderableWidget(incVAngleOffset);
        addRenderableWidget(decVAngleOffset);

        // deprecated as this feature doesn't seem to work right.
        //addRenderableWidget(incCount);
        //addRenderableWidget(decCount);

        addRenderableWidget(resetColorCode);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics,pMouseX,pMouseY,pPartialTick);
        this.renderBg(pGuiGraphics,pPartialTick,pMouseX,pMouseY);
        super.render(pGuiGraphics,pMouseX,pMouseY,pPartialTick);
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        Component modelabel = Component.translatable("container.thingamajigs.laser_light.mode_title")
                .withStyle(ChatFormatting.WHITE);
        Component extraInfolabel = Component.translatable("container.thingamajigs.laser_light.mode_unused");

        pGuiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY,
                TColors.getWhite(), false);

        pGuiGraphics.fillGradient(1,1,1,1,this.titleLabelX,this.titleLabelY);

        pGuiGraphics.drawString(this.font, Component.translatable("label.thingamajigs.laser_light.color"),
                this.titleLabelX, this.titleLabelY + 120,
                djllbe.color, false);

        if(djllbe.lightMode == 0){
            extraInfolabel = Component.translatable("container.thingamajigs.laser_light.mode_zero_label");
        }
    }

    @Override
    public void renderBackground(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderCustomBg(pGuiGraphics);
        if (this.minecraft.level != null) {
            net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(new net.neoforged.neoforge.client.event.ScreenEvent.BackgroundRendered(this, pGuiGraphics));
        }
    }

    public static final ResourceLocation BG_LOC = ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,"textures/block/sidewalk_new.png");

    public void renderCustomBg(GuiGraphics guigraph){
        guigraph.setColor(FastColor.ARGB32.red(djllbe.color),
                FastColor.ARGB32.green(djllbe.color),
                FastColor.ARGB32.blue(djllbe.color),
                1.0f);

        guigraph.blit(BG_LOC, this.leftPos - 18, this.topPos + 107, 0, 0.0F, 0.0F,
                16, 16, 16, 16);

        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.post(
                new net.neoforged.neoforge.client.event.ScreenEvent.BackgroundRendered(this, guigraph));
    }

    @Override
    protected void renderBg(GuiGraphics ggraph, float ptick, int mousx, int mousy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, BG_TEXTURE);
        ggraph.blit(BG_TEXTURE,
                this.leftPos,this.topPos,0,0,
                this.imageWidth,this.imageHeight,this.imageWidth,this.imageHeight);
        RenderSystem.disableBlend();
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if(key == 256){
            this.getMinecraft().player.closeContainer();
            return true;
        }
        return false;
    }

    private void setupExtras(){
        int smallSliderwidth = 72;
        int sliderheight = 16;
        int mediumSliderwidth = 102;

        int incHButxPos = this.leftPos + 8;
        int incHButyPos = this.topPos + 18;
        int rightPos = this.leftPos + 320;
        int bottomPos = this.topPos + 240;

        closeThisScreenButton = new RevertedButton(rightPos - 16,this.topPos,16,16,
                Component.literal("X"),(h) -> {
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT,1.0f));
            this.getMinecraft().player.closeContainer();
        }){
            @Nullable
            @Override
            public Tooltip getTooltip() {
                return Tooltip.create(Component.literal("Close The UI"));
            }
        };

        increaseHeightButton = new RevertedButton(incHButxPos + 66,incHButyPos,64,16,
                Component.translatable("button.thingamajigs.laser_light.height_inc"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),
                    djllbe.height + 1,
                    djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,1.0f));
        }){};

        decreaseHeightButton = new RevertedButton(incHButxPos,incHButyPos,64,16,
                Component.translatable("button.thingamajigs.laser_light.height_dec"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),djllbe.height - 1,djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,0.75f));
        }){};

        incHorzOscButton = new RevertedButton(this.leftPos + 74,this.topPos + 37,
                64,16,
                Component.translatable("button.thingamajigs.laser_light.horzoscinc"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),djllbe.height,djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc + 0.5f,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,1.0f));
        }){};

        decHorzOscButton = new RevertedButton(this.leftPos + 8,this.topPos + 37,64,16,
                Component.translatable("button.thingamajigs.laser_light.horzoscdec"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),djllbe.height,djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc - 0.5f,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,0.75f));
        }){};

        incVertOscButton = new RevertedButton(this.leftPos + 75,this.topPos + 55,64,16,
                Component.translatable("button.thingamajigs.laser_light.vertoscinc"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),djllbe.height,djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc + 0.5f,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,1.0f));
        }){};

        decVertOscButton = new RevertedButton(this.leftPos + 9,this.topPos + 55,64,16,
                Component.translatable("button.thingamajigs.laser_light.vertoscdec"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),djllbe.height,djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc - 0.5f,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,0.75f));
        }){};


        incHMulti = new RevertedButton(incVertOscButton.getX(),(incVertOscButton.getY() + incVertOscButton.getHeight()) + 2,64,16,
                Component.translatable("button.thingamajigs.laser_light.horzmultiinc"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),djllbe.height,djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti + 0.5f,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,1.0f));
        }){};

        decHMulti = new RevertedButton(decVertOscButton.getX(),(decVertOscButton.getY() + decVertOscButton.getHeight()) + 2,64,16,
                Component.translatable("button.thingamajigs.laser_light.horzmultidec"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),djllbe.height,djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti - 0.5f,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,0.75f));
        }){};

        incVMulti = new RevertedButton(incHMulti.getX(),(incHMulti.getY() + incHMulti.getHeight())+ 2,64,16,
                Component.translatable("button.thingamajigs.laser_light.vertmultiinc"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),djllbe.height,djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti + 0.5f,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,1.0f));
        }){};

        decVMulti = new RevertedButton(decHMulti.getX(),(decHMulti.getY() + decHMulti.getHeight() )+ 2,64,16,
                Component.translatable("button.thingamajigs.laser_light.vertmultidec"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),djllbe.height,djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti - 0.5f,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,0.75f));
        }){};



        decSize = new RevertedButton(this.leftPos + 141,increaseHeightButton.getY(),64,16,
                Component.translatable("button.thingamajigs.laser_light.sizedec"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),
                    djllbe.height,
                    djllbe.colorstr,
                    djllbe.laserSize - 0.25f,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,0.75f));
        }){};

        incSize = new RevertedButton(this.leftPos + 207,decSize.getY(),64,16,
                Component.translatable("button.thingamajigs.laser_light.sizeinc"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),
                    djllbe.height,
                    djllbe.colorstr,
                    djllbe.laserSize + 0.25f,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,1.0f));
        }){};

        incAngleOffset = new RevertedButton(incSize.getX(),(incSize.getY() + incSize.getHeight()) + 2,64,16,
                Component.translatable("button.thingamajigs.laser_light.angoffinc"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),
                    djllbe.height,
                    djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset + 0.5f,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,1.0f));
        }){};

        decAngleOffset = new RevertedButton(decSize.getX(),(incSize.getY() + incSize.getHeight()) + 2,64,16,
                Component.translatable("button.thingamajigs.laser_light.angoffdec"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),
                    djllbe.height,
                    djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset - 0.5f,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,0.75f));
        }){};

        incVAngleOffset = new RevertedButton(incAngleOffset.getX(),(incAngleOffset.getY() + incAngleOffset.getHeight()) + 2,64,16,
                Component.translatable("button.thingamajigs.laser_light.vangoffinc"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),
                    djllbe.height,
                    djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset + 0.5f,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,1.0f));
        }){};

        decVAngleOffset = new RevertedButton(this.leftPos + 141,incVAngleOffset.getY(),64,16,
                Component.translatable("button.thingamajigs.laser_light.vangoffdec"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),
                    djllbe.height,
                    djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset - 0.5f,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,0.75f));
        }){};

        decCount = new RevertedButton(decVAngleOffset.getX(),decVAngleOffset.getY() + 18,64,16,
                Component.translatable("button.thingamajigs.laser_light.deccount"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),
                    djllbe.height,
                    djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount - 1,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,0.75f));
        }){};

        incCount = new RevertedButton(incVAngleOffset.getX(),incVAngleOffset.getY() + 18,64,16,
                Component.translatable("button.thingamajigs.laser_light.inccount"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),
                    djllbe.height,
                    djllbe.colorstr,
                    djllbe.laserSize,
                    djllbe.laserCount + 1,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,1.0f));
        }){};



        colorHexBox = new CustomRSLocationEditBox(this.getMinecraft().font,incHButxPos,incHButyPos + 120,
                72,16,Component.literal("colorHexEditbox"),djllbe.colorstr){
            @Override
            public void setTextColor(int decColor) {
                super.setTextColor(djllbe.color);
            }
            @Override
            public void setCanLoseFocus(boolean loseFocus) {
                super.setCanLoseFocus(true);
            }
        };

        updateHexColorButton = new RevertedButton(colorHexBox.getX() + colorHexBox.getWidth() + 2,colorHexBox.getY(),24,16,
                Component.translatable("button.thingamajigs.laser_light.send_color_update"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),djllbe.height,colorHexBox.getValue(),
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.BUCKET_EMPTY,1.0f));
        }){};

        whiteButton = new RevertedButton(colorHexBox.getX(),colorHexBox.getY() + colorHexBox.getHeight() + 2,64,16,
                Component.translatable("button.thingamajigs.laser_light.reset"),(handler) -> {
            PacketDistributor.sendToServer(new DJLaserLightUpdatePayload(
                    new BlockPos(x,y,z),djllbe.height,"FFFFFF",
                    djllbe.laserSize,
                    djllbe.laserCount,
                    djllbe.angleOffset,
                    djllbe.vAngleOffset,
                    djllbe.lightMode,
                    djllbe.hOsc,
                    djllbe.vOsc,
                    djllbe.hMulti,
                    djllbe.vMulti,
                    djllbe.lightModeData,
                    djllbe.laserTextureLoc
            ));
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.ITEM_PICKUP,1.0f));
        }){};

        int rccbuty = whiteButton.getY() + whiteButton.getHeight() + 2;

        resetColorCode = new RevertedButton(
                whiteButton.getX(),rccbuty,72,24,
                Component.translatable("button.thingamajigs.laser_light.clear_box"),
                (handleit) -> {
                    try{
                        colorHexBox.setValue("");
                    }
                    catch(Exception e){
                        Logger.getAnonymousLogger().warning("DJ Laser Light Screen couldn't reset the colorHexBox value. ERR: " + e.getMessage());
                    }
                }
        ){};
    }
}
