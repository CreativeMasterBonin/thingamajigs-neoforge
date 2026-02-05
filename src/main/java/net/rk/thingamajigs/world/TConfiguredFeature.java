package net.rk.thingamajigs.world;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;

import java.util.OptionalInt;

public class TConfiguredFeature<T, T1> {
    public static final ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>> RUBBER_KEY = registerKey("rubber");
    public static final ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>> DRY_BRAMBLE_KEY = registerKey("dry_bramble");

    public static void bootstrap(BootstrapContext<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?,?>> context){
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);
        BlockPredicate blockpredicate = BlockPredicate.matchesBlocks();

        context.register(RUBBER_KEY, new ConfiguredFeature<>(
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(TBlocks.RUBBER_LOG.get()),
                        new FancyTrunkPlacer(5, 8, 0),

                        BlockStateProvider.simple(TBlocks.RUBBER_LEAVES.get()),
                        new FancyFoliagePlacer(ConstantInt.of(2),
                                ConstantInt.of(2), 1),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(7))).build()
        ));
    }

    public static ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID, name));
    }
}
