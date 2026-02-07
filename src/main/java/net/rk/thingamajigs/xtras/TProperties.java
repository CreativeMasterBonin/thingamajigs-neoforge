package net.rk.thingamajigs.xtras;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class TProperties{
    public static final BooleanProperty ON = BooleanProperty.create("on");
    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");
    public static final IntegerProperty AMOUNT_FOUR = IntegerProperty.create("amount",1,4);
}
