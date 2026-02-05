package net.rk.thingamajigs.block.custom;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class NetherChiseledBookshelf extends ChiseledBookShelfBlock{
    public NetherChiseledBookshelf(Properties p) {
        super(p.sound(SoundType.NETHERRACK));
        BlockState blockstate = this.stateDefinition.any().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH);
        for(BooleanProperty booleanproperty : SLOT_OCCUPIED_PROPERTIES) {
            blockstate = blockstate.setValue(booleanproperty,false);
        }
        this.registerDefaultState(blockstate);
    }
}
