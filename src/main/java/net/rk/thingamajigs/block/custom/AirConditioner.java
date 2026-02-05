package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class AirConditioner extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH = java.util.Optional.of(Block.box(-8, 0, 0, 24, 16, 16)).get();
    public static final VoxelShape EASTWEST = java.util.Optional.of(Block.box(0, 0, -8, 16, 16, 24)).get();

    public AirConditioner(BlockBehaviour.Properties properties) {
        super(properties.strength(2F,25F));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
            case SOUTH:
                return NORTHSOUTH;
            case EAST:
            case WEST:
                return EASTWEST;
            default: return Shapes.block();
        }
    }
}
