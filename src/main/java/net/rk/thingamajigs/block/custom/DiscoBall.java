package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.Tags;
import net.rk.thingamajigs.blockentity.custom.DiscoBallBE;
import net.rk.thingamajigs.xtras.TCalcStuff;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DiscoBall extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<DiscoBall> CODEC = simpleCodec(DiscoBall::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public DiscoBall(Properties p) {
        super(p.strength(1f,2f).sound(SoundType.GLASS).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED,false));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("block.thingamajigs.disco_ball.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(player.getItemInHand(hand).isEmpty()){
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        if(level.isClientSide()){
            DiscoBallBE discoBall = (DiscoBallBE)level.getBlockEntity(pos);
            if(discoBall instanceof DiscoBallBE){
                if(stack.is(Tags.Items.TOOLS_WRENCH) || stack.is(Tags.Items.TOOLS_SHEAR)){
                    player.playSound(SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF,0.5f, TCalcStuff.nextFloatBetweenInclusive(0.95f,1.0f));
                }
                else if(stack.is(Tags.Items.TOOLS_BRUSH) || stack.is(ItemTags.AXES)){
                    player.playSound(SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF,0.5f, TCalcStuff.nextFloatBetweenInclusive(0.92f,0.98f));
                }
                return ItemInteractionResult.SUCCESS;
            }
        }
        else{
            DiscoBallBE discoBall = (DiscoBallBE)level.getBlockEntity(pos);
            if(discoBall instanceof DiscoBallBE){
                if(stack.is(Tags.Items.TOOLS_WRENCH) || stack.is(Tags.Items.TOOLS_SHEAR)){
                    discoBall.speed -= 1.0D;
                    discoBall.updateBlock();
                    return ItemInteractionResult.SUCCESS;
                }
                else if(stack.is(Tags.Items.TOOLS_BRUSH) || stack.is(ItemTags.AXES)){
                    discoBall.speed += 1.0D;
                    discoBall.updateBlock();
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(!player.getItemInHand(player.getUsedItemHand()).isEmpty()){
            return InteractionResult.PASS;
        }
        if(level.isClientSide()){
            DiscoBallBE discoBall = (DiscoBallBE)level.getBlockEntity(pos);
            if(discoBall instanceof DiscoBallBE){
                player.playSound(SoundEvents.BAMBOO_WOOD_BUTTON_CLICK_OFF,0.5f, TCalcStuff.nextFloatBetweenInclusive(0.95f,1.0f));
                return InteractionResult.SUCCESS_NO_ITEM_USED;
            }
        }
        else{
            DiscoBallBE discoBall = (DiscoBallBE)level.getBlockEntity(pos);
            if(discoBall instanceof DiscoBallBE){
                discoBall.spinning = !discoBall.spinning;
                discoBall.updateBlock();
                return InteractionResult.SUCCESS_NO_ITEM_USED;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DiscoBallBE(blockPos,blockState);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
