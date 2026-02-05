package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.block.custom.DJLaserLight;
import net.rk.thingamajigs.blockentity.TBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

public class DJLaserLightBE extends BlockEntity {
    public int ticks;
    public boolean hidePose;

    public float angle = 90; // the angle for the beam to start at
    public float verticalAngle = 90;

    public int color = 16777215;

    public int height = 8;
    public int lightMode = 0;
    public float hOsc = 0;
    public float vOsc = 0;
    public float hMulti = 0;
    public float vMulti = 0;
    public float laserSize = 1.0f;
    public int laserCount = 1;
    public float angleOffset = 0;
    public float vAngleOffset = 0;
    public String colorstr = "FFFFFF";
    public String lightModeData = "";
    public String laserTextureLoc = "minecraft:textures/block/white_concrete.png";

    public DJLaserLightBE(BlockPos bp, BlockState bs) {
        super(TBlockEntity.DJ_LASER_LIGHT_BE.get(), bp, bs);
    }

    public void convertStrToColorInt(String str){
        colorstr = str;
        try{
            if(colorstr.isBlank()){
                colorstr = "FFFFFF";
            }

            color = Integer.parseInt(colorstr,16);
        }
        catch (Exception e){
            colorstr = "FFFFFF";
            color = 16777215;
            Logger.getAnonymousLogger().warning("Thingamajigs DJ Laser Light BE caught an error whilst updating colors! ERR: " + e.getMessage() + ". Color value must be in range 000000-FFFFFF!");
        }
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider slp) {
        super.saveAdditional(pTag, slp);
        pTag.putFloat("vert_angle",verticalAngle);
        pTag.putInt("height",height);
        pTag.putInt("light_mode",lightMode);
        pTag.putFloat("laser_size",laserSize);
        pTag.putInt("laser_count",laserCount);
        pTag.putString("color",colorstr);
        pTag.putFloat("hosc",hOsc);
        pTag.putFloat("vosc",vOsc);
        pTag.putFloat("hmulti",hMulti);
        pTag.putFloat("vmulti",vMulti);
        pTag.putFloat("angleoffset",angleOffset);
        pTag.putFloat("vangleoffset",vAngleOffset);
        pTag.putString("lightmodedata",lightModeData);
        pTag.putString("lasertextureloc",laserTextureLoc);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider lp) {
        verticalAngle = pTag.getFloat("vert_angle");
        height = pTag.getInt("height");
        lightMode = pTag.getInt("light_mode");
        laserSize = pTag.getFloat("laser_size");
        laserCount = pTag.getInt("laser_count");
        colorstr = pTag.getString("color");
        hOsc = pTag.getFloat("hosc");
        vOsc = pTag.getFloat("vosc");
        hMulti = pTag.getFloat("hmulti");
        vMulti = pTag.getFloat("vmulti");
        angleOffset = pTag.getFloat("angleoffset");
        vAngleOffset = pTag.getFloat("vangleoffset");
        lightModeData = pTag.getString("lightmodedata");
        laserTextureLoc = pTag.getString("lasertextureloc");
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        this.loadAdditional(tag,lookupProvider);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider lp) {
        CompoundTag ct = new CompoundTag();
        saveAdditional(ct,lp);
        return ct;
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
    }

    @Override
    public void clearRemoved() {
        super.clearRemoved();
    }


    public void updateBlock(){
        this.setChanged();
        if(this.getLevel() != null) {
            BlockState bs2 = this.getLevel().getBlockState(this.getBlockPos());
            this.getLevel().sendBlockUpdated(this.getBlockPos(), bs2, bs2, 3);
        }
    }

    public static void serverTick(Level slvl, BlockPos sbp, BlockState sbs, DJLaserLightBE sbe){
        ++sbe.ticks;
    }

    public static void clientTick(Level lvl, BlockPos bp, BlockState bs, DJLaserLightBE be){
        ++be.ticks;
        if(!be.getLevel().hasNearbyAlivePlayer((double)bp.getX() + 0.5, (double)bp.getY() + 0.5, (double)bp.getZ() + 0.5, 32)){
            be.hidePose = true;
        }
        else{
            try{
                be.convertStrToColorInt(be.colorstr);
            }
            catch (Exception e){
                be.colorstr = "FFFFFF";
                be.convertStrToColorInt("FFFFFF");
            }
            be.hidePose = !bs.getValue(DJLaserLight.ON);
            switch(bs.getValue(DJLaserLight.FACING)){
                case NORTH ->{
                    be.angle = 0;
                    return;
                }
                case SOUTH ->{
                    be.angle = 180;
                    return;
                }
                case EAST ->{
                    be.angle = 90;
                    return;
                }
                case WEST ->{
                    be.angle = 270;
                    return;
                }
            }
        }
    }
}
