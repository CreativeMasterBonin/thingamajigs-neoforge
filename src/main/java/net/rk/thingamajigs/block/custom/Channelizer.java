package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class Channelizer extends Block{
    private static final VoxelShape ALL = Shapes.join(Block.box(3, 0, 3, 13, 1, 13), Block.box(6, 0, 6, 10, 22, 10), BooleanOp.OR);

    public Channelizer(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }
}
