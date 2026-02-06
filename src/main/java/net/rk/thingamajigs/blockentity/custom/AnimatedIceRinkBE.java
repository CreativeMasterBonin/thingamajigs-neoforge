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

public class AnimatedIceRinkBE extends BlockEntity {
    BlockPos bp;
    public int ticks;
    public float yAngle = 0.0f;
    public boolean custom = false;
    public float ferrisAngle = 0;
    public float rinkAngle = 0;

    public AnimatedIceRinkBE(BlockPos pos, BlockState blockState) {
        super(TBlockEntity.ANIMATED_ICE_RINK.get(), pos, blockState);
        this.bp = pos;
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
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putFloat("y_angle",yAngle);
        tag.putBoolean("custom_settings",custom);
        tag.putFloat("ferris_angle",ferrisAngle);
        tag.putFloat("rink_angle",rinkAngle);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if(tag.contains("y_angle"))
            yAngle = tag.getFloat("y_angle");
        if(tag.contains("custom_settings"))
            custom = tag.getBoolean("custom_settings");
        if(tag.contains("ferris_angle"))
            ferrisAngle = tag.getFloat("ferris_angle");
        if(tag.contains("rink_angle"))
            rinkAngle = tag.getFloat("rink_angle");
    }

    public static void serverTick(Level slvl, BlockPos sbp, BlockState sbs, AnimatedIceRinkBE sbe){
        ++sbe.ticks;
        if(slvl.getBlockState(sbp).getValue(BlockStateProperties.ENABLED)){
            sbe.rinkAngle = Mth.lerp(sbe.ticks / 132.0f,0.0f,1.0f);
            sbe.ferrisAngle = Mth.lerp(sbe.ticks / 72.0f,0.0f,1.0f);
        }
        else{
            if(sbe.rinkAngle > 0){
                sbe.rinkAngle -= 0.01f;
            }
            else{
                sbe.rinkAngle = 0;
            }
            if(sbe.ferrisAngle > 0){
                sbe.ferrisAngle -= 0.01f;
            }
            else{
                sbe.ferrisAngle = 0;
            }
        }
        if(sbe.ticks > 32767){
            sbe.ticks = 0;
        }
    }

    public static void clientTick(Level lvl, BlockPos bp, BlockState bs, AnimatedIceRinkBE be){
        ++be.ticks;
        if(lvl.getBlockState(bp).getValue(BlockStateProperties.ENABLED)){
            be.rinkAngle = Mth.lerp(be.ticks / 132.0f,0.0f,1.0f);
            be.ferrisAngle = Mth.lerp(be.ticks / 72.0f,0.0f,1.0f);
        }
        else{
            if(be.rinkAngle > 0){
                be.rinkAngle -= 0.001f;
            }
            else{
                be.rinkAngle = 0;
            }
            if(be.ferrisAngle > 0){
                be.ferrisAngle -= 0.001f;
            }
            else{
                be.ferrisAngle = 0;
            }
        }
        if(be.ticks > 32767){
            be.ticks = 0;
        }
    }
}
