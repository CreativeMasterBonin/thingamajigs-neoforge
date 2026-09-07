package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
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
public class RecycleBin extends ThingamajigsDecorativeBlock{
    public static final int MIN_TYPES = 0;
    public static final int MAX_TYPES = 9;
    public static final IntegerProperty TYPE = IntegerProperty.create("type", MIN_TYPES, MAX_TYPES);
    public static final VoxelShape BLOCK_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 32.0D, 16.0D);
    public static final VoxelShape NORTH = Stream.of(
            Block.box(-1, 1, 11, 17, 3, 13),
            Block.box(0, 0, 10, 1, 4, 14),
            Block.box(15, 0, 10, 16, 4, 14),
            Block.box(7, 23, 14, 9, 25, 15),
            Block.box(5, 25, 14, 11, 26, 15),
            Block.box(2, 2, 1, 14, 3, 14),
            Block.box(1, 3, 0, 15, 27, 14),
            Block.box(15, 26, 0, 16, 27, 14),
            Block.box(0, 26, -1, 16, 27, 0),
            Block.box(0, 26, 14, 16, 27, 15),
            Block.box(0, 26, 0, 1, 27, 14),
            Block.box(1, 27, 0, 15, 28, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(3, 1, -1, 5, 3, 17),
            Block.box(2, 0, 0, 6, 4, 1),
            Block.box(2, 0, 15, 6, 4, 16),
            Block.box(1, 23, 7, 2, 25, 9),
            Block.box(1, 25, 5, 2, 26, 11),
            Block.box(2, 2, 2, 15, 3, 14),
            Block.box(2, 3, 1, 16, 27, 15),
            Block.box(2, 26, 15, 16, 27, 16),
            Block.box(16, 26, 0, 17, 27, 16),
            Block.box(1, 26, 0, 2, 27, 16),
            Block.box(2, 26, 0, 16, 27, 1),
            Block.box(2, 27, 1, 16, 28, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(-1, 1, 3, 17, 3, 5),
            Block.box(15, 0, 2, 16, 4, 6),
            Block.box(0, 0, 2, 1, 4, 6),
            Block.box(7, 23, 1, 9, 25, 2),
            Block.box(5, 25, 1, 11, 26, 2),
            Block.box(2, 2, 2, 14, 3, 15),
            Block.box(1, 3, 2, 15, 27, 16),
            Block.box(0, 26, 2, 1, 27, 16),
            Block.box(0, 26, 16, 16, 27, 17),
            Block.box(0, 26, 1, 16, 27, 2),
            Block.box(15, 26, 2, 16, 27, 16),
            Block.box(1, 27, 2, 15, 28, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(11, 1, -1, 13, 3, 17),
            Block.box(10, 0, 15, 14, 4, 16),
            Block.box(10, 0, 0, 14, 4, 1),
            Block.box(14, 23, 7, 15, 25, 9),
            Block.box(14, 25, 5, 15, 26, 11),
            Block.box(1, 2, 2, 14, 3, 14),
            Block.box(0, 3, 1, 14, 27, 15),
            Block.box(0, 26, 0, 14, 27, 1),
            Block.box(-1, 26, 0, 0, 27, 16),
            Block.box(14, 26, 0, 15, 27, 16),
            Block.box(0, 26, 15, 14, 27, 16),
            Block.box(0, 27, 1, 14, 28, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public RecycleBin(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE).setValue(TYPE, 0));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if(!lvl.isClientSide()){
            if(ply.getUsedItemHand() == InteractionHand.MAIN_HAND && ply.isShiftKeyDown()){
                int tv_type = bs.getValue(TYPE);
                tv_type++;
                lvl.setBlock(bp, bs.setValue(TYPE, tv_type), 0);

                if(tv_type >= MAX_TYPES){
                    tv_type = 0;
                    lvl.setBlock(bp, bs.setValue(TYPE, tv_type), 0);
                }
                return InteractionResult.SUCCESS;
            }
            else {
                return InteractionResult.CONSUME;
            }
        }
        return super.useWithoutItem(bs,lvl,bp,ply,bhr);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default->{return BLOCK_SHAPE;}
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TYPE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER).setValue(TYPE, 0);
    }
}
