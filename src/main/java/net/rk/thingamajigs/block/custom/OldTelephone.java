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
public class OldTelephone extends ThingamajigsDecorativeBlock{
    private static final VoxelShape NS = Stream.of(
            Block.box(12, 5, 9, 15, 6, 12),
            Block.box(3, 6, 9, 13, 7, 12),
            Block.box(1, 5, 9, 4, 6, 12),
            Block.box(11, 4, 7, 12, 5, 9),
            Block.box(3, 0, 4, 13, 1, 12),
            Block.box(4, 1, 4, 5, 2, 12),
            Block.box(11, 1, 4, 12, 2, 12),
            Block.box(5, 1, 8, 11, 6, 12),
            Block.box(4, 2, 5, 5, 3, 12),
            Block.box(4, 3, 6, 5, 4, 12),
            Block.box(11, 2, 5, 12, 3, 12),
            Block.box(11, 3, 6, 12, 4, 12),
            Block.box(4, 4, 7, 5, 5, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SS = Stream.of(
            Block.box(1, 5, 4, 4, 6, 7),
            Block.box(3, 6, 4, 13, 7, 7),
            Block.box(12, 5, 4, 15, 6, 7),
            Block.box(4, 4, 7, 5, 5, 9),
            Block.box(3, 0, 4, 13, 1, 12),
            Block.box(11, 1, 4, 12, 2, 12),
            Block.box(4, 1, 4, 5, 2, 12),
            Block.box(5, 1, 4, 11, 6, 8),
            Block.box(11, 2, 4, 12, 3, 11),
            Block.box(11, 3, 4, 12, 4, 10),
            Block.box(4, 2, 4, 5, 3, 11),
            Block.box(4, 3, 4, 5, 4, 10),
            Block.box(11, 4, 7, 12, 5, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape ES = Stream.of(
            Block.box(4, 5, 12, 7, 6, 15),
            Block.box(4, 6, 3, 7, 7, 13),
            Block.box(4, 5, 1, 7, 6, 4),
            Block.box(7, 4, 11, 9, 5, 12),
            Block.box(4, 0, 3, 12, 1, 13),
            Block.box(4, 1, 4, 12, 2, 5),
            Block.box(4, 1, 11, 12, 2, 12),
            Block.box(4, 1, 5, 8, 6, 11),
            Block.box(4, 2, 4, 11, 3, 5),
            Block.box(4, 3, 4, 10, 4, 5),
            Block.box(4, 2, 11, 11, 3, 12),
            Block.box(4, 3, 11, 10, 4, 12),
            Block.box(7, 4, 4, 9, 5, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WS = Stream.of(
            Block.box(9, 5, 1, 12, 6, 4),
            Block.box(9, 6, 3, 12, 7, 13),
            Block.box(9, 5, 12, 12, 6, 15),
            Block.box(7, 4, 4, 9, 5, 5),
            Block.box(4, 0, 3, 12, 1, 13),
            Block.box(4, 1, 11, 12, 2, 12),
            Block.box(4, 1, 4, 12, 2, 5),
            Block.box(8, 1, 5, 12, 6, 11),
            Block.box(5, 2, 11, 12, 3, 12),
            Block.box(6, 3, 11, 12, 4, 12),
            Block.box(5, 2, 4, 12, 3, 5),
            Block.box(6, 3, 4, 12, 4, 5),
            Block.box(7, 4, 11, 9, 5, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public OldTelephone(Properties properties) {
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
