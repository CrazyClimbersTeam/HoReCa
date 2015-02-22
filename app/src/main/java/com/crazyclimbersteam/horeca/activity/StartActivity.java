package com.crazyclimbersteam.horeca.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.fragment.welcome.RegistrationFragment;
import com.crazyclimbersteam.horeca.fragment.welcome.SplashFragment;

public class StartActivity extends ActionBarActivity {

    public static final int DELAY_MILLIS = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        getSupportFragmentManager().beginTransaction().add(R.id.main_container, new SplashFragment()).commit();

        final String userName = getSharedPreferences(RegistrationFragment.REGISTRATION_PREFS, Context.MODE_PRIVATE)
                .getString(RegistrationFragment.USER_NAME, "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userName.length() > 0) {
                    Intent mainActivityIntent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(mainActivityIntent);
                    finish();
                } else {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                        replace(R.id.main_container, new RegistrationFragment(),
                                RegistrationFragment.TAG).commitAllowingStateLoss();
                }
            }
        }, DELAY_MILLIS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(RegistrationFragment.TAG);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
