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
public class AirConditioner extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH = java.util.Optional.of(Block.box(-8, 0, 0, 24, 16, 16)).get();
    public static final VoxelShape EASTWEST = java.util.Optional.of(Block.box(0, 0, -8, 16, 16, 24)).get();

    public static final VoxelShape NORTHSOUTH_FOOSBALL_TABLE = Stream.of(
            Block.box(-6, 11, 0, 7, 17, 1),
            Block.box(-6, 11, 1, -5, 17, 16),
            Block.box(21, 11, 1, 22, 17, 16),
            Block.box(7, 11, 0, 22, 17, 1),
            Block.box(-6, 11, 15, 7, 17, 16),
            Block.box(7, 11, 15, 22, 17, 16),
            Block.box(-6, 10, 0, 6, 11, 16),
            Block.box(6, 10, 0, 22, 11, 16),
            Block.box(0, 0, 1, 2, 10, 2),
            Block.box(0, 0, 2, 2, 1, 14),
            Block.box(14, 0, 2, 16, 1, 14),
            Block.box(0, 0, 14, 2, 10, 15),
            Block.box(14, 0, 1, 16, 10, 2),
            Block.box(14, 0, 14, 16, 10, 15),
            Block.box(-3, 13, -3, -2, 14, 17),
            Block.box(0, 13, -3, 1, 14, 17),
            Block.box(3, 13, -3, 4, 14, 17),
            Block.box(18, 13, -1, 19, 14, 19),
            Block.box(15, 13, -1, 16, 14, 19),
            Block.box(12, 13, -1, 13, 14, 19),
            Block.box(-3.5, 12.5, -4.5, -1.5, 14.5, -1.5),
            Block.box(-0.5, 12.5, -4.5, 1.5, 14.5, -1.5),
            Block.box(2.5, 12.5, -4.5, 4.5, 14.5, -1.5),
            Block.box(17.5, 12.5, 17.5, 19.5, 14.5, 20.5),
            Block.box(14.5, 12.5, 17.5, 16.5, 14.5, 20.5),
            Block.box(11.5, 12.5, 17.5, 13.5, 14.5, 20.5),
            Block.box(-2, 11, 3, -1, 17, 5),
            Block.box(-2, 11, 7, -1, 17, 9),
            Block.box(-2, 11, 11, -1, 17, 13),
            Block.box(4, 11, 11, 5, 17, 13),
            Block.box(4, 11, 3, 5, 17, 5),
            Block.box(4, 11, 7, 5, 17, 9),
            Block.box(11, 11, 3, 12, 17, 5),
            Block.box(11, 11, 7, 12, 17, 9),
            Block.box(11, 11, 11, 12, 17, 13),
            Block.box(14, 11, 9, 15, 17, 11),
            Block.box(14, 11, 5, 15, 17, 7),
            Block.box(1, 11, 5, 2, 17, 7),
            Block.box(1, 11, 9, 2, 17, 11),
            Block.box(17, 11, 11, 18, 17, 13),
            Block.box(17, 11, 3, 18, 17, 5),
            Block.box(17, 11, 7, 18, 17, 9),
            Block.box(22, 12, 1, 23, 15, 2),
            Block.box(22, 12, 4, 23, 15, 5),
            Block.box(22, 11, 1, 23, 12, 5),
            Block.box(22.05, 12, 3, 22.05, 15, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST_FOOSBALL_TABLE = Stream.of(
            Block.box(15, 11, -6, 16, 17, 7),
            Block.box(0, 11, -6, 15, 17, -5),
            Block.box(0, 11, 21, 15, 17, 22),
            Block.box(15, 11, 7, 16, 17, 22),
            Block.box(0, 11, -6, 1, 17, 7),
            Block.box(0, 11, 7, 1, 17, 22),
            Block.box(0, 10, -6, 16, 11, 6),
            Block.box(0, 10, 6, 16, 11, 22),
            Block.box(14, 0, 0, 15, 10, 2),
            Block.box(2, 0, 0, 14, 1, 2),
            Block.box(2, 0, 14, 14, 1, 16),
            Block.box(1, 0, 0, 2, 10, 2),
            Block.box(14, 0, 14, 15, 10, 16),
            Block.box(1, 0, 14, 2, 10, 16),
            Block.box(-1, 13, -3, 19, 14, -2),
            Block.box(-1, 13, 0, 19, 14, 1),
            Block.box(-1, 13, 3, 19, 14, 4),
            Block.box(-3, 13, 18, 17, 14, 19),
            Block.box(-3, 13, 15, 17, 14, 16),
            Block.box(-3, 13, 12, 17, 14, 13),
            Block.box(17.5, 12.5, -3.5, 20.5, 14.5, -1.5),
            Block.box(17.5, 12.5, -0.5, 20.5, 14.5, 1.5),
            Block.box(17.5, 12.5, 2.5, 20.5, 14.5, 4.5),
            Block.box(-4.5, 12.5, 17.5, -1.5, 14.5, 19.5),
            Block.box(-4.5, 12.5, 14.5, -1.5, 14.5, 16.5),
            Block.box(-4.5, 12.5, 11.5, -1.5, 14.5, 13.5),
            Block.box(11, 11, -2, 13, 17, -1),
            Block.box(7, 11, -2, 9, 17, -1),
            Block.box(3, 11, -2, 5, 17, -1),
            Block.box(3, 11, 4, 5, 17, 5),
            Block.box(11, 11, 4, 13, 17, 5),
            Block.box(7, 11, 4, 9, 17, 5),
            Block.box(11, 11, 11, 13, 17, 12),
            Block.box(7, 11, 11, 9, 17, 12),
            Block.box(3, 11, 11, 5, 17, 12),
            Block.box(5, 11, 14, 7, 17, 15),
            Block.box(9, 11, 14, 11, 17, 15),
            Block.box(9, 11, 1, 11, 17, 2),
            Block.box(5, 11, 1, 7, 17, 2),
            Block.box(3, 11, 17, 5, 17, 18),
            Block.box(11, 11, 17, 13, 17, 18),
            Block.box(7, 11, 17, 9, 17, 18),
            Block.box(14, 12, 22, 15, 15, 23),
            Block.box(11, 12, 22, 12, 15, 23),
            Block.box(11, 11, 22, 15, 12, 23),
            Block.box(12, 12, 22.05, 13, 15, 22.05)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTHSOUTH_AIR_HOCKEY_TABLE = Stream.of(
            Block.box(-8, 10, 0, 24, 11, 16),
            Block.box(-8, 0, 0, -6, 10, 2),
            Block.box(22, 0, 0, 24, 10, 2),
            Block.box(-8, 0, 14, -6, 10, 16),
            Block.box(22, 0, 14, 24, 10, 16),
            Block.box(-7, 11, 0, 23, 15, 1),
            Block.box(-7, 11, 15, 23, 15, 16),
            Block.box(-8, 11, 0, -7, 15, 16),
            Block.box(23, 11, 0, 24, 15, 16),
            Block.box(-6, 13, 1, -4, 15, 15),
            Block.box(20, 13, 1, 22, 15, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST_AIR_HOCKEY_TABLE = Stream.of(
            Block.box(0, 10, -8, 16, 11, 24),
            Block.box(14, 0, -8, 16, 10, -6),
            Block.box(14, 0, 22, 16, 10, 24),
            Block.box(0, 0, -8, 2, 10, -6),
            Block.box(0, 0, 22, 2, 10, 24),
            Block.box(15, 11, -7, 16, 15, 23),
            Block.box(0, 11, -7, 1, 15, 23),
            Block.box(0, 11, -8, 16, 15, -7),
            Block.box(0, 11, 23, 16, 15, 24),
            Block.box(1, 13, -6, 15, 15, -4),
            Block.box(1, 13, 20, 15, 15, 22)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public AirConditioner(BlockBehaviour.Properties properties) {
        super(properties.strength(2F,25F));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = state.getValue(FACING);
        if(state.is(TBlocks.FOOSBALL_TABLE.get())){
            switch(direction){
                case NORTH,SOUTH -> {return NORTHSOUTH_FOOSBALL_TABLE;}
                case EAST,WEST -> {return EASTWEST_FOOSBALL_TABLE;}
                default -> {return Shapes.block();}
            }
        }
        else if(state.is(TBlocks.AIR_HOCKEY_TABLE.get())){
            switch(direction){
                case NORTH,SOUTH -> {return NORTHSOUTH_AIR_HOCKEY_TABLE;}
                case EAST,WEST -> {return EASTWEST_AIR_HOCKEY_TABLE;}
                default -> {return Shapes.block();}
            }
        }
        switch(direction){
            case NORTH:
            case SOUTH:
                return NORTHSOUTH;
            case EAST:
            case WEST:
                return EASTWEST;
            default: return Shapes.block();
        }
    }
}
