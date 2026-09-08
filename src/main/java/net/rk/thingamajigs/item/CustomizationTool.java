package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.xtras.TCalcStuff;

import java.util.List;

public class CustomizationTool extends Item {
    public int mode = 0;
    public int renderMode = 0;

    public Mode grabModeFromID(int id){
        switch (id){
            case 0: return Modes.ROTATE_X;
            case 1: return Modes.ROTATE_Y;
            case 2: return Modes.ROTATE_Z;
            case 3: return Modes.OFFSET_X;
            case 4: return Modes.OFFSET_Y;
            case 5: return Modes.OFFSET_Z;
            case 6: return Modes.SCALE_X;
            case 7: return Modes.SCALE_Y;
            case 8: return Modes.SCALE_Z;
            case 9: return Modes.SCALE_Z;
            default: throw new IllegalArgumentException("Invalid id: " + id + " expected range 0-8!");
        }
    }

    public Mode grabRenderModeFromID(int id){
        switch (id){
            case 0: return Modes.SOLIDIFY;
            case 1: return Modes.CUTOUT;
            case 2: return Modes.TRANSLUCENT;
            case 3: return Modes.TRANSLUCENT;
            case 4: return Modes.TRANSLUCENT;
            default: throw new IllegalArgumentException("Invalid id: " + id + " expected range 0-2!");
        }
    }

    public CustomizationTool(Properties properties) {
        super(properties.setNoRepair().fireResistant().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(stack.is(TItems.CUSTOMIZATION_TOOL.asItem())){
            tooltipComponents.add(Component.translatable("item.thingamajigs.customization_tool.desc").withStyle(ChatFormatting.GRAY));
            if(stack.has(Thingamajigs.MODE)){
                tooltipComponents.add(Component.translatable("item.thingamajigs.customization_tool.mode",
                                grabModeFromID(stack.get(Thingamajigs.MODE).intValue()).name)
                        .withStyle(ChatFormatting.GREEN));
            }
        }
        else if(stack.is(TItems.RENDERING_TOOL.asItem())){
            tooltipComponents.add(Component.translatable("item.thingamajigs.rendering_tool.desc").withStyle(ChatFormatting.GRAY));
            if(stack.has(Thingamajigs.RENDER_MODE)){
                tooltipComponents.add(Component.translatable("item.thingamajigs.rendering_tool.mode",
                                grabRenderModeFromID(stack.get(Thingamajigs.RENDER_MODE).intValue()).name)
                        .withStyle(ChatFormatting.GREEN));
            }
        }
    }

    public void onCraftedPostProcessRenderingTool(ItemStack stack, Level level) {
        stack.set(Thingamajigs.MODE,0);
    }

    public ItemStack getDefaultInstanceRenderingTool() {
        ItemStack stack = new ItemStack(TItems.CUSTOMIZATION_TOOL.asItem());
        stack.set(Thingamajigs.MODE,0);
        return stack;
    }

    @Override
    public void onCraftedPostProcess(ItemStack stack, Level level) {
        stack.set(Thingamajigs.MODE,0);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = new ItemStack(TItems.CUSTOMIZATION_TOOL.asItem());
        stack.set(Thingamajigs.MODE,0);
        return stack;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        InteractionHand hand = context.getHand();
        ItemStack stack = context.getItemInHand();
        if(level.isClientSide()){
            if(stack.has(Thingamajigs.MODE)) {
                if(level.getRandom().nextBoolean()){
                    player.playSound(SoundEvents.SPYGLASS_USE,0.5f,1.0f);
                }
                else{
                    player.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM,0.5f,1.0f);
                }
                return InteractionResult.SUCCESS;
            }
        }
        else{
            if(stack.has(Thingamajigs.MODE)){
                grabModeFromID(stack.get(Thingamajigs.MODE).intValue()).performModeTask(stack,level,pos,player);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if(action == ClickAction.SECONDARY){
            if(stack.has(Thingamajigs.MODE)){
                if(stack.get(Thingamajigs.MODE).intValue() >= 8){
                    stack.set(Thingamajigs.MODE,0);
                    return true;
                }
                else{
                    stack.set(Thingamajigs.MODE,(stack.get(Thingamajigs.MODE).intValue() + 1));
                    player.playSound(SoundEvents.IRON_GOLEM_REPAIR,0.45f, TCalcStuff.nextFloatBetweenInclusive(0.92f,1.1f));
                    return true;
                }
            }
            else{
                stack.set(Thingamajigs.MODE,0);
                return true;
            }
        }
        return false;
    }


    public InteractionResult useOnRenderMode(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        InteractionHand hand = context.getHand();
        ItemStack stack = context.getItemInHand();
        if(level.isClientSide()){
            if(stack.has(Thingamajigs.RENDER_MODE)) {
                if(level.getRandom().nextBoolean()){
                    player.playSound(SoundEvents.SPYGLASS_USE,0.5f,1.0f);
                }
                else{
                    player.playSound(SoundEvents.ITEM_FRAME_ROTATE_ITEM,0.5f,1.0f);
                }
                return InteractionResult.SUCCESS;
            }
        }
        else{
            if(stack.has(Thingamajigs.RENDER_MODE)){
                grabRenderModeFromID(stack.get(Thingamajigs.RENDER_MODE).intValue()).performModeTask(stack,level,pos,player);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    public boolean overrideOtherStackedOnMeRenderMode(ItemStack stack, ItemStack other, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if(action == ClickAction.SECONDARY){
            if(stack.has(Thingamajigs.RENDER_MODE)){
                if(stack.get(Thingamajigs.RENDER_MODE).intValue() >= 2){
                    stack.set(Thingamajigs.RENDER_MODE,0);
                    return true;
                }
                else{
                    stack.set(Thingamajigs.RENDER_MODE,(stack.get(Thingamajigs.RENDER_MODE).intValue() + 1));
                    player.playSound(SoundEvents.IRON_GOLEM_REPAIR,0.45f, TCalcStuff.nextFloatBetweenInclusive(0.93f,1.0f));
                    return true;
                }
            }
            else{
                stack.set(Thingamajigs.RENDER_MODE,0);
                return true;
            }
        }
        return false;
    }
}
