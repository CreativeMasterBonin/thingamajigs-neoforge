package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.item.TItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TItemTag extends ItemTagsProvider{
    public TItemTag(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Thingamajigs.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider prov) {
        this.tag(TTag.CHAIRS_ITEM)
                .add(TBlocks.STONE_CHAIR.get().asItem())
                .add(TBlocks.GOLD_CHAIR.get().asItem())
                .add(TBlocks.QUARTZ_CHAIR.get().asItem())
                .add(TBlocks.NETHER_BRICK_CHAIR.get().asItem())
                .add(TBlocks.PRISMARINE_CHAIR.get().asItem())
                .add(TBlocks.PURPUR_CHAIR.get().asItem())
                .add(TBlocks.SCULK_CHAIR.get().asItem())
                .add(TBlocks.POOP_CHAIR.get().asItem())
                .add(TBlocks.DIAMOND_CHAIR.get().asItem())
                .add(TBlocks.IRON_CHAIR.get().asItem())
                .add(TBlocks.COPPER_CHAIR.get().asItem())
                .add(TBlocks.EXPOSED_COPPER_CHAIR.get().asItem())
                .add(TBlocks.WEATHERED_COPPER_CHAIR.get().asItem())
                .add(TBlocks.OXIDIZED_COPPER_CHAIR.get().asItem())
                .add(TBlocks.WAXED_COPPER_CHAIR.get().asItem())
                .add(TBlocks.WAXED_EXPOSED_COPPER_CHAIR.get().asItem())
                .add(TBlocks.WAXED_WEATHERED_COPPER_CHAIR.get().asItem())
                .add(TBlocks.WAXED_OXIDIZED_COPPER_CHAIR.get().asItem())
                .replace(false)
        ;

        this.tag(TTag.TREE_RESIN_TAG)
                .add(TItems.TREE_RESIN.get().asItem())
                .replace(false)
        ;

        this.tag(TTag.RUBBER_TAG)
                .add(TItems.RUBBER.get().asItem())
                .replace(false)
        ;

        this.tag(TTag.RUBBER_LOGS_ITEM)
                .add(TBlocks.RUBBER_LOG.get().asItem())
                .add(TBlocks.RUBBER_WOOD.get().asItem())
                .add(TBlocks.STRIPPED_RUBBER_LOG.get().asItem())
                .add(TBlocks.STRIPPED_RUBBER_WOOD.get().asItem())
                .replace(false)
        ;

        // vanilla tags
        this.tag(ItemTags.LOGS_THAT_BURN)
                .addTag(TTag.RUBBER_LOGS_ITEM)
                .replace(false)
        ;

        this.tag(ItemTags.PLANKS)
                .add(TBlocks.RUBBER_PLANKS.get().asItem())
                .replace(false)
        ;

        this.tag(TTag.PAINT_BRUSHES)
                .add(TItems.PAINT_BRUSH.get().asItem())
                .replace(false)
        ;

        this.tag(TTag.TABLES_ITEM)
                .add(TBlocks.STONE_TABLE.get().asItem())
                .add(TBlocks.SCULK_TABLE.get().asItem())
                .add(TBlocks.QUARTZ_TABLE.get().asItem())
                .add(TBlocks.PURPUR_TABLE.get().asItem())
                .add(TBlocks.PRISMARINE_TABLE.get().asItem())
                .add(TBlocks.NETHER_BRICK_TABLE.get().asItem())
                .add(TBlocks.GOLD_TABLE.get().asItem())
                .add(TBlocks.IRON_TABLE.get().asItem())
                .add(TBlocks.DIAMOND_TABLE.get().asItem())
                .add(TBlocks.COPPER_TABLE.get().asItem())
                .add(TBlocks.EXPOSED_COPPER_TABLE.get().asItem())
                .add(TBlocks.WEATHERED_COPPER_TABLE.get().asItem())
                .add(TBlocks.OXIDIZED_COPPER_TABLE.get().asItem())
                .add(TBlocks.WAXED_COPPER_TABLE.get().asItem())
                .add(TBlocks.WAXED_EXPOSED_COPPER_TABLE.get().asItem())
                .add(TBlocks.WAXED_WEATHERED_COPPER_TABLE.get().asItem())
                .add(TBlocks.WAXED_OXIDIZED_COPPER_TABLE.get().asItem())
                .replace(false)
        ;
    }
}
