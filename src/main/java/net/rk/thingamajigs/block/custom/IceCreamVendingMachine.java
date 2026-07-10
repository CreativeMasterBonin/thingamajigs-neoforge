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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.item.TItems;
import net.rk.thingamajigs.xtras.TCalcStuff;
import net.rk.thingamajigs.xtras.TProperties;

import java.util.List;
import java.util.stream.Stream;

public class IceCreamVendingMachine extends DirectionalConnectedSideBlock implements VendingAble{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 0, 0, 16, 16, 16),
            Block.box(0, 16, 0, 16, 32, 16),
            Block.box(0, 30, -7, 16, 32, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, 0, 16, 16, 16),
            Block.box(0, 16, 0, 16, 32, 16),
            Block.box(16, 30, 0, 23, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 0, 0, 16, 16, 16),
            Block.box(0, 16, 0, 16, 32, 16),
            Block.box(0, 30, 16, 16, 32, 23)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 0, 16, 16, 16),
            Block.box(0, 16, 0, 16, 32, 16),
            Block.box(-7, 30, 0, 0, 32, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public IceCreamVendingMachine(Properties properties) {
        super(properties.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_RED).noOcclusion().strength(1f,5f)
                .instrument(NoteBlockInstrument.BELL));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch(state.getValue(FACING)){
            case NORTH -> {
                return NORTH;
            }
            case SOUTH -> {
                return SOUTH;
            }
            case EAST -> {
                return EAST;
            }
            case WEST -> {
                return WEST;
            }
            default -> {
                return DoubleTallDecorationBlock.BLOCK_SHAPE;
            }
        }
    }

    public static final FoodProperties ICE_CREAM = new FoodProperties.Builder()
            .nutrition(2).saturationModifier(2.25f)
            .fast()
            .build();

    public static final FoodProperties POWDERED_ICE_CREAM = new FoodProperties.Builder()
            .nutrition(7).saturationModifier(1.25f)
            .fast()
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
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if(!level.isClientSide()){
            if(state.getValue(FACING) == Direction.NORTH){
                // both east and west
                if(level.getBlockState(pos.east()).getBlock() instanceof IceCreamVendingMachine && level.getBlockState(pos.west()).getBlock() instanceof IceCreamVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.east()).getBlock() instanceof IceCreamVendingMachine) && level.getBlockState(pos.west()).getBlock() instanceof IceCreamVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// east but not west
                else if(level.getBlockState(pos.east()).getBlock() instanceof IceCreamVendingMachine && !(level.getBlockState(pos.west()).getBlock() instanceof IceCreamVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.SOUTH){
                // both east and west
                if(level.getBlockState(pos.east()).getBlock() instanceof IceCreamVendingMachine && level.getBlockState(pos.west()).getBlock() instanceof IceCreamVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.east()).getBlock() instanceof IceCreamVendingMachine) && level.getBlockState(pos.west()).getBlock() instanceof IceCreamVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// east but not west
                else if(level.getBlockState(pos.east()).getBlock() instanceof IceCreamVendingMachine && !(level.getBlockState(pos.west()).getBlock() instanceof IceCreamVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// neither east nor west
                else {
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.EAST){
                // both east and west
                if(level.getBlockState(pos.north()).getBlock() instanceof IceCreamVendingMachine && level.getBlockState(pos.south()).getBlock() instanceof IceCreamVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.north()).getBlock() instanceof IceCreamVendingMachine) && level.getBlockState(pos.south()).getBlock() instanceof IceCreamVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// east but not west
                else if(level.getBlockState(pos.north()).getBlock() instanceof IceCreamVendingMachine && !(level.getBlockState(pos.south()).getBlock() instanceof IceCreamVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.WEST){
                // both east and west
                if(level.getBlockState(pos.north()).getBlock() instanceof IceCreamVendingMachine && level.getBlockState(pos.south()).getBlock() instanceof IceCreamVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.north()).getBlock() instanceof IceCreamVendingMachine) && level.getBlockState(pos.south()).getBlock() instanceof IceCreamVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.LEFT),3);
                }// east but not west
                else if(level.getBlockState(pos.north()).getBlock() instanceof IceCreamVendingMachine && !(level.getBlockState(pos.south()).getBlock() instanceof IceCreamVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.RIGHT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE, TProperties.ConnectedSide.UNCONNECTED),3);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("generic.thingamajigs.vending_machine.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public ItemStack randomlyCreateVendingItemStack(RandomSource randomSource) {
        ItemStack iceCream = new ItemStack(Items.WHITE_DYE);
        ItemStack iceCreamPink = new ItemStack(Items.PINK_DYE);
        ItemStack iceCreamBrown = new ItemStack(Items.BROWN_DYE);
        ItemStack iceCreamLime = new ItemStack(Items.LIME_DYE);
        ItemStack iceCreamOrange = new ItemStack(Items.ORANGE_DYE);

        iceCream.set(DataComponents.ITEM_NAME, Component.translatable("item.thingamajigs.ice_cream.name"));
        iceCream.set(DataComponents.FOOD,ICE_CREAM);

        if(randomSource.nextBoolean()){
            int randomIceCreamType = randomSource.nextIntBetweenInclusive(0,4);
            if(randomIceCreamType <= 0){
                return iceCream;
            }
            else if(randomIceCreamType == 1){
                iceCreamPink.set(DataComponents.ITEM_NAME, Component.translatable("item.thingamajigs.ice_cream.name"));
                iceCreamPink.set(DataComponents.FOOD,ICE_CREAM);
                return iceCreamPink;
            }
            else if(randomIceCreamType == 2){
                iceCreamLime.set(DataComponents.ITEM_NAME, Component.translatable("item.thingamajigs.ice_cream.name"));
                iceCreamLime.set(DataComponents.FOOD,ICE_CREAM);
                return iceCreamLime;
            }
            else if(randomIceCreamType == 3){
                iceCreamOrange.set(DataComponents.ITEM_NAME, Component.translatable("item.thingamajigs.ice_cream.name"));
                iceCreamOrange.set(DataComponents.FOOD,ICE_CREAM);
                return iceCreamOrange;
            }
            else if(randomIceCreamType == 4){
                iceCreamBrown.set(DataComponents.ITEM_NAME, Component.translatable("item.thingamajigs.ice_cream.name"));
                iceCreamBrown.set(DataComponents.FOOD,ICE_CREAM);
                return iceCreamBrown;
            }
            else{
                return iceCream;
            }
        }
        return iceCream;
    }

    @Override
    public int purchaseMoneyAmount(ItemStack itemStack) {
        if(itemStack.is(TItems.MONEY)){
            return 12;
        }
        else{
            return 10;
        }
    }
}
