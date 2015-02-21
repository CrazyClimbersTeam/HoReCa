package com.crazyclimbersteam.horeca.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.adapter.MainCategoriesAdapter;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;
import com.crazyclimbersteam.horeca.net.pojo.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirash
 */
public class CategoriesFragment extends BaseFragment {
    public static String TAG = getFragmentTag(CategoriesFragment.class);

    public static final int COLUMNS_COUNT = 2;

    private RecyclerView gridView;

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    public CategoriesFragment() {
        // Nothing to do
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categories_fragment, container, false);
        initGridView(view);
        return view;
    }

    private void initGridView(View root) {
        gridView = (RecyclerView) root.findViewById(R.id.main_grid);
        gridView.setHasFixedSize(true);
        GridLayoutManager manager = new GridLayoutManager(getActivity().getBaseContext(), COLUMNS_COUNT);
        manager.offsetChildrenHorizontal(getResources().getDimensionPixelSize(R.dimen.grid_item_spacing));
        manager.offsetChildrenVertical(getResources().getDimensionPixelSize(R.dimen.grid_item_spacing));
        gridView.setLayoutManager(manager);
        MainCategoriesAdapter adapter = new MainCategoriesAdapter(getActivity().getBaseContext());
        gridView.setAdapter(adapter);
        List<Category> list = new ArrayList<Category>() {
            {
                add(new Category("url", "Category 1"));
                add(new Category("url", "Category 2"));
                add(new Category("url", "Category 3"));
                add(new Category("url", "Category 4"));
                add(new Category("url", "Category 5"));
                add(new Category("url", "Category 6"));
                add(new Category("url", "Category 7"));
                add(new Category("url", "Category 8"));
                add(new Category("url", "Category 9"));
                add(new Category("url", "Category 10"));
                add(new Category("url", "Category 11"));
                add(new Category("url", "Category 12"));
                add(new Category("url", "Category 13"));
                add(new Category("url", "Category 14"));
                add(new Category("url", "Category 15"));
                add(new Category("url", "Category 16"));
                add(new Category("url", "Category 17"));
            }
        };
        adapter.setCategoryList(list);
    }
}
