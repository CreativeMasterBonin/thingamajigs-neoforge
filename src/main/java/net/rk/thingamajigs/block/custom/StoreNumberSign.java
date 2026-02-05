package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
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

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class StoreNumberSign extends Block{
    public static IntegerProperty TYPE = IntegerProperty.create("type",0,9);
    public static VoxelShape ALL = Stream.of(
            Block.box(4, 24, 4, 12, 32, 12),
            Block.box(6, 0, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 24, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public StoreNumberSign(Properties properties) {
        super(properties.strength(1F,2F));
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return ALL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult bhr) {
        if (!pLevel.isClientSide){
            if(pPlayer.getUsedItemHand() == InteractionHand.MAIN_HAND && pPlayer.isShiftKeyDown()){
                int type = pState.getValue(TYPE);
                if(type >= 9){
                    type = 0;
                }
                else{
                    type++;
                }
                pLevel.setBlock(pPos, pState.setValue(TYPE, type), 0);
                return InteractionResult.SUCCESS;
            }
            else{
                return InteractionResult.CONSUME;
            }
        }
        else{
            return InteractionResult.CONSUME;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(TYPE, 0);
    }
}
