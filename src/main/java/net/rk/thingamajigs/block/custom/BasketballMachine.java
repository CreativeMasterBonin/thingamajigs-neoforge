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
public class BasketballMachine extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_NS = Stream.of(
            Block.box(14, 0, 28, 16, 3, 31),
            Block.box(0, 0, 0, 2, 3, 3),
            Block.box(14, 0, 0, 16, 3, 3),
            Block.box(1, 1, 1, 15, 2, 2),
            Block.box(7.5, 1, 1.5, 8.5, 2, 29.5),
            Block.box(1, 1, 29, 15, 2, 30),
            Block.box(0, 0, 28, 2, 3, 31),
            Block.box(0, 1, -1, 16, 10, 0),
            Block.box(0, 1, 31, 16, 31, 32),
            Block.box(0, 12, 29, 16, 28, 31),
            Block.box(0, 5, 0, 16, 6, 31),
            Block.box(0, 6, 0, 1, 10, 30),
            Block.box(15, 6, 0, 16, 10, 30)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SS = Stream.of(
            Block.box(0, 0, -15, 2, 3, -12),
            Block.box(14, 0, 13, 16, 3, 16),
            Block.box(0, 0, 13, 2, 3, 16),
            Block.box(1, 1, 14, 15, 2, 15),
            Block.box(7.5, 1, -13.5, 8.5, 2, 14.5),
            Block.box(1, 1, -14, 15, 2, -13),
            Block.box(14, 0, -15, 16, 3, -12),
            Block.box(0, 1, 16, 16, 10, 17),
            Block.box(0, 1, -16, 16, 31, -15),
            Block.box(0, 12, -15, 16, 28, -13),
            Block.box(0, 5, -15, 16, 6, 16),
            Block.box(15, 6, -14, 16, 10, 16),
            Block.box(0, 6, -14, 1, 10, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_ES = Stream.of(
            Block.box(-15, 0, 14, -12, 3, 16),
            Block.box(13, 0, 0, 16, 3, 2),
            Block.box(13, 0, 14, 16, 3, 16),
            Block.box(14, 1, 1, 15, 2, 15),
            Block.box(-13.5, 1, 7.5, 14.5, 2, 8.5),
            Block.box(-14, 1, 1, -13, 2, 15),
            Block.box(-15, 0, 0, -12, 3, 2),
            Block.box(16, 1, 0, 17, 10, 16),
            Block.box(-16, 1, 0, -15, 31, 16),
            Block.box(-15, 12, 0, -13, 28, 16),
            Block.box(-15, 5, 0, 16, 6, 16),
            Block.box(-14, 6, 0, 16, 10, 1),
            Block.box(-14, 6, 15, 16, 10, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_WS = Stream.of(
            Block.box(28, 0, 0, 31, 3, 2),
            Block.box(0, 0, 14, 3, 3, 16),
            Block.box(0, 0, 0, 3, 3, 2),
            Block.box(1, 1, 1, 2, 2, 15),
            Block.box(1.5, 1, 7.5, 29.5, 2, 8.5),
            Block.box(29, 1, 1, 30, 2, 15),
            Block.box(28, 0, 14, 31, 3, 16),
            Block.box(-1, 1, 0, 0, 10, 16),
            Block.box(31, 1, 0, 32, 31, 16),
            Block.box(29, 12, 0, 31, 28, 16),
            Block.box(0, 5, 0, 31, 6, 16),
            Block.box(0, 6, 15, 30, 10, 16),
            Block.box(0, 6, 0, 30, 10, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BasketballMachine(Properties properties) {
        super(properties.strength(1F,3.2F).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_NS;
            case SOUTH: return SOUTH_SS;
            case EAST: return EAST_ES;
            case WEST: return WEST_WS;
            default: return Shapes.block();
        }
    }
}
