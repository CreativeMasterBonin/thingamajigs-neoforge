package net.rk.thingamajigs.xtras;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class GeneralUseShapes {
    public static final VoxelShape CAR_WASH_COMPONENT_BOX_ALL = Stream.of(
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(0, 0, 0, 16, 16, 1),
            Block.box(0, 0, 15, 16, 16, 16),
            Block.box(0, 0, 0, 1, 16, 16),
            Block.box(15, 0, 0, 16, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape BEEPING_FIRE_ALARM_NORTH_SHAPE = Stream.of(
            Block.box(2, 5, 14, 3, 13, 16),
            Block.box(3, 4, 14, 13, 14, 16),
            Block.box(4, 3, 14, 12, 4, 16),
            Block.box(4, 14, 14, 12, 15, 16),
            Block.box(13, 5, 14, 14, 13, 16),
            Block.box(4, 9, 12, 12, 13, 14),
            Block.box(5, 5, 13, 11, 6, 14),
            Block.box(5, 7, 13, 11, 8, 14),
            Block.box(5, 10, 13, 11, 12, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape BEEPING_FIRE_ALARM_SOUTH_SHAPE = Stream.of(
            Block.box(13, 5, 0, 14, 13, 2),
            Block.box(3, 4, 0, 13, 14, 2),
            Block.box(4, 3, 0, 12, 4, 2),
            Block.box(4, 14, 0, 12, 15, 2),
            Block.box(2, 5, 0, 3, 13, 2),
            Block.box(4, 9, 2, 12, 13, 4),
            Block.box(5, 5, 2, 11, 6, 3),
            Block.box(5, 7, 2, 11, 8, 3),
            Block.box(5, 10, 2, 11, 12, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape BEEPING_FIRE_ALARM_EAST_SHAPE = Stream.of(
            Block.box(0, 5, 2, 2, 13, 3),
            Block.box(0, 4, 3, 2, 14, 13),
            Block.box(0, 3, 4, 2, 4, 12),
            Block.box(0, 14, 4, 2, 15, 12),
            Block.box(0, 5, 13, 2, 13, 14),
            Block.box(2, 9, 4, 4, 13, 12),
            Block.box(2, 5, 5, 3, 6, 11),
            Block.box(2, 7, 5, 3, 8, 11),
            Block.box(2, 10, 5, 3, 12, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape BEEPING_FIRE_ALARM_WEST_SHAPE = Stream.of(
            Block.box(14, 5, 13, 16, 13, 14),
            Block.box(14, 4, 3, 16, 14, 13),
            Block.box(14, 3, 4, 16, 4, 12),
            Block.box(14, 14, 4, 16, 15, 12),
            Block.box(14, 5, 2, 16, 13, 3),
            Block.box(12, 9, 4, 14, 13, 12),
            Block.box(13, 5, 5, 14, 6, 11),
            Block.box(13, 7, 5, 14, 8, 11),
            Block.box(13, 10, 5, 14, 12, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape HORN_FIRE_ALARM_NORTH_SHAPE = Stream.of(
            Block.box(3, 6, 14, 13, 15, 16),
            Block.box(5, 11, 13, 11, 12, 14),
            Block.box(5, 13, 13, 11, 14, 14),
            Block.box(5, 9, 13, 11, 10, 14),
            Block.box(5, 7, 13, 11, 8, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape HORN_FIRE_ALARM_SOUTH_SHAPE = Stream.of(
            Block.box(3, 6, 0, 13, 15, 2),
            Block.box(5, 11, 2, 11, 12, 3),
            Block.box(5, 13, 2, 11, 14, 3),
            Block.box(5, 9, 2, 11, 10, 3),
            Block.box(5, 7, 2, 11, 8, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape HORN_FIRE_ALARM_EAST_SHAPE = Stream.of(
            Block.box(0, 6, 3, 2, 15, 13),
            Block.box(2, 11, 5, 3, 12, 11),
            Block.box(2, 13, 5, 3, 14, 11),
            Block.box(2, 9, 5, 3, 10, 11),
            Block.box(2, 7, 5, 3, 8, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape HORN_FIRE_ALARM_WEST_SHAPE = Stream.of(
            Block.box(14, 6, 3, 16, 15, 13),
            Block.box(13, 11, 5, 14, 12, 11),
            Block.box(13, 13, 5, 14, 14, 11),
            Block.box(13, 9, 5, 14, 10, 11),
            Block.box(13, 7, 5, 14, 8, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape LOUD_FIRE_ALARM_NORTH_SHAPE = Stream.of(
            Block.box(5, 6, 14, 11, 14, 16),
            Block.box(5, 7, 13, 11, 13, 14),
            Block.box(5.75, 10.25, 12, 10.25, 12.25, 13),
            Block.box(6, 8, 12.9, 10, 10, 12.9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape LOUD_FIRE_ALARM_SOUTH_SHAPE = Stream.of(
            Block.box(5, 6, 0, 11, 14, 2),
            Block.box(5, 7, 2, 11, 13, 3),
            Block.box(5.75, 10.25, 3, 10.25, 12.25, 4),
            Block.box(6, 8, 3.0999999999999996, 10, 10, 3.0999999999999996)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape LOUD_FIRE_ALARM_EAST_SHAPE = Stream.of(
            Block.box(0, 6, 5, 2, 14, 11),
            Block.box(2, 7, 5, 3, 13, 11),
            Block.box(3, 10.25, 5.75, 4, 12.25, 10.25),
            Block.box(3.0999999999999996, 8, 6, 3.0999999999999996, 10, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape LOUD_FIRE_ALARM_WEST_SHAPE = Stream.of(
            Block.box(14, 6, 5, 16, 14, 11),
            Block.box(13, 7, 5, 14, 13, 11),
            Block.box(12, 10.25, 5.75, 13, 12.25, 10.25),
            Block.box(12.9, 8, 6, 12.9, 10, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static class CameraShapes{
        public static final VoxelShape NORTH_SECURE = Stream.of(
                Block.box(7, 7.5, 10.5, 9, 13.5, 15.5),
                Block.box(6, 6, 15, 10, 10, 16),
                Block.box(10, 14, 1, 11, 18, 12),
                Block.box(5, 14, 1, 6, 18, 12),
                Block.box(6, 14, 1, 10, 18, 1),
                Block.box(6, 14, 2, 10, 18, 12),
                Block.box(5, 18, -1, 11, 19, 12),
                Block.box(6, 13, 1, 10, 14, 12)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public static final VoxelShape EAST_SECURE = Stream.of(
                Block.box(0.5, 7.5, 7, 5.5, 13.5, 9),
                Block.box(0, 6, 6, 1, 10, 10),
                Block.box(4, 14, 10, 15, 18, 11),
                Block.box(4, 14, 5, 15, 18, 6),
                Block.box(15, 14, 6, 15, 18, 10),
                Block.box(4, 14, 6, 14, 18, 10),
                Block.box(4, 18, 5, 17, 19, 11),
                Block.box(4, 13, 6, 15, 14, 10)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public static final VoxelShape SOUTH_SECURE = Stream.of(
                Block.box(7, 7.5, 0.5, 9, 13.5, 5.5),
                Block.box(6, 6, 0, 10, 10, 1),
                Block.box(5, 14, 4, 6, 18, 15),
                Block.box(10, 14, 4, 11, 18, 15),
                Block.box(6, 14, 15, 10, 18, 15),
                Block.box(6, 14, 4, 10, 18, 14),
                Block.box(5, 18, 4, 11, 19, 17),
                Block.box(6, 13, 4, 10, 14, 15)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public static final VoxelShape WEST_SECURE = Stream.of(
                Block.box(10.5, 7.5, 7, 15.5, 13.5, 9),
                Block.box(15, 6, 6, 16, 10, 10),
                Block.box(1, 14, 5, 12, 18, 6),
                Block.box(1, 14, 10, 12, 18, 11),
                Block.box(1, 14, 6, 1, 18, 10),
                Block.box(2, 14, 6, 12, 18, 10),
                Block.box(-1, 18, 5, 12, 19, 11),
                Block.box(1, 13, 6, 12, 14, 10)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public static final VoxelShape NORTH_FILM = Stream.of(
                Block.box(7, 8, 12, 9, 10, 15),
                Block.box(5, 6, 15, 11, 12, 16),
                Block.box(7, 8, 3, 9, 10, 5),
                Block.box(6, 7, 5, 10, 17, 13)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public static final VoxelShape EAST_FILM = Stream.of(
                Block.box(1, 8, 7, 4, 10, 9),
                Block.box(0, 6, 5, 1, 12, 11),
                Block.box(11, 8, 7, 13, 10, 9),
                Block.box(3, 7, 6, 11, 17, 10)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public static final VoxelShape SOUTH_FILM = Stream.of(
                Block.box(7, 8, 1, 9, 10, 4),
                Block.box(5, 6, 0, 11, 12, 1),
                Block.box(7, 8, 11, 9, 10, 13),
                Block.box(6, 7, 3, 10, 17, 11)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public static final VoxelShape WEST_FILM = Stream.of(
                Block.box(12, 8, 7, 15, 10, 9),
                Block.box(15, 6, 5, 16, 12, 11),
                Block.box(3, 8, 7, 5, 10, 9),
                Block.box(5, 7, 6, 13, 17, 10)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public static final VoxelShape NORTH_BOX = Stream.of(
                Block.box(6, 13, 3, 10, 17, 13),
                Block.box(7, 14, 0, 9, 16, 3),
                Block.box(5, 6, 15, 11, 12, 16),
                Block.box(7, 9.5, 10.37868, 9, 13.5, 15.37868)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public static final VoxelShape EAST_BOX = Stream.of(
                Block.box(3, 13, 6, 13, 17, 10),
                Block.box(13, 14, 7, 16, 16, 9),
                Block.box(0, 6, 5, 1, 12, 11),
                Block.box(0.6213200000000008, 9.5, 7, 5.621320000000001, 13.5, 9)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public static final VoxelShape SOUTH_BOX = Stream.of(
                Block.box(6, 13, 3, 10, 17, 13),
                Block.box(7, 14, 13, 9, 16, 16),
                Block.box(5, 6, 0, 11, 12, 1),
                Block.box(7, 9.5, 0.6213200000000008, 9, 13.5, 5.621320000000001)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        public static final VoxelShape WEST_BOX = Stream.of(
                Block.box(3, 13, 6, 13, 17, 10),
                Block.box(0, 14, 7, 3, 16, 9),
                Block.box(15, 6, 5, 16, 12, 11),
                Block.box(10.37868, 9.5, 7, 15.37868, 13.5, 9)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    }

    public static final VoxelShape PEDESTRIAN_FLASHER_LIGHTS_NORTH = Shapes.join(
            Block.box(0, 11, 6.9, 16, 16, 9.9),
            Block.box(7, 0, 7, 9, 16, 9),BooleanOp.OR);
    public static final VoxelShape PEDESTRIAN_FLASHER_LIGHTS_EAST = Shapes.join(
            Block.box(6.1, 11, 0, 9.1, 16, 16),
            Block.box(7, 0, 7, 9, 16, 9),BooleanOp.OR);
    public static final VoxelShape PEDESTRIAN_FLASHER_LIGHTS_SOUTH = Shapes.join(
            Block.box(0, 11, 6.1, 16, 16, 9.1),
            Block.box(7, 0, 7, 9, 16, 9),BooleanOp.OR);
    public static final VoxelShape PEDESTRIAN_FLASHER_LIGHTS_WEST = Shapes.join(Block.box(6.9, 11, 0, 9.9, 16, 16),
            Block.box(7, 0, 7, 9, 16, 9),BooleanOp.OR);

    public static final VoxelShape OLD_FAX_MACHINE_ALL = Block.box(0,0,0,16,6,16);

}
