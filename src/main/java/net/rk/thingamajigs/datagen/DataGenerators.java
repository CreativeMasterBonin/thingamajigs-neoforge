package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.rk.thingamajigs.Thingamajigs;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@EventBusSubscriber(modid = Thingamajigs.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // paintings are all premade-data driven jsons now


        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(TLoot::new,LootContextParamSets.BLOCK)),lookupProvider));

        //TODO stuff coming up soon for other providers
        event.getGenerator().addProvider(event.includeServer(), new TBlockStateProvider(packOutput,event.getExistingFileHelper()));

        event.getGenerator().addProvider(event.includeClient(), new TBlockModelProvider(packOutput, event.getExistingFileHelper()));
        event.getGenerator().addProvider(event.includeClient(), new TItemModel(packOutput,event.getExistingFileHelper()));

        //generator.addProvider(true,new TFluidTag(packOutput,lookupProvider,event.getExistingFileHelper()));

        TBlockTag blockTags = new TBlockTag(packOutput, lookupProvider, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);

        event.getGenerator().addProvider(event.includeServer(),
                new TItemTag(packOutput, lookupProvider, blockTags.contentsGetter(),event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(),new TRecipe(packOutput,lookupProvider));

        Logger.getAnonymousLogger().info("Thingamajigs Datagen is running worldgen bootstaps.");
        event.getGenerator().addProvider(event.includeServer(),new TWorldGen(packOutput,lookupProvider));
    }
}
