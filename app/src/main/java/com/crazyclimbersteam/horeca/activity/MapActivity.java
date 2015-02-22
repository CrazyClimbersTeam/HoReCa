package com.crazyclimbersteam.horeca.activity;

import com.crazyclimbersteam.horeca.fragment.map.NewMapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * Created by Марковка on 22.02.2015.
 */
public class MapActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private NewMapFragment mapFragment;



    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }
}
