package com.crazyclimbersteam.horeca.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.crazyclimbersteam.horeca.R;

/**
 * @author Mirash
 */
public class DetailsActivity extends FragmentActivity {
    public static String TAG = DetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
    }
}
