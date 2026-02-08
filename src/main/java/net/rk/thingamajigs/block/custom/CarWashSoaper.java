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
import net.rk.thingamajigs.xtras.TParticles;

@SuppressWarnings("deprecated")
public class CarWashSoaper extends RedstoneLampBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public CarWashSoaper(Properties p) {
        super(p.strength(1F,20F).sound(SoundType.METAL).noOcclusion().noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState pState, Level level, BlockPos pPos, RandomSource pRandom) {
        for (int i = 0; i < 3; i++){
            double d0 = (double)pPos.getX() + 0.33D - level.getRandom().nextDouble() * 0.25D;
            double d1 = (double)pPos.getY() + 0.2D;
            double d2 = (double)pPos.getZ() + 0.33D - level.getRandom().nextDouble() * 0.25D;
            boolean isLit = pState.getValue(LIT);
            if (isLit){
                level.addParticle(TParticles.SOAP.get(), d0 + 0.33D, d1, d2 + 0.33D, 0.0D,
                        0.01D + level.getRandom().nextDouble() * 0.15D, 0.0D);
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
