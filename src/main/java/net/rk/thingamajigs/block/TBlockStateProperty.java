package net.rk.thingamajigs.block;

import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class TBlockStateProperty {
    public static final BooleanProperty ON = BooleanProperty.create("on");
    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");
    public static final BooleanProperty WAXED = BooleanProperty.create("waxed");
}
