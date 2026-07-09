package net.rk.thingamajigs.blockentity.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.xtras.TCalcStuff;
import net.rk.thingamajigs.xtras.TSoundEvent;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class TubeManDeco extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty TOGGLED = BlockStateProperties.ENABLED;
    public static final MapCodec<TubeManDeco> CODEC = simpleCodec(TubeManDeco::new);
    public static final VoxelShape BASE_ALL = Shapes.join(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(7, 2, 7, 9, 8, 9), BooleanOp.OR);

    public TubeManDeco(Properties properties) {
        super(properties.strength(0.25f,0.5f).sound(SoundType.WOOL).mapColor(MapColor.COLOR_BLUE)
                .noOcclusion().pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.BANJO));
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED,false).setValue(TOGGLED,false));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return BASE_ALL;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return context.isHoldingItem(TBlocks.TUBE_MAN_DECO.asItem()) ? Shapes.block() : BASE_ALL;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        if(!level.isClientSide()){
            if(projectile instanceof AbstractArrow && state.getValue(TOGGLED)){
                level.destroyBlock(hit.getBlockPos(),true,null);
                if(level instanceof ServerLevel serverLevel){
                    serverLevel.sendParticles(ParticleTypes.POOF,hit.getBlockPos().getX() + 0.5D,
                            hit.getBlockPos().getY() + 0.25D,hit.getBlockPos().getZ() + 0.5D,
                            Mth.nextInt(serverLevel.getRandom(),4,11),0D,0D,0D,0.02D);
                }
                level.playSound(null,hit.getBlockPos(),TSoundEvent.POP.get(), SoundSource.BLOCKS,1.0f, TCalcStuff.nextFloatBetweenInclusive(0.95f,1.0f));
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide()){
            if(player.getItemInHand(hand).is(Tags.Items.TOOLS_WRENCH) || player.getItemInHand(hand).is(ItemTags.AXES)){
                player.playSound(SoundEvents.WOOL_HIT,0.75f,TCalcStuff.nextFloatBetweenInclusive(0.95f,1.1f));
                return ItemInteractionResult.SUCCESS;
            }
            else if(player.getItemInHand(hand).is(Tags.Items.DYES)){
                player.playSound(SoundEvents.DYE_USE,0.75f,TCalcStuff.nextFloatBetweenInclusive(0.95f,1.1f));
                return ItemInteractionResult.SUCCESS;
            }
        }
        else{
            if(player.getItemInHand(hand).is(Tags.Items.TOOLS_WRENCH) || player.getItemInHand(hand).is(ItemTags.AXES)){
                TubeManDecoBE tubeMan = (TubeManDecoBE)level.getBlockEntity(pos);
                if(tubeMan != null){
                    if(tubeMan.yAngle > 359.0f){
                        tubeMan.yAngle = 0.0f;
                    }
                    else{
                        tubeMan.yAngle += 90.0f;
                    }
                    tubeMan.updateBlock();
                }
                return ItemInteractionResult.SUCCESS;
            }
            else if(player.getItemInHand(hand).getItem() instanceof DyeItem dyeItem){
                TubeManDecoBE tubeMan = (TubeManDecoBE)level.getBlockEntity(pos);
                if(tubeMan != null){
                    if(tubeMan.color != dyeItem.getDyeColor()){
                        tubeMan.color = dyeItem.getDyeColor();
                        player.getItemInHand(hand).shrink(1);
                        int color = dyeItem.getDyeColor().getFireworkColor();
                        int red = FastColor.ARGB32.red(color);
                        int green = FastColor.ARGB32.green(color);
                        int blue = FastColor.ARGB32.blue(color);
                        if(level instanceof ServerLevel serverLevel){
                            serverLevel.sendParticles(new DustParticleOptions(new Vector3f(red,green,blue),1.0f),
                                    pos.getX() + 0.5D,pos.getY() + 0.4D,pos.getZ() + 0.5D,
                                    4,
                                    0D,0.5D,0D,0.25);
                        }
                        tubeMan.updateBlock();
                        return ItemInteractionResult.SUCCESS;
                    }
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide()){
            if(player.isSecondaryUseActive()){
                player.playSound(TSoundEvent.AIR.get(),1.0f,1.0f);
                return InteractionResult.SUCCESS;
            }
        }
        else{
            if(player.isSecondaryUseActive()){
                level.setBlock(pos,state.cycle(TOGGLED),3);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TubeManDecoBE(blockPos,blockState);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TOGGLED,WATERLOGGED);
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
}
