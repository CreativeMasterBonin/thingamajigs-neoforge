package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.datagen.TTag;

public class BushLikeBlock extends BushBlock{
    public static final MapCodec<BushLikeBlock> CODEC = simpleCodec(BushLikeBlock::new);
    public final boolean overridesPlacementRules;
    public static final VoxelShape FLOWER_ALL = Block.box(5.0, 0.0, 5.0, 11.0, 10.0, 11.0);
    public static final VoxelShape SAPLING_ALL = Block.box(3.0, 0.0, 3.0, 13.0, 8.0, 13.0);

    public BushLikeBlock(Properties p) {
        super(p.instabreak().offsetType(OffsetType.XZ).pushReaction(PushReaction.DESTROY).noCollission());
        this.overridesPlacementRules = false;
    }

    public BushLikeBlock(Properties p, boolean overridesPlacementRules) {
        super(p.instabreak().offsetType(OffsetType.XZ).pushReaction(PushReaction.DESTROY).noCollission());
        this.overridesPlacementRules = overridesPlacementRules;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if(overridesPlacementRules){
            return !(state.getBlock() instanceof FlowerBlock
                    || state.getBlock() instanceof TallFlowerBlock
                    || state.getBlock() instanceof BushLikeBlock
                    || state.getBlock() instanceof TallGrassBlock
                    || state.getBlock() instanceof SeagrassBlock
                    || state.getBlock() instanceof TallSeagrassBlock);
        }
        return super.canSurvive(state, level, pos);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos){
        if(overridesPlacementRules){
            return true;
        }
        return state.is(TTag.SUPPORTS_BUSH_LIKE) || state.getBlock() instanceof FarmBlock;
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }
}
