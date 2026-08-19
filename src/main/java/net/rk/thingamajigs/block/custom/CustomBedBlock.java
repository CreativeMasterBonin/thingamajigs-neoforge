package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.xtras.TCalcStuff;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class CustomBedBlock extends Block{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape COMMON_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 32.0D);
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(0, 2, 0, 16, 6, 16),
            Block.box(0, 2, 16, 16, 6, 32),
            Block.box(1, 6, 23, 15, 8, 31),
            Block.box(0, 0, 0, 3, 2, 3),
            Block.box(13, 0, 0, 16, 2, 3),
            Block.box(13, 0, 29, 16, 2, 32),
            Block.box(0, 0, 29, 3, 2, 32)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(0, 2, 0, 16, 6, 16),
            Block.box(0, 2, -16, 16, 6, 0),
            Block.box(1, 6, -15, 15, 8, -7),
            Block.box(13, 0, 13, 16, 2, 16),
            Block.box(0, 0, 13, 3, 2, 16),
            Block.box(0, 0, -16, 3, 2, -13),
            Block.box(13, 0, -16, 16, 2, -13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(0, 2, 0, 16, 6, 16),
            Block.box(-16, 2, 0, 0, 6, 16),
            Block.box(-15, 6, 1, -7, 8, 15),
            Block.box(13, 0, 0, 16, 2, 3),
            Block.box(13, 0, 13, 16, 2, 16),
            Block.box(-16, 0, 13, -13, 2, 16),
            Block.box(-16, 0, 0, -13, 2, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(0, 2, 0, 16, 6, 16),
            Block.box(16, 2, 0, 32, 6, 16),
            Block.box(23, 6, 1, 31, 8, 15),
            Block.box(0, 0, 13, 3, 2, 16),
            Block.box(0, 0, 0, 3, 2, 3),
            Block.box(29, 0, 0, 32, 2, 3),
            Block.box(29, 0, 13, 32, 2, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CustomBedBlock(Properties properties) {
        super(properties.strength(1.25F,10F).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case EAST:
                return EAST_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return COMMON_SHAPE;
        }
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
        if(entity.isSuppressingBounce()){
            super.updateEntityAfterFallOn(level,entity);
        }
        else{
            if (entity.getDeltaMovement().y < 0.0) {
                entity.setDeltaMovement(entity.getDeltaMovement().x, -entity.getDeltaMovement().y * 0.6600000262260437 * (entity instanceof LivingEntity ? 1.1 : 0.9), entity.getDeltaMovement().z);
            }
        }
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(!level.isClientSide()){
            if (player.isPassenger()) {
                player.stopRiding();
            }
            if(player.getHealth() < player.getMaxHealth() / 2 && !player.isInvulnerable()){
                player.addEffect(new MobEffectInstance(MobEffects.HEAL,1,1,false,false));
                if(level instanceof ServerLevel serverLevel){
                    serverLevel.sendParticles(ParticleTypes.HEART,pos.getX() + 0.5D,pos.getY() + 0.25,pos.getZ() + 0.5D,1,0D,0D,0D,0.25D);
                }
            }
            player.setDeltaMovement(Vec3.ZERO);
            player.hasImpulse = true;
            return InteractionResult.SUCCESS;
        }
        else{
            if(player.getHealth() < player.getMaxHealth() / 2 && !player.isInvulnerable()){
                player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL,0.57f, TCalcStuff.nextFloatBetweenInclusive(0.97f,1.0f));
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean shouldDisplayFluidOverlay(BlockState state, BlockAndTintGetter world, BlockPos pos, FluidState fluidstate) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public boolean isBed(BlockState state, BlockGetter level, BlockPos pos, LivingEntity sleeper) {
        return true;
    }
}
