package ch.zhaw.bartout.domain.bartour.chronicle;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ch.zhaw.bartout.R;
import se.walkercrou.places.Place;

/**
 * Created by Nico on 31.03.2015.
 */
public class EstablishmentLocationChronicleEvent extends LocationChronicleEvent {

    public EstablishmentLocationChronicleEvent() {}

    @Override
    public String getDisplayName() {
        return getLocationName() + " besucht";
    }

    public EstablishmentLocationChronicleEvent(Place place) {
        super(place);
    }

    protected String convertType(List<String> gTypes) {
        ArrayList<String> types = new ArrayList<String>();
        if (gTypes.contains("night_club")) {
            types.add("Nachtclub");
        }
        if (gTypes.contains("restaurant")) {
            types.add("Restaurant");
        }
        if (gTypes.contains("food")) {
            types.add("Essen");
        }
        if (gTypes.contains("bar")) {
            types.add("Bar");
        }
        return types.toString().replace("[", "").replace("]", "");
    }

    @Override
    public View getView(Context context){
        View view = super.getView(context);

        ImageView img = (ImageView) view.findViewById(R.id.image_icon);
        img.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_beer_pin));

        return view;
    }
}
