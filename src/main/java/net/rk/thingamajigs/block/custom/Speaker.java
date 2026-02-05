package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class Speaker extends ThingamajigsDecorativeBlock{
    public static final VoxelShape ALL = Block.box(5, 0, 5, 11, 9, 11);

    public Speaker(Properties p) {
        super(p.strength(1f,2f).sound(SoundType.DEEPSLATE_TILES));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return ALL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs,Level lvl,BlockPos bp,Player p,BlockHitResult bhr) {
        if(!lvl.isClientSide){
            if(p.isShiftKeyDown()){
                RandomSource rnd = lvl.getRandom();
                if(rnd.nextInt(5) == 0){
                    lvl.playSound(null,bp, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS,1f,1f);
                }
                else if(rnd.nextInt(7) == 0){
                    lvl.playSound(null,bp, SoundEvents.AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE, SoundSource.BLOCKS,1f,1f);
                }
                else if(rnd.nextInt(11) == 0){
                    lvl.playSound(null,bp, SoundEvents.VILLAGER_CELEBRATE, SoundSource.BLOCKS,1f,1f);
                }
                else if(rnd.nextInt(15) == 0){
                    lvl.playSound(null,bp, SoundEvents.EVOKER_PREPARE_WOLOLO, SoundSource.BLOCKS,1f,1f);
                }
                else if(rnd.nextInt(21) == 0){
                    lvl.playSound(null,bp, SoundEvents.BEACON_POWER_SELECT, SoundSource.BLOCKS,1f,1f);
                }
            }
            else{
                return InteractionResult.PASS;
            }
        }
        return InteractionResult.sidedSuccess(lvl.isClientSide);
    }
}
