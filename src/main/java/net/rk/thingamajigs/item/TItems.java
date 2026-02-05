package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.xtras.DecorationCategory;

import java.util.List;

@SuppressWarnings("deprecated")
public class TItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Thingamajigs.MODID);

    public static final DeferredItem<Item> THINGAMAJIG = ITEMS.register("thingamajig",
            () -> new ThingamajigItem(new Item.Properties()));

    public static final DeferredItem<Item> THINGAMAJIG_GLOB = ITEMS.register("thingamajig_glob",
            () -> new Item(new Item.Properties()){
                @Override
                public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> lc, TooltipFlag p_41424_) {
                    lc.add(Component.translatable("tooltip.thingamajigs.thingamajig_glob")
                            .withStyle(ChatFormatting.GRAY));
                    lc.add(Component.translatable("title.thingamajigs.cbui")
                            .withStyle(ChatFormatting.GREEN));
                    lc.add(Component.translatable("tooltip.thingamajigs.compat_crafters")
                            .withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
                }
            });

    public static final DeferredItem<Item> CIRCLE_SIGN_GLOB = ITEMS.register("circle_sign_glob",
            () -> new BaseGlob(new Item.Properties(),"thingamajigs.glob.circle_sign.desc",false, Rarity.COMMON));
    public static final DeferredItem<Item> SQUARE_SIGN_GLOB = ITEMS.register("square_sign_glob",
            () -> new BaseGlob(new Item.Properties(),"thingamajigs.glob.square_sign.desc",false,Rarity.COMMON));
    public static final DeferredItem<Item> TRIANGLE_SIGN_GLOB = ITEMS.register("triangle_sign_glob",
            () -> new BaseGlob(new Item.Properties(),"thingamajigs.glob.triangle_sign.desc",false,Rarity.COMMON));
    public static final DeferredItem<Item> MISC_SIGN_GLOB = ITEMS.register("misc_sign_glob",
            () -> new BaseGlob(new Item.Properties(),"thingamajigs.glob.misc_sign.desc",false,Rarity.COMMON));
    public static final DeferredItem<Item> GLOB_SANDWICH = ITEMS.register("glob_sandwich",
            () -> new Item((new Item.Properties()
                    .food(TFoodProps.GLOBIZED_SANDWICH))){
                @Override
                public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> lc, TooltipFlag p_41424_) {
                    lc.add(Component.translatable("tooltip.thingamajigs.glob_sandwich").withStyle(ChatFormatting.GRAY));
                }
            });

    public static final DeferredItem<Item> SIGN_GLOB = ITEMS.register("sign_glob",
            () -> new Item((new Item.Properties().stacksTo(64))){
                @Override
                public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
                    p_41423_.add(Component.translatable("tooltip.thingamajigs.sign_glob").withStyle(ChatFormatting.GRAY));
                }
            });
    public static final DeferredItem<Item> DOOR_GLOB = ITEMS.register("door_glob",
            () -> new Item((new Item.Properties().stacksTo(64))){
                @Override
                public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
                    p_41423_.add(Component.translatable("tooltip.thingamajigs.door_glob").withStyle(ChatFormatting.GRAY));
                }
            });

    // op blockitems
    public static final DeferredItem<Item> WATER_SOURCE = ITEMS.register("water_source",
            () -> new BlockItem(Blocks.WATER, new Item.Properties()){
                @Override
                public InteractionResult useOn(UseOnContext uoc) {
                    Player p = uoc.getPlayer();
                    if(p != null){
                        if(p.isCreative()){
                            return super.useOn(uoc);
                        }
                    }
                    return InteractionResult.PASS;
                }
            });
    public static final DeferredItem<Item> NP_PLACEABLE = ITEMS.register("nether_portal_placeable",
            () -> new BlockItem(Blocks.NETHER_PORTAL, new Item.Properties()));
    public static final DeferredItem<Item> EP_PLACEABLE = ITEMS.register("end_portal_placeable",
            () -> new BlockItem(Blocks.END_PORTAL, new Item.Properties()));
    public static final DeferredItem<Item> EG_PLACEABLE = ITEMS.register("end_gateway_placeable",
            () -> new BlockItem(Blocks.END_GATEWAY, new Item.Properties()));
    public static final DeferredItem<Item> VOID_AIR_PLACEABLE = ITEMS.register("void_air_placeable",
            () -> new BlockItem(Blocks.VOID_AIR, new Item.Properties()));

    public static final DeferredItem<Item> ILLUSIONER_SPAWN_EGG = ITEMS.register("illusioner_spawn_egg",
            () -> new DeferredSpawnEggItem(() -> EntityType.ILLUSIONER,
                    1267859,7311783,
                    new Item.Properties()));

    public static final DeferredItem<Item> KEY = ITEMS.register("key",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)){
                @Override
                public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> lc, TooltipFlag p_41424_) {
                    lc.add(Component.translatable("item.thingamajigs.key.desc"));
                }
            });

    public static final DeferredItem<Item> TREE_RESIN = ITEMS.register("tree_resin",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> PAINT_BRUSH = ITEMS.register("paint_brush",
            () -> new Item(new Item.Properties().stacksTo(1)){
                @Override
                public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> lc, TooltipFlag p_41424_) {
                    lc.add(Component.translatable("tooltip.thingamajigs.blankpaintbrush"));
                }
            });

    public static final DeferredItem<Item> MONEY = ITEMS.register("money",
            () -> new Item(new Item.Properties().stacksTo(64)){
                @Override
                public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> lc, TooltipFlag p_41424_) {
                    lc.add(Component.translatable("item.thingamajigs.money.desc").withStyle(ChatFormatting.GRAY));
                }
            });

    public static final DeferredItem<Item> COIN = ITEMS.register("coin",
            () -> new Item(new Item.Properties().stacksTo(64)){
                @Override
                public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> lc, TooltipFlag p_41424_) {
                    lc.add(Component.translatable("item.thingamajigs.coin.desc").withStyle(ChatFormatting.GRAY));
                }
            });

    public static final DeferredItem<Item> GIANT_SPAWN_EGG = ITEMS.register("giant_spawn_egg",
            () -> new DeferredSpawnEggItem(() -> EntityType.GIANT,
                    52428,4813109,
                    new Item.Properties()){});


    // component items
    public static final DeferredItem<Item> BASE_COMPONENT = ITEMS.register("base_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.base",
                    false,
                    DecorationCategory.Categories.GENERIC,
                    false));
    public static final DeferredItem<Item> INFRASTRUCTURE_COMPONENT = ITEMS.register("infrastructure_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.infrastructure",
                    false,
                    DecorationCategory.Categories.INFRASTRUCTURE,
                    false));
    public static final DeferredItem<Item> FACTORY_COMPONENT = ITEMS.register("factory_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.factory",
                    false,
                    DecorationCategory.Categories.FACTORY,
                    false));
    public static final DeferredItem<Item> TECHNOLOGY_COMPONENT = ITEMS.register("technology_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.technology",
                    false,
                    DecorationCategory.Categories.TECHNOLOGY,
                    false));
    public static final DeferredItem<Item> SPORTS_COMPONENT = ITEMS.register("sports_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.sports",
                    false,
                    DecorationCategory.Categories.SPORTS,
                    false));

    public static final DeferredItem<Item> FURNITURE_COMPONENT = ITEMS.register("furniture_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.furniture",
                    false,
                    DecorationCategory.Categories.FURNITURE,
                    false));
    public static final DeferredItem<Item> MISC_COMPONENT = ITEMS.register("misc_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.misc",
                    false,
                    DecorationCategory.Categories.MISCELLANEOUS,
                    false));

    public static final DeferredItem<Item> MINI_COMPONENT = ITEMS.register("mini_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.mini",
                    false,
                    DecorationCategory.Categories.MINI_CITY,
                    false));



    // subcategory components
    public static final DeferredItem<Item> CAR_WASH_COMPONENT = ITEMS.register("car_wash_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.car_wash",
                    false,
                    DecorationCategory.Subcategories.CAR_WASH,
                    false));

    public static final DeferredItem<Item> TRAFFIC_SIGNAL_COMPONENT = ITEMS.register("traffic_signal_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.traffic_signal",
                    false,
                    DecorationCategory.Subcategories.TRAFFIC_SIGNALS,
                    false));

    public static final DeferredItem<Item> RAILROAD_COMPONENT = ITEMS.register("railroad_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.railroad",
                    false,
                    DecorationCategory.Subcategories.RAILROAD,
                    false));

    public static final DeferredItem<Item> COMPUTER_COMPONENT = ITEMS.register("computer_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.computer",
                    false,
                    DecorationCategory.Subcategories.COMPUTERS,
                    false));

    public static final DeferredItem<Item> GAME_CONSOLE_COMPONENT = ITEMS.register("game_console_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.game_console",
                    false,
                    DecorationCategory.Subcategories.GAME_CONSOLES,
                    false));

    public static final DeferredItem<Item> CHRISTMAS_COMPONENT = ITEMS.register("christmas_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.christmas",
                    false,
                    DecorationCategory.Subcategories.CHRISTMAS,
                    false));

    public static final DeferredItem<Item> SAFETY_COMPONENT = ITEMS.register("safety_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.safety",
                    false,
                    DecorationCategory.Subcategories.SAFETY,
                    false));

    public static final DeferredItem<Item> ARCADE_COMPONENT = ITEMS.register("arcade_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.arcade",
                    false,
                    DecorationCategory.Subcategories.ARCADE,
                    false));

    public static final DeferredItem<Item> HOME_COMPONENT = ITEMS.register("home_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.home",
                    false,
                    DecorationCategory.Subcategories.HOME,
                    false));

    public static final DeferredItem<Item> APPLIANCE_COMPONENT = ITEMS.register("appliance_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.appliance",
                    false,
                    DecorationCategory.Subcategories.APPLIANCE,
                    false));

    public static final DeferredItem<Item> PHONE_COMPONENT = ITEMS.register("phone_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.phone",
                    false,
                    DecorationCategory.Subcategories.PHONE,
                    false));

    public static final DeferredItem<Item> SCIENCE_COMPONENT = ITEMS.register("science_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.science",
                    false,
                    DecorationCategory.Subcategories.SCIENCE,
                    false));

    public static final DeferredItem<Item> HEALTH_COMPONENT = ITEMS.register("health_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.health",
                    false,
                    DecorationCategory.Subcategories.HEALTH,
                    false));

    public static final DeferredItem<Item> TOY_COMPONENT = ITEMS.register("toy_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.toy",
                    false,
                    DecorationCategory.Subcategories.TOY,
                    false));

    public static final DeferredItem<Item> MUSIC_COMPONENT = ITEMS.register("music_component",
            () -> new ComponentBase(new Item.Properties(),
                    "thingamajigs.component.music",
                    false,
                    DecorationCategory.Subcategories.MUSIC,
                    false));

    public static final DeferredItem<Item> RUBBER = ITEMS.register("rubber",
            () -> new Item(new Item.Properties().stacksTo(64).setNoRepair()){
                @Override
                public void appendHoverText(ItemStack p_41421_, TooltipContext p_339594_, List<Component> p_41423_, TooltipFlag p_41424_) {
                    p_41423_.add(Component.translatable("item.thingamajigs.rubber.desc").withStyle(ChatFormatting.GRAY));
                }
            });

    // removed crafting items as they are an addon now

    // torch items
    public static final DeferredItem<Item> CLEAR_BULB_ITEM = ITEMS.register("clear_bulb",
            () -> new StandingAndWallBlockItem(
                    TBlocks.GROUND_CLEAR_BULB.get(),TBlocks.WALL_CLEAR_BULB.get(),new Item.Properties(), Direction.DOWN));
    public static final DeferredItem<Item> FULL_BULB_ITEM = ITEMS.register("full_bulb",
            () -> new StandingAndWallBlockItem(
                    TBlocks.GROUND_FULL_BULB.get(),TBlocks.WALL_FULL_BULB.get(),new Item.Properties(), Direction.DOWN));
    public static final DeferredItem<Item> CLEAR_LANTERN_ITEM = ITEMS.register("clear_lantern",
            () -> new StandingAndWallBlockItem(
                    TBlocks.GROUND_CLEAR_LANTERN.get(),TBlocks.WALL_CLEAR_LANTERN.get(),new Item.Properties(), Direction.DOWN));
    public static final DeferredItem<Item> FULL_LANTERN_ITEM = ITEMS.register("full_lantern",
            () -> new StandingAndWallBlockItem(
                    TBlocks.GROUND_FULL_LANTERN.get(),TBlocks.WALL_FULL_LANTERN.get(),new Item.Properties(), Direction.DOWN));

    public static final DeferredItem<Item> RED_LANTERN_ITEM = ITEMS.register("red_lantern_item",
            () -> new StandingAndWallBlockItem(
                    TBlocks.RED_LANTERN.get(),
                    TBlocks.WALL_RED_LANTERN.get(),
                    new Item.Properties(),
                    Direction.DOWN));

    public static final DeferredItem<Item> PAPER_LANTERN_ITEM = ITEMS.register("paper_lantern_item",
            () -> new StandingAndWallBlockItem(
                    TBlocks.PAPER_LANTERN.get(),
                    TBlocks.WALL_PAPER_LANTERN.get(),
                    new Item.Properties(),
                    Direction.DOWN));

    public static final DeferredItem<Item> FLOWERING_LILY_PAD_ITEM = ITEMS.register("flowering_lily_pad_item",
            () -> new PlaceOnWaterBlockItem(TBlocks.FLOWERING_LILY_PAD.get(),new Item.Properties()));
    public static final DeferredItem<Item> TRIPLE_LILY_PAD_ITEM = ITEMS.register("triple_lily_pad_item",
            () -> new PlaceOnWaterBlockItem(TBlocks.TRIPLE_LILY_PAD.get(),new Item.Properties()));
}

