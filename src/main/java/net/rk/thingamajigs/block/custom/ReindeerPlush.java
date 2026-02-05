package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

public class ReindeerPlush extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NS = Optional.of(Block.box(6, 0, 5, 10, 7, 10)).get();
    public static final VoxelShape SS = Optional.of(Block.box(6, 0, 6, 10, 7, 11)).get();
    public static final VoxelShape ES = Optional.of(Block.box(6, 0, 6, 11, 7, 10)).get();
    public static final VoxelShape WS = Optional.of(Block.box(5, 0, 6, 10, 7, 10)).get();

    public ReindeerPlush(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false));
    }

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

    // alt stuff
    public static final VoxelShape NS_ALT = Optional.of(Block.box(6, 0, 5, 10, 7, 10)).get();
    public static final VoxelShape SS_ALT = Optional.of(Block.box(6, 0, 6, 10, 7, 11)).get();
    public static final VoxelShape ES_ALT = Optional.of(Block.box(6, 0, 6, 11, 7, 10)).get();
    public static final VoxelShape WS_ALT = Optional.of(Block.box(5, 0, 6, 10, 7, 10)).get();
}
