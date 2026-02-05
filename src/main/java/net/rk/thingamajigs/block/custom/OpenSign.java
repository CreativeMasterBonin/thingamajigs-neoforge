package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class OpenSign extends ToggledStateBlock{
    private static final VoxelShape N_S = Stream.of(
            Block.box(-3, 3, 15, 18, 13, 16),
            Block.box(-2, 3, 14, 17, 4, 15),
            Block.box(-2, 12, 14, 17, 13, 15),
            Block.box(-3, 3, 14, -2, 13, 15),
            Block.box(17, 3, 14, 18, 13, 15),
            Block.box(13, 5, 14, 17, 11, 15),
            Block.box(8, 7, 14, 12, 11, 15),
            Block.box(11, 5, 14, 12, 7, 15),
            Block.box(3, 10, 14, 7, 11, 15),
            Block.box(3, 5, 14, 7, 6, 15),
            Block.box(6, 6, 14, 7, 7, 15),
            Block.box(6, 8, 14, 7, 10, 15),
            Block.box(4, 7, 14, 7, 8, 15),
            Block.box(-2, 5, 14, 2, 11, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape S_S = Stream.of(
            Block.box(-2, 3, 0, 19, 13, 1),
            Block.box(-1, 3, 1, 18, 4, 2),
            Block.box(-1, 12, 1, 18, 13, 2),
            Block.box(18, 3, 1, 19, 13, 2),
            Block.box(-2, 3, 1, -1, 13, 2),
            Block.box(-1, 5, 1, 3, 11, 2),
            Block.box(4, 7, 1, 8, 11, 2),
            Block.box(4, 5, 1, 5, 7, 2),
            Block.box(9, 10, 1, 13, 11, 2),
            Block.box(9, 5, 1, 13, 6, 2),
            Block.box(9, 6, 1, 10, 7, 2),
            Block.box(9, 8, 1, 10, 10, 2),
            Block.box(9, 7, 1, 12, 8, 2),
            Block.box(14, 5, 1, 18, 11, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape E_S = Stream.of(
            Block.box(0, 3, -3, 1, 13, 18),
            Block.box(1, 3, -2, 2, 4, 17),
            Block.box(1, 12, -2, 2, 13, 17),
            Block.box(1, 3, -3, 2, 13, -2),
            Block.box(1, 3, 17, 2, 13, 18),
            Block.box(1, 5, 13, 2, 11, 17),
            Block.box(1, 7, 8, 2, 11, 12),
            Block.box(1, 5, 11, 2, 7, 12),
            Block.box(1, 10, 3, 2, 11, 7),
            Block.box(1, 5, 3, 2, 6, 7),
            Block.box(1, 6, 6, 2, 7, 7),
            Block.box(1, 8, 6, 2, 10, 7),
            Block.box(1, 7, 4, 2, 8, 7),
            Block.box(1, 5, -2, 2, 11, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape W_S = Stream.of(
            Block.box(15, 3, -2, 16, 13, 19),
            Block.box(14, 3, -1, 15, 4, 18),
            Block.box(14, 12, -1, 15, 13, 18),
            Block.box(14, 3, 18, 15, 13, 19),
            Block.box(14, 3, -2, 15, 13, -1),
            Block.box(14, 5, -1, 15, 11, 3),
            Block.box(14, 7, 4, 15, 11, 8),
            Block.box(14, 5, 4, 15, 7, 5),
            Block.box(14, 10, 9, 15, 11, 13),
            Block.box(14, 5, 9, 15, 6, 13),
            Block.box(14, 6, 9, 15, 7, 10),
            Block.box(14, 8, 9, 15, 10, 10),
            Block.box(14, 7, 9, 15, 8, 12),
            Block.box(14, 5, 14, 15, 11, 18)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public OpenSign(Properties p) {
        super(p.mapColor(MapColor.COLOR_BLUE).strength(0.95F,1F));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TOGGLED, false).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = bs.getValue(FACING);
        switch(direction){
            case NORTH: return N_S;
            case SOUTH: return S_S;
            case EAST: return E_S;
            case WEST: return W_S;
            default: return Shapes.block();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,TOGGLED,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(TOGGLED,false).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
