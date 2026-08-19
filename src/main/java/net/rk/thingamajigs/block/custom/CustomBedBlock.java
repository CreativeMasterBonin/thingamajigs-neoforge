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

    public static final VoxelShape NORTH_ELECTRIC_SHAPE = Stream.of(
            Block.box(0, 7, -4, 16, 10, 6),
            Block.box(-1, 6, -5, 0, 13, 8),
            Block.box(16, 6, -5, 17, 13, 8),
            Block.box(-1, 3, -6, 17, 15, -5),
            Block.box(-0.00999999999999801, 5, 22, 15.99, 15, 23),
            Block.box(-2, 8, 1, -1, 12, 8),
            Block.box(-2, 9, -3, -1, 13, -1),
            Block.box(1, 1, -2, 15, 2, -1),
            Block.box(1, 1, 17, 15, 2, 18),
            Block.box(0, 0, -3, 1, 3, 0),
            Block.box(15, 0, -3, 16, 3, 0),
            Block.box(15, 0, 16, 16, 3, 19),
            Block.box(0, 0, 16, 1, 3, 19),
            Block.box(7, 2, -4, 9, 4, 24),
            Block.box(5, 1, 0, 7, 5, 2),
            Block.box(9, 1, 0, 11, 5, 2),
            Block.box(9, 1, 14, 11, 5, 16),
            Block.box(5, 1, 14, 7, 5, 16),
            Block.box(4, 5, 0, 12, 6, 17),
            Block.box(13, 3, 23, 14, 11, 24),
            Block.box(7, 2, 24, 14, 4, 25),
            Block.box(12, 11, 23, 15, 12, 24),
            Block.box(0, 7, 6, 16, 9, 11),
            Block.box(0, 6, 11, 16, 8, 13),
            Block.box(0, 7, 13, 16, 9, 15),
            Block.box(0, 9, 15, 16, 13, 17),
            Block.box(0, 12, 17, 16, 16, 21),
            Block.box(16, 4, 10, 17, 12, 22),
            Block.box(-1, 4, 10, 0, 12, 22),
            Block.box(16, 12, 17, 17, 15, 24),
            Block.box(-1, 12, 17, 0, 15, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_ELECTRIC_SHAPE = Stream.of(
            Block.box(10, 7, 0, 20, 10, 16),
            Block.box(8, 6, -1, 21, 13, 0),
            Block.box(8, 6, 16, 21, 13, 17),
            Block.box(21, 3, -1, 22, 15, 17),
            Block.box(-7, 5, -0.00999999999999801, -6, 15, 15.99),
            Block.box(8, 8, -2, 15, 12, -1),
            Block.box(17, 9, -2, 19, 13, -1),
            Block.box(17, 1, 1, 18, 2, 15),
            Block.box(-2, 1, 1, -1, 2, 15),
            Block.box(16, 0, 0, 19, 3, 1),
            Block.box(16, 0, 15, 19, 3, 16),
            Block.box(-3, 0, 15, 0, 3, 16),
            Block.box(-3, 0, 0, 0, 3, 1),
            Block.box(-8, 2, 7, 20, 4, 9),
            Block.box(14, 1, 5, 16, 5, 7),
            Block.box(14, 1, 9, 16, 5, 11),
            Block.box(0, 1, 9, 2, 5, 11),
            Block.box(0, 1, 5, 2, 5, 7),
            Block.box(-1, 5, 4, 16, 6, 12),
            Block.box(-8, 3, 13, -7, 11, 14),
            Block.box(-9, 2, 7, -8, 4, 14),
            Block.box(-8, 11, 12, -7, 12, 15),
            Block.box(5, 7, 0, 10, 9, 16),
            Block.box(3, 6, 0, 5, 8, 16),
            Block.box(1, 7, 0, 3, 9, 16),
            Block.box(-1, 9, 0, 1, 13, 16),
            Block.box(-5, 12, 0, -1, 16, 16),
            Block.box(-6, 4, 16, 6, 12, 17),
            Block.box(-6, 4, -1, 6, 12, 0),
            Block.box(-8, 12, 16, -1, 15, 17),
            Block.box(-8, 12, -1, -1, 15, 0)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_ELECTRIC_SHAPE = Stream.of(
            Block.box(0, 7, 10, 16, 10, 20),
            Block.box(16, 6, 8, 17, 13, 21),
            Block.box(-1, 6, 8, 0, 13, 21),
            Block.box(-1, 3, 21, 17, 15, 22),
            Block.box(0.009999999999999787, 5, -7, 16.009999999999998, 15, -6),
            Block.box(17, 8, 8, 18, 12, 15),
            Block.box(17, 9, 17, 18, 13, 19),
            Block.box(1, 1, 17, 15, 2, 18),
            Block.box(1, 1, -2, 15, 2, -1),
            Block.box(15, 0, 16, 16, 3, 19),
            Block.box(0, 0, 16, 1, 3, 19),
            Block.box(0, 0, -3, 1, 3, 0),
            Block.box(15, 0, -3, 16, 3, 0),
            Block.box(7, 2, -8, 9, 4, 20),
            Block.box(9, 1, 14, 11, 5, 16),
            Block.box(5, 1, 14, 7, 5, 16),
            Block.box(5, 1, 0, 7, 5, 2),
            Block.box(9, 1, 0, 11, 5, 2),
            Block.box(4, 5, -1, 12, 6, 16),
            Block.box(2, 3, -8, 3, 11, -7),
            Block.box(2, 2, -9, 9, 4, -8),
            Block.box(1, 11, -8, 4, 12, -7),
            Block.box(0, 7, 5, 16, 9, 10),
            Block.box(0, 6, 3, 16, 8, 5),
            Block.box(0, 7, 1, 16, 9, 3),
            Block.box(0, 9, -1, 16, 13, 1),
            Block.box(0, 12, -5, 16, 16, -1),
            Block.box(-1, 4, -6, 0, 12, 6),
            Block.box(16, 4, -6, 17, 12, 6),
            Block.box(-1, 12, -8, 0, 15, -1),
            Block.box(16, 12, -8, 17, 15, -1)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_ELECTRIC_SHAPE = Stream.of(
            Block.box(-4, 7, 0, 6, 10, 16),
            Block.box(-5, 6, 16, 8, 13, 17),
            Block.box(-5, 6, -1, 8, 13, 0),
            Block.box(-6, 3, -1, -5, 15, 17),
            Block.box(22, 5, 0.009999999999999787, 23, 15, 16.009999999999998),
            Block.box(1, 8, 17, 8, 12, 18),
            Block.box(-3, 9, 17, -1, 13, 18),
            Block.box(-2, 1, 1, -1, 2, 15),
            Block.box(17, 1, 1, 18, 2, 15),
            Block.box(-3, 0, 15, 0, 3, 16),
            Block.box(-3, 0, 0, 0, 3, 1),
            Block.box(16, 0, 0, 19, 3, 1),
            Block.box(16, 0, 15, 19, 3, 16),
            Block.box(-4, 2, 7, 24, 4, 9),
            Block.box(0, 1, 9, 2, 5, 11),
            Block.box(0, 1, 5, 2, 5, 7),
            Block.box(14, 1, 5, 16, 5, 7),
            Block.box(14, 1, 9, 16, 5, 11),
            Block.box(0, 5, 4, 17, 6, 12),
            Block.box(23, 3, 2, 24, 11, 3),
            Block.box(24, 2, 2, 25, 4, 9),
            Block.box(23, 11, 1, 24, 12, 4),
            Block.box(6, 7, 0, 11, 9, 16),
            Block.box(11, 6, 0, 13, 8, 16),
            Block.box(13, 7, 0, 15, 9, 16),
            Block.box(15, 9, 0, 17, 13, 16),
            Block.box(17, 12, 0, 21, 16, 16),
            Block.box(10, 4, -1, 22, 12, 0),
            Block.box(10, 4, 16, 22, 12, 17),
            Block.box(17, 12, -1, 24, 15, 0),
            Block.box(17, 12, 16, 24, 15, 17)
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
