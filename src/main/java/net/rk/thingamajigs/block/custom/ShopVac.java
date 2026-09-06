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

public class ShopVac extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(6, 6.5, -4.5, 10, 14.5, -0.5),
            Block.box(0, 2, 0, 16, 18, 16),
            Block.box(0, 0, 0, 2, 2, 2),
            Block.box(14, 0, 0, 16, 2, 2),
            Block.box(0, 0, 14, 2, 2, 16),
            Block.box(14, 0, 14, 16, 2, 16),
            Block.box(1, 18, 1, 15, 20, 15),
            Block.box(2, 20, 2, 14, 21, 14),
            Block.box(6, 12, -1, 10, 16, 0),
            Block.box(6, 6, -2, 10, 10, -1),
            Block.box(7, 3, -1, 9, 9, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(16.5, 6.5, 6, 20.5, 14.5, 10),
            Block.box(0, 2, 0, 16, 18, 16),
            Block.box(14, 0, 0, 16, 2, 2),
            Block.box(14, 0, 14, 16, 2, 16),
            Block.box(0, 0, 0, 2, 2, 2),
            Block.box(0, 0, 14, 2, 2, 16),
            Block.box(1, 18, 1, 15, 20, 15),
            Block.box(2, 20, 2, 14, 21, 14),
            Block.box(16, 12, 6, 17, 16, 10),
            Block.box(17, 6, 6, 18, 10, 10),
            Block.box(16, 3, 7, 17, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(6, 6.5, 16.5, 10, 14.5, 20.5),
            Block.box(0, 2, 0, 16, 18, 16),
            Block.box(14, 0, 14, 16, 2, 16),
            Block.box(0, 0, 14, 2, 2, 16),
            Block.box(14, 0, 0, 16, 2, 2),
            Block.box(0, 0, 0, 2, 2, 2),
            Block.box(1, 18, 1, 15, 20, 15),
            Block.box(2, 20, 2, 14, 21, 14),
            Block.box(6, 12, 16, 10, 16, 17),
            Block.box(6, 6, 17, 10, 10, 18),
            Block.box(7, 3, 16, 9, 9, 17)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(-4.5, 6.5, 6, -0.5, 14.5, 10),
            Block.box(0, 2, 0, 16, 18, 16),
            Block.box(0, 0, 14, 2, 2, 16),
            Block.box(0, 0, 0, 2, 2, 2),
            Block.box(14, 0, 14, 16, 2, 16),
            Block.box(14, 0, 0, 16, 2, 2),
            Block.box(1, 18, 1, 15, 20, 15),
            Block.box(2, 20, 2, 14, 21, 14),
            Block.box(-1, 12, 6, 0, 16, 10),
            Block.box(-2, 6, 6, -1, 10, 10),
            Block.box(-1, 3, 7, 0, 9, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ShopVac(Properties p) {
        super(p.sound(SoundType.STONE).strength(1F,1F));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default -> {return Shapes.block();
            }
        }
    }
}
