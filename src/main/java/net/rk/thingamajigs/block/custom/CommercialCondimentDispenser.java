package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class CommercialCondimentDispenser extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NS_CDIS = Block.box(1, 0, 3, 15, 10, 13);
    public static final VoxelShape EW_CDIS = Block.box(3, 0, 1, 13, 10, 15);

    public CommercialCondimentDispenser(Properties properties) {
        super(properties.noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH,SOUTH:return NS_CDIS;
            case EAST,WEST:return EW_CDIS;
            default:return Shapes.block();
        }
    }
}
