package org.pursuit.googlemapsapiexercise;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng flatbushBrooklyn = new LatLng(40.578490, -73.966480);
        mMap.addMarker(new MarkerOptions().position(flatbushBrooklyn).title("Marker in flatbush, Brooklyn"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(flatbushBrooklyn));



        Geocoder coder = new Geocoder(getApplicationContext());
        List<Address> address;
        LatLng p1 = null;
        try {
            // May throw an IOException
            address = coder.getFromLocationName("1313 Disneyland Dr, Anaheim, CA 92802", 5);
            if (address != null) {
                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(p1).title("Marker in Di").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_airplane)));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }



        Geocoder coder2 = new Geocoder(getApplicationContext());
        List<Address> address2;
        LatLng p2 = null;
        try {
            // May throw an IOException
            address2 = coder2.getFromLocationName("2 Walter Carrington Crescent Victoria Island, Lagos", 5);
            if (address2 != null) {
                Address location = address2.get(0);
                p2 = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(p2).title("Marker in Lagos, Nigeria").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_flag)));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }



        Geocoder coder3 = new Geocoder(getApplicationContext());
        List<Address> address3;
        LatLng p3 = null;
        try {
            // May throw an IOException
            address3 = coder3.getFromLocationName("777 Harrah's Blvd, Atlantic City, NJ 08401", 5);
            if (address3 != null) {
                Address location = address3.get(0);
                p3 = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(p3).title("Marker in Harrahs Hotel AC").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_hotel)));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }



        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1020);
        } else {
            mMap.setMyLocationEnabled(true);
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                double lat = location.getLatitude();
                                double lng = location.getLongitude();
                               // mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("My Current Location"));
                                mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title("My Current Location").icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_arrow)));
                            }
                        }
                    });
        }
    }
}
