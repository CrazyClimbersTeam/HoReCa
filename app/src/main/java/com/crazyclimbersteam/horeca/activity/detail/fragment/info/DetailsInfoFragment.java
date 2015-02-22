package com.crazyclimbersteam.horeca.activity.detail.fragment.info;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.activity.MenuActivity;
import com.crazyclimbersteam.horeca.activity.detail.fragment.DetailsTabFragment;
import com.crazyclimbersteam.horeca.activity.detail.fragment.info.view.DetailsInfoContactsView;
import com.crazyclimbersteam.horeca.fragment.detail.RequestSearchFragment;
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
public class DetailsInfoFragment extends DetailsTabFragment {
    private TextView mTitleTextView;
    private TextView mContentTextView;
    private RatingBar mRatingBarView;
    private ImageView mMapPreview;
    private DetailsInfoContactsView mContactsView;
    private View mMenuButton;

    private MapView mapView;
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_tab_info, container, false);
        initViews(rootView, savedInstanceState);
        return rootView;
    }

    private void initViews(View rootView, Bundle savedInstanceState) {
        mTitleTextView = (TextView) rootView.findViewById(R.id.details_info_title);
        mContentTextView = (TextView) rootView.findViewById(R.id.details_info_description);
        mRatingBarView = (RatingBar) rootView.findViewById(R.id.details_info_rating_bar);
        mContactsView = (DetailsInfoContactsView) rootView.findViewById(R.id.details_info_contacts_view);
        initMenuButton(rootView);
        map(rootView, savedInstanceState);
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

    //TODO
    private void applyData() {
    }

    private void map(View v, Bundle savedInstanceState){
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
        final String name = getActivity().getIntent().getStringExtra(RequestSearchFragment.NAME);
        final String lat = getActivity().getIntent().getStringExtra(RequestSearchFragment.LAT);
        final String lng = getActivity().getIntent().getStringExtra(RequestSearchFragment.LNG);
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

}
