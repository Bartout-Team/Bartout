package ch.zhaw.bartout.gui;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ch.zhaw.bartout.R;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;


public class BarDetailsFragment extends Fragment {

    public static String ARGUMENT_PLACE_JSON = "Argument_Place_Json";

    public static BarDetailsFragment getNewInstance(Place place){
        BarDetailsFragment f = new BarDetailsFragment();
        Bundle b = new Bundle();
        b.putString(BarDetailsFragment.ARGUMENT_PLACE_JSON, place.getJson().toString());
        f.setArguments(b);
        return f;
    }

    public static BarDetailsFragment getNewInstance(String placeId){
        BarDetailsFragment f = new BarDetailsFragment();
        Bundle b = new Bundle();
        b.putString(BarDetailsFragment.ARGUMENT_PLACE_JSON, placeId);
        f.setArguments(b);
        return f;
    }

    private Place place;
    GooglePlaces client;

    public BarDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        client = new GooglePlaces(getString(R.string.google_places_api_key));
        return inflater.inflate(R.layout.fragment_bar_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle b = getArguments();
        if(b != null) {
            String placeJson = b.getString(ARGUMENT_PLACE_JSON);

            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            p.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            getView().setId(R.id.fragmentDetails);
            p.addRule(RelativeLayout.ALIGN_PARENT_START);
            getView().setLayoutParams(p);

            TextView textViewBarName = (TextView) getView().findViewById(R.id.textViewBarName);
            textViewBarName.setText(placeJson);
        }
    }

}
