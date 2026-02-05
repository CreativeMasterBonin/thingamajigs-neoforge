package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

@SuppressWarnings("deprecated")
public class RoadCoveringBlock extends Block{
    public static final VoxelShape COMMON_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public RoadCoveringBlock(Properties p) {
        super(p.noCollission());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return COMMON_SHAPE;
    }

    @Override
    public BlockState updateShape(BlockState bs, Direction dir, BlockState bs2, LevelAccessor la, BlockPos bp, BlockPos bp2) {
        return !bs.canSurvive(la, bp) ? Blocks.AIR.defaultBlockState() : super.updateShape(bs, dir, bs2, la, bp, bp2);
    }

    @Override
    public boolean canSurvive(BlockState bs, LevelReader lr, BlockPos bp) {
        BlockState cbb = lr.getBlockState(bp.below());
        boolean isRoadCover = cbb.is(TBlocks.ROAD_COVER.get());
        boolean isAltRoadCover = cbb.is(TBlocks.ALT_ROAD_COVER.get());
        boolean isRoadPanelCover = cbb.is(TBlocks.ROAD_PANEL_COVER.get());
        boolean isAltRoadPanelCover = cbb.is(TBlocks.ALT_ROAD_PANEL_COVER.get());

        if(isRoadCover || isAltRoadCover || isRoadPanelCover || isAltRoadPanelCover) {
            return false;
        }
        return Block.isFaceFull(cbb.getCollisionShape(lr, bp.below()), Direction.UP);
    }
}
