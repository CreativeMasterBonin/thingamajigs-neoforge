package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

@SuppressWarnings("deprecated")
public class GingerbreadHouse extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH_SHAPE = Optional.of(Block.box(4, 0, 0, 12, 16, 16)).get();
    public static final VoxelShape EASTWEST_SHAPE = Optional.of(Block.box(0, 0, 4, 16, 16, 12)).get();

    public GingerbreadHouse(Properties properties) {
        super(properties.sound(SoundType.WOOL).strength(0.75F,1F));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
            case SOUTH:
                return NORTHSOUTH_SHAPE;
            case EAST:
            case WEST:
                return EASTWEST_SHAPE;
            default: return Shapes.block();
        }
    }
}
