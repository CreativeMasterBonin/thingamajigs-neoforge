package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.logging.Logger;
import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class OperationTable extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH_S = Stream.of(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(5, 1, 5, 11, 3, 11),
            Block.box(6, 3, 6, 10, 11, 10),
            Block.box(0, 11, -8, 16, 13, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST_S = Stream.of(
            Block.box(4, 0, 4, 12, 1, 12),
            Block.box(5, 1, 5, 11, 3, 11),
            Block.box(6, 3, 6, 10, 11, 10),
            Block.box(-8, 11, 0, 24, 13, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public OperationTable(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = bs.getValue(FACING);
        switch(direction){
            case NORTH:
            case SOUTH:
                return NORTHSOUTH_S;
            case EAST:
            case WEST:
                return EASTWEST_S;
            default: return Shapes.block();
        }
    }

    @Override
    public boolean isBed(BlockState state, BlockGetter level, BlockPos pos, LivingEntity sleeper){
        try{
            if(sleeper instanceof Villager){
                return true;
            }
        }
        catch(Exception e){
            Logger.getAnonymousLogger()
                    .warning("Bed using Entity cannot use Thingamajigs Operating Table because it isn't Selectable. Err: "
                            + e.getMessage());
        }
        return false;
    }
}
