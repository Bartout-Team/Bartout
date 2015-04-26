package ch.zhaw.bartout.domain;

import android.location.Location;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import se.walkercrou.places.Place;

/**
 * Created by srueg on 25.04.15.
 */
public class Establishment implements Serializable {

    private String id;
    private String name;
    private String address;
    private String type;

    public Establishment() {
    }

    public Establishment(Place place) {
        id = place.getPlaceId();
        name = place.getName();
        address = place.getAddress();
        type = getType(place.getTypes());
    }

    private String getType(List<String> gTypes) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private Location location;
    private String openingHours;

}
