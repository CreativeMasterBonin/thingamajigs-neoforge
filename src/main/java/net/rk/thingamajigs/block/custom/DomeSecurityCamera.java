package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class DomeSecurityCamera extends ThingamajigsDecorativeBlock{
    public static final VoxelShape ALL = Shapes.join(Block.box(5, 14, 5, 11, 16, 11),
            Block.box(6, 12, 6, 10, 14, 10), BooleanOp.OR);
    public DomeSecurityCamera(Properties properties) {
        super(properties.strength(1F,5F).noOcclusion());
    }

    @Override
    public boolean canSurvive(BlockState bs, LevelReader lvlr, BlockPos bp) {
        return lvlr.getBlockState(bp.above()).isFaceSturdy(lvlr, bp.above(), Direction.DOWN);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }
}
