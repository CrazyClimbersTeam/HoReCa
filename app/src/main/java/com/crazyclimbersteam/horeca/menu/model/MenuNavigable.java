package com.crazyclimbersteam.horeca.menu.model;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import com.crazyclimbersteam.horeca.menu.views.MenuItemView;

/**
 * @author Mirash
 */
public interface MenuNavigable<T extends Activity> {
    String getTitle(Resources res);

    int getIconResourceId();

    String getTag();

    MenuItemView createMenuItem(Context context);

    void handleItemClick(T activity);
}
