package net.rk.thingamajigs.mix;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.xtras.TConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CreativeModeTab.class)
public abstract class TMixCTab {
    @Shadow public abstract Component getDisplayName();
    @Shadow public abstract boolean canScroll();

    @Inject(method = "getBackgroundTexture",at={@At("RETURN")},cancellable = true)
    public void thingamajigs_getBackgroundRL(CallbackInfoReturnable<ResourceLocation> cir){
        if(this.getDisplayName().equals(Thingamajigs.MAIN_CTAB.get().getDisplayName()) && TConfig.blueTabs.get().booleanValue()){
            if(TConfig.tabTheme.get().equals(TConfig.Theme.BLUE)){
                cir.setReturnValue(ResourceLocation.parse("thingamajigs:textures/gui/thingamajigsitems.png"));
            }
            else if(TConfig.tabTheme.get().equals(TConfig.Theme.GREEN)){
                cir.setReturnValue(ResourceLocation.parse("thingamajigs:textures/gui/themes/green.png"));
            }
            else if(TConfig.tabTheme.get().equals(TConfig.Theme.PURPLE)){
                cir.setReturnValue(ResourceLocation.parse("thingamajigs:textures/gui/themes/purple.png"));
            }
        }
    }

    @Inject(method = "getScrollerSprite",at={@At("RETURN")},cancellable = true)
    public void thingamajigs_getScrollRL(CallbackInfoReturnable<ResourceLocation> cir){
        if(this.getDisplayName().equals(Thingamajigs.MAIN_CTAB.get().getDisplayName()) && TConfig.blueTabs.get().booleanValue()){
            if(TConfig.tabTheme.get().equals(TConfig.Theme.BLUE)){
                if(this.canScroll()){
                    cir.setReturnValue(ResourceLocation.parse("thingamajigs:container/creative_inventory/scroller"));
                }
                else{
                    cir.setReturnValue(ResourceLocation.parse("thingamajigs:container/creative_inventory/scroller_disabled"));
                }
            }
            else if(TConfig.tabTheme.get().equals(TConfig.Theme.GREEN)){
                if(this.canScroll()){
                    cir.setReturnValue(ResourceLocation.parse("thingamajigs:container/creative_inventory/scroller_green"));
                }
                else{
                    cir.setReturnValue(ResourceLocation.parse("thingamajigs:container/creative_inventory/scroller_green_disabled"));
                }
            }
            else if(TConfig.tabTheme.get().equals(TConfig.Theme.PURPLE)){
                if(this.canScroll()){
                    cir.setReturnValue(ResourceLocation.parse("thingamajigs:container/creative_inventory/scroller_purple"));
                }
                else{
                    cir.setReturnValue(ResourceLocation.parse("thingamajigs:container/creative_inventory/scroller_purple_disabled"));
                }
            }
        }
    }
}
