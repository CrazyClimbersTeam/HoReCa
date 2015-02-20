package com.crazyclimbersteam.horeca.menu.model;

import android.content.Context;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.menu.views.MenuItemView;

/**
 * @author Mirash
 */
public enum MenuItemModel implements MenuNavigable {
    ITEM_1 {
        @Override
        public String getTitle() {
            return "ITEM_1";
        }

        @Override
        public int getIconResourceId() {
            return R.drawable.ferret;
        }
    },
    ITEM_2 {
        @Override
        public String getTitle() {
            return "ITEM_2";
        }

        @Override
        public int getIconResourceId() {
            return R.drawable.cartoon_ferret;
        }
    };


    @Override
    public String getTag() {
        return name();
    }

    @Override
    public MenuItemView createMenuItem(Context context) {
        MenuItemView item = new MenuItemView(context);
        item.setTitle(getTitle());
        item.setImageResource(getIconResourceId());
        return item;
    }
}
