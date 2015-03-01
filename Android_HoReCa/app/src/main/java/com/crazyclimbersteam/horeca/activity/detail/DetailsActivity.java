package com.crazyclimbersteam.horeca.activity.detail;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alexmirash.parallaxheaderviewpager.view.ParallaxHeaderPagerView;
import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.adapter.DetailsPagerAdapter;
import com.crazyclimbersteam.horeca.activity.detail.dataprovider.DetailsDataProvider;
import com.crazyclimbersteam.horeca.activity.detail.view.DetailHeaderView;
import com.crazyclimbersteam.horeca.net.pojo.DetailItemModel;

/**
 * @author Mirash
 */
public class DetailsActivity extends ActionBarActivity implements IDetailDataProvider {
    public static String TAG = DetailsActivity.class.getSimpleName();
    public static final String ITEM_DETAIL_KEY = "item_details";

    private DetailsDataProvider mDataProvider;
    private ParallaxHeaderPagerView mPagerView;
    private View mHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity_layout);
        initParallaxHeaderPagerView();
        initDataProvider();
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            DetailItemModel itemModel = (DetailItemModel) extra.getSerializable(ITEM_DETAIL_KEY);
            if (itemModel != null) {
                mDataProvider.setDetailItem(itemModel);
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mDataProvider.getDetailItem() != null) {
            getSupportActionBar().setTitle(mDataProvider.getDetailItem().getName());
        } else {
            getSupportActionBar().setTitle("Dude, where is my name?!");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataProvider.clear();
    }

    private void initDataProvider() {
        mDataProvider = new DetailsDataProvider();
        mDataProvider.updateAllData();
    }

    private void initParallaxHeaderPagerView() {
        mPagerView = (ParallaxHeaderPagerView) findViewById(R.id.detail_parallax_header_pager_view);
        mPagerView.setPagerAdapter(new DetailsPagerAdapter(getSupportFragmentManager()));
        mHeaderView = createHeaderView();
        mPagerView.setHeaderView(mHeaderView, getResources().getDimensionPixelSize(R.dimen.details_screen_header_height));
        mPagerView.setMinHeaderHeight(getResources().getDimensionPixelSize(R.dimen.tab_height_default));
        mPagerView.setHeaderParallaxWidth(getResources().getDimension(R.dimen.details_screen_header_parallax_margin));
        mPagerView.setCallbacks(new ParallaxHeaderPagerView.ICallbacks() {
            @Override
            public void onVerticalScroll(float ratio) {
            }

            @Override
            public void onPageScrolled(int position, float pageRatio, int scrollX, float totalRatio) {
            }

            @Override
            public void onPageSelected(int position) {
            }
        });
    }

    private View createHeaderView() {
        View view = new View(this);
        view.setBackground(getResources().getDrawable(R.drawable.test_image_space));
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelSize(R.dimen.details_screen_header_height)));
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public DetailsDataProvider getDataProvider() {
        return mDataProvider;
    }
}
