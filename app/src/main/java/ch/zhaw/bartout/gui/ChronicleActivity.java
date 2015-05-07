package ch.zhaw.bartout.gui;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.ATMLocationChronicleEvent;
import ch.zhaw.bartout.domain.Bartour;
import ch.zhaw.bartout.domain.Bartout;
import ch.zhaw.bartout.domain.ChronicleEvent;
import ch.zhaw.bartout.domain.LocationChronicleEvent;


public class ChronicleActivity extends BaseActivity {

    private Bartour bartour;
    private ChronicleAdapter chronicleAdapter;
    private GoogleMap map;

    public static final String CHRONICLE_EXTRA_BARTOUR = "chronicle_extra_bartour";

    public ChronicleActivity(){
        super(R.layout.activity_chronicle);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ActionBar actionBar = getActionBar();
        super.onCreate(savedInstanceState);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText(getText(R.string.timeline_name)).setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                findViewById(R.id.list_view).setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                findViewById(R.id.list_view).setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        }));
        actionBar.addTab(actionBar.newTab().setText(getText(R.string.map_name)).setTabListener(new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                findViewById(R.id.map).setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                findViewById(R.id.map).setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        }));
        findViewById(R.id.map).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onResume(){
        super.onResume();
        android.content.Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b != null && b.containsKey(CHRONICLE_EXTRA_BARTOUR)) {
            bartour = (Bartour) b.getSerializable(CHRONICLE_EXTRA_BARTOUR);
        } else {
            bartour = Bartout.getInstance().getActiveBartour();
        }
        ListView listView = (ListView) findViewById(R.id.list_view);

        chronicleAdapter = new ChronicleAdapter(
                this,
                bartour.getChronicle().getChronicleEvents()
        );
        listView.setAdapter(chronicleAdapter);
        fillMap();

    }

    private void fillMap(){
        final MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.setMyLocationEnabled(false);
                map.getUiSettings().setTiltGesturesEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                map.getUiSettings().setMapToolbarEnabled(false);
                map.clear();
                boolean first = true;
                PolylineOptions line = new PolylineOptions();
                map.addPolyline(line);
                for(ChronicleEvent event : bartour.getChronicle().getChronicleEvents(LocationChronicleEvent.class)){
                    LocationChronicleEvent locationEvent = (LocationChronicleEvent)event;
                    if(first){
                        first = false;
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locationEvent.getLatitude(), locationEvent.getLongitude()), 15));
                    }
                    MarkerOptions marker = new MarkerOptions();
                    LatLng latLng = new LatLng(locationEvent.getLatitude(), locationEvent.getLongitude());
                    marker.title(locationEvent.getLocationName().toString())
                            .position(latLng);
                    if(locationEvent instanceof ATMLocationChronicleEvent) {
                        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_money_pin));
                    }else {
                        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_beer_pin));
                    }
                    map.addMarker(marker);
                    line.add(latLng);
                }
                map.addPolyline(line);

            }
        });
    }

    @Override
    protected int getNameRes() {
        return R.string.title_chronicle;
    }
}
