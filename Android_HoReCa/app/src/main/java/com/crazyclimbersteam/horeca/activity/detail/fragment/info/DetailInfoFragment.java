package com.crazyclimbersteam.horeca.activity.detail.fragment.info;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alexmirash.parallaxheaderviewpager.fragment.TabHolderScrollFragment;
import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.MenuActivity;
import com.crazyclimbersteam.horeca.activity.detail.DetailsActivity;
import com.crazyclimbersteam.horeca.activity.detail.IDetailDataProvider;
import com.crazyclimbersteam.horeca.activity.detail.dataprovider.DetailsDataProvider;
import com.crazyclimbersteam.horeca.activity.detail.fragment.info.view.DetailsInfoContactsView;
import com.crazyclimbersteam.horeca.fragment.detail.RequestSearchFragment;
import com.crazyclimbersteam.horeca.net.pojo.DetailItemModel;
import com.crazyclimbersteam.horeca.utils.LogUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;

/**
 * @author Mirash
 */
public class DetailInfoFragment extends TabHolderScrollFragment<ScrollView> implements IDetailDataProvider {
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private RatingBar mRatingBarView;
    private DetailsInfoContactsView mContactsView;

    private MapView mapView;
    private GoogleMap map;


    @Override
    protected ScrollView createRootScrollView(LayoutInflater inflater) {
        return new ScrollView(getActivity());
    }

    @Override
    protected View createViewContent(LayoutInflater inflater, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_tab_info, null);
        mTitleTextView = (TextView) rootView.findViewById(R.id.details_info_title);
        mContentTextView = (TextView) rootView.findViewById(R.id.details_info_description);
        mRatingBarView = (RatingBar) rootView.findViewById(R.id.details_info_rating_bar);
        mContactsView = (DetailsInfoContactsView) rootView.findViewById(R.id.details_info_contacts_view);
        initMenuButton(rootView);
        initMap(rootView, savedInstanceState);
        applyData();
        return rootView;
    }

    //TODO
    private void applyData() {
        if (getDataProvider().getDetailItem() != null) {
            DetailItemModel itemModel = getDataProvider().getDetailItem();
            mTitleTextView.setText(itemModel.getName());
            mContactsView.setPhone(itemModel.getTelephone());
            mContactsView.setAddress(itemModel.getAddress());
        }
    }

    private void initMenuButton(View rootView) {
        rootView.findViewById(R.id.detail_info_menu_section).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.log("menu button on click");
                Intent menuActivityIntent = new Intent(getActivity(), MenuActivity.class);
                menuActivityIntent.putExtras(MenuActivity.getIntentParams("1"));
                startActivity(menuActivityIntent);
            }
        });
    }

    private void initMap(View v, Bundle savedInstanceState) {
        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = getActivity().getIntent();
        //TODO remade me please
        if (intent.getSerializableExtra(DetailsActivity.ITEM_DETAIL_KEY) == null) {
            return;
        }
        final String name = intent.getStringExtra(RequestSearchFragment.NAME);
        final String lat = intent.getStringExtra(RequestSearchFragment.LAT);
        final String lng = intent.getStringExtra(RequestSearchFragment.LNG);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)), 10);
        map.animateCamera(cameraUpdate);
        map.getUiSettings().setScrollGesturesEnabled(false);
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MAP", "mapview click");
            }
        });
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=" + lat + "," + lng + "(" + name + ")"));
                startActivity(intent);
                Log.d("MAP", "map click");

            }
        });
    }

    // DO NOT DELETE THESE CALLS
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public DetailsDataProvider getDataProvider() {
        Activity activity = getActivity();
        return ((IDetailDataProvider) activity).getDataProvider();
    }
}
