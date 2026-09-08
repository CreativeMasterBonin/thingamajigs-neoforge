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

@SuppressWarnings("deprecated")
public class SmallGlassStorageThing extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_BEAKER = Shapes.join(Block.box(5, 0, 5, 11, 8, 11), Block.box(7, 7, 3, 9, 8, 5), BooleanOp.OR);
    public static final VoxelShape EAST_BEAKER = Shapes.join(Block.box(5, 0, 5, 11, 8, 11), Block.box(11, 7, 7, 13, 8, 9), BooleanOp.OR);
    public static final VoxelShape SOUTH_BEAKER = Shapes.join(Block.box(5, 0, 5, 11, 8, 11), Block.box(7, 7, 11, 9, 8, 13), BooleanOp.OR);
    public static final VoxelShape WEST_BEAKER = Shapes.join(Block.box(5, 0, 5, 11, 8, 11), Block.box(3, 7, 7, 5, 8, 9), BooleanOp.OR);
    public static final VoxelShape STANDARD_SHAPE_ALL = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 7.0D, 13.0D);
    public static final VoxelShape FLASK_ALL = Shapes.join(Block.box(5, 0, 5, 11, 5, 11), Block.box(7, 5, 7, 9, 10, 9), BooleanOp.OR);

    public SmallGlassStorageThing(Properties properties) {
        super(properties.sound(SoundType.GLASS).strength(0.5F,0.75F));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(state.is(TBlocks.BEAKER.get())){
            switch (state.getValue(FACING)){
                case NORTH->{return NORTH_BEAKER;}
                case SOUTH->{return SOUTH_BEAKER;}
                case EAST->{return EAST_BEAKER;}
                case WEST->{return WEST_BEAKER;}
                default -> {return STANDARD_SHAPE_ALL;}
            }
        }
        else if(state.is(TBlocks.FLASK.get())){
            return FLASK_ALL;
        }
        return STANDARD_SHAPE_ALL;
    }
}
