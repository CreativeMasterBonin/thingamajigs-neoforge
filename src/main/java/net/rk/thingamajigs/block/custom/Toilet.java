package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.stream.Stream;

public class Toilet extends ToggledStateBlock{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static BooleanProperty TOGGLED = BlockStateProperties.ENABLED;

    //toilet shapes
    public static final VoxelShape NORTH_SHAPE = Stream.of(
            Block.box(4, 5, 2, 6, 6, 10),
            Block.box(6, 0, 6, 10, 6, 10),
            Block.box(3, 6, 1, 13, 7, 10),
            Block.box(4, 7, 0, 12, 9, 1),
            Block.box(3, 7, 0, 4, 9, 10),
            Block.box(12, 7, 0, 13, 9, 10),
            Block.box(11, 13, 9, 13, 14, 10),
            Block.box(7, 7.05, 7, 9, 7.05, 9),
            Block.box(3, 6, 10, 13, 16, 14),
            Block.box(3, 16, 10, 13, 17, 14),
            Block.box(4, 5, 10, 12, 6, 12),
            Block.box(6, 5, 2, 10, 6, 6),
            Block.box(10, 5, 2, 12, 6, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_SHAPE = Stream.of(
            Block.box(10, 5, 6, 12, 6, 14),
            Block.box(6, 0, 6, 10, 6, 10),
            Block.box(3, 6, 6, 13, 7, 15),
            Block.box(4, 7, 15, 12, 9, 16),
            Block.box(12, 7, 6, 13, 9, 16),
            Block.box(3, 7, 6, 4, 9, 16),
            Block.box(3, 13, 6, 5, 14, 7),
            Block.box(7, 7.05, 7, 9, 7.05, 9),
            Block.box(3, 6, 2, 13, 16, 6),
            Block.box(3, 16, 2, 13, 17, 6),
            Block.box(4, 5, 4, 12, 6, 6),
            Block.box(6, 5, 10, 10, 6, 14),
            Block.box(4, 5, 6, 6, 6, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_SHAPE = Stream.of(
            Block.box(6, 5, 4, 14, 6, 6),
            Block.box(6, 0, 6, 10, 6, 10),
            Block.box(6, 6, 3, 15, 7, 13),
            Block.box(15, 7, 4, 16, 9, 12),
            Block.box(6, 7, 3, 16, 9, 4),
            Block.box(6, 7, 12, 16, 9, 13),
            Block.box(6, 13, 11, 7, 14, 13),
            Block.box(7, 7.05, 7, 9, 7.05, 9),
            Block.box(2, 6, 3, 6, 16, 13),
            Block.box(2, 16, 3, 6, 17, 13),
            Block.box(4, 5, 4, 6, 6, 12),
            Block.box(10, 5, 6, 14, 6, 10),
            Block.box(6, 5, 10, 14, 6, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_SHAPE = Stream.of(
            Block.box(2, 5, 10, 10, 6, 12),
            Block.box(6, 0, 6, 10, 6, 10),
            Block.box(1, 6, 3, 10, 7, 13),
            Block.box(0, 7, 4, 1, 9, 12),
            Block.box(0, 7, 12, 10, 9, 13),
            Block.box(0, 7, 3, 10, 9, 4),
            Block.box(9, 13, 3, 10, 14, 5),
            Block.box(7, 7.05, 7, 9, 7.05, 9),
            Block.box(10, 6, 3, 14, 16, 13),
            Block.box(10, 16, 3, 14, 17, 13),
            Block.box(10, 5, 4, 12, 6, 12),
            Block.box(2, 5, 6, 6, 6, 10),
            Block.box(2, 5, 4, 10, 6, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public Toilet(Properties p) {
        super(p.sound(SoundType.STONE).strength(1F,10F).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TOGGLED, false).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_SHAPE;
            case SOUTH: return SOUTH_SHAPE;
            case EAST: return EAST_SHAPE;
            case WEST: return WEST_SHAPE;
            default: return Shapes.block();
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING,TOGGLED,WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(TOGGLED,false).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    // be use

    public SoundEvent SIT_SOUND = SoundEvents.DECORATED_POT_STEP;

    @Override
    protected InteractionResult useWithoutItem(BlockState bs, Level level, BlockPos pos, Player player, BlockHitResult result) {
        try{
            double a = pos.getX() + 1.0D;
            double b = pos.getY() + 1.0D;
            double c = pos.getZ() + 1.0D;
            AABB aabb = new AABB(pos.getX(),pos.getY(),pos.getZ(),a,b,c);

            if(!player.isShiftKeyDown()){
                if(!level.isClientSide){
                    //
                    List<net.rk.thingamajigs.entity.custom.Toilet> toilets = level.getEntitiesOfClass(net.rk.thingamajigs.entity.custom.Toilet.class, aabb);
                    if(toilets.isEmpty()){
                        net.rk.thingamajigs.entity.custom.Toilet te = new net.rk.thingamajigs.entity.custom.Toilet(level,pos,bs.getValue(FACING));
                        level.addFreshEntity(te);
                        player.startRiding(te,false);
                    }
                    playCustomSitSound(level,pos,player); // custom sitting sounds!
                }
            }
            else{
                if(level.isClientSide){
                    BlockState bs2 = bs.cycle(TOGGLED);
                    level.playLocalSound(pos,SoundEvents.BUCKET_FILL, SoundSource.PLAYERS,0.5F,1.0F,false);
                    return InteractionResult.SUCCESS;
                }
                else{
                    BlockState blockstate = this.pull(bs, level, pos);
                    return InteractionResult.CONSUME;
                }
            }
        }
        catch(Exception e){
            return InteractionResult.FAIL;
        }
        return InteractionResult.SUCCESS;
    }

    public void playCustomSitSound(Level l, BlockPos bp, Player p){
        SoundEvent event = SIT_SOUND;
        l.playSound(p,bp, event, SoundSource.PLAYERS,1.0F,1.0F);
    }
}
