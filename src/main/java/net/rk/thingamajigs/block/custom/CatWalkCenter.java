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

@SuppressWarnings("deprecated")
public class CatWalkCenter extends Block{
    public static final VoxelShape COMMON_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    public static final VoxelShape CENTER_WALL = Stream.of(
            Block.box(0, 0, 0, 1, 16, 1),
            Block.box(15, 0, 0, 16, 16, 1),
            Block.box(15, 0, 15, 16, 16, 16),
            Block.box(0, 0, 15, 1, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape COMBINED_ALL = Shapes.join(COMMON_SHAPE,CENTER_WALL,BooleanOp.OR);



    public CatWalkCenter(Properties p) {
        super(p.strength(1.0F,5F).sound(SoundType.CHAIN).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        if(ctx.isHoldingItem(TBlocks.CATWALK.get().asItem()) || ctx.isHoldingItem(TBlocks.CATWALK_CENTER.get().asItem())){
            return Shapes.block();
        }
        return COMBINED_ALL;
    }
    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return COMBINED_ALL;
    }
}
