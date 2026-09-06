package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
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
public class WreathBlock extends Block implements SimpleWaterloggedBlock{
    public static final MapCodec<WreathBlock> CHRISTMAS_WREATH_MAP_CODEC = Block.simpleCodec(WreathBlock::new);
    @Override
    public MapCodec<WreathBlock> codec() {return CHRISTMAS_WREATH_MAP_CODEC;}

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(1, 4, 14, 3, 12, 16),
            Block.box(4, 1, 14, 12, 3, 16),
            Block.box(4, 13, 14, 12, 15, 16),
            Block.box(3, 3, 14, 5, 5, 16),
            Block.box(3, 11, 14, 5, 13, 16),
            Block.box(11, 3, 14, 13, 5, 16),
            Block.box(11, 11, 14, 13, 13, 16),
            Block.box(13, 4, 14, 15, 12, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(13, 4, 0, 15, 12, 2),
            Block.box(4, 1, 0, 12, 3, 2),
            Block.box(4, 13, 0, 12, 15, 2),
            Block.box(11, 3, 0, 13, 5, 2),
            Block.box(11, 11, 0, 13, 13, 2),
            Block.box(3, 3, 0, 5, 5, 2),
            Block.box(3, 11, 0, 5, 13, 2),
            Block.box(1, 4, 0, 3, 12, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(0, 4, 1, 2, 12, 3),
            Block.box(0, 1, 4, 2, 3, 12),
            Block.box(0, 13, 4, 2, 15, 12),
            Block.box(0, 3, 3, 2, 5, 5),
            Block.box(0, 11, 3, 2, 13, 5),
            Block.box(0, 3, 11, 2, 5, 13),
            Block.box(0, 11, 11, 2, 13, 13),
            Block.box(0, 4, 13, 2, 12, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(14, 4, 13, 16, 12, 15),
            Block.box(14, 1, 4, 16, 3, 12),
            Block.box(14, 13, 4, 16, 15, 12),
            Block.box(14, 3, 11, 16, 5, 13),
            Block.box(14, 11, 11, 16, 13, 13),
            Block.box(14, 3, 3, 16, 5, 5),
            Block.box(14, 11, 3, 16, 13, 5),
            Block.box(14, 4, 1, 16, 12, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public WreathBlock(Properties p) {
        super(p.noOcclusion().strength(1F));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
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
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

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
