package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class ArrowBoard extends Block implements SimpleWaterloggedBlock{
    public static final MapCodec<ArrowBoard> ARROW_BOARD_CODEC = Block.simpleCodec(ArrowBoard::new);

    @Override
    protected MapCodec<? extends Block> codec() {
        return ARROW_BOARD_CODEC;
    }

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty MODE = IntegerProperty.create("mode",0, 5);
    // 0 = off, 1 = corners, 2 = arrow_left, 3 = arrow_right, 4 = arrow_both, 5 = flashing_diamond

    public static final VoxelShape NORTH = Stream.of(
            Block.box(-2, 5, 0, 18, 7, 16),
            Block.box(-3, 0, 0, -2, 5, 5),
            Block.box(-3, 0, 11, -2, 5, 16),
            Block.box(18, 0, 11, 19, 5, 16),
            Block.box(18, 0, 0, 19, 5, 5),
            Block.box(1, 7, 2, 15, 13, 14),
            Block.box(7, 13, 10, 9, 32, 12),
            Block.box(-5, 18, 0, 21, 32, 2),
            Block.box(2, 8, 1, 3, 12, 2),
            Block.box(13, 8, 1, 14, 12, 2),
            Block.box(-2, 2, 2, 18, 3, 3),
            Block.box(-2, 2, 13, 18, 3, 14),
            Block.box(7, 3, 1, 9, 5, 15),
            Block.box(7, 19, 2, 9, 30, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 5, -2, 16, 7, 18),
            Block.box(11, 0, -3, 16, 5, -2),
            Block.box(0, 0, -3, 5, 5, -2),
            Block.box(0, 0, 18, 5, 5, 19),
            Block.box(11, 0, 18, 16, 5, 19),
            Block.box(2, 7, 1, 14, 13, 15),
            Block.box(4, 13, 7, 6, 32, 9),
            Block.box(14, 18, -5, 16, 32, 21),
            Block.box(14, 8, 2, 15, 12, 3),
            Block.box(14, 8, 13, 15, 12, 14),
            Block.box(13, 2, -2, 14, 3, 18),
            Block.box(2, 2, -2, 3, 3, 18),
            Block.box(1, 3, 7, 15, 5, 9),
            Block.box(6, 19, 7, 14, 30, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(-2, 5, 0, 18, 7, 16),
            Block.box(18, 0, 11, 19, 5, 16),
            Block.box(18, 0, 0, 19, 5, 5),
            Block.box(-3, 0, 0, -2, 5, 5),
            Block.box(-3, 0, 11, -2, 5, 16),
            Block.box(1, 7, 2, 15, 13, 14),
            Block.box(7, 13, 4, 9, 32, 6),
            Block.box(-5, 18, 14, 21, 32, 16),
            Block.box(13, 8, 14, 14, 12, 15),
            Block.box(2, 8, 14, 3, 12, 15),
            Block.box(-2, 2, 13, 18, 3, 14),
            Block.box(-2, 2, 2, 18, 3, 3),
            Block.box(7, 3, 1, 9, 5, 15),
            Block.box(7, 19, 6, 9, 30, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 5, -2, 16, 7, 18),
            Block.box(0, 0, 18, 5, 5, 19),
            Block.box(11, 0, 18, 16, 5, 19),
            Block.box(11, 0, -3, 16, 5, -2),
            Block.box(0, 0, -3, 5, 5, -2),
            Block.box(2, 7, 1, 14, 13, 15),
            Block.box(10, 13, 7, 12, 32, 9),
            Block.box(0, 18, -5, 2, 32, 21),
            Block.box(1, 8, 13, 2, 12, 14),
            Block.box(1, 8, 2, 2, 12, 3),
            Block.box(2, 2, -2, 3, 3, 18),
            Block.box(13, 2, -2, 14, 3, 18),
            Block.box(1, 3, 7, 15, 5, 9),
            Block.box(2, 19, 7, 10, 30, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape ALL = Block.box(0,0,0,16,32,16);

    public ArrowBoard(Properties p) {
        super(p.strength(1F,5F).sound(SoundType.LANTERN).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(MODE,0).setValue(WATERLOGGED,false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return ALL;}
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player p, BlockHitResult p_60508_) {
        if(p.isShiftKeyDown()){
            if(!lvl.isClientSide()){
                lvl.setBlock(bp,bs.cycle(MODE),2);
                lvl.playSound(null,bp, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS,1.0F,1.0F);
                return InteractionResult.SUCCESS;
            }
        }
        else{
            return InteractionResult.PASS;
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING,MODE,WATERLOGGED);
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
        return this.defaultBlockState().setValue(MODE,0).setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
        list.add(Component.translatable("block.arrow_board.desc"));
    }
}
