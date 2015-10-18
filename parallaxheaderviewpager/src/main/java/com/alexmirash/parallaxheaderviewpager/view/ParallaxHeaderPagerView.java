package com.alexmirash.parallaxheaderviewpager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.alexmirash.parallaxheaderviewpager.IScrollTabHolder;
import com.alexmirash.parallaxheaderviewpager.R;
import com.alexmirash.parallaxheaderviewpager.TabPagerAdapter;
import com.alexmirash.parallaxheaderviewpager.view.tabstrip.PagerSlidingTabStrip;
import com.nineoldandroids.view.ViewHelper;

import static com.alexmirash.parallaxheaderviewpager.util.MirashUtils.clamp;

/**
 * @author Mirash
 */
public class ParallaxHeaderPagerView extends FrameLayout implements IScrollTabHolder, ViewPager.OnPageChangeListener {
    protected ViewGroup mHeaderContainer;
    protected View mHeader;

    protected ViewPager mViewPager;
    protected PagerSlidingTabStrip mPagerSlidingTabStrip;
    protected TabPagerAdapter mPagerAdapter;

    protected Attributes mAttrs;

    protected ICallbacks mCallbacks;

    public ParallaxHeaderPagerView(Context context) {
        this(context, null);
    }

    public ParallaxHeaderPagerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParallaxHeaderPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mAttrs = new Attributes();
        int tabStripLayoutId = R.layout.default_tab_strip;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ParallaxHeaderPagerView);
            setMinHeaderHeight(a.getDimensionPixelSize(R.styleable.ParallaxHeaderPagerView_minHeaderHeight, 0));
            setHeaderParallaxHeightFactor(a.getFloat(R.styleable.ParallaxHeaderPagerView_parallaxHeightFactor, 0));
            setHeaderParallaxWidth(a.getDimensionPixelSize(R.styleable.ParallaxHeaderPagerView_parallaxWidth, 0), false);
            setTabUnderHeader(a.getBoolean(R.styleable.ParallaxHeaderPagerView_isTabUnderHeader, false));
            tabStripLayoutId = a.getResourceId(R.styleable.ParallaxHeaderPagerView_tabStripLayout, R.layout.default_tab_strip);
            a.recycle();
        }
        init(context, tabStripLayoutId);
    }

    private void init(Context context, int tabStripLayoutId) {
        inflate(context, R.layout.parallax_header_pager_view, this);
        mHeaderContainer = (ViewGroup) findViewById(R.id.header_container);
        inflate(context, tabStripLayoutId, mHeaderContainer);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) mHeaderContainer.getChildAt(0);
        mViewPager = (ViewPager) findViewById(R.id.pager);
    }

    public void setHeaderView(View header, int headerHeight) {
        if (mHeader != null) {
            mHeaderContainer.removeView(mHeader);
        }
        mHeader = header;
        mHeaderContainer.addView(header, 0);
        mAttrs.setHeaderHeight(headerHeight);
        if (mAttrs.isTabUnderHeader()) {
            updateTabUnderHeaderMode();
        }
    }

    public void setPagerAdapter(TabPagerAdapter adapter) {
        mPagerAdapter = adapter;
        mPagerAdapter.setTabHolderScrollingContent(this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(mPagerAdapter.getCount());
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mPagerSlidingTabStrip.setOnPageChangeListener(this);
    }

    public void setMinHeaderHeight(int minHeaderHeight) {
        mAttrs.setMinHeaderHeight(minHeaderHeight);
    }

    public void setHeaderParallaxHeightFactor(float parallaxFactor) {
        mAttrs.setParallaxHeightFactor(parallaxFactor);
    }

    public void setHeaderParallaxWidth(float parallaxWidth) {
        setHeaderParallaxWidth(parallaxWidth, true);
    }

    public void setHeaderParallaxWidth(float parallaxWidth, boolean isSetHeaderNegativeRightMargin) {
        mAttrs.setParallaxWidth(parallaxWidth);
        if (isSetHeaderNegativeRightMargin) {
            ((MarginLayoutParams) mHeader.getLayoutParams()).rightMargin = (int) -parallaxWidth;
        }
    }

    public void setTabUnderHeader(boolean isTabUnderHeader) {
        if (mAttrs.isTabUnderHeader() != isTabUnderHeader) {
            mAttrs.setTabUnderHeader(isTabUnderHeader);
            if (mHeader != null) {
                updateTabUnderHeaderMode();
            }
        }
    }

    private void updateTabUnderHeaderMode() {
        int tabHeight = mPagerSlidingTabStrip.getLayoutParams().height;
        ((LayoutParams) mHeader.getLayoutParams()).bottomMargin = tabHeight;
        mAttrs.setHeaderHeight(mAttrs.getHeaderHeight() + tabHeight);
    }

    public PagerSlidingTabStrip getSlidingTabStrip() {
        return mPagerSlidingTabStrip;
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    @Override
    public int getHeaderHeight() {
        return mAttrs.getHeaderHeight();
//        return mHeaderContainer.getHeight();
    }

    @Override
    public int getMinHeaderHeight() {
        return mAttrs.getMinHeaderHeight();
    }

    //IScrollTabHolder~
    @Override
    public void onScroll(int scrollY, int pagePosition) {
        if (mViewPager.getCurrentItem() == pagePosition) {
            int maxTranslationY = mAttrs.getMinHeaderHeight() - getHeaderHeight();
            ViewHelper.setTranslationY(mHeaderContainer, Math.max(-scrollY, maxTranslationY));
            float ratio = maxTranslationY != 0 ?
                    clamp(ViewHelper.getTranslationY(mHeaderContainer) / maxTranslationY, 0f, 1f) : 1f;
            if (mAttrs.getParallaxHeightFactor() > 0 && (scrollY < -maxTranslationY)) {
                mHeader.setTranslationY(scrollY * mAttrs.getParallaxHeightFactor());
            }
            if (mCallbacks != null) {
                mCallbacks.onVerticalScroll(ratio);
            }
        }
    }

    @Override
    public void adjustScroll(int scrollHeight) {
    }
    //~

    //OnPageChangeListener~
    @Override
    public void onPageScrolled(int position, float pageRatio, int scrollX) {
        float totalRatio = mPagerAdapter.getCount() > 1 ? (position + pageRatio) / (mPagerAdapter.getCount() - 1) : 0f;
        if (mAttrs.getParallaxWidth() > 0) {
            mHeader.setTranslationX(-mAttrs.getParallaxWidth() * totalRatio);
        }
        if (mCallbacks != null) {
            mCallbacks.onPageScrolled(position, pageRatio, scrollX, totalRatio);
        }
    }

    @Override
    public void onPageSelected(int position) {
        IScrollTabHolder currentHolder = mPagerAdapter.getScrollTabHolder(position);
        currentHolder.adjustScroll((int) (getHeaderHeight() + ViewHelper.getTranslationY(mHeaderContainer)));
        if (mCallbacks != null) {
            mCallbacks.onPageSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
    }
    //~

    public void setCallbacks(ICallbacks callbacks) {
        mCallbacks = callbacks;
    }

    public interface ICallbacks {
        void onVerticalScroll(float ratio);

        void onPageScrolled(int position, float pageRatio, int scrollX, float totalRatio);

        void onPageSelected(int position);
    }
}
