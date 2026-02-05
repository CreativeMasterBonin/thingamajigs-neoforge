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
public class CoffinBlock extends RotatingToggledBlock{
    public static final VoxelShape NORTHSOUTH = Stream.of(
            Block.box(0, 10, -8, 16, 11, 24),
            Block.box(0, 0, -8, 16, 1, 24),
            Block.box(0, 1, -8, 16, 10, -7),
            Block.box(0, 1, 23, 16, 10, 24),
            Block.box(15.5, 0.5, -8, 16.5, 10.5, 24),
            Block.box(-0.5, 0.5, -8, 0.5, 10.5, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST = Stream.of(
            Block.box(-8, 10, 0, 24, 11, 16),
            Block.box(-8, 0, 0, 24, 1, 16),
            Block.box(23, 1, 0, 24, 10, 16),
            Block.box(-8, 1, 0, -7, 10, 16),
            Block.box(-8, 0.5, 15.5, 24, 10.5, 16.5),
            Block.box(-8, 0.5, -0.5, 24, 10.5, 0.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTHSOUTH_OPEN = Stream.of(
            Block.box(0, 0, -8, 16, 1, 24),
            Block.box(0, 1, -8, 16, 10, -7),
            Block.box(0, 1, 23, 16, 10, 24),
            Block.box(15.5, 0.5, -8, 16.5, 10.5, 24),
            Block.box(-0.5, 0.5, -8, 0.5, 10.5, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST_OPEN = Stream.of(
            Block.box(-8, 0, 0, 24, 1, 16),
            Block.box(23, 1, 0, 24, 10, 16),
            Block.box(-8, 1, 0, -7, 10, 16),
            Block.box(-8, 0.5, 15.5, 24, 10.5, 16.5),
            Block.box(-8, 0.5, -0.5, 24, 10.5, 0.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CoffinBlock(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        Boolean open = pState.getValue(OPENED);

        if(direction == Direction.NORTH && !open){
            return NORTHSOUTH;
        }
        else if(direction == Direction.NORTH && open){
            return NORTHSOUTH_OPEN;
        }
        else if(direction == Direction.SOUTH && !open){
            return NORTHSOUTH;
        }
        else if(direction == Direction.SOUTH && open){
            return NORTHSOUTH_OPEN;
        }
        else if(direction == Direction.EAST && !open){
            return EASTWEST;
        }
        else if(direction == Direction.EAST && open){
            return EASTWEST_OPEN;
        }
        else if(direction == Direction.WEST && !open){
            return EASTWEST;
        }
        else if(direction == Direction.WEST && open){
            return EASTWEST_OPEN;
        }
        else{
            return Shapes.block();
        }
    }
}
