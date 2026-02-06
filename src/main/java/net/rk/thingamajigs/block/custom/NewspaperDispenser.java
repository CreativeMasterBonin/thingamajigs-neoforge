package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class NewspaperDispenser extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(2, 0, 3, 14, 14, 13),
            Block.box(2, 14, 3, 14, 20, 13),
            Block.box(2, 9, 1, 14, 10, 3),
            Block.box(2, 19, 0, 14, 20, 3),
            Block.box(2, 13, 0, 14, 19, 1),
            Block.box(14, 14, 1, 15, 20, 3),
            Block.box(1, 14, 1, 2, 20, 3),
            Block.box(3, 10, 1.2, 13, 18, 1.2),
            Block.box(3, 10, 1.5, 13, 18, 1.5),
            Block.box(3, 10, 1.95, 13, 18, 1.95),
            Block.box(3, 10, 2.5, 13, 18, 2.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(3, 0, 2, 13, 14, 14),
            Block.box(3, 14, 2, 13, 20, 14),
            Block.box(13, 9, 2, 15, 10, 14),
            Block.box(13, 19, 2, 16, 20, 14),
            Block.box(15, 13, 2, 16, 19, 14),
            Block.box(13, 14, 14, 15, 20, 15),
            Block.box(13, 14, 1, 15, 20, 2),
            Block.box(14.8, 10, 3, 14.8, 18, 13),
            Block.box(14.5, 10, 3, 14.5, 18, 13),
            Block.box(14.05, 10, 3, 14.05, 18, 13),
            Block.box(13.5, 10, 3, 13.5, 18, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(2, 0, 3, 14, 14, 13),
            Block.box(2, 14, 3, 14, 20, 13),
            Block.box(2, 9, 13, 14, 10, 15),
            Block.box(2, 19, 13, 14, 20, 16),
            Block.box(2, 13, 15, 14, 19, 16),
            Block.box(1, 14, 13, 2, 20, 15),
            Block.box(14, 14, 13, 15, 20, 15),
            Block.box(3, 10, 14.8, 13, 18, 14.8),
            Block.box(3, 10, 14.5, 13, 18, 14.5),
            Block.box(3, 10, 14.05, 13, 18, 14.05),
            Block.box(3, 10, 13.5, 13, 18, 13.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE =Stream.of(
            Block.box(3, 0, 2, 13, 14, 14),
            Block.box(3, 14, 2, 13, 20, 14),
            Block.box(1, 9, 2, 3, 10, 14),
            Block.box(0, 19, 2, 3, 20, 14),
            Block.box(0, 13, 2, 1, 19, 14),
            Block.box(1, 14, 1, 3, 20, 2),
            Block.box(1, 14, 14, 3, 20, 15),
            Block.box(1.1999999999999993, 10, 3, 1.1999999999999993, 18, 13),
            Block.box(1.5, 10, 3, 1.5, 18, 13),
            Block.box(1.9499999999999993, 10, 3, 1.9499999999999993, 18, 13),
            Block.box(2.5, 10, 3, 2.5, 18, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_TRASH_SHAPE = Stream.of(
            Block.box(1, 0, 3, 15, 14, 13),
            Block.box(1, 14, 3, 15, 20, 13),
            Block.box(2, 13, 0, 14, 14, 3),
            Block.box(2, 19, 0, 14, 20, 3),
            Block.box(14, 13, 0, 15, 20, 3),
            Block.box(1, 13, 0, 2, 20, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_TRASH_SHAPE = Stream.of(
            Block.box(3, 0, 1, 13, 14, 15),
            Block.box(3, 14, 1, 13, 20, 15),
            Block.box(13, 13, 2, 16, 14, 14),
            Block.box(13, 19, 2, 16, 20, 14),
            Block.box(13, 13, 14, 16, 20, 15),
            Block.box(13, 13, 1, 16, 20, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_TRASH_SHAPE = Stream.of(
            Block.box(1, 0, 3, 15, 14, 13),
            Block.box(1, 14, 3, 15, 20, 13),
            Block.box(2, 13, 13, 14, 14, 16),
            Block.box(2, 19, 13, 14, 20, 16),
            Block.box(1, 13, 13, 2, 20, 16),
            Block.box(14, 13, 13, 15, 20, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_TRASH_SHAPE = Stream.of(
            Block.box(3, 0, 1, 13, 14, 15),
            Block.box(3, 14, 1, 13, 20, 15),
            Block.box(0, 13, 2, 3, 14, 14),
            Block.box(0, 19, 2, 3, 20, 14),
            Block.box(0, 13, 1, 3, 20, 2),
            Block.box(0, 13, 14, 3, 20, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public NewspaperDispenser(Properties p) {
        super(p.sound(SoundType.WOOD).instrument(NoteBlockInstrument.DIDGERIDOO));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch(state.getValue(FACING)){
            case NORTH -> {
                return NORTH_SHAPE;
            }
            case SOUTH -> {
                return SOUTH_SHAPE;
            }
            case EAST -> {
                return EAST_SHAPE;
            }
            case WEST -> {
                return WEST_SHAPE;
            }
            default -> {
                return Shapes.block();
            }
        }
    }
}
