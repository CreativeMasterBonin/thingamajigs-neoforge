package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;
import net.rk.thingamajigs.entity.custom.Chair;

import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class LoveCouch extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NS = Stream.of(
            Block.box(-8, 0, 12, -4, 5, 16),
            Block.box(-8, 0, 0, -4, 5, 4),
            Block.box(20, 0, 0, 24, 5, 4),
            Block.box(20, 0, 12, 24, 5, 16),
            Block.box(8, 7, 0, 24, 11, 16),
            Block.box(-8, 7, 0, 8, 11, 16),
            Block.box(-10, 7, -1, -7, 16, 17),
            Block.box(23, 7, -1, 26, 16, 17),
            Block.box(-7, 11, 16, 23, 13, 17),
            Block.box(-7, 13, 17, 23, 15, 18),
            Block.box(-7, 15, 18, 23, 17, 19),
            Block.box(-7, 17, 19, 23, 19, 20),
            Block.box(-7, 23, 22, 23, 25, 23),
            Block.box(-7, 19, 20, 23, 21, 21),
            Block.box(-7, 21, 21, 23, 23, 22),
            Block.box(20, 5, 0, 24, 7, 16),
            Block.box(-4, 5, 0, 20, 7, 16),
            Block.box(-8, 5, 0, -4, 7, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SS = Stream.of(
            Block.box(20, 0, 0, 24, 5, 4),
            Block.box(20, 0, 12, 24, 5, 16),
            Block.box(-8, 0, 12, -4, 5, 16),
            Block.box(-8, 0, 0, -4, 5, 4),
            Block.box(-8, 7, 0, 8, 11, 16),
            Block.box(8, 7, 0, 24, 11, 16),
            Block.box(23, 7, -1, 26, 16, 17),
            Block.box(-10, 7, -1, -7, 16, 17),
            Block.box(-7, 11, -1, 23, 13, 0),
            Block.box(-7, 13, -2, 23, 15, -1),
            Block.box(-7, 15, -3, 23, 17, -2),
            Block.box(-7, 17, -4, 23, 19, -3),
            Block.box(-7, 23, -7, 23, 25, -6),
            Block.box(-7, 19, -5, 23, 21, -4),
            Block.box(-7, 21, -6, 23, 23, -5),
            Block.box(-8, 5, 0, -4, 7, 16),
            Block.box(-4, 5, 0, 20, 7, 16),
            Block.box(20, 5, 0, 24, 7, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape ES = Stream.of(
            Block.box(0, 0, -8, 4, 5, -4),
            Block.box(12, 0, -8, 16, 5, -4),
            Block.box(12, 0, 20, 16, 5, 24),
            Block.box(0, 0, 20, 4, 5, 24),
            Block.box(0, 7, 8, 16, 11, 24),
            Block.box(0, 7, -8, 16, 11, 8),
            Block.box(-1, 7, -10, 17, 16, -7),
            Block.box(-1, 7, 23, 17, 16, 26),
            Block.box(-1, 11, -7, 0, 13, 23),
            Block.box(-2, 13, -7, -1, 15, 23),
            Block.box(-3, 15, -7, -2, 17, 23),
            Block.box(-4, 17, -7, -3, 19, 23),
            Block.box(-7, 23, -7, -6, 25, 23),
            Block.box(-5, 19, -7, -4, 21, 23),
            Block.box(-6, 21, -7, -5, 23, 23),
            Block.box(0, 5, 20, 16, 7, 24),
            Block.box(0, 5, -4, 16, 7, 20),
            Block.box(0, 5, -8, 16, 7, -4)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WS = Stream.of(
            Block.box(12, 0, 20, 16, 5, 24),
            Block.box(0, 0, 20, 4, 5, 24),
            Block.box(0, 0, -8, 4, 5, -4),
            Block.box(12, 0, -8, 16, 5, -4),
            Block.box(0, 7, -8, 16, 11, 8),
            Block.box(0, 7, 8, 16, 11, 24),
            Block.box(-1, 7, 23, 17, 16, 26),
            Block.box(-1, 7, -10, 17, 16, -7),
            Block.box(16, 11, -7, 17, 13, 23),
            Block.box(17, 13, -7, 18, 15, 23),
            Block.box(18, 15, -7, 19, 17, 23),
            Block.box(19, 17, -7, 20, 19, 23),
            Block.box(22, 23, -7, 23, 25, 23),
            Block.box(20, 19, -7, 21, 21, 23),
            Block.box(21, 21, -7, 22, 23, 23),
            Block.box(0, 5, -8, 16, 7, -4),
            Block.box(0, 5, -4, 16, 7, 20),
            Block.box(0, 5, 20, 16, 7, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTH_MYSTERIOUS = Stream.of(
            Block.box(8, 4, 0, 24, 8, 16),
            Block.box(-8, 4, 0, 8, 8, 16),
            Block.box(-8, 0, 0, -6, 4, 2),
            Block.box(22, 0, 0, 24, 4, 2),
            Block.box(22, 0, 14, 24, 4, 16),
            Block.box(-8, 0, 14, -6, 4, 16),
            Block.box(-12, 8, 0, -8, 12, 14),
            Block.box(24, 8, 0, 28, 12, 14),
            Block.box(-8, 7, 14, 24, 10, 18),
            Block.box(-8, 10, 15, 24, 13, 19),
            Block.box(-8, 13, 16, 24, 16, 20),
            Block.box(-8, 16, 17, 24, 19, 21),
            Block.box(-8, 19, 18, 24, 22, 22),
            Block.box(-8, 22, 19, 24, 23, 23)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_MYSTERIOUS = Stream.of(
            Block.box(0, 4, 8, 16, 8, 24),
            Block.box(0, 4, -8, 16, 8, 8),
            Block.box(14, 0, -8, 16, 4, -6),
            Block.box(14, 0, 22, 16, 4, 24),
            Block.box(0, 0, 22, 2, 4, 24),
            Block.box(0, 0, -8, 2, 4, -6),
            Block.box(2, 8, -12, 16, 12, -8),
            Block.box(2, 8, 24, 16, 12, 28),
            Block.box(-2, 7, -8, 2, 10, 24),
            Block.box(-3, 10, -8, 1, 13, 24),
            Block.box(-4, 13, -8, 0, 16, 24),
            Block.box(-5, 16, -8, -1, 19, 24),
            Block.box(-6, 19, -8, -2, 22, 24),
            Block.box(-7, 22, -8, -3, 23, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_MYSTERIOUS = Stream.of(
            Block.box(-8, 4, 0, 8, 8, 16),
            Block.box(8, 4, 0, 24, 8, 16),
            Block.box(22, 0, 14, 24, 4, 16),
            Block.box(-8, 0, 14, -6, 4, 16),
            Block.box(-8, 0, 0, -6, 4, 2),
            Block.box(22, 0, 0, 24, 4, 2),
            Block.box(24, 8, 2, 28, 12, 16),
            Block.box(-12, 8, 2, -8, 12, 16),
            Block.box(-8, 7, -2, 24, 10, 2),
            Block.box(-8, 10, -3, 24, 13, 1),
            Block.box(-8, 13, -4, 24, 16, 0),
            Block.box(-8, 16, -5, 24, 19, -1),
            Block.box(-8, 19, -6, 24, 22, -2),
            Block.box(-8, 22, -7, 24, 23, -3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_MYSTERIOUS = Stream.of(
            Block.box(0, 4, -8, 16, 8, 8),
            Block.box(0, 4, 8, 16, 8, 24),
            Block.box(0, 0, 22, 2, 4, 24),
            Block.box(0, 0, -8, 2, 4, -6),
            Block.box(14, 0, -8, 16, 4, -6),
            Block.box(14, 0, 22, 16, 4, 24),
            Block.box(0, 8, 24, 14, 12, 28),
            Block.box(0, 8, -12, 14, 12, -8),
            Block.box(14, 7, -8, 18, 10, 24),
            Block.box(15, 10, -8, 19, 13, 24),
            Block.box(16, 13, -8, 20, 16, 24),
            Block.box(17, 16, -8, 21, 19, 24),
            Block.box(18, 19, -8, 22, 22, 24),
            Block.box(19, 22, -8, 23, 23, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public LoveCouch(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = state.getValue(FACING);
        if(state.is(TBlocks.MYSTERIOUS_ONE_COUCH.get())){
            switch(direction){
                case NORTH: return NORTH_MYSTERIOUS;
                case SOUTH: return SOUTH_MYSTERIOUS;
                case EAST: return EAST_MYSTERIOUS;
                case WEST: return WEST_MYSTERIOUS;
                default: return Shapes.block();
            }
        }
        switch(direction){
            case NORTH: return NS;
            case SOUTH: return SS;
            case EAST: return ES;
            case WEST: return WS;
            default: return Shapes.block();
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level level, BlockPos pos, Player player, BlockHitResult bhr) {
        try{
            double a = pos.getX() + 1.0D;
            double b = pos.getY() + 1.0D;
            double c = pos.getZ() + 1.0D;
            AABB aabb = new AABB(pos.getX(),pos.getY(),pos.getZ(),a,b,c);

            if(!level.isClientSide()){
                List<Chair> chairs = level.getEntitiesOfClass(Chair.class, aabb);
                if(chairs.isEmpty()){
                    Chair ce = new Chair(level,pos,bs.getValue(FACING));
                    level.addFreshEntity(ce);
                    player.startRiding(ce,false);
                }
            }
            playCustomSitSound(level,pos,player);
        }
        catch(Exception e){
            return InteractionResult.FAIL;
        }
        return InteractionResult.SUCCESS;
    }

    public void playCustomSitSound(Level l, BlockPos bp, Player p){
        SoundEvent event = SoundEvents.WOOL_PLACE;
        l.playSound(p,bp, event, SoundSource.PLAYERS,1.0F,1.0F);
    }
}
