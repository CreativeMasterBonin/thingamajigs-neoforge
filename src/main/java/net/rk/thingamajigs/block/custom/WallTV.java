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

public class WallTV extends ToggledStateBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(5, 8, 15, 11, 10, 16),
            Block.box(7, 9, 13, 9, 12, 15),
            Block.box(-3, 2, 12, 19, 16, 13),
            Block.box(-3, 2, 12, 10, 3, 12),
            Block.box(10, 2, 12, 19, 3, 12),
            Block.box(18, 3, 12, 19, 15, 12),
            Block.box(-3, 3, 12, -2, 15, 12),
            Block.box(-3, 15, 12, 19, 16, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 8, 5, 1, 10, 11),
            Block.box(1, 9, 7, 3, 12, 9),
            Block.box(3, 2, -3, 4, 16, 19),
            Block.box(4, 2, -3, 4, 3, 10),
            Block.box(4, 2, 10, 4, 3, 19),
            Block.box(4, 3, 18, 4, 15, 19),
            Block.box(4, 3, -3, 4, 15, -2),
            Block.box(4, 15, -3, 4, 16, 19)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(5, 8, 0, 11, 10, 1),
            Block.box(7, 9, 1, 9, 12, 3),
            Block.box(-3, 2, 3, 19, 16, 4),
            Block.box(6, 2, 4, 19, 3, 4),
            Block.box(-3, 2, 4, 6, 3, 4),
            Block.box(-3, 3, 4, -2, 15, 4),
            Block.box(18, 3, 4, 19, 15, 4),
            Block.box(-3, 15, 4, 19, 16, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(15, 8, 5, 16, 10, 11),
            Block.box(13, 9, 7, 15, 12, 9),
            Block.box(12, 2, -3, 13, 16, 19),
            Block.box(12, 2, 6, 12, 3, 19),
            Block.box(12, 2, -3, 12, 3, 6),
            Block.box(12, 3, -3, 12, 15, -2),
            Block.box(12, 3, 18, 12, 15, 19),
            Block.box(12, 15, -3, 12, 16, 19)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTH_ANGLED = Stream.of(
            Block.box(5, 8, 15, 11, 10, 16),
            Block.box(7, 9, 13, 9, 12, 15),
            Block.box(-3, 2, 11, 19, 16, 15),
            Block.box(-3, 2, 12, 10, 3, 12),
            Block.box(10, 2, 12, 19, 3, 12),
            Block.box(18, 3, 12, 19, 15, 12),
            Block.box(-3, 3, 12, -2, 15, 12),
            Block.box(-3, 15, 12, 19, 16, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_ANGLED = Stream.of(
            Block.box(0, 8, 5, 1, 10, 11),
            Block.box(1, 9, 7, 3, 12, 9),
            Block.box(1, 2, -3, 5, 16, 19),
            Block.box(4, 2, -3, 4, 3, 10),
            Block.box(4, 2, 10, 4, 3, 19),
            Block.box(4, 3, 18, 4, 15, 19),
            Block.box(4, 3, -3, 4, 15, -2),
            Block.box(4, 15, -3, 4, 16, 19)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_ANGLED = Stream.of(
            Block.box(5, 8, 0, 11, 10, 1),
            Block.box(7, 9, 1, 9, 12, 3),
            Block.box(-3, 2, 1, 19, 16, 5),
            Block.box(6, 2, 4, 19, 3, 4),
            Block.box(-3, 2, 4, 6, 3, 4),
            Block.box(-3, 3, 4, -2, 15, 4),
            Block.box(18, 3, 4, 19, 15, 4),
            Block.box(-3, 15, 4, 19, 16, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_ANGLED = Stream.of(
            Block.box(15, 8, 5, 16, 10, 11),
            Block.box(13, 9, 7, 15, 12, 9),
            Block.box(11, 2, -3, 15, 16, 19),
            Block.box(12, 2, 6, 12, 3, 19),
            Block.box(12, 2, -3, 12, 3, 6),
            Block.box(12, 3, -3, 12, 15, -2),
            Block.box(12, 3, 18, 12, 15, 19),
            Block.box(12, 15, -3, 12, 16, 19)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public WallTV(Properties p){
        super(p.strength(1f,10f).noOcclusion().noCollission().mapColor(MapColor.COLOR_LIGHT_GRAY));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(state.getValue(TOGGLED)){
            switch (state.getValue(FACING)){
                case NORTH->{return NORTH_ANGLED;}
                case SOUTH->{return SOUTH_ANGLED;}
                case EAST->{return EAST_ANGLED;}
                case WEST->{return WEST_ANGLED;}
                default -> {return Shapes.block();}
            }
        }
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
