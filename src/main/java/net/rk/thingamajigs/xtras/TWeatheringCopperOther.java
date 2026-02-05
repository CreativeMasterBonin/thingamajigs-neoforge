package net.rk.thingamajigs.xtras;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.rk.thingamajigs.block.TBlocks;

import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("deprecated,unused")
public interface TWeatheringCopperOther extends ChangeOverTimeBlock<TWeatheringCopperOther.State>{
    Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(
            () -> ImmutableBiMap.<Block, Block>builder()
                    .put(TBlocks.COPPER_CHAIR.get(),TBlocks.EXPOSED_COPPER_CHAIR.get())
                    .put(TBlocks.EXPOSED_COPPER_CHAIR.get(),TBlocks.WEATHERED_COPPER_CHAIR.get())
                    .put(TBlocks.WEATHERED_COPPER_CHAIR.get(),TBlocks.OXIDIZED_COPPER_CHAIR.get())
                    .put(TBlocks.COPPER_TABLE.get(),TBlocks.EXPOSED_COPPER_TABLE.get())
                    .put(TBlocks.EXPOSED_COPPER_TABLE.get(),TBlocks.WEATHERED_COPPER_TABLE.get())
                    .put(TBlocks.WEATHERED_COPPER_TABLE.get(),TBlocks.OXIDIZED_COPPER_TABLE.get())
                    .build()
    );
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    static Optional<Block> getPrevious(Block p_154891_) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(p_154891_));
    }

    static Block getFirst(Block p_154898_) {
        Block block = p_154898_;

        for (Block block1 = PREVIOUS_BY_BLOCK.get().get(p_154898_); block1 != null; block1 = PREVIOUS_BY_BLOCK.get().get(block1)) {
            block = block1;
        }

        return block;
    }

    static Optional<BlockState> getPrevious(BlockState p_154900_) {
        return getPrevious(p_154900_.getBlock()).map(p_154903_ -> p_154903_.withPropertiesOf(p_154900_));
    }

    static Optional<Block> getNext(Block p_154905_) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(p_154905_));
    }

    static BlockState getFirst(BlockState p_154907_) {
        return getFirst(p_154907_.getBlock()).withPropertiesOf(p_154907_);
    }

    @Override
    default Optional<BlockState> getNext(BlockState p_154893_) {
        return getNext(p_154893_.getBlock()).map(p_154896_ -> p_154896_.withPropertiesOf(p_154893_));
    }

    @Override
    default float getChanceModifier() {
        return this.getAge() == TWeatheringCopperOther.State.UNAFFECTED ? 0.75F : 1.0F;
    }

    // updated copper to remove unneeded defines
    enum State implements StringRepresentable {
        UNAFFECTED("unaffected"),
        EXPOSED("exposed"),
        WEATHERED("weathered"),
        OXIDIZED("oxidized");

        public static final Codec<TWeatheringCopperOther.State> CODEC = StringRepresentable.fromEnum(TWeatheringCopperOther.State::values);
        private final String name;

        State(String p_304569_) {
            this.name = p_304569_;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}
