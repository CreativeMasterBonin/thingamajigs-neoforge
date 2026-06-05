package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.block.custom.CeilingFan;
import net.rk.thingamajigs.blockentity.TBlockEntity;

public class CeilingFanBE extends BlockEntity {
    public float yAngle = 0.0f;
    public boolean reversed = false;

    public CeilingFanBE(BlockPos pos, BlockState blockState) {
        super(TBlockEntity.CEILING_FAN_BE.get(), pos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putFloat("y_angle",yAngle);
        tag.putBoolean("reversed",reversed);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if(tag.contains("y_angle")){
            yAngle = tag.getFloat("y_angle");
        }
        if(tag.contains("reversed")){
            reversed = tag.getBoolean("reversed");
        }
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

    public static void clientTick(Level level, BlockPos pos, BlockState blockState, CeilingFanBE be){
        if(level.getBlockState(pos).getValue(CeilingFan.TOGGLED)){
            be.yAngle += 1.7f;
            be.yAngle = Mth.clamp(be.yAngle,0.0f,360.0f);
            if(be.yAngle >= 360.0f){
                be.yAngle = 0.0f;
            }
        }
        else{
            be.yAngle = 0.0f;
        }
    }
}
