package com.crazyclimbersteam.horeca.fragment.detail;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.DetailsActivity;
import com.crazyclimbersteam.horeca.adapter.DetailItemAdapter;
import com.crazyclimbersteam.horeca.fragment.base.BaseFragment;
import com.crazyclimbersteam.horeca.net.pojo.DetailItemModel;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Path;

/**
 * Created by Марковка on 21.02.2015.
 */
public class RequestSearchFragment extends BaseFragment {

    private static final String KEY_SEARCH_QUERY = "KEY_SEARCH_QUERY";
    public static final String BASE_URL = "http://192.168.101.22:8088";

    private ListView detailItemsList;
    private DetailItemAdapter adapter;
    private List<DetailItemModel> items;

    private String searchQuery;
    private IRequest service;

    public static RequestSearchFragment newInstance(String query) {
        RequestSearchFragment fragment = new RequestSearchFragment();
        Bundle args = new Bundle();
        args.putString(KEY_SEARCH_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchQuery = getArguments().getString(KEY_SEARCH_QUERY);
        startSearch(searchQuery);
        // TODO Start querying backend

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
//
        service = restAdapter.create(IRequest.class);

        service.listPlaces(new Callback<List<DetailItemModel>>() {
            @Override
            public void success(List<DetailItemModel> detailItemModels, Response response) {
                items = detailItemModels;
                adapter = new DetailItemAdapter(getActivity());

                adapter.setDataToAdapter(items);
                Log.d("Detail", ""+detailItemModels.size());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Detail", ""+error.getMessage());
            }
        });
//
//       String repos = service.getId("id");

    }

//    void runTask(){
//        new Task().execute();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.search_fragment, container, false);
        detailItemsList = (ListView) root.findViewById(R.id.items_list);
//        adapter = new DetailItemAdapter(getActivity(), items);
        detailItemsList.setAdapter(adapter);

        detailItemsList.setOnItemClickListener(new ClickListener());
//        runTask();
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

    private class Task extends AsyncTask<Void, Void, List<DetailItemModel>>{

        @Override
        protected List<DetailItemModel> doInBackground(Void... params) {


            List <DetailItemModel> list = service.listPlaces();
            return list;
        }

        @Override
        protected void onPostExecute(List<DetailItemModel> list) {
            List<DetailItemModel> l = list;
        }
    }

    public void startSearch(String searchQuery) {
        // TODO Start querying backend (separate thread)
    }
}
