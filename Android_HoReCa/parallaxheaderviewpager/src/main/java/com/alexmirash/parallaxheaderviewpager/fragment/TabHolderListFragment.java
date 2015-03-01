package com.alexmirash.parallaxheaderviewpager.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ListView;

public abstract class TabHolderListFragment<T extends ListView> extends ScrollTabHolderFragment implements OnScrollListener {
    protected FrameLayout mRootView;
    protected T mListView;

    abstract protected T createListView(LayoutInflater inflater);

    @Override
    public final View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        mRootView = new FrameLayout(getActivity());
        mListView = createListView(inflater);
        FrameLayout placeHolderView = new FrameLayout(getActivity());
        placeHolderView.setPadding(0, getHeaderHeight(), 0, 0);
        mListView.addHeaderView(placeHolderView);
        mRootView.addView(mListView);
        onCreateViewContent(inflater, savedInstanceState);
        return mRootView;
    }

    protected void onCreateViewContent(LayoutInflater inflater, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView.setOnScrollListener(this);
    }

    @Override
    public void adjustScroll(int scrollHeight) {
        if (scrollHeight == 0 && mListView.getFirstVisiblePosition() >= 1) {
            return;
        }
        mListView.setSelectionFromTop(1, scrollHeight);
    }

    private int getScrollY(AbsListView view) {
        View c = view.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();
        int headerHeight = 0;
        if (firstVisiblePosition >= 1) {
            headerHeight = getHeaderHeight();
        }
        return firstVisiblePosition * c.getHeight() + headerHeight - top;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // nothing
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mScrollTabHolder != null) {
            mScrollTabHolder.onScroll(getScrollY(view), mPosition);
        }
    }
}