package com.crazyclimbersteam.horeca.drawer;


import android.app.Activity;

import com.crazyclimbersteam.horeca.drawer.model.MenuNavigable;
import com.crazyclimbersteam.horeca.drawer.views.MenuItemView;

/**
 * @author Mirash
 */
public interface MenuItemClickListener<T extends Activity> {
    void onMenuItemClick(MenuNavigable<T> menuNavigable, MenuItemView menuItem);
}
