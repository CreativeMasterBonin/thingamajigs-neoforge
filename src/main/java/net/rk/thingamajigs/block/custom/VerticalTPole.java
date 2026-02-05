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
public class VerticalTPole extends Pole{
    public static final VoxelShape V_T_NORTH = Shapes.join(Block.box(9, 7, 7, 16, 9, 9),
            Block.box(7, 0, 7, 9, 16, 9),
            BooleanOp.OR);
    public static final VoxelShape V_T_SOUTH = Shapes.join(Block.box(0, 7, 7, 7, 9, 9),
            Block.box(7, 0, 7, 9, 16, 9),
            BooleanOp.OR);
    public static final VoxelShape V_T_EAST = Shapes.join(Block.box(7, 7, 9, 9, 9, 16),
            Block.box(7, 0, 7, 9, 16, 9),
            BooleanOp.OR);
    public static final VoxelShape V_T_WEST = Shapes.join(Block.box(7, 7, 0, 9, 9, 7),
            Block.box(7, 0, 7, 9, 16, 9),
            BooleanOp.OR);

    public VerticalTPole(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = state.getValue(FACING);
        switch (direction) {
            case NORTH:
                return V_T_NORTH;
            case SOUTH:
                return V_T_SOUTH;
            case EAST:
                return V_T_EAST;
            case WEST:
                return V_T_WEST;
        }
        return Shapes.block();
    }
}
