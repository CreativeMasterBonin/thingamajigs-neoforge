package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class DecorativePortalBlock extends Block{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape SIDE = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    public static final VoxelShape FRONT = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public DecorativePortalBlock(Properties p) {
        super(p.noCollission().mapColor(MapColor.WOOL).strength(2F,50F).pushReaction(PushReaction.DESTROY));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH, SOUTH:
                return SIDE;
            case EAST, WEST:
                return FRONT;
            default:
                return Shapes.block();
        }
    }

    @Override
    public boolean isStickyBlock(BlockState state) {
        return false;
    }

    @Override
    public boolean isPossibleToRespawnInThis(BlockState bs) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
