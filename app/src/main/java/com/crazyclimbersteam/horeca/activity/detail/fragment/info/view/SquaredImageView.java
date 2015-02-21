package com.crazyclimbersteam.horeca.activity.detail.fragment.info.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author Mirash
 */
public class SquaredImageView extends ImageView {
    public SquaredImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = getMeasuredWidth();
        setMeasuredDimension(w, w);
    }
}
