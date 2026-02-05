package net.rk.thingamajigs.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

@SuppressWarnings("deprecated,unused")
public class ConnectedTableBlock extends Block implements SimpleWaterloggedBlock{
    public static final MapCodec<ConnectedTableBlock> CTB_CODEC = Block.simpleCodec(ConnectedTableBlock::new);
    @Override
    public MapCodec<ConnectedTableBlock> codec() {return CTB_CODEC;}

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    //public static final BooleanProperty UP = BooleanProperty.create("up");
    //public static final BooleanProperty DOWN = BooleanProperty.create("down");

    // shapes
    public static final VoxelShape TOP = Block.box(0,14,0,16,16, 16);


    public static final VoxelShape NORTH_CORNER =
            Shapes.join(Block.box(0, 14, 0, 16, 16, 16),
                    Block.box(0, 0, 0, 2, 14, 2), BooleanOp.OR);
    public static final VoxelShape EAST_CORNER =
            Shapes.join(Block.box(14, 0, 0, 16, 14, 2),
                    Block.box(0, 14, 0, 16, 16, 16), BooleanOp.OR);
    public static final VoxelShape SOUTH_CORNER =
            Shapes.join(Block.box(14, 0, 14, 16, 14, 16),
                    Block.box(0, 14, 0, 16, 16, 16), BooleanOp.OR);
    public static final VoxelShape WEST_CORNER = Shapes.join(Block.box(0, 0, 14, 2, 14, 16),
            Block.box(0, 14, 0, 16, 16, 16), BooleanOp.OR);


    /*
        ->W
    */
    public static final VoxelShape NORTH_EDGE = Stream.of(
            Block.box(0, 0, 0, 2, 14, 2),
            Block.box(0, 0, 14, 2, 14, 16),
            Block.box(0, 14, 0, 16, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    /*
        d
        N
    */
    public static final VoxelShape EAST_EDGE = Stream.of(
            Block.box(14, 0, 0, 16, 14, 2),
            Block.box(0, 0, 0, 2, 14, 2),
            Block.box(0, 14, 0, 16, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    /*
       E<-
    */
    public static final VoxelShape SOUTH_EDGE = Stream.of(
            Block.box(14, 0, 14, 16, 14, 16),
            Block.box(14, 0, 0, 16, 14, 2),
            Block.box(0, 14, 0, 16, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    /*  S
        ^
    */
    public static final VoxelShape WEST_EDGE = Stream.of(
            Block.box(0, 0, 14, 2, 14, 16),
            Block.box(14, 0, 14, 16, 14, 16),
            Block.box(0, 14, 0, 16, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_SOUTH_EDGE = Shapes.join(NORTH_EDGE,SOUTH_EDGE,BooleanOp.OR);
    public static final VoxelShape EAST_WEST_EDGE = Shapes.join(EAST_EDGE,WEST_EDGE,BooleanOp.OR);

    public ConnectedTableBlock(Properties p) {
        super(p.noOcclusion());
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(NORTH,false)
                .setValue(SOUTH,false)
                .setValue(EAST,false)
                .setValue(WEST,false)
                .setValue(WATERLOGGED, false));
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        boolean n = bs.getValue(NORTH);
        boolean e = bs.getValue(EAST);
        boolean s = bs.getValue(SOUTH);
        boolean w = bs.getValue(WEST);

        // none
        if(!n && !e && !s && !w){
            return Table.ALL;
        }
        else{
            if(n && !e && !s && !w){
                return WEST_EDGE; // 270deg -> 2 legs
            }
            else if(!n && e && !s && !w){
                return NORTH_EDGE; // 0deg -> 2 legs
            }
            else if(!n && !e && s && !w){
                return EAST_EDGE; // 90deg -> 2 legs
            }
            else if(!n && !e && !s && w){
                return SOUTH_EDGE; // 180deg -> 2 legs
            }
            else{
                if(n && e && !s && !w){
                    return WEST_CORNER;
                }
                else if(!n && e && s && !w){
                    return NORTH_CORNER;
                }
                else if(!n && !e && s && w){
                    return EAST_CORNER;
                }
                else if(n && !e && !s && w){
                    return SOUTH_CORNER;
                }
                else{
                    return TOP; // all other cases are middle pieces
                }
            }
        }
    }

    @Override
    public BlockState updateShape(BlockState bs, Direction d, BlockState bs2, LevelAccessor la, BlockPos bp, BlockPos bp2) {
        boolean n = la.getBlockState(bp.north()).getBlock() == this;
        boolean s = la.getBlockState(bp.south()).getBlock() == this;
        boolean e = la.getBlockState(bp.east()).getBlock() == this;
        boolean w = la.getBlockState(bp.west()).getBlock() == this;

        //boolean u = la.getBlockState(bp.above()).getBlock() == this;
        //boolean d = la.getBlockState(bp.below()).getBlock() == this;

        return bs.setValue(NORTH,n).setValue(SOUTH,s).setValue(EAST,e).setValue(WEST,w);
    }

    @Override
    public FluidState getFluidState(BlockState bs) {
        return bs.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(bs);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        FluidState fluidState = bpc.getLevel().getFluidState(bpc.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH,SOUTH,EAST,WEST,WATERLOGGED);
    }
}
