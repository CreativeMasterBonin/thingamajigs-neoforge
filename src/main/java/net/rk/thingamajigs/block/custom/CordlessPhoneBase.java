package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class CordlessPhoneBase extends ThingamajigsDecorativeBlock{
    public static final VoxelShape BASE_ALL =  Block.box(0,0,0, 16,3.25,16);
    public static final VoxelShape NORTH_FEATURED = Stream.of(
            Block.box(2, 0, 0, 14, 2, 16),
            Block.box(9, 2, 2, 13, 4, 14),
            Block.box(9, 3, 14, 10, 4, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_FEATURED = Stream.of(
            Block.box(0, 0, 2, 16, 2, 14),
            Block.box(2, 2, 9, 14, 4, 13),
            Block.box(0, 3, 9, 2, 4, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_FEATURED = Stream.of(
            Block.box(2, 0, 0, 14, 2, 16),
            Block.box(3, 2, 2, 7, 4, 14),
            Block.box(6, 3, 0, 7, 4, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_FEATURED = Stream.of(
            Block.box(0, 0, 2, 16, 2, 14),
            Block.box(2, 2, 3, 14, 4, 7),
            Block.box(14, 3, 6, 16, 4, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CordlessPhoneBase(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(state.is(TBlocks.FEATURED_CORDLESS_PHONE.get())){
            switch (state.getValue(FACING)){
                case NORTH->{return NORTH_FEATURED;}
                case SOUTH->{return SOUTH_FEATURED;}
                case EAST->{return EAST_FEATURED;}
                case WEST->{return WEST_FEATURED;}
                default -> {return BASE_ALL;}
            }
        }
        return BASE_ALL;
    }
}
