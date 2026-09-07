package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

import java.util.Optional;
import java.util.stream.Stream;

public class Wheelbarrow extends ThingamajigsDecorativeBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape NS_S = Optional.of(Block.box(0, 0, -6, 16, 16, 22)).get();
    private static final VoxelShape EW_S = Optional.of(Block.box(-6, 0, 0, 22, 16, 16)).get();

    public static final VoxelShape NORTH_WHEELBARROW = Stream.of(
            Block.box(-1, 9, -6, 17, 14, -3),
            Block.box(-1, 9, 19, 17, 14, 22),
            Block.box(-1, 8, -3, 17, 9.2, 19),
            Block.box(-2, 9, -4, -1, 14, 20),
            Block.box(17, 9, -4, 18, 14, 20),
            Block.box(6, 2.5, -4.5, 10, 3.5, -3.5),
            Block.box(5, 0.32, 13.5, 11, 1.42, 14.5),
            Block.box(7, 0, -7, 9, 6, -1),
            Block.box(10, 2, -4, 11, 8, 2),
            Block.box(5, 2, -4, 6, 8, 2),
            Block.box(4, 1, 10, 5, 8, 15),
            Block.box(11, 1, 10, 12, 8, 15),
            Block.box(1, 13, 22, 2, 14, 24),
            Block.box(1, 14, 24, 2, 15, 26),
            Block.box(1, 16, 28, 2, 17, 30),
            Block.box(1, 15, 26, 2, 16, 28),
            Block.box(14, 13, 22, 15, 14, 24),
            Block.box(14, 14, 24, 15, 15, 26),
            Block.box(14, 16, 28, 15, 17, 30),
            Block.box(14, 15, 26, 15, 16, 28)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_WHEELBARROW = Stream.of(
            Block.box(19, 9, -1, 22, 14, 17),
            Block.box(-6, 9, -1, -3, 14, 17),
            Block.box(-3, 8, -1, 19, 9.2, 17),
            Block.box(-4, 9, -2, 20, 14, -1),
            Block.box(-4, 9, 17, 20, 14, 18),
            Block.box(19.5, 2.5, 6, 20.5, 3.5, 10),
            Block.box(1.5, 0.32, 5, 2.5, 1.42, 11),
            Block.box(17, 0, 7, 23, 6, 9),
            Block.box(14, 2, 10, 20, 8, 11),
            Block.box(14, 2, 5, 20, 8, 6),
            Block.box(1, 1, 4, 6, 8, 5),
            Block.box(1, 1, 11, 6, 8, 12),
            Block.box(-8, 13, 1, -6, 14, 2),
            Block.box(-10, 14, 1, -8, 15, 2),
            Block.box(-14, 16, 1, -12, 17, 2),
            Block.box(-12, 15, 1, -10, 16, 2),
            Block.box(-8, 13, 14, -6, 14, 15),
            Block.box(-10, 14, 14, -8, 15, 15),
            Block.box(-14, 16, 14, -12, 17, 15),
            Block.box(-12, 15, 14, -10, 16, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_WHEELBARROW = Stream.of(
            Block.box(-1, 9, 19, 17, 14, 22),
            Block.box(-1, 9, -6, 17, 14, -3),
            Block.box(-1, 8, -3, 17, 9.2, 19),
            Block.box(17, 9, -4, 18, 14, 20),
            Block.box(-2, 9, -4, -1, 14, 20),
            Block.box(6, 2.5, 19.5, 10, 3.5, 20.5),
            Block.box(5, 0.32, 1.5, 11, 1.42, 2.5),
            Block.box(7, 0, 17, 9, 6, 23),
            Block.box(5, 2, 14, 6, 8, 20),
            Block.box(10, 2, 14, 11, 8, 20),
            Block.box(11, 1, 1, 12, 8, 6),
            Block.box(4, 1, 1, 5, 8, 6),
            Block.box(14, 13, -8, 15, 14, -6),
            Block.box(14, 14, -10, 15, 15, -8),
            Block.box(14, 16, -14, 15, 17, -12),
            Block.box(14, 15, -12, 15, 16, -10),
            Block.box(1, 13, -8, 2, 14, -6),
            Block.box(1, 14, -10, 2, 15, -8),
            Block.box(1, 16, -14, 2, 17, -12),
            Block.box(1, 15, -12, 2, 16, -10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_WHEELBARROW = Stream.of(
            Block.box(-6, 9, -1, -3, 14, 17),
            Block.box(19, 9, -1, 22, 14, 17),
            Block.box(-3, 8, -1, 19, 9.2, 17),
            Block.box(-4, 9, 17, 20, 14, 18),
            Block.box(-4, 9, -2, 20, 14, -1),
            Block.box(-4.5, 2.5, 6, -3.5, 3.5, 10),
            Block.box(13.5, 0.32, 5, 14.5, 1.42, 11),
            Block.box(-7, 0, 7, -1, 6, 9),
            Block.box(-4, 2, 5, 2, 8, 6),
            Block.box(-4, 2, 10, 2, 8, 11),
            Block.box(10, 1, 11, 15, 8, 12),
            Block.box(10, 1, 4, 15, 8, 5),
            Block.box(22, 13, 14, 24, 14, 15),
            Block.box(24, 14, 14, 26, 15, 15),
            Block.box(28, 16, 14, 30, 17, 15),
            Block.box(26, 15, 14, 28, 16, 15),
            Block.box(22, 13, 1, 24, 14, 2),
            Block.box(24, 14, 1, 26, 15, 2),
            Block.box(28, 16, 1, 30, 17, 2),
            Block.box(26, 15, 1, 28, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTH_LAWN_MOWER = Stream.of(
            Block.box(0, 1, -8, 16, 5, 16),
            Block.box(-1, 0, 11, 0, 7, 18),
            Block.box(-1, 0, -9, 0, 4, -5),
            Block.box(16, 0, 11, 17, 7, 18),
            Block.box(16, 0, -9, 17, 4, -5),
            Block.box(2, 5, -6, 10, 11, -3),
            Block.box(11, 5, -6, 14, 6, 2),
            Block.box(11, 7, -6, 14, 8, 2),
            Block.box(11, 9, -6, 14, 11, 2),
            Block.box(10, 5, -1, 11, 11, 1),
            Block.box(8, 5, 4, 9, 11, 6),
            Block.box(2, 5, -1, 8, 9, 7),
            Block.box(2, 10, -1, 8, 11, 7),
            Block.box(10, 5, 3, 14, 7, 7),
            Block.box(7, -0.2, 3, 9, 0.8, 5),
            Block.box(9, 0, 3, 15, 1, 5),
            Block.box(7, 0, -3, 9, 1, 3),
            Block.box(1, 0, 3, 7, 1, 5),
            Block.box(7, 0, 5, 9, 1, 11),
            Block.box(2, 2, -9, 14, 4, -8),
            Block.box(16, 1.35, -4, 19.75, 3.85, 8),
            Block.box(0, 5, 11, 2, 19, 18),
            Block.box(14, 5, 11, 16, 19, 18),
            Block.box(0, 16, 15, 16, 20, 20)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_LAWN_MOWER = Stream.of(
            Block.box(0, 1, 0, 24, 5, 16),
            Block.box(-2, 0, -1, 5, 7, 0),
            Block.box(21, 0, -1, 25, 4, 0),
            Block.box(-2, 0, 16, 5, 7, 17),
            Block.box(21, 0, 16, 25, 4, 17),
            Block.box(19, 5, 2, 22, 11, 10),
            Block.box(14, 5, 11, 22, 6, 14),
            Block.box(14, 7, 11, 22, 8, 14),
            Block.box(14, 9, 11, 22, 11, 14),
            Block.box(15, 5, 10, 17, 11, 11),
            Block.box(10, 5, 8, 12, 11, 9),
            Block.box(9, 5, 2, 17, 9, 8),
            Block.box(9, 10, 2, 17, 11, 8),
            Block.box(9, 5, 10, 13, 7, 14),
            Block.box(11, -0.2, 7, 13, 0.8, 9),
            Block.box(11, 0, 9, 13, 1, 15),
            Block.box(13, 0, 7, 19, 1, 9),
            Block.box(11, 0, 1, 13, 1, 7),
            Block.box(5, 0, 7, 11, 1, 9),
            Block.box(24, 2, 2, 25, 4, 14),
            Block.box(8, 1.35, 16, 20, 3.85, 19.75),
            Block.box(-2, 5, 0, 5, 19, 2),
            Block.box(-2, 5, 14, 5, 19, 16),
            Block.box(-4, 16, 0, 1, 20, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_LAWN_MOWER = Stream.of(
            Block.box(0, 1, 0, 16, 5, 24),
            Block.box(16, 0, -2, 17, 7, 5),
            Block.box(16, 0, 21, 17, 4, 25),
            Block.box(-1, 0, -2, 0, 7, 5),
            Block.box(-1, 0, 21, 0, 4, 25),
            Block.box(6, 5, 19, 14, 11, 22),
            Block.box(2, 5, 14, 5, 6, 22),
            Block.box(2, 7, 14, 5, 8, 22),
            Block.box(2, 9, 14, 5, 11, 22),
            Block.box(5, 5, 15, 6, 11, 17),
            Block.box(7, 5, 10, 8, 11, 12),
            Block.box(8, 5, 9, 14, 9, 17),
            Block.box(8, 10, 9, 14, 11, 17),
            Block.box(2, 5, 9, 6, 7, 13),
            Block.box(7, -0.2, 11, 9, 0.8, 13),
            Block.box(1, 0, 11, 7, 1, 13),
            Block.box(7, 0, 13, 9, 1, 19),
            Block.box(9, 0, 11, 15, 1, 13),
            Block.box(7, 0, 5, 9, 1, 11),
            Block.box(2, 2, 24, 14, 4, 25),
            Block.box(-3.75, 1.35, 8, 0, 3.85, 20),
            Block.box(14, 5, -2, 16, 19, 5),
            Block.box(0, 5, -2, 2, 19, 5),
            Block.box(0, 16, -4, 16, 20, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_LAWN_MOWER = Stream.of(
            Block.box(-8, 1, 0, 16, 5, 16),
            Block.box(11, 0, 16, 18, 7, 17),
            Block.box(-9, 0, 16, -5, 4, 17),
            Block.box(11, 0, -1, 18, 7, 0),
            Block.box(-9, 0, -1, -5, 4, 0),
            Block.box(-6, 5, 6, -3, 11, 14),
            Block.box(-6, 5, 2, 2, 6, 5),
            Block.box(-6, 7, 2, 2, 8, 5),
            Block.box(-6, 9, 2, 2, 11, 5),
            Block.box(-1, 5, 5, 1, 11, 6),
            Block.box(4, 5, 7, 6, 11, 8),
            Block.box(-1, 5, 8, 7, 9, 14),
            Block.box(-1, 10, 8, 7, 11, 14),
            Block.box(3, 5, 2, 7, 7, 6),
            Block.box(3, -0.2, 7, 5, 0.8, 9),
            Block.box(3, 0, 1, 5, 1, 7),
            Block.box(-3, 0, 7, 3, 1, 9),
            Block.box(3, 0, 9, 5, 1, 15),
            Block.box(5, 0, 7, 11, 1, 9),
            Block.box(-9, 2, 2, -8, 4, 14),
            Block.box(-4, 1.35, -3.75, 8, 3.85, 0),
            Block.box(11, 5, 14, 18, 19, 16),
            Block.box(11, 5, 0, 18, 19, 2),
            Block.box(15, 16, 0, 20, 20, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_SLEIGH = Stream.of(
            Block.box(-1, 8, 17, 17, 21, 23),
            Block.box(0, 0, -6, 2, 1, 26),
            Block.box(14, 0, -6, 16, 1, 26),
            Block.box(0, 1, -7, 1, 4, -5),
            Block.box(15, 1, -7, 16, 4, -5),
            Block.box(0, 4, -6, 2, 5, -4),
            Block.box(14, 4, -6, 16, 5, -4),
            Block.box(-2, 6, -9, 18, 8, 23),
            Block.box(-2, 8, 23, 18, 24, 25),
            Block.box(-4, 8, -7, -2, 16, 9),
            Block.box(18, 8, -7, 20, 16, 9),
            Block.box(18, 8, 9, 20, 20, 23),
            Block.box(-4, 8, 9, -2, 20, 23),
            Block.box(-4, 11, -13, -2, 17, -7),
            Block.box(18, 11, -13, 20, 17, -7),
            Block.box(18, 17, -15, 20, 20, -10),
            Block.box(-4, 17, -15, -2, 20, -10),
            Block.box(-4, 16, -15, 20, 17, -13),
            Block.box(-4, 10, -13, 20, 11, -7),
            Block.box(-2, 8, -10, 18, 10, -7),
            Block.box(-2, 11, -14, 18, 16, -13),
            Block.box(-2, 17, -16, 18, 20, -15),
            Block.box(-1, 8, 1, 17, 10, 17),
            Block.box(0, 1, 19, 2, 6, 20),
            Block.box(14, 1, 19, 16, 6, 20),
            Block.box(0, 1, 16, 2, 6, 17),
            Block.box(14, 1, 16, 16, 6, 17),
            Block.box(14, 1, 0, 16, 6, 1),
            Block.box(0, 1, 0, 2, 6, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SLEIGH = Stream.of(
            Block.box(-7, 8, -1, -1, 21, 17),
            Block.box(-10, 0, 0, 22, 1, 2),
            Block.box(-10, 0, 14, 22, 1, 16),
            Block.box(21, 1, 0, 23, 4, 1),
            Block.box(21, 1, 15, 23, 4, 16),
            Block.box(20, 4, 0, 22, 5, 2),
            Block.box(20, 4, 14, 22, 5, 16),
            Block.box(-7, 6, -2, 25, 8, 18),
            Block.box(-9, 8, -2, -7, 24, 18),
            Block.box(7, 8, -4, 23, 16, -2),
            Block.box(7, 8, 18, 23, 16, 20),
            Block.box(-7, 8, 18, 7, 20, 20),
            Block.box(-7, 8, -4, 7, 20, -2),
            Block.box(23, 11, -4, 29, 17, -2),
            Block.box(23, 11, 18, 29, 17, 20),
            Block.box(26, 17, 18, 31, 20, 20),
            Block.box(26, 17, -4, 31, 20, -2),
            Block.box(29, 16, -4, 31, 17, 20),
            Block.box(23, 10, -4, 29, 11, 20),
            Block.box(23, 8, -2, 26, 10, 18),
            Block.box(29, 11, -2, 30, 16, 18),
            Block.box(31, 17, -2, 32, 20, 18),
            Block.box(-1, 8, -1, 15, 10, 17),
            Block.box(-4, 1, 0, -3, 6, 2),
            Block.box(-4, 1, 14, -3, 6, 16),
            Block.box(-1, 1, 0, 0, 6, 2),
            Block.box(-1, 1, 14, 0, 6, 16),
            Block.box(15, 1, 14, 16, 6, 16),
            Block.box(15, 1, 0, 16, 6, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SLEIGH = Stream.of(
            Block.box(-1, 8, -7, 17, 21, -1),
            Block.box(14, 0, -10, 16, 1, 22),
            Block.box(0, 0, -10, 2, 1, 22),
            Block.box(15, 1, 21, 16, 4, 23),
            Block.box(0, 1, 21, 1, 4, 23),
            Block.box(14, 4, 20, 16, 5, 22),
            Block.box(0, 4, 20, 2, 5, 22),
            Block.box(-2, 6, -7, 18, 8, 25),
            Block.box(-2, 8, -9, 18, 24, -7),
            Block.box(18, 8, 7, 20, 16, 23),
            Block.box(-4, 8, 7, -2, 16, 23),
            Block.box(-4, 8, -7, -2, 20, 7),
            Block.box(18, 8, -7, 20, 20, 7),
            Block.box(18, 11, 23, 20, 17, 29),
            Block.box(-4, 11, 23, -2, 17, 29),
            Block.box(-4, 17, 26, -2, 20, 31),
            Block.box(18, 17, 26, 20, 20, 31),
            Block.box(-4, 16, 29, 20, 17, 31),
            Block.box(-4, 10, 23, 20, 11, 29),
            Block.box(-2, 8, 23, 18, 10, 26),
            Block.box(-2, 11, 29, 18, 16, 30),
            Block.box(-2, 17, 31, 18, 20, 32),
            Block.box(-1, 8, -1, 17, 10, 15),
            Block.box(14, 1, -4, 16, 6, -3),
            Block.box(0, 1, -4, 2, 6, -3),
            Block.box(14, 1, -1, 16, 6, 0),
            Block.box(0, 1, -1, 2, 6, 0),
            Block.box(0, 1, 15, 2, 6, 16),
            Block.box(14, 1, 15, 16, 6, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SLEIGH = Stream.of(
            Block.box(17, 8, -1, 23, 21, 17),
            Block.box(-6, 0, 14, 26, 1, 16),
            Block.box(-6, 0, 0, 26, 1, 2),
            Block.box(-7, 1, 15, -5, 4, 16),
            Block.box(-7, 1, 0, -5, 4, 1),
            Block.box(-6, 4, 14, -4, 5, 16),
            Block.box(-6, 4, 0, -4, 5, 2),
            Block.box(-9, 6, -2, 23, 8, 18),
            Block.box(23, 8, -2, 25, 24, 18),
            Block.box(-7, 8, 18, 9, 16, 20),
            Block.box(-7, 8, -4, 9, 16, -2),
            Block.box(9, 8, -4, 23, 20, -2),
            Block.box(9, 8, 18, 23, 20, 20),
            Block.box(-13, 11, 18, -7, 17, 20),
            Block.box(-13, 11, -4, -7, 17, -2),
            Block.box(-15, 17, -4, -10, 20, -2),
            Block.box(-15, 17, 18, -10, 20, 20),
            Block.box(-15, 16, -4, -13, 17, 20),
            Block.box(-13, 10, -4, -7, 11, 20),
            Block.box(-10, 8, -2, -7, 10, 18),
            Block.box(-14, 11, -2, -13, 16, 18),
            Block.box(-16, 17, -2, -15, 20, 18),
            Block.box(1, 8, -1, 17, 10, 17),
            Block.box(19, 1, 14, 20, 6, 16),
            Block.box(19, 1, 0, 20, 6, 2),
            Block.box(16, 1, 14, 17, 6, 16),
            Block.box(16, 1, 0, 17, 6, 2),
            Block.box(0, 1, 0, 1, 6, 2),
            Block.box(0, 1, 14, 1, 6, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_SHOPPING_CART_MOVER = Stream.of(
            Block.box(0, 5, -8, 16, 21, 24),
            Block.box(3, 21, 20, 4, 29, 21),
            Block.box(2, 28, 19, 5, 32, 22),
            Block.box(-5, 6, -9, 21, 10, -5),
            Block.box(19.5, 5, -15.5, 23.5, 9, -7.5),
            Block.box(-5, 2, -3, 0, 8, -1),
            Block.box(-7, 1.8, -2.6999999999999993, -4, 3.8, -1.1999999999999993),
            Block.box(-8, 0, -4, -6, 4, 0),
            Block.box(-7, 1.8, 17.3, -4, 3.8, 18.8),
            Block.box(-8, 0, 16, -6, 4, 20),
            Block.box(20, 1.8, -2.6999999999999993, 23, 3.8, -1.1999999999999993),
            Block.box(22, 0, -4, 24, 4, 0),
            Block.box(20, 1.8, 17.3, 23, 3.8, 18.8),
            Block.box(22, 0, 16, 24, 4, 20),
            Block.box(0, 16, 24, 2, 21, 26),
            Block.box(14, 16, 24, 16, 21, 26),
            Block.box(1, 19, 26, 15, 21, 28),
            Block.box(16, 2, -3, 21, 8, -1),
            Block.box(-5, 2, 17, 0, 8, 19),
            Block.box(16, 2, 17, 21, 8, 19),
            Block.box(-7.5, 5, -15.5, -3.5, 9, -7.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHOPPING_CART_MOVER = Stream.of(
            Block.box(-8, 5, 0, 24, 21, 16),
            Block.box(-5, 21, 3, -4, 29, 4),
            Block.box(-6, 28, 2, -3, 32, 5),
            Block.box(21, 6, -5, 25, 10, 21),
            Block.box(23.5, 5, 19.5, 31.5, 9, 23.5),
            Block.box(17, 2, -5, 19, 8, 0),
            Block.box(17.2, 1.8, -7, 18.7, 3.8, -4),
            Block.box(16, 0, -8, 20, 4, -6),
            Block.box(-2.8000000000000007, 1.8, -7, -1.3000000000000007, 3.8, -4),
            Block.box(-4, 0, -8, 0, 4, -6),
            Block.box(17.2, 1.8, 20, 18.7, 3.8, 23),
            Block.box(16, 0, 22, 20, 4, 24),
            Block.box(-2.8000000000000007, 1.8, 20, -1.3000000000000007, 3.8, 23),
            Block.box(-4, 0, 22, 0, 4, 24),
            Block.box(-10, 16, 0, -8, 21, 2),
            Block.box(-10, 16, 14, -8, 21, 16),
            Block.box(-12, 19, 1, -10, 21, 15),
            Block.box(17, 2, 16, 19, 8, 21),
            Block.box(-3, 2, -5, -1, 8, 0),
            Block.box(-3, 2, 16, -1, 8, 21),
            Block.box(23.5, 5, -7.5, 31.5, 9, -3.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHOPPING_CART_MOVER = Stream.of(
            Block.box(0, 5, -8, 16, 21, 24),
            Block.box(12, 21, -5, 13, 29, -4),
            Block.box(11, 28, -6, 14, 32, -3),
            Block.box(-5, 6, 21, 21, 10, 25),
            Block.box(-7.5, 5, 23.5, -3.5, 9, 31.5),
            Block.box(16, 2, 17, 21, 8, 19),
            Block.box(20, 1.8, 17.2, 23, 3.8, 18.7),
            Block.box(22, 0, 16, 24, 4, 20),
            Block.box(20, 1.8, -2.8000000000000007, 23, 3.8, -1.3000000000000007),
            Block.box(22, 0, -4, 24, 4, 0),
            Block.box(-7, 1.8, 17.2, -4, 3.8, 18.7),
            Block.box(-8, 0, 16, -6, 4, 20),
            Block.box(-7, 1.8, -2.8000000000000007, -4, 3.8, -1.3000000000000007),
            Block.box(-8, 0, -4, -6, 4, 0),
            Block.box(14, 16, -10, 16, 21, -8),
            Block.box(0, 16, -10, 2, 21, -8),
            Block.box(1, 19, -12, 15, 21, -10),
            Block.box(-5, 2, 17, 0, 8, 19),
            Block.box(16, 2, -3, 21, 8, -1),
            Block.box(-5, 2, -3, 0, 8, -1),
            Block.box(19.5, 5, 23.5, 23.5, 9, 31.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHOPPING_CART_MOVER = Stream.of(
            Block.box(-8, 5, 0, 24, 21, 16),
            Block.box(20, 21, 12, 21, 29, 13),
            Block.box(19, 28, 11, 22, 32, 14),
            Block.box(-9, 6, -5, -5, 10, 21),
            Block.box(-15.5, 5, -7.5, -7.5, 9, -3.5),
            Block.box(-3, 2, 16, -1, 8, 21),
            Block.box(-2.6999999999999993, 1.8, 20, -1.1999999999999993, 3.8, 23),
            Block.box(-4, 0, 22, 0, 4, 24),
            Block.box(17.3, 1.8, 20, 18.8, 3.8, 23),
            Block.box(16, 0, 22, 20, 4, 24),
            Block.box(-2.6999999999999993, 1.8, -7, -1.1999999999999993, 3.8, -4),
            Block.box(-4, 0, -8, 0, 4, -6),
            Block.box(17.3, 1.8, -7, 18.8, 3.8, -4),
            Block.box(16, 0, -8, 20, 4, -6),
            Block.box(24, 16, 14, 26, 21, 16),
            Block.box(24, 16, 0, 26, 21, 2),
            Block.box(26, 19, 1, 28, 21, 15),
            Block.box(-3, 2, -5, -1, 8, 0),
            Block.box(17, 2, 16, 19, 8, 21),
            Block.box(17, 2, -5, 19, 8, 0),
            Block.box(-15.5, 5, 19.5, -7.5, 9, 23.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTH_SHOPPING_CART = Stream.of(
            Block.box(2, 15, 20, 3, 17, 23),
            Block.box(1, 7, -4, 15, 15, -3),
            Block.box(1, 7, 19, 15, 15, 20),
            Block.box(0, 7, -3, 1, 15, 19),
            Block.box(15, 7, -3, 16, 15, 19),
            Block.box(1, 6, -3, 15, 7, 19),
            Block.box(13, 15, 20, 14, 17, 23),
            Block.box(1, 16.8, 22.3, 15, 19.1, 24.5),
            Block.box(0, 15, -4, 1, 16, 20),
            Block.box(15, 15, -4, 16, 16, 20),
            Block.box(1, 15, -4, 15, 16, -3),
            Block.box(1, 15, 19, 15, 16, 20),
            Block.box(0, 6, -4, 1, 7, 20),
            Block.box(15, 6, -4, 16, 7, 20),
            Block.box(1, 6, -4, 15, 7, -3),
            Block.box(1, 6, 19, 15, 7, 20),
            Block.box(2, 0, -1, 3, 3, 2),
            Block.box(0, 0, -1, 1, 3, 2),
            Block.box(1, 1, 0, 2, 6, 1),
            Block.box(15, 0, -1, 16, 3, 2),
            Block.box(13, 0, -1, 14, 3, 2),
            Block.box(14, 1, 0, 15, 6, 1),
            Block.box(2, 0, 14, 3, 3, 17),
            Block.box(0, 0, 14, 1, 3, 17),
            Block.box(1, 1, 15, 2, 6, 16),
            Block.box(15, 0, 14, 16, 3, 17),
            Block.box(13, 0, 14, 14, 3, 17),
            Block.box(14, 1, 15, 15, 6, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHOPPING_CART = Stream.of(
            Block.box(-7, 15, 2, -4, 17, 3),
            Block.box(19, 7, 1, 20, 15, 15),
            Block.box(-4, 7, 1, -3, 15, 15),
            Block.box(-3, 7, 0, 19, 15, 1),
            Block.box(-3, 7, 15, 19, 15, 16),
            Block.box(-3, 6, 1, 19, 7, 15),
            Block.box(-7, 15, 13, -4, 17, 14),
            Block.box(-8.5, 16.8, 1, -6.300000000000001, 19.1, 15),
            Block.box(-4, 15, 0, 20, 16, 1),
            Block.box(-4, 15, 15, 20, 16, 16),
            Block.box(19, 15, 1, 20, 16, 15),
            Block.box(-4, 15, 1, -3, 16, 15),
            Block.box(-4, 6, 0, 20, 7, 1),
            Block.box(-4, 6, 15, 20, 7, 16),
            Block.box(19, 6, 1, 20, 7, 15),
            Block.box(-4, 6, 1, -3, 7, 15),
            Block.box(14, 0, 2, 17, 3, 3),
            Block.box(14, 0, 0, 17, 3, 1),
            Block.box(15, 1, 1, 16, 6, 2),
            Block.box(14, 0, 15, 17, 3, 16),
            Block.box(14, 0, 13, 17, 3, 14),
            Block.box(15, 1, 14, 16, 6, 15),
            Block.box(-1, 0, 2, 2, 3, 3),
            Block.box(-1, 0, 0, 2, 3, 1),
            Block.box(0, 1, 1, 1, 6, 2),
            Block.box(-1, 0, 15, 2, 3, 16),
            Block.box(-1, 0, 13, 2, 3, 14),
            Block.box(0, 1, 14, 1, 6, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHOPPING_CART = Stream.of(
            Block.box(13, 15, -7, 14, 17, -4),
            Block.box(1, 7, 19, 15, 15, 20),
            Block.box(1, 7, -4, 15, 15, -3),
            Block.box(15, 7, -3, 16, 15, 19),
            Block.box(0, 7, -3, 1, 15, 19),
            Block.box(1, 6, -3, 15, 7, 19),
            Block.box(2, 15, -7, 3, 17, -4),
            Block.box(1, 16.8, -8.5, 15, 19.1, -6.300000000000001),
            Block.box(15, 15, -4, 16, 16, 20),
            Block.box(0, 15, -4, 1, 16, 20),
            Block.box(1, 15, 19, 15, 16, 20),
            Block.box(1, 15, -4, 15, 16, -3),
            Block.box(15, 6, -4, 16, 7, 20),
            Block.box(0, 6, -4, 1, 7, 20),
            Block.box(1, 6, 19, 15, 7, 20),
            Block.box(1, 6, -4, 15, 7, -3),
            Block.box(13, 0, 14, 14, 3, 17),
            Block.box(15, 0, 14, 16, 3, 17),
            Block.box(14, 1, 15, 15, 6, 16),
            Block.box(0, 0, 14, 1, 3, 17),
            Block.box(2, 0, 14, 3, 3, 17),
            Block.box(1, 1, 15, 2, 6, 16),
            Block.box(13, 0, -1, 14, 3, 2),
            Block.box(15, 0, -1, 16, 3, 2),
            Block.box(14, 1, 0, 15, 6, 1),
            Block.box(0, 0, -1, 1, 3, 2),
            Block.box(2, 0, -1, 3, 3, 2),
            Block.box(1, 1, 0, 2, 6, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHOPPING_CART = Stream.of(
            Block.box(20, 15, 13, 23, 17, 14),
            Block.box(-4, 7, 1, -3, 15, 15),
            Block.box(19, 7, 1, 20, 15, 15),
            Block.box(-3, 7, 15, 19, 15, 16),
            Block.box(-3, 7, 0, 19, 15, 1),
            Block.box(-3, 6, 1, 19, 7, 15),
            Block.box(20, 15, 2, 23, 17, 3),
            Block.box(22.3, 16.8, 1, 24.5, 19.1, 15),
            Block.box(-4, 15, 15, 20, 16, 16),
            Block.box(-4, 15, 0, 20, 16, 1),
            Block.box(-4, 15, 1, -3, 16, 15),
            Block.box(19, 15, 1, 20, 16, 15),
            Block.box(-4, 6, 15, 20, 7, 16),
            Block.box(-4, 6, 0, 20, 7, 1),
            Block.box(-4, 6, 1, -3, 7, 15),
            Block.box(19, 6, 1, 20, 7, 15),
            Block.box(-1, 0, 13, 2, 3, 14),
            Block.box(-1, 0, 15, 2, 3, 16),
            Block.box(0, 1, 14, 1, 6, 15),
            Block.box(-1, 0, 0, 2, 3, 1),
            Block.box(-1, 0, 2, 2, 3, 3),
            Block.box(0, 1, 1, 1, 6, 2),
            Block.box(14, 0, 13, 17, 3, 14),
            Block.box(14, 0, 15, 17, 3, 16),
            Block.box(15, 1, 14, 16, 6, 15),
            Block.box(14, 0, 0, 17, 3, 1),
            Block.box(14, 0, 2, 17, 3, 3),
            Block.box(15, 1, 1, 16, 6, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Wheelbarrow(Properties properties) {
        super(properties.strength(1F,32F));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(state.is(TBlocks.WHEELBARROW.get())){
            switch (state.getValue(FACING)){
                case NORTH -> {return NORTH_WHEELBARROW;}
                case SOUTH -> {return SOUTH_WHEELBARROW;}
                case EAST -> {return EAST_WHEELBARROW;}
                case WEST -> {return WEST_WHEELBARROW;}
                default -> {return Shapes.block();}
            }
        }else if (state.is(TBlocks.LAWN_MOWER.get())) {
            switch (state.getValue(FACING)){
                case NORTH -> {return NORTH_LAWN_MOWER;}
                case SOUTH -> {return SOUTH_LAWN_MOWER;}
                case EAST -> {return EAST_LAWN_MOWER;}
                case WEST -> {return WEST_LAWN_MOWER;}
                default -> {return Shapes.block();}
            }
        }else if (state.is(TBlocks.SLEIGH.get())) {
            switch (state.getValue(FACING)){
                case NORTH -> {return NORTH_SLEIGH;}
                case SOUTH -> {return SOUTH_SLEIGH;}
                case EAST -> {return EAST_SLEIGH;}
                case WEST -> {return WEST_SLEIGH;}
                default -> {return Shapes.block();}
            }
        }else if (state.is(TBlocks.SHOPPING_CART_MOVER.get())) {
            switch (state.getValue(FACING)){
                case NORTH -> {return NORTH_SHOPPING_CART_MOVER;}
                case SOUTH -> {return SOUTH_SHOPPING_CART_MOVER;}
                case EAST -> {return EAST_SHOPPING_CART_MOVER;}
                case WEST -> {return WEST_SHOPPING_CART_MOVER;}
                default -> {return Shapes.block();}
            }
        }else if (state.is(TBlocks.SHOPPING_CART.get())) {
            switch (state.getValue(FACING)){
                case NORTH -> {return NORTH_SHOPPING_CART;}
                case SOUTH -> {return SOUTH_SHOPPING_CART;}
                case EAST -> {return EAST_SHOPPING_CART;}
                case WEST -> {return WEST_SHOPPING_CART;}
                default -> {return Shapes.block();}
            }
        } else{
            switch(state.getValue(FACING)){
                case NORTH:
                case SOUTH:
                    return NS_S;
                case EAST:
                case WEST:
                    return EW_S;
                default: return Shapes.block();
            }
        }
    }
}
