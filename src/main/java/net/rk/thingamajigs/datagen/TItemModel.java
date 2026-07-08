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

        simple(TItems.MUSIC_COMPONENT);
        fromModelMod(TBlocks.SECURITY_METAL_DETECTOR.get(),"block/security_metal_detector");
        fromModelMod(TBlocks.TRIPLE_SHELF.get(),"block/triple_shelf");
        fromModelMod(TBlocks.MYSTERIOUS_PILLAR.get(),"block/pillar/mysterious_standing");
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

        fromModelMod(TBlocks.ROUND_CLOTHES_RACK.get(),"block/full_round_clothes_rack");
        fromModelMod(TBlocks.PLUNGER.get(),"block/plunger");
        fromModelMod(TBlocks.PIZZA.get(),"block/pizza");
        fromModelMod(TBlocks.CAR_WHEEL.get(),"block/car_wheel");
        fromModelMod(TBlocks.TOWEL_STACK.get(),"block/towel_stack");
        fromModelMod(TBlocks.RARE_BLUE_GRAY_GAME_CONSOLE.get(),"block/rare_blue_gray_game_console");
        fromModelMod(TBlocks.FUNDEVICE_GAME_CONSOLE.get(),"block/fundevice_game_console");
        fromModelMod(TBlocks.GOLDME_CONSOLE.get(),"block/goldme_console");
        fromModelMod(TBlocks.CARDBOARD_BOX.get(),"block/cardboard_box");
        fromModelMod(TBlocks.FURIOUS_STATUE.get(),"block/statue/furious_statue");
        fromModelMod(TBlocks.SORROW_STATUE.get(),"block/statue/sorrow_statue");
        fromModelMod(TBlocks.SOCCER_BALL.get(),"block/soccer_ball");
        fromModelMod(TBlocks.BASKETBALL.get(),"block/basketball");
        fromModelMod(TBlocks.TENNIS_BALL.get(),"block/tennis_ball/single_tennis_ball");
        fromModelMod(TBlocks.TENNIS_NET.get(),"block/tennis_net/tennis_net_middle");
        fromModelMod(TBlocks.TENNIS_RACKET.get(),"block/tennis_racket");
        fromModelMod(TBlocks.PHONE_CROSSBAR.get(),"block/phone_crossbar");
        fromModelMod(TBlocks.STAINLESS_WASHER.get(),"block/stainless_washer");
        fromModelMod(TBlocks.WEIGHT_SCALE.get(),"block/weight_scale");
        fromModelMod(TBlocks.PHONE_GROUP_SELECTOR.get(),"block/phone_group_selector");
        fromModelMod(TBlocks.PHONE_AXIS_SWITCH.get(),"block/phone_axis_switch");
        fromModelMod(TBlocks.PHONE_AXIS_SWITCH_RELAY.get(),"block/phone_axis_switch_relay");
        // 1.8.5
        fromModelMod(TBlocks.DELUXE_CAT_TREE.get(),"block/deluxe_cat_tree");
        fromModelMod(TBlocks.CLAW_MACHINE.get(),"block/claw_machine");
        fromModelMod(TBlocks.OLD_MICROWAVE_TRANSMITTER.get(),"block/old_microwave_reflector");
        fromModelMod(TBlocks.OLD_MICROWAVE_TRANSMITTER_OPAQUE.get(),"block/old_microwave_reflector_opaque");
        fromModelMod(TBlocks.OLD_MICROWAVE_REFLECTOR_ROUNDED.get(),"block/old_microwave_reflector_rounded");
        fromModelMod(TBlocks.OLD_MICROWAVE_REFLECTOR_ROUNDED_OPAQUE.get(),"block/old_microwave_reflector_rounded_opaque");
        fromModelMod(TBlocks.DECORATIONAL_BUCKET.get(),"block/decorational_bucket");
        fromModelMod(TBlocks.EASEL.get(),"item/easel_all");
        fromModelMod(TBlocks.PORTABLE_DISH_WASHER.get(),"block/portable_dishwasher");
        fromModelMod(TBlocks.WHITE_CUBE_SHELF.get(),"block/white_cube_shelf");
        fromModelMod(TBlocks.WHITE_SECTIONED_SHELF.get(),"block/white_sectioned_shelf");
        fromModelMod(TBlocks.RUBBER_DUCK.get(),"block/rubber_duck");
        fromModelMod(TBlocks.AIR_STATION.get(),"block/air_station");
        fromModelMod(TBlocks.SAFE.get(),"block/safe_closed");
        fromModelMod(TBlocks.CAKE_DISPLAY_CASE.get(),"block/cake_display_case_empty");
        fromModelMod(TBlocks.CELL_TOWER_AMPLIFIER.get(),"block/cell_tower_amplifier");
        fromModelMod(TBlocks.FANCY_GAS_PUMP.get(),"block/fancy_gas_pump");
        fromModelMod(TBlocks.DELUXE_ARCADE_MACHINE.get(),"item/deluxe_arcade_machine_held");
        fromModelMod(TBlocks.CEILING_FAN.get(),"block/ceiling_fan");
        fromModelMod(TBlocks.DAUNTING_STATUE.get(),"block/statue/daunting_statue");
        fromModelMod(TBlocks.EXPOSED_DAUNTING_STATUE.get(),"block/statue/exposed_daunting_statue");
        fromModelMod(TBlocks.WEATHERED_DAUNTING_STATUE.get(),"block/statue/weathered_daunting_statue");
        fromModelMod(TBlocks.OXIDIZED_DAUNTING_STATUE.get(),"block/statue/oxidized_daunting_statue");
        fromModelMod(TBlocks.WAXED_DAUNTING_STATUE.get(),"block/statue/daunting_statue");
        fromModelMod(TBlocks.WAXED_EXPOSED_DAUNTING_STATUE.get(),"block/statue/exposed_daunting_statue");
        fromModelMod(TBlocks.WAXED_WEATHERED_DAUNTING_STATUE.get(),"block/statue/weathered_daunting_statue");
        fromModelMod(TBlocks.WAXED_OXIDIZED_DAUNTING_STATUE.get(),"block/statue/oxidized_daunting_statue");
        fromModelMod(TBlocks.GRAB_BAR.get(),"block/grab_bar/hand_bar_unconnected");
        fromModelMod(TBlocks.URINAL.get(),"block/toilets/urinal");
        fromModelMod(TBlocks.TUBE_MAN_DECO.get(),"bases/tube_man_components/tube_man_base_compressed");
        fromModelMod(TBlocks.BLUEYBOX.get(),"block/blueybox");
        flatHandheldItem(TItems.DEBIT_CARD.asItem(),"debit_card");
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

    public ItemModelBuilder flatHandheldItem(Item item, String name){
        return withExistingParent(item.asItem().toString(),
                ResourceLocation.withDefaultNamespace("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath("thingamajigs","item/" + name));
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
