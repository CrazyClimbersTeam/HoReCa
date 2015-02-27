package com.crazyclimbersteam.horeca.utils;

import android.util.Log;

/**
 * @author Mirash
 */
public final class LogUtils {
    public static final String TAG = "HOREC";

    public static void log(String message) {
        log(TAG, message);
    }

    public static void log(String tag, String message) {
        Log.d(tag, message);
    }
}
