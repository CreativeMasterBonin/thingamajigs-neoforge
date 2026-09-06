package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Modes {
    /*public static final Mode ROTATE_X = new Mode("rotate_x") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE) level.getBlockEntity(pos);
            if (customDeco instanceof CustomizableCopyingDecoBE) {
                double oldValue = customDeco.modelRotations.x;
                if (player.isShiftKeyDown()) {
                    customDeco.modelRotations = new Vec3(customDeco.modelRotations.x - 1f, customDeco.modelRotations.y, customDeco.modelRotations.z);
                } else {
                    customDeco.modelRotations = new Vec3(customDeco.modelRotations.x + 1f, customDeco.modelRotations.y, customDeco.modelRotations.z);
                }
                customDeco.updateBlock();
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message", "rot X", oldValue, customDeco.modelRotations.x)
                        .withStyle(ChatFormatting.BLUE), true);
            }
        }
    };

    public static final Mode ROTATE_Y = new Mode("rotate_y") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                double oldValue = customDeco.modelRotations.y;
                if(player.isShiftKeyDown()){
                    customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y - 1f,customDeco.modelRotations.z);
                }
                else{
                    customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y + 1f,customDeco.modelRotations.z);
                }
                customDeco.updateBlock();
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","rot Y",oldValue,customDeco.modelRotations.y)
                        .withStyle(ChatFormatting.BLUE),true);
            }
        }
    };

    public static final Mode ROTATE_Z = new Mode("rotate_z") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                double oldValue = customDeco.modelRotations.z;
                if(player.isShiftKeyDown()){
                    customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y,customDeco.modelRotations.z - 1f);
                    customDeco.updateBlock();
                }
                else{
                    customDeco.modelRotations = new Vec3(customDeco.modelRotations.x,customDeco.modelRotations.y,customDeco.modelRotations.z + 1f);
                    customDeco.updateBlock();
                }
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","rot Z",oldValue,customDeco.modelRotations.z)
                        .withStyle(ChatFormatting.BLUE),true);
            }
        }
    };

    public static final Mode OFFSET_X = new Mode("offset_x") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                double oldValue = customDeco.modelOffsets.x;
                if(player.isShiftKeyDown()){
                    customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x - 0.5f,customDeco.modelOffsets.y,customDeco.modelOffsets.z);
                    customDeco.updateBlock();
                }
                else{
                    customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x + 0.5f,customDeco.modelOffsets.y,customDeco.modelOffsets.z);
                    customDeco.updateBlock();
                }
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","offset X",oldValue,customDeco.modelOffsets.x)
                        .withStyle(ChatFormatting.GREEN),true);
            }
        }
    };

    public static final Mode OFFSET_Y = new Mode("offset_y") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                double oldValue = customDeco.modelOffsets.y;
                if(player.isShiftKeyDown()){
                    customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x,customDeco.modelOffsets.y - 0.5f,customDeco.modelOffsets.z);
                    customDeco.updateBlock();
                }
                else{
                    customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x,customDeco.modelOffsets.y + 0.5f,customDeco.modelOffsets.z);
                    customDeco.updateBlock();
                }
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","offset Y",oldValue,customDeco.modelOffsets.y)
                        .withStyle(ChatFormatting.GREEN),true);
            }
        }
    };

    public static final Mode OFFSET_Z = new Mode("offset_z") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                double oldValue = customDeco.modelOffsets.z;
                if(player.isShiftKeyDown()){
                    customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x,customDeco.modelOffsets.y,customDeco.modelOffsets.z - 0.5f);
                    customDeco.updateBlock();
                }
                else{
                    customDeco.modelOffsets = new Vec3(customDeco.modelOffsets.x,customDeco.modelOffsets.y,customDeco.modelOffsets.z + 0.5f);
                    customDeco.updateBlock();
                }
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","offset Z",oldValue,customDeco.modelOffsets.z)
                        .withStyle(ChatFormatting.GREEN),true);
            }
        }
    };

    public static final Mode SCALE_X = new Mode("scale_x") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                double oldValue = customDeco.modelScale.x;
                if(player.isShiftKeyDown()){
                    customDeco.modelScale = new Vec3(customDeco.modelScale.x - 0.5f,customDeco.modelScale.y,customDeco.modelScale.z);
                    customDeco.updateBlock();
                }
                else{
                    customDeco.modelScale = new Vec3(customDeco.modelScale.x + 0.5f,customDeco.modelScale.y,customDeco.modelScale.z);
                    customDeco.updateBlock();
                }
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","scale X",oldValue,customDeco.modelScale.x)
                        .withStyle(ChatFormatting.YELLOW),true);
            }
        }
    };

    public static final Mode SCALE_Y = new Mode("scale_y") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                double oldValue = customDeco.modelScale.y;
                if(player.isShiftKeyDown()){
                    customDeco.modelScale = new Vec3(customDeco.modelScale.x,customDeco.modelScale.y - 0.5f,customDeco.modelScale.z);
                    customDeco.updateBlock();
                }
                else{
                    customDeco.modelScale = new Vec3(customDeco.modelScale.x,customDeco.modelScale.y + 0.5f,customDeco.modelScale.z);
                    customDeco.updateBlock();
                }
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","scale Y",oldValue,customDeco.modelScale.y)
                        .withStyle(ChatFormatting.YELLOW),true);
            }
        }
    };

    public static final Mode SCALE_Z = new Mode("scale_z") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                double oldValue = customDeco.modelScale.z;
                if(player.isShiftKeyDown()){
                    customDeco.modelScale = new Vec3(customDeco.modelScale.x,customDeco.modelScale.y,customDeco.modelScale.z - 0.5f);
                    customDeco.updateBlock();
                }
                else{
                    customDeco.modelScale = new Vec3(customDeco.modelScale.x,customDeco.modelScale.y,customDeco.modelScale.z + 0.5f);
                    customDeco.updateBlock();
                }
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","scale Z",oldValue,customDeco.modelScale.z)
                        .withStyle(ChatFormatting.YELLOW),true);
            }
        }
    };

    public static final Mode SOLIDIFY = new Mode("solidify") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                String oldValue = customDeco.renderingMode;
                customDeco.renderingMode = "solid";
                customDeco.updateBlock();
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","render mode",oldValue,customDeco.renderingMode)
                        .withStyle(ChatFormatting.WHITE),true);
            }
        }
    };
    public static final Mode CUTOUT = new Mode("cutout") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                String oldValue = customDeco.renderingMode;
                customDeco.renderingMode = "cutout";
                customDeco.updateBlock();
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","render mode",oldValue,customDeco.renderingMode)
                        .withStyle(ChatFormatting.WHITE),true);
            }
        }
    };
    public static final Mode TRANSLUCENT = new Mode("translucent") {
        @Override
        public void performModeTask(ItemStack stack, Level level, BlockPos pos, Player player) {
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                String oldValue = customDeco.renderingMode;
                customDeco.renderingMode = "translucent";
                customDeco.updateBlock();
                player.displayClientMessage(Component.translatable("mode_change.custom_deco.message","render mode",oldValue,customDeco.renderingMode)
                        .withStyle(ChatFormatting.WHITE),true);
            }
        }
    };*/
}