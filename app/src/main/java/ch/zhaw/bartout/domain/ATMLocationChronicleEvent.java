package ch.zhaw.bartout.domain;

import java.util.ArrayList;
import java.util.List;

import se.walkercrou.places.Place;

/**
 * Created by Nico on 31.03.2015.
 */
public class ATMLocationChronicleEvent extends LocationChronicleEvent {

    public ATMLocationChronicleEvent() {}

    public ATMLocationChronicleEvent(Place place) {
        super(place);
    }

    @Override
    public String getDisplayName() {
        return "Bank besucht: " + getLocationName();
    }

    @Override
    protected String convertType(List<String> gTypes) {
        ArrayList<String> types = new ArrayList<String>();
        if (gTypes.contains("atm")) {
            types.add("Bankomat");
        }
        if (gTypes.contains("bank")) {
            types.add("Bank");
        }
        return types.toString().replace("[", "").replace("]", "");
    }
}
