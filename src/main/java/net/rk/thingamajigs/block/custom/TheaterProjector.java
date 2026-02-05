package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.blockentity.custom.TheaterProjectorBE;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class TheaterProjector extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final VoxelShape ALL = Block.box(0,0,0,16,32,16);
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 0, 0, 16, 16, 32),
            Block.box(-0.10000000000000142, 16, 17.1, 15.9, 19, 20.1),
            Shapes.join(Block.box(-0.00999999999999801, 18, 0, 15.99, 30, 20), Block.box(5, 21, -8, 11, 27, 4), BooleanOp.OR)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(-16, 0, 0, 16, 16, 16),
            Block.box(-4.100000000000001, 16, -0.10000000000000142, -1.1000000000000014, 19, 15.9),
            Shapes.join(Block.box(-4, 18, -0.00999999999999801, 16, 30, 15.99), Block.box(12, 21, 5, 24, 27, 11), BooleanOp.OR)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 0, -16, 16, 16, 16),
            Block.box(0.09999999999999964, 16, -4.100000000000001, 16.1, 19, -1.1000000000000014),
            Shapes.join(Block.box(0.009999999999999787, 18, -4, 16.009999999999998, 30, 16), Block.box(5, 21, 12, 11, 27, 24), BooleanOp.OR)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 0, 32, 16, 16),
            Block.box(17.1, 16, 0.09999999999999964, 20.1, 19, 16.1),
            Shapes.join(Block.box(0, 18, 0.009999999999999787, 20, 30, 16.009999999999998), Block.box(-8, 21, 5, 4, 27, 11), BooleanOp.OR)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public TheaterProjector(Properties properties) {
        super(properties.strength(1f,5f).sound(SoundType.METAL).mapColor(MapColor.COLOR_LIGHT_GRAY).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        BlockEntity be = bg.getBlockEntity(bp);
        if(be instanceof TheaterProjectorBE){
            if(((TheaterProjectorBE) be).custom){
                return ALL;
            }
        }
        switch(bs.getValue(FACING)){
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
            default -> {
                return ALL;
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs){return new TheaterProjectorBE(bp,bs);}
    @Override
    public RenderShape getRenderShape(BlockState state){return RenderShape.ENTITYBLOCK_ANIMATED;}

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }
    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
