package net.rk.thingamajigs.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import net.rk.thingamajigs.Thingamajigs;

public class TSound extends SoundDefinitionsProvider {
    protected TSound(PackOutput output, ExistingFileHelper helper) {
        super(output, Thingamajigs.MODID, helper);
    }

    @Override
    public void registerSounds() {

    }
}
