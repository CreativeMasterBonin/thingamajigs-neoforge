package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

import java.util.stream.Stream;

public class ShortCarWashBrush extends CarWashBrush{
    public static final VoxelShape SHORT_ALL = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7, 1, 7, 9, 18, 9),
            Block.box(4, 16, 4, 12, 17, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public ShortCarWashBrush(Properties properties) {
        super(properties.strength(1f,4f));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean isHoldingCarWashBrush = context.isHoldingItem(TBlocks.CAR_WASH_MIXED_BRUSH.asItem());
        return isHoldingCarWashBrush ? Shapes.block() : SHORT_ALL;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHORT_ALL;
    }
}
