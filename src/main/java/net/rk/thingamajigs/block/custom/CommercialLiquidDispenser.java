package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class CommercialLiquidDispenser extends ThingamajigsDecorativeBlock{
    public static final VoxelShape TALL_SHAPE = Block.box(3, 0, 3, 13, 16, 13);

    public CommercialLiquidDispenser(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return TALL_SHAPE;
    }
}
