package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class CinderBlockSmall extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH = Block.box(0, 0, 4, 16, 8, 12);
    public static final VoxelShape EASTWEST = Block.box(4, 0, 0, 12, 8, 16);

    public CinderBlockSmall(Properties properties){
        super(properties.strength(1.25f,20f).sound(SoundType.DEEPSLATE_TILES));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH:
            case SOUTH:
                return NORTHSOUTH;
            case EAST:
            case WEST:
                return EASTWEST;
            default: return Shapes.block();
        }
    }
}
