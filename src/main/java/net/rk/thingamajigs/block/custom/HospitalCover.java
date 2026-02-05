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
public class HospitalCover extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NS = Stream.of(
            Block.box(5, 0, 0, 11, 32, 2),
            Block.box(2, 0, 2, 5, 32, 3),
            Block.box(11, 0, 2, 14, 32, 3),
            Block.box(14, 0, 3, 16, 32, 4),
            Block.box(0, 0, 3, 2, 32, 4),
            Block.box(-2, 0, 4, 0, 32, 5),
            Block.box(16, 0, 4, 18, 32, 5),
            Block.box(18, 0, 5, 20, 32, 6),
            Block.box(20, 0, 6, 22, 32, 7),
            Block.box(-4, 0, 5, -2, 32, 6),
            Block.box(-6, 0, 6, -4, 32, 7),
            Block.box(-8, 0, 7, -6, 32, 8),
            Block.box(22, 0, 7, 24, 32, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SS = Stream.of(
            Block.box(5, 0, 14, 11, 32, 16),
            Block.box(11, 0, 13, 14, 32, 14),
            Block.box(2, 0, 13, 5, 32, 14),
            Block.box(0, 0, 12, 2, 32, 13),
            Block.box(14, 0, 12, 16, 32, 13),
            Block.box(16, 0, 11, 18, 32, 12),
            Block.box(-2, 0, 11, 0, 32, 12),
            Block.box(-4, 0, 10, -2, 32, 11),
            Block.box(-6, 0, 9, -4, 32, 10),
            Block.box(18, 0, 10, 20, 32, 11),
            Block.box(20, 0, 9, 22, 32, 10),
            Block.box(22, 0, 8, 24, 32, 9),
            Block.box(-8, 0, 8, -6, 32, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ES = Stream.of(
            Block.box(14, 0, 5, 16, 32, 11),
            Block.box(13, 0, 2, 14, 32, 5),
            Block.box(13, 0, 11, 14, 32, 14),
            Block.box(12, 0, 14, 13, 32, 16),
            Block.box(12, 0, 0, 13, 32, 2),
            Block.box(11, 0, -2, 12, 32, 0),
            Block.box(11, 0, 16, 12, 32, 18),
            Block.box(10, 0, 18, 11, 32, 20),
            Block.box(9, 0, 20, 10, 32, 22),
            Block.box(10, 0, -4, 11, 32, -2),
            Block.box(9, 0, -6, 10, 32, -4),
            Block.box(8, 0, -8, 9, 32, -6),
            Block.box(8, 0, 22, 9, 32, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WS = Stream.of(
            Block.box(0, 0, 5, 2, 32, 11),
            Block.box(2, 0, 11, 3, 32, 14),
            Block.box(2, 0, 2, 3, 32, 5),
            Block.box(3, 0, 0, 4, 32, 2),
            Block.box(3, 0, 14, 4, 32, 16),
            Block.box(4, 0, 16, 5, 32, 18),
            Block.box(4, 0, -2, 5, 32, 0),
            Block.box(5, 0, -4, 6, 32, -2),
            Block.box(6, 0, -6, 7, 32, -4),
            Block.box(5, 0, 18, 6, 32, 20),
            Block.box(6, 0, 20, 7, 32, 22),
            Block.box(7, 0, 22, 8, 32, 24),
            Block.box(7, 0, -8, 8, 32, -6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public HospitalCover(Properties properties) {
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
