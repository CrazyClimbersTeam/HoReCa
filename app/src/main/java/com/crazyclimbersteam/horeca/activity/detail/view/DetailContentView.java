package com.crazyclimbersteam.horeca.activity.detail.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @author Mirash
 */
public class DetailContentView extends FrameLayout {
    private ViewPager mViewPager;

    public DetailContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

    }
}
