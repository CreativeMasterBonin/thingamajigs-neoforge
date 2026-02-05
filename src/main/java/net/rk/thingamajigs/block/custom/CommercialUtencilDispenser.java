package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class CommercialUtencilDispenser extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NS = Block.box(2, 0, 0, 14, 17, 16);
    public static final VoxelShape EW = Block.box(0, 0, 2, 16, 17, 14);

    public CommercialUtencilDispenser(Properties properties) {
        super(properties.noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH,SOUTH:return NS;
            case EAST,WEST:return EW;
            default:return Shapes.block();
        }
    }
}
