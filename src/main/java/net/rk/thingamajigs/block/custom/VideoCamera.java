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

@SuppressWarnings("deprecated")
public class VideoCamera extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(5, 20, 5, 11, 21, 11),
            Block.box(4, 21, 7, 12, 27, 17),
            Block.box(5, 21, 2, 11, 27, 7),
            Block.box(4, 21, 0, 12, 22, 2),
            Block.box(4, 26, 0, 12, 27, 2),
            Block.box(4, 22, 0, 5, 26, 2),
            Block.box(11, 22, 0, 12, 26, 2),
            Block.box(0, 27, 8, 6, 31, 9),
            Block.box(9, 27, 12, 13, 31, 22),
            Block.box(11, 30, 3, 13, 32, 9),
            Block.box(11, 30, 9, 13, 32, 12),
            Block.box(5, 11, 6, 10, 20, 11),
            Block.box(4, 3, 4, 12, 11, 12),
            Block.box(2, 0, 2, 13, 3, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(5, 20, 5, 11, 21, 11),
            Block.box(-1, 21, 4, 9, 27, 12),
            Block.box(9, 21, 5, 14, 27, 11),
            Block.box(14, 21, 4, 16, 22, 12),
            Block.box(14, 26, 4, 16, 27, 12),
            Block.box(14, 22, 4, 16, 26, 5),
            Block.box(14, 22, 11, 16, 26, 12),
            Block.box(7, 27, 0, 8, 31, 6),
            Block.box(-6, 27, 9, 4, 31, 13),
            Block.box(7, 30, 11, 13, 32, 13),
            Block.box(4, 30, 11, 7, 32, 13),
            Block.box(5, 11, 5, 10, 20, 10),
            Block.box(4, 3, 4, 12, 11, 12),
            Block.box(3, 0, 2, 14, 3, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(5, 20, 5, 11, 21, 11),
            Block.box(4, 21, -1, 12, 27, 9),
            Block.box(5, 21, 9, 11, 27, 14),
            Block.box(4, 21, 14, 12, 22, 16),
            Block.box(4, 26, 14, 12, 27, 16),
            Block.box(11, 22, 14, 12, 26, 16),
            Block.box(4, 22, 14, 5, 26, 16),
            Block.box(10, 27, 7, 16, 31, 8),
            Block.box(3, 27, -6, 7, 31, 4),
            Block.box(3, 30, 7, 5, 32, 13),
            Block.box(3, 30, 4, 5, 32, 7),
            Block.box(6, 11, 5, 11, 20, 10),
            Block.box(4, 3, 4, 12, 11, 12),
            Block.box(3, 0, 3, 14, 3, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(5, 20, 5, 11, 21, 11),
            Block.box(7, 21, 4, 17, 27, 12),
            Block.box(2, 21, 5, 7, 27, 11),
            Block.box(0, 21, 4, 2, 22, 12),
            Block.box(0, 26, 4, 2, 27, 12),
            Block.box(0, 22, 11, 2, 26, 12),
            Block.box(0, 22, 4, 2, 26, 5),
            Block.box(8, 27, 10, 9, 31, 16),
            Block.box(12, 27, 3, 22, 31, 7),
            Block.box(3, 30, 3, 9, 32, 5),
            Block.box(9, 30, 3, 12, 32, 5),
            Block.box(6, 11, 6, 11, 20, 11),
            Block.box(4, 3, 4, 12, 11, 12),
            Block.box(2, 0, 3, 13, 3, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public VideoCamera(Properties p) {
        super(p.strength(1f,3f).sound(SoundType.LANTERN));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (pState.getValue(FACING)){
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
