package com.crazyclimbersteam.horeca.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.astuetz.PagerSlidingTabStrip;
import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.adapter.MenuPagerAdapter;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;
import com.crazyclimbersteam.horeca.fragment.menu.MenuFragment;
import com.crazyclimbersteam.horeca.net.pojo.MenuCategory;
import com.crazyclimbersteam.horeca.net.pojo.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 22.02.15
 * Time: 05:24
 */
public class MenuActivity extends ActionBarActivity {

    private static final String KEY_CAFE_ID = "KEY_CAFE_ID";

    private List<MenuCategory> menuCategoryList;
    private MenuPagerAdapter adapter;

    public static Bundle getIntentParams(String id) {
        Bundle args = new Bundle();
        args.putString(KEY_CAFE_ID, id);
        return args;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        ViewPager pager = (ViewPager) findViewById(R.id.menu_pager);
        adapter = new MenuPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.menu_tabs);
        tabs.setViewPager(pager);
        menuCategoryList = new ArrayList<MenuCategory>() {
            {
                add(new MenuCategory("Appetizers", new ArrayList<MenuItem>()));
                add(new MenuCategory("Drinks", new ArrayList<MenuItem>()));
                add(new MenuCategory("Main dishes", new ArrayList<MenuItem>()));
                add(new MenuCategory("Desserts", new ArrayList<MenuItem>()));
            }
        };
        populateAdapter(menuCategoryList);
        initActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initActionBar() {
        getSupportActionBar().setTitle("Banka Bar Menu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void populateAdapter(List<MenuCategory> categoryList) {
        for (final MenuCategory category : categoryList) {
            adapter.add(new MenuPagerAdapter.MenuPagerAdapterPage() {
                @Override
                public BaseFragment getFragment() {
                    return MenuFragment.newInstance(category.getItemList());
                }

                @Override
                public String getTitle() {
                    return category.getCategoryName();
                }
            });
        }
        adapter.notifyDataSetChanged();
    }
}
