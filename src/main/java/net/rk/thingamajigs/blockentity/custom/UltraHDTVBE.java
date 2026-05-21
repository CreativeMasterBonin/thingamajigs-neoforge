package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.blockentity.TBlockEntity;

public class UltraHDTVBE extends BlockEntity {
    public float yAngle = 0.0f;
    public boolean customRotation = false;
    public int currentChannel = 0;
    public int randomColor = 0;

    public UltraHDTVBE(BlockPos pos, BlockState blockState) {
        super(TBlockEntity.ULTRA_HD_TV.get(), pos, blockState);
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
        tag.putBoolean("custom_rotation",customRotation);
        tag.putInt("current_channel",currentChannel);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if(tag.contains("y_angle")){
            yAngle = tag.getFloat("y_angle");
        }
        if(tag.contains("custom_rotation")){
            customRotation = tag.getBoolean("custom_rotation");
        }
        if(tag.contains("current_channel")){
            currentChannel = tag.getInt("current_channel");
        }
    }

    public static void clientTick(Level lvl, BlockPos bp, BlockState bs, UltraHDTVBE tv){
        if(lvl.getRandom().nextIntBetweenInclusive(2,12003) % 51 == 0){
            tv.randomColor = lvl.getRandom().nextIntBetweenInclusive(1,16777215);
        }
    }
}
