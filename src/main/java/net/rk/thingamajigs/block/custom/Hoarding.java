package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

@SuppressWarnings("deprecated")
public class Hoarding extends Block implements SimpleWaterloggedBlock{
    public static final VoxelShape NS_SHAPE = Block.box(0, 0, 7, 16, 16, 9);
    public static final VoxelShape EW_SHAPE = Block.box(7, 0, 0, 9, 16, 16);
    public static final IntegerProperty TYPE = IntegerProperty.create("type",0,3);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape NORTH_SHAPE = Block.box(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
    public static final VoxelShape SOUTH_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
    public static final VoxelShape EAST_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D);
    public static final VoxelShape WEST_SHAPE = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);


    public Hoarding(Properties properties) {
        super(properties.strength(1f,15f).sound(SoundType.LANTERN).mapColor(MapColor.METAL).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, 3).setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getInteractionShape(BlockState bs, BlockGetter bg, BlockPos bp) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH:
                return SOUTH_SHAPE;
            case SOUTH:
                return NORTH_SHAPE;
            case EAST:
                return WEST_SHAPE;
            case WEST:
                return EAST_SHAPE;
            default: return Shapes.block();
        }
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        if(cc.isHoldingItem(
                TBlocks.STEEL_HOARDING.get().asItem())){
            return Shapes.block();
        }
        else{
            return super.getVisualShape(bs,bg,bp,cc);
        }
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    public void neighborChanged(BlockState state, Level lvl, BlockPos bp, Block block, BlockPos pos, boolean bl) {
        // if a pillar is on top
        if(lvl.getBlockState(bp.above()).is(state.getBlock())){
            // if a pillar is on top and below
            if(lvl.getBlockState(bp.below()).is(state.getBlock())){
                // set to middle
                lvl.setBlock(bp,state.setValue(TYPE,1),3);
            }
            // if a pillar is on top and not bottom
            else{
                // set to bottom
                lvl.setBlock(bp,state.setValue(TYPE,0),3);
            }
        }
        // if not a pillar is on top
        else{
            // if not a pillar on top but on bottom
            if(lvl.getBlockState(bp.below()).is(state.getBlock())){
                // set to top
                lvl.setBlock(bp,state.setValue(TYPE,2),3);
            }
            // if neither pillar on top nor bottom
            else{
                // set to freestanding pillar
                lvl.setBlock(bp,state.setValue(TYPE,3),3);
            }
        }
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE,FACING,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(TYPE, 3)
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
