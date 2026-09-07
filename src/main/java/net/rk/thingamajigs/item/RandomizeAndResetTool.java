package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.blockentity.custom.CustomizableCopyingDecoBE;
import net.rk.thingamajigs.xtras.TCalcStuff;

import java.util.List;

public class RandomizeAndResetTool extends Item {
    public RandomizeAndResetTool(Properties properties) {
        super(properties.fireResistant().setNoRepair().stacksTo(1));
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(TItems.RANDOMIZE_AND_RESET_TOOL.asItem());
        stack.set(Thingamajigs.RESET_PARAMETERS_ON_USE,false);
        return stack;
    }

    @Override
    public void onCraftedPostProcess(ItemStack stack, Level level) {
        stack.set(Thingamajigs.RESET_PARAMETERS_ON_USE,false);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.thingamajigs.randomize_and_reset_tool.desc")
                .withStyle(ChatFormatting.GRAY));
        if(stack.has(Thingamajigs.RESET_PARAMETERS_ON_USE)) {
            tooltipComponents.add(Component.translatable("item.thingamajigs.randomize_and_reset_tool.rotate_when_resetting",
                            stack.get(Thingamajigs.RESET_PARAMETERS_ON_USE).booleanValue())
                    .withStyle(ChatFormatting.GREEN));
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if(action == ClickAction.SECONDARY){
            if(stack.has(Thingamajigs.RESET_PARAMETERS_ON_USE)){
                stack.set(Thingamajigs.RESET_PARAMETERS_ON_USE,!stack.get(Thingamajigs.RESET_PARAMETERS_ON_USE).booleanValue());
                player.playSound(SoundEvents.IRON_GOLEM_REPAIR,0.45f, TCalcStuff.nextFloatBetweenInclusive(0.93f,1.0f));
                return true;
            }
            else{
                stack.set(Thingamajigs.RESET_PARAMETERS_ON_USE,false);
                return true;
            }
        }
        return false;
    }


    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        Player player = ctx.getPlayer();
        BlockPos clickedPos = ctx.getClickedPos();
        ItemStack handStack = ctx.getItemInHand();
        if(level.getBlockState(clickedPos).isAir()){
            return InteractionResult.PASS;
        }

        if(level.isClientSide()){
            if(player.isSecondaryUseActive()){
                CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(clickedPos);
                if(customDeco instanceof CustomizableCopyingDecoBE){
                    boolean allOffsetZero = customDeco.modelOffsets.x == 0.0D && customDeco.modelOffsets.y == 0.0D && customDeco.modelOffsets.z == 0.0D;
                    boolean allRotationZero = customDeco.modelRotations.x == 0.0D && customDeco.modelRotations.y == 0.0D && customDeco.modelRotations.z == 0.0D;
                    boolean allScaleZero = customDeco.modelScale.x == 0.0D && customDeco.modelScale.y == 0.0D && customDeco.modelScale.z == 0.0D;

                    if(handStack.has(Thingamajigs.RESET_PARAMETERS_ON_USE)) {
                        if (handStack.get(Thingamajigs.RESET_PARAMETERS_ON_USE).booleanValue()) {
                            if(!allOffsetZero && !allRotationZero && !allScaleZero){
                                player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL,0.4f,TCalcStuff.nextFloatBetweenInclusive(0.97f,1.1f));
                                return InteractionResult.SUCCESS;
                            }
                        }
                    }
                }
            }
            else{
                player.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM,0.4f,TCalcStuff.nextFloatBetweenInclusive(0.97f,1.1f));
                return InteractionResult.SUCCESS;
            }
        }
        else{
            if(player.isSecondaryUseActive()){
                CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(clickedPos);
                if(customDeco instanceof CustomizableCopyingDecoBE){
                    boolean allOffsetZero = customDeco.modelOffsets.x == 0.0D && customDeco.modelOffsets.y == 0.0D && customDeco.modelOffsets.z == 0.0D;
                    boolean allRotationZero = customDeco.modelRotations.x == 0.0D && customDeco.modelRotations.y == 0.0D && customDeco.modelRotations.z == 0.0D;
                    boolean allScaleZero = customDeco.modelScale.x == 0.0D && customDeco.modelScale.y == 0.0D && customDeco.modelScale.z == 0.0D;

                    if(handStack.has(Thingamajigs.RESET_PARAMETERS_ON_USE)){
                        if(handStack.get(Thingamajigs.RESET_PARAMETERS_ON_USE).booleanValue()){
                            if(!allOffsetZero && !allRotationZero && !allScaleZero){
                                customDeco.modelOffsets = new Vec3(0D,0D,0D);
                                customDeco.modelScale = new Vec3(1D,1D,1D); // must be 1 for all or the model won't be visible
                                customDeco.modelRotations = new Vec3(0D,0D,0D);

                                float rotationToTurn = player.getDirection().getOpposite().toYRot();
                                if(rotationToTurn == 180.0f){
                                    rotationToTurn = 0.0f;
                                }
                                else if(rotationToTurn == 0.0f){
                                    rotationToTurn = 180.0f;
                                }
                                customDeco.modelRotations = new Vec3(0D,rotationToTurn,0D);

                                customDeco.updateBlock();

                                if(level instanceof ServerLevel serverLevel){
                                    serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,clickedPos.getX() + 1.5D,clickedPos.getY() + 1.5,clickedPos.getZ() + 1.5D,
                                            10,
                                            0D,0D,0D,2);
                                }
                                return InteractionResult.SUCCESS;
                            }
                        }
                    }
                }
            }
            else{
                CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(clickedPos);
                if(customDeco instanceof CustomizableCopyingDecoBE){
                    customDeco.modelOffsets = new Vec3(TCalcStuff.nextDoubleBetweenInclusive(-0.25D,0.25D) - 0.5D,0D,
                            TCalcStuff.nextDoubleBetweenInclusive(0.25D,0.25D) - 0.5D);
                    customDeco.updateBlock();
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }
}
