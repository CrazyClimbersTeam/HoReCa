package com.crazyclimbersteam.horeca.drawer.views;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;


/**
 * @author Mirash
 */
public class MenuItemView extends FrameLayout {
    protected ImageView mIconView;
    protected TextView mTitleView;

    public MenuItemView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        inflate(context, getLayoutId(), this);
        mIconView = (ImageView) findViewById(R.id.menu_item_icon);
        mTitleView = (TextView) findViewById(R.id.menu_item_title);
        //initElements();
    }

    protected int getLayoutId() {
        return R.layout.navigation_menu_item;
    }

    public void setTitle(String title) {
        mTitleView.setText(title);
    }

    public void setImageResource(int resourceId) {
        mIconView.setImageResource(resourceId);
    }

}
