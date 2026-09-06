package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

import java.util.Optional;

public class InternetRouter extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH = Optional.of(Block.box(5, 0, 2, 11, 8, 14)).get();
    public static final VoxelShape EASTWEST = Optional.of(Block.box(2, 0, 5, 14, 8, 11)).get();

    public static final VoxelShape MODERN_MODEM_NORTHSOUTH = Block.box(7, 0, 2, 9, 8, 14);
    public static final VoxelShape MODERN_MODEM_EASTWEST = Block.box(2, 0, 7, 14, 8, 9);

    public static final VoxelShape OLD_MODEM_NORTH = Block.box(6.65, 0, 2, 8.65, 10, 14);
    public static final VoxelShape OLD_MODEM_EAST = Block.box(2, 0, 6.65, 14, 10, 8.65);
    public static final VoxelShape OLD_MODEM_SOUTH = Block.box(7.35, 0, 2, 9.35, 10, 14);
    public static final VoxelShape OLD_MODEM_WEST = Block.box(2, 0, 7.35, 14, 10, 9.35);

    public InternetRouter(Properties properties) {
        super(properties.sound(SoundType.METAL).noOcclusion().strength(1F,2F));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if(state.is(TBlocks.INTERNET_ROUTER.get())){
            switch(state.getValue(FACING)){
                case NORTH,SOUTH ->{return MODERN_MODEM_NORTHSOUTH;}
                case EAST,WEST ->{return MODERN_MODEM_EASTWEST;}
                default->{return Shapes.block();}
            }
        }
        else if(state.is(TBlocks.INTERNET_MODEM.get())){
            switch(state.getValue(FACING)){
                case NORTH ->{return OLD_MODEM_NORTH;}
                case SOUTH ->{return OLD_MODEM_SOUTH;}
                case EAST ->{return OLD_MODEM_EAST;}
                case WEST ->{return OLD_MODEM_WEST;}
                default->{return Shapes.block();}
            }
        }
        switch(state.getValue(FACING)){
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
