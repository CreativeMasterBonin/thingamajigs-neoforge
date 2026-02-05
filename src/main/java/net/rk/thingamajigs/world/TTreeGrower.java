package net.rk.thingamajigs.world;

import java.util.Optional;

public class TTreeGrower{
    public static final net.minecraft.world.level.block.grower.TreeGrower RUBBER =
            new net.minecraft.world.level.block.grower.TreeGrower("rubber",
                    Optional.empty(),
                    Optional.of(TConfiguredFeature.RUBBER_KEY),
                    Optional.empty());
}
