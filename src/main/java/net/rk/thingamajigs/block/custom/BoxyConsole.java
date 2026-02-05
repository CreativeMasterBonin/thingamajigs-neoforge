package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BoxyConsole extends ThingamajigsDecorativeBlock{
    public static final VoxelShape NORTHSOUTH = Block.box(1, 0, 3, 15, 4, 13);
    public static final VoxelShape EASTWEST = Block.box(3, 0, 1, 13, 4, 15);


    public BoxyConsole(Properties properties){
        super(properties.sound(SoundType.METAL)
                .pushReaction(PushReaction.DESTROY).strength(1f,2f).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH,SOUTH -> {
                return NORTHSOUTH;
            }
            case EAST,WEST -> {
                return EASTWEST;
            }
            default -> {
                return Shapes.block();
            }
        }
    }
}
