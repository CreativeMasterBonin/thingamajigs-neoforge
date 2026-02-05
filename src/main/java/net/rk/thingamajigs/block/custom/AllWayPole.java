package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class AllWayPole extends Pole{
    public static VoxelShape ALL = Stream.of(PLUS_NORTHSOUTH,PLUS_EASTWEST)
            .reduce((v1, v2) -> Shapes.join(v1,v2, BooleanOp.OR)).get();

    public AllWayPole(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return ALL;
    }
}
