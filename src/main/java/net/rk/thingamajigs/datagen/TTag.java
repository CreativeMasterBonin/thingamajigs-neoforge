package net.rk.thingamajigs.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.rk.thingamajigs.Thingamajigs;

public class TTag {
    public static final TagKey<Fluid> PURIFIED_WATER_TAG = thingamajigsFluidTag("purified_water");
    public static final TagKey<Fluid> SLUDGE_TAG = thingamajigsFluidTag("sludge");

    public static final TagKey<Block> VERTICAL_REDSTONE_BLOCKS = thingamajigsBlockTag("vertical_redstone_blocks");
    public static final TagKey<Block> RR_CANTILEVERS = thingamajigsBlockTag("rr_cantilevers");
    public static final TagKey<Block> RAILROAD_CROSSING_BELLS = thingamajigsBlockTag("railroad_crossing_bells");
    //public static final TagKey<Block> POLES = thingamajigsBlockTag("poles");

    public static final TagKey<Block> CHAIRS = thingamajigsBlockTag("chairs");
    public static final TagKey<Block> TABLES = thingamajigsBlockTag("tables");
    public static final TagKey<Block> ELECTRICAL_OUTLETS = thingamajigsBlockTag("electrical_outlets");
    public static final TagKey<Block> SUPPORTS_BUSH_LIKE = thingamajigsBlockTag("supports_bush_like");

    public static final TagKey<Block> RUBBER_LOGS = thingamajigsBlockTag("rubber_logs");
    public static final TagKey<Item> RUBBER_LOGS_ITEM = thingamajigsItemTag("rubber_logs");
    public static final TagKey<Item> CHAIRS_ITEM = thingamajigsItemTag("chairs");
    public static final TagKey<Item> MYSTERIOUS_ITEMS = thingamajigsItemTag("mysterious_items");
    public static final TagKey<Item> PROTECTS_STATUES = thingamajigsItemTag("protects_statues");
    //public static final TagKey<Item> POLES_ITEM = thingamajigsItemTag("poles");

    public static final TagKey<Item> PAINT_BRUSHES = thingamajigsItemTag("paint_brushes");

    public static final TagKey<Item> TABLES_ITEM = thingamajigsItemTag("tables");
    //public static final TagKey<Item> DECORATION_COMPONENT_ITEMS = thingamajigsItemTag("decoration_component_items");
    // common tags
    public static final TagKey<Item> RUBBER_TAG = commonItemTag("rubber");
    public static final TagKey<Item> TREE_RESIN_TAG = commonItemTag("tree_resin");

    public static final TagKey<Biome> RIVER_PLANTS_SUPPORTED = thingamajigsBiomeTag("river_plants_supported");
    public static final TagKey<Biome> CAN_SPAWN_JUNGLE_PLANTS = thingamajigsBiomeTag("can_spawn_jungle_plants");

    public static TagKey<Biome> thingamajigsBiomeTag(String name){
        return TagKey.create(Registries.BIOME,ResourceLocation.fromNamespaceAndPath(Thingamajigs.MODID,name));
    }

    private static TagKey<Fluid> thingamajigsFluidTag(String name){
        return FluidTags.create(ResourceLocation.fromNamespaceAndPath("thingamajigs", name));
    }

    private static TagKey<Block> thingamajigsBlockTag(String name){
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("thingamajigs", name));
    }

    private static TagKey<Item> thingamajigsItemTag(String name){
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("thingamajigs", name));
    }

    // default tag registry

    private static TagKey<Block> mcBlockTag(String name){
        return BlockTags.create(ResourceLocation.withDefaultNamespace(name));
    }

    private static TagKey<Item> mcItemTag(String name){
        return ItemTags.create(ResourceLocation.withDefaultNamespace(name));
    }

    private static TagKey<Fluid> mcFluidTag(String name){
        return FluidTags.create(ResourceLocation.withDefaultNamespace(name));
    }

    // common tag registry

    private static TagKey<Fluid> commonFluidTag(String name){
        return FluidTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    private static TagKey<Block> commonBlockTag(String name){
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    private static TagKey<Item> commonItemTag(String name){
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    // Press F for old tags, superseded by common tags
}
