package net.rk.thingamajigs.block.custom;


import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class TennisNet extends ThingamajigsDecorativeBlock{
    // middle
    public static final VoxelShape MIDDLE_NORTH_SOUTH = Block.box(0, 2, 7.5, 16, 16, 8.5);
    public static final VoxelShape MIDDLE_EAST_WEST = Block.box(7.5, 2, 0, 8.5, 16, 16);
    // left (right is reverse order)
    public static final VoxelShape LEFT_NORTH = Shapes.join(Block.box(12, 0, 6, 16, 16, 10),
            Block.box(0, 2, 7.5, 12, 16, 8.5), BooleanOp.OR);
    public static final VoxelShape LEFT_EAST = Shapes.join(Block.box(6, 0, 12, 10, 16, 16),
            Block.box(7.5, 2, 0, 8.5, 16, 12), BooleanOp.OR);
    public static final VoxelShape LEFT_SOUTH = Shapes.join(Block.box(0, 0, 6, 4, 16, 10),
            Block.box(4, 2, 7.5, 16, 16, 8.5), BooleanOp.OR);
    public static final VoxelShape LEFT_WEST = Shapes.join(Block.box(6, 0, 0, 10, 16, 4),
            Block.box(7.5, 2, 4, 8.5, 16, 16), BooleanOp.OR);
    public static final EnumProperty<TennisNetPart> PART = EnumProperty.create("part", TennisNetPart.class);
    public static final BooleanProperty NET = BooleanProperty.create("net");

    public TennisNet(Properties properties){
        super(properties.pushReaction(PushReaction.DESTROY).sound(SoundType.CANDLE)
                .mapColor(MapColor.COLOR_GREEN).instabreak());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH)
                .setValue(NET,true)
                .setValue(PART,TennisNetPart.MIDDLE)
                .setValue(WATERLOGGED,false));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("block.thingamajigs.tennis_net.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PART,NET);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        Block similar = context.getLevel().getBlockState(context.getClickedPos().above()).getBlock();
        boolean notTheSame = similar != this;
        return this.defaultBlockState()
                .setValue(NET,notTheSame)
                .setValue(PART,TennisNetPart.MIDDLE)
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        if(state.getValue(NET) && state.getValue(PART) == TennisNetPart.MIDDLE){
            return super.getCollisionShape(state,getter,pos,context);
        }
        else{
            return Shapes.empty();
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor accessor, BlockPos pos, BlockPos newPos) {
        BlockState copyState = state;
        if(accessor.getBlockState(pos.above()).getBlock() == this){
            copyState.setValue(NET,false);
        }
        else{
            copyState.setValue(NET,true);
        }
        switch(state.getValue(FACING)){
            case NORTH -> {
                if(accessor.getBlockState(pos.east()).getBlock() == this && accessor.getBlockState(pos.west()).getBlock() != this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.RIGHT),3);
                }
                else if(accessor.getBlockState(pos.east()).getBlock() != this && accessor.getBlockState(pos.west()).getBlock() == this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.LEFT),3);
                }
                else if(accessor.getBlockState(pos.east()).getBlock() == this && accessor.getBlockState(pos.west()).getBlock() == this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.MIDDLE),3);
                }
                else{
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.MIDDLE),3);
                }
            }
            case SOUTH -> {
                if(accessor.getBlockState(pos.east()).getBlock() == this && accessor.getBlockState(pos.west()).getBlock() != this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.LEFT),3);
                }
                else if(accessor.getBlockState(pos.east()).getBlock() != this && accessor.getBlockState(pos.west()).getBlock() == this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.RIGHT),3);
                }
                else if(accessor.getBlockState(pos.east()).getBlock() == this && accessor.getBlockState(pos.west()).getBlock() == this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.MIDDLE),3);
                }
                else{
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.MIDDLE),3);
                }
            }
            case EAST -> {
                if(accessor.getBlockState(pos.north()).getBlock() == this && accessor.getBlockState(pos.south()).getBlock() != this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.LEFT),3);
                }
                else if(accessor.getBlockState(pos.north()).getBlock() != this && accessor.getBlockState(pos.south()).getBlock() == this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.RIGHT),3);
                }
                else if(accessor.getBlockState(pos.north()).getBlock() == this && accessor.getBlockState(pos.south()).getBlock() == this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.MIDDLE),3);
                }
                else{
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.MIDDLE),3);
                }
            }
            case WEST -> {
                if(accessor.getBlockState(pos.north()).getBlock() == this && accessor.getBlockState(pos.south()).getBlock() != this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.RIGHT),3);
                }
                else if(accessor.getBlockState(pos.north()).getBlock() != this && accessor.getBlockState(pos.south()).getBlock() == this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.LEFT),3);
                }
                else if(accessor.getBlockState(pos.north()).getBlock() == this && accessor.getBlockState(pos.south()).getBlock() == this){
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.MIDDLE),3);
                }
                else{
                    accessor.setBlock(pos,copyState.setValue(PART,TennisNetPart.MIDDLE),3);
                }
            }
        }
        if (state.getValue(WATERLOGGED)){
            accessor.scheduleTick(pos,Fluids.WATER,Fluids.WATER.getTickDelay(accessor));
        }
        return copyState;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        if(state.getValue(PART) == TennisNetPart.LEFT){
            switch(state.getValue(FACING)){
                case NORTH -> {
                    return LEFT_NORTH;
                }
                case SOUTH -> {
                    return LEFT_SOUTH;
                }
                case EAST -> {
                    return LEFT_EAST;
                }
                case WEST -> {
                    return LEFT_WEST;
                }
            }
        }
        else if(state.getValue(PART) == TennisNetPart.RIGHT){
            switch(state.getValue(FACING)){
                case NORTH -> {
                    return LEFT_SOUTH;
                }
                case SOUTH -> {
                    return LEFT_NORTH;
                }
                case EAST -> {
                    return LEFT_WEST;
                }
                case WEST -> {
                    return LEFT_EAST;
                }
            }
        }
        else if(state.getValue(PART) == TennisNetPart.MIDDLE){
            switch(state.getValue(FACING)){
                case NORTH,SOUTH -> {
                    return MIDDLE_NORTH_SOUTH;
                }
                case EAST,WEST -> {
                    return MIDDLE_EAST_WEST;
                }
            }
        }
        return Shapes.block();
    }

    public enum TennisNetPart implements StringRepresentable {
        LEFT("left"),
        MIDDLE("middle"),
        RIGHT("right");

        private String partName;

        TennisNetPart(String name){
            this.partName = name;
        }

        @Override
        public String toString() {
            return this.partName;
        }

        @Override
        public String getSerializedName() {
            return this.partName;
        }
    }
}
