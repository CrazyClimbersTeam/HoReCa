package com.jeapie;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Abstracts away possibly non-present system information classes,
 * and handles permission-dependent queries for default system information.
 */
class SystemInformationProvider implements InformationProvider {

    public static final String LOGTAG = "JeapieAPI:SystemInformationProvider";

    private final Context mContext;
    private final DisplayMetrics mDisplayMetrics;
    private final String uuid;

    public SystemInformationProvider(Context context) {
        mContext = context;

        mDisplayMetrics = new DisplayMetrics();

        Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        display.getMetrics(mDisplayMetrics);

        // http://stackoverflow.com/a/2853253/699934
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String tmDevice = "";
        String tmSerial = "";
        String androidId = "";
        try {
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
        } catch (SecurityException e) {
            Log.w(LOGTAG, "Please, add android.permission.READ_PHONE_STATE permission");
        }
        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        uuid = deviceUuid.toString();
    }

    @Override
    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    @Override
    public String getModel() {
        return Build.MODEL;
    }

    @Override
    public String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    @Override
    public int getDisplayWidth() {
        return mDisplayMetrics.widthPixels;
    }

    @Override
    public int getDisplayHeight() {
        return mDisplayMetrics.heightPixels;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public String getLanguage() { return Locale.getDefault().getLanguage(); }

    @Override
    public String getTimezone() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
                Locale.getDefault());
        Date currentLocalTime = calendar.getTime();
        DateFormat date = new SimpleDateFormat("Z");

        return date.format(currentLocalTime);
    }
}
