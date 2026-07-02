package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class GrabBar extends DirectionalConnectedSideBlock {
    public static final VoxelShape NORTH_MIDDLE = Block.box(0, 4, 13, 16, 7, 15);
    public static final VoxelShape SOUTH_MIDDLE = Block.box(0, 4, 1, 16, 7, 3);
    public static final VoxelShape EAST_MIDDLE = Block.box(1, 4, 0, 3, 7, 16);
    public static final VoxelShape WEST_MIDDLE = Block.box(13, 4, 0, 15, 7, 16);

    public static final VoxelShape NORTH_LEFT = Shapes.join(Block.box(0, 4, 13, 14, 7, 15),
            Block.box(14, 3, 12, 16, 8, 16), BooleanOp.OR);
    public static final VoxelShape SOUTH_LEFT = Shapes.join(Block.box(2, 4, 1, 16, 7, 3),
            Block.box(0, 3, 0, 2, 8, 4), BooleanOp.OR);
    public static final VoxelShape EAST_LEFT = Shapes.join(Block.box(1, 4, 0, 3, 7, 14),
            Block.box(0, 3, 14, 4, 8, 16), BooleanOp.OR);
    public static final VoxelShape WEST_LEFT = Shapes.join(Block.box(13, 4, 2, 15, 7, 16),
            Block.box(12, 3, 0, 16, 8, 2), BooleanOp.OR);

    public static final VoxelShape NORTH_RIGHT = Shapes.join(Block.box(2, 4, 13, 16, 7, 15),
            Block.box(0, 3, 12, 2, 8, 16), BooleanOp.OR);
    public static final VoxelShape SOUTH_RIGHT = Shapes.join(Block.box(0, 4, 1, 14, 7, 3),
            Block.box(14, 3, 0, 16, 8, 4), BooleanOp.OR);
    public static final VoxelShape EAST_RIGHT = Shapes.join(Block.box(1, 4, 2, 3, 7, 16),
            Block.box(0, 3, 0, 4, 8, 2), BooleanOp.OR);
    public static final VoxelShape WEST_RIGHT = Shapes.join(Block.box(13, 4, 0, 15, 7, 14),
            Block.box(12, 3, 14, 16, 8, 16), BooleanOp.OR);

    public static final VoxelShape NORTH = Stream.of(
            Block.box(2, 4, 13, 16, 7, 15),
            Block.box(0, 3, 12, 2, 8, 16),
            Block.box(14, 3, 12, 16, 8, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(1, 4, 2, 3, 7, 16),
            Block.box(0, 3, 0, 4, 8, 2),
            Block.box(0, 3, 14, 4, 8, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 4, 1, 14, 7, 3),
            Block.box(14, 3, 0, 16, 8, 4),
            Block.box(0, 3, 0, 2, 8, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(13, 4, 0, 15, 7, 14),
            Block.box(12, 3, 14, 16, 8, 16),
            Block.box(12, 3, 0, 16, 8, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public GrabBar(Properties properties) {
        super(properties.strength(0.7f,0.2f).mapColor(MapColor.METAL));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)){
            case NORTH -> {
                switch(state.getValue(CONNECTED_SIDE)){
                    case UNCONNECTED -> {return NORTH;}
                    case LEFT -> {return NORTH_LEFT;}
                    case CENTER -> {return NORTH_MIDDLE;}
                    case RIGHT -> {return NORTH_RIGHT;}
                }
            }
            case SOUTH -> {
                switch(state.getValue(CONNECTED_SIDE)){
                    case UNCONNECTED -> {return SOUTH;}
                    case LEFT -> {return SOUTH_LEFT;}
                    case CENTER -> {return SOUTH_MIDDLE;}
                    case RIGHT -> {return SOUTH_RIGHT;}
                }
            }
            case EAST -> {
                switch(state.getValue(CONNECTED_SIDE)){
                    case UNCONNECTED -> {return EAST;}
                    case LEFT -> {return EAST_LEFT;}
                    case CENTER -> {return EAST_MIDDLE;}
                    case RIGHT -> {return EAST_RIGHT;}
                }
            }
            case WEST -> {
                switch(state.getValue(CONNECTED_SIDE)){
                    case UNCONNECTED -> {return WEST;}
                    case LEFT -> {return WEST_LEFT;}
                    case CENTER -> {return WEST_MIDDLE;}
                    case RIGHT -> {return WEST_RIGHT;}
                }
            }
        }
        return Shapes.block();
    }
}
