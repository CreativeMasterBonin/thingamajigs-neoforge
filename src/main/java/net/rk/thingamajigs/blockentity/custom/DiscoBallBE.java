package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.blockentity.TBlockEntity;

public class DiscoBallBE extends BlockEntity {
    public boolean spinning = false;
    public double speed = 1.0D;

    public DiscoBallBE(BlockPos pos, BlockState blockState) {
        super(TBlockEntity.DISCO_BALL_BE.get(),pos, blockState);
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
        this.saveAdditional(ct,prov);
        return ct;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putBoolean("spinning",spinning);
        tag.putDouble("speed",speed);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if(tag.contains("spinning")){
            spinning = tag.getBoolean("spinning");
        }
        if(tag.contains("speed")){
            speed = tag.getDouble("speed");
        }
    }
}
