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

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class BowlingBallRetriever extends ThingamajigsDecorativeBlock{
    private static final VoxelShape NORTH_NS = Stream.of(
            Block.box(0, 0, 16, 16, 24, 32),
            Block.box(4, 0, -15, 12, 8, 16),
            Block.box(5, 8, -10, 11, 12, -6),
            Block.box(5, 8, 3, 11, 12, 7),
            Block.box(-1, 10, -9, 5, 12, -7),
            Block.box(11, 10, -9, 17, 12, -7),
            Block.box(11, 10, 4, 17, 12, 6),
            Block.box(-1, 10, 4, 5, 12, 6),
            Block.box(0, 12, -16, 1, 13, 16),
            Block.box(4, 12, -16, 5, 13, 12),
            Block.box(11, 12, -16, 12, 13, 12),
            Block.box(15, 12, -16, 16, 13, 16),
            Block.box(5, 12, 11, 11, 13, 12),
            Block.box(11, 13, -16, 16, 15, -15),
            Block.box(0, 13, -16, 5, 15, -15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape SOUTH_SS = Stream.of(
            Block.box(0, 0, -16, 16, 24, 0),
            Block.box(4, 0, 0, 12, 8, 31),
            Block.box(5, 8, 22, 11, 12, 26),
            Block.box(5, 8, 9, 11, 12, 13),
            Block.box(11, 10, 23, 17, 12, 25),
            Block.box(-1, 10, 23, 5, 12, 25),
            Block.box(-1, 10, 10, 5, 12, 12),
            Block.box(11, 10, 10, 17, 12, 12),
            Block.box(15, 12, 0, 16, 13, 32),
            Block.box(11, 12, 4, 12, 13, 32),
            Block.box(4, 12, 4, 5, 13, 32),
            Block.box(0, 12, 0, 1, 13, 32),
            Block.box(5, 12, 4, 11, 13, 5),
            Block.box(0, 13, 31, 5, 15, 32),
            Block.box(11, 13, 31, 16, 15, 32)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape EAST_ES = Stream.of(
            Block.box(-16, 0, 0, 0, 24, 16),
            Block.box(0, 0, 4, 31, 8, 12),
            Block.box(22, 8, 5, 26, 12, 11),
            Block.box(9, 8, 5, 13, 12, 11),
            Block.box(23, 10, -1, 25, 12, 5),
            Block.box(23, 10, 11, 25, 12, 17),
            Block.box(10, 10, 11, 12, 12, 17),
            Block.box(10, 10, -1, 12, 12, 5),
            Block.box(0, 12, 0, 32, 13, 1),
            Block.box(4, 12, 4, 32, 13, 5),
            Block.box(4, 12, 11, 32, 13, 12),
            Block.box(0, 12, 15, 32, 13, 16),
            Block.box(4, 12, 5, 5, 13, 11),
            Block.box(31, 13, 11, 32, 15, 16),
            Block.box(31, 13, 0, 32, 15, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape WEST_WS = Stream.of(
            Block.box(16, 0, 0, 32, 24, 16),
            Block.box(-15, 0, 4, 16, 8, 12),
            Block.box(-10, 8, 5, -6, 12, 11),
            Block.box(3, 8, 5, 7, 12, 11),
            Block.box(-9, 10, 11, -7, 12, 17),
            Block.box(-9, 10, -1, -7, 12, 5),
            Block.box(4, 10, -1, 6, 12, 5),
            Block.box(4, 10, 11, 6, 12, 17),
            Block.box(-16, 12, 15, 16, 13, 16),
            Block.box(-16, 12, 11, 12, 13, 12),
            Block.box(-16, 12, 4, 12, 13, 5),
            Block.box(-16, 12, 0, 16, 13, 1),
            Block.box(11, 12, 5, 12, 13, 11),
            Block.box(-16, 13, 0, -15, 15, 5),
            Block.box(-16, 13, 11, -15, 15, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BowlingBallRetriever(Properties properties) {
        super(properties.sound(SoundType.METAL).strength(1F,10F).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_NS;
            case SOUTH: return SOUTH_SS;
            case EAST: return EAST_ES;
            case WEST: return WEST_WS;
            default: return Shapes.block();
        }
    }
}
