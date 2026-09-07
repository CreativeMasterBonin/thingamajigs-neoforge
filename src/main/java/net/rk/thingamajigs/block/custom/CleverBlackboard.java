package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.blockentity.custom.CleverBlackboardBE;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class CleverBlackboard extends ThingamajigsDecorativeBlock implements EntityBlock {
    public static final VoxelShape NORTH = Stream.of(
            Block.box(-8, 0, 8, 24, 2, 12),
            Block.box(-16, 0, 12, 32, 32, 16),
            Block.box(-16, 31, 6, 32, 32, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(4, 0, -8, 8, 2, 24),
            Block.box(0, 0, -16, 4, 32, 32),
            Block.box(4, 31, -16, 10, 32, 32)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(-8, 0, 4, 24, 2, 8),
            Block.box(-16, 0, 0, 32, 32, 4),
            Block.box(-16, 31, 4, 32, 32, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(8, 0, -8, 12, 2, 24),
            Block.box(12, 0, -16, 16, 32, 32),
            Block.box(6, 31, -16, 12, 32, 32)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ALL_CUSTOM = Block.box(0.0f,0.0f,0.0f,16.0f,32.0f,16.0f);

    public CleverBlackboard(Properties properties) {
        super(properties.strength(1f,2f).sound(SoundType.LANTERN).mapColor(MapColor.COLOR_LIGHT_GRAY).noOcclusion());
    }

    @Override
    public RenderShape getRenderShape(BlockState bs) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState bs) {
        return new CleverBlackboardBE(pos,bs);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(level != null) {
            CleverBlackboardBE blackboard = (CleverBlackboardBE) level.getBlockEntity(pos);
            if (blackboard instanceof CleverBlackboardBE) {
                if (blackboard.custom) {
                    return ALL_CUSTOM;
                }
            }
        }
        switch (state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return Shapes.block();}
        }
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx){
        if(level != null){
            CleverBlackboardBE blackboard = (CleverBlackboardBE)level.getBlockEntity(pos);
            if(blackboard instanceof CleverBlackboardBE){
                if(blackboard.custom){
                    return ALL_CUSTOM;
                }
                else{
                    switch (state.getValue(FACING)){
                        case NORTH -> {return NORTH;}
                        case SOUTH -> {return SOUTH;}
                        case EAST -> {return EAST;}
                        case WEST -> {return WEST;}
                        default -> {return Shapes.block();}
                    }
                }
            }
        }
        return Shapes.block();
    }
}
