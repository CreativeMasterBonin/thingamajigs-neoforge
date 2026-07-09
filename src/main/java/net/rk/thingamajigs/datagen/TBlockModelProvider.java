package net.rk.thingamajigs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
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
    public void registerModels() {
        allSidedBlock(TBlocks.CONCRETE,"thingamajigs:block/concrete");
        allSidedBlock(TBlocks.CONCRETE_BRICKS,"thingamajigs:block/concrete_bricks");
        allSidedBlock(TBlocks.COBBLED_CONCRETE,"thingamajigs:block/cobbled_concrete");

        createTubeManColorFromColor(DyeColor.WHITE);
        createTubeManColorFromColor(DyeColor.ORANGE);
        createTubeManColorFromColor(DyeColor.MAGENTA);
        createTubeManColorFromColor(DyeColor.LIGHT_BLUE);
        createTubeManColorFromColor(DyeColor.YELLOW);
        createTubeManColorFromColor(DyeColor.LIME);
        createTubeManColorFromColor(DyeColor.PINK);
        createTubeManColorFromColor(DyeColor.GRAY);
        createTubeManColorFromColor(DyeColor.LIGHT_GRAY);
        createTubeManColorFromColor(DyeColor.CYAN);
        createTubeManColorFromColor(DyeColor.PURPLE);
        //createTubeManColorFromColor(DyeColor.BLUE);
        createTubeManColorFromColor(DyeColor.BROWN);
        createTubeManColorFromColor(DyeColor.GREEN);
        createTubeManColorFromColor(DyeColor.RED);
        createTubeManColorFromColor(DyeColor.BLACK);
    }

    public void createTubeManColorFromColor(DyeColor color){
        String tubeManBodyTexture = "thingamajigs:block/aios/tube_man/" + color.getName() + "_tube_man";
        String tubeManSideTexture = "thingamajigs:block/aios/tube_man/" + color.getName() + "_tube_man_side";

        String fullModelLocation = "thingamajigs:bases/tube_man_components/" + color.getName();

        tubeManBodySection(fullModelLocation + "_tube_man_body_section",tubeManBodyTexture,tubeManSideTexture);
        tubeManHeadSection(fullModelLocation + "_tube_man_head_section",tubeManBodyTexture,tubeManSideTexture);
        tubeManCompressed(fullModelLocation + "_tube_man_base_compressed","block/blast_furnace_top",tubeManBodyTexture,tubeManSideTexture);
    }

    public void createTubeManColor(String color){
        String tubeManBodyTexture = "thingamajigs:block/aios/tube_man/" + color + "_tube_man";
        String tubeManSideTexture = "thingamajigs:block/aios/tube_man/" + color + "_tube_man_side";

        String fullModelLocation = "thingamajigs:bases/tube_man_components/" + color;

        tubeManBodySection(fullModelLocation + "_tube_man_body_section",tubeManBodyTexture,tubeManSideTexture);
        tubeManHeadSection(fullModelLocation + "_tube_man_head_section",tubeManBodyTexture,tubeManSideTexture);
        tubeManCompressed(fullModelLocation + "_tube_man_base_compressed","block/blast_furnace_top",tubeManBodyTexture,tubeManSideTexture);
    }

    /**
     * Generate a Tube Man body section model from the base one, supplying an all-in-one texture and particle texture
     * @param tubeManFileName The name of the model file
     * @param bodyTexture The location of the all-in-one texture
     * @param particleTexture The location of the particle texture
     * @return The returned BlockModelBuilder
     */
    public BlockModelBuilder tubeManBodySection(String tubeManFileName, String bodyTexture, String particleTexture){
        return withExistingParent(tubeManFileName,
                ResourceLocation.parse("thingamajigs:bases/tube_man_components/tube_man_body_section"))
                .texture("0", ResourceLocation.parse(bodyTexture))
                .texture("particle", ResourceLocation.parse(particleTexture));
    }

    /**
     * Generate a Tube Man head section model from the base one, supplying an all-in-one texture and side texture, which is used for particles.
     * @param tubeManFileName The name of the model file
     * @param bodyTexture The all-in-one texture that will be used for the head
     * @param sideTexture The texture that will be used for particles and the sides of the head
     * @return The returned BlockModelBuilder
     */
    public BlockModelBuilder tubeManHeadSection(String tubeManFileName, String bodyTexture, String sideTexture){
        return withExistingParent(tubeManFileName,
                ResourceLocation.parse("thingamajigs:bases/tube_man_components/tube_man_head_section"))
                .texture("0", ResourceLocation.parse(sideTexture))
                .texture("1", ResourceLocation.parse(bodyTexture))
                .texture("particle", ResourceLocation.parse(sideTexture));
    }

    /**
     * Generate a Tube Man compressed base model from the base one, supplying the base, all-in-one and side textures.
     * @param tubeManFileName The name of the model file
     * @param baseStandTexture The bottom base part texture
     * @param bodyTexture The all-in-one texture that will be used for the non-base part
     * @param sideTexture The texture that will be used for particles and the sides of the head
     * @return The returned BlockModelBuilder
     */
    public BlockModelBuilder tubeManCompressed(String tubeManFileName, String baseStandTexture, String bodyTexture, String sideTexture){
        return withExistingParent(tubeManFileName,
                ResourceLocation.parse("thingamajigs:bases/tube_man_components/tube_man_base_compressed"))
                .texture("0", ResourceLocation.withDefaultNamespace("block/blast_furnace_top"))
                .texture("2", ResourceLocation.parse(bodyTexture))
                .texture("3", ResourceLocation.parse(sideTexture))
                .texture("particle", ResourceLocation.withDefaultNamespace("block/blast_furnace_top"));
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
