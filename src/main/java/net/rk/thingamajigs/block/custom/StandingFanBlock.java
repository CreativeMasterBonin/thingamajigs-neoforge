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
public class StandingFanBlock extends ToggledStateBlock{
    private static final VoxelShape NORTH = Stream.of(
            Block.box(6, 21, 4, 10, 25, 15),
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 1, 7, 9, 17, 9),
            Block.box(7, 17, 7, 9, 23, 9),
            Block.box(4, 15, 2, 12, 16, 4),
            Block.box(3, 17, 2, 13, 18, 4),
            Block.box(1, 19, 2, 15, 20, 4),
            Block.box(0, 21, 2, 16, 22, 4),
            Block.box(-1, 23, 2, 17, 24, 4),
            Block.box(0, 25, 2, 16, 26, 4),
            Block.box(1, 27, 2, 15, 28, 4),
            Block.box(3, 29, 2, 13, 30, 4),
            Block.box(4, 31, 2, 12, 32, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SOUTH = Stream.of(
            Block.box(6, 21, 1, 10, 25, 12),
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 1, 7, 9, 17, 9),
            Block.box(7, 17, 7, 9, 23, 9),
            Block.box(4, 15, 12, 12, 16, 14),
            Block.box(3, 17, 12, 13, 18, 14),
            Block.box(1, 19, 12, 15, 20, 14),
            Block.box(0, 21, 12, 16, 22, 14),
            Block.box(-1, 23, 12, 17, 24, 14),
            Block.box(0, 25, 12, 16, 26, 14),
            Block.box(1, 27, 12, 15, 28, 14),
            Block.box(3, 29, 12, 13, 30, 14),
            Block.box(4, 31, 12, 12, 32, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape EAST = Stream.of(
            Block.box(1, 21, 6, 12, 25, 10),
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 1, 7, 9, 17, 9),
            Block.box(7, 17, 7, 9, 23, 9),
            Block.box(12, 15, 4, 14, 16, 12),
            Block.box(12, 17, 3, 14, 18, 13),
            Block.box(12, 19, 1, 14, 20, 15),
            Block.box(12, 21, 0, 14, 22, 16),
            Block.box(12, 23, -1, 14, 24, 17),
            Block.box(12, 25, 0, 14, 26, 16),
            Block.box(12, 27, 1, 14, 28, 15),
            Block.box(12, 29, 3, 14, 30, 13),
            Block.box(12, 31, 4, 14, 32, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WEST = Stream.of(
            Block.box(4, 21, 6, 15, 25, 10),
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 1, 7, 9, 17, 9),
            Block.box(7, 17, 7, 9, 23, 9),
            Block.box(2, 15, 4, 4, 16, 12),
            Block.box(2, 17, 3, 4, 18, 13),
            Block.box(2, 19, 1, 4, 20, 15),
            Block.box(2, 21, 0, 4, 22, 16),
            Block.box(2, 23, -1, 4, 24, 17),
            Block.box(2, 25, 0, 4, 26, 16),
            Block.box(2, 27, 1, 4, 28, 15),
            Block.box(2, 29, 3, 4, 30, 13),
            Block.box(2, 31, 4, 4, 32, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public StandingFanBlock(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        Boolean open = pState.getValue(TOGGLED);

        if(direction == Direction.NORTH && !open){
            return NORTH;
        }
        else if(direction == Direction.NORTH && open){
            return NORTH;
        }
        else if(direction == Direction.SOUTH && !open){
            return SOUTH;
        }
        else if(direction == Direction.SOUTH && open){
            return SOUTH;
        }
        else if(direction == Direction.EAST && !open){
            return EAST;
        }
        else if(direction == Direction.EAST && open){
            return EAST;
        }
        else if(direction == Direction.WEST && !open){
            return WEST;
        }
        else if(direction == Direction.WEST && open){
            return WEST;
        }
        else{
            return Shapes.block();
        }
    }
}
