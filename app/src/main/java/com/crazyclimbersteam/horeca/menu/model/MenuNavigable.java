package com.crazyclimbersteam.horeca.menu.model;

import android.content.Context;

import com.crazyclimbersteam.horeca.menu.views.MenuItemView;

/**
 * @author Mirash
 */
public interface MenuNavigable {
    String getTitle();

    int getIconResourceId();

    String getTag();

    MenuItemView createMenuItem(Context context);
}
