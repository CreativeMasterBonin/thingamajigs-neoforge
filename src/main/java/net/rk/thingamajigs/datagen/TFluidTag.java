package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.thingamajigs.Thingamajigs;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TFluidTag extends FluidTagsProvider{
    public TFluidTag(PackOutput po, CompletableFuture<HolderLookup.Provider> cfhl, @Nullable ExistingFileHelper existingFileHelper) {
        super(po, cfhl, Thingamajigs.MODID, existingFileHelper);
    }
}
