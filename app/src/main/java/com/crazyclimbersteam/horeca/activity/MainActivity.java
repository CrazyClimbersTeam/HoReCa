package com.crazyclimbersteam.horeca.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.crazyclimbersteam.horeca.HorecApplication;
import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.drawer.MenuItemClickListener;
import com.crazyclimbersteam.horeca.drawer.NavigationMenuFragment;
import com.crazyclimbersteam.horeca.drawer.model.MenuNavigable;
import com.crazyclimbersteam.horeca.drawer.views.MenuItemView;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;
import com.crazyclimbersteam.horeca.fragment.detail.RequestSearchFragment;
import com.crazyclimbersteam.horeca.fragment.main.CategoriesFragment;
import com.crazyclimbersteam.horeca.fragment.map.NewMapFragment;
import com.crazyclimbersteam.horeca.fragment.settings.SettingsFragment;
import com.crazyclimbersteam.horeca.tools.ParallaxView;
import com.crazyclimbersteam.horeca.tools.ScreenController;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jeapie.JeapieAPI;

import static com.crazyclimbersteam.horeca.utils.LogUtils.log;


public class MainActivity extends ActionBarActivity implements MenuItemClickListener<MainActivity>, CategoriesFragment.CategoriesFragmentHost, OnMapReadyCallback {

    private final static String TAG = MainActivity.class.getSimpleName();

    private final static String SENDER_ID = "660774978335";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private static final float MENU_HEIGHT = HorecApplication.getInstance().getResources().getDimension(R.dimen.navigation_drawer_width);
    public static final int SCREEN_CONTAINER_ID = R.id.screen_container;

    private Toolbar mToolbar;
    private ParallaxView containerView;
    private NavigationMenuFragment mNavigationMenuFragment;

    private ScreenController mScreenController;
    private DrawerLayout mDrawerLayout;
    private NewMapFragment mapFragment;
    private GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        JeapieAPI.init(this);
        // Check device for Play Services APK.
        if (checkPlayServices()) {
            // Get GCM token asynchronously in background
            JeapieAPI.getInstance().registerTokenInBackground(this, SENDER_ID);
            JeapieAPI.getInstance().emitAddTagEvent("HoReCa");
        }

        containerView = (ParallaxView) findViewById(SCREEN_CONTAINER_ID);
        mToolbar = getActionBarToolbar();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initDrawer();
        initScreenController();
        digestIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        digestIntent(intent);
    }

    private void digestIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            navigateToScreenFragment(RequestSearchFragment.newInstance(query), RequestSearchFragment.getArguments(query));
        } else {
            navigateToScreenFragment(CategoriesFragment.newInstance());
        }
    }

    private void initDrawer() {
        mNavigationMenuFragment = (NavigationMenuFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationMenuFragment.setItemClickListener(this);
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
                containerView.setOffset(MENU_HEIGHT * slideOffset);
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
//        mDrawerLayout.openDrawer(Gravity.LEFT);
    }

    private void initScreenController() {
        mScreenController = new ScreenController(getSupportFragmentManager(), SCREEN_CONTAINER_ID);
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
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();
            if (searchView != null) {
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                    @Override
                    public boolean onClose() {
                        navigateToScreenFragment(CategoriesFragment.newInstance());
                        return false;
                    }
                });
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_map:
                // mapFragment = new NewMapFragment();
                //mapFragment.getMapAsync(this);
                //getFragmentManager().beginTransaction().add(R.id.screen_container, mapFragment).commit();
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

    @Override
    public void onMenuItemClick(MenuNavigable<MainActivity> menuNavigable, MenuItemView menuItem) {
        log("onMenuItemClick: " + menuNavigable.getTag());
        menuNavigable.handleItemClick(this);
    }

    public void navigateToScreenFragment(BaseFragment fragment) {
        navigateToScreenFragment(fragment, null);
    }

    public void navigateToScreenFragment(BaseFragment fragment, Bundle parameters) {
        mScreenController.navigateToScreenFragment(fragment, parameters);
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onCategorySelected(String categoryName) {
        mScreenController.navigateToScreenFragment(RequestSearchFragment.newInstance(categoryName), null, true);
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.867, 151.206);

        googleMap.setMyLocationEnabled(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        googleMap.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
    }

    @Override
    public void onBackPressed() {
        if (CategoriesFragment.TAG.equals(mScreenController.getCurrentScreenTag())) {
            super.onBackPressed();
        } else {
            mScreenController.navigateToScreenFragment(CategoriesFragment.newInstance(), null, false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SettingsFragment.TAG);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
