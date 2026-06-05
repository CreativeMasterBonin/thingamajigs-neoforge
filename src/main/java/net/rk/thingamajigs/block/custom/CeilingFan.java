package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;
import net.rk.thingamajigs.blockentity.TBlockEntity;
import net.rk.thingamajigs.blockentity.custom.CeilingFanBE;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class CeilingFan extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final VoxelShape ALL = Stream.of(
            Block.box(3, 15, 3, 13, 16, 13),
            Block.box(5, 13, 5, 11, 15, 11),
            Block.box(7, 6, 7, 9, 13, 9),
            Block.box(6, 2, 6, 10, 6, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static BooleanProperty TOGGLED = BlockStateProperties.ENABLED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final MapCodec<CeilingFan> CODEC = Block.simpleCodec(CeilingFan::new);

    public CeilingFan(Properties properties) {
        super(properties.strength(1.5f).sound(SoundType.LANTERN).instrument(NoteBlockInstrument.COW_BELL));
        this.registerDefaultState(this.defaultBlockState().setValue(TOGGLED,false).setValue(WATERLOGGED,false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED,TOGGLED);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER).setValue(TOGGLED,false);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide()){
            if(player.getItemInHand(hand).is(Tags.Items.TOOLS_WRENCH) || player.getItemInHand(hand).is(Items.LEVER)){
                float randomPitchClient = 0.95f + level.getRandom().nextFloat() * 1.05f;
                player.playSound(SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,0.7f,randomPitchClient);
                return ItemInteractionResult.SUCCESS;
            }
        }
        else{ // if holding a wrench or lever and the player right-clicks the fan, reverse the direction of air flow
            if(player.getItemInHand(hand).is(Tags.Items.TOOLS_WRENCH) || player.getItemInHand(hand).is(Items.LEVER)) {
                if(level.getBlockEntity(pos) instanceof CeilingFanBE fan){
                    fan.reversed = !fan.reversed;
                    fan.updateBlock();
                }
                return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide()){
            if(player.isSecondaryUseActive()){
                float randomPitchClient = 0.95f + level.getRandom().nextFloat() * 1.05f;
                player.playSound(SoundEvents.IRON_GOLEM_STEP,0.7f,randomPitchClient);
                return InteractionResult.SUCCESS;
            }
        }
        else{
            if(player.isSecondaryUseActive()){
                level.setBlock(pos,state.cycle(TOGGLED),3);
                return InteractionResult.SUCCESS_NO_ITEM_USED;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return ALL;
    }

    @Override
    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param) {
        return level.getBlockEntity(pos) != null && level.getBlockEntity(pos).triggerEvent(id, param);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CeilingFanBE(blockPos,blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, TBlockEntity.CEILING_FAN_BE.get(),
                level.isClientSide ? CeilingFanBE::clientTick : null);
    }
}
