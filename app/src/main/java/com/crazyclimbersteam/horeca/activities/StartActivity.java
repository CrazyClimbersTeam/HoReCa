package com.crazyclimbersteam.horeca.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.fragments.RegistrationFragment;
import com.crazyclimbersteam.horeca.fragments.SplashFragment;

public class StartActivity extends FragmentActivity {

    public static final int DELAY_MILLIS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        getSupportFragmentManager().beginTransaction().add(R.id.main_container, new SplashFragment()).commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().
                        setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit).
                            replace(R.id.main_container, new RegistrationFragment(), RegistrationFragment.TAG).commit();
            }
        }, DELAY_MILLIS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(RegistrationFragment.TAG);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
