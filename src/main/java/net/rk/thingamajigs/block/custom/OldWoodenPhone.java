package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class OldWoodenPhone extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(7.02, 5, 6, 9.02, 9, 10),
            Block.box(3, 0, 12, 13, 16, 16),
            Block.box(13, 11, 12, 15, 12, 13),
            Block.box(13, 11, 15, 15, 12, 16),
            Block.box(13, 6, 12.5, 16, 11, 15.5),
            Block.box(13.5, 11, 13, 15.5, 12.5, 15),
            Block.box(13, 12, 12.5, 16, 13, 15.5),
            Block.box(13, 13, 14, 15, 14, 15),
            Block.box(8.5, 11, 11, 11.5, 14, 12),
            Block.box(4.5, 11, 11, 7.5, 14, 12),
            Block.box(5.5, 2, 11, 10.5, 9, 12),
            Block.box(7, 4, 10, 9, 7, 11),
            Block.box(9.5, 12, 10, 10.5, 13, 11),
            Block.box(5.5, 12, 10, 6.5, 13, 11),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(6.5, 8, 4, 9.5, 11, 6),
            Block.box(7, 8.5, 1, 9, 10.5, 4),
            Block.box(1, 7, 13, 3, 8, 14),
            Block.box(1, 4, 13, 2, 7, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(6, 5, 7.02, 10, 9, 9.02),
            Block.box(0, 0, 3, 4, 16, 13),
            Block.box(3, 11, 13, 4, 12, 15),
            Block.box(0, 11, 13, 1, 12, 15),
            Block.box(0.5, 6, 13, 3.5, 11, 16),
            Block.box(1, 11, 13.5, 3, 12.5, 15.5),
            Block.box(0.5, 12, 13, 3.5, 13, 16),
            Block.box(1, 13, 13, 2, 14, 15),
            Block.box(4, 11, 8.5, 5, 14, 11.5),
            Block.box(4, 11, 4.5, 5, 14, 7.5),
            Block.box(4, 2, 5.5, 5, 9, 10.5),
            Block.box(5, 4, 7, 6, 7, 9),
            Block.box(5, 12, 9.5, 6, 13, 10.5),
            Block.box(5, 12, 5.5, 6, 13, 6.5),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(10, 8, 6.5, 12, 11, 9.5),
            Block.box(12, 8.5, 7, 15, 10.5, 9),
            Block.box(2, 7, 1, 3, 8, 3),
            Block.box(2, 4, 1, 3, 7, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(6.98, 5, 6, 8.98, 9, 10),
            Block.box(3, 0, 0, 13, 16, 4),
            Block.box(1, 11, 3, 3, 12, 4),
            Block.box(1, 11, 0, 3, 12, 1),
            Block.box(0, 6, 0.5, 3, 11, 3.5),
            Block.box(0.5, 11, 1, 2.5, 12.5, 3),
            Block.box(0, 12, 0.5, 3, 13, 3.5),
            Block.box(1, 13, 1, 3, 14, 2),
            Block.box(4.5, 11, 4, 7.5, 14, 5),
            Block.box(8.5, 11, 4, 11.5, 14, 5),
            Block.box(5.5, 2, 4, 10.5, 9, 5),
            Block.box(7, 4, 5, 9, 7, 6),
            Block.box(5.5, 12, 5, 6.5, 13, 6),
            Block.box(9.5, 12, 5, 10.5, 13, 6),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(6.5, 8, 10, 9.5, 11, 12),
            Block.box(7, 8.5, 12, 9, 10.5, 15),
            Block.box(13, 7, 2, 15, 8, 3),
            Block.box(14, 4, 2, 15, 7, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(6, 5, 6.98, 10, 9, 8.98),
            Block.box(12, 0, 3, 16, 16, 13),
            Block.box(12, 11, 1, 13, 12, 3),
            Block.box(15, 11, 1, 16, 12, 3),
            Block.box(12.5, 6, 0, 15.5, 11, 3),
            Block.box(13, 11, 0.5, 15, 12.5, 2.5),
            Block.box(12.5, 12, 0, 15.5, 13, 3),
            Block.box(14, 13, 1, 15, 14, 3),
            Block.box(11, 11, 4.5, 12, 14, 7.5),
            Block.box(11, 11, 8.5, 12, 14, 11.5),
            Block.box(11, 2, 5.5, 12, 9, 10.5),
            Block.box(10, 4, 7, 11, 7, 9),
            Block.box(10, 12, 5.5, 11, 13, 6.5),
            Block.box(10, 12, 9.5, 11, 13, 10.5),
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(4, 8, 6.5, 6, 11, 9.5),
            Block.box(1, 8.5, 7, 4, 10.5, 9),
            Block.box(13, 7, 13, 14, 8, 15),
            Block.box(13, 4, 14, 14, 7, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public OldWoodenPhone(Properties properties) {
        super(properties.strength(1F,5F));
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
