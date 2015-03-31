package ch.zhaw.bartout.model;

import android.location.Location;

/**
 * Created by Nico on 31.03.2015.
 */
public abstract class LocationEvent extends Event {

    private String locationName;
    private Location location;

    public String getLocationName() {   return locationName;    }

    public Location getLocation() { return location;    }

    public void setLocationName(String locationName) {  this.locationName = locationName;   }

    public void setLocation(Location location) {    this.location = location;   }

}
