package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.xtras.TParticles;

import java.util.stream.Stream;

public class SnowMachine extends ToggledStateBlock{
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 0, 0, 2, 3, 2),
            Block.box(0, 0, 14, 2, 3, 16),
            Block.box(14, 0, 14, 16, 3, 16),
            Block.box(14, 0, 0, 16, 3, 2),
            Block.box(0, 3, 0, 16, 5, 16),
            Block.box(2, 5, 2, 14, 8, 13),
            Block.box(1, 5, 7, 3, 13, 9),
            Block.box(13, 5, 7, 15, 13, 9),
            Block.box(3, 5, 13, 13, 16, 17),
            Block.box(3, 7, 9, 13, 18, 13),
            Block.box(3, 9, 5, 13, 20, 9),
            Block.box(3, 11, 1, 13, 22, 5),
            Block.box(3, 9, -5, 13, 23, 1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(14, 0, 14, 16, 3, 16),
            Block.box(14, 0, 0, 16, 3, 2),
            Block.box(0, 0, 0, 2, 3, 2),
            Block.box(0, 0, 14, 2, 3, 16),
            Block.box(0, 3, 0, 16, 5, 16),
            Block.box(2, 5, 3, 14, 8, 14),
            Block.box(13, 5, 7, 15, 13, 9),
            Block.box(1, 5, 7, 3, 13, 9),
            Block.box(3, 5, -1, 13, 16, 3),
            Block.box(3, 7, 3, 13, 18, 7),
            Block.box(3, 9, 7, 13, 20, 11),
            Block.box(3, 11, 11, 13, 22, 15),
            Block.box(3, 9, 15, 13, 23, 21)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(14, 0, 0, 16, 3, 2),
            Block.box(0, 0, 0, 2, 3, 2),
            Block.box(0, 0, 14, 2, 3, 16),
            Block.box(14, 0, 14, 16, 3, 16),
            Block.box(0, 3, 0, 16, 5, 16),
            Block.box(3, 5, 2, 14, 8, 14),
            Block.box(7, 5, 1, 9, 13, 3),
            Block.box(7, 5, 13, 9, 13, 15),
            Block.box(-1, 5, 3, 3, 16, 13),
            Block.box(3, 7, 3, 7, 18, 13),
            Block.box(7, 9, 3, 11, 20, 13),
            Block.box(11, 11, 3, 15, 22, 13),
            Block.box(15, 9, 3, 21, 23, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(0, 0, 14, 2, 3, 16),
            Block.box(14, 0, 14, 16, 3, 16),
            Block.box(14, 0, 0, 16, 3, 2),
            Block.box(0, 0, 0, 2, 3, 2),
            Block.box(0, 3, 0, 16, 5, 16),
            Block.box(2, 5, 2, 13, 8, 14),
            Block.box(7, 5, 13, 9, 13, 15),
            Block.box(7, 5, 1, 9, 13, 3),
            Block.box(13, 5, 3, 17, 16, 13),
            Block.box(9, 7, 3, 13, 18, 13),
            Block.box(5, 9, 3, 9, 20, 13),
            Block.box(1, 11, 3, 5, 22, 13),
            Block.box(-5, 9, 3, 1, 23, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public SnowMachine(Properties p) {
        super(p.strength(1f,10f).sound(SoundType.LANTERN).mapColor(MapColor.COLOR_GRAY));
        this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(TOGGLED,false).setValue(WATERLOGGED,false);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random){
        if(state.getValue(TOGGLED)){
            double posX = (double)pos.getX() + 0.5;
            double posY = (double)pos.getY() + 1.25D;
            double posZ = (double)pos.getZ() + 0.5;
            Direction direction = state.getValue(FACING);
            Direction.Axis axis = direction.getAxis();
            double d3 = 0.52;
            double d4 = random.nextDouble() * 0.6 - 0.3;
            double d5 = axis == Direction.Axis.X ? (double)direction.getStepX() * d3 : d4;
            double d7 = axis == Direction.Axis.Z ? (double)direction.getStepZ() * d3 : d4;

            double x1 = posX + d5;
            double x = 0;
            double z = 0;
            switch(state.getValue(FACING)){
                case NORTH -> {
                    x = 0;
                    z = 0.15 * -1;
                }
                case EAST -> {
                    x = 0.151;
                    z = 0;
                }
                case WEST -> {
                    x = -0.151;
                    z = 0;
                }
                case SOUTH -> {
                    x = 0;
                    z = 0.151;
                }
            }
            level.addParticle(TParticles.ICY_AIR.get(), posX + d5, posY, posZ + d7, x, 0.175D, z);
            level.playLocalSound(pos.getX(),pos.getY(),pos.getZ(),
                    SoundEvents.POWDER_SNOW_PLACE, SoundSource.BLOCKS,0.25f,1.0f - level.getRandom().nextFloat() * 0.5f,true);
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch(state.getValue(FACING)){
            case NORTH -> {
                return NORTH_SHAPE;
            }
            case SOUTH ->  {
                return SOUTH_SHAPE;
            }
            case EAST -> {
                return EAST_SHAPE;
            }
            case WEST -> {
                return WEST_SHAPE;
            }
            default -> {
                return Shapes.block();
            }
        }
    }
}
