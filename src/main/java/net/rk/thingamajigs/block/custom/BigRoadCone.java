package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class BigRoadCone extends Block{
    private static final VoxelShape ALL = Stream.of(
            Block.box(2, 0, 2, 14, 1, 14),
            Block.box(6, 14, 6, 10, 22, 10),
            Block.box(3, 1, 3, 13, 5, 13),
            Block.box(4, 5, 4, 12, 9, 12),
            Block.box(5, 9, 5, 11, 13, 11),
            Block.box(6, 13, 6, 10, 14, 10),
            Block.box(6, 22, 6, 10, 24, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BigRoadCone(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }
}
