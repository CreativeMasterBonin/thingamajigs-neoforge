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
public class HospitalPortable extends ThingamajigsDecorativeBlock{
    private static final VoxelShape NS = Stream.of(
            Block.box(6, 3, 6, 10, 23, 10),
            Block.box(4, 2, 4, 12, 3, 12),
            Block.box(2, 14, 4, 14, 24, 8),
            Block.box(7.1, 0, 2, 8.9, 2, 4),
            Block.box(12.1, 0, 7.1, 13.9, 2, 8.9),
            Block.box(2.1, 0, 7.1, 3.9, 2, 8.9),
            Block.box(7.1, 0, 12, 8.9, 2, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SS = Stream.of(
            Block.box(6, 3, 6, 10, 23, 10),
            Block.box(4, 2, 4, 12, 3, 12),
            Block.box(2, 14, 8, 14, 24, 12),
            Block.box(7.1, 0, 12, 8.9, 2, 14),
            Block.box(2.0999999999999996, 0, 7.1, 3.9000000000000004, 2, 8.9),
            Block.box(12.1, 0, 7.1, 13.9, 2, 8.9),
            Block.box(7.1, 0, 2, 8.9, 2, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape ES = Stream.of(
            Block.box(6, 3, 6, 10, 23, 10),
            Block.box(4, 2, 4, 12, 3, 12),
            Block.box(8, 14, 2, 12, 24, 14),
            Block.box(12, 0, 7.1, 14, 2, 8.9),
            Block.box(7.1, 0, 12.1, 8.9, 2, 13.9),
            Block.box(7.1, 0, 2.0999999999999996, 8.9, 2, 3.9000000000000004),
            Block.box(2, 0, 7.1, 4, 2, 8.9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WS = Stream.of(
            Block.box(6, 3, 6, 10, 23, 10),
            Block.box(4, 2, 4, 12, 3, 12),
            Block.box(4, 14, 2, 8, 24, 14),
            Block.box(2, 0, 7.1, 4, 2, 8.9),
            Block.box(7.1, 0, 2.0999999999999996, 8.9, 2, 3.9000000000000004),
            Block.box(7.1, 0, 12.1, 8.9, 2, 13.9),
            Block.box(12, 0, 7.1, 14, 2, 8.9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public HospitalPortable(Properties properties) {
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
