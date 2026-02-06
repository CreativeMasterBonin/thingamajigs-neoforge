package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.blockentity.custom.OpenableContainer;
import net.rk.thingamajigs.item.TItems;
import org.jetbrains.annotations.Nullable;

public class OpenableContainerBlock extends ThingamajigsDecorativeBlock implements EntityBlock{
    // can be accessed if open and player has access
    public static BooleanProperty ACCESSIBLE = BooleanProperty.create("accessible");

    public OpenableContainerBlock(BlockBehaviour.Properties properties){
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false)
                .setValue(ACCESSIBLE,false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ACCESSIBLE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING,context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
                .setValue(ACCESSIBLE,false);
    }


    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return use(state,level,pos,player,player.getUsedItemHand(),hitResult);
    }

    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player ply, InteractionHand hand, BlockHitResult bhr){
        if(lvl.isClientSide){
            return InteractionResult.SUCCESS;
        }
        // unlock and lock
        if(ply.getItemInHand(hand).is(TItems.KEY.get())){
            lvl.setBlock(bp,bs.cycle(ACCESSIBLE),3);
            ply.swing(ply.getUsedItemHand());
            if(lvl.getBlockState(bp).getValue(ACCESSIBLE)){
                ply.displayClientMessage(Component.translatable("block.thingamajigs.openable_container.accessible"),true);
            }
            else{
                ply.displayClientMessage(Component.translatable("block.thingamajigs.openable_container.not_accessible"),true);
            }
            return InteractionResult.CONSUME;
        }
        // accessible
        if(lvl.getBlockState(bp).getValue(ACCESSIBLE) && ply.getItemInHand(hand).is(Items.AIR)){
            BlockEntity blockEntity = lvl.getBlockEntity(bp);
            if(blockEntity instanceof OpenableContainer){
                playCustomSound(lvl,bp);
                ply.openMenu((OpenableContainer)blockEntity);
            }
            else{
                throw new IllegalStateException("OpenableContainer BlockEntity Container Provider is missing!");
            }
            return InteractionResult.SUCCESS;
        }
        else if(!lvl.getBlockState(bp).getValue(ACCESSIBLE) && ply.getItemInHand(hand).is(Items.AIR)){
            ply.displayClientMessage(Component.translatable("block.thingamajigs.openable_container.not_accessible"),true);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new OpenableContainer(blockPos,blockState);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if(blockEntity instanceof OpenableContainer){
                Containers.dropContents(level,blockPos,(OpenableContainer)blockEntity);
            }
        }
        super.onRemove(state,level,blockPos,newState,isMoving);
    }

    public void playCustomSound(Level lvl, BlockPos bp){
        lvl.playSound(null,bp, SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundSource.BLOCKS,1.0f,1.0f);
    }
}
