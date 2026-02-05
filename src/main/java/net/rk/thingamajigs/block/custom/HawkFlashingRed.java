package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.item.TItems;
import org.jetbrains.annotations.Nullable;

public class HawkFlashingRed extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public HawkFlashingRed(Properties p) {
        super(p.strength(1F,2F).sound(SoundType.LANTERN).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {
        return TBlocks.HAWK_SIGNAL.asItem().getDefaultInstance();
    }

    @SuppressWarnings("deprecated")
    @Override
    public void onPlace(BlockState bs1, Level lvl, BlockPos bp, BlockState bs2, boolean b1) {
        lvl.scheduleTick(bp,this,100, TickPriority.LOW);
    }

    @SuppressWarnings("deprecated")
    @Override
    public void tick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        BlockState nbs = TBlocks.HAWK_SIGNAL.get().defaultBlockState();
        sl.setBlock(bp,nbs.setValue(FACING,bs.getValue(FACING)),3);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
