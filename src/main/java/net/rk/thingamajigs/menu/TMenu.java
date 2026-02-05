package net.rk.thingamajigs.menu;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.thingamajigs.Thingamajigs;

public class TMenu{
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(
            BuiltInRegistries.MENU, Thingamajigs.MODID);

    public static final DeferredHolder<MenuType<?>,MenuType<DJLaserLightMenu>> DJ_BE_MENU =
            MENU_TYPES.register("dj_be_menu", () ->
                    IMenuTypeExtension.create(DJLaserLightMenu::new));

    public static final DeferredHolder<MenuType<?>,MenuType<MailboxMenu>> MAILBOX_MENU =
            MENU_TYPES.register("mailbox_menu", () -> IMenuTypeExtension.create((id,inv,data) ->
                    new MailboxMenu(id,inv)));

    public static void register(IEventBus eventBus){MENU_TYPES.register(eventBus);}
}
