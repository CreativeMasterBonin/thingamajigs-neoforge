package net.rk.thingamajigs.xtras;

import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.compress.archivers.sevenz.CLI;

public class TConfig{
    public static void register(ModContainer cont) {
        regClient(cont);
        regServer(cont);
    }

    public static final ModConfigSpec.Builder CLIENT = new ModConfigSpec.Builder();
    public static final ModConfigSpec.Builder COMMON = new ModConfigSpec.Builder();
    public static final ModConfigSpec.Builder SERVER = new ModConfigSpec.Builder();

    // values for config
    public static ModConfigSpec.IntValue maxThingamajigsStackSize;
    public static ModConfigSpec.BooleanValue moneyExchangeEnabled;
    public static ModConfigSpec.BooleanValue blueTabs;
    public static ModConfigSpec.EnumValue<Theme> tabTheme;

    public enum Theme implements StringRepresentable {
        GREEN("green"),
        BLUE("blue"),
        PURPLE("purple"),
        ORANGE("orange"),
        RED("red");

        public String name;

        Theme(String name){
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public static void regClient(ModContainer cont){
        CLIENT.comment("Options").push("General");
        clientValues();
        CLIENT.pop();
        cont.registerConfig(ModConfig.Type.CLIENT,CLIENT.build());
    }

    public static void regCommon(ModContainer cont){
        cont.registerConfig(ModConfig.Type.COMMON,COMMON.build());
    }

    private static void clientValues(){
        blueTabs = CLIENT
                .translation("config.client.thingamajigs.blue_tabs.desc")
                .define("blueCreativeTabs",false);
        tabTheme = CLIENT
                .translation("config.client.thingamajigs.tab_theme.desc")
                .defineEnum("tabTheme",Theme.BLUE);
    }

    private static void serverValues(){
        maxThingamajigsStackSize = SERVER
                .translation("config.server.thingamajigs.max_thingamajigs_stack_size.desc")
                .worldRestart()
                .defineInRange("maxThingamajigsStackSize",64,16,64);
        moneyExchangeEnabled = SERVER
                .translation("config.server.thingamajigs.money_exchange_enabled.desc")
                .worldRestart()
                .define("moneyExchangeEnabled",true);
    }

    public static void regServer(ModContainer cont){
        SERVER.comment("Options").push("General");
        serverValues();
        SERVER.pop();
        cont.registerConfig(ModConfig.Type.SERVER,SERVER.build());
    }
}
