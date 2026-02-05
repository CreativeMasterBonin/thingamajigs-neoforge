package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.ticks.TickPriority;

public class FakeFluidPump extends Block{
    public FakeFluidPump(Properties p) {
        super(p.strength(1f,15f).pushReaction(PushReaction.BLOCK).noOcclusion());
    }

    @Override
    protected void onPlace(BlockState bs, Level lvl, BlockPos bp, BlockState bs2, boolean b1) {
        lvl.scheduleTick(bp,this,5, TickPriority.EXTREMELY_LOW);
    }

    @Override
    protected void tick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        boolean belowLava = sl.getBlockState(bp.below()).is(Blocks.LAVA);
        boolean north = sl.getBlockState(bp.north()).is(Blocks.LAVA);
        boolean south = sl.getBlockState(bp.south()).is(Blocks.LAVA);
        boolean east = sl.getBlockState(bp.east()).is(Blocks.LAVA);
        boolean west = sl.getBlockState(bp.west()).is(Blocks.LAVA);

        if(belowLava && north && south && east && west) {
            if (sl.getBlockState(bp.above()).is(Blocks.AIR)) {
                sl.setBlock(bp.above(),Blocks.LAVA.defaultBlockState(),3);
            }
        }
        sl.scheduleTick(bp,this,5, TickPriority.EXTREMELY_LOW);
    }
}
