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

@SuppressWarnings("deprecated")
public class WaterFountain extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Shapes.join(Block.box(0, 0, 10, 16, 8, 16), Block.box(0, 8, 0, 16, 16, 16), BooleanOp.OR);
    public static final VoxelShape SOUTH = Shapes.join(Block.box(0, 0, 0, 16, 8, 6), Block.box(0, 8, 0, 16, 16, 16), BooleanOp.OR);
    public static final VoxelShape EAST = Shapes.join(Block.box(0, 0, 0, 6, 8, 16), Block.box(0, 8, 0, 16, 16, 16), BooleanOp.OR);
    public static final VoxelShape WEST = Shapes.join(Block.box(10, 0, 0, 16, 8, 16), Block.box(0, 8, 0, 16, 16, 16), BooleanOp.OR);

    public WaterFountain(Properties properties) {
        super(properties.strength(1F,10F).sound(SoundType.LANTERN).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = bs.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case EAST:
                return EAST;
            case WEST:
                return WEST;
            default:
                return Shapes.block();
        }
    }
}
