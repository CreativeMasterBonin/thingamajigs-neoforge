package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
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
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.xtras.TCalcStuff;
import net.rk.thingamajigs.xtras.TConfig;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class WaxedDauntingStatue extends Podium{
    public static final BooleanProperty REDIRECTED = BooleanProperty.create("redirected");
    public WaxedDauntingStatue(Properties properties) {
        super(properties.randomTicks().sound(SoundType.COPPER)
                .requiresCorrectToolForDrops()
                .strength(3.0f,6.0f));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED,false)
                .setValue(REDIRECTED,false));
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

    public void toBeStripped(BlockState b, Level l, BlockPos p){
        BlockState normal = TBlocks.DAUNTING_STATUE.get().defaultBlockState();
        BlockState ok = TBlocks.EXPOSED_DAUNTING_STATUE.get().defaultBlockState();
        BlockState bad = TBlocks.WEATHERED_DAUNTING_STATUE.get().defaultBlockState();
        BlockState awful = TBlocks.OXIDIZED_DAUNTING_STATUE.get().defaultBlockState();

        // states to choose from
        if(b.is(TBlocks.WAXED_OXIDIZED_DAUNTING_STATUE.get())){
            l.setBlock(p,awful.setValue(FACING,b.getValue(FACING)),3);
        }
        else if(b.is(TBlocks.WAXED_WEATHERED_DAUNTING_STATUE.get())){
            l.setBlock(p,bad.setValue(FACING,b.getValue(FACING)),3);
        }
        else if(b.is(TBlocks.WAXED_EXPOSED_DAUNTING_STATUE.get())){
            l.setBlock(p,ok.setValue(FACING,b.getValue(FACING)),3);
        }
        else if(b.is(TBlocks.WAXED_DAUNTING_STATUE.get())){
            l.setBlock(p,normal.setValue(FACING,b.getValue(FACING)),3);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player pl, BlockHitResult bhr) {
        if(!TConfig.windChargeDauntingStatue.getAsBoolean()){
            return InteractionResult.PASS;
        }
        InteractionHand h = pl.getUsedItemHand();
        if(lvl.isClientSide()){
            if(pl.getItemInHand(h).is(ItemTags.AXES)){
                //pl.playSound(SoundEvents.AXE_WAX_OFF,1.0f,1.0f);
                return InteractionResult.SUCCESS;
            }
            else{
                return InteractionResult.SUCCESS_NO_ITEM_USED;
            }
        }

        if(pl.getItemInHand(h).is(ItemTags.AXES)){
            toBeStripped(bs,lvl,bp);
            lvl.playSound(null,bp, SoundEvents.AXE_WAX_OFF, SoundSource.PLAYERS,1.0f,1.0f);
            ParticleUtils.spawnParticlesOnBlockFaces(lvl, bp, ParticleTypes.WAX_OFF, UniformInt.of(3, 5));
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void onExplosionHit(BlockState state, Level level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
        if(explosion.canTriggerBlocks() && level.getBlockState(pos.below()).is(Blocks.NETHERITE_BLOCK) && !state.getValue(REDIRECTED) && TConfig.windChargeDauntingStatue.getAsBoolean()){
            WindCharge windCharge = new WindCharge(null,level,pos.getX(),pos.getY(),pos.getZ());
            switch (state.getValue(FACING)){
                case NORTH -> {
                    windCharge.setPos(pos.north().getX(),pos.above().north().getY(),pos.north().getZ());
                    windCharge.setDeltaMovement(0,0.01,-0.8);
                    break;
                }
                case SOUTH -> {
                    windCharge.setPos(pos.south().getX(),pos.above().south().getY(),pos.south().getZ());
                    windCharge.setDeltaMovement(0,0.01,0.8);
                    break;
                }
                case EAST -> {
                    windCharge.setPos(pos.east().getX(),pos.above().east().getY(),pos.east().getZ());
                    windCharge.setDeltaMovement(0.8,0.01,0);
                    break;
                }
                case WEST -> {
                    windCharge.setPos(pos.west().getX(),pos.above().west().getY(),pos.west().getZ());
                    windCharge.setDeltaMovement(-0.8,0.01,0);
                    break;
                }
            }
            level.addFreshEntity(windCharge);
            float randomPitch = TCalcStuff.nextFloatBetweenInclusive(0.75f,0.89f);
            // waxed statues are better at rubbing off wind, since they have a more resistant surface
            Direction dir = Direction.getRandom(level.getRandom());
            if(dir == Direction.UP){
                dir = Direction.NORTH;
            }
            else if(dir == Direction.DOWN){
                dir = Direction.SOUTH;
            }
            level.playSound(null,pos,SoundEvents.ZOMBIE_ATTACK_IRON_DOOR,SoundSource.BLOCKS,0.35f,randomPitch);
            if(dir != state.getValue(FACING)){
                level.setBlock(pos,state.setValue(REDIRECTED,true).setValue(FACING,dir),3);
            }
            else{
                level.setBlock(pos,state.setValue(REDIRECTED,true),3);
            }
            return;
        }
        super.onExplosionHit(state,level,pos,explosion,dropConsumer);
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
            if(state.getBlock() != TBlocks.WAXED_OXIDIZED_DAUNTING_STATUE.get()){
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(REDIRECTED);
    }
}
