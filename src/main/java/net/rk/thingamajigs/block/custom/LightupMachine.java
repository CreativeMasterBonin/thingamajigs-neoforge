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
public class LightupMachine extends ThingamajigsDecorativeBlock{
    public static final VoxelShape ALL = Stream.of(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(3, 2, 3, 13, 4, 13),
            Block.box(6, 4, 6, 10, 14, 10),
            Block.box(-6, 14, -6, 22, 15, 22),
            Block.box(-5, 15, -5, 21, 31, 21)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public LightupMachine(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }
}
