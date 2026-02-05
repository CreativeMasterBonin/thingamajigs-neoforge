package net.rk.thingamajigs.block.custom;

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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

import static net.minecraft.core.Direction.*;

@SuppressWarnings("deprecated")
public class StopGate extends RedstoneLampBlock implements SimpleWaterloggedBlock {
    public static final VoxelShape NORTHSOUTH_ON = Block.box(-16, 0, 0, 32, 12, 16);
    public static final VoxelShape EASTWEST_ON = Block.box(0, 0, -16, 16, 12, 32);

    public static final VoxelShape NORTH_OFF = Stream.of(
            Block.box(-16, 0, 0, 0, 12, 16),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH_OFF = Stream.of(
            Block.box(16, 0, 0, 32, 12, 16),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST_OFF = Stream.of(
            Block.box(0, 0, -16, 16, 12, 0),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST_OFF = Stream.of(
            Block.box(0, 0, 16, 16, 12, 32),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public StopGate(Properties p) {
        super(p.strength(1F,5F).sound(SoundType.LANTERN).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, NORTH).setValue(LIT, false).setValue(WATERLOGGED,false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        if(direction == NORTH){
            if(state.getValue(LIT)){
                return NORTHSOUTH_ON;
            }
            else{
                return NORTH_OFF;
            }
        }
        else if(direction == SOUTH){
            if(state.getValue(LIT)){
                return NORTHSOUTH_ON;
            }
            else{
                return SOUTH_OFF;
            }
        }
        else if(direction == EAST){
            if(state.getValue(LIT)){
                return EASTWEST_ON;
            }
            else{
                return EAST_OFF;
            }
        }
        else if(direction == WEST){
            if(state.getValue(LIT)){
                return EASTWEST_ON;
            }
            else{
                return WEST_OFF;
            }
        }
        return NORTH_OFF;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder){
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING,WATERLOGGED);
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
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(LIT, false).setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
