package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.xtras.TSoundType;

@SuppressWarnings("deprecated")
public class PoopBlock extends Block{
    public PoopBlock(Properties p) {
        super(p.sound(TSoundType.POOP));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack is, BlockState bs, Level lvl, BlockPos bp, Player ply, InteractionHand h, BlockHitResult bhr) {
        BlockState nbs = TBlocks.POOPOO.get().defaultBlockState();

        if(!lvl.isClientSide()){
            boolean handismain = h.equals(InteractionHand.MAIN_HAND);
            boolean shiftdown = ply.isShiftKeyDown();
            boolean requiredItem = ply.getMainHandItem().is(Items.MUSIC_DISC_CHIRP);

            if(requiredItem){
                lvl.setBlock(bp,nbs,3);
                lvl.playSound(null,bp.above(), SoundEvents.ILLUSIONER_CAST_SPELL, SoundSource.BLOCKS,1.0f,1.0f);
                ply.swing(InteractionHand.MAIN_HAND);
                return ItemInteractionResult.CONSUME;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return Block.box(1.5D, 0.0D, 1.5D, 13.5D, 13.5D, 13.5D);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockState cbb = levelReader.getBlockState(blockPos.below());
        return Block.isFaceFull(cbb.getCollisionShape(levelReader, blockPos.below()), Direction.UP);
    }
}
