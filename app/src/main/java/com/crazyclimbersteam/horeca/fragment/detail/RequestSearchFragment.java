package com.crazyclimbersteam.horeca.fragment.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.DetailsActivity;
import com.crazyclimbersteam.horeca.adapter.DetailItemAdapter;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;
import com.crazyclimbersteam.horeca.net.manager.HorecApiManager;
import com.crazyclimbersteam.horeca.net.pojo.DetailItemModel;
import com.crazyclimbersteam.horeca.utils.LogUtils;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Марковка on 21.02.2015.
 */
public class RequestSearchFragment extends BaseFragment {

    private static final String KEY_SEARCH_QUERY = "KEY_SEARCH_QUERY";

    private ListView detailItemsList;
    private DetailItemAdapter adapter;
    private List<DetailItemModel> items;

    private String searchQuery;
    private TextView emptyView;

    public static Bundle getArguments(String searchQuery) {
        Bundle args = new Bundle();
        args.putString(KEY_SEARCH_QUERY, searchQuery);
        return args;
    }

    public static RequestSearchFragment newInstance(String query) {
        RequestSearchFragment fragment = new RequestSearchFragment();
        fragment.setArguments(getArguments(query));
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchQuery = getArguments().getString(KEY_SEARCH_QUERY);
        startSearch(searchQuery);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.search_fragment, container, false);
        detailItemsList = (ListView) root.findViewById(R.id.items_list);
        detailItemsList.setOnItemClickListener(new ClickListener());
        emptyView = (TextView) root.findViewById(R.id.empty_view_search);
        detailItemsList.setEmptyView(emptyView);
        adapter = new DetailItemAdapter(getActivity());
        detailItemsList.setAdapter(adapter);
        return root;
    }

    private class ClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent callDetailIntent = new Intent(getActivity(), DetailsActivity.class);
//            callDetailIntent.putExtra();
            startActivity(callDetailIntent);
        }
    }

    public void startSearch(String searchQuery) {
        HorecApiManager.api.filterPlaces(searchQuery, new Callback<List<DetailItemModel>>() {
            @Override
            public void success(List<DetailItemModel> detailItemModels, Response response) {
                items = detailItemModels;
                adapter.setDataToAdapter(items);
                LogUtils.log("Got " + detailItemModels.size() + " items");
                if (items.size() == 0) {
                    emptyView.setText(getString(R.string.search_empty_list_message));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                LogUtils.log("Error: " + error.getMessage());
                if (error.getKind() == RetrofitError.Kind.NETWORK) {
                    emptyView.setText("Connection problems, please try again");
                } else {
                    emptyView.setText("Error occurred: " + error.getMessage());
                }
            }
        });
    }

    @Override
    public void onNewArguments(Bundle arguments) {
        String query = arguments.getString(KEY_SEARCH_QUERY);
        startSearch(query);
        LogUtils.log("Got new arguments " + query);
    }
}
