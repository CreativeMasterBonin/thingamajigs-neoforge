package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.xtras.TSoundEvent;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class Payphone extends ThingamajigsDecorativeBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    // fancy block hit-box
    public static final VoxelShape BASE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    public static final VoxelShape TOP_COVER = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public static final VoxelShape COMMON = Shapes.join(BASE,TOP_COVER, BooleanOp.OR);

    public static final VoxelShape NORTH_SHAPE = Stream.of(Block.box(0, 31, -2, 16, 32, 16), Block.box(0, 0, 0, 1, 31, 16), Block.box(1, 0, 15, 15, 31, 16), Block.box(15, 0, 0, 16, 31, 16), Block.box(1, 0.02, 0, 15, 0.02, 15), Block.box(0, 0, 0, 16, 0.25, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(Block.box(0, 0, 0, 16, 31, 1), Block.box(0, 0, 1, 1, 31, 15), Block.box(0, 0, 15, 16, 31, 16), Block.box(1, 0.02, 1, 16, 0.02, 15), Block.box(0, 31, 0, 18, 32, 16), Block.box(0, 0, 0, 16, 0.25, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(Block.box(14.75, 0, 0.25, 15.75, 31, 16.25), Block.box(0.75, 0, 0.25, 14.75, 31, 1.25), Block.box(-0.25, 0, 0.25, 0.75, 31, 16.25), Block.box(0.75, 0.02, 1.25, 14.75, 0.02, 16.25), Block.box(-0.25, 31, 0.25, 15.75, 32, 18.25), Block.box(0, 0, 0, 16, 0.25, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(Block.box(-0.5, 0, 15, 15.5, 31, 16), Block.box(14.5, 0, 1, 15.5, 31, 15), Block.box(-0.5, 0, 0, 15.5, 31, 1), Block.box(-0.5, 0.02, 1, 14.5, 0.02, 15), Block.box(-2.5, 31, 0, 15.5, 32, 16), Block.box(0, 0, 0, 16, 0.25, 16)).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Payphone(Properties p) {
        super(p);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if(ply.isShiftKeyDown() && ply.getItemInHand(ply.getUsedItemHand()).isEmpty()){
            RandomSource rs = lvl.getRandom();
            double f = rs.nextDouble();
            double fChange = Math.floor(f * 100) / 100;

            if(fChange == 0.01D){
                ply.playSound(TSoundEvent.PHONE_NO_SERVICE.get(),0.5f,1.0f);
            }
            else if(fChange == 0.05D){
                ply.playSound(TSoundEvent.PHONE_SONG_MAYBE.get(),0.5f,1.0f);
            }
            else if(fChange == 0.31D){
                ply.playSound(TSoundEvent.PHONE_NOT_A_NUMBER.get(),0.5f,1.0f);
            }
            else if(fChange == 0.42D){
                ply.playSound(TSoundEvent.PHONE_BIRDS_PERHAPS.get(),0.5f,1.0f);
            }
            return InteractionResult.SUCCESS;
        }
        else{
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_SHAPE;
            case SOUTH: return SOUTH_SHAPE;
            case EAST: return EAST_SHAPE;
            case WEST: return WEST_SHAPE;
            default: return COMMON;
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
