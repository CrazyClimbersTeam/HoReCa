package com.crazyclimbersteam.horeca.activity.detail.fragment.feedback;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * @author Mirash
 */
public class FeedbackAdapter extends ArrayAdapter<FeedbackItemModel> {
    public FeedbackAdapter(Context context, int resource, List<FeedbackItemModel> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeedbackItemView itemView = convertView == null ?
                new FeedbackItemView(getContext()) : (FeedbackItemView) convertView;
        FeedbackItemModel item = getItem(position);
        itemView.setIcon(item.getIcon());
        itemView.setRating(item.getRating());
        itemView.setName(item.getName());
        itemView.setComment(item.getMessage());
        return itemView;
    }
}
