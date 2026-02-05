package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class StandMixer extends ThingamajigsDecorativeBlock{
    public static final VoxelShape SHAPE_NS = Block.box(3,0,0,13,18,16);
    public static final VoxelShape SHAPE_EW = Block.box(0,0,3,16,18,13);

    public StandMixer(Properties properties) {
        super(properties.strength(1.1F,1F).sound(SoundType.LANTERN));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction d = bs.getValue(FACING);
        switch(d){
            case NORTH:
            case SOUTH:
                return SHAPE_NS;
            case EAST:
            case WEST:
                return SHAPE_EW;
            default:
                return Shapes.block();
        }
    }
}
