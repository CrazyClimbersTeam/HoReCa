package com.crazyclimbersteam.horeca.menu.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.menu.MenuItemClickListener;
import com.crazyclimbersteam.horeca.menu.model.MenuItemModel;
import com.crazyclimbersteam.horeca.menu.model.MenuNavigable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mirash
 */
public class MenuItemsContainer extends LinearLayout {

    private List<MenuNavigable> mItems;
    private MenuItemClickListener mOnMenuItemClickListener;

    public MenuItemsContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        mItems = new ArrayList<>(MenuItemModel.values().length);
        Collections.addAll(mItems, MenuItemModel.values());
        boolean addSeparator = false;
        for (MenuNavigable menuNavigable : mItems) {
            if (addSeparator) {
                addSeparator();
            }
            addItem(menuNavigable);
            addSeparator = true;
        }
    }

    private void addItem(final MenuNavigable menuNavigable) {
        MenuItemView menuItemView = menuNavigable.createMenuItem(getContext());
        addView(menuItemView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        menuItemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnMenuItemClickListener != null) {
                    mOnMenuItemClickListener.onMenuItemClick(menuNavigable, (MenuItemView) v);
                }
            }
        });
    }

    private void addSeparator() {
        View separator = new View(getContext());
        separator.setBackgroundColor(getResources().getColor(R.color.menu_item_separator_color));
        addView(separator, new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getResources().getDimensionPixelSize(R.dimen.navigation_menu_separator_height)));
    }

    public void setMenuItemClickListener(MenuItemClickListener listener) {
        mOnMenuItemClickListener = listener;
    }
}
