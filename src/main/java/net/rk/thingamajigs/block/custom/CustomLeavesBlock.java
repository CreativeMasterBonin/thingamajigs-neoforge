package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.IShearable;

import java.util.OptionalInt;

@SuppressWarnings("deprecated")
public class CustomLeavesBlock extends Block implements SimpleWaterloggedBlock, IShearable{
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final IntegerProperty DISTANCE = IntegerProperty.create("distance", 1, 12);

    public CustomLeavesBlock(Properties p) {
        super(p.mapColor(MapColor.PLANT).strength(0.2f).randomTicks());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(DISTANCE, 1)
                .setValue(PERSISTENT, false)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState bs, BlockGetter bg, BlockPos bp) {
        return Shapes.empty();
    }

    @Override
    public boolean isRandomlyTicking(BlockState bs) {
        return bs.getValue(DISTANCE) == 12 && !bs.getValue(PERSISTENT);
    }

    @Override
    public void randomTick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        if (this.decaythis(bs)) {
            dropResources(bs, sl, bp);
            sl.removeBlock(bp, false);
        }
    }

    @Override
    public void tick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        sl.setBlock(bp,updateDistanceV(bs,sl,bp),3);
    }

    @Override
    public int getLightBlock(BlockState bs, BlockGetter bg, BlockPos bp) {
        return 1;
    }

    private static BlockState updateDistanceV(BlockState bs, LevelAccessor la, BlockPos bp) {
        int i = 12;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        for(Direction direction : Direction.values()) {
            mutableBlockPos.setWithOffset(bp, direction);
            i = Math.min(i, getDistanceAtV(la.getBlockState(mutableBlockPos)) + 1);
            if (i == 1) {
                break;
            }
        }
        return bs.setValue(DISTANCE,i);
    }

    private static int getDistanceAtV(BlockState bs) {
        return getOptionalDistanceAtV(bs).orElse(12);
    }

    private boolean decaythis(BlockState bs) {
        return !bs.getValue(PERSISTENT) && bs.getValue(DISTANCE) == 12;
    }

    @Override
    public BlockState updateShape(BlockState bs, Direction dir, BlockState bs2, LevelAccessor la, BlockPos bp, BlockPos bp2) {
        if (bs.getValue(WATERLOGGED)) {
            la.scheduleTick(bp, Fluids.WATER, Fluids.WATER.getTickDelay(la));
        }
        int i = getDistanceAtV(bs2) + 1;
        if (i != 1 || bs.getValue(DISTANCE) != i) {
            la.scheduleTick(bp, this, 1);
        }
        return bs;
    }


    public static OptionalInt getOptionalDistanceAtV(BlockState bs) {
        if(bs.is(BlockTags.LOGS)) {
            return OptionalInt.of(0);
        }
        else{
            if(bs.hasProperty(DISTANCE)){
                return OptionalInt.of(bs.getValue(DISTANCE));
            }
            return OptionalInt.empty();
        }
    }

    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {
        if (lvl.isRainingAt(bp.above())) {
            if (rs.nextInt(32) == 1) {
                BlockPos blockpos = bp.below();
                BlockState blockstate = lvl.getBlockState(blockpos);
                if (!blockstate.canOcclude() || !blockstate.isFaceSturdy(lvl, blockpos, Direction.UP)) {
                    ParticleUtils.spawnParticleBelow(lvl, bp, rs, ParticleTypes.DRIPPING_WATER);
                }
            }
        }
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT, WATERLOGGED);
    }

    public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        FluidState fluidstate = bpc.getLevel().getFluidState(bpc.getClickedPos());
        BlockState blockstate = this.defaultBlockState().setValue(PERSISTENT,true).setValue(WATERLOGGED,fluidstate.getType() == Fluids.WATER);
        return updateDistanceV(blockstate, bpc.getLevel(), bpc.getClickedPos());
    }
}
