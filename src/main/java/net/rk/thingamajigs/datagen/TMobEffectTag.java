package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.rk.thingamajigs.Thingamajigs;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TMobEffectTag extends TagsProvider<MobEffect> {
    public TMobEffectTag(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, BuiltInRegistries.MOB_EFFECT.key(), lookupProvider, Thingamajigs.MODID, existingFileHelper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        tag(TTag.DISALLOWED_IN_VENDING_MACHINES)
                .add(MobEffects.BAD_OMEN.getKey())
                .add(MobEffects.RAID_OMEN.getKey())
                .add(MobEffects.TRIAL_OMEN.getKey())
                .add(MobEffects.CONDUIT_POWER.getKey())
                .add(MobEffects.DOLPHINS_GRACE.getKey())
                .add(MobEffects.HERO_OF_THE_VILLAGE.getKey())
        ;
    }
}
