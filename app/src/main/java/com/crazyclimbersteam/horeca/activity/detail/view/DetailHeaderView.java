package com.crazyclimbersteam.horeca.activity.detail.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.tools.ParallaxView;

/**
 * @author Mirash
 */
public class DetailHeaderView extends FrameLayout {
    private ParallaxView mParallaxView;
    private ImageView mImageView;
    private float mLeftOffset;

    public DetailHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.details_header, this);
        mLeftOffset = getResources().getDimension(R.dimen.details_screen_header_parallax_margin);
        mParallaxView = (ParallaxView) findViewById(R.id.details_parallax_container);
        mParallaxView.setParallaxValue(1);
        mImageView = (ImageView) findViewById(R.id.details_header_image);
    }

    public void setContentView(View view) {
        removeAllViews();
        addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setImageId(int drawableId) {
        mImageView.setImageResource(drawableId);
    }

    public void setLeftOffset(float offsetValue) {
        mParallaxView.setOffset(-mLeftOffset * (offsetValue - 1));
    }

    public void setTopOffset(float offsetValue) {
        setTranslationY(-offsetValue * 0.25f);
    }
}
