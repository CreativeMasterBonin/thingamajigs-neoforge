package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class Microwave extends ToggledStateBlock{
    public static final VoxelShape NS = Block.box(1.0D, 0.0D, 1.0D, 15.0D,  12.0D, 15.0D);
    public static final VoxelShape NORTH = Stream.of(
            Block.box(1, 0, 3, 15, 1, 13),
            Block.box(1, 1, 3, 2, 9, 12),
            Block.box(1, 1, 12, 15, 9, 13),
            Block.box(14, 1, 3, 15, 9, 12),
            Block.box(1, 9, 3, 15, 9.5, 13),
            Block.box(5, 1, 5, 11, 1.4, 11),
            Block.box(13, 1, 3, 14, 9, 4),
            Block.box(3, 8, 3, 13, 9, 4),
            Block.box(3, 2, 3, 13, 8, 4),
            Block.box(2, 1, 3, 3, 9, 4),
            Block.box(3, 1, 3, 13, 2, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(3, 0, 1, 13, 1, 15),
            Block.box(4, 1, 1, 13, 9, 2),
            Block.box(3, 1, 1, 4, 9, 15),
            Block.box(4, 1, 14, 13, 9, 15),
            Block.box(3, 9, 1, 13, 9.5, 15),
            Block.box(5, 1, 5, 11, 1.4, 11),
            Block.box(12, 1, 13, 13, 9, 14),
            Block.box(12, 8, 3, 13, 9, 13),
            Block.box(12, 2, 3, 13, 8, 13),
            Block.box(12, 1, 2, 13, 9, 3),
            Block.box(12, 1, 3, 13, 2, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(1, 0, 3, 15, 1, 13),
            Block.box(14, 1, 4, 15, 9, 13),
            Block.box(1, 1, 3, 15, 9, 4),
            Block.box(1, 1, 4, 2, 9, 13),
            Block.box(1, 9, 3, 15, 9.5, 13),
            Block.box(5, 1, 5, 11, 1.4, 11),
            Block.box(2, 1, 12, 3, 9, 13),
            Block.box(3, 8, 12, 13, 9, 13),
            Block.box(3, 2, 12, 13, 8, 13),
            Block.box(13, 1, 12, 14, 9, 13),
            Block.box(3, 1, 12, 13, 2, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(3, 0, 1, 13, 1, 15),
            Block.box(3, 1, 14, 12, 9, 15),
            Block.box(12, 1, 1, 13, 9, 15),
            Block.box(3, 1, 1, 12, 9, 2),
            Block.box(3, 9, 1, 13, 9.5, 15),
            Block.box(5, 1, 5, 11, 1.4, 11),
            Block.box(3, 1, 2, 4, 9, 3),
            Block.box(3, 8, 3, 4, 9, 13),
            Block.box(3, 2, 3, 4, 8, 13),
            Block.box(3, 1, 13, 4, 9, 14),
            Block.box(3, 1, 3, 4, 2, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Microwave(Properties p) {
        super(p.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(TOGGLED, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return Shapes.block();}
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

    @Override
    public void playSound(BlockState bs, Level lvl, BlockPos bp){
        if(bs.getValue(TOGGLED)){
            lvl.playSound(null,bp, SoundEvents.COPPER_TRAPDOOR_CLOSE, SoundSource.BLOCKS,1f,1f);
        }
        else{
            lvl.playSound(null,bp,SoundEvents.COPPER_TRAPDOOR_OPEN, SoundSource.BLOCKS,1f,1f);
        }
    }
}
