package net.rk.thingamajigs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.item.TItems;

public class TItemModel extends ItemModelProvider {
    public TItemModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Thingamajigs.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleCustomBlock(TBlocks.BRAMBLE,"block/plants/bramble");
        defaultCustomSimple(TBlocks.POTTED_BRAMBLE.get(),"plants/bramble");
        //simpleCustomBlock(TBlocks.CURVED_MONITOR,"item/curved_monitor");

        simple(TItems.MUSIC_COMPONENT);
        fromModelMod(TBlocks.SECURITY_METAL_DETECTOR.get(),"block/security_metal_detector");
        fromModelMod(TBlocks.TRIPLE_SHELF.get(),"block/triple_shelf");
        fromModelMod(TBlocks.MYSTERIOUS_PILLAR.get(),"block/pillar/mysterious_standing");
        //simpleCustomBlock(TBlocks.CLEVER_BLACKBOARD,"item/clever_blackboard");
        //simpleCustomBlock(TBlocks.UMBRELLA,"item/umbrella");
        //simpleCustomBlock(TBlocks.THEATER_PROJECTOR,"item/theater_projector");
        fromModelMod(TBlocks.SUPERMARKET_CONVEYOR.get(),"block/supermarket_conveyor");
        fromModelMod(TBlocks.STRING_BASS.get(),"block/string_bass");
        fromModelMod(TBlocks.BASS_DRUM.get(),"block/bass_drum");
        fromModelMod(TBlocks.SNARE_DRUM.get(),"block/snare_drum");
        fromModelMod(TBlocks.CYMBAL_CRASH.get(),"block/cymbal_crash");
        fromModelMod(TBlocks.FLOOR_TOM.get(),"block/floor_tom");
        fromModelMod(TBlocks.RACK_TOM.get(),"block/rack_tom");
        fromModelMod(TBlocks.BONGOS.get(),"block/bongos");
        fromModelMod(TBlocks.HI_HAT.get(),"block/hi_hat");
        fromModelMod(TBlocks.CONGAS.get(),"block/congas");
        fromModelMod(TBlocks.CAJON.get(),"block/cajon");
        fromModelMod(TBlocks.VOICE_MICROPHONE.get(),"block/voice_microphone");
        fromModelMod(TBlocks.TEDDY_BEAR.get(),"block/teddy_bear");
        fromModelMod(TBlocks.CHIMNEY.get(),"block/chimney");
        fromModelMod(TBlocks.GOAL.get(),"block/goal");

        // 1.8.0-1.8.4
        fromModelMod(TBlocks.NEWSPAPER_DISPENSER.get(),"block/newspaper_dispenser");
        fromModelMod(TBlocks.RESTAURANT_TRASH_CAN.get(),"block/restaurant_trash_can");
        fromModelMod(TBlocks.SPECIAL_STATUE.get(),"block/statue/special_statue");
        handheld(TItems.PAINT_BRUSH);
        fromModelMod(TBlocks.SNOW_MACHINE.get(),"block/snow_machine");
        fromModelMod(TBlocks.BALL_PIT.get(),"block/ball_pit");
        fromModelMod(TBlocks.BONDING_STATUE.get(),"block/statue/bonding_statue");
        fromModelMod(TBlocks.CATCHING_STATUE.get(),"block/statue/catching_statue");
        fromModelModItem(TItems.STRANGE_STATUE.get(),"block/statue/strange_statue");
        fromModelModItem(TItems.VALIANT_STATUE.get(),"block/statue/valiant_statue");

        flatDefault(TBlocks.ROUND_BUSH.get(),"plants/round_bush");
        flatDefault(TBlocks.BULBLET.get(),"plants/bulblet");
        flatDefault(TBlocks.WISPY_WEED.get(),"plants/wispy_weed");
        fromModelMod(TBlocks.FOOD_COOLER.get(),"block/food_cooler");
    }

    private ItemModelBuilder fromModelModItem(Item item, String source){
        return withExistingParent(item.toString(),
                ResourceLocation.fromNamespaceAndPath("thingamajigs",source));
    }

    private ItemModelBuilder fromModelMod(Block block2, String source){
        return withExistingParent(block2.asItem().toString(),
                ResourceLocation.fromNamespaceAndPath("thingamajigs",source));
    }

    public ItemModelBuilder flatHandheld(Block block, String name){
        return withExistingParent(block.asItem().toString(),
                ResourceLocation.withDefaultNamespace("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath("thingamajigs","block/" + name));
    }

    public ItemModelBuilder flatHandheldRod(Block block, String name){
        return withExistingParent(block.asItem().toString(),
                ResourceLocation.withDefaultNamespace("item/handheld_rod")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath("thingamajigs","block/" + name));
    }

    public ItemModelBuilder flatDefault(Block block, String name){
        return withExistingParent(block.asItem().toString(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath("thingamajigs","block/" + name));
    }

    private ItemModelBuilder defaultSimple(DeferredItem<Item> item){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath("minecraft","item/barrier"));
    }

    // flat 2d custom facing player model
    private ItemModelBuilder defaultCustomSimple(Block block1, String source){
        return withExistingParent(block1.asItem().toString(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath("thingamajigs","block/" + source));
    }

    private ItemModelBuilder simpleCustomBlock(DeferredBlock<Block> block, String fullPathNoModID){
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,fullPathNoModID));
    }

    private ItemModelBuilder itemFromModel(Item item, String source){
        return withExistingParent(item.toString(),
                ResourceLocation.parse(source));
    }

    private ItemModelBuilder fromModel(Block block1, String source){
        return withExistingParent(block1.asItem().toString(),
                ResourceLocation.parse(source));
    }

    private ItemModelBuilder defaultCustomSimpleItem(Block block1, String source){
        return withExistingParent(block1.asItem().toString(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath("thingamajigs","item/" + source));
    }

    // cube_all model with custom texture
    private ItemModelBuilder blockAll(DeferredItem<Item> item, String textureLocation){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("block/cube_all")).texture("all",
                ResourceLocation.parse(textureLocation));
    }

    private ItemModelBuilder blockItemModelAll(String blockName, String textureLocation){
        return withExistingParent(blockName,
                ResourceLocation.parse("thingamajigs:block/" + blockName)).texture("all",
                ResourceLocation.parse(textureLocation));
    }

    // copy block model from existing directory (model must be valid)
    private ItemModelBuilder blockSimple(DeferredItem<Item> item, String blockModelPath){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse(blockModelPath));
    }

    // flat 2D simple item model using texture with same name as item (texture must exist to work)
    private ItemModelBuilder simple(DeferredItem<Item> item){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,"item/" + item.getId().getPath()));
    }

    // vanilla tool model (fishing rods, swords, pickaxes, etc.)
    private ItemModelBuilder handheld(DeferredItem<Item> item){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,"item/" + item.getId().getPath()));
    }

    // custom simple tool model
    private ItemModelBuilder customHandheld(DeferredItem<Item> item, String directory){
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID, directory));
    }
}
