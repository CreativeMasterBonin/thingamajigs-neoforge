package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.xtras.TCalcStuff;

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

        if(itemstack.isEmpty()){
            if(lvl.isClientSide()){
                if(pl.canEat(true)){
                    if(lvl.getBlockState(bp).hasProperty(FULLNESS)){
                        if(lvl.getBlockState(bp).getValue(FULLNESS) >= 3){
                            pl.playSound(SoundEvents.GENERIC_EAT,0.75f, TCalcStuff.nextFloatBetweenInclusive(0.97f,1.1f));
                        }
                        else{
                            pl.playSound(SoundEvents.GENERIC_EAT,0.75f, TCalcStuff.nextFloatBetweenInclusive(0.94f,0.98f));
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
            else{
                if(pl.canEat(true)){
                    pl.getFoodData().eat(1,1);
                    lvl.gameEvent(pl, GameEvent.EAT,bp);
                    if(lvl.getBlockState(bp).hasProperty(FULLNESS)){
                        if(lvl.getBlockState(bp).getValue(FULLNESS).intValue() >= 3){
                            lvl.setBlock(bp, Blocks.AIR.defaultBlockState(),3);
                            lvl.gameEvent(pl,GameEvent.BLOCK_DESTROY,bp);
                        }
                        else{
                            lvl.setBlock(bp,bs.cycle(FULLNESS),3);
                        }
                    }
                    return InteractionResult.CONSUME;
                }
            }
        }
        return InteractionResult.PASS;
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
