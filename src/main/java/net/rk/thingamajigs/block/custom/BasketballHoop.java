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
public class BasketballHoop extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_HOOP = Stream.of(
            Block.box(7, 9, 7, 9, 16, 9),
            Block.box(0, 7, 7, 16, 9, 9),
            Block.box(7, 7, 9, 9, 9, 16),
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(-4, 0, 6, 20, 16, 7),
            Block.box(2, 3, 4, 14, 5, 6),
            Block.box(4, 3, -6, 12, 5, -4),
            Block.box(2, 3, -6, 4, 5, 4),
            Block.box(12, 3, -6, 14, 5, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH_HOOP = Stream.of(
            Block.box(7, 9, 7, 9, 16, 9),
            Block.box(0, 7, 7, 16, 9, 9),
            Block.box(7, 7, 0, 9, 9, 7),
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(-4, 0, 9, 20, 16, 10),
            Block.box(2, 3, 10, 14, 5, 12),
            Block.box(4, 3, 20, 12, 5, 22),
            Block.box(12, 3, 12, 14, 5, 22),
            Block.box(2, 3, 12, 4, 5, 22)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST_HOOP = Stream.of(
            Block.box(7, 9, 7, 9, 16, 9),
            Block.box(7, 7, 0, 9, 9, 16),
            Block.box(0, 7, 7, 7, 9, 9),
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(9, 0, -4, 10, 16, 20),
            Block.box(10, 3, 2, 12, 5, 14),
            Block.box(20, 3, 4, 22, 5, 12),
            Block.box(12, 3, 2, 22, 5, 4),
            Block.box(12, 3, 12, 22, 5, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST_HOOP = Stream.of(
            Block.box(7, 9, 7, 9, 16, 9),
            Block.box(7, 7, 0, 9, 9, 16),
            Block.box(9, 7, 7, 16, 9, 9),
            Block.box(7, 0, 7, 9, 7, 9),
            Block.box(6, 0, -4, 7, 16, 20),
            Block.box(4, 3, 2, 6, 5, 14),
            Block.box(-6, 3, 4, -4, 5, 12),
            Block.box(-6, 3, 12, 4, 5, 14),
            Block.box(-6, 3, 2, 4, 5, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BasketballHoop(Properties properties) {
        super(properties.sound(SoundType.LANTERN).strength(0.75f,1.25f).mapColor(MapColor.METAL));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction d = bs.getValue(FACING);
        switch(d){
            case NORTH: return NORTH_HOOP;
            case SOUTH: return SOUTH_HOOP;
            case EAST: return EAST_HOOP;
            case WEST: return WEST_HOOP;
            default: return Shapes.block();
        }
    }
}
