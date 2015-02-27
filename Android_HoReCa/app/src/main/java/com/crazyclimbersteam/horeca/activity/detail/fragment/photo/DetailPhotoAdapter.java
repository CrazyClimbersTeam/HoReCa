package com.crazyclimbersteam.horeca.activity.detail.fragment.photo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.fragment.info.view.SquaredImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirash
 */
public class DetailPhotoAdapter extends BaseAdapter {
    private List<Item> mItems;
    private Context mContext;

    public DetailPhotoAdapter(Context context, List<Item> items) {
        mContext = context;
        mItems = items;
    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new SquaredImageView(mContext);
            ((ImageView) view).setScaleType(ImageView.ScaleType.FIT_XY);
        }
        Item item = getItem(i);
        ((ImageView) view).setImageDrawable(item.drawable);
        return view;
    }

    public static class Item {
        public final Drawable drawable;

        public Item(Drawable drawable) {
            this.drawable = drawable;
        }
    }
}
