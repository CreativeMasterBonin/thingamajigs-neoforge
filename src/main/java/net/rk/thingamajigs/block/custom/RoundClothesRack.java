package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class RoundClothesRack extends ThingamajigsDecorativeBlock{
    public static final VoxelShape OLD_ALL = Stream.of(
            Block.box(7, 3, 7, 9, 19, 9),
            Block.box(6, 1, 6, 10, 3, 10),
            Block.box(5, 0, 5, 7, 1, 7),
            Block.box(9, 0, 5, 11, 1, 7),
            Block.box(9, 0, 9, 11, 1, 11),
            Block.box(5, 0, 9, 7, 1, 11),
            Block.box(0, 16.04, -2, 16, 18.04, 0),
            Block.box(0, 16.04, 16, 16, 18.04, 18),
            Block.box(16, 16.04, 0, 18, 18.04, 16),
            Block.box(-2, 16.04, 0, 0, 18.04, 16),
            Block.box(0, 17, 0, 3, 18, 3),
            Block.box(0, 17, 13, 3, 18, 16),
            Block.box(13, 17, 13, 16, 18, 16),
            Block.box(13, 17, 0, 16, 18, 3),
            Block.box(3, 17, 3, 7, 18, 7),
            Block.box(3, 17, 9, 7, 18, 13),
            Block.box(9, 17, 9, 13, 18, 13),
            Block.box(9, 17, 3, 13, 18, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape ALL = Stream.of(
            Block.box(5, 21, 5, 7, 22, 7),
            Block.box(5, 21, 9, 7, 22, 11),
            Block.box(9, 21, 9, 11, 22, 11),
            Block.box(9, 21, 5, 11, 22, 7),
            Block.box(11, 21, 3, 13, 22, 5),
            Block.box(11, 21, 11, 13, 22, 13),
            Block.box(3, 21, 11, 5, 22, 13),
            Block.box(3, 21, 3, 5, 22, 5),
            Block.box(13, 21, 0, 16, 22, 3),
            Block.box(13, 21, 13, 16, 22, 16),
            Block.box(0, 21, 13, 3, 22, 16),
            Block.box(0, 21, 0, 3, 22, 3),
            Block.box(7, 3, 7, 9, 23, 9),
            Block.box(6, 1, 6, 10, 3, 10),
            Block.box(5, 0, 5, 7, 1, 7),
            Block.box(9, 0, 5, 11, 1, 7),
            Block.box(9, 0, 9, 11, 1, 11),
            Block.box(5, 0, 9, 7, 1, 11),
            Block.box(0, 20.04, -2, 16, 22.04, 0),
            Block.box(0, 20.04, 16, 16, 22.04, 18),
            Block.box(16, 20.04, 0, 18, 22.04, 16),
            Block.box(-2, 20.04, 0, 0, 22.04, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static BooleanProperty TOGGLED = BlockStateProperties.ENABLED;

    public RoundClothesRack(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter getter, BlockPos blockPos, CollisionContext context) {
        return ALL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TOGGLED);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return use(state,level,pos,player,player.getUsedItemHand(),hitResult);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult bhr) {
        if(level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        else{
            if(player.getItemInHand(hand).isEmpty() && player.isShiftKeyDown()){
                level.setBlock(pos,state.cycle(TOGGLED),3);
                level.playSound(null,pos, SoundEvents.WOOL_HIT, SoundSource.BLOCKS,1.0f,1.0f);
                player.swing(hand);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }
}
