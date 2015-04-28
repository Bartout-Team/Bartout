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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.ATMLocationChronicleEvent;
import ch.zhaw.bartout.domain.EstablishmentLocationChronicleEvent;
import ch.zhaw.bartout.domain.LocationChronicleEvent;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;


public class SearchActivity extends BaseActivity {
    private static final String BAR_DETAILS_TAG = "BARDETIALS_TAG";

    private String filter = "bar";
    private GoogleMap map;
    private LocationManager locationManager;
    private Map<LatLng, Place> places = new HashMap<LatLng, Place>();

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
                        if (places.containsKey(marker.getPosition())) {
                            showDetails(places.get(marker.getPosition()));
                        }
                        return true;
                    }
                });
                map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        hideDetails();
                    }
                });
                map.setMyLocationEnabled(true);
                map.getUiSettings().setTiltGesturesEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                map.getUiSettings().setMapToolbarEnabled(false);

                Location location = getCurrentLocation();
                if(location != null){
                    showLocation(location);
                    searchPlaces(location);
                }
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
        String p = locationManager.getBestProvider(new Criteria(), true);
        if (p == null) {
            p = locationManager.getBestProvider(new Criteria(), false);
        }
        Location loc = locationManager.getLastKnownLocation(p);
        return loc;
    }

    /**
     * Button filter
     *
     * @param view
     */
    public void filterOnClick(View view) {
        map.clear();
        places.clear();
        SearchFilterFragment filterFragment = SearchFilterFragment.newInstance(filter);
        filterFragment.attatch(new SearchFilterFragment.NoticeDialogListener() {
            @Override
            public void onDialogPositiveClick(String filter) {
                SearchActivity.this.filter = filter;
                searchPlaces(getCurrentLocation());
            }
        });
        filterFragment.show(getFragmentManager(), "searchFilter");
    }

    private void searchPlaces(Location loc) {
        AsyncTask<Location, Void, Void> types = new AsyncTask<Location, Void, Void>() {
            private List<Place> places = new ArrayList<Place>();

            @Override
            protected Void doInBackground(Location... params) {
                try {
                    GooglePlaces client = new GooglePlaces(getString(R.string.google_places_api_key));
                    Location myPosition = params[0];
                    places = client.getNearbyPlacesRankedByDistance(myPosition.getLatitude(), myPosition.getLongitude(), Param.name("types").value(SearchActivity.this.filter));
                } catch (Exception ex) {
                    Logger.getAnonymousLogger().log(new LogRecord(Level.ALL, "No Internet"));
                }
                return null;
            }

            protected void onPostExecute(Void result) {
                for (Place place : places) {
                    if (!SearchActivity.this.places.containsValue(place)) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.title(place.getName().toString())
                                .position(new LatLng(place.getLatitude(), place.getLongitude()))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_beer_pin));
                        SearchActivity.this.places.put(markerOptions.getPosition(), place);
                        map.addMarker(markerOptions);
                    }
                }
            }
        }.execute(loc);

    }

    private void hideDetails() {
        Fragment f = getFragmentManager().findFragmentByTag(BAR_DETAILS_TAG);
        if (f != null) {
            getFragmentManager().beginTransaction()
                    .remove(f)
                    .commit();
        }
    }

    private void showDetails(Place place) {
        hideDetails();

        LocationChronicleEvent locationChronicleEvent;
        if(place.getTypes().contains("atm") || place.getTypes().contains("bank")) {
            locationChronicleEvent = new ATMLocationChronicleEvent();
        } else {
            locationChronicleEvent = new EstablishmentLocationChronicleEvent(place);
        }
        Fragment f = BarDetailsFragment.getNewInstance(locationChronicleEvent);

        getFragmentManager().beginTransaction()
                .add(R.id.relLayout, f, BAR_DETAILS_TAG)
                .commit();
    }

}
