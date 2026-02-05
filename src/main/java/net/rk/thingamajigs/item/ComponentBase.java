package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.rk.thingamajigs.xtras.DecorationCategory;

import java.util.List;

public class ComponentBase extends Item{
    public String FLAVOR_TEXT = "Used to craft decorations."; // set text in constructor
    public boolean NO_LANG = true; // will not translate by default
    public Rarity RARE_TYPE = Rarity.UNCOMMON;
    public DecorationCategory.Categories CATEGORY =
            DecorationCategory.Categories.GENERIC; // used to define color and other chat formatting

    public DecorationCategory.Subcategories SUBCATEGORY =
            DecorationCategory.Subcategories.NONE; // if none, this component is a master component

    public boolean MASTER = true;

    public boolean SHINE = false;

    public ComponentBase(Properties p) {
        super(p);
        if(SUBCATEGORY != DecorationCategory.Subcategories.NONE){
            MASTER = false;
        }
    }

    public ComponentBase(Properties p, String str, boolean langOpt, DecorationCategory.Categories category, boolean shiny){
        super(p);
        FLAVOR_TEXT = str;
        NO_LANG = langOpt;
        CATEGORY = category;
        SHINE = shiny;
        if(SUBCATEGORY != DecorationCategory.Subcategories.NONE){
            MASTER = false;
        }
    }

    public ComponentBase(Properties p, String str, boolean langOpt, DecorationCategory.Subcategories subcategory, boolean shiny){
        super(p);
        FLAVOR_TEXT = str;
        NO_LANG = langOpt;
        SUBCATEGORY = subcategory;
        SHINE = shiny;
        if(SUBCATEGORY != DecorationCategory.Subcategories.NONE){
            MASTER = false;
        }
    }

    public ComponentBase(Properties p, String str, boolean langOpt, Rarity rareType, DecorationCategory.Categories category, boolean shiny){
        super(p.rarity(rareType));
        FLAVOR_TEXT = str;
        NO_LANG = langOpt;
        CATEGORY = category;
        SHINE = shiny;
        RARE_TYPE = rareType;
        if(SUBCATEGORY != DecorationCategory.Subcategories.NONE){
            MASTER = false;
        }
    }

    @Override
    public void appendHoverText(ItemStack is, TooltipContext tc, List<Component> list, TooltipFlag tf) {
        if(SUBCATEGORY == DecorationCategory.Subcategories.NONE){
            list.add(Component.translatable("thingamajigs.component_can_create.desc").withStyle(ChatFormatting.GREEN));
            if(NO_LANG){
                list.add(Component.literal(FLAVOR_TEXT).withStyle(DecorationCategory.getColorChatFormatting(CATEGORY)));
            }
            else{
                list.add(Component.translatable(FLAVOR_TEXT).withStyle(DecorationCategory.getColorChatFormatting(CATEGORY)));
            }
            return;
        }
        else{
            list.add(Component.translatable("thingamajigs.component_can_create.desc").withStyle(ChatFormatting.GREEN));
            if(NO_LANG){
                list.add(Component.literal(FLAVOR_TEXT).withStyle(DecorationCategory.getCFColorSub(SUBCATEGORY)));
            }
            else{
                list.add(Component.translatable(FLAVOR_TEXT).withStyle(DecorationCategory.getCFColorSub(SUBCATEGORY)));
            }
        }
    }

    public boolean isFoil(ItemStack is) {
        if(is.isEnchanted()){
            return true;
        }
        else{
            return SHINE;
        }
    }
}
