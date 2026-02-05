package net.rk.thingamajigs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;

public class TBlockStateProvider extends BlockStateProvider{
    public TBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Thingamajigs.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        stairsBlock((StairBlock) TBlocks.CONCRETE_STAIRS.get(),
                ResourceLocation.parse("thingamajigs:block/concrete"));

        stairsBlock((StairBlock)TBlocks.CONCRETE_BRICKS_STAIRS.get(),
                ResourceLocation.parse("thingamajigs:block/concrete_bricks"));

        stairsBlock((StairBlock)TBlocks.COBBLED_CONCRETE_STAIRS.get(),
                ResourceLocation.parse("thingamajigs:block/cobbled_concrete"));
    }
}
