package ch.zhaw.bartout.gui;

import android.app.Fragment;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.zhaw.bartout.R;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;


public class SearchActivity extends BaseActivity {
    private String filter = "bar";
    private GoogleMap map;
    private LocationManager locationManager;
    private Map<MarkerOptions, Place> places = new HashMap<MarkerOptions, Place>();
    private BarDetailsFragment detailsFragment = new BarDetailsFragment();

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
                map = googleMap;
                map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                    @Override
                    public void onCameraChange(CameraPosition cameraPosition) {
                        setMark(cameraPosition.target, "Me");
                    }
                });
                map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                    @Override
                    public void onMapLongClick(LatLng latLng) {
                        setMark(latLng, getString(R.string.search_from_here));
                        Location loc = new Location("");
                        loc.setLatitude(latLng.latitude);
                        loc.setLongitude(latLng.longitude);
                        searchPlaces(loc);
                    }
                });
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        //marker.showInfoWindow();
                        //if (places.keySet(). (marker)) {
                        showDetails(places.get(marker));
                        //    }
                        return true;
                    }
                });
                map.setMyLocationEnabled(true);
                map.getUiSettings().setTiltGesturesEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                map.getUiSettings().setMapToolbarEnabled(false);

                Location location = getCurrentLocation();
                showLocation(location);
                searchPlaces(location);
            }
        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getNameRes() {
        return R.string.title_search;
    }

    private MarkerOptions setMark(LatLng location, String title) {
        MarkerOptions marker = new MarkerOptions()
                .position(location)
                .title(title);
        map.addMarker(marker);
        return marker;
    }

    /**
     * Button locateMe
     *
     * @param view
     */
    public void locateMeOnClick(View view) {
        showLocation(getCurrentLocation());
    }

    private void showLocation(Location location) {
        float zoomLevel = map.getCameraPosition().zoom;
        if (zoomLevel < 12) zoomLevel = 15;
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), zoomLevel));
    }

    private Location getCurrentLocation() {
        Location loc = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
        return loc;
    }

    /**
     * Button filter
     *
     * @param view
     */
    public void filterOnClick(View view) {
        map.clear();
        SearchFilterFragment filterFragment = SearchFilterFragment.newInstance(filter);
        filterFragment.attatch(new SearchFilterFragment.NoticeDialogListener() {
            @Override
            public void onDialogPositiveClick(String filter) {
                SearchActivity.this.filter = filter;
            }
        });
        filterFragment.show(getFragmentManager(), "searchFilter");
    }

    private void searchPlaces(Location loc) {
        AsyncTask<Location, Void, Void> types = new AsyncTask<Location, Void, Void>() {
            private List<Place> places;

            @Override
            protected Void doInBackground(Location... params) {
                GooglePlaces client = new GooglePlaces(getString(R.string.google_places_api_key));
                Location myPosition = params[0];
                places = client.getNearbyPlacesRankedByDistance(myPosition.getLatitude(), myPosition.getLongitude(), Param.name("types").value(SearchActivity.this.filter));
                return null;
            }

            protected void onPostExecute(Void result) {
                for (Place place : places) {
                    if (!SearchActivity.this.places.containsValue(place)) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.title(place.getName().toString())
                                .position(new LatLng(place.getLatitude(), place.getLongitude()))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_beer_pin));
                        SearchActivity.this.places.put(markerOptions, place);
                        map.addMarker(markerOptions);
                    }
                }
            }
        }.execute(loc);
    }

    private void showDetails(Place place) {
        Fragment f = getFragmentManager().findFragmentById(R.id.fragmentDetails);
        if (f.isVisible()) {
            getFragmentManager().beginTransaction()
                    .hide(f)
                    .commit();
        } else {
            getFragmentManager().beginTransaction()
                    .show(f)
                    .commit();
        }
    }

    public void testOnClick(View view) {
        Fragment f = getFragmentManager().findFragmentById(R.id.fragmentDetails);
        getFragmentManager().beginTransaction()
                .hide(f)
                .commit();
    }
}