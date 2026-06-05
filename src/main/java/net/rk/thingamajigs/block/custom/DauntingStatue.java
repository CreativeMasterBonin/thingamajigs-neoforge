package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.windcharge.WindCharge;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.xtras.TCalcStuff;
import net.rk.thingamajigs.xtras.TConfig;
import net.rk.thingamajigs.xtras.TWeatheringCopperOther;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class DauntingStatue extends Podium implements TWeatheringCopperOther {
    private final TWeatheringCopperOther.State ruststate;

    public DauntingStatue(TWeatheringCopperOther.State rs,Properties properties) {
        super(properties.randomTicks().sound(SoundType.COPPER)
                .requiresCorrectToolForDrops()
                .strength(3.0f,6.0f));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED,false));
        this.ruststate = rs;
    }

    @Override
    public void randomTick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        this.changeOverTime(bs,sl,bp,rs);
    }

    public boolean isRandomlyTicking(BlockState bsr1) {
        return TWeatheringCopperOther.getNext(bsr1.getBlock()).isPresent();
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {list.add(Component.translatable("statue.thingamajigs.author.cmb").withStyle(ChatFormatting.BLUE));}

    public static final VoxelShape NORTH = Stream.of(
            Block.box(3, 0, 3, 13, 2, 13),
            Block.box(4, 2, 4, 12, 12, 12),
            Block.box(3, 12, 3, 13, 14, 13),
            Block.box(6, 20, 6, 10, 24, 10),
            Block.box(4, 16, 7, 12, 18, 9),
            Block.box(4, 26, 7, 12, 28, 9),
            Block.box(12, 18, 7, 14, 26, 9),
            Block.box(2, 18, 7, 4, 26, 9),
            Block.box(7, 21, 12, 9, 23, 14),
            Block.box(7.5, 21.5, 3, 8.5, 22.5, 4),
            Block.box(5, 21, 2, 6, 23, 6),
            Block.box(10, 21, 2, 11, 23, 6),
            Block.box(7, 24, 2, 9, 25, 6),
            Block.box(7, 19, 2, 9, 20, 6),
            Block.box(7, 24, 10, 9, 25, 14),
            Block.box(7, 19, 10, 9, 20, 14),
            Block.box(10, 21, 10, 11, 23, 14),
            Block.box(5, 21, 10, 6, 23, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(3, 0, 3, 13, 2, 13),
            Block.box(4, 2, 4, 12, 12, 12),
            Block.box(3, 12, 3, 13, 14, 13),
            Block.box(6, 20, 6, 10, 24, 10),
            Block.box(7, 16, 4, 9, 18, 12),
            Block.box(7, 26, 4, 9, 28, 12),
            Block.box(7, 18, 12, 9, 26, 14),
            Block.box(7, 18, 2, 9, 26, 4),
            Block.box(2, 21, 7, 4, 23, 9),
            Block.box(12, 21.5, 7.5, 13, 22.5, 8.5),
            Block.box(10, 21, 5, 14, 23, 6),
            Block.box(10, 21, 10, 14, 23, 11),
            Block.box(10, 24, 7, 14, 25, 9),
            Block.box(10, 19, 7, 14, 20, 9),
            Block.box(2, 24, 7, 6, 25, 9),
            Block.box(2, 19, 7, 6, 20, 9),
            Block.box(2, 21, 10, 6, 23, 11),
            Block.box(2, 21, 5, 6, 23, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(3, 0, 3, 13, 2, 13),
            Block.box(4, 2, 4, 12, 12, 12),
            Block.box(3, 12, 3, 13, 14, 13),
            Block.box(6, 20, 6, 10, 24, 10),
            Block.box(4, 16, 7, 12, 18, 9),
            Block.box(4, 26, 7, 12, 28, 9),
            Block.box(2, 18, 7, 4, 26, 9),
            Block.box(12, 18, 7, 14, 26, 9),
            Block.box(7, 21, 2, 9, 23, 4),
            Block.box(7.5, 21.5, 12, 8.5, 22.5, 13),
            Block.box(10, 21, 10, 11, 23, 14),
            Block.box(5, 21, 10, 6, 23, 14),
            Block.box(7, 24, 10, 9, 25, 14),
            Block.box(7, 19, 10, 9, 20, 14),
            Block.box(7, 24, 2, 9, 25, 6),
            Block.box(7, 19, 2, 9, 20, 6),
            Block.box(5, 21, 2, 6, 23, 6),
            Block.box(10, 21, 2, 11, 23, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(3, 0, 3, 13, 2, 13),
            Block.box(4, 2, 4, 12, 12, 12),
            Block.box(3, 12, 3, 13, 14, 13),
            Block.box(6, 20, 6, 10, 24, 10),
            Block.box(7, 16, 4, 9, 18, 12),
            Block.box(7, 26, 4, 9, 28, 12),
            Block.box(7, 18, 2, 9, 26, 4),
            Block.box(7, 18, 12, 9, 26, 14),
            Block.box(12, 21, 7, 14, 23, 9),
            Block.box(3, 21.5, 7.5, 4, 22.5, 8.5),
            Block.box(2, 21, 10, 6, 23, 11),
            Block.box(2, 21, 5, 6, 23, 6),
            Block.box(2, 24, 7, 6, 25, 9),
            Block.box(2, 19, 7, 6, 20, 9),
            Block.box(10, 24, 7, 14, 25, 9),
            Block.box(10, 19, 7, 14, 20, 9),
            Block.box(10, 21, 5, 14, 23, 6),
            Block.box(10, 21, 10, 14, 23, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cc) {
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
            default -> {return Shapes.block();}
        }
    }

    @Override
    public void onExplosionHit(BlockState state, Level level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
        if(explosion.canTriggerBlocks() && level.getBlockState(pos.below()).is(Blocks.NETHERITE_BLOCK) && TConfig.windChargeDauntingStatue.getAsBoolean()){
            WindCharge windCharge = new WindCharge(null,level,pos.getX(),pos.getY(),pos.getZ());
            switch (state.getValue(FACING)){
                case NORTH -> {
                    windCharge.setPos(pos.north().getX(),pos.above().north().getY(),pos.north().getZ());
                    windCharge.setDeltaMovement(0,-0.01,-0.8);
                    break;
                }
                case SOUTH -> {
                    windCharge.setPos(pos.south().getX(),pos.above().south().getY(),pos.south().getZ());
                    windCharge.setDeltaMovement(0,-0.01,0.8);
                    break;
                }
                case EAST -> {
                    windCharge.setPos(pos.east().getX(),pos.above().east().getY(),pos.east().getZ());
                    windCharge.setDeltaMovement(0.8,-0.01,0);
                    break;
                }
                case WEST -> {
                    windCharge.setPos(pos.west().getX(),pos.above().west().getY(),pos.west().getZ());
                    windCharge.setDeltaMovement(-0.8,-0.01,0);
                    break;
                }
            }
            level.addFreshEntity(windCharge);
            float randomPitch = TCalcStuff.nextFloatBetweenInclusive(0.75f,0.89f);
            // unwaxed statues break easily when hit with wind
            level.playSound(null,pos,SoundEvents.IRON_GOLEM_STEP,SoundSource.BLOCKS,0.85f,randomPitch);
            level.destroyBlock(pos,true,windCharge);
            return;
        }
        super.onExplosionHit(state,level,pos,explosion,dropConsumer);
    }

    @Override
    public State getAge() {
        return this.ruststate;
    }

    public void applyWaxItemToBlock(BlockState bs, Level lvl, BlockPos bp){
        BlockState w_normal = TBlocks.WAXED_DAUNTING_STATUE.get().defaultBlockState();
        BlockState w_ok = TBlocks.WAXED_EXPOSED_DAUNTING_STATUE.get().defaultBlockState();
        BlockState w_bad = TBlocks.WAXED_WEATHERED_DAUNTING_STATUE.get().defaultBlockState();
        BlockState w_awful = TBlocks.WAXED_OXIDIZED_DAUNTING_STATUE.get().defaultBlockState();

        if(bs.is(TBlocks.OXIDIZED_DAUNTING_STATUE.get())){
            lvl.setBlock(bp,w_awful.setValue(FACING,bs.getValue(FACING)),3);
        }
        else if(bs.is(TBlocks.WEATHERED_DAUNTING_STATUE.get())){
            lvl.setBlock(bp,w_bad.setValue(FACING,bs.getValue(FACING)),3);
        }
        else if(bs.is(TBlocks.EXPOSED_DAUNTING_STATUE.get())){
            lvl.setBlock(bp,w_ok.setValue(FACING,bs.getValue(FACING)),3);
        }
        else if(bs.is(TBlocks.DAUNTING_STATUE.get())){
            lvl.setBlock(bp,w_normal.setValue(FACING,bs.getValue(FACING)),3);
        }
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!TConfig.windChargeDauntingStatue.getAsBoolean()){
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        if(player.getItemInHand(hand).is(Items.WIND_CHARGE) && !player.getCooldowns().isOnCooldown(Items.WIND_CHARGE)){
            if(level.isClientSide()){
                player.playSound(SoundEvents.BREEZE_WIND_CHARGE_BURST.value());
                return ItemInteractionResult.SUCCESS;
            }
            player.getCooldowns().addCooldown(Items.WIND_CHARGE,80);
            boolean hasEnhancer = level.getBlockState(pos.below()).is(Blocks.NETHERITE_BLOCK);
            if(ruststate != State.OXIDIZED){
                if(!player.isCreative()){
                    player.getItemInHand(hand).shrink(1);
                }
                WindCharge windCharge = new WindCharge(player,level,pos.getX(),pos.getY(),pos.getZ());
                if(hasEnhancer){
                    windCharge.setRemainingFireTicks(64);
                }
                switch (state.getValue(FACING)){
                    case NORTH -> {
                        windCharge.setPos(pos.north().getX(),pos.above().north().getY(),pos.north().getZ());
                        windCharge.setDeltaMovement(0,-0.01,-0.5);
                        break;
                    }
                    case SOUTH -> {
                        windCharge.setPos(pos.south().getX(),pos.above().south().getY(),pos.south().getZ());
                        windCharge.setDeltaMovement(0,-0.01,0.5);
                        break;
                    }
                    case EAST -> {
                        windCharge.setPos(pos.east().getX(),pos.above().east().getY(),pos.east().getZ());
                        windCharge.setDeltaMovement(0.5,-0.01,0);
                        break;
                    }
                    case WEST -> {
                        windCharge.setPos(pos.west().getX(),pos.above().west().getY(),pos.west().getZ());
                        windCharge.setDeltaMovement(-0.5,-0.01,0);
                        break;
                    }
                }
                level.addFreshEntity(windCharge);
                level.playSound(player,pos,SoundEvents.WIND_CHARGE_THROW,SoundSource.BLOCKS,1.0f,1.0f);
                return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level level, BlockPos pos, Player player, BlockHitResult bhr) {
        if(!TConfig.windChargeDauntingStatue.getAsBoolean()){
            return InteractionResult.PASS;
        }
        BlockState normal = TBlocks.DAUNTING_STATUE.get().defaultBlockState();
        BlockState ok = TBlocks.EXPOSED_DAUNTING_STATUE.get().defaultBlockState();
        BlockState bad = TBlocks.WEATHERED_DAUNTING_STATUE.get().defaultBlockState();
        BlockState current = bs;
        boolean successful = false;

        InteractionHand hand = player.getUsedItemHand();

        if(player.getItemInHand(hand).is(ItemTags.AXES)){
            if(level.getBlockState(pos).is(TBlocks.OXIDIZED_DAUNTING_STATUE.get())){
                level.setBlock(pos,bad.setValue(FACING,current.getValue(FACING)),3);

                if(player.getMainHandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }
                else if(player.getOffhandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.OFFHAND);
                }

                successful = true;
            }
            else if(level.getBlockState(pos).is(TBlocks.WEATHERED_DAUNTING_STATUE.get())){
                level.setBlock(pos,ok.setValue(FACING,current.getValue(FACING)),3);

                if(player.getMainHandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.MAINHAND);
                }
                else if(player.getOffhandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.OFFHAND);
                }

                successful = true;
            }
            else if(level.getBlockState(pos).is(TBlocks.EXPOSED_DAUNTING_STATUE.get())){
                level.setBlock(pos,normal.setValue(FACING,current.getValue(FACING)),3);

                if(player.getMainHandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.MAINHAND);
                }
                else if(player.getOffhandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.OFFHAND);
                }

                successful = true;
            }
            if(successful){
                level.playSound(null,pos, SoundEvents.AXE_SCRAPE, SoundSource.PLAYERS,1.0F,1.0F);
                ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.SCRAPE, UniformInt.of(3, 5));
                player.swing(hand);
            }
            return InteractionResult.CONSUME;
        }
        else if(player.getItemInHand(hand).is(Items.HONEYCOMB)){
            if(!player.isCreative()){
                player.getItemInHand(hand).shrink(1);
            }
            applyWaxItemToBlock(bs,level,pos); // apply the wax to the block, converting it to a waxed block variant
            // level renderer
            level.playLocalSound(pos,SoundEvents.HONEYCOMB_WAX_ON,SoundSource.PLAYERS,1.0F,1.0F,false);
            ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.WAX_ON, UniformInt.of(3, 5));
            // end
            player.swing(hand);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }
}
