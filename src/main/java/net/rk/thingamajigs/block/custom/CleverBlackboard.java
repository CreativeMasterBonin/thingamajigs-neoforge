package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.rk.thingamajigs.blockentity.custom.CleverBlackboardBE;
import org.jetbrains.annotations.Nullable;

public class CleverBlackboard extends ThingamajigsDecorativeBlock implements EntityBlock {
    public CleverBlackboard(Properties properties) {
        super(properties.strength(1f,2f).sound(SoundType.LANTERN).mapColor(MapColor.COLOR_LIGHT_GRAY).noOcclusion());
    }

    @Override
    public RenderShape getRenderShape(BlockState bs) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState bs) {
        return new CleverBlackboardBE(pos,bs);
    }
}
