package com.jeapie;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.Date;

/**
 * GCMReceiver for handling Google Cloud Messaging intents.
 * <p/>
 * <p>You can use GCMReceiver to report Google Cloud Messaging registration identifiers
 * to app associated with Jeapie.
 * <p/>
 * <p>To enable GCMReceiver in your application, add a clause like the following
 * to the &lt;application&gt; tag of your AndroidManifest.xml. (Be sure to replace "YOUR APPLICATION PACKAGE NAME"
 * in the snippet with the actual package name of your app.)
 * <p/>
 * <pre>
 * {@code
 *
 * <receiver android:name="com.jeapie.GCMReceiver" >
 *  <intent-filter>
 *      <action android:name="com.google.android.c2dm.intent.RECEIVE" />
 *      <action android:name="com.google.android.c2dm.intent.REGISTRATION" />
 *      <category android:name="YOUR APPLICATION PACKAGE NAME" />
 *  </intent-filter>
 * </receiver>
 *
 * }
 * </pre>
 * <p/>
 * <p>In addition, GCMReceiver will also need the following permissions configured
 * in your AndroidManifest.xml file:
 * <p/>
 * <pre>
 * {@code
 *
 * <!-- Be sure to change YOUR_PACKAGE_NAME to the real name of your application package -->
 * <permission android:name="YOUR_PACKAGE_NAME.permission.C2D_MESSAGE" android:protectionLevel="signature" />
 * <uses-permission android:name="YOUR_PACKAGE_NAME.permission.C2D_MESSAGE" />
 *
 * <uses-permission android:name="android.permission.INTERNET" />
 * <uses-permission android:name="android.permission.GET_ACCOUNTS" />
 * <uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />
 * <uses-permission android:name="android.permission.WAKE_LOCK" />
 *
 * }
 * </pre>
 */
public class GCMReceiver extends BroadcastReceiver {
    public static final String M_ID_EXTRA_FIELD = "m_id";
    public static final String MESSAGE_EXTRA_FIELD = "message";
    public static final String REGISTRATION_ID_EXTRA_FIELD = "registration_id";

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            handleRegistrationIntent(intent);
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
            handleNotificationIntent(context, intent);
        }
    }

    private void handleRegistrationIntent(Intent intent) {
        final String registration = intent.getStringExtra(REGISTRATION_ID_EXTRA_FIELD);

        if (registration != null) {
            try {
                JeapieAPI.getInstance().addEvent(EventBuilder.buildTokenEvent(registration));
            } catch (IllegalAccessError e) {
                Log.e(JeapieAPI.LOGTAG, "JeapieAPI isn't initialisated!", e);
            }
        }
    }

    private void handleNotificationIntent(Context context, Intent intent) {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
        String messageType = gcm.getMessageType(intent);

        if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            final String mId = intent.getStringExtra(M_ID_EXTRA_FIELD);
            if (mId != null) {
                try {
                    JeapieAPI.init(context);
                    JeapieAPI.getInstance().addEvent(EventBuilder.buildPushEvent(mId, new Date()));
                } catch (IllegalAccessError e) {
                    Log.e(JeapieAPI.LOGTAG, "JeapieAPI initialisation error!", e);
                }
            }

            customActionOnPush(context, intent);
        }
    }

    public void customActionOnPush(Context context, Intent intent) {
        final String message = intent.getExtras().getString("message");
        final String sound   = intent.getExtras().getString("sound", null);

        if (message == null) return;

        final PackageManager manager = context.getPackageManager();
        final Intent appIntent = manager.getLaunchIntentForPackage(context.getPackageName());
        CharSequence notificationTitle = "";
        int notificationIcon = android.R.drawable.sym_def_app_icon;
        try {
            final ApplicationInfo appInfo = manager.getApplicationInfo(context.getPackageName(), 0);
            notificationTitle = manager.getApplicationLabel(appInfo);
            notificationIcon = appInfo.icon;
        } catch (final PackageManager.NameNotFoundException e) {
            // In this case, use a blank title and default icon
        }

        final PendingIntent contentIntent = PendingIntent.getActivity(
                context.getApplicationContext(),
                0,
                appIntent, // add this pass null to intent
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        if (Build.VERSION.SDK_INT < 11) {
            showNotificationSDKLessThan11(context, contentIntent, notificationIcon, notificationTitle, message, sound);
        } else {
            showNotificationSDK11OrHigher(context, contentIntent, notificationIcon, notificationTitle, message, sound);
        }
    }

    @SuppressWarnings("deprecation")
    @TargetApi(8)
    private void showNotificationSDKLessThan11(Context context, PendingIntent intent, int notificationIcon, CharSequence title, CharSequence message, String sound) {
        final NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        final Notification n = new Notification(notificationIcon, message, System.currentTimeMillis());

        n.defaults |= Notification.DEFAULT_LIGHTS;
        n.defaults |= Notification.DEFAULT_VIBRATE;

        if (sound != null && sound.equals("default"))
            n.defaults |= Notification.DEFAULT_SOUND;

        n.flags |= Notification.FLAG_AUTO_CANCEL;
        n.setLatestEventInfo(context, title, message, intent);
        nm.notify(0, n);
    }

    @SuppressWarnings("deprecation")
    @TargetApi(11)
    private void showNotificationSDK11OrHigher(Context context, PendingIntent intent, int notificationIcon, CharSequence title, CharSequence message, String sound) {
        final NotificationManager nm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        final Notification.Builder builder = new Notification.Builder(context).
                setSmallIcon(notificationIcon).
                setTicker(message).
                setWhen(System.currentTimeMillis()).
                setContentTitle(title).
                setContentText(message).
                setContentIntent(intent);
        Notification n;
        if (Build.VERSION.SDK_INT < 16) {
            n = builder.getNotification();
        } else {
            n = builder.build();
        }

        n.defaults |= Notification.DEFAULT_LIGHTS;
        n.defaults |= Notification.DEFAULT_VIBRATE;

        if (sound != null && sound.equals("default"))
            n.defaults |= Notification.DEFAULT_SOUND;

        n.flags |= Notification.FLAG_AUTO_CANCEL;
        nm.notify(0, n);
    }
}