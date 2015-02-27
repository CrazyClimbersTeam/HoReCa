package com.crazyclimbersteam.horeca.activity.detail;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;

/**
 * Created by damvol on 22.02.2015.
 */
public class NotificationActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity_layout);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String message = extra.getString("key", null);
            if (message != null) {
                ((TextView) findViewById(R.id.notification_description)).setText(message);
            }
        }
    }
}
