package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.logging.Logger;

@SuppressWarnings("deprecated")
public class BowlingPin extends Block implements SimpleWaterloggedBlock{
    public static final MapCodec<BowlingPin> BOWLING_PIN_MAP_CODEC = Block.simpleCodec(BowlingPin::new);
    @Override
    public MapCodec<BowlingPin> codec(){return BOWLING_PIN_MAP_CODEC;}

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public BowlingPin(Properties p) {
        super(p.strength(0.8F,1.2F).sound(SoundType.BONE_BLOCK).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED,false));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    }

    @Override
    public void entityInside(BlockState bs, Level lvl, BlockPos bp, Entity e) {
        if(e instanceof ItemEntity){
            lvl.playSound(null,bp, SoundEvents.DECORATED_POT_HIT, SoundSource.BLOCKS,2.0f,1.0f);
            lvl.setBlock(bp, Blocks.AIR.defaultBlockState(),3);
            double xd = e.getDeltaMovement().x;
            double yd = e.getDeltaMovement().y;
            double zd = e.getDeltaMovement().z;
            try{
                ItemEntity nEe1 = new ItemEntity(lvl,bp.getX(),bp.getY(),bp.getZ(),new ItemStack(this.asItem()),xd,yd,zd);
                lvl.addFreshEntity(nEe1);
                lvl.updateNeighborsAt(bp,this);
            }
            catch(Exception ne1){
                Logger.getAnonymousLogger().warning(ne1.getMessage());
            }
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
