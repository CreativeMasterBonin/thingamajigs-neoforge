package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import net.rk.thingamajigs.blockentity.custom.CustomizableCopyingDecoBE;
import net.rk.thingamajigs.xtras.TCalcStuff;

import javax.annotation.Nullable;
import java.util.List;

public class CustomizableCopyingDeco extends BaseEntityBlock {
    public static final ModelProperty<BlockState> BLOCKSTATE_ID = new ModelProperty<>();
    public static final MapCodec<CustomizableCopyingDeco> CODEC = simpleCodec(CustomizableCopyingDeco::new);

    public CustomizableCopyingDeco(Properties p) {
        super(p.noOcclusion().pushReaction(PushReaction.BLOCK).instrument(NoteBlockInstrument.BIT));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public float getFriction(BlockState state, LevelReader level, BlockPos pos, @org.jetbrains.annotations.Nullable Entity entity) {
        CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
        // attempt to change the friction for this block to the copied type of block
        if(customDeco instanceof CustomizableCopyingDecoBE){
            return customDeco.blockTypeToCopy.getFriction(level,pos,entity);
        }
        return 0.6f; // just return the default if nothing else works
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        /*CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
        if(customDeco instanceof CustomizableCopyingDecoBE){
            return customDeco.blockTypeToCopy.getCollisionShape(level,pos,ctx);
        }*/
        return Shapes.block();
    }

    @Override
    public float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
        if(customDeco instanceof CustomizableCopyingDecoBE){
            return customDeco.blockTypeToCopy.getShadeBrightness(level,pos);
        }
        return 0.2f;
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return Shapes.block();
    }

    @Override
    public void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
        CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
        if(customDeco instanceof CustomizableCopyingDecoBE){
            // custom particles from the state used in the render instead of this block's actual particles
            if(!customDeco.blockTypeToCopy.isAir()){
                level.levelEvent(player,2001,pos,getId(customDeco.blockTypeToCopy));
                return;
            }
            else{
                level.levelEvent(player,2001,pos,getId(state));
                return;
            }
        }
        super.spawnDestroyParticles(level,player,pos,state);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack handStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack oppositeStack = player.getItemInHand(InteractionHand.OFF_HAND);
        if(level.isClientSide()){
            if(!handStack.isEmpty()){
                if(handStack.getItem() instanceof BlockItem blockItem){
                    boolean isNotAir = blockItem.getBlock() instanceof AirBlock;
                    // air, liquid or entity blocks are not allowed, as they may render things outside of block models (especially air and liquid, which have no model and a special renderer, respectively)
                    if(!isNotAir && !(blockItem.getBlock() instanceof LiquidBlock) && !(blockItem.getBlock() instanceof EntityBlock)){
                        player.playSound(SoundEvents.ITEM_FRAME_ADD_ITEM,0.7f, TCalcStuff.nextFloatBetweenInclusive(0.97f,1.1f));
                        return ItemInteractionResult.SUCCESS;
                    }
                }
            }
        }
        else{
            if(!handStack.isEmpty()){
                if(handStack.getItem() instanceof BlockItem blockItem){
                    boolean isNotAir = blockItem.getBlock() instanceof AirBlock;
                    // air, liquid or entity blocks are not allowed, as they may render things outside of block models (especially air and liquid, which have no model and a special renderer, respectively)
                    if(!isNotAir && !(blockItem.getBlock() instanceof LiquidBlock) && !(blockItem.getBlock() instanceof EntityBlock)){
                        CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE)level.getBlockEntity(pos);
                        if(customDeco instanceof CustomizableCopyingDecoBE){
                            if(oppositeStack.isEmpty()){
                                customDeco.blockTypeToCopy = blockItem.getBlock().defaultBlockState();
                            }
                            else{
                                if(blockItem.getBlock().getStateDefinition().getProperties().contains(BlockStateProperties.WATERLOGGED) && oppositeStack.is(Items.WATER_BUCKET)){
                                    customDeco.blockTypeToCopy = blockItem.getBlock().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED,true);
                                }
                                else{
                                    customDeco.blockTypeToCopy = blockItem.getBlock().defaultBlockState();
                                }
                            }
                            customDeco.updateBlock();
                        }
                        return ItemInteractionResult.SUCCESS;
                    }
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter level, BlockPos pos, FluidState fluidState) {
        if(level.getBlockEntity(pos) instanceof CustomizableCopyingDecoBE customDeco){
            return customDeco.blockTypeToCopy.getBlock() instanceof HalfTransparentBlock || customDeco.blockTypeToCopy.getBlock() instanceof LeavesBlock;
        }
        return false;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("block.thingamajigs.customizable_copying_deco.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CustomizableCopyingDecoBE(blockPos,blockState);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if(entity instanceof Player player){
            CustomizableCopyingDecoBE customDeco = (CustomizableCopyingDecoBE) level.getBlockEntity(pos);
            if(customDeco instanceof CustomizableCopyingDecoBE){
                float rotationToTurn = player.getDirection().getOpposite().toYRot();
                if(rotationToTurn == 180.0f){
                    rotationToTurn = 0.0f;
                }
                else if(rotationToTurn == 0.0f){
                    rotationToTurn = 180.0f;
                }
                customDeco.modelRotations = new Vec3(0D,rotationToTurn,0D);
                customDeco.updateBlock();
            }
        }
    }
}
