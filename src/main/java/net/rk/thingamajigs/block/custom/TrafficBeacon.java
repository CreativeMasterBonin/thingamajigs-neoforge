package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class TrafficBeacon extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(7, 0, 7, 9, 8, 9),
            Block.box(5, 0, 2, 11, 6, 4),
            Block.box(5, 5, 0, 11, 6, 2),
            Block.box(5, 2, 0, 6, 5, 2),
            Block.box(10, 2, 0, 11, 5, 2),
            Block.box(7, 2, 4, 9, 4, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(7, 0, 7, 9, 8, 9),
            Block.box(2, 0, 5, 4, 6, 11),
            Block.box(0, 5, 5, 2, 6, 11),
            Block.box(0, 2, 10, 2, 5, 11),
            Block.box(0, 2, 5, 2, 5, 6),
            Block.box(4, 2, 7, 7, 4, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(7, 0, 7, 9, 8, 9),
            Block.box(5, 0, 12, 11, 6, 14),
            Block.box(5, 5, 14, 11, 6, 16),
            Block.box(10, 2, 14, 11, 5, 16),
            Block.box(5, 2, 14, 6, 5, 16),
            Block.box(7, 2, 9, 9, 4, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(7, 0, 7, 9, 8, 9),
            Block.box(12, 0, 5, 14, 6, 11),
            Block.box(14, 5, 5, 16, 6, 11),
            Block.box(14, 2, 5, 16, 5, 6),
            Block.box(14, 2, 10, 16, 5, 11),
            Block.box(9, 2, 7, 12, 4, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public TrafficBeacon(Properties properties) {
        super(properties.sound(SoundType.LANTERN).strength(1F,2F));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = state.getValue(FACING);
        switch (direction) {
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
        }
        return Shapes.block();
    }
}
