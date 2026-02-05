package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class BathroomSign extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH = Block.box(7, 0, 0, 9, 16, 16);
    public static final VoxelShape EASTWEST = Block.box(0, 0, 7, 16, 16, 9);
    public BathroomSign(Properties p) {
        super(p.strength(1f,2f).sound(SoundType.LANTERN).mapColor(MapColor.TERRACOTTA_WHITE));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH,SOUTH->{
                return NORTHSOUTH;
            }
            case EAST,WEST->{
                return EASTWEST;
            }
            default->{
                return Shapes.block();
            }
        }
    }
}
