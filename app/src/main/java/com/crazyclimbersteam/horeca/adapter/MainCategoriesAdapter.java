package com.crazyclimbersteam.horeca.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.crazyclimbersteam.horeca.net.pojo.Category;

import java.util.List;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 21.02.15
 * Time: 14:44
 */
public class MainCategoriesAdapter extends BaseAdapter {

    Context context;

    List<Category> categoryList;

    public MainCategoriesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
