package net.rk.thingamajigs.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.rk.thingamajigs.block.custom.CustomizableCopyingDeco;
import net.rk.thingamajigs.blockentity.TBlockEntity;

import static net.minecraft.world.level.block.Block.UPDATE_CLIENTS;
import static net.minecraft.world.level.block.Block.UPDATE_NEIGHBORS;

public class CustomizableCopyingDecoBE extends BlockEntity {
    public BlockState blockTypeToCopy = Blocks.AIR.defaultBlockState();
    public Vec3 modelRotations = new Vec3(0D, 0D, 0D);
    public Vec3 modelOffsets = new Vec3(0D, 0D, 0D);
    public Vec3 modelScale = new Vec3(1.0D, 1.0D, 1.0D);
    public String renderingMode = "solid";

    enum ModelRenderingMode implements StringRepresentable {
        SOLID("solid"),
        CUTOUT("cutout"),
        TRANSLUCENT("translucent");

        private String name;

        ModelRenderingMode(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public CustomizableCopyingDecoBE(BlockPos pos, BlockState state) {
        super(TBlockEntity.CUSTOMIZABLE_COPYING_DECO_BE.get(), pos, state);
    }

    public void updateBlock() {
        this.setChanged();
        if (level != null) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), UPDATE_CLIENTS + UPDATE_NEIGHBORS);
        }
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        this.loadAdditional(tag, lookupProvider);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag ct = new CompoundTag();
        if (blockTypeToCopy != null) {
            CompoundTag blockStateTag = NbtUtils.writeBlockState(blockTypeToCopy);
            ct.put("blockState", blockStateTag);
        }
        this.saveAdditional(ct,registries);
        return ct;
    }

    @Override
    public ModelData getModelData() {
        return ModelData.builder().with(CustomizableCopyingDeco.BLOCKSTATE_ID, blockTypeToCopy).build();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
        if (level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        // standard read blockstate
        if (tag.contains("blockState")) {
            HolderGetter<Block> holderGetter = this.level != null ? this.level.holderLookup(Registries.BLOCK) : BuiltInRegistries.BLOCK.asLookup();
            blockTypeToCopy = NbtUtils.readBlockState(holderGetter, tag.getCompound("blockState"));
        } else {
            blockTypeToCopy = Blocks.AIR.defaultBlockState();
        }

        if (tag.contains("rendering_mode")) {
            renderingMode = tag.getString("rendering_mode");
        }

        // all model customs
        if (tag.contains("model_scale_x") && tag.contains("model_scale_y") && tag.contains("model_scale_z")) {
            modelScale = new Vec3(tag.getDouble("model_scale_x"),
                    tag.getDouble("model_scale_y"),
                    tag.getDouble("model_scale_z"));
        }

        if (tag.contains("model_rotation_x") && tag.contains("model_rotation_y") && tag.contains("model_rotation_z")) {
            modelRotations = new Vec3(tag.getDouble("model_rotation_x"),
                    tag.getDouble("model_rotation_y"),
                    tag.getDouble("model_rotation_z"));
        }

        if (tag.contains("model_offset_x") && tag.contains("model_offset_y") && tag.contains("model_offset_z")) {
            modelOffsets = new Vec3(tag.getDouble("model_offset_x"),
                    tag.getDouble("model_offset_y"),
                    tag.getDouble("model_offset_z"));
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if (blockTypeToCopy != null) {
            CompoundTag blockStateTag = NbtUtils.writeBlockState(blockTypeToCopy);
            tag.put("blockState", blockStateTag); // standard write blockstate
        }
        tag.putString("rendering_mode", renderingMode);

        // save all model customs
        tag.putDouble("model_scale_x", modelScale.x);
        tag.putDouble("model_scale_y", modelScale.y);
        tag.putDouble("model_scale_z", modelScale.z);

        tag.putDouble("model_rotation_x", modelRotations.x);
        tag.putDouble("model_rotation_y", modelRotations.y);
        tag.putDouble("model_rotation_z", modelRotations.z);

        tag.putDouble("model_offset_x", modelOffsets.x);
        tag.putDouble("model_offset_y", modelOffsets.y);
        tag.putDouble("model_offset_z", modelOffsets.z);
    }
}
