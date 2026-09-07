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
import net.rk.thingamajigs.block.TBlocks;

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

    public static final VoxelShape N_PINBALL_MACHINE = Stream.of(
            Block.box(0, 4, 30, 16, 32, 32),
            Block.box(0, 0, 0, 2, 4, 2),
            Block.box(0, 0, 30, 2, 4, 32),
            Block.box(14, 0, 0, 16, 4, 2),
            Block.box(14, 0, 30, 16, 4, 32),
            Block.box(0, 0, 2, 1, 15, 30),
            Block.box(0, 3, 1, 16, 7, 30),
            Block.box(15, 0, 2, 16, 15, 30),
            Block.box(2, 0, 30.9, 14, 8, 31.9),
            Block.box(1, 5, 2, 15, 7, 30)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape E_PINBALL_MACHINE = Stream.of(
            Block.box(-16, 4, 0, -14, 32, 16),
            Block.box(14, 0, 0, 16, 4, 2),
            Block.box(-16, 0, 0, -14, 4, 2),
            Block.box(14, 0, 14, 16, 4, 16),
            Block.box(-16, 0, 14, -14, 4, 16),
            Block.box(-14, 0, 0, 14, 15, 1),
            Block.box(-14, 3, 0, 15, 7, 16),
            Block.box(-14, 0, 15, 14, 15, 16),
            Block.box(-15.899999999999999, 0, 2, -14.899999999999999, 8, 14),
            Block.box(-14, 5, 1, 14, 7, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape S_PINBALL_MACHINE = Stream.of(
            Block.box(0, 4, -16, 16, 32, -14),
            Block.box(14, 0, 14, 16, 4, 16),
            Block.box(14, 0, -16, 16, 4, -14),
            Block.box(0, 0, 14, 2, 4, 16),
            Block.box(0, 0, -16, 2, 4, -14),
            Block.box(15, 0, -14, 16, 15, 14),
            Block.box(0, 3, -14, 16, 7, 15),
            Block.box(0, 0, -14, 1, 15, 14),
            Block.box(2, 0, -15.899999999999999, 14, 8, -14.899999999999999),
            Block.box(1, 5, -14, 15, 7, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape W_PINBALL_MACHINE = Stream.of(
            Block.box(30, 4, 0, 32, 32, 16),
            Block.box(0, 0, 14, 2, 4, 16),
            Block.box(30, 0, 14, 32, 4, 16),
            Block.box(0, 0, 0, 2, 4, 2),
            Block.box(30, 0, 0, 32, 4, 2),
            Block.box(2, 0, 15, 30, 15, 16),
            Block.box(1, 3, 0, 30, 7, 16),
            Block.box(2, 0, 0, 30, 15, 1),
            Block.box(30.9, 0, 2, 31.9, 8, 14),
            Block.box(2, 5, 1, 30, 7, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BasketballMachine(Properties properties) {
        super(properties.strength(1F,3.2F).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = state.getValue(FACING);
        if(state.is(TBlocks.PINBALL_MACHINE.get())){
            switch(direction){
                case NORTH: return N_PINBALL_MACHINE;
                case SOUTH: return S_PINBALL_MACHINE;
                case EAST: return E_PINBALL_MACHINE;
                case WEST: return W_PINBALL_MACHINE;
                default: return Shapes.block();
            }
        }
        switch(direction){
            case NORTH: return NORTH_NS;
            case SOUTH: return SOUTH_SS;
            case EAST: return EAST_ES;
            case WEST: return WEST_WS;
            default: return Shapes.block();
        }
    }
}
