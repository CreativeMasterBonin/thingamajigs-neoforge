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
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

@SuppressWarnings("deprecated")
public class BigTV extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final int MIN_TYPES = 0;
    public static final int MAX_TYPES = 4;
    public static final IntegerProperty TYPE = IntegerProperty.create("type", MIN_TYPES, MAX_TYPES);

    public static final VoxelShape NORTH_SHAPE = Shapes.join(
            Block.box(-15, 2, 7, 31, 32, 8),
            Block.box(5, 0, 5, 11, 2, 11),
            BooleanOp.OR);

    public static final VoxelShape EAST_SHAPE = Shapes.join(
            Block.box(8, 2, -15, 9, 32, 31),
            Block.box(5, 0, 5, 11, 2, 11),
            BooleanOp.OR);

    public static final VoxelShape SOUTH_SHAPE = Shapes.join(
            Block.box(-15, 2, 8, 31, 32, 9),
            Block.box(5, 0, 5, 11, 2, 11),
            BooleanOp.OR);

    public static final VoxelShape WEST_SHAPE = Shapes.join(
            Block.box(7, 2, -15, 8, 32, 31),
            Block.box(5, 0, 5, 11, 2, 11),
            BooleanOp.OR);

    public BigTV(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(TYPE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
        list.add(Component.translatable("block.tv.desc"));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH:return NORTH_SHAPE;
            case SOUTH:return SOUTH_SHAPE;
            case EAST:return EAST_SHAPE;
            case WEST:return WEST_SHAPE;
            default:return Shapes.block();
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        if(!pLevel.isClientSide()){
            if(pPlayer.getUsedItemHand() == InteractionHand.MAIN_HAND && pPlayer.isShiftKeyDown()){
                int tv_type = pState.getValue(TYPE);
                double d0 = (double)pPos.getX() + 0.5D;
                double d1 = (double)pPos.getY() + 0.5D;
                double d2 = (double)pPos.getZ() + 0.5D;

                tv_type++;
                pLevel.setBlock(pPos, pState.setValue(TYPE, tv_type), 0);

                if(tv_type >= MAX_TYPES){
                    tv_type = 0;
                    pLevel.setBlock(pPos, pState.setValue(TYPE, tv_type), 0);
                }

                playSound(pLevel, d0, d1, d2);
                return InteractionResult.SUCCESS;
            }
            else {
                return InteractionResult.CONSUME;
            }
        }
        return super.useWithoutItem(pState,pLevel,pPos,pPlayer,pHit);
    }

    // old
    public static void playSound(LevelAccessor world, double x, double y, double z) {
        if(world instanceof Level lvl) {
            if(!lvl.isClientSide()) {
                BlockPos bp = new BlockPos((int)x,(int)y,(int)z);
                lvl.playSound(null,bp,SoundEvents.ARMOR_EQUIP_GENERIC.value(),SoundSource.BLOCKS,10,1);
            }
            else {
                lvl.playLocalSound(x,y,z,SoundEvents.ARMOR_EQUIP_GENERIC.value(),SoundSource.BLOCKS,10,1,false);
            }
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
