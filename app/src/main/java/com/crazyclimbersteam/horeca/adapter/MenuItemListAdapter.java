package com.crazyclimbersteam.horeca.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.net.pojo.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 22.02.15
 * Time: 05:57
 */
public class MenuItemListAdapter extends BaseAdapter {

    private List<MenuItem> menuItemList;

    public MenuItemListAdapter(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
        this.menuItemList = new ArrayList<MenuItem>() {
            {
                add(new MenuItem("Green Mexican", "30.00", "50 ml"));
                add(new MenuItem("Hoogaarden", "45.00", "0.5 l"));
                add(new MenuItem("Zlata Praha", "45.00", "0.5 l"));
            }
        };
    }

    @Override
    public int getCount() {
        return menuItemList.size();
    }

    @Override
    public MenuItem getItem(int position) {
        return menuItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list_item, parent, false);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            holder.itemName = (TextView) root.findViewById(R.id.item_name);
            holder.itemPrice = (TextView) root.findViewById(R.id.item_price);
            holder.itemPortion = (TextView) root.findViewById(R.id.item_portion);
            root.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.itemName.setText(getItem(position).getItemName());
        holder.itemPrice.setText(getItem(position).getItemPrice());
        holder.itemPortion.setText(getItem(position).getItemPortion());
        return root;
    }

    static class ViewHolder {
        TextView itemName;
        TextView itemPrice;
        TextView itemPortion;
    }
}
