package com.crazyclimbersteam.horeca.activity.detail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.crazyclimbersteam.horeca.R;

/**
 * @author Mirash
 */
public class DetailHeaderView extends FrameLayout {
    private ImageView mImageView;

    public DetailHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.details_header, this);
        mImageView = (ImageView) findViewById(R.id.details_header_image);
    }

    public void setContentView(View view) {
        removeAllViews();
        addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setImageId(int drawableId) {
        mImageView.setImageResource(drawableId);
    }

}
