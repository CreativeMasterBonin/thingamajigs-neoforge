package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class TransparentFanBlock extends FanBlock{
    @Override
    public VoxelShape getVisualShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return Shapes.empty();
    }

    @Override
    public float getShadeBrightness(BlockState bs, BlockGetter bg, BlockPos bp) {
        return 1.0F;
    }

    public boolean propagatesSkylightDown(BlockState bs, BlockGetter bg, BlockPos bp) {
        return true;
    }
    
    @Override
    public boolean skipRendering(BlockState bs, BlockState adjbs, Direction dir) {
        return adjbs.is(this) || super.skipRendering(bs,adjbs,dir);
    }

    public TransparentFanBlock(Properties properties) {
        super(properties.noOcclusion());
    }
}
