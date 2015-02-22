package com.crazyclimbersteam.horeca.activity;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.crazyclimbersteam.horeca.R;
import com.crazyclimbersteam.horeca.fragment.map.NewMapFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Марковка on 22.02.2015.
 */
public class MapActivity extends Activity implements OnMapReadyCallback {

    private GoogleMap map;
    private NewMapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map_fragment_yalout);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);
        //map.setOnMyLocationChangeListener(myLocationChangeListener);
        getCurrentLocation();
    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            map.addMarker(new MarkerOptions().position(loc));
            if(map != null){
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
            }
        }
    };

    void getCurrentLocation() {
        Location myLocation  = map.getMyLocation();
        if(myLocation!=null)
        {
            double dLatitude = myLocation.getLatitude();
            double dLongitude = myLocation.getLongitude();
            Log.i("APPLICATION", " : " + dLatitude);
            Log.i("APPLICATION"," : "+dLongitude);
//            map.clear();
            map.addMarker(new MarkerOptions().position(
                    new LatLng(dLatitude, dLongitude)).title("My Location"));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dLatitude, dLongitude), 20));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(dLatitude, dLongitude), 20);
            map.animateCamera(cameraUpdate);
//            LatLng loc = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
//            if(map != null){
//                map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 80.0f));
//            }

        }
        else
        {
            Toast.makeText(this, "Unable to fetch the current location", Toast.LENGTH_SHORT).show();
        }

    }
}
