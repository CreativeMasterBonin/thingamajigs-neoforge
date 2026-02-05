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

public class Satellite extends ThingamajigsDecorativeBlock{
    private static final VoxelShape NS = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 17, 9),
            Block.box(0, 10, 1, 16, 11, 2),
            Block.box(0, 11, 2, 16, 12, 3),
            Block.box(0, 12, 3, 16, 13, 4),
            Block.box(0, 15, 6, 16, 16, 7),
            Block.box(0, 14, 5, 16, 15, 6),
            Block.box(0, 13, 4, 16, 14, 5),
            Block.box(0, 18, 9, 16, 19, 10),
            Block.box(0, 17, 8, 16, 18, 9),
            Block.box(0, 16, 7, 16, 17, 8),
            Block.box(0, 20, 11, 16, 21, 12),
            Block.box(0, 19, 10, 16, 20, 11),
            Block.box(7, 13, 2, 9, 15, 4),
            Block.box(7, 15, 0, 9, 17, 2),
            Block.box(7, 17, -2, 9, 19, 0),
            Block.box(7, 19, -3, 9, 22, -1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SS = Stream.of(
            Block.box(7, 2, 7, 9, 17, 9),
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(0, 10, 14, 16, 11, 15),
            Block.box(0, 11, 13, 16, 12, 14),
            Block.box(0, 12, 12, 16, 13, 13),
            Block.box(0, 14, 10, 16, 15, 11),
            Block.box(0, 13, 11, 16, 14, 12),
            Block.box(0, 15, 9, 16, 16, 10),
            Block.box(0, 18, 6, 16, 19, 7),
            Block.box(0, 17, 7, 16, 18, 8),
            Block.box(0, 16, 8, 16, 17, 9),
            Block.box(0, 20, 4, 16, 21, 5),
            Block.box(0, 19, 5, 16, 20, 6),
            Block.box(7, 19, 17, 9, 22, 19)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape ES = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 17, 9),
            Block.box(14, 10, 0, 15, 11, 16),
            Block.box(13, 11, 0, 14, 12, 16),
            Block.box(12, 12, 0, 13, 13, 16),
            Block.box(9, 15, 0, 10, 16, 16),
            Block.box(10, 14, 0, 11, 15, 16),
            Block.box(11, 13, 0, 12, 14, 16),
            Block.box(6, 18, 0, 7, 19, 16),
            Block.box(7, 17, 0, 8, 18, 16),
            Block.box(8, 16, 0, 9, 17, 16),
            Block.box(4, 20, 0, 5, 21, 16),
            Block.box(5, 19, 0, 6, 20, 16),
            Block.box(12, 13, 7, 14, 15, 9),
            Block.box(14, 15, 7, 16, 17, 9),
            Block.box(16, 17, 7, 18, 19, 9),
            Block.box(17, 19, 7, 19, 22, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WS = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(6, 1, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 17, 9),
            Block.box(1, 10, 0, 2, 11, 16),
            Block.box(2, 11, 0, 3, 12, 16),
            Block.box(3, 12, 0, 4, 13, 16),
            Block.box(6, 15, 0, 7, 16, 16),
            Block.box(5, 14, 0, 6, 15, 16),
            Block.box(4, 13, 0, 5, 14, 16),
            Block.box(9, 18, 0, 10, 19, 16),
            Block.box(8, 17, 0, 9, 18, 16),
            Block.box(7, 16, 0, 8, 17, 16),
            Block.box(11, 20, 0, 12, 21, 16),
            Block.box(10, 19, 0, 11, 20, 16),
            Block.box(2, 13, 7, 4, 15, 9),
            Block.box(0, 15, 7, 2, 17, 9),
            Block.box(-2, 17, 7, 0, 19, 9),
            Block.box(-3, 19, 7, -1, 22, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Satellite(Properties properties) {
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
