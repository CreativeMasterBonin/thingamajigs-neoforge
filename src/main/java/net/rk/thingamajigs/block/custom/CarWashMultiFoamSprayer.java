package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
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
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.rk.thingamajigs.xtras.TColors;
import org.joml.Vector3f;

@SuppressWarnings("deprecated")
public class CarWashMultiFoamSprayer extends RedstoneLampBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public CarWashMultiFoamSprayer(Properties p) {
        super(p.strength(1F,20F).sound(SoundType.METAL).noOcclusion().noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource randomSource) {
        boolean isLit = state.getValue(LIT);
        if (isLit) {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            int color = TColors.getColorFromList(level.getRandom().nextInt() * 11);

            for (int l = 0; l < 4; ++l) {
                double d0 = (x + randomSource.nextFloat());
                double d1 = (y - randomSource.nextFloat() * 2.5);
                double d2 = (z + randomSource.nextFloat());
                double d3 = (randomSource.nextFloat() - 0.5D) * 0.4000000014901161D;
                double d4 = (randomSource.nextFloat() - 0.5D) * 0.4000000014901161D;
                double d5 = (randomSource.nextFloat() - 0.5D) * 0.4000000014901161D;

                float scale = level.getRandom().nextFloat() * 3.47f;
                if(scale < 2.15f){
                    scale = 2.15f;
                }

                level.addParticle(new DustParticleOptions(Vec3.fromRGB24(color).toVector3f(),scale), d0, d1, d2, d3, d4, d5);
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
