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
public class GraphicsCard extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NS = Stream.of(
            Block.box(5, 0, 3, 11, 1, 9),
            Block.box(5, 0, 9, 11, 1, 15),
            Block.box(4, 0, 3, 5, 2, 15),
            Block.box(4, 0, 15.01, 12, 2, 15.01),
            Block.box(4, 0, 2.95, 12, 2, 2.95),
            Block.box(11, 0, 3, 12, 2, 15),
            Block.box(5, 1, 9, 11, 2, 15),
            Block.box(5, 0, 3, 11, 0, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SS = Stream.of(
            Block.box(5, 0, 7, 11, 1, 13),
            Block.box(5, 0, 1, 11, 1, 7),
            Block.box(11, 0, 1, 12, 2, 13),
            Block.box(4, 0, 0.9900000000000002, 12, 2, 0.9900000000000002),
            Block.box(4, 0, 13.05, 12, 2, 13.05),
            Block.box(4, 0, 1, 5, 2, 13),
            Block.box(5, 1, 1, 11, 2, 7),
            Block.box(5, 0, 7, 11, 0, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ES = Stream.of(
            Block.box(7, 0, 5, 13, 1, 11),
            Block.box(1, 0, 5, 7, 1, 11),
            Block.box(1, 0, 4, 13, 2, 5),
            Block.box(0.9900000000000002, 0, 4, 0.9900000000000002, 2, 12),
            Block.box(13.05, 0, 4, 13.05, 2, 12),
            Block.box(1, 0, 11, 13, 2, 12),
            Block.box(1, 1, 5, 7, 2, 11),
            Block.box(7, 0, 5, 13, 0, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WS = Stream.of(
            Block.box(3, 0, 5, 9, 1, 11),
            Block.box(9, 0, 5, 15, 1, 11),
            Block.box(3, 0, 11, 15, 2, 12),
            Block.box(15.01, 0, 4, 15.01, 2, 12),
            Block.box(2.9499999999999993, 0, 4, 2.9499999999999993, 2, 12),
            Block.box(3, 0, 4, 15, 2, 5),
            Block.box(9, 1, 5, 15, 2, 11),
            Block.box(3, 0, 5, 9, 0, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public GraphicsCard(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NS;
            case SOUTH: return SS;
            case EAST: return ES;
            case WEST: return WS;
            default: return Shapes.block();
        }
    }
}
