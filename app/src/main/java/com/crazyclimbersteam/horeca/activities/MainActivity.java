package com.crazyclimbersteam.horeca.activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.crazyclimbersteam.horeca.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.jeapie.JeapieAPI;


public class MainActivity extends ActionBarActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private final static String SENDER_ID = "660774978335";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    JeapieAPI mJeapieAPI;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init Jeapie service
        JeapieAPI.init(this);

        // Check device for Play Services APK.
        if (checkPlayServices()) {
            // Get GCM token asynchronously in background
            mJeapieAPI = JeapieAPI.getInstance();
            mJeapieAPI.registerTokenInBackground(this, SENDER_ID);
            mJeapieAPI.emitAddTagEvent("Coffee");
        }

        setContentView(R.layout.main_layout);

        mToolbar = getActionBarToolbar();

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,  drawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
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
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.searchable.
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
