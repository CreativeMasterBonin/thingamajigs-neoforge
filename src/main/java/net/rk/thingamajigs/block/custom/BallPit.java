package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.xtras.TCalcStuff;
import net.rk.thingamajigs.xtras.TParticles;
import net.rk.thingamajigs.xtras.TSoundEvent;

import java.util.List;

public class BallPit extends Block{
    public static final VoxelShape ALL = Block.box(0,0,0,16,3,16);

    public BallPit(BlockBehaviour.Properties p) {
        super(p.strength(1f,5f)
                .noOcclusion()
                .sound(SoundType.CANDLE).mapColor(MapColor.COLOR_LIGHT_GRAY));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return ALL;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return ALL;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("block.thingamajigs.ball_pit.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if(entity instanceof LivingEntity && level.getGameTime() % 7 == 0){
            boolean xIsGood = entity.getDeltaMovement().x > 0.05D || entity.getDeltaMovement().x < -0.05D;
            boolean zIsGood = entity.getDeltaMovement().z > 0.05D || entity.getDeltaMovement().z < -0.05D;

            if(xIsGood && zIsGood){
                float pitch = level.getRandom().nextFloat() * 2.15f;
                if(pitch < 1.75f){
                    pitch = 1.75f;
                }
                level.playLocalSound(pos,
                        TSoundEvent.PLASTIC_SHUFFLING.get(),
                        SoundSource.BLOCKS,
                        0.15f,pitch,true);
                for(int p = 0; p < 5; p++){
                    double calcValueX = TCalcStuff.nextDoubleBetweenInclusive(-0.95D,0.95D);
                    double calcValueZ = TCalcStuff.nextDoubleBetweenInclusive(-0.95D,0.95D);
                    level.addParticle(TParticles.PLASTIC_BALL.get(),
                            entity.getX() + calcValueX,entity.getY() + 0.95D,entity.getZ() + calcValueZ,
                            0.0D,0.55D + level.getRandom().nextDouble() * 0.25,0.0D);
                }
                return;
            }
        }
    }
}
