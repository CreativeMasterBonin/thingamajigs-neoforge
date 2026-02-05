package net.rk.thingamajigs.block.custom;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.SoundType;

public class FireEscapeLadder extends LadderBlock{
    public FireEscapeLadder(Properties p) {
        super(p.strength(1.0F,20F).sound(SoundType.LANTERN));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }
}
