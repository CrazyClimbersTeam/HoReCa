package com.alexmirash.parallaxheaderviewpager.util;

import android.util.Log;

/**
 * @author Mirash
 */
public final class MirashUtils {
    private static final String LOG = "LOL";

    public static float clamp(float value, float max, float min) {
        return Math.max(Math.min(value, min), max);
    }

    public static void log(String msg) {
        logD(msg);
    }

    public static void logD(String msg) {
        Log.d(LOG, msg);
    }
}
