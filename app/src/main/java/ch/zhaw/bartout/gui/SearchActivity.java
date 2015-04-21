package ch.zhaw.bartout.gui;

import android.location.Location;
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

import ch.zhaw.bartout.R;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;


public class SearchActivity extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static String PIPE_CHAR = "%7C";

    public SearchActivity() {
        super(R.layout.activity_search);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected int getNameRes() {
        return R.string.title_search;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
    }

    /**
     * Button SetMark
     * @param view
     */
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
        Location loc = mMap.getMyLocation();
        if(loc == null) {
            loc = new Location("");
            loc.setLatitude(47.3777494);
            loc.setLongitude(8.532601);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(loc.getLatitude(), loc.getLongitude()), 15));
    }

    /**
     * Button SearchPlaces
     * @param view
     */
    public void searchPlaces(View view) {
        mMap.clear();
        Location loc = mMap.getMyLocation();
        if(loc == null) {
            loc = new Location("");
            loc.setLatitude(47.3777494);
            loc.setLongitude(8.532601);
        }
        new AsyncTask<Location, Void, Void>() {
            private List<Place> mPlaces;

            @Override
            protected Void doInBackground(Location... params) {
                GooglePlaces client = new GooglePlaces(getString(R.string.google_places_api_key));
                Location myPosition = params[0];
                mPlaces = client.getNearbyPlacesRankedByDistance(myPosition.getLatitude(), myPosition.getLongitude(), Param.name("types").value("bar"+PIPE_CHAR+"restaurant"));
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
        }.execute(loc);
    }
}