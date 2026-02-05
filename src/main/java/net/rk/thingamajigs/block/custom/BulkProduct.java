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
public class BulkProduct extends ThingamajigsDecorativeBlock{
    private static VoxelShape ALL = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 2, 0, 16, 3, 16),
            Block.box(0, 1, 0, 1, 2, 1),
            Block.box(15, 1, 0, 16, 2, 1),
            Block.box(15, 1, 15, 16, 2, 16),
            Block.box(0, 1, 15, 1, 2, 16),
            Block.box(0, 3, 0, 8, 11, 8),
            Block.box(0, 3, 8, 8, 11, 16),
            Block.box(8, 3, 0, 16, 11, 8),
            Block.box(8, 3, 8, 16, 11, 16),
            Block.box(0, 11, 0, 8, 19, 8),
            Block.box(0, 11, 8, 8, 19, 16),
            Block.box(8, 11, 0, 16, 19, 8),
            Block.box(8, 11, 8, 16, 19, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BulkProduct(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return ALL;
    }
}
