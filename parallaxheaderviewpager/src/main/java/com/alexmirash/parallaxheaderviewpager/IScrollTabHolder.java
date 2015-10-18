package com.alexmirash.parallaxheaderviewpager;

public interface IScrollTabHolder {

    void adjustScroll(int scrollHeight);

    void onScroll(int scrollY, int pagePosition);

    int getHeaderHeight();

    int getMinHeaderHeight();
}
