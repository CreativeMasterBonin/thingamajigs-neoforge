package net.rk.thingamajigs.xtras;

import net.minecraft.ChatFormatting;

public class DecorationCategory{
    public enum Categories{
        GENERIC,
        BOOKSHELVES,
        INFRASTRUCTURE,
        FACTORY,
        TECHNOLOGY,
        TECHNO,
        SPORTS,
        FURNITURE,
        MINI_CITY,
        MISCELLANEOUS
    }

    public enum Subcategories{
        NONE,
        CAR_WASH,
        TRAFFIC_SIGNALS,
        RAILROAD,
        CHAIRS,
        COUCHES,
        TABLES,
        POLES,
        COMPUTERS,
        GAME_CONSOLES,
        CHRISTMAS,
        SAFETY,
        ARCADE,
        PHONE,
        HOME,
        APPLIANCE,
        SCIENCE,
        HEALTH,
        TOY,
        MUSIC
    }

    public static Categories[] getCategories(){
        return Categories.values();
    }

    public static Subcategories[] getSubcategories(){
        return Subcategories.values();
    }

    public static ChatFormatting getColorChatFormatting(Categories category) {
        switch(category){
            case GENERIC,INFRASTRUCTURE,FACTORY -> {
                return ChatFormatting.GRAY;
            }
            case SPORTS ->{
                return ChatFormatting.RED;
            }
            case FURNITURE -> {
                return ChatFormatting.AQUA;
            }
            case TECHNOLOGY,TECHNO,BOOKSHELVES -> {
                return ChatFormatting.GOLD;
            }
            case MISCELLANEOUS,MINI_CITY -> {
                return ChatFormatting.BLUE;
            }
        }
        return ChatFormatting.GRAY;
    }

    public static ChatFormatting getCFColorSub(Subcategories subcategories){
        switch(subcategories){
            case NONE -> {
                return ChatFormatting.GRAY;
            }
            case CAR_WASH,CHRISTMAS,ARCADE,HEALTH -> {
                return ChatFormatting.DARK_RED;
            }
            case TRAFFIC_SIGNALS,POLES,RAILROAD,PHONE -> {
                return ChatFormatting.DARK_GREEN;
            }
            case CHAIRS,COUCHES,APPLIANCE,HOME,TOY,MUSIC -> {
                return ChatFormatting.LIGHT_PURPLE;
            }
            case COMPUTERS,GAME_CONSOLES,SAFETY,SCIENCE ->{
                return ChatFormatting.YELLOW;
            }
        }
        return ChatFormatting.GRAY;
    }
}
