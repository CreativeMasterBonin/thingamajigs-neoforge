package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.TBlocks;

import java.util.logging.Logger;

@SuppressWarnings("deprecated")
public class ConveyorBelt extends ThingamajigsDecorativeBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final VoxelShape ALL = Block.box(0,0,0,16,4,16);

    public ConveyorBelt(Properties p) {
        super(p.strength(1f,5f).sound(SoundType.LANTERN));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    // only forward instead of up and forward
    @Override
    public void stepOn(Level lvl, BlockPos bp, BlockState bs, Entity e) {
        double x = 0.0D;
        double y = 0.0D;
        double z = 0.0D;

        switch(bs.getValue(FACING)){
            case NORTH -> z = 0.25D;
            case SOUTH -> z = -0.25D;
            case EAST -> x = -0.25D;
            case WEST -> x = 0.25D;
        }

        if(e instanceof Player){
            e.setDeltaMovement(x,y,z);
        }
        else{
            try{
                if(e instanceof ExperienceOrb){
                    e.setDeltaMovement(x,y,z);
                }
                else if (e instanceof Monster) {
                    e.setDeltaMovement(x,y,z);
                }
                else if (e instanceof ItemEntity) {
                    e.setDeltaMovement(x,y,z);
                }
            }
            catch (Exception exc){
                Logger.getAnonymousLogger().warning("Couldn't move an entity. ERR: " + exc);
            }
        }
    }

    @Override
    public VoxelShape getInteractionShape(BlockState bs, BlockGetter bg, BlockPos bp) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        if(cc.isHoldingItem(TBlocks.CONVEYOR_BELT.get().asItem())){
            return Shapes.block();
        }
        else{
            return super.getVisualShape(bs,bg,bp,cc);
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
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }
}
