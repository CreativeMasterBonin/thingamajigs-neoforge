package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class PlacardGravestone extends ThingamajigsDecorativeBlock{
    private static final VoxelShape NORTHSOUTH = Shapes.join(Block.box(0, 0, 1, 16, 2, 15), Block.box(1, 2, 2, 15, 3, 14), BooleanOp.OR);
    private static final VoxelShape EASTWEST = Shapes.join(Block.box(1, 0, 0, 15, 2, 16), Block.box(2, 2, 1, 14, 3, 15), BooleanOp.OR);

    public PlacardGravestone(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch (direction){
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
