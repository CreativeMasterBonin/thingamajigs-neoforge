package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FlipBlock extends Block{
    public static BooleanProperty FLIPPED = BooleanProperty.create("flipped");

    public FlipBlock(Properties p) {
        super(p.strength(1f,50f).pushReaction(PushReaction.BLOCK).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FLIPPED,false));
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        if(bs.getValue(FLIPPED)){
            return Block.box(0.0D,0.0D,0.0D,0.0D,0.0D,0.0D);
        }
        else{
            return Shapes.block();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FLIPPED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FLIPPED,false);
    }
}
