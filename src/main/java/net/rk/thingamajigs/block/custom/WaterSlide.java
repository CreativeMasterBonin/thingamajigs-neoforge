package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

@SuppressWarnings("deprecated")
public class WaterSlide extends ThingamajigsDecorativeBlock{
    public WaterSlide(Properties p) {
        super(p.strength(1F,5F).noOcclusion().sound(SoundType.CALCITE));
    }

    @Override
    public VoxelShape getInteractionShape(BlockState bs, BlockGetter bg, BlockPos bp) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH:
                return EscalatorBlock.NORTH_SHAPE;
            case SOUTH:
                return EscalatorBlock.SOUTH_SHAPE;
            case EAST:
                return EscalatorBlock.EAST_SHAPE;
            case WEST:
                return EscalatorBlock.WEST_SHAPE;
            default: return Shapes.block();
        }
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        if(cc.isHoldingItem(TBlocks.WATER_SLIDE.get().asItem())){
            return Shapes.block();
        }
        else{
            return super.getVisualShape(bs,bg,bp,cc);
        }
    }
}
