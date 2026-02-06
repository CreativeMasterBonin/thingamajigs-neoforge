package net.rk.thingamajigs.world;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;

import java.util.OptionalInt;

public class TConfiguredFeature<T, T1> {
    public static final ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>> RUBBER_KEY = registerKey("rubber");
    public static final ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>> DRY_BRAMBLE_KEY = registerKey("dry_bramble");
    public static final ResourceKey<ConfiguredFeature<?,?>> WISPY_WEEDS_FEATURE = registerKey("wispy_weeds");
    public static final ResourceKey<ConfiguredFeature<?,?>> BULBLETS_PATCH_FEATURE = registerKey("bulblet_patch");

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

        context.register(
                WISPY_WEEDS_FEATURE,
                new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                        new RandomPatchConfiguration(
                                11,
                                2,
                                3,
                                PlacementUtils.filtered(
                                        Feature.SIMPLE_BLOCK,
                                        new SimpleBlockConfiguration(
                                                new WeightedStateProvider(
                                                        SimpleWeightedRandomList.<BlockState>builder()
                                                                .add(TBlocks.WISPY_WEED.get().defaultBlockState(),1)
                                                                .add(TBlocks.ROUND_BUSH.get().defaultBlockState(),7)

                                                )
                                        ),
                                        BlockPredicate.allOf(
                                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                                BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(),
                                                        Blocks.PODZOL))
                                        )
                                )
                        )));
        context.register(
                BULBLETS_PATCH_FEATURE,
                new ConfiguredFeature<>(Feature.RANDOM_PATCH,
                        new RandomPatchConfiguration(
                                7,
                                5,
                                2,
                                PlacementUtils.filtered(
                                        Feature.SIMPLE_BLOCK,
                                        new SimpleBlockConfiguration(
                                                new WeightedStateProvider(
                                                        SimpleWeightedRandomList.<BlockState>builder()
                                                                .add(TBlocks.BULBLET.get().defaultBlockState(),1)
                                                                .add(TBlocks.ROUND_BUSH.get().defaultBlockState(),2)
                                                )
                                        ),
                                        BlockPredicate.allOf(
                                                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                                                BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(),
                                                        Blocks.COARSE_DIRT))
                                        )
                                )
                        )));
    }

    public static ResourceKey<net.minecraft.world.level.levelgen.feature.ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID, name));
    }
}
