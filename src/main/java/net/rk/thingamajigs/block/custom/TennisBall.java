package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.xtras.TProperties;

import javax.annotation.Nullable;

public class TennisBall extends DecorativeSportBall{
    public static final IntegerProperty AMOUNT = TProperties.AMOUNT_FOUR;

    public TennisBall(Properties properties){
        super(properties.instrument(NoteBlockInstrument.BANJO).mapColor(MapColor.COLOR_LIGHT_GREEN));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(AMOUNT,1)
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        switch(state.getValue(AMOUNT)){
            case 1: {
                return DecorativeSportBall.SINGLE_TENNIS_BALL;
            }
            case 2,3: {
                return DecorativeSportBall.DOUBLE_TENNIS_BALL_TRIPLE_TENNIS_BALL;
            }
            case 4: {
                return DecorativeSportBall.QUADRUPLE_TENNIS_BALL;
            }
            default: {
                return Shapes.block();
            }
        }
    }


    @Override
    public boolean useShapeForLightOcclusion(BlockState state){return true;}
    @Override
    public float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return state.getValue(AMOUNT) == 4 ? 0.75f : 0.95f;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AMOUNT);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if(blockstate.is(this)) {
            int i = blockstate.getValue(AMOUNT);
            return blockstate.setValue(AMOUNT, Math.min(4, i + 1)).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        }
        else {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
                    .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
        }
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel serverLevel, BlockPos pos, ItemStack stack, boolean bool1) {
        for(int item = 0; item < state.getValue(AMOUNT); item++){
            serverLevel.addFreshEntity(new ItemEntity(serverLevel,pos.getX(),pos.getY() + 0.2D,pos.getZ(),new ItemStack(this.asItem())));
        }
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        if(context.getItemInHand().is(this.asItem()) && state.getValue(AMOUNT) < 4){
            if(context.replacingClickedOnBlock()){
                return context.getClickedFace() == Direction.UP || context.getClickedFace() == Direction.DOWN || context.getClickedFace() == Direction.NORTH || context.getClickedFace() == Direction.EAST || context.getClickedFace() == Direction.WEST || context.getClickedFace() == Direction.SOUTH;
            }
            else{
                return true;
            }
        }
        else{
            return state.getValue(AMOUNT) == 1;
        }
    }
}
