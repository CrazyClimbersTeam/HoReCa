package com.alexmirash.parallaxheaderviewpager.fragment;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;

import com.alexmirash.parallaxheaderviewpager.view.headergridview.HeaderGridView;

public abstract class TabHolderGridFragment<T extends HeaderGridView> extends TabHolderAbsListFragment<T> implements OnScrollListener {

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
        mRootScrollingView.setSelectionFromTop(0, scrollHeight - getHeaderHeight());
    }
}