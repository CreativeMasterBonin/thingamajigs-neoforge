package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class StudioCamera extends ProfessionalTVCamera{
    public static final VoxelShape BASE = Stream.of(
            Block.box(5, 5, 5, 11, 19, 11),
            Block.box(3, 3, 3, 13, 5, 13),
            Block.box(2, 0, 2, 4, 2, 4),
            Block.box(12, 0, 2, 14, 2, 4),
            Block.box(12, 0, 12, 14, 2, 14),
            Block.box(2, 0, 12, 4, 2, 14),
            Block.box(3, 2, 3, 4, 3, 4),
            Block.box(12, 2, 3, 13, 3, 4),
            Block.box(3, 2, 12, 4, 3, 13),
            Block.box(12, 2, 12, 13, 3, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_BASE = Stream.of(NORTH,BASE).reduce((v1,v2)->Shapes.join(v1,v2,BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_BASE = Stream.of(SOUTH,BASE).reduce((v1,v2)->Shapes.join(v1,v2,BooleanOp.OR)).get();
    public static final VoxelShape EAST_BASE = Stream.of(EAST,BASE).reduce((v1,v2)->Shapes.join(v1,v2,BooleanOp.OR)).get();
    public static final VoxelShape WEST_BASE = Stream.of(WEST,BASE).reduce((v1,v2)->Shapes.join(v1,v2,BooleanOp.OR)).get();

    public StudioCamera(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch(pState.getValue(FACING)){
            case NORTH ->{
                return NORTH_BASE;
            }
            case SOUTH ->{
                return SOUTH_BASE;
            }
            case EAST ->{
                return EAST_BASE;
            }
            case WEST ->{
                return WEST_BASE;
            }
            default ->{
                return Shapes.block();
            }
        }
    }
}
