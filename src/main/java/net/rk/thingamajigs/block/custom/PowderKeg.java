package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.ticks.TickPriority;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PowderKeg extends Block{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty VOLATILE = BooleanProperty.create("volatile"); // if it is explosively ready

    public boolean primed = false;

    public PowderKeg(Properties p) {
        super(p.mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.5F).sound(SoundType.WOOD).ignitedByLava());
        this.registerDefaultState(this.defaultBlockState().setValue(VOLATILE,false).setValue(FACING, Direction.NORTH));
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> lc, TooltipFlag p_49819_) {
        lc.add(Component.translatable("block.thingamajigs.powder_keg.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pBlockEntity, pTool);
    }

    @Override
    public BlockState playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if(!pLevel.isClientSide && !pPlayer.isCreative() && pPlayer.mayInteract(pLevel,pPos)){
            double x = (double)pPos.getX();
            double y = (double)pPos.getY();
            double z = (double)pPos.getZ();
            if(pState.getValue(VOLATILE)){
                pLevel.removeBlock(pPos,false);
                pLevel.explode(null,x,y,z,5f, Level.ExplosionInteraction.BLOCK);
            }
        }
        return super.playerWillDestroy(pLevel,pPos,pState,pPlayer);
    }

    @Override
    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity igniter) {
        if(!level.isClientSide){
            double x = (double)pos.getX();
            double y = (double)pos.getY();
            double z = (double)pos.getZ();

            if(state.getValue(VOLATILE)){
                level.removeBlock(pos,false);
                level.explode(null,x,y,z,5f, Level.ExplosionInteraction.BLOCK);
            }
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level l, BlockPos bp, Player ply, BlockHitResult bhr) {
        ItemStack iih = ply.getMainHandItem();

        if(!l.isClientSide){
            if(ply.mayInteract(l,bp)){
                if(iih.is(Items.BLAZE_POWDER) && !bs.getValue(VOLATILE)){
                    l.setBlock(bp,bs.setValue(VOLATILE,true),3);
                    l.playSound(null,bp.above(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS,0.5f,1.0f);
                    return InteractionResult.SUCCESS;
                }
                else if(iih.is(Items.FLINT_AND_STEEL) && bs.getValue(VOLATILE)){
                    if(ply.getUsedItemHand() == InteractionHand.MAIN_HAND){
                        ply.getItemInHand(ply.getUsedItemHand()).hurtAndBreak(1,ply,EquipmentSlot.MAINHAND);
                    }
                    else if(ply.getUsedItemHand() == InteractionHand.OFF_HAND){
                        ply.getItemInHand(ply.getUsedItemHand()).hurtAndBreak(1,ply,EquipmentSlot.OFFHAND);
                    }

                    l.playSound(null,bp.above(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS,1.0f,1.0f);
                    l.scheduleTick(bp,this,50, TickPriority.LOW);
                    primed = true;
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {
        if(bs.getValue(VOLATILE)){
            double d0 = (double)bp.getX() + rs.nextDouble();
            double d1 = (double)bp.getY() + 1.0D;
            double d2 = (double)bp.getZ() + rs.nextDouble();
            lvl.addParticle(ParticleTypes.LARGE_SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void tick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        double x = (double)bp.getX();
        double y = (double)bp.getY();
        double z = (double)bp.getZ();
        if(primed){
            sl.removeBlock(bp,false);
            sl.explode(null,x,y,z,5f, Level.ExplosionInteraction.BLOCK);
        }
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,VOLATILE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(VOLATILE,false).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
