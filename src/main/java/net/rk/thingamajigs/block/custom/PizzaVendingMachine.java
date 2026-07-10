package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.item.TItems;
import net.rk.thingamajigs.xtras.TCalcStuff;
import net.rk.thingamajigs.xtras.TProperties;

import java.util.List;

public class PizzaVendingMachine extends DirectionalConnectedSideBlock implements VendingAble {
    public PizzaVendingMachine(Properties properties) {
        super(properties.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_RED).noOcclusion().strength(1f,5f)
                .instrument(NoteBlockInstrument.DIDGERIDOO));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED)
                .setValue(WATERLOGGED,false)
        );
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if(!level.isClientSide()){
            if(state.getValue(FACING) == Direction.NORTH){
                // both east and west
                if(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine && level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine) && level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// east but not west
                else if(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine && !(level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.SOUTH){
                // both east and west
                if(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine && level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine) && level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// east but not west
                else if(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine && !(level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// neither east nor west
                else {
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.EAST){
                // both east and west
                if(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine && level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine) && level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// east but not west
                else if(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine && !(level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.WEST){
                // both east and west
                if(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine && level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine) && level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// east but not west
                else if(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine && !(level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return DoubleTallDecorationBlock.BLOCK_SHAPE;
    }

    public static final FoodProperties HOT_PIZZA = new FoodProperties.Builder()
            .nutrition(4).saturationModifier(0.95f)
            .build();

    public static final FoodProperties COLD_PIZZA = new FoodProperties.Builder()
            .nutrition(3).saturationModifier(0.5f)
            .build();

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide()){
            ItemStack itemInHand = player.getItemInHand(player.getUsedItemHand());
            if(itemInHand.is(TItems.MONEY)){
                player.playSound(SoundEvents.VAULT_INSERT_ITEM,1.0f, TCalcStuff.nextFloatBetweenInclusive(0.92f,0.96f));
                return InteractionResult.SUCCESS;
            }
            else if(itemInHand.is(TItems.DEBIT_CARD)){
                if(itemInHand.has(Thingamajigs.MONEY)){
                    if(itemInHand.get(Thingamajigs.MONEY).intValue() - this.purchaseMoneyAmount(itemInHand) >= 0){
                        player.playSound(SoundEvents.VAULT_INSERT_ITEM,1.0f, TCalcStuff.nextFloatBetweenInclusive(0.92f,0.98f));
                        return InteractionResult.SUCCESS;
                    }
                    else{
                        player.playSound(SoundEvents.VAULT_INSERT_ITEM_FAIL,1.0f,0.74f);
                    }
                }
                else{
                    player.playSound(SoundEvents.VAULT_INSERT_ITEM_FAIL,1.0f,0.96f);
                }
            }
        }
        else{
            return vendItem(player.getItemInHand(player.getUsedItemHand()),player,level) ? InteractionResult.SUCCESS : InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("generic.thingamajigs.vending_machine.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public ItemStack randomlyCreateVendingItemStack(RandomSource randomSource) {
        ItemStack pizza = new ItemStack(TBlocks.PIZZA.asItem());
        if(randomSource.nextBoolean()){
            pizza.set(DataComponents.ITEM_NAME, Component.translatable("item.thingamajigs.hot_pizza.name"));
            pizza.set(DataComponents.FOOD,HOT_PIZZA);
        }
        else{
            pizza.set(DataComponents.ITEM_NAME, Component.translatable("item.thingamajigs.cold_pizza.name"));
            pizza.set(DataComponents.FOOD,COLD_PIZZA);
        }
        return pizza;
    }

    @Override
    public int purchaseMoneyAmount(ItemStack itemStack) {
        if(itemStack.is(TItems.MONEY)){
            return 9;
        }
        else{
            return 7;
        }
    }
}
