package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.entity.custom.Chair;
import net.rk.thingamajigs.xtras.TWeatheringCopperOther;

import java.util.List;

public class WeatheringCopperChairBlock extends Block implements TWeatheringCopperOther{
    private final TWeatheringCopperOther.State ruststate;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public WeatheringCopperChairBlock(TWeatheringCopperOther.State rs, BlockBehaviour.Properties p) {
        super(p.mapColor(MapColor.COLOR_ORANGE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 6.0F)
                .sound(SoundType.COPPER));
        this.ruststate = rs;
    }

    @Override
    public void randomTick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        this.changeOverTime(bs,sl,bp,rs);
    }

    public boolean isRandomlyTicking(BlockState bsr1) {
        return TWeatheringCopperOther.getNext(bsr1.getBlock()).isPresent();
    }

    public TWeatheringCopperOther.State getAge() {
        return this.ruststate;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH: return ChairBlock.NORTH;
            case SOUTH: return ChairBlock.SOUTH;
            case EAST: return ChairBlock.EAST;
            case WEST: return ChairBlock.WEST;
            default: return Shapes.block();
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level level, BlockPos pos, Player player, BlockHitResult bhr) {
        BlockState normal = TBlocks.COPPER_CHAIR.get().defaultBlockState();
        BlockState ok = TBlocks.EXPOSED_COPPER_CHAIR.get().defaultBlockState();
        BlockState bad = TBlocks.WEATHERED_COPPER_CHAIR.get().defaultBlockState();
        BlockState current = bs;
        boolean successful = false;

        InteractionHand hand = player.getUsedItemHand();

        if(player.getItemInHand(hand).is(ItemTags.AXES)){
            if(level.getBlockState(pos).is(TBlocks.OXIDIZED_COPPER_CHAIR.get())){
                level.setBlock(pos,bad.setValue(FACING,current.getValue(FACING)),3);

                if(player.getMainHandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.MAINHAND);
                }
                else if(player.getOffhandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.OFFHAND);
                }

                successful = true;
            }
            else if(level.getBlockState(pos).is(TBlocks.WEATHERED_COPPER_CHAIR.get())){
                level.setBlock(pos,ok.setValue(FACING,current.getValue(FACING)),3);

                if(player.getMainHandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.MAINHAND);
                }
                else if(player.getOffhandItem().is(ItemTags.AXES)){
                    player.getItemInHand(hand).hurtAndBreak(1, player,EquipmentSlot.OFFHAND);
                }

                successful = true;
            }
            else if(level.getBlockState(pos).is(TBlocks.EXPOSED_COPPER_CHAIR.get())){
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
            else{
                return spawnChairPerhaps(pos,level,bs,player);
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
        else{
            return spawnChairPerhaps(pos,level,bs,player);
        }
    }

    public InteractionResult spawnChairPerhaps(BlockPos pos, Level level, BlockState bs, Player player){
        try{
            double a = pos.getX() + 1.0D;
            double b = pos.getY() + 1.0D;
            double c = pos.getZ() + 1.0D;
            AABB aabb = new AABB(pos.getX(),pos.getY(),pos.getZ(),a,b,c);

            if(!level.isClientSide){

                List<Chair> chairs = level.getEntitiesOfClass(Chair.class, aabb);
                if(chairs.isEmpty()){
                    Chair ce = new Chair(level,pos,bs.getValue(FACING));
                    level.addFreshEntity(ce);
                    player.startRiding(ce,false);
                }
            }

            playCustomSitSound(level,pos,player); // custom sitting sounds!
            return InteractionResult.SUCCESS;
        }
        catch(Exception e){
            return InteractionResult.FAIL;
        }
    }

    public void applyWaxItemToBlock(BlockState bs, Level lvl, BlockPos bp){
        BlockState w_normal = TBlocks.WAXED_COPPER_CHAIR.get().defaultBlockState();
        BlockState w_ok = TBlocks.WAXED_EXPOSED_COPPER_CHAIR.get().defaultBlockState();
        BlockState w_bad = TBlocks.WAXED_WEATHERED_COPPER_CHAIR.get().defaultBlockState();
        BlockState w_awful = TBlocks.WAXED_OXIDIZED_COPPER_CHAIR.get().defaultBlockState();

        if(bs.is(TBlocks.OXIDIZED_COPPER_CHAIR.get())){
            lvl.setBlock(bp,w_awful.setValue(FACING,bs.getValue(FACING)),3);
        }
        else if(bs.is(TBlocks.WEATHERED_COPPER_CHAIR.get())){
            lvl.setBlock(bp,w_bad.setValue(FACING,bs.getValue(FACING)),3);
        }
        else if(bs.is(TBlocks.EXPOSED_COPPER_CHAIR.get())){
            lvl.setBlock(bp,w_ok.setValue(FACING,bs.getValue(FACING)),3);
        }
        else if(bs.is(TBlocks.COPPER_CHAIR.get())){
            lvl.setBlock(bp,w_normal.setValue(FACING,bs.getValue(FACING)),3);
        }
    }

    public void playCustomSitSound(Level l, BlockPos bp, Player p){
        SoundEvent event = SoundEvents.COPPER_HIT;
        l.playSound(p,bp, event, SoundSource.PLAYERS,1.0F,1.0F);
    }
}
