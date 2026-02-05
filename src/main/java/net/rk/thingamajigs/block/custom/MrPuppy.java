package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class MrPuppy extends ThingamajigsDecorativeBlock{
    public static final VoxelShape N_S = Stream.of(
            Block.box(5, 1, 7, 11, 8, 10),
            Block.box(5.5, 9, 7, 10.5, 12, 9),
            Block.box(7, 7, 7.5, 9, 9, 9.5),
            Block.box(7.2, 9.3, 6, 8.8, 10.3, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape S_S = Stream.of(
            Block.box(5, 1, 6, 11, 8, 9),
            Block.box(5.5, 9, 7, 10.5, 12, 9),
            Block.box(7, 7, 6.5, 9, 9, 8.5),
            Block.box(7.199999999999999, 9.3, 9, 8.8, 10.3, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape E_S = Stream.of(
            Block.box(6, 1, 5, 9, 8, 11),
            Block.box(7, 9, 5.5, 9, 12, 10.5),
            Block.box(6.5, 7, 7, 8.5, 9, 9),
            Block.box(9, 9.3, 7.2, 10, 10.3, 8.8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape W_S = Stream.of(
            Block.box(7, 1, 5, 10, 8, 11),
            Block.box(7, 9, 5.5, 9, 12, 10.5),
            Block.box(7.5, 7, 7, 9.5, 9, 9),
            Block.box(6, 9.3, 7.199999999999999, 7, 10.3, 8.8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public MrPuppy(Properties properties) {
        super(properties.strength(0.5F,1.5F).sound(SoundType.WOOL));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return N_S;
            case SOUTH: return S_S;
            case EAST: return E_S;
            case WEST: return W_S;
            default: return Shapes.block();
        }
    }
}
