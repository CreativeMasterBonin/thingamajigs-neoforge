package net.rk.thingamajigs.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import net.rk.thingamajigs.block.custom.ToggledStateBlock;

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

        rotatedThingamajigsDecoration(TBlocks.NEWSPAPER_DISPENSER.get(),"thingamajigs:block/newspaper_dispenser");
        rotatedThingamajigsDecoration(TBlocks.RESTAURANT_TRASH_CAN.get(),"thingamajigs:block/restaurant_trash_can");
        rotatedThingamajigsDecoration(TBlocks.SPECIAL_STATUE.get(),"thingamajigs:block/statue/special_statue");
        rotatedThingamajigsDecoration(TBlocks.SNOW_MACHINE.get(),"thingamajigs:block/snow_machine");
        rotatedThingamajigsDecoration(TBlocks.CATCHING_STATUE.get(),"thingamajigs:block/statue/catching_statue");
        rotatedThingamajigsDecoration(TBlocks.STRANGE_STATUE.get(),"thingamajigs:block/statue/strange_statue");
        rotatedThingamajigsDecoration(TBlocks.VALIANT_STATUE.get(),"thingamajigs:block/statue/valiant_statue");

        rotatedThingamajigsDecoration(TBlocks.PLUNGER.get(),"thingamajigs:block/plunger");
        rotatedThingamajigsDecoration(TBlocks.PIZZA.get(),"thingamajigs:block/pizza");
        rotatedThingamajigsDecoration(TBlocks.TOWEL_STACK.get(),"thingamajigs:block/towel_stack");
        rotatedThingamajigsDecoration(TBlocks.RARE_BLUE_GRAY_GAME_CONSOLE.get(),"thingamajigs:block/rare_blue_gray_game_console");
        rotatedThingamajigsDecoration(TBlocks.FUNDEVICE_GAME_CONSOLE.get(),"thingamajigs:block/fundevice_game_console");
        rotatedThingamajigsDecoration(TBlocks.GOLDME_CONSOLE.get(),"thingamajigs:block/goldme_console");
        rotatedThingamajigsDecoration(TBlocks.FURIOUS_STATUE.get(),"thingamajigs:block/statue/furious_statue");
        rotatedThingamajigsDecoration(TBlocks.SORROW_STATUE.get(),"thingamajigs:block/statue/sorrow_statue");
        rotatedThingamajigsDecoration(TBlocks.SOCCER_BALL.get(),"thingamajigs:block/soccer_ball");
        rotatedThingamajigsDecoration(TBlocks.BASKETBALL.get(),"thingamajigs:block/basketball");
        rotatedThingamajigsDecoration(TBlocks.TENNIS_RACKET.get(),"thingamajigs:block/tennis_racket");
        rotatedThingamajigsDecoration(TBlocks.PHONE_CROSSBAR.get(),"thingamajigs:block/phone_crossbar");
        rotatedThingamajigsDecoration(TBlocks.STAINLESS_WASHER.get(),"thingamajigs:block/stainless_washer");
        rotatedThingamajigsDecoration(TBlocks.WEIGHT_SCALE.get(),"thingamajigs:block/weight_scale");
        rotatedThingamajigsDecoration(TBlocks.PHONE_GROUP_SELECTOR.get(),"thingamajigs:block/phone_group_selector");
        rotatedThingamajigsDecoration(TBlocks.PHONE_AXIS_SWITCH.get(),"thingamajigs:block/phone_axis_switch");
        rotatedThingamajigsDecoration(TBlocks.PHONE_AXIS_SWITCH_RELAY.get(),"thingamajigs:block/phone_axis_switch_relay");

        /*
        rotatedThingamajigsDecoration(TBlocks.STAINLESS_WASHER.get(),"thingamajigs:block/stainless_washer");
        rotatedThingamajigsDecoration(TBlocks.WEIGHT_SCALE.get(),"thingamajigs:block/weight_scale");
        rotatedThingamajigsDecoration(TBlocks.PHONE_GROUP_SELECTOR.get(),"thingamajigs:block/phone_group_selector");
        rotatedThingamajigsDecoration(TBlocks.PHONE_AXIS_SWITCH.get(),"thingamajigs:block/phone_axis_switch");
        rotatedThingamajigsDecoration(TBlocks.PHONE_AXIS_SWITCH_RELAY.get(),"thingamajigs:block/phone_axis_switch_relay");
         */
    }

    public void rotatedThingamajigsDecoration(Block block, String modelLocation){
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(ThingamajigsDecorativeBlock.FACING);
            ModelFile decorationModel = new ModelFile(ResourceLocation.tryParse(modelLocation)) {
                @Override
                protected boolean exists() {
                    return true;
                }
            };
            return ConfiguredModel.builder()
                    .modelFile(decorationModel)
                    .rotationY((int)(facing.getOpposite()).toYRot())
                    .uvLock(false)
                    .build();
        });
    }

    public void rotatedToggledThingamajigsDecoration(Block block,String toggledModel,String untoggledModel){
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(ToggledStateBlock.FACING);
            boolean toggled = state.getValue(ToggledStateBlock.TOGGLED);
            ModelFile decorationModelToggled = new ModelFile(ResourceLocation.tryParse(toggledModel)) {
                @Override
                protected boolean exists() {
                    return true;
                }
            };
            ModelFile decorationModelUntoggled = new ModelFile(ResourceLocation.tryParse(untoggledModel)) {
                @Override
                protected boolean exists() {
                    return true;
                }
            };
            return ConfiguredModel.builder()
                    .modelFile(toggled ? decorationModelToggled : decorationModelUntoggled)
                    .rotationY((int)(facing.getOpposite()).toYRot())
                    .uvLock(false)
                    .build();
        });
    }
}
