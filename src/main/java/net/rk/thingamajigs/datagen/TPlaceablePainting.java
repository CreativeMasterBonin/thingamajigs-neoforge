package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.thingamajigs.Thingamajigs;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TPlaceablePainting extends TagsProvider<PaintingVariant> {
    protected TPlaceablePainting(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256596_, Registries.PAINTING_VARIANT, p_256513_, Thingamajigs.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p) {
        this.tag(PaintingVariantTags.PLACEABLE);
    }
}
