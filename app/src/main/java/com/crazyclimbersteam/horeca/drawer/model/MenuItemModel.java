package com.crazyclimbersteam.horeca.drawer.model;

import android.content.Context;
import android.content.res.Resources;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.MainActivity;
import com.crazyclimbersteam.horeca.drawer.views.MenuItemView;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;
import com.crazyclimbersteam.horeca.fragment.main.CategoriesFragment;
import com.crazyclimbersteam.horeca.fragment.settings.SettingsFragment;

/**
 * @author Mirash
 */
public enum MenuItemModel implements MenuNavigable<MainActivity> {
    CATEGORIES {
        @Override
        public String getTitle(Resources resources) {
            return "Categories";
        }

        @Override
        public int getIconResourceId() {
            return R.drawable.navigation_menu_item_category_selector;
        }

        @Override
        protected BaseFragment getFragment() {
            return new CategoriesFragment();
        }

        @Override
        public String getTag() {
            return CategoriesFragment.TAG;
        }
    },
    POPULAR {
        @Override
        public String getTitle(Resources resources) {
            return "Popular";
        }

        @Override
        public int getIconResourceId() {
            return R.drawable.navigation_menu_item_popular_selector;
        }

        @Override
        protected BaseFragment getFragment() {
            return new CategoriesFragment();
        }

        @Override
        public String getTag() {
            return CategoriesFragment.TAG;
        }
    },
    FAVOURITE {
        @Override
        public String getTitle(Resources resources) {
            return "Favourite";
        }

        @Override
        public int getIconResourceId() {
            return R.drawable.navigation_menu_item_favourite_selector;
        }

        @Override
        protected BaseFragment getFragment() {
            return new CategoriesFragment();
        }

        @Override
        public String getTag() {
            return CategoriesFragment.TAG;
        }
    },
    SPECIAL {
        @Override
        public String getTitle(Resources resources) {
            return "Special propositions";
        }

        @Override
        public int getIconResourceId() {
            return R.drawable.navigation_menu_item_special_selector;
        }

        @Override
        protected BaseFragment getFragment() {
            return new CategoriesFragment();
        }

        @Override
        public String getTag() {
            return CategoriesFragment.TAG;
        }
    },
    SETTINGS {
        @Override
        public String getTitle(Resources resources) {
            return "Settings";
        }

        @Override
        public int getIconResourceId() {
            return R.drawable.navigation_menu_item_settings_selector;
        }

        @Override
        protected BaseFragment getFragment() {
            return new SettingsFragment();
        }

        @Override
        public String getTag() {
            return SettingsFragment.TAG;
        }
    };

    @Override
    public MenuItemView createMenuItem(Context context) {
        MenuItemView item = new MenuItemView(context);
        item.setTitle(getTitle(context.getResources()));
        item.setImageResource(getIconResourceId());
        return item;
    }

    @Override
    public void handleItemClick(MainActivity activity) {
        activity.navigateToScreenFragment(getFragment());
    }

    protected BaseFragment getFragment() {
        return null;
    }
}
