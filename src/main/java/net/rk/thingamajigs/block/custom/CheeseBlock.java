package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

@SuppressWarnings("deprecated")
public class CheeseBlock extends Block{
    public static final VoxelShape FULL = Block.box(
            0.0D,0.0D,0.0D,16.0D,16.0D,16.0D);
    public static final VoxelShape ALMOST_FULL = Block.box(
            0.0D,0.0D,0.0D,16.0D,12.0D,16.0D);
    public static final VoxelShape NEARLY_FULL = Block.box(
            0.0D,0.0D,0.0D,16.0D,8.0D,16.0D);
    public static final VoxelShape BARELY_FULL = Block.box(
            0.0D,0.0D,0.0D,16.0D,4.0D,16.0D);

    public static final IntegerProperty FULLNESS = IntegerProperty.create("fullness",0,3);

    public CheeseBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FULLNESS,0));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FULLNESS)){
            case 0: return FULL;
            case 1: return ALMOST_FULL;
            case 2: return NEARLY_FULL;
            case 3: return BARELY_FULL;
        }
        return Shapes.block();
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player pl, BlockHitResult bhr) {
        ItemStack itemstack = pl.getItemInHand(pl.getUsedItemHand());

        if (lvl.isClientSide) {
            if (eat(lvl, bp, bs, pl).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }
        return eat(lvl, bp, bs, pl);
    }

    private static InteractionResult eat(LevelAccessor lvla, BlockPos bp, BlockState bs, Player pl) {
        if (!pl.canEat(false)) {
            return InteractionResult.PASS;
        }
        else {
            pl.awardStat(Stats.EAT_CAKE_SLICE);
            pl.getFoodData().eat(1, 0.5F);
            int i = bs.getValue(FULLNESS);
            lvla.gameEvent(pl, GameEvent.EAT, bp);

            float sfxPitch = lvla.getRandom().nextFloat() + 0.25F;
            lvla.playSound(pl,bp, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS,0.25F,(float)sfxPitch);

            if (i < 3) {
                lvla.setBlock(bp, bs.setValue(FULLNESS, i + 1), 3);
            }
            else {
                lvla.removeBlock(bp, false);
                lvla.gameEvent(pl, GameEvent.BLOCK_DESTROY, bp);
            }
            return InteractionResult.SUCCESS;
        }
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
        list.add(Component.translatable("block.thingamajigs.cheese_block.desc"));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FULLNESS);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FULLNESS,0);
    }
}
