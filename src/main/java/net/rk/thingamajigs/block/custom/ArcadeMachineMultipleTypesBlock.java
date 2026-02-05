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
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class ArcadeMachineMultipleTypesBlock extends Block{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final int MIN_TYPES = 0;
    public static final int MAX_TYPES = 3;
    public static final IntegerProperty TYPE = IntegerProperty.create("type", MIN_TYPES, MAX_TYPES);

    // shapes
    public static final VoxelShape NORTH_S = Stream.of(
            Block.box(0, 0, 0, 16, 16, 11),
            Block.box(0, 0, 11, 16, 32, 16),
            Block.box(0, 30, 0, 16, 32, 11),
            Block.box(0, 16, 0, 2, 30, 11),
            Block.box(14, 16, 0, 16, 30, 11),
            Block.box(-0.019999999999999574, 0, 0, -0.019999999999999574, 32, 16),
            Block.box(16.02, 0, 0, 16.02, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_S = Stream.of(
            Block.box(0, 0, 5, 16, 16, 16),
            Block.box(0, 0, 0, 16, 32, 5),
            Block.box(0, 30, 5, 16, 32, 16),
            Block.box(14, 16, 5, 16, 30, 16),
            Block.box(0, 16, 5, 2, 30, 16),
            Block.box(16.02, 0, 0, 16.02, 32, 16),
            Block.box(-0.019999999999999574, 0, 0, -0.019999999999999574, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_S = Stream.of(
            Block.box(5, 0, 0, 16, 16, 16),
            Block.box(0, 0, 0, 5, 32, 16),
            Block.box(5, 30, 0, 16, 32, 16),
            Block.box(5, 16, 0, 16, 30, 2),
            Block.box(5, 16, 14, 16, 30, 16),
            Block.box(0, 0, -0.019999999999999574, 16, 32, -0.019999999999999574),
            Block.box(0, 0, 16.02, 16, 32, 16.02)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_S = Stream.of(
            Block.box(0, 0, 0, 11, 16, 16),
            Block.box(11, 0, 0, 16, 32, 16),
            Block.box(0, 30, 0, 11, 32, 16),
            Block.box(0, 16, 14, 11, 30, 16),
            Block.box(0, 16, 0, 11, 30, 2),
            Block.box(0, 0, 16.02, 16, 32, 16.02),
            Block.box(0, 0, -0.019999999999999574, 16, 32, -0.019999999999999574)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ArcadeMachineMultipleTypesBlock(Properties p) {
        super(p.strength(1.2F,1.5F).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(TYPE, 0));
    }

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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if(!lvl.isClientSide()){
            InteractionHand h = ply.getUsedItemHand();
            if(h == InteractionHand.MAIN_HAND && ply.isShiftKeyDown()){
                int arcade_machine_type = bs.getValue(TYPE);

                arcade_machine_type++;
                lvl.setBlock(bp,bs.setValue(TYPE, arcade_machine_type), 0);

                if(arcade_machine_type >= MAX_TYPES){
                    arcade_machine_type = 0;
                    lvl.setBlock(bp,bs.setValue(TYPE, arcade_machine_type), 0);
                }
                return InteractionResult.SUCCESS;
            }
            else {
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.sidedSuccess(lvl.isClientSide);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
