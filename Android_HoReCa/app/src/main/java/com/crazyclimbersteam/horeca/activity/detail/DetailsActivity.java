package com.crazyclimbersteam.horeca.activity.detail;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.adapter.DetailsAdapter;
import com.crazyclimbersteam.horeca.activity.detail.dataprovider.DetailsDataProvider;
import com.crazyclimbersteam.horeca.activity.detail.view.DetailContentView;
import com.crazyclimbersteam.horeca.activity.detail.view.DetailHeaderView;
import com.crazyclimbersteam.horeca.net.pojo.DetailItemModel;

import java.util.ArrayList;
import java.util.List;

import static com.crazyclimbersteam.horeca.utils.LogUtils.log;

/**
 * @author Mirash
 */
public class DetailsActivity extends ActionBarActivity implements DetailContentView.IDetailCallbacks {
    public static String TAG = DetailsActivity.class.getSimpleName();
    public static final int DETAIL_SCREENS_COUNT = 3;
    public static final String ITEM_DETAIL_KEY = "item_details";

    private DetailContentView mContentView;
    private DetailHeaderView mHeaderView;

    private DetailsDataProvider mDataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity_layout);
        mContentView = (DetailContentView) findViewById(R.id.details_content);
        mHeaderView = (DetailHeaderView) findViewById(R.id.details_header);
        initContentView();
        initDataProvider();
//        initScrollContainer();

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

    private void initContentView() {
        List<String> titles = new ArrayList<>(DETAIL_SCREENS_COUNT);
        titles.add(getString(R.string.details_info_tab_title));
        titles.add(getString(R.string.details_feedback_tab_title));
        titles.add(getString(R.string.details_photos_tab_title));
        mContentView.setPagerAdapter(new DetailsAdapter(getSupportFragmentManager(), titles));
        mContentView.setCallbacksListener(this);
    }

/*    private void initScrollContainer() {
        final ScrollView scrollView = (ScrollView) findViewById(R.id.details_scroll_container);
        final float headerHeight = getResources().getDimension(R.dimen.details_screen_header_height);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                log("scroll = " + scrollY);
                if (scrollY < headerHeight) {
                    mHeaderView.setTopOffset(scrollY);
                }
            }
        });
    }*/

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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        log("onPageScrolled: " + position + ", " + positionOffset + ", " + positionOffsetPixels);
        mHeaderView.setLeftOffset(position + positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    public DetailsDataProvider getDataProvider() {
        return mDataProvider;
    }
}
