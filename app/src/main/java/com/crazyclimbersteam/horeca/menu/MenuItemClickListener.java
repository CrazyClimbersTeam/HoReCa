package com.crazyclimbersteam.horeca.menu;


import com.crazyclimbersteam.horeca.menu.model.MenuNavigable;
import com.crazyclimbersteam.horeca.menu.views.MenuItemView;

/**
 * @author Mirash
 */
public interface MenuItemClickListener {
    void onMenuItemClick(MenuNavigable menuNavigable, MenuItemView menuItem);
}
