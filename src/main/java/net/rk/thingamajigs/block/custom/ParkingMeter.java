package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class ParkingMeter extends ThingamajigsDecorativeBlock{
    public ParkingMeter(Properties p) {
        super(p.strength(1f,5f).sound(SoundType.LANTERN).mapColor(MapColor.METAL));
    }

    public static final VoxelShape NS = Shapes.join(
            Block.box(5, 16, 6, 11, 24, 10),
            Block.box(7, 0, 7, 9, 16, 9), BooleanOp.OR);

    public static final VoxelShape EW = Shapes.join(
            Block.box(6, 16, 5, 10, 24, 11),
            Block.box(7, 0, 7, 9, 16, 9), BooleanOp.OR);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction dir = pState.getValue(FACING);
        switch(dir){
            case NORTH,SOUTH -> {
                return NS;
            }
            case EAST,WEST -> {
                return EW;
            }
            default -> {
                return Shapes.block();
            }
        }
    }
}
