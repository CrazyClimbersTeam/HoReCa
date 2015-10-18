package com.alexmirash.parallaxheaderviewpager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;

public abstract class TabHolderAbsListFragment<T extends AbsListView> extends ScrollTabHolderFragment implements OnScrollListener {
    protected FrameLayout mRootView;
    protected T mRootScrollingView;

    abstract protected T createScrollingRootView(LayoutInflater inflater);

    @Override
    public final View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        mRootView = new FrameLayout(getActivity());
        mRootScrollingView = createScrollingRootView(inflater);
        applyScrollingHeaderHolder(mRootScrollingView, getHeaderHeight());
        mRootView.addView(mRootScrollingView);
        onCreateViewContent(inflater, savedInstanceState);
        return mRootView;
    }

    abstract protected void applyScrollingHeaderHolder(T scrollingView, int headerHeight);

    abstract protected int getScrollY(AbsListView view);

    protected void onCreateViewContent(LayoutInflater inflater, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRootScrollingView.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mScrollTabHolder != null) {
            mScrollTabHolder.onScroll(getScrollY(view), mPosition);
        }
    }
}