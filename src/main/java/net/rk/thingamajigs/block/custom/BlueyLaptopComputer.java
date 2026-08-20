package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.xtras.TCalcStuff;

import java.util.stream.Stream;

public class BlueyLaptopComputer extends ThingamajigsDecorativeBlock {
    public static final IntegerProperty VERSION = IntegerProperty.create("version",0,2);

    public static final VoxelShape NORTH = Stream.of(
            Block.box(0, 1, 12, 16, 3, 13),
            Block.box(0, 3, 13, 16, 5, 14),
            Block.box(0, 5, 14, 16, 7, 15),
            Block.box(0, 7, 15, 16, 9, 16),
            Block.box(0, 9, 16, 16, 12, 17),
            Block.box(0, 0, 1, 16, 1, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(3, 1, 0, 4, 3, 16),
            Block.box(2, 3, 0, 3, 5, 16),
            Block.box(1, 5, 0, 2, 7, 16),
            Block.box(0, 7, 0, 1, 9, 16),
            Block.box(-1, 9, 0, 0, 12, 16),
            Block.box(4, 0, 0, 15, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(0, 1, 3, 16, 3, 4),
            Block.box(0, 3, 2, 16, 5, 3),
            Block.box(0, 5, 1, 16, 7, 2),
            Block.box(0, 7, 0, 16, 9, 1),
            Block.box(0, 9, -1, 16, 12, 0),
            Block.box(0, 0, 4, 16, 1, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(12, 1, 0, 13, 3, 16),
            Block.box(13, 3, 0, 14, 5, 16),
            Block.box(14, 5, 0, 15, 7, 16),
            Block.box(15, 7, 0, 16, 9, 16),
            Block.box(16, 9, 0, 17, 12, 16),
            Block.box(1, 0, 0, 12, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public BlueyLaptopComputer(Properties p){
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)){
            case NORTH -> {
                return NORTH;
            }
            case SOUTH -> {
                return SOUTH;
            }
            case EAST -> {
                return EAST;
            }
            case WEST -> {
                return WEST;
            }
            default -> {
                return Shapes.block();
            }
        }
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VERSION);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(level.isClientSide()){
            player.playSound(SoundEvents.CAKE_ADD_CANDLE,1f, TCalcStuff.nextFloatBetweenInclusive(0.98f,1.05f));
            return InteractionResult.SUCCESS;
        }
        else {
            level.setBlock(pos,state.cycle(VERSION),3);
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }
    }
}
