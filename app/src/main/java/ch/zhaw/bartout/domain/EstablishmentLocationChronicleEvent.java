package ch.zhaw.bartout.domain;

import java.util.ArrayList;
import java.util.List;

import se.walkercrou.places.Place;

/**
 * Created by Nico on 31.03.2015.
 */
public class EstablishmentLocationChronicleEvent extends LocationChronicleEvent {
    private String type;

    public EstablishmentLocationChronicleEvent() {}

    @Override
    public String getDisplayName() {
        return getLocationName() + " besucht";
    }

    public EstablishmentLocationChronicleEvent(Place place) {
        super(place);
        type = convertType(place.getTypes());
    }

    public String getType() {   return type;   }

    public void setType(String type) {  this.type = type;   }

    private String convertType(List<String> gTypes) {
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
        if (gTypes.contains("atm")) {
            types.add("Bankomat");
        }
        if (gTypes.contains("bank")) {
            types.add("Bank");
        }
        return types.toString().replace("[", "").replace("]", "");
    }

}
