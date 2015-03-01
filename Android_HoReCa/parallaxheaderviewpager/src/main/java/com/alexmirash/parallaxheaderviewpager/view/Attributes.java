package com.alexmirash.parallaxheaderviewpager.view;

/**
 * @author Mirash
 */
public class Attributes {
    private int mMinHeaderHeight;
    private float mParallaxHeightFactor;
    private float mParallaxWidth;
    private int mHeaderHeight;
    private boolean mIsTabUnderHeader;

    int getHeaderHeight() {
        return mHeaderHeight;
    }

    public void setHeaderHeight(int headerHeight) {
        mHeaderHeight = headerHeight;
    }

    int getMinHeaderHeight() {
        return mMinHeaderHeight;
    }

    public void setMinHeaderHeight(int minHeaderTranslation) {
        mMinHeaderHeight = minHeaderTranslation;
    }

    public void setParallaxHeightFactor(float parallaxFactor) {
        mParallaxHeightFactor = parallaxFactor;
    }

    public void setParallaxWidth(float parallaxWidth) {
        mParallaxWidth = parallaxWidth;
    }

    float getParallaxHeightFactor() {
        return mParallaxHeightFactor;
    }

    float getParallaxWidth() {
        return mParallaxWidth;
    }

    boolean isTabUnderHeader() {
        return mIsTabUnderHeader;
    }

    public void setTabUnderHeader(boolean isTabOverlapHeader) {
        mIsTabUnderHeader = isTabOverlapHeader;
    }

}
