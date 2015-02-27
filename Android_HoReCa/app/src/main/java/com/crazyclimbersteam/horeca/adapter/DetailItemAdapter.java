package com.crazyclimbersteam.horeca.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.net.pojo.DetailItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Марковка on 21.02.2015.
 */
public class DetailItemAdapter extends BaseAdapter {

    private Context context;
    private List<DetailItemModel> items;
    private LayoutInflater inflater;

    public DetailItemAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
        inflater = LayoutInflater.from(context);

    }

    public void setDataToAdapter(List<DetailItemModel> list) {
        items = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public DetailItemModel getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(DetailItemModel item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        DetailItemModel oneItemModel = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.detail_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title_text_view);
            viewHolder.rating = (RatingBar) convertView.findViewById(R.id.rating);
            viewHolder.distance = (TextView) convertView.findViewById(R.id.distance_view);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (oneItemModel != null) {
            viewHolder.title.setText(oneItemModel.getName());
            viewHolder.rating.setRating((oneItemModel.getRating()));
            float distance = oneItemModel.getDistance();
            String distanceText;
            if (distance >= 1000) {
                distanceText = String.format("%.1f", distance / 1000f) + " km";
            } else {
                distanceText = String.valueOf(Math.round(distance)) + " m";
            }
            viewHolder.distance.setText(distanceText);
        }


        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        RatingBar rating;
        TextView distance;
    }
}
