package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class ProfessionalTVCamera extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(4, 19, -8, 12, 27, 16),
            Block.box(4, 21, 16, 12, 27, 18),
            Block.box(5.1, 27, 17, 11.1, 30, 23),
            Block.box(4, 26, -13, 12, 27, -8),
            Block.box(4, 19, -9, 5, 26, -8),
            Block.box(11, 19, -9, 12, 26, -8),
            Block.box(2, 18, 14, 4, 22, 19),
            Block.box(2.1, 17, 18, 4.1, 19, 23),
            Block.box(5, 17, 5, 11, 19, 11),
            Block.box(4, 0, 4, 12, 17, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(4, 19, 0, 12, 27, 24),
            Block.box(4, 21, -2, 12, 27, 0),
            Block.box(4.9, 27, -7, 10.9, 30, -1),
            Block.box(4, 26, 24, 12, 27, 29),
            Block.box(11, 19, 24, 12, 26, 25),
            Block.box(4, 19, 24, 5, 26, 25),
            Block.box(12, 18, -3, 14, 22, 2),
            Block.box(11.9, 17, -7, 13.9, 19, -2),
            Block.box(5, 17, 5, 11, 19, 11),
            Block.box(4, 0, 4, 12, 17, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 19, 4, 24, 27, 12),
            Block.box(-2, 21, 4, 0, 27, 12),
            Block.box(-7, 27, 5.1, -1, 30, 11.1),
            Block.box(24, 26, 4, 29, 27, 12),
            Block.box(24, 19, 4, 25, 26, 5),
            Block.box(24, 19, 11, 25, 26, 12),
            Block.box(-3, 18, 2, 2, 22, 4),
            Block.box(-7, 17, 2.0999999999999996, -2, 19, 4.1),
            Block.box(5, 17, 5, 11, 19, 11),
            Block.box(4, 0, 4, 12, 17, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(-8, 19, 4, 16, 27, 12),
            Block.box(16, 21, 4, 18, 27, 12),
            Block.box(17, 27, 4.9, 23, 30, 10.9),
            Block.box(-13, 26, 4, -8, 27, 12),
            Block.box(-9, 19, 11, -8, 26, 12),
            Block.box(-9, 19, 4, -8, 26, 5),
            Block.box(14, 18, 12, 19, 22, 14),
            Block.box(18, 17, 11.9, 23, 19, 13.9),
            Block.box(5, 17, 5, 11, 19, 11),
            Block.box(4, 0, 4, 12, 17, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ProfessionalTVCamera(Properties p) {
        super(p.strength(1f,2f).sound(SoundType.LANTERN));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch(pState.getValue(FACING)){
            case NORTH ->{
                return NORTH;
            }
            case SOUTH ->{
                return SOUTH;
            }
            case EAST ->{
                return EAST;
            }
            case WEST ->{
                return WEST;
            }
            default ->{
                return Shapes.block();
            }
        }
    }
}
