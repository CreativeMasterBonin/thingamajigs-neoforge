package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.blockentity.custom.FancyStorageDecorationBE;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Optional;

public class FancyStorageDecoration extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public final SoundEvent OPEN_CONTAINER_SOUND;
    public final boolean usesCustomModel;
    public final String translatableName;
    public Vector3f offsets;

    public static final MapCodec<FancyStorageDecoration> CODEC = RecordCodecBuilder.mapCodec(
            map -> map.group(
                    propertiesCodec(),
                SoundEvent.DIRECT_CODEC.optionalFieldOf("open_container_sound").forGetter(
                        deco -> Optional.of(deco.OPEN_CONTAINER_SOUND)),
                    Codec.STRING.fieldOf("translatable_name").forGetter(
                            deco -> deco.translatableName
                    ),
                    Codec.BOOL.fieldOf("uses_block_entity_model").forGetter(
                            deco -> deco.usesCustomModel
                    ),
                    ExtraCodecs.VECTOR3F.fieldOf("offsets").forGetter(
                            deco -> deco.offsets
                    )
            ).apply(map,FancyStorageDecoration::new)
    );

    public FancyStorageDecoration(Properties p, Optional<SoundEvent> openContainerSound, String translatableName, boolean usesCustomModel, Vector3f offsets){
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
        this.OPEN_CONTAINER_SOUND = openContainerSound.orElse(SoundEvents.WOODEN_TRAPDOOR_OPEN);
        this.usesCustomModel = false;
        this.translatableName = translatableName;
        this.offsets = offsets;
    }

    @Override
    public void onRemove(BlockState bs, Level lvl, BlockPos bp, BlockState bs2, boolean boo1) {
        if(bs.getBlock() != bs2.getBlock()){
            BlockEntity blockEntity = lvl.getBlockEntity(bp);
            if(blockEntity instanceof FancyStorageDecorationBE fancyStorageDecorationBE){
                Containers.dropContents(lvl,bp,fancyStorageDecorationBE);
            }
        }
        super.onRemove(bs,lvl,bp,bs2,boo1);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level l, BlockPos bp, Player pl, BlockHitResult bhr) {
        if(l.isClientSide()){
            return InteractionResult.SUCCESS;
        }
        else{
            BlockEntity blockEntity = l.getBlockEntity(bp);
            if(blockEntity instanceof FancyStorageDecorationBE fancyStorageDecorationBE){
                l.playSound(null,bp, OPEN_CONTAINER_SOUND, SoundSource.BLOCKS,0.75f,1.0f);
                pl.openMenu(fancyStorageDecorationBE,bp);
                pl.swing(pl.getUsedItemHand());
                PiglinAi.angerNearbyPiglins(pl, true); // if it is visible, it makes sense they can see it too
                return InteractionResult.CONSUME;
            }
            else{
                throw new IllegalStateException("FancyStorageDecorationBE Container Provider is missing!");
            }
        }
    }

    @Override
    public MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FancyStorageDecorationBE(blockPos,blockState,translatableName);
    }

    @Override
    public RenderShape getRenderShape(BlockState bs) {
        if(usesCustomModel){
            return RenderShape.ENTITYBLOCK_ANIMATED;
        }
        else{
            return RenderShape.MODEL;
        }
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    public BlockState updateShape(BlockState bs, Direction dir, BlockState bs2, LevelAccessor lvla, BlockPos bp1, BlockPos bp2) {
        if(bs.getValue(WATERLOGGED)){
            lvla.scheduleTick(bp1,Fluids.WATER,Fluids.WATER.getTickDelay(lvla));
        }
        return super.updateShape(bs,dir,bs2,lvla,bp1,bp2);
    }
}
