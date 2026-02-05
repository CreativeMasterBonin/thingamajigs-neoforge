package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

@SuppressWarnings("deprecated")
public class SecurityCameraQuintDirectional extends Block{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final int MIN_TYPES = 0;
    public static final int MAX_TYPES = 5;
    public static final IntegerProperty TYPE = IntegerProperty.create("type", MIN_TYPES, MAX_TYPES);

    public SecurityCameraQuintDirectional(Properties p) {
        super(p.strength(1F,32F).noOcclusion().sound(SoundType.LANTERN).noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(TYPE,0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if(!lvl.isClientSide()){
            if(ply.getUsedItemHand() == InteractionHand.MAIN_HAND && ply.isShiftKeyDown()){
                int rotated_type = bs.getValue(TYPE);
                double d0 = (double)bp.getX() + 0.5D;
                double d1 = (double)bp.getY() + 0.5D;
                double d2 = (double)bp.getZ() + 0.5D;

                rotated_type++;
                lvl.setBlock(bp,bs.setValue(TYPE, rotated_type), 0);

                if(rotated_type >= MAX_TYPES){
                    rotated_type = 0;
                    lvl.setBlock(bp,bs.setValue(TYPE, rotated_type), 0);
                }

                // play noise to alert player of change in state
                playSound(lvl, d0, d1, d2);
                return InteractionResult.SUCCESS;
            }
            else {
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.sidedSuccess(lvl.isClientSide);
    }

    // Old Thingamajigs sound method condensed for when using vanilla Minecraft sounds
    public static void playSound(LevelAccessor world, double x, double y, double z) {
        if(world instanceof Level lvl) {
            if(!lvl.isClientSide()) {
                lvl.playSound(null, x, y, z, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS,1,1);
            }
            else {
                lvl.playLocalSound(x, y, z, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundSource.BLOCKS, 1, 1, false);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
        list.add(Component.literal("§eHas 5 angles, Shift right click on the block to change it"));
    }

    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
