package net.rk.thingamajigs.world;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;

public class TPlacedFeature{
    public static final ResourceKey<PlacedFeature> RUBBER_PLACED_KEY = registerKey("rubber_placed");
    public static final ResourceKey<PlacedFeature> WISPY_WEEDS_KEY = registerKey("wispy_weeds_patch");
    public static final ResourceKey<PlacedFeature> BULBLET_PATCH_KEY = registerKey("bulblets_patch");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> cf = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(RUBBER_PLACED_KEY, new PlacedFeature(cf.getOrThrow(TConfiguredFeature.RUBBER_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.5f, 1),
                        TBlocks.RUBBER_SAPLING.get())));

        context.register(WISPY_WEEDS_KEY, new PlacedFeature(cf.getOrThrow(TConfiguredFeature.WISPY_WEEDS_FEATURE),
                VegetationPlacements.worldSurfaceSquaredWithCount(2)));

        context.register(BULBLET_PATCH_KEY, new PlacedFeature(cf.getOrThrow(TConfiguredFeature.BULBLETS_PATCH_FEATURE),
                VegetationPlacements.worldSurfaceSquaredWithCount(1)));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,name));
    }
}
