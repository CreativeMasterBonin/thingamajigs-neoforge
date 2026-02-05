package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.blockentity.custom.UmbrellaBE;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class UmbrellaBlock extends ThingamajigsDecorativeBlock implements EntityBlock{
    public static final VoxelShape ALL = Stream.of(
            Block.box(7, 0, 7, 9, 32, 9),
            Block.box(6, 24, 6, 10, 32, 10)
            //Block.box(-8, 30, -8, 24, 32, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape XTRA = Stream.of(
            Block.box(7, 0, 7, 9, 32, 9),
            Block.box(6, 16, 6, 10, 32, 10),
            Block.box(0,0,0,16,16,16)
    ).reduce((v1,v2) -> Shapes.join(v1,v2,BooleanOp.OR)).get();

    public UmbrellaBlock(Properties p) {
        super(p.strength(0.25f,1f).sound(SoundType.BAMBOO_WOOD).mapColor(MapColor.WOOL));
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
        list.add(Component.translatable("block.thingamajigs.umbrella.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public RenderShape getRenderShape(BlockState bs) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return XTRA;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_60572_, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_) {
        return ALL;
    }

    @Override
    public float getShadeBrightness(BlockState p_60472_, BlockGetter p_60473_, BlockPos p_60474_) {
        return 0.5f;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState bs, BlockGetter bg, BlockPos bp) {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos bp, BlockState bs) {
        return new UmbrellaBE(bp,bs);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if(lvl.isClientSide){
            return InteractionResult.SUCCESS;
        }
        else{
            ItemStack itemStack = ply.getItemInHand(ply.getUsedItemHand());
            Item item = itemStack.getItem();
            UmbrellaBE be = (UmbrellaBE)lvl.getBlockEntity(bp);
            if(!(be instanceof UmbrellaBE)){
                return InteractionResult.PASS;
            }
            if(!ply.getItemInHand(ply.getUsedItemHand()).isEmpty()){
                if(item instanceof DyeItem){
                    switch(((DyeItem) item).getDyeColor().getId()){
                        case 0 -> {
                            be.setColor(0);
                            lvl.playSound(null,bp, SoundEvents.DYE_USE, SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 1 -> {
                            be.setColor(1);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 2 -> {
                            be.setColor(2);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 3 -> {
                            be.setColor(3);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 4 -> {
                            be.setColor(4);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 5 -> {
                            be.setColor(5);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 6 -> {
                            be.setColor(6);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 7 -> {
                            be.setColor(7);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 8 -> {
                            be.setColor(8);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 9 -> {
                            be.setColor(9);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 10 -> {
                            be.setColor(10);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 11 -> {
                            be.setColor(11);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 12 -> {
                            be.setColor(12);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 13 -> {
                            be.setColor(13);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 14 -> {
                            be.setColor(14);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        case 15 -> {
                            be.setColor(15);
                            lvl.playSound(null,bp,SoundEvents.DYE_USE,SoundSource.BLOCKS,1.0f,1.0f);
                        }
                        default -> {
                            lvl.playSound(ply,bp, SoundEvents.VILLAGER_NO, SoundSource.BLOCKS,1.0f,1.0f);
                            return InteractionResult.CONSUME;
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.PASS;
        }
    }
}
