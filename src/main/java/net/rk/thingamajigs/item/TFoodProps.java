package net.rk.thingamajigs.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class TFoodProps{
    public static final FoodProperties GLOBIZED_SANDWICH = new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(2.2F)
            .fast()
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 5, true, false,false), 1.0F)
            .effect(new MobEffectInstance(MobEffects.LUCK, 750, 5,true, false,false), 1.0F)
            .build();
}
