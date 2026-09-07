package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class SaltTank extends ToggledStateBlock{
    public static final VoxelShape ALL = Block.box(0, 0, 0, 16, 26, 16);
    public SaltTank(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return ALL;
    }

    @Override
    public void playSound(BlockState bs, Level lvl, BlockPos bp){
        if(bs.getValue(TOGGLED)){
            lvl.playSound(null,bp, SoundEvents.COPPER_DOOR_CLOSE, SoundSource.BLOCKS,1f,0.75f);
        }
        else{
            lvl.playSound(null,bp,SoundEvents.COPPER_DOOR_OPEN, SoundSource.BLOCKS,1f,0.75f);
        }
    }
}
