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

public class FancySink extends ToggledStateBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static BooleanProperty TOGGLED = BlockStateProperties.ENABLED;

    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 0, 0, 16, 1, 32),
            Block.box(0, 1, 28, 16, 3, 32),
            Block.box(0, 1, 1, 1, 3, 28),
            Block.box(15, 1, 1, 16, 3, 28),
            Block.box(0, 1, 0, 16, 3, 1),
            Block.box(12, 3, 29, 14, 4, 31),
            Block.box(2, 3, 29, 4, 4, 31),
            Block.box(7, 3, 29, 9, 11, 31),
            Block.box(7, 11, 21, 9, 13, 31),
            Block.box(7, 9, 21, 9, 11, 23)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(0, 0, -16, 16, 1, 16),
            Block.box(0, 1, -16, 16, 3, -12),
            Block.box(15, 1, -12, 16, 3, 15),
            Block.box(0, 1, -12, 1, 3, 15),
            Block.box(0, 1, 15, 16, 3, 16),
            Block.box(2, 3, -15, 4, 4, -13),
            Block.box(12, 3, -15, 14, 4, -13),
            Block.box(7, 3, -15, 9, 11, -13),
            Block.box(7, 11, -15, 9, 13, -5),
            Block.box(7, 9, -7, 9, 11, -5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(-16, 0, 0, 16, 1, 16),
            Block.box(-16, 1, 0, -12, 3, 16),
            Block.box(-12, 1, 0, 15, 3, 1),
            Block.box(-12, 1, 15, 15, 3, 16),
            Block.box(15, 1, 0, 16, 3, 16),
            Block.box(-15, 3, 12, -13, 4, 14),
            Block.box(-15, 3, 2, -13, 4, 4),
            Block.box(-15, 3, 7, -13, 11, 9),
            Block.box(-15, 11, 7, -5, 13, 9),
            Block.box(-7, 9, 7, -5, 11, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(0, 0, 0, 32, 1, 16),
            Block.box(28, 1, 0, 32, 3, 16),
            Block.box(1, 1, 15, 28, 3, 16),
            Block.box(1, 1, 0, 28, 3, 1),
            Block.box(0, 1, 0, 1, 3, 16),
            Block.box(29, 3, 2, 31, 4, 4),
            Block.box(29, 3, 12, 31, 4, 14),
            Block.box(29, 3, 7, 31, 11, 9),
            Block.box(21, 11, 7, 31, 13, 9),
            Block.box(21, 9, 7, 23, 11, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public FancySink(Properties properties) {
        super(properties.sound(SoundType.STONE).strength(1F,10F).noOcclusion());
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
                lvl.playLocalSound(bp,SoundEvents.BUCKET_FILL,SoundSource.PLAYERS,0.5F,1.0F,false);
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
