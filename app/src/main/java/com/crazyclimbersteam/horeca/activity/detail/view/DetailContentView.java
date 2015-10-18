package com.crazyclimbersteam.horeca.activity.detail.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.crazyclimbersteam.horeca.R;

/**
 * @author Mirash
 */
public class DetailContentView extends FrameLayout {
    private ViewPager mViewPager;
    private PagerTabStrip mHeader;
    private IDetailCallbacks mListener;

    public DetailContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.details_content, this);
        mViewPager = (ViewPager) findViewById(R.id.details_view_pager);
        mHeader = (PagerTabStrip) findViewById(R.id.details_pager_tab_strip);
        mHeader.setTabIndicatorColor(getResources().getColor(R.color.horec_theme));
        mHeader.setTextColor(getResources().getColor(R.color.horec_theme));
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mListener != null) {
                    mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (mListener != null) {
                    mListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void setPagerAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
    }

    public void setCallbacksListener(IDetailCallbacks listener) {
        mListener = listener;
    }

    public interface IDetailCallbacks {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);
    }
}
