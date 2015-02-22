package com.crazyclimbersteam.horeca.fragment.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.adapter.MainCategoriesAdapter;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;
import com.crazyclimbersteam.horeca.net.pojo.Category;

import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.GridLayoutManager;
import org.lucasr.twowayview.widget.SpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirash
 */
public class CategoriesFragment extends BaseFragment {
    public static String TAG = getFragmentTag(CategoriesFragment.class);

    public static final int COLUMNS_COUNT = 2;
    private List<Category> categoryList;

    public interface CategoriesFragmentHost {
        public void onCategorySelected(String categoryName);
    }

    private RecyclerView gridView;
    private MainCategoriesAdapter adapter;

    private CategoriesFragmentHost host;
    private MainCategoriesAdapter.ViewHolder.OnCategoryClickListener onCategoryClickListener;

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    public CategoriesFragment() {
        // Nothing to do
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCategoryClickListener = new MainCategoriesAdapter.ViewHolder.OnCategoryClickListener() {
            @Override
            public void onCategoryClick(int categoryId) {
                host.onCategorySelected(categoryList.get(categoryId).getName());
            }
        };
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof CategoriesFragmentHost) {
            host = (CategoriesFragmentHost) activity;
        } else {
            throw new IllegalStateException("Activity should implement CategoriesFragmentHost to use the fragment!");
        }
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
        GridLayoutManager manager = new GridLayoutManager(TwoWayLayoutManager.Orientation.VERTICAL, COLUMNS_COUNT, COLUMNS_COUNT);
        gridView.setLayoutManager(manager);
        adapter = new MainCategoriesAdapter(onCategoryClickListener);
        gridView.setAdapter(adapter);
        int spacing = getActivity().getResources().getDimensionPixelSize(R.dimen.grid_item_spacing);
        gridView.addItemDecoration(new SpacingItemDecoration(spacing, spacing));
        categoryList = new ArrayList<Category>() {
            {
                add(new Category("url", "Beer", R.drawable.default_grid_image_beer));
                add(new Category("url", "Pizza", R.drawable.default_grid_image_pizza));
                add(new Category("url", "Sushi", R.drawable.default_grid_image_sushi));
                add(new Category("url", "Banana", R.drawable.default_grid_image_banana));
                add(new Category("url", "Hookah", R.drawable.default_grid_image_hookan));
                add(new Category("url", "Chokolate", R.drawable.default_grid_image_chokolate));
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
        adapter.setCategoryList(categoryList);
    }
}
