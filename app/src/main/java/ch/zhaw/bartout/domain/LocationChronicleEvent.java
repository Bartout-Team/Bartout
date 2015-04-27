package ch.zhaw.bartout.domain;

import android.location.Location;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class LocationChronicleEvent extends ChronicleEvent {

    private String locationName;
    private Location location;

    public String getLocationName() {   return locationName;    }

    public Location getLocation() { return location;    }

    public void setLocationName(String locationName) {  this.locationName = locationName;   }

    public void setLocation(double latitude, double longitude) {
        location = new Location("LocationChronicleEvent");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
    }

}
