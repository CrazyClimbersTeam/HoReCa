package com.crazyclimbersteam.horeca.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.crazyclimbersteam.horeca.R;


/**
 * @author Mirash
 */
public class ParallaxView extends FrameLayout {

    private static final float DEFAULT_PARALLAX_VALUE = 0.25f;

    private float mParallaxValue;

    public ParallaxView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParallaxView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ParallaxView);
            mParallaxValue = a.getFloat(R.styleable.ParallaxView_parallaxValue, DEFAULT_PARALLAX_VALUE);
            a.recycle();
        }
    }

    public void setParallaxValue(float parallaxValue) {
        mParallaxValue = parallaxValue;
    }

    public void setOffset(float offset) {
        setTranslationX(offset * mParallaxValue);
    }
}
