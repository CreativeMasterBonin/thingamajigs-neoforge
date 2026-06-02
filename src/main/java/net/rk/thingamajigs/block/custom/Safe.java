package net.rk.thingamajigs.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class Safe extends OpenableContainerBlock{
    public static final VoxelShape NORTH_OPEN = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 1, 0, 1, 16, 16),
            Block.box(15, 1, 0, 16, 16, 16),
            Block.box(1, 1, 2, 2, 15, 14),
            Block.box(14, 1, 2, 15, 15, 14),
            Block.box(1, 1, 14, 15, 15, 15),
            Block.box(1, 15, 2, 15, 16, 16),
            Block.box(1, 1, 14, 15, 15, 16),
            Block.box(1, 1, -14, 2, 16, 0),
            Block.box(2, 14, -14, 3, 16, 0),
            Block.box(2, 1, -14, 3, 3, 0),
            Block.box(2, 3, -14, 3, 14, -12),
            Block.box(2, 3, -2, 3, 14, 0),
            Block.box(0, 5, -9, 3, 10, -5),
            Block.box(3, 4, -12, 4, 6, -2),
            Block.box(3, 9, -12, 4, 11, -2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_OPEN = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 1, 0, 16, 16, 1),
            Block.box(0, 1, 15, 16, 16, 16),
            Block.box(2, 1, 1, 14, 15, 2),
            Block.box(2, 1, 14, 14, 15, 15),
            Block.box(1, 1, 1, 2, 15, 15),
            Block.box(0, 15, 1, 14, 16, 15),
            Block.box(0, 1, 1, 2, 15, 15),
            Block.box(16, 1, 1, 30, 16, 2),
            Block.box(16, 14, 2, 30, 16, 3),
            Block.box(16, 1, 2, 30, 3, 3),
            Block.box(28, 3, 2, 30, 14, 3),
            Block.box(16, 3, 2, 18, 14, 3),
            Block.box(21, 5, 0, 25, 10, 3),
            Block.box(18, 4, 3, 28, 6, 4),
            Block.box(18, 9, 3, 28, 11, 4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_OPEN = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(15, 1, 0, 16, 16, 16),
            Block.box(0, 1, 0, 1, 16, 16),
            Block.box(14, 1, 2, 15, 15, 14),
            Block.box(1, 1, 2, 2, 15, 14),
            Block.box(1, 1, 1, 15, 15, 2),
            Block.box(1, 15, 0, 15, 16, 14),
            Block.box(1, 1, 0, 15, 15, 2),
            Block.box(14, 1, 16, 15, 16, 30),
            Block.box(13, 14, 16, 14, 16, 30),
            Block.box(13, 1, 16, 14, 3, 30),
            Block.box(13, 3, 28, 14, 14, 30),
            Block.box(13, 3, 16, 14, 14, 18),
            Block.box(13, 5, 21, 16, 10, 25),
            Block.box(12, 4, 18, 13, 6, 28),
            Block.box(12, 9, 18, 13, 11, 28)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_OPEN = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(0, 1, 15, 16, 16, 16),
            Block.box(0, 1, 0, 16, 16, 1),
            Block.box(2, 1, 14, 14, 15, 15),
            Block.box(2, 1, 1, 14, 15, 2),
            Block.box(14, 1, 1, 15, 15, 15),
            Block.box(2, 15, 1, 16, 16, 15),
            Block.box(14, 1, 1, 16, 15, 15),
            Block.box(-14, 1, 14, 0, 16, 15),
            Block.box(-14, 14, 13, 0, 16, 14),
            Block.box(-14, 1, 13, 0, 3, 14),
            Block.box(-14, 3, 13, -12, 14, 14),
            Block.box(-2, 3, 13, 0, 14, 14),
            Block.box(-9, 5, 13, -5, 10, 16),
            Block.box(-12, 4, 12, -2, 6, 13),
            Block.box(-12, 9, 12, -2, 11, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();


    public Safe(Properties p) {
        super(p.sound(SoundType.METAL).strength(2f,50f).noOcclusion().mapColor(MapColor.METAL));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("block.thingamajigs.safe.desc")
                .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void playCustomSound(Level lvl, BlockPos bp) {
        lvl.playSound(null,bp, SoundEvents.IRON_TRAPDOOR_OPEN, SoundSource.BLOCKS,1.0f,1.0f);
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        projectile.deflect(ProjectileDeflection.REVERSE,projectile,projectile.getOwner(),false);
        level.playSound(null,new BlockPos(projectile.getBlockX(),projectile.getBlockY(),projectile.getBlockZ()),
                SoundEvents.LANTERN_HIT,SoundSource.BLOCKS,1f,1f);
    }

    @Override
    public void onExplosionHit(BlockState state, Level level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
        if(state.getValue(ACCESSIBLE)){
            super.onExplosionHit(state,level,pos,explosion,dropConsumer);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if(state.getValue(ACCESSIBLE)){
            switch (state.getValue(FACING)){
                case NORTH -> {
                    return NORTH_OPEN;
                }
                case SOUTH -> {
                    return SOUTH_OPEN;
                }
                case EAST -> {
                    return EAST_OPEN;
                }
                case WEST -> {
                    return WEST_OPEN;
                }
                default -> {return Shapes.block();}
            }
        }
        else{
            return Shapes.block();
        }
    }
}
