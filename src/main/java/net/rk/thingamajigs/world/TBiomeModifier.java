package net.rk.thingamajigs.world;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.datagen.TTag;

public class TBiomeModifier{
    public static final ResourceKey<BiomeModifier> ADD_RUBBER_TREE = registerKey("add_rubber_tree");
    public static final ResourceKey<BiomeModifier> ADD_WISPY_WEEDS_RIVER = registerKey("add_wispy_weeds_river");
    public static final ResourceKey<BiomeModifier> ADD_BULBLETS_JUNGLE = registerKey("add_bulblets_jungle");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_RUBBER_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_JUNGLE),
                HolderSet.direct(placedFeatures.getOrThrow(TPlacedFeature.RUBBER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_WISPY_WEEDS_RIVER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(TTag.RIVER_PLANTS_SUPPORTED),
                HolderSet.direct(placedFeatures.getOrThrow(TPlacedFeature.WISPY_WEEDS_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_BULBLETS_JUNGLE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(TTag.CAN_SPAWN_JUNGLE_PLANTS),
                HolderSet.direct(placedFeatures.getOrThrow(TPlacedFeature.BULBLET_PATCH_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,name));
    }
}
