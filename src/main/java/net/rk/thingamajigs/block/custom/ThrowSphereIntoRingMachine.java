package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class ThrowSphereIntoRingMachine extends ThingamajigsDecorativeBlock{
    public static EnumProperty<MachineEnding> MACHINE_ENDING = EnumProperty.create("machine_ending",MachineEnding.class);

    public enum MachineEnding implements StringRepresentable {
        PAYMENT_SECTION("payment_section"),
        GOAL_SECTION("goal_section");

        private String endName;

        MachineEnding(String name){
            this.endName = name;
        }

        @Override
        public String toString() {
            return this.endName;
        }

        @Override
        public String getSerializedName() {
            return this.endName;
        }
    }

    public static final VoxelShape NORTH_BACK = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(0, 0, 14, 4, 7, 16),
            Block.box(12, 0, 14, 16, 7, 16),
            Block.box(14, 11, 0, 16, 13, 16),
            Block.box(0, 11, 0, 2, 13, 16),
            Block.box(0, 12, 17, 16, 13, 26),
            Block.box(4, 24, 13, 12, 29, 14),
            Block.box(2, 11, 11, 14, 12.5, 14),
            Block.box(2, 6, 4, 14, 7, 13),
            Block.box(0, 13, 13, 16, 26, 28)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_BACK = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(0, 0, 0, 2, 7, 4),
            Block.box(0, 0, 12, 2, 7, 16),
            Block.box(0, 11, 14, 16, 13, 16),
            Block.box(0, 11, 0, 16, 13, 2),
            Block.box(-10, 12, 0, -1, 13, 16),
            Block.box(2, 24, 4, 3, 29, 12),
            Block.box(2, 11, 2, 5, 12.5, 14),
            Block.box(3, 6, 2, 12, 7, 14),
            Block.box(-12, 13, 0, 3, 26, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_BACK = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(12, 0, 0, 16, 7, 2),
            Block.box(0, 0, 0, 4, 7, 2),
            Block.box(0, 11, 0, 2, 13, 16),
            Block.box(14, 11, 0, 16, 13, 16),
            Block.box(0, 12, -10, 16, 13, -1),
            Block.box(4, 24, 2, 12, 29, 3),
            Block.box(2, 11, 2, 14, 12.5, 5),
            Block.box(2, 6, 3, 14, 7, 12),
            Block.box(0, 13, -12, 16, 26, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_BACK = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(14, 0, 12, 16, 7, 16),
            Block.box(14, 0, 0, 16, 7, 4),
            Block.box(0, 11, 0, 16, 13, 2),
            Block.box(0, 11, 14, 16, 13, 16),
            Block.box(17, 12, 0, 26, 13, 16),
            Block.box(13, 24, 4, 14, 29, 12),
            Block.box(11, 11, 2, 14, 12.5, 14),
            Block.box(4, 6, 2, 13, 7, 14),
            Block.box(13, 13, 0, 28, 26, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape NORTH_FRONT = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(0, 0, 0, 4, 7, 2),
            Block.box(12, 0, 0, 16, 7, 2),
            Block.box(0, 11, 0, 2, 13, 16),
            Block.box(14, 11, 0, 16, 13, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_FRONT = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(14, 0, 0, 16, 7, 4),
            Block.box(14, 0, 12, 16, 7, 16),
            Block.box(0, 11, 0, 16, 13, 2),
            Block.box(0, 11, 14, 16, 13, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_FRONT = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(12, 0, 14, 16, 7, 16),
            Block.box(0, 0, 14, 4, 7, 16),
            Block.box(14, 11, 0, 16, 13, 16),
            Block.box(0, 11, 0, 2, 13, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_FRONT = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(0, 0, 12, 2, 7, 16),
            Block.box(0, 0, 0, 2, 7, 4),
            Block.box(0, 11, 14, 16, 13, 16),
            Block.box(0, 11, 0, 16, 13, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape NORTH_BACK_COL = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(0, 0, 14, 4, 7, 16),
            Block.box(12, 0, 14, 16, 7, 16),
            Block.box(14, 11, 0, 16, 13, 16),
            Block.box(0, 11, 0, 2, 13, 16),
            Block.box(0, 25, 14, 16, 26, 28),
            Block.box(4, 24, 13, 12, 29, 14),
            Block.box(0, 13, 26, 16, 25, 28),
            Block.box(0, 13, 13, 2, 26, 14),
            Block.box(14, 13, 13, 16, 26, 14),
            Block.box(2, 6, 4, 14, 7, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape EAST_BACK_COL = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(0, 0, 0, 2, 7, 4),
            Block.box(0, 0, 12, 2, 7, 16),
            Block.box(0, 11, 14, 16, 13, 16),
            Block.box(0, 11, 0, 16, 13, 2),
            Block.box(-12, 25, 0, 2, 26, 16),
            Block.box(2, 24, 4, 3, 29, 12),
            Block.box(-12, 13, 0, -10, 25, 16),
            Block.box(2, 13, 0, 3, 26, 2),
            Block.box(2, 13, 14, 3, 26, 16),
            Block.box(3, 6, 2, 12, 7, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape SOUTH_BACK_COL = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(12, 0, 0, 16, 7, 2),
            Block.box(0, 0, 0, 4, 7, 2),
            Block.box(0, 11, 0, 2, 13, 16),
            Block.box(14, 11, 0, 16, 13, 16),
            Block.box(0, 25, -12, 16, 26, 2),
            Block.box(4, 24, 2, 12, 29, 3),
            Block.box(0, 13, -12, 16, 25, -10),
            Block.box(14, 13, 2, 16, 26, 3),
            Block.box(0, 13, 2, 2, 26, 3),
            Block.box(2, 6, 3, 14, 7, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape WEST_BACK_COL = Stream.of(
            Block.box(0, 7, 0, 16, 11, 16),
            Block.box(14, 0, 12, 16, 7, 16),
            Block.box(14, 0, 0, 16, 7, 4),
            Block.box(0, 11, 0, 16, 13, 2),
            Block.box(0, 11, 14, 16, 13, 16),
            Block.box(14, 25, 0, 28, 26, 16),
            Block.box(13, 24, 4, 14, 29, 12),
            Block.box(26, 13, 0, 28, 25, 16),
            Block.box(13, 13, 14, 14, 26, 16),
            Block.box(13, 13, 0, 14, 26, 2),
            Block.box(4, 6, 2, 13, 7, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public ThrowSphereIntoRingMachine(Properties properties) {
        super(properties.pushReaction(PushReaction.BLOCK));
        this.registerDefaultState(this.defaultBlockState().setValue(MACHINE_ENDING,MachineEnding.PAYMENT_SECTION)
                .setValue(WATERLOGGED,false).setValue(FACING, Direction.NORTH));
    }

    public static Direction getConnected(BlockState blockState) {
        Direction direction = blockState.getValue(FACING);
        return blockState.getValue(MACHINE_ENDING) == MachineEnding.PAYMENT_SECTION ? direction.getOpposite() : direction;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockGetter getter, BlockPos blockPos, CollisionContext context) {
        if(blockState.getValue(MACHINE_ENDING) != MachineEnding.PAYMENT_SECTION){
            switch (blockState.getValue(FACING)){
                case NORTH -> {
                    return NORTH_BACK_COL;
                }
                case SOUTH -> {
                    return SOUTH_BACK_COL;
                }
                case EAST -> {
                    return EAST_BACK_COL;
                }
                case WEST -> {
                    return WEST_BACK_COL;
                }
            }
        }
        return super.getCollisionShape(blockState,getter,blockPos,context);
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter getter, BlockPos blockPos, CollisionContext context){
        if(blockState.getValue(MACHINE_ENDING) != MachineEnding.PAYMENT_SECTION){
            switch(getConnected(blockState).getOpposite()){
                case NORTH -> {
                    return SOUTH_BACK;
                }
                case SOUTH -> {
                    return NORTH_BACK;
                }
                case WEST -> {
                    return EAST_BACK;
                }
                case EAST -> {
                    return WEST_BACK;
                }
                default -> {
                    return Shapes.block();
                }
            }
        }
        else{
            switch(getConnected(blockState).getOpposite()){
                case NORTH -> {
                    return NORTH_FRONT;
                }
                case SOUTH -> {
                    return SOUTH_FRONT;
                }
                case WEST -> {
                    return WEST_FRONT;
                }
                case EAST -> {
                    return EAST_FRONT;
                }
                default -> {
                    return Shapes.block();
                }
            }
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState blockState, LootParams.Builder params){
        if(blockState.getValue(MACHINE_ENDING) == MachineEnding.PAYMENT_SECTION){
            return super.getDrops(blockState, params);
        }
        else{
            List<ItemStack> emptyItems = List.of(ItemStack.EMPTY); // hardcoded to stop extra pieces from dropping anything
            return emptyItems;
        }
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel serverLevel, BlockPos pos, ItemStack stack, boolean b1){
        if(state.getValue(MACHINE_ENDING) == MachineEnding.PAYMENT_SECTION){
            serverLevel.addFreshEntity(new ItemEntity(serverLevel,pos.getX(),pos.getY() + 0.2D,pos.getZ(),new ItemStack(this.asItem())));
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        BlockState state = blockState;
        super.playerWillDestroy(level, blockPos, blockState, player);
        switch(state.getValue(FACING)){
            case NORTH -> {
                level.removeBlock(blockPos.south(),false);
                break;
            }
            case SOUTH -> {
                level.removeBlock(blockPos.north(),false);
                break;
            }
            case EAST -> {
                level.removeBlock(blockPos.west(),false);
                break;
            }
            case WEST -> {
                level.removeBlock(blockPos.east(),false);
                break;
            }
        }
        return blockState;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity entity, ItemStack itemStack) {
        super.setPlacedBy(level,blockPos,blockState,entity,itemStack);
        if(!level.isClientSide){
            BlockPos bp = blockPos.relative(blockState.getValue(FACING));
            switch (blockState.getValue(FACING)){
                case NORTH -> {
                    level.setBlock(bp.south().south(), blockState.setValue(MACHINE_ENDING,MachineEnding.GOAL_SECTION), 3);
                    break;
                }
                case SOUTH -> {
                    level.setBlock(bp.north().north(), blockState.setValue(MACHINE_ENDING,MachineEnding.GOAL_SECTION), 3);
                    break;
                }
                case EAST -> {
                    level.setBlock(bp.west().west(), blockState.setValue(MACHINE_ENDING,MachineEnding.GOAL_SECTION), 3);
                    break;
                }
                case WEST -> {
                    level.setBlock(bp.east().east(), blockState.setValue(MACHINE_ENDING,MachineEnding.GOAL_SECTION), 3);
                    break;
                }
            }
            level.blockUpdated(blockPos, Blocks.AIR);
            blockState.updateNeighbourShapes(level,blockPos,3);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(MACHINE_ENDING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection(); // reverse
        BlockPos blockPos = context.getClickedPos();
        BlockPos blockPos1 = blockPos.relative(direction);
        Level level = context.getLevel();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

        return level.getBlockState(blockPos1).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(blockPos1) ?
                this.defaultBlockState().setValue(FACING, direction.getOpposite()) : null;
    }

    @Override
    public void stepOn(Level lvl, BlockPos bp, BlockState bs, Entity e) {
        if(e instanceof ItemEntity){
            double x = 0.0D;
            double y = 0.035D;
            double z = 0.0D;

            if(bs.getValue(MACHINE_ENDING) == MachineEnding.GOAL_SECTION){
                y = 0.035D * (double) Mth.randomBetween(lvl.getRandom(),2.0f,7.0f);
            }
            else{
                y = 0.035D;
            }

            switch(bs.getValue(FACING)){
                case NORTH -> z = 0.5D;
                case SOUTH -> z = -0.5D;
                case EAST -> x = -0.5D;
                case WEST -> x = 0.5D;
            }

            e.setDeltaMovement(x,y,z);
        }
    }

    @Override
    public void fallOn(Level lvl, BlockState bs, BlockPos bp, Entity e, float f1) {
        if(e instanceof ItemEntity){
            double x = 0.0D;
            double y = e.getDeltaMovement().y;
            double z = 0.0D;

            if(bs.getValue(MACHINE_ENDING) == MachineEnding.GOAL_SECTION){
                y = e.getDeltaMovement().y * (double)Mth.randomBetween(lvl.getRandom(),2.0f,7.0f);
            }
            else{
                y = e.getDeltaMovement().y;
            }

            switch(bs.getValue(FACING)){
                case NORTH -> z = 0.5D * 2.0D;
                case SOUTH -> z = -0.5D * 2.0D;
                case EAST -> x = -0.5D * 2.0D;
                case WEST -> x = 0.5D * 2.0D;
            }

            e.setDeltaMovement(x,y,z);
        }
    }
}
