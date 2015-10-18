package com.crazyclimbersteam.horeca.activity.detail.fragment.feedback;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author Mirash
 */
public class FeedbackAdapter extends BaseAdapter {
    public static final FeedbackItemModel EMPTY_ITEM_MODEL = null;
    private View mTopView;
    private List<FeedbackItemModel> mItems;
    private Context mContext;

    public FeedbackAdapter(Context context, View topView, List<FeedbackItemModel> objects) {
        mContext = context;
        mItems = objects;
        mTopView = topView;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public FeedbackItemModel getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        //TODO some kind of hack but let it b^^
        return mItems.get(position) == EMPTY_ITEM_MODEL ? 0 : 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItem(position) == EMPTY_ITEM_MODEL) {
            return mTopView;
        }
        FeedbackItemView itemView = convertView == null ?
                new FeedbackItemView(mContext) : (FeedbackItemView) convertView;
        FeedbackItemModel item = getItem(position);
        itemView.setIcon(item.getIcon());
        itemView.setRating(item.getRating());
        itemView.setName(item.getName());
        itemView.setComment(item.getMessage());
        return itemView;
    }
}
