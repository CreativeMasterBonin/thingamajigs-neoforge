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
import net.rk.thingamajigs.block.TBlocks;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class StandMixer extends ThingamajigsDecorativeBlock{
    public static final VoxelShape SHAPE_NS = Block.box(3,0,0,13,18,16);
    public static final VoxelShape SHAPE_EW = Block.box(0,0,3,16,18,13);
    public static final VoxelShape NORTH_MICROSCOPE = Stream.of(
            Block.box(4, 0, 0, 12, 2, 16),
            Block.box(4, 2, 13, 12, 11, 16),
            Block.box(4.03, 16, 0, 12.03, 20, 8),
            Block.box(6, 14, 2, 10, 16, 6),
            Block.box(7, 10, 3, 9, 14, 5),
            Block.box(5, 8, 1, 11, 9, 7),
            Block.box(6, 2, 2, 10, 8, 3),
            Block.box(6, 2, 5, 10, 8, 6),
            Block.box(3, 3, 14, 4, 4, 15),
            Block.box(3, 5, 14, 4, 6, 15),
            Block.box(4, 11, 11, 12, 14, 15),
            Block.box(4, 14, 8, 12, 17, 11),
            Block.box(5, 20, -4, 7, 24, 0),
            Block.box(9, 20, -4, 11, 24, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_MICROSCOPE = Stream.of(
            Block.box(0, 0, 4, 16, 2, 12),
            Block.box(0, 2, 4, 3, 11, 12),
            Block.box(8, 16, 4.03, 16, 20, 12.03),
            Block.box(10, 14, 6, 14, 16, 10),
            Block.box(11, 10, 7, 13, 14, 9),
            Block.box(9, 8, 5, 15, 9, 11),
            Block.box(13, 2, 6, 14, 8, 10),
            Block.box(10, 2, 6, 11, 8, 10),
            Block.box(1, 3, 3, 2, 4, 4),
            Block.box(1, 5, 3, 2, 6, 4),
            Block.box(1, 11, 4, 5, 14, 12),
            Block.box(5, 14, 4, 8, 17, 12),
            Block.box(16, 20, 5, 20, 24, 7),
            Block.box(16, 20, 9, 20, 24, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_MICROSCOPE = Stream.of(
            Block.box(4, 0, 0, 12, 2, 16),
            Block.box(4, 2, 0, 12, 11, 3),
            Block.box(3.9700000000000006, 16, 8, 11.969999999999999, 20, 16),
            Block.box(6, 14, 10, 10, 16, 14),
            Block.box(7, 10, 11, 9, 14, 13),
            Block.box(5, 8, 9, 11, 9, 15),
            Block.box(6, 2, 13, 10, 8, 14),
            Block.box(6, 2, 10, 10, 8, 11),
            Block.box(12, 3, 1, 13, 4, 2),
            Block.box(12, 5, 1, 13, 6, 2),
            Block.box(4, 11, 1, 12, 14, 5),
            Block.box(4, 14, 5, 12, 17, 8),
            Block.box(9, 20, 16, 11, 24, 20),
            Block.box(5, 20, 16, 7, 24, 20)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_MICROSCOPE = Stream.of(
            Block.box(0, 0, 4, 16, 2, 12),
            Block.box(13, 2, 4, 16, 11, 12),
            Block.box(0, 16, 3.9700000000000006, 8, 20, 11.969999999999999),
            Block.box(2, 14, 6, 6, 16, 10),
            Block.box(3, 10, 7, 5, 14, 9),
            Block.box(1, 8, 5, 7, 9, 11),
            Block.box(2, 2, 6, 3, 8, 10),
            Block.box(5, 2, 6, 6, 8, 10),
            Block.box(14, 3, 12, 15, 4, 13),
            Block.box(14, 5, 12, 15, 6, 13),
            Block.box(11, 11, 4, 15, 14, 12),
            Block.box(8, 14, 4, 11, 17, 12),
            Block.box(-4, 20, 9, 0, 24, 11),
            Block.box(-4, 20, 5, 0, 24, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_SEWING_MACHINE = Stream.of(
            Block.box(5, 0, 0, 11, 1, 16),
            Block.box(5, 1, 11, 11, 9, 16),
            Block.box(5, 9, 0, 11, 16, 16),
            Block.box(7.5, 4, 2, 8.5, 8, 3),
            Block.box(6, 8, 1, 10, 9, 5),
            Block.box(4, 14, 12, 5, 15, 13),
            Block.box(4, 14, 14, 5, 15, 15),
            Block.box(6, 11, 16, 10, 15, 17),
            Block.box(7, 12, 17, 9, 14, 18)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SEWING_MACHINE = Stream.of(
            Block.box(0, 0, 5, 16, 1, 11),
            Block.box(0, 1, 5, 5, 9, 11),
            Block.box(0, 9, 5, 16, 16, 11),
            Block.box(13, 4, 7.5, 14, 8, 8.5),
            Block.box(11, 8, 6, 15, 9, 10),
            Block.box(3, 14, 4, 4, 15, 5),
            Block.box(1, 14, 4, 2, 15, 5),
            Block.box(-1, 11, 6, 0, 15, 10),
            Block.box(-2, 12, 7, -1, 14, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SEWING_MACHINE = Stream.of(
            Block.box(5, 0, 0, 11, 1, 16),
            Block.box(5, 1, 0, 11, 9, 5),
            Block.box(5, 9, 0, 11, 16, 16),
            Block.box(7.5, 4, 13, 8.5, 8, 14),
            Block.box(6, 8, 11, 10, 9, 15),
            Block.box(11, 14, 3, 12, 15, 4),
            Block.box(11, 14, 1, 12, 15, 2),
            Block.box(6, 11, -1, 10, 15, 0),
            Block.box(7, 12, -2, 9, 14, -1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SEWING_MACHINE = Stream.of(
            Block.box(0, 0, 5, 16, 1, 11),
            Block.box(11, 1, 5, 16, 9, 11),
            Block.box(0, 9, 5, 16, 16, 11),
            Block.box(2, 4, 7.5, 3, 8, 8.5),
            Block.box(1, 8, 6, 5, 9, 10),
            Block.box(12, 14, 11, 13, 15, 12),
            Block.box(14, 14, 11, 15, 15, 12),
            Block.box(16, 11, 6, 17, 15, 10),
            Block.box(17, 12, 7, 18, 14, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_JUICER = Stream.of(
            Block.box(3, 0, 0, 13, 2, 16),
            Block.box(4, 2, 13, 12, 10, 15),
            Block.box(4, 10, 5, 12, 18, 16),
            Block.box(6, 12, 4, 10, 16, 5),
            Block.box(6, 12, 0, 10, 13, 4),
            Block.box(6, 13, 0, 7, 15, 4),
            Block.box(9, 13, 0, 10, 15, 4),
            Block.box(6, 18, 11, 10, 20, 12),
            Block.box(6, 18, 14, 10, 20, 15),
            Block.box(6, 18, 12, 7, 20, 14),
            Block.box(9, 18, 12, 10, 20, 14),
            Block.box(7, 18.02, 12, 9, 18.02, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_JUICER = Stream.of(
            Block.box(0, 0, 3, 16, 2, 13),
            Block.box(1, 2, 4, 3, 10, 12),
            Block.box(0, 10, 4, 11, 18, 12),
            Block.box(11, 12, 6, 12, 16, 10),
            Block.box(12, 12, 6, 16, 13, 10),
            Block.box(12, 13, 6, 16, 15, 7),
            Block.box(12, 13, 9, 16, 15, 10),
            Block.box(4, 18, 6, 5, 20, 10),
            Block.box(1, 18, 6, 2, 20, 10),
            Block.box(2, 18, 6, 4, 20, 7),
            Block.box(2, 18, 9, 4, 20, 10),
            Block.box(2, 18.02, 7, 4, 18.02, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_JUICER = Stream.of(
            Block.box(3, 0, 0, 13, 2, 16),
            Block.box(4, 2, 1, 12, 10, 3),
            Block.box(4, 10, 0, 12, 18, 11),
            Block.box(6, 12, 11, 10, 16, 12),
            Block.box(6, 12, 12, 10, 13, 16),
            Block.box(9, 13, 12, 10, 15, 16),
            Block.box(6, 13, 12, 7, 15, 16),
            Block.box(6, 18, 4, 10, 20, 5),
            Block.box(6, 18, 1, 10, 20, 2),
            Block.box(9, 18, 2, 10, 20, 4),
            Block.box(6, 18, 2, 7, 20, 4),
            Block.box(7, 18.02, 2, 9, 18.02, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_JUICER = Stream.of(
            Block.box(0, 0, 3, 16, 2, 13),
            Block.box(13, 2, 4, 15, 10, 12),
            Block.box(5, 10, 4, 16, 18, 12),
            Block.box(4, 12, 6, 5, 16, 10),
            Block.box(0, 12, 6, 4, 13, 10),
            Block.box(0, 13, 9, 4, 15, 10),
            Block.box(0, 13, 6, 4, 15, 7),
            Block.box(11, 18, 6, 12, 20, 10),
            Block.box(14, 18, 6, 15, 20, 10),
            Block.box(12, 18, 9, 14, 20, 10),
            Block.box(12, 18, 6, 14, 20, 7),
            Block.box(12, 18.02, 7, 14, 18.02, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_STAND_MIXER = Stream.of(
            Block.box(4, 0, 1, 12, 1, 15),
            Block.box(5, 1, 8, 11, 3, 14),
            Block.box(6, 3, 9, 10, 16, 13),
            Block.box(4, 16, 1, 12, 17, 15),
            Block.box(5, 17, 2, 11, 18, 14),
            Block.box(5, 13, 2, 11, 16, 8),
            Block.box(6, 12, 3, 10, 13, 7),
            Block.box(7, 6, 4, 9, 12, 6),
            Block.box(5, 3, 13, 11, 16, 16),
            Block.box(3, 1, -2, 13, 2, 8),
            Block.box(3, 2, -2, 4, 8, 7),
            Block.box(4, 2, -2, 13, 8, -1),
            Block.box(12, 2, -1, 13, 8, 8),
            Block.box(3, 2, 7, 12, 8, 8)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_STAND_MIXER = Stream.of(
            Block.box(1, 0, 4, 15, 1, 12),
            Block.box(2, 1, 5, 8, 3, 11),
            Block.box(3, 3, 6, 7, 16, 10),
            Block.box(1, 16, 4, 15, 17, 12),
            Block.box(2, 17, 5, 14, 18, 11),
            Block.box(8, 13, 5, 14, 16, 11),
            Block.box(9, 12, 6, 13, 13, 10),
            Block.box(10, 6, 7, 12, 12, 9),
            Block.box(0, 3, 5, 3, 16, 11),
            Block.box(8, 1, 3, 18, 2, 13),
            Block.box(9, 2, 3, 18, 8, 4),
            Block.box(17, 2, 4, 18, 8, 13),
            Block.box(8, 2, 12, 17, 8, 13),
            Block.box(8, 2, 3, 9, 8, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_STAND_MIXER = Stream.of(
            Block.box(4, 0, 1, 12, 1, 15),
            Block.box(5, 1, 2, 11, 3, 8),
            Block.box(6, 3, 3, 10, 16, 7),
            Block.box(4, 16, 1, 12, 17, 15),
            Block.box(5, 17, 2, 11, 18, 14),
            Block.box(5, 13, 8, 11, 16, 14),
            Block.box(6, 12, 9, 10, 13, 13),
            Block.box(7, 6, 10, 9, 12, 12),
            Block.box(5, 3, 0, 11, 16, 3),
            Block.box(3, 1, 8, 13, 2, 18),
            Block.box(12, 2, 9, 13, 8, 18),
            Block.box(3, 2, 17, 12, 8, 18),
            Block.box(3, 2, 8, 4, 8, 17),
            Block.box(4, 2, 8, 13, 8, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_STAND_MIXER = Stream.of(
            Block.box(1, 0, 4, 15, 1, 12),
            Block.box(8, 1, 5, 14, 3, 11),
            Block.box(9, 3, 6, 13, 16, 10),
            Block.box(1, 16, 4, 15, 17, 12),
            Block.box(2, 17, 5, 14, 18, 11),
            Block.box(2, 13, 5, 8, 16, 11),
            Block.box(3, 12, 6, 7, 13, 10),
            Block.box(4, 6, 7, 6, 12, 9),
            Block.box(13, 3, 5, 16, 16, 11),
            Block.box(-2, 1, 3, 8, 2, 13),
            Block.box(-2, 2, 12, 7, 8, 13),
            Block.box(-2, 2, 3, -1, 8, 12),
            Block.box(-1, 2, 3, 8, 8, 4),
            Block.box(7, 2, 4, 8, 8, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public StandMixer(Properties properties) {
        super(properties.strength(1.1F,1F).sound(SoundType.LANTERN));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction d = state.getValue(FACING);
        if(state.is(TBlocks.MICROSCOPE.get())){
            switch(d){
                case NORTH->{return NORTH_MICROSCOPE;}
                case SOUTH->{return SOUTH_MICROSCOPE;}
                case EAST->{return EAST_MICROSCOPE;}
                case WEST->{return WEST_MICROSCOPE;}
                default->{return Shapes.block();}
            }
        }
        else if(state.is(TBlocks.SEWING_MACHINE.get())){
            switch(d){
                case NORTH->{return NORTH_SEWING_MACHINE;}
                case SOUTH->{return SOUTH_SEWING_MACHINE;}
                case EAST->{return EAST_SEWING_MACHINE;}
                case WEST->{return WEST_SEWING_MACHINE;}
                default->{return Shapes.block();}
            }
        }
        else if(state.is(TBlocks.JUICER.get())){
            switch(d){
                case NORTH->{return NORTH_JUICER;}
                case SOUTH->{return SOUTH_JUICER;}
                case EAST->{return EAST_JUICER;}
                case WEST->{return WEST_JUICER;}
                default->{return Shapes.block();}
            }
        }
        else if(state.is(TBlocks.STAND_MIXER.get())){
            switch(d){
                case NORTH->{return NORTH_STAND_MIXER;}
                case SOUTH->{return SOUTH_STAND_MIXER;}
                case EAST->{return EAST_STAND_MIXER;}
                case WEST->{return WEST_STAND_MIXER;}
                default->{return Shapes.block();}
            }
        }
        switch(d){
            case NORTH:
            case SOUTH:
                return SHAPE_NS;
            case EAST:
            case WEST:
                return SHAPE_EW;
            default:
                return Shapes.block();
        }
    }
}
