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
import net.rk.thingamajigs.block.TBlocks;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class HazardSignBlock extends Block implements SimpleWaterloggedBlock{
    public static final MapCodec<HazardSignBlock> HAZARD_SIGN_BLOCK_MAP_CODEC = Block.simpleCodec(HazardSignBlock::new);
    @Override
    public MapCodec<HazardSignBlock> codec() {return HAZARD_SIGN_BLOCK_MAP_CODEC;}

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final VoxelShape NORTH_SHAPE = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape SOUTH_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    public static final VoxelShape EAST_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    public static final VoxelShape WEST_SHAPE = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public static final VoxelShape NORTH_PAPER_TOWEL = Stream.of(
            Block.box(1, 4, 10, 15, 16, 10),
            Block.box(1, 10, 10, 15, 16, 16),
            Block.box(0, 12, 12, 1, 14, 17),
            Block.box(15, 12, 12, 16, 14, 17)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_PAPER_TOWEL = Stream.of(
            Block.box(6, 4, 1, 6, 16, 15),
            Block.box(0, 10, 1, 6, 16, 15),
            Block.box(-1, 12, 0, 4, 14, 1),
            Block.box(-1, 12, 15, 4, 14, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_PAPER_TOWEL = Stream.of(
            Block.box(1, 4, 6, 15, 16, 6),
            Block.box(1, 10, 0, 15, 16, 6),
            Block.box(15, 12, -1, 16, 14, 4),
            Block.box(0, 12, -1, 1, 14, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_PAPER_TOWEL = Stream.of(
            Block.box(10, 4, 1, 10, 16, 15),
            Block.box(10, 10, 1, 16, 16, 15),
            Block.box(12, 12, 15, 17, 14, 16),
            Block.box(12, 12, 0, 17, 14, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTH_TOILET_PAPER = Stream.of(
            Block.box(5, 4, 10, 11, 16, 10),
            Block.box(5, 10, 10, 11, 16, 16),
            Block.box(4, 12, 12, 5, 14, 17),
            Block.box(11, 12, 12, 12, 14, 17)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_TOILET_PAPER = Stream.of(
            Block.box(6, 4, 5, 6, 16, 11),
            Block.box(0, 10, 5, 6, 16, 11),
            Block.box(-1, 12, 4, 4, 14, 5),
            Block.box(-1, 12, 11, 4, 14, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_TOILET_PAPER = Stream.of(
            Block.box(5, 4, 6, 11, 16, 6),
            Block.box(5, 10, 0, 11, 16, 6),
            Block.box(11, 12, -1, 12, 14, 4),
            Block.box(4, 12, -1, 5, 14, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_TOILET_PAPER = Stream.of(
            Block.box(10, 4, 5, 10, 16, 11),
            Block.box(10, 10, 5, 16, 16, 11),
            Block.box(12, 12, 11, 17, 14, 12),
            Block.box(12, 12, 4, 17, 14, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public HazardSignBlock(Properties p) {
        super(p.strength(1.25F,32.5F).noOcclusion().noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED,false));
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        Direction direction = state.getValue(FACING);
        if(state.is(TBlocks.PAPER_TOWEL.get())){
            switch(direction){
                case NORTH:
                    return NORTH_PAPER_TOWEL;
                case SOUTH:
                    return SOUTH_PAPER_TOWEL;
                case EAST:
                    return EAST_PAPER_TOWEL;
                case WEST:
                    return WEST_PAPER_TOWEL;
                default:
                    return Shapes.block();
            }
        }
        else if (state.is(TBlocks.TOILET_PAPER.get())){
            switch(direction){
                case NORTH:
                    return NORTH_TOILET_PAPER;
                case SOUTH:
                    return SOUTH_TOILET_PAPER;
                case EAST:
                    return EAST_TOILET_PAPER;
                case WEST:
                    return WEST_TOILET_PAPER;
                default:
                    return Shapes.block();
            }
        }
        else{
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
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
