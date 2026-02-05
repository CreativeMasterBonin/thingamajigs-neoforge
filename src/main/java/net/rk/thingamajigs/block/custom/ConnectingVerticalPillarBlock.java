package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

@SuppressWarnings("deprecated")
public class ConnectingVerticalPillarBlock extends Block{
    public static final IntegerProperty TYPE = IntegerProperty.create("type",0,3);
    public ConnectingVerticalPillarBlock(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, 3));
    }

    @Override
    public void neighborChanged(BlockState state, Level lvl, BlockPos bp, Block block, BlockPos pos, boolean bl) {
        // if a pillar is on top
        if(lvl.getBlockState(bp.above()).is(state.getBlock())){
            // if a pillar is on top and below
            if(lvl.getBlockState(bp.below()).is(state.getBlock())){
                // set to middle
                lvl.setBlock(bp,state.setValue(TYPE,1),3);
            }
            // if a pillar is on top and not bottom
            else{
                // set to bottom
                lvl.setBlock(bp,state.setValue(TYPE,0),3);
            }
        }
        // if not a pillar is on top
        else{
            // if not a pillar on top but on bottom
            if(lvl.getBlockState(bp.below()).is(state.getBlock())){
                // set to top
                lvl.setBlock(bp,state.setValue(TYPE,2),3);
            }
            // if neither pillar on top nor bottom
            else{
                // set to free-standing pillar
                lvl.setBlock(bp,state.setValue(TYPE,3),3);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(TYPE, 3);
    }
}
