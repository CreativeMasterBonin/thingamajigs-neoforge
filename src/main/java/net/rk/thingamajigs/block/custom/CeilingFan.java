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
public class CeilingFan extends ThingamajigsDecorativeBlock{
    public static final VoxelShape ALL = Stream.of(
            Block.box(3, 15, 3, 13, 16, 13),
            Block.box(6, 11, 6, 10, 15, 10),
            Block.box(5.5, 5, 5.5, 10.5, 10, 10.5),
            Block.box(5, 3, 5, 11, 5, 11),
            Block.box(7.5, 10, 7.5, 8.5, 11, 8.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CeilingFan(Properties properties) {
        super(properties.strength(1.5F));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return ALL;
    }
}
