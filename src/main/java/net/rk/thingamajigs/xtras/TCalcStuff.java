package net.rk.thingamajigs.xtras;

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
}
