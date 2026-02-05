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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.entity.custom.Stool;

import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class LibraryStool extends ThingamajigsDecorativeBlock{
    public static final VoxelShape ALL = Stream.of(
            Block.box(1, 0, 1, 15, 3, 15),
            Block.box(2, 11, 2, 14, 12, 14),
            Block.box(2, 3, 6, 4, 11, 10),
            Block.box(12, 3, 6, 14, 11, 10),
            Block.box(6, 3, 2, 10, 11, 4),
            Block.box(6, 3, 12, 10, 11, 14),
            Block.box(2, 12, 2, 14, 15, 14),
            Block.box(3, 15, 3, 13, 16, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public LibraryStool(Properties properties) {
        super(properties.sound(SoundType.LANTERN));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return ALL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level level, BlockPos pos, Player player, BlockHitResult result) {
        try{
            double a = pos.getX() + 1.0D;
            double b = pos.getY() + 1.0D;
            double c = pos.getZ() + 1.0D;
            AABB aabb = new AABB(pos.getX(),pos.getY(),pos.getZ(),a,b,c);

            if(!level.isClientSide){
                List<Stool> chairs = level.getEntitiesOfClass(Stool.class, aabb);
                if(chairs.isEmpty()){
                    Stool ce = new Stool(level,pos,bs.getValue(FACING));
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
        l.playSound(p,bp, event, SoundSource.PLAYERS,0.5F,1.0F);
    }
}
