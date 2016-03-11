package com.nikhil.intelimentapp.Activities;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nikhil.intelimentapp.Model.CityModel;
import com.nikhil.intelimentapp.R;
import com.nikhil.intelimentapp.Utility.Constants;
import com.nikhil.intelimentapp.Utility.UIUtil;

public class CityMapLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CityModel cityModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_map_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        cityModel = (CityModel) getIntent().getSerializableExtra(Constants.CITY);
        UIUtil.AppLog("City Details -= " + cityModel.toString());

        ((ImageView) findViewById(R.id.ivNavigation)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?daddr=" + cityModel.getLatitude() + "," + cityModel.getLongitude();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (!TextUtils.isEmpty(cityModel.getLatitude()) && !TextUtils.isEmpty(cityModel.getLongitude())) {

            LatLng latLng = new LatLng(Double.parseDouble(cityModel.getLatitude()), Double.parseDouble(cityModel.getLongitude()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15), 2000, null);
            mMap.addMarker(new MarkerOptions().position(latLng).title("" + cityModel.getName()).snippet("Car - " + cityModel.getCarMode() + "\n Train - " + cityModel.getTrainMode()));

        } else {
            UIUtil.ShowToastCenter(this, "Lat Long Not Present");
        }

    }
}
