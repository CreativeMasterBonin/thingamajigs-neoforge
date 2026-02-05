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
public class LOnlyPole extends Pole{
    public static final VoxelShape NS = Shapes.join(Block.box(7, 7, 9, 9, 9, 16), Block.box(7, 7, 7, 16, 9, 9), BooleanOp.OR);
    public static final VoxelShape SS = Shapes.join(Block.box(7, 7, 0, 9, 9, 7), Block.box(0, 7, 7, 9, 9, 9), BooleanOp.OR);
    public static final VoxelShape ES = Shapes.join(Block.box(0, 7, 7, 7, 9, 9), Block.box(7, 7, 7, 9, 9, 16), BooleanOp.OR);
    public static final VoxelShape WS = Shapes.join(Block.box(9, 7, 7, 16, 9, 9), Block.box(7, 7, 0, 9, 9, 9), BooleanOp.OR);


    public LOnlyPole(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return NS;
            case SOUTH:
                return SS;
            case EAST:
                return ES;
            case WEST:
                return WS;
            default: return Shapes.block();
        }
    }
}
