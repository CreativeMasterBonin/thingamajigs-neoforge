package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

@SuppressWarnings("deprecated")
public class CatWalk extends ThingamajigsDecorativeBlock{
    public static final VoxelShape COMMON_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    public static final VoxelShape NORTHSOUTH = Stream.of(
            Block.box(15, 0, 1, 16, 12, 15),
            Block.box(0, 0, 0, 1, 16, 1),
            Block.box(15, 0, 0, 16, 16, 1),
            Block.box(15, 0, 15, 16, 16, 16),
            Block.box(0, 0, 15, 1, 16, 16),
            Block.box(0, 0, 1, 1, 12, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EASTWEST = Stream.of(
            Block.box(1, 0, 15, 15, 12, 16),
            Block.box(15, 0, 0, 16, 16, 1),
            Block.box(15, 0, 15, 16, 16, 16),
            Block.box(0, 0, 15, 1, 16, 16),
            Block.box(0, 0, 0, 1, 16, 1),
            Block.box(1, 0, 0, 15, 12, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTHSOUTH_COMBINED = Shapes.join(NORTHSOUTH,COMMON_SHAPE,BooleanOp.OR);
    public static final VoxelShape EASTWEST_COMBINED = Shapes.join(EASTWEST,COMMON_SHAPE,BooleanOp.OR);

    public CatWalk(Properties p) {
        super(p.strength(1.0F,5F).sound(SoundType.CHAIN).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(ctx.isHoldingItem(TBlocks.CATWALK.get().asItem()) || ctx.isHoldingItem(TBlocks.CATWALK_CENTER.get().asItem())){
            return Shapes.block();
        }
        switch (state.getValue(FACING)){
            case NORTH,SOUTH->{return NORTHSOUTH_COMBINED;}
            case EAST,WEST->{return EASTWEST_COMBINED;}
            default -> {return Shapes.block();}
        }
    }
    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)){
            case NORTH,SOUTH->{return NORTHSOUTH_COMBINED;}
            case EAST,WEST->{return EASTWEST_COMBINED;}
            default -> {return Shapes.block();}
        }
    }
}
