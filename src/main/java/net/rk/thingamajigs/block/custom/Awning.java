package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class Awning extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 12, 9, 16, 15, 13),
            Block.box(0, 12, 15, 16, 16, 16),
            Block.box(0, 6, -7, 16, 8, -3),
            Block.box(0, 8, -3, 16, 10, 1),
            Block.box(0, 9, 1, 16, 11, 5),
            Block.box(0, 11, 5, 16, 13, 9),
            Block.box(0, 14, 13, 16, 16, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(3, 12, 0, 7, 15, 16),
            Block.box(0, 12, 0, 1, 16, 16),
            Block.box(19, 6, 0, 23, 8, 16),
            Block.box(15, 8, 0, 19, 10, 16),
            Block.box(11, 9, 0, 15, 11, 16),
            Block.box(7, 11, 0, 11, 13, 16),
            Block.box(1, 14, 0, 3, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 12, 3, 16, 15, 7),
            Block.box(0, 12, 0, 16, 16, 1),
            Block.box(0, 6, 19, 16, 8, 23),
            Block.box(0, 8, 15, 16, 10, 19),
            Block.box(0, 9, 11, 16, 11, 15),
            Block.box(0, 11, 7, 16, 13, 11),
            Block.box(0, 14, 1, 16, 16, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(9, 12, 0, 13, 15, 16),
            Block.box(15, 12, 0, 16, 16, 16),
            Block.box(-7, 6, 0, -3, 8, 16),
            Block.box(-3, 8, 0, 1, 10, 16),
            Block.box(1, 9, 0, 5, 11, 16),
            Block.box(5, 11, 0, 9, 13, 16),
            Block.box(13, 14, 0, 15, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ALL_OLD = Block.box(0,8, 0,16,16,16);

    public Awning(BlockBehaviour.Properties properties) {
        super(properties.strength(0.5F,0.25F).sound(SoundType.WOOL).noCollission());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default -> {return ALL_OLD;}
        }
    }
}
