package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.blockentity.TBlockEntity;
import net.rk.thingamajigs.blockentity.custom.AnimatedDeerBE;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class AnimatedDeer extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<AnimatedDeer> CODEC = simpleCodec(AnimatedDeer::new);
    public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(4, 10, 0, 12, 17, 16),
            Block.box(4, 0, 0, 6, 10, 2),
            Block.box(4, 0, 14, 6, 10, 16),
            Block.box(10, 0, 14, 12, 10, 16),
            Block.box(10, 0, 0, 12, 10, 2),
            Block.box(6, 8, 16, 10, 15, 20)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(0, 10, 4, 16, 17, 12),
            Block.box(14, 0, 4, 16, 10, 6),
            Block.box(0, 0, 4, 2, 10, 6),
            Block.box(0, 0, 10, 2, 10, 12),
            Block.box(14, 0, 10, 16, 10, 12),
            Block.box(-4, 8, 6, 0, 15, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(4, 10, 0, 12, 17, 16),
            Block.box(10, 0, 14, 12, 10, 16),
            Block.box(10, 0, 0, 12, 10, 2),
            Block.box(4, 0, 0, 6, 10, 2),
            Block.box(4, 0, 14, 6, 10, 16),
            Block.box(6, 8, -4, 10, 15, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(0, 10, 4, 16, 17, 12),
            Block.box(0, 0, 10, 2, 10, 12),
            Block.box(14, 0, 10, 16, 10, 12),
            Block.box(14, 0, 4, 16, 10, 6),
            Block.box(0, 0, 4, 2, 10, 6),
            Block.box(16, 8, 6, 20, 15, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public AnimatedDeer(Properties p) {
        super(p.strength(1f,5f).noOcclusion().sound(SoundType.LANTERN));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING,Direction.NORTH).setValue(WATERLOGGED,false));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("block.thingamajigs.animated_deer.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        BlockEntity be = level.getBlockEntity(pos);
        if(be instanceof AnimatedDeerBE){
            if(((AnimatedDeerBE) be).custom){
                return Shapes.block();
            }
        }
        switch(state.getValue(FACING)){
            case NORTH -> {
                return NORTH_SHAPE;
            }
            case SOUTH -> {
                return SOUTH_SHAPE;
            }
            case EAST -> {
                return EAST_SHAPE;
            }
            case WEST -> {
                return WEST_SHAPE;
            }
            default -> {
                return Shapes.block();
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ENABLED,FACING,WATERLOGGED);
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
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER).setValue(ENABLED,false);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AnimatedDeerBE(pos,state);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide){
            return InteractionResult.SUCCESS;
        }
        else{
            BlockEntity be = level.getBlockEntity(pos);
            if(player.getItemInHand(player.getUsedItemHand()).isEmpty()){
                level.setBlock(pos,state.cycle(ENABLED),3);
                player.swing(player.getUsedItemHand());
                player.playSound(SoundEvents.WOODEN_BUTTON_CLICK_OFF,0.5f,level.getRandom().nextFloat() * 0.95f);
                return InteractionResult.SUCCESS;
            } else if(player.getItemInHand(player.getUsedItemHand()).is(TBlocks.REINDEER_WALL_HEAD.asItem())){
                if(be instanceof AnimatedDeerBE){
                    if (((AnimatedDeerBE) be).showAntlers) {
                        ((AnimatedDeerBE) be).showAntlers = false;
                        ((AnimatedDeerBE) be).updateBlock();
                    }
                    else{
                        ((AnimatedDeerBE) be).showAntlers = true;
                        ((AnimatedDeerBE) be).updateBlock();
                    }
                    player.swing(player.getUsedItemHand());
                    player.playSound(SoundEvents.WOOD_HIT,0.5f,level.getRandom().nextFloat() * 0.95f);
                    return InteractionResult.SUCCESS;
                }
            } else if(player.getItemInHand(player.getUsedItemHand()).is(Tags.Items.RODS_WOODEN) || player.getItemInHand(player.getUsedItemHand()).is(ItemTags.AXES)) {
                if(be instanceof AnimatedDeerBE){
                    if(((AnimatedDeerBE) be).alternateMovement){
                        ((AnimatedDeerBE) be).alternateMovement = false;
                        ((AnimatedDeerBE) be).updateBlock();
                    }
                    else{
                        ((AnimatedDeerBE) be).alternateMovement = true;
                        ((AnimatedDeerBE) be).updateBlock();
                    }
                    player.swing(player.getUsedItemHand());
                    player.playSound(SoundEvents.ARMOR_EQUIP_CHAIN.value(),0.5f,level.getRandom().nextFloat() * 0.95f);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.SUCCESS_NO_ITEM_USED;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, TBlockEntity.ANIMATED_DEER_BE.get(),
                level.isClientSide ? AnimatedDeerBE::clientTick : AnimatedDeerBE::serverTick);
    }
}
