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
public class MultipurposeIVPump extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_IV = Stream.of(
            Block.box(5, 23, 7, 7, 28, 9),
            Block.box(9, 23, 7, 11, 28, 9),
            Block.box(1.5, 28, 7.5, 14, 29, 8.5),
            Block.box(6.5, 13, 8.5, 9, 14, 9.5),
            Block.box(5, 12, 6, 11, 17, 8),
            Block.box(7.5, 3, 7.5, 8.5, 28, 8.5),
            Block.box(4, 2, 4, 12, 3, 12),
            Block.box(7.1, 0, 2, 8.9, 2, 4),
            Block.box(12.1, 0, 7.1, 13.9, 2, 8.9),
            Block.box(2.1, 0, 7.1, 3.9, 2, 8.9),
            Block.box(7.1, 0, 12, 8.9, 2, 14),
            Block.box(5, 21, 6, 11, 22.32, 8),
            Block.box(7, 12, 5.99, 11, 17, 5.99),
            Block.box(5, 12, 5.99, 7, 17, 5.99),
            Block.box(6, 17, 5.99, 10, 21, 5.99),
            Block.box(5, 17, 6, 11, 21, 8),
            Block.box(3, 19, 6, 5, 20.5, 8),
            Block.box(3, 20.5, 6, 5, 21.5, 8),
            Block.box(3, 18, 6, 5, 19, 8),
            Block.box(3, 17, 6, 5, 18, 8),
            Block.box(11.75, 18, 5.99, 12.25, 18.75, 5.99),
            Block.box(11, 19, 6, 13, 20.5, 8),
            Block.box(11, 20.5, 6, 13, 21.5, 8),
            Block.box(11, 18, 6, 13, 19, 8),
            Block.box(11, 17, 6, 13, 18, 8),
            Block.box(3.75, 18, 5.99, 4.25, 18.75, 5.99)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_IV = Stream.of(
            Block.box(9, 23, 7, 11, 28, 9),
            Block.box(5, 23, 7, 7, 28, 9),
            Block.box(2, 28, 7.5, 14.5, 29, 8.5),
            Block.box(7, 13, 6.5, 9.5, 14, 7.5),
            Block.box(5, 12, 8, 11, 17, 10),
            Block.box(7.5, 3, 7.5, 8.5, 28, 8.5),
            Block.box(4, 2, 4, 12, 3, 12),
            Block.box(7.1, 0, 12, 8.9, 2, 14),
            Block.box(2.0999999999999996, 0, 7.1, 3.9000000000000004, 2, 8.9),
            Block.box(12.1, 0, 7.1, 13.9, 2, 8.9),
            Block.box(7.1, 0, 2, 8.9, 2, 4),
            Block.box(5, 21, 8, 11, 22.32, 10),
            Block.box(5, 12, 10.01, 9, 17, 10.01),
            Block.box(9, 12, 10.01, 11, 17, 10.01),
            Block.box(6, 17, 10.01, 10, 21, 10.01),
            Block.box(5, 17, 8, 11, 21, 10),
            Block.box(11, 19, 8, 13, 20.5, 10),
            Block.box(11, 20.5, 8, 13, 21.5, 10),
            Block.box(11, 18, 8, 13, 19, 10),
            Block.box(11, 17, 8, 13, 18, 10),
            Block.box(3.75, 18, 10.01, 4.25, 18.75, 10.01),
            Block.box(3, 19, 8, 5, 20.5, 10),
            Block.box(3, 20.5, 8, 5, 21.5, 10),
            Block.box(3, 18, 8, 5, 19, 10),
            Block.box(3, 17, 8, 5, 18, 10),
            Block.box(11.75, 18, 10.01, 12.25, 18.75, 10.01)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_IV = Stream.of(
            Block.box(7, 23, 5, 9, 28, 7),
            Block.box(7, 23, 9, 9, 28, 11),
            Block.box(7.5, 28, 1.5, 8.5, 29, 14),
            Block.box(6.5, 13, 6.5, 7.5, 14, 9),
            Block.box(8, 12, 5, 10, 17, 11),
            Block.box(7.5, 3, 7.5, 8.5, 28, 8.5),
            Block.box(4, 2, 4, 12, 3, 12),
            Block.box(12, 0, 7.1, 14, 2, 8.9),
            Block.box(7.1, 0, 12.1, 8.9, 2, 13.9),
            Block.box(7.1, 0, 2.0999999999999996, 8.9, 2, 3.9000000000000004),
            Block.box(2, 0, 7.1, 4, 2, 8.9),
            Block.box(8, 21, 5, 10, 22.32, 11),
            Block.box(10.01, 12, 7, 10.01, 17, 11),
            Block.box(10.01, 12, 5, 10.01, 17, 7),
            Block.box(10.01, 17, 6, 10.01, 21, 10),
            Block.box(8, 17, 5, 10, 21, 11),
            Block.box(8, 19, 3, 10, 20.5, 5),
            Block.box(8, 20.5, 3, 10, 21.5, 5),
            Block.box(8, 18, 3, 10, 19, 5),
            Block.box(8, 17, 3, 10, 18, 5),
            Block.box(10.01, 18, 11.75, 10.01, 18.75, 12.25),
            Block.box(8, 19, 11, 10, 20.5, 13),
            Block.box(8, 20.5, 11, 10, 21.5, 13),
            Block.box(8, 18, 11, 10, 19, 13),
            Block.box(8, 17, 11, 10, 18, 13),
            Block.box(10.01, 18, 3.75, 10.01, 18.75, 4.25)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_IV = Stream.of(
            Block.box(7, 23, 9, 9, 28, 11),
            Block.box(7, 23, 5, 9, 28, 7),
            Block.box(7.5, 28, 2, 8.5, 29, 14.5),
            Block.box(8.5, 13, 7, 9.5, 14, 9.5),
            Block.box(6, 12, 5, 8, 17, 11),
            Block.box(7.5, 3, 7.5, 8.5, 28, 8.5),
            Block.box(4, 2, 4, 12, 3, 12),
            Block.box(2, 0, 7.1, 4, 2, 8.9),
            Block.box(7.1, 0, 2.0999999999999996, 8.9, 2, 3.9000000000000004),
            Block.box(7.1, 0, 12.1, 8.9, 2, 13.9),
            Block.box(12, 0, 7.1, 14, 2, 8.9),
            Block.box(6, 21, 5, 8, 22.32, 11),
            Block.box(5.99, 12, 5, 5.99, 17, 9),
            Block.box(5.99, 12, 9, 5.99, 17, 11),
            Block.box(5.99, 17, 6, 5.99, 21, 10),
            Block.box(6, 17, 5, 8, 21, 11),
            Block.box(6, 19, 11, 8, 20.5, 13),
            Block.box(6, 20.5, 11, 8, 21.5, 13),
            Block.box(6, 18, 11, 8, 19, 13),
            Block.box(6, 17, 11, 8, 18, 13),
            Block.box(5.99, 18, 3.75, 5.99, 18.75, 4.25),
            Block.box(6, 19, 3, 8, 20.5, 5),
            Block.box(6, 20.5, 3, 8, 21.5, 5),
            Block.box(6, 18, 3, 8, 19, 5),
            Block.box(6, 17, 3, 8, 18, 5),
            Block.box(5.99, 18, 11.75, 5.99, 18.75, 12.25)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public MultipurposeIVPump(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_IV;
            case SOUTH: return SOUTH_IV;
            case EAST: return EAST_IV;
            case WEST: return WEST_IV;
            default: return Shapes.block();
        }
    }
}
