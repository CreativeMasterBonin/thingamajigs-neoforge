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

    public static int nextIntBetweenInclusive(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
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


    /**
     * Converts a value from its old range to a new range (floats)
     * @param value The input float to change its value relative to another range
     * @param oldMin The old range minimum
     * @param oldMax The old range maximum
     * @param newMin The new range minimum
     * @param newMax The new range maximum
     * @return The converted float value
     */
    public static float convertFloatRangeToOther(float value,float oldMin,float oldMax,float newMin,float newMax){
        if(oldMin == oldMax){
            return -1.0f;
        }
        if(newMin == newMax){
            return -1.0f;
        }
        return ((value - oldMin) / (oldMax - oldMin)) * (newMax - newMin) + newMin;
    }

    /**
     * Converts a value from its old range to a new range (integers)
     * @param value The input int to change its value relative to another range
     * @param oldMin The old range minimum
     * @param oldMax The old range maximum
     * @param newMin The new range minimum
     * @param newMax The new range maximum
     * @return The converted int value
     */
    public static int convertIntRangeToOther(int value,int oldMin,int oldMax,int newMin,int newMax){
        if(oldMin == oldMax){
            return -1;
        }
        if(newMin == newMax){
            return -1;
        }
        return ((value - oldMin) / (oldMax - oldMin)) * (newMax - newMin) + newMin;
    }
}
