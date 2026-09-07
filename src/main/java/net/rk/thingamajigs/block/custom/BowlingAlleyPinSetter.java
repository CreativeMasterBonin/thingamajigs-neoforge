package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class BowlingAlleyPinSetter extends RedstoneLampBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape BLOCK_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 32.0D, 16.0D);
    public static final VoxelShape NORTHSOUTH = Stream.of(
            Block.box(-8, 16, 0, 24, 32, 16),
            Block.box(-8, 0, 0, -6, 16, 16),
            Block.box(22, 0, 0, 24, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST = Stream.of(
            Block.box(0, 16, -8, 16, 32, 24),
            Block.box(0, 0, -8, 16, 16, -6),
            Block.box(0, 0, 22, 16, 16, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTHSOUTH_COMBINED = Shapes.join(NORTHSOUTH,BLOCK_SHAPE,BooleanOp.OR);
    public static final VoxelShape EASTWEST_COMBINED = Shapes.join(EASTWEST,BLOCK_SHAPE,BooleanOp.OR);

    public BowlingAlleyPinSetter(Properties p) {
        super(p.strength(2.5F,3.5F).sound(SoundType.METAL).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        switch(state.getValue(FACING)){
            case NORTH,SOUTH -> {return NORTHSOUTH_COMBINED;}
            case EAST,WEST -> {return EASTWEST_COMBINED;}
            default -> {return BLOCK_SHAPE;}
        }
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        switch(state.getValue(FACING)){
            case NORTH,SOUTH -> {return NORTHSOUTH_COMBINED;}
            case EAST,WEST -> {return EASTWEST_COMBINED;}
            default -> {return BLOCK_SHAPE;}
        }
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH:
            case SOUTH:
                return NORTHSOUTH;
            case EAST:
            case WEST:
                return EASTWEST;
            default:
                return BLOCK_SHAPE;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH,SOUTH -> {return NORTHSOUTH_COMBINED;}
            case EAST,WEST -> {return EASTWEST_COMBINED;}
            default -> {return BLOCK_SHAPE;}
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos())).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
