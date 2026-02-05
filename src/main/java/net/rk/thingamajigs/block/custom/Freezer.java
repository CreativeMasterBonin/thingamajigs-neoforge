package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
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
public class Freezer extends ToggledStateBlock{
    public static final VoxelShape ALL = Stream.of(
            Block.box(14, 0, 0, 16, 1, 2),
            Block.box(0, 0, 14, 2, 1, 16),
            Block.box(14, 0, 14, 16, 1, 16),
            Block.box(0, 0, 0, 2, 1, 2),
            Block.box(0, 2, 15, 16, 16, 16),
            Block.box(0, 2, 0, 1, 16, 15),
            Block.box(15, 2, 0, 16, 16, 15),
            Block.box(0, 1, 0, 16, 2, 16),
            Block.box(1, 2, 0, 15, 16, 1),
            Block.box(1, 3, 1, 5, 4, 8),
            Block.box(1, 2, 1, 7, 3, 9),
            Block.box(11, 3, 8.5, 15, 4, 15.5),
            Block.box(9, 2, 7.5, 15, 3, 15.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Freezer(Properties p) {
        super(p.sound(SoundType.STONE).strength(1F,10F).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TOGGLED, false).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        Boolean aBoolean = pState.getValue(TOGGLED);
        if (aBoolean){
            return ALL;
        }
        else{
            return Shapes.block();
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
            lvl.playSound(null,bp, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.BLOCKS,1f,1f);
        }
        else{
            lvl.playSound(null,bp,SoundEvents.IRON_GOLEM_STEP, SoundSource.BLOCKS,1f,1f);
        }
    }
}
