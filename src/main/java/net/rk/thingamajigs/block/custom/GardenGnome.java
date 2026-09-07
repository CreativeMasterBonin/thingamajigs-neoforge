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

public class GardenGnome extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(6, 17, 7, 11, 18, 12),
            Block.box(2, 0, 3, 14, 1, 13),
            Block.box(6, 11, 6, 10, 15, 10),
            Block.box(5.5, 3, 5.5, 10.5, 11, 10.5),
            Block.box(6.5, 13.5, 12.5, 9.5, 16.5, 13.5),
            Block.box(7, 13.5, 13, 9, 15.5, 14),
            Block.box(3, 2, 8.05, 5, 4, 10.05),
            Block.box(11.02, 10.3, 1, 13.02, 10.8, 3),
            Block.box(11.02, 10.8, 1, 13.02, 12.3, 1.25),
            Block.box(10.92, 10.8, 1.0999999999999996, 11.17, 12.3, 3.0999999999999996),
            Block.box(11.02, 10.8, 2.9499999999999993, 13.02, 12.3, 3.1999999999999993),
            Block.box(12.87, 10.8, 1.0999999999999996, 13.12, 12.3, 3.0999999999999996),
            Block.box(11, 11, 1, 13, 11, 3),
            Block.box(11.75, 11, 1.75, 12.25, 14, 2.25),
            Block.box(10, 13, 0, 14, 16, 4),
            Block.box(7.5, 12.25, 5, 8.5, 13.25, 6),
            Block.box(6, 1, 2, 11, 3, 7),
            Block.box(6, 15, 6, 11, 17, 13),
            Block.box(11, 9, 2, 13, 11, 7),
            Block.box(4, 4, 8, 6, 10, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(4, 17, 6, 9, 18, 11),
            Block.box(3, 0, 2, 13, 1, 14),
            Block.box(6, 11, 6, 10, 15, 10),
            Block.box(5.5, 3, 5.5, 10.5, 11, 10.5),
            Block.box(2.5, 13.5, 6.5, 3.5, 16.5, 9.5),
            Block.box(2, 13.5, 7, 3, 15.5, 9),
            Block.box(5.949999999999999, 2, 3, 7.949999999999999, 4, 5),
            Block.box(13, 10.3, 11.02, 15, 10.8, 13.02),
            Block.box(14.75, 10.8, 11.02, 15, 12.3, 13.02),
            Block.box(12.9, 10.8, 10.92, 14.9, 12.3, 11.17),
            Block.box(12.8, 10.8, 11.02, 13.05, 12.3, 13.02),
            Block.box(12.9, 10.8, 12.87, 14.9, 12.3, 13.12),
            Block.box(13, 11, 11, 15, 11, 13),
            Block.box(13.75, 11, 11.75, 14.25, 14, 12.25),
            Block.box(12, 13, 10, 16, 16, 14),
            Block.box(10, 12.25, 7.5, 11, 13.25, 8.5),
            Block.box(9, 1, 6, 14, 3, 11),
            Block.box(3, 15, 6, 10, 17, 11),
            Block.box(9, 9, 11, 14, 11, 13),
            Block.box(6, 4, 4, 8, 10, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(5, 17, 4, 10, 18, 9),
            Block.box(2, 0, 3, 14, 1, 13),
            Block.box(6, 11, 6, 10, 15, 10),
            Block.box(5.5, 3, 5.5, 10.5, 11, 10.5),
            Block.box(6.5, 13.5, 2.5, 9.5, 16.5, 3.5),
            Block.box(7, 13.5, 2, 9, 15.5, 3),
            Block.box(11, 2, 5.949999999999999, 13, 4, 7.949999999999999),
            Block.box(2.9800000000000004, 10.3, 13, 4.98, 10.8, 15),
            Block.box(2.9800000000000004, 10.8, 14.75, 4.98, 12.3, 15),
            Block.box(4.83, 10.8, 12.9, 5.08, 12.3, 14.9),
            Block.box(2.9800000000000004, 10.8, 12.8, 4.98, 12.3, 13.05),
            Block.box(2.880000000000001, 10.8, 12.9, 3.130000000000001, 12.3, 14.9),
            Block.box(3, 11, 13, 5, 11, 15),
            Block.box(3.75, 11, 13.75, 4.25, 14, 14.25),
            Block.box(2, 13, 12, 6, 16, 16),
            Block.box(7.5, 12.25, 10, 8.5, 13.25, 11),
            Block.box(5, 1, 9, 10, 3, 14),
            Block.box(5, 15, 3, 10, 17, 10),
            Block.box(3, 9, 9, 5, 11, 14),
            Block.box(10, 4, 6, 12, 10, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(7, 17, 5, 12, 18, 10),
            Block.box(3, 0, 2, 13, 1, 14),
            Block.box(6, 11, 6, 10, 15, 10),
            Block.box(5.5, 3, 5.5, 10.5, 11, 10.5),
            Block.box(12.5, 13.5, 6.5, 13.5, 16.5, 9.5),
            Block.box(13, 13.5, 7, 14, 15.5, 9),
            Block.box(8.05, 2, 11, 10.05, 4, 13),
            Block.box(1, 10.3, 2.9800000000000004, 3, 10.8, 4.98),
            Block.box(1, 10.8, 2.9800000000000004, 1.25, 12.3, 4.98),
            Block.box(1.0999999999999996, 10.8, 4.83, 3.0999999999999996, 12.3, 5.08),
            Block.box(2.9499999999999993, 10.8, 2.9800000000000004, 3.1999999999999993, 12.3, 4.98),
            Block.box(1.0999999999999996, 10.8, 2.880000000000001, 3.0999999999999996, 12.3, 3.130000000000001),
            Block.box(1, 11, 3, 3, 11, 5),
            Block.box(1.75, 11, 3.75, 2.25, 14, 4.25),
            Block.box(0, 13, 2, 4, 16, 6),
            Block.box(5, 12.25, 7.5, 6, 13.25, 8.5),
            Block.box(2, 1, 5, 7, 3, 10),
            Block.box(6, 15, 5, 13, 17, 10),
            Block.box(2, 9, 3, 7, 11, 5),
            Block.box(8, 4, 10, 10, 10, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public GardenGnome(Properties p) {
        super(p.sound(SoundType.DECORATED_POT).strength(1.25F));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
