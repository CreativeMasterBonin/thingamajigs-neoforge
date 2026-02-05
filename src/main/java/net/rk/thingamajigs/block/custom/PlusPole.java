package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class PlusPole extends Pole{
    public PlusPole(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = bs.getValue(FACING);
        switch(direction){
            case NORTH:
            case SOUTH:
                return PLUS_NORTHSOUTH;
            case EAST:
            case WEST:
                return PLUS_EASTWEST;
            default: return Shapes.block();
        }
    }
}
