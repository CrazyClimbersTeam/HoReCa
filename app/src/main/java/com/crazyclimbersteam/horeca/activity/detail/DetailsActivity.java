package com.crazyclimbersteam.horeca.activity.detail;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.adapter.DetailsAdapter;
import com.crazyclimbersteam.horeca.activity.detail.view.DetailContentView;
import com.crazyclimbersteam.horeca.activity.detail.view.DetailHeaderView;

import java.util.ArrayList;
import java.util.List;

import static com.crazyclimbersteam.horeca.utils.LogUtils.log;

/**
 * @author Mirash
 */
public class DetailsActivity extends FragmentActivity implements DetailContentView.IDetailCallbacks {
    public static String TAG = DetailsActivity.class.getSimpleName();
    public static final int DETAIL_SCREENS_COUNT = 3;

    private DetailContentView mContentView;
    private DetailHeaderView mHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity_layout);
        mContentView = (DetailContentView) findViewById(R.id.details_content);
        mHeaderView = (DetailHeaderView) findViewById(R.id.details_header);
        initContentView();
    }

    private void initContentView() {
        List<String> titles = new ArrayList<>(DETAIL_SCREENS_COUNT);
        titles.add(getString(R.string.details_info_tab_title));
        titles.add(getString(R.string.details_feedback_tab_title));
        titles.add(getString(R.string.details_photos_tab_title));
        mContentView.setPagerAdapter(new DetailsAdapter(getSupportFragmentManager(), titles));
        mContentView.setCallbacksListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        log("onPageScrolled: " + position + ", " + positionOffset + ", " + positionOffsetPixels);
        mHeaderView.setLeftOffset(position + positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }
}
