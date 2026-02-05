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
public class BluemanConsole extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NS = Shapes.join(Block.box(6.5, 1, 2, 9.5, 11, 14), Block.box(6, 0, 1, 10, 1, 15), BooleanOp.OR);
    public static final VoxelShape EW = Shapes.join(Block.box(2, 1, 6.5, 14, 11, 9.5), Block.box(1, 0, 6, 15, 1, 10), BooleanOp.OR);

    public BluemanConsole(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
            case SOUTH:
                return NS;
            case EAST:
            case WEST:
                return EW;
            default: return Shapes.block();
        }
    }
}
