package com.crazyclimbersteam.horeca.menu.model;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.MainActivity;
import com.crazyclimbersteam.horeca.activity.detail.DetailsActivity;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;
import com.crazyclimbersteam.horeca.fragment.main.CategoriesFragment;
import com.crazyclimbersteam.horeca.fragment.settings.SettingsFragment;
import com.crazyclimbersteam.horeca.menu.views.MenuItemView;

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
            return R.drawable.ferret;
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
            return R.drawable.ferret;
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
            return R.drawable.ferret;
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
            return R.drawable.ferret;
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
            return R.drawable.cartoon_ferret;
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
