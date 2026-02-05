package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class MirrorBlock extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 0, 14, 16, 16, 16),
            Block.box(0, 16, 14, 16, 32, 16),
            Block.box(0, 0, 13, 16, 16, 14),
            Block.box(0, 16, 13, 16, 32, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, 0, 2, 16, 16),
            Block.box(0, 16, 0, 2, 32, 16),
            Block.box(2, 0, 0, 3, 16, 16),
            Block.box(2, 16, 0, 3, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 0, 0, 16, 16, 2),
            Block.box(0, 16, 0, 16, 32, 2),
            Block.box(0, 0, 2, 16, 16, 3),
            Block.box(0, 16, 2, 16, 32, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(14, 0, 0, 16, 16, 16),
            Block.box(14, 16, 0, 16, 32, 16),
            Block.box(13, 0, 0, 14, 16, 16),
            Block.box(13, 16, 0, 14, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public MirrorBlock(Properties properties) {
        super(properties.sound(SoundType.GLASS).pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction d = bs.getValue(FACING);
        switch(d){
            case NORTH: return NORTH;
            case SOUTH: return SOUTH;
            case EAST: return EAST;
            case WEST: return WEST;
            default: return Shapes.block();
        }
    }
}
