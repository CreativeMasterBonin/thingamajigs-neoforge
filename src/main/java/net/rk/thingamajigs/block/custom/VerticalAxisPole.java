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
public class VerticalAxisPole extends AxisPole{
    public static VoxelShape NEW_NS = Stream.of(NS,VERTICAL_ALL).reduce((v1, v2) -> Shapes.join(v1,v2, BooleanOp.OR)).get();
    public static VoxelShape NEW_SS = Stream.of(SS,VERTICAL_ALL).reduce((v1,v2) -> Shapes.join(v1,v2,BooleanOp.OR)).get();
    public static VoxelShape NEW_ES = Stream.of(ES,VERTICAL_ALL).reduce((v1,v2) -> Shapes.join(v1,v2,BooleanOp.OR)).get();
    public static VoxelShape NEW_WS = Stream.of(WS,VERTICAL_ALL).reduce((v1,v2) -> Shapes.join(v1,v2,BooleanOp.OR)).get();
    public VerticalAxisPole(Properties properties) {
        super(properties.strength(1f));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return NEW_NS;
            case SOUTH:
                return NEW_SS;
            case EAST:
                return NEW_ES;
            case WEST:
                return NEW_WS;
            default: return Shapes.block();
        }
    }
}
