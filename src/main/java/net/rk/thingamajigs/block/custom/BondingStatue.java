package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.datagen.TTag;

import java.util.List;

public class BondingStatue extends Podium{
    public static final BooleanProperty RUINED = BooleanProperty.create("ruined");
    public static final BooleanProperty PROTECTED = BooleanProperty.create("protected");

    public BondingStatue(Properties properties) {
        super(properties.mapColor(MapColor.DIAMOND).sound(SoundType.METAL).randomTicks());
        this.defaultBlockState().setValue(RUINED,false).setValue(PROTECTED,false);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("statue.thingamajigs.author.cmb")
                .withStyle(ChatFormatting.BLUE));
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(!level.dimensionType().hasFixedTime() && !state.getValue(RUINED) && level.isNight() && !state.getValue(PROTECTED)){
            level.setBlock(pos,state.setValue(RUINED,true),3);
            level.sendParticles(ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER_OMINOUS,
                    pos.getX() + 0.5, pos.getY() + 0.9, pos.getZ() + 0.5,
                    7, 0.0, 0.0, 0.0, 0.01);
            level.playSound(null,pos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS,0.5f,random.nextFloat() * 0.75f);
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide){
            return ItemInteractionResult.SUCCESS;
        }
        else{
            if(player.getItemInHand(hand).is(TTag.MYSTERIOUS_ITEMS) && state.getValue(RUINED)){
                if(player.mayInteract((ServerLevel)level,pos)){
                    level.setBlock(pos,state.setValue(RUINED,false),3);
                    try{
                        ServerLevel lvl = (ServerLevel) level;
                        lvl.sendParticles(ParticleTypes.TRIAL_OMEN,
                                pos.getX() + 0.5, pos.getY() + 0.9, pos.getZ() + 0.5,
                                10, 0.0, 0.0, 0.0, 0.01);
                    }
                    catch (Exception e){
                        // skip
                    }
                    level.playSound(null,pos, SoundEvents.CONDUIT_ATTACK_TARGET, SoundSource.BLOCKS,0.4f,
                            level.getRandom().nextFloat() * 0.85f);
                    return ItemInteractionResult.CONSUME;
                }
            }
            else if (player.getItemInHand(hand).is(TTag.PROTECTS_STATUES)) {
                if(player.mayInteract((ServerLevel)level,pos)){
                    level.setBlock(pos,state.setValue(PROTECTED,true),3);
                    if(!player.isCreative()){
                        player.getItemInHand(hand).shrink(1);
                    }
                    ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.ELECTRIC_SPARK, UniformInt.of(4,7));
                    level.playLocalSound(pos,SoundEvents.END_PORTAL_FRAME_FILL,SoundSource.PLAYERS,0.75f,1.0F,true);
                    return ItemInteractionResult.CONSUME;
                }
            }
        }
        return super.useItemOn(stack,state,level,pos,player,hand,hitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(RUINED,PROTECTED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
                .setValue(RUINED,false).setValue(PROTECTED,false);
    }
}
