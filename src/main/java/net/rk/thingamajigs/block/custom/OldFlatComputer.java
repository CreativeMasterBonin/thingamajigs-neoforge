package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class OldFlatComputer extends ThingamajigsDecorativeBlock{
    public static final VoxelShape SHAPE_ALL = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);

    public OldFlatComputer(Properties properties) {
        super(properties.strength(1F,11F).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return SHAPE_ALL;
    }
}
