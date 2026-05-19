package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.rk.thingamajigs.block.custom.ShortCarWashBrush;
import net.rk.thingamajigs.blockentity.TBlockEntity;

public class CarWashBrushBE extends BlockEntity {
    public float yAngle = 0.0f;
    public float extensionAngle = 0.0f;
    // unneeded, so unused
    /*public BrushState currentState = BrushState.RETRACTED;
    public enum BrushState implements StringRepresentable {
        RETRACTED("retracted"),
        EXTENDING("extending"),
        EXTENDED("extended"),
        RETRACTING("retracting");

        private final String name;

        BrushState(String name){
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }*/

    public CarWashBrushBE(BlockPos pos, BlockState blockState) {
        super(TBlockEntity.CAR_WASH_BRUSH_BE.get(), pos, blockState);
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

    // only on client side as BE does not need server to show its blades spinning
    public static void clientTick(Level slvl, BlockPos sbp, BlockState sbs, CarWashBrushBE carWashBrush){
        if(sbs.getValue(BlockStateProperties.LIT)){
            if(sbs.getBlock() instanceof ShortCarWashBrush){
                if(carWashBrush.extensionAngle < 50.0f){
                    carWashBrush.extensionAngle += 3.5f / (float)Util.getMillis() + 2.5f;
                }
                else{
                    carWashBrush.extensionAngle = 50.0f;
                }
            }
            else{
                if(carWashBrush.extensionAngle < 80.0f){
                    carWashBrush.extensionAngle += 3.5f / (float)Util.getMillis() + 2.5f;
                }
                else{
                    carWashBrush.extensionAngle = 80.0f;
                }
            }
            carWashBrush.yAngle += 5.0f;
            if(carWashBrush.yAngle >= 360.0f || carWashBrush.yAngle <= -360.0f){
                carWashBrush.yAngle = 0.0f;
            }
        }
        else{
            if(sbs.getBlock() instanceof ShortCarWashBrush){
                if(carWashBrush.extensionAngle > 6.0f){
                    carWashBrush.extensionAngle -= 1.2f;
                }
                else if(carWashBrush.extensionAngle < -6.0f){
                    carWashBrush.extensionAngle += 1.2f;
                }
                else{
                    carWashBrush.extensionAngle = 6.0f;
                }
            }
            else{
                if(carWashBrush.extensionAngle > 1.0f){
                    carWashBrush.extensionAngle -= 1.2f;
                }
                else if(carWashBrush.extensionAngle < -1.0f){
                    carWashBrush.extensionAngle += 1.2f;
                }
                else{
                    carWashBrush.extensionAngle = 0.0f;
                }
            }

            if(carWashBrush.yAngle != 0.0f){
                if(carWashBrush.yAngle > 1.0f){
                    carWashBrush.yAngle -= 1.2f;
                }
                else if(carWashBrush.yAngle < -1.0f){
                    carWashBrush.yAngle += 1.2f;
                }
                else{
                    carWashBrush.yAngle = 0.0f;
                }
            }
        }
    }
}
