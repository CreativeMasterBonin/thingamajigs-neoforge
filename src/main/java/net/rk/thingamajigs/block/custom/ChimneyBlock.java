package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.xtras.TParticles;

import java.util.stream.Stream;

public class ChimneyBlock extends Block implements SimpleWaterloggedBlock {
    public static final VoxelShape ALL = Stream.of(
            Block.box(0, 0, 0, 16, 3, 16),
            Block.box(1, 3, 1, 15, 14, 3),
            Block.box(1, 3, 13, 15, 14, 15),
            Block.box(13, 3, 3, 15, 14, 13),
            Block.box(1, 3, 3, 3, 14, 13),
            Block.box(0, 14, 0, 14, 16, 2),
            Block.box(14, 14, 0, 16, 16, 14),
            Block.box(2, 14, 14, 16, 16, 16),
            Block.box(0, 14, 2, 2, 16, 16),
            Block.box(3, 3.02, 3, 13, 3.02, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public ChimneyBlock(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {
        double d0 = (double)bp.getX() + 0.5D;
        double d1 = (double)bp.getY() + 0.85D;
        double d2 = (double)bp.getZ() + 0.5D;

        if(lvl.getGameTime() % 6 == 0)
            lvl.addParticle(TParticles.CHIMNEY_SMOKE.get(),
                    d0, d1, d2,
                    Mth.randomBetween(rs,-0.02f,0.02f),
                    0.037,
                    Mth.randomBetween(rs,-0.02f,0.02f));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return ALL;
    }

    public BlockState updateShape(BlockState bs1, Direction dir, BlockState bs2, LevelAccessor lvla, BlockPos bp1, BlockPos bp2) {
        if (bs1.getValue(WATERLOGGED)) {
            lvla.scheduleTick(bp1, Fluids.WATER,Fluids.WATER.getTickDelay(lvla));
        }
        return bs1;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
