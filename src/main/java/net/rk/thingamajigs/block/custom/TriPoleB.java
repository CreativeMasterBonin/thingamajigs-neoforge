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
public class TriPoleB extends Pole{
    public static final VoxelShape NS = Shapes.join(Block.box(7, 0, 7, 9, 7, 9), Block.box(7, 7, 7, 16, 9, 9), BooleanOp.OR);
    public static final VoxelShape SS = Shapes.join(Block.box(7, 0, 7, 9, 7, 9), Block.box(0, 7, 7, 9, 9, 9), BooleanOp.OR);
    public static final VoxelShape ES = Shapes.join(Block.box(7, 0, 7, 9, 7, 9), Block.box(7, 7, 7, 9, 9, 16), BooleanOp.OR);
    public static final VoxelShape WS = Shapes.join(Block.box(7, 0, 7, 9, 7, 9), Block.box(7, 7, 0, 9, 9, 9), BooleanOp.OR);

    public static final VoxelShape NS_NEW = Stream.of(NS, Pole.VERTICAL_ALL).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SS_NEW = Stream.of(SS, Pole.VERTICAL_ALL).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ES_NEW = Stream.of(ES, Pole.VERTICAL_ALL).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WS_NEW = Stream.of(WS, Pole.VERTICAL_ALL).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public TriPoleB(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NS_NEW;
            case SOUTH: return SS_NEW;
            case EAST: return ES_NEW;
            case WEST: return WS_NEW;
            default: return Shapes.block();
        }
    }
}
