package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class Easel extends ThingamajigsDecorativeBlock {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    public static final VoxelShape NORTH_TOP = Stream.of(
            Block.box(7, 0, 11, 9, 8, 13),
            Block.box(7, 8, 11, 9, 19, 13),
            Block.box(2, 19, 7, 14, 21, 9),
            Block.box(7, 19, 9, 9, 21, 16),
            Block.box(4, 13, 9, 12, 16, 11),
            Block.box(0, 0, 1, 16, 2, 3),
            Block.box(0, 2, 2, 16, 4, 4),
            Block.box(0, 4, 3, 16, 6, 5),
            Block.box(0, 6, 4, 16, 8, 6),
            Block.box(0, 8, 5, 16, 10, 7),
            Block.box(0, 10, 6, 16, 12, 8),
            Block.box(0, 12, 7, 16, 16, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_TOP = Stream.of(
            Block.box(3, 0, 7, 5, 8, 9),
            Block.box(3, 8, 7, 5, 19, 9),
            Block.box(7, 19, 2, 9, 21, 14),
            Block.box(0, 19, 7, 7, 21, 9),
            Block.box(5, 13, 4, 7, 16, 12),
            Block.box(13, 0, 0, 15, 2, 16),
            Block.box(12, 2, 0, 14, 4, 16),
            Block.box(11, 4, 0, 13, 6, 16),
            Block.box(10, 6, 0, 12, 8, 16),
            Block.box(9, 8, 0, 11, 10, 16),
            Block.box(8, 10, 0, 10, 12, 16),
            Block.box(7, 12, 0, 9, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_TOP = Stream.of(
            Block.box(7, 0, 3, 9, 8, 5),
            Block.box(7, 8, 3, 9, 19, 5),
            Block.box(2, 19, 7, 14, 21, 9),
            Block.box(7, 19, 0, 9, 21, 7),
            Block.box(4, 13, 5, 12, 16, 7),
            Block.box(0, 0, 13, 16, 2, 15),
            Block.box(0, 2, 12, 16, 4, 14),
            Block.box(0, 4, 11, 16, 6, 13),
            Block.box(0, 6, 10, 16, 8, 12),
            Block.box(0, 8, 9, 16, 10, 11),
            Block.box(0, 10, 8, 16, 12, 10),
            Block.box(0, 12, 7, 16, 16, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_TOP = Stream.of(
            Block.box(11, 0, 7, 13, 8, 9),
            Block.box(11, 8, 7, 13, 19, 9),
            Block.box(7, 19, 2, 9, 21, 14),
            Block.box(9, 19, 7, 16, 21, 9),
            Block.box(9, 13, 4, 11, 16, 12),
            Block.box(1, 0, 0, 3, 2, 16),
            Block.box(2, 2, 0, 4, 4, 16),
            Block.box(3, 4, 0, 5, 6, 16),
            Block.box(4, 6, 0, 6, 8, 16),
            Block.box(5, 8, 0, 7, 10, 16),
            Block.box(6, 10, 0, 8, 12, 16),
            Block.box(7, 12, 0, 9, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_BOTTOM = Stream.of(
            Block.box(0, 0, 0, 1, 16, 2),
            Block.box(7, 0, 14, 9, 16, 16),
            Block.box(15, 0, 0, 16, 16, 2),
            Block.box(0, 14, 2, 16, 16, 4),
            Block.box(7, 14, 4, 9, 16, 14),
            Block.box(2, 14, -3, 14, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST_BOTTOM = Stream.of(
            Block.box(14, 0, 0, 16, 16, 1),
            Block.box(0, 0, 7, 2, 16, 9),
            Block.box(14, 0, 15, 16, 16, 16),
            Block.box(12, 14, 0, 14, 16, 16),
            Block.box(2, 14, 7, 12, 16, 9),
            Block.box(14, 14, 2, 19, 16, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH_BOTTOM = Stream.of(
            Block.box(15, 0, 14, 16, 16, 16),
            Block.box(7, 0, 0, 9, 16, 2),
            Block.box(0, 0, 14, 1, 16, 16),
            Block.box(0, 14, 12, 16, 16, 14),
            Block.box(7, 14, 2, 9, 16, 12),
            Block.box(2, 14, 14, 14, 16, 19)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST_BOTTOM = Stream.of(
            Block.box(0, 0, 15, 2, 16, 16),
            Block.box(14, 0, 7, 16, 16, 9),
            Block.box(0, 0, 0, 2, 16, 1),
            Block.box(2, 14, 0, 4, 16, 16),
            Block.box(4, 14, 7, 14, 16, 9),
            Block.box(-3, 14, 2, 2, 16, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public Easel(Properties properties) {
        super(properties.sound(SoundType.WOOD).strength(1f,0.5f).mapColor(MapColor.TERRACOTTA_WHITE));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(WATERLOGGED,false).setValue(HALF,DoubleBlockHalf.LOWER));
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(!level.isClientSide()){
            if(player.isSecondaryUseActive()){
                float randomPitch = 0.97f + level.getRandom().nextFloat() * 1.1f;
                level.playSound(player,pos, SoundEvents.VILLAGER_WORK_CARTOGRAPHER, SoundSource.BLOCKS,1.0f,randomPitch);
                return InteractionResult.CONSUME;
            }
            else{
                return InteractionResult.PASS;
            }
        }
        else{
            if(player.isSecondaryUseActive()){
                float randomPitchClient = 0.97f + level.getRandom().nextFloat() * 1.1f;
                player.playSound(SoundEvents.VILLAGER_WORK_CARTOGRAPHER,1.0f,randomPitchClient);
            }
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        level.setBlock(pos.above(),state.setValue(HALF,DoubleBlockHalf.UPPER),3);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HALF);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

        if (blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection())
                    .setValue(HALF, DoubleBlockHalf.LOWER)
                    .setValue(WATERLOGGED,fluidstate.getType() == Fluids.WATER);
        }
        else {
            return null;
        }
    }

    public static void preventBottomDrops(Level level, BlockPos pos, BlockState state, Player player){
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockstate1 = blockstate.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(blockpos, blockstate1, 35);
                level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }
    }

    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && (player.isCreative() || !player.hasCorrectToolForDrops(state, level, pos))) {
            preventBottomDrops(level,pos,state,player);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (facing.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (facing == Direction.UP)) {
            return facingState.getBlock() instanceof Easel && facingState.getValue(HALF) != doubleblockhalf ? facingState.setValue(HALF, doubleblockhalf) : Blocks.AIR.defaultBlockState();
        }
        else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)){
            case NORTH -> {
                return state.getValue(HALF) == DoubleBlockHalf.LOWER ? SOUTH_BOTTOM : SOUTH_TOP;
            }
            case SOUTH -> {
                return state.getValue(HALF) == DoubleBlockHalf.LOWER ? NORTH_BOTTOM : NORTH_TOP;
            }
            case EAST -> {
                return state.getValue(HALF) == DoubleBlockHalf.LOWER ? WEST_BOTTOM : WEST_TOP;
            }
            case WEST -> {
                return state.getValue(HALF) == DoubleBlockHalf.LOWER ? EAST_BOTTOM : EAST_TOP;
            }
            default -> {
                return Shapes.block();
            }
        }
    }
}
