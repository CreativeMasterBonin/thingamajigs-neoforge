package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

@SuppressWarnings("deprecated")
public class VhsPlayer extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NS_S = Optional.of(Block.box(1, 0, 2, 15, 2, 14)).get();
    public static final VoxelShape EW_S = Optional.of(Block.box(2, 0, 1, 14, 2, 15)).get();

    public VhsPlayer(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
            case SOUTH:
                return NS_S;
            case EAST:
            case WEST:
                return EW_S;
            default: return Shapes.block();
        }
    }
}
