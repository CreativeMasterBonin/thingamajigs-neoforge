package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class Pole extends Block implements SimpleWaterloggedBlock{
    public static final MapCodec<Pole> POLE_MAP_CODEC = Block.simpleCodec(Pole::new);
    @Override
    public MapCodec<Pole> codec() {return POLE_MAP_CODEC;}

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    // single parts
    public static final VoxelShape HORIZONTAL_NORTHSOUTH = Optional.of(Block.box(0, 7, 7, 16, 9, 9)).get();
    public static final VoxelShape HORIZONTAL_EASTWEST = Optional.of(Block.box(7, 7, 0, 9, 9, 16)).get();
    public static final VoxelShape VERTICAL_ALL = Optional.of(Block.box(7, 0, 7, 9, 16, 9)).get();
    public static final VoxelShape SMALL_TOP_VERTICAL = Optional.of(Block.box(7, 9, 7, 9, 16, 9)).get();
    public static final VoxelShape SMALL_BOTTOM_VERTICAL = Optional.of(Block.box(7, 0, 7, 9, 7, 9)).get();
    public static final VoxelShape SMALL_NORTH = Optional.of(Block.box(7, 7, 0, 9, 9, 7)).get();
    public static final VoxelShape SMALL_SOUTH = Optional.of(Block.box(7, 7, 9, 9, 9, 16)).get();
    public static final VoxelShape SMALL_EAST = Optional.of(Block.box(9, 7, 7, 16, 9, 9)).get();
    public static final VoxelShape SMALL_WEST = Optional.of(Block.box(0, 7, 7, 7, 9, 9)).get();
    // multi shapes
    // T-Pole Shapes
    public static final VoxelShape T_NORTHSOUTH = Stream.of(SMALL_BOTTOM_VERTICAL, HORIZONTAL_NORTHSOUTH).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape T_EASTWEST = Stream.of(SMALL_BOTTOM_VERTICAL, HORIZONTAL_EASTWEST).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape T_INVERT_NORTHSOUTH = Stream.of(SMALL_TOP_VERTICAL, HORIZONTAL_NORTHSOUTH).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape T_INVERT_EASTWEST = Stream.of(SMALL_TOP_VERTICAL, HORIZONTAL_EASTWEST).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    // L-Horizontal
    public static final VoxelShape L_NORTH = Stream.of(SMALL_NORTH, SMALL_EAST).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape L_SOUTH = Stream.of(SMALL_EAST, SMALL_SOUTH).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape L_EAST = Stream.of(SMALL_SOUTH, SMALL_WEST).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape L_WEST = Stream.of(SMALL_WEST, SMALL_NORTH).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    // Axis Pole
    public static final VoxelShape AXIS_NORTH = Stream.of(L_NORTH, SMALL_BOTTOM_VERTICAL).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape AXIS_SOUTH = Stream.of(L_SOUTH, SMALL_BOTTOM_VERTICAL).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape AXIS_EAST = Stream.of(L_EAST, SMALL_BOTTOM_VERTICAL).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape AXIS_WEST = Stream.of(L_WEST, SMALL_BOTTOM_VERTICAL).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    // Plus Pole
    public static final VoxelShape PLUS_NORTHSOUTH = Stream.of(VERTICAL_ALL, HORIZONTAL_NORTHSOUTH).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape PLUS_EASTWEST = Stream.of(VERTICAL_ALL, HORIZONTAL_EASTWEST).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    // Holder Pole
    public static final VoxelShape NORTH_HOLDER = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(-1, 0, 0, 17, 1, 1),
            Block.box(-1, 15, 0, 17, 16, 1),
            Block.box(7, 0, 1, 9, 16, 2),
            Block.box(0, 0, 1, 2, 2, 3),
            Block.box(2, 0, 3, 4, 2, 5),
            Block.box(4, 0, 5, 6, 2, 7),
            Block.box(6, 0, 7, 7, 2, 8),
            Block.box(4, 14, 5, 6, 16, 7),
            Block.box(2, 14, 3, 4, 16, 5),
            Block.box(0, 14, 1, 2, 16, 3),
            Block.box(6, 14, 7, 7, 16, 8),
            Block.box(14, 0, 1, 16, 2, 3),
            Block.box(12, 0, 3, 14, 2, 5),
            Block.box(10, 0, 5, 12, 2, 7),
            Block.box(9, 0, 7, 10, 2, 8),
            Block.box(10, 14, 5, 12, 16, 7),
            Block.box(12, 14, 3, 14, 16, 5),
            Block.box(14, 14, 1, 16, 16, 3),
            Block.box(9, 14, 7, 10, 16, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_HOLDER = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(15, 0, -1, 16, 1, 17),
            Block.box(15, 15, -1, 16, 16, 17),
            Block.box(14, 0, 7, 15, 16, 9),
            Block.box(13, 0, 0, 15, 2, 2),
            Block.box(11, 0, 2, 13, 2, 4),
            Block.box(9, 0, 4, 11, 2, 6),
            Block.box(8, 0, 6, 9, 2, 7),
            Block.box(9, 14, 4, 11, 16, 6),
            Block.box(11, 14, 2, 13, 16, 4),
            Block.box(13, 14, 0, 15, 16, 2),
            Block.box(8, 14, 6, 9, 16, 7),
            Block.box(13, 0, 14, 15, 2, 16),
            Block.box(11, 0, 12, 13, 2, 14),
            Block.box(9, 0, 10, 11, 2, 12),
            Block.box(8, 0, 9, 9, 2, 10),
            Block.box(9, 14, 10, 11, 16, 12),
            Block.box(11, 14, 12, 13, 16, 14),
            Block.box(13, 14, 14, 15, 16, 16),
            Block.box(8, 14, 9, 9, 16, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_HOLDER = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(-1, 0, 15, 17, 1, 16),
            Block.box(-1, 15, 15, 17, 16, 16),
            Block.box(7, 0, 14, 9, 16, 15),
            Block.box(14, 0, 13, 16, 2, 15),
            Block.box(12, 0, 11, 14, 2, 13),
            Block.box(10, 0, 9, 12, 2, 11),
            Block.box(9, 0, 8, 10, 2, 9),
            Block.box(10, 14, 9, 12, 16, 11),
            Block.box(12, 14, 11, 14, 16, 13),
            Block.box(14, 14, 13, 16, 16, 15),
            Block.box(9, 14, 8, 10, 16, 9),
            Block.box(0, 0, 13, 2, 2, 15),
            Block.box(2, 0, 11, 4, 2, 13),
            Block.box(4, 0, 9, 6, 2, 11),
            Block.box(6, 0, 8, 7, 2, 9),
            Block.box(4, 14, 9, 6, 16, 11),
            Block.box(2, 14, 11, 4, 16, 13),
            Block.box(0, 14, 13, 2, 16, 15),
            Block.box(6, 14, 8, 7, 16, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_HOLDER = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(0, 0, -1, 1, 1, 17),
            Block.box(0, 15, -1, 1, 16, 17),
            Block.box(1, 0, 7, 2, 16, 9),
            Block.box(1, 0, 14, 3, 2, 16),
            Block.box(3, 0, 12, 5, 2, 14),
            Block.box(5, 0, 10, 7, 2, 12),
            Block.box(7, 0, 9, 8, 2, 10),
            Block.box(5, 14, 10, 7, 16, 12),
            Block.box(3, 14, 12, 5, 16, 14),
            Block.box(1, 14, 14, 3, 16, 16),
            Block.box(7, 14, 9, 8, 16, 10),
            Block.box(1, 0, 0, 3, 2, 2),
            Block.box(3, 0, 2, 5, 2, 4),
            Block.box(5, 0, 4, 7, 2, 6),
            Block.box(7, 0, 6, 8, 2, 7),
            Block.box(5, 14, 4, 7, 16, 6),
            Block.box(3, 14, 2, 5, 16, 4),
            Block.box(1, 14, 0, 3, 16, 2),
            Block.box(7, 14, 6, 8, 16, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Pole(Properties properties) {
        super(properties.strength(0.25F,2F).sound(SoundType.METAL).noOcclusion().noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
