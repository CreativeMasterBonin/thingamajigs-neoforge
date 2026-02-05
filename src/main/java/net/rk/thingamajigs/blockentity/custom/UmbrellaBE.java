package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.blockentity.TBlockEntity;

public class UmbrellaBE extends BlockEntity{
    BlockPos bp;
    public float yAngle = 0.0f;

    public float base_x = 0.0f;
    public float base_z = 0.0f;
    public float base_x_rot = 0.0f;
    public float base_z_rot = 0.0f;

    public float pole_x_rot = 0.0f;
    public float pole_y_rot = 0.0f;
    public float pole_z_rot = 0.0f;

    public boolean custom = false;
    public String texture_name = "umbrella";
    public String texture_namespace = "thingamajigs";
    private int color = 0;

    public ResourceLocation rl(){
        return ResourceLocation.parse(getRLAsFull());
    }

    public String getRLAsFull(){
        String strLoc = texture_namespace + ":textures/entity/" + texture_name + ".png";
        if(ResourceLocation.tryParse(strLoc) != null){
            return texture_namespace + ":textures/entity/" + texture_name + ".png";
        }
        return "thingamajigs:textures/entity/umbrella.png";
    }

    public UmbrellaBE(BlockPos p_155229_, BlockState p_155230_) {
        super(TBlockEntity.UMBRELLA_BE.get(), p_155229_, p_155230_);
        this.bp = getBlockPos();
        this.base_x_rot = 0.17f;
        this.pole_x_rot = 0.21f;
        this.base_z_rot = 0.02f;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider p_323635_) {
        super.saveAdditional(pTag, p_323635_);
        pTag.putFloat("y_angle",yAngle);
        pTag.putBoolean("custom_settings",custom);
        pTag.putFloat("base_x",base_x);
        pTag.putFloat("base_z",base_z);

        pTag.putFloat("base_x_rot",base_x_rot);
        pTag.putFloat("base_z_rot",base_z_rot);

        pTag.putFloat("pole_x_rot",pole_x_rot);
        pTag.putFloat("pole_y_rot",pole_y_rot);
        pTag.putFloat("pole_z_rot",pole_z_rot);
        pTag.putString("texture_name",texture_name);
        pTag.putString("texture_namespace",texture_namespace);
        pTag.putInt("color",color);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider p_338445_) {
        yAngle = pTag.getFloat("y_angle");
        custom = pTag.getBoolean("custom_settings");
        base_x = pTag.getFloat("base_x");
        base_z = pTag.getFloat("base_z");

        base_x_rot = pTag.getFloat("base_x_rot");
        base_z_rot = pTag.getFloat("base_z_rot");

        pole_x_rot = pTag.getFloat("pole_x_rot");
        pole_y_rot = pTag.getFloat("pole_y_rot");
        pole_z_rot = pTag.getFloat("pole_z_rot");
        texture_name = pTag.getString("texture_name");
        texture_namespace = pTag.getString("texture_namespace");
        if(texture_namespace.isBlank()){
            texture_namespace = "thingamajigs";
        }
        if(texture_name.isBlank()){
            texture_name = "umbrella";
        }
        color = pTag.getInt("color");
    }

    public void updateBlock(){
        this.setChanged();
        if(this.getLevel() != null) {
            BlockState bs2 = this.getLevel().getBlockState(this.getBlockPos());
            this.getLevel().sendBlockUpdated(this.getBlockPos(), bs2, bs2, 3);
        }
    }

    public void setColor(int color){
        this.color = color;
        updateBlock();
    }

    public int getColor(){
        return this.color;
    }



    public ClientboundBlockEntityDataPacket getUpdatePacket(){return ClientboundBlockEntityDataPacket.create(this);}

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        this.loadAdditional(tag,lookupProvider);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag ct = new CompoundTag();
        saveAdditional(ct,provider);
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
}
