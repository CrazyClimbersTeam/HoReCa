package com.crazyclimbersteam.horeca.menu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.menu.views.MenuItemsContainer;


/**
 * @author Mirash
 */
//TODO implement me
public class NavigationMenuFragment extends Fragment {
    private MenuItemsContainer mMenuItemContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.drawer_layout, null);
        mMenuItemContainer = (MenuItemsContainer) rootView.findViewById(R.id.menu_item_container);
        return rootView;
    }

    public void setItemClickListener(MenuItemClickListener itemClickListener) {
        mMenuItemContainer.setMenuItemClickListener(itemClickListener);
    }
}
