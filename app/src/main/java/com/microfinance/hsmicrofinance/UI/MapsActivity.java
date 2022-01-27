package com.microfinance.hsmicrofinance.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.microfinance.hsmicrofinance.R;
import com.microfinance.hsmicrofinance.databinding.ActivityMapsBinding;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    public static final int LOCATION_CODE=10;
   // LatLng Githurai = new LatLng(-1.20615, 36.91593);
    //LatLng BarakaAlphaApartments = new LatLng(-1.13654,36.97125);
    MarkerOptions mGithurai = new MarkerOptions().position(new LatLng(-1.20615, 36.91593)).title("Kingdom Sacco");
    MarkerOptions mBaraAlpha = new MarkerOptions().position(new LatLng(-1.13654,36.97125)).title("Baraka Alpha Apartments");

    private ArrayList<LatLng> mLatLngslocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.setTitle("Back");
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(callback);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MapsActivity.this.finishAffinity();
        startActivity(new Intent(MapsActivity.this, HomeActivity.class));
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            ///
//            for (int i=0; i<mLatLngslocations.size();i++){
//                googleMap.addMarker(new MarkerOptions().position(mLatLngslocations.get(i)).title("Marker")
//                        .icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_baseline_flag_24)));
//                googleMap.animateCamera(CameraUpdateFactory.zoomTo(20.0f));
//                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLngslocations.get(i), 10));
//
//            }
            googleMap.addMarker(mGithurai.icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_baseline_flag_24)));
            googleMap.addMarker(mBaraAlpha.icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_baseline_flag_24)));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(20.0f));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mGithurai.getPosition(), 10));

        }
        private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
            // below line is use to generate a drawable.
            Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

            // below line is use to set bounds to our vector drawable.
            vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

            // below line is use to create a bitmap for our
            // drawable which we have added.
            Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

            // below line is use to add bitmap in our canvas.
            Canvas canvas = new Canvas(bitmap);

            // below line is use to draw our
            // vector drawable in canvas.
            vectorDrawable.draw(canvas);

            // after generating our bitmap we are returning our bitmap.
            return BitmapDescriptorFactory.fromBitmap(bitmap);
        }
    };
}