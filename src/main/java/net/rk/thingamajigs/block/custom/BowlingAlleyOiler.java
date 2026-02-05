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
public class BowlingAlleyOiler extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH = Stream.of(
            Block.box(-8, 2, 0, 24, 10, 16),
            Block.box(-8, 0, 0, -7, 2, 2),
            Block.box(23, 0, 0, 24, 2, 2),
            Block.box(-8, 0, 14, -7, 2, 16),
            Block.box(23, 0, 14, 24, 2, 16),
            Block.box(-7, 0.5, 0.5, 23, 1.5, 1.5),
            Block.box(-7, 0.5, 14.5, 23, 1.5, 15.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST = Stream.of(
            Block.box(0, 2, -8, 16, 10, 24),
            Block.box(14, 0, -8, 16, 2, -7),
            Block.box(14, 0, 23, 16, 2, 24),
            Block.box(0, 0, -8, 2, 2, -7),
            Block.box(0, 0, 23, 2, 2, 24),
            Block.box(14.5, 0.5, -7, 15.5, 1.5, 23),
            Block.box(0.5, 0.5, -7, 1.5, 1.5, 23)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BowlingAlleyOiler(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
            case SOUTH:
                return NORTHSOUTH;
            case EAST:
            case WEST:
                return EASTWEST;
            default:
                return Shapes.block();
        }
    }
}
