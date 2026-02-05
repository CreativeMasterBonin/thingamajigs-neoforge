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

public class BarberHairDryer extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 8, 14, 16, 16, 16),
            Block.box(6, 10, 13, 10, 13, 14),
            Block.box(0, 0, 0, 1, 16, 13),
            Block.box(15, 0, 0, 16, 16, 13),
            Block.box(1, 0, 12, 15, 16, 13),
            Block.box(1, 15, 0, 15, 16, 12),
            Block.box(1, 9, 0, 15, 15, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 8, 0, 2, 16, 16),
            Block.box(2, 10, 6, 3, 13, 10),
            Block.box(3, 0, 0, 16, 16, 1),
            Block.box(3, 0, 15, 16, 16, 16),
            Block.box(3, 0, 1, 4, 16, 15),
            Block.box(4, 15, 1, 16, 16, 15),
            Block.box(15, 9, 1, 16, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 8, 0, 16, 16, 2),
            Block.box(6, 10, 2, 10, 13, 3),
            Block.box(15, 0, 3, 16, 16, 16),
            Block.box(0, 0, 3, 1, 16, 16),
            Block.box(1, 0, 3, 15, 16, 4),
            Block.box(1, 15, 4, 15, 16, 16),
            Block.box(1, 9, 15, 15, 15, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(14, 8, 0, 16, 16, 16),
            Block.box(13, 10, 6, 14, 13, 10),
            Block.box(0, 0, 15, 13, 16, 16),
            Block.box(0, 0, 0, 13, 16, 1),
            Block.box(12, 0, 1, 13, 16, 15),
            Block.box(0, 15, 1, 12, 16, 15),
            Block.box(0, 9, 1, 1, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BarberHairDryer(Properties properties) {
        super(properties.strength(1f,10f).sound(SoundType.LANTERN)
                .noOcclusion().pushReaction(PushReaction.BLOCK));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
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
