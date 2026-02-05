package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class DumpsterBlock extends ToggledStateBlock{
    public static final VoxelShape NSSHAPE = Stream.of(
            Block.box(-8, 1, 0, 24, 13, 16),
            Block.box(-8, 13, 0, 8, 15, 16),
            Block.box(8, 13, 0, 24, 15, 16),
            Block.box(-7, 0, 1, -6, 1, 2),
            Block.box(22, 0, 1, 23, 1, 2),
            Block.box(-7, 0, 14, -6, 1, 15),
            Block.box(22, 0, 14, 23, 1, 15),
            Block.box(-6, 15, 1, 8, 16, 15),
            Block.box(8, 15, 1, 22, 16, 15),
            Block.box(-4, 16, 3, 6, 17, 13),
            Block.box(10, 16, 3, 20, 17, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EWSHAPE = Stream.of(
            Block.box(0, 1, -8, 16, 13, 24),
            Block.box(0, 13, 8, 16, 15, 24),
            Block.box(0, 13, -8, 16, 15, 8),
            Block.box(1, 0, 22, 2, 1, 23),
            Block.box(1, 0, -7, 2, 1, -6),
            Block.box(14, 0, 22, 15, 1, 23),
            Block.box(14, 0, -7, 15, 1, -6),
            Block.box(1, 15, 8, 15, 16, 22),
            Block.box(1, 15, -6, 15, 16, 8),
            Block.box(3, 16, 10, 13, 17, 20),
            Block.box(3, 16, -4, 13, 17, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public DumpsterBlock(Properties p) {
        super(p.noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH,SOUTH: return NSSHAPE;
            case EAST,WEST: return EWSHAPE;
            default: return Shapes.block();
        }
    }

    @Override
    public void playSound(BlockState bs, Level lvl, BlockPos bp){
        if(bs.getValue(TOGGLED)){
            lvl.playSound(null,bp, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.BLOCKS,1f,0.5f);
        }
        else{
            lvl.playSound(null,bp,SoundEvents.ARMOR_EQUIP_NETHERITE.value(), SoundSource.BLOCKS,1f,0.75f);
        }
    }
}
