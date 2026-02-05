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
public class ToolStation extends ThingamajigsDecorativeBlock{
    private static final VoxelShape NS = Stream.of(
            Block.box(0, 0, 0, 2, 2, 2),
            Block.box(14, 0, 0, 16, 2, 2),
            Block.box(0, 0, 14, 2, 2, 16),
            Block.box(14, 0, 14, 16, 2, 16),
            Block.box(0, 2, 0, 16, 16, 16),
            Block.box(2, 4, -1, 14, 5, 0),
            Block.box(2, 7, -1, 14, 8, 0),
            Block.box(2, 10, -1, 14, 11, 0),
            Block.box(2, 13, -1, 14, 14, 0),
            Block.box(0, 16, 15, 16, 24, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SS = Stream.of(
            Block.box(14, 0, 13, 16, 2, 15),
            Block.box(0, 0, 13, 2, 2, 15),
            Block.box(14, 0, -1, 16, 2, 1),
            Block.box(0, 0, -1, 2, 2, 1),
            Block.box(0, 2, -1, 16, 16, 15),
            Block.box(2, 4, 15, 14, 5, 16),
            Block.box(2, 7, 15, 14, 8, 16),
            Block.box(2, 10, 15, 14, 11, 16),
            Block.box(2, 13, 15, 14, 14, 16),
            Block.box(0, 16, -1, 16, 24, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape ES = Stream.of(
            Block.box(13.5, 0, -0.5, 15.5, 2, 1.5),
            Block.box(13.5, 0, 13.5, 15.5, 2, 15.5),
            Block.box(-0.5, 0, -0.5, 1.5, 2, 1.5),
            Block.box(-0.5, 0, 13.5, 1.5, 2, 15.5),
            Block.box(-0.5, 2, -0.5, 15.5, 16, 15.5),
            Block.box(15.5, 4, 1.5, 16.5, 5, 13.5),
            Block.box(15.5, 7, 1.5, 16.5, 8, 13.5),
            Block.box(15.5, 10, 1.5, 16.5, 11, 13.5),
            Block.box(15.5, 13, 1.5, 16.5, 14, 13.5),
            Block.box(-0.5, 16, -0.5, 0.5, 24, 15.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WS = Stream.of(
            Block.box(0.5, 0, 13.5, 2.5, 2, 15.5),
            Block.box(0.5, 0, -0.5, 2.5, 2, 1.5),
            Block.box(14.5, 0, 13.5, 16.5, 2, 15.5),
            Block.box(14.5, 0, -0.5, 16.5, 2, 1.5),
            Block.box(0.5, 2, -0.5, 16.5, 16, 15.5),
            Block.box(-0.5, 4, 1.5, 0.5, 5, 13.5),
            Block.box(-0.5, 7, 1.5, 0.5, 8, 13.5),
            Block.box(-0.5, 10, 1.5, 0.5, 11, 13.5),
            Block.box(-0.5, 13, 1.5, 0.5, 14, 13.5),
            Block.box(15.5, 16, -0.5, 16.5, 24, 15.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ToolStation(Properties properties) {
        super(properties);
    }

    @Override
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
            default:
                return Shapes.block();
        }
    }
}
