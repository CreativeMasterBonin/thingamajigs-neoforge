package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class Locker extends ToggledStateBlock{
    public static final VoxelShape ALL = Block.box(0,0,0,16,32,16);

    public Locker(Properties p) {
        super(p.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_BLUE).strength(1F,10F));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TOGGLED, false).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return ALL;
    }

    @Override
    public void playSound(BlockState bs, Level lvl, BlockPos bp){
        if(bs.getValue(TOGGLED)){
            lvl.playSound(null,bp,SoundEvents.ARMOR_EQUIP_NETHERITE.value(), SoundSource.BLOCKS,1f,1f);
        }
        else{
            lvl.playSound(null,bp,SoundEvents.COPPER_TRAPDOOR_OPEN, SoundSource.BLOCKS,1f,1f);
        }
    }
}
