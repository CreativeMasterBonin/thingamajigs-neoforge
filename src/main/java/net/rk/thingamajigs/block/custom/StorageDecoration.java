package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
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
import net.rk.thingamajigs.blockentity.custom.StorageDecorationBE;
import org.jetbrains.annotations.Nullable;

public class StorageDecoration extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public final SoundEvent OPEN_CONTAINER_SOUND;
    public final boolean usesCustomModel;
    private String translatableName;

    public static final MapCodec<StorageDecoration> STORAGE_DECORATION_MAP_CODEC = Block.simpleCodec(StorageDecoration::new);

    public StorageDecoration(Properties p) {
        super(p.noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
        this.OPEN_CONTAINER_SOUND = SoundEvents.WOODEN_DOOR_OPEN;
        this.usesCustomModel = false;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return STORAGE_DECORATION_MAP_CODEC;
    }

    public StorageDecoration(Properties p, SoundEvent openContainerSound){
        super(p.noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
        this.OPEN_CONTAINER_SOUND = openContainerSound;
        this.usesCustomModel = false;
    }

    public StorageDecoration(Properties p, SoundEvent openContainerSound, String translatableName){
        super(p.noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
        this.OPEN_CONTAINER_SOUND = openContainerSound;
        this.usesCustomModel = false;
        this.translatableName = translatableName;
    }

    public StorageDecoration(Properties p, String translatableName){
        super(p.noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
        this.usesCustomModel = false;
        this.OPEN_CONTAINER_SOUND = SoundEvents.WOODEN_DOOR_OPEN;
        this.translatableName = translatableName;
    }

    public StorageDecoration(Properties p, SoundEvent openContainerSound, boolean usesCustomModel){
        super(p.noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
        this.OPEN_CONTAINER_SOUND = openContainerSound;
        this.usesCustomModel = usesCustomModel;
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
    public void onRemove(BlockState bs, Level lvl, BlockPos bp, BlockState bs2, boolean boo1) {
        if(bs.getBlock() != bs2.getBlock()){
            BlockEntity blockEntity = lvl.getBlockEntity(bp);
            if(blockEntity instanceof StorageDecorationBE){
                Containers.dropContents(lvl,bp,(StorageDecorationBE)blockEntity);
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
            if(blockEntity instanceof StorageDecorationBE){
                StorageDecorationBE be = (StorageDecorationBE)blockEntity;
                String str = be.getContainerNameTranslation();
                l.playSound(null,bp, OPEN_CONTAINER_SOUND, SoundSource.BLOCKS,0.75f,1.0f);
                pl.openMenu((StorageDecorationBE)blockEntity,bp);
                pl.swing(pl.getUsedItemHand());
                return InteractionResult.CONSUME;
            }
            else{
                throw new IllegalStateException("StorageDecorationBE Container Provider is missing!");
            }
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return new StorageDecorationBE(bp,bs,translatableName);
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
