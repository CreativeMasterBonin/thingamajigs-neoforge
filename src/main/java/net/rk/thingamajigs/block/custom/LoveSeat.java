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
import net.rk.thingamajigs.entity.custom.Chair;

import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class LoveSeat extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NOR = Stream.of(
            Block.box(12, 5, 0, 16, 7, 16),
            Block.box(4, 5, 0, 12, 7, 16),
            Block.box(0, 5, 0, 4, 7, 16),
            Block.box(0, 0, 12, 4, 5, 16),
            Block.box(0, 0, 0, 4, 5, 4),
            Block.box(12, 0, 0, 16, 5, 4),
            Block.box(12, 0, 12, 16, 5, 16),
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(-2, 7, -1, 1, 16, 17),
            Block.box(15, 7, -1, 18, 16, 17),
            Block.box(-1, 11, 17, 17, 13, 18),
            Block.box(-1, 13, 18, 17, 15, 19),
            Block.box(-1, 15, 19, 17, 17, 20),
            Block.box(-1, 17, 20, 17, 19, 21),
            Block.box(-1, 19, 21, 17, 21, 22),
            Block.box(-1, 21, 22, 17, 23, 23),
            Block.box(-1, 23, 23, 17, 25, 24)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOU = Stream.of(
            Block.box(0, 5, 0, 4, 7, 16),
            Block.box(4, 5, 0, 12, 7, 16),
            Block.box(12, 5, 0, 16, 7, 16),
            Block.box(12, 0, 0, 16, 5, 4),
            Block.box(12, 0, 12, 16, 5, 16),
            Block.box(0, 0, 12, 4, 5, 16),
            Block.box(0, 0, 0, 4, 5, 4),
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(15, 7, -1, 18, 16, 17),
            Block.box(-2, 7, -1, 1, 16, 17),
            Block.box(-1, 11, -2, 17, 13, -1),
            Block.box(-1, 13, -3, 17, 15, -2),
            Block.box(-1, 15, -4, 17, 17, -3),
            Block.box(-1, 17, -5, 17, 19, -4),
            Block.box(-1, 19, -6, 17, 21, -5),
            Block.box(-1, 21, -7, 17, 23, -6),
            Block.box(-1, 23, -8, 17, 25, -7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAS = Stream.of(
            Block.box(0, 5, 12, 16, 7, 16),
            Block.box(0, 5, 4, 16, 7, 12),
            Block.box(0, 5, 0, 16, 7, 4),
            Block.box(0, 0, 0, 4, 5, 4),
            Block.box(12, 0, 0, 16, 5, 4),
            Block.box(12, 0, 12, 16, 5, 16),
            Block.box(0, 0, 12, 4, 5, 16),
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(-1, 7, -2, 17, 16, 1),
            Block.box(-1, 7, 15, 17, 16, 18),
            Block.box(-2, 11, -1, -1, 13, 17),
            Block.box(-3, 13, -1, -2, 15, 17),
            Block.box(-4, 15, -1, -3, 17, 17),
            Block.box(-5, 17, -1, -4, 19, 17),
            Block.box(-6, 19, -1, -5, 21, 17),
            Block.box(-7, 21, -1, -6, 23, 17),
            Block.box(-8, 23, -1, -7, 25, 17)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WES = Stream.of(
            Block.box(0, 5, 0, 16, 7, 4),
            Block.box(0, 5, 4, 16, 7, 12),
            Block.box(0, 5, 12, 16, 7, 16),
            Block.box(12, 0, 12, 16, 5, 16),
            Block.box(0, 0, 12, 4, 5, 16),
            Block.box(0, 0, 0, 4, 5, 4),
            Block.box(12, 0, 0, 16, 5, 4),
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(-1, 7, 15, 17, 16, 18),
            Block.box(-1, 7, -2, 17, 16, 1),
            Block.box(17, 11, -1, 18, 13, 17),
            Block.box(18, 13, -1, 19, 15, 17),
            Block.box(19, 15, -1, 20, 17, 17),
            Block.box(20, 17, -1, 21, 19, 17),
            Block.box(21, 19, -1, 22, 21, 17),
            Block.box(22, 21, -1, 23, 23, 17),
            Block.box(23, 23, -1, 24, 25, 17)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public LoveSeat(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NOR;
            case SOUTH: return SOU;
            case EAST: return EAS;
            case WEST: return WES;
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

            if(!level.isClientSide){
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
