package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.world.TBiomeModifier;
import net.rk.thingamajigs.world.TConfiguredFeature;
import net.rk.thingamajigs.world.TPlacedFeature;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TWorldGen extends DatapackBuiltinEntriesProvider{
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, TConfiguredFeature::bootstrap)
            .add(Registries.PLACED_FEATURE, TPlacedFeature::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, TBiomeModifier::bootstrap);

    public TWorldGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Thingamajigs.MODID));
    }
}
