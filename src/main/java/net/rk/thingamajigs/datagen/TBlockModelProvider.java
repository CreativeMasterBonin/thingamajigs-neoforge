package net.rk.thingamajigs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;
import org.jetbrains.annotations.NotNull;

public class TBlockModelProvider extends BlockModelProvider{
    public static final ResourceLocation POLE_TEXTURE = ResourceLocation.parse("minecraft:block/blast_furnace_side");
    public TBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Thingamajigs.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        allSidedBlock(TBlocks.CONCRETE,"thingamajigs:block/concrete");
        allSidedBlock(TBlocks.CONCRETE_BRICKS,"thingamajigs:block/concrete_bricks");
        allSidedBlock(TBlocks.COBBLED_CONCRETE,"thingamajigs:block/cobbled_concrete");
    }

    private BlockModelBuilder cakeBlock(DeferredBlock<Block> block, String cakeBottom, String cakeTop, String cakeSide){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("minecraft:block/cake"))
                .texture("bottom", ResourceLocation.parse(cakeBottom)) // cake bottom (cakey part)
                .texture("top", ResourceLocation.parse(cakeTop)) // cake top (frosting)
                .texture("side", ResourceLocation.parse(cakeSide)) // cake side (frosting to cakey part)
                .texture("particle", ResourceLocation.parse("side"));
    }

    private BlockModelBuilder orientableBlock(DeferredBlock<Block> block, String front, String side, String top){
        return withExistingParent(block.getId().getPath(),ResourceLocation.parse("minecraft:block/orientable"))
                .texture("front",ResourceLocation.parse(front))
                .texture("side",ResourceLocation.parse(side))
                .texture("top",ResourceLocation.parse(top));
    }

    private BlockModelBuilder cubeColumnBlock(DeferredBlock<Block> block, String end, String side){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("minecraft:cube_column"))
                .texture("end",ResourceLocation.parse(end))
                .texture("side",ResourceLocation.parse(side));
    }

    private BlockModelBuilder customPumpkinBlock(DeferredBlock<Block> block, String end, String side){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("thingamajigs:bases/custom_pumpkin"))
                .texture("end",ResourceLocation.parse(end))
                .texture("side",ResourceLocation.parse(side));
    }

    private BlockModelBuilder allSidedBlock(DeferredBlock<Block> block, String textureLocation){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("minecraft:block/cube_all"))
                .texture("all", ResourceLocation.parse(textureLocation));
    }

    @NotNull
    private BlockModelBuilder signModelBuilder(DeferredBlock<Block> block, String directory, String front, String back){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("thingamajigs:bases/sign"))
                .texture("front", ResourceLocation.parse("thingamajigs:block/signs/" + directory + "/" + front))
                .texture("back", ResourceLocation.parse("thingamajigs:block/signs/back/" + back))
                .texture("pole", POLE_TEXTURE)
                .texture("particle", POLE_TEXTURE);
    }

    private BlockModelBuilder standingTorchModelBuilder(DeferredBlock<Block> block, String directory){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("thingamajigs:bases/standing_torch"))
                .texture("0", ResourceLocation.parse(directory))
                .texture("particle", ResourceLocation.parse(directory)).renderType("cutout");
    }

    private BlockModelBuilder wallTorchModelBuilder(DeferredBlock<Block> block, String directory){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("thingamajigs:bases/wall_torch"))
                .texture("0", ResourceLocation.parse(directory))
                .texture("missing", ResourceLocation.parse(directory))
                .texture("particle", ResourceLocation.parse(directory)).renderType("cutout");
    }

    private BlockModelBuilder lanternModelBuilder(DeferredBlock<Block> block, String directory){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("minecraft:block/template_lantern"))
                .texture("lantern",directory).renderType("cutout");
    }

    private BlockModelBuilder hangingLanternModelBuilder(DeferredBlock<Block> block, String directory){
        return withExistingParent(block.getId().getPath() + "_hanging",
                ResourceLocation.parse("minecraft:block/template_hanging_lantern"))
                .texture("lantern",directory).renderType("cutout");
    }

    private BlockModelBuilder customChainModelBuilder(DeferredBlock<Block> block, String textureDirectory){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("minecraft:block/chain"))
                .texture("all",textureDirectory)
                .texture("particle",textureDirectory).renderType("cutout");
    }
}
