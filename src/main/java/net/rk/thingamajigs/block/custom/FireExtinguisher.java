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
public class FireExtinguisher extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH_SHAPE = Block.box(
            4.5, 4, 9.5, 10.5, 18, 15.5);
    public static final VoxelShape SOUTH_SHAPE = Block.box(
            5.5, 4, 0.5, 11.5, 18, 6.5);
    public static final VoxelShape EAST_SHAPE = Block.box(
            0.5, 4, 4.5, 6.5, 18, 10.5);
    public static final VoxelShape WEST_SHAPE = Block.box(
            9.5, 4, 5.5, 15.5, 18, 11.5);
    public FireExtinguisher(Properties p) {
        super(p.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_RED).strength(0.25F,15F));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
        }
        return Shapes.block();
    }
}
