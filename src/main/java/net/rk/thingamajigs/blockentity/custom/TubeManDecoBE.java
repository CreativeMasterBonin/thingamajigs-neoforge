package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.blockentity.TBlockEntity;
import net.rk.thingamajigs.xtras.TCalcStuff;

public class TubeManDecoBE extends BlockEntity {
    public float yAngle = 0.0f;
    public DyeColor color = DyeColor.BLUE;
    public float randomOffset = 0.0f;

    public TubeManDecoBE(BlockPos pos, BlockState blockState) {
        super(TBlockEntity.TUBE_MAN_DECO_BE.get(), pos, blockState);
        randomOffset = TCalcStuff.nextFloatBetweenInclusive(2.11f,3.29f);
    }

    public float getRandomOffset(){
        return this.randomOffset;
    }

    public void updateBlock(){
        this.setChanged();
        if(this.getLevel() != null) {
            BlockState bs2 = this.getLevel().getBlockState(this.getBlockPos());
            this.getLevel().sendBlockUpdated(this.getBlockPos(), bs2, bs2, 3);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        this.loadAdditional(tag,lookupProvider);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider prov) {
        CompoundTag ct = new CompoundTag();
        saveAdditional(ct,prov);
        return ct;
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putFloat("y_angle",yAngle);
        tag.putInt("color",color.getId());
        tag.putFloat("random_offset",randomOffset);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if(tag.contains("y_angle")){
            yAngle = tag.getFloat("y_angle");
        }
        if(tag.contains("color")){
            color = DyeColor.byId(tag.getInt("color"));
        }
        if(tag.contains("random_offset")){
            randomOffset = tag.getFloat("random_offset");
        }
    }
}
