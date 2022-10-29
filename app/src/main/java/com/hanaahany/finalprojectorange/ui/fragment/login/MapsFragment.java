package com.hanaahany.finalprojectorange.ui.fragment.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.location.Address;
import android.location.Geocoder;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.hanaahany.finalprojectorange.R;

import java.util.List;
import java.util.Locale;

public class MapsFragment extends Fragment {
    private static final String TAG = "MapsFragment";
    TextView textView;
    MaterialButton button;
    private GoogleMap mMap;
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
            textView=getView().findViewById(R.id.tv_location_address);
            button=getView().findViewById(R.id.btn_set_maps_fragment);
            mMap = googleMap;
            LatLng home = new LatLng(30.0611763, 31.4335013);

            mMap.addMarker(new MarkerOptions().position(home).title("My Home"));
            // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(home,12));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(home,16));
            //Log.i(TAG, "onMapReady: "+mMap.getMyLocation());

            Geocoder gcd = new Geocoder(getContext(),
                    Locale.getDefault());
            List<Address> addresses;
            try {

                addresses = gcd.getFromLocation(home.latitude,
                        home.longitude, 1);
                if (addresses.size() > 0) {
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String locality = addresses.get(0).getLocality();
                    String subLocality = addresses.get(0).getSubLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();

                    textView.setText(knownName+","+subLocality+","+locality+","+address+","+country);
                    Log.i(TAG, "onMapReady: "+country);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            NavController navController= Navigation.findNavController(view);
                            navController.navigate(R.id.action_mapsFragment_to_congratsFragment);
                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(HomeActivity.this, Constants.ToastConstatnts.ERROR_FETCHING_LOCATION, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }





}