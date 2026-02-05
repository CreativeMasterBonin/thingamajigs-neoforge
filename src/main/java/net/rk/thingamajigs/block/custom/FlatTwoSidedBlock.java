package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class FlatTwoSidedBlock extends Block{
    public static final VoxelShape FLAT_PLANE = Block.box(0.0D,0.0D,0.0D,16.0D,1.0D,16.0D);

    public FlatTwoSidedBlock(Properties p) {
        super(p.strength(1f).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return FLAT_PLANE;
    }
}
