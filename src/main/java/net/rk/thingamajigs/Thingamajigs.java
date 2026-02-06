package net.rk.thingamajigs;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.blockentity.TBlockEntity;
import net.rk.thingamajigs.entity.TEntity;
import net.rk.thingamajigs.item.TItems;
import net.rk.thingamajigs.menu.TMenu;
import net.rk.thingamajigs.network.Handler;
import net.rk.thingamajigs.xtras.TConfig;
import net.rk.thingamajigs.xtras.TParticles;
import net.rk.thingamajigs.xtras.TSoundEvent;
import org.slf4j.Logger;

@Mod(Thingamajigs.MODID)
public class Thingamajigs {
    public static final String MODID = "thingamajigs";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_CTAB = CREATIVE_MODE_TABS.register("main_ctab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.thingamajigs"))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> TItems.THINGAMAJIG.get().getDefaultInstance())
            .backgroundTexture(ResourceLocation.fromNamespaceAndPath("thingamajigs","textures/gui/thingamajigsitems.png"))
            .build());

    public Thingamajigs(IEventBus modEventBus, ModContainer modContainer){
        modEventBus.addListener(this::commonSetup);
        TConfig.register(modContainer);

        modEventBus.addListener(Handler::register);
        TParticles.register(modEventBus);
        TSoundEvent.register(modEventBus);
        TBlocks.BLOCKS.register(modEventBus);
        TItems.ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        TMenu.register(modEventBus);
        TBlockEntity.register(modEventBus);
        TEntity.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}