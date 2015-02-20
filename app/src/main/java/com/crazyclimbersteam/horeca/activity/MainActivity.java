package com.crazyclimbersteam.horeca.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.crazyclimbersteam.horeca.HorecApplication;
import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.tools.ParallaxView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.jeapie.JeapieAPI;

import static com.crazyclimbersteam.horeca.utils.LogUtils.log;


public class MainActivity extends ActionBarActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private final static String SENDER_ID = "660774978335";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private static final float MENU_HEIGHT = HorecApplication.getInstance().getResources().getDimension(R.dimen.navigation_drawer_width);

    private Toolbar mToolbar;
    private ParallaxView mParallaxView;
    JeapieAPI mJeapieAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity_layout);

        JeapieAPI.init(this);

        // Check device for Play Services APK.
        if (checkPlayServices()) {
            // Get GCM token asynchronously in background
            mJeapieAPI = JeapieAPI.getInstance();
            mJeapieAPI.registerTokenInBackground(this, SENDER_ID);
            mJeapieAPI.emitAddTagEvent("Coffee");
        }

        mParallaxView = (ParallaxView) findViewById(R.id.parallax_content);
        mToolbar = getActionBarToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initDrawerToggle();
    }

    private void initDrawerToggle() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mParallaxView.setOffset(MENU_HEIGHT * slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                log("onDrawerOpened");
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                log("onDrawerClosed");
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    protected Toolbar getActionBarToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();
            if (searchView != null) {
                searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
