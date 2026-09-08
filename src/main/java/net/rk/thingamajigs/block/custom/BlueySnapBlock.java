package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class BlueySnapBlock extends ThingamajigsDecorativeBlock{
    public static final VoxelShape OLD_ALL = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

    public static final VoxelShape NORTHSOUTH_BASE = Stream.of(
            Block.box(5, 0, 0, 11, 2, 16),
            Block.box(5, 2, 0, 7, 9, 16),
            Block.box(9, 2, 0, 11, 9, 16),
            Block.box(7.5, 2, 2, 8.5, 9, 14),
            Block.box(7.5, 2, 14, 8.5, 9, 16),
            Block.box(7.5, 2, 0, 8.5, 9, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST_BASE = Stream.of(
            Block.box(0, 0, 5, 16, 2, 11),
            Block.box(0, 2, 5, 16, 9, 7),
            Block.box(0, 2, 9, 16, 9, 11),
            Block.box(2, 2, 7.5, 14, 9, 8.5),
            Block.box(0, 2, 7.5, 2, 9, 8.5),
            Block.box(14, 2, 7.5, 16, 9, 8.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_CONSOLE = Stream.of(
            Block.box(2, 0, 7, 14, 7, 8),
            Block.box(0, 0, 7, 2, 7, 8),
            Block.box(14, 0, 7, 16, 7, 8),
            Block.box(7, -0.05, 7, 9, -0.05, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_CONSOLE = Stream.of(
            Block.box(8, 0, 2, 9, 7, 14),
            Block.box(8, 0, 0, 9, 7, 2),
            Block.box(8, 0, 14, 9, 7, 16),
            Block.box(8, -0.05, 7, 9, -0.05, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_CONSOLE = Stream.of(
            Block.box(2, 0, 8, 14, 7, 9),
            Block.box(14, 0, 8, 16, 7, 9),
            Block.box(0, 0, 8, 2, 7, 9),
            Block.box(7, -0.05, 8, 9, -0.05, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_CONSOLE = Stream.of(
            Block.box(7, 0, 2, 8, 7, 14),
            Block.box(7, 0, 14, 8, 7, 16),
            Block.box(7, 0, 0, 8, 7, 2),
            Block.box(7, -0.05, 7, 8, -0.05, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BlueySnapBlock(BlockBehaviour.Properties properties) {
        super(properties.strength(1f));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        if(state.is(TBlocks.BLUEYSNAP_BASE.get())){
            switch (state.getValue(FACING)){
                case NORTH,SOUTH->{return NORTHSOUTH_BASE;}
                case EAST,WEST->{return EASTWEST_BASE;}
                default -> {return OLD_ALL;}
            }
        }
        else if(state.is(TBlocks.BLUEYSNAP_CONSOLE.get())){
            switch (state.getValue(FACING)){
                case NORTH->{return NORTH_CONSOLE;}
                case SOUTH->{return SOUTH_CONSOLE;}
                case EAST->{return EAST_CONSOLE;}
                case WEST->{return WEST_CONSOLE;}
                default -> {return OLD_ALL;}
            }
        }
        return OLD_ALL;
    }
}
