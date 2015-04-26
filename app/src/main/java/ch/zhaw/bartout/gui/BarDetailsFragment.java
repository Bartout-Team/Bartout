package ch.zhaw.bartout.gui;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import ch.zhaw.bartout.R;
import ch.zhaw.bartout.domain.Establishment;
import se.walkercrou.places.GooglePlaces;


public class BarDetailsFragment extends Fragment {

    public static String ARGUMENT_PLACE_JSON = "Argument_Establishment";

    public static BarDetailsFragment getNewInstance(Establishment establishment) {
        BarDetailsFragment f = new BarDetailsFragment();
        Bundle b = new Bundle();
        b.putSerializable(BarDetailsFragment.ARGUMENT_PLACE_JSON, establishment);
        f.setArguments(b);
        return f;
    }

    private Establishment establishment;
    GooglePlaces client;

    public BarDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        client = new GooglePlaces(getString(R.string.google_places_api_key));

        View view =  inflater.inflate(R.layout.fragment_bar_details, container, false);

        FloatingActionButton navigateButton = (FloatingActionButton) view.findViewById(R.id.navigateButton);
        FloatingActionButton checkinButton = (FloatingActionButton) view.findViewById(R.id.checkinButton);

        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" +
                                establishment.getLatitude() + "," +
                                establishment.getLongitude()));
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle b = getArguments();
        if (b != null) {
            Establishment est = (Establishment) b.getSerializable(ARGUMENT_PLACE_JSON);
            establishment = est;
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            p.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            getView().setId(R.id.fragmentDetails);
            p.addRule(RelativeLayout.ALIGN_PARENT_START);
            getView().setLayoutParams(p);

            TextView textViewBarName = (TextView) getView().findViewById(R.id.textViewBarName);
            textViewBarName.setText(establishment.getName());

            TextView textViewType = (TextView) getView().findViewById(R.id.textViewType);
            textViewType.setText(establishment.getType());
        }
    }

}
