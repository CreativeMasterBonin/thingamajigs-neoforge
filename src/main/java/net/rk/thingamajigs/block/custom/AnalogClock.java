package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;

@SuppressWarnings("deprecated")
public class AnalogClock extends ThingamajigsDecorativeBlock{
    public static final IntegerProperty TIME = IntegerProperty.create("time",0,7);

    public static final VoxelShape NORTH = Block.box(0, 0, 14, 16, 16, 16);
    public static final VoxelShape EAST = Block.box(0, 0, 0, 2, 16, 16);
    public static final VoxelShape SOUTH = Block.box(0, 0, 0, 16, 16, 2);
    public static final VoxelShape WEST = Block.box(14, 0, 0, 16, 16, 16);


    public AnalogClock(Properties p) {
        super(p.strength(1f,2f).sound(SoundType.CALCITE).noCollission());
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(TIME,0));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction d = bs.getValue(FACING);
        switch(d){
            case NORTH: return NORTH;
            case SOUTH: return SOUTH;
            case EAST: return EAST;
            case WEST: return WEST;
            default: return Shapes.block();
        }
    }

    @Override
    public void onPlace(BlockState bs, Level lvl, BlockPos bp, BlockState bs2, boolean b1) {
        lvl.scheduleTick(bp,this,15, TickPriority.LOW);
    }

    @Override
    public void tick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        try{
            long l1 = sl.getDayTime(); // get the current time in long format
            int time1 = (Integer.parseInt(Long.toString(l1)) + 6000) % 24000; // convert time to integer from string
            int nTime1 = time1 / 1000;

            if(nTime1 < 0){
                nTime1 = 0;
            }

            if(nTime1 > 24){
                nTime1 = 24;
            }

            // logic for time setting specifics, sorry no switches for you
            if(nTime1 == 24 || nTime1 == 0 || nTime1 == 1 || nTime1 == 2){
                sl.setBlock(bp,bs.setValue(TIME,0),3);
            }
            else if(nTime1 == 3 || nTime1 == 4 || nTime1 == 5){
                sl.setBlock(bp,bs.setValue(TIME,1),3);
            }
            else if(nTime1 == 6 || nTime1 == 7 || nTime1 == 8){
                sl.setBlock(bp,bs.setValue(TIME,2),3);
            }
            else if(nTime1 == 9 || nTime1 == 10 || nTime1 == 11){
                sl.setBlock(bp,bs.setValue(TIME,3),3);
            }
            else if(nTime1 == 7 || nTime1 == 8 || nTime1 == 9){
                sl.setBlock(bp,bs.setValue(TIME,4),3);
            }
            else if(nTime1 == 10 || nTime1 == 11 || nTime1 == 12){
                sl.setBlock(bp,bs.setValue(TIME,5),3);
            }
            else if(nTime1 == 13 || nTime1 == 14 || nTime1 == 15){
                sl.setBlock(bp,bs.setValue(TIME,6),3);
            }
            else if(nTime1 == 16 || nTime1 == 17 || nTime1 == 18){
                sl.setBlock(bp,bs.setValue(TIME,7),3);
            }
            else if(nTime1 == 19 || nTime1 == 20 || nTime1 == 21 || nTime1 == 22 || nTime1 == 23){
                sl.setBlock(bp,bs.setValue(TIME,7),3);
            }

        }
        catch (Exception exc){
            //
        }
        sl.scheduleTick(bp,this,80,TickPriority.LOW); // repetitive ticks
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED,TIME);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
                .setValue(TIME,0);
    }
}
