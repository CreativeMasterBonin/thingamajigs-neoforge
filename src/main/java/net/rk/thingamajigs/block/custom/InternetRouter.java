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

public class InternetRouter extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH = Optional.of(Block.box(5, 0, 2, 11, 8, 14)).get();
    public static final VoxelShape EASTWEST = Optional.of(Block.box(2, 0, 5, 14, 8, 11)).get();

    public InternetRouter(Properties properties) {
        super(properties.sound(SoundType.METAL).noOcclusion().strength(1F,2F));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
            case SOUTH:
                return NORTHSOUTH;
            case EAST:
            case WEST:
                return EASTWEST;
            default: return Shapes.block();
        }
    }
}
