package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class MicrowaveTransmitter extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_S = Stream.of(
            Block.box(6, -16, 6, 10, -12, 10),
            Block.box(4, -12, 4, 12, -4, 12),
            Block.box(0, -4, 0, 16, 0, 16),
            Block.box(5, 22, 0, 11, 23, 16),
            Block.box(0, 0, 15, 16, 9, 19),
            Block.box(2, 9, 15, 14, 14, 19),
            Block.box(4, 14, 15, 12, 19, 19),
            Block.box(6, 19, 15, 10, 22, 19),
            Block.box(0, 0, 0, 2, 8, 15),
            Block.box(14, 0, 0, 16, 8, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_S = Stream.of(
            Block.box(6, -16, 6, 10, -12, 10),
            Block.box(4, -12, 4, 12, -4, 12),
            Block.box(0, -4, 0, 16, 0, 16),
            Block.box(5, 22, 0, 11, 23, 16),
            Block.box(0, 0, -3, 16, 9, 1),
            Block.box(2, 9, -3, 14, 14, 1),
            Block.box(4, 14, -3, 12, 19, 1),
            Block.box(6, 19, -3, 10, 22, 1),
            Block.box(14, 0, 1, 16, 8, 16),
            Block.box(0, 0, 1, 2, 8, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_S = Stream.of(
            Block.box(6, -16, 6, 10, -12, 10),
            Block.box(4, -12, 4, 12, -4, 12),
            Block.box(0, -4, 0, 16, 0, 16),
            Block.box(0, 22, 5, 16, 23, 11),
            Block.box(-3, 0, 0, 1, 9, 16),
            Block.box(-3, 9, 2, 1, 14, 14),
            Block.box(-3, 14, 4, 1, 19, 12),
            Block.box(-3, 19, 6, 1, 22, 10),
            Block.box(1, 0, 0, 16, 8, 2),
            Block.box(1, 0, 14, 16, 8, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_S = Stream.of(
            Block.box(6, -16, 6, 10, -12, 10),
            Block.box(4, -12, 4, 12, -4, 12),
            Block.box(0, -4, 0, 16, 0, 16),
            Block.box(0, 22, 5, 16, 23, 11),
            Block.box(15, 0, 0, 19, 9, 16),
            Block.box(15, 9, 2, 19, 14, 14),
            Block.box(15, 14, 4, 19, 19, 12),
            Block.box(15, 19, 6, 19, 22, 10),
            Block.box(0, 0, 14, 15, 8, 16),
            Block.box(0, 0, 0, 15, 8, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public MicrowaveTransmitter(Properties properties) {
        super(properties.sound(SoundType.METAL).strength(2F,50F).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED,false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_S;
            case SOUTH: return SOUTH_S;
            case EAST: return EAST_S;
            case WEST: return WEST_S;
            default: return Shapes.block();
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
