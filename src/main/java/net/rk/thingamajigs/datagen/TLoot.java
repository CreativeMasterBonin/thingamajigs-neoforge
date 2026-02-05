package net.rk.thingamajigs.datagen;

import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.block.custom.CheeseBlock;
import net.rk.thingamajigs.block.custom.GlowingCheeseBlock;
import net.rk.thingamajigs.block.custom.LockableDoor;
import net.rk.thingamajigs.item.TItems;

import java.util.ArrayList;
import java.util.List;

public class TLoot extends VanillaBlockLoot {
    public TLoot(HolderLookup.Provider p) {
        super(p);
    }

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        this.dropSelf(TBlocks.ABANDONED_BOOKSHELF.get());
        this.dropSelf(TBlocks.AC_DUCT_ALLWAY.get());
        this.dropSelf(TBlocks.AC_DUCT_CORNER.get());
        this.dropSelf(TBlocks.AC_DUCT.get());
        this.dropSelf(TBlocks.AC_THERMOSTAT.get());
        this.dropSelf(TBlocks.ACACIA_LANE.get());

        this.dropSelf(TBlocks.AIR_CONDITIONER.get());

        this.dropSelf(TBlocks.AIR_HOCKEY_TABLE.get());
        this.dropSelf(TBlocks.AIR_PURIFIER.get());
        this.dropSelf(TBlocks.AIRDUCT_VENT.get());
        this.dropSelf(TBlocks.AISLE_SIGN.get());
        this.add(TBlocks.ALARMED_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        TBlocks.ALARMED_DOOR,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(TBlocks.ALARMED_DOOR)
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.ALARMED_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));

        this.dropSelf(TBlocks.ALL_WAY_POLE.get());
        this.dropSelf(TBlocks.ALT_FRENCH_BRICK.get());
        this.dropSelf(TBlocks.ALT_ORANGE_CAUTION.get());
        this.dropSelf(TBlocks.ALT_ROAD_COVER.get());
        this.dropSelf(TBlocks.ALT_ROAD_PANEL_COVER.get());
        this.dropSelf(TBlocks.ALT_US_OUTLET.get());

        this.dropSelf(TBlocks.ALT_NEON_BLOCK.get());
        this.dropSelf(TBlocks.AMBER_STRING_LIGHTS.get());
        this.dropSelf(TBlocks.ANCIENT_BOOKSHELF.get());
        this.dropSelf(TBlocks.ANTENNA.get());
        this.dropSelf(TBlocks.AQUARIUM.get());
        this.dropSelf(TBlocks.ARCADE_MACHINE_OPENABLE.get());
        this.dropSelf(TBlocks.ARCADE_MACHINE.get());
        this.dropSelf(TBlocks.ARROW_BEACON.get());
        this.dropSelf(TBlocks.ARROW_BOARD.get());

        this.dropSelf(TBlocks.ATM.get());
        this.dropSelf(TBlocks.AUDIO_CONTROLLER.get());
        this.dropSelf(TBlocks.AUSTRALIAN_OUTLET.get());
        this.dropSelf(TBlocks.AXIS_POLE.get());
        this.dropSelf(TBlocks.BABY_CARRIAGE.get());
        this.dropSelf(TBlocks.BARREL_KEG.get());

        this.dropSelf(TBlocks.BASIC_BATHROOM_TILE.get());
        this.dropSelf(TBlocks.BASKETBALL_HOOP.get());
        this.dropSelf(TBlocks.BASKETBALL_MACHINE.get());
        this.dropSelf(TBlocks.BATHTUB_NOZZLE.get());
        this.dropSelf(TBlocks.BEAKER.get());
        this.dropSelf(TBlocks.BEEP_FIRE_ALARM.get());

        this.dropSelf(TBlocks.BIG_ROAD_CONE.get());

        this.dropSelf(TBlocks.BIG_TV.get());


        this.dropSelf(TBlocks.BIO_HAZARD_SIGN.get());
        this.dropSelf(TBlocks.BIOHAZARD_BIN.get());


        this.dropSelf(TBlocks.BLACK_FAN.get());
        this.dropSelf(TBlocks.BLACK_MAILBOX.get());
        this.dropSelf(TBlocks.BLACK_TELEPHONE.get());
        this.dropSelf(TBlocks.BLANK_BOOKSHELF.get());
        this.dropSelf(TBlocks.BLAST_HAZARD_SIGN.get());
        this.dropSelf(TBlocks.BLENDER.get());

        this.dropSelf(TBlocks.BLUE_BOWLING_BALL.get());
        this.dropSelf(TBlocks.MINI_BLUE_BUILDING.get());

        this.dropSelf(TBlocks.BLUE_SODA_MACHINE.get());

        this.dropSelf(TBlocks.BLUE_STRING_LIGHTS.get());
        this.dropSelf(TBlocks.BLUE_TOME_BOOKSHELF.get());

        this.dropSelf(TBlocks.BLUE_VENDING_MACHINE.get());

        this.dropSelf(TBlocks.BLUEMAN_CONSOLE.get());

        this.dropSelf(TBlocks.SNOWMAN_BLUEMAN_STATUE.get());
        this.dropSelf(TBlocks.BLUEMAN_STATUE.get());

        this.dropSelf(TBlocks.BLUEY_DESKTOP_COMPUTER.get());

        this.dropSelf(TBlocks.BLUEYBOX.get());

        this.dropSelf(TBlocks.BLUEYCUBE_CONSOLE.get());
        this.dropSelf(TBlocks.BLUEYDOWS_LAPTOP.get());

        this.dropSelf(TBlocks.BLUEYSNAP_BASE.get());
        this.dropSelf(TBlocks.BLUEYSNAP_CONSOLE.get());
        this.dropSelf(TBlocks.BLUEYTOSH_LAPTOP_OLD.get());
        this.dropSelf(TBlocks.BLUEYTOSH_LAPTOP.get());
        this.dropSelf(TBlocks.BLUEYTOSH_STUDIO.get());

        this.dropSelf(TBlocks.BONE_BOOKSHELF.get());
        this.dropSelf(TBlocks.BOTH_BATHROOM_SIGN.get());
        this.dropSelf(TBlocks.BOWLING_ALLEY_OILER.get());
        this.dropSelf(TBlocks.BOWLING_BALL_RETRIEVER.get());
        this.dropSelf(TBlocks.BOWLING_FLOORING.get());
        this.dropSelf(TBlocks.BOWLING_GAME_CONTROLLER.get());
        this.dropSelf(TBlocks.BOWLING_PIN.get());
        this.dropSelf(TBlocks.BOX_SECURITY_CAMERA.get());

        this.dropSelf(TBlocks.BREAD_MACHINE.get());
        this.dropSelf(TBlocks.BRICK_BOOKSHELF.get());
        this.dropSelf(TBlocks.BRICK_CULVERT.get());

        this.dropSelf(TBlocks.BRICK_SIDEWALK_HB.get());
        this.dropSelf(TBlocks.BRICK_SIDEWALK.get());
        this.dropSelf(TBlocks.BRIDGE_BARRIER.get());

        this.dropSelf(TBlocks.BROKEN_COMPUTER.get());

        this.dropSelf(TBlocks.BROWN_BOWLING_BALL.get());
        this.dropSelf(TBlocks.BROWN_PATHWAY.get());

        this.add(TBlocks.BUBBLE_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        TBlocks.BUBBLE_DOOR,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(TBlocks.BUBBLE_DOOR)
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.BUBBLE_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(TBlocks.BULBY_FLOWER.get());
        this.dropSelf(TBlocks.BULK_PRODUCT.get());

        this.dropSelf(TBlocks.BUTTER_CHURNER.get());
        this.dropSelf(TBlocks.BUTTON_SWITCH.get());

        this.dropSelf(TBlocks.CALENDAR.get());

        this.dropSelf(TBlocks.CAR_WASH_BLUE_BRUSH.get());
        this.dropSelf(TBlocks.CAR_WASH_DRIPPER.get());
        this.dropSelf(TBlocks.CAR_WASH_DRYER.get());
        this.dropSelf(TBlocks.CAR_WASH_MITTER_CURTAIN.get());
        this.dropSelf(TBlocks.CAR_WASH_MIXED_BRUSH.get());
        this.dropSelf(TBlocks.CAR_WASH_RED_BRUSH.get());
        this.dropSelf(TBlocks.CAR_WASH_SIGNAGE.get());
        this.dropSelf(TBlocks.CAR_WASH_SIGNAL.get());
        this.dropSelf(TBlocks.CAR_WASH_SOAPER.get());
        this.dropSelf(TBlocks.CAR_WASH_SPRAYER.get());
        this.dropSelf(TBlocks.CAR_WASH_TIRE_SCRUBBER.get());
        this.dropSelf(TBlocks.CAR_WASH_TRIFOAMER.get());
        this.dropSelf(TBlocks.CAR_WASH_WAXER.get());
        this.dropSelf(TBlocks.CAR_WASH_YELLOW_BRUSH.get());

        this.dropSelf(TBlocks.CARNIVAL_AWNING.get());

        this.dropSelf(TBlocks.CASH_REGISTER.get());


        this.dropSelf(TBlocks.CATWALK_CENTER.get());
        this.dropSelf(TBlocks.CATWALK.get());
        this.dropSelf(TBlocks.CEILING_FAN.get());
        this.dropSelf(TBlocks.CELL_MULTI_ANGLED_TRANSMITTER.get());
        this.dropSelf(TBlocks.CELL_MULTI_TRANSMITTER.get());
        this.dropSelf(TBlocks.CELL_TRANSMITTER.get());
        this.dropSelf(TBlocks.CHAINLINK_FENCE.get());
        this.dropSelf(TBlocks.CHANGE_MACHINE.get());

        this.dropSelf(TBlocks.CHECKBOARD_WOOL.get());
        this.add(TBlocks.CHEESE_BLOCK.get(), thing -> LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(TBlocks.CHEESE_BLOCK.asItem()))
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(thing)
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(CheeseBlock.FULLNESS, 0)))));

        this.dropSelf(TBlocks.CHEMICAL_TUBE.get());
        this.dropSelf(TBlocks.CHERRY_LANE.get());

        this.dropSelf(TBlocks.CHISELED_PANEL_DARKENED_STONE_BRICKS.get());
        this.dropSelf(TBlocks.CHISELED_PANEL_STONE_BRICKS.get());
        this.dropSelf(TBlocks.CHISELED_STONE_BRICK_PILLAR.get());
        this.dropSelf(TBlocks.CHISELED_TECHNO_BLOCK.get());
        this.dropSelf(TBlocks.CHRISTMAS_FIREPLACE.get());
        this.dropSelf(TBlocks.CHRISTMAS_LIGHTS_ALT.get());
        this.dropSelf(TBlocks.CHRISTMAS_LIGHTS.get());
        this.dropSelf(TBlocks.CHRISTMAS_TREE.get());
        this.dropSelf(TBlocks.CHRISTMAS_WREATH.get());
        this.dropSelf(TBlocks.CIRCUITS.get());
        this.dropSelf(TBlocks.CLASSIC_TV.get());
        this.dropSelf(TBlocks.CLAW_MACHINE.get());
        this.dropSelf(TBlocks.CLOCK_RADIO.get());
        this.dropSelf(TBlocks.COFFEE_GRINDER.get());

        this.dropSelf(TBlocks.COFFEE_MACHINE.get());

        this.dropSelf(TBlocks.COFFIN.get());
        this.dropWhenSilkTouch(TBlocks.COLORED_GLASS.get());
        this.dropSelf(TBlocks.COMMERCIAL_CONDIMENT_DISPENSER.get());
        this.dropSelf(TBlocks.COMMERCIAL_DRYER.get());
        this.dropSelf(TBlocks.COMMERCIAL_JUICE_DISPENSER.get());
        this.dropSelf(TBlocks.COMMERCIAL_LIQUID_DISPENSER.get());
        this.dropSelf(TBlocks.COMMERCIAL_UTENCIL_DISPENSER.get());
        this.dropSelf(TBlocks.COMMERCIAL_WASHER.get());
        this.dropSelf(TBlocks.COMPUTER_CONTROLS.get());
        this.dropSelf(TBlocks.CONCRETE_BARRIER.get());

        this.dropSelf(TBlocks.CONVENIENCE_SHELF.get());
        this.dropSelf(TBlocks.COOKIE_JAR.get());
        this.dropSelf(TBlocks.COPPER_CHAIR.get());
        this.dropSelf(TBlocks.CORNER_COMPUTER_WM.get());
        this.dropSelf(TBlocks.CORNER_COMPUTER.get());
        this.dropSelf(TBlocks.COTTON_CANDY_MAKER.get());
        this.dropSelf(TBlocks.CRACKED_PANEL_STONE_BRICKS.get());



        this.dropSelf(TBlocks.CREEPER_PLUSHY.get());
        this.dropSelf(TBlocks.CRIB.get());

        this.dropSelf(TBlocks.CRIMSON_LANE.get());
        this.dropSelf(TBlocks.CROSS_GRAVESTONE.get());

        this.dropSelf(TBlocks.CROSSWALK_BUTTON.get());
        this.dropSelf(TBlocks.CRYO_HAZARD_SIGN.get());
        this.dropSelf(TBlocks.CRYSTAL_BLOCK.get());
        this.dropSelf(TBlocks.CRYSTALINE_STONE.get());
        this.dropSelf(TBlocks.CULVERT.get());
        this.dropSelf(TBlocks.CUP.get());

        this.dropSelf(TBlocks.DARK_CRYSTAL_BLOCK.get());
        this.dropSelf(TBlocks.DARK_DARKENED_STONE.get());
        this.dropSelf(TBlocks.DARK_FIREOUS_GLAZED_TERRACOTTA.get());
        this.dropSelf(TBlocks.DARK_OAK_LANE.get());
        this.dropSelf(TBlocks.DARKENED_STONE_BRICKS.get());
        this.dropSelf(TBlocks.DARKENED_STONE.get());

        this.dropSelf(TBlocks.DEATH_HAZARD_SIGN.get());
        this.dropSelf(TBlocks.DECORATIVE_PORTAL.get());

        this.dropSelf(TBlocks.DEHUMIDIFIER.get());

        this.dropSelf(TBlocks.DIAMOND_BOWLING_PIN.get());
        this.dropSelf(TBlocks.DIAMOND_CHAIR.get());

        this.dropSelf(TBlocks.DIRT_CULVERT.get());

        this.dropSelf(TBlocks.DISHWASHER_WALL.get());

        this.dropSelf(TBlocks.DIVING_BOARD.get());

        this.dropSelf(TBlocks.DOG_HOUSE.get());
        this.dropSelf(TBlocks.DOME_SECURITY_CAMERA.get());
        this.dropSelf(TBlocks.DOOR_BELL.get());

        this.dropSelf(TBlocks.DRYER.get());

        this.dropSelf(TBlocks.DUCK_STATUE.get());
        this.dropSelf(TBlocks.DUMPSTER.get());
        this.dropSelf(TBlocks.DVD_COLLECTION.get());

        this.dropSelf(TBlocks.DVD_PLAYER.get());

        this.dropSelf(TBlocks.EATING_UTENCILS.get());

        this.dropSelf(TBlocks.ESCALATOR.get());
        this.dropSelf(TBlocks.ESCALATOR_DOWN.get());

        this.dropSelf(TBlocks.EXPENSIVE_BOOKSHELF.get());
        this.dropSelf(TBlocks.EXPERIENCE_BOOKSHELF.get());
        this.dropSelf(TBlocks.EXPLORER_BOOKSHELF.get());
        this.dropSelf(TBlocks.EXPOSED_COPPER_CHAIR.get());
        this.dropSelf(TBlocks.EXPOSED_RUNICSTONE_BLOCK.get());
        this.dropSelf(TBlocks.FALLING_HAZARD_SIGN.get());


        this.dropSelf(TBlocks.FAN_BLOCK_FAST.get());
        this.dropSelf(TBlocks.FAN_BLOCK_OFF.get());
        this.dropSelf(TBlocks.FAN_BLOCK_SPARK.get());
        this.dropSelf(TBlocks.FAN_BLOCK_ULTRASONIC.get());
        this.dropSelf(TBlocks.FAN_BLOCK.get());

        this.dropSelf(TBlocks.FANCY_SINK.get());

        this.dropSelf(TBlocks.FAX_MACHINE.get());
        this.dropSelf(TBlocks.FEATURED_CORDLESS_PHONE.get());
        this.dropSelf(TBlocks.FEMALE_BATHROOM_SIGN.get());
        this.add(TBlocks.FESTIVE_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        TBlocks.FESTIVE_DOOR,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(TBlocks.FESTIVE_DOOR)
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.FESTIVE_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(TBlocks.FILM_SECURITY_CAMERA.get());

        this.dropSelf(TBlocks.FIRE_DETECTOR.get());
        this.dropSelf(TBlocks.FIRE_ESCAPE_LADDER.get());
        this.dropSelf(TBlocks.FIRE_EXTINGUISHER.get());
        this.dropSelf(TBlocks.FIRE_HAZARD_SIGN.get());

        this.dropSelf(TBlocks.FIREOUS_GLAZED_TERRACOTTA.get());
        this.dropSelf(TBlocks.FIREWORKS_DISPLAY.get());

        this.dropSelf(TBlocks.FLASK.get());
        this.dropSelf(TBlocks.FLOWERING_LILY_PAD.get());
        this.dropSelf(TBlocks.FOOD_DEHYDRATOR.get());
        this.dropSelf(TBlocks.FOOD_PROCESSOR.get());
        this.dropSelf(TBlocks.FOOSBALL_TABLE.get());

        this.dropSelf(TBlocks.FREEZER.get());
        this.dropSelf(TBlocks.FRENCH_BRICK.get());
        this.dropSelf(TBlocks.FRENCH_PRESS.get());
        this.dropSelf(TBlocks.FRIDGE.get());
        this.dropSelf(TBlocks.FRIER.get());

        this.dropSelf(TBlocks.FULL_POOP_BLOCK.get());
        this.dropSelf(TBlocks.GARDEN_GNOME.get());

        this.dropSelf(TBlocks.GARDEN_HOSE.get());

        this.dropSelf(TBlocks.GAS_CAN.get());
        this.dropSelf(TBlocks.GAS_HEATER.get());
        this.dropSelf(TBlocks.GAS_PUMP.get());
        this.dropSelf(TBlocks.GEARS_BLOCK.get());
        this.dropSelf(TBlocks.GENERAL_DIGITAL_PHONE.get());
        this.dropSelf(TBlocks.GENERAL_HAZARD_SIGN.get());
        this.dropSelf(TBlocks.GENERATOR.get());

        this.dropSelf(TBlocks.GERMAN_OUTLET.get());
        this.dropSelf(TBlocks.GINGERBREAD_HOUSE.get());

        this.add(TBlocks.GLOWING_CHEESE_BLOCK.get(), thing -> LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(TBlocks.GLOWING_CHEESE_BLOCK.asItem()))
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(thing)
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(GlowingCheeseBlock.FULLNESS, 0)))));
        this.dropSelf(TBlocks.GLOWSTONE_BOOKSHELF.get());
        this.dropSelf(TBlocks.GOLD_BOWLING_PIN.get());
        this.dropSelf(TBlocks.GOLD_CHAIR.get());
        this.dropSelf(TBlocks.GOLD_TABLE.get());

        this.dropSelf(TBlocks.GRADIENT_DARKENED_STONE.get());

        this.dropSelf(TBlocks.GRAPHICS_CARD.get());
        this.dropSelf(TBlocks.GRATE.get());
        this.dropSelf(TBlocks.GRAY_FAN.get());

        this.dropSelf(TBlocks.GREEN_BOWLING_BALL.get());
        this.dropSelf(TBlocks.MINI_GREEN_BUILDING.get());
        this.dropSelf(TBlocks.GREEN_CAUTION.get());

        this.dropSelf(TBlocks.GREEN_TOME_BOOKSHELF.get());

        this.dropSelf(TBlocks.GREY_MAILBOX.get());

        this.dropSelf(TBlocks.GUMBALL_MACHINE.get());
        this.dropSelf(TBlocks.HAMMER_MACHINE.get());
        this.dropSelf(TBlocks.HARD_DRIVE.get());
        this.dropSelf(TBlocks.HARDHAT_HAZARD_SIGN.get());

        this.dropOther(TBlocks.HAWK_SIGNAL_FLASHING_RED.get(),
                TBlocks.HAWK_SIGNAL.asItem());
        this.dropOther(TBlocks.HAWK_SIGNAL_FLASHING_YELLOW.get(),
                TBlocks.HAWK_SIGNAL.asItem());
        this.dropOther(TBlocks.HAWK_SIGNAL_RED.get(),
                TBlocks.HAWK_SIGNAL.asItem());
        this.dropOther(TBlocks.HAWK_SIGNAL_YELLOW.get(),
                TBlocks.HAWK_SIGNAL.asItem());
        this.dropSelf(TBlocks.HAWK_SIGNAL.get());

        this.dropSelf(TBlocks.HEART_MONITOR.get());

        this.dropSelf(TBlocks.HISTORIAN_BOOKSHELF.get());

        this.dropSelf(TBlocks.HOLDER_POLE.get());
        this.dropSelf(TBlocks.HOME_BREAKER.get());

        this.dropSelf(TBlocks.HORIZONTAL_TRAFFIC_SIGNAL_1.get());
        this.dropSelf(TBlocks.HORIZONTAL_TRAFFIC_SIGNAL_2.get());
        this.dropSelf(TBlocks.HORIZONTAL_TRAFFIC_SIGNAL_3.get());
        this.dropSelf(TBlocks.HORN_FIRE_ALARM.get());

        this.dropSelf(TBlocks.HOSPITAL_BED.get());
        this.dropSelf(TBlocks.HOSPITAL_COMPUTER.get());
        this.dropSelf(TBlocks.HOSPITAL_COVER.get());
        this.dropSelf(TBlocks.HOTDOG_ROTATOR.get());
        this.dropSelf(TBlocks.HOTTUB.get());

        this.dropSelf(TBlocks.HUMIDIFIER.get());

        this.dropSelf(TBlocks.ICE_CREAM_MAKER.get());
        this.dropSelf(TBlocks.ICECREAM_MACHINE.get());
        this.dropSelf(TBlocks.INDENTED_STONE.get());
        this.dropSelf(TBlocks.INSET_ATM.get());
        this.dropSelf(TBlocks.INSTANT_POT.get());
        this.dropSelf(TBlocks.INTERNET_JACK_OUTLET.get());
        this.dropSelf(TBlocks.INTERNET_MODEM.get());
        this.dropSelf(TBlocks.NEWER_INTERNET_ROUTER.get());
        this.dropSelf(TBlocks.INTERNET_ROUTER.get());

        this.dropSelf(TBlocks.IRON_CHAIR.get());
        this.dropSelf(TBlocks.IRONING_TABLE.get());
        this.dropSelf(TBlocks.IV.get());

        this.dropSelf(TBlocks.JUICER.get());
        this.dropSelf(TBlocks.JUNGLE_LANE.get());
        this.dropSelf(TBlocks.KITCHEN_SINK.get());
        this.dropSelf(TBlocks.L_ONLY_POLE.get());
        this.dropSelf(TBlocks.L_POLE.get());

        this.dropSelf(TBlocks.LAVA_LAMP.get());
        this.dropSelf(TBlocks.LAWN_MOWER.get());

        this.dropSelf(TBlocks.LIBRARY_STOOL.get());

        this.dropSelf(TBlocks.LIGHT_BLUE_BOWLING_BALL.get());
        this.dropSelf(TBlocks.LIGHT_BLUE_CAUTION.get());

        this.dropSelf(TBlocks.LIGHT_POLE.get());
        this.dropSelf(TBlocks.LIGHTED_CHRISTMAS_TREE.get());
        this.dropSelf(TBlocks.LIGHTED_DEER.get());
        this.dropSelf(TBlocks.LIGHTUP_MACHINE.get());

        this.dropSelf(TBlocks.LIME_BOWLING_BALL.get());


        this.add(TBlocks.LOCKABLE_DOOR.get(),
                LootTable.lootTable().withPool(applyExplosionCondition(TBlocks.LOCKABLE_DOOR,LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(TBlocks.LOCKABLE_DOOR)
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.LOCKABLE_DOOR.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER))
                                ).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.LOCKABLE_DOOR.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LockableDoor.LOCKED,false)))
                        ))));


        this.dropSelf(TBlocks.LOCKER.get());
        this.dropSelf(TBlocks.LOUD_FIRE_ALARM.get());
        this.dropSelf(TBlocks.LOVE_COUCH.get());

        this.dropSelf(TBlocks.LOVE_SEAT_WOOL.get());

        this.dropSelf(TBlocks.LOVE_SEAT.get());

        this.dropSelf(TBlocks.MAILBOX.get());
        this.dropSelf(TBlocks.MALE_BATHROOM_SIGN.get());
        this.dropSelf(TBlocks.MANGROVE_LANE.get());



        this.dropSelf(TBlocks.METAL_SCAFFOLDING.get());
        this.dropSelf(TBlocks.METAL_VENTS.get());
        this.dropSelf(TBlocks.METALLIC_DOOR_BELL.get());
        this.add(TBlocks.METALLIC_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        TBlocks.METALLIC_DOOR,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(TBlocks.METALLIC_DOOR)
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.METALLIC_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(TBlocks.MICROSCOPE.get());
        this.dropSelf(TBlocks.CELL_MICROWAVE_TRANSMITTER.get());
        this.dropSelf(TBlocks.MICROWAVE.get());

        this.dropSelf(TBlocks.MINI_FRIDGE.get());
        this.dropSelf(TBlocks.MINI_RAIL.get());
        this.dropSelf(TBlocks.MINI_ROAD.get());
        this.dropSelf(TBlocks.MINIGOLF_FLAG.get());

        this.dropSelf(TBlocks.MINIGOLF_GRASS_BLOCK.get());
        this.dropSelf(TBlocks.MINIGOLF_HOLE.get());

        this.dropSelf(TBlocks.MIRROR.get());
        this.dropSelf(TBlocks.MOBILE_PHONE.get());

        this.dropSelf(TBlocks.MOONSTONE_BLOCK.get());
        this.dropSelf(TBlocks.MOSSY_PANEL_STONE_BRICKS.get());

        this.dropSelf(TBlocks.MOVING_GEARS_BLOCK.get());
        this.dropSelf(TBlocks.MRPUPPY.get());

        this.dropSelf(TBlocks.MYSTERIOUS_ONE_COUCH.get());
        this.dropSelf(TBlocks.MYSTERIOUS_ONE_WOOL.get());

        this.dropSelf(TBlocks.NEON_BLOCK.get());

        this.dropSelf(TBlocks.NETHER_BRICK_CHAIR.get());
        this.dropSelf(TBlocks.NETHER_BRICK_TABLE.get());
        this.dropWhenSilkTouch(TBlocks.NETHER_CHISELED_BOOKSHELF.get());

        this.dropSelf(TBlocks.TRAFFIC_SIGNAL_SYMBOL_1.get());

        this.dropSelf(TBlocks.NOENTRY_HAZARD_SIGN.get());
        this.dropSelf(TBlocks.NORTH_POLE.get());

        this.dropSelf(TBlocks.NOT_QUITE.get());
        this.dropSelf(TBlocks.NUTCRACKER.get());

        this.dropSelf(TBlocks.OAK_LANE.get());

        this.dropSelf(TBlocks.OFFICE_PHONE.get());

        this.dropSelf(TBlocks.OLD_DOOR_BELL.get());
        this.dropSelf(TBlocks.OLD_FLAT_COMPUTER.get());
        this.dropSelf(TBlocks.OLD_MICROWAVE_TRANSMITTER.get());
        this.dropSelf(TBlocks.OLD_PC.get());

        this.dropSelf(TBlocks.OLD_TEAL_WOOL.get());

        this.dropSelf(TBlocks.OLD_WOODEN_PHONE.get());

        this.dropSelf(TBlocks.OPEN_SIGN_ALT_TWO.get());
        this.dropSelf(TBlocks.OPEN_SIGN_ALT.get());
        this.dropSelf(TBlocks.OPEN_SIGN.get());
        this.dropSelf(TBlocks.OPERATION_TABLE.get());
        this.dropSelf(TBlocks.OPERATION_TOOLS.get());

        this.dropSelf(TBlocks.ORANGE_CAUTION.get());
        this.dropSelf(TBlocks.OUTLET_BLOCK.get());
        this.dropSelf(TBlocks.OVEN.get());
        this.dropSelf(TBlocks.OXIDIZED_COPPER_CHAIR.get());
        this.dropSelf(TBlocks.OXYGEN_HAZARD_SIGN.get());
        this.dropSelf(TBlocks.PANEL_DARKENED_STONE_BRICKS.get());
        this.dropSelf(TBlocks.PANEL_STONE_BRICKS.get());
        this.dropSelf(TBlocks.PANEL_STONE.get());
        this.dropSelf(TBlocks.PANINI_MAKER.get());
        this.dropSelf(TBlocks.PAPER_FLOWER_WALL_BLOCK.get());
        this.dropSelf(TBlocks.PAPER_TOWEL.get());
        this.dropSelf(TBlocks.PAPER_WALL_BLOCK.get());

        this.dropSelf(TBlocks.PARTICULAR_STATUE.get());
        this.dropSelf(TBlocks.PAYPHONE_SEETHROUGH.get());
        this.dropSelf(TBlocks.PAYPHONE.get());

        this.dropSelf(TBlocks.PC_CONTROLS.get());
        this.dropSelf(TBlocks.OLD_PC_MONITOR.get());
        this.dropSelf(TBlocks.MODERN_PC_MONITOR.get());

        this.dropSelf(TBlocks.PED_FLASHERS.get());
        this.dropSelf(TBlocks.PED_SIGNAL_MAN_1.get());
        this.dropSelf(TBlocks.PED_SIGNAL_WORDED.get());
        this.dropSelf(TBlocks.PED_SIGNAL_SYMBOLS.get());

        this.dropSelf(TBlocks.PHONE_SWITCHER.get());
        this.dropSelf(TBlocks.PICNIC_TABLE.get());

        this.dropSelf(TBlocks.PIN_SETTER.get());
        this.dropSelf(TBlocks.PINBALL_MACHINE.get());

        this.dropSelf(TBlocks.PINK_BOWLING_BALL.get());
        this.dropSelf(TBlocks.PIZZA_BOX.get());
        this.dropSelf(TBlocks.PLACARD_GRAVESTONE.get());

        this.dropSelf(TBlocks.PLATE.get());

        this.dropSelf(TBlocks.PLUCK_DOOR_BELL.get());
        this.dropSelf(TBlocks.PLUS_POLE.get());

        this.dropSelf(TBlocks.POISON_HAZARD_SIGN.get());
        this.dropSelf(TBlocks.POOP_CHAIR.get());
        this.dropSelf(TBlocks.POOP.get());
        this.dropOther(TBlocks.POOPOO.get(),TBlocks.POOP.asItem());
        this.dropSelf(TBlocks.POOPSHELF.get());

        this.dropSelf(TBlocks.POPCORN_MACHINE.get());

        this.dropSelf(TBlocks.PORTA_POTTY.get());
        this.dropSelf(TBlocks.PORTABLE_DISH_WASHER.get());
        this.dropSelf(TBlocks.POTION_BOOKSHELF.get());
        this.dropSelf(TBlocks.POWDER_KEG.get());
        this.dropSelf(TBlocks.PRESENT_PILE.get());
        this.dropSelf(TBlocks.PRINTER.get());
        this.dropSelf(TBlocks.PRISMARINE_CHAIR.get());
        this.dropSelf(TBlocks.PRISMARINE_TABLE.get());
        this.dropSelf(TBlocks.PROFESSIONAL_TV_CAMERA.get());
        this.dropSelf(TBlocks.PROJECTOR.get());
        this.dropSelf(TBlocks.PUNCHING_BAG.get());

        this.dropSelf(TBlocks.PURPLE_BOWLING_BALL.get());

        this.dropSelf(TBlocks.PURPUR_CHAIR.get());
        this.dropSelf(TBlocks.PURPUR_TABLE.get());
        this.dropSelf(TBlocks.QUARTZ_CHAIR.get());
        this.dropSelf(TBlocks.QUARTZ_TABLE.get());
        this.dropSelf(TBlocks.RADIATION_HAZARD_SIGN.get());
        this.dropSelf(TBlocks.RADIOACTIVE_BARREL.get());
        this.dropSelf(TBlocks.RADIOWAVES_HAZARD_SIGN.get());

        this.dropSelf(TBlocks.REBAR_CONCRETE_BARRIER.get());

        this.dropSelf(TBlocks.RECYCLE_BIN.get());
        this.dropSelf(TBlocks.TRAFFIC_SIGNAL_ALLWAY_STOP_BEACON.get());

        this.dropSelf(TBlocks.RED_BEACON.get());
        this.dropSelf(TBlocks.RED_BOWLING_PIN.get());
        this.dropSelf(TBlocks.MINI_RED_BUILDING.get());
        this.dropSelf(TBlocks.RED_CAUTION.get());
        this.dropSelf(TBlocks.TRAFFIC_SIGNAL_RED_FLASH.get());
        this.dropSelf(TBlocks.TRAFFIC_SIGNAL_YELLOW_FLASH.get());

        this.dropSelf(TBlocks.RED_SODA_MACHINE.get());

        this.dropSelf(TBlocks.RED_TOME_BOOKSHELF.get());
        this.dropSelf(TBlocks.RED_VENDING_MACHINE.get());
        this.dropSelf(TBlocks.REFRESHMENT_MACHINE.get());

        this.dropSelf(TBlocks.REINDEER_PLUSHY.get());
        this.dropSelf(TBlocks.REINDEER_WALL_HEAD.get());
        this.dropSelf(TBlocks.REINFORCED_CONCRETE_BARRIER.get());
        this.dropWhenSilkTouch(TBlocks.REINFORCED_GLASS.get());

        this.dropSelf(TBlocks.RGB_PC_CONTROLS.get());
        this.dropSelf(TBlocks.RICE_COOKER.get());

        this.dropSelf(TBlocks.ROAD_BARREL.get());
        this.dropSelf(TBlocks.ROAD_BARRIER_BRIDGE_CLOSED.get());
        this.dropSelf(TBlocks.ROAD_BARRIER_BRIDGE_THRU_CLOSED.get());
        this.dropSelf(TBlocks.ROAD_BARRIER_CLOSED.get());
        this.dropSelf(TBlocks.ROAD_BARRIER_LIGHTED.get());
        this.dropSelf(TBlocks.ROAD_BARRIER_SMALL_LIGHTED.get());
        this.dropSelf(TBlocks.ROAD_BARRIER_SMALL.get());
        this.dropSelf(TBlocks.ROAD_BARRIER_THRU_CLOSED.get());
        this.dropSelf(TBlocks.ROAD_CHANNELIZER.get());
        this.dropSelf(TBlocks.ROAD_COVER.get());

        this.dropSelf(TBlocks.ROAD_PANEL_COVER.get());
        this.dropSelf(TBlocks.ROAD_PANEL.get());

        this.dropSelf(TBlocks.ROAST_TURKEY.get());
        this.dropSelf(TBlocks.ROBOT_SECURITY_CAMERA.get());
        this.dropSelf(TBlocks.ROCKER_SWITCH.get());

        this.dropSelf(TBlocks.RAILROAD_CROSSING_BLOCKER.get());

        // rubber wood
        this.dropSelf(TBlocks.RUBBER_LOG.get());
        this.dropSelf(TBlocks.RUBBER_PLANKS.get());
        this.dropSelf(TBlocks.RUBBER_SAPLING.get());
        this.dropSelf(TBlocks.RUBBER_WOOD_BUTTON.get());
        this.add(TBlocks.RUBBER_WOOD_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        TBlocks.RUBBER_WOOD_DOOR,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(TBlocks.RUBBER_WOOD_DOOR)
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.RUBBER_WOOD_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(TBlocks.RUBBER_WOOD_FENCE_GATE.get());
        this.dropSelf(TBlocks.RUBBER_WOOD_FENCE.get());
        this.dropSelf(TBlocks.RUBBER_WOOD_PRESSURE_PLATE.get());

        this.add(TBlocks.RUBBER_WOOD_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TBlocks.RUBBER_WOOD_SLAB.get(),
                                LootItem.lootTableItem(TBlocks.RUBBER_WOOD_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.RUBBER_WOOD_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.dropSelf(TBlocks.RUBBER_WOOD_STAIRS.get());
        this.dropSelf(TBlocks.RUBBER_WOOD_TRAPDOOR.get());
        this.dropSelf(TBlocks.RUBBER_WOOD.get());
        this.dropSelf(TBlocks.STRIPPED_RUBBER_LOG.get());
        this.dropSelf(TBlocks.STRIPPED_RUBBER_WOOD.get());
        //
        this.dropSelf(TBlocks.RUNICSTONE_BRICKS.get());
        this.dropSelf(TBlocks.RUNICSTONE_BLOCK.get());
        this.dropSelf(TBlocks.SALT_TANK.get());
        this.dropSelf(TBlocks.SAND_CULVERT.get());
        this.dropSelf(TBlocks.SANDSTONE_CULVERT.get());
        this.dropSelf(TBlocks.SANTA_INFLATABLE.get());
        this.dropSelf(TBlocks.SANTA_STATUE.get());
        this.dropSelf(TBlocks.SATELLITE_DISH.get());

        this.dropSelf(TBlocks.SCARY_BOOKSHELF.get());
        this.dropSelf(TBlocks.SCHOOL_DESK.get());

        this.dropSelf(TBlocks.SCRAP_PANELS.get());
        this.add(TBlocks.SCREEN_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        TBlocks.SCREEN_DOOR,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(TBlocks.SCREEN_DOOR)
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.SCREEN_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(TBlocks.SCREEN.get());
        this.dropSelf(TBlocks.SCROLLING_YELLOW_CAUTION.get());
        this.dropSelf(TBlocks.SCULK_CHAIN.get());
        this.dropSelf(TBlocks.SCULK_CHAIR.get());
        this.dropSelf(TBlocks.SCULK_LANTERN.get());
        this.dropSelf(TBlocks.SCULK_TABLE.get());


        this.dropSelf(TBlocks.SECURE_SECURITY_CAMERA.get());
        this.dropSelf(TBlocks.SERVER_RACK.get());
        this.dropSelf(TBlocks.SEWING_MACHINE.get());
        this.dropSelf(TBlocks.SHOCK_HAZARD_SIGN.get());
        this.dropSelf(TBlocks.SHOP_VACUUM.get());
        this.dropSelf(TBlocks.SHOPPING_BASKET_PILE.get());
        this.dropSelf(TBlocks.SHOPPING_BASKET.get());
        this.dropSelf(TBlocks.SHOPPING_CART_MOVER.get());
        this.dropSelf(TBlocks.SHOPPING_CART.get());

        this.dropSelf(TBlocks.SHOWER_HANDLES.get());
        this.dropSelf(TBlocks.SHOWER_HEAD.get());

        this.dropSelf(TBlocks.SLEIGH.get());

        this.dropSelf(TBlocks.SLOW_COOKER.get());

        this.dropSelf(TBlocks.SLUSHY_MACHINE.get());
        this.dropSelf(TBlocks.SMALL_CHRISTMAS_TREE.get());
        this.dropSelf(TBlocks.SMALL_SINK.get());
        this.dropSelf(TBlocks.SMARTPHONE.get());

        this.dropSelf(TBlocks.SMOKER_GRILL.get());

        this.add(TBlocks.SNOWMAN_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        TBlocks.SNOWMAN_DOOR,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(TBlocks.SNOWMAN_DOOR)
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.SNOWMAN_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(TBlocks.SNOWMAN_PLUSHY.get());
        this.dropSelf(TBlocks.SNOWMAN.get());

        this.dropSelf(TBlocks.SOLAR_PANEL.get());
        this.dropSelf(TBlocks.SPACE_HEATER.get());

        this.dropSelf(TBlocks.SPRUCE_LANE.get());
        this.dropSelf(TBlocks.STAND_MIXER.get());
        this.dropSelf(TBlocks.STANDARD_GRAVESTONE.get());

        this.dropSelf(TBlocks.STEAM_CLEANER.get());
        this.dropSelf(TBlocks.STEVE_PLUSHY.get());
        this.dropSelf(TBlocks.STONE_BRICK_CULVERT.get());
        this.dropSelf(TBlocks.STONE_BRICK_PILLAR.get());
        this.dropSelf(TBlocks.STONE_CHAIR.get());
        this.dropSelf(TBlocks.STONE_CULVERT.get());

        this.add(TBlocks.STONE_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        TBlocks.STONE_DOOR,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(TBlocks.STONE_DOOR)
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.STONE_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(TBlocks.STONE_PILLAR.get());
        this.dropSelf(TBlocks.STONE_TABLE.get());

        this.dropSelf(TBlocks.STOP_GATE.get());

        this.dropSelf(TBlocks.STORE_FLOORING.get());
        this.dropSelf(TBlocks.STORE_FREEZER.get());
        this.dropSelf(TBlocks.STORE_NUMBER_SIGN.get());
        this.dropSelf(TBlocks.STORE_SHELF.get());

        this.dropSelf(TBlocks.STORE_STAND.get());
        this.dropSelf(TBlocks.STOVE_HOOD.get());
        this.dropSelf(TBlocks.STOVE.get());
        this.dropSelf(TBlocks.STRAIGHT_HORIZONTAL_POLE.get());
        this.dropSelf(TBlocks.STRAIGHT_POLE.get());
        this.dropSelf(TBlocks.STUDIO_CAMERA.get());
        this.dropSelf(TBlocks.SUNSTONE_BLOCK.get());
        this.dropSelf(TBlocks.SWIRLY_TECHNO_BLOCK.get());
        this.dropSelf(TBlocks.T_HORZ_ONLY_POLE.get());
        this.dropSelf(TBlocks.T_POLE_B.get());
        this.dropSelf(TBlocks.T_POLE_C.get());
        this.dropSelf(TBlocks.T_POLE.get());
        this.dropSelf(TBlocks.T_US_OUTLET.get());
        this.dropSelf(TBlocks.TALL_LAMP.get());
        this.dropSelf(TBlocks.MINI_TALL_YELLOW_BUILDING.get());
        this.dropSelf(TBlocks.TEACHING_BOARD.get());

        this.dropSelf(TBlocks.TECHNO_CORE.get());
        this.dropSelf(TBlocks.TECHNO_PILLAR.get());

        this.dropSelf(TBlocks.TENTH_ANNIVERSARY_CAKE.get());
        this.dropSelf(TBlocks.TERRACOTTA_CULVERT.get());

        this.dropSelf(TBlocks.THEATER_SEAT_CONTINUOUS.get());
        this.dropSelf(TBlocks.THEATER_SEAT.get());

        this.dropSelf(TBlocks.THERMOMETER.get());

        this.dropSelf(TBlocks.THREE_WAY_POLE.get());

        this.dropSelf(TBlocks.TICKET_TELLER_WINDOW.get());

        this.dropSelf(TBlocks.TINY_CROSSING.get());
        this.dropSelf(TBlocks.TISSUE_BOX.get());
        this.dropSelf(TBlocks.TL_CONNECTOR.get());

        this.dropSelf(TBlocks.TOASTER_OVEN.get());
        this.dropSelf(TBlocks.TOASTER.get());
        this.dropSelf(TBlocks.TOILET_PAPER.get());
        this.dropSelf(TBlocks.TOILET.get());

        this.dropSelf(TBlocks.TOOL_STATION.get());
        this.dropSelf(TBlocks.TOY_BOX.get());

        this.dropSelf(TBlocks.TRAFFIC_CONTROL_BOX.get());

        this.dropSelf(TBlocks.TRAFFIC_SIGNAL_DOGHOUSE_1.get());
        this.dropSelf(TBlocks.TRAFFIC_SIGNAL_DOGHOUSE_2.get());
        this.dropSelf(TBlocks.TRAFFIC_SIGNAL_NORMAL_1.get());
        this.dropSelf(TBlocks.TRAFFIC_SIGNAL_NORMAL_2.get());
        this.dropSelf(TBlocks.TRAFFIC_SIGNAL_NORMAL_3.get());
        this.dropSelf(TBlocks.TRAFFIC_SIGNAL_NORMAL_4.get());

        this.dropSelf(TBlocks.TRANSPARENT_FAN_BLOCK.get());
        this.dropSelf(TBlocks.TRANSPARENT_FAST_FAN_BLOCK.get());
        this.dropSelf(TBlocks.TRANSPARENT_OFF_FAN_BLOCK.get());
        this.dropSelf(TBlocks.TRASH_CAN.get());
        this.dropSelf(TBlocks.TRI_CANDLE_HOLDER_BLOCK.get());
        this.dropSelf(TBlocks.TRI_POLE_B.get());
        this.dropSelf(TBlocks.TRI_POLE.get());

        this.dropSelf(TBlocks.TRIPLE_LILY_PAD.get());

        this.dropSelf(TBlocks.TV.get());


        this.dropSelf(TBlocks.ULTRA_HD_TV.get());
        this.dropSelf(TBlocks.UK_OUTLET.get());

        this.dropSelf(TBlocks.UNGROUNDED_US_OUTLET.get());

        this.dropSelf(TBlocks.US_OUTLET.get());
        this.dropSelf(TBlocks.USB_OUTLET.get());
        this.dropSelf(TBlocks.STANDING_VACUUM.get());
        this.dropSelf(TBlocks.VELVET_ROPE_FENCE.get());
        this.dropSelf(TBlocks.VERTICAL_AXIS_POLE.get());
        this.dropSelf(TBlocks.VERTICAL_POLE_REDSTONE.get());
        this.dropSelf(TBlocks.VERTICAL_T_POLE.get());
        this.dropSelf(TBlocks.VHS_COLLECTION.get());
        this.dropSelf(TBlocks.VHS_PLAYER.get());
        this.dropSelf(TBlocks.VIDEO_CAMERA.get());

        this.dropSelf(TBlocks.WACK_MACHINE.get());
        this.dropSelf(TBlocks.WAFFLE_IRON.get());
        this.dropSelf(TBlocks.WARDEN_TROPHY.get());
        this.dropSelf(TBlocks.WASHER.get());
        this.dropSelf(TBlocks.WATER_BOTTLE_PACK.get());

        this.dropSelf(TBlocks.WATER_FOUNTAIN.get());
        this.dropSelf(TBlocks.WATER_SLIDE.get());
        this.dropSelf(TBlocks.WATER_SOFTENER.get());
        this.dropSelf(TBlocks.WAXED_COPPER_CHAIR.get());
        this.dropSelf(TBlocks.WAXED_EXPOSED_COPPER_CHAIR.get());
        this.dropSelf(TBlocks.WAXED_OXIDIZED_COPPER_CHAIR.get());
        this.dropSelf(TBlocks.WAXED_WEATHERED_COPPER_CHAIR.get());
        this.dropSelf(TBlocks.WEATHERED_COPPER_CHAIR.get());
        this.dropSelf(TBlocks.WHEELBARROW.get());

        this.dropSelf(TBlocks.WHITE_FAN.get());

        this.dropSelf(TBlocks.WHITE_TELEPHONE.get());

        this.add(TBlocks.WHITE_WOOD_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        TBlocks.WHITE_WOOD_DOOR,
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(TBlocks.WHITE_WOOD_DOOR)
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.WHITE_WOOD_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(TBlocks.WIFI_ROUTER.get());
        this.dropSelf(TBlocks.WINE_BOTTLE.get());
        this.dropSelf(TBlocks.WOOD_CAR.get());
        this.dropSelf(TBlocks.WOOD_DUCK.get());
        this.dropSelf(TBlocks.WORKERS_HAZARD_SIGN.get());

        this.dropSelf(TBlocks.YELLOW_BEACON.get());
        this.dropSelf(TBlocks.YELLOW_BOWLING_BALL.get());
        this.dropSelf(TBlocks.MINI_YELLOW_BUILDING.get());
        this.dropSelf(TBlocks.YELLOW_CAUTION.get());

        this.dropSelf(TBlocks.TRAFFIC_SIGNAL_YELLOW_FLASH.get());

        this.dropSelf(TBlocks.YELLOW_TOME_BOOKSHELF.get());

        this.dropSelf(TBlocks.YOGURT_MAKER.get());
        this.dropSelf(TBlocks.ZOMBIE_PLUSHIE.get());

        this.dropSelf(TBlocks.BIRCH_LANE.get());
        this.dropSelf(TBlocks.WARPED_LANE.get());

        this.dropSelf(TBlocks.GOBO_LIGHT.get());
        this.dropSelf(TBlocks.TURNTABLE.get());

        this.dropSelf(TBlocks.ITEM_DISPLAY_BLOCK.get());

        this.dropSelf(TBlocks.GLOW_BLOCK.get());
        this.dropSelf(TBlocks.LIGHT_GRAY_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.GRAY_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.BLACK_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.BROWN_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.RED_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.ORANGE_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.YELLOW_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.LIME_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.GREEN_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.CYAN_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.LIGHT_BLUE_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.BLUE_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.PURPLE_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.MAGENTA_GLOW_BLOCK.get());
        this.dropSelf(TBlocks.PINK_GLOW_BLOCK.get());

        this.dropSelf(TBlocks.GRAY_SCREEN.get());
        this.dropSelf(TBlocks.BLUE_SCREEN.get());

        this.dropSelf(TBlocks.RUBBER_LANE.get());

        this.add(TBlocks.BRAMBLE.get(),(sup) -> createShearsDispatchTable(sup,
                this.applyExplosionDecay(sup,
                        LootItem.lootTableItem(Items.STICK)
                                .apply(SetItemCountFunction.setCount(
                                        UniformGenerator.between(0.0F, 1.0F))))));

        this.dropPottedContents(TBlocks.POTTED_BRAMBLE.get());

        this.dropSelf(TBlocks.DJ_LASER_LIGHT.get());
        this.dropSelf(TBlocks.CONVEYOR_BELT.get());
        this.dropSelf(TBlocks.ANALOG_CLOCK.get());

        // fire hydrants
        this.dropSelf(TBlocks.RED_FIRE_HYDRANT.get());
        this.dropSelf(TBlocks.YELLOW_FIRE_HYDRANT.get());
        this.dropSelf(TBlocks.SILVER_FIRE_HYDRANT.get());

        this.dropSelf(TBlocks.PARKING_METER.get());

        this.dropSelf(TBlocks.WAXED_COPPER_TABLE.get());
        this.dropSelf(TBlocks.WAXED_EXPOSED_COPPER_TABLE.get());
        this.dropSelf(TBlocks.WAXED_WEATHERED_COPPER_TABLE.get());
        this.dropSelf(TBlocks.WAXED_OXIDIZED_COPPER_TABLE.get());
        this.dropSelf(TBlocks.IRON_TABLE.get());
        this.dropSelf(TBlocks.DIAMOND_TABLE.get());

        this.dropSelf(TBlocks.COPPER_TABLE.get());
        this.dropSelf(TBlocks.EXPOSED_COPPER_TABLE.get());
        this.dropSelf(TBlocks.WEATHERED_COPPER_TABLE.get());
        this.dropSelf(TBlocks.OXIDIZED_COPPER_TABLE.get());


        this.dropSelf(TBlocks.DOOR_BLOCKADE.get());
        this.dropSelf(TBlocks.WINDOW_BLOCKADE.get());

        this.dropSelf(TBlocks.CINDER_BLOCK.get());
        this.dropSelf(TBlocks.CINDER_BLOCK_SMALL.get());

        this.add(TBlocks.CINDER_BLOCK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TBlocks.CINDER_BLOCK_SLAB.get(),
                                LootItem.lootTableItem(TBlocks.CINDER_BLOCK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.CINDER_BLOCK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.dropSelf(TBlocks.I_BEAM.get());
        this.dropSelf(TBlocks.STEEL_HOARDING.get());
        this.dropSelf(TBlocks.STEEL.get());
        this.dropSelf(TBlocks.CONCRETE.get());
        this.dropSelf(TBlocks.CONCRETE_BRICKS.get());
        this.dropSelf(TBlocks.COBBLED_CONCRETE.get());
        this.dropSelf(TBlocks.LADDER_RAILING.get());
        this.dropSelf(TBlocks.CURVED_RAILING.get());
        this.dropSelf(TBlocks.RED_PATHWAY.get());
        this.dropSelf(TBlocks.GRAY_PATHWAY.get());
        this.dropSelf(TBlocks.CONCRETE_STAIRS.get());

        this.add(TBlocks.CONCRETE_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TBlocks.CONCRETE_SLAB.get(),
                                LootItem.lootTableItem(TBlocks.CONCRETE_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.CONCRETE_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.dropSelf(TBlocks.CONCRETE_BRICKS_STAIRS.get());

        this.add(TBlocks.CONCRETE_BRICKS_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TBlocks.CONCRETE_BRICKS_SLAB.get(),
                                LootItem.lootTableItem(TBlocks.CONCRETE_BRICKS_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.CONCRETE_BRICKS_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.dropSelf(TBlocks.COBBLED_CONCRETE_STAIRS.get());

        this.add(TBlocks.COBBLED_CONCRETE_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(TBlocks.COBBLED_CONCRETE_SLAB.get(),
                                LootItem.lootTableItem(TBlocks.COBBLED_CONCRETE_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(TBlocks.COBBLED_CONCRETE_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.dropSelf(TBlocks.DISCO_BALL.get());
        this.dropSelf(TBlocks.LAUNDRY_PILE.get());
        this.dropSelf(TBlocks.LAUNDRY_BASKET.get());
        this.dropSelf(TBlocks.BAR_STOOL.get());
        this.dropSelf(TBlocks.SPEAKER.get());
        this.dropSelf(TBlocks.AUDIO_MIXER.get());

        this.dropSelf(TBlocks.BARBER_POLE.get());
        this.dropSelf(TBlocks.WALL_TV.get());
        this.dropSelf(TBlocks.BARBER_CHAIR.get());
        this.dropSelf(TBlocks.BARBER_HAIR_DRYER.get());
        this.dropSelf(TBlocks.BOXY_CONSOLE.get());
        this.dropSelf(TBlocks.ORANGE_BOXY_CONSOLE.get());
        this.dropSelf(TBlocks.TALL_BOXY_CONSOLE.get());
        this.dropSelf(TBlocks.DENTAL_CUP.get());
        this.dropSelf(TBlocks.GRAY_GAME_CONSOLE.get());
        this.dropSelf(TBlocks.BLACK_GAME_CONSOLE.get());

        // 1.7.6
        this.dropSelf(TBlocks.AIR_FRYER.get());
        this.dropSelf(TBlocks.WATER_DISPENSER.get());
        this.dropSelf(TBlocks.CAT_TREE.get());
        this.dropSelf(TBlocks.TRASH_BAG.get());
        this.dropSelf(TBlocks.GAMING_PC.get());
        this.dropSelf(TBlocks.LITTER_BOX.get());
        this.dropSelf(TBlocks.DESK_LAMP.get());
        this.dropSelf(TBlocks.REFINED_THINGAMAJIG_BLOCK.get());
        this.dropSelf(TBlocks.CAMPING_FUEL_CAN.get());
        this.dropSelf(TBlocks.ICECREAM_DISPLAY.get());
        this.dropSelf(TBlocks.PTAC_AC.get());
        this.dropSelf(TBlocks.CLOTHES_RACK.get());
        this.dropSelf(TBlocks.CARD_READER.get());
        this.dropSelf(TBlocks.CURVED_MONITOR.get());

        // 1.7.7
        this.dropSelf(TBlocks.SECURITY_METAL_DETECTOR.get());
        this.dropSelf(TBlocks.CLEVER_BLACKBOARD.get());
        this.dropSelf(TBlocks.SUPERMARKET_CONVEYOR.get());
        this.dropSelf(TBlocks.UMBRELLA.get());
        this.dropSelf(TBlocks.STRING_BASS.get());
        this.dropSelf(TBlocks.BASS_DRUM.get());
        this.dropSelf(TBlocks.SNARE_DRUM.get());
        this.dropSelf(TBlocks.CYMBAL_CRASH.get());
        this.dropSelf(TBlocks.FLOOR_TOM.get());
        this.dropSelf(TBlocks.RACK_TOM.get());
        this.dropSelf(TBlocks.HI_HAT.get());
        this.dropSelf(TBlocks.BONGOS.get());
        this.dropSelf(TBlocks.CONGAS.get());
        this.dropSelf(TBlocks.CAJON.get());
        this.dropSelf(TBlocks.VOICE_MICROPHONE.get());
        this.dropSelf(TBlocks.THEATER_PROJECTOR.get());
        this.dropSelf(TBlocks.TRIPLE_SHELF.get());
        this.dropSelf(TBlocks.TEDDY_BEAR.get());
        this.dropSelf(TBlocks.CHIMNEY.get());
        this.dropSelf(TBlocks.MYSTERIOUS_PILLAR.get());
        this.dropSelf(TBlocks.GOAL.get());


        this.add(TBlocks.FAKE_FLUID_PUMP.get(),noDrop());

        // torches
        this.dropOther(TBlocks.WALL_CLEAR_BULB.get(), TItems.CLEAR_BULB_ITEM);
        this.dropOther(TBlocks.GROUND_CLEAR_BULB.get(), TItems.CLEAR_BULB_ITEM);

        this.dropOther(TBlocks.WALL_FULL_BULB.get(), TItems.FULL_BULB_ITEM);
        this.dropOther(TBlocks.GROUND_FULL_BULB.get(), TItems.FULL_BULB_ITEM);

        this.dropOther(TBlocks.WALL_CLEAR_LANTERN.get(), TItems.CLEAR_LANTERN_ITEM);
        this.dropOther(TBlocks.GROUND_CLEAR_LANTERN.get(), TItems.CLEAR_LANTERN_ITEM);

        this.dropOther(TBlocks.WALL_FULL_LANTERN.get(), TItems.FULL_LANTERN_ITEM);
        this.dropOther(TBlocks.GROUND_FULL_LANTERN.get(), TItems.FULL_LANTERN_ITEM);


        this.dropOther(TBlocks.WALL_PAPER_LANTERN.get(), TItems.PAPER_LANTERN_ITEM);
        this.dropOther(TBlocks.PAPER_LANTERN.get(), TItems.PAPER_LANTERN_ITEM);

        this.dropOther(TBlocks.WALL_RED_LANTERN.get(), TItems.RED_LANTERN_ITEM);
        this.dropOther(TBlocks.RED_LANTERN.get(), TItems.RED_LANTERN_ITEM);

        // rubber leaves
        this.add(TBlocks.RUBBER_LEAVES.get(),
                thing -> this.createRubberLeavesDrops(thing,TBlocks.RUBBER_SAPLING.get(),NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(TBlocks.DROOPY_FLOWER.get());

        this.dropPottedContents(TBlocks.POTTED_BULBY_FLOWER.get());
        this.dropPottedContents(TBlocks.POTTED_DROOPY_FLOWER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        List<Block> knownBlocks = new ArrayList<>();
        knownBlocks.addAll(TBlocks.BLOCKS.getEntries().stream().map(DeferredHolder::get).toList());
        return knownBlocks;
    }


    protected LootItemCondition.Builder hasSilkTouch() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return MatchTool.toolMatches(
                ItemPredicate.Builder.item()
                        .withSubPredicate(
                                ItemSubPredicates.ENCHANTMENTS,
                                ItemEnchantmentsPredicate.enchantments(
                                        List.of(new EnchantmentPredicate(registrylookup.getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1)))
                                )
                        )
        );
    }


    public LootTable.Builder createRubberLeavesDrops(Block rubberLeaves, Block saplingBlock,float... chances) {
        HolderLookup.RegistryLookup<Enchantment> rl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createLeavesDrops(rubberLeaves,saplingBlock,chances)
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(hasSilkTouch().invert())
                        .add(this.applyExplosionCondition(rubberLeaves, LootItem.lootTableItem(TItems.TREE_RESIN.asItem()))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(
                                        rl.getOrThrow(Enchantments.FORTUNE), 0.002F,
                                        0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
    }
}
