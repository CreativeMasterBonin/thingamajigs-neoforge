package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.blockentity.custom.DecorationalBucketBE;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class DecorationalBucket extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<DecorationalBucket> BUCKET_CODEC = Block.simpleCodec(DecorationalBucket::new);
    @Override
    public MapCodec<DecorationalBucket> codec(){return BUCKET_CODEC;}
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final VoxelShape ALL = Stream.of(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(4, 1, 4, 5, 10, 12),
            Block.box(11, 1, 4, 12, 10, 12),
            Block.box(5, 1, 4, 11, 10, 5),
            Block.box(5, 1, 11, 11, 10, 12)
            ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public DecorationalBucket(Properties p) {
        super(p.sound(SoundType.LANTERN).instrument(NoteBlockInstrument.XYLOPHONE).noOcclusion()
                .mapColor(MapColor.METAL).strength(1.25F,10F));
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return ALL;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new DecorationalBucketBE(blockPos,blockState);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
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

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide()){
            if(player.getItemInHand(hand).getItem() instanceof BucketItem bucketItem){
                try{
                    if(level.getBlockEntity(pos) instanceof DecorationalBucketBE bucket){
                        if(bucketItem.content != Fluids.EMPTY){
                            bucket.setFluid(bucketItem.content);
                            if(bucketItem.content.getFluidType().isVanilla()){
                                if(bucketItem.content == Fluids.LAVA){
                                    level.playSound(player,pos, SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.BLOCKS,1.0f,1.0f);
                                }
                                else if(bucketItem.content == Fluids.WATER){
                                    level.playSound(player,pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS,1.0f,1.0f);
                                }
                            }

                            // there may not be modded fluids in most bucket items, but put this here anyway
                            if(!bucketItem.content.getFluidType().isVanilla()){
                                level.playSound(player,pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS,1.0f,1.0f);
                            }
                        }
                    }
                }
                catch (Exception e){
                    return InteractionResult.FAIL;
                }
            }
        }
        else{
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
