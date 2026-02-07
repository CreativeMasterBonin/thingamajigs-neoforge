package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class CarWheel extends DirectionalBlock implements SimpleWaterloggedBlock {
    public static final VoxelShape NORTH = Stream.of(
            Block.box(1, 0, 3, 15, 14, 10),
            Block.box(6, 5, 1, 10, 9, 16),
            Block.box(5, 4, 2, 11, 10, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(6, 0, 1, 13, 14, 15),
            Block.box(0, 5, 6, 15, 9, 10),
            Block.box(13, 4, 5, 14, 10, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(1, 0, 6, 15, 14, 13),
            Block.box(6, 5, 0, 10, 9, 15),
            Block.box(5, 4, 13, 11, 10, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(3, 0, 1, 10, 14, 15),
            Block.box(1, 5, 6, 16, 9, 10),
            Block.box(2, 4, 5, 3, 10, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape UP = Stream.of(
            Block.box(1, 6, 1, 15, 13, 15),
            Block.box(6, 0, 6, 10, 15, 10),
            Block.box(5, 13, 5, 11, 14, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape DOWN = Stream.of(
            Block.box(1, 2, 1, 15, 9, 15),
            Block.box(6, 0, 6, 10, 15, 10),
            Block.box(5, 1, 5, 11, 2, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final MapCodec<CarWheel> CODEC = simpleCodec(CarWheel::new);

    public CarWheel(Properties p) {
        super(p.sound(SoundType.METAL).destroyTime(2f).explosionResistance(32).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter getter, BlockPos blockPos, CollisionContext context) {
        switch (blockState.getValue(FACING)){
            case NORTH -> {
                return NORTH;
            }
            case SOUTH -> {
                return SOUTH;
            }
            case EAST -> {
                return EAST;
            }
            case WEST -> {
                return WEST;
            }
            case UP -> {
                return UP;
            }
            case DOWN -> {
                return DOWN;
            }
            default -> {
                return Shapes.block();
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        FluidState fluidstate = bpc.getLevel().getFluidState(bpc.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING,bpc.getNearestLookingDirection().getOpposite().getOpposite())
                .setValue(WATERLOGGED,fluidstate.getType() == Fluids.WATER);
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation(blockState.getValue(FACING)));
    }
}
