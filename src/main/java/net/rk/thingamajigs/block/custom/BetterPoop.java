package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

@SuppressWarnings("deprecated")
public class BetterPoop extends Block{
    public BetterPoop(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return Block.box(1.5D, 0.0D, 1.5D, 13.5D, 13.5D, 13.5D);
    }

    @Override
    public boolean canBeReplaced(BlockState bs, Fluid fl) {
        return false;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return new ItemStack(TBlocks.POOP.get().asItem());
    }

    @Override
    public boolean canSurvive(BlockState bs, LevelReader lr, BlockPos bp) {
        BlockState cbb = lr.getBlockState(bp.below());
        return Block.isFaceFull(cbb.getCollisionShape(lr, bp.below()), Direction.UP);
    }
}
