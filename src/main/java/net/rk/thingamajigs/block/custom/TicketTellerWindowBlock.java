package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class TicketTellerWindowBlock extends ThingamajigsDecorativeBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape NS = Stream.of(
            Block.box(0, 3, 0, 16, 4, 4),
            Block.box(0, 0, 0, 16, 1, 4),
            Block.box(0, 30, 0, 16, 32, 4),
            Block.box(0, 4, 0, 2, 30, 4),
            Block.box(0, 1, 0, 2, 3, 4),
            Block.box(14, 1, 0, 16, 3, 4),
            Block.box(14, 4, 0, 16, 30, 4),
            Block.box(2, 4, 0, 14, 30, 1),
            Block.box(6, 13, -1, 10, 17, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SS = Stream.of(
            Block.box(0, 3, 12, 16, 4, 16),
            Block.box(0, 0, 12, 16, 1, 16),
            Block.box(0, 30, 12, 16, 32, 16),
            Block.box(14, 4, 12, 16, 30, 16),
            Block.box(14, 1, 12, 16, 3, 16),
            Block.box(0, 1, 12, 2, 3, 16),
            Block.box(0, 4, 12, 2, 30, 16),
            Block.box(2, 4, 15, 14, 30, 16),
            Block.box(6, 13, 13, 10, 17, 17)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ES = Stream.of(
            Block.box(12, 3, 0, 16, 4, 16),
            Block.box(12, 0, 0, 16, 1, 16),
            Block.box(12, 30, 0, 16, 32, 16),
            Block.box(12, 4, 0, 16, 30, 2),
            Block.box(12, 1, 0, 16, 3, 2),
            Block.box(12, 1, 14, 16, 3, 16),
            Block.box(12, 4, 14, 16, 30, 16),
            Block.box(15, 4, 2, 16, 30, 14),
            Block.box(13, 13, 6, 17, 17, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WS = Stream.of(
            Block.box(0, 3, 0, 4, 4, 16),
            Block.box(0, 0, 0, 4, 1, 16),
            Block.box(0, 30, 0, 4, 32, 16),
            Block.box(0, 4, 14, 4, 30, 16),
            Block.box(0, 1, 14, 4, 3, 16),
            Block.box(0, 1, 0, 4, 3, 2),
            Block.box(0, 4, 0, 4, 30, 2),
            Block.box(0, 4, 2, 1, 30, 14),
            Block.box(-1, 13, 6, 3, 17, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public TicketTellerWindowBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NS;
            case SOUTH: return SS;
            case EAST: return ES;
            case WEST: return WS;
            default: return Shapes.block();
        }
    }
}
