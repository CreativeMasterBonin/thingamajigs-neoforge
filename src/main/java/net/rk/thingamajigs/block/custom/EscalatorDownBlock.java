package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("deprecated")
public class EscalatorDownBlock extends EscalatorBlock{
    public EscalatorDownBlock(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public void stepOn(Level lvl, BlockPos bp, BlockState bs, Entity e) {
        double x = 0.0D;
        double y = 0.0D;
        double z = 0.0D;

        if(bs.getValue(FACING) == Direction.NORTH && lvl.getBlockState(bp.north().above()).getBlock() == this){
            y = 0.5D;
        }
        if(bs.getValue(FACING) == Direction.SOUTH && lvl.getBlockState(bp.south().above()).getBlock() == this){
            y = 0.5D;
        }
        if(bs.getValue(FACING) == Direction.EAST && lvl.getBlockState(bp.east().above()).getBlock() == this){
            y = 0.5D;
        }
        if(bs.getValue(FACING) == Direction.WEST && lvl.getBlockState(bp.west().above()).getBlock() == this){
            y = 0.5D;
        }

        switch(bs.getValue(FACING)){
            case NORTH -> z = -0.25D;
            case SOUTH -> z = 0.25D;
            case EAST -> x = 0.25D;
            case WEST -> x = -0.25D;
        }

        if(e instanceof Player){
            e.setDeltaMovement(x,y,z);
        }
    }
}
