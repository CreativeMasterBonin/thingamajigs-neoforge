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
public class TPoleC extends Pole{
    public static final VoxelShape NS = Stream.of(
            Block.box(7, 7, 9, 9, 9, 16),
            Block.box(0, 7, 7, 7, 9, 9),
            Block.box(7, 7, 7, 16, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SS = Stream.of(
            Block.box(7, 7, 0, 9, 9, 7),
            Block.box(9, 7, 7, 16, 9, 9),
            Block.box(0, 7, 7, 9, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ES = Stream.of(
            Block.box(0, 7, 7, 7, 9, 9),
            Block.box(7, 7, 0, 9, 9, 7),
            Block.box(7, 7, 7, 9, 9, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WS = Stream.of(
            Block.box(9, 7, 7, 16, 9, 9),
            Block.box(7, 7, 9, 9, 9, 16),
            Block.box(7, 7, 0, 9, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NS_NEW = Stream.of(NS, Pole.SMALL_BOTTOM_VERTICAL).reduce((v1,v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SS_NEW = Stream.of(SS, Pole.SMALL_BOTTOM_VERTICAL).reduce((v1,v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ES_NEW = Stream.of(ES, Pole.SMALL_BOTTOM_VERTICAL).reduce((v1,v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WS_NEW = Stream.of(WS, Pole.SMALL_BOTTOM_VERTICAL).reduce((v1,v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public TPoleC(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return SS_NEW;
            case SOUTH: return NS_NEW;
            case EAST: return WS_NEW;
            case WEST: return ES_NEW;
            default: return Shapes.block();
        }
    }
}
