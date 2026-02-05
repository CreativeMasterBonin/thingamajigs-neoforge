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
public class StandardGravestone extends ThingamajigsDecorativeBlock{
    private static final VoxelShape NORTHSOUTH = Shapes.join(Block.box(0, 0, 5, 16, 2, 11), Block.box(1, 2, 6, 15, 18, 10), BooleanOp.OR);
    private static final VoxelShape EASTWEST = Shapes.join(Block.box(5, 0, 0, 11, 2, 16), Block.box(6, 2, 1, 10, 18, 15), BooleanOp.OR);

    public StandardGravestone(Properties properties) {
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
