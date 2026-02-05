package net.rk.thingamajigs.xtras;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class TConfig{
    //private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static void register(ModContainer cont) {
        regCommon(cont);
    }

    public static final ModConfigSpec.Builder CLIENT = new ModConfigSpec.Builder();
    public static final ModConfigSpec.Builder COMMON = new ModConfigSpec.Builder();
    public static final ModConfigSpec.Builder SERVER = new ModConfigSpec.Builder();

    // values for config
    public static ModConfigSpec.IntValue maxThingamajigsStackSize;

    public static ModConfigSpec.BooleanValue moneyExchangeEnabled;


    public static void regClient(ModContainer cont){
        cont.registerConfig(ModConfig.Type.CLIENT,CLIENT.build());
    }

    public static void regCommon(ModContainer cont){
        COMMON.comment("Options").push("General");
        commonValues();
        COMMON.pop();
        cont.registerConfig(ModConfig.Type.COMMON,COMMON.build());
    }

    private static void commonValues(){
        maxThingamajigsStackSize = COMMON
                .comment("Set the maximum size a stack of Thingamajigs can be.")
                .worldRestart()
                .defineInRange("maxThingamajigsStackSize",64,16,64);
        moneyExchangeEnabled = COMMON
                .comment("Enable the money exchanging mechanic. Basically makes the change machine useful or not.")
                .worldRestart()
                .define("moneyExchangeEnabled",false);
    }

    public static void regServer(ModContainer cont){
        cont.registerConfig(ModConfig.Type.SERVER,SERVER.build());
    }
}
