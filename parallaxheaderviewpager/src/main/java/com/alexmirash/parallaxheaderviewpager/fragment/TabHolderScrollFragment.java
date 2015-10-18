package com.alexmirash.parallaxheaderviewpager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

/**
 * @author Mirash
 */
public abstract class TabHolderScrollFragment<T extends ScrollView> extends ScrollTabHolderFragment
        implements ViewTreeObserver.OnScrollChangedListener {

    protected T mScrollView;

    abstract protected T createRootScrollView(LayoutInflater inflater);

    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        mScrollView = createRootScrollView(inflater);
        mScrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(this);
        final View contentView = createViewContent(inflater, savedInstanceState);
        contentView.setPadding(0, getHeaderHeight(), 0, 0);
        if (container != null) {
            contentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    contentView.setMinimumHeight(container.getHeight() + getHeaderHeight() - getMinHeaderHeight());
                }
            });
        }
        mScrollView.addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return mScrollView;
    }

    protected abstract View createViewContent(LayoutInflater inflater, Bundle savedInstanceState);

    @Override
    public void onScrollChanged() {
        if (mScrollTabHolder != null) {
            mScrollTabHolder.onScroll(mScrollView.getScrollY(), mPosition);
        }
    }

    @Override
    public void adjustScroll(int scrollHeight) {
        mScrollView.scrollTo(0, getHeaderHeight() - scrollHeight);
    }
}
