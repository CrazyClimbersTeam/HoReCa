package com.crazyclimbersteam.horeca.utils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author Mirash
 */
public class HorecUtils {
    public static void hideActionBarTitle(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public static void hideStatusBar(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void showStatusBar(Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
