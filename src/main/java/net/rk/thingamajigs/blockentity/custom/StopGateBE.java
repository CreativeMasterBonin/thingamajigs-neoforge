package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.block.custom.StopGate;
import net.rk.thingamajigs.blockentity.TBlockEntity;

public class StopGateBE extends BlockEntity {
    public float gateAngle = 0.0f;
    public double offsetX = 0.5D;
    public double offsetY = -1.0D;
    public double offsetZ = 0.375D;

    public float northXRot = 0.5f;
    public float southXRot = 0.5f;
    public float eastXRot = 0.0f;
    public float westXRot = 0.0f;

    public float northYRot = 0.43f;
    public float southYRot = 0.43f;
    public float eastYRot = 0.43f;
    public float westYRot = 0.43f;

    public float northZRot = 0.0f;
    public float southZRot = 0.0f;
    public float eastZRot = 0.5f;
    public float westZRot = 0.5f;

    public boolean inverse = false;

    public StopGateBE(BlockPos pos, BlockState blockState) {
        super(TBlockEntity.STOP_GATE_BE.get(), pos, blockState);
    }

    public void updateBlock(){
        this.setChanged();
        if(this.getLevel() != null){
            this.getLevel().sendBlockUpdated(this.getBlockPos(),this.getBlockState(),this.getBlockState(),3);
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
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag,registries);
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putFloat("gate_angle",gateAngle);
        tag.putBoolean("inverse",inverse);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if(tag.contains("gate_angle")){
            gateAngle = tag.getFloat("gate_angle");
        }
        if(tag.contains("inverse")){
            inverse = tag.getBoolean("inverse");
        }
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, StopGateBE stopGate){
        if(state.hasProperty(StopGate.LIT)){
            if(stopGate.inverse){
                if(state.getValue(StopGate.LIT)){
                    if(stopGate.gateAngle > -90.0f){
                        stopGate.gateAngle -= 0.5f;
                        stopGate.updateBlock();
                    }
                }
                else{
                    if(stopGate.gateAngle < 0.0f){
                        stopGate.gateAngle += 0.5f;
                        stopGate.updateBlock();
                    }
                }
            }
            else{
                if(state.getValue(StopGate.LIT)){
                    if(stopGate.gateAngle < 90.0f){
                        stopGate.gateAngle += 0.5f;
                        stopGate.updateBlock();
                    }
                }
                else{
                    if(stopGate.gateAngle > 0.0f){
                        stopGate.gateAngle -= 0.5f;
                        stopGate.updateBlock();
                    }
                }
            }
        }
    }
}
