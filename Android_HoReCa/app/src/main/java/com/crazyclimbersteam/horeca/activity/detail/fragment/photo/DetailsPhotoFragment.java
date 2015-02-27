package com.crazyclimbersteam.horeca.activity.detail.fragment.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.dataprovider.DetailsListUpdateListener;
import com.crazyclimbersteam.horeca.activity.detail.fragment.DetailsTabFragment;
import com.crazyclimbersteam.horeca.utils.LogUtils;

import java.util.List;

/**
 * @author Mirash
 */
public class DetailsPhotoFragment extends DetailsTabFragment implements AdapterView.OnItemClickListener, DetailsListUpdateListener<DetailPhotoAdapter.Item> {
    private GridView mGridView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_tab_photos, null);
        mGridView = (GridView) rootView.findViewById(R.id.details_tab_photo_grid);
        mGridView.setOnItemClickListener(this);
        updateData(mDataProvider.getPhotos());
        mDataProvider.addPhotoUpdateObserver(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDataProvider.removePhotoUpdateObserver(this);
    }

    public void updateData(List<DetailPhotoAdapter.Item> photos) {
        mGridView.setAdapter(new DetailPhotoAdapter(mGridView.getContext(), photos));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LogUtils.log("photo on item click " + position);
    }

    @Override
    public void onDataUpdate(List<DetailPhotoAdapter.Item> feedbackItemModelList) {
        updateData(feedbackItemModelList);
    }
}
