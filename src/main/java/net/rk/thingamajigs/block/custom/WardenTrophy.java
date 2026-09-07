package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class WardenTrophy extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(3, 5, 6, 13, 14, 11),
            Block.box(0, 0, 15, 16, 16, 16),
            Block.box(4, 6, 11, 12, 12, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(5, 5, 3, 10, 14, 13),
            Block.box(0, 0, 0, 1, 16, 16),
            Block.box(1, 6, 4, 5, 12, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(3, 5, 5, 13, 14, 10),
            Block.box(0, 0, 0, 16, 16, 1),
            Block.box(4, 6, 1, 12, 12, 5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(6, 5, 3, 11, 14, 13),
            Block.box(15, 0, 0, 16, 16, 16),
            Block.box(11, 6, 4, 15, 12, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public WardenTrophy(Properties properties) {
        super(properties.sound(SoundType.SCULK_SHRIEKER).strength(1.0F,50F).noOcclusion());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState p_60503_, Level level, BlockPos pos, Player player, BlockHitResult p_60508_) {
        if(player.isShiftKeyDown()){
            level.playSound(null,pos, SoundEvents.WARDEN_ANGRY, SoundSource.BLOCKS,1.0F,1.0F);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack is, Item.TooltipContext tc, List<Component> list, TooltipFlag tf) {
        super.appendHoverText(is,tc,list,tf);
        list.add(Component.translatable("tooltip.thingamajigs.warden_trophy"));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH->{return NORTH;}
            case SOUTH->{return SOUTH;}
            case EAST->{return EAST;}
            case WEST->{return WEST;}
            default -> {return Shapes.block();}
        }
    }
}
