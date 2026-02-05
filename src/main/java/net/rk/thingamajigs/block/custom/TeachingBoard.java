package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class TeachingBoard extends Block{
    public static final VoxelShape NORTH_SHAPE = Shapes.join(
            Block.box(8, 0, 15,
                    24, 16, 16),
            Block.box(-8, 0, 15,
                    8, 16, 16),
            BooleanOp.OR);
    public static final VoxelShape SOUTH_SHAPE = Shapes.join(Block.box(-8, 0, 0,
                    8, 16, 1),
            Block.box(8, 0, 0,
                    24, 16, 1),
            BooleanOp.OR);
    public static final VoxelShape EAST_SHAPE = Shapes.join(Block.box(0, 0, 8,
                    1, 16, 24),
            Block.box(0, 0, -8,
                    1, 16, 8),
            BooleanOp.OR);
    public static final VoxelShape WEST_SHAPE = Shapes.join(Block.box(15, 0, -8,
                    16, 16, 8),
            Block.box(15, 0, 8,
                    16, 16, 24),
            BooleanOp.OR);

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty TYPE = IntegerProperty.create("type",0,3);

    public TeachingBoard(BlockBehaviour.Properties properties) {
        super(properties.strength(1.5F,10F).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE,0).setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
        }
        return Shapes.block();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player player, BlockHitResult p_60508_) {
        if(player.isShiftKeyDown()){
            if(!lvl.isClientSide()){
                lvl.setBlock(bp,bs.cycle(TYPE),2);
                lvl.playSound(null,bp, SoundEvents.ITEM_FRAME_ROTATE_ITEM, SoundSource.BLOCKS,1.0F,1.0F);
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
        builder.add(FACING,TYPE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(TYPE,0).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
