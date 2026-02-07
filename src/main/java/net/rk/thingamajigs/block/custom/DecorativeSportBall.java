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

public class DecorativeSportBall extends ThingamajigsDecorativeBlock{
    public static final VoxelShape ALL = Block.box(4, 0, 4, 12, 8, 12);

    public static final VoxelShape SINGLE_TENNIS_BALL =
            Block.box(6, 0, 6, 10, 4, 10);
    public static final VoxelShape DOUBLE_TENNIS_BALL_TRIPLE_TENNIS_BALL = Stream.of(
            Block.box(3, 0, 3, 13, 4, 13),
            Block.box(9, 0, 6, 13, 4, 10),
            Block.box(3, 0, 6, 7, 4, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape QUADRUPLE_TENNIS_BALL = Stream.of(
            Block.box(3, 0, 3, 13, 8, 13),
            Block.box(9, 0, 9, 13, 4, 13),
            Block.box(3, 0, 9, 7, 4, 13),
            Block.box(6, 0, 3, 10, 4, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public DecorativeSportBall(Properties properties) {
        super(properties.sound(SoundType.CANDLE).strength(1f,10f).instabreak());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return ALL;
    }
}
