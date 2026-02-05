package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
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

import java.util.stream.Stream;

public class SmallSink extends ToggledStateBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static BooleanProperty TOGGLED = BlockStateProperties.ENABLED;

    // custom sink shapes
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(6, 0, 6, 10, 12, 10),
            Block.box(2, 12, 2, 14, 13, 14),
            Block.box(0, 13, 0, 2, 16, 16),
            Block.box(14, 13, 0, 16, 16, 16),
            Block.box(2, 13, 0, 14, 16, 2),
            Block.box(2, 13, 14, 14, 16, 16),
            Block.box(7, 16, 14, 9, 20, 16),
            Block.box(11, 16, 14, 13, 17, 16),
            Block.box(3, 16, 14, 5, 17, 16),
            Block.box(7, 19, 7, 9, 20, 14),
            Block.box(7, 13.01, 7, 9, 13.01, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(6, 0, 6, 10, 12, 10),
            Block.box(2, 12, 2, 14, 13, 14),
            Block.box(14, 13, 0, 16, 16, 16),
            Block.box(0, 13, 0, 2, 16, 16),
            Block.box(2, 13, 14, 14, 16, 16),
            Block.box(2, 13, 0, 14, 16, 2),
            Block.box(7, 16, 0, 9, 20, 2),
            Block.box(3, 16, 0, 5, 17, 2),
            Block.box(11, 16, 0, 13, 17, 2),
            Block.box(7, 19, 2, 9, 20, 9),
            Block.box(7, 13.01, 7, 9, 13.01, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();;
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(6, 0, 6, 10, 12, 10),
            Block.box(2, 12, 2, 14, 13, 14),
            Block.box(0, 13, 14, 16, 16, 16),
            Block.box(0, 13, 0, 16, 16, 2),
            Block.box(0, 13, 2, 2, 16, 14),
            Block.box(14, 13, 2, 16, 16, 14),
            Block.box(14, 16, 7, 16, 20, 9),
            Block.box(14, 16, 3, 16, 17, 5),
            Block.box(14, 16, 11, 16, 17, 13),
            Block.box(7, 19, 7, 14, 20, 9),
            Block.box(7, 13.01, 7, 9, 13.01, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(6, 0, 6, 10, 12, 10),
            Block.box(2, 12, 2, 14, 13, 14),
            Block.box(0, 13, 0, 16, 16, 2),
            Block.box(0, 13, 14, 16, 16, 16),
            Block.box(14, 13, 2, 16, 16, 14),
            Block.box(0, 13, 2, 2, 16, 14),
            Block.box(0, 16, 7, 2, 20, 9),
            Block.box(0, 16, 11, 2, 17, 13),
            Block.box(0, 16, 3, 2, 17, 5),
            Block.box(2, 19, 7, 9, 20, 9),
            Block.box(7, 13.01, 7, 9, 13.01, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public SmallSink(Properties p) {
        super(p.sound(SoundType.STONE).strength(1F,10F).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TOGGLED, false).setValue(WATERLOGGED, false));
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_SHAPE;
            case SOUTH: return SOUTH_SHAPE;
            case EAST: return EAST_SHAPE;
            case WEST: return WEST_SHAPE;
            default: return Shapes.block();
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if (ply.isShiftKeyDown()){
            if (lvl.isClientSide) {
                BlockState blockstate1 = bs.cycle(TOGGLED);
                lvl.playLocalSound(bp, SoundEvents.BUCKET_FILL, SoundSource.PLAYERS,0.5F,1.0F,false);
                return InteractionResult.SUCCESS;
            }
            else {
                BlockState blockstate = this.pull(bs,lvl,bp);
                return InteractionResult.CONSUME;
            }
        }
        else{
            return InteractionResult.CONSUME;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,TOGGLED,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(TOGGLED,false).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
