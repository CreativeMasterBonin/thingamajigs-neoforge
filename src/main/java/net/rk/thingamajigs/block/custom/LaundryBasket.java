package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class LaundryBasket extends Block implements SimpleWaterloggedBlock{
    public static final VoxelShape ALL = Stream.of(
            Block.box(1, 0, 1, 15, 0.25, 15),
            Block.box(1, 15, 1, 15, 15.25, 2),
            Block.box(1, 15, 14, 15, 15.25, 15),
            Block.box(1, 15, 2, 2, 15.25, 14),
            Block.box(14, 15, 2, 15, 15.25, 14),
            Block.box(1, 0.25, 1, 15, 15, 2),
            Block.box(1, 0.25, 14, 15, 15, 15),
            Block.box(1, 0.25, 2, 2, 15, 14),
            Block.box(14, 0.25, 2, 15, 15, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape ALL_FULL = Stream.of(
            Block.box(1, 0, 1, 15, 7, 15),
            Block.box(1, 0, 1, 15, 0.25, 15),
            Block.box(1, 15, 1, 15, 15.25, 2),
            Block.box(1, 15, 14, 15, 15.25, 15),
            Block.box(1, 15, 2, 2, 15.25, 14),
            Block.box(14, 15, 2, 15, 15.25, 14),
            Block.box(1, 0.25, 1, 15, 15, 2),
            Block.box(1, 0.25, 14, 15, 15, 15),
            Block.box(1, 0.25, 2, 2, 15, 14),
            Block.box(14, 0.25, 2, 15, 15, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static BooleanProperty TOGGLED = BlockStateProperties.ENABLED;

    public LaundryBasket(Properties p) {
        super(p.strength(1f,2f).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.WOOD).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(TOGGLED, false).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        if(bs.getValue(TOGGLED)){
            return ALL_FULL;
        }
        return ALL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if (ply.isShiftKeyDown()){
            if (lvl.isClientSide) {
                BlockState blockstate1 = bs.cycle(TOGGLED);
                return InteractionResult.SUCCESS;
            } else {
                BlockState blockstate = this.pull(bs, lvl, bp);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    public BlockState pull(BlockState pState, Level pLevel, BlockPos pPos) {
        pState = pState.cycle(TOGGLED);
        pLevel.setBlock(pPos, pState, 3);
        this.updateNeighbours(pState, pLevel, pPos);
        return pState;
    }

    private void updateNeighbours(BlockState pState, Level pLevel, BlockPos pPos) {
        pLevel.updateNeighborsAt(pPos, this);
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
        builder.add(TOGGLED,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(TOGGLED,false)
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
