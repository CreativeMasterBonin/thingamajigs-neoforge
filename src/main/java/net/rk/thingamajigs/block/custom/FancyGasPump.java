package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class FancyGasPump extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(-4, 0, 0, 20, 2, 16),
            Block.box(0, 2, 2, 16, 16, 14),
            Block.box(1, 13, 0, 5, 18, 2),
            Block.box(6, 13, 0, 10, 18, 2),
            Block.box(11, 13, 0, 15, 18, 2),
            Block.box(0, 16, 2, 16, 30, 14),
            Block.box(-6, 30, 0, 8, 32, 16),
            Block.box(8, 30, 0, 22, 32, 16),
            Block.box(-3, 2, 5, 0, 12, 11),
            Block.box(-2, 21, 4, 0, 23, 12),
            Block.box(16, 6, 7, 17, 24, 9),
            Block.box(16, 24, 4, 18, 26, 12),
            Block.box(16, 22, 5, 19, 24, 6),
            Block.box(16, 22, 10, 19, 24, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, -4, 16, 2, 20),
            Block.box(2, 2, 0, 14, 16, 16),
            Block.box(14, 13, 1, 16, 18, 5),
            Block.box(14, 13, 6, 16, 18, 10),
            Block.box(14, 13, 11, 16, 18, 15),
            Block.box(2, 16, 0, 14, 30, 16),
            Block.box(0, 30, -6, 16, 32, 8),
            Block.box(0, 30, 8, 16, 32, 22),
            Block.box(5, 2, -3, 11, 12, 0),
            Block.box(4, 21, -2, 12, 23, 0),
            Block.box(7, 6, 16, 9, 24, 17),
            Block.box(4, 24, 16, 12, 26, 18),
            Block.box(10, 22, 16, 11, 24, 19),
            Block.box(5, 22, 16, 6, 24, 19)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(-4, 0, 0, 20, 2, 16),
            Block.box(0, 2, 2, 16, 16, 14),
            Block.box(11, 13, 14, 15, 18, 16),
            Block.box(6, 13, 14, 10, 18, 16),
            Block.box(1, 13, 14, 5, 18, 16),
            Block.box(0, 16, 2, 16, 30, 14),
            Block.box(8, 30, 0, 22, 32, 16),
            Block.box(-6, 30, 0, 8, 32, 16),
            Block.box(16, 2, 5, 19, 12, 11),
            Block.box(16, 21, 4, 18, 23, 12),
            Block.box(-1, 6, 7, 0, 24, 9),
            Block.box(-2, 24, 4, 0, 26, 12),
            Block.box(-3, 22, 10, 0, 24, 11),
            Block.box(-3, 22, 5, 0, 24, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, -4, 16, 2, 20),
            Block.box(2, 2, 0, 14, 16, 16),
            Block.box(0, 13, 11, 2, 18, 15),
            Block.box(0, 13, 6, 2, 18, 10),
            Block.box(0, 13, 1, 2, 18, 5),
            Block.box(2, 16, 0, 14, 30, 16),
            Block.box(0, 30, 8, 16, 32, 22),
            Block.box(0, 30, -6, 16, 32, 8),
            Block.box(5, 2, 16, 11, 12, 19),
            Block.box(4, 21, 16, 12, 23, 18),
            Block.box(7, 6, -1, 9, 24, 0),
            Block.box(4, 24, -2, 12, 26, 0),
            Block.box(5, 22, -3, 6, 24, 0),
            Block.box(10, 22, -3, 11, 24, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public FancyGasPump(Properties p) {
        super(p.strength(1f,15f).sound(SoundType.METAL)
                .mapColor(MapColor.METAL).instrument(NoteBlockInstrument.BIT));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch(state.getValue(FACING)){
            case NORTH -> {
                return NORTH;
            }
            case SOUTH -> {
                return SOUTH;
            }
            case EAST -> {
                return EAST;
            }
            case WEST -> {
                return WEST;
            }
            default -> {
                return Shapes.block();
            }
        }
    }
}
