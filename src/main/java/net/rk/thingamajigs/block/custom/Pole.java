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
    //

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
