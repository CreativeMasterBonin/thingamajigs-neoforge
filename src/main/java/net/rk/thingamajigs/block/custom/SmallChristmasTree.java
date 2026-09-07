package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class SmallChristmasTree extends ThingamajigsDecorativeBlock{
    public SmallChristmasTree(Properties p) {
        super(p.sound(SoundType.AZALEA_LEAVES).noOcclusion().strength(1.15F,8F));
    }
    public static final VoxelShape ALL = Stream.of(
            Block.box(6, 0, 6, 10, 4, 10),
            Block.box(6, 0, 6, 10, 4, 6.5),
            Block.box(9.5, 0, 6, 10, 4, 10),
            Block.box(6, 0, 9.5, 10, 4, 10),
            Block.box(6, 0, 6, 6.5, 4, 10),
            Block.box(6, 0, 6, 10, 0, 10),
            Block.box(6.5, 3, 6.5, 9.5, 3, 9.5),
            Block.box(7.2, 3, 7.2, 8.7, 17, 8.7),
            Block.box(6, 15, 6, 10, 16, 10),
            Block.box(4, 11, 4, 12, 12, 12),
            Block.box(3, 9, 3, 13, 10, 13),
            Block.box(2, 7, 2, 14, 8, 14),
            Block.box(7, 17, 7, 9, 18, 9),
            Block.box(5, 13, 5, 11, 14, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return ALL;
    }
}
