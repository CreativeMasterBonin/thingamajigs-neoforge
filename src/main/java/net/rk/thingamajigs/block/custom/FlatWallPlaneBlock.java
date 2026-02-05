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
public class FlatWallPlaneBlock extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_SHAPE = Optional.of(Block.box(0, 0, 15, 16, 16, 16)).get();
    public static final VoxelShape SOUTH_SHAPE = Optional.of(Block.box(0, 0, 0, 16, 16, 1)).get();
    public static final VoxelShape EAST_SHAPE = Optional.of(Block.box(0, 0, 0, 1, 16, 16)).get();
    public static final VoxelShape WEST_SHAPE = Optional.of(Block.box(15, 0, 0, 16, 16, 16)).get();

    public FlatWallPlaneBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_SHAPE;
            case SOUTH: return SOUTH_SHAPE;
            case EAST: return EAST_SHAPE;
            case WEST: return WEST_SHAPE;
            default: return Shapes.block();
        }
    }
}
