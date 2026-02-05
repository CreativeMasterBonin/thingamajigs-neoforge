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

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class PortaPotty extends ToggledStateBlock{
    public static final VoxelShape NS_CLOSED = Stream.of(
            Block.box(0, 28, 0, 16, 29, 16),
            Block.box(1, 29, 0, 15, 30, 16),
            Block.box(2, 30, 0, 14, 31, 16),
            Block.box(3, 31, 0, 13, 32, 16),
            Block.box(0, 0, 0, 16, 28, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EW_CLOSED = Stream.of(
            Block.box(0, 28, 0, 16, 29, 16),
            Block.box(0, 29, 1, 16, 30, 15),
            Block.box(0, 30, 2, 16, 31, 14),
            Block.box(0, 31, 3, 16, 32, 13),
            Block.box(0, 0, 0, 16, 28, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    // Open Collision Shapes
    public static final VoxelShape N_OPEN = Stream.of(
            Block.box(0, 28, 0, 16, 29, 16),
            Block.box(1, 29, 0, 15, 30, 16),
            Block.box(2, 30, 0, 14, 31, 16),
            Block.box(3, 31, 0, 13, 32, 16),
            Block.box(1, 1, 15, 15, 28, 16),
            Block.box(0, 0, 0, 1, 28, 16),
            Block.box(15, 0, 0, 16, 28, 16),
            Block.box(1, 0, 0, 15, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape S_OPEN = Stream.of(
            Block.box(0, 28, 0, 16, 29, 16),
            Block.box(1, 29, 0, 15, 30, 16),
            Block.box(2, 30, 0, 14, 31, 16),
            Block.box(3, 31, 0, 13, 32, 16),
            Block.box(1, 1, 0, 15, 28, 1),
            Block.box(15, 0, 0, 16, 28, 16),
            Block.box(0, 0, 0, 1, 28, 16),
            Block.box(1, 0, 0, 15, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape E_OPEN = Stream.of(
            Block.box(0, 28, 0, 16, 29, 16),
            Block.box(0, 29, 1, 16, 30, 15),
            Block.box(0, 30, 2, 16, 31, 14),
            Block.box(0, 31, 3, 16, 32, 13),
            Block.box(0, 1, 1, 1, 28, 15),
            Block.box(0, 0, 0, 16, 28, 1),
            Block.box(0, 0, 15, 16, 28, 16),
            Block.box(0, 0, 1, 16, 1, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape W_OPEN = Stream.of(
            Block.box(0, 28, 0, 16, 29, 16),
            Block.box(0, 29, 1, 16, 30, 15),
            Block.box(0, 30, 2, 16, 31, 14),
            Block.box(0, 31, 3, 16, 32, 13),
            Block.box(15, 1, 1, 16, 28, 15),
            Block.box(0, 0, 15, 16, 28, 16),
            Block.box(0, 0, 0, 16, 28, 1),
            Block.box(0, 0, 1, 16, 1, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public PortaPotty(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        Boolean enabled = state.getValue(TOGGLED);
        if(enabled){
            switch(direction){
                case NORTH: return N_OPEN;
                case SOUTH: return S_OPEN;
                case EAST: return E_OPEN;
                case WEST: return W_OPEN;
            }
        }
        else{
            switch(direction){
                case NORTH:
                case SOUTH:
                    return NS_CLOSED;
                case EAST:
                case WEST:
                    return EW_CLOSED;
            }
        }
        return Shapes.block();
    }
}
