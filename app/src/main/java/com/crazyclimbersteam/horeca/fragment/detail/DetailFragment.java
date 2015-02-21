package com.crazyclimbersteam.horeca.fragment.detail;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.adapter.DetailItemAdapter;

import java.util.List;

/**
 * Created by Марковка on 21.02.2015.
 */
public class DetailFragment extends Fragment {

    private ListView detailItemsList;
    private DetailItemAdapter adapter;
    private List<DetailItemModel> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.detail_fragmet_layout, container, false);
        detailItemsList = (ListView)root.findViewById(R.id.items_list);
        adapter = new DetailItemAdapter(getActivity(), items);
        detailItemsList.setAdapter(adapter);

        detailItemsList.setOnItemClickListener(new ClickListener());

        return root;
    }

    private class ClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //startActivity(new Intent(getActivity().this, Detail));
        }
    }
}
