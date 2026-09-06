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

import static net.rk.thingamajigs.xtras.GeneralUseShapes.OLD_FAX_MACHINE_ALL;

@SuppressWarnings("deprecated")
public class FaxMachine extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(16, 0, 0, 20, 2, 15),
            Block.box(16, 2, 0, 20, 4, 4),
            Block.box(16, 2, 11, 20, 4, 15),
            Block.box(17, 4, 1, 19, 6, 14),
            Block.box(5, 4, 1, 14, 5, 7),
            Block.box(1, 4, 1, 4, 5, 3),
            Block.box(1, 4, 5, 4, 5, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(1, 0, 16, 16, 2, 20),
            Block.box(12, 2, 16, 16, 4, 20),
            Block.box(1, 2, 16, 5, 4, 20),
            Block.box(2, 4, 17, 15, 6, 19),
            Block.box(9, 4, 5, 15, 5, 14),
            Block.box(13, 4, 1, 15, 5, 4),
            Block.box(9, 4, 1, 11, 5, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(-4, 0, 1, 0, 2, 16),
            Block.box(-4, 2, 12, 0, 4, 16),
            Block.box(-4, 2, 1, 0, 4, 5),
            Block.box(-3, 4, 2, -1, 6, 15),
            Block.box(2, 4, 9, 11, 5, 15),
            Block.box(12, 4, 13, 15, 5, 15),
            Block.box(12, 4, 9, 15, 5, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 0, 16, 4, 16),
            Block.box(0, 0, -4, 15, 2, 0),
            Block.box(0, 2, -4, 4, 4, 0),
            Block.box(11, 2, -4, 15, 4, 0),
            Block.box(1, 4, -3, 14, 6, -1),
            Block.box(1, 4, 2, 7, 5, 11),
            Block.box(1, 4, 12, 3, 5, 15),
            Block.box(5, 4, 12, 7, 5, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public FaxMachine(Properties properties) {
        super(properties.strength(1F,5F).sound(SoundType.LANTERN));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch (bs.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default -> {return OLD_FAX_MACHINE_ALL;}
        }
    }
}
