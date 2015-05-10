package ch.zhaw.bartout.gui;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.bartour.Bartour;
import ch.zhaw.bartout.domain.Bartout;
import ch.zhaw.bartout.domain.bartour.chronicle.EstablishmentLocationChronicleEvent;
import ch.zhaw.bartout.domain.bartour.chronicle.LocationChronicleEvent;
import se.walkercrou.places.GooglePlaces;


public class BarDetailsFragment extends Fragment {

    public static String ARGUMENT_PLACE_JSON = "Argument_LocationChronicleEvent";

    public static BarDetailsFragment getNewInstance(LocationChronicleEvent locationChronicleEvent) {
        BarDetailsFragment f = new BarDetailsFragment();
        Bundle b = new Bundle();
        b.putSerializable(BarDetailsFragment.ARGUMENT_PLACE_JSON, locationChronicleEvent);
        f.setArguments(b);
        return f;
    }

    private LocationChronicleEvent locationChronicleEvent;
    private Bartour bartour;
    GooglePlaces client;

    public BarDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bartour = Bartout.getInstance().getActiveBartour();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        client = new GooglePlaces(getString(R.string.google_places_api_key));

        View view =  inflater.inflate(R.layout.fragment_bar_details, container, false);

        FloatingActionButton navigateButton = (FloatingActionButton) view.findViewById(R.id.navigateButton);
        FloatingActionButton checkInButton = (FloatingActionButton) view.findViewById(R.id.checkInButton);

        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" +
                                locationChronicleEvent.getLatitude() + "," +
                                locationChronicleEvent.getLongitude()));
                startActivity(intent);
            }
        });

        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bartour != null){
                    bartour.getChronicle().addEvent(locationChronicleEvent);
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), getString(R.string.toast_location_check_in_success), Toast.LENGTH_LONG);
                    toast.show();
                    if(locationChronicleEvent instanceof EstablishmentLocationChronicleEvent) {
                        Intent intent = new Intent(getActivity(), DrinkActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), getString(R.string.toast_location_check_in_fail), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        setCheckInButtonColor(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle b = getArguments();
        if (b != null) {
            LocationChronicleEvent loc = (LocationChronicleEvent) b.getSerializable(ARGUMENT_PLACE_JSON);
            locationChronicleEvent = loc;
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            p.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            //getView().setId(R.id.fragmentDetails);
            p.addRule(RelativeLayout.ALIGN_PARENT_START);
            getView().setLayoutParams(p);

            TextView textViewBarName = (TextView) getView().findViewById(R.id.textViewBarName);
            textViewBarName.setText(locationChronicleEvent.getLocationName());

            TextView textViewType = (TextView) getView().findViewById(R.id.textViewType);
            textViewType.setText(locationChronicleEvent.getType());
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        bartour = Bartout.getInstance().getActiveBartour();
        setCheckInButtonColor(getView());

    }

    private void setCheckInButtonColor(View view) {
        FloatingActionButton checkInButton = (FloatingActionButton) view.findViewById(R.id.checkInButton);
        if(bartour == null) {
            checkInButton.setColorNormal(Color.GRAY);
            checkInButton.setColorPressed(Color.GRAY);
            checkInButton.setColorRipple(Color.GRAY);
        } else {
            checkInButton.setColorNormal(Color.parseColor("#4CAF50"));
            checkInButton.setColorPressed(Color.parseColor("#2E7D32"));
            checkInButton.setColorRipple(Color.parseColor("#43A047"));
        }
    }

}
