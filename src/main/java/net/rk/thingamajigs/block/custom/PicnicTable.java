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
public class PicnicTable extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NS = Stream.of(
            Block.box(-4, 8, 0, 0, 9, 16),
            Block.box(16, 8, 0, 20, 9, 16),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(-2, 6, 0, 18, 8, 1),
            Block.box(-2, 6, 15, 18, 8, 16),
            Block.box(1, 13, 0, 15, 15, 1),
            Block.box(1, 13, 15, 15, 15, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EW = Stream.of(
            Block.box(0, 8, 16, 16, 9, 20),
            Block.box(0, 8, -4, 16, 9, 0),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(0, 6, -2, 1, 8, 18),
            Block.box(15, 6, -2, 16, 8, 18),
            Block.box(0, 13, 1, 1, 15, 15),
            Block.box(15, 13, 1, 16, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public PicnicTable(Properties properties) {
        super(properties.sound(SoundType.WOOD).strength(1.1f,1.5f));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction dir = bs.getValue(FACING);
        return switch (dir) {
            case NORTH, SOUTH -> NS;
            case EAST, WEST -> EW;
            default -> Shapes.block();
        };
    }
}
