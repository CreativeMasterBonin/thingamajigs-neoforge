package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class SchoolDesk extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 0, 0, 1, 12, 1),
            Block.box(-4, 15, 0, 20, 16, 16),
            Block.box(15, 0, 0, 16, 12, 1),
            Block.box(-4, 13, 0, -3, 15, 16),
            Block.box(19, 13, 0, 20, 15, 16),
            Block.box(-4, 12, 0, 20, 13, 16),
            Block.box(-3, 13, 15, 19, 15, 16),
            Block.box(15, 0, 15, 16, 12, 16),
            Block.box(0, 0, 15, 1, 12, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(15, 0, 15, 16, 12, 16),
            Block.box(-4, 15, 0, 20, 16, 16),
            Block.box(0, 0, 15, 1, 12, 16),
            Block.box(19, 13, 0, 20, 15, 16),
            Block.box(-4, 13, 0, -3, 15, 16),
            Block.box(-4, 12, 0, 20, 13, 16),
            Block.box(-3, 13, 0, 19, 15, 1),
            Block.box(0, 0, 0, 1, 12, 1),
            Block.box(15, 0, 0, 16, 12, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(15, 0, 0, 16, 12, 1),
            Block.box(0, 15, -4, 16, 16, 20),
            Block.box(15, 0, 15, 16, 12, 16),
            Block.box(0, 13, -4, 16, 15, -3),
            Block.box(0, 13, 19, 16, 15, 20),
            Block.box(0, 12, -4, 16, 13, 20),
            Block.box(0, 13, -3, 1, 15, 19),
            Block.box(0, 0, 15, 1, 12, 16),
            Block.box(0, 0, 0, 1, 12, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(0, 0, 15, 1, 12, 16),
            Block.box(0, 15, -4, 16, 16, 20),
            Block.box(0, 0, 0, 1, 12, 1),
            Block.box(0, 13, 19, 16, 15, 20),
            Block.box(0, 13, -4, 16, 15, -3),
            Block.box(0, 12, -4, 16, 13, 20),
            Block.box(15, 13, -3, 16, 15, 19),
            Block.box(15, 0, 0, 16, 12, 1),
            Block.box(15, 0, 15, 16, 12, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public SchoolDesk(Properties properties) {
        super(properties.sound(SoundType.LANTERN).mapColor(MapColor.WOOD).strength(1F,5F));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos bp, CollisionContext collisionContext) {
        Direction direction = state.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
        }
        return Shapes.block();
    }
}
