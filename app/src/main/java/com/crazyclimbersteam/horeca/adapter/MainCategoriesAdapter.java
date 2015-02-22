package com.crazyclimbersteam.horeca.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.net.pojo.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Android Studio.
 * User: Vitalii Morozov
 * Date: 21.02.15
 * Time: 14:44
 */
public class MainCategoriesAdapter extends RecyclerView.Adapter<MainCategoriesAdapter.ViewHolder> {

    private List<Category> categoryList;

    private ViewHolder.OnCategoryClickListener onClickListener;

    public MainCategoriesAdapter(ViewHolder.OnCategoryClickListener listener) {
        this.onClickListener = listener;
        categoryList = new ArrayList<>();
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        ViewHolder holder = new ViewHolder(v, onClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.categoryName.setText(categoryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView categoryName;
        ImageView categoryImage;
        OnCategoryClickListener onClickListener;

        public ViewHolder(View itemView, OnCategoryClickListener listener) {
            super(itemView);
            this.categoryName = (TextView) itemView.findViewById(R.id.category_name);
            this.categoryImage = (ImageView) itemView.findViewById(R.id.category_image);
            this.onClickListener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onClickListener != null) {
                onClickListener.onCategoryClick(getPosition());
            }
        }

        public interface OnCategoryClickListener {
            public void onCategoryClick(int categoryId);
        }
    }
}
