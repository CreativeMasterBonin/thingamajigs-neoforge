package net.rk.thingamajigs.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.item.TItems;

public class TLanguage extends LanguageProvider {
    public TLanguage(PackOutput output) {
        super(output, Thingamajigs.MODID, "en_us");
    }
    // TODO: refactor files to depend on a generated language file from here instead of a manually created one

    @Override
    protected void addTranslations() {
        addItem(TItems.THINGAMAJIG,"Thingamajig");
        addItem(TItems.THINGAMAJIG_GLOB,"Thingamajig Glob");
        addItem(TItems.GLOB_SANDWICH,"Glob Sandwich");

        addItem(TItems.CIRCLE_SIGN_GLOB,"Circle Sign Glob");
        addItem(TItems.SQUARE_SIGN_GLOB,"Square Sign Glob");
        addItem(TItems.TRIANGLE_SIGN_GLOB,"Triangle Sign Glob");
        addItem(TItems.MISC_SIGN_GLOB,"Misc Sign Glob");
        addItem(TItems.SIGN_GLOB,"Sign Glob");
        addItem(TItems.DOOR_GLOB,"Door Glob");

        addItem(TItems.ILLUSIONER_SPAWN_EGG,"Illusioner Spawn Egg");
        addItem(TItems.GIANT_SPAWN_EGG,"Giant Spawn Egg");

        addItem(TItems.KEY,"Key");
        addItem(TItems.TREE_RESIN,"Tree Resin");

        //
        addItem(TItems.CLEAR_BULB_ITEM,"Clear Bulb");
        addItem(TItems.FULL_BULB_ITEM,"Full Bulb");
        addItem(TItems.CLEAR_LANTERN_ITEM,"Clear Lantern");
        addItem(TItems.FULL_LANTERN_ITEM,"Full Lantern");
        addItem(TItems.PAINT_BRUSH,"Paintbrush");
        addItem(TItems.RED_LANTERN_ITEM,"Red Lantern");
        addItem(TItems.PAPER_LANTERN_ITEM,"Paper Lantern");
    }

    @Override
    public String getName() {
        return "Thingamajigs Language DataGen EN_US";
    }
}
