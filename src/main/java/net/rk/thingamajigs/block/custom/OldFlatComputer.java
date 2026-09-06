package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class OldFlatComputer extends ThingamajigsDecorativeBlock{
    public static final VoxelShape SHAPE_ALL = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);

    public static final VoxelShape WIFI_NORTH = Stream.of(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(3, 2, 11, 4.5, 4, 12),
            Block.box(3, 4, 11, 4, 6, 12),
            Block.box(1, 6, 11, 3, 9, 12),
            Block.box(11.5, 2, 11, 13, 4, 12),
            Block.box(12.5, 4, 11, 13.5, 6, 12),
            Block.box(13, 6, 11, 15, 8.5, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WIFI_EAST = Stream.of(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(4, 2, 3, 5, 4, 4.5),
            Block.box(4, 4, 3, 5, 6, 4),
            Block.box(4, 6, 1, 5, 9, 3),
            Block.box(4, 2, 11.5, 5, 4, 13),
            Block.box(4, 4, 12.5, 5, 6, 13.5),
            Block.box(4, 6, 13, 5, 8.5, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WIFI_SOUTH = Stream.of(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(11.5, 2, 4, 13, 4, 5),
            Block.box(12, 4, 4, 13, 6, 5),
            Block.box(13, 6, 4, 15, 9, 5),
            Block.box(3, 2, 4, 4.5, 4, 5),
            Block.box(2.5, 4, 4, 3.5, 6, 5),
            Block.box(1, 6, 4, 3, 8.5, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WIFI_WEST = Stream.of(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(11, 2, 11.5, 12, 4, 13),
            Block.box(11, 4, 12, 12, 6, 13),
            Block.box(11, 6, 13, 12, 9, 15),
            Block.box(11, 2, 3, 12, 4, 4.5),
            Block.box(11, 4, 2.5, 12, 6, 3.5),
            Block.box(11, 6, 1, 12, 8.5, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public OldFlatComputer(Properties properties) {
        super(properties.strength(1F,11F).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        if(state.is(TBlocks.WIFI_ROUTER.get())){
            switch (state.getValue(FACING)){
                case NORTH->{return WIFI_NORTH;}
                case SOUTH->{return WIFI_SOUTH;}
                case EAST->{return WIFI_EAST;}
                case WEST->{return WIFI_WEST;}
                default -> {return SHAPE_ALL;}
            }
        }
        return SHAPE_ALL;
    }
}
