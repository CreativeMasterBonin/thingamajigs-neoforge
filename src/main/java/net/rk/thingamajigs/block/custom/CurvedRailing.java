package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecated")
public class CurvedRailing extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NS = Shapes.join(Block.box(0, 0, 0, 16, 0.5, 8),
            Block.box(0, 0, 0, 16, 16, 4), BooleanOp.OR);
    public static final VoxelShape ES =
            Shapes.join(Block.box(8, 0, 0, 16, 0.5, 16),
                    Block.box(12, 0, 0, 16, 16, 16), BooleanOp.OR);
    public static final VoxelShape SS = Shapes.join(Block.box(0, 0, 8, 16, 0.5, 16),
            Block.box(0, 0, 12, 16, 16, 16), BooleanOp.OR);
    public static final VoxelShape WS = Shapes.join(Block.box(0, 0, 0, 8, 0.5, 16),
            Block.box(0, 0, 0, 4, 16, 16), BooleanOp.OR);
    // col
    public static final VoxelShape NSC = Shapes.join(Block.box(0, 0, 0, 16, 0.5, 8),
            Block.box(0, 0, 0, 16, 20, 4),BooleanOp.OR);
    public static final VoxelShape ESC =
            Shapes.join(Block.box(8, 0, 0, 16, 0.5, 16),
                    Block.box(12, 0, 0, 16, 20, 16), BooleanOp.OR);
    public static final VoxelShape SSC = Shapes.join(Block.box(0, 0, 8, 16, 0.5, 16),
            Block.box(0, 0, 12, 16, 20, 16), BooleanOp.OR);
    public static final VoxelShape WSC = Shapes.join(Block.box(0, 0, 0, 8, 0.5, 16),
            Block.box(0, 0, 0, 4, 20, 16), BooleanOp.OR);

    // other
    public static final VoxelShape NS2 = Block.box(0, 0, 0, 16, 16, 4);
    public static final VoxelShape ES2 = Block.box(12, 0, 0, 16, 16, 16);
    public static final VoxelShape SS2 = Block.box(0, 0, 12, 16, 16, 16);
    public static final VoxelShape WS2 = Block.box(0, 0, 0, 4, 16, 16);

    public CurvedRailing(Properties properties) {
        super(properties.strength(1f,5f).mapColor(MapColor.METAL).sound(SoundType.METAL));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = bs.getValue(FACING);
        switch(direction){
            case NORTH:
                return NS;
            case SOUTH:
                return SS;
            case EAST:
                return ES;
            case WEST:
                return WS;
            default:
                return Shapes.block();
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = bs.getValue(FACING);
        switch(direction){
            case NORTH:
                return NSC;
            case SOUTH:
                return SSC;
            case EAST:
                return ESC;
            case WEST:
                return WSC;
            default:
                return Shapes.block();
        }
    }
}
