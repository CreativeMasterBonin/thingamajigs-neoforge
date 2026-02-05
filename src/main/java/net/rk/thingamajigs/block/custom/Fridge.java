package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.blockentity.custom.FridgeBE;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecated")
public class Fridge extends DoubleTallDecorationBlock implements EntityBlock {
    public static final MapCodec<Fridge> FRIDGE_BLOCK_MAP_CODEC = Block.simpleCodec(Fridge::new);
    @Override
    public MapCodec<Fridge> codec() {return FRIDGE_BLOCK_MAP_CODEC;}

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape BLOCK_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 32.0D, 16.0D);

    public Fridge(BlockBehaviour.Properties p) {
        super(p.strength(1.25F,10F).noOcclusion().pushReaction(PushReaction.BLOCK));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        return BLOCK_SHAPE;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos blockPos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if(blockEntity instanceof FridgeBE){
                Containers.dropContents(level,blockPos,(FridgeBE)blockEntity);
            }
        }
        super.onRemove(state,level,blockPos,newState,isMoving);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level l, BlockPos bp, Player pl, BlockHitResult bhr) {
        if(!l.isClientSide()){
            BlockEntity blockEntity = l.getBlockEntity(bp);
            if(blockEntity instanceof FridgeBE){
                l.playSound(null,bp, SoundEvents.BAMBOO_WOOD_DOOR_OPEN, SoundSource.BLOCKS,1.0f,1.0f);
                pl.openMenu((FridgeBE)blockEntity);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.sidedSuccess(l.isClientSide);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {return new FridgeBE(bp,bs);}
}
