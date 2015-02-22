package com.crazyclimbersteam.horeca.net.manager;

import com.crazyclimbersteam.horeca.net.HorecApi;

import retrofit.RestAdapter;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 22.02.15
 * Time: 03:10
 */
public class HorecApiManager {

    public static final String BASE_URL = "http://192.168.101.22:8088";

    public static HorecApi api;

    public static void init() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        api = restAdapter.create(HorecApi.class);
    }
}
