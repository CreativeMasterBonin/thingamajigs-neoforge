package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.rk.thingamajigs.xtras.TProperties;

public class DirectionalConnectedSideBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<TProperties.ConnectedSide> CONNECTED_SIDE = TProperties.CONNECTED_SIDE;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final MapCodec<DirectionalConnectedSideBlock> DIRECTIONAL_CONNECTED_SIDE_BLOCK_MAP_CODEC =
            simpleCodec(DirectionalConnectedSideBlock::new);

    @Override
    protected MapCodec<? extends Block> codec() {
        return DIRECTIONAL_CONNECTED_SIDE_BLOCK_MAP_CODEC;
    }

    public DirectionalConnectedSideBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false).setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED));
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if(!level.isClientSide()){
            if(state.getValue(FACING) == Direction.NORTH){
                // both east and west
                if(level.getBlockState(pos.east()).getBlock() instanceof DirectionalConnectedSideBlock && level.getBlockState(pos.west()).getBlock() instanceof DirectionalConnectedSideBlock){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.east()).getBlock() instanceof DirectionalConnectedSideBlock) && level.getBlockState(pos.west()).getBlock() instanceof DirectionalConnectedSideBlock){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// east but not west
                else if(level.getBlockState(pos.east()).getBlock() instanceof DirectionalConnectedSideBlock && !(level.getBlockState(pos.west()).getBlock() instanceof DirectionalConnectedSideBlock)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.SOUTH){
                // both east and west
                if(level.getBlockState(pos.east()).getBlock() instanceof DirectionalConnectedSideBlock && level.getBlockState(pos.west()).getBlock() instanceof DirectionalConnectedSideBlock){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.east()).getBlock() instanceof DirectionalConnectedSideBlock) && level.getBlockState(pos.west()).getBlock() instanceof DirectionalConnectedSideBlock){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// east but not west
                else if(level.getBlockState(pos.east()).getBlock() instanceof DirectionalConnectedSideBlock && !(level.getBlockState(pos.west()).getBlock() instanceof DirectionalConnectedSideBlock)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// neither east nor west
                else {
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.EAST){
                // both east and west
                if(level.getBlockState(pos.north()).getBlock() instanceof DirectionalConnectedSideBlock && level.getBlockState(pos.south()).getBlock() instanceof DirectionalConnectedSideBlock){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.north()).getBlock() instanceof DirectionalConnectedSideBlock) && level.getBlockState(pos.south()).getBlock() instanceof DirectionalConnectedSideBlock){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// east but not west
                else if(level.getBlockState(pos.north()).getBlock() instanceof DirectionalConnectedSideBlock && !(level.getBlockState(pos.south()).getBlock() instanceof DirectionalConnectedSideBlock)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.WEST){
                // both east and west
                if(level.getBlockState(pos.north()).getBlock() instanceof DirectionalConnectedSideBlock && level.getBlockState(pos.south()).getBlock() instanceof DirectionalConnectedSideBlock){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.north()).getBlock() instanceof DirectionalConnectedSideBlock) && level.getBlockState(pos.south()).getBlock() instanceof DirectionalConnectedSideBlock){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// east but not west
                else if(level.getBlockState(pos.north()).getBlock() instanceof DirectionalConnectedSideBlock && !(level.getBlockState(pos.south()).getBlock() instanceof DirectionalConnectedSideBlock)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
        }
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING,WATERLOGGED,CONNECTED_SIDE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
