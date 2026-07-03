package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;
import net.rk.thingamajigs.xtras.TCalcStuff;
import net.rk.thingamajigs.xtras.TProperties;

public class Urinal extends Toilet{
    public static final BooleanProperty REVERSED = TProperties.REVERSED;

    public Urinal(Properties p) {
        super(p.instrument(NoteBlockInstrument.COW_BELL).mapColor(MapColor.TERRACOTTA_WHITE));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false).setValue(REVERSED,false));
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(REVERSED);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.block();
    }

    @Override
    public InteractionResult useWithoutItem(BlockState bs, Level level, BlockPos pos, Player player, BlockHitResult result) {
        return InteractionResult.PASS;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide()){
            if(player.getItemInHand(hand).isEmpty()){
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            else{
                if(player.getItemInHand(hand).is(Tags.Items.TOOLS_WRENCH) || player.getItemInHand(hand).is(ItemTags.AXES)){
                    player.playSound(SoundEvents.IRON_GOLEM_REPAIR,0.5f, TCalcStuff.nextFloatBetweenInclusive(0.95f,1.1f));
                }
                else if(player.getItemInHand(hand).is(Tags.Items.TOOLS_SHEAR)){
                    player.playSound(SoundEvents.DECORATED_POT_HIT,0.5f, TCalcStuff.nextFloatBetweenInclusive(0.95f,1.1f));
                }
                return ItemInteractionResult.SUCCESS;
            }
        }
        else{
            if(player.getItemInHand(hand).isEmpty()){
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            else{ // for changing flush handle orientation/piping of urinal
                if(player.getItemInHand(hand).is(Tags.Items.TOOLS_WRENCH) || player.getItemInHand(hand).is(ItemTags.AXES)){
                    level.setBlock(pos,state.cycle(REVERSED),3);
                    if(level instanceof ServerLevel serverLevel){
                        serverLevel.sendParticles(ParticleTypes.POOF,pos.getX() + 0.5D,pos.getY() + 0.75,pos.getZ() + 0.5D,7,0D,0D,0D,0);
                    }
                } // for changing type of urinal (classic or modern)
                else if(player.getItemInHand(hand).is(Tags.Items.TOOLS_SHEAR)){
                    level.setBlock(pos,state.cycle(TOGGLED),3);
                    if(level instanceof ServerLevel serverLevel){
                        serverLevel.sendParticles(ParticleTypes.POOF,pos.getX() + 0.5D,pos.getY() + 0.75,pos.getZ() + 0.5D,15,0D,0D,0D,0);
                    }
                }
                return ItemInteractionResult.SUCCESS;
            }
        }
    }
}
