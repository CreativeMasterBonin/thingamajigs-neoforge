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

import java.util.Optional;

@SuppressWarnings("deprecated")
public class StoreFreezer extends ToggledStateBlock {
    public static final VoxelShape ALL = Optional.of(Block.box(0.0,0.0,0.0,16.0,32.0,16.0)).get();

    public StoreFreezer(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }

    @Override
    public void playSound(BlockState bs, Level lvl, BlockPos bp){
        if(bs.getValue(TOGGLED)){
            lvl.playSound(null,bp, SoundEvents.BAMBOO_WOOD_DOOR_CLOSE, SoundSource.BLOCKS,1f,1.25f);
        }
        else{
            lvl.playSound(null,bp,SoundEvents.BAMBOO_WOOD_DOOR_OPEN, SoundSource.BLOCKS,1f,1.25f);
        }
    }
}
