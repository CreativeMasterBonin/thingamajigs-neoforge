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
public class ThreeWayPole extends Pole{
    public static final VoxelShape NS = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(7, 7, 7, 16, 9, 9),
            Block.box(7, 7, 9, 9, 9, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SS = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(0, 7, 7, 9, 9, 9),
            Block.box(7, 7, 0, 9, 9, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ES = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(7, 7, 7, 9, 9, 16),
            Block.box(0, 7, 7, 7, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WS = Stream.of(
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(7, 7, 0, 9, 9, 9),
            Block.box(9, 7, 7, 16, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static VoxelShape NEW_NS = Stream.of(NS,VERTICAL_ALL,PLUS_NORTHSOUTH).reduce((v1, v2) -> Shapes.join(v1,v2, BooleanOp.OR)).get();
    public static VoxelShape NEW_SS = Stream.of(SS,VERTICAL_ALL,PLUS_NORTHSOUTH).reduce((v1,v2) -> Shapes.join(v1,v2,BooleanOp.OR)).get();
    public static VoxelShape NEW_ES = Stream.of(ES,VERTICAL_ALL,PLUS_EASTWEST).reduce((v1,v2) -> Shapes.join(v1,v2,BooleanOp.OR)).get();
    public static VoxelShape NEW_WS = Stream.of(WS,VERTICAL_ALL,PLUS_EASTWEST).reduce((v1,v2) -> Shapes.join(v1,v2,BooleanOp.OR)).get();

    public ThreeWayPole(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = bs.getValue(FACING);
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
