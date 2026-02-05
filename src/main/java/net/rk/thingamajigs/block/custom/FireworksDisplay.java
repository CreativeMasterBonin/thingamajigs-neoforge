package net.rk.thingamajigs.block.custom;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.ticks.TickPriority;

import java.util.List;
import java.util.Random;

public class FireworksDisplay extends Block{
    public FireworksDisplay(Properties properties) {
        super(properties.strength(1.5F,15F).noCollission());
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, Item.TooltipContext p_339606_, List<Component> list, TooltipFlag p_49819_) {
        list.add(Component.translatable("tooltip.thingamajigs.fireworks_display"));
    }

    public void neighborChanged(BlockState bs, Level lvl, BlockPos bp, Block blk, BlockPos bp2, boolean b1) {
        boolean powered_flag = lvl.hasNeighborSignal(bp);
        if (powered_flag) {
            lvl.scheduleTick(bp,this,5, TickPriority.LOW);
        }
    }

    @Override
    public void tick(BlockState bs, ServerLevel serverLevel, BlockPos bp, RandomSource rnds) {
        fireRandomRocketNewer(serverLevel,rnds,null,bp);
    }

    // making our OWN firework rocket with randomness
    private ItemStack getFirework(DyeColor dyeColor, int num2) {
        ItemStack itemstack = new ItemStack(Items.FIREWORK_ROCKET);

        RandomSource rd = RandomSource.create();

        int r = rd.nextInt(FireworkExplosion.Shape.values().length);
        if(r > 4){
            r = 4;
        }

        DyeColor dyecolor2 = Util.getRandom(DyeColor.values(),rd);

        int r2 = rd.nextInt(0,10);
        int r3 = rd.nextInt(0,10);

        itemstack.set(DataComponents.FIREWORKS,
                new Fireworks((byte)num2,
                        List.of(new FireworkExplosion(
                                        FireworkExplosion.Shape.byId(r),
                                        IntList.of(dyeColor.getFireworkColor()),
                                        IntList.of(dyecolor2.getFireworkColor()),
                                r2 <= 2,
                                        r3 >= 3))
                ));
        return itemstack;
    }

    public void fireRandomRocketNewer(ServerLevel level1, RandomSource rnd1, LivingEntity entity, BlockPos pos){
        DyeColor dyecolor = Util.getRandom(DyeColor.values(), rnd1);
        ItemStack itemstack = this.getFirework(dyecolor, 3);
        FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(
                level1.getLevel(), entity, pos.getX() + 0.5, pos.getY() + 0.35, pos.getZ() + 0.5, itemstack);
        level1.getLevel().addFreshEntity(fireworkrocketentity);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level lvl, BlockPos bp, Player ply, BlockHitResult bhr) {
        if(lvl instanceof ServerLevel serverLevelS){
            fireRandomRocketNewer((ServerLevel)lvl,lvl.getRandom(),ply,bp);
        }
        return InteractionResult.SUCCESS;
    }
}
