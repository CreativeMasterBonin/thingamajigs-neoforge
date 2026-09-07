package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

import java.util.stream.Stream;

public class ShoppingBasket extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH_SHORT = Stream.of(
            Block.box(-1, 0, 0, 17, 1, 1),
            Block.box(-1, 0, 15, 17, 1, 16),
            Block.box(-1, 0, 1, 16, 1, 15),
            Block.box(16, 0, 1, 17, 1, 15),
            Block.box(-1, 10, 0, 17, 11, 1),
            Block.box(-1, 10, 15, 17, 11, 16),
            Block.box(-1, 10, 1, 0, 11, 15),
            Block.box(16, 10, 1, 17, 11, 15),
            Block.box(-1, 1, 0, 0, 10, 1),
            Block.box(-1, 1, 15, 0, 10, 16),
            Block.box(16, 1, 15, 17, 10, 16),
            Block.box(16, 1, 0, 17, 10, 1),
            Block.box(0, 1, 1, 16, 10, 15),
            Block.box(-1, 11, 6, 1, 13, 10),
            Block.box(15, 11, 6, 17, 13, 10),
            Block.box(13, 13, 7, 16, 14, 9),
            Block.box(0, 13, 7, 3, 14, 9),
            Block.box(2, 14, 7, 5, 15, 9),
            Block.box(4, 15, 7, 7, 16, 9),
            Block.box(7, 16, 7.1, 9, 17, 9.1),
            Block.box(0, 1, 1, 0, 10, 15),
            Block.box(16, 1, 1, 16, 10, 15),
            Block.box(0, 1, 15, 16, 10, 15),
            Block.box(0, 1, 1, 16, 10, 1),
            Block.box(11, 14, 7, 14, 15, 9),
            Block.box(9, 15, 7, 12, 16, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST_SHORT = Stream.of(
            Block.box(15, 0, -1, 16, 1, 17),
            Block.box(0, 0, -1, 1, 1, 17),
            Block.box(1, 0, -1, 15, 1, 16),
            Block.box(1, 0, 16, 15, 1, 17),
            Block.box(15, 10, -1, 16, 11, 17),
            Block.box(0, 10, -1, 1, 11, 17),
            Block.box(1, 10, -1, 15, 11, 0),
            Block.box(1, 10, 16, 15, 11, 17),
            Block.box(15, 1, -1, 16, 10, 0),
            Block.box(0, 1, -1, 1, 10, 0),
            Block.box(0, 1, 16, 1, 10, 17),
            Block.box(15, 1, 16, 16, 10, 17),
            Block.box(1, 1, 0, 15, 10, 16),
            Block.box(6, 11, -1, 10, 13, 1),
            Block.box(6, 11, 15, 10, 13, 17),
            Block.box(7, 13, 13, 9, 14, 16),
            Block.box(7, 13, 0, 9, 14, 3),
            Block.box(7, 14, 2, 9, 15, 5),
            Block.box(7, 15, 4, 9, 16, 7),
            Block.box(6.9, 16, 7, 8.9, 17, 9),
            Block.box(1, 1, 0, 15, 10, 0),
            Block.box(1, 1, 16, 15, 10, 16),
            Block.box(1, 1, 0, 1, 10, 16),
            Block.box(15, 1, 0, 15, 10, 16),
            Block.box(7, 14, 11, 9, 15, 14),
            Block.box(7, 15, 9, 9, 16, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTHSOUTH_TALL = Stream.of(
            Block.box(-1, 0, 0, 17, 1, 1),
            Block.box(-1, 0, 15, 17, 1, 16),
            Block.box(-1, 0, 1, 16, 1, 15),
            Block.box(16, 0, 1, 17, 1, 15),
            Block.box(0, 1, 1, 16, 10, 1),
            Block.box(-1, 10, 0, 17, 11, 1),
            Block.box(-1, 10, 15, 17, 11, 16),
            Block.box(-1, 10, 1, 0, 11, 15),
            Block.box(16, 10, 1, 17, 11, 15),
            Block.box(-1, 1, 0, 0, 10, 1),
            Block.box(-1, 1, 15, 0, 10, 16),
            Block.box(16, 1, 15, 17, 10, 16),
            Block.box(16, 1, 0, 17, 10, 1),
            Block.box(0, 1, 1, 16, 10, 15),
            Block.box(-1.0199999999999996, 11, 6, 0.9800000000000004, 13, 10),
            Block.box(15.02, 11, 6, 17.02, 13, 10),
            Block.box(-4, 5, 7.1, -1, 12, 9.1),
            Block.box(0, 1, 1, 0, 10, 15),
            Block.box(16, 1, 1, 16, 10, 15),
            Block.box(0, 1, 15, 16, 10, 15),
            Block.box(-1, 11, 0, 17, 12, 1),
            Block.box(-1, 11, 15, 17, 12, 16),
            Block.box(-1, 11, 1, 16, 12, 15),
            Block.box(16, 11, 1, 17, 12, 15),
            Block.box(0, 12, 1, 16, 21, 1),
            Block.box(-1, 21, 0, 17, 22, 1),
            Block.box(-1, 21, 15, 17, 22, 16),
            Block.box(-1, 21, 1, 0, 22, 15),
            Block.box(16, 21, 1, 17, 22, 15),
            Block.box(-1, 12, 0, 0, 21, 1),
            Block.box(-1, 12, 15, 0, 21, 16),
            Block.box(16, 12, 15, 17, 21, 16),
            Block.box(16, 12, 0, 17, 21, 1),
            Block.box(0, 12, 1, 16, 21, 15),
            Block.box(-1, 22, 6, 1, 24, 10),
            Block.box(15, 22, 6, 17, 24, 10),
            Block.box(-4, 16, 7.1, -1, 23, 9.1),
            Block.box(0, 12, 1, 0, 21, 15),
            Block.box(16, 12, 1, 16, 21, 15),
            Block.box(0, 12, 15, 16, 21, 15),
            Block.box(17, 16, 7.1, 20, 23, 9.1),
            Block.box(17, 5, 7.1, 20, 12, 9.1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST_TALL = Stream.of(
            Block.box(15, 0, -1, 16, 1, 17),
            Block.box(0, 0, -1, 1, 1, 17),
            Block.box(1, 0, -1, 15, 1, 16),
            Block.box(1, 0, 16, 15, 1, 17),
            Block.box(15, 1, 0, 15, 10, 16),
            Block.box(15, 10, -1, 16, 11, 17),
            Block.box(0, 10, -1, 1, 11, 17),
            Block.box(1, 10, -1, 15, 11, 0),
            Block.box(1, 10, 16, 15, 11, 17),
            Block.box(15, 1, -1, 16, 10, 0),
            Block.box(0, 1, -1, 1, 10, 0),
            Block.box(0, 1, 16, 1, 10, 17),
            Block.box(15, 1, 16, 16, 10, 17),
            Block.box(1, 1, 0, 15, 10, 16),
            Block.box(6, 11, -1.0199999999999996, 10, 13, 0.9800000000000004),
            Block.box(6, 11, 15.02, 10, 13, 17.02),
            Block.box(6.9, 5, -4, 8.9, 12, -1),
            Block.box(1, 1, 0, 15, 10, 0),
            Block.box(1, 1, 16, 15, 10, 16),
            Block.box(1, 1, 0, 1, 10, 16),
            Block.box(15, 11, -1, 16, 12, 17),
            Block.box(0, 11, -1, 1, 12, 17),
            Block.box(1, 11, -1, 15, 12, 16),
            Block.box(1, 11, 16, 15, 12, 17),
            Block.box(15, 12, 0, 15, 21, 16),
            Block.box(15, 21, -1, 16, 22, 17),
            Block.box(0, 21, -1, 1, 22, 17),
            Block.box(1, 21, -1, 15, 22, 0),
            Block.box(1, 21, 16, 15, 22, 17),
            Block.box(15, 12, -1, 16, 21, 0),
            Block.box(0, 12, -1, 1, 21, 0),
            Block.box(0, 12, 16, 1, 21, 17),
            Block.box(15, 12, 16, 16, 21, 17),
            Block.box(1, 12, 0, 15, 21, 16),
            Block.box(6, 22, -1, 10, 24, 1),
            Block.box(6, 22, 15, 10, 24, 17),
            Block.box(6.9, 16, -4, 8.9, 23, -1),
            Block.box(1, 12, 0, 15, 21, 0),
            Block.box(1, 12, 16, 15, 21, 16),
            Block.box(1, 12, 0, 1, 21, 16),
            Block.box(6.9, 16, 17, 8.9, 23, 20),
            Block.box(6.9, 5, 17, 8.9, 12, 20)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ShoppingBasket(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(state.is(TBlocks.SHOPPING_BASKET_PILE.get())){
            switch (state.getValue(FACING)){
                case NORTH,SOUTH ->{return NORTHSOUTH_TALL;}
                case EAST,WEST ->{return EASTWEST_TALL;}
                default -> {return Shapes.block();}
            }
        }
        else{
            switch (state.getValue(FACING)){
                case NORTH,SOUTH ->{return NORTHSOUTH_SHORT;}
                case EAST,WEST ->{return EASTWEST_SHORT;}
                default -> {return Shapes.block();}
            }
        }
    }
}
