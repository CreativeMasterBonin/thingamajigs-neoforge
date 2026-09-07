package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
import net.minecraft.world.ticks.TickPriority;
import net.neoforged.neoforge.common.Tags;
import net.rk.thingamajigs.blockentity.TBlockEntity;
import net.rk.thingamajigs.blockentity.custom.StopGateBE;
import net.rk.thingamajigs.xtras.TCalcStuff;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

import static net.minecraft.core.Direction.*;

@SuppressWarnings("deprecated")
public class StopGate extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<StopGate> CODEC = simpleCodec(StopGate::new);
    public static final VoxelShape NORTHSOUTH_ON = Block.box(-16, 0, 0, 32, 12, 16);
    public static final VoxelShape EASTWEST_ON = Block.box(0, 0, -16, 16, 12, 32);

    public static final VoxelShape NORTH_OFF = Stream.of(
            Block.box(-16, 0, 0, 0, 12, 16),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH_OFF = Stream.of(
            Block.box(16, 0, 0, 32, 12, 16),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST_OFF = Stream.of(
            Block.box(0, 0, -16, 16, 12, 0),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST_OFF = Stream.of(
            Block.box(0, 0, 16, 16, 12, 32),
            Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(4, 2, 8, 12, 12, 16),
            Block.box(7, 6, 6, 9, 8, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(0, 2, 4, 8, 12, 12),
            Block.box(8, 6, 7, 10, 8, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(4, 2, 0, 12, 12, 8),
            Block.box(7, 6, 8, 9, 8, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(0, 0, 0, 16, 2, 16),
            Block.box(8, 2, 4, 16, 12, 12),
            Block.box(6, 6, 7, 8, 8, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public StopGate(Properties p) {
        super(p.strength(1F,5F).sound(SoundType.LANTERN).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING,NORTH).setValue(LIT, false));
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("block.thingamajigs.stop_gate.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH_SHAPE;}
            case SOUTH->{return SOUTH_SHAPE;}
            case EAST->{return EAST_SHAPE;}
            case WEST->{return WEST_SHAPE;}
            default->{return Shapes.block();}
        }
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING,LIT,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(LIT,context.getLevel().hasNeighborSignal(context.getClickedPos()))
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED,fluidstate.getType() == Fluids.WATER);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if(!level.isClientSide){
            boolean lit = state.getValue(LIT);
            if(lit != level.hasNeighborSignal(pos)){
                if(lit){
                    level.scheduleTick(pos,this,8, TickPriority.EXTREMELY_LOW); // longer tick time than traditional redstone
                } else{
                    level.setBlock(pos,state.cycle(LIT),2);
                }
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel serverLevel, BlockPos pos, RandomSource random) {
        if(state.getValue(LIT) && !serverLevel.hasNeighborSignal(pos)){
            serverLevel.setBlock(pos,state.cycle(LIT),2);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new StopGateBE(blockPos,blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, TBlockEntity.STOP_GATE_BE.get(),
                level.isClientSide() ? null : StopGateBE::serverTick);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide()){
            if(player.getItemInHand(hand).is(ItemTags.AXES) || player.getItemInHand(hand).is(Tags.Items.TOOLS_SHEAR)){
                player.playSound(SoundEvents.IRON_GOLEM_REPAIR,0.5f, TCalcStuff.nextFloatBetweenInclusive(0.94f,1.0f));
                return ItemInteractionResult.SUCCESS;
            }
        }
        else{
            if(player.getItemInHand(hand).is(ItemTags.AXES) || player.getItemInHand(hand).is(Tags.Items.TOOLS_SHEAR)) {
                StopGateBE stopGate = (StopGateBE) level.getBlockEntity(pos);
                if (stopGate instanceof StopGateBE) {
                    stopGate.inverse = !stopGate.inverse;
                    stopGate.updateBlock();
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
