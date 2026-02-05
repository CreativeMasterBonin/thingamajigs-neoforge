package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.item.TItems;

import java.util.stream.Stream;

@SuppressWarnings("deprecated")
public class FireHydrant extends ThingamajigsDecorativeBlock{
    public FireHydrant(Properties p) {
        super(p.strength(1f,10f).sound(SoundType.METAL).pushReaction(PushReaction.BLOCK));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false));
    }

    public static final VoxelShape CENTER_ALL = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(6, 1, 6, 10, 11, 10),
            Block.box(7, 14, 7, 9, 16, 9),
            Block.box(5, 11, 5, 11, 12, 11),
            Block.box(6, 12, 6, 10, 14, 10)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return CENTER_ALL;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        boolean finished = false;
        ItemStack playerItem = pPlayer.getItemInHand(pPlayer.getUsedItemHand());
        if(!pLevel.isClientSide){
            if(playerItem.is(TItems.KEY.asItem())){
                Direction dir = pState.getValue(FACING);
                if(dir == Direction.NORTH){
                    if(pLevel.getBlockState(pPos.north()).is(Blocks.WATER)){
                        pLevel.setBlock(pPos.north(),Blocks.AIR.defaultBlockState(),3);
                        finished = true;
                    }
                    else if(pLevel.getBlockState(pPos.north()).is(Blocks.AIR)){
                        pLevel.setBlock(pPos.north(),Blocks.WATER.defaultBlockState(),3);
                        finished = true;
                    }
                }
                else if(dir == Direction.SOUTH){
                    if(pLevel.getBlockState(pPos.south()).is(Blocks.WATER)){
                        pLevel.setBlock(pPos.south(),Blocks.AIR.defaultBlockState(),3);
                        finished = true;
                    }
                    else if(pLevel.getBlockState(pPos.south()).is(Blocks.AIR)){
                        pLevel.setBlock(pPos.south(),Blocks.WATER.defaultBlockState(),3);
                        finished = true;
                    }
                }
                else if(dir == Direction.EAST){
                    if(pLevel.getBlockState(pPos.east()).is(Blocks.WATER)){
                        pLevel.setBlock(pPos.east(),Blocks.AIR.defaultBlockState(),3);
                        finished = true;
                    }
                    else if(pLevel.getBlockState(pPos.east()).is(Blocks.AIR)){
                        pLevel.setBlock(pPos.east(),Blocks.WATER.defaultBlockState(),3);
                        finished = true;
                    }
                }
                else if(dir == Direction.WEST){
                    if(pLevel.getBlockState(pPos.west()).is(Blocks.WATER)){
                        pLevel.setBlock(pPos.west(),Blocks.AIR.defaultBlockState(),3);
                        finished = true;
                    }
                    else if(pLevel.getBlockState(pPos.west()).is(Blocks.AIR)){
                        pLevel.setBlock(pPos.west(),Blocks.WATER.defaultBlockState(),3);
                        finished = true;
                    }
                }
            }

            if(finished){
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}
