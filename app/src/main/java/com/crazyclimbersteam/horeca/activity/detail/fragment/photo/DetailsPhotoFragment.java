package com.crazyclimbersteam.horeca.activity.detail.fragment.photo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alexmirash.parallaxheaderviewpager.fragment.TabHolderGridFragment;
import com.alexmirash.parallaxheaderviewpager.view.headergridview.HeaderGridView;
import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.detail.IDetailDataProvider;
import com.crazyclimbersteam.horeca.activity.detail.dataprovider.DetailsDataProvider;
import com.crazyclimbersteam.horeca.activity.detail.dataprovider.DetailsListUpdateListener;
import com.crazyclimbersteam.horeca.utils.LogUtils;

import java.util.List;

/**
 * @author Mirash
 */
public class DetailsPhotoFragment extends TabHolderGridFragment<HeaderGridView> implements IDetailDataProvider,
        AdapterView.OnItemClickListener, DetailsListUpdateListener<DetailPhotoAdapter.Item> {

    @Override
    protected HeaderGridView createScrollingRootView(LayoutInflater inflater) {
        HeaderGridView gridView = new HeaderGridView(getActivity());
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setNumColumns(2);
        gridView.setHorizontalSpacing(getResources().getDimensionPixelSize(R.dimen.detail_screen_photo_grid_spacing));
        gridView.setVerticalSpacing(getResources().getDimensionPixelSize(R.dimen.detail_screen_photo_grid_spacing));
        return gridView;
    }

    @Override
    protected void onCreateViewContent(LayoutInflater inflater, Bundle savedInstanceState) {
        mRootScrollingView.setOnItemClickListener(this);
        updateData(getDataProvider().getPhotos());
        getDataProvider().addPhotoUpdateObserver(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getDataProvider().removePhotoUpdateObserver(this);
    }

    public void updateData(List<DetailPhotoAdapter.Item> photos) {
        mRootScrollingView.setAdapter(new DetailPhotoAdapter(getActivity(), photos));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LogUtils.log("photo on item click " + position);
    }

    @Override
    public void onDataUpdate(List<DetailPhotoAdapter.Item> feedbackItemModelList) {
        updateData(feedbackItemModelList);
    }

    @Override
    public DetailsDataProvider getDataProvider() {
        Activity activity = getActivity();
        return ((IDetailDataProvider) activity).getDataProvider();
    }
}
