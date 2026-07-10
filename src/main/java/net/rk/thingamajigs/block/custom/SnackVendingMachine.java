package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.item.TItems;
import net.rk.thingamajigs.xtras.TCalcStuff;
import net.rk.thingamajigs.xtras.TConfig;

import java.util.List;

@SuppressWarnings("deprecated")
public class SnackVendingMachine extends Block implements VendingAble {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape BLOCK_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 32.0D, 16.0D);

    public SnackVendingMachine(Properties properties){
        super(properties.strength(1.25F,10F).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        return BLOCK_SHAPE;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide()){
            ItemStack itemInHand = player.getItemInHand(player.getUsedItemHand());
            if(itemInHand.is(TItems.MONEY)){
                player.playSound(SoundEvents.VAULT_INSERT_ITEM,1.0f, TCalcStuff.nextFloatBetweenInclusive(0.95f,0.98f));
                return InteractionResult.SUCCESS;
            }
            else if(itemInHand.is(TItems.DEBIT_CARD)){
                if(itemInHand.has(Thingamajigs.MONEY)){
                    if(itemInHand.get(Thingamajigs.MONEY).intValue() - this.purchaseMoneyAmount(itemInHand) >= 0){
                        player.playSound(SoundEvents.VAULT_INSERT_ITEM,1.0f, TCalcStuff.nextFloatBetweenInclusive(0.94f,0.98f));
                        return InteractionResult.SUCCESS;
                    }
                    else{
                        player.playSound(SoundEvents.VAULT_INSERT_ITEM_FAIL,1.0f,0.75f);
                    }
                }
                else{
                    player.playSound(SoundEvents.VAULT_INSERT_ITEM_FAIL,1.0f,0.97f);
                }
            }
        }
        else{
            return vendItem(player.getItemInHand(player.getUsedItemHand()),player,level) ? InteractionResult.SUCCESS : InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean vendItem(ItemStack paymentStack, Player player, Level level) {
        if(paymentStack.is(TItems.MONEY) && paymentStack.getCount() >= this.purchaseMoneyAmount(paymentStack)){
            paymentStack.shrink(this.purchaseMoneyAmount(paymentStack));
            ItemStack productToBeDispensed = randomlyCreateVendingItemStack(level.getRandom());
            ItemEntity inWorldItem = new ItemEntity(level,
                    player.getX(),player.getY(),player.getZ(),
                    productToBeDispensed,0D,0.1D,0D);
            level.addFreshEntity(inWorldItem);

            player.displayClientMessage(Component.translatable("message.thingamajigs.vending_machine.success")
                    .withStyle(ChatFormatting.ITALIC),true);
            return true;
        }
        else if(paymentStack.is(TItems.DEBIT_CARD)){
            if(!paymentStack.has(Thingamajigs.MONEY)){
                player.displayClientMessage(Component.translatable("message.thingamajigs.vending_machine.failed.card_not_ready")
                        .withStyle(ChatFormatting.ITALIC),true);
                return false;
            }
            else{
                if(paymentStack.get(Thingamajigs.MONEY).intValue() - this.purchaseMoneyAmount(paymentStack) >= 0){
                    paymentStack.set(Thingamajigs.MONEY.get(),paymentStack.get(Thingamajigs.MONEY).intValue() - this.purchaseMoneyAmount(paymentStack));
                    ItemStack productToBeDispensed = randomlyCreateVendingItemStack(level.getRandom());
                    ItemEntity inWorldItem = new ItemEntity(level,
                            player.getX(),player.getY(),player.getZ(),
                            productToBeDispensed,0D,0.1D,0D);
                    level.addFreshEntity(inWorldItem);
                    player.displayClientMessage(Component.translatable("message.thingamajigs.vending_machine.success")
                            .withStyle(ChatFormatting.ITALIC),true);
                }
                else{
                    player.displayClientMessage(Component.translatable("message.thingamajigs.vending_machine.failed.need_money")
                            .withStyle(ChatFormatting.ITALIC),true);
                    return false;
                }
                return true;
            }
        }
        else{
            player.displayClientMessage(Component.translatable("message.thingamajigs.vending_machine.failed.unacceptable_payment_method")
                    .withStyle(ChatFormatting.ITALIC),true);
        }
        return false;
    }

    public static final FoodProperties HARDY_APPLE = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(1.5f)
            .effect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 2400, 2), 0.1f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 2400, 3), 0.2f)
            .build();

    public static final FoodProperties CHOCOLATE_PIE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(1.7f)
            .effect(new MobEffectInstance(MobEffects.DIG_SPEED, 2400, 2), 0.3f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2400, 4), 0.4f)
            .build();

    @Override
    public ItemStack randomlyCreateVendingItemStack(RandomSource randomSource) {
        if(randomSource.nextBoolean()){
            return new ItemStack(List.of(Items.COOKED_BEEF,Items.COOKED_PORKCHOP,Items.PUMPKIN_PIE,Items.BAKED_POTATO,Items.CAKE,Items.BEETROOT_SOUP,Items.MUSHROOM_STEW,Items.RABBIT_STEW).get(randomSource.nextIntBetweenInclusive(0,6)),1);
        }
        else{
            // a custom pie, called chocolate pie
            ItemStack customPieChocolate = new ItemStack(Items.PUMPKIN_PIE);
            customPieChocolate.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.chocolate_pie.name"));
            customPieChocolate.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE,true);
            customPieChocolate.set(DataComponents.FOOD, CHOCOLATE_PIE);
            ItemStack customApple = new ItemStack(Items.APPLE);
            customApple.set(DataComponents.ITEM_NAME,Component.translatable("item.thingamajigs.hardy_apple.name"));
            customApple.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE,true);
            customApple.set(DataComponents.FOOD,HARDY_APPLE);


            List<ItemStack> customFoods = List.of(customPieChocolate,customApple,new ItemStack(TItems.GLOB_SANDWICH.asItem()));
            return customFoods.get(randomSource.nextIntBetweenInclusive(0,customFoods.size() - 1));
        }
    }

    @Override
    public int purchaseMoneyAmount(ItemStack itemStack) {
        if(itemStack.is(TItems.MONEY)){
            return 8;
        }
        else{
            return 4;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("generic.thingamajigs.vending_machine.desc").withStyle(ChatFormatting.GRAY));
    }
}
