package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class BiohazardBin extends ThingamajigsDecorativeBlock{
    public static final VoxelShape BIN = Stream.of(
            Block.box(1, 0, 1, 15, 1, 15),
            Block.box(1, 0, 1, 14, 16, 3),
            Block.box(2, 0, 13, 15, 16, 15),
            Block.box(1, 0, 2, 3, 16, 15),
            Block.box(13, 0, 1, 15, 16, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BiohazardBin(Properties properties) {
        super(properties.sound(SoundType.CALCITE).strength(1F,25F));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return BIN;
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
        list.add(Component.translatable("tooltip.thingamajigs.useless_storage"));
    }
}
