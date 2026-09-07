package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

import java.util.stream.Stream;

public class KeyboardAndScreenBlock extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTH = Stream.of(
            Block.box(5, 0, 14, 11, 1, 16),
            Block.box(-3, 2, 12, 19, 16, 13),
            Block.box(-2, 3, 12, 18, 15, 12),
            Block.box(-3, 2, 12, 10, 3, 12),
            Block.box(10, 2, 12, 19, 3, 12),
            Block.box(18, 3, 12, 19, 15, 12),
            Block.box(-3, 3, 12, -2, 15, 12),
            Block.box(-3, 15, 12, 19, 16, 12),
            Block.box(7, 1, 13, 9, 4, 15),
            Block.box(0.75, 1, 3.5, 1.25, 1.25, 4.5),
            Block.box(0, 0, 2, 2, 1, 5),
            Block.box(3, 0, 0, 16, 1, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(0, 0, 5, 2, 1, 11),
            Block.box(3, 2, -3, 4, 16, 19),
            Block.box(4, 3, -2, 4, 15, 18),
            Block.box(4, 2, -3, 4, 3, 10),
            Block.box(4, 2, 10, 4, 3, 19),
            Block.box(4, 3, 18, 4, 15, 19),
            Block.box(4, 3, -3, 4, 15, -2),
            Block.box(4, 15, -3, 4, 16, 19),
            Block.box(1, 1, 7, 3, 4, 9),
            Block.box(11.5, 1, 0.75, 12.5, 1.25, 1.25),
            Block.box(11, 0, 0, 14, 1, 2),
            Block.box(9, 0, 3, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(5, 0, 0, 11, 1, 2),
            Block.box(-3, 2, 3, 19, 16, 4),
            Block.box(-2, 3, 4, 18, 15, 4),
            Block.box(6, 2, 4, 19, 3, 4),
            Block.box(-3, 2, 4, 6, 3, 4),
            Block.box(-3, 3, 4, -2, 15, 4),
            Block.box(18, 3, 4, 19, 15, 4),
            Block.box(-3, 15, 4, 19, 16, 4),
            Block.box(7, 1, 1, 9, 4, 3),
            Block.box(14.75, 1, 11.5, 15.25, 1.25, 12.5),
            Block.box(14, 0, 11, 16, 1, 14),
            Block.box(0, 0, 9, 13, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(14, 0, 5, 16, 1, 11),
            Block.box(12, 2, -3, 13, 16, 19),
            Block.box(12, 3, -2, 12, 15, 18),
            Block.box(12, 2, 6, 12, 3, 19),
            Block.box(12, 2, -3, 12, 3, 6),
            Block.box(12, 3, -3, 12, 15, -2),
            Block.box(12, 3, 18, 12, 15, 19),
            Block.box(12, 15, -3, 12, 16, 19),
            Block.box(13, 1, 7, 15, 4, 9),
            Block.box(3.5, 1, 14.75, 4.5, 1.25, 15.25),
            Block.box(2, 0, 14, 5, 1, 16),
            Block.box(0, 0, 0, 7, 1, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape FLAT_SHAPE = Block.box(0,0,0,16,1,16);

    public KeyboardAndScreenBlock(Properties p) {
        super(p.sound(SoundType.LANTERN)
                .strength(0.95F,2F));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH -> {return NORTH;}
            case SOUTH -> {return SOUTH;}
            case EAST -> {return EAST;}
            case WEST -> {return WEST;}
            default -> {return Shapes.block();}
        }
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(ctx.isHoldingItem(TBlocks.PC_CONTROLS.get().asItem()) || ctx.isHoldingItem(TBlocks.RGB_PC_CONTROLS.get().asItem())){
            return FLAT_SHAPE;
        }
        else{
            switch(state.getValue(FACING)){
                case NORTH -> {return NORTH;}
                case SOUTH -> {return SOUTH;}
                case EAST -> {return EAST;}
                case WEST -> {return WEST;}
                default -> {return Shapes.block();}
            }
        }
    }
}
