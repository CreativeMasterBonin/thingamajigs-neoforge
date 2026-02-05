package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class EscalatorBlock extends Block implements SimpleWaterloggedBlock{
    public static final MapCodec<EscalatorBlock> ESCALATOR_BLOCK_MAP_CODEC = simpleCodec(EscalatorBlock::new);
    @Override
    protected MapCodec<? extends Block> codec() {
        return ESCALATOR_BLOCK_MAP_CODEC;
    }

    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 0, 0, 16, 4, 4),
            Block.box(0, 0, 4, 16, 8, 8),
            Block.box(0, 0, 8, 16, 12, 12),
            Block.box(0, 0, 12, 16, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(12, 0, 0, 16, 4, 16),
            Block.box(8, 0, 0, 12, 8, 16),
            Block.box(4, 0, 0, 8, 12, 16),
            Block.box(0, 0, 0, 4, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(0, 0, 12, 16, 4, 16),
            Block.box(0, 0, 8, 16, 8, 12),
            Block.box(0, 0, 4, 16, 12, 8),
            Block.box(0, 0, 0, 16, 16, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(0, 0, 0, 4, 4, 16),
            Block.box(4, 0, 0, 8, 8, 16),
            Block.box(8, 0, 0, 12, 12, 16),
            Block.box(12, 0, 0, 16, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public EscalatorBlock(Properties p) {
        super(p.strength(1f,5f).sound(SoundType.LANTERN).noOcclusion());
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getInteractionShape(BlockState bs, BlockGetter bg, BlockPos bp) {
        return Shapes.block();
    }


    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> lc, TooltipFlag p_49819_) {
        lc.add(Component.translatable("block.thingamajigs.escalator.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void stepOn(Level lvl, BlockPos bp, BlockState bs, Entity e) {
        double x = 0.0D;
        double y = 0.0D;
        double z = 0.0D;

        if(bs.getValue(FACING) == Direction.NORTH && lvl.getBlockState(bp.north().above()).getBlock() == this){
            y = 0.5D;
        }
        if(bs.getValue(FACING) == Direction.SOUTH && lvl.getBlockState(bp.south().above()).getBlock() == this){
            y = 0.5D;
        }
        if(bs.getValue(FACING) == Direction.EAST && lvl.getBlockState(bp.east().above()).getBlock() == this){
            y = 0.5D;
        }
        if(bs.getValue(FACING) == Direction.WEST && lvl.getBlockState(bp.west().above()).getBlock() == this){
            y = 0.5D;
        }

        switch(bs.getValue(FACING)){
            case NORTH -> z = 0.25D;
            case SOUTH -> z = -0.25D;
            case EAST -> x = -0.25D;
            case WEST -> x = 0.25D;
        }

        if(e instanceof Player){
            e.setDeltaMovement(x,y,z);
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default: return Shapes.block();
        }
    }

    // TODO re-enable getShape stuff
    /*
    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        if(cc.isHoldingItem(
                TBlocks.ESCALATOR.get().asItem()) || cc.isHoldingItem(TBlocks.ESCALATOR_DOWN.get().asItem())){
            return Shapes.block();
        }
        else{
            return super.getVisualShape(bs,bg,bp,cc);
        }
    }
    */

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
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
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
