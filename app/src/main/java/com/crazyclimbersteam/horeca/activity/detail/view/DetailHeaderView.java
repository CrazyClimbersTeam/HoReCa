package com.crazyclimbersteam.horeca.activity.detail.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
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

    public DetailHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.details_header, this);
        mParallaxView = (ParallaxView) findViewById(R.id.details_parallax_container);
        mImageView = (ImageView) findViewById(R.id.details_header_image);
    }

    public void setImage(Drawable drawable) {
        mImageView.setImageDrawable(drawable);
    }
}
