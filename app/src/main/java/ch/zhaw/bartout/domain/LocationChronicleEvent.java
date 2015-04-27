package ch.zhaw.bartout.domain;

import android.location.Location;

import se.walkercrou.places.Place;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class LocationChronicleEvent extends ChronicleEvent {

    private String locationName;
    private double latitude;
    private double longitude;
    private String address;

    public LocationChronicleEvent() {}

    public LocationChronicleEvent(Place place) {
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
}
