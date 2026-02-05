package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class AxisPole extends Pole{
    public static final VoxelShape NS = Stream.of(
            net.minecraft.world.level.block.Block.box(7, 0, 7, 9, 7, 9),
            net.minecraft.world.level.block.Block.box(7, 7, 7, 16, 9, 9),
            net.minecraft.world.level.block.Block.box(7, 7, 9, 9, 9, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SS = Stream.of(
            net.minecraft.world.level.block.Block.box(7, 0, 7, 9, 7, 9),
            net.minecraft.world.level.block.Block.box(0, 7, 7, 9, 9, 9),
            net.minecraft.world.level.block.Block.box(7, 7, 0, 9, 9, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ES = Stream.of(
            net.minecraft.world.level.block.Block.box(7, 0, 7, 9, 7, 9),
            net.minecraft.world.level.block.Block.box(7, 7, 7, 9, 9, 16),
            net.minecraft.world.level.block.Block.box(0, 7, 7, 7, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WS = Stream.of(
            net.minecraft.world.level.block.Block.box(7, 0, 7, 9, 7, 9),
            net.minecraft.world.level.block.Block.box(7, 7, 0, 9, 9, 9),
            net.minecraft.world.level.block.Block.box(9, 7, 7, 16, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public AxisPole(Properties properties) {
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
            default: return Shapes.block();
        }
    }
}
