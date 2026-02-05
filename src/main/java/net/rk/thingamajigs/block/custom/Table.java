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
public class Table extends Block{
    public static final VoxelShape ALL = Stream.of(
            Block.box(0, 14, 0, 16, 16, 16),
            Block.box(0, 0, 0, 2, 14, 2),
            Block.box(14, 0, 0, 16, 14, 2),
            Block.box(0, 0, 14, 2, 14, 16),
            Block.box(14, 0, 14, 16, 14, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Table(Properties p) {
        super(p.strength(1F,20F).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }
}
