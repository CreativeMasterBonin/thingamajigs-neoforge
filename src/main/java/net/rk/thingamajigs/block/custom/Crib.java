package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class Crib extends ThingamajigsDecorativeBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(-8, 8, 0, 8, 9, 16),
            Block.box(-8, 0, 0, -5, 8, 3),
            Block.box(-8, 0, 13, -5, 8, 16),
            Block.box(21, 0, 0, 24, 8, 3),
            Block.box(21, 0, 13, 24, 8, 16),
            Block.box(8, 8, 0, 24, 9, 16),
            Block.box(-8, 9, 0, -6, 21, 2),
            Block.box(22, 9, 0, 24, 21, 2),
            Block.box(18, 9, 0, 20, 21, 2),
            Block.box(-4, 9, 0, -2, 21, 2),
            Block.box(0, 9, 0, 2, 21, 2),
            Block.box(14, 9, 0, 16, 21, 2),
            Block.box(10, 9, 0, 12, 21, 2),
            Block.box(4, 9, 0, 6, 21, 2),
            Block.box(6, 10, 0, 10, 12, 2),
            Block.box(6, 18, 0, 10, 20, 2),
            Block.box(6, 14, 0, 10, 16, 2),
            Block.box(-8, 9, 14, -6, 21, 16),
            Block.box(22, 9, 14, 24, 21, 16),
            Block.box(22, 9, 10, 24, 21, 12),
            Block.box(22, 9, 4, 24, 21, 6),
            Block.box(-8, 9, 10, -6, 21, 12),
            Block.box(-8, 9, 4, -6, 21, 6),
            Block.box(-4, 9, 14, -2, 25, 16),
            Block.box(18, 9, 14, 20, 25, 16),
            Block.box(-2, 10, 15, 8, 24, 16),
            Block.box(8, 10, 15, 18, 24, 16),
            Block.box(-6, 13, 0, -4, 17, 2),
            Block.box(-2, 13, 0, 0, 17, 2),
            Block.box(2, 13, 0, 4, 17, 2),
            Block.box(16, 13, 0, 18, 17, 2),
            Block.box(12, 13, 0, 14, 17, 2),
            Block.box(20, 13, 0, 22, 17, 2),
            Block.box(-8, 13, 2, -6, 17, 4),
            Block.box(-8, 13, 12, -6, 17, 14),
            Block.box(22, 13, 2, 24, 17, 4),
            Block.box(22, 13, 12, 24, 17, 14),
            Block.box(22, 14, 6, 24, 16, 10),
            Block.box(-8, 14, 6, -6, 16, 10),
            Block.box(20, 11, 15, 22, 19, 16),
            Block.box(-6, 11, 15, -4, 19, 16),
            Block.box(-6, 9, 2, 8, 10, 14),
            Block.box(8, 9, 2, 22, 10, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(8, 8, 0, 24, 9, 16),
            Block.box(21, 0, 13, 24, 8, 16),
            Block.box(21, 0, 0, 24, 8, 3),
            Block.box(-8, 0, 13, -5, 8, 16),
            Block.box(-8, 0, 0, -5, 8, 3),
            Block.box(-8, 8, 0, 8, 9, 16),
            Block.box(22, 9, 14, 24, 21, 16),
            Block.box(-8, 9, 14, -6, 21, 16),
            Block.box(-4, 9, 14, -2, 21, 16),
            Block.box(18, 9, 14, 20, 21, 16),
            Block.box(14, 9, 14, 16, 21, 16),
            Block.box(0, 9, 14, 2, 21, 16),
            Block.box(4, 9, 14, 6, 21, 16),
            Block.box(10, 9, 14, 12, 21, 16),
            Block.box(6, 10, 14, 10, 12, 16),
            Block.box(6, 18, 14, 10, 20, 16),
            Block.box(6, 14, 14, 10, 16, 16),
            Block.box(22, 9, 0, 24, 21, 2),
            Block.box(-8, 9, 0, -6, 21, 2),
            Block.box(-8, 9, 4, -6, 21, 6),
            Block.box(-8, 9, 10, -6, 21, 12),
            Block.box(22, 9, 4, 24, 21, 6),
            Block.box(22, 9, 10, 24, 21, 12),
            Block.box(18, 9, 0, 20, 25, 2),
            Block.box(-4, 9, 0, -2, 25, 2),
            Block.box(8, 10, 0, 18, 24, 1),
            Block.box(-2, 10, 0, 8, 24, 1),
            Block.box(20, 13, 14, 22, 17, 16),
            Block.box(16, 13, 14, 18, 17, 16),
            Block.box(12, 13, 14, 14, 17, 16),
            Block.box(-2, 13, 14, 0, 17, 16),
            Block.box(2, 13, 14, 4, 17, 16),
            Block.box(-6, 13, 14, -4, 17, 16),
            Block.box(22, 13, 12, 24, 17, 14),
            Block.box(22, 13, 2, 24, 17, 4),
            Block.box(-8, 13, 12, -6, 17, 14),
            Block.box(-8, 13, 2, -6, 17, 4),
            Block.box(-8, 14, 6, -6, 16, 10),
            Block.box(22, 14, 6, 24, 16, 10),
            Block.box(-6, 11, 0, -4, 19, 1),
            Block.box(20, 11, 0, 22, 19, 1),
            Block.box(8, 9, 2, 22, 10, 14),
            Block.box(-6, 9, 2, 8, 10, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(0, 8, -8, 16, 9, 8),
            Block.box(13, 0, -8, 16, 8, -5),
            Block.box(0, 0, -8, 3, 8, -5),
            Block.box(13, 0, 21, 16, 8, 24),
            Block.box(0, 0, 21, 3, 8, 24),
            Block.box(0, 8, 8, 16, 9, 24),
            Block.box(14, 9, -8, 16, 21, -6),
            Block.box(14, 9, 22, 16, 21, 24),
            Block.box(14, 9, 18, 16, 21, 20),
            Block.box(14, 9, -4, 16, 21, -2),
            Block.box(14, 9, 0, 16, 21, 2),
            Block.box(14, 9, 14, 16, 21, 16),
            Block.box(14, 9, 10, 16, 21, 12),
            Block.box(14, 9, 4, 16, 21, 6),
            Block.box(14, 10, 6, 16, 12, 10),
            Block.box(14, 18, 6, 16, 20, 10),
            Block.box(14, 14, 6, 16, 16, 10),
            Block.box(0, 9, -8, 2, 21, -6),
            Block.box(0, 9, 22, 2, 21, 24),
            Block.box(4, 9, 22, 6, 21, 24),
            Block.box(10, 9, 22, 12, 21, 24),
            Block.box(4, 9, -8, 6, 21, -6),
            Block.box(10, 9, -8, 12, 21, -6),
            Block.box(0, 9, -4, 2, 25, -2),
            Block.box(0, 9, 18, 2, 25, 20),
            Block.box(0, 10, -2, 1, 24, 8),
            Block.box(0, 10, 8, 1, 24, 18),
            Block.box(14, 13, -6, 16, 17, -4),
            Block.box(14, 13, -2, 16, 17, 0),
            Block.box(14, 13, 2, 16, 17, 4),
            Block.box(14, 13, 16, 16, 17, 18),
            Block.box(14, 13, 12, 16, 17, 14),
            Block.box(14, 13, 20, 16, 17, 22),
            Block.box(12, 13, -8, 14, 17, -6),
            Block.box(2, 13, -8, 4, 17, -6),
            Block.box(12, 13, 22, 14, 17, 24),
            Block.box(2, 13, 22, 4, 17, 24),
            Block.box(6, 14, 22, 10, 16, 24),
            Block.box(6, 14, -8, 10, 16, -6),
            Block.box(0, 11, 20, 1, 19, 22),
            Block.box(0, 11, -6, 1, 19, -4),
            Block.box(2, 9, -6, 14, 10, 8),
            Block.box(2, 9, 8, 14, 10, 22)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(0, 8, 8, 16, 9, 24),
            Block.box(0, 0, 21, 3, 8, 24),
            Block.box(13, 0, 21, 16, 8, 24),
            Block.box(0, 0, -8, 3, 8, -5),
            Block.box(13, 0, -8, 16, 8, -5),
            Block.box(0, 8, -8, 16, 9, 8),
            Block.box(0, 9, 22, 2, 21, 24),
            Block.box(0, 9, -8, 2, 21, -6),
            Block.box(0, 9, -4, 2, 21, -2),
            Block.box(0, 9, 18, 2, 21, 20),
            Block.box(0, 9, 14, 2, 21, 16),
            Block.box(0, 9, 0, 2, 21, 2),
            Block.box(0, 9, 4, 2, 21, 6),
            Block.box(0, 9, 10, 2, 21, 12),
            Block.box(0, 10, 6, 2, 12, 10),
            Block.box(0, 18, 6, 2, 20, 10),
            Block.box(0, 14, 6, 2, 16, 10),
            Block.box(14, 9, 22, 16, 21, 24),
            Block.box(14, 9, -8, 16, 21, -6),
            Block.box(10, 9, -8, 12, 21, -6),
            Block.box(4, 9, -8, 6, 21, -6),
            Block.box(10, 9, 22, 12, 21, 24),
            Block.box(4, 9, 22, 6, 21, 24),
            Block.box(14, 9, 18, 16, 25, 20),
            Block.box(14, 9, -4, 16, 25, -2),
            Block.box(15, 10, 8, 16, 24, 18),
            Block.box(15, 10, -2, 16, 24, 8),
            Block.box(0, 13, 20, 2, 17, 22),
            Block.box(0, 13, 16, 2, 17, 18),
            Block.box(0, 13, 12, 2, 17, 14),
            Block.box(0, 13, -2, 2, 17, 0),
            Block.box(0, 13, 2, 2, 17, 4),
            Block.box(0, 13, -6, 2, 17, -4),
            Block.box(2, 13, 22, 4, 17, 24),
            Block.box(12, 13, 22, 14, 17, 24),
            Block.box(2, 13, -8, 4, 17, -6),
            Block.box(12, 13, -8, 14, 17, -6),
            Block.box(6, 14, -8, 10, 16, -6),
            Block.box(6, 14, 22, 10, 16, 24),
            Block.box(15, 11, -6, 16, 19, -4),
            Block.box(15, 11, 20, 16, 19, 22),
            Block.box(2, 9, 8, 14, 10, 22),
            Block.box(2, 9, -6, 14, 10, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Crib(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = bs.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return Shapes.block();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
