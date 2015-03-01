package com.alexmirash.parallaxheaderviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;

import com.alexmirash.parallaxheaderviewpager.fragment.ScrollTabHolderFragment;

import java.util.List;

/**
 * @author Mirash
 */
public abstract class TabPagerAdapter extends FragmentPagerAdapter {
    private SparseArrayCompat<IScrollTabHolder> mScrollTabHolders;
    private List<String> mTitles;
    private IScrollTabHolder mListener;
    private FragmentManager mFragmentManager;

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
        mTitles = getTitles();
        mScrollTabHolders = new SparseArrayCompat<>();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public Fragment getItem(int position) {
        ScrollTabHolderFragment fragment = getTabItem(position);
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
            fragment.setArguments(bundle);
        }
        bundle.putInt(ScrollTabHolderFragment.ARG_POSITION, position);

        mScrollTabHolders.put(position, fragment);
        if (mListener != null) {
            fragment.setScrollTabHolder(mListener);
        }
        return fragment;
    }

    public void setTabHolderScrollingContent(IScrollTabHolder listener) {
        mListener = listener;
        //rotation is a dangerous thing
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (fragments != null && !fragments.isEmpty()) {
            for (Fragment fr : fragments) {
                if (fr instanceof ScrollTabHolderFragment) {
                    ScrollTabHolderFragment tabHolderFragment = (ScrollTabHolderFragment) fr;
                    tabHolderFragment.setScrollTabHolder(mListener);
                    mScrollTabHolders.put(tabHolderFragment.getPosition(), tabHolderFragment);
                }
            }
        }
    }

    public IScrollTabHolder getScrollTabHolder(int position) {
        return mScrollTabHolders.valueAt(position);
    }

    protected abstract List<String> getTitles();

    protected abstract ScrollTabHolderFragment getTabItem(int position);

}