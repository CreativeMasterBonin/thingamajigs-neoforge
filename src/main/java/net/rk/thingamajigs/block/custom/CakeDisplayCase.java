package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class CakeDisplayCase extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty HAS_CAKE = BooleanProperty.create("has_cake");
    public static final MapCodec<CakeDisplayCase> CODEC = Block.simpleCodec(CakeDisplayCase::new);
    public static final VoxelShape ALL = Stream.of(
            Block.box(6, 0, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 5, 9),
            Block.box(0, 5, 0, 16, 7, 16),
            Block.box(0, 7, 0, 16, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    public CakeDisplayCase(Properties properties) {
        super(properties.strength(0.75f,1f).noOcclusion().mapColor(MapColor.TERRACOTTA_WHITE)
                .sound(SoundType.CALCITE));
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED,false)
                .setValue(HAS_CAKE,false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return ALL;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide()){
            if(player.getItemInHand(hand).getItem() instanceof BlockItem item){
                if(item.getBlock() instanceof CakeBlock){
                    player.playSound(SoundEvents.CAKE_ADD_CANDLE,1f,1f);
                }
            }
            return ItemInteractionResult.SUCCESS;
        }
        else{
            if(player.getItemInHand(hand).getItem() instanceof BlockItem item){
                if(item.getBlock() instanceof CakeBlock){
                    level.setBlock(pos,state.cycle(HAS_CAKE),3);
                    level.playSound(player,pos, SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS,1f,1f);
                }
            }
            return ItemInteractionResult.CONSUME;
        }
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED,HAS_CAKE);
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
}
