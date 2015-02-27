package com.crazyclimbersteam.horeca.fragment.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.adapter.MenuItemListAdapter;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;
import com.crazyclimbersteam.horeca.net.pojo.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 22.02.15
 * Time: 05:25
 */
public class MenuFragment extends BaseFragment {
    private static final String KEY_MENU_ITEM_LIST = "KEY_MENU_ITEM_LIST";

    private List<MenuItem> menuItemList;

    public static MenuFragment newInstance(ArrayList<MenuItem> menuItemList) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_MENU_ITEM_LIST, menuItemList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //noinspection unchecked
        menuItemList = (List<MenuItem>) getArguments().getSerializable(KEY_MENU_ITEM_LIST);
        View root = inflater.inflate(R.layout.menu_fragment, container, false);
        ListView listView = (ListView) root.findViewById(R.id.menu_items_list);
        MenuItemListAdapter adapter = new MenuItemListAdapter(menuItemList);
        listView.setAdapter(adapter);
        return root;
    }
}
