package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class CommercialJuiceDispenser extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 0, 0, 1, 1, 1),
            Block.box(15, 0, 0, 16, 1, 1),
            Block.box(15, 0, 15, 16, 1, 16),
            Block.box(0, 0, 15, 1, 1, 16),
            Block.box(0, 1, 0, 16, 5, 16),
            Block.box(0, 12, 3, 16, 16, 16),
            Block.box(0, 5, 5, 16, 12, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(15, 0, 0, 16, 1, 1),
            Block.box(15, 0, 15, 16, 1, 16),
            Block.box(0, 0, 15, 1, 1, 16),
            Block.box(0, 0, 0, 1, 1, 1),
            Block.box(0, 1, 0, 16, 5, 16),
            Block.box(0, 12, 0, 13, 16, 16),
            Block.box(0, 5, 0, 11, 12, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(15, 0, 15, 16, 1, 16),
            Block.box(0, 0, 15, 1, 1, 16),
            Block.box(0, 0, 0, 1, 1, 1),
            Block.box(15, 0, 0, 16, 1, 1),
            Block.box(0, 1, 0, 16, 5, 16),
            Block.box(0, 12, 0, 16, 16, 13),
            Block.box(0, 5, 0, 16, 12, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(0, 0, 15, 1, 1, 16),
            Block.box(0, 0, 0, 1, 1, 1),
            Block.box(15, 0, 0, 16, 1, 1),
            Block.box(15, 0, 15, 16, 1, 16),
            Block.box(0, 1, 0, 16, 5, 16),
            Block.box(3, 12, 0, 16, 16, 16),
            Block.box(5, 5, 0, 16, 12, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CommercialJuiceDispenser(Properties p) {
        super(p.noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH:return NORTH_SHAPE;
            case SOUTH:return SOUTH_SHAPE;
            case EAST:return EAST_SHAPE;
            case WEST:return WEST_SHAPE;
            default:return Shapes.block();
        }
    }
}
