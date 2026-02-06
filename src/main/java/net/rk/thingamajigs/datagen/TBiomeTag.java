package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.thingamajigs.Thingamajigs;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TBiomeTag extends BiomeTagsProvider {
    public TBiomeTag(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, Thingamajigs.MODID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "Thingamajigs Biome Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider p) {
        this.tag(TTag.RIVER_PLANTS_SUPPORTED)
                .add(Biomes.RIVER)
        ;
        this.tag(TTag.CAN_SPAWN_JUNGLE_PLANTS)
                .add(Biomes.JUNGLE)
                .add(Biomes.SPARSE_JUNGLE)
                .add(Biomes.BAMBOO_JUNGLE)
        ;
    }
}
