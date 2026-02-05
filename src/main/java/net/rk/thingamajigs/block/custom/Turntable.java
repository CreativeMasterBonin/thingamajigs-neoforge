package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class Turntable extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NS = Stream.of(
            Block.box(8, 0, 0, 24, 2, 16),
            Block.box(-8, 0, 0, 8, 2, 16),
            Block.box(-6, 0, 16.1, -4, 1, 16.1),
            Block.box(-3, 0, 16.1, 0, 1, 16.1),
            Block.box(4, 0, 16.1, 7, 1, 16.1),
            Block.box(7, 0, 16.1, 10, 1, 16.1),
            Block.box(21, 0, 16.1, 22, 1, 16.1),
            Block.box(9, 2, 0, 10, 3, 16),
            Block.box(6, 2, 0, 7, 3, 16),
            Block.box(-6, 2, 15, -3, 2.5, 16),
            Block.box(-2, 2, 15, 1, 2.5, 16),
            Block.box(2, 2, 15, 5, 2.5, 16),
            Block.box(11, 2, 15, 14, 2.5, 16),
            Block.box(15, 2, 15, 18, 2.5, 16),
            Block.box(19, 2, 15, 22, 2.5, 16),
            Block.box(5, 3, 6, 8, 4, 7),
            Block.box(8, 3, 8, 11, 4, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape ES = Stream.of(
            Block.box(0, 0, 8, 16, 2, 24),
            Block.box(0, 0, -8, 16, 2, 8),
            Block.box(-0.10000000000000142, 0, -6, -0.10000000000000142, 1, -4),
            Block.box(-0.10000000000000142, 0, -3, -0.10000000000000142, 1, 0),
            Block.box(-0.10000000000000142, 0, 4, -0.10000000000000142, 1, 7),
            Block.box(-0.10000000000000142, 0, 7, -0.10000000000000142, 1, 10),
            Block.box(-0.10000000000000142, 0, 21, -0.10000000000000142, 1, 22),
            Block.box(0, 2, 9, 16, 3, 10),
            Block.box(0, 2, 6, 16, 3, 7),
            Block.box(0, 2, -6, 1, 2.5, -3),
            Block.box(0, 2, -2, 1, 2.5, 1),
            Block.box(0, 2, 2, 1, 2.5, 5),
            Block.box(0, 2, 11, 1, 2.5, 14),
            Block.box(0, 2, 15, 1, 2.5, 18),
            Block.box(0, 2, 19, 1, 2.5, 22),
            Block.box(9, 3, 5, 10, 4, 8),
            Block.box(7, 3, 8, 8, 4, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SS = Stream.of(
            Block.box(-8, 0, 0, 8, 2, 16),
            Block.box(8, 0, 0, 24, 2, 16),
            Block.box(20, 0, -0.10000000000000142, 22, 1, -0.10000000000000142),
            Block.box(16, 0, -0.10000000000000142, 19, 1, -0.10000000000000142),
            Block.box(9, 0, -0.10000000000000142, 12, 1, -0.10000000000000142),
            Block.box(6, 0, -0.10000000000000142, 9, 1, -0.10000000000000142),
            Block.box(-6, 0, -0.10000000000000142, -5, 1, -0.10000000000000142),
            Block.box(6, 2, 0, 7, 3, 16),
            Block.box(9, 2, 0, 10, 3, 16),
            Block.box(19, 2, 0, 22, 2.5, 1),
            Block.box(15, 2, 0, 18, 2.5, 1),
            Block.box(11, 2, 0, 14, 2.5, 1),
            Block.box(2, 2, 0, 5, 2.5, 1),
            Block.box(-2, 2, 0, 1, 2.5, 1),
            Block.box(-6, 2, 0, -3, 2.5, 1),
            Block.box(8, 3, 9, 11, 4, 10),
            Block.box(5, 3, 7, 8, 4, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WS = Stream.of(
            Block.box(0, 0, -8, 16, 2, 8),
            Block.box(0, 0, 8, 16, 2, 24),
            Block.box(16.1, 0, 20, 16.1, 1, 22),
            Block.box(16.1, 0, 16, 16.1, 1, 19),
            Block.box(16.1, 0, 9, 16.1, 1, 12),
            Block.box(16.1, 0, 6, 16.1, 1, 9),
            Block.box(16.1, 0, -6, 16.1, 1, -5),
            Block.box(0, 2, 6, 16, 3, 7),
            Block.box(0, 2, 9, 16, 3, 10),
            Block.box(15, 2, 19, 16, 2.5, 22),
            Block.box(15, 2, 15, 16, 2.5, 18),
            Block.box(15, 2, 11, 16, 2.5, 14),
            Block.box(15, 2, 2, 16, 2.5, 5),
            Block.box(15, 2, -2, 16, 2.5, 1),
            Block.box(15, 2, -6, 16, 2.5, -3),
            Block.box(6, 3, 8, 7, 4, 11),
            Block.box(8, 3, 5, 9, 4, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Turntable(Properties p) {
        super(p.strength(1f,2f).sound(SoundType.LANTERN).pushReaction(PushReaction.DESTROY));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch(pState.getValue(FACING)){
            case NORTH -> {
                return NS;
            }
            case SOUTH -> {
                return SS;
            }
            case EAST -> {
                return ES;
            }
            case WEST -> {
                return WS;
            }
            default -> {
                return Shapes.block();
            }
        }
    }
}
