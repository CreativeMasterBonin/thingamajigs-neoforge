package net.rk.thingamajigs.xtras;

import net.minecraft.util.Mth;

import java.util.Random;

public class TCalcStuff {
    public static float nextFloatBetweenInclusive(float min, float max) {
        Random random = new Random();
        return random.nextFloat(max - min + 1) + min;
    }

    public static double nextDoubleBetweenInclusive(double min, double max) {
        Random random = new Random();
        return random.nextDouble(max - min + 1) + min;
    }

    /**
     * Converts degrees (e.g. 45 or 180) to radians (e.g. 0.1 or 1.2)
     * @param degrees The degrees (from -360 to 360 is the preferred use-case)
     * @return The radians result from the conversion
     */
    public static float degreesToRadians(float degrees){
        return (degrees * Mth.PI / 180);
    }

    /**
     * Converts radians to degrees (same as degreesToRadians function but opposite)
     * @param radians The radians to convert to degrees
     * @return The degrees result from the conversion
     */
    public static float radiansToDegrees(float radians){
        return (radians * 180 / Mth.PI);
    }
}
