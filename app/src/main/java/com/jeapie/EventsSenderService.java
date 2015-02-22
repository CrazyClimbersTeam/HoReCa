package com.jeapie;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

/**
 * Service for send events to server
 * <p/>
 * <p>To enable EventsSenderService in your application, add a clause like the following
 * to the &lt;application&gt; tag of your AndroidManifest.xml.
 * <p/>
 * <pre>
 * {@code
 *
 * <application ...>
 *     <service android:name="com.jeapie.EventsSenderService" />
 * </application>
 */
public class EventsSenderService extends IntentService {

    public static final int FAILED_RESULT_CODE = 0;
    public static final int SUCCEEDED_RESULT_CODE = 1;
    public static final int SENDING_IS_STARTED_CODE = 2;
    public static final String JEAPIE_EVENTS_SENDER_SERVICE_NAME = "Jeapie events sender service";

    private String key;
    private String secret;
    private String url;

    private ResultReceiver eventsReceiver;

    public EventsSenderService() {
        super(JEAPIE_EVENTS_SENDER_SERVICE_NAME);
    }

    private static Bundle createResponseBundle(String response) {
        Bundle responseBundle = new Bundle();
        responseBundle.putString("response", response);
        return responseBundle;
    }

    private static Bundle createExceptionBundle(Throwable e) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("exception", e);
        return bundle;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        url = intent.getStringExtra(JeapieAPI.URL_EXTRA_FIELD);
        key = intent.getStringExtra(JeapieAPI.KEY_EXTRA_FIELD);
        secret = intent.getStringExtra(JeapieAPI.SECRET_EXTRA_FIELD);
        eventsReceiver = intent.getParcelableExtra(JeapieAPI.EVENTS_RECEIVER_EXTRA_FIELD);
        sendEventsToServer();
    }

    private void sendEventsToServer() {
        eventsReceiver.send(SENDING_IS_STARTED_CODE, null);
        try {
            String eventsJSONString = JeapieAPI.getInstance().eventsSaver.read().toString();
            ServerMessage.Result result = ServerMessage.postData(url, key, secret, eventsJSONString);

            if (result.getStatus() == ServerMessage.Status.SUCCEEDED) {
                eventsReceiver.send(SUCCEEDED_RESULT_CODE, createResponseBundle(result.getResponse()));
            } else {
                throw new Exception("Request failed with error: " + result.getResponse());
            }
        } catch (Exception e) {
            e.printStackTrace();
            eventsReceiver.send(FAILED_RESULT_CODE, createExceptionBundle(e));
        }
    }
}
