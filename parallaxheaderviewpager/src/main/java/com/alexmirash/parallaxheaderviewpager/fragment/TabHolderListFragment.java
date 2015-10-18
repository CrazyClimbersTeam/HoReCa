package com.alexmirash.parallaxheaderviewpager.fragment;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ListView;

public abstract class TabHolderListFragment<T extends ListView> extends TabHolderAbsListFragment<T> implements OnScrollListener {

    protected void applyScrollingHeaderHolder(T scrollingView, int headerHeight) {
        FrameLayout placeHolderView = new FrameLayout(getActivity());
        placeHolderView.setPadding(0, getHeaderHeight(), 0, 0);
        scrollingView.addHeaderView(placeHolderView);
    }

    protected int getScrollY(AbsListView view) {
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
    public void adjustScroll(int scrollHeight) {
        if (scrollHeight == 0 && mRootScrollingView.getFirstVisiblePosition() >= 1) {
            return;
        }
        mRootScrollingView.setSelectionFromTop(1, scrollHeight);
    }
}