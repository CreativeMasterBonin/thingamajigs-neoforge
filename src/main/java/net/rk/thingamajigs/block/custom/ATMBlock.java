package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class ATMBlock extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_S = Stream.of(
            Block.box(0, 0, 0, 16, 16, 11),
            Block.box(0, 0, 11, 16, 32, 16),
            Block.box(0, 30, 0, 16, 32, 11),
            Block.box(0, 16, 0, 2, 30, 11),
            Block.box(14, 16, 0, 16, 30, 11),
            Block.box(-0.019999999999999574, 0, 0, -0.019999999999999574, 32, 16),
            Block.box(16.02, 0, 0, 16.02, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_S = Stream.of(
            Block.box(0, 0, 5, 16, 16, 16),
            Block.box(0, 0, 0, 16, 32, 5),
            Block.box(0, 30, 5, 16, 32, 16),
            Block.box(14, 16, 5, 16, 30, 16),
            Block.box(0, 16, 5, 2, 30, 16),
            Block.box(16.02, 0, 0, 16.02, 32, 16),
            Block.box(-0.019999999999999574, 0, 0, -0.019999999999999574, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_S = Stream.of(
            Block.box(5, 0, 0, 16, 16, 16),
            Block.box(0, 0, 0, 5, 32, 16),
            Block.box(5, 30, 0, 16, 32, 16),
            Block.box(5, 16, 0, 16, 30, 2),
            Block.box(5, 16, 14, 16, 30, 16),
            Block.box(0, 0, -0.019999999999999574, 16, 32, -0.019999999999999574),
            Block.box(0, 0, 16.02, 16, 32, 16.02)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_S = Stream.of(
            Block.box(0, 0, 0, 11, 16, 16),
            Block.box(11, 0, 0, 16, 32, 16),
            Block.box(0, 30, 0, 11, 32, 16),
            Block.box(0, 16, 14, 11, 30, 16),
            Block.box(0, 16, 0, 11, 30, 2),
            Block.box(0, 0, 16.02, 16, 32, 16.02),
            Block.box(0, 0, -0.019999999999999574, 16, 32, -0.019999999999999574)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ATMBlock(Properties p) {
        super(p.strength(1.2F, 1.5F).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch (direction) {
            case NORTH:
                return NORTH_S;
            case SOUTH:
                return SOUTH_S;
            case EAST:
                return EAST_S;
            case WEST:
                return WEST_S;
            default:
                return Shapes.block();
        }
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
        list.add(Component.translatable("block.thingamajigs.atm.desc").withStyle(ChatFormatting.GRAY));
    }
}
