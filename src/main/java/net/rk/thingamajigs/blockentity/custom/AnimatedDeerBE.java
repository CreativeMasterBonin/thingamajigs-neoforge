package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.rk.thingamajigs.blockentity.TBlockEntity;

public class AnimatedDeerBE extends BlockEntity{
    BlockPos bp;
    public int ticks;
    public float yAngle = 0.0f;
    public boolean custom = false;
    public float headAngle = 0.0f;
    public float gearAngle = 0.0f;
    public boolean showAntlers = false;
    public boolean alternateMovement = false;

    public AnimatedDeerBE(BlockPos pos, BlockState blockState) {
        super(TBlockEntity.ANIMATED_DEER_BE.get(), pos, blockState);
        this.bp = pos;
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
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putFloat("y_angle",yAngle);
        tag.putBoolean("custom",custom);
        tag.putFloat("head_angle",headAngle);
        tag.putBoolean("show_antlers",showAntlers);
        tag.putBoolean("alternate_movement",alternateMovement);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if(tag.contains("y_angle"))
            yAngle = tag.getFloat("y_angle");
        if(tag.contains("custom"))
            custom = tag.getBoolean("custom");
        if(tag.contains("head_angle"))
            headAngle = tag.getFloat("head_angle");
        if(tag.contains("show_antlers"))
            showAntlers = tag.getBoolean("show_antlers");
        if(tag.contains("alternate_movement"))
            alternateMovement = tag.getBoolean("alternate_movement");
    }

    public static void serverTick(Level slvl, BlockPos sbp, BlockState sbs, AnimatedDeerBE sbe){
        ++sbe.ticks;
        if(sbs.getValue(BlockStateProperties.ENABLED)){
            float f = Mth.sin(sbe.ticks / 72.0f) / 2.0f;
            sbe.headAngle = (f + 0.35f) * -1.0f;
            sbe.gearAngle += 0.02f;
        }
        else{
            if(sbe.headAngle > 0.1f){
                sbe.headAngle -= 0.1f;
            }
            sbe.gearAngle = 0.0f;
        }
        if(sbe.ticks > 32767){
            sbe.ticks = 0;
        }
    }

    public static void clientTick(Level lvl, BlockPos bp, BlockState bs, AnimatedDeerBE be){
        ++be.ticks;
        if(bs.getValue(BlockStateProperties.ENABLED)){
            float f = Mth.sin(be.ticks / 72.0f) / 2.0f;
            be.headAngle = (f + 0.35f) * -1.0f;
            be.gearAngle += 0.02f;
        }
        else{
            if(be.headAngle > 0.1f){
                be.headAngle -= 0.1f;
            }
            be.gearAngle = 0.0f;
        }
        if(be.ticks > 32767){
            be.ticks = 0;
        }
    }
}
