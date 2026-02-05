package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class TriCandleHolder extends ThingamajigsDecorativeBlock{
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public static final VoxelShape NORTHSOUTH = Stream.of(
            Block.box(7, 1, 7, 9, 9, 9),
            Block.box(6, 0, 6, 10, 1, 10),
            Block.box(13, 11, 7, 15, 16, 9),
            Block.box(2, 9, 6, 14, 10, 10),
            Block.box(0, 10, 6, 4, 11, 10),
            Block.box(12, 10, 6, 16, 11, 10),
            Block.box(6, 10, 6, 10, 11, 10),
            Block.box(1, 11, 7, 3, 16, 9),
            Block.box(7, 11, 7, 9, 16, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EASTWEST = Stream.of(
            Block.box(7, 1, 7, 9, 9, 9),
            Block.box(6, 0, 6, 10, 1, 10),
            Block.box(7, 11, 1, 9, 16, 3),
            Block.box(6, 9, 2, 10, 10, 14),
            Block.box(6, 10, 12, 10, 11, 16),
            Block.box(6, 10, 0, 10, 11, 4),
            Block.box(6, 10, 6, 10, 11, 10),
            Block.box(7, 11, 13, 9, 16, 15),
            Block.box(7, 11, 7, 9, 16, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final IntegerProperty COLOR = IntegerProperty.create("color",0,15);

    public TriCandleHolder(Properties p) {
        super(p.sound(SoundType.LANTERN));
        this.registerDefaultState(this.defaultBlockState().setValue(LIT,false).setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction direction = bs.getValue(FACING);
        switch(direction){
            case NORTH, SOUTH:
                return NORTHSOUTH;
            case EAST, WEST:
                return EASTWEST;
            default: return Shapes.block();
        }
    }

    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {
        double d0 = (double)bp.getX() + 0.5D;
        double d1 = (double)bp.getY() + 1.2D;
        double d2 = (double)bp.getZ() + 0.5D;
        double d3 = (double)bp.getY() + 1.45D;

        Direction direction = bs.getValue(FACING);
        if(bs.getValue(LIT)){
            lvl.addParticle(ParticleTypes.SMOKE, d0, d3, d2, 0.0D, 0.0D, 0.0D);
            lvl.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            if(direction == Direction.NORTH || direction == Direction.SOUTH){
                lvl.addParticle(ParticleTypes.SMOKE, d0 - 0.4, d3, d2, 0.0D, 0.0D, 0.0D);
                lvl.addParticle(ParticleTypes.SMOKE, d0 + 0.4, d3, d2, 0.0D, 0.0D, 0.0D);
                lvl.addParticle(ParticleTypes.FLAME, d0 - 0.4, d1, d2, 0.0D, 0.0D, 0.0D);
                lvl.addParticle(ParticleTypes.FLAME, d0 + 0.4, d1, d2, 0.0D, 0.0D, 0.0D);
            }
            else if(direction == Direction.EAST || direction == Direction.WEST){
                lvl.addParticle(ParticleTypes.SMOKE, d0, d3, d2 - 0.4, 0.0D, 0.0D, 0.0D);
                lvl.addParticle(ParticleTypes.SMOKE, d0, d3, d2 + 0.4, 0.0D, 0.0D, 0.0D);
                lvl.addParticle(ParticleTypes.FLAME, d0, d1, d2 - 0.4, 0.0D, 0.0D, 0.0D);
                lvl.addParticle(ParticleTypes.FLAME, d0, d1, d2 + 0.4, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack is, BlockState bs, Level l, BlockPos pos, Player ply, InteractionHand h, BlockHitResult bhr) {
        if(ply.getItemInHand(h).is(Items.FLINT_AND_STEEL)){
            if(!bs.getValue(LIT)){
                l.setBlock(pos,bs.setValue(LIT,true),3);

                if(h == InteractionHand.MAIN_HAND){
                    ply.getItemInHand(h).hurtAndBreak(1,ply,EquipmentSlot.MAINHAND);
                }
                else if(h == InteractionHand.OFF_HAND){
                    ply.getItemInHand(h).hurtAndBreak(1,ply,EquipmentSlot.OFFHAND);
                }

                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT,FACING,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(LIT,false).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
