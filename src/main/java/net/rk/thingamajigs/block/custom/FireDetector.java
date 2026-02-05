package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class FireDetector extends Block{
    public static final VoxelShape FIRE_DETECTOR_SHAPE_ALL = Stream.of(
            Block.box(4, 15, 4, 12, 16, 12),
            Block.box(4.5, 14.8, 4.5, 11.5, 15, 11.5),
            Block.box(5, 14.6, 5, 6, 14.85, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public FireDetector(Properties p) {
        super(p.strength(0.5F,25F).sound(SoundType.LANTERN));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return FIRE_DETECTOR_SHAPE_ALL;
    }

    @Override
    public boolean canSurvive(BlockState bs, LevelReader lvlr, BlockPos bp) {
        return lvlr.getBlockState(bp.above()).isFaceSturdy(lvlr, bp.above(), Direction.DOWN);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState state2, LevelAccessor lvlAc, BlockPos bp1, BlockPos bp2) {
        return !this.canSurvive(state, lvlAc, bp1) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, dir, state2, lvlAc, bp1, bp2);
    }
}
