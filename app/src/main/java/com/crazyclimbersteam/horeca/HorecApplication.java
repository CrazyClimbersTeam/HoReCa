package com.crazyclimbersteam.horeca;

import android.app.Application;

import com.crazyclimbersteam.horeca.net.manager.HorecApiManager;

import static com.crazyclimbersteam.horeca.utils.LogUtils.log;

/**
 * @author Mirash
 */
public class HorecApplication extends Application {

    private static HorecApplication instance;

    public static HorecApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        log("application onCreate");
        super.onCreate();
        instance = this;
        HorecApiManager.init();
    }
}
