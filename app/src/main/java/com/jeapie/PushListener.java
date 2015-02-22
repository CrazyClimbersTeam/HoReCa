package com.jeapie;

import android.content.Intent;

/**
 * API push receiver interface
 */
public interface PushListener {
    /**
     * Invoked on push event income
     *
     * @param intent push message
     */
    void onPush(Intent intent);
}
