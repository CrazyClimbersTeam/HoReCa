package com.jeapie;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.jeapie.util.JSONArrayFile;
import com.jeapie.util.json.JSONArray;
import com.jeapie.util.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Jeapie API class.
 * <p/>
 * First, you need init api with {@link #init(android.content.Context)}}.
 * <p/>
 * To get api instanse use {@link #getInstance()}
 * <p/>
 * To emit tags events use {@link #emitTagsEvent(java.util.List<String>)}
 * <p/>
 * To receive push notifications from Jeapie, you must realize PushListener interface and add it API with registerPushListener method.
 * <p/>
 * <p/>
 * To correct work you need define the permissions:
 * <pre>
 * {@code
 *
 * <!-- Be sure to change YOUR_PACKAGE_NAME to the real name of your application package, replace YOUR_KEY and YOUR_SECRET with real values! -->
 *
 * <!-- To register and receive message -->
 *     <uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />
 * <!-- Only this application can receive the messages and registration result -->
 *     <permission android:name="YOUR_PACKAGE_NAME.permission.C2D_MESSAGE" android:protectionLevel="signature" />
 *     <uses-permission android:name="YOUR_PACKAGE_NAME.permission.C2D_MESSAGE" />
 * <!-- GCM connects to Google Services. -->
 *     <uses-permission android:name="android.permission.INTERNET"/>
 * <!-- GCM requires a Google account. -->
 *     <uses-permission android:name="android.permission.GET_ACCOUNTS"/>
 * <!-- Keeps the processor from sleeping when a message is received. -->
 *      <uses-permission android:name="android.permission.WAKE_LOCK"/>
 * <!-- To create jeapie unique id. -->
 *      <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
 * }
 * </pre>
 * <p>And add this code to your application difinition:</p>
 * <p>
 * <pre>
 * {@code
 * <application ...>
 *         <meta-data
 *               android:name="com.jeapie.key"
 *               android:value="YOUR_KEY"
 *               />
 *         <meta-data
 *               android:name="com.jeapie.secret"
 *               android:value="YOUR_SECRET"
 *               />
 *         <receiver android:name="com.jeapie.GCMReceiver">
 *             <intent-filter>
 *                 <action android:name="com.google.android.c2dm.intent.RECEIVE"/>
 *                 <action android:name="com.google.android.c2dm.intent.REGISTRATION"/>
 *                 <category android:name="YOUR_PACKAGE_NAME"/>
 *             </intent-filter>
 *         </receiver>
 *
 *         <service android:name="com.jeapie.EventsSenderService" />
 * </application>
 * </pre></p>
 *
 * }
 *
 * @author Tolsi
 */
public final class JeapieAPI {

    public static final String LIB_VERSION = "1.3.1";

    public static final String LOGTAG = "JeapieAPI";
    static final String URL_EXTRA_FIELD = "url";
    static final String KEY_EXTRA_FIELD = "key";
    static final String SECRET_EXTRA_FIELD = "secret";
    static final String SEND_INTERVAL_EXTRA_FIELD = "sendInterval";
    static final String EVENTS_RECEIVER_EXTRA_FIELD = "eventsReceiver";
    private static final String URL_TEMPLATE = "http://app.jeapie.com:1337/api/v1/mobile/track?data=%s";

    private static final String ANDROID_OS = "android";
    private static final int SEND_INTERVAL = 60 * 1000; // ms
    private static volatile JeapieAPI instance;
    protected final String key;
    protected final String secret;
    protected final JSONArrayFile eventsSaver;
    protected final Map<String, Object> generalInfo;
    private final String uuid;
    private final List<Map<String, Object>> events = new LinkedList<Map<String, Object>>();
    private final List<Map<String, Object>> notFlushedEvents = new LinkedList<Map<String, Object>>();
    private boolean firstEvent = false;
    private boolean sendIsActive;
    private Timer sendTimer;
    private Context context;
    private Set<PushListener> pushListeners = new LinkedHashSet<PushListener>();

    private GoogleCloudMessaging mGcm;

    protected JeapieAPI(Context context, InformationProvider si, String appDataDir) {

        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            this.key = bundle.getString("com.jeapie.key");
            this.secret = bundle.getString("com.jeapie.secret");
            if (key == null || secret == null) throw new IllegalStateException("Please, add key and secret metadata to your AndroidManifest.xml!");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException("Error getting package name! Can't read manifest!");
        }

        this.context = context;

        final String os_version = si.getAndroidVersion();
        final String manufacturer = si.getManufacturer();
        final String model = si.getModel();
        final String screen_height = String.valueOf(si.getDisplayHeight());
        final String screen_width = String.valueOf(si.getDisplayWidth());
        uuid = si.getUuid();
        final String language = si.getLanguage();
        final String timezone = si.getTimezone();

        generalInfo = new HashMap<String, Object>();
        generalInfo.put("uuid", uuid);
        generalInfo.put("lib_version", LIB_VERSION);
        generalInfo.put("os_version", os_version);
        generalInfo.put("os", ANDROID_OS);
        generalInfo.put("manufacturer", manufacturer);
        generalInfo.put("model", model);
        generalInfo.put("screen_height", screen_height);
        generalInfo.put("screen_width", screen_width);
        generalInfo.put("language", language);
        generalInfo.put("timezone", timezone);
        generalInfo.put("push_allowed", true);

        JSONArrayFile saver = null;
        try {
            File eventsFile = new File(appDataDir, "jeapie-events.json");
            if (!eventsFile.exists()) eventsFile.createNewFile();
            Log.i(LOGTAG, "events file path: " + eventsFile.getAbsolutePath());
            saver = new JSONArrayFile(eventsFile);
            if (saver.read().length() == 0) firstEvent = true;
        } catch (RuntimeException e) {
            // for tests
            saver = new JSONArrayFile("jeapie-events.json");
        } catch (IOException e) {
            throw new Error("Can't create temp jeapie file: " + e.getLocalizedMessage());
        }
        eventsSaver = saver;

        createSendTimer();
    }

    public static JeapieAPI getInstance() {
        JeapieAPI localInstance = instance;
        if (null == localInstance) {
            throw new IllegalAccessError("call init() first");
        }
        return localInstance;
    }

    public static void init(Context context) {
        JeapieAPI localInstance = instance;
        if (null == localInstance) {
            synchronized (JeapieAPI.class) {
                localInstance = instance;
                if (localInstance == null) {
                    String appDataDir = null;
                    try {
                        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
                        appDataDir = applicationInfo.dataDir;

                        SystemInformationProvider si = new SystemInformationProvider(context);

                        registerActivityLifecycleCallbacks(context);

                        instance = new JeapieAPI(context, si, appDataDir);
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.w(LOGTAG, "System information constructed with a context that apparently doesn't exist.");
                    }
                }
            }
        }
    }

    /**
     * Attempt to register JeapieActivityLifecycleCallbacks to the application's event lifecycle.
     * Once registered, we can automatically check for and show surveys when the application is opened.
     * This is only available if the android version is >= 14.
     */
    @TargetApi(14)
    private static void registerActivityLifecycleCallbacks(Context context) {
        if (context.getApplicationContext() instanceof Application) {
            Log.w(LOGTAG, "registerActivityLifecycleCallbacks");
            final Application app = (Application) context.getApplicationContext();
            app.registerActivityLifecycleCallbacks((new JeapieActivityLifecycleCallbacks()));
        }
    }

    private synchronized void addEventToList(Map<String, Object> event, List<Map<String, Object>> events) {
        if (event != null) {
            if (firstEvent) {
                events.add(generalInfo);
                firstEvent = false;
            }
            // each event must have uuid
            event.put("uuid", uuid);
            events.add(event);
        }
    }

    protected void sendEventsToServer() {
        Intent mServiceIntent = new Intent(context, EventsSenderService.class);

        mServiceIntent.putExtra(URL_EXTRA_FIELD, URL_TEMPLATE);
        mServiceIntent.putExtra(KEY_EXTRA_FIELD, key);
        mServiceIntent.putExtra(SECRET_EXTRA_FIELD, secret);
        mServiceIntent.putExtra(SEND_INTERVAL_EXTRA_FIELD, SEND_INTERVAL);
        mServiceIntent.putExtra(EVENTS_RECEIVER_EXTRA_FIELD, new EventsUploadReceiver(null));

        context.startService(mServiceIntent);
    }

    private Timer createSendTimer() {
        if (sendTimer != null) {
            sendTimer.cancel();
        }
        sendTimer = new Timer();
        sendTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                sendEventsToServer();
            }
        }, SEND_INTERVAL, SEND_INTERVAL);
        return sendTimer;
    }

    private synchronized void flushNotFlushedEventsToFile() {
        if (!notFlushedEvents.isEmpty()) {
            events.clear();
            events.addAll(notFlushedEvents);
        }
        flushEventsToFile();
    }

    protected synchronized void addEvent(Map<String, Object> event) {
        Log.d(LOGTAG, "Event " + event);
        if (sendIsActive) {
            addEventToList(event, notFlushedEvents);
        } else {
            flushNotFlushedEventsToFile();
            addEventToList(event, events);
            flushEventsToFile();
        }
    }

    private void flushEventsToFile() {
        try {
            eventsSaver.append(eventsAsJSONArray());
            events.clear();
        } catch (IOException e) {
            Log.e(LOGTAG, "Flush events to file error", e);
        }
    }

    protected void clearEvents() {
        try {
            eventsSaver.write(new JSONArray());
            events.clear();
        } catch (IOException e) {
            Log.e(LOGTAG, "Clear events in file error", e);
        }
    }

    protected JSONArray eventsAsJSONArray() {
        JSONArray array = new JSONArray();
        for (Map<String, Object> event : events) {
            array.put(new JSONObject(event));
        }
        return array;
    }

    public void emitTagsEvent(List<String> tags) {
        addEvent(EventBuilder.buildTagsEvent(tags));
    }

    /*public void registerPushListener(PushListener listener) {
        pushListeners.add(listener);
    }

    public void removePushListener(PushListener listener) {
        pushListeners.remove(listener);
    }*/

    void notifyPushListener(Intent intent) {
        for (PushListener listener : pushListeners) {
            listener.onPush(intent);
        }
    }

    public void emitAddTagEvent(String tag) {
        addEvent(EventBuilder.buildAddTagEvent(tag));
    }

    public void emitRemoveTagEvent(String tag) {
        addEvent(EventBuilder.buildRemoveTagEvent(tag));
    }

    public void emitRemoveTagsEvent() {
        addEvent(EventBuilder.buildRemoveTagsEvent());
    }

    public void emitAliasEvent(String alias) {
        addEvent(EventBuilder.buildAliasEvent(alias));
    }

    private class EventsUploadReceiver extends ResultReceiver {

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public EventsUploadReceiver(Handler handler) {
            super(handler);
        }

        private void onSendingEnded() {
            sendIsActive = false;
            flushNotFlushedEventsToFile();
            createSendTimer();
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);

            switch (resultCode) {
                case EventsSenderService.SENDING_IS_STARTED_CODE:
                    sendIsActive = true;
                    break;
                case EventsSenderService.SUCCEEDED_RESULT_CODE:
                    clearEvents();
                    onSendingEnded();
                    break;
                case EventsSenderService.FAILED_RESULT_CODE:
                    onSendingEnded();
                    Exception e = (Exception) resultData.getSerializable("exception");
                    Log.w(LOGTAG, "Request failed with error: " + e.getMessage());
                    break;
                default:
                    Log.w(LOGTAG, "Unknown result code " + resultCode);
            }
        }
    }

    /**
     * Registers the application with GCM servers asynchronously.
     */
    public void registerTokenInBackground(final Context context, final String gcmSenderId) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg;
                try {
                    if (mGcm == null) {
                        mGcm = GoogleCloudMessaging.getInstance(context);
                    }
                    String regid = mGcm.register(gcmSenderId);
                    msg = "Device registered, registration id=" + regid;
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }
        }.execute(null, null, null);
    }
}
