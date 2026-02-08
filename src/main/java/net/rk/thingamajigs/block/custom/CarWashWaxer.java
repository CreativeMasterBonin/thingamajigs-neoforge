package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class CarWashWaxer extends RedstoneLampBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public CarWashWaxer(Properties p) {
        super(p.strength(1F,20F).sound(SoundType.METAL).noOcclusion().noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource){
        if(state.getValue(LIT)){
            for (int i = 0; i < 3; i++) {
                double d0 = (double)pos.getX() + 0.7D * randomSource.nextDouble();
                double d1 = (double)pos.getY() - 3.5D * randomSource.nextDouble();
                double d2 = (double)pos.getZ() + 0.7D * randomSource.nextDouble();
                boolean randomBoolean = randomSource.nextBoolean();
                if (randomBoolean){
                    level.addParticle(ParticleTypes.WAX_ON, d0, d1, d2, 0.0D, -2.0D, 0.0D);
                }
                else{
                    level.addParticle(ParticleTypes.CLOUD, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    level.addParticle(ParticleTypes.DOLPHIN, d0, d1, d2, 0.0D, -0.2D, 0.0D);
                }
            }
        }
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos())).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
