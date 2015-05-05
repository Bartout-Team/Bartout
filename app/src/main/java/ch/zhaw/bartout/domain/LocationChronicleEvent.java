package ch.zhaw.bartout.domain;

import java.util.List;

import se.walkercrou.places.Place;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class LocationChronicleEvent extends ChronicleEvent {

    private String type;
    private String locationName;
    private double latitude;
    private double longitude;
    private String address;

    /**
     * Create an empty LocationChronicleEvent
     */
    public LocationChronicleEvent() {}

    /**
     * Create a LocationChronicleEvent with populated data from the place
     * @param place A place from the Google Places API for which the event will be created
     */
    public LocationChronicleEvent(Place place) {
        type = convertType(place.getTypes());
        locationName = place.getName();
        address = place.getAddress();
        latitude = place.getLatitude();
        longitude = place.getLongitude();
    }

    public String getLocationName() {   return locationName;    }

    public void setLocationName(String locationName) {  this.locationName = locationName;   }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getType() {   return type;   }

    public void setType(String type) {  this.type = type;   }

    /**
     * Converts the type of the Google Places API into userfriendly text
     * @param gTypes The types of google places API
     * @return The types in a userfriendly text
     */
    protected abstract String convertType(List<String> gTypes);
}
