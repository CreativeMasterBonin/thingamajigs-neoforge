package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class PoolTable extends ThrowSphereIntoRingMachine{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 0, 0, 3, 13, 3),
            Block.box(0, 13, 0, 16, 16, 2),
            Block.box(0, 13, 2, 2, 16, 16),
            Block.box(14, 13, 2, 16, 16, 16),
            Block.box(13, 0, 0, 16, 13, 3),
            Block.box(2, 13, 2, 14, 14, 16),
            Block.box(0, 15, 0, 1, 16, 1),
            Block.box(15, 15, 0, 16, 16, 1),
            Block.box(0, 0.95, 0, 1, 1.9499999999999997, 1),
            Block.box(15, 0.95, 0, 16, 1.9499999999999997, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST = Stream.of(
            Block.box(13, 0, 0, 16, 13, 3),
            Block.box(14, 13, 0, 16, 16, 16),
            Block.box(0, 13, 0, 14, 16, 2),
            Block.box(0, 13, 14, 14, 16, 16),
            Block.box(13, 0, 13, 16, 13, 16),
            Block.box(0, 13, 2, 14, 14, 14),
            Block.box(15, 15, 0, 16, 16, 1),
            Block.box(15, 15, 15, 16, 16, 16),
            Block.box(15, 0.95, 0, 16, 1.9499999999999997, 1),
            Block.box(15, 0.95, 15, 16, 1.9499999999999997, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH = Stream.of(
            Block.box(13, 0, 13, 16, 13, 16),
            Block.box(0, 13, 14, 16, 16, 16),
            Block.box(14, 13, 0, 16, 16, 14),
            Block.box(0, 13, 0, 2, 16, 14),
            Block.box(0, 0, 13, 3, 13, 16),
            Block.box(2, 13, 0, 14, 14, 14),
            Block.box(15, 15, 15, 16, 16, 16),
            Block.box(0, 15, 15, 1, 16, 16),
            Block.box(15, 0.95, 15, 16, 1.9499999999999997, 16),
            Block.box(0, 0.95, 15, 1, 1.9499999999999997, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST = Stream.of(
            Block.box(0, 0, 13, 3, 13, 16),
            Block.box(0, 13, 0, 2, 16, 16),
            Block.box(2, 13, 14, 16, 16, 16),
            Block.box(2, 13, 0, 16, 16, 2),
            Block.box(0, 0, 0, 3, 13, 3),
            Block.box(2, 13, 2, 16, 14, 14),
            Block.box(0, 15, 15, 1, 16, 16),
            Block.box(0, 15, 0, 1, 16, 1),
            Block.box(0, 0.95, 15, 1, 1.9499999999999997, 16),
            Block.box(0, 0.95, 0, 1, 1.9499999999999997, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_POOL_BACK = Stream.of(
            Block.box(13, 0, 13, 16, 13, 16),
            Block.box(0, 13, 14, 16, 16, 16),
            Block.box(14, 13, 0, 16, 16, 14),
            Block.box(0, 13, 0, 2, 16, 14),
            Block.box(0, 0, 13, 3, 13, 16),
            Block.box(2, 13, 0, 14, 14, 14),
            Block.box(15, 15, 15, 16, 16, 16),
            Block.box(0, 15, 15, 1, 16, 16),
            Block.box(15, 0.95, 15, 16, 1.9499999999999997, 16),
            Block.box(0, 0.95, 15, 1, 1.9499999999999997, 16),
            Block.box(11, 14, 8, 13, 16, 10),
            Block.box(7, 14, 8, 9, 16, 10),
            Block.box(9, 14, 8, 11, 16, 10),
            Block.box(5, 14, 8, 7, 16, 10),
            Block.box(3, 14, 8, 5, 16, 10),
            Block.box(7, 14, 4, 9, 16, 6),
            Block.box(9, 14, 4, 11, 16, 6),
            Block.box(5, 14, 4, 7, 16, 6),
            Block.box(4, 14, 6, 6, 16, 8),
            Block.box(6, 14, 2, 8, 16, 4),
            Block.box(10, 14, 6, 12, 16, 8),
            Block.box(8, 14, 2, 10, 16, 4),
            Block.box(6, 14, 6, 8, 16, 8),
            Block.box(8, 14, 6, 10, 16, 8),
            Block.box(7, 14, 0, 9, 16, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_POOL_BACK = Stream.of(
            Block.box(0, 0, 13, 3, 13, 16),
            Block.box(0, 13, 0, 2, 16, 16),
            Block.box(2, 13, 14, 16, 16, 16),
            Block.box(2, 13, 0, 16, 16, 2),
            Block.box(0, 0, 0, 3, 13, 3),
            Block.box(2, 13, 2, 16, 14, 14),
            Block.box(0, 15, 15, 1, 16, 16),
            Block.box(0, 15, 0, 1, 16, 1),
            Block.box(0, 0.95, 15, 1, 1.9499999999999997, 16),
            Block.box(0, 0.95, 0, 1, 1.9499999999999997, 1),
            Block.box(6, 14, 11, 8, 16, 13),
            Block.box(6, 14, 7, 8, 16, 9),
            Block.box(6, 14, 9, 8, 16, 11),
            Block.box(6, 14, 5, 8, 16, 7),
            Block.box(6, 14, 3, 8, 16, 5),
            Block.box(10, 14, 7, 12, 16, 9),
            Block.box(10, 14, 9, 12, 16, 11),
            Block.box(10, 14, 5, 12, 16, 7),
            Block.box(8, 14, 4, 10, 16, 6),
            Block.box(12, 14, 6, 14, 16, 8),
            Block.box(8, 14, 10, 10, 16, 12),
            Block.box(12, 14, 8, 14, 16, 10),
            Block.box(8, 14, 6, 10, 16, 8),
            Block.box(8, 14, 8, 10, 16, 10),
            Block.box(14, 14, 7, 16, 16, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_POOL_BACK = Stream.of(
            Block.box(0, 0, 0, 3, 13, 3),
            Block.box(0, 13, 0, 16, 16, 2),
            Block.box(0, 13, 2, 2, 16, 16),
            Block.box(14, 13, 2, 16, 16, 16),
            Block.box(13, 0, 0, 16, 13, 3),
            Block.box(2, 13, 2, 14, 14, 16),
            Block.box(0, 15, 0, 1, 16, 1),
            Block.box(15, 15, 0, 16, 16, 1),
            Block.box(0, 0.95, 0, 1, 1.9499999999999997, 1),
            Block.box(15, 0.95, 0, 16, 1.9499999999999997, 1),
            Block.box(3, 14, 6, 5, 16, 8),
            Block.box(7, 14, 6, 9, 16, 8),
            Block.box(5, 14, 6, 7, 16, 8),
            Block.box(9, 14, 6, 11, 16, 8),
            Block.box(11, 14, 6, 13, 16, 8),
            Block.box(7, 14, 10, 9, 16, 12),
            Block.box(5, 14, 10, 7, 16, 12),
            Block.box(9, 14, 10, 11, 16, 12),
            Block.box(10, 14, 8, 12, 16, 10),
            Block.box(8, 14, 12, 10, 16, 14),
            Block.box(4, 14, 8, 6, 16, 10),
            Block.box(6, 14, 12, 8, 16, 14),
            Block.box(8, 14, 8, 10, 16, 10),
            Block.box(6, 14, 8, 8, 16, 10),
            Block.box(7, 14, 14, 9, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_POOL_BACK =Stream.of(
            Block.box(13, 0, 0, 16, 13, 3),
            Block.box(14, 13, 0, 16, 16, 16),
            Block.box(0, 13, 0, 14, 16, 2),
            Block.box(0, 13, 14, 14, 16, 16),
            Block.box(13, 0, 13, 16, 13, 16),
            Block.box(0, 13, 2, 14, 14, 14),
            Block.box(15, 15, 0, 16, 16, 1),
            Block.box(15, 15, 15, 16, 16, 16),
            Block.box(15, 0.95, 0, 16, 1.9499999999999997, 1),
            Block.box(15, 0.95, 15, 16, 1.9499999999999997, 16),
            Block.box(8, 14, 3, 10, 16, 5),
            Block.box(8, 14, 7, 10, 16, 9),
            Block.box(8, 14, 5, 10, 16, 7),
            Block.box(8, 14, 9, 10, 16, 11),
            Block.box(8, 14, 11, 10, 16, 13),
            Block.box(4, 14, 7, 6, 16, 9),
            Block.box(4, 14, 5, 6, 16, 7),
            Block.box(4, 14, 9, 6, 16, 11),
            Block.box(6, 14, 10, 8, 16, 12),
            Block.box(2, 14, 8, 4, 16, 10),
            Block.box(6, 14, 4, 8, 16, 6),
            Block.box(2, 14, 6, 4, 16, 8),
            Block.box(6, 14, 8, 8, 16, 10),
            Block.box(6, 14, 6, 8, 16, 8),
            Block.box(0, 14, 7, 2, 16, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public PoolTable(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter getter, BlockPos blockPos, CollisionContext context) {
        return this.hasCollision ? blockState.getShape(getter, blockPos) : Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter getter, BlockPos blockPos, CollisionContext context) {
        if(blockState.getValue(MACHINE_ENDING) != MachineEnding.PAYMENT_SECTION){
            switch(getConnected(blockState).getOpposite()){
                case NORTH -> {
                    return SOUTH_POOL_BACK;
                }
                case SOUTH -> {
                    return NORTH_POOL_BACK;
                }
                case WEST -> {
                    return EAST_POOL_BACK;
                }
                case EAST -> {
                    return WEST_POOL_BACK;
                }
                default -> {
                    return Shapes.block();
                }
            }
        }
        else{
            switch(getConnected(blockState).getOpposite()){
                case NORTH -> {
                    return NORTH;
                }
                case SOUTH -> {
                    return SOUTH;
                }
                case WEST -> {
                    return WEST;
                }
                case EAST -> {
                    return EAST;
                }
                default -> {
                    return Shapes.block();
                }
            }
        }
    }

    @Override
    public void stepOn(Level lvl, BlockPos bp, BlockState bs, Entity e) {

    }

    @Override
    public void fallOn(Level lvl, BlockState bs, BlockPos bp, Entity e, float f1) {

    }
}
