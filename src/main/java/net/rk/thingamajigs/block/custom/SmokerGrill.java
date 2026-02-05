package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class SmokerGrill extends ToggledStateBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static BooleanProperty TOGGLED = BlockStateProperties.ENABLED;

    public static final VoxelShape ALL = Stream.of(
            Block.box(14, 0, 0, 16, 5, 2),
            Block.box(14, 0, 14, 16, 5, 16),
            Block.box(0, 0, 0, 2, 5, 2),
            Block.box(0, 0, 14, 2, 5, 16),
            Block.box(0, 5, 0, 16, 9, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public SmokerGrill(Properties p) {
        super(p);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TOGGLED, false).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return ALL;
    }

    @Override
    public void animateTick(BlockState state, Level lvl, BlockPos pos, RandomSource rnd) {
        if (state.getValue(TOGGLED)) {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ() + 0.5D;
            if (rnd.nextDouble() < 0.1D) {
                lvl.playLocalSound(d0, d1, d2, SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
            }

            lvl.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, d0, d1 + 0.8D, d2, 0.0D, 0.0D, 0.0D);
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
            lvl.playSound(null,bp,SoundEvents.SMOKER_SMOKE, SoundSource.BLOCKS,1f,1f);
        }
        else{
            lvl.playSound(null,bp,SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS,0.5f,1f);
        }
    }
}
