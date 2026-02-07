package net.rk.thingamajigs.xtras;

import net.minecraft.util.RandomSource;

public class TColors{
    public static int getWhite(){
        return 16777215;
    }
    public static int getLightGray(){
        return 10329495;
    }
    public static int getGray(){
        return 4673362;
    }
    public static int getBlack(){
        return 855571;
    }
    public static int getBrown(){
        return 6832163;
    }
    public static int getTeal() {
        return 6616766;
    }

    public static int getDefaultWater(){
        return 4159204;
    }

    public static int getRandom(){
        RandomSource rs = RandomSource.create();
        return rs.nextInt(16777215);
    }

    public static int getColorFromList(int color){
        int ci = getWhite();
        // 1red, 2orange, 3yellow, 4lime, 5green, 6cyan, 7light_blue, 8blue, 9purple, 10magenta, 11pink
        if(color == 1){
            ci = 11217700;
        }
        else if(color == 2){
            ci = 15494412;
        }
        else if(color == 3){
            ci = 16634933;
        }
        else if(color == 4){
            ci = 8834086;
        }
        else if(color == 5){
            ci = 6653465;
        }
        else if(color == 6){
            ci = 1481628;
        }
        else if(color == 7){
            ci = 5162471;
        }
        else if(color == 8){
            ci = 3093140;
        }
        else if(color == 9){
            ci = 7283875;
        }
        else if(color == 10){
            ci = 14049489;
        }
        else if(color == 11){
            ci = 16032444;
        }
        return ci;
    }
}
