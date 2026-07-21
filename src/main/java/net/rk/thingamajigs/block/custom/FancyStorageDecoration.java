package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.HopperMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.blockentity.custom.FancyStorageDecorationBE;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.Optional;
import java.util.stream.Stream;

public class FancyStorageDecoration extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public final SoundEvent OPEN_CONTAINER_SOUND;
    public final boolean usesCustomModel;
    public final String translatableName;
    public Vector3f offsets;
    public final int containerSize;

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
                    ),
                    Codec.INT.fieldOf("container_size").forGetter(
                            deco -> deco.containerSize
                    )
            ).apply(map,FancyStorageDecoration::new)
    );

    public static final VoxelShape NORTH_WHITE_CUBE = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(0, 1, 0, 1, 15, 15),
            Block.box(15, 1, 0, 16, 15, 15),
            Block.box(0, 1, 15, 16, 15, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_WHITE_CUBE = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(1, 1, 0, 16, 15, 1),
            Block.box(1, 1, 15, 16, 15, 16),
            Block.box(0, 1, 0, 1, 15, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_WHITE_CUBE = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(15, 1, 1, 16, 15, 16),
            Block.box(0, 1, 1, 1, 15, 16),
            Block.box(0, 1, 0, 16, 15, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_WHITE_CUBE = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(0, 1, 15, 15, 15, 16),
            Block.box(0, 1, 0, 15, 15, 1),
            Block.box(15, 1, 0, 16, 15, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_WHITE_SECTIONED = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(1, 7, 0, 15, 9, 15),
            Block.box(0, 1, 0, 1, 15, 15),
            Block.box(15, 1, 0, 16, 15, 15),
            Block.box(0, 1, 15, 16, 15, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_WHITE_SECTIONED = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(1, 7, 1, 16, 9, 15),
            Block.box(1, 1, 0, 16, 15, 1),
            Block.box(1, 1, 15, 16, 15, 16),
            Block.box(0, 1, 0, 1, 15, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_WHITE_SECTIONED = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(1, 7, 1, 15, 9, 16),
            Block.box(15, 1, 1, 16, 15, 16),
            Block.box(0, 1, 1, 1, 15, 16),
            Block.box(0, 1, 0, 16, 15, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_WHITE_SECTIONED = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(0, 7, 1, 15, 9, 15),
            Block.box(0, 1, 15, 15, 15, 16),
            Block.box(0, 1, 0, 15, 15, 1),
            Block.box(15, 1, 0, 16, 15, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();



    public FancyStorageDecoration(Properties p, Optional<SoundEvent> openContainerSound, String translatableName, boolean usesCustomModel, Vector3fc offsets, int containerSize){
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
        this.OPEN_CONTAINER_SOUND = openContainerSound.orElse(SoundEvents.WOODEN_TRAPDOOR_OPEN);
        this.usesCustomModel = false;
        this.translatableName = translatableName;
        this.offsets = new Vector3f(offsets.x(),offsets.y(),offsets.z());
        this.containerSize = containerSize;
    }

    public AbstractContainerMenu createMenu(int i1, Inventory i, Container container) throws IllegalStateException{
        switch(containerSize){
            case 54 -> {
                return ChestMenu.sixRows(i1, i, container);
            }
            case 45 -> {
                return new ChestMenu(MenuType.GENERIC_9x5,i1,i,container,5);
            }
            case 36 -> {
                return new ChestMenu(MenuType.GENERIC_9x4,i1,i,container,4);
            }
            case 27 -> {
                return ChestMenu.threeRows(i1,i,container);
            }
            case 18 -> {
                return new ChestMenu(MenuType.GENERIC_9x2,i1,i,container,2);
            }
            case 9 -> {
                return new ChestMenu(MenuType.GENERIC_9x1,i1,i,container,1);
            }
            case 5 -> {
                return new HopperMenu(i1,i,container);
            }
            default -> throw new IllegalStateException("FancyStorageDecoration could not create menu for container: " + container.toString() + " container size was: " + containerSize + " but should match 5, 18, 27, 36, 45 or 54");
        }
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
                if(l instanceof ServerLevel serverLevel)
                    PiglinAi.angerNearbyPiglins(serverLevel,pl,true); // if it is visible, it makes sense they can see it too
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
        return new FancyStorageDecorationBE(blockPos,blockState,translatableName,this.containerSize);
    }

    @Override
    public RenderShape getRenderShape(BlockState bs) {
        if(usesCustomModel){
            return RenderShape.INVISIBLE;
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
    protected BlockState updateShape(BlockState blockState, LevelReader reader, ScheduledTickAccess access, BlockPos pos, Direction dir, BlockPos pos2, BlockState blockState2, RandomSource source) {
        if(blockState.getValue(WATERLOGGED)){
            access.scheduleTick(pos,Fluids.WATER,Fluids.WATER.getTickDelay(reader));
        }
        return super.updateShape(blockState,reader,access,pos,dir,pos2,blockState2,source);
    }
}
