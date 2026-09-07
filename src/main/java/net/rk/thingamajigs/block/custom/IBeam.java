package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class IBeam extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(2, 1, 0, 14, 3, 16),
            Block.box(2, 13, 0, 14, 15, 16),
            Block.box(3, 3, 0, 13, 13, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 15, 0, 16, 16, 16),
            Block.box(0, 1, 2, 16, 3, 14),
            Block.box(0, 13, 2, 16, 15, 14),
            Block.box(0, 3, 3, 16, 13, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public IBeam(Properties properties) {
        super(properties);
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH,SOUTH->{return NORTHSOUTH;}
            case EAST,WEST->{return EASTWEST;}
            default -> {return Shapes.block();}
        }
    }
}
