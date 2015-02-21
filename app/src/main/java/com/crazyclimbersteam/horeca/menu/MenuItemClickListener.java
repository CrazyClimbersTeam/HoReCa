package com.crazyclimbersteam.horeca.menu;


import android.app.Activity;

import com.crazyclimbersteam.horeca.menu.model.MenuNavigable;
import com.crazyclimbersteam.horeca.menu.views.MenuItemView;

/**
 * @author Mirash
 */
public interface MenuItemClickListener<T extends Activity> {
    void onMenuItemClick(MenuNavigable<T> menuNavigable, MenuItemView menuItem);
}
