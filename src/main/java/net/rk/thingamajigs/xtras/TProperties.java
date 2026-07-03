package net.rk.thingamajigs.xtras;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class TProperties{
    public static final BooleanProperty ON = BooleanProperty.create("on");
    public static final BooleanProperty CONNECTED = BooleanProperty.create("connected");
    public static final IntegerProperty AMOUNT_FOUR = IntegerProperty.create("amount",1,4);

    public static final EnumProperty<ConnectedSide> CONNECTED_SIDE = EnumProperty.create("connected_side",ConnectedSide.class);
    public static final BooleanProperty REVERSED = BooleanProperty.create("reversed");

    public enum ConnectedSide implements StringRepresentable {
        UNCONNECTED("unconnected"),
        LEFT("left"),
        CENTER("center"),
        RIGHT("right");
        private final String name;

        ConnectedSide(String name){
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}
