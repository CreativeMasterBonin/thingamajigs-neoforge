package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class AisleSign extends ThingamajigsDecorativeBlock{
    public static final IntegerProperty NUMBER = IntegerProperty.create("number",0,10);
    public static final VoxelShape NORTH = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(4, 11, 6, 12, 13, 8),
            Block.box(15, 0, 12, 16, 16, 14),
            Block.box(14, 0, 10, 15, 16, 12),
            Block.box(12, 0, 6, 13, 16, 8),
            Block.box(13, 0, 8, 14, 16, 10),
            Block.box(10, 0, 2, 11, 16, 4),
            Block.box(11, 0, 4, 12, 16, 6),
            Block.box(8, 0, -2, 9, 16, 0),
            Block.box(9, 0, 0, 10, 16, 2),
            Block.box(1, 0, 11, 2, 16, 13),
            Block.box(2, 0, 9, 3, 16, 11),
            Block.box(3, 0, 7, 4, 16, 9),
            Block.box(4, 0, 5, 5, 16, 7),
            Block.box(5, 0, 3, 6, 16, 5),
            Block.box(5, 0, 1, 6, 16, 3),
            Block.box(6, 0, -1, 7, 16, 1),
            Block.box(7, 0, -2, 8, 16, -1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(8, 11, 4, 10, 13, 12),
            Block.box(2, 0, 15, 4, 16, 16),
            Block.box(4, 0, 14, 6, 16, 15),
            Block.box(8, 0, 12, 10, 16, 13),
            Block.box(6, 0, 13, 8, 16, 14),
            Block.box(12, 0, 10, 14, 16, 11),
            Block.box(10, 0, 11, 12, 16, 12),
            Block.box(16, 0, 8, 18, 16, 9),
            Block.box(14, 0, 9, 16, 16, 10),
            Block.box(3, 0, 1, 5, 16, 2),
            Block.box(5, 0, 2, 7, 16, 3),
            Block.box(7, 0, 3, 9, 16, 4),
            Block.box(9, 0, 4, 11, 16, 5),
            Block.box(11, 0, 5, 13, 16, 6),
            Block.box(13, 0, 5, 15, 16, 6),
            Block.box(15, 0, 6, 17, 16, 7),
            Block.box(17, 0, 7, 18, 16, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(4, 11, 8, 12, 13, 10),
            Block.box(0, 0, 2, 1, 16, 4),
            Block.box(1, 0, 4, 2, 16, 6),
            Block.box(3, 0, 8, 4, 16, 10),
            Block.box(2, 0, 6, 3, 16, 8),
            Block.box(5, 0, 12, 6, 16, 14),
            Block.box(4, 0, 10, 5, 16, 12),
            Block.box(7, 0, 16, 8, 16, 18),
            Block.box(6, 0, 14, 7, 16, 16),
            Block.box(14, 0, 3, 15, 16, 5),
            Block.box(13, 0, 5, 14, 16, 7),
            Block.box(12, 0, 7, 13, 16, 9),
            Block.box(11, 0, 9, 12, 16, 11),
            Block.box(10, 0, 11, 11, 16, 13),
            Block.box(10, 0, 13, 11, 16, 15),
            Block.box(9, 0, 15, 10, 16, 17),
            Block.box(8, 0, 17, 9, 16, 18)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(7, 0, 7, 9, 16, 9),
            Block.box(6, 11, 4, 8, 13, 12),
            Block.box(12, 0, 0, 14, 16, 1),
            Block.box(10, 0, 1, 12, 16, 2),
            Block.box(6, 0, 3, 8, 16, 4),
            Block.box(8, 0, 2, 10, 16, 3),
            Block.box(2, 0, 5, 4, 16, 6),
            Block.box(4, 0, 4, 6, 16, 5),
            Block.box(-2, 0, 7, 0, 16, 8),
            Block.box(0, 0, 6, 2, 16, 7),
            Block.box(11, 0, 14, 13, 16, 15),
            Block.box(9, 0, 13, 11, 16, 14),
            Block.box(7, 0, 12, 9, 16, 13),
            Block.box(5, 0, 11, 7, 16, 12),
            Block.box(3, 0, 10, 5, 16, 11),
            Block.box(1, 0, 10, 3, 16, 11),
            Block.box(-1, 0, 9, 1, 16, 10),
            Block.box(-2, 0, 8, -1, 16, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public AisleSign(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(NUMBER,0).setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch(state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return Shapes.block();}
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player p, BlockHitResult bhr) {
        if(p.isShiftKeyDown()){
            if(!lvl.isClientSide()){
                lvl.setBlock(bp,bs.cycle(NUMBER),2);
                lvl.playSound(null,bp, SoundEvents.ITEM_FRAME_ROTATE_ITEM, SoundSource.BLOCKS,1F,1F);
                return InteractionResult.SUCCESS;
            }
        }
        else{
            return InteractionResult.PASS;
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(NUMBER);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(NUMBER,0).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
