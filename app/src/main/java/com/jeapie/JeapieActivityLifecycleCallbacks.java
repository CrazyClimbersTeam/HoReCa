package com.jeapie;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;

import java.util.Date;

@TargetApi(14)
class JeapieActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    private static final String LOGTAG = "JeapieAPI:JeapieActivityLifecycleCallbacks";
    private Date sessionStart;
    private Date sessionPerhapsStop;
    private boolean mHasDoneFirstCheck = false;
    private Integer mCurOrientation;

    /**
     * If JeapieActivityLifecycleCallbacks is registered with the Application then this method
     * will be called anytime an activity is created. Our goal is to automatically check for and show
     * an eligible survey when the app is opened. Unfortunately, this also gets called every time
     * the device's orientation changes. We'll attempt to account for that by tracking the
     * orientation state and only checking for surveys if the orientation state did not change.
     * Furthermore, the library is unlikely to be instantiated in time for this to be called
     * on the initial opening of the application. However, this method is executed when the
     * application is in memory but closed and the user re-opens it.
     * back up.
     *
     * @param activity
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        final Configuration config = activity.getResources().getConfiguration();
        final boolean dueToOrientationChange = mCurOrientation != null && config.orientation != mCurOrientation;
        if (!dueToOrientationChange && activity.isTaskRoot()) {
            emitOpenedEvent();
        }
        mCurOrientation = config.orientation;
    }

    /**
     * This method is called anytime an activity is started (which is quite frequently). The only
     * reason we are interested in this call is to check and show an eligible survey on initial app
     * open. Unfortunately, by the time JeapieActivityLifecycleCallbacks is registered, we've
     * already missed the onActivityCreated call. We'll use this event to "catch up".
     * checkForSurveys is only called if hasn't been previously called in the life of the app.
     *
     * @param activity
     */
    @Override
    public void onActivityStarted(Activity activity) {
        if (!mHasDoneFirstCheck && activity.isTaskRoot()) {
            emitOpenedEvent();
        }
    }

    private long getTimeDifferenceInSeconds(Date from, Date to) {
        long sessionInMs = Math.abs(to.getTime() -
                from.getTime());
        long seconds = sessionInMs / 1000;
        return seconds;
    }

    @Override
    public void onActivityResumed(Activity activity) {

        if (sessionPerhapsStop != null && getTimeDifferenceInSeconds(sessionPerhapsStop, new Date()) > 10) {
            if (null != sessionStart) {
                JeapieAPI.getInstance().addEvent(EventBuilder.buildSessionEvent(getTimeDifferenceInSeconds(sessionStart, sessionPerhapsStop)));
                sessionStart = null;
                JeapieAPI.getInstance().sendEventsToServer();
            }
        }

        if (null == sessionStart) sessionStart = new Date();
    }

    @Override
    public void onActivityPaused(Activity activity) {
        sessionPerhapsStop = new Date();
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    private void emitOpenedEvent() {
        if (!mHasDoneFirstCheck) mHasDoneFirstCheck = true;
//        JeapieAPI.getInstance().addEvent(EventBuilder.buildOpenEvent(new Date()));
    }
}
