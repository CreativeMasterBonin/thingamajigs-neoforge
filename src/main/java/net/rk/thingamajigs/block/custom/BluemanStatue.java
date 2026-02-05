package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

@SuppressWarnings("deprecated")
public class BluemanStatue extends ToggledStateBlock{
    public BluemanStatue(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return Optional.of(Block.box(4, 0, 4, 12, 23, 12)).get();
    }
}
