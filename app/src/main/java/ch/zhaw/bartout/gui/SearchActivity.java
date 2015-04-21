package ch.zhaw.bartout.gui;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Objects;

import ch.zhaw.bartout.R;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;


public class SearchActivity extends BaseActivity {
    private static String PIPE_CHAR = "%7C";

    private GoogleMap mMap;
    private LocationManager locationManager;

    public SearchActivity() {
        super(R.layout.activity_search);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setTiltGesturesEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mMap.getUiSettings().setMapToolbarEnabled(false);

                Location location = getCurrentLocation();
                showLocation(location);
                searchPlaces(location);
            }
        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected int getNameRes() {
        return R.string.title_search;
    }

    public void setMark(View view) {
        Location loc = mMap.getMyLocation();
        if(loc == null) {
            loc = new Location("");
            loc.setLatitude(47.3777494);
            loc.setLongitude(8.532601);
        }
        MarkerOptions marker = new MarkerOptions()
                .position(new LatLng(loc.getLatitude(), loc.getLongitude()))
                .title("Mark");
        mMap.addMarker(marker);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 15));
    }

    /**
     * Button locateMe
     * @param view
     */
    public void locateMeOnClick(View view){
        showLocation(getCurrentLocation());
    }

    private void showLocation(Location location){
        float zoomLevel = mMap.getCameraPosition().zoom;
        if(zoomLevel < 12) zoomLevel = 15;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), zoomLevel));
    }

    private Location getCurrentLocation(){
        Location loc = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
        return loc;
    }

    /**
     * Button filter
     * @param view
     */
    public void filterOnClick(View view){

    }

    private void searchPlaces(Location loc) {
        mMap.clear();
        new AsyncTask<Object, Void, Void>() {
            private List<Place> mPlaces;

            @Override
            protected Void doInBackground(Object... params) {
                GooglePlaces client = new GooglePlaces(getString(R.string.google_places_api_key));
                Location myPosition = (Location) params[0];
                String filter = (String) params[1];
                mPlaces = client.getNearbyPlacesRankedByDistance(myPosition.getLatitude(), myPosition.getLongitude(), Param.name("types").value(filter));
                return null;
            }

            protected void onPostExecute(Void result) {
                for (se.walkercrou.places.Place p : mPlaces) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.title(p.getName().toString())
                            .position(new LatLng(p.getLatitude(), p.getLongitude()))
                            .snippet(p.getTypes().toString());
                    mMap.addMarker(markerOptions);
                }
            }
        }.execute(loc, "bar"+PIPE_CHAR+"restaurant");
    }
}