package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.xtras.TSoundEvent;

@SuppressWarnings("deprecated")
public class AirductVent extends Block implements SimpleWaterloggedBlock {
    public static VoxelShape ALL = Block.box(0,6,0,16,16,16);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public AirductVent(Properties p) {
        super(p.strength(1f,2f).sound(SoundType.DEEPSLATE_TILES));
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED,false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }

    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {
        try{
            float rf = (float)(rs.nextFloat() + 1.0f);
            if(rf < 0.95f){
                rf = 0.95f;
            }
            else if(rf > 2.0f){
                rf = 2.0f;
            }
            // randomly-ish do this next part and the next part separately
            if(rs.nextInt(0,5) <= 2){
                if (!isFaceFull(lvl.getBlockState(bp.below()).getCollisionShape(lvl, bp), Direction.UP)) {
                    double d0 = (double)bp.getX() + rs.nextDouble();
                    double d1 = (double)bp.getY() + 0.4125D;
                    double d2 = (double)bp.getZ() + rs.nextDouble();
                    lvl.addParticle(ParticleTypes.END_ROD,d0,d1,d2,0.0D,-0.025D,0.0D);
                }
            }
            if(rs.nextInt(1,15) <= 3){
                if (!isFaceFull(lvl.getBlockState(bp.below()).getCollisionShape(lvl, bp), Direction.UP)) {
                    lvl.playSound(null,bp, TSoundEvent.AIR.get(), SoundSource.BLOCKS,0.25f,rf);
                }
            }
        }
        catch(Exception except){
            // do nothing
        }
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return state.getValue(WATERLOGGED);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
