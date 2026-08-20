package net.rk.thingamajigs.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class CelltowerComponent extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_SINGULAR_ANTENNA = Stream.of(
            Block.box(6, 0, 0, 10, 16, 4),
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(7, 7, 4, 9, 9, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SINGULAR_ANTENNA = Stream.of(
            Block.box(12, 0, 6, 16, 16, 10),
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(9, 7, 7, 12, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SINGULAR_ANTENNA = Stream.of(
            Block.box(6, 0, 12, 10, 16, 16),
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(7, 7, 9, 9, 9, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SINGULAR_ANTENNA = Stream.of(
            Block.box(0, 0, 6, 4, 16, 10),
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(4, 7, 7, 7, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NS_WAY_ANTENNA = Stream.of(NORTH_SINGULAR_ANTENNA,SOUTH_SINGULAR_ANTENNA).reduce((v1,v2) -> Shapes.join(v1,v2,BooleanOp.OR)).get();
    public static final VoxelShape EW_WAY_ANTENNA = Stream.of(EAST_SINGULAR_ANTENNA,WEST_SINGULAR_ANTENNA).reduce((v1,v2) -> Shapes.join(v1,v2,BooleanOp.OR)).get();
    public static final VoxelShape ALL_FOUR_WAY_ANTENNA = Stream.of(NS_WAY_ANTENNA,EW_WAY_ANTENNA).reduce((v1,v2) -> Shapes.join(v1,v2,BooleanOp.OR)).get();

    public static final VoxelShape NORTH_MULTI_ANGLE = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(6, 7, -7, 11, 23, -4),
            Block.box(6, 5, 21, 11, 21, 24),
            Block.box(-5, 11, 4, -2, 27, 9),
            Block.box(19, 11, 5, 22, 27, 10),
            Block.box(3, 12, 6, 7, 14, 8),
            Block.box(1, 14, 6, 4, 16, 8),
            Block.box(-1, 16, 6, 2, 18, 8),
            Block.box(-2, 18, 6, -1, 21, 8),
            Block.box(7, 10, 9, 9, 12, 13),
            Block.box(7, 12, 13, 9, 14, 17),
            Block.box(7, 14, 17, 9, 16, 21),
            Block.box(9, 8, 6, 11, 10, 8),
            Block.box(11, 10, 6, 14, 12, 8),
            Block.box(13, 12, 6, 16, 14, 8),
            Block.box(15, 14, 6, 18, 16, 8),
            Block.box(18, 16, 6, 19, 19, 8),
            Block.box(7, 10, 2, 9, 12, 7),
            Block.box(7, 12, -4, 9, 14, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_MULTI_ANGLE = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(20, 7, 6, 23, 23, 11),
            Block.box(-8, 5, 6, -5, 21, 11),
            Block.box(7, 11, -5, 12, 27, -2),
            Block.box(6, 11, 19, 11, 27, 22),
            Block.box(8, 12, 3, 10, 14, 7),
            Block.box(8, 14, 1, 10, 16, 4),
            Block.box(8, 16, -1, 10, 18, 2),
            Block.box(8, 18, -2, 10, 21, -1),
            Block.box(3, 10, 7, 7, 12, 9),
            Block.box(-1, 12, 7, 3, 14, 9),
            Block.box(-5, 14, 7, -1, 16, 9),
            Block.box(8, 8, 9, 10, 10, 11),
            Block.box(8, 10, 11, 10, 12, 14),
            Block.box(8, 12, 13, 10, 14, 16),
            Block.box(8, 14, 15, 10, 16, 18),
            Block.box(8, 16, 18, 10, 19, 19),
            Block.box(9, 10, 7, 14, 12, 9),
            Block.box(14, 12, 7, 20, 14, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_MULTI_ANGLE = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(5, 7, 20, 10, 23, 23),
            Block.box(5, 5, -8, 10, 21, -5),
            Block.box(18, 11, 7, 21, 27, 12),
            Block.box(-6, 11, 6, -3, 27, 11),
            Block.box(9, 12, 8, 13, 14, 10),
            Block.box(12, 14, 8, 15, 16, 10),
            Block.box(14, 16, 8, 17, 18, 10),
            Block.box(17, 18, 8, 18, 21, 10),
            Block.box(7, 10, 3, 9, 12, 7),
            Block.box(7, 12, -1, 9, 14, 3),
            Block.box(7, 14, -5, 9, 16, -1),
            Block.box(5, 8, 8, 7, 10, 10),
            Block.box(2, 10, 8, 5, 12, 10),
            Block.box(0, 12, 8, 3, 14, 10),
            Block.box(-2, 14, 8, 1, 16, 10),
            Block.box(-3, 16, 8, -2, 19, 10),
            Block.box(7, 10, 9, 9, 12, 14),
            Block.box(7, 12, 14, 9, 14, 20)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_MULTI_ANGLE = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(-7, 7, 5, -4, 23, 10),
            Block.box(21, 5, 5, 24, 21, 10),
            Block.box(4, 11, 18, 9, 27, 21),
            Block.box(5, 11, -6, 10, 27, -3),
            Block.box(6, 12, 9, 8, 14, 13),
            Block.box(6, 14, 12, 8, 16, 15),
            Block.box(6, 16, 14, 8, 18, 17),
            Block.box(6, 18, 17, 8, 21, 18),
            Block.box(9, 10, 7, 13, 12, 9),
            Block.box(13, 12, 7, 17, 14, 9),
            Block.box(17, 14, 7, 21, 16, 9),
            Block.box(6, 8, 5, 8, 10, 7),
            Block.box(6, 10, 2, 8, 12, 5),
            Block.box(6, 12, 0, 8, 14, 3),
            Block.box(6, 14, -2, 8, 16, 1),
            Block.box(6, 16, -3, 8, 19, -2),
            Block.box(2, 10, 7, 7, 12, 9),
            Block.box(-4, 12, 7, 2, 14, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape N_MULTI_ANGLE_ALL = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(6, 0, 0, 10, 16, 4),
            Block.box(7, 7, 4, 9, 9, 7),
            Block.box(11.75, 0, 6, 15.75, 16, 10),
            Block.box(8.75, 7, 6.75, 11.75, 9, 8.75),
            Block.box(6, 0, 12, 10, 16, 16),
            Block.box(7.2, 7, 8.5, 8.7, 9, 12.5),
            Block.box(4.25, 7, 6.75, 7.25, 9, 8.75),
            Block.box(0.25, 0, 6, 4.25, 16, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape E_MULTI_ANGLE_ALL = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(12, 0, 6, 16, 16, 10),
            Block.box(9, 7, 7, 12, 9, 9),
            Block.box(6, 0, 11.75, 10, 16, 15.75),
            Block.box(7.25, 7, 8.75, 9.25, 9, 11.75),
            Block.box(0, 0, 6, 4, 16, 10),
            Block.box(3.5, 7, 7.2, 7.5, 9, 8.7),
            Block.box(7.25, 7, 4.25, 9.25, 9, 7.25),
            Block.box(6, 0, 0.25, 10, 16, 4.25)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape S_MULTI_ANGLE_ALL = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(6, 0, 12, 10, 16, 16),
            Block.box(7, 7, 9, 9, 9, 12),
            Block.box(0.25, 0, 6, 4.25, 16, 10),
            Block.box(4.25, 7, 7.25, 7.25, 9, 9.25),
            Block.box(6, 0, 0, 10, 16, 4),
            Block.box(7.300000000000001, 7, 3.5, 8.8, 9, 7.5),
            Block.box(8.75, 7, 7.25, 11.75, 9, 9.25),
            Block.box(11.75, 0, 6, 15.75, 16, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape W_MULTI_ANGLE_ALL = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(0, 0, 6, 4, 16, 10),
            Block.box(4, 7, 7, 7, 9, 9),
            Block.box(6, 0, 0.25, 10, 16, 4.25),
            Block.box(6.75, 7, 4.25, 8.75, 9, 7.25),
            Block.box(12, 0, 6, 16, 16, 10),
            Block.box(8.5, 7, 7.300000000000001, 12.5, 9, 8.8),
            Block.box(6.75, 7, 8.75, 8.75, 9, 11.75),
            Block.box(6, 0, 11.75, 10, 16, 15.75)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CelltowerComponent(Properties p) {
        super(p);
    }
}
